package communication.administration.dpa;

import java.io.Serializable;
import java.util.Date;

public class LevelOfServiceVehicleDPAData implements Serializable {
	
	private static final long serialVersionUID = 1L;

	//TYPE
	public static String TYPE_VEHICLE_TYPE_1_LABEL="generic.dpa.levelofservicevehicle.nodata";//NODATA 
	public static String TYPE_VEHICLE_TYPE_1_VALUE="NO";
	public static String TYPE_VEHICLE_TYPE_2_LABEL="generic.dpa.levelofservicevehicle.ro";//ROMEU 
	public static String TYPE_VEHICLE_TYPE_2_VALUE="RO";
	public static String TYPE_VEHICLE_TYPE_3_LABEL="generic.dpa.levelofservicedriver.ju";//JULIETA 
	public static String TYPE_VEHICLE_TYPE_3_VALUE="JU";
	
	//GENERAL
	public static String TYPE_GENERIC_1_VALUE="1";
	public static String TYPE_GENERIC_2_VALUE="2";
	public static String TYPE_GENERIC_3_VALUE="3";
	public static String TYPE_GENERIC_1_LABEL="generic.dpa.levelofservicedriver.nodata";
	public static String TYPE_GENERIC_2_LABEL="generic.dpa.levelofservicedriver.no";
	public static String TYPE_GENERIC_3_LABEL="generic.dpa.levelofservicedriver.ok";
	
	
	private Integer id;
	
	private String inspector;
	
	private Integer vehicleId;
	
	private Date date;
	
	private String status;
	
	private String tipo;
	
	private Integer fleet;
	
	private String propTank;
	
	private String matricula;
	
	private String eqp;
	
	private String marca;
	
	private String model;
	
	private String yearModel;
	
	private Date startOperation;
	
	private Date inspectVehicle;
	//Laudo de Aferição do Tacógrafo
	private Date vvaluationReportOfTheTachograph;
	
	private String chassi;
	
	private String renavan;
	
	//LAUDO DE INSP. 30 DIAS
	private String reportOFINSP;
	
	//CRLV (ANO)
	private String crlv;
	private Date crlvDate;
	//Seguro Terceiros
	private String thirdPartyInsurance;
	private Date thirdPartyInsuranceDate;
	
	private String plant;

	private Integer divOrg1;
	private Integer divOrg2;
	
	public Date getCrlvDate() {
		return crlvDate;
	}

	public void setCrlvDate(Date crlvDate) {
		this.crlvDate = crlvDate;
	}

	public Date getThirdPartyInsuranceDate() {
		return thirdPartyInsuranceDate;
	}

	public void setThirdPartyInsuranceDate(Date thirdPartyInsuranceDate) {
		this.thirdPartyInsuranceDate = thirdPartyInsuranceDate;
	}

	public Integer getDivOrg1() {
		return divOrg1;
	}

	public void setDivOrg1(Integer divOrg1) {
		this.divOrg1 = divOrg1;
	}

	public Integer getDivOrg2() {
		return divOrg2;
	}

	public void setDivOrg2(Integer divOrg2) {
		this.divOrg2 = divOrg2;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInspector() {
		return inspector;
	}

	public void setInspector(String inspector) {
		this.inspector = inspector;
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getFleet() {
		return fleet;
	}

	public void setFleet(Integer fleet) {
		this.fleet = fleet;
	}

	public String getPropTank() {
		return propTank;
	}

	public void setPropTank(String propTank) {
		this.propTank = propTank;
	}

	public String getEqp() {
		return eqp;
	}

	public void setEqp(String eqp) {
		this.eqp = eqp;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getYearModel() {
		return yearModel;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public void setYearModel(String yearModel) {
		this.yearModel = yearModel;
	}

	public Date getStartOperation() {
		return startOperation;
	}

	public void setStartOperation(Date startOperation) {
		this.startOperation = startOperation;
	}

	public Date getInspectVehicle() {
		return inspectVehicle;
	}

	public void setInspectVehicle(Date inspectVehicle) {
		this.inspectVehicle = inspectVehicle;
	}

	public Date getVvaluationReportOfTheTachograph() {
		return vvaluationReportOfTheTachograph;
	}

	public void setVvaluationReportOfTheTachograph(
			Date vvaluationReportOfTheTachograph) {
		this.vvaluationReportOfTheTachograph = vvaluationReportOfTheTachograph;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public String getRenavan() {
		return renavan;
	}

	public void setRenavan(String renavan) {
		this.renavan = renavan;
	}

	public String getReportOFINSP() {
		return reportOFINSP;
	}

	public void setReportOFINSP(String reportOFINSP) {
		this.reportOFINSP = reportOFINSP;
	}

	public String getCrlv() {
		return crlv;
	}

	public void setCrlv(String crlv) {
		this.crlv = crlv;
	}

	public String getThirdPartyInsurance() {
		return thirdPartyInsurance;
	}

	public void setThirdPartyInsurance(String thirdPartyInsurance) {
		this.thirdPartyInsurance = thirdPartyInsurance;
	}

	public String getPlant() {
		return plant;
	}

	public void setPlant(String plant) {
		this.plant = plant;
	}
	
	
	

}
