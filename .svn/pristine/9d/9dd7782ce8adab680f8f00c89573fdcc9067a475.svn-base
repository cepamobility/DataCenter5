package persistence.report.tables;

import java.io.Serializable;

import communication.report.JJGoalsData;

/**
 * @author fcarriquiry
 */
public class JJGoals implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;

	private Integer year;
	
	private Double cpmm;
	
	private Double ipmm;
	
	private Double cpmmTop;
	
	private Double ipmmTop;
	
    public Double getCpmmTop() {
		return cpmmTop;
	}

	public void setCpmmTop(Double cpmmTop) {
		this.cpmmTop = cpmmTop;
	}

	public Double getIpmmTop() {
		return ipmmTop;
	}

	public void setIpmmTop(Double ipmmTop) {
		this.ipmmTop = ipmmTop;
	}

	public Double getCpmm() {
		return cpmm;
	}

	public void setCpmm(Double cpmm) {
		this.cpmm = cpmm;
	}

	public Double getIpmm() {
		return ipmm;
	}

	public void setIpmm(Double ipmm) {
		this.ipmm = ipmm;
	}

	public JJGoals() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
     
	public JJGoalsData getData(){
		JJGoalsData data=new JJGoalsData();
		data.setCpmm(cpmm);
		data.setId(id);
		data.setYear(year);
		data.setIpmm(ipmm);
		data.setIpmmTop(ipmmTop);
		data.setCpmmTop(cpmmTop);
		
		return data;
	}

	 
}
