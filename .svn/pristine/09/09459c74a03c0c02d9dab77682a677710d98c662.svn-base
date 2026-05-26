/**
 * Created on 10-jul-2006
 * CEPA
 * DataCenter 5
 */
package presentation.report.activity;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.table.TableModel;

import org.apache.struts.action.ActionForm;
import org.jfree.report.ElementAlignment;
import org.jfree.report.ItemBand;
import org.jfree.report.ReportFooter;
import org.jfree.report.ReportHeader;
import org.jfree.report.elementfactory.LabelElementFactory;
import org.jfree.report.elementfactory.StaticShapeElementFactory;
import org.jfree.report.elementfactory.TextFieldElementFactory;

import presentation.report.ReportFactory;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.activity.tables.CEPAActivityData;
import communication.activity.tables.CEPAActivityScheduleData;
import communication.activity.tables.CEPAActivityScheduleRegistryData;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.ItemData;
import communication.driver.tables.DriverData;
import communication.language.Text;

public class ActivityReportOldFactory extends ReportFactory {

    protected Text messages;
    
    protected DriverData driver;
    
    protected CEPAActivityData activity;
    protected CEPAActivityScheduleData scheduleActivity;
    protected CEPAActivityScheduleRegistryData registry;    
    
    protected String notaCorta;
    protected String notaLarga;
    protected String observaciones;
    protected String reportType;
    
    protected boolean tieneItems;
            
    public ActivityReportOldFactory(CEPAActivityScheduleRegistryData registry,
    		CEPAActivityData activity,DriverData driver,String name, 
    		TableModel model, ActionForm form, SystemAccess sys, Text messages,
    		String notaCorta, String notaLarga,	String observaciones, 
    		boolean tieneItems, String reportType) {
        super(name, model,form);
        this.messages = messages;
        this.driver = driver;
        this.activity = activity;
        this.scheduleActivity = null;
        this.registry = registry;        
        count = 1;             
        this.notaCorta= notaCorta;
        this.notaLarga= notaLarga;
        this.observaciones = observaciones;
        this.tieneItems = tieneItems;
        this.reportType = reportType;
        
    }
    
    public ActivityReportOldFactory(CEPAActivityScheduleRegistryData registry,
    		CEPAActivityScheduleData scheduleActivity,DriverData driver,String name, 
    		TableModel model, ActionForm form, SystemAccess sys, Text messages, String notaCorta,String notaLarga,
    		String observaciones, boolean tieneItems, String reportType) {
        super(name, model,form);
        this.messages = messages;
        this.driver = driver;
        this.activity = scheduleActivity;
        this.scheduleActivity = scheduleActivity;        
        this.registry = registry;        
        count = 1;             
        this.notaCorta= notaCorta;
        this.notaLarga= notaLarga; 
        this.observaciones = observaciones;
        this.tieneItems = tieneItems;
        this.reportType = reportType;
    }
        
//  it is declared but it is not used 
    protected int getReportWidth() {
    	return 0;
    }
    protected ItemBand createItemBand() {
        ItemBand items = super.createItemBand();    
        
        //boolean tieneItems = activity.getType().getResourceBundleId().equals("datatype.scheduledactivitytype.btw1") || activity.getType().getResourceBundleId().equals("datatype.scheduledactivitytype.btw2") || activity.getType().getResourceBundleId().equals("datatype.scheduledactivitytype.commentarydrive");
        if (tieneItems) {
	        /*items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,
	                new Rectangle2D.Float(10, 0, -100, 60), false, true));
	        */                
	        items.addElement(TextFieldElementFactory.createStringElement("item",new Rectangle2D.Float(10, 0, -100, 60),
	        		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","item"));
        }    

        return items;
    }
    
    protected ReportHeader createReportHeaderViejo() {        
    	
        ReportHeader reportHeader = new ReportHeader();
        int y = 0; // para mover los textos segun corresponda.
         
        
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, DCGray1, null,
                new Rectangle2D.Float(10, 0, -100, 15), false, true));        

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, y, -100, 15),
        		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,
        		messages.getText(this.activity.getDefinition().getResourceKeyName())));
   				// generic.activity.form.btw1liviano.title")));
        y += 30;        
        // Agregar una sección titulada Datos de la Actividad        
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, y,-100, 15),
                Color.blue,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE_PARAM_FONT,messages.getText("generic.activity.activitydata")));
        y += 20;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, y,-30, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.activity.activitydata.type")+": "));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(-40, y,-50, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText(activity.getType().getResourceBundleId())));
        y += 20;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, y, -30, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.activity.activitydata.country")+": "));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(-40, y, 100, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText(activity.getCountryName())));
        y += 20;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, y, -30, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.activity.activitydata.city")+": "));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(-40, y, 100, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,activity.getCity()));
        y += 20;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, y, -30, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.activity.activitydata.date")+": "));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(-40, y, 100, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,Util.formatDate(activity.getDate())));        
              
        y += 20;
        if (this.activity.getDefinition().getResourceKeyName().equals("datatype.scheduledactivitytype.commentarydrive"))
    	{
            // Si es Commentary Drive, no tiene instructor, tiene partner y tipo.
        	// Tipo
        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, y, -30, 15),
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.activity.form.commentarydrive.commentarydrivetype")+": "));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(-40, y, 100, 15),
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText(registry.getConductionType())));
	        // Fin Tipo
	        y += 20;
	        // Partner:
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, y, -30, 15),
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.activity.form.commentarydrive.peer")+": "));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(-40, y, 100, 15),
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,registry.getPartner()));
        	// Fin Partner
	        y += 20;
    	}
        else
        {
	    	// Instructor
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, y, -30, 15),
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.activity.instructor")+": "));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(-40, y, 100, 15),
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,registry.getInstructor()));        
	        // Fin Intructor
	        y += 20;
        }
        
        //Agregar una sección titulada Datos del Participante
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, y, -100, 15),
                Color.blue,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE_PARAM_FONT,messages.getText("generic.event.driverdata")));
        y += 20;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, y, -30, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.name")+": "));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(-40, y, 100, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,driver.getFirstName() + " " + driver.getLastName()));
        y += 20;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, y, -30, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.birthdate")+": "));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(-40, y, 100, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,Util.formatDate(driver.getBirthDate())));        
        y += 20;
        // Agregar una sección titulada Resultado
        // El commentary drive no tiene notas.
        if (this.activity.getDefinition().isHaveNote())
        {
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, y, -100, 15),
	                Color.blue,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE_PARAM_FONT,messages.getText("generic.activity.form.btw1liviano.result")));
	       	y += 20;
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, y, -30, 15),
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.activity.note")+": "));

	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(-40, y, 100, 15),
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(this.notaLarga) + "-" + this.notaCorta));
        }
        
        // Sección Aspectos técnicos valorados y entrenados
        y += 20;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, y, -100, 15),
                Color.blue,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE_PARAM_FONT,messages.getText("generic.activity.form.btw1liviano.technicalaspectscoached")));
 
        return reportHeader;
    }
   
    /*
     * Add a Title in the Header.
     */
    private int addHeaderTitle(ReportHeader reportHeader, int x, int y)
    {  
    	
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, DCGray1, null,
                new Rectangle2D.Float(x, y, -100, 15), false, true));        

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y, -100, 15),
        		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,
        		messages.getText(this.activity.getDefinition().getResourceKeyName())));
   				// generic.activity.form.btw1liviano.title")));
        y += 30;
        return y;
    }
    
    /*
     * Add an Activity Data section in the Header.
     */
    private int addActivityData(ReportHeader reportHeader, int x, int y)
    {
    	int x2 = x + 140; // posicion en que se dibujan los valores de los campos (en %)
    	int ancho = -25; // 25% del ancho del dibujo.
    	int anchoTitulo = -45; // 45%
    	int altura = 15; // altura de los textos
    	
    	// Activity Data:
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y,anchoTitulo, altura),
                Color.blue,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE_PARAM_FONT,messages.getText("generic.activity.activitydata")));
        y += 20;
        // Type:
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y,ancho, altura),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.activity.activitydata.type")+": "));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x2, y,ancho, altura),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText(activity.getType().getResourceBundleId())));
        y += 20;
        // Date:
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y, ancho, altura),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.activity.activitydata.date")+": "));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x2, y, ancho, altura),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,Util.formatDate(activity.getDate())));       
        y += 20;
        if (this.activity.getDefinition().getResourceKeyName().equals("datatype.scheduledactivitytype.commentarydrive"))
    	{
            // Si es Commentary Drive, no tiene instructor, tiene partner, tipo y plate (matricula)
        	// Tipo
        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y, ancho, altura),
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.activity.form.commentarydrive.commentarydrivetype")+": "));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x2, y, ancho, altura),
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText(registry.getConductionType())));
	        // Fin Tipo
	        y += 20;
	        // Partner:
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y, ancho, altura),
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.activity.form.commentarydrive.peer")+": "));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x2, y, ancho, altura),
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,registry.getPartner()));
        	// Fin Partner
	        y += 20;
	        // Plate (matricula):
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x ,y, ancho, altura),
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.activity.form.commentarydrive.vehicleplate")+": "));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x2, y, ancho, altura),
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,registry.getPlate()));
        	// Fin Plate
	        y += 20;
    	}
        else
        {
	    	// Instructor
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y, ancho, altura),
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.activity.instructor")+": "));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x2, y, ancho, altura),
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,registry.getInstructor()));        
	        // Fin Intructor
	        y += 20;
        }
        return y;
    }
    
    private int addLocation(ReportHeader reportHeader, int x, int y)
    {
    	int x2 = x + 140;
    	int anchoTitulo = -45;
        // Location
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y,anchoTitulo, 15),
                Color.blue,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE_PARAM_FONT,messages.getText("generic.activity.location")));
        y += 20;
    	// Country
    	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y, -25, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.activity.activitydata.country")+": "));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x2, y, -25, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText(activity.getCountryName())));
        y += 20;
        // City
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y, -25, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.activity.activitydata.city")+": "));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x2, y, -25, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,activity.getCity()));
        y += 20;
    	return y;
    }
    
    /*
     * Add Driver Data information in the Header
     */
    private int addDriverData(ReportHeader reportHeader, int x, int y)
    {
    	int x2 = x + 140;
    	int anchoTitulo = -45;
    	// Driver Data
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y, anchoTitulo, 15),
                Color.blue,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE_PARAM_FONT,messages.getText("generic.event.driverdata")));
        y += 20;
        
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y, -25, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.name")+": "));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x2, y, -23, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,driver.getFirstName() + " " + driver.getLastName()));
        y += 20;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y, -25, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.birthdate")+": "));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x2, y, -23, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,Util.formatDate(driver.getBirthDate())));        
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
    	int x2 = x + 140;

    	it = scheduleActivity.getDivOrgsIterator();		
		while (it.hasNext())
		{
			DivOrgData divOrgData = (DivOrgData) it.next();
			if(divOrgData.getDivOrgLevels() != null) {
				item1 = (ItemData)divOrgData.getDivOrgLevels().getItemIterator().next();				
				// Muestro Titulo: (La primera vuelta: Vehiculo Asigando, la segunda
				// vuelta Divisiones
				reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y, -45, 15),
		                Color.blue,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE_PARAM_FONT,
		                messages.getText(divOrgData.getName())));
		        y += 20;
		        //  Muestro vehiculo asignado(primera vuelta)  o Pais (en la segunda vuelta)
				reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y, -25, 15),
						Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
					    messages.getText(divOrgData.getDivOrgLevels().getName())));
				reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x2, y, -20, 15),
						Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,
						item1.getName()));
				y += 20;				
				
		    	if(item1.getSubItems() != null) 
		    	{
					item2 = (ItemData)item1.getSubItems().getItemIterator().next();
		    		// Si tiene subitems, dibujo la division:
					reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y, -25, 15),
							Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
							messages.getText(item1.getSubItems().getName())));
					reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x2, y, -20, 15),
							Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,
							item2.getName()));
					y += 20;				
							
			    	if(item2.getSubItems() != null) {
						item3 = (ItemData)item2.getSubItems().getItemIterator().next();
						// Si tiene subitesms, dibujo la SubDivision:
						reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y, -23, 15),
								Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
								messages.getText(item2.getSubItems().getName())));
						reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x2, y, -23, 15),
								Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,
								item3.getName()));
						y += 20;						
					} // end if(item2.getSubItems() != null)
				} // end if(item1.getSubItems() != null) 
			}
		}

	    for(int i = 0; i < 3; i++) {
	        for(int j = 0; j < 3; j++) {
	            if(!Util.empty(divOrgLabels[i][j])) {
	                reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y, 100, 18),
	                        Color.decode("#D0D0D0"),ElementAlignment.CENTER,ElementAlignment.MIDDLE,fontDefinitionBold,messages.getText(divOrgLabels[i][j])));
	                y += 20;
	            }
	        }
	    }    
	    return y;
    }
    
    /*
     * Add a Note about the course.
     */
    private int addResult(ReportHeader reportHeader, int x, int y)
    {
    	int x2 = x + 140;
    	String texto;
    	ArrayList arrayTextos;
    	Iterator it;
    	
    	// El commentary drive no tiene notas.
        if (this.activity.getDefinition().isHaveNote())
        {
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y, -50, 15),
	                Color.blue,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE_PARAM_FONT,messages.getText("generic.activity.form.btw1liviano.result")));
	       	y += 20;
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y, -25, 15),
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.activity.note")+": "));        
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(152, y, 70, 15),
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(this.notaCorta)));	        
	        y += 20;
	        
	        arrayTextos = cutText(messages.getText(this.notaLarga), 127);
	        if (arrayTextos.size() > 0)
	        {
	        	it = arrayTextos.iterator();
	        	while (it.hasNext())
	        	{
	        		texto = (String) it.next();
	        	      	reportHeader.addElement(LabelElementFactory.createLabelElement(
	        	      			null,new Rectangle2D.Float(10, y, -100, 10),
	        	      			Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,
	        	      			texto));
	        	      	y += 10;
	        	}
	        }
	        y += 10;
	        
	        /*
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y, -100, 15),
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText(this.notaLarga)));
	                        
	        y += 20;
*/	        	        
        }
    	return y;    	
    }
   
    /*
     * Add a Technical Aspect Section: 
     */ 
    private int addTechnicalAspects(ReportHeader reportHeader, int x, int y)
    {
    	ArrayList arrayTextos;
    	Iterator it;
    	String texto;
        if (tieneItems) {
        	
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, y, -100, 15),
	                Color.blue,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE_PARAM_FONT,messages.getText("generic.activity.form.btw1liviano.technicalaspectscoached")));
	        y+=20;
	        
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, y,-100, 60),
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("dc4.textosinforme.technicalaspects.1")));
	        
	        y += 70;
	        
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, y,-100, 60),
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("dc4.textosinforme.technicalaspects.2")));
	        
	        y += 10;     
	                
                	        
        }
        return y;
    }
    
    protected ReportHeader createReportHeader() {        
        
    	ReportHeader reportHeader = new ReportHeader();
        
    	int y1 = 0; //(Coordenadas para la primer columna)
        int x1 = 10;
        int y2 = 0; //(Coordenadas para la segunda columna)
        int x2 = 275;
        
        // Title:
        y1 = addHeaderTitle(reportHeader, x1, y1);
        y2 = y1;
        
        // Driver Data:
        y1 = addDriverData(reportHeader,x1,y1);
        // Assigned Vehicle & Divisions:
        y1 = addAssignedVehicle(reportHeader, x1,y1);        
        // Result Section:
        y1 = addResult(reportHeader, x1,y1);
        
        // Activity Data:
        y2 = addActivityData(reportHeader,x2,y2);
        // Location:
        y2 = addLocation(reportHeader,x2,y2);        

        x1 = 10; y1 = Math.max(y1,y2);
        // Technical Aspects: (Sección Aspectos técnicos valorados y entrenados)
        if (!this.activity.getDefinition().getResourceKeyName().
        		equals("datatype.scheduledactivitytype.gabinetepsicotecnico")){
        	y1 = addTechnicalAspects(reportHeader, x1, y1);        
        }
       
        return reportHeader;
    }
   

    protected ReportFooter createReportFooter() {
        ReportFooter reportFooter = new ReportFooter();
        ArrayList arrayTextos;
        String texto;
        Iterator it;
       if ((observaciones != null) && (!observaciones.equals(""))) { 
//      Agregar una sección titulada Observaciones Complementarias
        
/*        reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, Color.CYAN, null,
                new Rectangle2D.Float(10, 30, -100, 15), false, true));
*/
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 30,-100, 15),
                Color.blue,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE_PARAM_FONT,messages.getText("generic.activity.observations")));
        
    /*    
        arrayTextos = cutText(observaciones, 130);
        if (arrayTextos.size() > 0)
        {
        	it = arrayTextos.iterator();
        	int y = 50;
        	while (it.hasNext())
        	{
        		texto = (String) it.next();
        	      	reportFooter.addElement(LabelElementFactory.createLabelElement(
        	      			null,new Rectangle2D.Float(10, y, -100, 15),
        	      			Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,
        	      			texto));
        	      	y += 10;
        	}
        }
      */  
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 50,-100, 150),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,observaciones));
        
        
       }
        return reportFooter;
    }

    /*
    protected ReportHeader createReportHeader() {
        VehicleReportForm reportForm = (VehicleReportForm)form;
        ReportHeader reportHeader = new ReportHeader();
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, Color.decode("#808080"), null,
                new Rectangle2D.Float(0, 0, getWidth(), 25), false, true));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(0, 0, getWidth(), 25),
                Color.decode("#D0D0D0"),ElementAlignment.CENTER,ElementAlignment.MIDDLE,new FontDefinition ("SansSerif",18),"Vehículos - Resultado de la Consulta"));
        
        int height = 25; 
        
        height = writeParameter("Tipo:",reportForm.getType(),reportHeader,height,0,100,100,200);
        height = writeParameter("Marca:",reportForm.getTrade(),reportHeader,height,0,100,100,200);
        height = writeParameter("Año:",reportForm.getYear(),reportHeader,height,0,100,100,200);
        height = writeParameter("Uso:",reportForm.getUse(),reportHeader,height,0,100,100,200);
        height = writeParameter("Combustible:",reportForm.getFuel(),reportHeader,height,0,100,100,200);

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                height = writeParameter(divOrgLabels[i][j]+":",divOrgValues[i][j],reportHeader,height,0,100,100,200);
            }
        }
        for(int i = 0; i < 3; i++) {
            height = writeParameter(divGeoLabels[i]+":",divGeoValues[i],reportHeader,height,0,100,100,200);
        }

                
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,Color.decode("#808080"), null,
                new Rectangle2D.Float(0, height, getWidth(), 18), false, true));
        
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(0, height, 100, 18),
                Color.decode("#D0D0D0"),ElementAlignment.CENTER,ElementAlignment.MIDDLE,fontDefinitionBold,messages.getText("generic.vehicle.vehicledata.registrationNumber")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(100, height, 100, 18),
                Color.decode("#D0D0D0"),ElementAlignment.CENTER,ElementAlignment.MIDDLE,fontDefinitionBold,messages.getText("generic.vehicle.vehicledata.vehicletype")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(200, height, 100, 18),
                Color.decode("#D0D0D0"),ElementAlignment.CENTER,ElementAlignment.MIDDLE,fontDefinitionBold,messages.getText("generic.vehicle.vehicledata.trade")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(300, height, 100, 18),
                Color.decode("#D0D0D0"),ElementAlignment.CENTER,ElementAlignment.MIDDLE,fontDefinitionBold,messages.getText("generic.vehicle.vehicledata.model")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(400, height, 100, 18),
                Color.decode("#D0D0D0"),ElementAlignment.CENTER,ElementAlignment.MIDDLE,fontDefinitionBold,messages.getText("generic.vehicle.vehicledata.year")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(500, height, 100, 18),
                Color.decode("#D0D0D0"),ElementAlignment.CENTER,ElementAlignment.MIDDLE,fontDefinitionBold,messages.getText("generic.vehicle.vehicledata.country")));
        
        int w = 600;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(!Util.empty(divOrgLabels[i][j])) {
                    reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                            Color.decode("#D0D0D0"),ElementAlignment.CENTER,ElementAlignment.MIDDLE,fontDefinitionBold,messages.getText(divOrgLabels[i][j])));
                    w += 100;
                }
            }
        }
        return reportHeader;
    }

    protected int writeParameter(String parameterName,String parameterValue,ReportHeader reportHeader,int height,int xLabel,int wLabel,int xValue,int wValue) {
        if(!Util.empty(parameterValue)) {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(xLabel, height, wLabel, 18),
                    Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,fontDefinitionBold,parameterName));
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(xValue, height, wValue, 18),
                    Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,fontDefinitionSimple,parameterValue));
            return height+20;
        }
        return height;
    }

    protected ReportFooter createReportFooter() {
        ReportFooter reportFooter = new ReportFooter();
        reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, Color.GRAY, null,
                new Rectangle2D.Float(0, 10, getWidth(), 30), false, true));
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(0, 15, 200, 20),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,new FontDefinition ("SansSerif",18),"Total"));
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement(null,new Rectangle2D.Float(200, 15, 200, 20),
                Color.BLACK,ElementAlignment.RIGHT,ElementAlignment.MIDDLE,new FontDefinition ("SansSerif",18),"", "#####","countExpression"));
        return reportFooter;
    }
*/
   
  /*
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
        color.setColorFalse(Color.LIGHT_GRAY);
        functions.add(color);

        TextFormatExpression textExp = new TextFormatExpression();
        textExp.setName("URLCreateExpression");
        textExp.setPattern("http://localhost:8080/datacenter/layout.jsp?refer=/vehicle/VehicleView.jsp?id={0}");
        textExp.setField(0,"id");
        functions.add(textExp);

        CreateHyperLinksFunction link = new CreateHyperLinksFunction();
        link.setName("AddHRefs");
        link.setField("URLCreateExpression");
        link.setElement("URL-Field");
        functions.add(link);

        return functions;
    }
    */
}
