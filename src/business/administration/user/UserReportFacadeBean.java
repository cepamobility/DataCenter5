/*
 * Created on 22-ago-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package business.administration.user;

import java.util.Collection;
import java.util.LinkedList;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import persistence.administration.user.UserReport;
import persistence.administration.user.UserReportHome;
import persistence.administration.user.UserReportUtil;
import persistence.hibernate.HibernateUtil;
import business.util.ExceptionHandler;

import communication.administration.RoleData;
import communication.administration.bookmark.tables.BookmarkData;
import communication.administration.user.UserSessionData;
import communication.administration.user.tables.UserAttributes;
import communication.administration.user.tables.UserData;
import communication.exception.CommunicationProblemException;
import communication.exception.DeslogedException;
import communication.exception.InvalidDataException;
import communication.util.ListResult;

/**
 * @ejb.bean name="UserReportFacade" display-name="Name for UserReportFacade"
 *           description="Description for UserReportFacade"
 *           jndi-name="ejb/UserReportFacade" type="Stateless" view-type="remote"
 * 
 * @ejb.ejb-ref ejb-name = "UserReport" ref-name = "ejb/UserReport" view-type =
 *              "remote"
 * 
 * @jboss.ejb-ref-jndi jndi-name = "ejb/UserReport" ref-name = "UserReport"
 * 
 * @ejb.util generate="physical"
 *  
 */
public abstract class UserReportFacadeBean implements SessionBean {	
    private ExceptionHandler handler = new ExceptionHandler(UserABMFacadeBean.class);
    
	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 * @ejb.create-method
     * @ejb.permission role-name = "systemaccess,administrator,resetpassword"
	 */
	public void ejbCreate() throws CreateException {
		// TODO Auto-generated method stub

	}

	/**
	 * @ejb.interface-method
     * @ejb.permission role-name = "systemaccess,administrator,resetpassword"
	 * 
	 * @param userPK
	 * @return  UserData
 	 * @throws InvalidDataException
	 * @throws CommunicationProblemException
	 * @throws DeslogedException
	 */
	public UserData findUserByPrimaryKey(String userPK, UserSessionData userSessionData) throws Exception {
		
		UserData data=null;
		UserReport userReport=null;
		try {
			if (userPK == null) {
				throw new InvalidDataException("Class business.user.UserReportFacade(findUserByPrimaryKey), primary Key is empty","generic.user.");
			}
			
			if(userPK==null){
				throw new InvalidDataException("Class business.user.UserReportFacade(findUserByPrimaryKey), nick name is empty","generic.user.userdata.nickname.isempty");
			}

			UserReportHome urHome = UserReportUtil.getHome();
			userReport = urHome.create();
			data=userReport.findUserByPrimaryKey(userPK,userSessionData);
			
			return data;			
		} catch (Exception exception) {
			Object[] parameters = new Object[1];
			parameters[0] = userPK;
            handler.handleException("findUserByPrimaryKey", exception, userSessionData, parameters);
        } 
		return null;		
	}
	
	
	/**
	 * @ejb.interface-method
     * @ejb.permission role-name = "systemaccess,administrator"
	 * @param attrName
	 * @param attrValue
	 * @param first
	 * @param count
	 * @return
	 * @throws InvalidDataException
	 * @throws CommunicationProblemException
	 * @throws DeslogedException
	 */
	public ListResult findUserList(String attrName, String attrValue, int first, int count, 
			UserSessionData userSessionData, String roles, boolean cepaUser) throws Exception {
		try {
			if (attrName == null) {
				throw new InvalidDataException("findUserList", "AttrName == null");
			}
			UserReportHome userHome = UserReportUtil.getHome();
			UserReport userReport = userHome.create();
            int attribute = 0;
            if (attrName.equals("firstName")) {
                attribute = UserData.FIRST_NAME;
            }else if (attrName.equals("lastName")) {
                attribute = UserData.LAST_NAME;
            }else if (attrName.equals("nick")) {
                attribute = UserData.NICK_NAME;
            }else if (attrName.equals("mail")) {
                attribute = UserData.MAIL;
            }else if (attrName.equalsIgnoreCase("firstorlastname")){
            	attribute = UserData.FIRST_OR_LAST_NAME;
            }
            
			return userReport.findList(attrValue,userSessionData,attribute,first,count, roles, cepaUser);
		} catch (Exception exception) {
			Object[] parameters = new Object[4];
			parameters[0] = attrName;
			parameters[1] = attrValue;
			parameters[2] = first;
			parameters[3] = count;
            handler.handleException("findUserList", exception, userSessionData, parameters);
        } 
		return null;		
	}

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "resetpassword"
     */
    public UserAttributes findUserByEmail(String email, UserSessionData userSessionData) throws Exception {
        try {
            if (email == null) {
                throw new InvalidDataException("findUserByEmail", "email == null");
            }
            UserReportHome userHome = UserReportUtil.getHome();
            UserReport userReport = userHome.create();
            return userReport.findUserByEmail(email, userSessionData);
        } catch (Exception exception) {
        	Object[] parameters = new Object[1];
        	parameters[0] = email;
            handler.handleException("findUserByEmail", exception, userSessionData, parameters);
        } 
        return null;        
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,groupedit"
     * @return Group Data
     * @throws Exception
     */
    public Collection findGroups(String loginName,UserSessionData userSessionData) throws Exception {
        try {
            UserReportHome userHome = UserReportUtil.getHome();
            UserReport userReport = userHome.create();
            return userReport.findGroups(loginName, String.valueOf(userSessionData.getFilter().getCorporationId()),userSessionData);
        }catch (Exception e) {
        	Object[] parameters = new Object[1];
        	parameters[0] = loginName;
            handler.handleException("findGroups", e, userSessionData,parameters);
        }
        return null;
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,groupedit"
     * @return Group Data
     * @throws Exception
     */
    public Collection findGroupAttributes(String loginName,UserSessionData userSessionData) throws Exception {
        try {
            UserReportHome userHome = UserReportUtil.getHome();
            UserReport userReport = userHome.create();
            return userReport.findGroupAttributes(loginName, String.valueOf(userSessionData.getFilter().getCorporationId()),userSessionData);
        }catch (Exception e) {
        	Object [] parameters = new Object[1];
        	parameters[0] = loginName;
            handler.handleException("findGroupAttributes", e, userSessionData, parameters);
        }
        return null;
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,userpermission"
     * @return Group Data
     * @throws Exception
     */
    public RoleData findUserRole(String user,UserSessionData userSessionData) throws Exception {
        try {
            UserReportHome userHome = UserReportUtil.getHome();
            UserReport userReport = userHome.create();
            return userReport.findUserRole(user, userSessionData);
        }catch (Exception e) {
        	Object[] parameters = new Object[1];
        	parameters[0] = user;
            handler.handleException("findUserRole", e, userSessionData, parameters);
        }
        return null;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,userpermission"
     * @return Group Data
     * @throws Exception
     */
    public Collection findUserPermissions(String user,UserSessionData userSessionData) throws Exception {
        try {
            UserReportHome userHome = UserReportUtil.getHome();
            UserReport userReport = userHome.create();
            return userReport.findUserPermissions(user, userSessionData);
        }catch (Exception e) {
        	Object[] parameters = new Object[1];
        	parameters[0] = user;
            handler.handleException("findUserPermissions", e, userSessionData, parameters);
        }
        return null;
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,useredit"
     */
    public UserAttributes findUserAttributes(String loginName, UserSessionData userSessionData) throws Exception {
        try {
            if (loginName == null) {
                throw new InvalidDataException("findUserAttributes","loginName = null");
            }
            
            if(userSessionData==null){
                throw new DeslogedException("findUserAttributes","userSessionData = null");
            }

            UserReportHome urHome = UserReportUtil.getHome();
            UserReport userReport = urHome.create();
            return userReport.findUserAttributes(loginName,userSessionData);
            
        } catch (Exception exception) {
        	Object[] parameters = new Object[1];
        	parameters[0] = loginName;
            handler.handleException("findUserAttributes", exception, userSessionData, parameters);
        } 
        return null;
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,resetpassword, systemaccess"
     */
    public int getPasswordType(String loginName, UserSessionData userSessionData) throws Exception {
        try {
            if (loginName == null) {
                throw new InvalidDataException("getPasswordType","loginName = null");
            }
            
            if(userSessionData==null){
                throw new DeslogedException("getPasswordType","userSessionData = null");
            }

            UserReportHome urHome = UserReportUtil.getHome();
            UserReport userReport = urHome.create();
            return userReport.getPasswordType(loginName,userSessionData);
            
        } catch (Exception exception) {
        	Object[] parameters = new Object[1];
        	parameters[0] = loginName;
            handler.handleException("getPasswordType", exception, userSessionData, parameters);
        } 
        return UserAttributes.DIFFICULT;
    }

    /**
     * @throws Exception 
     * @ejb.interface-method
     * @ejb.permission role-name = "systemaccess"
     */
    public boolean isFirstAccess(String user) throws Exception {
        try {
            UserReportHome urHome = UserReportUtil.getHome();
            UserReport userReport = urHome.create();
            return userReport.isFirstAccess(user);            
        } catch (Exception exception) {
        	Object[] parameters = new Object[1];
        	parameters[0] = user;
            handler.handleException("isFirstAccess", exception, null, parameters);
        }
        return false; 
    }

    /**
     * @throws Exception 
     * @ejb.interface-method
     * @ejb.permission role-name = "systemaccess,resetpassword"
     */
    public boolean isMultipleCorporation(String user) throws Exception {
        try {
            UserReportHome urHome = UserReportUtil.getHome();
            UserReport userReport = urHome.create();
            return userReport.isMultipleCorporation(user);            
        } catch (Exception exception) {
        	Object[] parameters = new Object[1];
        	parameters[0] = user;
            handler.handleException("isMultipleCorporation", exception, null, parameters);
        }
        return false; 
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "systemaccess"
     */
    public String[][] getBookmark(UserSessionData userSessionData) throws Exception {
        try {
            UserReportHome urHome = UserReportUtil.getHome();
            UserReport userReport = urHome.create();
            return userReport.getBookmark(userSessionData);            
        } catch (Exception exception) {
            handler.handleException("getBookmark", exception, userSessionData, null);
        }
        return new String[0][0]; 
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "systemaccess"
     */
    public BookmarkData getBookmarkData(Integer id,UserSessionData userSessionData) throws Exception {
        try {
            UserReportHome urHome = UserReportUtil.getHome();
            UserReport userReport = urHome.create();
            return userReport.getBookmarkData(id,userSessionData);            
        } catch (Exception exception) {
        	Object[] parameters = new Object[1];
        	parameters[0] = id;
            handler.handleException("getBookmarkData", exception, userSessionData, parameters);
        }
        return null; 
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "systemaccess"
     */
    public Integer getDefaultBookmark(UserSessionData userSessionData) throws Exception {
        try {
            UserReport userReport = UserReportUtil.getHome().create();
            return userReport.getDefaultBookmark(userSessionData);            
        } catch (Exception exception) {
            handler.handleException("getDefaultBookmark", exception, userSessionData, null);
        }
        return null; 
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "systemaccess"
     */
    public Collection getCompleteBookmarks(UserSessionData userSessionData) throws Exception {
        Collection list = new LinkedList();
        try {
            UserReportHome urHome = UserReportUtil.getHome();
            UserReport userReport = urHome.create();
            list = userReport.getCompleteBookmarks(userSessionData);            
        } catch (Exception exception) {
            handler.handleException("getCompleteBookmarks", exception, userSessionData, null);
        }
        return list; 
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,usercreate"
     */
    public boolean userExist(String login,UserSessionData userSessionData) throws Exception {
        try {
            return UserReportUtil.getHome().create().userExist(login,userSessionData);
        } catch (Exception exception) {
        	Object[] parameters = new Object[1];
        	parameters[0] = login;
            handler.handleException("userExist", exception,userSessionData,parameters);
        }
        return false; 
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,systemaccess"
     */
    public Integer getDivOrgItemLevel1ID(String divItemLevel1Name,Integer divOrgId, UserSessionData userRequest) throws Exception {
        try {
        	return UserReportUtil.getHome().create().getDivOrgItemLevel1ID(divItemLevel1Name,divOrgId,userRequest);
        } catch (Exception exception) {
        	Object[] parameters = new Object[1];
        	parameters[0] = divItemLevel1Name;
            handler.handleException("getDivOrgItemLevel1ID", exception,userRequest,parameters);
        }
        return null;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,systemaccess"
     */
    public boolean exists(String user, String password, String role, UserSessionData userSessionData) throws Exception {
        try{
        	return UserReportUtil.getHome().create().exists(user,password,role,userSessionData);
        } catch (Exception e) {
			Object[] parameters = {user,password,role};
            handler.handleException("exists", e,userSessionData,parameters);
        }
        return false;
   }
}