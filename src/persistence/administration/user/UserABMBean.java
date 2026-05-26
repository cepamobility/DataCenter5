package persistence.administration.user;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.SessionBean;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import persistence.administration.bookmarks.tables.Bookmark;
import persistence.administration.corporation.tables.Corporation;
import persistence.administration.tables.Permission;
import persistence.administration.tables.Role;
import persistence.administration.tables.UserCorporation;
import persistence.administration.tables.UserCorporationPK;
import persistence.administration.user.tables.User;
import persistence.administration.user.tables.UserAccess;
import persistence.administration.user.tables.UserLogin;
import persistence.driver.tables.Driver;
import persistence.hibernate.HibernateUtil;
import persistence.log.user.UserCorporationLog;
import persistence.log.user.UserLog;
import persistence.util.ExceptionHandler;

import communication.administration.RoleData;
import communication.administration.bookmark.tables.BookmarkData;
import communication.administration.corporation.tables.CorporationData;
import communication.administration.user.UserSessionData;
import communication.administration.user.tables.UserAttributes;
import communication.administration.user.tables.UserData;
import communication.exception.AlreadyExistsException;
import communication.exception.CommunicationProblemException;
import communication.exception.DeslogedException;
import communication.exception.InvalidDataException;
import communication.util.LogActions;
import communication.util.MD5;

/**
 * @ejb.bean name="UserABM" display-name="Name for UserABM"
 *           description="Description for UserABM" jndi-name="ejb/UserABM"
 *           type="Stateless"  
 *  
 */
public abstract class UserABMBean implements SessionBean, Serializable {

    private ExceptionHandler handler = new ExceptionHandler(UserABMBean.class);
    
    /**
     * Init transaction
     * @ejb.create-method
     * @ejb.permission role-name = "administrator,systemaccess,usercreate,userdelete,userchangepassword,userupdate,userupdateaccess,userpermission,resetpassword"
     */
    public void ejbCreate()
        throws javax.ejb.CreateException {
    }

    /**
	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,usercreate"
	 * 
	 * @param data
	 * @param userRequest
	 * @throws Exception
	 */
	public void userCreate(UserData data,UserSessionData userRequest) throws Exception {
		try {
			//Check integration data
			if (userRequest == null) {
				throw new DeslogedException("Has ocurred error in class persistence.user.UserABM(userCreate), session data is empty","generic.error.deslogued");
			}
			if (data == null) {
				throw new InvalidDataException("Has ocurred error in class persistence.user.UserABM(userCreate), user data is empty","generic.user.userdata.isempty");
			}
			if(!validateUserCreateData(data,userRequest)){
				throw new InvalidDataException("Has ocurred error in class persistence.user.UserABM(userCreate), user data is invalid","generic.user.userdata.isinvalid");
			}

			//Execute operations
			Session session = HibernateUtil.currentSession();            
			String loginName = data.getLoginName().trim();
			String pass = new MD5().encriptar(data.getPassword());
			String firstName = data.getFirstName().trim();
			String lastName = data.getLastName().trim();
			String mail = data.getEmail().trim();
			String language = data.getLanguage().trim();
						
			UserAttributes attributesEncript = new UserAttributes(pass, firstName,lastName,language,loginName,mail,userRequest.getFilter().getCorporationId(), data.getRoleName());

			User userCreating = new User(attributesEncript);
			
			// Si es un usuario contratista, seteo el id y nombre del contratista:
			if (data.getContractorId() != null){
				userCreating.setContractorId(data.getContractorId());
			}
			
			session.save(userCreating);
            
            if(userRequest.getFilter().getCorporationId().intValue() == CorporationData.JJ) {
                UserAccess access = new UserAccess(loginName, true, new Date());
                session.save(access);
            }

			UserLog log = new UserLog(userCreating,userRequest.getFilter().getLoginName(),new Date(),LogActions.CREATE);
			session.save(log);

			Corporation corporation=(Corporation)session.load(Corporation.class,userRequest.getFilter().getCorporationId());
			UserCorporation userCorporation=new UserCorporation(userCreating,corporation);
			Set permissions = new HashSet();
			Enumeration enum1 = data.getPermissions().keys();
			while(enum1.hasMoreElements()) {
			    permissions.add((Permission)session.load(Permission.class,(String)enum1.nextElement()));
			}
			userCorporation.setPermissions(permissions);
			userCorporation.setRole((Role)session.load(Role.class,data.getRole().getName()));
			session.save(userCorporation);

			UserCorporationLog ucLog = new UserCorporationLog(userCorporation,userRequest.getFilter().getLoginName(),new Date(),LogActions.CREATE,session);
			session.save(ucLog);

		} catch (Exception e) {
			Object[] parameters = {data};
            handler.getException(e, "userCreate", userRequest,parameters);
        }
	}

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "resetpassword,usercreate,administrator"
     */
    public void createDriverUser(UserData data,UserSessionData userRequest) throws Exception {
        try {
            //Check integration data
            if (userRequest == null) {
                throw new DeslogedException("Has ocurred error in class persistence.user.UserABM(userCreate), session data is empty","generic.error.deslogued");
            }
            if (data == null) {
                throw new InvalidDataException("Has ocurred error in class persistence.user.UserABM(userCreate), user data is empty","generic.user.userdata.isempty");
            }
            if(!validateUserCreateData(data,userRequest)){
                throw new InvalidDataException("Has ocurred error in class persistence.user.UserABM(userCreate), user data is invalid","generic.user.userdata.isinvalid");
            }
            //check user exist
            if (userExist(data.getLoginName(), userRequest)) {
                throw new AlreadyExistsException("Has ocurred error in class persistence.user.UserABM(userCreate), user already exist","generic.user.alreadyexist");
            }

            //Check user permission for this operation
            
            //Execute operations
            Session session = HibernateUtil.currentSession();
            
            String loginName = data.getLoginName().trim();
            String pass = new MD5().encriptar(data.getPassword());
            String firstName = data.getFirstName().trim();
            String lastName = data.getLastName().trim();
            String mail = (data.getEmail() == null) ? null : data.getEmail().trim();
            String language = data.getLanguage().trim();
                        
            UserAttributes attributesEncript = new UserAttributes(pass, firstName,lastName,language,loginName,mail,data.getCorporationId(), data.getRoleName());

            User userCreating = new User(attributesEncript);
            Driver driver = (Driver)session.load(Driver.class,data.getDriverData().getId());
            userCreating.setDriver(driver);
            session.save(userCreating);

            UserLog log = new UserLog(userCreating,userRequest.getFilter().getLoginName(),new Date(),LogActions.CREATE);
            session.save(log);

            Corporation corporation=(Corporation)session.load(Corporation.class,data.getCorporationId());
            UserCorporation userCorporation=new UserCorporation(userCreating,corporation);
            Set permissions = new HashSet();
            Enumeration enum1 = data.getPermissions().keys();
            while(enum1.hasMoreElements()) {
                permissions.add((Permission)session.load(Permission.class,(String) enum1.nextElement()));
            }
            userCorporation.setPermissions(permissions);
            userCorporation.setRole((Role)session.load(Role.class,data.getRole().getName()));

            session.save(userCorporation);
            
            UserCorporationLog ucLog = new UserCorporationLog(userCorporation,userRequest.getFilter().getLoginName(),new Date(),LogActions.CREATE,session);
            session.save(ucLog);

        } catch (Exception e) {
			Object[] parameters = {data};
            handler.getException(e, "createDriverUser", userRequest, parameters);
        }
    }

	/**
	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,usercreate,userdelete,userchangepassword,userupdate,userupdateaccess"
	 * 
	 * @param data
	 * @param permission
	 * @param userRequest
	 * @throws Exception
	 */
	public void userUpdate(UserData data, UserSessionData userRequest, boolean validate) throws Exception {
		try {
			//Check integration data
			if (userRequest == null) {
				throw new DeslogedException("Class persistence.user.UserABM(userUpdate), session data is empty","generic.error.deslogued");
			}
			if (data == null) {
				throw new InvalidDataException("Class persistence.user.UserABM, user data is empty","generic.user.userdata.isempty");
			}

			//check user exist
			if (!userExist(data.getLoginName(), userRequest)) {
				throw new InvalidDataException("Class persistence.user.UserReport(userUpdate), user not exist","generic.user.notexist");
			}

			Session session = HibernateUtil.currentSession();
            
		    Corporation corporation = (Corporation)session.load(Corporation.class,userRequest.getFilter().getCorporationId());
			User user = (User)session.load(User.class,data.getLoginName());
			user.setCorporationId(corporation.getId());
			user.setEmail(data.getEmail());
			user.setFirstName(data.getFirstName());
			user.setLastName(data.getLastName());
			user.setLanguage(data.getLanguage());
			
		    UserCorporation userCorporation = new UserCorporation(user,corporation);
		    		    
			Set permissions = new HashSet();
			Enumeration enum1 = data.getPermissions().keys();
			while(enum1.hasMoreElements()) {
			    permissions.add((Permission)session.load(Permission.class,(String)enum1.nextElement()));
			}
			userCorporation.setPermissions(permissions);
			userCorporation.setRole((Role)session.load(Role.class,data.getRole().getName()));

			session.update(userCorporation);
		    
			logChanges(userRequest,session);
			
			session.update(user);
			
			UserLog log = new UserLog(user,userRequest.getFilter().getLoginName(),new Date(),LogActions.UPDATE);
			session.save(log);

		} catch (Exception e) {
			Object[] parameters = {data};
            handler.getException(e, "userUpdate", userRequest, parameters);
        }
	}

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,userpermission"
     * 
     * @param data
     * @param permission
     * @param userRequest
     * @throws Exception
     */
    public void userUpdateRole(String user, String roleName, Collection permissionKeys, UserSessionData userSessionData) throws Exception {
        try {
            //Check integration data
            if (userSessionData == null) {
                throw new DeslogedException("userUpdateRole","userSessionData = null");
            }
            if (user == null) {
                throw new InvalidDataException("userUpdateRole","user = null");
            }

            Session session = HibernateUtil.currentSession();           
            UserCorporation userCorporation = (UserCorporation)session.load(UserCorporation.class, new UserCorporationPK(user,userSessionData.getFilter().getCorporationId()));
                        
            Set permissions = new HashSet();
            Iterator it = permissionKeys.iterator();
            while(it.hasNext()) {
                permissions.add((Permission)session.load(Permission.class,(String)it.next()));
            }
            userCorporation.setPermissions(permissions);
            userCorporation.setRole((Role)session.load(Role.class,roleName));

            session.update(userCorporation);
            
            UserCorporationLog ucLog = new UserCorporationLog(userCorporation,userSessionData.getFilter().getLoginName(),new Date(),LogActions.CREATE,session);
            session.save(ucLog);
            
        } catch (Exception e) {
			Object[] parameters = {user,roleName,permissionKeys};
            handler.getException(e, "userUpdateRole", userSessionData, parameters);
        }
    }

	
	/**
	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,userchangepassword"
	 * 
	 * @param data
	 * @param userRequest
	 * @throws Exception
	 */
	public void userResetPassword(UserData data, UserSessionData userRequest) throws Exception {
		if (userRequest == null) {
			throw new DeslogedException("userResetPassword","session is null");
		}
		if (data == null) {
			throw new InvalidDataException("userResetPassword","data is null");
		}
		if (data.getPassword()==null) {
			throw new InvalidDataException("userResetPassword","password is null");
		}
		if (data.getLoginName()==null || data.getLoginName().trim().length()==0) {
			throw new InvalidDataException("userResetPassword","login is null");
		}
		if (!userExist(data.getLoginName(), userRequest)) {
			throw new InvalidDataException("userResetPassword","user does not exists");
		}
		modifyPassword(data.getLoginName().trim(),data.getPassword(),userRequest);
	}
	
	/**
	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,systemaccess"
	 * Modify the password of the logged user.
	 * @param password	- New Password
	 * @param userRequest	- Logged user 	
	 * @throws Exception
	 */
	public void userResetOwnPassword(String password, UserSessionData userRequest) throws Exception {
		if (userRequest == null) {
			throw new DeslogedException("userResetOwnPassword","session is null");
		}
		if (password == null || password.trim().length() == 0) {
			throw new InvalidDataException("userResetOwnPassword","password is null");
		}
		modifyPassword(userRequest.getFilter().getLoginName(),password,userRequest);
	}

	/**
     * @param password
     * @param nick
     * @throws Exception
     */
    private void modifyPassword(String nick, String password, UserSessionData userRequest) throws Exception {
        try {
            Session session = HibernateUtil.currentSession();
            
			String pass = new MD5().encriptar(password);
			User user =null;
			user=(User)session.load(User.class,nick);
			user.setPassword(pass);
			session.update(user);

            UserAccess access = new UserAccess(nick,false,new Date());
            session.saveOrUpdate(access);
            
			UserLog log = new UserLog(user,userRequest.getFilter().getLoginName(),new Date(),LogActions.CREATE);
			session.save(log);

		} catch (Exception e) {
			Object[] parameters = {nick,password};
            handler.getException(e, "modifyPassword", userRequest,parameters);
        }
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,systemaccess"
	 * 
	 * @param lngName
	 * @param userRequest
	 * @throws Exception
	 */
	public void userSwitchLanguage(String lngName, UserSessionData userRequest) throws Exception {
		if(!isLanguage(lngName)){
			throw new InvalidDataException("userSwitchLanguage","invalid language");
		}
		if (userRequest == null) {
			throw new DeslogedException("userSwitchLanguage","session is null");
		}
		
		try {
            Session session = HibernateUtil.currentSession();
			String nick=userRequest.getFilter().getLoginName();
			User user =null;
			user=(User)session.load(User.class,nick);
			user.setLanguage(lngName);
			session.update(user);

			UserLog log = new UserLog(user,userRequest.getFilter().getLoginName(),new Date(),LogActions.CREATE);
			session.save(log);
		} catch (Exception e) {
			Object[] parameters = {lngName};
            handler.getException(e, "userSwitchLanguage", userRequest, parameters);
        }
	}

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,systemaccess"
     * 
     * @param corporationId
     * @param userRequest
     * @throws InvalidDataException
     * @throws DeslogedException
     * @throws CommunicationProblemException
     */
    public void userSwitchCorporation(Integer corporationId, UserSessionData userRequest) throws Exception {
        try {
            //Check integration parameters
            if(corporationId == null){
                throw new InvalidDataException("Has ocurred error in class persistence.user.UserABM(userSwitchLanguage), lngName is not a valid language name","persistence.user.userdata.corporationId.isinvalid");
            }
            
            //Check integration data
            if (userRequest == null) {
                throw new DeslogedException("Class persistence.user.UserABM(userSwitchCorporation), Session data is empty","generic.error.deslogued");
            }

        	Session session = HibernateUtil.currentSession();
            
            String nick=userRequest.getFilter().getLoginName();
            
            User user =null;
            
            user=(User)session.load(User.class,nick);
            
            if(userRequest.isContractor()) {
            	user.setContractorId(getNewContractor(user.getCorporationId(),corporationId.intValue(),user.getContractorId()));
            }
            user.setCorporationId(corporationId);

            session.update(user);
            
            
            
            UserLog log = new UserLog(user,userRequest.getFilter().getLoginName(),new Date(),LogActions.CREATE);
            session.save(log);

        }catch (Exception e) {
			Object[] parameters = {corporationId};
            handler.getException(e, "userSwitchCorporation", userRequest, parameters);
        }
    }

    private String getCorporationNameSQL(int corporation) {
    	switch(corporation) {
    		case CorporationData.FORESTALORIENTAL:
    			return "fosa";
    		case CorporationData.WEYERHAEUSER:
    			return "wy";
    		case CorporationData.MONTES_DE_PLATA:
    			return "mdp";
    	}
    	return null;

    }
	private Integer getNewContractor(int oldCorporation, int newCorporation, Integer contractor) throws Exception {
		Integer newId = null;
    	Session session = HibernateUtil.currentSession();
    	Query q = session.createSQLQuery("SELECT " + getCorporationNameSQL(newCorporation) + " AS corporation FROM transporterlinks WHERE " + getCorporationNameSQL(oldCorporation) + " = "+contractor).addScalar("corporation",Hibernate.STRING);
    	Iterator it = q.list().iterator();
    	if(it.hasNext()) {
    		newId = (Integer)it.next();
    	}
		return newId;
	}

	/**
	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,userdelete"
	 * 
	 * @param data
	 * @param userRequest
	 * @throws Exception
	 */
	public void userDelete(UserData data, UserSessionData userRequest) throws Exception {
		try {
			//Check integrity data
			if (data == null) {
				throw new InvalidDataException("Class persistence.user.UserABM(userDelete), user data is empty","generic.user.userdata.isempty");
			}
			if (data.getLoginName() == null) {
				throw new InvalidDataException("Class persistence.user.UserABM(userDelete), primary key is empty","persistence.user.userdata.primarykey.isempty");
			}
			if (userRequest == null) {
				throw new DeslogedException("Class persistence.user.UserABM(userDelete), session data is empty","generic.error.deslogued");
			}
			if (!userExist(data.getLoginName(), userRequest)) {
				throw new InvalidDataException("Class persistence.user.UserABM(userDelete), user not exist","generic.user.notexist");
			}

            Session session = HibernateUtil.currentSession();
            
			User user =null;

			Query queryBookmark = session.createQuery("SELECT bookmark FROM persistence.administration.bookmarks.tables.Bookmark AS bookmark WHERE userId.loginName = :user");
			queryBookmark.setParameter("user", data.getLoginName());
			Collection list = queryBookmark.list();
			if(list != null && list.size() > 0) {
				Iterator it = list.iterator();
				while (it.hasNext()) {
				    Bookmark bookmark = (Bookmark) it.next();
					session.delete(bookmark);
				}
			}
			
			Query query = session
			.createQuery("select userCorporation from persistence.administration.tables.UserCorporation as  userCorporation"
				    	+ " where loginname = :user");
	
			query.setParameter("user", data.getLoginName());

			List userColl = query.list();
			Iterator iter = userColl.iterator();

			while (iter.hasNext()) {
			    UserCorporation userCorporation = (UserCorporation) iter.next();

				userCorporation.setPermissions(new HashSet());
				session.delete(userCorporation);

				UserCorporationLog ucLog = new UserCorporationLog(userCorporation,userRequest.getFilter().getLoginName(),new Date(),LogActions.DELETE,session);
				session.save(ucLog);
			}
			
			user=(User)session.load(User.class, data.getLoginName());
			session.delete(user);

			UserLog log = new UserLog(user,userRequest.getFilter().getLoginName(),new Date(),LogActions.DELETE);
			session.save(log);
		
		} catch (Exception e) {
			Object[] parameters = {data};
            handler.getException(e, "userDelete", userRequest, parameters);
        }		
	}
	
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,userdelete"
     */
    public void deleteUserGroup(UserData data, UserSessionData userSessionData) throws Exception {
        try {
            //Check integrity data
            if (data == null) {
                throw new InvalidDataException("deleteUserGroup","data = null");
            }
            if (data.getLoginName() == null) {
                throw new InvalidDataException("deleteUserGroup","loginname = null");
            }
            if (userSessionData == null) {
                throw new DeslogedException("deleteUserGroup","userSessionData = null");
            }

            Session session = HibernateUtil.currentSession();

            Query query = session.createQuery("delete from persistence.administration.tables.UserGroup "
                        + " where loginname = :user");
            query.setParameter("user", data.getLoginName());

            query.executeUpdate();
                    
        } catch (Exception e) {
			Object[] parameters = {data};
            handler.getException(e, "deleteUserGroup", userSessionData, parameters);
        }       
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,usercreate,resetpassword"
     */
	public boolean userExist(String nick, UserSessionData userRequest) throws Exception {
		boolean result=false;
		
		try{
            Session session = HibernateUtil.currentSession();
            
			Query query=session.createQuery("select user from persistence.administration.user.tables.User as user " +
											"where lower(user.loginName)=lower(:nickname)");
			query.setParameter("nickname",nick);
			
			List users=query.list();
			if(users.size()==0){
				result=false;
			}
			else{
				result=true;
			}
			
		} catch (Exception e) {
			Object[] parameters = {nick};
            handler.getException(e, "userExists", userRequest, parameters);
        }
		return result;
	}
	
	/**
	 * @param lngName
	 * @return boolean indicate is lngName a valid language name
	 */
	private boolean isLanguage(String lngName){
		boolean result=false;
		
		try{
			//Language lng=(Language)session.load(Language.class,lngName);
			
			//The lngName is a valid language name
			
			result=true;
		} catch(HibernateException he){
			//The lngName is a not valid language name
			result=false;
		}
	
		return result;
	}
	
	public boolean validateUserCreateData(UserData data,UserSessionData userRequest){
		if (data.getPassword() == null || data.getPassword().trim().length() == 0) {
			return false;
		}

		if (data.getFirstName() == null
				|| data.getFirstName().trim().length() == 0) {
			return false;
		}

		if (data.getLastName() == null
				|| data.getLastName().trim().length() == 0) {
			return false;
		}
		if (data.getLanguage() == null
				|| data.getLanguage().trim().length() == 0) {
			return false;
		}

		return true;

	}

	private void logChanges(UserSessionData userRequest,Session session) {
	    UserCorporation userCorporation = (UserCorporation)session.load(UserCorporation.class,new UserCorporationPK(userRequest.getFilter().getLoginName(),userRequest.getFilter().getCorporationId()));
        UserCorporationLog ucLog = new UserCorporationLog(userCorporation,userRequest.getFilter().getLoginName(),new Date(),LogActions.CREATE,session);
        session.save(ucLog);
	}

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "resetpassword"
     */
    public void resetPassword(UserAttributes userData, String password, UserSessionData userSessionData) throws Exception {
        try {
            Session session = HibernateUtil.currentSession();
            User user = (User)session.load(User.class,userData.getLoginName());
            user.setPassword(password);
            session.save(user);
            
            Query query = session.createQuery("SELECT COUNT(*) FROM UserCorporation uc WHERE uc.pk.user = '"+userData.getLoginName()+"' AND uc.pk.corporation.id = "+CorporationData.JJ);
            Integer count = new Integer(((Long)query.uniqueResult()).intValue());
            if(count.intValue() != 0) {
                UserAccess access = new UserAccess(userData.getLoginName(),true,new Date());
                session.saveOrUpdate(access);
            }
            
        } catch (Exception e) {
			Object[] parameters = {userData,password};
            handler.getException(e,"resetPassword",userSessionData, parameters);
        }
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,systemaccess"
     */
    public void saveLogin(long date,UserSessionData userSessionData) throws Exception {
        try{    
            Session session = HibernateUtil.currentSession();
            UserLogin login = new UserLogin(userSessionData.getFilter().getLoginName(),String.valueOf(date));
            session.save(login);
        } catch (Exception e) {
			Object[] parameters = {date};
            handler.getException(e,"saveLogin",userSessionData,parameters);
        } 
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,systemaccess"
     */
    public void associateUserToCorporation(Integer corporationId,String userId,String roleId,UserSessionData userSessionData) throws Exception {
        try{    
            Session session = HibernateUtil.currentSession();

            Corporation corporation = (Corporation)session.load(Corporation.class,corporationId);
            User user = (User)session.load(User.class,userId);
            UserCorporation userCorporation=new UserCorporation(user,corporation);
            
            Role role = (Role)session.load(Role.class,roleId);
            userCorporation.setRole(role);
            
            Set permissions = new HashSet();
            Iterator it = role.getPermissions().iterator();
            while(it.hasNext()) {
                permissions.add(it.next());
            }
            userCorporation.setPermissions(permissions);

            session.save(userCorporation);
            
        } catch (Exception e) {
			Object[] parameters = {corporationId,userId,roleId};
            handler.getException(e,"associateUserToCorporation",userSessionData,parameters);
        } 
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,systemaccess"
     */
    public void addBookmarkItem(BookmarkData data,UserSessionData userSessionData) throws Exception {
        try{    
            Session session = HibernateUtil.currentSession();
            Bookmark bookmark = new Bookmark(data,userSessionData,session);
            session.save(bookmark);            
        } catch (Exception e) {
			Object[] parameters = {data};
            handler.getException(e,"addBookmarkItem",userSessionData,parameters);
        } 
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,systemaccess"
     */
    public void udpateDefaulttBookmarkItem(UserSessionData userSessionData) throws Exception {
        try{    
            Session session = HibernateUtil.currentSession();
            Query q = session.createQuery("UPDATE Bookmark SET start = 'false' WHERE userId = '"+userSessionData.getFilter().getLoginName()+"' AND corporation = "+String.valueOf(userSessionData.getFilter().getCorporationId()));
            q.executeUpdate();
        } catch (Exception e) {
			Object[] parameters = {};
            handler.getException(e,"udpateDefaulttBookmarkItem",userSessionData,parameters);
        } 
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,systemaccess"
     */
    public void udpateBookmarkItem(BookmarkData data,UserSessionData userSessionData) throws Exception {
        try{    
            Session session = HibernateUtil.currentSession();
            String sql = "UPDATE Bookmark SET name = '"+data.getName()+"', start =";
            sql += (data.isStart()) ? " 'true' " : " 'false' ";
            sql += " WHERE id = "+String.valueOf(data.getId());
            Query q = session.createQuery(sql);
            q.executeUpdate();
        } catch (Exception e) {
			Object[] parameters = {data};
            handler.getException(e,"udpateBookmarkItem",userSessionData,parameters);
        } 
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,systemaccess"
     */
    public void deleteBookmarkItem(String bookmarkId,UserSessionData userSessionData) throws Exception {
        try{    
            Session session = HibernateUtil.currentSession();
            Bookmark bookmark = (Bookmark)session.load(Bookmark.class,new Integer(bookmarkId));
            session.delete(bookmark);
        } catch (Exception e) {
			Object[] parameters = {bookmarkId};
            handler.getException(e,"deleteBookmarkItem",userSessionData,parameters);
        } 
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,systemaccess"
     */
    public void deleteDriverUser(Integer driverId,UserSessionData userSessionData) throws Exception {
        try{    
            Session session = HibernateUtil.currentSession();
			Query query = session.createQuery("SELECT uc FROM UserCorporation AS uc WHERE uc.pk.user IN (SELECT loginName FROM User WHERE driver = "+String.valueOf(driverId)+") AND uc.role = '"+RoleData.DRIVER+"'");
			Iterator it = query.list().iterator();
			while(it.hasNext()) {
				String login = ((UserCorporation)it.next()).getPk().getUser().getLoginName();
				
				Query q1 = session.createQuery("DELETE FROM UserGroup WHERE pk.user = '"+login+"'");
				q1.executeUpdate();

				userDelete(new UserData(login), userSessionData);
			}
        } catch (Exception e) {
			Object[] parameters = {driverId};
            handler.getException(e,"deleteDriverUser",userSessionData,parameters);
        } 
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,systemaccess"
     */
    public void deleteUserByDriver(Integer driverId,UserSessionData userSessionData) throws Exception {
        try{    
            Session session = HibernateUtil.currentSession();
			Query query = session.createQuery("SELECT uc FROM UserCorporation AS uc WHERE uc.pk.user IN (SELECT loginName FROM User WHERE driver = "+String.valueOf(driverId)+")");
			Iterator it = query.list().iterator();
			while(it.hasNext()) {
				String login = ((UserCorporation)it.next()).getPk().getUser().getLoginName();
				
				Query q1 = session.createQuery("DELETE FROM UserGroup WHERE pk.user = '"+login+"'");
				q1.executeUpdate();

				userDelete(new UserData(login), userSessionData);
			}
        } catch (Exception e) {
			Object[] parameters = {driverId};
            handler.getException(e,"deleteDriverUser",userSessionData,parameters);
        } 
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,usercreate"
     */
   public void userAddCorporation (String login, Integer coporationId, String roleName, UserSessionData userSessionData) throws Exception {
    	
		try {

			Session session = HibernateUtil.currentSession();

			Corporation corporation = (Corporation) session.load(Corporation.class,coporationId);
			User user = (User) session.load(User.class, login);
			Role role = (Role) session.load(Role.class, roleName);

			UserCorporation userCorporation = new UserCorporation(user, corporation);
			userCorporation.setRole(role);

            Set permissions = new HashSet();
            Iterator it = role.getPermissions().iterator();
            while(it.hasNext()) {
                permissions.add((Permission)it.next());
            }
            userCorporation.setPermissions(permissions);

			session.save(userCorporation);
			
		} catch (Exception e) {
        	Object[] parameters = {login,coporationId,roleName};
            handler.getException(e,"userAddCorporation",userSessionData,parameters);
        } 
	}
}