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

public class ContractComodatoOwnFleetReportFactory extends ReportFactory {

    protected Text messages;
    protected SystemAccess sys;
	private DriverData driver;
	private VehicleData vehicle;
	private String data;
    
    public ContractComodatoOwnFleetReportFactory(String name, TableModel model, ActionForm form, 
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
        items.addElement(TextFieldElementFactory.createStringElement("item",new Rectangle2D.Float(10, 10, getReportWidth(), 65), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,"", "item"));
        return items;
    }
    
    protected ReportHeader createReportHeader() {
        ReportHeader reportHeader = new ReportHeader();
		reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 20, getReportWidth(), 25),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_UNDERLINE_TITLE,"CONTRATO DE COMODATO"));

		String aux1 = "Sr.";
		String aux2 = "domiciliado";
		String aux3 = "COMODATÁRIO";
		if(driver.getSex().getResourceBundleId().equals(SexDataType.FEMALE)) {
			aux1 = "Sra.";
			aux2 = "domiciliada";
			aux3 = "COMODATÁRIA";
		}
		String rg = (driver.getFederativeEntity() == null) ? "--" : 	driver.getFederativeEntity();
		String cfg = (driver.getLicenseCoverage() == null) ? "--" : driver.getLicenseCoverage();
		String text1 = "Pelo presente instrumento particular, de um lado ALLERGAN PRODUTOS FARMACÊUTICOS LTDA., com sede à Av. Dr. Cardoso de Melo, 1855, 13º andar – Bloco I -, Vila Olímpia, Estado de São Paulo - SP, inscrita no CGC MF sob o nº 43.426.626/0001-77, neste ato representada pelos infra-assinados doravante denominada COMODANTE e de outro a";
		text1 += aux1 + " " + driver.getFirstName()+" "+driver.getLastName()+" residente e "+aux2+" à "+driver.getPersonalAddress()+", portador da célula  de identidade RG nº "+rg+", CPF nº"+cfg+" e da CNH nº "+driver.getLicenseNumber()+" categoria "+driver.getLicenseCategory()+" doravante denominado "+aux3+", tem entre si justo e contratado o seguinte:";
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 70, getReportWidth(), 70), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,text1));
        
        String cargo = (driver.getAuthorizateWho() == null) ? "---" : driver.getAuthorizateWho();
        String text2 = "O "+aux3+", na qualidade de funcionário da COMODANTE, e exercendo o cargo de "+cargo+", para o completo desempenho de suas atribuições recebe, neste ato, da  COMODANTE, um veículo com as seguintes características:";
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 150, getReportWidth(), 60), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,text2));
        
        String text3 = "MARCA: "+messages.getText(vehicle.getTrade().getResourceBundleId());
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(50, 220, 200, 20), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,text3));

        String text4 = "MODELO: "+vehicle.getModel();
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(275, 220, 300, 20), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,text4));
        
        String text7 = "PLACA: "+vehicle.getMatricula();
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(50, 240, 200, 20), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,text7));

        String text8 = "COR: "+vehicle.getColour(); 
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(275, 240, 300, 20), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,text8));

        String text5 = "ANO: "+vehicle.getYear();	 
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(50, 260, 200, 20), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,text5));
        
        String text9 = "FABRICAÇÃO/MODELO: "+vehicle.getYear()+"/"+vehicle.getModel();	 
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(275, 260, 300, 20), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,text9));
        
        String text6 = "CHASSIS: "+vehicle.getChasisNumber();	 
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(50, 280, 200, 20), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,text6));

        String text10 =  (vehicle.getOptionalInfo() != null) ? "RENAVAM: " +vehicle.getOptionalInfo().getRenavan() : "RENAVAM: ---";	 
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(275, 280, 300, 20), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,text10));
        
        
        String text11 = "O "+aux3+" recebe da COMODANTE, o veículo já mencionado, passando o presente contrato a vigorar com as seguintes cláusulas e condições:";
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 330, getReportWidth(), 40), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,text11));
        
        return reportHeader;
    }

    protected ReportFooter createReportFooter() {
    	ReportFooter reportFooter = super.createReportFooter();

        String text1 = "E por estarem justos e contratados, assinam o presente em duas vias de igual teor, para o mesmo efeito, diante das testemunhas abaixo mencionadas.";
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, getReportWidth(), 40), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,text1));

        String text2 = "São Paulo, "+data+".";
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 60, getReportWidth(), 20), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,HELVETICA_BOLD,text2));
        
        
        String text3 = "Declara estar Ciente e De Acordo com a  Política de Veículos, bem como do recebimento do CRLV (Certificado de Registro e Licenciamento de Veículo):";
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 90, getReportWidth(), 40), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_ITALICS_BOLD,text3));
        	
        String line = "__________________________________________";

        String text6 = driver.getFirstName()+" "+driver.getLastName();
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 140, 300, 20), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,line));
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 160, 300, 20), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,text6));

        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(350, 140, 300, 20), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,line));
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(350, 160, 300, 20), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,"Allergan Produtos Farmacêuticos Ltda"));

        String text7 = "Testemunhas:";
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 200, 300, 20), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.TOP,STYLE_HELVETICA_SMALL,line));
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 220, 300, 30), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,text7));
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(350, 200, 300, 20), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.TOP,STYLE_HELVETICA_SMALL,line));
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(350, 220, 300, 30), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA_SMALL,text7));

    	return reportFooter;
    }
}
