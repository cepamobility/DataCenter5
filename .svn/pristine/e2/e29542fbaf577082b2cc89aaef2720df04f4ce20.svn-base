package presentation.report.driver;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

import javax.swing.table.TableModel;

import org.apache.struts.action.ActionForm;
import org.jfree.report.ElementAlignment;
import org.jfree.report.ItemBand;
import org.jfree.report.ReportFooter;
import org.jfree.report.ReportHeader;
import org.jfree.report.elementfactory.DateFieldElementFactory;
import org.jfree.report.elementfactory.LabelElementFactory;
import org.jfree.report.elementfactory.NumberFieldElementFactory;
import org.jfree.report.elementfactory.StaticShapeElementFactory;
import org.jfree.report.elementfactory.TextFieldElementFactory;
import org.jfree.report.function.CreateHyperLinksFunction;
import org.jfree.report.function.ElementColorFunction;
import org.jfree.report.function.ExpressionCollection;
import org.jfree.report.function.ItemCountFunction;
import org.jfree.report.function.TextFormatExpression;

import presentation.report.ReportFactory;
import presentation.translator.web.driver.TelematicControlQueryForm;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.language.Text;

public class TelematicControlReportFactory extends ReportFactory {

    protected Text messages;
    private SystemAccess sys;
    
    public TelematicControlReportFactory(String name, TableModel model, ActionForm form, SystemAccess sys) {
        super(name, model, form);
        this.sys = sys;
        this.messages = sys.getText();
        parseDivisionValues(sys, messages);
    }

    protected int getWidth() {
    	return getReportWidth() + 82;
    }

    protected int getReportWidth() {
   		return 960;
    }
    
    protected ItemBand createItemBand() {
        ItemBand items = super.createItemBand();
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,new Rectangle2D.Float(10, 0, getReportWidth(), 15), false, true));
        items.addElement(TextFieldElementFactory.createStringElement("URL-Field",new Rectangle2D.Float(10, 0, 200, 15),
        		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","driver"));
        items.addElement(TextFieldElementFactory.createStringElement("divorg1",new Rectangle2D.Float(210, 0, 100, 15),
        		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","divorg1"));
        items.addElement(TextFieldElementFactory.createStringElement("vehicle",new Rectangle2D.Float(310, 0, 80, 15),
        		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","vehicle"));
        items.addElement(TextFieldElementFactory.createStringElement("date", new Rectangle2D.Float(390, 0, 80, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","date"));
        items.addElement(TextFieldElementFactory.createStringElement("time", new Rectangle2D.Float(470, 0, 80, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","time"));
        items.addElement(TextFieldElementFactory.createStringElement("type", new Rectangle2D.Float(550, 0, 100, 15),
        		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","type"));
        items.addElement(TextFieldElementFactory.createStringElement("location", new Rectangle2D.Float(650, 0, 150, 15),
        		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","location"));
        items.addElement(TextFieldElementFactory.createStringElement("speed", new Rectangle2D.Float(800, 0, 80, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","speed"));
        items.addElement(TextFieldElementFactory.createStringElement("duration", new Rectangle2D.Float(880, 0, 80, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","duration"));
        
        return items;
    }
    
    protected ReportHeader createReportHeader() {
    	TelematicControlQueryForm reportForm = (TelematicControlQueryForm)form;
        ReportHeader reportHeader = new ReportHeader();
        int height = writeFilter(reportForm, reportHeader);
                
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FirstLevelColor, null,
                new Rectangle2D.Float(10, height, getReportWidth(), 18), false, true));
       
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 200, 18), 
        		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
        		messages.getText("generic.driver.driverdata.firstname")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(210, height, 100, 18), 
        		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
        		messages.getText("divorg.ecolab.departamento")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(310, height, 80, 18), 
        		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
        		messages.getText("generic.driver.driverdata.vehicle")));
	    reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(390, height, 80, 18),
	        		Color.WHITE,ElementAlignment.CENTER, ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.telematic.date")));
	    reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(470, height, 80, 18),
	        		Color.WHITE,ElementAlignment.CENTER, ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.hour")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(550, height, 100, 18),
        		Color.WHITE,ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.telematic.type")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(650, height, 150, 18),
        		Color.WHITE,ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.activity.location")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(800, height, 80, 18),
        		Color.WHITE,ElementAlignment.CENTER, ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.fine.speed")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(880, height, 80, 18),
        		Color.WHITE,ElementAlignment.CENTER, ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.telematic.duration")));
        
        return reportHeader;
    }

    /**
     * 
     * @param reportForm
     * @param reportHeader
     * @return
     */
	protected int writeFilter(TelematicControlQueryForm reportForm, ReportHeader reportHeader) {
		
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 25), false, true));
		reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, getReportWidth(), 25),
        		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("query.telematiccontrolquery.title")));
        
        int height = 35;

        height = writeParametersTitle(messages.getText("generic.driver.personaldata"),reportHeader,height,10,160);
        
        height = writeParameter(messages.getText("generic.driver.driverdata.firstname")+":",reportForm.getFirstname(),reportHeader,height,10,80,90,80);
        height = writeParameter(messages.getText("generic.driver.driverdata.lastname")+":",reportForm.getLastname(),reportHeader,height,10,80,90,80);
        height = writeParameter(messages.getText("generic.driver.driverdata.status")+":",messages.getText(reportForm.getStatusString()),reportHeader,height,10,80,90,80);

        height = writeParametersTitle(messages.getText("generic.driver.divgeo"),reportHeader,height,10,160); 
        for(int i = 0; i < 3; i++) {
            if(divGeoLabels[i]!=null) {
                height = writeParameter(divGeoLabels[i]+":",divGeoValues[i],reportHeader,height,10,80,90,80);
            }
        }
        
        int maxHeight = height;
        height = 35;
       
        String[] divOrgs = sys.getUserSessionData().getFilter().getLevelLabels();
        for(int i = 0; i < divOrgs.length; i++) {
            if(!Util.empty(divOrgs[i])) {
                height = writeParametersTitle(messages.getText(divOrgs[i]),reportHeader,height,310,160);
                for(int j = 0; j < 3; j++) {
                    if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 1 && j== 0) && 
                            !(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 2 && j== 0)) {
                        if(!Util.empty(divOrgLabels[i][j])) {
                            height = writeParameter(divOrgLabels[i][j]+":",divOrgValues[i][j],reportHeader,height,310,80,390,80);
                        }
                    }
                }
            }
        }
        maxHeight=Math.max(maxHeight,height);

        height = writeParametersTitle(messages.getText("generic.driver.telematiccontrol"),reportHeader,height,310,160);
        height = writeParameter(messages.getText("driver.query.from")+":",reportForm.getFrom(messages),reportHeader,height,310,80,390,80);
        height = writeParameter(messages.getText("driver.query.to")+":",reportForm.getTo(messages),reportHeader,height,310,80,390,80);
        height = writeParameter(messages.getText("driver.telematic.type")+":",reportForm.getType(messages),reportHeader,height,310,80,390,80);

        maxHeight=Math.max(maxHeight,height);
        

        return maxHeight;
	}

	/**
	 * 
	 */
    protected int writeParameter(String parameterName,String parameterValue,ReportHeader reportHeader,int height,int xLabel,int wLabel,int xValue,int wValue) {
    	 
		
    	if(!Util.empty(parameterValue)) {
    		reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(xLabel, height, wLabel, 18),
                    Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,parameterName));
    		reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(xValue, height, wValue, 18),
                    Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,parameterValue));
    		return height+20;
    	}else if(!Util.empty(parameterName)){ 
    		reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(xLabel, height, wLabel, 18),
                    Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,parameterName));
    		reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(xValue, height, wValue, 18),
                    Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"--"));
        	return height+20;
        }
    	return height;
    }
    
    
    /**
     * 
     */
    protected int writeParametersTitle(String parameterName,ReportHeader reportHeader,int height,int xLabel,int wLabel) {
    		reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(xLabel, height, wLabel, 18),
                    Color.blue,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE_PARAM_FONT,parameterName)); 
    		return height+20;
    }
    
    /*
     * 
     * (non-Javadoc)
     * @see presentation.report.ReportFactory#createReportFooter()
     */
    protected ReportFooter createReportFooter() {
    	int height = 40;
    	
        ReportFooter reportFooter = new ReportFooter();
        reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, null,
                new Rectangle2D.Float(10, 10, getReportWidth(), 20), false, true));
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 200, 20),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,messages.getText("query.driver.driverdata.total")));
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement(null,new Rectangle2D.Float(780, 10, 80, 20),
                Color.WHITE,ElementAlignment.RIGHT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,"", "#####","total"));
        return reportFooter;
    }

    /**
     * 
     */
    protected ExpressionCollection createFunctions() {
        ExpressionCollection functions = super.createFunctions();
        
        final ItemCountFunction countFunction = new ItemCountFunction();
        countFunction.setName("countExpression");
        countFunction.setGroup("dummy");
        functions.add(countFunction);
        
        final ElementColorFunction color = new ElementColorFunction();
        color.setName("changeColor");
        color.setElement("Variance Field");
        color.setField("color");
        color.setColorTrue(Color.WHITE);
        color.setColorFalse(DCGray2);
        functions.add(color);

        TextFormatExpression textExp = new TextFormatExpression();
        textExp.setName("URLCreateExpression");
        textExp.setPattern("./layout.jsp?refer=/driver/DriverView.jsp?id={0}#telematicControl");
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
