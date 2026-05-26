/**
 * Created on 10-jul-2006
 * CEPA
 * DataCenter 5
 */
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
import org.jfree.report.style.FontDefinition;

import presentation.report.ReportFactory;
import presentation.translator.web.activity.schedule.DriversBTWAssistForm;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.administration.corporation.tables.CorporationData;
import communication.language.Text;

public class DriversBTWAssistFactory extends ReportFactory {

	String[] principalLabels;
	
	private SystemAccess sys;
	
    protected Text messages;
    
    private int pages;
    private int pageNumber;

    private int last;
    private int corporationId;
    
    protected static final FontDefinition STYLE_TITLE= new FontDefinition("SansSerif",13,true,false,false,false);
    protected static final FontDefinition STYLE_SIMPLE_FONT= new FontDefinition("SansSerif",8);
    protected static final FontDefinition STYLE_SIMPLE_BOLD_FONT= new FontDefinition("SansSerif",8,true,false,false,false);
    protected static final FontDefinition STYLE_TOTAL_FONT= new FontDefinition("SansSerif",12,true,false,false,false);
    protected static final FontDefinition STYLE_TITLE_PARAM_FONT= new FontDefinition("SansSerif",9,true,false,true,false);
    
    public DriversBTWAssistFactory(String name, TableModel model, ActionForm form, SystemAccess sys, Text messages,int pages,int pageNumber) {
        super(name, model, form);
        this.pages = pages;
        this.pageNumber = pageNumber;
        this.messages = messages;
        count = 1;
        parseDivisionValues(sys,messages);
        this.principalLabels = sys.getUserSessionData().getFilter().getPrincipalLabels();
        this.sys=sys;
        this.corporationId = sys.getUserSessionData().getFilter().getCorporationId().intValue();
        last = (corporationId == CorporationData.MONSANTO) ? 690 : 470;
        for(int i = 0; i < principalLabels.length; i++) {
            if(!Util.empty(principalLabels[i])) {
                last += 100;
            }
        }
    }

    public DriversBTWAssistFactory(String name, TableModel model, ActionForm form, SystemAccess sys, Text messages, int count,int pages,int pageNumber) {
        super(name, model, form);
        this.pages = pages;
        this.pageNumber = pageNumber;
        this.messages = messages;
        this.count = count;
        parseDivisionValues(sys,messages);
        this.principalLabels = sys.getUserSessionData().getFilter().getPrincipalLabels();
        this.sys=sys;
        this.corporationId = sys.getUserSessionData().getFilter().getCorporationId().intValue();
        last = (corporationId == CorporationData.MONSANTO) ? 570 : 470;
        for(int i = 0; i < principalLabels.length; i++) {
            if(!Util.empty(principalLabels[i])) {
                last += 100;
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
        items.addElement(TextFieldElementFactory.createStringElement("URL-Field",new Rectangle2D.Float(10, 0, 160, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","name"));
        items.addElement(TextFieldElementFactory.createStringElement("country",new Rectangle2D.Float(170, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","country"));        
        int x = 270;
        if(!Util.empty(principalLabels[0])) {
            items.addElement(TextFieldElementFactory.createStringElement("division",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","division"));
            x += 100;
        }
        if(principalLabels.length > 1) {
            if(!Util.empty(principalLabels[1])) {
                items.addElement(TextFieldElementFactory.createStringElement("subdivision",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","subdivision"));
                x += 100;
            }
            if(principalLabels.length > 2) {
                if(!Util.empty(principalLabels[2])) {
                    items.addElement(TextFieldElementFactory.createStringElement("unit",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","unit"));        
                    x += 100;
                    if(principalLabels.length > 3) {
                        if(!Util.empty(principalLabels[3])) {
                            items.addElement(TextFieldElementFactory.createStringElement("safety",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","safety"));        
                            x += 100;
                        }
                    }
                }
            }
        }
        if(corporationId == CorporationData.MONSANTO) {
            items.addElement(TextFieldElementFactory.createStringElement("allowed",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","allowed"));        
            x += 100;
            items.addElement(TextFieldElementFactory.createStringElement("licenseObs",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","licenseObs"));        
            x += 120;
        }
        
        if(corporationId == CorporationData.JJ) {
        	// WWID:
       		items.addElement(TextFieldElementFactory.createStringElement("corporationId",new Rectangle2D.Float(x, 0, 90, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","corporationId"));
       		x += 90;
        } 
        
        items.addElement(TextFieldElementFactory.createStringElement("activitytype",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","activitytype"));        
        x+=100;
        items.addElement(TextFieldElementFactory.createStringElement("lastdate",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","lastdate"));        
        return items;
    }
    
    protected ReportHeader createReportHeader() {
    	DriversBTWAssistForm reportForm = (DriversBTWAssistForm)form;
        ReportHeader reportHeader = new ReportHeader();
        int height = writeFilter(reportForm, reportHeader);
        
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FirstLevelColor, null,
                new Rectangle2D.Float(10, height, getReportWidth(), 18), false, true));
       
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 160, 18), 
        		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.firstname")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(170, height, 100, 18),
         		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(divGeoLabels[1])));        
        
        int x = 270;
        for(int i = 0; i < principalLabels.length; i++) {
            if(!Util.empty(principalLabels[i])) {
                reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 100, 18),
                       Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(principalLabels[i])));
                x += 100;
            }
        }
        if(corporationId == CorporationData.MONSANTO) {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 100, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.vehicleallowed")));        
            x += 100;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 100, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.licenseobs")));        
            x += 120;
        }
        
        if(corporationId == CorporationData.JJ) {
        	// WWID
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 90, 18),
            		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.corporationid")));
       		x += 90;
        }  
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 100, 18),
         		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.activityexpiry.activitytype")));        
        x += 100;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 100, 18),
         		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.messages.assistance.lastdate")));        
        return reportHeader;
    }

	protected int writeFilter(DriversBTWAssistForm reportForm, ReportHeader reportHeader) {
		reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 25), false, true));        
		reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, getReportWidth(), 25),
        		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText(name)));
        
        int height = 25;
        int maxHeight=height;
      
        height = writeParametersTitle(messages.getText("query.activityexpiry.basicdata"),reportHeader,height,10,160);
        height = writeParameter(messages.getText("query.activityexpiry.activitytype")+":",messages.getText(reportForm.getType()),reportHeader,height,10,60,70,100);
        height = writeParameter(messages.getText("generic.activity.driverdata.status")+":",messages.getText(reportForm.getStatus()),reportHeader,height,10,60,70,100);       
        if(corporationId == CorporationData.MONSANTO) {
            String allowed = (reportForm.getVehicleAllowed() != null && reportForm.getVehicleAllowed().length() == 0) ? "generic.messages.all" : reportForm.getVehicleAllowed();
            height = writeParameter(messages.getText("generic.driver.driverdata.vehicleallowed")+":",messages.getText(allowed),reportHeader,height,10,60,70,100);
        }
        height = writeParameter(messages.getText("query.driversbtwassist.asistence"),messages.getText(reportForm.getAssistence()),reportHeader,height,10,60,70,100);

        height = writeParametersTitle(messages.getText("generic.driver.divgeo"),reportHeader,height,10,160);         
        for(int i = 0; i < 3; i++) {
            if(divGeoLabels[i]!=null)
        	height = writeParameter(divGeoLabels[i]+":",divGeoValues[i],reportHeader,height,10,60,70,100);
        }        
        
        maxHeight=Math.max(maxHeight,height);
        height = 25;
       
        String[] divOrgs = sys.getUserSessionData().getFilter().getLevelLabels();
        for(int i = 0; i < divOrgs.length; i++) {
           if(!Util.empty(divOrgs[i])) {
               height = writeParametersTitle(messages.getText(divOrgs[i]),reportHeader,height,260,200);
               for(int j = 0; j < 3; j++) {
                   if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 1 && j== 0) && 
                           !(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 2 && j== 0)) {
                       if(!Util.empty(divOrgLabels[i][j])) {
                           height = writeParameter(divOrgLabels[i][j]+":",divOrgValues[i][j],reportHeader,height,260,100,360,100);
                       }
                   }
               }
           }
       }
       maxHeight=Math.max(maxHeight,height);

       /*
       height = 25;
       height = writeParametersTitle(messages.getText("driver.query.period"),reportHeader,height,460,200);
       height = writeParameter(messages.getText("driver.query.from")+":",reportForm.getFrom(messages),reportHeader,height,460,100,560,100);
       height = writeParameter(messages.getText("driver.query.to")+":",reportForm.getTo(messages),reportHeader,height,460,100,560,100);
       */
       
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
                new Rectangle2D.Float(10, 10, getReportWidth(), 20), false, true));
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 160, 20),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,messages.getText("query.driver.driverdata.total")));
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement(null,new Rectangle2D.Float(last, 10, 90, 20),
                Color.WHITE,ElementAlignment.RIGHT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,"", "#####","total"));
        if(((DriversBTWAssistForm)form).getOutput().equals("HTML")) {
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
        
        if(((DriversBTWAssistForm)form).getOutput().equals("HTML")) {
            for(int i = 1; i <= pages; i++) {
                if(i != pageNumber) {
                    TextFormatExpression textExp2 = new TextFormatExpression();
                    textExp2.setName("URLCreateExpression"+i);
                    textExp2.setPattern("./layout.jsp?refer=/activity/schedule/driversBTWAssistReportPage.jsp?page="+i+((DriversBTWAssistForm)form).getLink());
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
