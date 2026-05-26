package persistence.administration.user.tables;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;

import persistence.administration.tables.Role;
import persistence.driver.tables.Driver;

import communication.administration.RoleData;
import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.corporation.tables.LabelItemData;
import communication.administration.group.tables.GroupData;
import communication.administration.group.tables.GroupLineData;
import communication.administration.user.tables.UserAttributes;
import communication.administration.user.tables.UserData;
import communication.driver.tables.DriverData;
import communication.exception.InvalidDataException;
import communication.util.Filter;


public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String password;
	private String firstName;
	private String lastName;
	private String language;
	private String loginName;
	private String email;
	private Integer corporationId;
	
	
	private Role role;
	private Hashtable permissions; 
	
    private Driver driver;
    private Integer contractorId; // Id contratista, si es usuario contratista.
    
    private Date alertDate; // fecha de ultima alerta enviada a este usuario (se usua para usuarios fleetdrivers de Monsanto BR
    
    private String group;
    
	public User() {
	}

	public User(UserAttributes attrs) throws InvalidDataException {
		this.corporationId=attrs.getCorporationId();	
		this.firstName = attrs.getFirstName();
		this.lastName = attrs.getLastName();
		this.email = attrs.getEmail();
		this.language = attrs.getLanguage();
		this.loginName = attrs.getLoginName();
		this.password = attrs.getPassword();
		
		//groups
		this.group = attrs.getGroup();
	}

	public UserData getUserData(Collection groups) {
        DriverData driverData = (driver == null) ? null : driver.getDriverData();
        // Collection groupLines = groups;
        Collection groupLines = new LinkedList();
        
        if(driverData != null && !role.getResourceKey().equals(RoleData.CDS)) {
        	// Si es un usuario Driver:
        	
            Iterator it = driverData.getGroupLine().iterator();
            while(it.hasNext()) {
                groupLines.add((GroupLineData)it.next());
            }
            
        }else{
        	if (groups != null && groups.size() > 0){
        		Iterator it = groups.iterator();
        		Collection lines = new LinkedList();
        		while(it.hasNext()) {
        			GroupData data = (GroupData)it.next();
        			
        			//groupLines.addAll(data.getAllLines(lines));
        			groupLines.addAll(data.getAllLines(lines));
        		}
        	}
        }
	    
        UserData data = new UserData(password,firstName,lastName,language,loginName,email,
        		corporationId,permissions,
        		(role != null && role.getData() != null) ? role.getData():null,
        		driverData,groupLines,contractorId);
	    data.setAlertDate(alertDate);
	    
		return data;
	}
	
	/**
	 * Devuelve el user data cuando el rol es contratista.
	 * @param groups
	 * @return
	 */
	public UserData getUserContractorData(Collection groups, String contractorName) {
        Collection groupLines = new LinkedList();
        
        if (role.isContractor() && 
        	corporationId.equals(CorporationData.LOSPIQUES)
        	&& contractorId != null){
        
        	// Si es un usuario cuyo rol es Contratista 
        	// y corporacion es Los PIQUES:
        	
        	Iterator it = groups.iterator();
            while(it.hasNext()) {
                GroupLineData data = (GroupLineData)it.next();
                groupLines.add(data);                
            }   
            
            // Cambio los contratista (trasnportistas) de las lineas del grupo
            // al contratista asociado al usuario
            it = groupLines.iterator();
            while (it.hasNext()){
            	GroupLineData gp = (GroupLineData)it.next();
            	LabelItemData[] lid = gp.getDivOrgs();
            	Collection list = lid[2].getItems(); // DivOrg3: Transportista en los Piques
            	if (list != null){
            		Iterator itItems = list.iterator(); 
            		while (itItems.hasNext()){
            			ItemData itemData = (ItemData) itItems.next();
            			if (itemData != null && itemData.getId() != this.contractorId){
            				itemData.setId(this.contractorId);
            				itemData.setName(contractorName);
            			}
            		}
            	}
            	
            }
             
		}
	    UserData data = new UserData(password,firstName,lastName,language,loginName,email,corporationId,permissions,role.getData(),null,groupLines,contractorId);
	    data.setAlertDate(alertDate);
	    
		return data;
	}
	
	
	public Filter getFilter(){
		return null;
	}
	
	/**
	 * 
	 * @return First Name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * <p>
	 * Represents ...
	 * </p>
	 * 
	 * @param _firstName
	 */
	public void setFirstName(String _firstName) {
		firstName = _firstName;
	}

	/**
	 * <p>
	 * Represents ...
	 * </p>
	 * 
	 * 
	 * @return Last Name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * <p>
	 * Represents ...
	 * </p>
	 * 
	 * 
	 * 
	 * @param _lastName
	 */
	public void setLastName(String _lastName) {
		lastName = _lastName;
	}

	/**
	 * <p>
	 * Represents ...
	 * </p>
	 * 
	 * 
	 * @return Preference language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * <p>
	 * Represents ...
	 * </p>
	 * 
	 * 
	 * 
	 * @param _language
	 */
	public void setLanguage(String _language) {
		language = _language;
	}

	/**
	 * 
	 * @return Nick Name
	 */
	public String getLoginName() {
		return this.loginName;
	}

	/**
	 * <p>
	 * Represents ...
	 * </p>
	 * 
	 * 
	 * 
	 * @param _loginName
	 */
	public void setLoginName(String _loginName) {
		this.loginName = _loginName;
	}

	/**
	 * <p>
	 * Represents ...
	 * </p>
	 * 
	 * 
	 * @return Email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * <p>
	 * Represents ...
	 * </p>
	 * 
	 * 
	 * 
	 * @param _email
	 */
	public void setEmail(String _email) {
		email = _email;
	}

	/**
	 * 
	 * @return Password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 				  
	 * @return Corporation
	 */

	public Integer getCorporationId() {
		return corporationId;
	}
	
	public void setCorporationId(Integer corporationid) {
		this.corporationId = corporationid;
	}
	
	public boolean equals(Object arg0) {
		User u = (User) arg0;
		if (u != null && u.getLoginName() != null) {
			if (this.loginName != null
					&& this.loginName.compareTo(u.getLoginName()) == 0) {
				return true;
			}
		}
		return false;
	}

    /**
     * @param roleName2
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * @param permissions2
     */
    public void setPermissions(Hashtable permissions) {
        this.permissions = permissions;
    }

    /**
     * @return
     */
    public UserAttributes getPrincipalAttributes() {
        return new UserAttributes("",firstName,lastName,language,loginName,email,corporationId,null);//agregue 1 null por los groups,null
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

	/**
	 * @return the contractorId
	 */
	public Integer getContractorId() {
		return contractorId;
	}

	/**
	 * @param contractorId the contractorId to set
	 */
	public void setContractorId(Integer contractorId) {
		this.contractorId = contractorId;
	}

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @return the permissions
	 */
	public Hashtable getPermissions() {
		return permissions;
	}

	/**
	 * @return the alertDate
	 */
	public Date getAlertDate() {
		return alertDate;
	}

	/**
	 * @param alertDate the alertDate to set
	 */
	public void setAlertDate(Date alertDate) {
		this.alertDate = alertDate;
	}

	//groups
	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

}