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

public class TelematicControlQueryFactory extends ReportFactory {

    protected Text messages;
    private SystemAccess sys;
    
    private int pages;
    private int pageNumber;
    private String colorOrType; // filtro por color o por type de eventos
    private String reportType; // resumido o detallado

    private int last;
    private int[][] principal;
    
    public TelematicControlQueryFactory(String name, TableModel model, ActionForm form, 
    		SystemAccess sys, Text messages,int pages,int pageNumber, String colorOrType,
    		String reportType) {
        super(name, model, form);
        this.messages = messages;
        this.pages = pages;
        this.pageNumber = pageNumber;
        this.colorOrType = colorOrType;
        this.reportType = reportType;
        
        count = 1;
        parseDivisionValues(sys,messages);
        this.sys = sys;
        last = 170;
        principal = sys.getUserSessionData().getFilter().getPrincipalDivOrgs();
        for(int i = 0; i < principal.length; i++) {
            if(principal[i][0] != 0) {
                last += 100;
            }
        }
    }

    public TelematicControlQueryFactory(String name, TableModel model, ActionForm form, 
    		SystemAccess sys, Text messages, int count,int pages,int pageNumber) {
        super(name, model, form);
        this.messages = messages;
        this.count = count;
        this.pages = pages;
        this.pageNumber = pageNumber;
        parseDivisionValues(sys,messages);
        this.sys = sys;
        last = 170;
        principal = sys.getUserSessionData().getFilter().getPrincipalDivOrgs();
        for(int i = 0; i < principal.length; i++) {
            if(principal[i][0] != 0) {
                last += 100;
            }
        }       
    }

    protected int getWidth() {
    	if (colorOrType.equalsIgnoreCase("type")){
    		return last + 600 + 480 + 60 + 80;
    	}else if (reportType.equalsIgnoreCase("detailed")){
    		return last + 600 + 60 + 480 + 60 + 80;
    	}else if (reportType.equalsIgnoreCase("resumed")){
    		return last + 300 + 60 + 80;
    	}else{
    		return last + 600 + 80;
    	}
    }

    protected int getReportWidth() {
    	if (colorOrType.equalsIgnoreCase("type")){
    		// Reporte fitlrado por tipo de evento
    		return last + 600 + 480 + 60 + 80;
    	}else if (reportType.equalsIgnoreCase("detailed")){
    		// Reporte filtrado por color mostrando eventos
    		return last + 600 + 60 + 480 + 60 + 80;
    	}else if (reportType.equalsIgnoreCase("resumed")){
    		// reporte filtrado por colores sin mostrar eventos
    		return last + 300 + 60 + 80 + 60;
    	}else{
    		return last + 600 + 80;
    	}
    }
    
    protected ItemBand createItemBand() {
        ItemBand items = super.createItemBand();
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,new Rectangle2D.Float(10, 0, getReportWidth(), 15), false, true));
        items.addElement(TextFieldElementFactory.createStringElement("URL-Field",new Rectangle2D.Float(10, 0, 80, 15),
        		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","firstname"));
        items.addElement(TextFieldElementFactory.createStringElement("URL-Field",new Rectangle2D.Float(90, 0, 80, 15),
        		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","lastname"));
        items.addElement(TextFieldElementFactory.createStringElement("vehicle",new Rectangle2D.Float(170, 0, 80, 15),
        		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","vehicle"));
        
        int x = 250;
        // Pais:
        items.addElement(TextFieldElementFactory.createStringElement("country",new Rectangle2D.Float(x, 0, 70, 15),
        		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,
        		STYLE_SIMPLE_FONT,"","country"));
        x += 70;
        
      
        for(int i = 0; i < principal.length; i++) {
            if(principal[i][0] != 0) {
                items.addElement(TextFieldElementFactory.createStringElement("unit",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","divorg"+String.valueOf(i+1)));
                x += 100;
            }
        }
        
        // IdDallas
        items.addElement(TextFieldElementFactory.createStringElement("iddallas",
        		new Rectangle2D.Float(x, 0, 60, 15),Color.BLACK,ElementAlignment.LEFT,
        		ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","iddallas"));
        x += 60;
        
        if (colorOrType.equalsIgnoreCase("color")){
        	// Muestro el color
        	 items.addElement(TextFieldElementFactory.createStringElement("colorFilter",
             		new Rectangle2D.Float(x, 0, 60, 15),Color.BLACK,ElementAlignment.LEFT,
             		ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","colorFilter"));
             x += 60;
             
             if (reportType.equalsIgnoreCase("resumed")){
     	        // date, fecha
     	        items.addElement(DateFieldElementFactory.createDateElement("date",
     	        		new Rectangle2D.Float(x, 0, 60, 15),Color.BLACK,ElementAlignment.CENTER,
     	        		ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-",DATE_FORMAT,"date"));
     	        x += 60;
             }
        }
        
        if (reportType.equalsIgnoreCase("detailed") || colorOrType.equalsIgnoreCase("type")){
        
        	// Tipo de Evento
        	items.addElement(TextFieldElementFactory.createStringElement("type",
 	        		new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,
 	        		ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","type"));
 	        x += 100;
 	        
 	        // Occurencias
 	       items.addElement(TextFieldElementFactory.createStringElement("occurrences",
	        		new Rectangle2D.Float(x, 0, 60, 15),Color.BLACK,ElementAlignment.LEFT,
	        		ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","occurrences"));
	        x += 60;
	        
	        // Velocidad
	 	       items.addElement(TextFieldElementFactory.createStringElement("speed",
		        		new Rectangle2D.Float(x, 0, 60, 15),Color.BLACK,ElementAlignment.LEFT,
		        		ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","speed"));
		        x += 60;
		        
	        // Limit Exceeded
	 	       items.addElement(TextFieldElementFactory.createStringElement("limitExceeded",
		        		new Rectangle2D.Float(x, 0, 60, 15),Color.BLACK,ElementAlignment.LEFT,
		        		ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","limitExceeded"));
		        x += 60;
 	        
	        // Limit ExceededRain
	 	       items.addElement(TextFieldElementFactory.createStringElement("limitExceededRain",
		        		new Rectangle2D.Float(x, 0, 60, 15),Color.BLACK,ElementAlignment.LEFT,
		        		ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","limitExceededRain"));
		        x += 60;
	 	        
      
	        // date, fecha
	        items.addElement(DateFieldElementFactory.createDateElement("date",
	        		new Rectangle2D.Float(x, 0, 60, 15),Color.BLACK,ElementAlignment.CENTER,
	        		ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-",DATE_FORMAT,"date"));
	        x += 60;
        	
	        // time
	        items.addElement(TextFieldElementFactory.createStringElement("time",
	        		new Rectangle2D.Float(x, 0, 60, 15),Color.BLACK,ElementAlignment.LEFT,
	        		ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","time"));
	        x += 60;
	        // duration
	        items.addElement(TextFieldElementFactory.createStringElement("duration",
	        		new Rectangle2D.Float(x, 0, 60, 15),Color.BLACK,ElementAlignment.LEFT,
	        		ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","duration"));
	        x += 60; 

	        // totalduration
	        items.addElement(TextFieldElementFactory.createStringElement("totalDuration",
	        		new Rectangle2D.Float(x, 0, 60, 15),Color.BLACK,ElementAlignment.LEFT,
	        		ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","totalDuration"));
	        x += 60;
	        
	        // Distance
	        items.addElement(TextFieldElementFactory.createStringElement("distance",
	        		new Rectangle2D.Float(x, 0, 60, 15),Color.BLACK,ElementAlignment.LEFT,
	        		ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","distance"));
	        x += 60;
	        
	        // averageVelocity
	        items.addElement(TextFieldElementFactory.createStringElement("averageVelocity",
	        		new Rectangle2D.Float(x, 0, 60, 15),Color.BLACK,ElementAlignment.LEFT,
	        		ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","averageVelocity"));
	        x += 60;
           
	        /*
	        items.addElement(TextFieldElementFactory.createStringElement("recomendation",
	        		new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,
	        		ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","recomendation"));
	        x += 100;
	        
	        items.addElement(TextFieldElementFactory.createStringElement("observation",
	        		new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,
	        		ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","observation"));
	        x += 100;
	        
	        */
        }
        
        items.addElement(TextFieldElementFactory.createStringElement("email",
        		new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,
        		ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","email"));
        x += 100;
        
        return items;
    }
    
    protected ReportHeader createReportHeader() {
    	TelematicControlQueryForm reportForm = (TelematicControlQueryForm)form;
        ReportHeader reportHeader = new ReportHeader();
        int height = writeFilter(reportForm, reportHeader);
                
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FirstLevelColor, null,
                new Rectangle2D.Float(10, height, getReportWidth(), 18), false, true));
       
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 80, 18), 
        		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
        		messages.getText("generic.driver.driverdata.firstname")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(90, height, 80, 18), 
        		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
        		messages.getText("generic.driver.driverdata.lastname")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(170, height, 80, 18), 
        		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
        		messages.getText("generic.driver.driverdata.vehicle")));
        
        int x = 250;
        
        // Pais:
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 70, 18),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
                messages.getText("generic.driver.driverdata.country")));
        x += 70;
        
        
        for(int i = 0; i < principal.length; i++) {
            if(principal[i][0] != 0) {
                reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 100, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,divOrgLabels[principal[i][0]-1][principal[i][1]-1]));
                x += 100;
            }
        }
       
        // IdDallas
        reportHeader.addElement(LabelElementFactory.createLabelElement(
        		null,new Rectangle2D.Float(x, height, 60, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.
                    MIDDLE,STYLE_SIMPLE_BOLD_FONT,
                    messages.getText("generic.driver.driverdata.corporationid")));
        x += 60;
        
        if (colorOrType.equalsIgnoreCase("color")){
        	// Si se muestra el color:
        	 reportHeader.addElement(LabelElementFactory.createLabelElement(
             		null,new Rectangle2D.Float(x, height, 60, 18),
                         Color.WHITE,ElementAlignment.LEFT,ElementAlignment.
                         MIDDLE,STYLE_SIMPLE_BOLD_FONT,
                         messages.getText("driver.telematiccontrol.color")));
             x += 60;

             if (reportType.equalsIgnoreCase("resumed")){
            	 // Fecha del Evento
	 	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,
	 	        		new Rectangle2D.Float(x, height, 60, 18),
	 	        		Color.WHITE,ElementAlignment.CENTER, ElementAlignment.MIDDLE,
	 	        		STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.telematic.date")));
	 	        x += 60;
             }
 	        
        }
        
        if (reportType.equalsIgnoreCase("detailed") || colorOrType.equalsIgnoreCase("type")){
        	// Si el reporte es detallado (con color)
        	// o si es por evento:
        	
        	// Tipo de Evento
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 100, 18),
	        		Color.WHITE,ElementAlignment.CENTER, ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.telematic.type")));
	        x += 100;
	        
	        // ocurrences
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 60, 18),
	        		Color.WHITE,ElementAlignment.CENTER, ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.telematic.occurrences")));
	        x += 60;
	        
	        // speed
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,
	        		new Rectangle2D.Float(x, height, 60, 18),
	        		Color.WHITE,ElementAlignment.CENTER, ElementAlignment.MIDDLE,
	        		STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.telematic.speed")));
	        x += 60;
        	
	        // limitExceeded
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,
	        		new Rectangle2D.Float(x, height, 60, 18),
	        		Color.WHITE,ElementAlignment.CENTER, ElementAlignment.MIDDLE,
	        		STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.telematic.limitExceeded")));
	        x += 60;
	        
	        // limitExceededRain
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,
	        		new Rectangle2D.Float(x, height, 60, 18),
	        		Color.WHITE,ElementAlignment.CENTER, ElementAlignment.MIDDLE,
	        		STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.telematic.limitExceededRain")));
	        x += 60;
	        
	        // Fecha del Evento
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,
	        		new Rectangle2D.Float(x, height, 60, 18),
	        		Color.WHITE,ElementAlignment.CENTER, ElementAlignment.MIDDLE,
	        		STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.telematic.date")));
	        x += 60;
	        
	        // Time
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,
	        		new Rectangle2D.Float(x, height, 60, 18),
	        		Color.WHITE,ElementAlignment.CENTER, ElementAlignment.MIDDLE,
	        		STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.telematic.time")));
	        x += 60;
	        
	        // Duration
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,
	        		new Rectangle2D.Float(x, height, 60, 18),
	        		Color.WHITE,ElementAlignment.CENTER, ElementAlignment.MIDDLE,
	        		STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.telematic.duration")));
	        x += 60;
	        
	        // totalDuration
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,
	        		new Rectangle2D.Float(x, height, 60, 18),
	        		Color.WHITE,ElementAlignment.CENTER, ElementAlignment.MIDDLE,
	        		STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.telematic.totalDuration")));
	        x += 60;
	        
	        // distance
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,
	        		new Rectangle2D.Float(x, height, 60, 18),
	        		Color.WHITE,ElementAlignment.CENTER, ElementAlignment.MIDDLE,
	        		STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.telematic.distance")));
	        x += 60;
	        
	        // averageVelocity
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,
	        		new Rectangle2D.Float(x, height, 60, 18),
	        		Color.WHITE,ElementAlignment.CENTER, ElementAlignment.MIDDLE,
	        		STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.telematic.averageVelocity")));
	        x += 60;

	        /*	        
	        
	        // Tratamiento
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 100, 18),
	        		Color.WHITE,ElementAlignment.CENTER, ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.telematic.recomendation")));
	        x += 100;
	        
	        // Observaciones
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 100, 18),
	        		Color.WHITE,ElementAlignment.CENTER, ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.telematic.observations")));
	        x += 100;
	        
	        */
        }
        
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 100, 18),
        		Color.WHITE,ElementAlignment.CENTER, ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
        		messages.getText("generic.driver.driverdata.email")));
        x += 100;
        
        
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
        
        int height = 25;

        height = writeParametersTitle(messages.getText("generic.driver.personaldata"),reportHeader,height,10,160);
        
        height = writeParameter(messages.getText("generic.driver.driverdata.firstname")+":",reportForm.getFirstname(),reportHeader,height,10,80,90,80);
        height = writeParameter(messages.getText("generic.driver.driverdata.lastname")+":",reportForm.getLastname(),reportHeader,height,10,80,90,80);
        height = writeParameter(messages.getText("generic.driver.driverdata.status")+":",messages.getText(reportForm.getStatusString()),reportHeader,height,10,80,90,80);
        height = writeParameter(messages.getText("generic.driver.driverdata.iddallasonly")+":",reportForm.getIdDallas(),reportHeader,height,10,80,90,80);
            

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
        height = writeParametersTitle(messages.getText("driver.query.period"),reportHeader,height,last,160);
        height = writeParameter(messages.getText("driver.query.from")+":",reportForm.getFrom(messages),reportHeader,height,last,60,last+60,100);
        height = writeParameter(messages.getText("driver.query.to")+":",reportForm.getTo(messages),reportHeader,height,last,60,last+60,100);

        maxHeight=Math.max(maxHeight,height);
        
        // Datos de los controles telematicos
        //height = 25;
        height = writeParametersTitle(messages.getText("generic.driver.telematiccontroldata")
        		,reportHeader,height,last,160);
       
        if (colorOrType.equalsIgnoreCase("color")){
        	   height = writeParameter(messages.getText("driver.telematiccontrol.color")+":",
   	        		messages.getText(reportForm.getColor()),reportHeader,height,last,60,last+60,100);
        	   
   	    }else{
	        height = writeParameter(messages.getText("driver.telematic.type")+":",
	        		messages.getText(reportForm.getTypeString()),reportHeader,height,last,60,last+60,100);
	        height = writeParameter(messages.getText("driver.telematic.recomendation")+":",
	        		messages.getText(reportForm.getRecomendationString()),reportHeader,height,last,60,last+60,100);
        }
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
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 160, 20),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,messages.getText("query.driver.driverdata.total")));
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement(null,new Rectangle2D.Float(last, 10, 90, 20),
                Color.WHITE,ElementAlignment.RIGHT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,"", "#####","total"));

        if(((TelematicControlQueryForm)form).getOutput().equals("HTML")) {
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

        if(((TelematicControlQueryForm)form).getOutput().equals("HTML")) {
            for(int i = 1; i <= pages; i++) {
                if(i != pageNumber) {
                    TextFormatExpression textExp2 = new TextFormatExpression();
                    textExp2.setName("URLCreateExpression"+i);
                    textExp2.setPattern("./layout.jsp?refer=/driver/TelematicControlReportPage.jsp?page="+i+((TelematicControlQueryForm)form).getLink());
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
	 * @return the colorOrType
	 */
	public String getColorOrType() {
		return colorOrType;
	}

	/**
	 * @param colorOrType the colorOrType to set
	 */
	public void setColorOrType(String colorOrType) {
		this.colorOrType = colorOrType;
	}

	/**
	 * @return the reportType
	 */
	public String getReportType() {
		return reportType;
	}

	/**
	 * @param reportType the reportType to set
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
    
    
}
