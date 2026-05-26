package persistence.administration.tables;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;

import persistence.administration.corporation.tables.DivOrgItemLevel1;
import persistence.driver.tables.Driver;
import persistence.hibernate.HibernateUtil;
import persistence.util.Util;
import persistence.vehicle.tables.Vehicle;


import communication.administration.dpa.LevelOfServiceDriverDPAData;
import communication.administration.dpa.LevelOfServiceVehicleDPAData;
import communication.administration.user.UserSessionData;
import communication.language.Text;


/**
 * 
 * @author fcarriquiry
 *
 */
public class LevelOfServiceVehicleDPA implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
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
	//En caso Si crlv va la fecha de vencimiento en crlvDate
	private Date crlvDate;
	
	//Seguro Terceiros
	private String thirdPartyInsurance;
	//En caso Si thirdPartyInsurance va la fecha de vencimiento en thirdPartyInsuranceDate
	private Date thirdPartyInsuranceDate;
	
	private String plant;
	
		
	public LevelOfServiceVehicleDPA(){
		
	}	
	
	
	public LevelOfServiceVehicleDPA(LevelOfServiceVehicleDPAData data){
		
		 this.id=data.getId();
		
		 this.inspector=data.getInspector();
		
		 this.vehicleId=data.getVehicleId();
		
		 this.date=data.getDate();
		
		 this.status=data.getStatus();
		
		 this.tipo=data.getTipo();
		
		 this.fleet=data.getFleet();
		
		 this.propTank=data.getPropTank();
		
		 this.matricula=data.getMatricula();
		
		 this.eqp=data.getEqp();
		
		 this.marca=data.getMarca();
		
		 this.model=data.getModel();
		
		 this.yearModel=data.getYearModel();
		
		 this.startOperation=data.getStartOperation();
		
		 this.inspectVehicle=data.getInspectVehicle();
		//Laudo de Aferição do Tacógrafo
		 this.vvaluationReportOfTheTachograph=data.getVvaluationReportOfTheTachograph();
		
		 this.chassi=data.getChassi();
		
		 this.renavan=data.getRenavan();
		
		//LAUDO DE INSP. 30 DIAS
		 this.reportOFINSP=data.getReportOFINSP();
		
		//CRLV (ANO)
		 this.crlv=data.getCrlv();
		 this.crlvDate=data.getCrlvDate();
		 
		//Seguro Terceiros
		 this.thirdPartyInsurance=data.getThirdPartyInsurance();
		 this.thirdPartyInsuranceDate=data.getThirdPartyInsuranceDate();
		 
		 
		 this.plant=data.getPlant();
	}
	
	public void updateData(LevelOfServiceVehicleDPAData data){
				
		 this.inspector=data.getInspector();
		
		 this.vehicleId=data.getVehicleId();
		
		 this.date=data.getDate();
		
		 this.status=data.getStatus();
		
		 this.tipo=data.getTipo();
		
		 this.fleet=data.getFleet();
		
		 this.propTank=data.getPropTank();
		
		 this.matricula=data.getMatricula();
		
		 this.eqp=data.getEqp();
		
		 this.marca=data.getMarca();
		
		 this.model=data.getModel();
		
		 this.yearModel=data.getYearModel();
		
		 this.startOperation=data.getStartOperation();
		
		 this.inspectVehicle=data.getInspectVehicle();
		//Laudo de Aferição do Tacógrafo
		 this.vvaluationReportOfTheTachograph=data.getVvaluationReportOfTheTachograph();
		
		 this.chassi=data.getChassi();
		
		 this.renavan=data.getRenavan();
		
		//LAUDO DE INSP. 30 DIAS
		 this.reportOFINSP=data.getReportOFINSP();
		
		//CRLV (ANO)
		 this.crlv=data.getCrlv();
		 this.crlvDate=data.getCrlvDate();
		 
		//Seguro Terceiros
		 this.thirdPartyInsurance=data.getThirdPartyInsurance();
		 this.thirdPartyInsuranceDate=data.getThirdPartyInsuranceDate();
		 
		 this.plant=data.getPlant();
	}
	
	public LevelOfServiceVehicleDPAData getLevelOfServiceVehicleDPAData(UserSessionData userSessionData){
		LevelOfServiceVehicleDPAData data=new LevelOfServiceVehicleDPAData();
		
		 data.setId(id);
		
		 data.setInspector(inspector);
		
		 data.setVehicleId(vehicleId);
		
		 data.setDate(date);
		
		 data.setStatus(status);
		
		 data.setTipo(tipo);
		
		 data.setFleet(fleet);
		
		 Session session=HibernateUtil.currentSession();
		 if(this.fleet!=null){
			DivOrgItemLevel1 divOrg=(DivOrgItemLevel1)session.load(DivOrgItemLevel1.class, this.fleet);
			
			data.setDivOrg1(divOrg.getDivOrgItemLevel2().getDivOrgItemLevel3().getId());
			
			data.setDivOrg2(divOrg.getDivOrgItemLevel2().getId());
		 } 
			
		 data.setPropTank(propTank);
		
		 data.setMatricula(matricula);
		
		 data.setEqp(eqp);
		
		 data.setMarca(marca);
		
		 data.setModel(model);
		
		 data.setYearModel(yearModel);
		
		 data.setStartOperation(startOperation);
		
		 data.setInspectVehicle(inspectVehicle);
		//Laudo de Aferição do Tacógrafo
		 data.setVvaluationReportOfTheTachograph(vvaluationReportOfTheTachograph);
		
		 data.setChassi(chassi);
		
		 data.setRenavan(renavan);
		
		//LAUDO DE INSP. 30 DIAS
		 data.setReportOFINSP(reportOFINSP);
		
		//CRLV (ANO)
		 data.setCrlv(crlv);
		 data.setCrlvDate(crlvDate);
			
		//Seguro Terceiros
		 data.setThirdPartyInsurance(thirdPartyInsurance);
		 data.setThirdPartyInsuranceDate(thirdPartyInsuranceDate);
		 
		 data.setPlant(plant);
		
		return data;
	}
	
	public String[] getRowToExport(Text messages){
		
		Session session=HibernateUtil.currentSession();
		
		String dateC="";
		if(crlv!=null && crlv.equals(LevelOfServiceVehicleDPAData.TYPE_GENERIC_3_VALUE)){
			dateC=Util.formatDate(crlvDate);
		}
		String dateT="";
		if(thirdPartyInsurance!=null && thirdPartyInsurance.equals(LevelOfServiceVehicleDPAData.TYPE_GENERIC_3_VALUE)){
			dateT=Util.formatDate(thirdPartyInsuranceDate);
		}
		
		Vehicle vehicle=(Vehicle)session.load(Vehicle.class, vehicleId);
		
		
		String label1="Sim Dados";
		
		String label2="Sim Dados";
		String label3="Sim Dados";
		DivOrgItemLevel1 divOrg=vehicle.getDivOrg1();
		if(divOrg!=null && divOrg.getDivOrgItemLevel2()!=null && divOrg.getDivOrgItemLevel2().getDivOrgItemLevel3()!=null){
			label1=divOrg.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();
			
			label2=divOrg.getDivOrgItemLevel2().getResourceKey();
			
			label3=divOrg.getResourceKey();
		}
		
		
		
		String[] row=new String[]{ 
				id!=null ?id.toString():"",
				//getHumanValue(this.tipo,messages),
				messages.getText(vehicle.getType().getResourceBundleId()),
				Util.formatDate(this.date),
				vehicle.getMatricula(),	
		 		inspector	,
		 		status.equals("A")?"Ativo":"Inativo",
				plant,
				label1,
				label2,
				label3,
				propTank,	
				messages.getText(vehicle.getTrade().getResourceBundleId()),
				vehicle.getModel(),
				vehicle.getYear()!=null?vehicle.getYear().toString():"",
				Util.formatDate(this.startOperation),
				Util.formatDate(this.inspectVehicle),
				Util.formatDate(this.vvaluationReportOfTheTachograph),
				vehicle.getChasisNumber(),
				vehicle.getPermissionNumber(),
				getHumanValue(reportOFINSP,messages),
				getHumanValue(crlv,messages),
				dateC,
				getHumanValue(thirdPartyInsurance,messages),
				dateT
		};
		
		
		return row;
	}

	public static String[] getHeaderToExport(Text messages){
		 
	     String[] row=new String[]{
	    		 	"ID de registro",
					messages.getText("generic.dpa.levelofservicevheicle.type"),
					messages.getText("generic.activity.date")+" ( "+messages.getText("generic.messages.dateformat")+")",
					messages.getText("generic.dpa.levelofservicevehicle.matriucula"),	
			 		messages.getText("generic.dpa.levelofservicedriver.inspector")	,
			 		messages.getText("generic.dpa.levelofservicedriver.status"),
					messages.getText("generic.dpa.levelofservicedriver.plant"),
					messages.getText("generic.dpa.divorglevel1"),
					messages.getText("generic.dpa.divorglevel2"),
					messages.getText("generic.dpa.divorglevel3"),
					messages.getText("generic.dpa.levelofservicevehicle.proptank"),	
					messages.getText("generic.dpa.levelofservicevehicle.marca"),
					messages.getText("generic.dpa.levelofservicevehicle.model"),
					messages.getText("generic.dpa.levelofservicevehicle.yearmodel"),
					messages.getText("generic.dpa.levelofservicevehicle.startoperation")+" ( "+messages.getText("generic.messages.dateformat")+")",
					messages.getText("generic.dpa.levelofservicevehicle.inspectvehicle")+" ( "+messages.getText("generic.messages.dateformat")+")",
					messages.getText("generic.dpa.levelofservicevehicle.vvaluationreportofthetachograph")+" ( "+messages.getText("generic.messages.dateformat")+")",
					messages.getText("generic.dpa.levelofservicevehicle.chassi"),
					messages.getText("generic.dpa.levelofservicevehicle.renavan"),
					messages.getText("generic.dpa.levelofservicevehicle.reportofinsp"),
					messages.getText("generic.dpa.levelofservicevehicle.crlv"),
					messages.getText("generic.dpa.levelofservicevehicle.crlv")+" Data",
					messages.getText("generic.dpa.levelofservicevehicle.thirdpartyinsurance"),
					messages.getText("generic.dpa.levelofservicevehicle.thirdpartyinsurance")+" Data "
			
			};
	     return row;

	}
	
	private String getHumanValue(String v,Text messages) {
		if(v==null){
			return "";
		}
		
		if(v.equals(LevelOfServiceVehicleDPAData.TYPE_GENERIC_1_VALUE)){
			return messages.getText(LevelOfServiceVehicleDPAData.TYPE_GENERIC_1_LABEL);//Sin datos
		}
		if(v.equals(LevelOfServiceVehicleDPAData.TYPE_GENERIC_2_VALUE)){
			return messages.getText(LevelOfServiceVehicleDPAData.TYPE_GENERIC_2_LABEL);//NO
		}
		if(v.equals(LevelOfServiceVehicleDPAData.TYPE_VEHICLE_TYPE_1_VALUE)){
			return  messages.getText(LevelOfServiceVehicleDPAData.TYPE_VEHICLE_TYPE_1_LABEL);
		}
		if(v.equals(LevelOfServiceVehicleDPAData.TYPE_VEHICLE_TYPE_2_VALUE)){
			return  messages.getText(LevelOfServiceVehicleDPAData.TYPE_VEHICLE_TYPE_2_LABEL);
		}
		if(v.equals(LevelOfServiceVehicleDPAData.TYPE_VEHICLE_TYPE_3_VALUE)){
			return  messages.getText(LevelOfServiceVehicleDPAData.TYPE_VEHICLE_TYPE_3_LABEL);
		}
		
		return messages.getText(LevelOfServiceVehicleDPAData.TYPE_GENERIC_3_LABEL);//SI
	}


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


	public String getMatricula() {
		return matricula;
	}


	public void setMatricula(String matricula) {
		this.matricula = matricula;
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
	
	 @Override
	 public int hashCode() {
		 return this.id;
	 }
	
	 

	
	
	
	
	
	
}
