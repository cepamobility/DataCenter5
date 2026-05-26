/*
 * Created on 11-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package communication.administration.user.tables;

import java.io.Serializable;

/**
 * @author fcarriquiry
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UserAttributes implements Serializable{
	
    public static final int SIMPLE = 1;
    public static final int DIFFICULT = 2;    
    
    private static final long serialVersionUID = 1L;

    private String password;
	private String firstName;
	private String lastName;
	private String language;
	private String loginName;
	private String email;
    private String roleName;
	protected Integer corporationId;

	private String group;
	
	/**
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param language
	 * @param loginName
	 * @param email
	 * @param corporationId
	 * @param permissions 
	 * @param role 
	 * 
	 */
	public UserAttributes(String password, String firstName, String lastName, String language, String loginName, String email,
			Integer corporationId,String roleName) {
		super();
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.language = language;
		this.loginName = loginName;
		this.email = email;
		this.corporationId = corporationId;
        this.roleName = roleName;
        
        //group
        this.group = group;
	}

    public UserAttributes(String loginName) {
        this.loginName = loginName;
    }
    /**
	 * @return Returns the corporationId.
	 */
	public Integer getCorporationId() {
		return corporationId;
	}
	
	/**
	 * 
	 * @return Returns the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * 
	 * @return Returns the FirstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * 
	 * @return Returns the Language
	 */
	public String getLanguage() {
		return language;
	}
	
	/**
	 * 
	 * @return Returns the LastName
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * 
	 * @return Returns the LoginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * 
	 * @return Returns the Password
	 */
	public String getPassword() {
		return password;
	}
	
    /**
     * @param corporationId
     */
    public void setCorporationId(Integer corporationId) {
        this.corporationId = corporationId;
    }
    /**
     * @param email The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * @param firstName The firstName to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * @param language The language to set.
     */
    public void setLanguage(String language) {
        this.language = language;
    }
    /**
     * @param lastName The lastName to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * @param loginName The loginName to set.
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    /**
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getRoleName() {
        return roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}
    
}
