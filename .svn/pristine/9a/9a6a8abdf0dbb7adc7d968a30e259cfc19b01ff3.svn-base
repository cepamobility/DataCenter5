package persistence.vehicle.tables;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.Session;

import communication.vehicle.tables.VehicleOptionalInfoData;

public class VehicleOptionalInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
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
	private String sl;
	

	public VehicleOptionalInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @return VehicleOptionalInfoData
	 */
	public VehicleOptionalInfoData getVehicleOptionalInfoData(){
		return new VehicleOptionalInfoData(id,prefix,totalGrossWeight,
				totalCombinedGrossWeight,monitoringSystem,tachograph,
				renavan,limitLoad,asr,currentKm,dateCurrentKm,
				anttExpiry,citvExpiry);
	}
	
	/**
	 * 
	 * @param data VehicleOptionalInfoData
	 */
	public VehicleOptionalInfo(VehicleOptionalInfoData data) {
		super();

		this.id = data.getId();
		this.prefix = data.getPrefix();
		this.totalGrossWeight = data.getTotalGrossWeight();
		this.totalCombinedGrossWeight = data.getTotalCombinedGrossWeight();
		this.monitoringSystem = data.getMonitoringSystem();
		this.tachograph = data.getTachograph();
		this.renavan = data.getRenavan();
		this.limitLoad = data.getLimitLoad();
		this.asr = data.getAsr();
		this.currentKm = data.getCurrentKm();
		this.dateCurrentKm = data.getDateCurrentKm();
		this.anttExpiry = data.getAnttExpiry();
		this.citvExpiry = data.getCitvExpiry();
		this.sl=data.getSl();
	}




	/**
	 * @param id
	 * @param prefix
	 * @param totalGrossWeight
	 * @param totalCombinedGrossWeight
	 * @param monitoringSystem
	 * @param tachograph
	 * @param renavan
	 * @param limitLoad
	 * @param asr
	 * @param currentKm
	 * @param dateCurrentKm
	 * @param anttExpiry
	 * @param citvExpiry
	 */
	public VehicleOptionalInfo(Integer id, String prefix, Integer totalGrossWeight, 
			Integer totalCombinedGrossWeight, String monitoringSystem,
			String tachograph, String renavan, Integer limitLoad, boolean asr, 
			Integer currentKm, Date dateCurrentKm, Date anttExpiry, Date citvExpiry) {
		super();
		// TODO Auto-generated constructor stub
		this.id = id;
		this.prefix = prefix;
		this.totalGrossWeight = totalGrossWeight;
		this.totalCombinedGrossWeight = totalCombinedGrossWeight;
		this.monitoringSystem = monitoringSystem;
		this.tachograph = tachograph;
		this.renavan = renavan;
		this.limitLoad = limitLoad;
		this.asr = asr;
		this.currentKm = currentKm;
		this.dateCurrentKm = dateCurrentKm;
		this.anttExpiry = anttExpiry;
		this.citvExpiry = citvExpiry;
	}




	public String getSl() {
		return sl;
	}

	public void setSl(String sl) {
		this.sl = sl;
	}

	public Date getAnttExpiry() {
		return anttExpiry;
	}



	public void setAnttExpiry(Date anttExpiry) {
		this.anttExpiry = anttExpiry;
	}



	public boolean getAsr() {
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



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
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
	
	public void updateData(VehicleOptionalInfoData data, Session session)
	{
			id = data.getId();
			prefix = data.getPrefix();
			totalGrossWeight = data.getTotalGrossWeight();
			totalCombinedGrossWeight = data.getTotalCombinedGrossWeight();
			monitoringSystem = data.getMonitoringSystem();
			tachograph = data.getTachograph();
			renavan = data.getRenavan();
			limitLoad = data.getLimitLoad();
			asr = data.getAsr();
			currentKm  = data.getCurrentKm();
			dateCurrentKm = data.getDateCurrentKm();
			anttExpiry = data.getAnttExpiry();
			citvExpiry = data.getCitvExpiry();
			sl=data.getSl();
	 }

}
