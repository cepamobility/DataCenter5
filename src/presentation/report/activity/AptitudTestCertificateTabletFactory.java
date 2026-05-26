package presentation.report.activity;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.TableModel;

import org.apache.struts.action.ActionForm;
import org.jfree.report.ElementAlignment;
import org.jfree.report.Group;
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
import communication.activity.tables.CEPAScheduleActivityRegistryObservationSelectedData;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.ItemData;
import communication.driver.tables.DriverData;
import communication.language.Text;

public class AptitudTestCertificateTabletFactory extends ReportFactory {

    protected Text messages;
    
    protected DriverData driver;
    protected String reportType;
    private SystemAccess sys;
    private String date;
    private String nota;
    private String expiry;
    
	        
    public AptitudTestCertificateTabletFactory(DriverData driver,String name, 
    		TableModel model, ActionForm form, SystemAccess sys,
    		Text messages, String reportType, String date, String nota,String expirity) {
        // super(name, model,form);        
    	super(name,model,form,sys);
        this.messages = messages;   
        this.driver = driver;
        this.reportType = reportType;
        this.sys = sys;
        this.date=date;
        this.expiry=expirity;
        this.nota=nota;
        count = 1;             
    }
    
  
    
//  it is declared but it is not used 
    protected int getReportWidth() {
    	return 0;
    }
    
    protected GroupList createGroups() {


		GroupList grouplist = super.createGroups();
     
        Group group = new Group();        	
        
        group.setName("points");        
        group.addField("point");
       
        GroupHeader header = new GroupHeader();
        header.setName("pointHeader");
        
    	header.addElement(TextFieldElementFactory.createStringElement("point",        		
    		new Rectangle2D.Float(20, 10, 200, 10),DarkBlue,
            ElementAlignment.LEFT,ElementAlignment.BOTTOM,
            STYLE_SIMPLE_BOLD_FONT,"","point"));
    	
    	group.setHeader(header);

             
        grouplist.add(group);
        return grouplist;
    }
    
    protected ItemBand createItemBand() {
        ItemBand items = super.createItemBand();      
        	
        	items.addElement(TextFieldElementFactory.createStringElement("item",
       			new Rectangle2D.Float(50, 0, -100, 8),
        		Color.BLACK,ElementAlignment.LEFT,
        		ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","item"));
			
       	return items;
    }
    
   
    /*
     * Add a Title in the Header.
     */
    private int addHeaderTitle(ReportHeader reportHeader, int x, int y)
    {          

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,
        		new Rectangle2D.Float(x+100, y, 300, 15),
        		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,
        		messages.getText("generic.driver.aptitudtest"))); 
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
                new Rectangle2D.Float(xFondo, yFondo,anchoPais , altura), false, true));
    	xFondo = xFondo + anchoPais + espacio/2 ;
    	reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,DarkBlue, null,
                new Rectangle2D.Float(xFondo, yFondo, anchoFecha, altura ), false, true));
    	
    	yFondo = yFondo + altura + interlineado;    	
    	reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,DarkBlue, null,
    	        new Rectangle2D.Float(xFondo, yFondo, anchoFecha, altura), false, true));
    	
    	// Dibujo los textos: 
        // Type:
    	int x2 = x;
    	String title =  messages.getText("generic.driver.aptitudtest");
    	    	
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x2, y,200, altura),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
                " "+messages.getText("generic.activity.activitydata.activitytype")+": "+ title
               ));
        x2 += anchoTipoDeActividad + espacio;
      
        // Country
    	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x2, y, 100, altura),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
                " "+messages.getText("generic.activity.activitydata.country")+": "+
                messages.getText(driver.getCountryName())));
        //y += 20;
    	x2+=anchoPais+espacio;
    	
        // Date:
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x2, y, 85, altura),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
                " "+messages.getText("generic.activity.activitydata.date")+": "+
                date));
        //x2 += anchoFecha + espacio/2;
        y += 20;
        reportHeader.addElement(LabelElementFactory.createLabelElement(
        		null,new Rectangle2D.Float(x2, y, 290, altura),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
                " "+messages.getText("generic.activity.activitydata.activityexpiry")+": " + 
                this.expiry));
        y += 20;
        
      // Instructor
        	x2 = x;
        	
        /*	if (registry.getInstructor() == null){
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
	        y += 20;*/
        
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
        y += 20;
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement
				(null, Color.LIGHT_GRAY, null,new Rectangle2D.Float(x, y, 545, 15), false, true)); 
	    reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x2, y, 500, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,
                " "+messages.getText("generic.driver.driverdata.corporationid")+": " +
                driver.getCorporationId()));       
        y += 20;
    	return y;	
    }
    
    
    /*
     * Add a Note about the course.
     */
    private int addResult(ReportHeader reportHeader, int x, int y)
    {
    	 
        	reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement
    				(null, DarkBlue , null,new Rectangle2D.Float(x, y, 545, 15), false, true));
        	
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y, 80, 15),
	                Color.white,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
	                " "+messages.getText("generic.activity.form.btw1liviano.result")+" Apto: "));
	        Color colorNote = Color.white;
	        
	        /*if (this.nota.equals("generic.activity.note.notapruved"))
	        	 colorNote= Color.RED.darker();
	        else 
	        	if (this.nota.equals("generic.activity.note.apruvedwithobservations"))
	        		colorNote = Color.YELLOW.darker();
	        	else 
	        		colorNote = Color.GREEN.darker();
	        		*/
	        
	        reportHeader.addElement(LabelElementFactory.createLabelElement(
	        		null,new Rectangle2D.Float(x+85, y, 150, 15),
	                colorNote,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
	                messages.getText(this.nota).toUpperCase()));
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
    	
    	it = this.driver.getDivOrgsIterator();		
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
					int y2 = y-20;
					x = 10;
					switch (cantSub)
					{
						case 1:
							reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement
									(null,DarkBlue, null,new Rectangle2D.Float(x, y2, 386, 15), false, true)); 
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
								null,new Rectangle2D.Float(x, y-20, 250, 15),
							Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
						    " "+messages.getText(divOrgData.getDivOrgLevels().getName())+": "+ item1.getName()));
						x = 266;
					} 
					
			    	if(item1.getSubItems() != null) 
			    	{
						item2 = (ItemData)item1.getSubItems().getItemIterator().next();
			    		// Si tiene subitems, dibujo la division:
						reportHeader.addElement(LabelElementFactory.createLabelElement(
								null,new Rectangle2D.Float(x, y-20, 250, 15),
								Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
								" "+messages.getText(item1.getSubItems().getName())+": " + item2.getName()));
						x += 266;
				    	if(item2.getSubItems() != null) {
							item3 = (ItemData)item2.getSubItems().getItemIterator().next();
							// Si tiene subitesms, dibujo la SubDivision:
							reportHeader.addElement(LabelElementFactory.createLabelElement(
									null,new Rectangle2D.Float(x, y-20, 190, 15),
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
	                reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y-20, 100, 18),
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
        
        if(!Util.empty(reportType) && !reportType.equals("HTML")) 
        {
        	// Si el reporte NO es HTML pongo titulo
        	// LOGO CEPA:
        	reportHeader.addElement(org.jfree.report.elementfactory.ImageFieldElementFactory.
         			createImageDataRowElement("logoCepa", new Rectangle2D.Float(x1, y1, 60, 45), "logoCepa"));
        	if (driver.getCorporation().getId().equals(new Integer(1))){
        		// LOGO MONSANTO:
        		reportHeader.addElement(org.jfree.report.elementfactory.ImageFieldElementFactory.
         			createImageDataRowElement("logoMonsanto", new Rectangle2D.Float(420, y1, 100, 45), "logoMonsanto"));
        	} else if (driver.getCorporation().getId().equals(new Integer(4))){
        		// Obtengo la Region del driver para ver si muestro el lofo de Safe Fleet LA o el de J&J:
        		GeoItemData region = (GeoItemData)driver.getCountry().getItems().iterator().next();
        		if (region.getName().equals("LA&C")){
        			// LOGO Safe Fleet J&J Latino America
        			reportHeader.addElement(org.jfree.report.elementfactory.ImageFieldElementFactory.
             			createImageDataRowElement("logoSafeFleetLA", new Rectangle2D.Float(473, y1, 47, 45), "logoSafeFleetLA"));
        		}else{
        			// LOGO J&J:
            		reportHeader.addElement(org.jfree.report.elementfactory.ImageFieldElementFactory.
                 			createImageDataRowElement("logoJ&J", new Rectangle2D.Float(420, y1, 100, 45), "logoJ&J"));
            		
        		}
          	} else if (driver.getCorporation().getId().equals(new Integer(9))){
          		// LOGO FOSA
          		reportHeader.addElement(org.jfree.report.elementfactory.ImageFieldElementFactory.
             			createImageDataRowElement("logoMonsanto", new Rectangle2D.Float(420, y1, 100, 45), "logoFosa"));
          	}
        	
          	
            y1 += 10;
            // Title:
            y1 = addHeaderTitle(reportHeader, x1, y1);
        }
     	  
        y1 = addActivityData(reportHeader,x1,y1);
        y1 = addAssignedVehicle(reportHeader, x1,y1);
        // Driver Data:
        y1 = addDriverData(reportHeader,x1,y1);
        // Result Section:
        y1 = addResult(reportHeader, x1,y1);  
   

        x1 = 10; 
        y1 += 15;
        
		
       
        return reportHeader;
    }
   
    
    protected ReportFooter createReportFooter() {
       ReportFooter reportFooter = new ReportFooter();
       /*   int y = 50;
       
       List<CEPAScheduleActivityRegistryObservationSelectedData> obs = 
    	   		(List<CEPAScheduleActivityRegistryObservationSelectedData>) registry.getObsSelected();
       
       if (obs !=null && obs.size() > 0){
    	   
    	   Collections.sort(obs);
    	   
    	   Iterator<CEPAScheduleActivityRegistryObservationSelectedData> it = obs.iterator();
    	   
    	   // Titulo de Observaciones
    	   reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement
   				(null, Color.LIGHT_GRAY, null,new Rectangle2D.Float(10, 30, 545, 15), false, true));
       	   reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 30,-100, 15),
                   Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
                   " "+messages.getText("generic.activity.observations")+": "));
    	   
       	   // Itero sobre las observaciones seleccionadas para mostrarlas
       	   while (it.hasNext()){
    		   CEPAScheduleActivityRegistryObservationSelectedData ob = it.next();
    		   if (ob != null){    			   
    			   reportFooter.addElement(LabelElementFactory.createLabelElement(
    	   	      			null,new Rectangle2D.Float(20, y, 500, 8),
    	   	      			Color.BLACK,ElementAlignment.LEFT,ElementAlignment.TOP,STYLE_SIMPLE_FONT,
    	   	      			messages.getText(ob.getObservation().getTextReport())
    	   	      			));  
    			   y += 10;
    		   }
    	   }
    	   
       }*/
       
      
        
       return reportFooter;
    }
 
}
