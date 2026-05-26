/*
 * Created on 17-ago-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.user;

import org.apache.struts.action.ActionForm;

/**
 * @author fcarriquiry
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class RoleForm extends ActionForm {

    private static final long serialVersionUID = 1L;

	private String loginname;
    private String newrole;
    private String oldrole;

	public RoleForm() {	    
	}
	
	public RoleForm(String loginname, String role) {
	    this.loginname = loginname;
        oldrole = role;
        newrole = role;
	}

    public String getLoginname() {
        return loginname;
    }

    public String getNewrole() {
        return newrole;
    }

    public String getOldrole() {
        return oldrole;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public void setNewrole(String newrole) {
        this.newrole = newrole;
    }

    public void setOldrole(String oldrole) {
        this.oldrole = oldrole;
    }
}