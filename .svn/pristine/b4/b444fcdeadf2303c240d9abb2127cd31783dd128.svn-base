package presentation.report.activity;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.TableModel;

import org.apache.struts.action.ActionForm;
import org.jfree.report.ElementAlignment;
import org.jfree.report.Group;
import org.jfree.report.GroupFooter;
import org.jfree.report.GroupHeader;
import org.jfree.report.GroupList;
import org.jfree.report.ItemBand;
import org.jfree.report.ReportFooter;
import org.jfree.report.ReportHeader;
import org.jfree.report.elementfactory.LabelElementFactory;
import org.jfree.report.elementfactory.StaticShapeElementFactory;
import org.jfree.report.elementfactory.TextFieldElementFactory;

import presentation.report.ReportFactory;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.activity.tables.CEPAActivityConstants;
import communication.activity.tables.CEPAActivityData;
import communication.activity.tables.CEPAActivityScheduleData;
import communication.activity.tables.CEPAActivityScheduleRegistryData;
import communication.activity.tables.CheckListData;
import communication.activity.tables.PointData;
import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.ItemData;
import communication.driver.tables.DriverData;
import communication.language.Text;

public class EBtwReportFactory extends ReportFactory {

    protected Text messages;
    
    protected DriverData driver;
    protected String reportType;
    protected CEPAActivityData activity;
    protected CEPAActivityScheduleData scheduleActivity;
    protected CEPAActivityScheduleRegistryData registry;   
    private SystemAccess sys;
    
    private Collection points;
            
    public EBtwReportFactory(CEPAActivityScheduleRegistryData registry,
    		CEPAActivityScheduleData scheduleActivity,DriverData driver,String name, 
    		TableModel model, ActionForm form, SystemAccess sys, Text messages, String reportType) {
        //super(name, model,form);
    	super(name,model,form,sys);
        this.messages = messages;
        this.driver = driver;
        this.activity = scheduleActivity;
        this.scheduleActivity = scheduleActivity;        
        this.registry = registry; 
        this.reportType = reportType;
        this.sys = sys;
        
        try {
	        CheckListData checkList = sys.getActivityReportFacade().findCheckList(
	        		new Integer(CheckListData.EBTW),sys.getUserSessionData());	
			points = checkList.getPoints();
			Collections.sort((List)points);
        }catch (Exception e) {
			e.printStackTrace();
		}
        count = 1;             
    }
    
//  it is declared but it is not used 
    protected int getReportWidth() {
    	return 0;
    }
    
    /*
     * Add a Title in the Header.
     */
    private int addHeaderTitle(ReportHeader reportHeader, int x, int y) {        
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,
        		new Rectangle2D.Float(x+100, y, 300, 15),
        		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,
        		messages.getText("query.activity.generalreport.reporttitle"))); 
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,
        		new Rectangle2D.Float(x+100, y+17, 300, 15),
        		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,
        		messages.getText("query.activity.generalreport.reportsubtitle")));
        
        y += 42;
        return y;
    }
    
    /*
     * Add an Activity Data section in the Header.
     */
    private int addActivityData(ReportHeader reportHeader, int x, int y)
    {
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
                new Rectangle2D.Float(xFondo, yFondo, anchoPais, altura *2 + interlineado), false, true));
    	
    	xFondo = x;
    	yFondo = yFondo + altura + interlineado;    	
    	reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,DarkBlue, null,
                new Rectangle2D.Float(xFondo, yFondo, anchoInstructor, altura), false, true));
    	
    	// Dibujo los textos: 
        // Type:
    	int x2 = x;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x2, y,200, altura),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
                " "+messages.getText("generic.activity.activitydata.activitytype")+": "+
                messages.getText(activity.getType().getResourceBundleId())));
        x2 += anchoTipoDeActividad + espacio;
        // Date:
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x2, y, 85, altura),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
                " "+messages.getText("generic.activity.activitydata.date")+": "+
                Util.formatDate(activity.getDate())));
        x2 += anchoFecha + espacio/2;
        // Country
    	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x2, y, 100, altura),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
                " "+messages.getText("generic.activity.activitydata.country")+": "+
                messages.getText(activity.getCountryName())));
        y += 20;        
       
    	// Instructor
    	x2 = x;
    	
    	if (registry.getInstructor() == null){
    		reportHeader.addElement(LabelElementFactory.createLabelElement(
	        		null,new Rectangle2D.Float(x2, y, 290, altura),
	                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
	                " "+messages.getText("generic.activity.instructor")+": N/A "));
    	}else{
    		reportHeader.addElement(LabelElementFactory.createLabelElement(
        		null,new Rectangle2D.Float(x2, y, 290, altura),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
                " "+messages.getText("generic.activity.instructor")+": " + 
                registry.getInstructor()));
    	}
    	
        // Fin Intructor
        // City
        x2 = 405;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x2, y, 90, altura),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
                " "+messages.getText("generic.activity.activitydata.city")+": " +
                activity.getCity()));
        y += 20;
        return y;
    }
      
   
    private int addParameterData(ReportHeader reportHeader, int x, int y)
    {
/*    	int altura = 15; // altura de los textos
  
    	// Dibujo el fondo: Tipo de Actividad, Fecha, Pais, Instructor, Ciudad
      	int anchoTipoDeActividad = 250;
    	int anchoFecha = 135;
    	int espacio = 7;
    	int interlineado = 5;
    	int anchoPais = 132;
    	int anchoInstructor = 388;
  */  	
    	Iterator it = points.iterator();
    	boolean cont = true;
    	int x2 = x;
    	int index = 0;
    	int w = 250;
    	while(it.hasNext() && cont) {
    		PointData point = (PointData)it.next();
    		if(point.getPointOrderReport() == 1) {
    			Iterator items = point.getOrderedItems().iterator();
    			String text = "";
    			while(items.hasNext()) {
    				communication.activity.tables.ItemData item = (communication.activity.tables.ItemData)items.next();
    				Integer valueItem = registry.getValueFromItem(item);
    				if(valueItem != null && valueItem.intValue() == 1) {
    					text = messages.getText(item.getText());
    				}
    			}
    	    	reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,DarkBlue, null,
    	                new Rectangle2D.Float(x2, y, w, 15), false, true));
    	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x2, y, w-10, 15),
    	                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
    	                " "+messages.getText(point.getText())+": "+text));
    	        switch(index) {
    	        	case 0: case 2:
    	    	        x2 += 253;
    	    	        w = 270;
    	    	        break;
    	        	case 1: case 3:
    	        		x2 = x;
    	    	        y += 20;
    	    	        w = 250;
    	    	        break;
    	        }
    	        index++;
    		}else{
    			cont = false;
    		}
    	}
        return y;
    }
    /*
     * Add Driver Data information in the Header
     */
    private int addDriverData(ReportHeader reportHeader, int x, int y)
    {
    	int x2 = x; 
    	reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement
				(null, Color.LIGHT_GRAY, null,new Rectangle2D.Float(x, y, 545, 15), false, true)); 
	    reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x2, y, 500, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,
                " "+messages.getText("generic.activity.nameandlastnameofparticipant")+": " +
                driver.getFirstName() + " " + driver.getLastName()));       
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
	    y += 20;
    	return y;	
    }
    
    /*
     * Add Assigned Vehicle Information
     */
    private int addAssignedVehicle(ReportHeader reportHeader, int x, int y)
    {   	
    	
    	ItemData item1, item2, item3;
    	Iterator it;    	
    	
    	it = scheduleActivity.getDivOrgsIterator();		
		while (it.hasNext())
		{
			DivOrgData divOrgData = (DivOrgData) it.next();
			if(divOrgData.getDivOrgLevels() != null) {
				item1 = (ItemData)divOrgData.getDivOrgLevels().getItemIterator().next();				
				int cantSub =  divOrgData.getLabels().length; // cantidad de subdivisiones
				// la subdivision Pais no la muestro
				if (messages.getText(divOrgData.getDivOrgLevels().getName()).trim().
						equals(messages.getText("monsanto.divisions.country").trim()) ||
					messages.getText(divOrgData.getDivOrgLevels().getName()).trim().
					equals(messages.getText("monsanto.units.country").trim()))
				{
					cantSub --;
				}
				if (cantSub > 0)
				{					
					// Dibujo Fondo segun cantidad de subdivisiones organizacionales que
					// tiene cada division organizacional
					int y2 = y;
					x = 10;
					switch (cantSub)
					{
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
					// Si es Pais no lo muestro.
					if (!messages.getText(divOrgData.getDivOrgLevels().getName()).trim().
							equals(messages.getText("monsanto.divisions.country").trim()) &&
							!messages.getText(divOrgData.getDivOrgLevels().getName()).trim().
							equals(messages.getText("monsanto.units.country").trim())) 
					{						
						//  Muestro vehiculo asignado(primera vuelta)  
						reportHeader.addElement(LabelElementFactory.createLabelElement(
								null,new Rectangle2D.Float(x, y, 250, 15),
							Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
						    " "+messages.getText(divOrgData.getDivOrgLevels().getName())+": "+ item1.getName()));
						x = 266;
					} 
					
			    	if(item1.getSubItems() != null) 
			    	{
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
					} // end if(item1.getSubItems() != null) 

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
    
    /*
     * Add a Note about the course.
     */
    private int addResult(ReportHeader reportHeader, int x, int y) {
    	reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, DarkBlue , null,new Rectangle2D.Float(x, y, 545, 15), false, true));
	    reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y, 60, 15),Color.white,
	    		ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT," "+messages.getText("activity.ebtw.noteteorico")+": "));
	    reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x+65, y, 150, 15),Color.white,
	    		ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(registry.getVehicleType()).toUpperCase()));
	    y += 20;

	    reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, DarkBlue , null,new Rectangle2D.Float(x, y, 545, 15), false, true));
	    reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y, 60, 15),Color.white,
	    		ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT," "+messages.getText("activity.ebtw.notepractico")+": "));
	    reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x+65, y, 150, 15),Color.white,
	    		ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(registry.getNote()).toUpperCase()));
	    y += 20;

	    return y;    	
    }
   
    /**
     * Add a Technical Aspect Section: 
     */ 
    private int addTechnicalAspects(ReportHeader reportHeader, int x, int y) {
	
    	reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement
				(null, Color.LIGHT_GRAY, null,new Rectangle2D.Float(x, y, 545, 15), false, true));
	
    	
        reportHeader.addElement(LabelElementFactory.createLabelElement(
        			null,new Rectangle2D.Float(75, y, 350, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,
                messages.getText("generic.activity.form.btw1liviano.technicalaspectscoached").toUpperCase()+": "));
        y += 15;
        
  		reportHeader.addElement(LabelElementFactory.createLabelElement(
      			null,new Rectangle2D.Float(10, y, 500, 100),
      			Color.BLACK,ElementAlignment.LEFT,ElementAlignment.TOP,STYLE_SIMPLE_FONT,
      			" \n" + messages.getText("ebtw.report.message1") +
				" \n\n" + messages.getText("ebtw.report.message2") +
				" \n" + messages.getText("ebtw.report.message3") +
				" \n\n" + messages.getText("ebtw.report.message4") +
				" \n" + messages.getText("ebtw.report.message5") +
				" \n" + messages.getText("ebtw.report.message6")));
  		y += 105;     	
  		
	    String last1 = "";
	    String last2 = "";
	    Iterator it = points.iterator();
    	while(it.hasNext()) {
    		PointData point = (PointData)it.next();
    		if(point.getPointOrderReport().intValue() > 1) {
    			Iterator items = point.getOrderedItems().iterator();
    			
    			while(items.hasNext()) {
    				communication.activity.tables.ItemData item = (communication.activity.tables.ItemData)items.next();
    				Integer valueItem = registry.getValueFromItem(item);
    				if(valueItem != null && valueItem.intValue() > 1) {
    					String text = messages.getText("ebtw.item.result"+String.valueOf(valueItem));		
	    				switch(point.getPointOrderReport()) {
	    					case 2:case 3:
	    						if(!last1.equals("ebtw.point.family"+point.getPointOrderReport())) {
	    		        	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y , 400, 15),
	    		        	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
	    		        	                " "+messages.getText("ebtw.point.family"+point.getPointOrderReport())));
	    		        	        last1 = "ebtw.point.family"+point.getPointOrderReport();
	    		        		    y += 20;
	    						}
	    						if(!last2.equals(point.getText())) {
	    							reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x+20, y , 400, 15),
	    		        	                Color.DARK_GRAY,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
	    		        	                " "+messages.getText(point.getText())));
	    		        	        last2 = point.getText();
	    		        		    y += 20;
	    						}
	    	        	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x+40, y, 400, 15),
	    	        	                Color.GRAY,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
	    	        	                " "+text+": "+messages.getText(item.getText())));
	    	        		    y += 20;
	    						break;
	    					case 4:
	    						if(!last1.equals(point.getText())) {
	    		        	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y , 400, 15),
	    		        	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
	    		        	                " "+messages.getText(point.getText())));
	    		        	        last1 = point.getText();
	    		        		    y += 20;
	    						}
	    	        	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x+20, y, 400, 15),
	    	        	                Color.GRAY,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
	    	        	                " "+text+": "+messages.getText(item.getText())));
	    	        		    y += 20;
	    						break;
	    				}
    				}
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
                		new Rectangle2D.Float(20, 30, 290, 15),
                		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,
                		messages.getText("query.activity.generalreport.reporttitle"))); 
                reportHeader.addElement(LabelElementFactory.createLabelElement(null,
                		new Rectangle2D.Float(20, 47, 290, 15),
                		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,
                		messages.getText("query.activity.generalreport.reportsubtitle")));
                
                y1 = 82;
    			reportHeader.addElement(org.jfree.report.elementfactory.ImageFieldElementFactory.
             			createImageDataRowElement("logoSafeFleetLA", new Rectangle2D.Float(310, 20, 208, 40), "logoSafeFleetLA"));
        	}else {
	        	// Si el reporte NO es HTML pongo titulo
	        	// LOGO CEPA:
	        	reportHeader.addElement(org.jfree.report.elementfactory.ImageFieldElementFactory.
	         			createImageDataRowElement("logoCepa", new Rectangle2D.Float(x1, y1, 60, 45), "logoCepa"));
	        	if (sys.isCorporation(CorporationData.MONSANTO)){
	        		// LOGO MONSANTO:
	        		reportHeader.addElement(org.jfree.report.elementfactory.ImageFieldElementFactory.
	         			createImageDataRowElement("logoMonsanto", new Rectangle2D.Float(420, y1, 100, 45), "logoMonsanto"));
	          	} else if (sys.isCorporation(CorporationData.FORESTALORIENTAL)){
	          		// LOGO FOSA
	          		reportHeader.addElement(org.jfree.report.elementfactory.ImageFieldElementFactory.
	             			createImageDataRowElement("logoMonsanto", new Rectangle2D.Float(420, y1, 100, 45), "logoFosa"));
	          	}
          	
	            y1 += 10;
	            // Title:
	            y1 = addHeaderTitle(reportHeader, x1, y1);
	        }
        }     	  
        y1 = addActivityData(reportHeader,x1,y1);
        y1 = addAssignedVehicle(reportHeader, x1,y1);
        y1 = addParameterData(reportHeader,x1,y1);
        // Driver Data:
        y1 = addDriverData(reportHeader,x1,y1);
        // Result Section:
        y1 = addResult(reportHeader, x1,y1);  
   

        x1 = 10; 
        y1 += 15;
        
		// Technical Aspects: (Sección Aspectos técnicos valorados y entrenados)
        // Las Charlas de Seguridad no muestran esta seccion.
        if (!activity.getType().getResourceBundleId().equalsIgnoreCase(CEPAActivityConstants.SECURITY_TALK) &&
        	!activity.getType().getResourceBundleId().equalsIgnoreCase(CEPAActivityConstants.GENERAL_SAFETY_SPEECH) ){
        	
        	y1 = addTechnicalAspects(reportHeader, x1, y1);
        	
        }
       
        return reportHeader;
    }
   
    
    protected ReportFooter createReportFooter() {
       ReportFooter reportFooter = new ReportFooter();
       int y = 50;
       
       // Observaciones para Mercancias Peligrosas.
       if (registry.getActivity().getType().getResourceBundleId().equals(CEPAActivityConstants.DANGEROUS_MATERIALS) ||
			   registry.getActivity().getType().getResourceBundleId().equals(CEPAActivityConstants.DANGEROUS_MATERIALS_RENEWAL) ){
    	   
		   registry.setObs(messages.getText("generic.activity.result")+": "+registry.getClassNumber()+"% \n"+
				   messages.getText("generic.activity.expiry")+": "+ Util.formatDate(registry.getActivity().getDateExpiry())+ " \n"+
				   registry.getObs()); 
	   }
       
       
       if ((registry.getObs() != null) && (!registry.getObs().equals("")))
       { 
    	   reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement
				(null, Color.LIGHT_GRAY, null,new Rectangle2D.Float(10, 30, 545, 15), false, true));
    	   reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 30,-100, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
                " "+messages.getText("generic.activity.observations")+": "));
    	  
    	    
    		   reportFooter.addElement(LabelElementFactory.createLabelElement(
   	      			null,new Rectangle2D.Float(20, y, 500, 750),
   	      			Color.BLACK,ElementAlignment.LEFT,ElementAlignment.TOP,STYLE_SIMPLE_FONT,
   	      			registry.getObs()));      
       }   	
        
       return reportFooter;
    }
 
}
