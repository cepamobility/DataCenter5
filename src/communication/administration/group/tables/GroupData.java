/**
 * Created on 29-nov-2006
 * CEPA
 * DataCenter 5
 */
package communication.administration.group.tables;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class GroupData extends GroupMainAttributes implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Collection lines;
    private Collection groups;
    
    public GroupData() {
        super();
    }

    public GroupData(Integer id, String name, String description,
            boolean active, Integer period, Integer type) {
        super(id, name, description, active, period, type);
    }

    public void setLines(Collection linesData) {
        lines = linesData;
    }

    public Collection getLines() {
        return lines;
    }
    
    public Iterator getLinesIterator() {
        if(lines == null) {
            return new LinkedList().iterator();
        }
        Collections.sort((List) lines);
        return lines.iterator();
    }

    public void setGroups(Collection groups) {
        this.groups = groups;
    }

    public Collection getGroups() {
        return groups;
    }

    public Collection getAllLines(Collection procesedLines) {
        if(type.equals(GroupMainAttributes.INDIVIDUAL)) {
            return lines;
        }else {
            Collection allLines = new LinkedList();
            if(groups!=null){
	            Iterator it = groups.iterator();
	            while(it.hasNext()) {
	            	GroupData groupData = (GroupData)it.next(); 
	            	if(procesedLines.contains(groupData.getId())) {
	            		System.out.println("ya lo tiene");
	            	}else {
	            		procesedLines.add(groupData.getId());
	        	    	allLines.addAll(groupData.getAllLines(procesedLines));
	            	}
	            }
            }
            return allLines;
        }
    }

    public Collection getAllLines() {
        if(type.equals(GroupMainAttributes.INDIVIDUAL)) {
            return lines;
        }else {
            Collection allLines = new LinkedList();
            if(groups!=null){
	            Iterator it = groups.iterator();
	            while(it.hasNext()) {
	            	GroupData groupData = (GroupData)it.next(); 
        	    	allLines.addAll(groupData.getAllLines());
	            }
            }
            return allLines;
        }
    }
}
