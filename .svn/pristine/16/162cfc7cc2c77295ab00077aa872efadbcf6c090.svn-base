package business.alert.reporthandler;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

import javax.swing.table.TableModel;

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

import business.alert.access.AlertSystemAccess;

import communication.language.Text;

public class AlertWeyerhaeuserDriverReportFactory extends AlertReportFactory {

    protected Text messages;
    protected int pages;
    protected int pageNumber;
    protected AlertSystemAccess sys;
    protected String[] labels;
    
        
    public AlertWeyerhaeuserDriverReportFactory(String name, TableModel model, AlertDivisionForm form, 
    		AlertSystemAccess sys, Text messages, int pages, int pageNumber, int count, boolean[] expired) {
        super(name, model, form);
        this.messages = messages;
        this.count = count;
        this.pages = pages;
        this.pageNumber = pageNumber;
        this.sys = sys;
        labels = sys.getUserSessionData().getFilter().getDivOrgLabels();
        parseDivisionValues(sys,messages);
    }

    protected int getWidth() {
        return 1422;
    }
    
    protected int getReportWidth() {
        return 1340;
    }

    protected ItemBand createItemBand() {
        String divOrgName =  "divorg31";

        ItemBand items = new ItemBand();
        
        Color fontColor = null;		
    	int x = 10;
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement(
        		"Variance Field", null, null,new Rectangle2D.Float(x, 0, getReportWidth(), 15), 
        		false, true));
        
        items.addElement(TextFieldElementFactory.createStringElement(
        		"Variance Field 2",new Rectangle2D.Float(x, 0, 80, 15),fontColor,
        		ElementAlignment.CENTER,ElementAlignment.MIDDLE,
        		STYLE_SIMPLE_FONT,"","corporationId"));
        x+= 80;
        items.addElement(TextFieldElementFactory.createStringElement(
        		"Variance Field 2",new Rectangle2D.Float(x, 0, 180, 15),fontColor,
        		ElementAlignment.LEFT,ElementAlignment.MIDDLE,
        		STYLE_SIMPLE_FONT,"","name"));
        x+=180;
        items.addElement(TextFieldElementFactory.createStringElement(
        		"Variance Field 2", new Rectangle2D.Float(x, 0, 180, 15),fontColor,
        		ElementAlignment.CENTER,ElementAlignment.MIDDLE,
        		STYLE_SIMPLE_FONT,"-",divOrgName));
        x+=180;
        items.addElement(TextFieldElementFactory.createStringElement(
        		"Variance Field 2",new Rectangle2D.Float(x, 0, 80, 15),fontColor,
        		ElementAlignment.CENTER,ElementAlignment.MIDDLE,
        		STYLE_SIMPLE_FONT,"-","birthdate"));
        x+=80;
     // Carne de Salud	
        items.addElement(TextFieldElementFactory.createStringElement("Variance Field 2",
        		new Rectangle2D.Float(x, 0, 100, 15),fontColor,
        		ElementAlignment.CENTER,ElementAlignment.MIDDLE,
        		STYLE_SIMPLE_FONT,"---","health"));
        x+=100;
     // Libreta de Conducir	
        items.addElement(TextFieldElementFactory.createStringElement("Variance Field 2",
        		new Rectangle2D.Float(x, 0, 100, 15),fontColor,
        		ElementAlignment.CENTER,ElementAlignment.MIDDLE,
        		STYLE_SIMPLE_FONT,"---","licenseExpiry"));
        x+=100;
                
        items.addElement(TextFieldElementFactory.createStringElement(
        		"Variance Field 2",new Rectangle2D.Float(x, 0, 150, 15),fontColor,
        		ElementAlignment.CENTER,ElementAlignment.MIDDLE,
        		STYLE_SIMPLE_FONT,"---","test"));
        x+=150;
        items.addElement(TextFieldElementFactory.createStringElement(
        		"Variance Field 2",new Rectangle2D.Float(x, 0, 60, 15),fontColor,
        		ElementAlignment.CENTER,ElementAlignment.MIDDLE,
        		STYLE_SIMPLE_FONT,"---","apt"));
        x+=60;
        items.addElement(TextFieldElementFactory.createStringElement(
        		"Variance Field 2",new Rectangle2D.Float(x, 0, 100, 15),fontColor,
        		ElementAlignment.CENTER,ElementAlignment.MIDDLE,
        		STYLE_SIMPLE_FONT,"---","expiry"));
        x+=100;
        
//        items.addElement(TextFieldElementFactory.createStringElement(
//        		"Variance Field 2",new Rectangle2D.Float(840, 0, 100, 15),fontColor,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","lastBTW"));
//        items.addElement(TextFieldElementFactory.createStringElement(
//        		"Variance Field 2",new Rectangle2D.Float(940, 0, 100, 15),fontColor,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","expiryBTW"));
        
        
            items.addElement(TextFieldElementFactory.createStringElement("Variance Field 2",
            		new Rectangle2D.Float(x, 0, 100, 15),fontColor,ElementAlignment.CENTER,
            		ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","lastRadar"));
            x+=100;
            items.addElement(TextFieldElementFactory.createStringElement(
            		"Variance Field 2",new Rectangle2D.Float(x, 0, 100, 15),fontColor,
            		ElementAlignment.CENTER,ElementAlignment.MIDDLE,
            		STYLE_SIMPLE_FONT,"---","noteRadar"));
            x+=100;
            items.addElement(TextFieldElementFactory.createStringElement(
            		"Variance Field 2",new Rectangle2D.Float(x, 0, 100, 15),fontColor,
            		ElementAlignment.CENTER,ElementAlignment.MIDDLE,
            		STYLE_SIMPLE_FONT,"---","lastInduction"));
            x+=100;
     
        return items;
    }
    
    protected ReportHeader createReportHeader() {
       // FOSADriverReportForm reportForm = (FOSADriverReportForm)form;
        ReportHeader reportHeader = new ReportHeader();
        int height = 0;
        
    	int heightHeader = 30;
    	int x = 10;
        
        
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FirstLevelColor, null,
                new Rectangle2D.Float(x, height, getReportWidth(), heightHeader), false, true));
       
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, heightHeader), 
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.identitydocument")));
        x += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 180, heightHeader), 
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.lastname")+" "+messages.getText("generic.driver.driverdata.firstname")));
        x += 180;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 180, heightHeader),
        		Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.messages.contractor")));
        x += 180;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, heightHeader),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.birthdate")));
        x += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, heightHeader),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.health")));
        x+= 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, heightHeader),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.licenseexpiry")));
        x +=80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 150, heightHeader),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.aptitudtest.query.lasttest")));
        x +=150;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 60, heightHeader),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.aptitudtest.query.isapt")));
        x+=60;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 100, heightHeader),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.aptitudtest.expiry")));
        
        x+= 100;
//        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(840, height, 100, heightHeader),
//                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.activities.last")));
//        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(940, height, 100, heightHeader),
//                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("activity.query.activityexpirybtw")));
        
      
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 100, heightHeader),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("activity.query.lastradar")));
            x+= 100;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 100, heightHeader),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("activity.query.noteradar")));
            x+= 100;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 100, heightHeader),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("activity.query.lastinduction")));
            x+= 100;
      
        return reportHeader;
    }

	

    protected ReportFooter createReportFooter() {
        ReportFooter reportFooter = new ReportFooter();
        reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, null,
                new Rectangle2D.Float(10, 10, getReportWidth(), 15), false, true));
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 160, 15),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,messages.getText("query.driver.driverdata.total")));
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement(null,new Rectangle2D.Float(getReportWidth()-80, 10, 80, 15),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,"", formatNoDecimals,"total"));
        
       return reportFooter;
    }
      
    
    protected ExpressionCollection createFunctions() {
        ExpressionCollection functions = super.createFunctions();
        
        final ElementColorFunction color = new ElementColorFunction();
        color.setName("changeColor");
        color.setElement("Variance Field");
        color.setField("color");
        color.setColorTrue(Color.WHITE);
        color.setColorFalse(DCGray2);
        functions.add(color);
        
        final ElementColorFunction fontColor = new ElementColorFunction();		
        fontColor.setName("changeFontColor");
        fontColor.setElement("Variance Field 2");
        fontColor.setField("expired");
        fontColor.setColorTrue(Color.RED);
		fontColor.setColorFalse(Color.BLACK);
        functions.add(fontColor);

        TextFormatExpression textExp = new TextFormatExpression();
        textExp.setName("URLCreateExpression");
        textExp.setPattern("./layout.jsp?refer=/driver/DriverView.jsp?id={0}");
        textExp.setField(0,"id");
        functions.add(textExp);

        CreateHyperLinksFunction link = new CreateHyperLinksFunction();
        link.setName("AddHRefs");
        link.setField("URLCreateExpression");
        link.setElement("URL-Field");
        functions.add(link);

        return functions;
    }
}
