package business.ws.client.vehicle;

import java.io.Serializable;
import java.util.Date;

import business.ws.client.common.WSLDivOrgData;

import communication.administration.corporation.tables.CorporationData;
import communication.vehicle.tables.VehicleOptionalInfoData;

public class WSLVehicleData implements Serializable{

		public static final int ACTIVE = 0;
	    public static final int EXCLUDED = 1;
	    public static final int ALL = 2;
	    
	    public static final int DIV_ORG = 1;
	    public static final int DIV_GEO = 2;
		    
	    private static final long serialVersionUID = 1L;
		private String type;
		private String typeLabel;
		private String colour;
		private String permissionNumber;
		private String use;
		private Date permissionExpiry;
		private String ownerShip;
		private String fuel;
		private Date purchaseDate;
		private String chasisNumber;
		private Float co2emission;
		private Float consume;
		private String engine;
		private Integer tankCapacity;
		private String tankUnit;
		private boolean abs;
		private boolean airBag;
		private boolean aireAcondicionado;	
		private String odometerUnit;
		private CorporationData corporation;
	    private WSLDivOrgData[] divOrgs;	
	    //private DriverData driver;
		private String model;	
		private String trade;
		private String tradeLabel;
		private String cover;		
	    private String costCenter;
	    private Date inclusionDate;
	    
	    private Date mtopExpiry;
	    private Date suctaExpiry;
	    private String mtopExpiryStr;
	    private String suctaExpiryStr;
	    private Date greenCardExpiry;

	    public String getMtopExpiryStr() {
			return mtopExpiryStr;
		}

		public void setMtopExpiryStr(String mtopExpiryStr) {
			this.mtopExpiryStr = mtopExpiryStr;
		}

		public String getSuctaExpiryStr() {
			return suctaExpiryStr;
		}

		public void setSuctaExpiryStr(String suctaExpiryStr) {
			this.suctaExpiryStr = suctaExpiryStr;
		}

		private String ensuranceType;
	    private String ensurance;

	    private boolean ownerCard;                        

	    private Integer axisCount;
	    private Integer hp;

	    private String observations;
	    private VehicleOptionalInfoData optionalInfo;
	    
	    private Integer cost;
		private Integer odometer;
	    
	    private String assignedVehicle;
		
		private String comment;
		
		private String matricula;

		private Integer id;
		
//		private GeoLabelData country;
		private Integer year;
		private Date exclusionDate;
		    
		public WSLVehicleData(){}
		
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

		public Date getExclusionDate() {
			return exclusionDate;
		}

		public void setExclusionDate(Date exclusionDate) {
			this.exclusionDate = exclusionDate;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getColour() {
			return colour;
		}

		public String getMatricula() {
			return matricula;
		}

		public void setMatricula(String matricula) {
			this.matricula = matricula;
		}

		public void setColour(String colour) {
			this.colour = colour;
		}

		public String getPermissionNumber() {
			return permissionNumber;
		}

		public void setPermissionNumber(String permissionNumber) {
			this.permissionNumber = permissionNumber;
		}

		public String getUse() {
			return use;
		}

		public void setUse(String use) {
			this.use = use;
		}

		public Date getPermissionExpiry() {
			return permissionExpiry;
		}

		public void setPermissionExpiry(Date permissionExpiry) {
			this.permissionExpiry = permissionExpiry;
		}

		public String getOwnerShip() {
			return ownerShip;
		}

		public void setOwnerShip(String ownerShip) {
			this.ownerShip = ownerShip;
		}

		public String getFuel() {
			return fuel;
		}

		public void setFuel(String fuel) {
			this.fuel = fuel;
		}

		public Date getPurchaseDate() {
			return purchaseDate;
		}

		public void setPurchaseDate(Date purchaseDate) {
			this.purchaseDate = purchaseDate;
		}

		public String getChasisNumber() {
			return chasisNumber;
		}

		public void setChasisNumber(String chasisNumber) {
			this.chasisNumber = chasisNumber;
		}

		public Float getCo2emission() {
			return co2emission;
		}

		public void setCo2emission(Float co2emission) {
			this.co2emission = co2emission;
		}

		public Float getConsume() {
			return consume;
		}

		public void setConsume(Float consume) {
			this.consume = consume;
		}

		public String getEngine() {
			return engine;
		}

		public void setEngine(String engine) {
			this.engine = engine;
		}

		public Integer getTankCapacity() {
			return tankCapacity;
		}

		public void setTankCapacity(Integer tankCapacity) {
			this.tankCapacity = tankCapacity;
		}

		public String getTankUnit() {
			return tankUnit;
		}

		public void setTankUnit(String tankUnit) {
			this.tankUnit = tankUnit;
		}

		public boolean isAbs() {
			return abs;
		}

		public void setAbs(boolean abs) {
			this.abs = abs;
		}

		public boolean isAirBag() {
			return airBag;
		}

		public void setAirBag(boolean airBag) {
			this.airBag = airBag;
		}

		public boolean isAireAcondicionado() {
			return aireAcondicionado;
		}

		public void setAireAcondicionado(boolean aireAcondicionado) {
			this.aireAcondicionado = aireAcondicionado;
		}

		public String getOdometerUnit() {
			return odometerUnit;
		}

		public void setOdometerUnit(String odometerUnit) {
			this.odometerUnit = odometerUnit;
		}

		public CorporationData getCorporation() {
			return corporation;
		}

		public void setCorporation(CorporationData corporation) {
			this.corporation = corporation;
		}

		public WSLDivOrgData[] getDivOrgs() {
			return divOrgs;
		}

		public void setDivOrgs(WSLDivOrgData[] divOrgs) {
			this.divOrgs = divOrgs;
		}

		public String getModel() {
			return model;
		}

		public void setModel(String model) {
			this.model = model;
		}

		public String getTrade() {
			return trade;
		}

		public void setTrade(String trade) {
			this.trade = trade;
		}

		public String getCover() {
			return cover;
		}

		public void setCover(String cover) {
			this.cover = cover;
		}

		public String getCostCenter() {
			return costCenter;
		}

		public void setCostCenter(String costCenter) {
			this.costCenter = costCenter;
		}

		public Date getInclusionDate() {
			return inclusionDate;
		}

		public void setInclusionDate(Date inclusionDate) {
			this.inclusionDate = inclusionDate;
		}

		public Date getMtopExpiry() {
			return mtopExpiry;
		}

		public void setMtopExpiry(Date mtopExpiry) {
			this.mtopExpiry = mtopExpiry;
		}

		public Date getSuctaExpiry() {
			return suctaExpiry;
		}

		public void setSuctaExpiry(Date suctaExpiry) {
			this.suctaExpiry = suctaExpiry;
		}

		public Date getGreenCardExpiry() {
			return greenCardExpiry;
		}

		public void setGreenCardExpiry(Date greenCardExpiry) {
			this.greenCardExpiry = greenCardExpiry;
		}

		public String getEnsuranceType() {
			return ensuranceType;
		}

		public void setEnsuranceType(String ensuranceType) {
			this.ensuranceType = ensuranceType;
		}

		public String getEnsurance() {
			return ensurance;
		}

		public void setEnsurance(String ensurance) {
			this.ensurance = ensurance;
		}

		public boolean isOwnerCard() {
			return ownerCard;
		}

		public void setOwnerCard(boolean ownerCard) {
			this.ownerCard = ownerCard;
		}

		public Integer getAxisCount() {
			return axisCount;
		}

		public void setAxisCount(Integer axisCount) {
			this.axisCount = axisCount;
		}

		public Integer getHp() {
			return hp;
		}

		public void setHp(Integer hp) {
			this.hp = hp;
		}

		public String getObservations() {
			return observations;
		}

		public void setObservations(String observations) {
			this.observations = observations;
		}

		 

		public VehicleOptionalInfoData getOptionalInfo() {
			return optionalInfo;
		}

		public void setOptionalInfo(VehicleOptionalInfoData optionalInfo) {
			this.optionalInfo = optionalInfo;
		}

		public Integer getCost() {
			return cost;
		}

		public void setCost(Integer cost) {
			this.cost = cost;
		}

		public Integer getOdometer() {
			return odometer;
		}

		public void setOdometer(Integer odometer) {
			this.odometer = odometer;
		}

		public String getAssignedVehicle() {
			return assignedVehicle;
		}

		public void setAssignedVehicle(String assignedVehicle) {
			this.assignedVehicle = assignedVehicle;
		}

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}
		
		
		
}
