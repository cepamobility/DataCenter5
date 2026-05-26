package presentation.translator.web.vehicle;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;

import presentation.report.ReportFactory;
import presentation.report.ReportUtil;
import presentation.report.vehicle.ContractComodatoOwnFleetReportFactory;
import presentation.report.vehicle.ContractComodatoReportFactory;
import presentation.report.vehicle.ContractDevolutionReportFactory;
import presentation.report.vehicle.ContractDistratoReportFactory;
import presentation.report.vehicle.ContractResponsabilityReportFactory;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.datatype.tables.VehicleContractDataType;
import communication.vehicle.report.ContractReportData;
import communication.vehicle.tables.VehicleContractData;
import communication.vehicle.tables.VehicleData;


public class VehicleListContractAction extends AbstractAction {

	/**
	 * 
	 */
    public ActionForward action(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        
        DynaActionForm reportForm = (DynaActionForm)form;
        
        JFreeReportBoot jfree = JFreeReportBoot.getInstance(); 
        try{
        	jfree.start();
        }catch (Throwable e){
        	jfree.start();
        }
        Collection<JFreeReport> reports = new LinkedList<JFreeReport>();
       
        String data = null;
        Date date = null;
        if(Integer.parseInt(reportForm.getString("contractDateType")) == 1) {
	        String day = reportForm.getString("day");
	        String month = reportForm.getString("month");
	        String year = reportForm.getString("year");
	        date = Util.getDate(day, month, year);
	       
		    data = day + "/" + month + "/" + year;
        }
        
	    int type = Integer.parseInt(reportForm.getString("type"));
	    Collection vehicles = Util.stringToCollection(reportForm.getString("list"));
	   
	    if(vehicles != null && vehicles.size() > 0) {
	    	Iterator it = vehicles.iterator();
	    	while(it.hasNext()) {
	    		JFreeReport report = getReport(sys,(String)it.next(),type,data,reportForm,date); 
	    		reports.add(report);
	    	}
        	return new ReportUtil().executeReport(mapping,response,reports,"PDF",session,"vehicle");
	    }
	    return null;
    }
    


    /**
     * 
     * @param sys
     * @param vehicleId
     * @param type
     * @param data
     * @param reportForm
     * @return
     * @throws Exception
     */
    public JFreeReport getReport(SystemAccess sys, String vehicleId, int type,String data,
    		DynaActionForm reportForm, Date date) throws Exception {

    	
	    VehicleData vehicle = sys.getVehicleReportFacade().
	    	findVehicleById(new Integer(vehicleId), sys.getUserSessionData());
	    if(data == null) {
	    	date = (vehicle.getPurchaseDate() == null) ? Calendar.getInstance().getTime() : vehicle.getPurchaseDate();
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(date);
	        data = calendar.get(Calendar.DAY_OF_MONTH)+"/"+(calendar.get(Calendar.MONTH)+1)+"/"+calendar.get(Calendar.YEAR);
	    }
	    // Guardar el Contrato, para saber con que fecha se hizo el contrato
	    // y tener un registro de contratos hechos y no hechos
	    String contractType = "";
	    switch(type) {
	    	case 1: contractType = VehicleContractDataType.COMODATO; break;
	    	case 2: contractType = VehicleContractDataType.DISTRATO; break;
	    	case 3: contractType = VehicleContractDataType.RESPONSABILITY; break;
	    	case 4: contractType = VehicleContractDataType.DEVOLUTION; break;
	    	case 5: contractType = VehicleContractDataType.COMODATO_OWNFLEET; break;
	    }
	    VehicleContractData contract = new VehicleContractData(
	    		new Integer(vehicleId),date,	new VehicleContractDataType(contractType),false);
	    sys.getVehicleABMFacade().createVehicleContract(
	    		new Integer(vehicleId),contract,sys.getUserSessionData());
	    
	    // Genero los reportes de los contratos:
	    ContractReportData contractData = new ContractReportData(sys,getData(type));
	    ReportFactory factory = null;
	    switch(type) {
	    	case 1:
		    	factory = new ContractComodatoReportFactory("contract",contractData,reportForm,sys,vehicle,data);
	    		break;
	    	case 2:
		    	factory = new ContractDistratoReportFactory("contract",contractData,reportForm,sys,vehicle,data);
	    		break;
	    	case 3:
		    	factory = new ContractResponsabilityReportFactory("contract",contractData,reportForm,sys,vehicle,data);
	    		break;
	    	case 4:
		    	factory = new ContractDevolutionReportFactory("contract",contractData,reportForm,sys,vehicle,data);
	    		break;
	    	case 5:
		    	factory = new ContractComodatoOwnFleetReportFactory("contract",contractData,reportForm,sys,vehicle,data);
	    		break;
	    }
	    return factory.createReport();
    }
    
    /**
     * 
     */
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }

    /**
     * 
     * @param type
     * @return
     */
    public String[][] getData(int type) {
    	if(type == 1) {
    		String[][] data = {{"1ª) 	A COMODANTE  entrega à COMODATÁRIA o veículo acima descrito, devidamente licenciado e lacrado, com todos os impostos e taxas pagos para livre circulação em todo Território Nacional, inclusive com apólice de seguro com cobertura para danos materiais e pessoais, nos termos da política interna da COMODANTE."},
    			{"2ª) 	Destina-se o aludido veículo a que a COMODATÁRIA possa exercer suas atividades para a COMODANTE, de acordo com as normas internas."},
    			{"3ª) 	É permitido à COMODATÁRIA a utilização do aludido bem, de acordo com as condições constantes do presente contrato de comodato."},
    			{"4ª) 	É proibida a condução do veículo por terceiros, salvo por outro funcionário da COMODANTE e quando a trabalho desta, desde que devidamente habilitado e autorizado pela COMODATÁRIA."},
    			{"5ª) 	A COMODATÁRIA será reembolsada pelas despesas de utilização do veículo de acordo com as normas internas da COMODANTE."},
    			{"6ª) 	No caso de ocorrer acidentes com o veículo objeto deste contrato, a COMODANTE pagará os custos de reparo, desde que não seja comprovada culpa da COMODATÁRIA, situação na qual uma vez configurada implicará á COMODATÁRIA o pagamento de 50% do custo da franquia do seguro, ou a outros critérios estipulados em normas internas da COMODANTE ."},
    			{"7ª) 	Havendo perda total ou furto do veículo, a COMODANTE providenciará a sua substituição. Se for comprovada culpa da COMODATÁRIA, este arcará com 50% custos incorridos pelo COMODANTE."},
    			{"8ª) 	Os consertos que não sejam de caráter de urgência, bem como reformas totais ou parciais, necessitarão de prévia autorização da unidade responsável pela administração da frota para que sejam executados."},
    			{"9ª)	Mensalmente, a COMODATÁRIA deverá apresentar ao seu superior imediato, Relatório de Despesas contendo as despesas com o veículo."},
    			{"10ª)	Não será permitido à instalação de acessórios / equipamentos adicionais que alterem as características originais do veículo."},
    			{"11ª)	Havendo alteração de cargo para o qual a empresa não conceda veículo, ou, se durante a  vigência deste contrato houver rescisão de contrato de trabalho entre as partes, a COMODATÁRIA deverá devolver o veículo recebido da COMODANTE, em perfeitas condições de uso, salvo o desgaste natural das peças e componentes."},
    			{"12ª) 	Em se configurando a má utilização do veículo pela COMODATÁRIA, poderá a COMODANTE  pleitear ressarcimento das despesas com o conserto das partes do veículo diretamente afetadas por esta condição, ou sem qualquer aviso prévio ou notificação, rescindir  o presente contrato e reaver o veículo, sem qualquer ressarcimento ou indenização à COMODATÁRIA."},
    			{"13ª) 	Reserva-se à COMODANTE, o direito de vistoriar o veículo, a qualquer tempo, em data fixada pela unidade responsável pela administração da frota, no sentido de verificar se estão sendo cumpridas as cláusulas deste, no tocante à conservação do veículo, inclusive a conferência de quilometragem informada no relatório com a  registrada no hodômetro do veículo."},
    			{"14ª) 	Definida a venda do veículo pela COMODANTE, terá a COMODATÁRIA preferência  sobre a sua aquisição de conformidade com o procedimento vigente na época."},
    			{"15ª)	Compromete-se a COMODATÁRIA a conduzir o veículo dentro das leis de trânsito arcando, com multas por infrações que forem aplicadas pelas autoridades competentes, bem como a ressarcir a COMODANTE do valor das despesas por ela quitadas, através de desconto em folha de pagamento, estando sujeito a COMODATÁRIA ao não reembolso das despesas descritas na clausula 5ª até sua quitação, incluindo-se os danos com acidentes de trânsito.  "},
    			{"Único - 	No caso de acidente, roubo ou furto, a COMODATÁRIA deverá providenciar junto aos órgãos locais competentes toda documentação necessária para o processo de reparação."},
    			{"16ª)	Correrão por conta da COMODANTE  as despesas com licenciamento e seguro do veículo."},
    			{"17ª) 	O presente contrato vigorará pelo prazo de 36 meses ou 100.000 km rodados, não sendo permitida a substituição do veículo por outro, salvo se a COMODATÁRIA vier a exercer outra atividade para a COMODANTE que, de acordo com as normas internas, lhe destine um veículo com outras características."},
    			{"18ª)	Poderá a COMODANTE, a qualquer momento e a seu critério rescindir o presente Contrato de Comodato, mediante simples comunicação à COMODATÁRIA."},
    			{"19ª) 	 A COMODATÁRIA obriga-se a observar as normas que regem o presente contrato ciente de que a sua não observância acarretará em sanções previstas de acordo com as normas internas."},
    			{"20ª) A COMODATÁRIA recebe nesta data a cópia da política de veículos vigente e se compromete a cumpri-la em sua totalidade. Sempre que uma nova política entrar em vigor, esta deverá ser entregue pela COMODANTE à COMODATÁRIA."},
    			{"21ª) 	As partes elegem o Foro da Comarca de São Paulo para dirimir quaisquer dúvidas da interpretação do presente contrato."}};
    		return data;
    	}else if(type == 3) {
    		String[][] data = {{"2.7 Será permitida a instalação de acessórios no carro-frota desde que sob as expensas do FUNCIONÁRIO e desde que não alterem as características originais do carro-frota. Na hipótese de instalação de algum equipamento ou acessório que não puder ser removido quando da devolução do carro-frota à LOCADORA, quer por encerramento de contrato ou desligamento do FUNCIONÁRIO, o mesmo não será reembolsado pela instalação desses acessórios."},
    			{"2.8 Na hipótese dos acessórios instalados sob as expensas do FUNCIONÁRIO serem removidos e alterarem as características originais do carro-frota, o FUNCIONÁRIO arcará com os custos de reparo na parte diretamente afetada por esta condição. Fica desde já autorizado pelo FUNCIONÁRIO o desconto do referido valor na folha de pagamento (devolução por encerramento de contrato) ou na rescisão (no caso de desligamento ou pedido de demissão)."},
    			{"2.9 Em caso de colisão decorrente de dolo ou culpa do usuário com avarias no carro-frota que a critério da LOCADORA puderem ser reparadas, o FUNCIONÁRIO será responsável pelo pagamento integral do conserto, através do desconto em sua folha de pagamento, até o limite máximo de 5% (cinco por cento) do valor do mesmo carro novo (zero quilometro), conforme a respectiva tabela de revendedores vigente na data do pagamento."},
    			{"2.10 Em caso de colisão decorrente de dolo ou culpa do usuário que acarrete a perda total do carro-frota, o FUNCIONÁRIO será responsável pelo pagamento, através do desconto em sua folha de pagamento, do valor equivalente a 10% (dez por cento) do valor do mesmo carro novo (zero quilometro) conforme a respectiva tabela de revendedores vigente na data do pagamento."},
    			{"2.11 Na hipótese de furto do carro-frota em que se caracterize a negligência quanto à guarda e utilização do carro-frota em relação às instruções recebidas da COMPANHIA através da Política de Carros-Frota – Frota Terceirizada, o FUNCIONÁRIO será advertido formalmente e será responsável pelo pagamento, através do desconto em sua folha de pagamento, do valor equivalente a 10% (dez por cento) do valor do mesmo carro novo (zero quilometro) conforme a respectiva tabela de revendedores vigente na data do pagamento."},
    			{"2.12 Na hipótese do carro-frota titular não estar disponível, por se encontrar em serviços de manutenção ou reparos, o FUNCIONÁRIO terá a sua disposição um outro carro-frota fornecido pela LOCADORA, respeitando-se o tempo mínimo de quatro horas para execução de reparos conforme estabelecido em contrato entre a LOCADORA e a ALLERGAN, aplicando-se também a este carro-frota todas as regras estabelecidas no presente Termo. O carro-frota reserva quando utilizado, deverá ser devolvido abastecido. Na hipótese de devolução sem abastecimento o usuário arcará com o acréscimo de 15% do valor do combustível utilizado, cobrado pela Locadora, sendo este valor descontado de seus vencimentos."},
    			{"2.13 O FUNCIONÁRIO arcará com os custos INTEGRAIS com reparos, reposições de carros-frota e indenizações por danos materiais e/ou pessoais, não somente em favor da locadora como também em favor de terceiros no caso de ele ter agido com dolo ou culpa gravíssima na guarda, condução ou conservação do carro-frota, ficando o mesmo totalmente responsável pelas conseqüências de tais condutas, devendo suportar e reembolsar à Allergan e/ou a LOCADORA por todas as despesas que estas vierem a suportar em decorrência de tais atos."}};
    		return data;

    	}else if(type == 5) {
    		String[][] data = {{"1ª) 	A COMODANTE  entrega ao COMODATÁRIO o veículo acima descrito, devidamente licenciado e lacrado, com todos os impostos e taxas pagos para livre circulação em todo Território Nacional, inclusive com apólice de seguro com cobertura para danos materiais e pessoais, nos termos da política interna da COMODANTE ." +
					"\n\n2ª) 	Destina-se o aludido veículo a que o COMODATÁRIO possa exercer suas atividades para a COMODANTE, de acordo com as normas internas."},
				{"3ª) 	É permitido ao COMODATÁRIO a utilização do aludido bem, de acordo com as condições constantes do presente contrato de comodato." +
						"\n\n4ª) 	É proibida a condução do veículo por terceiros, salvo por outro funcionário da COMODANTE quando a trabalho desta e devidamente autorizado pelo COMODATÁRIO ou eventualmente pelo cônjuge do COMODATÁRIO desde que habilitado e que não o utilize como meio principal de transporte."},
				{"5ª) 	O COMODATÁRIO será reembolsado pelas despesas de utilização do veículo de acordo com as normas internas da COMODANTE.\" "},
				{"6ª) 	No caso de ocorrer acidentes com o veículo objeto deste contrato, a COMODANTE  pagará os custos de reparo, desde que não seja comprovada culpa do COMODATÁRIO, situação na qual uma vez configurada implicará ao COMODATÁRIO o pagamento integral do custo da franquia do seguro, ou o valor total para reparo do veículo caso a franquia estipulada pelo seguro não seja atingida ou ainda a outros critérios estipulados em normas internas da COMODANTE ."},
				{"7ª) 	Havendo perda total ou furto do veículo, a COMODANTE  providenciará a sua substituição.\n\n8ª) 	Os consertos que não sejam de caráter de urgência, bem como reformas totais ou parciais, necessitarão de prévia autorização da unidade responsável  pela administração da frota para que sejam executados."},
				{"9ª)	Mensalmente, o COMODATÁRIO deverá apresentar ao seu superior imediato, Relatório de Despesas contendo as despesas com o veículo.\n\n10ª)	Não será permitido a instalação de acessórios / equipamentos adicionais que alterem as características originais do veículo."},
				{"11ª)	Havendo alteração de cargo para o qual a empresa não conceda veículo, ou, se durante a  vigência deste contrato houver rescisão de contrato de trabalho entre as partes, o COMODATÁRIO deverá devolver o veículo recebido da COMODANTE , em perfeitas condições de uso, salvo o desgaste natural das peças e componentes ."},
				{"12ª) 	Em se configurando a má utilização do veículo pelo COMODATÁRIO, poderá a COMODANTE  pleitear ressarcimento das despesas com o conserto das partes do veículo diretamente afetadas por esta condição,ou sem qualquer aviso prévio ou notificação, rescindir  o presente contrato e reaver o veículo, sem qualquer ressarcimento ou indenização ao COMODATÁRIO."},
				{"13ª) 	Reserva-se à COMODANTE, o direito de vistoriar o veículo, a qualquer tempo, em data fixada pela unidade responsável pela administração da frota, no sentido de verificar se estão sendo cumpridas as cláusulas deste, no tocante à conservação do veículo, inclusive a conferência de quilometragem informada no relatório com a  registrada no hodômetro do veículo.\n\n14ª) 	Definida a venda do veículo pela COMODANTE, terá o COMODATÁRIO preferência  sobre a sua aquisição de conformidade com o procedimento vigente na época."},
				{"15ª)	Compromete-se o COMODATÁRIO a conduzir o veículo dentro das leis de trânsito arcando, com multas por infrações que forem aplicadas pelas autoridades competentes, bem como a ressarcir a COMODANTE do valor das despesas por ela quitadas, através de desconto em folha de pagamento, estando sujeita o COMODATÁRIO ao não reembolso das despesas descritas na clausula 5ª até sua quitação, incluindo-se os danos com acidentes de trânsito.\n\tÚnico - 	No caso de acidente, roubo ou furto, o COMODATÁRIO deverá providenciar junto aos órgãos locais competentes toda documentação necessária para o processo de reparação."},
				{"16ª)	Correrão por conta da COMODANTE  as despesas com licenciamento e seguro do veículo.\n\n17ª) 	O presente contrato vigorará pelo prazo de 36 meses ou 100.000 km rodados, não sendo permitida a substituição do veículo por outro, salvo se o COMODATÁRIO vier a exercer outra atividade para a COMODANTE que, de acordo com as normas internas, lhe destine um veículo com outras características."},
				{"18ª)	Poderá a COMODANTE rescindir o presente se o COMODATÁRIO vier a exercer outra atividade para a COMODANTE, nos termos das normas internas em vigor.\n\n19ª) 	O COMODATÁRIO  obriga-se a observar as normas que regem o presente contrato ciente de que a sua não observância acarretará em sanções previstas de acordo com as normas internas."},
				{"20ª) 	As partes elegem o Foro da Comarca de São Paulo para dirimir quaisquer dúvidas da interpretação do presente contrato."}};
    		return data;
    	}

    	return new String[0][0];
    }

} 
    


