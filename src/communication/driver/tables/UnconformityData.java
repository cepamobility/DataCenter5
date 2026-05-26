/**
 * Created on 22-jun-2007
 * CEPA
 * DataCenter 5
 */
package communication.driver.tables;

import java.io.Serializable;
import java.util.Date;

import communication.datatype.tables.UnconformityDataType;

public class UnconformityData implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private Integer id;
    private Date date;
    private UnconformityDataType type;
    private String observations;
    
    public UnconformityData() {
    }

	public UnconformityData(Integer id, Date date, UnconformityDataType type, String observations) {
		this.id = id;
		this.date = date;
		this.type = type;
		this.observations = observations;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public UnconformityDataType getType() {
		return type;
	}

	public void setType(UnconformityDataType type) {
		this.type = type;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

}
