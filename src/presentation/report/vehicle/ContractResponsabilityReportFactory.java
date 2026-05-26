package presentation.report.vehicle;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

import javax.swing.table.TableModel;

import org.apache.struts.action.ActionForm;
import org.jfree.report.ElementAlignment;
import org.jfree.report.ItemBand;
import org.jfree.report.ReportFooter;
import org.jfree.report.ReportHeader;
import org.jfree.report.elementfactory.LabelElementFactory;
import org.jfree.report.elementfactory.NumberFieldElementFactory;
import org.jfree.report.elementfactory.StaticShapeElementFactory;
import org.jfree.report.elementfactory.TextFieldElementFactory;
import org.jfree.report.function.CreateHyperLinksFunction;
import org.jfree.report.function.ElementColorFunction;
import org.jfree.report.function.ExpressionCollection;
import org.jfree.report.function.TextFormatExpression;

import presentation.report.ReportFactory;
import presentation.translator.web.driver.DriverMultiReportForm;
import presentation.translator.web.driver.DriverReportForm;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.administration.corporation.tables.CorporationData;
import communication.datatype.tables.SexDataType;
import communication.driver.tables.DriverData;
import communication.language.Text;
import communication.vehicle.tables.VehicleData;

public class ContractResponsabilityReportFactory extends ReportFactory {

    protected Text messages;
    protected SystemAccess sys;
	private DriverData driver;
	private VehicleData vehicle;
	private String data;
    
    public ContractResponsabilityReportFactory(String name, TableModel model, ActionForm form, 
    		SystemAccess sys, VehicleData vehicle, String data) {
        super(name, model, form);
        this.sys = sys;
        this.messages = sys.getText();
        this.vehicle = vehicle;
        this.driver = vehicle.getDriver();
        this.data = data;
    }

    protected int getWidth() {
        return 700;
    }

    protected int getReportWidth() {
        return 600;
    }

    protected ItemBand createItemBand() {
        ItemBand items = super.createItemBand();
        items.addElement(TextFieldElementFactory.createStringElement("item",new Rectangle2D.Float(10, 10, getReportWidth(), 70), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,"", "item"));
        return items;
    }
    
    protected ReportHeader createReportHeader() {
        ReportHeader reportHeader = new ReportHeader();
		reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 20, getReportWidth(), 25),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_UNDERLINE_TITLE,"TERMO DE RESPONSABILIDADE DE UTILIZAÇÃO DE CARRO-FROTA"));

		String aux1 = "Sr.";
		String aux2 = "domiciliado";
		String aux3 = "COMODATÁRIO";
		if(driver.getSex().getResourceBundleId().equals(SexDataType.FEMALE)) {
			aux1 = "Sra.";
			aux2 = "domiciliada";
			aux3 = "COMODATÁRIA";
		}
		String text1 = "Pelo presente Termo de Responsabilidade de Utilização de Carro-Frota, COMPANHIA e FUNCIONÁRIO, adiante indicados, concordam com as seguintes condições para utilização de carro frota - locado pela Companhia.";
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 70, getReportWidth(), 20), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,text1));
        
        String text2 = "O presente Termo complementa e é parte integrante da Política de Carros-Frota – Frota Terceirizada da Companhia, podendo ser atualizado sempre que um fato novo assim o determinar.";
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 100, getReportWidth(), 20), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,text2));
        
        String text3 = "1 – DAS RESPONSABILIDADES DA COMPANHIA";
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 130, getReportWidth(), 20), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_BOLD,text3));

        String text4 = "1.1 A COMPANHIA reembolsará o FUNCIONÁRIO das despesas havidas com combustível, lavagem, lubrificação do carro-frota e estacionamento, dentro da política em vigor. ";
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 160, getReportWidth(), 20), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,text4));
        
        String text5 = "1.2 Compete à LOCADORA a responsabilidade pelas despesas com documentação e seguro do carro-frota.";	 
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 180, getReportWidth(), 20), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,text5));
        
        String text6 = "2 – DAS OBRIGAÇÕES DO FUNCIONÁRIO";	 
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 210, getReportWidth(), 20), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_BOLD,text6));
        
        String text7 = "2.1 Para o exercício de suas atividades, o FUNCIONÁRIO terá à sua disposição o carro-frota "+messages.getText(vehicle.getTrade().getResourceBundleId())+", ano/modelo "+vehicle.getYear()+"/"+vehicle.getModel()+", cor "+vehicle.getColour()+", chassi "+vehicle.getChasisNumber()+" e  placa "+vehicle.getMatricula();
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 240, getReportWidth(), 20), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,text7));
        
        String text8 = "2.2 O FUNCIONÁRIO será responsável pelo pagamento das multas acrescidas das respectivas taxas administrativas cobradas pela LOCADORA, porventura aplicadas ao carro-frota, a que título for, a serviço da COMPANHIA ou não, ficando desde já autorizado o desconto dos respectivos valores em seus vencimentos. Fica desde já autorizado pelo FUNCIONÁRIO o desconto do referido valor na folha de pagamento, (devolução por encerramento de contrato), ou na rescisão (no caso de desligamento ou pedido de demissão)."; 
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 260, getReportWidth(), 60), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,text8));

        String text9 = "2.3 A LOCADORA é responsável por proceder á necessária identificação do condutor junto aos órgãos de trânsito.";
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 330, getReportWidth(), 20), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,text9));
        
        String text10 = "2.4 Para dar cumprimento ao que determina o Código Nacional de Trânsito quanto à identificação do condutor, a Allergan fornecerá à Locadora os dados pessoais (endereço, telefone, CPF, RG e CNH) do FUNCIONÁRIO usuário do carro-frota.";
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 360, getReportWidth(), 40), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,text10));

        String text11 = "2.5 O FUNCIONÁRIO se compromete a manter o carro-frota em condições ótimas de dirigibilidade e conservação.";
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 410, getReportWidth(), 20), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,text11));

        String text12 = "2.6 O FUNCIONÁRIO será responsável por encaminhar o carro-frota para as manutenções periódicas, devendo fazê-lo dentro das especificações determinadas pelo fabricante, em oficinas autorizadas e indicadas pela LOCADORA.";
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 440, getReportWidth(), 40), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,text12));

        return reportHeader;
    }

    protected ReportFooter createReportFooter() {
    	ReportFooter reportFooter = super.createReportFooter();

        String text1 = "2.14 O FUNCIONÁRIO se compromete a não permitir que o carro-frota seja utilizado por pessoa não vinculada à COMPANHIA ou por ela não autorizada.";
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, getReportWidth(), 40), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,text1));

        String text2 = "2.15 O FUNCIONÁRIO se compromete a manter atualizada a sua documentação junto às autoridades de trânsito.";
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 50, getReportWidth(), 40), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,text2));

        String text3 = "E por estarem cientes e de acordo, assinam o presente Termo em duas vias de igual teor.";
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 90, getReportWidth(), 20), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,text3));
        
        String text4 = "São Paulo, "+data+".";
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 120, getReportWidth(), 20), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,text4));
        
        
        	
        String line = "_______________________________";

        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 170, 220, 30), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA,line));
        String text6 = driver.getFirstName()+" "+driver.getLastName();
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 200, 220, 18), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.TOP,STYLE_HELVETICA,text6));

        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(250, 170, 220, 30), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA,line));
        String text7 = "Allergan Produtos Farmacêuticos Ltda.";
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(250, 200, 220, 20), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.TOP,STYLE_HELVETICA,text7));
    	return reportFooter;
    }
}
