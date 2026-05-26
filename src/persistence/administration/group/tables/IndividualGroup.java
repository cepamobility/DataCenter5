/**
 * Created on 28-nov-2006
 * CEPA
 * DataCenter 5
 */
package persistence.administration.group.tables;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import org.hibernate.Session;

import communication.administration.group.tables.GroupData;
import communication.administration.group.tables.GroupMainAttributes;

public class IndividualGroup extends AbstractGroup {

    private Set lines;
    
    public IndividualGroup() {
        super();
    }

    public IndividualGroup(GroupMainAttributes groupData, Session session, Integer corporationId) {
        super(groupData,session,corporationId);
    }

    public GroupData getData() {
        GroupData groupData = super.getData();
        Iterator it = lines.iterator();
        Collection linesData = new LinkedList();
        while(it.hasNext()) {
            linesData.add(((GroupLine)it.next()).getData());
        }
        groupData.setLines(linesData);
        return groupData;
    }

    public Set getLines() {
        if(lines == null) {
            lines = new HashSet();
        }
        return lines;
    }

    public void setLines(Set lines) {
        this.lines = lines;
    }
}
