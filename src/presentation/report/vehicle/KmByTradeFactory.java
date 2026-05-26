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
import org.jfree.report.function.ItemCountFunction;
import org.jfree.report.function.TextFormatExpression;

import presentation.report.ReportFactory;
import presentation.translator.web.util.Util;
import presentation.translator.web.vehicle.KmByTradeForm;
import business.SystemAccess;

import communication.administration.corporation.tables.CorporationData;
import communication.language.Text;
import communication.vehicle.tables.VehicleData;

public class KmByTradeFactory extends ReportFactory {

	String[] principalLabels;
	
	private SystemAccess sys;
	
    protected Text messages;
    
    private int pages;
    private int pageNumber;

    private int last;    
    
    public KmByTradeFactory(String name, TableModel model, ActionForm form,SystemAccess sys, Text messages,int pages) {
        super(name, model, form);
        this.pages = pages;
        // this.pageNumber = pageNumber;
        this.messages = messages;
        count = 1;
        parseDivisionValues(sys,messages);
        this.principalLabels = sys.getUserSessionData().getFilter().getPrincipalLabels();
        this.sys=sys;
        switch(sys.getCorporationId()) {
    		case CorporationData.MONSANTO:
    			last = 1110;
    			break;
	    	case CorporationData.ALLERGAN:
	    		last = 1030;
    			break;
        	default:	
        		last = 870;
        }
        for(int i = 0; i < principalLabels.length; i++) {
            if(!Util.empty(principalLabels[i])) {
                last += 100;
            }
        }
    }

    public KmByTradeFactory(String name, TableModel model, ActionForm form, 
    		SystemAccess sys, Text messages, int count,int pages,int pageNumber) {
        super(name, model, form);
        this.pages = pages;
        this.pageNumber = pageNumber;
        this.messages = messages;
        this.count = count;
        parseDivisionValues(sys,messages);
        this.principalLabels = sys.getUserSessionData().getFilter().getPrincipalLabels();
        this.sys=sys;
        switch(sys.getCorporationId()) {
    		case CorporationData.MONSANTO:
    			last = 1110;
    			break;
	    	case CorporationData.ALLERGAN:
	    		last = 1030;
    			break;
        	default:	
        		last = 870;
        }
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
        items.addElement(TextFieldElementFactory.createStringElement("URL-Field",new Rectangle2D.Float(10, 0, 60, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","plate"));
        items.addElement(TextFieldElementFactory.createStringElement("URL-Field-2",new Rectangle2D.Float(70, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","driverFirstName"));
        items.addElement(TextFieldElementFactory.createStringElement("URL-Field-2",new Rectangle2D.Float(150, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","driverLastName"));
        int x = 230;
        items.addElement(TextFieldElementFactory.createStringElement("director",new Rectangle2D.Float(x, 0, 60, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","director"));
        x += 60;
        if(sys.getCorporationId() == CorporationData.MONSANTO) {
            items.addElement(TextFieldElementFactory.createStringElement("superviser",new Rectangle2D.Float(x, 0, 120, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","superviser"));
            x += 120;
            items.addElement(TextFieldElementFactory.createStringElement("manager",new Rectangle2D.Float(x, 0, 120, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","manager"));
            x += 120;
        }
        
        items.addElement(TextFieldElementFactory.createStringElement("country",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","country"));
        x += 100;
        
        if(!Util.empty(principalLabels[0])) {
            items.addElement(TextFieldElementFactory.createStringElement("divorg1",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","divorg1"));
            x += 100;
        }
        if(principalLabels.length > 1) {
            if(!Util.empty(principalLabels[1])) {
                items.addElement(TextFieldElementFactory.createStringElement("divorg2",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","divorg2"));
                x += 100;
            }
            if(principalLabels.length > 2) {
                if(!Util.empty(principalLabels[2])) {
                    items.addElement(TextFieldElementFactory.createStringElement("divorg3",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","divorg3"));        
                    x += 100;
                }
            }
            if(principalLabels.length > 3) {
                if(!Util.empty(principalLabels[3])) {
                    items.addElement(TextFieldElementFactory.createStringElement("divorg4",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","divorg4"));        
                    x += 100;
                }
            }
        }
        items.addElement(TextFieldElementFactory.createStringElement("kms",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","kms"));        
        x += 100;
        items.addElement(TextFieldElementFactory.createStringElement("comment",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","comment"));        
        x += 100;
        items.addElement(TextFieldElementFactory.createStringElement("insertLast",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","insertLast"));        
        x += 100;
        items.addElement(TextFieldElementFactory.createStringElement("accidents",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","accidents"));        
        x += 80;
        items.addElement(TextFieldElementFactory.createStringElement("incidents",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","incidents"));        
        x += 80;
        if(sys.isCorporation(CorporationData.ALLERGAN)) {
	        items.addElement(TextFieldElementFactory.createStringElement("litres",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","litres"));        
	        x += 80;
	        items.addElement(TextFieldElementFactory.createStringElement("fuel",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","fuel"));        
        }       
        return items;
    }
    
    protected ReportHeader createReportHeader() {
    	KmByTradeForm reportForm = (KmByTradeForm)form;
        ReportHeader reportHeader = new ReportHeader();
        int height = writeFilter(reportForm, reportHeader);
        
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FirstLevelColor, null,
                new Rectangle2D.Float(10, height, getReportWidth(), 18), false, true));
       
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 60, 18),Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.matricula")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(70, height, 80, 18),Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.firstname")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(150, height, 80, 18),Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.lastname")));
        int x = 230;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 60, 18),Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.director")));
        x += 60;
        if(sys.getCorporationId() == CorporationData.MONSANTO) {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 120, 18),Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.superviser")));
            x += 120;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 120, 18),Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.manager")));
            x += 120;
        }
        
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 100, 18),Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.country")));
        x += 100;
        
        for(int i = 0; i < principalLabels.length; i++) {
            if(!Util.empty(principalLabels[i])) {
                reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 100, 18),Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(principalLabels[i])));
                x += 100;
            }
        }
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 100, 18),Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.kmbytrade.kms")));
        x += 100;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 100, 18),Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.messages.comment")));
        x += 100;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 100, 18),Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.kmbytrade.insertlast")));
        x += 100;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.kmbytrade.accidents")));
        x += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.kmbytrade.incidents")));
        x += 80;
        if(sys.isCorporation(CorporationData.ALLERGAN)) {
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.kmbytrade.litres")));
	        x += 80;
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 100, 18),Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.kmbytrade.fuel")));
        }        
        return reportHeader;
    }

	protected int writeFilter(KmByTradeForm reportForm, ReportHeader reportHeader) {
		reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 25), false, true));        
		reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, getReportWidth(), 25),
        		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,name));
        
        int height = 25;
        int maxHeight=height;         
        
        int statusId = Integer.parseInt(reportForm.getStatus());
        String status = (statusId == VehicleData.ACTIVE) ? messages.getText("generic.messages.active") : (statusId == VehicleData.EXCLUDED) ? messages.getText("generic.messages.excluded") : messages.getText("generic.messages.all.pl");
        		
        height = writeParametersTitle(messages.getText("query.mileage.parameters"),reportHeader,height,10,220);
        height = writeParameter(messages.getText("query.mileage.from")+":",((KmByTradeForm)form).getFrom(messages),reportHeader,height,10,60,70,160);
        height = writeParameter(messages.getText("query.mileage.to")+":",((KmByTradeForm)form).getTo(messages),reportHeader,height,10,60,70,160);
        height = writeParameter(messages.getText("generic.vehicle.vehicledata.matricula")+":",reportForm.getPlate(),reportHeader,height,10,60,70,160);
        height = writeParameter(messages.getText("generic.activity.driverdata.status")+":",status,reportHeader,height,10,60,70,160);
        
        maxHeight=Math.max(maxHeight,height);
        height = 25;

        height = writeParametersTitle(messages.getText("generic.driver.divgeo"),reportHeader,height,getReportWidth()-440,180);         
        for(int i = 0; i < 3; i++) {
            if(divGeoLabels[i]!=null)
                height = writeParameter(divGeoLabels[i]+":",divGeoValues[i],reportHeader,height,getReportWidth()-440,100,getReportWidth()-340,80);
        }        
        
        maxHeight=Math.max(maxHeight,height);
        height = 25;
       
        String[] divOrgs = sys.getUserSessionData().getFilter().getLevelLabels();
        for(int i = 0; i < divOrgs.length; i++) {
           if(!Util.empty(divOrgs[i])) {
               height = writeParametersTitle(messages.getText(divOrgs[i]),reportHeader,height,getReportWidth()-180,180);
               for(int j = 0; j < 3; j++) {
                   if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 1 && j== 0) && 
                           !(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 2 && j== 0)) {
                       if(!Util.empty(divOrgLabels[i][j])) {
                           height = writeParameter(divOrgLabels[i][j]+":",divOrgValues[i][j],reportHeader,height,getReportWidth()-180,80,getReportWidth()-100,100);
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
                new Rectangle2D.Float(10, 10, getReportWidth(), 20), false, true));
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 220, 20),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,messages.getText("query.driver.driverdata.total")));
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement(null,new Rectangle2D.Float(getReportWidth()-100, 10, 100, 20),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,"", "#####","total"));
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
        textExp.setPattern("./layout.jsp?refer=/vehicle/VehicleView.jsp?id={0}");
        textExp.setField(0,"vehicleid");
        functions.add(textExp);

        CreateHyperLinksFunction link = new CreateHyperLinksFunction();
        link.setName("AddHRefs");
        link.setField("URLCreateExpression");
        link.setElement("URL-Field");
        functions.add(link);
        
        TextFormatExpression textExp2 = new TextFormatExpression();
        textExp2.setName("URLCreateExpression2");
        textExp2.setPattern("./layout.jsp?refer=/driver/DriverView.jsp?id={0}");
        textExp2.setField(0,"driverid");
        functions.add(textExp2);

        CreateHyperLinksFunction link2 = new CreateHyperLinksFunction();
        link2.setName("AddHRefs2");
        link2.setField("URLCreateExpression2");
        link2.setElement("URL-Field-2");
        functions.add(link2);

        return functions;
    }
}


