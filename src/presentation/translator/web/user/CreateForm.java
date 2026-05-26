/*
 * Created on 17-ago-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.user;

import org.apache.struts.action.ActionForm;

import communication.administration.user.tables.UserAttributes;

/**
 * @author fcarriquiry
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class CreateForm extends ActionForm {

	/**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String password;
	private String rePassword;
	private String firstName;
	private String lastName;
	private String email;
	private String language;
	private String loginname;
	private String info;
    private String role;
    private String type;
    
    private String pageNum;
    private String all;
    private String pageSize;
   
    private String groupIds;
    private String action;
    
	public CreateForm() {	    
	}
	
	public CreateForm(UserAttributes data) {
	    loginname = data.getLoginName();
	    firstName = data.getFirstName();
	    lastName = data.getLastName();
	    email = data.getEmail();
	    loginname = data.getLoginName();
	    language = data.getLanguage();
	    role = data.getRoleName();
	    action = data.getGroup();
	}
	
    /**
     * @return Returns the email.
     */
    public String getEmail() {
        return email;
    }
    /**
     * @param email The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * @return Returns the firstName.
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * @param firstName The firstName to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * @return Returns the language.
     */
    public String getLanguage() {
        return language;
    }
    /**
     * @param language The language to set.
     */
    public void setLanguage(String language) {
        this.language = language;
    }
    /**
     * @return Returns the lastName.
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * @param lastName The lastName to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * @return Returns the loginname.
     */
    public String getLoginname() {
        return loginname;
    }
    /**
     * @param loginname The loginname to set.
     */
    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }
    /**
     * @return Returns the password.
     */
    public String getPassword() {
        return password;
    }
    /**
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * @return Returns the rePassword.
     */
    public String getRePassword() {
        return rePassword;
    }
    /**
     * @param rePassword The rePassword to set.
     */
    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }
    
    /**
     * @return Returns the info.
     */
    public String getInfo() {
        return info;
    }

    /**
     * @param info The info to set.
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * @return Returns the user's role.
     */
    public String getRole() {
        return role;
    }

    /**
     * @param info The role to set the user.
     */
    public void setRole(String role) {
        this.role = role;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

	
	
    //-----------
    
	public String getAll() {
		return all;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public void setAll(String all) {
		this.all = all;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	
	public String getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(String groupIds) {
		this.groupIds = groupIds;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}	
	
}