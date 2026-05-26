/**
 * Created on 28-jun-2007
 * CEPA
 * DataCenter 5
 */
package communication.driver.report;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;

import persistence.administration.corporation.tables.GeoLevel1;
import persistence.driver.tables.AptitudTest;
import persistence.driver.tables.Driver;

public class AptitudTestReportDataType extends DriverReportDataType implements Comparable,Serializable {

    private Date testDate;
    private boolean apt;
    private Date expiry;
	private boolean queryCount = false;

    public AptitudTestReportDataType(Driver driver) {
        id = driver.getId();
        firstname = driver.getFirstName();
        lastname = driver.getLastName();
        country = driver.getCountry().getResourceKey();
        birthdate = driver.getBirthDate();
        exclusiondate = driver.getExclusionDate();
        corporationId = driver.getCorporationId();

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
        if(driver.getAptitudTestSet() != null) {
            it = driver.getAptitudTestSet().iterator();
            while(it.hasNext()) {
                AptitudTest test = (AptitudTest)it.next();
                if(testDate == null || expiry.before(test.getExpiry())) {
                    testDate = test.getPk().getDate();
                    apt = test.isApt();
                    expiry = test.getExpiry();
                }
            }
        }
    }

    public AptitudTestReportDataType(AptitudTest test) {
    	queryCount = true;
    	this.driver = test.getPk().getDriver();
        id = driver.getId();
        firstname = driver.getFirstName();
        lastname = driver.getLastName();
        country = driver.getCountry().getResourceKey();
        birthdate = driver.getBirthDate();
        exclusiondate = driver.getExclusionDate();
        corporationId = driver.getCorporationId();

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
        testDate = test.getPk().getDate();
        apt = test.isApt();
        expiry = test.getExpiry();
	}

	public boolean isApt() {
        return apt;
    }

    public Date getExpiry() {
        return expiry;
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setApt(boolean apt) {
        this.apt = apt;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public int compareTo(Object arg0) {
    	if(queryCount ) {
    		return testDate.compareTo(((AptitudTestReportDataType)arg0).testDate);
    	}else {
    		return super.compareTo(arg0);
    	}
    }
    
}
