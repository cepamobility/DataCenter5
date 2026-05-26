package presentation.translator.web.dpa;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;

import org.apache.struts.action.ActionForm;


import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.dpa.LevelOfServiceVehicleDPAData;
import communication.exception.CommunicationProblemException;
import communication.exception.DCSecurityException;
import communication.exception.DeslogedException;
import communication.vehicle.tables.VehicleData;

import business.SystemAccess;
import business.alert.handler.Util;

public class DPALevelOfServiceVehicleForm extends ActionForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String inspector;
	
	private Integer vehicleId;
	
	private String dateDay;
	private String dateMonth;
	private String dateYear;
	
	private String status;
	
	private String tipo;
	
	private String fleet;
	
	private String propTank;
	
	private String matricula;
	
	private String eqp;
	
	private String marca;
	
	private String model;
	
	private String yearModel;
	
	private String startOperationDay;
	private String startOperationMonth;
	private String startOperationYear;
	
	private String inspectVehicleDay;
	private String inspectVehicleMonth;
	private String inspectVehicleYear;
	
	//Laudo de Aferição do Tacógrafo
	private String vvaluationReportOfTheTachographDay;
	private String vvaluationReportOfTheTachographMonth;
	private String vvaluationReportOfTheTachographYear;
	
	private String chassi;
	
	private String renavan;
	
	//LAUDO DE INSP. 30 DIAS
	private String reportOFINSP;
	
	//CRLV (ANO)
	private String crlv;
	private String crlvDay;
	private String crlvMonth;
	private String crlvYear;
	
	//Seguro Terceiros
	private String thirdPartyInsurance;
	private String thirdPartyInsuranceDay;
	private String thirdPartyInsuranceMonth;
	private String thirdPartyInsuranceYear;
	
	private String plant;
	
	private String divOrg1;
	private String divOrg2;
	private String divOrg3;
	
	public DPALevelOfServiceVehicleForm(){
		
	}
	
	public void load(SystemAccess sys,Integer id, Integer vehicleId) throws DeslogedException, CommunicationProblemException, DCSecurityException, RemoteException, Exception{
		LevelOfServiceVehicleDPAData data=null;
		if(vehicleId!=null){
			//Creacion
			data=sys.getCorporationReportFacade().findLevelOfServiceVehicleDPAByVehicleId(sys.getUserSessionData(), vehicleId);
			if(data!=null){
				data.setId(null);
			}
		}else if(id!=null){
			//Edicion
			data=sys.getCorporationReportFacade().findLevelOfServiceVehicleDPAById(sys.getUserSessionData(), id);
		}
		
		if(data==null){
			//Creacion, primer caso donde no existe ningun nivel de servicio anterior para el vehicle
			data=new LevelOfServiceVehicleDPAData();
		}
		this.id=data.getId();
		
		if(data.getId()==null || data.getId()==0){
			//Creacion	
			//Inicializar con los datos del vehicle
			VehicleData vehicleData=sys.getVehicleReportFacade().findVehicleById(vehicleId, sys.getUserSessionData());
			
			data.setInspector(sys.getUserSessionData().getFullName());
			data.setVehicleId(vehicleId);
			data.setDate(new Date());
			data.setStatus(vehicleData.getExclusionDate()==null?"A":"I");
			data.setMatricula(vehicleData.getMatricula());
			data.setChassi(vehicleData.getChasisNumber());
			data.setYearModel(vehicleData.getYear()!=null?vehicleData.getYear().toString():"");
			data.setModel(vehicleData.getModel());
			data.setRenavan(vehicleData.getPermissionNumber());
			
			DivOrgData divOrgData=(DivOrgData) vehicleData.getDivOrgs().iterator().next();
			ItemData level1=(ItemData)divOrgData.getDivOrgLevels().getItemIterator().next();
			data.setDivOrg1(level1.getId());
			ItemData level2=(ItemData)level1.getSubItems().getItemIterator().next();
			data.setDivOrg2(level2.getId());
			ItemData level3=(ItemData)level2.getSubItems().getItemIterator().next();
			data.setFleet(level3.getId());
			
		}
		
		
		Calendar cal=Calendar.getInstance();
		if(data.getDate()!=null){
			cal.setTime(data.getDate());
			dateDay=String.valueOf(cal.get(Calendar.DATE));
			dateMonth=String.valueOf(cal.get(Calendar.MONTH)+1);
			dateYear=String.valueOf(cal.get(Calendar.YEAR));
		}
		
		 this.id=data.getId();
			
		 this.inspector=data.getInspector();
		
		 this.vehicleId=data.getVehicleId();
		
		 this.status=data.getStatus();
		
		 this.tipo=data.getTipo();
		
		 this.fleet=data.getFleet()!=null?data.getFleet().toString():"0";
		
		 this.propTank=data.getPropTank();
		
		 this.matricula=data.getMatricula();
		
		 this.eqp=data.getEqp();
		
		 this.marca=data.getMarca();
		
		 this.model=data.getModel();
		
		 this.yearModel=data.getYearModel();
		
		 cal=Calendar.getInstance();
		 if(data.getStartOperation()!=null){
			cal.setTime(data.getStartOperation());
			startOperationDay=String.valueOf(cal.get(Calendar.DATE));
			startOperationMonth=String.valueOf(cal.get(Calendar.MONTH)+1);
			startOperationYear=String.valueOf(cal.get(Calendar.YEAR));
		 }
		
		 cal=Calendar.getInstance();
		 if(data.getInspectVehicle()!=null){
			cal.setTime(data.getInspectVehicle());
			inspectVehicleDay=String.valueOf(cal.get(Calendar.DATE));
			inspectVehicleMonth=String.valueOf(cal.get(Calendar.MONTH)+1);
			inspectVehicleYear=String.valueOf(cal.get(Calendar.YEAR));
		 }
		 
		//Laudo de Aferição do Tacógrafo
		 
		 cal=Calendar.getInstance();
		 if(data.getVvaluationReportOfTheTachograph()!=null){
			cal.setTime(data.getVvaluationReportOfTheTachograph());
			vvaluationReportOfTheTachographDay=String.valueOf(cal.get(Calendar.DATE));
			vvaluationReportOfTheTachographMonth=String.valueOf(cal.get(Calendar.MONTH)+1);
			vvaluationReportOfTheTachographYear=String.valueOf(cal.get(Calendar.YEAR));
		 }
		 this.chassi=data.getChassi();
		
		 this.renavan=data.getRenavan();
		
		//LAUDO DE INSP. 30 DIAS
		 this.reportOFINSP=data.getReportOFINSP();
		
		//CRLV (ANO)
		 this.crlv=data.getCrlv();
		 cal=Calendar.getInstance();
		 if(data.getCrlvDate()!=null){
			cal.setTime(data.getCrlvDate());
			crlvDay=String.valueOf(cal.get(Calendar.DATE));
			crlvMonth=String.valueOf(cal.get(Calendar.MONTH)+1);
			crlvYear=String.valueOf(cal.get(Calendar.YEAR));
		 }
		 
		//Seguro Terceiros
		 this.thirdPartyInsurance=data.getThirdPartyInsurance();
		 cal=Calendar.getInstance();
		 if(data.getThirdPartyInsuranceDate()!=null){
			cal.setTime(data.getThirdPartyInsuranceDate());
			thirdPartyInsuranceDay=String.valueOf(cal.get(Calendar.DATE));
			thirdPartyInsuranceMonth=String.valueOf(cal.get(Calendar.MONTH)+1);
			thirdPartyInsuranceYear=String.valueOf(cal.get(Calendar.YEAR));
		 }
		 this.plant=data.getPlant();
		
		divOrg3=data.getFleet()!=null ?data.getFleet().toString():"0";
		divOrg2=data.getDivOrg2()!=null ?data.getDivOrg2().toString():"0";
		divOrg1=data.getDivOrg1()!=null ?data.getDivOrg1().toString():"0";
		
		
		
	}

	public LevelOfServiceVehicleDPAData makeLevelOfServiceVehicleDPAData(SystemAccess sys){
		LevelOfServiceVehicleDPAData data=new LevelOfServiceVehicleDPAData();
		 data.setId(id);
			
		 data.setInspector(inspector);
		
		 data.setVehicleId(vehicleId);
		
		 data.setDate(Util.getDate(this.dateDay, this.dateMonth, this.dateYear));
		
		 data.setStatus(status);
		
		 data.setTipo(tipo);
		
		 data.setFleet(fleet!=null?(!fleet.equals("0")?Integer.valueOf(fleet):null):null);
		
		
		 data.setPropTank(propTank);
		
		 data.setMatricula(matricula);
		
		 data.setEqp(eqp);
		
		 data.setMarca(marca);
		
		 data.setModel(model);
		
		 data.setYearModel(yearModel);
		
		 data.setStartOperation(Util.getDate(this.startOperationDay, this.startOperationMonth, this.startOperationYear));
		
		 data.setInspectVehicle(Util.getDate(this.inspectVehicleDay, this.inspectVehicleMonth, this.inspectVehicleYear));
		//Laudo de Aferição do Tacógrafo
		 data.setVvaluationReportOfTheTachograph(Util.getDate(this.vvaluationReportOfTheTachographDay, this.vvaluationReportOfTheTachographMonth, this.vvaluationReportOfTheTachographYear));
		
		 data.setChassi(chassi);
		
		 data.setRenavan(renavan);
		
		//LAUDO DE INSP. 30 DIAS
		 data.setReportOFINSP(reportOFINSP);
		
		//CRLV (ANO)
		 data.setCrlv(crlv);
		 data.setCrlvDate(Util.getDate(this.crlvDay, this.crlvMonth, this.crlvYear));
		 
		//Seguro Terceiros
		 data.setThirdPartyInsurance(thirdPartyInsurance);
		 data.setThirdPartyInsuranceDate(Util.getDate(this.thirdPartyInsuranceDay, this.thirdPartyInsuranceMonth, this.thirdPartyInsuranceYear));
		 
		 data.setPlant(plant);
		
		return data;
		
	}

	public String getCrlvDay() {
		return crlvDay;
	}

	public void setCrlvDay(String crlvDay) {
		this.crlvDay = crlvDay;
	}

	public String getCrlvMonth() {
		return crlvMonth;
	}

	public void setCrlvMonth(String crlvMonth) {
		this.crlvMonth = crlvMonth;
	}

	public String getCrlvYear() {
		return crlvYear;
	}

	public void setCrlvYear(String crlvYear) {
		this.crlvYear = crlvYear;
	}

	public String getThirdPartyInsuranceDay() {
		return thirdPartyInsuranceDay;
	}

	public void setThirdPartyInsuranceDay(String thirdPartyInsuranceDay) {
		this.thirdPartyInsuranceDay = thirdPartyInsuranceDay;
	}

	public String getThirdPartyInsuranceMonth() {
		return thirdPartyInsuranceMonth;
	}

	public void setThirdPartyInsuranceMonth(String thirdPartyInsuranceMonth) {
		this.thirdPartyInsuranceMonth = thirdPartyInsuranceMonth;
	}

	public String getThirdPartyInsuranceYear() {
		return thirdPartyInsuranceYear;
	}

	public void setThirdPartyInsuranceYear(String thirdPartyInsuranceYear) {
		this.thirdPartyInsuranceYear = thirdPartyInsuranceYear;
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

	public String getDateDay() {
		return dateDay;
	}

	public void setDateDay(String dateDay) {
		this.dateDay = dateDay;
	}

	public String getDateMonth() {
		return dateMonth;
	}

	public void setDateMonth(String dateMonth) {
		this.dateMonth = dateMonth;
	}

	public String getDateYear() {
		return dateYear;
	}

	public void setDateYear(String dateYear) {
		this.dateYear = dateYear;
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

	public String getFleet() {
		return fleet;
	}

	public void setFleet(String fleet) {
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

	public String getStartOperationDay() {
		return startOperationDay;
	}

	public void setStartOperationDay(String startOperationDay) {
		this.startOperationDay = startOperationDay;
	}

	public String getStartOperationMonth() {
		return startOperationMonth;
	}

	public void setStartOperationMonth(String startOperationMonth) {
		this.startOperationMonth = startOperationMonth;
	}

	public String getStartOperationYear() {
		return startOperationYear;
	}

	public void setStartOperationYear(String startOperationYear) {
		this.startOperationYear = startOperationYear;
	}

	public String getInspectVehicleDay() {
		return inspectVehicleDay;
	}

	public void setInspectVehicleDay(String inspectVehicleDay) {
		this.inspectVehicleDay = inspectVehicleDay;
	}

	public String getInspectVehicleMonth() {
		return inspectVehicleMonth;
	}

	public void setInspectVehicleMonth(String inspectVehicleMonth) {
		this.inspectVehicleMonth = inspectVehicleMonth;
	}

	public String getInspectVehicleYear() {
		return inspectVehicleYear;
	}

	public void setInspectVehicleYear(String inspectVehicleYear) {
		this.inspectVehicleYear = inspectVehicleYear;
	}

 

	public String getVvaluationReportOfTheTachographDay() {
		return vvaluationReportOfTheTachographDay;
	}

	public void setVvaluationReportOfTheTachographDay(
			String vvaluationReportOfTheTachographDay) {
		this.vvaluationReportOfTheTachographDay = vvaluationReportOfTheTachographDay;
	}

	public String getVvaluationReportOfTheTachographMonth() {
		return vvaluationReportOfTheTachographMonth;
	}

	public void setVvaluationReportOfTheTachographMonth(
			String vvaluationReportOfTheTachographMonth) {
		this.vvaluationReportOfTheTachographMonth = vvaluationReportOfTheTachographMonth;
	}

	public String getVvaluationReportOfTheTachographYear() {
		return vvaluationReportOfTheTachographYear;
	}

	public void setVvaluationReportOfTheTachographYear(
			String vvaluationReportOfTheTachographYear) {
		this.vvaluationReportOfTheTachographYear = vvaluationReportOfTheTachographYear;
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

	public String getDivOrg1() {
		return divOrg1;
	}

	public void setDivOrg1(String divOrg1) {
		this.divOrg1 = divOrg1;
	}

	public String getDivOrg2() {
		return divOrg2;
	}

	public void setDivOrg2(String divOrg2) {
		this.divOrg2 = divOrg2;
	}

	public String getDivOrg3() {
		return divOrg3;
	}

	public void setDivOrg3(String divOrg3) {
		this.divOrg3 = divOrg3;
	}

	
	
}
