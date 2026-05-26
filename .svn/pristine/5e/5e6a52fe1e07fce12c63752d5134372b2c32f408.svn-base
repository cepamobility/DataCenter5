package presentation.report.activity;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;

import javax.swing.table.TableModel;

import org.apache.struts.action.ActionForm;
import org.jfree.report.ElementAlignment;
import org.jfree.report.ReportFooter;
import org.jfree.report.ReportHeader;
import org.jfree.report.elementfactory.LabelElementFactory;
import org.jfree.report.elementfactory.StaticShapeElementFactory;

import presentation.report.ReportFactory;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.activity.tables.CEPAActivityData;
import communication.activity.tables.CEPAActivityScheduleData;
import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.ItemData;
import communication.driver.tables.DriverData;
import communication.language.Text;

public class InductionActivityReportFactory extends ReportFactory {

    protected Text messages;
    
    protected DriverData driver;
    protected String reportType;
    protected CEPAActivityData activity;
    protected CEPAActivityScheduleData scheduleActivity;
    protected String observations;   
            
    public InductionActivityReportFactory(String observations,CEPAActivityData activity,DriverData driver,String name, 
    		TableModel model, ActionForm form, SystemAccess sys,Text messages, String reportType) {
        // super(name, model,form);        
    	super(name,model,form,sys);
        this.messages = messages;   
        this.driver = driver;
        this.activity = activity;
        this.scheduleActivity = null;
        this.reportType = reportType;
        this.observations = observations;
        count = 1;             
    }
    
    public InductionActivityReportFactory(String observations,CEPAActivityScheduleData scheduleActivity,DriverData driver,String name, 
    		TableModel model, ActionForm form, SystemAccess sys, Text messages, String reportType) {
    	super(name,model,form,sys);
        this.messages = messages;
        this.driver = driver;
        this.activity = scheduleActivity;
        this.scheduleActivity = scheduleActivity;        
        this.reportType = reportType;
        this.observations = observations;
        count = 1;             
    }
    
    protected int getReportWidth() {
    	return 0;
    }
    
    /*
     * Add a Title in the Header.
     */
    private int addHeaderTitle(ReportHeader reportHeader, int x, int y) {        
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x+70, y, 300, 15),
        		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("query.activity.generalreport.reporttitle"))); 
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x+150, y+17, 300, 15),
        		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("query.activity.generalreport.reportsubtitle")));
        return y+42;
    }
    
    /*
     * Add an Activity Data section in the Header.
     */
    private int addActivityData(ReportHeader reportHeader, int x, int y) {
    	int altura = 15; // altura de los textos
  
    	// Dibujo el fondo: Tipo de Actividad, Fecha, Pais, Instructor, Ciudad
      	int anchoTipoDeActividad = 250;
    	int anchoFecha = 135;
    	int espacio = 7;
    	int interlineado = 5;
    	int anchoPais = 132;
    	int anchoInstructor = 388;
    	
    	int xFondo = x;
    	int yFondo = y;
    	reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,DarkBlue, null,
                new Rectangle2D.Float(xFondo, yFondo, anchoTipoDeActividad, altura), false, true));
    	xFondo = xFondo + anchoTipoDeActividad + espacio/2;
    	reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,DarkBlue, null,
                new Rectangle2D.Float(xFondo, yFondo, anchoFecha, altura), false, true));
    	xFondo = xFondo + anchoFecha + espacio/2 ;
    	reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,DarkBlue, null,
                new Rectangle2D.Float(xFondo, yFondo, anchoPais, altura), false, true));
    	
    	xFondo = x;
    	yFondo = yFondo + altura + interlineado;    	
    	reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,DarkBlue, null,
                new Rectangle2D.Float(xFondo, yFondo, anchoInstructor, altura), false, true));
    	
    	// Dibujo los textos: 
        // Type:
    	int x2 = x;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x2, y,200, altura),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT," "+messages.getText("generic.activity.activitydata.activitytype")+": "+ messages.getText(activity.getType().getResourceBundleId())));
        x2 += anchoTipoDeActividad + espacio;
        // Date:
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x2, y, 85, altura),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT," "+messages.getText("generic.activity.activitydata.date")+": "+ Util.formatDate(activity.getDate())));
        x2 += anchoFecha + espacio/2;
        // Country
    	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x2, y, 100, altura),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT," "+messages.getText("generic.activity.activitydata.country")+": "+messages.getText(activity.getCountryName())));
        return y+20;
    }
      
   
    /*
     * Add Driver Data information in the Header
     */
    private int addDriverData(ReportHeader reportHeader, int x, int y) {
    	int x2 = x; 
    	reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, Color.LIGHT_GRAY, null,new Rectangle2D.Float(x, y, 545, 15), false, true)); 
	    reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x2, y, 500, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT," "+messages.getText("generic.activity.nameandlastnameofparticipant")+": " +driver.getFirstName() + " " + driver.getLastName()));       
        if(sys.getCorporationId() == CorporationData.JJ) {
            y += 20;
        	String msg = (driver.getCorporationId() == null) ? "---" : driver.getCorporationId(); 
        	reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement
    				(null, Color.LIGHT_GRAY, null,new Rectangle2D.Float(x, y, 545, 15), false, true)); 
    	    reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x2, y, 500, 15),
                    Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,
                    " "+messages.getText("generic.driver.driverdata.wwid").toUpperCase()+": " +
                    msg));       
        }
    	return y+20;	
    }
    
    /*
     * Add Assigned Vehicle Information
     */
    private int addAssignedVehicle(ReportHeader reportHeader, int x, int y)
    {   	
    	
    	ItemData item1, item2, item3;
    	Iterator it;    	
    	
    	it = scheduleActivity.getDivOrgsIterator();		
		while (it.hasNext()) {
			DivOrgData divOrgData = (DivOrgData) it.next();
			if(divOrgData.getDivOrgLevels() != null) {
				item1 = (ItemData)divOrgData.getDivOrgLevels().getItemIterator().next();				
				int cantSub =  divOrgData.getLabels().length; // cantidad de subdivisiones
				// la subdivision Pais no la muestro
				if (messages.getText(divOrgData.getDivOrgLevels().getName()).trim().equals(messages.getText("monsanto.divisions.country").trim()) ||
					messages.getText(divOrgData.getDivOrgLevels().getName()).trim().equals(messages.getText("monsanto.units.country").trim())) {
					cantSub --;
				}
				if (cantSub > 0) {					
					// Dibujo Fondo segun cantidad de subdivisiones organizacionales que
					// tiene cada division organizacional
					int y2 = y;
					x = 10;
					switch (cantSub) {
						case 1:
							reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement
									(null,DarkBlue, null,new Rectangle2D.Float(x, y2, 538, 15), false, true)); 
							y2 += 20;
							break;						
						case 2:
							reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(
									null,DarkBlue, null, new Rectangle2D.Float(x, y2, 250, 15), false, true));
							reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(
									null,DarkBlue, null, new Rectangle2D.Float(x+250+3, y2, 270, 15), false, true));
							y2 += 20;
							break;
						case 3:
							reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,DarkBlue, null,
					                new Rectangle2D.Float(x, y2, 170, 15), false, true));
							reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,DarkBlue, null,
					                new Rectangle2D.Float(x+175, y2, 170, 15), false, true));
							reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,DarkBlue, null,
					                new Rectangle2D.Float(x+350, y2, 170, 15), false, true));
							y2 += 20;
							break;
						default:					
					} 
					
					x = 10;
			    	if(item1.getSubItems() != null) {
						item2 = (ItemData)item1.getSubItems().getItemIterator().next();
			    		// Si tiene subitems, dibujo la division:
						reportHeader.addElement(LabelElementFactory.createLabelElement(
								null,new Rectangle2D.Float(x, y, 250, 15),
								Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
								" "+messages.getText(item1.getSubItems().getName())+": " + item2.getName()));
						x += 266;
				    	if(item2.getSubItems() != null) {
							item3 = (ItemData)item2.getSubItems().getItemIterator().next();
							// Si tiene subitesms, dibujo la SubDivision:
							reportHeader.addElement(LabelElementFactory.createLabelElement(
									null,new Rectangle2D.Float(x, y, 190, 15),
									Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
									" "+messages.getText(item2.getSubItems().getName())+": " + item3.getName()));																			
						} // end if(item2.getSubItems() != null)
				    	x = 10;
					} else {
                        reportHeader.addElement(LabelElementFactory.createLabelElement(
                                null,new Rectangle2D.Float(x, y, 250, 15),
                                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
                                " "+messages.getText(divOrgData.getDivOrgLevels().getName())+": "+item1.getName()));
                    } 

				}
			}
			y += 20;
		}
	    for(int i = 0; i < 3; i++) {
	        for(int j = 0; j < 3; j++) {
	            if(!Util.empty(divOrgLabels[i][j])) {
	                reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y, 100, 18),
	                        Color.decode("#D0D0D0"),ElementAlignment.CENTER,ElementAlignment.MIDDLE,fontDefinitionBold,messages.getText(divOrgLabels[i][j])));	               
	            }
	        }
	    }    
	    return y;
    }
    
    
    protected ReportHeader createReportHeader() {        
        
    	ReportHeader reportHeader = new ReportHeader();        
    	int y1 = 20; //(Coordenadas para la primer columna)
        int x1 = 10;       
        
        if(!Util.empty(reportType) && !reportType.equals("HTML")) {
        	if(sys.isCorporation(CorporationData.JJ)) {
                reportHeader.addElement(LabelElementFactory.createLabelElement(null,
                		new Rectangle2D.Float(20, 30, 300, 15),
                		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,
                		messages.getText("query.activity.generalreport.reporttitle"))); 
                reportHeader.addElement(LabelElementFactory.createLabelElement(null,
                		new Rectangle2D.Float(20, 47, 300, 15),
                		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,
                		messages.getText("query.activity.generalreport.reportsubtitle")));
                
                y1 = 82;
    			reportHeader.addElement(org.jfree.report.elementfactory.ImageFieldElementFactory.
             			createImageDataRowElement("logoSafeFleetLA", new Rectangle2D.Float(335, 20, 208, 40), "logoSafeFleetLA"));
        	}else {
	        	// Si el reporte NO es HTML pongo titulo
	        	// LOGO CEPA:
	        	reportHeader.addElement(org.jfree.report.elementfactory.ImageFieldElementFactory.
	         			createImageDataRowElement("logoCepa", new Rectangle2D.Float(x1, y1, 50, 45), "logoCepa"));
	        	if (sys.isCorporation(CorporationData.JJ)){
	        		// LOGO MONSANTO:
	        		reportHeader.addElement(org.jfree.report.elementfactory.ImageFieldElementFactory.
	         			createImageDataRowElement("logoMonsanto", new Rectangle2D.Float(420, y1, 100, 45), "logoMonsanto"));
	        	}
	            y1 += 10;
	            // Title:
	            y1 = addHeaderTitle(reportHeader, x1, y1);
        	}
        }
     	  
        y1 = addActivityData(reportHeader,x1,y1);
        y1 = addAssignedVehicle(reportHeader, x1,y1);
        // Driver Data:
        y1 = addDriverData(reportHeader,x1,y1);
        return reportHeader;
    }
   
    
    protected ReportFooter createReportFooter() {
       ReportFooter reportFooter = new ReportFooter();
       reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, Color.LIGHT_GRAY, 
               null,new Rectangle2D.Float(10, 10, 545, 15), false, true));
       reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10,-100, 15),
               Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT," "+messages.getText("generic.activity.observations")+": "));
       reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(20, 40, 545, 150),
               Color.BLACK,ElementAlignment.LEFT,ElementAlignment.TOP,STYLE_SIMPLE_FONT,observations));      
       return reportFooter;
    }
    
}
