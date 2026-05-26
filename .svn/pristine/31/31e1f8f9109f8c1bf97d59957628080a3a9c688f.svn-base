package persistence.report.tables;

import java.io.Serializable;

import org.hibernate.Query;
import org.hibernate.Session;

import communication.report.JJOGSumTotalData;

/**
 * @author fcarriquiry
 */
public class JJOGSumTotal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;

	private String lineLabel;
	
	private Integer order;
	
	private Integer averageVehicles;
	
	private Integer drivers;
	
	private Double averageMiles; 
	
	private Double totalMiles; 
	
	private Double salesMiles;
	
	private Double nonSalesMiles;
	
	private Integer totalCrashes;
	
	private Integer salesCrashes;
	
	private Integer nonSalesCrashes;
	
	private Double  cpmm;
	
	private Double  salesCpmm;
	
	private Double  nonSalesCpmm;
	
	private Double  annualizedProjection;
	
	private Integer totalIncidents;
	
	private Integer injured;
	
	private Integer salesInjured;
	
	private Integer nonSalesInjured;
	
	private Double  ipmm;
	
	private Double  salesIpmm;
	
	private Double  nonSalesIpmm;
	
	private JJSnapshot snapshot;
	
	 
    public JJOGSumTotal() {
		
	}
    
    
	public Double getTotalMiles() {
		return totalMiles;
	}


	public void setTotalMiles(Double totalMiles) {
		this.totalMiles = totalMiles;
	}


	public Double getSalesMiles() {
		return salesMiles;
	}


	public void setSalesMiles(Double salesMiles) {
		this.salesMiles = salesMiles;
	}


	public Double getNonSalesMiles() {
		return nonSalesMiles;
	}


	public void setNonSalesMiles(Double nonSalesMiles) {
		this.nonSalesMiles = nonSalesMiles;
	}


	public Integer getSalesCrashes() {
		return salesCrashes;
	}


	public void setSalesCrashes(Integer salesCrashes) {
		this.salesCrashes = salesCrashes;
	}


	public Integer getNonSalesCrashes() {
		return nonSalesCrashes;
	}


	public void setNonSalesCrashes(Integer nonSalesCrashes) {
		this.nonSalesCrashes = nonSalesCrashes;
	}


	public Double getSalesCpmm() {
		return salesCpmm;
	}


	public void setSalesCpmm(Double salesCpmm) {
		this.salesCpmm = salesCpmm;
	}


	public Double getNonSalesCpmm() {
		return nonSalesCpmm;
	}


	public void setNonSalesCpmm(Double nonSalesCpmm) {
		this.nonSalesCpmm = nonSalesCpmm;
	}


	public Integer getSalesInjured() {
		return salesInjured;
	}


	public void setSalesInjured(Integer salesInjured) {
		this.salesInjured = salesInjured;
	}


	public Integer getNonSalesInjured() {
		return nonSalesInjured;
	}


	public void setNonSalesInjured(Integer nonSalesInjured) {
		this.nonSalesInjured = nonSalesInjured;
	}


	public Double getSalesIpmm() {
		return salesIpmm;
	}


	public void setSalesIpmm(Double salesIpmm) {
		this.salesIpmm = salesIpmm;
	}


	public Double getNonSalesIpmm() {
		return nonSalesIpmm;
	}


	public void setNonSalesIpmm(Double nonSalesIpmm) {
		this.nonSalesIpmm = nonSalesIpmm;
	}


	public JJSnapshot getSnapshot() {
		return snapshot;
	}

	public void setSnapshot(JJSnapshot snapshot) {
		this.snapshot = snapshot;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLineLabel() {
		return lineLabel;
	}

	public void setLineLabel(String lineLabel) {
		this.lineLabel = lineLabel;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getAverageVehicles() {
		return averageVehicles;
	}

	public void setAverageVehicles(Integer averageVehicles) {
		this.averageVehicles = averageVehicles;
	}

	public Integer getDrivers() {
		return drivers;
	}

	public void setDrivers(Integer drivers) {
		this.drivers = drivers;
	}

	public Double getAverageMiles() {
		return averageMiles;
	}

	public void setAverageMiles(Double averageMiles) {
		this.averageMiles = averageMiles;
	}

	public Integer getTotalCrashes() {
		return totalCrashes;
	}

	public void setTotalCrashes(Integer totalCrashes) {
		this.totalCrashes = totalCrashes;
	}

	public Double getCpmm() {
		return cpmm;
	}

	public void setCpmm(Double cpmm) {
		this.cpmm = cpmm;
	}

	 
	public Double getAnnualizedProjection() {
		return annualizedProjection;
	}

	public void setAnnualizedProjection(Double annualizedProjection) {
		this.annualizedProjection = annualizedProjection;
	}

	public Integer getTotalIncidents() {
		return totalIncidents;
	}

	public void setTotalIncidents(Integer totalIncidents) {
		this.totalIncidents = totalIncidents;
	}

	public Integer getInjured() {
		return injured;
	}

	public void setInjured(Integer injured) {
		this.injured = injured;
	}

	public Double getIpmm() {
		return ipmm;
	}

	public void setIpmm(Double ipmm) {
		this.ipmm = ipmm;
	}

    public JJOGSumTotalData getData(double cpmmGoal, double ipmmGoal,Session session){
    	JJOGSumTotalData data=new JJOGSumTotalData();
    	data.setAnnualizedProjection(this.annualizedProjection);
    	data.setAverageMiles(this.averageMiles);
    	data.setAverageVehicles(this.averageVehicles);
    	data.setCpmm(this.cpmm);
    	data.setDrivers(this.drivers);
    	data.setId(this.id);
    	data.setInjured(this.injured);
    	data.setIpmm(this.ipmm);
    	data.setLineLabel(this.lineLabel);
    	data.setNonSalesCpmm(this.nonSalesCpmm);
    	data.setNonSalesCrashes(this.nonSalesCrashes);
    	data.setNonSalesInjured(this.nonSalesInjured);
    	data.setNonSalesIpmm(this.nonSalesIpmm);
    	data.setNonSalesMiles(this.nonSalesMiles);
    	data.setOrder(this.order);
    	data.setSalesCpmm(this.salesCpmm);
    	data.setSalesCrashes(this.salesCrashes);
    	data.setSalesInjured(this.salesInjured);
    	data.setSalesIpmm(this.salesIpmm);
    	data.setSalesMiles(this.salesMiles);
    	data.setTotalCrashes(this.totalCrashes);
    	data.setTotalIncidents(this.totalIncidents);
    	data.setTotalMiles(this.totalMiles);
    	data.setCpmmGoal(cpmmGoal);
    	data.setIpmmGoal(ipmmGoal);
    	
    	Query query=session.createSQLQuery("select m.text from generalmessages as m where m.labelkey='generic.month."+snapshot.getMonth()+"' and m.language='en' ");
    	Object oj=query.list().iterator().next();
    	data.setMonth((String)oj);
    	return data;
    }
	 
}
