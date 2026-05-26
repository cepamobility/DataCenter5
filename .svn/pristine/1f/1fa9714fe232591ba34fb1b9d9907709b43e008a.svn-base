/*
 * Created on 10-ago-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package persistence.administration.user;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.CreateException;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.classic.Session;

import persistence.administration.bookmarks.tables.Bookmark;
import persistence.administration.corporation.tables.DivOrgItemLevel1;
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
import communication.util.ListResult;
import communication.util.MD5;

/**
 * 
 * @ejb.bean name="UserReport" jndi-name="ejb/UserReport" type="Stateless"
 *           transaction-type="Container"
 * 
 * <!-- end-xdoclet-definition -->
 * @generated
 */
public abstract class UserReportBean implements javax.ejb.SessionBean {
	
    private ExceptionHandler handler = new ExceptionHandler(UserReportBean.class);
    
    /**
     * @ejb.create-method 
     * @ejb.permission role-name = "systemaccess,administrator,resetpassword,vehicleview"
     * @throws CreateException
     */
    public void ejbCreate() throws CreateException { }
    
	/**
	 * @ejb.interface-method
     * @ejb.permission role-name = "systemaccess,administrator"
	 * 
	 * @param loginName
	 * @param passwd
	 * @return boolean
	 */
	public boolean login(String loginName, String passwd)throws Exception {
		try {
            Session session = HibernateUtil.currentSession();
            Query query = session.createQuery("SELECT user FROM persistence.administration.user.tables.User user WHERE lower(user.loginName) = lower(:nick)");
		    
            query.setParameter("nick", loginName);
		    User usr = (User) query.uniqueResult();
			return (usr.getPassword().compareTo(new MD5().encriptar(passwd.trim())) == 0);
		} catch (Exception e) {
			Object[] parameters = {loginName,passwd};
            handler.getException(e,"login",null,parameters);
			return false;
		}
	}

	/**
	 * @ejb.interface-method
     * @ejb.permission role-name = "systemaccess,administrator,resetpassword"
	 * @param loginName
	 * @param userRequest
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
            Hashtable permissions = new Hashtable();
            
            if(userSessionData.getFilter() == null) {
                long time = System.currentTimeMillis();
			    
                Query query = session.createQuery("SELECT user FROM persistence.administration.user.tables.User user WHERE lower(user.loginName) = lower(:nick)");
			    
                query.setParameter("nick", loginName);
			    User user = (User) query.uniqueResult();
                //System.out.println("TIEMPO 1 --> "+String.valueOf(System.currentTimeMillis() - time));
                time = System.currentTimeMillis();
                
                Query q = session.createSQLQuery("SELECT uc.role,up.permission ,p.module,p.index,p.corporationid,r.cepauser FROM usercorporations uc "+
                "JOIN userpermissions up ON (up.userid = uc.loginname AND up.corporationid = uc.id) "+
                "JOIN roles r ON uc.role = r.resourcekey "+
                "JOIN permissions p ON p.key = up.permission "+
                "WHERE LOWER(loginname) = '"+loginName.toLowerCase()+"' AND id = "+String.valueOf(user.getCorporationId())).addScalar("role",Hibernate.STRING).addScalar("permission",Hibernate.STRING).addScalar("module",Hibernate.STRING).addScalar("index",Hibernate.INTEGER).addScalar("corporationid",Hibernate.INTEGER).addScalar("cepauser",Hibernate.BOOLEAN); 

                List list = q.list();
                if (list != null){
                	Iterator it = list.iterator();
	                boolean first = true;
	                permissions = new Hashtable();
	                while(it.hasNext()) {
	                    Object[] ucData = (Object[])it.next();
	                    if(first && user != null) {
	                        user.setRole(new Role((String)ucData[0],((Boolean)ucData[5]).booleanValue()));
	                        first = false;
	                    }
	                    permissions.put((String)ucData[1],new PermissionData((String)ucData[1],(String)ucData[2],(Integer)ucData[3],(Integer)ucData[4]));
	                }
                }
                
             
                time = System.currentTimeMillis();
			    if(user != null) {
			    	user.setPermissions(permissions);
/*                    ;
                    String time_1 = String.valueOf(System.currentTimeMillis() - time);
                    time = System.currentTimeMillis();
                    Collection groups = findGroups(loginName,String.valueOf(user.getCorporationId()),userSessionData);
                    String time_2 = String.valueOf(System.currentTimeMillis() - time);
                    time = System.currentTimeMillis();*/
			        //data=user.getUserData(getUserGroups(loginName,String.valueOf(user.getCorporationId()),userSessionData));
			    	
                    Collection groups = (user.getCorporationId().intValue() == CorporationData.BAYER_MX) ? new LinkedList() : findGroups(loginName,String.valueOf(user.getCorporationId()),userSessionData);
				    	
			    	// Si el rol del usuario es rol contratista y la corporacion Los Piques,
                    // (Se puede adaptar para otras corporaciones)
                    // tengo que traer todos los grupos de esa corporacion para el usuario 
                    // contratista.
                    if (user.getRole().getResourceKey().equals(RoleData.CONTRACTOR) &&
                    	user.getCorporationId().intValue() == CorporationData.LOSPIQUES){
                    	// Groups Lines de todos los Grupos que tenga la Corporación
                    	groups = getCorporationGroupsLines(String.valueOf(user.getCorporationId()),userSessionData);
                    	String contractorName = getContractorName(user.getCorporationId(), userSessionData);
                    	data=user.getUserContractorData(groups, contractorName);
			    	}else{
			    		
                        data=user.getUserData(groups);
			    	}
                    //System.out.println("TIEMPO 4 --> "+String.valueOf(System.currentTimeMillis() - time));
                    //data.setFirstName(time_1);
                    //data.setLastName(time_2);
			    }

            }else {
		        UserCorporation userCorporation = (UserCorporation) session.load(UserCorporation.class,new UserCorporationPK(loginName,userSessionData.getFilter().getCorporationId()));
			    if(userCorporation != null) {
				    Query query = session.createQuery("select user from persistence.administration.user.tables.User user  "
			     			 +"where LOWER(user.loginName) = :nick ");
				    query.setParameter("nick", loginName.toLowerCase());
				    User user = (User) query.uniqueResult();
                  
				    if(user != null) {
				    	user.setRole(userCorporation.getRole());
					    loadPermissions(userCorporation,user);
						    
                        Collection groups = (user.getCorporationId().intValue() == CorporationData.BAYER_MX) ? new LinkedList() : findGroups(loginName,String.valueOf(user.getCorporationId()),userSessionData);
                                                
                        // Si el rol del usuario es rol contratista y la corporacion Los Piques,
                        // (Se puede adaptar para otras corporaciones)
                        // tengo que traer todos los grupos de esa corporacion para el usuario 
                        // contratista.
                        if (user.getRole().getResourceKey().equals(RoleData.CONTRACTOR) &&
                        	user.getCorporationId().intValue() == CorporationData.LOSPIQUES){
                        	// Groups Lines de todos los Grupos que tenga la Corporación
                        	groups = getCorporationGroupsLines(String.valueOf(user.getCorporationId()),userSessionData);
                        	String contractorName = getContractorName(user.getCorporationId(), userSessionData);
                        	data=user.getUserContractorData(groups, contractorName);
				    	}else{
				    		data=user.getUserData(groups);	
				    	}
                        
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
	 * @param contractorId
	 * @param userSessionData
	 * @return
	 * @throws Exception
	 */
	private String getContractorName(Integer contractorId, UserSessionData userSessionData) throws Exception{
		String name = null;
		try{
			 Session session = HibernateUtil.currentSession();
	         Query query = session.createSQLQuery(
	            	"SELECT d.resourcekey as name FROM divorgitemlevel1s d WHERE d.id = " + contractorId +" and active = true").
	            	addScalar("name",Hibernate.STRING);
	         name = (String)query.uniqueResult();
	              	
		}catch(Exception e){
			Object[] parameters = {contractorId};
			handler.getException(e, "getContractorName", userSessionData, parameters);
		}
		return name;
	}
	
	/**
	 * Trae todas las GroupLines de todos los grupos de la corporación.
	 * 
	 * @param corporation
	 * @param userSessionData
	 * @return
	 * @throws Exception
	 */
	private Collection getCorporationGroupsLines(String corporation,UserSessionData userSessionData) throws Exception {
        Collection lines = new LinkedList();
        try {
            String composed = "";
            String ids = "";
            Iterator it;
            Session session = HibernateUtil.currentSession();
            Query query = session.createSQLQuery(
            	"SELECT g.id,g.type FROM groups g WHERE g.corporation = "+corporation).
            	addScalar("id",Hibernate.INTEGER).
            	addScalar("type",Hibernate.INTEGER);
            List list = query.list();
            if (list != null){
	            it = list.iterator();
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
            
            while(composed.length() > 0) {
                query = session.createSQLQuery(
                		"SELECT id,type FROM groups WHERE id IN (" +
                		"	select element from groupelements where groupelement in ("+composed+"))").
                		addScalar("id",Hibernate.INTEGER).
                		addScalar("type",Hibernate.INTEGER);
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
            
            if (ids.length() > 0){               
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
	            list = sqlQuery.list();
	            if (list != null){
		            it = list.iterator();
		            while(it.hasNext()) {
		                lines.add(new GroupLineData((Object[])it.next(),userSessionData));
		            }
	            }
            }
        }catch (Exception e) {
			Object[] parameters = {corporation};
            handler.getException(e,"getCorporationGroupsLines",userSessionData, parameters);
        }
        return lines;
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
     * @param queryStr
     * @param attribute
     * @param value
     * @param userRequest
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
		        value = value.toLowerCase();
		        break;
		    case UserData.MAIL:
		        queryStr +=" and lower(u.email) like lower(:value) ";
		        break;
		    case UserData.FIRST_OR_LAST_NAME:
		    	queryStr +=" and ( lower(u.firstName) like lower(:value) or lower(u.lastName) like lower(:value)) ";
		    	break;
		}
        if(order) {
            queryStr +=" order by u.firstName,u.loginName ";
        }
	    Query query = session.createQuery(queryStr);
		query.setParameter("corporation", userRequest.getFilter().getCorporationId());
		if (attribute >= 1 && attribute <= 5) query.setParameter("value","%"+value+"%");
		return query;
    }

	/**
	 * @ejb.interface-method
     * @ejb.permission role-name = "systemaccess,administrator"
	 * @param value
	 * @param userRequest
	 * @param attribute
	 * @param first
	 * @param count
	 * @return
	 * @throws Exception
	 */
	public ListResult findList(String value, UserSessionData userSessionData, int attribute,int first, int count,
			String roles, boolean cepaUser) throws Exception {
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
                    "and u.loginName != 'resetpassword' and uc.role not in ('fleetdriver','permissions') ";
            
            if (roles != null){
            	queryStr += " and uc.role IN (" + roles +") ";
            }
            
            if (cepaUser){
            	// Si queremos filtrar por usuarios de CPA solamente:
            	queryStr += " and uc.role != null and uc.role.cepaUser = true ";
            }

            Query query = getQuery(queryStr,attribute,value,userSessionData,false);
            Integer total = ((Long)query.uniqueResult()).intValue();
            
            queryStr ="select u from User u, UserCorporation uc "
            	 + " where u.loginName = uc.pk.user.loginName " +
                    "and uc.pk.corporation.id = (:corporation)  " +
                    "and u.loginName != 'resetpassword' and uc.role not in ('fleetdriver','permissions') ";
            
            if (roles != null){
            	queryStr += " and uc.role IN (" + roles +") ";
            }
            
            
            if (cepaUser){
            	// Si queremos filtrar por usuarios de CPA solamente:
            	queryStr += " and uc.role != null and uc.role.cepaUser = true ";
            }

            
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
            session.close();

            return new ListResult(result,total.intValue());
        }  catch (Exception e) {
			Object[] parameters = {value,attribute,first,count};
            handler.getException(e, "findList", userSessionData, parameters);
        }
        return new ListResult(new LinkedList(),0);
	}

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "resetpassword"
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

            SQLQuery query = session.createSQLQuery("SELECT loginname,firstname,lastname,email,language,corporationid " +
            		"FROM users WHERE lower(email) = '"+email.toLowerCase()+"' " +
            		"AND loginname NOT IN (SELECT loginname FROM usercorporations WHERE role = 'fleetdriver')");
            query.addScalar("loginname",Hibernate.STRING);
            query.addScalar("firstname",Hibernate.STRING);
            query.addScalar("lastname",Hibernate.STRING);
            query.addScalar("email",Hibernate.STRING);
            query.addScalar("language",Hibernate.STRING);
            query.addScalar("corporationid",Hibernate.INTEGER);
            Iterator it = query.list().iterator();
            if(it.hasNext()) {
                Object[] user = (Object[])it.next();
                if(user != null) {
                    data = new UserAttributes("",(String)user[1],(String)user[2],(String)user[4],(String)user[0],(String)user[3],(Integer)user[5],"");
                }
            }
        } catch (Exception e) {
			Object[] parameters = {email};
            handler.getException(e, "findUserByEmail", userSessionData, parameters);
        }
        return data;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,userpermission"
     * @return Group Data
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
            Query query = session.createQuery("SELECT uc.role FROM UserCorporation uc WHERE uc.pk.user = '"+user+"' AND uc.pk.corporation.id = "+userSessionData.getFilter().getCorporationId());
            
            Role role = (Role)query.uniqueResult();
            return role.getData();
            
        }catch (Exception e) {
			Object[] parameters = {user};
            handler.getException(e,"findUserRole",userSessionData, parameters);
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
            Query query = session.createQuery("SELECT uc.permissions FROM UserCorporation uc WHERE uc.pk.user = '"+user+"' AND uc.pk.corporation.id = "+userSessionData.getFilter().getCorporationId());
            
            Iterator it = query.list().iterator();
            while(it.hasNext()) {
                permissions.add(((Permission)it.next()).getData());
            }
            
        }catch (Exception e) {
			Object[] parameters = {user};
            handler.getException(e,"findUserPermissions",userSessionData, parameters);
        }
        return permissions;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,groupedit"
     * @return Group Data
     * @throws Exception
     */
    public Collection findGroups(String loginName,String corporation,UserSessionData userSessionData) throws Exception {
        Collection groups = new LinkedList();
        try {
            Session session = HibernateUtil.currentSession();
            Query query = session.createQuery("SELECT usergroup FROM UserGroup usergroup WHERE LOWER(usergroup.pk.user) = '"+loginName.toLowerCase()+"' and usergroup.pk.group.corporation = "+corporation);
            Iterator iter = query.list().iterator();
            while (iter.hasNext()) {
                groups.add(((UserGroup) iter.next()).getPk().getGroup().getData());
            }
        }catch (Exception e) {
			Object[] parameters = {loginName,corporation};
            handler.getException(e,"findGroups",userSessionData, parameters);
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
            Query query = session.createSQLQuery("SELECT g.id,g.type FROM groups g JOIN usergroups ug ON ug.id = g.id WHERE LOWER(ug.loginname) = '"+login.toLowerCase()+"' AND g.corporation = "+corporation).addScalar("id",Hibernate.INTEGER).addScalar("type",Hibernate.INTEGER);
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
                query = session.createSQLQuery("SELECT id,type FROM groups WHERE id IN (select element from groupelements where groupelement in ("+composed+"))").addScalar("id",Hibernate.INTEGER).addScalar("type",Hibernate.INTEGER);
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
			Object[] parameters = {login,corporation};
            handler.getException(e,"getUserGroups",userSessionData, parameters);
        }
        return lines;
    }
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,useredit"
     */
    public Collection findGroupAttributes(String loginName,String corporation,UserSessionData userSessionData) throws Exception {
        Collection groups = new LinkedList();
        try {
            Session session = HibernateUtil.currentSession();
            Query query = session.createQuery("SELECT usergroup FROM UserGroup usergroup WHERE LOWER(usergroup.pk.user) = '"+loginName.toLowerCase()+"' and usergroup.pk.group.corporation = "+corporation);
            Iterator iter = query.list().iterator();
            while (iter.hasNext()) {
                groups.add(((UserGroup) iter.next()).getPk().getGroup().getMainAttributes());
            }
        }catch (Exception e) {
			Object[] parameters = {loginName,corporation};
            handler.getException(e,"findGroups",userSessionData, parameters);
        }
        return groups;
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,useredit"
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
            
            Query query = session.createQuery("SELECT uc.role FROM UserCorporation uc WHERE LOWER(uc.pk.user) = '"+loginName.toLowerCase()+"' AND uc.pk.corporation.id = "+userSessionData.getFilter().getCorporationId());
            Role role = (Role)query.uniqueResult();
            data.setRoleName(role.getResourceKey());
            
        }catch (Exception e) {
			Object[] parameters = {loginName};
            handler.getException(e,"findUserAttributes",userSessionData, parameters);
        }
        return data;
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,resetpassword, systemaccess"
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
            Query query = session.createQuery("SELECT COUNT(*) FROM UserCorporation uc WHERE LOWER(uc.pk.user) = '"+loginName.toLowerCase()+"' AND uc.pk.corporation.id = "+CorporationData.JJ);
            Long count = (Long)query.uniqueResult();
            return (count.intValue() == 0) ? UserAttributes.SIMPLE : UserAttributes.DIFFICULT;
            
        }catch (Exception e) {
			Object[] parameters = {loginName};
            handler.getException(e,"getPasswordType",userSessionData,parameters);
        }
        return UserAttributes.DIFFICULT;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission unchecked="" 
     */
    public boolean isFirstAccess(String user) throws Exception {
        try {
            Session session = HibernateUtil.currentSession();
            Query query = session.createQuery("SELECT firstAccess,passwordDate FROM UserAccess WHERE LOWER(loginName) = '"+user.toLowerCase()+"' AND LOWER(loginName) IN (SELECT LOWER(pk.user.loginName) FROM UserCorporation WHERE pk.corporation.id = 4)");
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
			Object[] parameters = {user};
            handler.getException(e,"isFirstAccess",null,parameters);
        }
        return false; 
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "systemaccess,resetpassword"
     */
    public boolean isMultipleCorporation(String user) throws Exception {
        try {
            Session session = HibernateUtil.currentSession();
            Query query = session.createSQLQuery("SELECT count(*) AS count FROM usercorporations " +
            		"WHERE LOWER(loginname) = '"+user.toLowerCase()+"'").addScalar("count",Hibernate.INTEGER);
            return (((Integer)query.uniqueResult()).intValue() > 1);
        } catch (Exception e) {
			Object[] parameters = {user};
            handler.getException(e,"isMultipleCorporation",null,parameters);
        }
        return false; 
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "systemaccess"
     */
    public String[][] getBookmark(UserSessionData userSessionData) throws Exception {
        try {
            Session session = HibernateUtil.currentSession();
            Query query = session.createSQLQuery("SELECT id,name FROM bookmarkitems WHERE LOWER(userid) = '"+userSessionData.getFilter().getLoginName().toLowerCase()+"' AND corporation = "+String.valueOf(userSessionData.getFilter().getCorporationId())).addScalar("id",Hibernate.INTEGER).addScalar("name",Hibernate.STRING);
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
			Object[] parameters = {};
            handler.getException(e,"getBookmark",userSessionData,parameters);
        }
        return new String[0][0]; 
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "systemaccess"
     */
    public BookmarkData getBookmarkData(Integer id,UserSessionData userSessionData) throws Exception {
        try {
            Session session = HibernateUtil.currentSession();
            Bookmark bookmark = (Bookmark)session.load(Bookmark.class,id);
            return bookmark.getData();
        } catch (Exception e) {
			Object[] parameters = {id};
            handler.getException(e,"getBookmarkData",userSessionData,parameters);
        }
        return null; 
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "systemaccess"
     */
    public Integer getDefaultBookmark(UserSessionData userSessionData) throws Exception {
        try {
            Session session = HibernateUtil.currentSession();
            Query query = session.createQuery("SELECT id FROM Bookmark WHERE start = 'true' AND LOWER(userId) = '"+userSessionData.getFilter().getLoginName().toLowerCase()+"' AND corporation = "+String.valueOf(userSessionData.getFilter().getCorporationId()));
            List list = query.list();
            if(list.size() > 0) {
                return (Integer)list.iterator().next();
            }
        } catch (Exception e) {
			Object[] parameters = {};
            handler.getException(e,"getDefaultBookmark",userSessionData,parameters);
        }
        return null; 
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "systemaccess"
     */
    public Collection getCompleteBookmarks(UserSessionData userSessionData) throws Exception {
        Collection listResult = new LinkedList();
        try {
            Session session = HibernateUtil.currentSession();
            Query query = session.createQuery("SELECT item FROM Bookmark item WHERE LOWER(userId) = '"+userSessionData.getFilter().getLoginName().toLowerCase()+"' AND corporation = "+String.valueOf(userSessionData.getFilter().getCorporationId()));
            List list = query.list();
            Iterator it = list.iterator();
            while(it.hasNext()) {
                listResult.add(((Bookmark)it.next()).getData());
            }
        } catch (Exception e) {
			Object[] parameters = {};
            handler.getException(e,"getCompleteBookmarks",userSessionData,parameters);
        }
        return listResult; 
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,usercreate"
     */
    public boolean userExist(String login, UserSessionData userRequest) throws Exception {
        try{
            Session session = HibernateUtil.currentSession();
            Query query = session.createSQLQuery("SELECT COUNT(*) AS count FROM users WHERE LOWER(loginname) = '" + login.toLowerCase() +"'").addScalar("count",Hibernate.INTEGER);
            return ((Integer)query.uniqueResult()).intValue() > 0;
        } catch (Exception e) {
			Object[] parameters = {login};
            handler.getException(e, "userExist", userRequest, parameters);
        }
        return false;
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,systemaccess"
     */
    public Integer getDivOrgItemLevel1ID(String divItemLevel1Name,Integer divOrgId, UserSessionData userRequest) throws Exception {
    	Integer id=null;
        try{
            Session session = HibernateUtil.currentSession();
            Query query = session.createQuery("select i from DivOrgItemLevel1 as i where i.divOrg.id=(:divOrgId) and lower(i.resourceKey)=lower((:divItemName))");
            query.setParameter("divOrgId", divOrgId);
            query.setParameter("divItemName", divItemLevel1Name);
            Iterator iter=query.list().iterator();
            
            if(iter.hasNext()){
            	DivOrgItemLevel1 divItem=(DivOrgItemLevel1)iter.next();
            	id=divItem.getId();
            }
            if(iter.hasNext()){
            	return null;
            }
        } catch (Exception e) {
			Object[] parameters = {divItemLevel1Name};
            handler.getException(e, "getDivOrgItemLevel1ID", userRequest, parameters);
        }
        return id;
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,systemaccess"
     */
    public boolean exists(String user, String password, String role, UserSessionData userSessionData) throws Exception {
        try{
            Session session = HibernateUtil.currentSession();
            Query query = session.createSQLQuery("SELECT COUNT(*) AS count FROM users u JOIN usercorporations uc ON u.loginname = uc.loginname WHERE LOWER(u.loginname) = '" + user.toLowerCase() +"' AND u.password = '" + password +"' AND uc.role = '"+role+"' AND uc.id = '"+userSessionData.getCorporationId()+"'").addScalar("count",Hibernate.INTEGER);
            return ((Integer)query.uniqueResult()).intValue() > 0;
        } catch (Exception e) {
			Object[] parameters = {user,password,role};
            handler.getException(e, "exists", userSessionData, parameters);
        }
        return false;
   }
}