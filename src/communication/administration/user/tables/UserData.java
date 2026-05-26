package communication.administration.user.tables;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;

import communication.administration.RoleData;
import communication.administration.corporation.tables.GeoLabelData;
import communication.administration.corporation.tables.LabelItemData;
import communication.administration.group.tables.GroupData;
import communication.administration.group.tables.GroupLineData;
import communication.administration.user.PermissionData;
import communication.driver.tables.DriverData;

public class UserData extends UserAttributes implements Serializable {

    private static final long serialVersionUID = 1L;

    protected Hashtable permissions;
    protected RoleData role;

    private DriverData driverData;
	
    private Collection groupLines;
    private Integer contractorId;
    
    private Date alertDate;
    
    //group
    private String group;
    
    public static final int FIRST_NAME = 1;
    public static final int LAST_NAME = 2;
    public static final int NICK_NAME = 3;
    public static final int MAIL = 4;
    
    public static final int FIRST_OR_LAST_NAME = 5;

    /**
     * @param password
     * @param firstName
     * @param lastName
     * @param language
     * @param loginName
     * @param email
     * @param corporationId
     * @param groupLines2 
     * @param driverData2 
     */
    public UserData(String password, String firstName, String lastName, String language,String loginName, String email,Integer corporationId,Hashtable permissions,
    		RoleData role, Collection groupLines) {
        this(password,firstName,lastName,language,loginName,email,corporationId,permissions,role,null,groupLines,null);
    }
    

    /**
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param language
	 * @param loginName
	 * @param email
	 * @param corporationId
     * @param driverData
     *  
	 */
    //@param group 
	public UserData(String password, String firstName, String lastName,	String language,String loginName, String email,Integer corporationId,Hashtable permissions,
			RoleData role, DriverData driverData, Collection groupLines, Integer contractorId) {//, String group
		super(password, firstName, lastName, language, loginName, email,corporationId, null);
        this.role = role;
        this.permissions = permissions;        
        this.driverData = driverData;
        this.groupLines = groupLines;
        this.contractorId = contractorId; 
        //this.group = group;
	}
    
    public UserData(String loginName) {
        super(loginName);
        permissions = new Hashtable();
    }

    /**
     * @return true if the user has permissions for action.
     */
    public boolean checkPermission(String action) {
        return permissions.containsKey(action);
    }

    /**
     * Add new permission to a user
     * @param string new permission
     */
    public void addPermission(PermissionData permission) {
        permissions.put(permission.getName(),permission);
    }

    /**
     * Remove an existing permission from a user
     * @param string removed permission
     */
    public void removePermission(String permission) {
        permissions.remove(permission);
    }

    public DriverData getDriverData() {
        return driverData;
    }

    public void setDriverData(DriverData driverData) {
        this.driverData = driverData;
    }

    public void loadAccess(Collection groups) {
        Iterator groupIt = groups.iterator();
        while(groupIt.hasNext()) {
            GroupData group = (GroupData)groupIt.next();
            Iterator itemIt = group.getLinesIterator();
            while(itemIt.hasNext()) {
                GroupLineData line = (GroupLineData)itemIt.next();
                LabelItemData[] items = line.getDivOrgs();
                GeoLabelData geoData = line.getCountry();
            }
        }
    }

    public Collection getGroupLines() {
        return groupLines;
    }
    
    public RoleData getRole() {
        return role;
    }

    /**
     * @return
     */
    public Hashtable getPermissions() {
        return permissions;
    }

    /**
     * @param newRole
     */
    public void setRole(RoleData role) {
        this.role = role;
    }

    public static String validatePassword(String passwd) {
        if(passwd.length() < 6) {
            return "user.error.shortpassword";
        }
        String extraChars = "@#$%^&+=";
        boolean upper = false;
        boolean lower = false;
        boolean extraChar = false;
        for(int i = 0; i < passwd.length(); i++) {
            char c = passwd.charAt(i);
            if(Character.isUpperCase(c)) {
                upper = true;
            } else if(Character.isLowerCase(c)) {
                lower = true;
            } else if(Character.isDigit(c) || extraChars.contains(String.valueOf(c))) {
                extraChar = true;
            }
        }
        if(!upper || !lower || !extraChar) {
            return "user.error.invalidpassword";
        }
        return null;
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
	 * @param permissions the permissions to set
	 */
	public void setPermissions(Hashtable permissions) {
		this.permissions = permissions;
	}

	/**
	 * @param groupLines the groupLines to set
	 */
	public void setGroupLines(Collection groupLines) {
		this.groupLines = groupLines;
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
}