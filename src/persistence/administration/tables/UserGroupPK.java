/**
 * Created on 07-dic-2006
 * CEPA
 * DataCenter 5
 */
package persistence.administration.tables;

import java.io.Serializable;

import persistence.administration.group.tables.AbstractGroup;
import persistence.administration.user.tables.User;

public class UserGroupPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AbstractGroup group;
    private User user;
    
    public UserGroupPK() {
    }

    public UserGroupPK(User user, AbstractGroup group) {
        this.user = user;
        this.group = group;
    }

    public AbstractGroup getGroup() {
        return group;
    }

    public User getUser() {
        return user;
    }

    public void setGroup(AbstractGroup group) {
        this.group = group;
    }

    public void setUser(User user) {
        this.user = user;
    }

    
}
