/**
 * Created on 04-ago-2008
 * CEPA
 * DataCenter 5
 */
package communication.administration.bookmark.tables;

import java.io.Serializable;
import java.util.Hashtable;

public class BookmarkData implements Serializable {

    public static final int DRIVER_REPORT = 1;
    public static final int DRIVER_LICENSE_EXPIRY = 2;
    public static final int HRD_REPORT = 3;
    public static final int PHRD_REPORT = 4;
    public static final int HRD_MONSANTO_REPORT = 5;
    public static final int HISTORY_REPORT = 6;
    public static final int EVENT_REPORT = 7;
    public static final int EVENT_TYPE_REPORT = 8;
    public static final int EVENT_BYPERIOD_REPORT = 9;
    public static final int EVENT_COUNT_REPORT = 10;
    public static final int VEHICLE_REPORT = 11;
    public static final int KM_BYDIVISION_REPORT = 12;
    public static final int ODOMETER_LOG_REPORT = 13;
    public static final int CONSUMED_FUEL_REPORT = 14;
    public static final int KM_LTR_CONSUMED_REPORT = 15;
    public static final int MILEAGE_REPORT = 16;
    public static final int SUCTA_MTOP_REPORT = 17;
    public static final int CHECK_LIST_REPORT = 18;
    public static final int TRAILERS_WO_CHECKLIST_REPORT = 19;
    public static final int VEHICLE_PURSUIT_REPORT = 20;
    public static final int VEHICLE_WO_PURSUIT_REPORT = 21;
    public static final int ACTIVITY_GENERAL_REPORT = 22;
    public static final int ACTIVITY_DELIVERED_REPORT = 23;
    public static final int ACTIVITY_EXPIRY_REPORT = 24;
    public static final int DRIVERS_WO_BTW_REPORT = 25;
    public static final int DRIVER_WO_EC = 26;
    public static final int DRIVERS_BTW_ASSISTANCE_REPORT = 27;
    public static final int COMMENTARY_DRIVE_LOG_REPORT = 28;
    public static final int COMMENTARY_DRIVE_REPORT = 29;
    public static final int COMMENTARY_DRIVE_REPORT_2 = 30;
    public static final int APMM_BYDIVISION_REPORT = 31;
    public static final int APMM_BYPERIOD_REPORT = 32;
    public static final int FOSA_DRIVER_REPORT = 33;
    public static final int KM_BYTRADE_REPORT = 34;
	public static final int MISSING_ODOMETER_REPORT = 35;
	public static final int UNCONFORMITIES_REPORT = 36;
    
    private Integer id;
    private String name;
    private boolean start;
    private int action;
    private int tol;
    private Hashtable parameters;

    public BookmarkData() {
    }

    /**
     * @param action
     */
    public BookmarkData(int action) {
        this.action = action;
        tol = 1;
        parameters = new Hashtable();
    }


    /**
     * @param id
     * @param name 
     * @param action
     * @param parameters
     */
    public BookmarkData(Integer id, String name, boolean start, int action, Hashtable parameters) {
        this.id = id;
        this.name = name;
        this.action = action;
        this.start = start;
        this.parameters = parameters;
    }

    public int getAction() {
        return action;
    }

    public Integer getId() {
        return id;
    }

    public Hashtable getParameters() {
        return parameters;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setParameters(Hashtable parameters) {
        this.parameters = parameters;
    }

    public int getTol() {
        return tol;
    }

    public void setTol(int tol) {
        this.tol = tol;
    }

    public void addParameter(String key, String value) {
        parameters.put(key,value);
    }

    public String getActionName() {
        switch(action) {
            case DRIVER_REPORT:
                return "driver.query.byevent";
            case DRIVER_LICENSE_EXPIRY:
                return "driver.query.licenseexpiry";
            case HRD_REPORT:
                return "driver.query.hrd";
            case PHRD_REPORT:
                return "driver.query.phrd";
            case HRD_MONSANTO_REPORT:
                return "driver.query.hrdmonsanto";
            case HISTORY_REPORT:
                return "driver.query.history";
            case EVENT_REPORT:
                return "event.query.byevent";
            case EVENT_TYPE_REPORT:
                return "event.query.bytype";
            case EVENT_BYPERIOD_REPORT:
                return "event.query.byperiod";
            case EVENT_COUNT_REPORT:
                return "event.query.count";
            case VEHICLE_REPORT:
                return "fleet.query.vehicles";
            case KM_BYDIVISION_REPORT:
                return "fleet.query.apmm.division";
            case ODOMETER_LOG_REPORT:
                return "fleet.query.odometerlog";
            case CONSUMED_FUEL_REPORT:
                return "fleet.query.consumedfuel.menu";
            case KM_LTR_CONSUMED_REPORT:
                return "fleet.query.kmltr.menu";
            case MILEAGE_REPORT:
                return "fleet.query.mileage";
            case SUCTA_MTOP_REPORT:
                return "generic.messages.suctamtopquery.title";
            case CHECK_LIST_REPORT:
                return "vehicle.checklist.query";
            case TRAILERS_WO_CHECKLIST_REPORT:
                return "vehicle.trailer.without.checklist";
            case VEHICLE_PURSUIT_REPORT:
                return "vehicle.seguimientoenruta.query";
            case VEHICLE_WO_PURSUIT_REPORT:
                return "vehicle.seguimientoenruta.query.notpursuit";
            case ACTIVITY_GENERAL_REPORT:
                return "generic.activity.general";
            case ACTIVITY_DELIVERED_REPORT:
                return "activity.query.activitydelivered";
            case ACTIVITY_EXPIRY_REPORT:
                return "activity.query.activityexpiry";
            case DRIVERS_WO_BTW_REPORT:
                return "activity.query.driverswithoutbtw";
            case DRIVER_WO_EC:
                return "query.activity.ecquery.menu";
            case DRIVERS_BTW_ASSISTANCE_REPORT:
                return "activity.query.driversbtwassist";
            case COMMENTARY_DRIVE_LOG_REPORT:
                return "fleet.query.odometerlog";
            case COMMENTARY_DRIVE_REPORT:
                return "generic.activity.form.commentarydrive.commentarydrive";
            case COMMENTARY_DRIVE_REPORT_2:
                return "activity.query.resultcommentarydrive";
            case APMM_BYDIVISION_REPORT:
                return "queries.apmm.division";
            case APMM_BYPERIOD_REPORT:
                return "queries.apmm.period";
            case FOSA_DRIVER_REPORT:
                return "driver.query.byevent";
            case MISSING_ODOMETER_REPORT:
                return "query.missingodometer.title";
        }
        return "";
    }

    public String getName() {
        return name;
    }

    public boolean isStart() {
        return start;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public void incrementTol() {
        tol++;
    }
}
