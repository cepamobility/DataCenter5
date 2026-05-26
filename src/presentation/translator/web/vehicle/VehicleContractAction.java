/*
 * Created on 24-feb-2006
 */
package presentation.translator.web.vehicle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.jfree.report.JFreeReportBoot;

import presentation.report.ReportFactory;
import presentation.report.ReportUtil;
import presentation.report.vehicle.ContractComodatoOwnFleetReportFactory;
import presentation.report.vehicle.ContractDevolutionReportFactory;
import presentation.report.vehicle.ContractDistratoReportFactory;
import presentation.report.vehicle.ContractResponsabilityReportFactory;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.alert.AlertConstants;
import communication.datatype.tables.SexDataType;
import communication.datatype.tables.VehicleContractDataType;
import communication.driver.tables.DriverData;
import communication.language.Text;
import communication.security.SecurityConstants;
import communication.vehicle.report.ContractComodatoDataSource;
import communication.vehicle.report.ContractReportData;
import communication.vehicle.tables.VehicleContractData;
import communication.vehicle.tables.VehicleData;


public class VehicleContractAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        
        DynaActionForm reportForm = (DynaActionForm)form;
        
        // TODO: Validar Fecha
        String day = reportForm.getString("day");
        String month = reportForm.getString("month");
        String year = reportForm.getString("year");
        Date date = Util.getDate(day, month, year);
        String message = null;
        if ( date == null){
        	message =  "generic.vehicle.vehicledata.invalidDate";	        	
        }
        
    	JFreeReportBoot.getInstance().start();

     
	    String data = reportForm.getString("day")+"/"+
	    		reportForm.getString("month")+"/"+
	    		reportForm.getString("year");
	    String vehicleId = reportForm.getString("vehicle");
	    // int type = Integer.parseInt(reportForm.getString("type"));
	    
	    String type = (String)reportForm.getString("type");
	
	    VehicleData vehicle = sys.getVehicleReportFacade().findVehicleById(
	    		new Integer(vehicleId), sys.getUserSessionData());
	    
	    // Guardar el Contrato, para saber con que fecha se hizo el contrato
	    // y tener un registro de contratos hechos y no hechos
	    /*
	    String contractType = "";
	    switch(type) {
	    	case 1: contractType = VehicleContractDataType.COMODATO; break;
	    	case 2: contractType = VehicleContractDataType.DISTRATO; break;
	    	case 3: contractType = VehicleContractDataType.RESPONSABILITY; break;
	    	case 4: contractType = VehicleContractDataType.DEVOLUTION; break;
	    }
	    */
	    VehicleContractData contract = new VehicleContractData(new Integer(vehicleId),date,new VehicleContractDataType(type),false);
	    sys.getVehicleABMFacade().createVehicleContract(new Integer(vehicleId),contract,sys.getUserSessionData());
	    
	    // Genero el reporte de contrato:
	    ContractReportData contractData = new ContractReportData(sys,getData(type));
	    
	    if (type.equalsIgnoreCase(VehicleContractDataType.COMODATO)){
		    String contractName = SecurityConstants.DATACENTER5_WAR_DIRECTORY+"allergan/contracts/Comodato_"+vehicle.getMatricula().replaceAll(" ","_")+"_"+Util.formatDate(date)+".pdf";
    		File f = new File(contractName);	
    		FileOutputStream out = new FileOutputStream(f);

    		Text messages = sys.getText();
	        HashMap<String,Object> parameters = new HashMap<String,Object>();
	        parameters.put("titulo", "CONTRATO DE COMODATO");
	        parameters.put("texto0", getComodatoText0(vehicle.getDriver()));
	        parameters.put("texto1", getComodatoText1(vehicle.getDriver()));
	        
	        parameters.put("texto2", "O COMODATÃ�RIO(A)  recebe da COMODANTE, o veÃ­culo jÃ¡ mencionado, passando o presente contrato a vigorar com as seguintes clÃ¡usulas e condiÃ§Ãµes:");
	    	parameters.put("tradetxt", messages.getText("generic.vehicle.vehicledata.trade"));
	    	parameters.put("trade", vehicle.getTrade().getResourceBundleId());
	    	parameters.put("platetxt", messages.getText("generic.vehicle.vehicledata.registrationNumber"));
	    	parameters.put("plate", vehicle.getMatricula());
	    	parameters.put("yeartxt", messages.getText("generic.vehicle.vehicledata.year"));
	    	parameters.put("year", String.valueOf(vehicle.getYear()));
	    	parameters.put("chassistxt", messages.getText("generic.vehicle.vehicledata.chasisNumber"));
	    	parameters.put("chassis", vehicle.getChasisNumber());
	    	parameters.put("modeltxt", messages.getText("generic.vehicle.vehicledata.model"));
	    	parameters.put("model", vehicle.getModel());
	    	parameters.put("colortxt", messages.getText("generic.vehicle.vehicledata.colour"));
	    	parameters.put("color", vehicle.getColour());
	    	parameters.put("modelFactorytxt", messages.getText("Modelo Fab"));
	    	parameters.put("modelFactory", "");
	    	parameters.put("renavamtxt", "RENAVAM");
	    	parameters.put("renavam", vehicle.getPermissionNumber());
	    	parameters.put("accesoriestxt", messages.getText("AcessÃ³rios"));
	    	parameters.put("accesories", "");
	    	parameters.put("texto3", "E por estarem justos e contratados, assinam o presente em duas vias de igual teor, para o mesmo efeito, diante das testemunhas abaixo mencionadas.");
	    	parameters.put("currentDate", "SÃ£o Paulo, "+data);
	    	parameters.put("declaration", "Declara estar Ciente e De Acordo com a PolÃ­tica de VeÃ­culos:");
	    	parameters.put("allergan", "Allergan Produtos FarmacÃªuticos Ltda");
	    	parameters.put("witness", "Testemunha");
	        
	    	//JasperCompileManager.compileReportToFile(AlertConstants.TEMP_DIRECTORY_ALLERGAN+"jasper/comodato.jrxml", AlertConstants.TEMP_DIRECTORY_ALLERGAN+"jasper/comodato.jasper");
	        FileInputStream input2 = new FileInputStream(new File(AlertConstants.TEMP_DIRECTORY_ALLERGAN+"jasper/comodato.jasper"));
	        
	        JasperReport jasperReport = (JasperReport)JRLoader.loadObject(input2);
	        response.setHeader("Content-Type", "application/pdf");
	   	 	response.setHeader("Content-Disposition", "inline; filename=\"Comodato.pdf");
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new ContractComodatoDataSource());
	        
	        JRPdfExporter exporter = new JRPdfExporter();
	        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
	        exporter.exportReport();       
	        out.flush();
	        out.close();

	    }else {
		    ReportFactory factory = null;
		    String contractType = "";
		    if (type.equalsIgnoreCase(VehicleContractDataType.DISTRATO)){
		    	factory = new ContractDistratoReportFactory("contract",contractData,reportForm,sys,vehicle,data);
		    	contractType = "Distrato";
		    }else if (type.equalsIgnoreCase(VehicleContractDataType.RESPONSABILITY)){
		    	factory = new ContractResponsabilityReportFactory("contract",contractData,reportForm,sys,vehicle,data);
		    	contractType = "Responsability";
		    }else if (type.equalsIgnoreCase(VehicleContractDataType.DEVOLUTION)){
		    	factory = new ContractDevolutionReportFactory("contract",contractData,reportForm,sys,vehicle,data);
		    	contractType = "Devolution";
		    }else if (type.equalsIgnoreCase(VehicleContractDataType.COMODATO_OWNFLEET)){
		    	factory = new ContractComodatoOwnFleetReportFactory("contract",contractData,reportForm,sys,vehicle,data);
		    	contractType = "ComodatoFrotaPropia";
		    }
		    String contractName = SecurityConstants.DATACENTER5_WAR_DIRECTORY+"allergan/contracts/"+contractType+"_"+vehicle.getMatricula().replaceAll(" ","_")+"_"+Util.formatDate(date)+".pdf";
		
		    new ReportUtil().executeReport(mapping,response,factory.createReport(),"PDFFile",session,contractName,true);
	    }
	    sys.getUserSessionData().setValue(contract);
	    sys.getUserSessionData().setUrl("vehicle/viewContract.jsp");
	    return mapping.findForward("next");
    }

    private Object getComodatoText0(DriverData driver) {
		String text = "\t\tPelo presente instrumento particular, de um lado ALLERGAN PRODUTOS FARMACÃŠUTICOS LTDA., com sede Ã  Av. Dr. Cardoso de Melo, 1855, 13Âº andar â€“ Bloco I -, Vila OlÃ­mpia, Estado de SÃ£o Paulo - SP, inscrita no CGC MF sob o nÂº 43.426.626/0001-77, neste ato representada pelos infra-assinados doravante denominada COMODANTE e de outro a ";
		boolean female = driver.getSex().getResourceBundleId().equals(SexDataType.FEMALE);
		text += (female) ? "Sra. " : "Sr. ";
		text += driver.getFirstName()+" "+driver.getLastName();
		text += (female) ? ", residente e domiciliada Ã " : ", residente e domiciliado Ã  ";
		text += driver.getPersonalAddress()+" CEP --- ";
		text += (female) ? "portadora da cÃ©lula de identidade RG nÂº " : "portador da cÃ©lula de identidade RG nÂº ";
		text += driver.getFederativeEntity()+", CPF nÂº "+driver.getLicenseCoverage()+" e da CNH nÂº "+driver.getLicenseNumber()+" categoria "+driver.getLicenseCategory()+" doravante denominada ";
		text += (female) ? " COMODATÃ�RIA " : " COMODATÃ�RIO ";
		text += ", tem entre si justo e contratado o seguinte:";
		return text;
	}

    private Object getComodatoText1(DriverData driver) {
		boolean female = driver.getSex().getResourceBundleId().equals(SexDataType.FEMALE);
		String text = (female) ? "O COMODATÃ�RIA" : "O COMODATÃ�RIO";
		text += ", na qualidade de funcionÃ¡rio da COMODANTE, e exercendo o cargo de ";
		text += (driver.getAuthorizateWho() == null) ? "---" : driver.getAuthorizateWho();
		text += ", para o completo desempenho de suas atribuiÃ§Ãµes recebe, neste ato, da COMODANTE, um veÃ­culo com as seguintes caracterÃ­sticas:";
		return text;
	}

    /**
     * 
     */
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }

    public String[][] getData(String  type) {
    	if(type.equalsIgnoreCase(VehicleContractDataType.COMODATO)) {
    		String[][] data = {{"1Âª) 	A COMODANTE  entrega Ã  COMODATÃ�RIA o veÃ­culo acima descrito, devidamente licenciado e lacrado, com todos os impostos e taxas pagos para livre circulaÃ§Ã£o em todo TerritÃ³rio Nacional, inclusive com apÃ³lice de seguro com cobertura para danos materiais e pessoais, nos termos da polÃ­tica interna da COMODANTE."},
    			{"2Âª) 	Destina-se o aludido veÃ­culo a que a COMODATÃ�RIA possa exercer suas atividades para a COMODANTE, de acordo com as normas internas."},
    			{"3Âª) 	Ã‰ permitido Ã  COMODATÃ�RIA a utilizaÃ§Ã£o do aludido bem, de acordo com as condiÃ§Ãµes constantes do presente contrato de comodato."},
    			{"4Âª) 	Ã‰ proibida a conduÃ§Ã£o do veÃ­culo por terceiros, salvo por outro funcionÃ¡rio da COMODANTE e quando a trabalho desta, desde que devidamente habilitado e autorizado pela COMODATÃ�RIA."},
    			{"5Âª) 	A COMODATÃ�RIA serÃ¡ reembolsada pelas despesas de utilizaÃ§Ã£o do veÃ­culo de acordo com as normas internas da COMODANTE."},
    			{"6Âª) 	No caso de ocorrer acidentes com o veÃ­culo objeto deste contrato, a COMODANTE pagarÃ¡ os custos de reparo, desde que nÃ£o seja comprovada culpa da COMODATÃ�RIA, situaÃ§Ã£o na qual uma vez configurada implicarÃ¡ Ã¡ COMODATÃ�RIA o pagamento de 50% do custo da franquia do seguro, ou a outros critÃ©rios estipulados em normas internas da COMODANTE ."},
    			{"7Âª) 	Havendo perda total ou furto do veÃ­culo, a COMODANTE providenciarÃ¡ a sua substituiÃ§Ã£o. Se for comprovada culpa da COMODATÃ�RIA, este arcarÃ¡ com 50% custos incorridos pelo COMODANTE."},
    			{"8Âª) 	Os consertos que nÃ£o sejam de carÃ¡ter de urgÃªncia, bem como reformas totais ou parciais, necessitarÃ£o de prÃ©via autorizaÃ§Ã£o da unidade responsÃ¡vel pela administraÃ§Ã£o da frota para que sejam executados."},
    			{"9Âª)	Mensalmente, a COMODATÃ�RIA deverÃ¡ apresentar ao seu superior imediato, RelatÃ³rio de Despesas contendo as despesas com o veÃ­culo."},
    			{"10Âª)	NÃ£o serÃ¡ permitido Ã  instalaÃ§Ã£o de acessÃ³rios / equipamentos adicionais que alterem as caracterÃ­sticas originais do veÃ­culo."},
    			{"11Âª)	Havendo alteraÃ§Ã£o de cargo para o qual a empresa nÃ£o conceda veÃ­culo, ou, se durante a  vigÃªncia deste contrato houver rescisÃ£o de contrato de trabalho entre as partes, a COMODATÃ�RIA deverÃ¡ devolver o veÃ­culo recebido da COMODANTE, em perfeitas condiÃ§Ãµes de uso, salvo o desgaste natural das peÃ§as e componentes."},
    			{"12Âª) 	Em se configurando a mÃ¡ utilizaÃ§Ã£o do veÃ­culo pela COMODATÃ�RIA, poderÃ¡ a COMODANTE  pleitear ressarcimento das despesas com o conserto das partes do veÃ­culo diretamente afetadas por esta condiÃ§Ã£o, ou sem qualquer aviso prÃ©vio ou notificaÃ§Ã£o, rescindir  o presente contrato e reaver o veÃ­culo, sem qualquer ressarcimento ou indenizaÃ§Ã£o Ã  COMODATÃ�RIA."},
    			{"13Âª) 	Reserva-se Ã  COMODANTE, o direito de vistoriar o veÃ­culo, a qualquer tempo, em data fixada pela unidade responsÃ¡vel pela administraÃ§Ã£o da frota, no sentido de verificar se estÃ£o sendo cumpridas as clÃ¡usulas deste, no tocante Ã  conservaÃ§Ã£o do veÃ­culo, inclusive a conferÃªncia de quilometragem informada no relatÃ³rio com a  registrada no hodÃ´metro do veÃ­culo."},
    			{"14Âª) 	Definida a venda do veÃ­culo pela COMODANTE, terÃ¡ a COMODATÃ�RIA preferÃªncia  sobre a sua aquisiÃ§Ã£o de conformidade com o procedimento vigente na Ã©poca."},
    			{"15Âª)	Compromete-se a COMODATÃ�RIA a conduzir o veÃ­culo dentro das leis de trÃ¢nsito arcando, com multas por infraÃ§Ãµes que forem aplicadas pelas autoridades competentes, bem como a ressarcir a COMODANTE do valor das despesas por ela quitadas, atravÃ©s de desconto em folha de pagamento, estando sujeito a COMODATÃ�RIA ao nÃ£o reembolso das despesas descritas na clausula 5Âª atÃ© sua quitaÃ§Ã£o, incluindo-se os danos com acidentes de trÃ¢nsito.  "},
    			{"Ãšnico - 	No caso de acidente, roubo ou furto, a COMODATÃ�RIA deverÃ¡ providenciar junto aos Ã³rgÃ£os locais competentes toda documentaÃ§Ã£o necessÃ¡ria para o processo de reparaÃ§Ã£o."},
    			{"16Âª)	CorrerÃ£o por conta da COMODANTE  as despesas com licenciamento e seguro do veÃ­culo."},
    			{"17Âª) 	O presente contrato vigorarÃ¡ pelo prazo de 36 meses ou 100.000 km rodados, nÃ£o sendo permitida a substituiÃ§Ã£o do veÃ­culo por outro, salvo se a COMODATÃ�RIA vier a exercer outra atividade para a COMODANTE que, de acordo com as normas internas, lhe destine um veÃ­culo com outras caracterÃ­sticas."},
    			{"18Âª)	PoderÃ¡ a COMODANTE, a qualquer momento e a seu critÃ©rio rescindir o presente Contrato de Comodato, mediante simples comunicaÃ§Ã£o Ã  COMODATÃ�RIA."},
    			{"19Âª) 	 A COMODATÃ�RIA obriga-se a observar as normas que regem o presente contrato ciente de que a sua nÃ£o observÃ¢ncia acarretarÃ¡ em sanÃ§Ãµes previstas de acordo com as normas internas."},
    			{"20Âª) A COMODATÃ�RIA recebe nesta data a cÃ³pia da polÃ­tica de veÃ­culos vigente e se compromete a cumpri-la em sua totalidade. Sempre que uma nova polÃ­tica entrar em vigor, esta deverÃ¡ ser entregue pela COMODANTE Ã  COMODATÃ�RIA."},
    			{"21Âª) 	As partes elegem o Foro da Comarca de SÃ£o Paulo para dirimir quaisquer dÃºvidas da interpretaÃ§Ã£o do presente contrato."}};
    		return data;
    	}else if(type.equalsIgnoreCase(VehicleContractDataType.COMODATO_OWNFLEET)) {
    		String[][] data = {{"1Âª) 	A COMODANTE  entrega ao COMODATÃ�RIO o veÃ­culo acima descrito, devidamente licenciado e lacrado, com todos os impostos e taxas pagos para livre circulaÃ§Ã£o em todo TerritÃ³rio Nacional, inclusive com apÃ³lice de seguro com cobertura para danos materiais e pessoais, nos termos da polÃ­tica interna da COMODANTE ." +
    				"\n\n2Âª) 	Destina-se o aludido veÃ­culo a que o COMODATÃ�RIO possa exercer suas atividades para a COMODANTE, de acordo com as normas internas."},
    			{"3Âª) 	Ã‰ permitido ao COMODATÃ�RIO a utilizaÃ§Ã£o do aludido bem, de acordo com as condiÃ§Ãµes constantes do presente contrato de comodato." +
    					"\n\n4Âª) 	Ã‰ proibida a conduÃ§Ã£o do veÃ­culo por terceiros, salvo por outro funcionÃ¡rio da COMODANTE quando a trabalho desta e devidamente autorizado pelo COMODATÃ�RIO ou eventualmente pelo cÃ´njuge do COMODATÃ�RIO desde que habilitado e que nÃ£o o utilize como meio principal de transporte."},
    			{"5Âª) 	O COMODATÃ�RIO serÃ¡ reembolsado pelas despesas de utilizaÃ§Ã£o do veÃ­culo de acordo com as normas internas da COMODANTE.\" "},
    			{"6Âª) 	No caso de ocorrer acidentes com o veÃ­culo objeto deste contrato, a COMODANTE  pagarÃ¡ os custos de reparo, desde que nÃ£o seja comprovada culpa do COMODATÃ�RIO, situaÃ§Ã£o na qual uma vez configurada implicarÃ¡ ao COMODATÃ�RIO o pagamento integral do custo da franquia do seguro, ou o valor total para reparo do veÃ­culo caso a franquia estipulada pelo seguro nÃ£o seja atingida ou ainda a outros critÃ©rios estipulados em normas internas da COMODANTE ."},
    			{"7Âª) 	Havendo perda total ou furto do veÃ­culo, a COMODANTE  providenciarÃ¡ a sua substituiÃ§Ã£o.\n\n8Âª) 	Os consertos que nÃ£o sejam de carÃ¡ter de urgÃªncia, bem como reformas totais ou parciais, necessitarÃ£o de prÃ©via autorizaÃ§Ã£o da unidade responsÃ¡vel  pela administraÃ§Ã£o da frota para que sejam executados."},
    			{"9Âª)	Mensalmente, o COMODATÃ�RIO deverÃ¡ apresentar ao seu superior imediato, RelatÃ³rio de Despesas contendo as despesas com o veÃ­culo.\n\n10Âª)	NÃ£o serÃ¡ permitido a instalaÃ§Ã£o de acessÃ³rios / equipamentos adicionais que alterem as caracterÃ­sticas originais do veÃ­culo."},
    			{"11Âª)	Havendo alteraÃ§Ã£o de cargo para o qual a empresa nÃ£o conceda veÃ­culo, ou, se durante a  vigÃªncia deste contrato houver rescisÃ£o de contrato de trabalho entre as partes, o COMODATÃ�RIO deverÃ¡ devolver o veÃ­culo recebido da COMODANTE , em perfeitas condiÃ§Ãµes de uso, salvo o desgaste natural das peÃ§as e componentes ."},
    			{"12Âª) 	Em se configurando a mÃ¡ utilizaÃ§Ã£o do veÃ­culo pelo COMODATÃ�RIO, poderÃ¡ a COMODANTE  pleitear ressarcimento das despesas com o conserto das partes do veÃ­culo diretamente afetadas por esta condiÃ§Ã£o,ou sem qualquer aviso prÃ©vio ou notificaÃ§Ã£o, rescindir  o presente contrato e reaver o veÃ­culo, sem qualquer ressarcimento ou indenizaÃ§Ã£o ao COMODATÃ�RIO."},
    			{"13Âª) 	Reserva-se Ã  COMODANTE, o direito de vistoriar o veÃ­culo, a qualquer tempo, em data fixada pela unidade responsÃ¡vel pela administraÃ§Ã£o da frota, no sentido de verificar se estÃ£o sendo cumpridas as clÃ¡usulas deste, no tocante Ã  conservaÃ§Ã£o do veÃ­culo, inclusive a conferÃªncia de quilometragem informada no relatÃ³rio com a  registrada no hodÃ´metro do veÃ­culo.\n\n14Âª) 	Definida a venda do veÃ­culo pela COMODANTE, terÃ¡ o COMODATÃ�RIO preferÃªncia  sobre a sua aquisiÃ§Ã£o de conformidade com o procedimento vigente na Ã©poca."},
    			{"15Âª)	Compromete-se o COMODATÃ�RIO a conduzir o veÃ­culo dentro das leis de trÃ¢nsito arcando, com multas por infraÃ§Ãµes que forem aplicadas pelas autoridades competentes, bem como a ressarcir a COMODANTE do valor das despesas por ela quitadas, atravÃ©s de desconto em folha de pagamento, estando sujeita o COMODATÃ�RIO ao nÃ£o reembolso das despesas descritas na clausula 5Âª atÃ© sua quitaÃ§Ã£o, incluindo-se os danos com acidentes de trÃ¢nsito.\n\tÃšnico - 	No caso de acidente, roubo ou furto, o COMODATÃ�RIO deverÃ¡ providenciar junto aos Ã³rgÃ£os locais competentes toda documentaÃ§Ã£o necessÃ¡ria para o processo de reparaÃ§Ã£o."},
    			{"16Âª)	CorrerÃ£o por conta da COMODANTE  as despesas com licenciamento e seguro do veÃ­culo.\n\n17Âª) 	O presente contrato vigorarÃ¡ pelo prazo de 36 meses ou 100.000 km rodados, nÃ£o sendo permitida a substituiÃ§Ã£o do veÃ­culo por outro, salvo se o COMODATÃ�RIO vier a exercer outra atividade para a COMODANTE que, de acordo com as normas internas, lhe destine um veÃ­culo com outras caracterÃ­sticas."},
    			{"18Âª)	PoderÃ¡ a COMODANTE rescindir o presente se o COMODATÃ�RIO vier a exercer outra atividade para a COMODANTE, nos termos das normas internas em vigor.\n\n19Âª) 	O COMODATÃ�RIO  obriga-se a observar as normas que regem o presente contrato ciente de que a sua nÃ£o observÃ¢ncia acarretarÃ¡ em sanÃ§Ãµes previstas de acordo com as normas internas."},
    			{"20Âª) 	As partes elegem o Foro da Comarca de SÃ£o Paulo para dirimir quaisquer dÃºvidas da interpretaÃ§Ã£o do presente contrato."}};
    		return data;
    	}else if(type.equalsIgnoreCase(VehicleContractDataType.RESPONSABILITY)) {
    		String[][] data = {{"2.7 SerÃ¡ permitida a instalaÃ§Ã£o de acessÃ³rios no carro-frota desde que sob as expensas do FUNCIONÃ�RIO e desde que nÃ£o alterem as caracterÃ­sticas originais do carro-frota. Na hipÃ³tese de instalaÃ§Ã£o de algum equipamento ou acessÃ³rio que nÃ£o puder ser removido quando da devoluÃ§Ã£o do carro-frota Ã  LOCADORA, quer por encerramento de contrato ou desligamento do FUNCIONÃ�RIO, o mesmo nÃ£o serÃ¡ reembolsado pela instalaÃ§Ã£o desses acessÃ³rios."},
    			{"2.8 Na hipÃ³tese dos acessÃ³rios instalados sob as expensas do FUNCIONÃ�RIO serem removidos e alterarem as caracterÃ­sticas originais do carro-frota, o FUNCIONÃ�RIO arcarÃ¡ com os custos de reparo na parte diretamente afetada por esta condiÃ§Ã£o. Fica desde jÃ¡ autorizado pelo FUNCIONÃ�RIO o desconto do referido valor na folha de pagamento (devoluÃ§Ã£o por encerramento de contrato) ou na rescisÃ£o (no caso de desligamento ou pedido de demissÃ£o)."},
    			{"2.9 Em caso de colisÃ£o decorrente de dolo ou culpa do usuÃ¡rio com avarias no carro-frota que a critÃ©rio da LOCADORA puderem ser reparadas, o FUNCIONÃ�RIO serÃ¡ responsÃ¡vel pelo pagamento integral do conserto, atravÃ©s do desconto em sua folha de pagamento, atÃ© o limite mÃ¡ximo de 5% (cinco por cento) do valor do mesmo carro novo (zero quilometro), conforme a respectiva tabela de revendedores vigente na data do pagamento."},
    			{"2.10 Em caso de colisÃ£o decorrente de dolo ou culpa do usuÃ¡rio que acarrete a perda total do carro-frota, o FUNCIONÃ�RIO serÃ¡ responsÃ¡vel pelo pagamento, atravÃ©s do desconto em sua folha de pagamento, do valor equivalente a 10% (dez por cento) do valor do mesmo carro novo (zero quilometro) conforme a respectiva tabela de revendedores vigente na data do pagamento."},
    			{"2.11 Na hipÃ³tese de furto do carro-frota em que se caracterize a negligÃªncia quanto Ã  guarda e utilizaÃ§Ã£o do carro-frota em relaÃ§Ã£o Ã s instruÃ§Ãµes recebidas da COMPANHIA atravÃ©s da PolÃ­tica de Carros-Frota â€“ Frota Terceirizada, o FUNCIONÃ�RIO serÃ¡ advertido formalmente e serÃ¡ responsÃ¡vel pelo pagamento, atravÃ©s do desconto em sua folha de pagamento, do valor equivalente a 10% (dez por cento) do valor do mesmo carro novo (zero quilometro) conforme a respectiva tabela de revendedores vigente na data do pagamento."},
    			{"2.12 Na hipÃ³tese do carro-frota titular nÃ£o estar disponÃ­vel, por se encontrar em serviÃ§os de manutenÃ§Ã£o ou reparos, o FUNCIONÃ�RIO terÃ¡ a sua disposiÃ§Ã£o um outro carro-frota fornecido pela LOCADORA, respeitando-se o tempo mÃ­nimo de quatro horas para execuÃ§Ã£o de reparos conforme estabelecido em contrato entre a LOCADORA e a ALLERGAN, aplicando-se tambÃ©m a este carro-frota todas as regras estabelecidas no presente Termo. O carro-frota reserva quando utilizado, deverÃ¡ ser devolvido abastecido. Na hipÃ³tese de devoluÃ§Ã£o sem abastecimento o usuÃ¡rio arcarÃ¡ com o acrÃ©scimo de 15% do valor do combustÃ­vel utilizado, cobrado pela Locadora, sendo este valor descontado de seus vencimentos."},
    			{"2.13 O FUNCIONÃ�RIO arcarÃ¡ com os custos INTEGRAIS com reparos, reposiÃ§Ãµes de carros-frota e indenizaÃ§Ãµes por danos materiais e/ou pessoais, nÃ£o somente em favor da locadora como tambÃ©m em favor de terceiros no caso de ele ter agido com dolo ou culpa gravÃ­ssima na guarda, conduÃ§Ã£o ou conservaÃ§Ã£o do carro-frota, ficando o mesmo totalmente responsÃ¡vel pelas conseqÃ¼Ãªncias de tais condutas, devendo suportar e reembolsar Ã  Allergan e/ou a LOCADORA por todas as despesas que estas vierem a suportar em decorrÃªncia de tais atos."}};
    		return data;

    	}
    	return new String[0][0];
    }
} 
    


