/**
 * Created on 28-nov-2006
 * CEPA
 * DataCenter 5
 */
package persistence.administration.group.tables;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import org.hibernate.Session;

import communication.administration.group.tables.GroupData;
import communication.administration.group.tables.GroupMainAttributes;

public class ComposedGroup extends AbstractGroup {

    private Set groups;
    
    public ComposedGroup() {
        super();
    }

    public ComposedGroup(GroupMainAttributes groupData, Session session, Integer corporationId) {
        super(groupData,session,corporationId);
    }

    public GroupData getData() {
        GroupData groupData = super.getData();
        Iterator it = groups.iterator();
        Collection groupsData = new LinkedList();
        while(it.hasNext()) {
            groupsData.add(((GroupElement)it.next()).getElement().getData());
        }
        groupData.setGroups(groupsData);
        return groupData;
    }

    public Set getGroups() {
        return groups;
    }

    public void setGroups(Set groups) {
        this.groups = groups;
    }
}
