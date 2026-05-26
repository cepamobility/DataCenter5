/**
 * Created on 10-nov-2006
 * CEPA
 * DataCenter 5
 */
package communication.fleet.util;

import java.io.Serializable;
import java.util.StringTokenizer;

import persistence.util.Util;

import communication.administration.user.UserSessionData;
import communication.fleet.tables.FleetData;
import communication.language.Text;
import communication.vehicle.tables.VehicleData;


public class APMMData extends DivisionData implements Comparable, Serializable {

    protected String divOrg1Name;
    protected String divOrg2Name;
    protected String divOrg3Name;
    protected String divOrg4Name;
    
    protected APMMDataPeriod[] periodArray;
    protected int periodFrom;
    protected int yearFrom;
    protected int periodTo;
    protected int yearTo;
    
    public APMMData() {
    }

    public APMMData(Object[] data,int periodFrom,int yearFrom,int periodTo,int yearTo, int order1, int order2, int order3 ,int order4 ,Text messages) {
        if(data.length > 4) {
            divOrg1Id = (Integer) data[4];
            divOrg1Name = (order1 == 3) ? messages.getText((String) data[5]) : (String) data[5];
        }
        if(data.length > 6) {
            divOrg2Id = (Integer) data[6];
            divOrg2Name = (order2 == 3) ? messages.getText((String) data[7]) : (String) data[7];
        }
        if(data.length > 8) {
            divOrg3Id = (Integer) data[8];
            divOrg3Name = (order3 == 3) ? messages.getText((String) data[9]) : (String) data[9];
        }
        if(data.length > 10) {
            divOrg4Id = (Integer) data[10];
            divOrg4Name = (order4 == 3) ? messages.getText((String) data[11]) : (String) data[11];
        }

        this.periodFrom = periodFrom;
        this.yearFrom = yearFrom;
        this.periodTo = periodTo;
        this.yearTo = yearTo;
        periodArray = new APMMDataPeriod[getDiference(periodFrom,yearFrom,periodTo,yearTo)];
        
        APMMDataPeriod period = new APMMDataPeriod(data);
        addData(period);
    }

    public APMMData(String key, VehicleData data, Integer miles, Integer accidents,int periodFrom,int yearFrom,int periodTo,int yearTo,UserSessionData userSessionData, int order1, int order2, int order3, int order4, Text messages) {
        int[][] principal = userSessionData.getFilter().getPrincipalDivOrgs();
        
        if(order1 != 4) {
            if(order1 == 3) {
                divOrg1Id = data.getCountryId();
                divOrg1Name = messages.getText(data.getCountryName());
            }else {
                divOrg1Id = data.getIdData(principal[order1][0],principal[order1][1]);
                divOrg1Name = data.getData(principal[order1][0],principal[order1][1]);
            }
        }

        if(order2 != 4) {
            if(order2 == 3) {
                divOrg2Id = data.getCountryId();
                divOrg2Name = messages.getText(data.getCountryName());
            }else {
                divOrg2Id = data.getIdData(principal[order2][0],principal[order2][1]);
                divOrg2Name = data.getData(principal[order2][0],principal[order2][1]);
            }
        }
    
        if(order3 != 4) {
            if(order3 == 3) {
                divOrg3Id = data.getCountryId();
                divOrg3Name = messages.getText(data.getCountryName());
            }else {
                divOrg3Id = data.getIdData(principal[order3][0],principal[order3][1]);
                divOrg3Name = data.getData(principal[order3][0],principal[order3][1]);
            }
        }

        if(order4 != 4) {
            if(order4 == 3) {
                divOrg4Id = data.getCountryId();
                divOrg4Name = messages.getText(data.getCountryName());
            }else {
                divOrg4Id = data.getIdData(principal[order4][0],principal[order4][1]);
                divOrg4Name = data.getData(principal[order4][0],principal[order4][1]);
            }
        }

        this.periodFrom = periodFrom;
        this.yearFrom = yearFrom;
        this.periodTo = periodTo;
        this.yearTo = yearTo;
        periodArray = new APMMDataPeriod[getDiference(periodFrom,yearFrom,periodTo,yearTo)];

        StringTokenizer token = new StringTokenizer(key,"/");
        APMMDataPeriod period = new APMMDataPeriod(new Integer(token.nextToken()),new Integer(token.nextToken()),miles,accidents);
        addData(period);
    }

    protected int getDiference(int pFrom, int yFrom, int pTo, int yTo) {
        return (12 - pFrom + 1) + ((yTo - yFrom - 1) * 12) + pTo;
    }

    public String getDivOrg1Name() {
        return divOrg1Name;
    }

    public String getDivOrg2Name() {
        return divOrg2Name;
    }

    public String getDivOrg3Name() {
        return divOrg3Name;
    }

    public void setDivOrg1Name(String divOrg1Name) {
        this.divOrg1Name = divOrg1Name;
    }

    public void setDivOrg2Name(String divOrg2Name) {
        this.divOrg2Name = divOrg2Name;
    }

    public void setDivOrg3Name(String divOrg3Name) {
        this.divOrg3Name = divOrg3Name;
    }

    public int compareTo(Object arg0) {
        APMMData apmm = (APMMData)arg0;
        if(!divOrg1Name.equals(apmm.divOrg1Name)) {
            return divOrg1Name.compareTo(apmm.divOrg1Name);
        }else{
            if(!divOrg1Id.equals(apmm.divOrg1Id)) {
                return divOrg1Id.compareTo(apmm.divOrg1Id);
            }
        }
        if(!divOrg2Name.equals(apmm.divOrg2Name)) {
            return divOrg2Name.compareTo(apmm.divOrg2Name);
        }else{
            if(!divOrg2Id.equals(apmm.divOrg2Id)) {
                return divOrg2Id.compareTo(apmm.divOrg2Id);
            }
        }
        if(!divOrg3Name.equals(apmm.divOrg3Name)) {
            return divOrg3Name.compareTo(apmm.divOrg3Name);
        }else{
            if(!divOrg3Id.equals(apmm.divOrg3Id)) {
                return divOrg3Id.compareTo(apmm.divOrg3Id);
            }
        }
        if(!divOrg4Name.equals(apmm.divOrg4Name)) {
            return divOrg4Name.compareTo(apmm.divOrg4Name);
        }else{
            if(!divOrg4Id.equals(apmm.divOrg4Id)) {
                return divOrg4Id.compareTo(apmm.divOrg4Id);
            }
        }
        return 0;
    }


    public Integer getPeriod(int i) {
        return periodArray[i].getPeriod();
    }

    public Integer getYear(int i) {
        return periodArray[i].getYear();
    }

    public Double getAPMM(int i) {
        if(periodArray[i] == null || periodArray[i].getMiles().intValue() == 0) {
            return new Double(0);
        }
        return new Double(periodArray[i].getAccidents().doubleValue() * 1000000 / periodArray[i].getMiles().doubleValue());        
    }

    public Double getAPMK(int i) {
        if(periodArray[i] == null || periodArray[i].getMiles().intValue() == 0) {
            return new Double(0);
        }
        return new Double(periodArray[i].getAccidents().doubleValue() * 1000000 / (periodArray[i].getMiles().doubleValue() * Util.KMS_TO_MILES));        
    }

    public Double getAccidents(int i) {
        if(periodArray[i] == null) {
            return new Double(0);
        }
        return new Double(periodArray[i].getAccidents().intValue());        
    }

    public Double getMiles(int i) {
        if(periodArray[i] == null) {
            return new Double(0);
        }
        return new Double(periodArray[i].getMiles().intValue());        
    }

    public Double getKms(int i) {
        if(periodArray[i] == null) {
            return new Double(0);
        }
        return new Double(periodArray[i].getMiles().doubleValue() * Util.KMS_TO_MILES);        
    }

    public void addData(APMMDataPeriod apmm) {
        int index = getDiference(periodFrom,yearFrom,apmm.getPeriod().intValue(),apmm.getYear().intValue())-1;
        if(periodArray[index] == null) {
            periodArray[index] = apmm;
        }else {
            periodArray[index].addData(apmm);
        }
    }

    public APMMDataPeriod[] getPeriodArray() {
        return periodArray;
    }

    public int getPeriodFrom() {
        return periodFrom;
    }

    public int getPeriodTo() {
        return periodTo;
    }

    public int getYearFrom() {
        return yearFrom;
    }

    public int getYearTo() {
        return yearTo;
    }

    public void setPeriodFrom(int periodFrom) {
        this.periodFrom = periodFrom;
    }

    public void setPeriodTo(int periodTo) {
        this.periodTo = periodTo;
    }

    public void setYearFrom(int yearFrom) {
        this.yearFrom = yearFrom;
    }

    public void setYearTo(int yearTo) {
        this.yearTo = yearTo;
    }

    public String getDivOrg4Name() {
        return divOrg4Name;
    }
    
    
}
