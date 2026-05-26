/**
 * Created on 29-jul-2008
 * CEPA
 * DataCenter 5
 */
package communication.util;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Hashtable;

import communication.language.Text;

public class BreadcrumbItem implements Comparable,Serializable  {

    private int action;
    private Hashtable parameters;
    private Calendar date; 
    
    public BreadcrumbItem() {
    }

    public BreadcrumbItem(int action, Hashtable parameters, Calendar date) {
        this.action = action;
        this.parameters = parameters;
        this.date = date;
    }

    public BreadcrumbItem(int action, Calendar date) {
        this.action = action;
        this.parameters = new Hashtable();
        this.date = date;
    }

    public int getAction() {
        return action;
    }

    public Calendar getDate() {
        return date;
    }

    public Hashtable getParameters() {
        return parameters;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public void setParameters(Hashtable parameters) {
        this.parameters = parameters;
    }

    public int compareTo(Object o) {
        return ((BreadcrumbItem)o).date.compareTo(date);
    }
    
    public String getString() {
        return (String)parameters.get("name");
    }

    public String getLink() {
        switch(action) {
            case Breadcrumb.DRIVER_EDIT:
                return "layout.jsp?refer=/driver/DriverView.jsp?id="+String.valueOf(parameters.get("id"));
            case Breadcrumb.EVENT_EDIT:
                return "layout.jsp?refer=/event/EventView.jsp?eventId="+String.valueOf(parameters.get("id"));
            case Breadcrumb.FINE_EDIT:
                return "layout.jsp?refer=/event/FineView.jsp?fine="+String.valueOf(parameters.get("id"));
            case Breadcrumb.VEHICLE_EDIT:
                return "layout.jsp?refer=/vehicle/VehicleView.jsp?id="+String.valueOf(parameters.get("id"));
            case Breadcrumb.ACTIVITY_EDIT:
                return "layout.jsp?refer=/activity/schedule/CEPAActivityScheduleView.jsp?id="+String.valueOf(parameters.get("id"));
        }
        return "";
    }

    public String getType(Text messages) {
        switch(action) {
            case Breadcrumb.DRIVER_EDIT:
                return messages.getText("generic.data.driver");
            case Breadcrumb.EVENT_EDIT:
                return messages.getText("generic.data.event");
            case Breadcrumb.FINE_EDIT:
                return messages.getText("generic.event.fine");
            case Breadcrumb.VEHICLE_EDIT:
                return messages.getText("generic.data.vehicle");
            case Breadcrumb.ACTIVITY_EDIT:
                return messages.getText("web.activity.view");
        }
        return "";
    }

    public String getImage() {
        switch(action) {
            case Breadcrumb.DRIVER_EDIT:
                return "driver_icon.png";
            case Breadcrumb.EVENT_EDIT:
                return "event_icon.png";
            case Breadcrumb.FINE_EDIT:
                return "fine_icon.png";
            case Breadcrumb.VEHICLE_EDIT:
                return "vehicle_icon.png";
            case Breadcrumb.ACTIVITY_EDIT:
                return "activity_icon.png";
        }
        return "";
    }
}
