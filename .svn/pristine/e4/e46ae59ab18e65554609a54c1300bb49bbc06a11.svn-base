/**
 * Created on 07-dic-2006
 * CEPA
 * DataCenter 5
 */
package persistence.administration.tables;

import java.io.Serializable;

import persistence.administration.group.tables.AbstractGroup;
import persistence.administration.user.tables.User;

public class UserGroup implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserGroupPK pk;
    
    public UserGroup() {
    }

    public UserGroup(User user, AbstractGroup group) {
        pk = new UserGroupPK(user,group);
    }

    public UserGroupPK getPk() {
        return pk;
    }

    public void setPk(UserGroupPK pk) {
        this.pk = pk;
    }

}
