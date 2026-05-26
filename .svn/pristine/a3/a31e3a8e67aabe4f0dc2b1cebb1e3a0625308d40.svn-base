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

public class ContractDistratoReportFactory extends ReportFactory {

    protected Text messages;
    protected SystemAccess sys;
	private DriverData driver;
	private VehicleData vehicle;
	private String data;
    
    public ContractDistratoReportFactory(String name, TableModel model, ActionForm form, SystemAccess sys, VehicleData vehicle, String data) {
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
        return items;
    }
    
    protected ReportHeader createReportHeader() {
        ReportHeader reportHeader = new ReportHeader();
		reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 20, getReportWidth(), 25),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_UNDERLINE_TITLE,"TERMO DE DISTRATO DE CONTRATO DE COMODATO"));

		String text1 = "Pelo presente instrumento particular, de um lado:";
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 60, getReportWidth(), 20), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,text1));
        
        String text2 = "a) ALLERGAN PRODUTOS FARMACÊUTICOS LTDA., com sede à Av. Dr. Cardoso de Melo, 1855, 13º andar – Bloco I - , Vila Olímpia, Estado de São Paulo - SP, inscrita no CGC MF sob o nº 43.426.626/0001-77, doravante denominada COMODANTE, representada por seu representante legal que esta subscreve.";
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 80, getReportWidth(), 80), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,text2));
        
        String text3 = "e do outro lado,";
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 160, getReportWidth(), 20), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,text3));
        
        String aux1 = "domiciliado";
        String aux2 = "COMODATÁRIO";
        if(driver.getSex().getResourceBundleId().equals(SexDataType.FEMALE)) {
        	aux1 = "domiciliada";
        	aux2 = "COMODATÁRIA";
        }
		String rg = (driver.getFederativeEntity() == null) ? "--" : 	driver.getFederativeEntity();
		String cpf = (driver.getLicenseCoverage() == null) ? "--" : driver.getLicenseCoverage();
        String text4 = "b) "+driver.getFirstName()+" "+driver.getLastName()+", residente e "+aux1+" à "+driver.getPersonalAddress()+" , São Paulo/SP - CEP , portadora da célula de identidade RG nº "+rg+", CPF nº "+cpf+" e da CNH nº "+driver.getLicenseNumber()+" categoria  "+driver.getLicenseCategory()+"  doravante denominado "+aux2+".";
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 180, getReportWidth(), 80), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,text4));

        String text5 = "1 - As partes firmaram Contrato de Comodato de Concessão de Veículo em"+data+" , para que o empregado pudesse desempenhar suas atividades laborativas.";
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 260, getReportWidth(), 40), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,text5));
        
        String text6 = "2 – Em virtude da ocorrência da clausula 17ª, do referido contrato, as partes resolvem firmar o presente Distrato do Contrato de Comodato, que se regerá conforme condições abaixo descritas.";
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 310, getReportWidth(), 40), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,text6));

        String text7 = "3 – Nesse ato, o empregado entrega o presente veículo marca "+messages.getText(vehicle.getTrade().getResourceBundleId())+" modelo "+vehicle.getModel()+" ano "+vehicle.getYear()+" Cor "+vehicle.getColour()+" placa "+vehicle.getMatricula()+" e Chassis nº "+vehicle.getChasisNumber()+", sem qualquer ressalva do COMODANTE, quanto ao estado de conservação do bem.";
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 360, getReportWidth(), 60), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,text7));

        String text8 = "4 – Em virtude da devolução do veículo, as partes rescindem, de forma irrevogável e irretratável, o Contrato de Comodato, acima descrito, desobrigando-se mutuamente de tudo que foi ali acordado, excetuando-se eventuais multas que venham a ser cobradas em razão de infrações cometidas pelo COMODATÁRIO, no período em que esteve com a posse do veículo.";
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 430, getReportWidth(), 60), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,text8));
        
        String text9 = "E por estarem justos e contratados, assinam o presente em duas vias de igual teor, para o mesmo efeito, diante das testemunhas abaixo mencionadas.";
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 500, getReportWidth(), 40), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,text9));

        String text10 = "São Paulo, "+data+".";
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 560, getReportWidth(), 30), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,text10));
        
        String line = "________________________________";

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 630, 200, 30), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,line));
        String text11 = driver.getFirstName()+" "+driver.getLastName();
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 660, 200, 18), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.TOP,STYLE_HELVETICA_SMALL,text11));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(230, 630, 200, 30), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,line));
        String text12 = "Allergan Produtos Farmacêuticos Ltda.";
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(230, 660, 200, 20), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.TOP,STYLE_HELVETICA_SMALL,text12));

        String text13 = "Testemunhas:";
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 700, getReportWidth(), 20), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,text13));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 750, 200, 18), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.TOP,STYLE_HELVETICA_SMALL,"1."+line));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(230, 750, 200, 18), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.TOP,STYLE_HELVETICA_SMALL,"2."+line));

        return reportHeader;
    }

}
