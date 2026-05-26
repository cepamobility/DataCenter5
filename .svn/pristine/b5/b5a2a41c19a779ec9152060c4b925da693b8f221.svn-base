/**
 * Created on 22-jun-2007
 * CEPA
 * DataCenter 5
 */
package communication.driver.tables;

import java.io.Serializable;
import java.util.Date;

public class AlcoholControlData implements Serializable, Comparable<AlcoholControlData>  {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date date;
    private Integer result;
    
    public AlcoholControlData() {
    }

    public AlcoholControlData(Date date,Integer result) {
        this.date = date;
        this.result = result;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

	public int compareTo(AlcoholControlData o) {
		return date.compareTo(o.date);
	}
}
