package communication.administration.dpa;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class LevelOfServiceDriverDPAData implements Serializable {
	
	

	private static final long serialVersionUID = 1L;

	//FUNCTION
	public static String TYPE_DRIVER_FUNCTION_1_LABEL="generic.dpa.levelofservicedriver.mo";//MOTORISTA 
	public static String TYPE_DRIVER_FUNCTION_1_VALUE="MO";
	public static String TYPE_DRIVER_FUNCTION_2_LABEL="generic.dpa.levelofservicedriver.ma";//MANOBRISTA 
	public static String TYPE_DRIVER_FUNCTION_2_VALUE="MA";

	//FUNCTION
	public static String TYPE_CEPA_1_LABEL="generic.dpa.levelofservicedriver.cepa1";//REUNIÃO  
	public static String TYPE_CEPA_1_VALUE="C1";
	public static String TYPE_CEPA_2_LABEL="generic.dpa.levelofservicedriver.cepa2";//BLITS 
	public static String TYPE_CEPA_2_VALUE="C2";
	public static String TYPE_CEPA_3_LABEL="generic.dpa.levelofservicedriver.cepa3";// DSRS  
	public static String TYPE_CEPA_3_VALUE="C3";
	public static String TYPE_CEPA_4_LABEL="generic.dpa.levelofservicedriver.cepa4";//PALESTRA 
	public static String TYPE_CEPA_4_VALUE="C4";
	public static String TYPE_CEPA_5_LABEL="generic.dpa.levelofservicedriver.cepa5";//TREINAMENTO  
	public static String TYPE_CEPA_5_VALUE="C5";
	public static String TYPE_CEPA_6_LABEL="generic.dpa.levelofservicedriver.cepa6";//Outros 
	public static String TYPE_CEPA_6_VALUE="C6";
	
	
	
	//GENERAL
	public static String TYPE_GENERIC_1_VALUE="1";
	public static String TYPE_GENERIC_2_VALUE="2";
	public static String TYPE_GENERIC_3_VALUE="3";
	public static String TYPE_GENERIC_1_LABEL="generic.dpa.levelofservicedriver.nodata";
	public static String TYPE_GENERIC_2_LABEL="generic.dpa.levelofservicedriver.no";
	public static String TYPE_GENERIC_3_LABEL="generic.dpa.levelofservicedriver.ok";
	
	
	private Integer id;
	
	private String inspector;
	
	private Integer driverId;
	
	private Date date=new Date();
	
	private String status;
	
	private String corporationId;
	
	private String firstName;
	
	private String lastName;
	
	private String plant;
	
	private Integer divOrg1;
	
	private Integer divOrg2;
	
	private Integer fleet;//divOrg3, hoja
	
	private String function=LevelOfServiceDriverDPAData.TYPE_GENERIC_1_VALUE;
	
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
	private String cepaComment;
	private Set<LevelOfServiceDriverCourseDPAData> course=new HashSet<LevelOfServiceDriverCourseDPAData>();
	
	
	
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
	
	public Set<LevelOfServiceDriverCourseDPAData> getCourse() {
		return course;
	}

	public void setCourse(Set<LevelOfServiceDriverCourseDPAData> course) {
		this.course = course;
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

	public String getInspector() {
		return inspector;
	}

	public void setInspector(String inspector) {
		this.inspector = inspector;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDriverId() {
		if(driverId!=null){
			return driverId;
		}else{
			return 0;
		}
	}

	public void setDriverId(Integer driverId) {
		if( driverId!=null){
			this.driverId = driverId;
		}
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

	 

	public String getCopyRG() {
		return copyRG;
	}

	public void setCopyRG(String copyRG) {
		this.copyRG = copyRG;
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
		
	

}
