
package presentation.report.driver;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.text.Format;
import java.text.SimpleDateFormat;

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
import presentation.translator.web.driver.DriverHRDMonQueryForm;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.driver.tables.DriverData;
import communication.language.Text;

public class DetailedDriverHRDMonQueryFactory extends ReportFactory {

    protected Text messages;
    private SystemAccess sys;
    private int last;
    private int[][] principal;
    private int pages;
    private int pageNumber;
    private boolean flag = false; // este flag habilita o deshabilita mostrar
    // los datos del HRD Monsanto Brasil, es para no mostrar datos cuando se esta
    // corriendo el script phrd.sql en el CRON
    
    /**
     * 
     * @param name
     * @param model
     * @param form
     * @param sys
     * @param messages
     */
    public DetailedDriverHRDMonQueryFactory(String name, TableModel model, ActionForm form,
    		SystemAccess sys, Text messages, int count, int pages, int pageNumber) {
        super(name, model, form);
        this.messages = messages;
        this.count = count;
        this.pages = pages;
        this.pageNumber = pageNumber;
        parseDivisionValues(sys,messages);
        this.sys = sys;
        last = 1330;
        principal = sys.getUserSessionData().getFilter().getPrincipalDivOrgs();
        for(int i = 0; i < 3; i++) {
            if(principal[i][0] != 0) {
                last += 100;
            }
        }
    }


    public void setFlag(boolean flag){
    	this.flag = flag;
    }
    
    /**
     * 
     */
    protected int getWidth() {
        return last+82;
    }

    protected int getReportWidth() {
    	return last;
    }
    
    protected ItemBand createItemBand() {
        ItemBand items = super.createItemBand();
        
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field",null, null,new Rectangle2D.Float(10, 0, getReportWidth(), 15), false, true));
        items.addElement(TextFieldElementFactory.createStringElement("URL-Field",new Rectangle2D.Float(10, 0, 80, 15),Color.BLACK,
        		ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","firstname"));
        items.addElement(TextFieldElementFactory.createStringElement("URL-Field",new Rectangle2D.Float(90, 0, 80, 15),Color.BLACK,
        		ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","lastname"));
        items.addElement(TextFieldElementFactory.createStringElement("URL-Field22", new Rectangle2D.Float(170, 0, 80, 15),Color.BLACK,
        		ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","vehiclePlate"));
        items.addElement(TextFieldElementFactory.createStringElement("country",new Rectangle2D.Float(250, 0, 100, 15),Color.BLACK,
        		ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","country")); 
        items.addElement(TextFieldElementFactory.createStringElement("unit",new Rectangle2D.Float(350, 0, 100, 15),Color.BLACK,
                ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","divorg11"));
        int x = 450;
        for(int i = 0; i < 3; i++) {
            if(principal[i][0] != 0) {
                items.addElement(TextFieldElementFactory.createStringElement("unit",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,
                		ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","divorg"+String.valueOf(i+1)));
                x += 100;
            }
        } 
        items.addElement(TextFieldElementFactory.createStringElement("conditional",new Rectangle2D.Float(x, 0,100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","conditional"));
        x+=100; 
        items.addElement(TextFieldElementFactory.createStringElement("hasHRD",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","hasHRD"));       
        x+=80;
        items.addElement(DateFieldElementFactory.createDateElement("lastHRD",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",DATE_FORMAT,"lastHRD"));
        x+=80;
        items.addElement(DateFieldElementFactory.createDateElement("lastTOJ",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",DATE_FORMAT,"lastTOJ"));
        x+=80;
        items.addElement(TextFieldElementFactory.createStringElement("date",new Rectangle2D.Float(x, 0,60, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","date"));
        x+=60; 
        items.addElement(TextFieldElementFactory.createStringElement("type",new Rectangle2D.Float(x, 0,100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","type"));
        x+=100; 
        items.addElement(TextFieldElementFactory.createStringElement("clasification",new Rectangle2D.Float(x, 0,150, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","clasification"));
        x+=150; 
        items.addElement(TextFieldElementFactory.createStringElement("result",new Rectangle2D.Float(x, 0,150, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","result"));
        x+=150; 
        items.addElement(TextFieldElementFactory.createStringElement("hrdConditions",new Rectangle2D.Float(x, 0,80, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","hrdConditions"));
        return items;
    }
    
    protected ReportHeader createReportHeader() {
        DriverHRDMonQueryForm reportForm = (DriverHRDMonQueryForm)form;
        ReportHeader reportHeader = new ReportHeader();
        int height = writeFilter(reportForm, reportHeader);
                
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FirstLevelColor, null,
                new Rectangle2D.Float(10, height, getReportWidth(), 18), false, true));
       
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 80, 18),Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.firstname")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(90, height, 80, 18),Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.lastname")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(170, height, 80, 18), 
        		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.vehicle")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(250, height, 100, 18),Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.country")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(350, height, 100, 18),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,divOrgLabels[0][0]));
        int x = 450;
        for(int i = 0; i < 3; i++) {
            if(principal[i][0] != 0) {
                reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 100, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,divOrgLabels[principal[i][0]-1][principal[i][1]-1]));
                x += 100;
            }
        }
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x,height,100,18),Color.WHITE, ElementAlignment.CENTER, ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.hrdconditial")));
        x+=100;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x,height,80,18),
                Color.WHITE, ElementAlignment.CENTER, ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.hashrd")));
        x+=80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x,height,80,18),
        		Color.WHITE, ElementAlignment.CENTER, ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.lasthrd")));
        x+=80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x,height,80,18),
        		Color.WHITE, ElementAlignment.CENTER, ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.lasttoj")));
        x+=80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x,height,60,18),Color.WHITE, ElementAlignment.CENTER, ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.date")));
        x+=60; 
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x,height,100,18),Color.WHITE, ElementAlignment.CENTER, ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.type")));
        x+=100; 
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x,height,150,18),Color.WHITE, ElementAlignment.CENTER, ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.clasification")));
        x+=150; 
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x,height,150,18),Color.WHITE, ElementAlignment.CENTER, ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.result")));
        x+=150; 
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x,height,80,18),Color.WHITE, ElementAlignment.CENTER, ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.hrdMonConditions.criterio")));     
        return reportHeader;
    }

	protected int writeFilter(DriverHRDMonQueryForm reportForm, ReportHeader reportHeader) {
		Format dateFormat = new SimpleDateFormat("dd-MM-yyyy");	
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 25), false, true));
		reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, getReportWidth(), 25),
        		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("query.hrdmon.title")));
        
        int height = 25;

        height = writeParametersTitle(messages.getText("generic.driver.personaldata"),reportHeader,height,10,160);
        
        height = writeParameter(messages.getText("generic.driver.driverdata.firstname")+":",reportForm.getFirstname(),reportHeader,height,10,80,90,80);
        height = writeParameter(messages.getText("generic.driver.driverdata.lastname")+":",reportForm.getLastname(),reportHeader,height,10,80,90,80);
        height = writeParameter(messages.getText("generic.driver.driverdata.status")+":",
        		((reportForm.getStatus() == DriverData.ALL)?messages.getText("generic.messages.all.pl"):
        			((reportForm.getStatus() == DriverData.ACTIVE)?messages.getText("generic.messages.active"):
            			((reportForm.getStatus() == DriverData.EXCLUDED)?messages.getText("generic.messages.excluded"):"")))
        			,reportHeader,height,10,80,90,80);

        height = writeParametersTitle(messages.getText("generic.driver.divgeo"),reportHeader,height,10,160); 
        for(int i = 0; i < 3; i++) {
            if(divGeoLabels[i]!=null) {
                height = writeParameter(divGeoLabels[i]+":",divGeoValues[i],reportHeader,height,10,80,90,80);
            }
        }
        
        int maxHeight = height;
        height = 25;
       
        String[] divOrgs = sys.getUserSessionData().getFilter().getLevelLabels();
        for(int i = 0; i < divOrgs.length; i++) {
            if(!Util.empty(divOrgs[i])) {
                height = writeParametersTitle(messages.getText(divOrgs[i]),reportHeader,height,270,200);
                for(int j = 0; j < 3; j++) {
                    if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 1 && j== 0) && 
                            !(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 2 && j== 0)) {
                        if(!Util.empty(divOrgLabels[i][j])) {
                            height = writeParameter(divOrgLabels[i][j]+":",divOrgValues[i][j],reportHeader,height,270,100,370,100);
                        }
                    }
                }
            }
        }
        maxHeight=Math.max(maxHeight,height);
        height = 25;
        
        /* Ultima Actualizacion */
        height = writeParameter(messages.getText("generic.driver.driverdata.lastupdate")+" "+
        		messages.getText("generic.driver.driverdata.lastupdate.format")+":",
        		(!flag)?dateFormat.format(reportForm.getLastUpdate()):
        			messages.getText("generic.driver.driverdata.phrd.generatingdata")
        			,reportHeader,height,getReportWidth()-380,150,getReportWidth()-230,150);
        height = 25;
        /*
        height = writeParametersTitle(messages.getText("driver.query.period"),reportHeader,height,last,160);
        height = writeParameter(messages.getText("driver.query.from")+":",reportForm.getFrom(messages),reportHeader,height,last,60,last+60,100);
        height = writeParameter(messages.getText("driver.query.to")+":",reportForm.getTo(messages),reportHeader,height,last,60,last+60,100);
        */
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
        ReportFooter reportFooter = new ReportFooter();
        
        reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 20), false, true));
        if (flag) {
        	reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(40, 15, 400, 20),
                    Color.BLUE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT," "+messages.getText("generic.driver.driverdata.phrd.generatingdata")));
        }else{
        	reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, 150, 20),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT," " + messages.getText("query.driver.driverdata.total")));
        	reportFooter.addElement(NumberFieldElementFactory.createNumberElement("total",new Rectangle2D.Float(getReportWidth()-80, 0, 80, 20),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,"", "##########","total"));
        }
        
        if(((DriverHRDMonQueryForm)form).getOutput().equals("HTML")) {
        	return pagination (reportFooter,20);
        }else 
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

        TextFormatExpression textExp22 = new TextFormatExpression();
        textExp22.setName("URLCreateExpression2");
        textExp22.setPattern("./layout.jsp?refer=/vehicle/VehicleView.jsp?id={0}");
        textExp22.setField(0,"vehicleId");
        functions.add(textExp22);

        CreateHyperLinksFunction link22 = new CreateHyperLinksFunction();
        link22.setName("AddHRefs2");
        link22.setField("URLCreateExpression2");
        link22.setElement("URL-Field22");
        functions.add(link22);

        if(((DriverHRDMonQueryForm)form).getOutput().equals("HTML")) {
            for(int i = 1; i <= pages; i++) {
                if(i != pageNumber) {
                    TextFormatExpression textExp2 = new TextFormatExpression();
                    textExp2.setName("URLCreateExpression"+i);
                    textExp2.setPattern("./layout.jsp?refer=/driver/driverReportPage.jsp?page="+i+((DriverHRDMonQueryForm)form).getLink());
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
    
    /**
     * Dibuja el footer del reporte
     * @param reportFooter
     * @param height
     * @return
     */
    protected ReportFooter pagination (ReportFooter reportFooter,int height){
    	int left = 60;
    	
        // Referencias:            	
    	height += 25;
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height,400, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
                messages.getText("generic.driver.driverdata.hrdMonConditions")+":"));
        height += 15;
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height,400, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,
                " - " + messages.getText("generic.driver.driverdata.hrdMonCondition1")));
        height += 15;
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height,400, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,
                " - " + messages.getText("generic.driver.driverdata.hrdMonCondition2")));
        height += 15;
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height,400, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,
                " - " + messages.getText("generic.driver.driverdata.hrdMonCondition3")));
        height += 15;
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height,400, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,
                " - " + messages.getText("generic.driver.driverdata.hrdMonCondition4")));
        height += 15;
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height,400, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,
                " - " + messages.getText("generic.driver.driverdata.hrdMonAnexo")));
        // Fin Referencias
        height += 30;    	
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 50, 15),
                Color.BLUE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE_PARAM_FONT,messages.getText("generic.messages.pages")));
                
        for(int i = 1; i <= pages; i++) {
            int width = (i < 10) ? 15 : 20; 
            if (left>getReportWidth()){
            	left=60;
            	height+=15;
            }
            if(i != pageNumber) {
                reportFooter.addElement(LabelElementFactory.createLabelElement("URL-Field-"+i,new Rectangle2D.Float(left, height, width, 15),
                        Color.BLUE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE_PARAM_FONT,String.valueOf(i)));
            }else {
                reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(left, height, width, 15),
                        Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(i)));
            }
            left += width;
        }

        return reportFooter;
    }
}

