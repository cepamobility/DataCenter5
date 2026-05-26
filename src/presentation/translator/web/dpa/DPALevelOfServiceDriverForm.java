package presentation.translator.web.dpa;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.struts.action.ActionForm;

import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.dpa.LevelOfServiceDriverCourseDPAData;
import communication.administration.dpa.LevelOfServiceDriverDPAData;
import communication.datatype.tables.DPACourseDataType;
import communication.driver.tables.DriverData;
import communication.exception.CommunicationProblemException;
import communication.exception.DCSecurityException;
import communication.exception.DeslogedException;

import business.SystemAccess;
import business.alert.handler.Util;

public class DPALevelOfServiceDriverForm extends ActionForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer driverId;
	
	private String inspector;
	
	private String dateDay;
	private String dateMonth;
	private String dateYear;
	
	private String status;
	
	private String corporationId;
	
	private String firstName;
	
	private String lastName;
	
	private String plant;
	
	private String function=LevelOfServiceDriverDPAData.TYPE_GENERIC_1_VALUE;
	
	private String celular;
	
	private String birthDateDay;
	private String birthDateMonth;
	private String birthDateYear;
	
	private String idade;
	
	private String cpf;
	
	private String rg;
	
	private String uf;
	
	private String vcnhDay;
	private String vcnhMonth;
	private String vcnhYear;
	
	private String libPamcary;
	
	private String dataValDay;
	private String dataValMonth;
	private String dataValYear;
	
	private String dirDefensivaDay;
	private String dirDefensivaMonth;
	private String dirDefensivaYear;
	
	private String asoDay;
	private String asoMonth;
	private String asoYear;
	
	private String contractDay;
	private String contractMonth;
	private String contractYear;
	
	private String cadastroDPA=LevelOfServiceDriverDPAData.TYPE_GENERIC_1_VALUE;
	
	private String copyRG=LevelOfServiceDriverDPAData.TYPE_GENERIC_1_VALUE;
	
	private String copyCPF=LevelOfServiceDriverDPAData.TYPE_GENERIC_1_VALUE;
	
	private String copyCNH=LevelOfServiceDriverDPAData.TYPE_GENERIC_1_VALUE;
	
	private String compResid=LevelOfServiceDriverDPAData.TYPE_GENERIC_1_VALUE;
	
	private String testPsicologico=LevelOfServiceDriverDPAData.TYPE_GENERIC_1_VALUE;
	
	private String testTheorist=LevelOfServiceDriverDPAData.TYPE_GENERIC_1_VALUE;
	
	private String testPractical=LevelOfServiceDriverDPAData.TYPE_GENERIC_1_VALUE;
	
	
	//Trein de coleta de amostra
	private String workoutOfGathering=LevelOfServiceDriverDPAData.TYPE_GENERIC_1_VALUE; 
	
	private String termoEtilometro=LevelOfServiceDriverDPAData.TYPE_GENERIC_1_VALUE; 
	
	//Prontuario da CNH
	private String prontuarioCNH=LevelOfServiceDriverDPAData.TYPE_GENERIC_1_VALUE;
	
	private String operationalTraining=LevelOfServiceDriverDPAData.TYPE_GENERIC_1_VALUE;
	
	private String protocolFM300=LevelOfServiceDriverDPAData.TYPE_GENERIC_1_VALUE;
	
	private String workContract=LevelOfServiceDriverDPAData.TYPE_GENERIC_1_VALUE;
	
	private String cepa=LevelOfServiceDriverDPAData.TYPE_GENERIC_1_VALUE;
	
	private String cepaComment="";
	
	private String divOrg1;
	private String divOrg2;
	private String divOrg3;
	
	private String courses;
	
	private Set<LevelOfServiceDriverCourseDPAData> coursesData;
	
	
	
	public DPALevelOfServiceDriverForm(){
		coursesData=new HashSet<LevelOfServiceDriverCourseDPAData>();
	}
	
	public void load(SystemAccess sys,Integer id, Integer driverId) throws DeslogedException, CommunicationProblemException, DCSecurityException, RemoteException, Exception{
		LevelOfServiceDriverDPAData data=null;
		if(driverId!=null){
			//Creacion
			data=sys.getCorporationReportFacade().findLevelOfServiceDriverDPAByDriverId(sys.getUserSessionData(), driverId);
			if(data!=null){
				data.setId(null);
			}
		}else if(id!=null){
			//Edicion
			data=sys.getCorporationReportFacade().findLevelOfServiceDriverDPAById(sys.getUserSessionData(), id);
		}
		
		if(data==null){
			//Creacion, primer caso donde no existe ningun nivel de servicio anterior para el driver
			data=new LevelOfServiceDriverDPAData();
		}
		this.id=data.getId();
		
		if(data.getId()==null || data.getId()==0){
			//Creacion	
			//Inicializar con los datos del driver
			DriverData driverData=sys.getDriverReportFacade().findDriverByPrimaryKey(driverId, sys.getUserSessionData());
			
			data.setInspector(sys.getUserSessionData().getFullName());
			data.setDriverId(driverId);
			data.setDate(new Date());
			data.setStatus(driverData.getExclusionDate()==null?"A":"I");
			data.setCorporationId(driverData.getCorporationId());
			data.setFirstName(driverData.getFirstName());
			data.setLastName(driverData.getLastName());
		    data.setUf(driverData.getStateOrRegion());		
			data.setCpf(driverData.getIdDallas());		
			data.setRg(driverData.getBpsregistry());	
			data.setCelular(driverData.getSuperviser());
			data.setBirthDate(driverData.getBirthDate());
			data.setVcnh(driverData.getLicenseExpiry());
			DivOrgData divOrgData=(DivOrgData) driverData.getDivOrgs().iterator().next();
			ItemData level1=(ItemData)divOrgData.getDivOrgLevels().getItemIterator().next();
			data.setDivOrg1(level1.getId());
			ItemData level2=(ItemData)level1.getSubItems().getItemIterator().next();
			data.setDivOrg2(level2.getId());
			ItemData level3=(ItemData)level2.getSubItems().getItemIterator().next();
			data.setFleet(level3.getId());
			
		}
		
		this.inspector=data.getInspector();
		
		this.driverId=data.getDriverId();
		Calendar cal=Calendar.getInstance();
		if(data.getDate()!=null){
			cal.setTime(data.getDate());
			dateDay=String.valueOf(cal.get(Calendar.DATE));
			dateMonth=String.valueOf(cal.get(Calendar.MONTH)+1);
			dateYear=String.valueOf(cal.get(Calendar.YEAR));
		}
		
		status=data.getStatus();
		
		corporationId=data.getCorporationId();
		
		firstName=data.getFirstName();
		
		lastName=data.getLastName();
		
		plant=data.getPlant();
		
		divOrg3=data.getFleet()!=null ?data.getFleet().toString():"0";
		divOrg2=data.getDivOrg2()!=null ?data.getDivOrg2().toString():"0";
		divOrg1=data.getDivOrg1()!=null ?data.getDivOrg1().toString():"0";
		
		if(data.getFleet()!=null){
			sys.getUserSessionData().getFilter().getLabelItemData(0, data.getFleet());
		}
		
		function=data.getFunction();
		
		celular=data.getCelular();
		
		cal.setTime(data.getBirthDate());
		if(data.getBirthDate()!=null){
			birthDateDay=String.valueOf(cal.get(Calendar.DATE));
			birthDateMonth=String.valueOf(cal.get(Calendar.MONTH)+1);
			birthDateYear=String.valueOf(cal.get(Calendar.YEAR));
		}
	 
		
		idade=data.getIdade();
		
		cpf=data.getCpf();
		
		rg=data.getRg();
		
		uf=data.getUf();
		
		if(data.getVcnh()!=null){
			cal.setTime(data.getVcnh());
			vcnhDay=String.valueOf(cal.get(Calendar.DATE));
			vcnhMonth=String.valueOf(cal.get(Calendar.MONTH)+1);
			vcnhYear=String.valueOf(cal.get(Calendar.YEAR));
		}
	
		libPamcary=data.getLibPamcary();
		
		if(data.getDataVal()!=null){
			cal.setTime(data.getDataVal());
			dataValDay=String.valueOf(cal.get(Calendar.DATE));
			dataValMonth=String.valueOf(cal.get(Calendar.MONTH)+1);
			dataValYear=String.valueOf(cal.get(Calendar.YEAR));
		}

		if(data.getDirDefensiva()!=null){
			cal.setTime(data.getDirDefensiva());
			dirDefensivaDay=String.valueOf(cal.get(Calendar.DATE));
			dirDefensivaMonth=String.valueOf(cal.get(Calendar.MONTH)+1);
			dirDefensivaYear=String.valueOf(cal.get(Calendar.YEAR));
		}
		 
		if(data.getAso()!=null){
			cal.setTime(data.getAso());
			asoDay=String.valueOf(cal.get(Calendar.DATE));
			asoMonth=String.valueOf(cal.get(Calendar.MONTH)+1);
			asoYear=String.valueOf(cal.get(Calendar.YEAR));
		}
		
		if(data.getContract()!=null){
			cal.setTime(data.getContract());
			contractDay=String.valueOf(cal.get(Calendar.DATE));
			contractMonth=String.valueOf(cal.get(Calendar.MONTH)+1);
			contractYear=String.valueOf(cal.get(Calendar.YEAR));
		}
	 
		cadastroDPA=data.getCadastroDPA();
		
		copyRG=data.getCopyRG();
		
		copyCPF=data.getCopyCPF();
		
		copyCNH=data.getCopyCNH();
		
		compResid=data.getCompResid();
		
		testPsicologico=data.getTestPsicologico();
		
		testTheorist=data.getTestTheorist();
		
		testPractical=data.getTestPractical();
		
		//Trein de coleta de amostra
		workoutOfGathering=data.getWorkoutOfGathering(); 
		
		termoEtilometro=data.getTermoEtilometro(); 
		
		//Prontuario da CNH
		prontuarioCNH=data.getProntuarioCNH();
		
		operationalTraining=data.getOperationalTraining();
		
		protocolFM300=data.getProtocolFM300();
		
		workContract=data.getWorkContract();
	
		cepa=data.getCepa();
		
		cepaComment=data.getCepaComment();
		
		this.coursesData=data.getCourse();
	}

	public LevelOfServiceDriverDPAData makeLevelOfServiceDriverDPAData(SystemAccess sys){
		LevelOfServiceDriverDPAData data=new LevelOfServiceDriverDPAData();
		
		data.setId(this.id);
		
		data.setInspector(inspector);
		
		data.setDriverId(this.driverId);
		
		data.setDate(Util.getDate(dateDay, dateMonth, dateYear));
		
		data.setStatus(this.status);
		
		data.setCorporationId(this.corporationId);
		
		data.setPlant(this.plant);
		
		data.setFleet(this.divOrg3!=null?Integer.valueOf(this.divOrg3):null);
		
		data.setFunction(this.function);
		
		data.setCelular(this.celular);
		
		data.setBirthDate(Util.getDate(this.birthDateDay, this.birthDateMonth, this.birthDateYear));
		
		Calendar cal=Calendar.getInstance();
		cal.setTime(Util.getDate(this.birthDateDay, this.birthDateMonth, this.birthDateYear));
		 
		data.setIdade(String.valueOf(calcularEdad(cal)));
		
		data.setCpf(this.cpf);
		
		data.setRg(this.rg);
		
		data.setUf(this.uf);
		
		data.setFirstName(firstName);
		
		data.setLastName(lastName);
		
		data.setVcnh(Util.getDate(this.vcnhDay,this.vcnhMonth,this.vcnhYear));
		
		data.setLibPamcary(this.libPamcary);
		
		data.setDataVal(Util.getDate(this.dataValDay,this.dataValMonth,this.dataValYear));
		
		data.setDirDefensiva(Util.getDate(this.dirDefensivaDay,this.dirDefensivaMonth,this.dirDefensivaYear));
		
		data.setAso(Util.getDate(this.asoDay,this.asoMonth,this.asoYear));
		
		data.setContract(Util.getDate(this.contractDay,this.contractMonth,this.contractYear));
		
		data.setCadastroDPA(this.cadastroDPA);
		
		data.setCopyRG(this.copyRG);
		
		data.setCopyCPF(this.copyCPF);
		
		data.setCopyCNH(this.copyCNH);
		
		data.setCompResid(this.compResid);
		
		data.setTestPsicologico(this.testPsicologico);
		
		data.setTestTheorist(testTheorist);
		
		data.setTestPractical(testPractical);
		
		
		//Trein de coleta de amostra
		data.setWorkoutOfGathering(this.workoutOfGathering); 
		
		data.setTermoEtilometro(this.termoEtilometro); 
		
		//Prontuario da CNH
		data.setProntuarioCNH(this.prontuarioCNH);
		
		data.setOperationalTraining(this.operationalTraining);
		
		data.setProtocolFM300(this.protocolFM300);
		
		data.setWorkContract(this.workContract);
		
		data.setCepa(this.cepa);
		
		data.setCepaComment(cepaComment);
		
		data.setCourse(new HashSet<LevelOfServiceDriverCourseDPAData>());
		if(this.courses!=null && this.courses.trim().length()>0){
			String[] courseSP=this.courses.split("_");
			for(String row:courseSP){
				String[] columns=row.split("#");
				String cur=columns[0].trim();
				String[] dateSP=columns[1].split(";");
				Calendar calen=Calendar.getInstance();
				calen.set(Calendar.DATE, Integer.valueOf(dateSP[0].trim()));
				calen.set(Calendar.MONTH, Integer.valueOf(dateSP[1].trim())-1);
				calen.set(Calendar.YEAR, Integer.valueOf(dateSP[2].trim()));
				calen.set(Calendar.HOUR, 0);
				calen.set(Calendar.MINUTE, 0);
				calen.set(Calendar.SECOND, 0);
				
				String com=columns[2]!=null?columns[2].trim():"";
				LevelOfServiceDriverCourseDPAData dCourse=new LevelOfServiceDriverCourseDPAData();
				dCourse.setComment(com);
				dCourse.setDate(calen.getTime());
				dCourse.setCourse(cur);
				data.getCourse().add(dCourse);
			}
		}
		
		return data;
	}

	
	
	public Set<LevelOfServiceDriverCourseDPAData> getCoursesData() {
		return coursesData;
	}

	public void setCoursesData(Set<LevelOfServiceDriverCourseDPAData> coursesData) {
		this.coursesData = coursesData;
	}

	public String getCourses() {
		return courses;
	}

	public void setCourses(String courses) {
		this.courses = courses;
	}

	public String getInspector() {
		return inspector;
	}

	public void setInspector(String inspector) {
		this.inspector = inspector;
	}

	public Integer getId() {
		return id;
	}

	public String getCepa() {
		return cepa;
	}

	public void setCepa(String cepa) {
		this.cepa = cepa;
	}

	public String getCepaComment() {
		return cepaComment;
	}

	public void setCepaComment(String cepaComment) {
		this.cepaComment = cepaComment;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDriverId() {
		if(driverId==null){
			return 0;
		}
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		if(driverId!=null){
			this.driverId = driverId;
		}
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

	public String getCorporationId() {
		return corporationId;
	}

	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
	}

	 

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCopyRG() {
		return copyRG;
	}

	public void setCopyRG(String copyRG) {
		this.copyRG = copyRG;
	}

	public String getTestTheorist() {
		return testTheorist;
	}

	public void setTestTheorist(String testTheorist) {
		this.testTheorist = testTheorist;
	}

	public String getTestPractical() {
		return testPractical;
	}

	public void setTestPractical(String testPractical) {
		this.testPractical = testPractical;
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

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getBirthDateDay() {
		return birthDateDay;
	}

	public void setBirthDateDay(String birthDateDay) {
		this.birthDateDay = birthDateDay;
	}

	public String getBirthDateMonth() {
		return birthDateMonth;
	}

	public void setBirthDateMonth(String birthDateMonth) {
		this.birthDateMonth = birthDateMonth;
	}

	public String getBirthDateYear() {
		return birthDateYear;
	}

	public void setBirthDateYear(String birthDateYear) {
		this.birthDateYear = birthDateYear;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getVcnhDay() {
		return vcnhDay;
	}

	public void setVcnhDay(String vcnhDay) {
		this.vcnhDay = vcnhDay;
	}

	public String getVcnhMonth() {
		return vcnhMonth;
	}

	public void setVcnhMonth(String vcnhMonth) {
		this.vcnhMonth = vcnhMonth;
	}

	public String getVcnhYear() {
		return vcnhYear;
	}

	public void setVcnhYear(String vcnhYear) {
		this.vcnhYear = vcnhYear;
	}

	public String getLibPamcary() {
		return libPamcary;
	}

	public void setLibPamcary(String libPamcary) {
		this.libPamcary = libPamcary;
	}

	public String getDataValDay() {
		return dataValDay;
	}

	public void setDataValDay(String dataValDay) {
		this.dataValDay = dataValDay;
	}

	public String getDataValMonth() {
		return dataValMonth;
	}

	public void setDataValMonth(String dataValMonth) {
		this.dataValMonth = dataValMonth;
	}

	public String getDataValYear() {
		return dataValYear;
	}

	public void setDataValYear(String dataValYear) {
		this.dataValYear = dataValYear;
	}

	public String getDirDefensivaDay() {
		return dirDefensivaDay;
	}

	public void setDirDefensivaDay(String dirDefensivaDay) {
		this.dirDefensivaDay = dirDefensivaDay;
	}

	public String getDirDefensivaMonth() {
		return dirDefensivaMonth;
	}

	public void setDirDefensivaMonth(String dirDefensivaMonth) {
		this.dirDefensivaMonth = dirDefensivaMonth;
	}

	public String getDirDefensivaYear() {
		return dirDefensivaYear;
	}

	public void setDirDefensivaYear(String dirDefensivaYear) {
		this.dirDefensivaYear = dirDefensivaYear;
	}

	public String getAsoDay() {
		return asoDay;
	}

	public void setAsoDay(String asoDay) {
		this.asoDay = asoDay;
	}

	public String getAsoMonth() {
		return asoMonth;
	}

	public void setAsoMonth(String asoMonth) {
		this.asoMonth = asoMonth;
	}

	public String getAsoYear() {
		return asoYear;
	}

	public void setAsoYear(String asoYear) {
		this.asoYear = asoYear;
	}

	public String getContractDay() {
		return contractDay;
	}

	public void setContractDay(String contractDay) {
		this.contractDay = contractDay;
	}

	public String getContractMonth() {
		return contractMonth;
	}

	public void setContractMonth(String contractMonth) {
		this.contractMonth = contractMonth;
	}

	public String getContractYear() {
		return contractYear;
	}

	public void setContractYear(String contractYear) {
		this.contractYear = contractYear;
	}

	public String getCadastroDPA() {
		return cadastroDPA;
	}

	public void setCadastroDPA(String cadastroDPA) {
		this.cadastroDPA = cadastroDPA;
	}

	public String getCopyCPF() {
		return copyCPF;
	}

	public void setCopyCPF(String copyCPF) {
		this.copyCPF = copyCPF;
	}

	public String getCopyCNH() {
		return copyCNH;
	}

	public void setCopyCNH(String copyCNH) {
		this.copyCNH = copyCNH;
	}

	public String getCompResid() {
		return compResid;
	}

	public void setCompResid(String compResid) {
		this.compResid = compResid;
	}

	public String getTestPsicologico() {
		return testPsicologico;
	}

	public void setTestPsicologico(String testPsicologico) {
		this.testPsicologico = testPsicologico;
	}

	public String getWorkoutOfGathering() {
		return workoutOfGathering;
	}

	public void setWorkoutOfGathering(String workoutOfGathering) {
		this.workoutOfGathering = workoutOfGathering;
	}

	public String getTermoEtilometro() {
		return termoEtilometro;
	}

	public void setTermoEtilometro(String termoEtilometro) {
		this.termoEtilometro = termoEtilometro;
	}

	public String getProntuarioCNH() {
		return prontuarioCNH;
	}

	public void setProntuarioCNH(String prontuarioCNH) {
		this.prontuarioCNH = prontuarioCNH;
	}

	public String getOperationalTraining() {
		return operationalTraining;
	}

	public void setOperationalTraining(String operationalTraining) {
		this.operationalTraining = operationalTraining;
	}

	public String getProtocolFM300() {
		return protocolFM300;
	}

	public void setProtocolFM300(String protocolFM300) {
		this.protocolFM300 = protocolFM300;
	}

	public String getWorkContract() {
		return workContract;
	}

	public void setWorkContract(String workContract) {
		this.workContract = workContract;
	}
	
	
	public int calcularEdad(Calendar fechaNac) {
		 
	    Calendar fechaActual = Calendar.getInstance();
	 
	    // Cálculo de las diferencias.
	    int anios = fechaActual.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);
	    int meses = fechaActual.get(Calendar.MONTH) - fechaNac.get(Calendar.MONTH);
	    int dias = fechaActual.get(Calendar.DAY_OF_MONTH) - fechaNac.get(Calendar.DAY_OF_MONTH);
	 
	    // Hay que comprobar si el día de su cumpleaños es posterior
	    // a la fecha actual, para restar 1 a la diferencia de años,
	    // pues aún no ha sido su cumpleaños.</code>
	 
	    if(meses < 0 // Aún no es el mes de su cumpleaños
	       || (meses==0 && dias < 0)) { // o es el mes pero no ha llegado el día.
	 
	        anios--;
	    }
	    return anios;
	}
}
