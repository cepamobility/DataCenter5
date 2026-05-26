package business.alert.access;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.ejb.CreateException;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.classic.Session;

import persistence.administration.bookmarks.tables.Bookmark;
import persistence.administration.corporation.tables.Corporation;
import persistence.administration.tables.Permission;
import persistence.administration.tables.Role;
import persistence.administration.tables.UserCorporation;
import persistence.administration.tables.UserCorporationPK;
import persistence.administration.tables.UserGroup;
import persistence.administration.user.tables.User;
import persistence.hibernate.HibernateUtil;
import persistence.util.ExceptionHandler;

import communication.administration.RoleData;
import communication.administration.bookmark.tables.BookmarkData;
import communication.administration.corporation.tables.CorporationData;
import communication.administration.group.tables.GroupData;
import communication.administration.group.tables.GroupLineData;
import communication.administration.user.PermissionData;
import communication.administration.user.UserSessionData;
import communication.administration.user.tables.UserAttributes;
import communication.administration.user.tables.UserData;
import communication.exception.DeslogedException;
import communication.exception.InvalidDataException;
import communication.language.TextContainer;
import communication.util.ListResult;
import communication.util.MD5;


public class UserHandler implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
    private ExceptionHandler handler = new ExceptionHandler(UserHandler.class);
    private static TextContainer textContainer;
    private Hashtable texts = new Hashtable();
    
    /**
     * 
     * @throws CreateException
     */
    public void ejbCreate() throws CreateException { }
    
	/**
	 * 
	 * @param loginName
	 * @param passwd
	 * @return
	 * @throws Exception
	 */
	public boolean login(String loginName, String passwd)throws Exception {
		try {
            Session session = HibernateUtil.currentSession();
			User usr = (User)session.load(User.class, loginName.trim());
			return (usr.getPassword().compareTo(new MD5().encriptar(passwd.trim())) == 0);
		} catch (Exception e) {
			Object[] parameters = {loginName,passwd};
            handler.getException(e,"login",null,parameters);
			return false;
		}
	}

	/**
	 * 
	 * @param loginName
	 * @param userSessionData
	 * @return
	 * @throws Exception
	 */
	public UserData findUserByPrimaryKey(String loginName, UserSessionData userSessionData) throws Exception {
		UserData data=null;
		try {
			if(loginName==null){
				throw new InvalidDataException("findUserByPrimaryKey","loginname = null");
			}
			if (userSessionData == null) {
				throw new DeslogedException("findUserByPrimaryKey","userRequest = null");
			}
		    
            Session session = HibernateUtil.currentSession();
            if(userSessionData.getFilter() == null) {
                long time = System.currentTimeMillis();
			    
                Query query = session.createQuery("SELECT user FROM persistence.administration.user.tables.User user WHERE lower(user.loginName) = lower(:nick)");
			    
                query.setParameter("nick", loginName);
			    User user = (User) query.uniqueResult();
                System.out.println("TIEMPO 1 --> "+String.valueOf(System.currentTimeMillis() - time));
                time = System.currentTimeMillis();
                
                Query q = session.createSQLQuery("SELECT uc.role,up.permission ,p.module,p.index,p.corporationid,r.cepauser FROM usercorporations uc "+
                "JOIN userpermissions up ON (up.userid = uc.loginname AND up.corporationid = uc.id) "+
                "JOIN roles r ON uc.role = r.resourcekey "+
                "JOIN permissions p ON p.key = up.permission "+
                "WHERE loginname = '"+loginName+"' AND id = "+String.valueOf(user.getCorporationId())).addScalar("role",Hibernate.STRING).addScalar("permission",Hibernate.STRING).addScalar("module",Hibernate.STRING).addScalar("index",Hibernate.INTEGER).addScalar("corporationid",Hibernate.INTEGER).addScalar("cepauser",Hibernate.BOOLEAN); 


                Iterator it = q.list().iterator();
                boolean first = true;
                Hashtable permissions = new Hashtable();
                while(it.hasNext()) {
                    Object[] ucData = (Object[])it.next();
                    if(first) {
                        user.setRole(new Role((String)ucData[0],((Boolean)ucData[5]).booleanValue()));
                        first = false;
                    }
                    permissions.put((String)ucData[1],new PermissionData((String)ucData[1],(String)ucData[2],(Integer)ucData[3],(Integer)ucData[4]));
                }

                user.setPermissions(permissions);
                time = System.currentTimeMillis();
			    if(user != null) {
/*                    ;
                    String time_1 = String.valueOf(System.currentTimeMillis() - time);
                    time = System.currentTimeMillis();
                    Collection groups = findGroups(loginName,String.valueOf(user.getCorporationId()),userSessionData);
                    String time_2 = String.valueOf(System.currentTimeMillis() - time);
                    time = System.currentTimeMillis();*/
			        //data=user.getUserData(getUserGroups(loginName,String.valueOf(user.getCorporationId()),userSessionData));
                    data=user.getUserData(findGroups(loginName,String.valueOf(user.getCorporationId()),userSessionData));
                    //System.out.println("TIEMPO 4 --> "+String.valueOf(System.currentTimeMillis() - time));
                    //data.setFirstName(time_1);
                    //data.setLastName(time_2);
			    }

            }else {
		        UserCorporation userCorporation = (UserCorporation) session.load(UserCorporation.class,new UserCorporationPK(loginName,userSessionData.getFilter().getCorporationId()));
			    if(userCorporation != null) {
				    Query query = session.createQuery("select user from persistence.administration.user.tables.User user  "
			     			 +"where user.loginName = :nick ");
				    query.setParameter("nick", loginName);
				    User user = (User) query.uniqueResult();
                    user.setRole(userCorporation.getRole());
				    loadPermissions(userCorporation,user);
				    if(user != null) {
                        Collection groups = findGroups(loginName,String.valueOf(user.getCorporationId()),userSessionData);
				        data=user.getUserData(groups);
				    }
			    }
		    }
		} catch (Exception e) {
			
			Object[] parameters = {loginName};
            handler.getException(e, "findUserByPrimaryKey", userSessionData, parameters);
        }
		
		return data;
	}

	/**
	 * 
	 * @param userCorporation
	 * @param user
	 */
	private void loadPermissions(UserCorporation userCorporation,User user) {
        user.setRole(userCorporation.getRole());
        Iterator it = userCorporation.getPermissions().iterator();
        Hashtable permissions = new Hashtable();
        while(it.hasNext()) {
            Permission permission = (Permission)it.next();
            permissions.put(permission.getKey(),permission.getData());
        }
        user.setPermissions(permissions);
	}
	
	
	/**
	 * 
	 * @param queryStr
	 * @param attribute
	 * @param value
	 * @param userRequest
	 * @param order
	 * @return
	 */
    private Query getQuery(String queryStr, int attribute, String value, UserSessionData userRequest,boolean order) {
        Session session = HibernateUtil.currentSession();
		switch(attribute) {
		    case UserData.FIRST_NAME:
		        queryStr +=" and lower(u.firstName) like lower(:value) ";
		        break;
		    case UserData.LAST_NAME:
		        queryStr +=" and lower(u.lastName) like lower(:value) ";
		        break;
		    case UserData.NICK_NAME:
		        queryStr +=" and lower(u.loginName) like lower(:value) ";
		        break;
		    case UserData.MAIL:
		        queryStr +=" and lower(u.email) like lower(:value) ";
		        break;
		}
        if(order) {
            queryStr +=" order by u.firstName,u.loginName ";
        }
	    Query query = session.createQuery(queryStr);
		query.setParameter("corporation", userRequest.getFilter().getCorporationId());
		query.setParameter("value","%"+value+"%");
		return query;
    }

	
    /**
     * 
     * @param value
     * @param userSessionData
     * @param attribute
     * @param first
     * @param count
     * @return
     * @throws Exception
     */
	public ListResult findList(String value, UserSessionData userSessionData,
			int attribute,int first, int count) throws Exception {
        try {
            Session session = HibernateUtil.currentSession();
            if (value == null) {
            	throw new InvalidDataException("findList","value = null");
            }
            if (userSessionData == null) {
            	throw new DeslogedException("findList","userSessionData = null");
            }
            String queryStr ="select count(*) from User u, UserCorporation uc "
            	 + " where u.loginName = uc.pk.user.loginName " +
                    "and uc.pk.corporation.id = (:corporation) " +
                    "and u.loginName != 'resetpassword' ";

            Query query = getQuery(queryStr,attribute,value,userSessionData,false);
            // Integer total = (Integer)query.uniqueResult();
            Integer total = new Integer(((Long)query.uniqueResult()).intValue());
            
            queryStr ="select u from User u, UserCorporation uc "
            	 + " where u.loginName = uc.pk.user.loginName " +
                    "and uc.pk.corporation.id = (:corporation)  " +
                    "and u.loginName != 'resetpassword' ";
            query = getQuery(queryStr,attribute,value,userSessionData,true);
            if(count != -1) {
                if(total.intValue() < first) {
                    if(total.intValue() % count == 0) {
            	        first = total.intValue() - count;
                    }else {
                        first = total.intValue() - (total.intValue() % count);
                    }
                }
                query.setFirstResult(first);
                query.setMaxResults(count);
            }
            List userColl = query.list();
            Collection result = new LinkedList();
            Iterator iter = userColl.iterator();
            while (iter.hasNext()) {
            	User user = (User) iter.next();
            	result.add(user.getPrincipalAttributes());
            }
            //session.close();

            return new ListResult(result,total.intValue());
        }  catch (Exception e) {
        	
			Object[] parameters = {value,attribute,first,count};
            handler.getException(e, "findList", userSessionData, parameters);
        }
        return new ListResult(new LinkedList(),0);
	}

    /**
     * 
     * @param email
     * @param userSessionData
     * @return
     * @throws Exception
     */
    public UserAttributes findUserByEmail(String email, UserSessionData userSessionData) throws Exception {
        UserAttributes data=null;
        try {
            //Check if inited transaction       
            if(email==null){
                throw new InvalidDataException("findUserByEmail","email = null");
            }
            if (userSessionData == null) {
                throw new DeslogedException("findUserByEmail","userSessionData = null");
            }
            Session session = HibernateUtil.currentSession();

            Query query = session.createQuery("select user from persistence.administration.user.tables.User user  "
                         +"where lower(user.email) = lower(:email)");
            query.setParameter("email", email);
            Iterator it = query.list().iterator();
            if(it.hasNext()) {
                User user = (User)it.next();
                if(user != null) {
                    data=user.getPrincipalAttributes();
                }
            }
        } catch (Exception e) {
        	Object[] p = new Object[1];p[0] = email;
            handler.getException(e, "findUserByEmail", userSessionData,p);
        }
        return data;
    }

    /**
     * 
     * @param user
     * @param userSessionData
     * @return
     * @throws Exception
     */
    public RoleData findUserRole(String user,UserSessionData userSessionData) throws Exception {
        try {
            //Check if inited transaction       
            if(user==null){
                throw new InvalidDataException("findUserRole","user = null");
            }
            if (userSessionData == null) {
                throw new DeslogedException("findUserRole","userSessionData = null");
            }

            Session session = HibernateUtil.currentSession();
            Query query = session.createQuery("SELECT uc.role FROM UserCorporation uc WHERE uc.pk.user.loginName = '"+user+"' AND uc.pk.corporation.id = "+userSessionData.getFilter().getCorporationId());
            
            Role role = (Role)query.uniqueResult();
            return role.getData();
            
        }catch (Exception e) {
        	Object[] p = new Object[1];
        	p[0] = user;
            handler.getException(e,"findUserRole",userSessionData,p);
        }
        return null;
    }

    /**
     * 
     * @param user
     * @param userSessionData
     * @return
     * @throws Exception
     */
    public Collection findUserPermissions(String user,UserSessionData userSessionData) throws Exception {
        Collection permissions = new LinkedList();
        try {
            //Check if inited transaction       
            if(user==null){
                throw new InvalidDataException("findUserPermissions","user = null");
            }
            if (userSessionData == null) {
                throw new DeslogedException("findUserPermissions","userSessionData = null");
            }

            Session session = HibernateUtil.currentSession();
            Query query = session.createQuery("SELECT uc.permissions FROM UserCorporation uc " +
            		"WHERE uc.pk.user.loginName = '"+user+"' AND " +
            		"uc.pk.corporation.id = "+userSessionData.getFilter().getCorporationId());
            
            Iterator it = query.list().iterator();
            while(it.hasNext()) {
                permissions.add(((Permission)it.next()).getData());
            }
            
        }catch (Exception e) {
        	Object[] p = new Object[1];
        	p[0] = user;
            handler.getException(e,"findUserPermissions",userSessionData,p);;
        }
        return permissions;
    }

   
    /**
     * 
     * @param loginName
     * @param corporation
     * @param userSessionData
     * @return
     * @throws Exception
     */
    public Collection findGroups(String loginName,String corporation,UserSessionData userSessionData) throws Exception {
        Collection groups = new LinkedList();
        try {
            Session session = HibernateUtil.currentSession();
            Query query = session.createQuery("SELECT usergroup FROM UserGroup usergroup " +
            		"WHERE usergroup.pk.user.loginName = '"+loginName+"' and usergroup.pk.group.corporation = "+corporation);
            Iterator iter = query.list().iterator();
            while (iter.hasNext()) {
                groups.add(((UserGroup) iter.next()).getPk().getGroup().getData());
            }
        }catch (Exception e) {
        	Object[] p = new Object[2];
        	p[0] = loginName;
        	p[1] = corporation;
            handler.getException(e,"findGroups",userSessionData,p);
        }
        return groups;
    }

    /**
     * 
     * @param login
     * @param corporation
     * @param userSessionData
     * @return
     * @throws Exception
     */
    public Collection getUserGroups(String login,String corporation,UserSessionData userSessionData) throws Exception {
        Collection lines = new LinkedList();
        try {
            String composed = "";
            String ids = "";
            Session session = HibernateUtil.currentSession();
            Query query = session.createSQLQuery("SELECT g.id,g.type FROM groups g " +
            		"JOIN usergroups ug ON ug.id = g.id WHERE ug.loginname = '"+login+"' " +
            				"AND g.corporation = "+corporation).addScalar("id",Hibernate.INTEGER).addScalar("type",Hibernate.INTEGER);
            Iterator it = query.list().iterator();
            while(it.hasNext()) {
                Object[] data = (Object[])it.next();
                if(((Integer)data[1]).intValue() == GroupData.COMPOSED.intValue()) {
                    composed += String.valueOf(data[0]);
                    if(it.hasNext()) {
                        composed += ",";
                    }
                }else {
                    ids += String.valueOf(data[0]);
                    if(it.hasNext()) {
                        ids += ",";
                    }
                }
            }
            
            while(composed.length() > 0) {
                query = session.createSQLQuery("SELECT id,type FROM groups WHERE id " +
                		"IN (select element from groupelements where groupelement in ("+composed+"))").
                		addScalar("id",Hibernate.INTEGER).addScalar("type",Hibernate.INTEGER);
                composed = "";
                it = query.list().iterator();
                while(it.hasNext()) {
                    Object[] data = (Object[])it.next();
                    if(((Integer)data[1]).intValue() == GroupData.COMPOSED.intValue()) {
                        composed += String.valueOf(data[0]);
                        if(it.hasNext()) {
                            composed += ",";
                        }
                    }else {
                        ids += String.valueOf(data[0]);
                        if(it.hasNext()) {
                            ids += ",";
                        }
                    }
                }
            }
            
            SQLQuery sqlQuery = session.createSQLQuery("SELECT gl2.id AS geoId2,gl2.resourcekey AS geoName2,gl1.id AS geoId1,gl1.resourcekey AS geoName1,c.id AS country,c.resourcekey AS cName," +
                    "d13.id AS d13Id,d13.resourcekey AS d13Name,d12.id AS d12Id,d12.resourcekey AS d12Name,d11.id AS d11Id,d11.resourcekey AS d11Name," +
                    "d23.id AS d23Id,d23.resourcekey AS d23Name,d22.id AS d22Id,d22.resourcekey AS d22Name,d21.id AS d21Id,d21.resourcekey AS d21Name," +
                    "d33.id AS d33Id,d33.resourcekey AS d33Name,d32.id AS d32Id,d32.resourcekey AS d32Name,d31.id AS d31Id,d31.resourcekey AS d31Name " +
                    "FROM grouplines gl " +
                    "JOIN country c ON gl.country = c.id " +
                    "LEFT JOIN geolevel1s  gl1 ON gl1.id = gl.geolevel1 " +
                    "LEFT JOIN geolevel2s  gl2 ON gl2.id = gl.geolevel2 " +
                    "LEFT JOIN divorgitemlevel1s  d11 ON d11.id = gl.divorg11 " +
                    "LEFT JOIN divorgitemlevel2s  d12 ON d12.id = gl.divorg12 " +
                    "LEFT JOIN divorgitemlevel3s  d13 ON d13.id = gl.divorg13 " +
                    "LEFT JOIN divorgitemlevel1s  d21 ON d21.id = gl.divorg21 " +
                    "LEFT JOIN divorgitemlevel2s  d22 ON d22.id = gl.divorg22 " +
                    "LEFT JOIN divorgitemlevel3s  d23 ON d23.id = gl.divorg23 " +
                    "LEFT JOIN divorgitemlevel1s  d31 ON d31.id = gl.divorg31 " +
                    "LEFT JOIN divorgitemlevel2s  d32 ON d32.id = gl.divorg32 " +
                    "LEFT JOIN divorgitemlevel3s  d33 ON d33.id = gl.divorg33 " +
                    "WHERE groupelement IN ("+ids+")");
            sqlQuery.addScalar("geoId2",Hibernate.INTEGER);
            sqlQuery.addScalar("geoName2",Hibernate.STRING);
            sqlQuery.addScalar("geoId1",Hibernate.INTEGER);
            sqlQuery.addScalar("geoName1",Hibernate.STRING);
            sqlQuery.addScalar("country",Hibernate.INTEGER);
            sqlQuery.addScalar("cName",Hibernate.STRING);
            sqlQuery.addScalar("d13Id",Hibernate.INTEGER);
            sqlQuery.addScalar("d13Name",Hibernate.STRING);
            sqlQuery.addScalar("d12Id",Hibernate.INTEGER);
            sqlQuery.addScalar("d12Name",Hibernate.STRING);
            sqlQuery.addScalar("d11Id",Hibernate.INTEGER);
            sqlQuery.addScalar("d11Name",Hibernate.STRING);
            sqlQuery.addScalar("d23Id",Hibernate.INTEGER);
            sqlQuery.addScalar("d23Name",Hibernate.STRING);
            sqlQuery.addScalar("d22Id",Hibernate.INTEGER);
            sqlQuery.addScalar("d22Name",Hibernate.STRING);
            sqlQuery.addScalar("d21Id",Hibernate.INTEGER);
            sqlQuery.addScalar("d21Name",Hibernate.STRING);
            sqlQuery.addScalar("d33Id",Hibernate.INTEGER);
            sqlQuery.addScalar("d33Name",Hibernate.STRING);
            sqlQuery.addScalar("d32Id",Hibernate.INTEGER);
            sqlQuery.addScalar("d32Name",Hibernate.STRING);
            sqlQuery.addScalar("d31Id",Hibernate.INTEGER);
            sqlQuery.addScalar("d31Name",Hibernate.STRING);
            it = sqlQuery.list().iterator();
            while(it.hasNext()) {
                lines.add(new GroupLineData((Object[])it.next(),userSessionData));
            }
        }catch (Exception e) {
            Object[] p = new Object[2];
        	p[0] = login;
        	p[1] = corporation;
            handler.getException(e,"getUserGroups",userSessionData,p);
        }
        return lines;
    }
    
    /**
     * 
     * @param loginName
     * @param corporation
     * @param userSessionData
     * @return
     * @throws Exception
     */
    public Collection findGroupAttributes(String loginName,String corporation,UserSessionData userSessionData) throws Exception {
        Collection groups = new LinkedList();
        try {
            Session session = HibernateUtil.currentSession();
            Query query = session.createQuery("SELECT usergroup FROM UserGroup usergroup " +
            		"WHERE usergroup.pk.user = '"+loginName+"' and usergroup.pk.group.corporation = "+corporation);
            Iterator iter = query.list().iterator();
            while (iter.hasNext()) {
                groups.add(((UserGroup) iter.next()).getPk().getGroup().getMainAttributes());
            }
        }catch (Exception e) {
        	
            
			Object[] parameters = {loginName,corporation};
            handler.getException(e,"findGroupAttributes",userSessionData,parameters);
        }
        return groups;
    }
    
    
    /**
     * 
     * @param loginName
     * @param userSessionData
     * @return
     * @throws Exception
     */
    public UserAttributes findUserAttributes(String loginName, UserSessionData userSessionData) throws Exception {
        UserAttributes data=null;
        try {
            if(loginName==null){
                throw new InvalidDataException("findUserAttributes","loginname = null");
            }
            if (userSessionData == null) {
                throw new DeslogedException("findUserAttributes","userRequest = null");
            }
            Session session = HibernateUtil.currentSession();
            User user = (User) session.load(User.class,loginName);
            
            data = user.getPrincipalAttributes();
            
            Query query = session.createQuery("SELECT uc.role FROM " +
            		"UserCorporation uc WHERE uc.pk.user.loginName = '"+loginName+"' " +
            				"AND uc.pk.corporation.id = "+userSessionData.getFilter().getCorporationId());
            Role role = (Role)query.uniqueResult();
            data.setRoleName(role.getResourceKey());
            
        }catch (Exception e) {
        	
            
			Object[] parameters = {loginName};
            handler.getException(e,"findUserAttributes",userSessionData,parameters);
        }
        return data;
    }
    
    /**
     * 
     * @param loginName
     * @param userSessionData
     * @return
     * @throws Exception
     */
    public int getPasswordType(String loginName, UserSessionData userSessionData) throws Exception {
        try {
            if(loginName==null){
                throw new InvalidDataException("getPasswordType","loginname = null");
            }
            if (userSessionData == null) {
                throw new DeslogedException("getPasswordType","userRequest = null");
            }
            Session session = HibernateUtil.currentSession();
            Query query = session.createQuery("SELECT COUNT(*) FROM UserCorporation uc" +
            		" WHERE uc.pk.user.loginName = '"+loginName+"' AND uc.pk.corporation.id = "+CorporationData.JJ);
            Integer count = (Integer)query.uniqueResult();
            return (count.intValue() == 0) ? UserAttributes.SIMPLE : UserAttributes.DIFFICULT;
            
        }catch (Exception e) {
          
			Object[] parameters = {loginName};
            handler.getException(e,"getPasswordType",userSessionData,parameters);
        }
        
        return UserAttributes.DIFFICULT;
        
    }

    
    /**
     * 
     * @param user
     * @return
     * @throws Exception
     */
    public boolean isFirstAccess(String user) throws Exception {
        try {
            Session session = HibernateUtil.currentSession();
            Query query = session.createQuery("SELECT firstAccess,passwordDate FROM " +
            		"UserAccess WHERE loginName = '"+user+"' AND loginName IN " +
            				"(SELECT pk.user.loginName FROM UserCorporation WHERE pk.corporation.id = 4)");
            Iterator it = query.list().iterator();
            if(it.hasNext()) {
                Object[] data = (Object[])it.next();
                if(((Boolean)data[0]).booleanValue()) {
                    return true;
                }else {
                    Calendar today = Calendar.getInstance();
                    Calendar access = Calendar.getInstance();
                    access.setTime((Date) data[1]);
                    access.add(Calendar.DATE,90);
                    return access.before(today);
                }
            }
        } catch (Exception e) {
        	Object[]p = new Object[1];p[0] = user;
            handler.getException(e,"isFirstAccess",null,p);
        }
        return false; 
    }

    /**
     * 
     * @param user
     * @return
     * @throws Exception
     */
    public boolean isMultipleCorporation(String user) throws Exception {
        try {
            Session session = HibernateUtil.currentSession();
            Query query = session.createSQLQuery("SELECT count(*) AS count " +
            		"FROM usercorporations WHERE loginname = '"+user+"'").addScalar("count",Hibernate.INTEGER);
            // return (((Integer)query.uniqueResult()).intValue() > 1);
            return ( ((Long)query.uniqueResult()).intValue() > 1);
        } catch (Exception e) {
        	Object[]p = new Object[1];
        	p[0] = user;
            handler.getException(e,"isMultipleCorporation",null,p);
        }
        return false; 
    }

    /**
     * 
     * @param userSessionData
     * @return
     * @throws Exception
     */
    public String[][] getBookmark(UserSessionData userSessionData) throws Exception {
        try {
            Session session = HibernateUtil.currentSession();
            Query query = session.createSQLQuery("SELECT id,name FROM " +
            		"bookmarkitems WHERE userid = '"+userSessionData.getFilter().getLoginName()+"' AND corporation = "+String.valueOf(userSessionData.getFilter().getCorporationId())).addScalar("id",Hibernate.INTEGER).addScalar("name",Hibernate.STRING);
            List list = query.list();
            String[][] bookmark = new String[list.size()][2]; 
            Iterator it = list.iterator();
            int i = 0;
            while(it.hasNext()) {
                Object[] data = (Object[])it.next();
                bookmark[i][0] = String.valueOf(data[0]);
                bookmark[i][1] = (String)data[1];
                i++;
            }
            return bookmark;
        } catch (Exception e) {
        	
            handler.getException(e,"getBookmark",userSessionData,null);
        }
        return new String[0][0]; 
    }

    /**
     * 
     * @param id
     * @param userSessionData
     * @return
     * @throws Exception
     */
    public BookmarkData getBookmarkData(Integer id,UserSessionData userSessionData) throws Exception {
        try {
            Session session = HibernateUtil.currentSession();
            Bookmark bookmark = (Bookmark)session.load(Bookmark.class,id);
            return bookmark.getData();
        } catch (Exception e) {
        	Object[]p = new Object[1];
        	p[0] = id;
            handler.getException(e,"getBookmarkData",userSessionData,p);
        }
        return null; 
    }

    /**
     * 
     * @param userSessionData
     * @return
     * @throws Exception
     */
    public Integer getDefaultBookmark(UserSessionData userSessionData) throws Exception {
        try {
            Session session = HibernateUtil.currentSession();
            Query query = session.createQuery("SELECT id FROM Bookmark " +
            		"WHERE start = 'true' AND userId = '"+userSessionData.getFilter().getLoginName()+"' AND corporation = "+String.valueOf(userSessionData.getFilter().getCorporationId()));
            List list = query.list();
            if(list.size() > 0) {
                return (Integer)list.iterator().next();
            }
        } catch (Exception e) {
            handler.getException(e,"getDefaultBookmark",userSessionData,null);
        }
        return null; 
    }

    /**
     * 
     * @param userSessionData
     * @return
     * @throws Exception
     */
    public Collection getCompleteBookmarks(UserSessionData userSessionData) throws Exception {
        Collection listResult = new LinkedList();
        try {
            Session session = HibernateUtil.currentSession();
            Query query = session.createQuery("SELECT item FROM Bookmark item " +
            		"WHERE userId = '"+userSessionData.getFilter().getLoginName()+"' AND corporation = "+String.valueOf(userSessionData.getFilter().getCorporationId()));
            List list = query.list();
            Iterator it = list.iterator();
            while(it.hasNext()) {
                listResult.add(((Bookmark)it.next()).getData());
            }
        } catch (Exception e) {
            handler.getException(e,"getCompleteBookmarks",userSessionData,null);
        }
        return listResult; 
    }

    /**
     * 
     * @param login
     * @param userRequest
     * @return
     * @throws Exception
     */
    public boolean userExist(String login, UserSessionData userRequest) throws Exception {
        try{
            Session session = HibernateUtil.currentSession();
            Query query = session.createSQLQuery("SELECT COUNT(*) AS count FROM users WHERE loginname = '" + login +"'").addScalar("count",Hibernate.INTEGER);
            return ((Integer)query.uniqueResult()).intValue() > 0;
        } catch (Exception e) {
            Object[] parameters = {login};
            handler.getException(e, "userExist", userRequest, parameters);
        }
        return false;
    }
    
    /**
     * 
     * @param id
     * @param userRequest
     * @return
     * @throws Exception
     */
    public CorporationData findCorporationByPrimaryKey(Integer id, UserSessionData userRequest) throws Exception {
		if (id == null) {
			throw new InvalidDataException("findCorporationByPrimaryKey","id = null");
		}
		if (userRequest == null) {
			throw new DeslogedException("findCorporationByPrimaryKey","userRequest = null");
		}
		CorporationData crpData = null;
		try {
			Session session = HibernateUtil.currentSession();
			Corporation corporation=null;
			
			corporation=(Corporation)session.load(Corporation.class,id);
			
			Query query = session.createQuery("SELECT corporationCountry  " +
					"FROM persistence.administration.corporation.tables.CorporationCountry as corporationCountry  "+
					"WHERE corporationCountry.pk.corporation.id = (:corp)");
			query.setParameter("corp", id);

			List crpColl = query.list();
			Iterator iter = crpColl.iterator();

			Set countries = new HashSet();
			while (iter.hasNext()) {
			    countries.add(iter.next());
			}
			corporation.setCountries(countries);
			crpData=corporation.getData();
		}catch (Exception e) {
			Object[]p = new Object[1]; p[0] = id;
			handler.getException(e, "findCorporationByPrimaryKey", userRequest,p);
       }
		return crpData;
	}
    
    /**
     * 
     * @param language
     * @param corporation
     * @return
     * @throws Exception
     */
    public Object[][] getTexts(String language,Integer corporation) throws Exception {
        try {
            String key = language+"-"+String.valueOf(corporation);
            if(texts.containsKey(key)) {
                return (Object[][])texts.get(key);
            }else {
                Locale locale=new Locale(language,"",String.valueOf(corporation));
                Object[][] contents = findMessages(locale);
                synchronized (texts) {
                    texts.put(key,contents);
                }	
                return contents;
            }
        } catch (Exception e) {
        	Object[]p = new Object[2]; 
        	p[0] = language;
        	p[1] = corporation;
			handler.getException(e, "getTexts", null,p);
        }
        return null;
    }
    
    /**
     * 
     * @param locale
     * @return
     * @throws Exception
     */
    public Object[][] findMessages(Locale locale) throws Exception{
		try{
            if(locale==null){
                throw new InvalidDataException("findMessages","locale is empty");
            }

            Session session=HibernateUtil.currentSession();

            Query query=session.createSQLQuery("SELECT labelkey,text FROM generalmessages WHERE language = '"+locale.getLanguage()+"'").addScalar("labelkey",Hibernate.STRING).addScalar("text",Hibernate.STRING);
            Collection list1 = query.list();
            
            Collection list2 = new LinkedList();
            if(locale.getVariant()!=null && locale.getVariant().length() > 0){
                query=session.createSQLQuery("SELECT labelkey,text FROM corporationmessages WHERE language = '"+locale.getLanguage()+"' AND corporationname = "+locale.getVariant()).addScalar("labelkey",Hibernate.STRING).addScalar("text",Hibernate.STRING);
                list2 = query.list();
            }
            
            Object[][] messages=new Object[list1.size() + list2.size()][2];
            int i = 0;
            
            Iterator it = list1.iterator();
			while(it.hasNext()){
                messages[i] = (Object[])it.next();
				i++;
			}
			
            it = list2.iterator();
            while(it.hasNext()){
                messages[i] = (Object[])it.next();
                i++;
            }

            return messages;
		}catch(Exception e){
			
			Object[] parameters = {locale};
			handler.getException(e,"findMessages",null,parameters);
		}		
		return new Object[0][0];
	}

	public static void setTextContainer(TextContainer textContainer) {
		UserHandler.textContainer = textContainer;
	}

	public static TextContainer getTextContainer() {
		return textContainer;
	}
}