/**
 * Created on 29-jul-2008
 * CEPA
 * DataCenter 5
 */
package communication.util;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import communication.language.Text;

public class Breadcrumb implements Serializable {

    public static final int DRIVER_EDIT = 1;
    public static final int EVENT_EDIT = 2;
    public static final int FINE_EDIT = 3;
    public static final int VEHICLE_EDIT = 4;
    public static final int ACTIVITY_EDIT = 5;
    
    public Collection log; 
    
    /**
     * Constructor por defecto
     */
    public Breadcrumb() {
        this.log = new LinkedList();
    }

    public void addItem(int action,Hashtable parameters) {
    	boolean found = false;
    	if (log.size() > 0){
	    	Collections.sort((List)log);
	        Iterator it = log.iterator();        
	        while(it.hasNext() && !found) {
	            BreadcrumbItem item = (BreadcrumbItem)it.next();
	            if(item.getAction() == action && item.getParameters().get("id").equals(parameters.get("id"))) {
	                item.setDate(Calendar.getInstance());
	                found = true;
	            }
	        }
    	}
        if(!found) {   
            log.add(new BreadcrumbItem(action,parameters,Calendar.getInstance()));
        }
    }
    
    public String getList(Text messages) {
        String list = "";
        if (log.size() > 0){
	        Collections.sort((List)log);
	        Iterator it = log.iterator();
	        int i = 0; 
	        while(it.hasNext() && i < 7) {
	            BreadcrumbItem item = (BreadcrumbItem)it.next();
	            list += "<td>" +
	                    "<table border=0 cellpadding=0 cellspacing=0>" +
	                    "   <tr class=\"history\">"+
	                    "       <td width='5'><img src='/datacenter/util/imgs/btn_left.png' width='5' height='26' align='bottom' border='0'></td>"+
	                    "       <td align=\"left\"><a href=\""+item.getLink()+"\" border='1'>"+item.getString()+"</a></td>"+
	                    "       <td width='37'><img src='/datacenter/util/imgs/"+item.getImage()+"' width='37' height='26' align='bottom' border='0'></td>" +
	                    "   </tr>" +
	                    "</table>" +
	                    "</td>";
	            i++;
	        }
        }
        return list;
    }

    public Iterator getSortedIterator() {
        Collections.sort((List)log);
        return log.iterator();
    }
}
