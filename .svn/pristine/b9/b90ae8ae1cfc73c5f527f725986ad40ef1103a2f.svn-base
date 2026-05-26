/*
 * Created on 27-oct-2005
 */
package presentation.translator.web.cenapra;

import java.util.Calendar;
import java.util.Iterator;

import presentation.translator.web.driver.DriverDivOrgForm;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.user.UserSessionData;
import communication.cenapra.tables.ActivityData;
import communication.datatype.tables.ActivityStateDataType;
import communication.datatype.tables.GeneralDTData;
/**
 * @author jrodriguez
 */
public class CENAPRAActivityForm extends DriverDivOrgForm {

    private static final long serialVersionUID = 1L;

    private String id;
    private String city;
    private String instructor;
	private String observations;
	private String date;
	private String month;
    private String year;
    private String comment;
    private String type;
    private String state;

    public CENAPRAActivityForm() {
    }

    public CENAPRAActivityForm(ActivityData data) {
        id = String.valueOf(data.getId());
        city = data.getCity();
        observations = data.getObservations();
        instructor = data.getInstructor();
        state = data.getState().getResourceBundleId();
        type = data.getType().getResourceBundleId();
        Calendar c = Calendar.getInstance();
        c.setTime(data.getDate());
        date = String.valueOf(c.get(Calendar.DATE));
        month = String.valueOf(c.get(Calendar.MONTH)+1);
        year = String.valueOf(c.get(Calendar.YEAR));
        GeoItemData geoItem1 = (GeoItemData)data.getCountry().getItemIterator().next();
        country1 = String.valueOf(geoItem1.getId());
        if(geoItem1.getSubItems() != null) {
            GeoItemData geoItem2 = (GeoItemData)geoItem1.getSubItems().getItemIterator().next();
            country2 = String.valueOf(geoItem2.getId());
            if(geoItem2.getSubItems() != null) {
                GeoItemData geoItem3 = (GeoItemData)geoItem2.getSubItems().getItemIterator().next();
                country3 = String.valueOf(geoItem3.getId());
            }
        }

        Iterator it = data.getDivOrgsIterator();
        int i = 1;
        while(it.hasNext()) {
            DivOrgData divOrgData = (DivOrgData)it.next();
            ItemData item1 = (ItemData)divOrgData.getDivOrgLevels().getItemIterator().next();
            setDivOrg(String.valueOf(item1.getId()),i,1);
            if(item1.getSubItems() != null) {
                ItemData item2 = (ItemData)item1.getSubItems().getItemIterator().next();
                setDivOrg(String.valueOf(item2.getId()),i,2);
                if(item2.getSubItems() != null) {
                    ItemData item3 = (ItemData)item2.getSubItems().getItemIterator().next();
                    setDivOrg(String.valueOf(item3.getId()),i,3);
                }
            }
            i++;
        }
    }

    public String getCity() {
        return city;
    }

    public String getComment() {
        return comment;
    }

    public String getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public String getMonth() {
        return month;
    }

    public String getObservations() {
        return observations;
    }

    public String getYear() {
        return year;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public ActivityData getActivityData(SystemAccess sys) throws Exception{
        Integer activityId = (Util.empty(id)) ? null : new Integer(id);
        return new ActivityData(activityId,Util.getDate(date,month,year),getGeoLabelData(),getDivOrgs(sys.getUserSessionData()),
                new GeneralDTData(type),new ActivityStateDataType(state),instructor,city,observations);
    }

    public String validate(UserSessionData userSessionData) {
        if(Util.empty(type)) {
            return "cenapra.dataerror.type";
        }
        if(Util.empty(instructor)) {
            return "cenapra.dataerror.instructor";
        }
        if(Util.empty(city)) {
            return "cenapra.dataerror.city";
        }
        try{
            int d = Integer.parseInt(date);
            int m= Integer.parseInt(month);
            int y =Integer.parseInt(year);
            boolean checkBirth = Util.checkDate(d,m,y);
            if(checkBirth == false) {
                return "cenapra.dataerror.activitydate";
            }
        }catch(Exception e) {
            return "cenapra.dataerror.activitydate";
        }
        for(int j = 0; j < userSessionData.getFilter().getDivOrgCount(); j++) {
            for(int i = 1; i <= userSessionData.getFilter().getDivOrgLevel(j); i++) {
                if(Util.empty(getDivOrg(j+1,i))) {
                    return "cenapra.dataerror.divorg";
                }
            }
        }
        if(Util.empty(getCountry1())) {
            return "cenapra.dataerror.country";
        }
        return null;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

}
