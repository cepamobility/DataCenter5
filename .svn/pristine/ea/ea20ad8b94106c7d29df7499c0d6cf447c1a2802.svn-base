package persistence.administration.tables;

import java.io.Serializable;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

import persistence.administration.corporation.tables.DivOrgItemLevel1;
import persistence.driver.tables.Driver;
import persistence.hibernate.HibernateUtil;
import persistence.util.Util;


import communication.administration.dpa.LevelOfServiceDriverCourseDPAData;
import communication.administration.dpa.LevelOfServiceDriverDPAData;
import communication.administration.user.UserSessionData;
import communication.language.Text;


/**
 * 
 * @author fcarriquiry
 *
 */
public class LevelOfServiceDriverDPA implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String inspector;
	
	private Integer driverId;
	
	private Date date;
	
	private String status;
	
	private String corporationId;
	
	private String firstName;
	
	private String lastName;
	
	private String plant;
	
	private Integer fleet;
	
	private String function;
	
	private String celular;
	
	private Date birthDate;
	
	private String idade;
	
	private String cpf;
	
	private String rg;
	
	private String uf;
	
	private Date vcnh;
	
	private String libPamcary;
	
	private Date dataVal;
	
	private Date dirDefensiva;
	
	private Date aso;
	
	private Date contract;
	
	private String cadastroDPA;
	
	private String copyRG;
	
	
	private String copyCPF;
	
	private String copyCNH;
	
	private String compResid;
	
	private String testPsicologico;
	
	private String testTheorist;
	
	private String testPractical;
	
	
	//Trein de coleta de amostra
	private String workoutOfGathering; 
	
	private String termoEtilometro; 
	
	//Prontuario da CNH
	private String prontuarioCNH;
	
	private String operationalTraining;
	
	private String protocolFM300;
	
	private String workContract;
	private String cepa;
	private String cepaComment;
	
	private Set<LevelOfServiceDriverCourseDPA> course;
		
	

	public LevelOfServiceDriverDPA(){
		
	}	
	
	
	public LevelOfServiceDriverDPA(LevelOfServiceDriverDPAData data){
		
		this.id=data.getId();
		
		this.inspector=data.getInspector();
		
		this.driverId=data.getDriverId();
		
		this.date=data.getDate();
		
		this.status=data.getStatus();
		
		this.corporationId=data.getCorporationId();
		
		this.firstName=data.getFirstName();
		
		this.lastName=data.getLastName();
		
		this.plant=data.getPlant();
		
		this.fleet=data.getFleet();
		
		this.function=data.getFunction();
		
		this.celular=data.getCelular();
		
		this.birthDate=data.getBirthDate();
		
		this.idade=data.getIdade();
		
		this.cpf=data.getCpf();
		
		this.rg=data.getRg();
		
		this.uf=data.getUf();
		
		this.vcnh=data.getVcnh();
		
		this.libPamcary=data.getLibPamcary();
		
		this.dataVal=data.getDataVal();
		
		this.dirDefensiva=data.getDirDefensiva();
		
		this.aso=data.getAso();
		
		this.contract=data.getContract();
		
		this.cadastroDPA=data.getCadastroDPA();
		
		this.copyRG=data.getCopyRG();
		
		this.copyCPF=data.getCopyCPF();
		
		this.copyCNH=data.getCopyCNH();
		
		this.compResid=data.getCompResid();
		
		this.testPsicologico=data.getTestPsicologico();
		
		this.testTheorist=data.getTestTheorist();
		
		this.testPractical=data.getTestPractical();
		
		//Trein de coleta de amostra
		this.workoutOfGathering=data.getWorkoutOfGathering(); 
		
		this.termoEtilometro=data.getTermoEtilometro(); 
		
		//Prontuario da CNH
		this.prontuarioCNH=data.getProntuarioCNH();
		
		this.operationalTraining=data.getOperationalTraining();
		
		this.protocolFM300=data.getProtocolFM300();
		
		this.workContract=data.getWorkContract();
		
		this.cepa=data.getCepa();
		
		this.cepaComment=data.getCepaComment();
		
		this.course=new HashSet<LevelOfServiceDriverCourseDPA>();
		
		for(LevelOfServiceDriverCourseDPAData cData:data.getCourse()){
			this.course.add(new LevelOfServiceDriverCourseDPA(cData));
		}
	}
	
	public void updateData(LevelOfServiceDriverDPAData data){
		

		this.date=data.getDate();
		
		this.inspector=data.getInspector();
		
		this.status=data.getStatus();
		
		this.corporationId=data.getCorporationId();
		
		this.firstName=data.getFirstName();
		
		this.lastName=data.getLastName();
		
		this.plant=data.getPlant();
		
		this.fleet=data.getFleet();
		
		this.function=data.getFunction();
		
		this.celular=data.getCelular();
		
		this.birthDate=data.getBirthDate();
		
		this.idade=data.getIdade();
		
		this.cpf=data.getCpf();
		
		this.rg=data.getRg();
		
		this.uf=data.getUf();
		
		this.vcnh=data.getVcnh();
		
		this.libPamcary=data.getLibPamcary();
		
		this.dataVal=data.getDataVal();
		
		this.dirDefensiva=data.getDirDefensiva();
		
		this.aso=data.getAso();
		
		this.contract=data.getContract();
		
		this.cadastroDPA=data.getCadastroDPA();
		
		this.copyRG=data.getCopyRG();
		
		this.copyCPF=data.getCopyCPF();
		
		this.copyCNH=data.getCopyCNH();
		
		this.compResid=data.getCompResid();
		
		this.testPsicologico=data.getTestPsicologico();
		
		this.testTheorist=data.getTestTheorist();
		
		this.testPractical=data.getTestPractical();
		
		//Trein de coleta de amostra
		this.workoutOfGathering=data.getWorkoutOfGathering(); 
		
		this.termoEtilometro=data.getTermoEtilometro(); 
		
		//Prontuario da CNH
		this.prontuarioCNH=data.getProntuarioCNH();
		
		this.operationalTraining=data.getOperationalTraining();
		
		this.protocolFM300=data.getProtocolFM300();
		
		this.workContract=data.getWorkContract();
		
		this.cepa=data.getCepa();
		
		this.cepaComment=data.getCepaComment();
		
		this.course.clear();
		
		for(LevelOfServiceDriverCourseDPAData cData:data.getCourse()){
			this.course.add(new LevelOfServiceDriverCourseDPA(cData));
		}
	}
	
	public LevelOfServiceDriverDPAData getLevelOfServiceDriverDPAData(UserSessionData userSessionData){
		LevelOfServiceDriverDPAData data=new LevelOfServiceDriverDPAData();
		
		data.setId(this.id);
		
		data.setInspector(inspector);
		
		data.setDriverId(this.driverId);
		
		data.setDate(this.date);
		
		data.setStatus(this.status);
		
		data.setCorporationId(this.corporationId);
		
		data.setFirstName(firstName);
		
		data.setLastName(lastName);
		
		data.setPlant(this.plant);
		
		data.setFleet(this.fleet);
		
		Session session=HibernateUtil.currentSession();
		if(this.fleet!=null){
			DivOrgItemLevel1 divOrg=(DivOrgItemLevel1)session.load(DivOrgItemLevel1.class, this.fleet);
			
			data.setDivOrg1(divOrg.getDivOrgItemLevel2().getDivOrgItemLevel3().getId());
			
			data.setDivOrg2(divOrg.getDivOrgItemLevel2().getId());
		}
		
		data.setFunction(this.function);
		
		data.setCelular(this.celular);
		
		data.setBirthDate(this.birthDate);
		
		data.setIdade(this.idade);
		
		data.setCpf(this.cpf);
		
		data.setRg(this.rg);
		
		data.setUf(this.uf);
		
		data.setVcnh(this.vcnh);
		
		data.setLibPamcary(this.libPamcary);
		
		data.setDataVal(this.dataVal);
		
		data.setDirDefensiva(this.dirDefensiva);
		
		data.setAso(this.aso);
		
		data.setContract(this.contract);
		
		data.setCopyRG(copyRG);
		
		data.setCadastroDPA(this.cadastroDPA);
		
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
		
		data.setCepaComment(this.cepaComment);
		
		Set<LevelOfServiceDriverCourseDPAData> coursesData=new HashSet<LevelOfServiceDriverCourseDPAData>();
		
		for(LevelOfServiceDriverCourseDPA cData:course){
			coursesData.add(cData.getLevelOfServiceDriverCourseDPAData(userSessionData));
		}
		
		data.setCourse(coursesData);
		
		return data;
	}
	
	public String getRowToExport(Text messages){
		//Data( dd/mm/aaaa ) 	Inspetor	Status	ID	Nome	Sobrenome	Planta	Frota	Fun��o	N� Celular	Data nasc. ( dd/mm/aaaa ) 	CPF	RG	UF	V.CNH ( dd/mm/aaaa ) 	Lib. Pamcary	Data. Val. ( dd/mm/aaaa ) 	Dir. Defensiva ( dd/mm/aaaa ) 	ASO ( dd/mm/aaaa ) 	Contrata��o ( dd/mm/aaaa ) 	Cadastro DPA	Copia RG	Copia CPF	Copia CNH	Comp. Resid.	Teste Psicologico	Teste Teorico	Teste Pratico	Trein de coleta de amostra	Termo  Etilometro	Prontuario da CNH	Treinamento Operacional	Protocolo  (FM300)	Contrato de trabalho / CTPS Assinada	
		String row=id!=null ?id.toString():"";
		
		Session session=HibernateUtil.currentSession();
		
		persistence.driver.tables.Driver driver=(persistence.driver.tables.Driver)session.load(Driver.class, driverId);

		DivOrgItemLevel1 divOrg=driver.getDivOrg1();
		if(divOrg!=null){
			row+="\t"+divOrg.getDivOrgItemLevel2().getDivOrgItemLevel3().getResourceKey();
			
			row+="\t"+divOrg.getDivOrgItemLevel2().getResourceKey();
			
			row+="\t"+divOrg.getResourceKey();
		}
		
		
		
		row+="\t"+(date!=null?Util.formatDate(date):"");
		
		row+="\t"+(inspector!=null?inspector:"");
		
		row+="\t"+(driverId!=null?driverId:"") ;
		
		row+="\t"+ (status!=null && !status.equals("A")?"I":"A");
		
		row+="\t"+  (corporationId!=null?corporationId:"") ;
		
		row+="\t"+ (driver.getFirstName()!=null?driver.getFirstName():"")  ;
		
		row+="\t"+  (driver.getLastName()!=null?driver.getLastName():"")  ;
		
		row+="\t"+   (plant!=null?plant:"") ;
		
		//row+="\t"+  (fleet!=null?fleet:"")  ;
		
		row+="\t"+   (function!=null?(function.equals(LevelOfServiceDriverDPAData.TYPE_GENERIC_1_VALUE)?"":getHumanValue(function,messages)):"") ;
		
		row+="\t"+  (driver.getSuperviser()!=null?driver.getSuperviser():"")  ;
		
		row+="\t"+  (driver.getBirthDate()!=null?Util.formatDate(driver.getBirthDate()):"")  ;
		
		//row+="\t"+   (idade!=null?idade:"");
		
		row+="\t"+   (driver.getIdDallas()!=null?driver.getIdDallas():"");
		
		row+="\t"+  (driver.getBpsregistry()!=null?driver.getBpsregistry():"") ;
		
		row+="\t"+  (driver.getStateOrRegion()!=null?driver.getStateOrRegion():"") ;
		
		row+="\t"+ (driver.getLicenseExpiry()!=null?Util.formatDate(driver.getLicenseExpiry()):"");
		
		row+="\t"+  (libPamcary!=null?libPamcary:"") ;
		
		row+="\t"+(dataVal!=null?Util.formatDate(dataVal):"") ;
		
		row+="\t"+ (dirDefensiva!=null?Util.formatDate(dirDefensiva):"");
		
		row+="\t"+(aso!=null?Util.formatDate(aso):"") ;
		
		row+="\t"+(contract!=null?Util.formatDate(contract):"") ;
		
		row+="\t"+   (cadastroDPA!=null?getHumanValue(cadastroDPA,messages):"");
		
		row+="\t"+  (copyRG!=null?getHumanValue(copyRG,messages):"") ;
		
		row+="\t"+  (copyCPF!=null?getHumanValue(copyCPF,messages):"") ;
		
		row+="\t"+   (copyCNH!=null?getHumanValue(copyCNH,messages):"");
		
		row+="\t"+   (compResid!=null?getHumanValue(compResid,messages):"");
		
		row+="\t"+  (testPsicologico!=null?getHumanValue(testPsicologico,messages):"") ;
		
		row+="\t"+  (testTheorist!=null?getHumanValue(testTheorist,messages):"") ;
		
		row+="\t"+  (testPractical!=null?getHumanValue(testPractical,messages):"") ;
		
		//Trein de coleta de amostra
		row+="\t"+  (workoutOfGathering!=null?getHumanValue(workoutOfGathering,messages):"") ; 
		
		row+="\t"+   (termoEtilometro!=null?getHumanValue(termoEtilometro,messages):""); 
		
		//Prontuario da CNH
		row+="\t"+  (prontuarioCNH!=null?getHumanValue(prontuarioCNH,messages):"") ;
		
		row+="\t"+  (operationalTraining!=null?getHumanValue(operationalTraining,messages):"") ;
		
		row+="\t"+ (protocolFM300!=null?getHumanValue(protocolFM300,messages):"")  ;
		
		row+="\t"+  (workContract!=null?getHumanValue(workContract,messages):"") ;
		
		row+="\t"+  (cepa!=null?getHumanValue(cepa,messages):"") ;
		
		row+="\t"+  (cepaComment!=null?cepaComment:"") ;
		
		return row+"\n";
	}

	public static String getHeaderToExport(Text messages){
	     String header="ID de registro";
	     header+="\t"+messages.getText("generic.dpa.divorglevel1");
	     header+="\t"+messages.getText("generic.dpa.divorglevel2");
	     header+="\t"+messages.getText("generic.dpa.divorglevel3");
	     header+="\t"+ messages.getText("generic.activity.date")+"( "+messages.getText("generic.messages.dateformat")+" ) ";
	     header+="\t"+messages.getText("generic.dpa.levelofservicedriver.inspector");
	     header+="\t"+"ID Condutor";
	     header+="\t"+messages.getText("generic.dpa.levelofservicedriver.status");
	     header+="\t"+messages.getText("generic.dpa.levelofservicedriver.corporationid");
	     header+="\t"+messages.getText("generic.dpa.levelofservicedriver.firstName");
		 header+="\t"+messages.getText("generic.dpa.levelofservicedriver.lastName");
		 header+="\t"+messages.getText("generic.dpa.levelofservicedriver.plant");
		 //header+="\t"+messages.getText("generic.dpa.levelofservicedriver.fleet");
		 header+="\t"+messages.getText("generic.dpa.levelofservicedriver.function");
		 header+="\t"+messages.getText("generic.dpa.levelofservicedriver.celular");
		 header+="\t"+messages.getText("generic.dpa.levelofservicedriver.birthdate")+" ( "+messages.getText("generic.messages.dateformat")+" ) ";
		 header+="\t"+messages.getText("generic.dpa.levelofservicedriver.cpf");
		 header+="\t"+messages.getText("generic.dpa.levelofservicedriver.rg");
		 header+="\t"+messages.getText("generic.dpa.levelofservicedriver.uf");
		 header+="\t"+messages.getText("generic.dpa.levelofservicedriver.vnch")+ " ( "+messages.getText("generic.messages.dateformat")+" ) ";
		 header+="\t"+messages.getText("generic.dpa.levelofservicedriver.libpamcary");
		 header+="\t"+messages.getText("generic.dpa.levelofservicedriver.dataValDay") +" ( "+messages.getText("generic.messages.dateformat")+" ) ";
		 header+="\t"+messages.getText("generic.dpa.levelofservicedriver.dirdefensiva")+ " ( "+messages.getText("generic.messages.dateformat")+" ) ";
		 header+="\t"+messages.getText("generic.dpa.levelofservicedriver.aso") +" ( "+messages.getText("generic.messages.dateformat")+" ) ";
		 header+="\t"+messages.getText("generic.dpa.levelofservicedriver.contract") +" ( "+messages.getText("generic.messages.dateformat")+" ) ";
		 header+="\t"+messages.getText("generic.dpa.levelofservicedriver.cadastrodpa");
		 header+="\t"+messages.getText("generic.dpa.levelofservicedriver.copyrg");
		 header+="\t"+messages.getText("generic.dpa.levelofservicedriver.copycpf");
		 header+="\t"+messages.getText("generic.dpa.levelofservicedriver.copycnh");
		 header+="\t"+messages.getText("generic.dpa.levelofservicedriver.compresid");
		 header+="\t"+messages.getText("generic.dpa.levelofservicedriver.testpsicologico");
		 header+="\t"+messages.getText("generic.dpa.levelofservicedriver.testtheorist");
		 header+="\t"+messages.getText("generic.dpa.levelofservicedriver.testpractical");
		 header+="\t"+messages.getText("generic.dpa.levelofservicedriver.workoutofgathering");
		 header+="\t"+messages.getText("generic.dpa.levelofservicedriver.termoetilometro");
		 header+="\t"+messages.getText("generic.dpa.levelofservicedriver.prontuariocnh");
		 header+="\t"+messages.getText("generic.dpa.levelofservicedriver.operationaltraining");
		 header+="\t"+messages.getText("generic.dpa.levelofservicedriver.protocolfm300");
		 header+="\t"+messages.getText("generic.dpa.levelofservicedriver.workcontract");
		 header+="\t"+messages.getText("generic.dpa.levelofservicedriver.cepa");
		 header+="\t"+messages.getText("generic.dpa.levelofservicedriver.cepacomment");
		 
		 return header+"\n";

	}
	
	private String getHumanValue(String v,Text messages) {
		if(v==null){
			return "";
		}
		
		if(v.equals(LevelOfServiceDriverDPAData.TYPE_GENERIC_1_VALUE)){
			return messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_1_LABEL);//Sin datos
		}
		if(v.equals(LevelOfServiceDriverDPAData.TYPE_GENERIC_2_VALUE)){
			return messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_2_LABEL);//NO
		}
		if(v.equals(LevelOfServiceDriverDPAData.TYPE_DRIVER_FUNCTION_1_VALUE)){
			return  messages.getText(LevelOfServiceDriverDPAData.TYPE_DRIVER_FUNCTION_1_LABEL);
		}
		if(v.equals(LevelOfServiceDriverDPAData.TYPE_DRIVER_FUNCTION_2_VALUE)){
			return  messages.getText(LevelOfServiceDriverDPAData.TYPE_DRIVER_FUNCTION_2_LABEL);
		}
		if(v.equals(LevelOfServiceDriverDPAData.TYPE_CEPA_1_VALUE)){
			return  messages.getText(LevelOfServiceDriverDPAData.TYPE_CEPA_1_LABEL);
		}
		if(v.equals(LevelOfServiceDriverDPAData.TYPE_CEPA_2_VALUE)){
			return  messages.getText(LevelOfServiceDriverDPAData.TYPE_CEPA_2_LABEL);
		}
		if(v.equals(LevelOfServiceDriverDPAData.TYPE_CEPA_3_VALUE)){
			return  messages.getText(LevelOfServiceDriverDPAData.TYPE_CEPA_3_LABEL);
		}
		if(v.equals(LevelOfServiceDriverDPAData.TYPE_CEPA_4_VALUE)){
			return  messages.getText(LevelOfServiceDriverDPAData.TYPE_CEPA_4_LABEL);
		}
		if(v.equals(LevelOfServiceDriverDPAData.TYPE_CEPA_5_VALUE)){
			return  messages.getText(LevelOfServiceDriverDPAData.TYPE_CEPA_5_LABEL);
		}
		if(v.equals(LevelOfServiceDriverDPAData.TYPE_CEPA_6_VALUE)){
			return  messages.getText(LevelOfServiceDriverDPAData.TYPE_CEPA_6_LABEL);
		}
		
		return messages.getText(LevelOfServiceDriverDPAData.TYPE_GENERIC_3_LABEL);//SI
	}
	
	public Set<LevelOfServiceDriverCourseDPA> getCourse() {
		return course;
	}


	public void setCourse(Set<LevelOfServiceDriverCourseDPA> course) {
		this.course = course;
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


	public String getInspector() {
		return inspector;
	}

	public void setInspector(String inspector) {
		this.inspector = inspector;
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

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDriverId() {
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
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

	public String getPlant() {
		return plant;
	}

	public void setPlant(String plant) {
		this.plant = plant;
	}

	public Integer getFleet() {
		return fleet;
	}

	public void setFleet(Integer fleet) {
		this.fleet = fleet;
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
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

	public Date getVcnh() {
		return vcnh;
	}

	public void setVcnh(Date vcnh) {
		this.vcnh = vcnh;
	}

	public String getLibPamcary() {
		return libPamcary;
	}

	public void setLibPamcary(String libPamcary) {
		this.libPamcary = libPamcary;
	}

	public Date getDataVal() {
		return dataVal;
	}

	public void setDataVal(Date dataVal) {
		this.dataVal = dataVal;
	}

	public Date getDirDefensiva() {
		return dirDefensiva;
	}

	public void setDirDefensiva(Date dirDefensiva) {
		this.dirDefensiva = dirDefensiva;
	}

	public Date getAso() {
		return aso;
	}

	public void setAso(Date aso) {
		this.aso = aso;
	}

	public Date getContract() {
		return contract;
	}

	public void setContract(Date contract) {
		this.contract = contract;
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
	
	
	
	
	
}
