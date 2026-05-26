package persistence.administration.corporation.tables;

import java.util.Date;

 

public class FOSAPointsEndMonth {

	private Integer id;
	private Date dateExecute;
	private Integer month;
	private Integer year;
	private Integer contractorId;
	private String contractorName;
	private Integer sumLessPoints;
	private Integer sumTotalPoints;//totalContractVehiclesX100
	private Integer maxLessPointsWithNoSuspended;
	private Integer totalContractVehicles;
	private Integer totalSuspendedVehicles;
	private Integer totalMinimumSuspendedVehicles;//Si totalSuspendedVehicles<totalMinimumSuspendedVehicles->Infraccion
	
	public Integer getMaxLessPointsWithNoSuspended() {
		return maxLessPointsWithNoSuspended;
	}
	public void setMaxLessPointsWithNoSuspended(Integer maxLessPointsWithNoSuspended) {
		this.maxLessPointsWithNoSuspended = maxLessPointsWithNoSuspended;
	}
	public Integer getContractorId() {
		return contractorId;
	}
	public void setContractorId(Integer contractorId) {
		this.contractorId = contractorId;
	}
	public Integer getSumLessPoints() {
		return sumLessPoints;
	}
	public void setSumLessPoints(Integer sumLessPoints) {
		this.sumLessPoints = sumLessPoints;
	}
	public Integer getSumTotalPoints() {
		return sumTotalPoints;
	}
	public void setSumTotalPoints(Integer sumTotalPoints) {
		this.sumTotalPoints = sumTotalPoints;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDateExecute() {
		return dateExecute;
	}
	public void setDateExecute(Date dateExecute) {
		this.dateExecute = dateExecute;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getContractorName() {
		return contractorName;
	}
	public void setContractorName(String contractorName) {
		this.contractorName = contractorName;
	}
	 
	public Integer getTotalContractVehicles() {
		return totalContractVehicles;
	}
	public void setTotalContractVehicles(Integer totalContractVehicles) {
		this.totalContractVehicles = totalContractVehicles;
	}
	public Integer getTotalSuspendedVehicles() {
		return totalSuspendedVehicles;
	}
	public void setTotalSuspendedVehicles(Integer totalSuspendedVehicles) {
		this.totalSuspendedVehicles = totalSuspendedVehicles;
	}
	public Integer getTotalMinimumSuspendedVehicles() {
		return totalMinimumSuspendedVehicles;
	}
	public void setTotalMinimumSuspendedVehicles(
			Integer totalMinimumSuspendedVehicles) {
		this.totalMinimumSuspendedVehicles = totalMinimumSuspendedVehicles;
	}
	
	 

}
