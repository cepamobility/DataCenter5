/**
 * Created on 10-nov-2006
 * CEPA
 * DataCenter 5
 */
package communication.fleet.util;

import java.io.Serializable;



public class EventInjuriesData extends DivisionData implements Serializable  {

   protected Integer injuriesCount;

    public EventInjuriesData(Object[] data, boolean accident) {
       
        injuriesCount = (data[0] == null) ? new Integer(0) : new Integer(((Long)data[0]).intValue());
        if (data.length>=3)
        	divOrg1Id = (Integer) data[1];
        if (data.length>=5)
        	divOrg2Id = (Integer) data[3];
        if (data.length>=7)
        	divOrg3Id = (Integer) data[5];
        if (data.length>=9)
        	divOrg4Id = (Integer) data[7];
    }

    public Integer getInjuriesCount() {
        return injuriesCount;
    }

    
    public void setInjuriesCount(Integer injuriesCount) {
        this.injuriesCount = injuriesCount;
    }
    
}
