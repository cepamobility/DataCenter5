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
import communication.driver.tables.DriverData;
import communication.language.Text;
import communication.vehicle.tables.VehicleData;

public class ContractDevolutionReportFactory extends ReportFactory {

    protected Text messages;
    protected SystemAccess sys;
	private DriverData driver;
	private VehicleData vehicle;
	private String data;
    
    public ContractDevolutionReportFactory(String name, TableModel model, ActionForm form, 
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
        return items;
    }
    
    protected ReportHeader createReportHeader() {
        ReportHeader reportHeader = new ReportHeader();
		reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 20, getReportWidth(), 25),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_UNDERLINE_TITLE,"TERMO DE DEVOLUÇÃO DE CARRO-FROTA"));

		String text1 = "Pelo presente instrumento, declaram as partes a extinção do Termo de Responsabilidade de Utilização de Carro-Frota, firmado em 12 de dezembro de 2006, referente ao automóvel  "+messages.getText(vehicle.getTrade().getResourceBundleId())+" "+vehicle.getModel()+", "+vehicle.getYear()+", placa "+vehicle.getMatricula()+", locado pela companhia e disponibilizado ao empregado "+driver.getFirstName()+" "+driver.getLastName()+", para exercício de suas atividades";
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 70, getReportWidth(), 80), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA,text1));
        
        String text2 = "Em razão do encerramento do contrato de locação e substituição do veículo locado, o empregado compromete-se a entregar o veículo acima descrito, observando as obrigações pactuadas no item 2 do Termo de Responsabilidade de Utilização de Carro-Frota. ";
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 170, getReportWidth(), 60), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA,text2));
        
        String text3 = "Assim, o presente Termo de Devolução implica na entrega do veículo pelo empregado que se responsabilizará pelo perfeito estado do automóvel, bem como pelo ressarcimento de eventuais danos e multas a que tenha dado causa.";
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 250, getReportWidth(), 60), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA,text3));

        String text4 = "E por estarem cientes e de acordo, assinam o presente Termo em duas vias de igual teor.";
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 320, getReportWidth(), 40), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA,text4));

        String text5 = "São Paulo, "+data+".";
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 380, getReportWidth(), 30), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA,text5));
        
        String line = "_______________________________";

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 470, getReportWidth(), 30), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA,line));
        String text6 = driver.getFirstName()+" "+driver.getLastName();
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 500, getReportWidth(), 18), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.TOP,STYLE_HELVETICA,text6));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 560, getReportWidth(), 30), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_HELVETICA,line));
        String text7 = "Allergan Produtos Farmacêuticos Ltda.";
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 590, getReportWidth(), 20), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.TOP,STYLE_HELVETICA,text7));

        return reportHeader;
    }

}
