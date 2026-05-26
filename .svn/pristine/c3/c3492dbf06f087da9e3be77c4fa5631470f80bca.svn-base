package business.administration.user;

import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.hibernate.Query;
import org.hibernate.classic.Session;

import persistence.administration.RoleReport;
import persistence.administration.RoleReportHome;
import persistence.administration.RoleReportUtil;
import persistence.administration.corporation.tables.Corporation;
import persistence.administration.group.GroupABM;
import persistence.administration.group.GroupABMHome;
import persistence.administration.group.GroupABMUtil;
import persistence.administration.group.GroupReport;
import persistence.administration.group.GroupReportHome;
import persistence.administration.group.GroupReportUtil;
import persistence.administration.tables.Role;
import persistence.administration.tables.UserCorporation;
import persistence.administration.user.UserABM;
import persistence.administration.user.UserABMHome;
import persistence.administration.user.UserABMUtil;
import persistence.administration.user.UserReport;
import persistence.administration.user.UserReportUtil;
import persistence.administration.user.tables.User;
import persistence.driver.DriverReport;
import persistence.driver.DriverReportUtil;
import persistence.hibernate.HibernateUtil;
import persistence.util.Util;
import business.util.ExceptionHandler;

import communication.administration.RoleData;
import communication.administration.bookmark.tables.BookmarkData;
import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.CountryData;
import communication.administration.group.tables.GroupLineData;
import communication.administration.group.tables.GroupMainAttributes;
import communication.administration.user.PermissionData;
import communication.administration.user.UserSessionData;
import communication.administration.user.tables.UserAttributes;
import communication.administration.user.tables.UserData;
import communication.driver.tables.DriverData;
import communication.exception.AlreadyExistsException;
import communication.exception.DeslogedException;
import communication.exception.InvalidDataException;
import communication.security.SecurityConstants;
import communication.util.MD5;

/**
 * @ejb.bean name = "UserABMFacade" jndi-name = "ejb/UserABMFacade" type =
 *           "Stateless" transaction-type = "Container"
 * 
 * @ejb.ejb-ref ejb-name = "UserABM" ref-name = "ejb/UserABM" view-type =
 *              "remote"
 * 
 * @jboss.ejb-ref-jndi jndi-name = "ejb/UserABM" ref-name = "UserABM"
 *  
 * @ejb.ejb-ref ejb-name = "UserReport" ref-name = "ejb/UserReport" view-type =
 *              "remote"
 * 
 * @jboss.ejb-ref-jndi jndi-name = "ejb/UserReport" ref-name = "UserReport"
 *  
 * @ejb.ejb-ref ejb-name = "RoleReport" ref-name = "ejb/RoleReport" view-type =
 *              "remote"
 * 
 * @jboss.ejb-ref-jndi jndi-name = "ejb/RoleReport" ref-name = "RoleReport"
 *  
 * @ejb.ejb-ref ejb-name = "GroupABM" ref-name = "ejb/GroupABM" view-type =
 *              "remote"
 * 
 * @jboss.ejb-ref-jndi jndi-name = "ejb/GroupABM" ref-name = "GroupABM"
 * 
 * @ejb.ejb-ref ejb-name = "GroupReport" ref-name = "ejb/GroupReport" view-type =
 *              "remote" 
 * @jboss.ejb-ref-jndi jndi-name = "ejb/GroupReport" ref-name = "GroupReport"
 *  
 * @ejb.ejb-ref ejb-name = "DriverReport" ref-name = "ejb/DriverReport" view-type =
 *              "remote"
 * 
 * @jboss.ejb-ref-jndi jndi-name = "ejb/DriverReport" ref-name = "DriverReport"
 *  
 * @ejb.util generate="physical"
 * 
 */
public abstract class UserABMFacadeBean implements javax.ejb.SessionBean {
    private ExceptionHandler handler = new ExceptionHandler(UserABMFacadeBean.class);

	/**
	 * Create method
	 * 
	 * @ejb.create-method
     * @ejb.permission role-name = "administrator,systemaccess,sercreate,userdelete,userchangepassword,userupdate,userupdateaccess,userpermission,resetpassword"	 */
	public void ejbCreate() {

	}

	
	
	/**
	 * @ejb.interface-method view-type = "remote"
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,usercreate"
	 *
	 * @param data
	 * @throws Exception
	 */
	public void userCreate(UserData data,UserSessionData userSessionData) throws Exception {
		
		UserABM userABM=null;
		try {
			if (userSessionData == null) {
				throw new DeslogedException("Class business.user.UserABMFacade(userCreate), session data is empty","generic.error.deslogued");
			}
			if (data == null) {
				throw new InvalidDataException("Class business.user.UserABMFacade(userCreate), user data is empty","generic.user.userdata.isempty");
			}
			if (!validate(data,userSessionData)) {
				throw new InvalidDataException("Class business.user.UserABMFacade(userCreate), invalid user data","generic.user.userdata.isinvalid");
			}

			String nick = data.getLoginName();
			String passwd = data.getPassword();
			String firstName = data.getFirstName();
			String lastName = data.getLastName();
			String language = data.getLanguage();
			String mail = data.getEmail();
			
			RoleReportHome roleHome = RoleReportUtil.getHome();
			RoleData roleData = roleHome.create().findRoleByPrimaryKey(data.getRole().getName(),userSessionData);
		    Iterator it = roleData.getPermissions().iterator();
		    while(it.hasNext()) {
	            data.addPermission((PermissionData)it.next());
		    }
			
			UserData createData = new UserData(passwd, firstName, lastName, language, nick , mail, data.getCorporationId(), data.getPermissions(), roleData, new LinkedList());
			if (data.getContractorId() != null){
				createData.setContractorId(data.getContractorId());
			}
			UserABMHome home = UserABMUtil.getHome();
		
			userABM = home.create();
            if (userABM.userExist(data.getLoginName(), userSessionData)) {
                throw new AlreadyExistsException("Has ocurred error in class persistence.user.UserABM(userCreate), user already exist","generic.user.alreadyexist");
            }
            
			userABM.userCreate(createData,userSessionData);
			
		}  catch (Exception exception) {
			Object[] parameters = new Object[1];
			parameters[0] = data;
            handler.handleException("userCreate",exception, userSessionData, parameters);
        } 
	}
	
	/**
	 * @ejb.interface-method view-type = "remote"
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,usercreate"
     */
    public String userFleetDriverCreate(DriverData driver,String language,UserSessionData userSessionData) throws Exception {
    	String loginName = null;
        try {
            if (userSessionData == null) {
                throw new DeslogedException("userFleetDriverCreate","session is null");
            }
            if (driver == null) {
                throw new InvalidDataException("userFleetDriverCreate","driver is null");
            }
            
            if(Util.empty(driver.getEmail())) {
                throw new InvalidDataException("userFleetDriverCreate","driver mail is null");
            }
            
            UserReport userReport = UserReportUtil.getHome().create();
            int i = 1;
			MD5 md5 = new MD5();
            loginName = "fleetUser"+String.valueOf(driver.getId())+"_"+i;
            String crypt = md5.encriptar(loginName); 
            while(userReport.userExist(crypt, userSessionData)) {
            	i++;
            	loginName = md5.encriptar("fleetUser"+String.valueOf(driver.getId())+"_"+i);
            	crypt = md5.encriptar(loginName);
            }
            	
            UserData user = new UserData(crypt);
            user.setPassword(md5.encriptar(crypt));
            user.setFirstName(driver.getFirstName());
            user.setLastName(driver.getLastName());
            user.setEmail(driver.getEmail());
            user.setCorporationId(userSessionData.getFilter().getCorporationId());
            user.setLanguage(language);
            RoleReportHome roleHome = RoleReportUtil.getHome();
            RoleData roleData = roleHome.create().findRoleByPrimaryKey(RoleData.FLEET_DRIVER,userSessionData);
            user.setRole(roleData);
            Iterator it = roleData.getPermissions().iterator();
            while(it.hasNext()) {
                user.addPermission((PermissionData)it.next());
            }
            if(userSessionData.getCorporationId() == CorporationData.MONSANTO && driver.getCountryName().equals(CountryData.ARGENTINA)) {
            	user.addPermission(new PermissionData(SecurityConstants.DRIVER_CREATE_EVENT));
            }
            user.setDriverData(driver);
            
            UserABM userABM = UserABMUtil.getHome().create();
            userABM.createDriverUser(user,userSessionData);
            
        } catch (Exception e) {
        	Object[] parameters = new Object[2];
        	parameters[0] = driver;
        	parameters[1] = language ;
            handler.handleException("userFleetDriverCreate", e, userSessionData,parameters);
        }
        return loginName;
    }


	/**
	 * @ejb.interface-method view-type = "remote"
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,usercreate"
     */
    public String userDriverCreate(Integer driverId,String password,String language,UserSessionData userSessionData) throws Exception {
        try {
            if (userSessionData == null) {
                throw new DeslogedException("userDriverCreate","session is null");
            }
            if (driverId == null) {
                throw new InvalidDataException("userDriverCreate","driver is null");
            }
            DriverReport driverReport = DriverReportUtil.getHome().create();
            DriverData driver = driverReport.findDriverByPrimaryKey(driverId,userSessionData);
            
            if(Util.empty(driver.getEmail())) {
                throw new InvalidDataException("userDriverCreate","driver mail is null");
            }
            UserData user = new UserData(driver.getEmail());
            if(Util.empty(password)) {
                int type = (userSessionData.getFilter().getCorporationId().intValue() != CorporationData.JJ) ? UserAttributes.SIMPLE : UserAttributes.DIFFICULT;
                password = getNewPass(type);
            }
            user.setPassword(password);
            user.setFirstName(driver.getFirstName());
            user.setLastName(driver.getLastName());
            user.setEmail(driver.getEmail());
            user.setCorporationId(userSessionData.getFilter().getCorporationId());
            user.setLanguage(language);
            RoleReportHome roleHome = RoleReportUtil.getHome();
            RoleData roleData = roleHome.create().findRoleByPrimaryKey(RoleData.DRIVER,userSessionData);
            user.setRole(roleData);
            Iterator it = roleData.getPermissions().iterator();
            while(it.hasNext()) {
                user.addPermission((PermissionData)it.next());
            }
            user.setDriverData(driver);
            
            UserABM userABM = UserABMUtil.getHome().create();
            userABM.createDriverUser(user,userSessionData);
            
        } catch (Exception e) {
        	Object[] parameters = new Object[3];
        	parameters[0] = driverId;
        	parameters[1] = password;
        	parameters[2] = language;
            handler.handleException("userDriverCreate", e, userSessionData, parameters);
        }
        return password;
    }


	/**
	 * @ejb.interface-method view-type = "remote"
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,userchangepassword"
	 * 
	 * @param data
	 * @throws Exception
	 */
	public void userResetPassword(UserData data,UserSessionData userSessionData) throws Exception {

		UserABM userABM=null;
		try {
			if (userSessionData == null) {
				throw new DeslogedException("Class business.user.UserABMFacade(userResetPassword), session data is empty","generic.error.deslogued");
			}
			if (data == null) {
				throw new InvalidDataException("Class business.user.UserABMFacade(userResetPassword), user data is empty","generic.user.userdata.isempty");
			}
			
			if (data.getPassword()==null) {
				throw new InvalidDataException("Class business.user.UserABMFacade(userResetPassword), password is empty","generic.user.userdata.pass.isempty");
			}

			UserABMHome home = UserABMUtil.getHome();
			userABM = home.create();
			userABM.userResetPassword(data, userSessionData);
		} catch (Exception exception) {
			Object[] parameters = new Object[1];
			parameters[0] = data;
            handler.handleException("userResetPassword", exception, userSessionData, parameters);
        } 
	}

	/**
	 * @ejb.interface-method view-type = "remote"
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,systemaccess,firstaccess"
	 * 
	 * @param data
	 * @throws Exception
	 */
	public void userResetOwnPassword(String password,UserSessionData userSessionData) throws Exception {

		UserABM userABM=null;
		try {
			if (userSessionData == null) {
				throw new DeslogedException("Class business.user.UserABMFacade(userResetPassword), session data is empty","generic.error.deslogued");
			}
			if (password == null) {
				throw new InvalidDataException("Class business.user.UserABMFacade(userResetPassword), password is empty","generic.user.userdata.pass.isempty");
			}

			UserABMHome home = UserABMUtil.getHome();
			userABM = home.create();
			userABM.userResetOwnPassword(password,userSessionData);
		}  catch (Exception exception) {
			Object[] parameters = new Object[1];
			parameters[0] = password;
            handler.handleException("userResetOwnPassword", exception,userSessionData,parameters);
        } 
	}

    /**
	 * @ejb.interface-method view-type = "remote"
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,systemaccess"
	 * 
	 * @param lngName is a valid language name ISO 639 two letter codes
	 * @throws Exception
	 */
	public void userSwitchLanguage(String lngName,UserSessionData userSessionData) throws Exception {

		UserABM userABM=null;
		try {
			if (userSessionData == null) {
				throw new DeslogedException("Class business.user.UserABMFacade(userSwitchLanguage), session data is empty","generic.error.deslogued");
			}
			
			UserABMHome home = UserABMUtil.getHome();
			userABM = home.create();
			
			userABM.userSwitchLanguage(lngName,userSessionData);
			userSessionData.setLenguage(lngName);
			
		}  catch (Exception exception) {
			Object[] parameters = new Object[1];
			parameters[0] = lngName;
            handler.handleException("userSwitchLanguage", exception, userSessionData,parameters);
        } 
	}

    /**
     * @ejb.interface-method view-type = "remote"
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,systemaccess"
     * 
     * @param corporationId
     * @throws Exception
     */
    public void userSwitchCorporation(Integer corporationId,UserSessionData userSessionData) throws Exception {

        UserABM userABM=null;
        try {
            if (userSessionData == null) {
                throw new DeslogedException("Class business.user.UserABMFacade(userSwitchCorporation), session data is empty","generic.error.deslogued");
            }

        	UserABMHome home = UserABMUtil.getHome();
            userABM = home.create();
            
            userSessionData.getFilter().setCorporationId(corporationId);
            userABM.userSwitchCorporation(corporationId,userSessionData);
            
        }  catch (Exception exception) {
        	Object[] parameters = new Object[1];
        	parameters[0] = corporationId;
            handler.handleException("userSwitchCorporation", exception, userSessionData,parameters);
        } 
    }

	/**
	 * @ejb.interface-method
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,userdelete"
	 * 
	 * @param data
	 * @throws Exception
	 */
	public void userDelete(UserData data,UserSessionData userSessionData) throws Exception {
		
		UserABM userABM=null;
		try {
			if (userSessionData == null) {
				throw new DeslogedException("Class business.user.UserABMFacade(userDelete), session data is empty","generic.error.deslogued");
			}
			if (data == null) {
				throw new InvalidDataException("Class bisiness.user.UserABMFacade(userDelete), user data is empty","generic.useruserdata.isempty");
			}

			UserABMHome home = UserABMUtil.getHome();
			userABM = home.create();
            userABM.deleteUserGroup(data, userSessionData);
			userABM.userDelete(data, userSessionData);

		}  catch (Exception exception) {
			Object[] parameters = new Object[1];
			parameters[0] = data;
            handler.handleException("userDelete", exception, userSessionData, parameters);
        } 
	}

	/**
	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,systemaccess,userupdate,userupdateaccess"
	 * 
	 * @param data
	 * @throws Exception
	 */
	public void userUpdate(UserData data,UserSessionData userSessionData) throws Exception {
		try{
			userUpdate(data,true,userSessionData);
		}catch (Exception e){
			Object[] parameters = new Object[1];
			parameters[0] = data;
            handler.handleException("userUpdate", e, userSessionData, parameters);
		}
	}
	
	/**
	 * @ejb.interface-method
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,systemaccess,userupdate,userupdateaccess"
	 * 
	 * @param data
	 * @throws Exception
	 */
	public void userUpdate(UserData data, boolean validate, UserSessionData userSessionData) throws Exception {

		UserABM userABM=null;
		try {
			if (userSessionData == null) {
				throw new DeslogedException("Class business.user.UserABMFacade(userUpdate), session data is empty","generic.error.deslogued");
			}
			if (data == null) {
				throw new InvalidDataException("Class business.user.UserABMFacade(userUpdate), user data is empty","generic.user.usuerdata.isempty");
			}
	        if (!validate(data,userSessionData)) {
	            throw new InvalidDataException("Class business.user.UserABMFacade(userCreate), invalid user data","generic.user.userdata.isinvalid");
	        }

			UserABMHome home = UserABMUtil.getHome();
			userABM = home.create();
			
			userABM.userUpdate(data,userSessionData,validate);
		}  catch (Exception exception) {
			Object[] parameters = new Object[2];
			parameters[0] = data;
			parameters[1] = validate;
            handler.handleException("userUpdate", exception,userSessionData, parameters);
        } 
	}

	private boolean validate(UserData data,UserSessionData userSessionData) {

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
        if (data.getRole() == null || data.getRole().getName() == null
                || data.getRole().getName().trim().length() == 0) {
            return false;
        }

		return true;

	}

	/**
     * @ejb.interface-method
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,userpermission"
     * @throws Exception
     */ 
	public void userModifyRole(String userName, String newRole, UserSessionData userSessionData) throws Exception {

		UserABM userABM=null;
		try {
			if (userSessionData == null) {
				throw new DeslogedException("Class business.user.UserABMFacade(userUpdate), session data is empty","generic.error.deslogued");
			}

            RoleReportHome roleHome = RoleReportUtil.getHome();
		    RoleReport roleReport = roleHome.create();
		    RoleData roleData = roleReport.findRoleByPrimaryKey(newRole,userSessionData);
		    Iterator it = roleData.getPermissions().iterator();
            Collection permissionKeys = new LinkedList();
		    while(it.hasNext()) {
		        permissionKeys.add(((PermissionData)it.next()).getName());
		    }

            UserABMHome userHome = UserABMUtil.getHome();
		    userABM = userHome.create();
		    userABM.userUpdateRole(userName,newRole,permissionKeys,userSessionData);
            
		}  catch (Exception exception) {
			Object [] parameters = new Object[2];
			parameters[0] = userName;
			parameters[1] = newRole;
            handler.handleException("userModifyRole", exception,userSessionData, parameters);
        } 
	}

    /**
     * @ejb.interface-method
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "resetpassword"
     */
	public String resetPassword(UserAttributes userData, UserSessionData userSessionData,int passwordType) throws Exception {
        try {
            String password = getNewPass(passwordType);
            UserABMHome userHome = UserABMUtil.getHome();
            UserABM userABM = userHome.create();
            userABM.resetPassword(userData,new MD5().encriptar(password),userSessionData);
            return password;
        }  catch (Exception exception) {
        	Object[] parameters = new Object[2];
        	parameters[0] = userData;
        	parameters[1] = passwordType;
            handler.handleException("resetPassword", exception, userSessionData, parameters);
        } 
        return null;
    }

    /**
     * @ejb.interface-method
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "resetpassword"
     */
    public String createDriverUser(DriverData driver, UserSessionData userSessionData,String language) throws Exception {
        try {
            int passwordType = (userSessionData.getFilter().getCorporationId().intValue() == CorporationData.JJ) ? UserAttributes.DIFFICULT : UserAttributes.SIMPLE; 
            String password = getNewPass(passwordType);
            UserABMHome userHome = UserABMUtil.getHome();
            UserABM userABM = userHome.create();
            String loginName = driver.getEmail();
            if(!userABM.userExist(loginName,userSessionData)) {

                RoleReportHome roleHome = RoleReportUtil.getHome();
                RoleData roleData = roleHome.create().findRoleByPrimaryKey("driver",userSessionData);
                Iterator roles = roleData.getPermissions().iterator();
                Hashtable permissions = new Hashtable();
                while(roles.hasNext()) {
                    PermissionData permission = (PermissionData)roles.next();
                	if(!(userSessionData.isCorporation(new int[]{CorporationData.BAYER,CorporationData.COVESTRO})
                    		&& (permission.getName().equals(SecurityConstants.DRIVER_CREATE_EVENT)
                    			|| permission.getName().equals(SecurityConstants.DRIVER_MODIFY_VEHICLE)))) {
                		permissions.put(permission.getName(),permission);
                	}
                }
                UserData userData = new UserData(password,driver.getFirstName(),driver.getLastName(),language,loginName,driver.getEmail(),driver.getCorporation().getId(),permissions,roleData,new LinkedList());
                userData.setDriverData(driver);
                userABM.createDriverUser(userData,userSessionData);
                                
                return password;
            }
        }  catch (Exception exception) {
        	Object[] parameters = new Object[2];
        	parameters[0]=driver;
        	parameters[1] = language;
            handler.handleException("resetPassword", exception, userSessionData, parameters);
        } 
        return null;
    }
    
    /**
     * @ejb.interface-method
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "resetpassword,administrator,usercreate"
     */
    public String getNewPass(int passwordType){
        String randomCharacterSet = (passwordType == UserAttributes.SIMPLE) ? "0123456789" : "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789@#$%^&+=";
        String result;     
        do {
            result = "";
            for (int i=1; i<=9; i++) {  
                int x = (int)Math.round(Math.random()*(randomCharacterSet.length()-1));
                result+=randomCharacterSet.charAt(x);
            }
        }while((passwordType != UserAttributes.SIMPLE) && (UserData.validatePassword(result) != null));
        return(result);
        
    }

    /**
     * @ejb.interface-method
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,userupdateaccess"
     */
    public void deleteUserGroup(String user,Collection groups,UserSessionData userSessionData) throws Exception {
        try{    
            GroupABMHome home = GroupABMUtil.getHome();
            GroupABM groupABM = home.create();
            Iterator it = groups.iterator();
            while(it.hasNext()) {
                groupABM.deleteUserGroup(new Integer((String)it.next()), user,userSessionData);
            }
        } catch (Exception e) {
        	Object [] parameters = new Object[2];
        	parameters[0] = user;
        	parameters[1] = groups;        	
            handler.handleException("deleteUserGroup",e, userSessionData, parameters);
        } 
    }

    /**
     * @ejb.interface-method
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,userupdateaccess"
     */
    public void addUserGroup(String user, Collection groups, UserSessionData userSessionData) throws Exception {
        try{    
            GroupABMHome home = GroupABMUtil.getHome();
            GroupABM groupABM = home.create();
            Iterator it = groups.iterator();
            while(it.hasNext()) {
                groupABM.addUserGroup(new Integer((String)it.next()),user, userSessionData);
            }
        } catch (Exception e) {
        	Object[] parameters = new Object[2];
        	parameters[0] = user;
        	parameters[1] = groups;
            handler.handleException("addUserGroup",e,userSessionData,parameters);
        } 
    }
    

    /**
     * Agrega a un usuario de rol contratista, el grupo del contratista seleccionado.
     * @ejb.interface-method
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,userupdateaccess,groupcreate"
     */
    public void addUserContractorGroup(String loginName, String contractorName,
    		String contractorId, Collection groupLines, 
    		UserSessionData userSessionData) throws Exception {
        try{       	
        	
        	GroupReportHome groupReportHome = GroupReportUtil.getHome();
        	GroupReport groupReport = groupReportHome.create();
        	GroupABMHome homeABM = GroupABMUtil.getHome();
            GroupABM groupABM = homeABM.create();
        	
        	Integer groupId = groupReport.getContractorGroup(contractorId, userSessionData);
        	if (groupId == null){
        		/* Si no existe un grupo individual para ese contratista 
        		con una única linea con la division de ese contratista
        		creo un nuevo grupo */
        		boolean active = true;
        		String description = "generic.messages.groupcreatedbycontractorusers";
        		String name = contractorName;
        		GroupMainAttributes dataMain = new GroupMainAttributes(null,name, 
        				description, active, 
        				GroupMainAttributes.MONTHLY, GroupMainAttributes.INDIVIDUAL);
        		groupId = groupABM.createGroup(dataMain,userSessionData);
        		
        		Iterator it = groupLines.iterator();
                while(it.hasNext()) {
                     GroupLineData groupLine = (GroupLineData)it.next();
                     String valid = groupLine.validate();
                     if (valid != null) {
                         throw new InvalidDataException("addUserContractorGroup",valid);
                     }
                     groupABM.addLine(groupId,groupLine,userSessionData);
                }                        		    			
        	}
        	
        	// Agregar el usuario al grupo:
            groupABM.addUserGroup(groupId,loginName, userSessionData);
            
        } catch (Exception e) {
        	Object[] parameters = new Object[4];        	
    		parameters[0] = loginName;
        	parameters[1] = contractorName;
        	parameters[2] = contractorId;
        	parameters[3] = groupLines;
            handler.handleException("addUserGroup",e,userSessionData,parameters);
        } 
    }
    
    
    
    

    /**
     * @ejb.interface-method
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,systemaccess"
     */
    public void saveLogin(long date,UserSessionData userSessionData) throws Exception {
        try{    
            UserABMHome userHome = UserABMUtil.getHome();
            UserABM userABM = userHome.create();
            userABM.saveLogin(date,userSessionData);
        } catch (Exception e) {
        	Object[] parameters = new Object[1];
        	parameters[0] = date;
            handler.handleException("addUserGroup",e,userSessionData,parameters);
        } 
    }
    
    /**
     * @ejb.interface-method
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator"
     */
    public void associateUserToCorporation(Integer corporationId,String userId,
    		String roleId,UserSessionData userSessionData) throws Exception {
        try{    
            UserABMHome userHome = UserABMUtil.getHome();
            UserABM userABM = userHome.create();
            userABM.associateUserToCorporation(corporationId,userId,roleId,userSessionData);
        } catch (Exception e) {
        	Object[] parameters = new Object[3];
        	parameters[0] = corporationId;
        	parameters[1] = userId;
        	parameters[2] = roleId;
            handler.handleException("associateUserToCorporation",e,userSessionData,parameters);
        } 
    }

    /**
     * @ejb.interface-method
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,systemaccess"
     */
    public void addBookmarkItem(BookmarkData data,UserSessionData userSessionData) throws Exception {
        try{    
            UserABMHome userHome = UserABMUtil.getHome();
            UserABM userABM = userHome.create();
            if(data.isStart()) {
                userABM.udpateDefaulttBookmarkItem(userSessionData);
            }
            userABM.addBookmarkItem(data,userSessionData);
        } catch (Exception e) {
        	Object[] parameters = new Object[1];
        	parameters[0] = data;
            handler.handleException("addBookmarkItem",e,userSessionData,parameters);
        } 
    }

    /**
     * @ejb.interface-method
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,systemaccess"
     */
    public void udpateBookmarkItem(BookmarkData data,UserSessionData userSessionData) throws Exception {
        try{    
            UserABMHome userHome = UserABMUtil.getHome();
            UserABM userABM = userHome.create();
            if(data.isStart()) {
                userABM.udpateDefaulttBookmarkItem(userSessionData);
            }
            userABM.udpateBookmarkItem(data,userSessionData);
        } catch (Exception e) {
        	Object[] parameters = new Object[1];
        	parameters[0] = data;
            handler.handleException("udpateBookmarkItem",e,userSessionData,parameters);
        } 
    }

    /**
     * @ejb.interface-method
     * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,systemaccess"
     */
    public void deleteBookmarkItems(Collection bookmarks,UserSessionData userSessionData) throws Exception {
        try{    
            UserABMHome userHome = UserABMUtil.getHome();
            UserABM userABM = userHome.create();
            Iterator it = bookmarks.iterator();
            while(it.hasNext()) {
                userABM.deleteBookmarkItem((String)it.next(),userSessionData);
            }
        } catch (Exception e) {
        	Object[] parameters = new Object[1]; parameters[0] =bookmarks;
            handler.handleException("deleteBookmarkItems",e,userSessionData,parameters);
        } 
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,usercreate"
     */
    public String userDriverCreate(DriverData driver,String password,String language,
    		UserSessionData userSessionData) throws Exception {
        try {
            if (userSessionData == null) {
                throw new DeslogedException("userDriverCreate","session is null");
            }
            if (driver == null) {
                throw new InvalidDataException("userDriverCreate","driver is null");
            }
            
            if(Util.empty(driver.getEmail())) {
                throw new InvalidDataException("userDriverCreate","driver mail is null");
            }
            UserData user = new UserData(driver.getEmail());
            if(Util.empty(password)) {
                int type = (userSessionData.getFilter().getCorporationId().intValue() != CorporationData.JJ) ? UserAttributes.SIMPLE : UserAttributes.DIFFICULT;
                password = getNewPass(type);
            }
            if(driver.getCountryName().equals(CountryData.BRASIL)) {
                password = new MD5().encriptar(password);
            }
            user.setPassword(password);
            user.setFirstName(driver.getFirstName());
            user.setLastName(driver.getLastName());
            user.setEmail(driver.getEmail());
            user.setCorporationId(userSessionData.getFilter().getCorporationId());
            user.setLanguage(language);
            RoleReportHome roleHome = RoleReportUtil.getHome();
            RoleData roleData = roleHome.create().findRoleByPrimaryKey(RoleData.DRIVER,userSessionData);
            user.setRole(roleData);
            Iterator it = roleData.getPermissions().iterator();
            while(it.hasNext()) {
            	PermissionData permission = (PermissionData)it.next();
            	if(!(userSessionData.isCorporation(new int[]{CorporationData.BAYER,CorporationData.COVESTRO})
            		&& (permission.getName().equals(SecurityConstants.DRIVER_CREATE_EVENT)
            			|| permission.getName().equals(SecurityConstants.DRIVER_MODIFY_VEHICLE)))) {
            		user.addPermission(permission);
            	}
            }
            user.setDriverData(driver);
            
            UserABM userABM = UserABMUtil.getHome().create();
            userABM.createDriverUser(user,userSessionData);
            
        } catch (Exception e) {
        	Object[] parameters = new Object[3];
        	parameters[0] = driver;
        	parameters[1] = password;
        	parameters[2] = language;
            handler.handleException("userDriverCreate", e, userSessionData, parameters);
        }
        return password;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,usercreate"
     */
    public String userDriverCreate(String login, DriverData driver,String password,String language, UserSessionData userSessionData) throws Exception {
        try {
            if (userSessionData == null) {
                throw new DeslogedException("userDriverCreate","session is null");
            }
            if (driver == null) {
                throw new InvalidDataException("userDriverCreate","driver is null");
            }
            
            if(Util.empty(driver.getEmail())) {
                throw new InvalidDataException("userDriverCreate","driver mail is null");
            }
            UserData user = new UserData(driver.getEmail());
            user.setLoginName(login);
            user.setPassword(password);
            user.setFirstName(driver.getFirstName());
            user.setLastName(driver.getLastName());
            user.setEmail(driver.getEmail());
            user.setCorporationId(userSessionData.getFilter().getCorporationId());
            user.setLanguage(language);
            RoleReportHome roleHome = RoleReportUtil.getHome();
            RoleData roleData = roleHome.create().findRoleByPrimaryKey(RoleData.DRIVER,userSessionData);
            user.setRole(roleData);
        	user.addPermission(new PermissionData(SecurityConstants.ACCESS_TO_SYSTEM));
        	user.addPermission(new PermissionData(SecurityConstants.VIEW_DRIVER_LIST));
        	user.addPermission(new PermissionData(SecurityConstants.DRIVER_VIEW_OWN_DATA));
            user.setDriverData(driver);
            
            UserABM userABM = UserABMUtil.getHome().create();
            userABM.createDriverUser(user,userSessionData);
            
        } catch (Exception e) {
        	Object[] parameters = new Object[3];
        	parameters[0] = driver;
        	parameters[1] = password;
        	parameters[2] = language;
            handler.handleException("userDriverCreate", e, userSessionData, parameters);
        }
        return password;
    }

    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,usercreate"
     */
    public String userCDSCreate(DriverData driver,String loginname,String password,String language,UserSessionData userSessionData) throws Exception {
        try {
            if (userSessionData == null) {
                throw new DeslogedException("userCDSCreate","session is null");
            }
            if (driver == null) {
                throw new InvalidDataException("userCDSCreate","driver is null");
            }
            UserData user = new UserData(loginname);
            user.setPassword(password);
            user.setFirstName(driver.getFirstName());
            user.setLastName(driver.getLastName());
            user.setEmail(driver.getEmail());
            user.setCorporationId(userSessionData.getFilter().getCorporationId());
            user.setLanguage(language);
            RoleReportHome roleHome = RoleReportUtil.getHome();
            RoleData roleData = roleHome.create().findRoleByPrimaryKey(RoleData.CDS,userSessionData);
            user.setRole(roleData);
            Iterator it = roleData.getPermissions().iterator();
            while(it.hasNext()) {
                user.addPermission((PermissionData)it.next());
            }
            user.setDriverData(driver);
            
            UserABM userABM = UserABMUtil.getHome().create();
            userABM.createDriverUser(user, userSessionData);
            
        } catch (Exception e) {
        	Object[] parameters = new Object[3];
        	parameters[0] = driver;
        	parameters[1] = password;
        	parameters[2] = language;
            handler.handleException("userCDSCreate", e, userSessionData, parameters);
        }
        return password;
    }
    
    /**
     * @ejb.interface-method
     * @ejb.permission role-name = "administrator,usercreate"
     */
    public void userAddCorporation(String user, Integer corporation, String role, UserSessionData userSessionData) throws Exception {
        try {
            UserABM userABM = UserABMUtil.getHome().create();
            userABM.userAddCorporation(user, corporation, role, userSessionData);
	   } catch (Exception e) {
	    	Object[] parameters = {user,corporation,role};
	        handler.handleException("userAddCorporation", e, userSessionData, parameters);
	    }    	
    }
 
    
}