/*
 * Created on 27-oct-2005
 */
package persistence.log.vehicle;

import java.util.Date;

import persistence.vehicle.tables.VehicleOptionalInfo;

public class VehicleOptionalInfoLog {

    private VehicleOptionalInfoLogPK pk;
    
    private Integer action;   
    private String comment;    
    
	private String prefix;
	private Integer totalGrossWeight;
	private Integer totalCombinedGrossWeight;
	private String monitoringSystem;
	private String tachograph;
	private String renavan;
	private Integer limitLoad;
	private boolean asr;
	private Integer currentKm ;
	private Date dateCurrentKm;
	private Date anttExpiry;
	private Date citvExpiry;
        
    
    

	public VehicleOptionalInfoLog() {
		
	}
	
	public VehicleOptionalInfoLog(VehicleOptionalInfo optionalInfo,String user,
			Date date,int action,String comment) {
		pk = new VehicleOptionalInfoLogPK(optionalInfo.getId(),user,date);
		this.action = Integer.valueOf(action);
        this.comment = comment;		
       
    	prefix = optionalInfo.getPrefix();
    	totalGrossWeight = optionalInfo.getTotalGrossWeight();
    	totalCombinedGrossWeight = optionalInfo.getTotalCombinedGrossWeight();
    	monitoringSystem = optionalInfo.getMonitoringSystem();
    	tachograph = optionalInfo.getTachograph();
    	renavan = optionalInfo.getRenavan();
    	limitLoad = optionalInfo.getLimitLoad();
    	asr = optionalInfo.getAsr();
    	currentKm = optionalInfo.getCurrentKm();
    	dateCurrentKm = optionalInfo.getDateCurrentKm();
    	anttExpiry = optionalInfo.getAnttExpiry();
    	citvExpiry = optionalInfo.getCitvExpiry();        
	}

	public Integer getAction() {
		return action;
	}

	public void setAction(Integer action) {
		this.action = action;
	}

	public Date getAnttExpiry() {
		return anttExpiry;
	}

	public void setAnttExpiry(Date anttExpiry) {
		this.anttExpiry = anttExpiry;
	}

	public boolean isAsr() {
		return asr;
	}

	public void setAsr(boolean asr) {
		this.asr = asr;
	}

	public Date getCitvExpiry() {
		return citvExpiry;
	}

	public void setCitvExpiry(Date citvExpiry) {
		this.citvExpiry = citvExpiry;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getCurrentKm() {
		return currentKm;
	}

	public void setCurrentKm(Integer currentKm) {
		this.currentKm = currentKm;
	}

	public Date getDateCurrentKm() {
		return dateCurrentKm;
	}

	public void setDateCurrentKm(Date dateCurrentKm) {
		this.dateCurrentKm = dateCurrentKm;
	}

	public Integer getLimitLoad() {
		return limitLoad;
	}

	public void setLimitLoad(Integer limitLoad) {
		this.limitLoad = limitLoad;
	}

	public String getMonitoringSystem() {
		return monitoringSystem;
	}

	public void setMonitoringSystem(String monitoringSystem) {
		this.monitoringSystem = monitoringSystem;
	}

	public VehicleOptionalInfoLogPK getPk() {
		return pk;
	}

	public void setPk(VehicleOptionalInfoLogPK pk) {
		this.pk = pk;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getRenavan() {
		return renavan;
	}

	public void setRenavan(String renavan) {
		this.renavan = renavan;
	}

	public String getTachograph() {
		return tachograph;
	}

	public void setTachograph(String tachograph) {
		this.tachograph = tachograph;
	}

	public Integer getTotalCombinedGrossWeight() {
		return totalCombinedGrossWeight;
	}

	public void setTotalCombinedGrossWeight(Integer totalCombinedGrossWeight) {
		this.totalCombinedGrossWeight = totalCombinedGrossWeight;
	}

	public Integer getTotalGrossWeight() {
		return totalGrossWeight;
	}

	public void setTotalGrossWeight(Integer totalGrossWeight) {
		this.totalGrossWeight = totalGrossWeight;
	}
	
    
    

}
