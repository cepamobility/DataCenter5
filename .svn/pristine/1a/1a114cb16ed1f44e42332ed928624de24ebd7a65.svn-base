package persistence.log.user;

import java.io.Serializable;
import java.util.Date;

import persistence.administration.user.tables.User;

import communication.exception.InvalidDataException;


public class UserLog implements Serializable {

    private static final long serialVersionUID = 1L;

    private UserLogPK pk;
    private Integer action;
    
	private String password;
	private String firstName;
	private String lastName;
	private String language;
	private String loginName;
	private String email;
	private Integer corporationId;
	
	public UserLog() {
	}

	public UserLog(User user,String loginUser,Date date,int action) throws InvalidDataException {
	    pk = new UserLogPK(user.getLoginName(),loginUser,date);
	    this.action = Integer.valueOf(action);
		corporationId=user.getCorporationId();	
		firstName = user.getFirstName();
		lastName = user.getLastName();
		email = user.getEmail();
		language = user.getLanguage();
		loginName = user.getLoginName();
		password = user.getPassword();
	}

    /**
     * @return Returns the pk.
     */
    public UserLogPK getPk() {
        return pk;
    }

    /**
     * @return Returns the action.
     */
    public Integer getAction() {
        return action;
    }

    /**
	 * 
	 * @return First Name
	 */
	public String getFirstName() {
		return firstName;
	}

	/** 
	 * @param _firstName
	 */
	public void setFirstName(String _firstName) {
		firstName = _firstName;
	}

	/**
	 * @return Last Name
	 */
	public String getLastName() {
		return lastName;
	}

    /**
     * @param pk The pk to set.
     */
    public void setPk(UserLogPK pk) {
        this.pk = pk;
    }

    /**
     * @param action The action to set.
     */
    public void setAction(Integer action) {
        this.action = action;
    }
    
	/**
	 * @param _lastName
	 */
	public void setLastName(String _lastName) {
		lastName = _lastName;
	}

	/**
	 * @return Preference language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param _language
	 */
	public void setLanguage(String _language) {
		language = _language;
	}

	/**
	 * @return Nick Name
	 */
	public String getLoginName() {
		return this.loginName;
	}

	/**
	 * @param _loginName
	 */
	public void setLoginName(String _loginName) {
		this.loginName = _loginName;
	}

	/**
	 * @return Email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param _email
	 */
	public void setEmail(String _email) {
		email = _email;
	}

	/**
	 * @return Password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return Corporation
	 */

	public Integer getCorporationId() {
		return corporationId;
	}
	
	public void setCorporationId(Integer corporationid) {
		this.corporationId = corporationid;
	}
	
}