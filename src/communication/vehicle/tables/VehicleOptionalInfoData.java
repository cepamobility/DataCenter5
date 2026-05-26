package communication.vehicle.tables;

import java.io.Serializable;
import java.util.Date;

public class VehicleOptionalInfoData implements Serializable {

	
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
	
	public VehicleOptionalInfoData() {
		super();
		// TODO Auto-generated constructor stub
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
	public VehicleOptionalInfoData(Integer id, String prefix, Integer totalGrossWeight, 
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
	
	
	

}
