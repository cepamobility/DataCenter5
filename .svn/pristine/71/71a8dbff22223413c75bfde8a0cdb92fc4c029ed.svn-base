/**
 * Created on 22-nov-2006
 * CEPA
 * DataCenter 5
 */
package communication.fleet.util;

import java.io.Serializable;

import communication.administration.user.UserSessionData;
import communication.language.Text;
import communication.vehicle.tables.VehicleData;

public class APMMAcumulatedData extends APMMData implements Serializable  {

    /**
     * @param data
     * @param periodFrom
     * @param yearFrom
     * @param periodTo
     * @param yearTo
     * @param messages 
     * @param order4 
     * @param order3 
     * @param order2 
     * @param order1 
     */
    public APMMAcumulatedData(Object[] data, int periodFrom, int yearFrom, int periodTo, int yearTo, int order1, int order2, int order3, int order4, Text messages) {
        super(data, periodFrom, yearFrom, periodTo, yearTo, order1, order2, order3, order4, messages);
    }

    /**
     * @param key
     * @param data
     * @param miles
     * @param accidents
     * @param periodFrom
     * @param yearFrom
     * @param periodTo
     * @param yearTo
     */
    public APMMAcumulatedData(String key, VehicleData data, Integer miles, Integer accidents, int periodFrom, int yearFrom, int periodTo, int yearTo,UserSessionData userSessionData,int order1,int order2,int order3,int order4, Text messages) {
        super(key, data, miles, accidents, periodFrom, yearFrom, periodTo, yearTo, userSessionData, order1, order2, order3, order4, messages);
    }

    public void addData(APMMDataPeriod apmm) {
        for(int i = apmm.getYear().intValue(); i <= yearTo; i++) {
            int startPeriod = (i > apmm.getYear().intValue()) ? 1 : apmm.getPeriod().intValue();
            int endPeriod = (i < yearTo) ? 12 : periodTo;
            for(int j = startPeriod; j <= endPeriod; j++) {
                int index = getDiference(periodFrom,yearFrom,j,i)-1;
                APMMDataPeriod apmmPeriod = new APMMDataPeriod(new Integer(j),new Integer(i),apmm.getMiles(),apmm.getAccidents()); 
                if(periodArray[index] == null) {
                    periodArray[index] = apmmPeriod;
                }else {
                    periodArray[index].addData(apmmPeriod);
                }
            }
        }
    }
}
