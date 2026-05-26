/**
 * Created on 28-jun-2007
 * CEPA
 * DataCenter 5
 */
package communication.driver.report;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import persistence.administration.corporation.tables.GeoLevel1;
import persistence.driver.tables.AlcoholControl;
import persistence.driver.tables.Driver;

public class AlcoholControlReportDataType extends DriverReportDataType implements Serializable {

    public static final int EXPIRED = 1;
    public static final int NOT_EXPIRED = 2;
    public static final int NEVER = 3;
    
    private Date lastControl;
    private Integer result;
    private int status;

    public AlcoholControlReportDataType(Driver driver) {
        id = driver.getId();
        firstname = driver.getFirstName();
        lastname = driver.getLastName();
        country = driver.getCountry().getResourceKey();
        birthdate = driver.getBirthDate();
        exclusiondate = driver.getExclusionDate();

        if(driver.getDivOrg1() != null) {
            if(driver.getDivOrg1().getDivOrgItemLevel2() != null) {
                if(driver.getDivOrg1().getDivOrgItemLevel2().getDivOrgItemLevel3() != null) {
                    divorg11 = driver.getDivOrg1().getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();                                    
                    divorg12 = driver.getDivOrg1().getDivOrgItemLevel2().getResourceKey();                
                    divorg13 = driver.getDivOrg1().getResourceKey();
                }else {
                    divorg11 = driver.getDivOrg1().getDivOrgItemLevel2().getResourceKey();                
                    divorg12 = driver.getDivOrg1().getResourceKey();
                }
            }else {
                divorg11 = driver.getDivOrg1().getResourceKey();
            }
        }
        if(driver.getDivOrg2() != null) {
            if(driver.getDivOrg2().getDivOrgItemLevel2() != null) {
                if(driver.getDivOrg2().getDivOrgItemLevel2().getDivOrgItemLevel3() != null) {
                    divorg21 = driver.getDivOrg2().getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();                                    
                    divorg22 = driver.getDivOrg2().getDivOrgItemLevel2().getResourceKey();                
                    divorg23 = driver.getDivOrg2().getResourceKey();
                }else {
                    divorg21 = driver.getDivOrg2().getDivOrgItemLevel2().getResourceKey();                
                    divorg22 = driver.getDivOrg2().getResourceKey();
                }
            }else {
                divorg21 = driver.getDivOrg2().getResourceKey();
            }
        }
        if(driver.getDivOrg3() != null) {
            if(driver.getDivOrg3().getDivOrgItemLevel2() != null) {
                if(driver.getDivOrg3().getDivOrgItemLevel2().getDivOrgItemLevel3() != null) {
                    divorg31 = driver.getDivOrg3().getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();                                    
                    divorg32 = driver.getDivOrg3().getDivOrgItemLevel2().getResourceKey();                
                    divorg33 = driver.getDivOrg3().getResourceKey();
                }else {
                    divorg31 = driver.getDivOrg3().getDivOrgItemLevel2().getResourceKey();                
                    divorg32 = driver.getDivOrg3().getResourceKey();
                }
            }else {
                divorg31 = driver.getDivOrg3().getResourceKey();
            }
        }
        divgeo1 = driver.getCorpCountry().getPk().getCountry().getResourceKey();
        Iterator it = driver.getCorpCountry().getGeoLevel1Set().iterator();
        if(it.hasNext()){
            GeoLevel1 level1 = (GeoLevel1)it.next();
            divgeo2 = level1.getResourceKey();
            if(level1.getGeoLevel2() != null) {
                divgeo3 = level1.getGeoLevel2().getResourceKey();
            }
        }
        contract = (driver.getContract() == null) ? "" : driver.getContract().getResourceBundleId();
        if(driver.getAlcoholControlSet() != null && !driver.getAlcoholControlSet().isEmpty()) {
            it = driver.getAlcoholControlSet().iterator();
            Calendar c = Calendar.getInstance();
            c.add(Calendar.YEAR,-1);
            Date yearAgo = c.getTime();
            while(it.hasNext()) {
                AlcoholControl control = (AlcoholControl)it.next();
                if(lastControl == null || lastControl.before(control.getPk().getDate())) {
                    lastControl = control.getPk().getDate();
                    result = control.getResult();
                }
            }
            if(lastControl.before(yearAgo)) {
                status = EXPIRED;
            }else {
                status = NOT_EXPIRED;
            }
        }else {
            status = NEVER;
        }
    }

    public Date getLastControl() {
        return lastControl;
    }

    public Integer getResult() {
        return result;
    }

    public int getStatus() {
        return status;
    }

    public void setLastControl(Date lastControl) {
        this.lastControl = lastControl;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
