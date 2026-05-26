package presentation.report.activity;

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
import org.jfree.report.function.ItemCountFunction;
import org.jfree.report.function.TextFormatExpression;

import presentation.report.ReportFactory;
import presentation.translator.web.activity.schedule.DriverWithOutECForm;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.driver.tables.DriverData;
import communication.language.Text;

public class DriversWithOutECFactory extends ReportFactory {

	String[] principalLabels;
	
	private SystemAccess sys;
	
    protected Text messages;
    
    private int pages;
    private int pageNumber;

    private int last;    
    
    public DriversWithOutECFactory(String name, TableModel model, ActionForm form, 
    		SystemAccess sys, Text messages,int pages,int pageNumber) {
        super(name, model, form);
        this.pages = pages;
        this.pageNumber = pageNumber;
        this.messages = messages;
        count = 1;
        parseDivisionValues(sys,messages);
        this.principalLabels = sys.getUserSessionData().getFilter().getPrincipalLabels();
        this.sys=sys;
        //last = 410;
        last = 310;
        for(int i = 0; i < principalLabels.length; i++) {
            if(!Util.empty(principalLabels[i])) {
                last += 120;
            }
        }
    }

    public DriversWithOutECFactory(String name, TableModel model, ActionForm form, 
    		SystemAccess sys, Text messages, int count,int pages,int pageNumber) {
        super(name, model, form);
        this.pages = pages;
        this.pageNumber = pageNumber;
        this.messages = messages;
        this.count = count;
        parseDivisionValues(sys,messages);
        this.principalLabels = sys.getUserSessionData().getFilter().getPrincipalLabels();
        this.sys=sys;
        //last = 470;
        last = 310;
        for(int i = 0; i < principalLabels.length; i++) {
            if(!Util.empty(principalLabels[i])) {
                last += 120;
            }
        }
    }

    protected int getWidth() {
    	return last + 82;
    }
    
    protected int getReportWidth() {
    	return last;
    }

    protected ItemBand createItemBand() {
        ItemBand items = super.createItemBand();
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,new Rectangle2D.Float(10, 0, getReportWidth(), 15), false, true));
        items.addElement(TextFieldElementFactory.createStringElement("URL-Field",new Rectangle2D.Float(10, 0, 200, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","name"));
        items.addElement(TextFieldElementFactory.createStringElement("country",new Rectangle2D.Float(210, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","country"));        
        int x = 310;
        if(!Util.empty(principalLabels[0])) {
            items.addElement(TextFieldElementFactory.createStringElement("divorg1",new Rectangle2D.Float(x, 0, 120, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","divorg1"));
            x += 120;
        }
        if(principalLabels.length > 1) {
            if(!Util.empty(principalLabels[1])) {
                items.addElement(TextFieldElementFactory.createStringElement("divorg2",new Rectangle2D.Float(x, 0, 120, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","divorg2"));
                x += 120;
            }
            if(principalLabels.length > 2) {
                if(!Util.empty(principalLabels[2])) {
                    items.addElement(TextFieldElementFactory.createStringElement("divorg3",new Rectangle2D.Float(x, 0, 120, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","divorg3"));        
                    x += 120;
                }
            }
        }
       
        return items;
    }
    
    protected ReportHeader createReportHeader() {
    	DriverWithOutECForm reportForm = (DriverWithOutECForm)form;
        ReportHeader reportHeader = new ReportHeader();
        int height = writeFilter(reportForm, reportHeader);
        
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FirstLevelColor, null,
                new Rectangle2D.Float(10, height, getReportWidth(), 18), false, true));
       
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 200, 18), 
        		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.firstname")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(210, height, 100, 18),
         		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(divGeoLabels[1])));        
        int x = 310;
        for(int i = 0; i < principalLabels.length; i++) {
            if(!Util.empty(principalLabels[i])) {
                reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 120, 18),
                       Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(principalLabels[i])));
                x += 120;
            }
        }
        
        return reportHeader;
    }

	protected int writeFilter(DriverWithOutECForm reportForm, ReportHeader reportHeader) {
		reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 25), false, true));        
		reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, getReportWidth(), 25),
        		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText(name)));
        
        int height = 25;
        int maxHeight=height;         
        
        String status = (reportForm.getStatus() == DriverData.ACTIVE)? messages.getText("generic.messages.active"):
        		(reportForm.getStatus() == DriverData.EXCLUDED)?messages.getText("generic.messages.excluded"):
        			messages.getText("generic.messages.all.pl");
        		
        height = writeParametersTitle(messages.getText("generic.activity.driverdata"),reportHeader,height,10,200);
        height = writeParameter(messages.getText("generic.activity.driverdata.status")+":",
        		messages.getText(status),reportHeader,height,10,70,80,130);
        
        height = writeParametersTitle(messages.getText("generic.driver.divgeo"),reportHeader,height,10,200);         
        for(int i = 0; i < 3; i++) {
            if(divGeoLabels[i]!=null)
        	height = writeParameter(divGeoLabels[i]+":",divGeoValues[i],reportHeader,height,10,70,80,130);
        }        
        
        maxHeight=Math.max(maxHeight,height);
        height = 25;
       
        String[] divOrgs = sys.getUserSessionData().getFilter().getLevelLabels();
        for(int i = 0; i < divOrgs.length; i++) {
           if(!Util.empty(divOrgs[i])) {
               height = writeParametersTitle(messages.getText(divOrgs[i]),reportHeader,height,210,320);
               for(int j = 0; j < 3; j++) {
                   if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 1 && j== 0) && 
                           !(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 2 && j== 0)) {
                       if(!Util.empty(divOrgLabels[i][j])) {
                           height = writeParameter(divOrgLabels[i][j]+":",divOrgValues[i][j],reportHeader,height,210,100,310,120);
                       }
                   }
               }
           }
       }
       maxHeight=Math.max(maxHeight,height);      
       
       return maxHeight;
	}

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

    protected int writeParametersTitle(String parameterName,ReportHeader reportHeader,int height,int xLabel,int wLabel) {
    		reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(xLabel, height, wLabel, 18),
                    Color.blue,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE_PARAM_FONT,parameterName)); 
    		return height+20;
    }
       
    protected ReportFooter createReportFooter() {
    	int height = 40;
    	
        ReportFooter reportFooter = new ReportFooter();
        reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, null,
                new Rectangle2D.Float(10, 18, getReportWidth(), 18), false, true));
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 18, 200, 18),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,messages.getText("query.driver.driverdata.total")));
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement(null,new Rectangle2D.Float(getReportWidth()-120, 18, 120, 18),
                Color.WHITE,ElementAlignment.RIGHT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,"", "#####","total"));
        if(((DriverWithOutECForm)form).getOutput().equals("HTML")) {
            int left = 60;
            reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(0, 40, 50, 15),
                    Color.BLUE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_TITLE_PARAM_FONT,messages.getText("generic.messages.pages")));
            for(int i = 1; i <= pages; i++) {
                int width = (i < 10) ? 15 : 20; 
                
                if (left>getReportWidth()){
                	left=60;
                	height+=15;
                }                
                
                if(i != pageNumber) {
                    reportFooter.addElement(LabelElementFactory.createLabelElement("URL-Field-"+i,new Rectangle2D.Float(left, height, width, 15),
                            Color.BLUE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_TITLE_PARAM_FONT,String.valueOf(i)));
                }else {
                    reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(left, height, width, 15),
                            Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(i)));
                }
                left += width;
            }
        }
        return reportFooter;
    }

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
        textExp.setPattern("./layout.jsp?refer=/driver/DriverView.jsp?id={0}");
        textExp.setField(0,"id");
        functions.add(textExp);

        CreateHyperLinksFunction link = new CreateHyperLinksFunction();
        link.setName("AddHRefs");
        link.setField("URLCreateExpression");
        link.setElement("URL-Field");
        functions.add(link);
        
        if(((DriverWithOutECForm)form).getOutput().equals("HTML")) {
            for(int i = 1; i <= pages; i++) {
                if(i != pageNumber) {
                    TextFormatExpression textExp2 = new TextFormatExpression();
                    textExp2.setName("URLCreateExpression"+i);
                    textExp2.setPattern("./layout.jsp?refer=/activity/schedule/DriversWithOutECReportPage.jsp?page="+i+((DriverWithOutECForm)form).getLink());
                    functions.add(textExp2);
            
                    CreateHyperLinksFunction link2 = new CreateHyperLinksFunction();
                    link2.setName("AddHRefs"+i);
                    link2.setField("URLCreateExpression"+i);
                    link2.setElement("URL-Field-"+i);
                    functions.add(link2);
                }
            }
        }
        

        return functions;
    }
}

