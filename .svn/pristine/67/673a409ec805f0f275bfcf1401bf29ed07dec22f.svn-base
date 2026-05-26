package presentation.report.reporterjj;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.util.Calendar;

import javax.swing.table.TableModel;

import org.apache.struts.action.ActionForm;
import org.jfree.report.ElementAlignment;
import org.jfree.report.ItemBand;
import org.jfree.report.PageDefinition;
import org.jfree.report.PageFooter;
import org.jfree.report.PageHeader;
import org.jfree.report.ReportFooter;
import org.jfree.report.ReportHeader;
import org.jfree.report.SimplePageDefinition;
import org.jfree.report.elementfactory.LabelElementFactory;
import org.jfree.report.elementfactory.StaticShapeElementFactory;
import org.jfree.report.elementfactory.TextFieldElementFactory;
import org.jfree.report.function.ElementColorFunction;
import org.jfree.report.function.ExpressionCollection;
import org.jfree.report.util.PageFormatFactory;

import presentation.report.ReportFactory;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.administration.user.UserSessionData;
import communication.driver.tables.DriverData;
import communication.language.Text;

public class SelfEvaluationReportFactory extends ReportFactory {

    protected Text messages;
    protected SystemAccess sys;
	
    private Integer activity;
	private DriverData driver;
	private UserSessionData userSessionData;
	
    public SelfEvaluationReportFactory(String name, TableModel model, ActionForm form, SystemAccess sys, Integer activity) throws Exception {
        super(name, model, form);
    
        this.sys = sys;
        this.messages = sys.getText();
        userSessionData = sys.getUserSessionData();
        this.activity = activity;
        this.driver = userSessionData.getDriverData();

    }

    public SelfEvaluationReportFactory(String name, TableModel model, ActionForm form, SystemAccess sys, Integer activity, DriverData driver) throws Exception {
        super(name, model, form);
    
        this.sys = sys;
        this.messages = sys.getText();
        userSessionData = sys.getUserSessionData();
        this.activity = activity;
        this.driver = driver;

    }

    protected PageDefinition getPageDefinition() {
        final PageFormatFactory pff = PageFormatFactory.getInstance();
        final Paper paper = pff.createPaper(getWidth(),820);
        pff.setBorders(paper, 0, 0, 0, 0);
        final PageFormat format = pff.createPageFormat(paper, PageFormat.PORTRAIT);
        return new SimplePageDefinition(format);
    }

    protected int getWidth() {
        return 682;
    }

    protected int getReportWidth() {
        return 600;
    }
    
    protected ReportHeader createReportHeader() {
        ReportHeader reportHeader = new ReportHeader();
        
		reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(30, 0, getReportWidth()-200, 25),
        		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("activity.reporterjj.points")+" - "+messages.getText("reporterjj.selfevaluation.point")));
       
		reportHeader.addElement(org.jfree.report.elementfactory.ImageFieldElementFactory.
     			createImageDataRowElement("newlogoSafeFleetLA", new Rectangle2D.Float(getReportWidth()-262, 10, 262, 40), "newlogoSafeFleetLA"));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(30, 30, 60, 18), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.firstname")+":"));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(100, 30, 300, 18), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,driver.getFirstName()+" "+driver.getLastName()));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(30, 50, 60, 18), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.wwid")+":"));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(100, 50, 300, 18), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,driver.getCorporationId()));
        
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(30, 70, 60, 18), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.activity.date")+":"));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(100, 70, 300, 18), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,Util.formatDate(Calendar.getInstance().getTime())));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(30, 90, getReportWidth(), 18),
                Color.BLUE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_REPORTEJJ_1,messages.getText("activity.reporterjj.interpretation")));
       
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(30, 115, 80, 18), 
                Color.RED,ElementAlignment.LEFT,ElementAlignment.TOP,STYLE_SIMPLE_BOLD_FONT,messages.getText("activity.reporterjj.pointio")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(110, 115, getReportWidth()-90, 70), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.TOP,STYLE_SIMPLE_BOLD_FONT,messages.getText("activity.reporterjj.iodescription")));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(30, 190, 190, 18), 
        		new Color(53,87,36),ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,messages.getText("activity.reporterjj.pointrangegreen")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(220, 190, 220, 18), 
                new Color(216,130,43),ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,messages.getText("activity.reporterjj.pointrangeyellow")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(440, 190, 190, 18), 
                new Color(134,12,7),ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,messages.getText("activity.reporterjj.pointrangered")));
        
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(50, 220, 50, 40), 
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.BOTTOM,STYLE_SIMPLE_BOLD_FONT,messages.getText("Suas Respostas")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(100, 220, 50, 40), 
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.BOTTOM,STYLE_SIMPLE_BOLD_FONT,messages.getText("Pontuação do Item")));

        return reportHeader;
    }

    protected ItemBand createItemBand() {
        ItemBand items = super.createItemBand();
    
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,new Rectangle2D.Float(30,0,getReportWidth(),2), false, true));
        items.addElement(TextFieldElementFactory.createStringElement("number",new Rectangle2D.Float(30, 2, 20, 18),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","number"));
        items.addElement(TextFieldElementFactory.createStringElement("point",new Rectangle2D.Float(50, 2, 50, 18),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","point"));
        items.addElement(TextFieldElementFactory.createStringElement("io",new Rectangle2D.Float(100, 2, 50, 18),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","io"));
        items.addElement(TextFieldElementFactory.createStringElement("item",new Rectangle2D.Float(150, 2, getReportWidth()-220, 18),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","item"));
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,new Rectangle2D.Float(getReportWidth()-80, 2, 110, 18), false, true));
        items.addElement(TextFieldElementFactory.createStringElement("Variance Field 1",new Rectangle2D.Float(getReportWidth()-80, 2, 100, 18),Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","extra"));

        return items;
    }
    
    protected PageHeader createPageHeader() {
        PageHeader pageHeader = new PageHeader();
        pageHeader.addElement(org.jfree.report.elementfactory.ImageFieldElementFactory.
     			createImageDataRowElement("header", new Rectangle2D.Float(0, 0, getWidth(), 26), "header"));
        return pageHeader;
    }

    protected PageFooter createPageFooter() {
        PageFooter pageFooter = new PageFooter();
        pageFooter.addElement(org.jfree.report.elementfactory.ImageFieldElementFactory.
     			createImageDataRowElement("footer", new Rectangle2D.Float(0, 0, getWidth(), 13), "footer"));
        return pageFooter;
    }

    protected ReportFooter createReportFooter() {
        ReportFooter reportFooter = new ReportFooter();
        reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement("", null, null,new Rectangle2D.Float(30, 0, getReportWidth(), 2), false, true));

        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(30, 5, getReportWidth(), 20), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.BOTTOM,STYLE_TITLE,messages.getText("CONCLUSÕES :")));					
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(30, 30, getReportWidth(), 20), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.BOTTOM,STYLE_SIMPLE_BOLD_FONT,messages.getText("1 - Parabéns pela conclusão deste exercício.  Com certeza você identificou alguns sinais de melhoria sobre sua conduta ao volante.")));										
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(30, 55, getReportWidth(), 20), 
        		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.BOTTOM,STYLE_SIMPLE_BOLD_FONT,messages.getText("2 - Para que esta atividade traga benefícios, é muito importante que você estabeleça um plano de ação para melhoria das áreas identificadas.")));									

        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(30, 80, getReportWidth(), 20), 
        		Color.BLUE,ElementAlignment.CENTER,ElementAlignment.BOTTOM,STYLE_TITLE,messages.getText("PARABÉNS E BOA SORTE !")));									
        return reportFooter;
    }

    protected ExpressionCollection createFunctions() {
        ExpressionCollection functions = super.createFunctions();
        
        final ElementColorFunction color = new ElementColorFunction();
        color.setName("changeColor");
        color.setElement("Variance Field");
        color.setField("color");
        color.setColorTrue(Color.WHITE);
        color.setColorFalse(Color.BLACK);
        functions.add(color);
        
        final ElementColorFunction color1 = new ElementColorFunction();
        color1.setName("changeColor1");
        color1.setElement("Variance Field 1");
        color1.setField("color");
        color1.setColorTrue(Color.BLACK);
        color1.setColorFalse(Color.WHITE);
        functions.add(color1);
        
        final ElementColorFunction color2 = new ElementColorFunction();
        color2.setName("changeColor2");
        color2.setElement("Variance Field 2");
        color2.setField("color2");
        color2.setColorTrue(Color.WHITE);
        color2.setColorFalse(Color.BLACK);
        functions.add(color2);

        return functions;
    }
}
