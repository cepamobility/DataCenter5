package presentation.report.activity;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.jfree.report.ElementAlignment;
import org.jfree.report.ReportFooter;
import org.jfree.report.ReportHeader;
import org.jfree.report.elementfactory.LabelElementFactory;
import org.jfree.report.elementfactory.StaticShapeElementFactory;

import presentation.report.ReportFactory;
import presentation.translator.web.util.Util;
import business.SystemAccess;

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

public class CepaSystemReportFactory extends ReportFactory {

	private CEPAActivityScheduleRegistryData registryData;
	private CEPAActivityScheduleData activitySchedule;
	private CheckListData checkList;
	protected String reportType;
	// private SystemAccess sys;
	protected DriverData driver;
	private Text messages;

	public CepaSystemReportFactory(CEPAActivityScheduleRegistryData registryData, 
			CEPAActivityScheduleData activitySchedule, String name, ActionForm form, 
			CheckListData checkList, SystemAccess sys) {
		super(name, null, form, sys);
		this.registryData = registryData;
		this.sys= sys;
		this.messages = sys.getText();
		this.checkList = checkList;
		this.activitySchedule = activitySchedule;
		this.reportType = activitySchedule.getType().getResourceBundleId();
		this.driver = registryData.getDriver();
	}
    
    protected int getReportWidth() {
    	return 580;
    }
    
    
    protected int addPoints(ReportHeader reportHeader,int x1,int y1) {        
        
//    	ReportHeader reportHeader = super.createReportHeader();
//    	
//    	reportHeader.addElement(org.jfree.report.elementfactory.ImageFieldElementFactory.
//     			createImageDataRowElement("logoCepa", new Rectangle2D.Float(10, 10, 50, 30), "logoCepa"));
//
//    	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 60, getReportWidth()-80, 25),
//        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("query.cepasystem.title")));
//    	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(35, 60, getReportWidth()-80, 25),
//        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("query.cepasystem.subtitle")));
//    	
//
//    	if(sys.getCorporationId() == CorporationData.PEPSICO) {
//    		reportHeader.addElement(org.jfree.report.elementfactory.ImageFieldElementFactory.
//     			createImageDataRowElement("logoPepsico", new Rectangle2D.Float(400, 10, 80, 30), "logoPepsico"));
//    	}
//    	
//		int height = 90;
//		height = writeParameter(messages.getText("generic.activity.activitydata.type")+":",messages.getText(registryData.getActivity().getDefinition().getResourceKeyName()),reportHeader,height,10,80,100,380);
//		height = writeParameter(messages.getText("generic.activity.date")+":",Util.formatDate(registryData.getActivity().getDate()),reportHeader,height,10,80,100,380);
//		height = writeParameter(messages.getText("generic.activity.country")+":",registryData.getActivity().getCountryName(),reportHeader,height,10,80,100,380);
//		height = writeParameter(messages.getText("generic.activity.city")+":",registryData.getActivity().getCity(),reportHeader,height,10,80,100,380);
//		height = writeParameter(messages.getText("generic.activity.instructor")+":",registryData.getInstructor(),reportHeader,height,10,80,100,380);

				
//		height = writeParameter(messages.getText("generic.data.driver")+":",registryData.getDriver().getFirstName()+" "+registryData.getDriver().getLastName(),reportHeader,height,10,80,100,380);
//	
	
		// int height += 30;
    	int height = y1;
		
		Collection points = checkList.getPoints();
		Collections.sort((List)points);
		Iterator pointIt = points.iterator();
		while(pointIt.hasNext()) {	
			PointData point = (PointData) pointIt.next();
 			Collection items = point.getItems();
			Collections.sort((List)items);
			Iterator itemIt = items.iterator();
			if(itemIt.hasNext()) {
				communication.activity.tables.ItemData item = (communication.activity.tables.ItemData)itemIt.next();
				String display = "none";
				String comment = "";
				Integer valueItem = registryData.getValueFromItem(item);
				int value = (valueItem == null) ? 0 : valueItem.intValue();
				String strValue = (value != 2) ? messages.getText("cepasystem.result.satisfactied") : messages.getText("cepasystem.result.unsatisfactied");
				height = writeParameter(messages.getText(item.getText())+":",strValue,reportHeader,height,x1,280,350,200);
				if(value == 2) {
					comment = registryData.getPointComment(point);
					height = writeComment(messages.getText("generic.activity.form.commentarydrive.comments")+":",comment,reportHeader,height,x1,80,100,380);
					height = writeParameter("","",reportHeader,height,x1,0,10,480);
				}
			}
		}
		
		return height;
    }
    
    protected int writeComment(String parameterName,String parameterValue,ReportHeader reportHeader,int height,int xLabel,int wLabel,int xValue,int wValue) {
        if(!Util.empty(parameterValue)) {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(xLabel, height, wLabel, 98),
                    Color.BLACK,ElementAlignment.LEFT,ElementAlignment.TOP,STYLE_SIMPLE_BOLD_FONT,parameterName));
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(xValue, height, wValue, 98),
                    Color.BLACK,ElementAlignment.LEFT,ElementAlignment.TOP,STYLE_SIMPLE_FONT,parameterValue));
            return height+100;
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
        	if (activitySchedule.getCorpooration().getId().equals(new Integer(1))){
        		// LOGO MONSANTO:
        		reportHeader.addElement(org.jfree.report.elementfactory.ImageFieldElementFactory.
         			createImageDataRowElement("logoMonsanto", new Rectangle2D.Float(420, y1, 100, 45), "logoMonsanto"));
        	} else if (activitySchedule.getCorpooration().getId().equals(new Integer(4))){
        		// Obtengo la Region del driver para ver si muestro el lofo de Safe Fleet LA o el de J&J:
        		GeoItemData region = (GeoItemData)registryData.getDriver().getCountry().getItems().iterator().next();
        		if (region.getName().equals("LA&C")){
        			// LOGO Safe Fleet J&J Latino America
        			reportHeader.addElement(org.jfree.report.elementfactory.ImageFieldElementFactory.
             			createImageDataRowElement("logoSafeFleetLA", new Rectangle2D.Float(473, y1, 47, 45), "logoSafeFleetLA"));
        		}else{
        			// LOGO J&J:
            		reportHeader.addElement(org.jfree.report.elementfactory.ImageFieldElementFactory.
                 			createImageDataRowElement("logoJ&J", new Rectangle2D.Float(420, y1, 100, 45), "logoJ&J"));
            		
        		}
          	} else if (activitySchedule.getCorpooration().getId().equals(new Integer(9))){
          		// LOGO FOSA
          		reportHeader.addElement(org.jfree.report.elementfactory.ImageFieldElementFactory.
             			createImageDataRowElement("logoMonsanto", new Rectangle2D.Float(420, y1, 100, 45), "logoFosa"));
          	} else if (activitySchedule.getCorpooration().getId().equals(CorporationData.PEPSICO)){
          		reportHeader.addElement(org.jfree.report.elementfactory.ImageFieldElementFactory.
             			createImageDataRowElement("logoPepsico", new Rectangle2D.Float(420, y1, 100, 45), "logoPepsico"));
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
		y1 = addPoints(reportHeader,x1,y1);
       
        return reportHeader;
    }
    
    /**
     * 
     */
    protected ReportFooter createReportFooter() {
       ReportFooter reportFooter = new ReportFooter();
       reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 50,100, 15),
               Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.activity.observations")+": "));
       
       String obs = cutCharsText(registryData.getObs(),110);
       
       reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 65, 480, 350),
               Color.BLACK,ElementAlignment.LEFT,ElementAlignment.TOP,STYLE_SIMPLE_FONT,obs));      
       return reportFooter;
    }
    
    /**
     * Esta funcion corta un texto en chars caracteres por linea separando siempre por espacios en blanco
     * en tantas lineas como sea necesario.
     * 
     * @param text
     * @param chars
     * @return
     */
    public String cutCharsText(String text, int chars){
    	char[] c = text.toCharArray();
    	String newText = new String();
    	
    	int count = 0;
    	for (int i = 0; i < c.length; i++){
    		
    		Character cc = new Character(c[i]);
    		newText = newText.concat(cc.toString());
    	
    		if (count >= chars && Character.isWhitespace(cc) ){
    			newText = newText.concat("\n");
    			count = 0;
    		}
    		count ++;
    		
    	}
    	return newText;
    }
    
    /*
     * Add a Title in the Header.
     */
    private int addHeaderTitle(ReportHeader reportHeader, int x, int y)
    {    
             

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
                messages.getText(activitySchedule.getType().getResourceBundleId())));
        x2 += anchoTipoDeActividad + espacio;
        // Date:
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x2, y, 85, altura),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
                " "+messages.getText("generic.activity.activitydata.date")+": "+
                Util.formatDate(activitySchedule.getDate())));
        x2 += anchoFecha + espacio/2;
        // Country
    	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x2, y, 100, altura),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
                " "+messages.getText("generic.activity.activitydata.country")+": "+
                messages.getText(activitySchedule.getCountryName())));
        y += 20;        
       
        
	    	// Instructor
        	x2 = x;
        	
        	if (registryData.getInstructor() == null){
        		reportHeader.addElement(LabelElementFactory.createLabelElement(
    	        		null,new Rectangle2D.Float(x2, y, 290, altura),
    	                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
    	                " "+messages.getText("generic.activity.instructor")+": N/A "));
        	}else{
        		reportHeader.addElement(LabelElementFactory.createLabelElement(
	        		null,new Rectangle2D.Float(x2, y, 290, altura),
	                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
	                " "+messages.getText("generic.activity.instructor")+": " + 
	                registryData.getInstructor()));
        	}
        	
	        // Fin Intructor
	        // City
	        x2 = 405;
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x2, y, 90, altura),
	                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
	                " "+messages.getText("generic.activity.activitydata.city")+": " +
	                activitySchedule.getCity()));
	        y += 20;
       
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
    	return y;	
    }
    
    /*
     * Add Assigned Vehicle Information
     */
    private int addAssignedVehicle(ReportHeader reportHeader, int x, int y)
    {   	
    	
    	ItemData item1, item2, item3;
    	Iterator it;    	
    	
    	it = activitySchedule.getDivOrgsIterator();		
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
    private int addResult(ReportHeader reportHeader, int x, int y)
    {
    	// El commentary drive no tiene notas.
        if (this.activitySchedule.getDefinition().isHaveNote())
        {
        	reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement
    				(null, DarkBlue , null,new Rectangle2D.Float(x, y, 545, 15), false, true));
        	
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y, 60, 15),
	                Color.white,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
	                " "+messages.getText("generic.activity.form.btw1liviano.result")+": "));
	        Color colorNote = Color.white;
	        /*
	        if (registry.getNote().equals("generic.activity.note.notapruved"))
	        	 colorNote= Color.RED.darker();
	        else 
	        	if (registry.getNote().equals("generic.activity.note.apruvedwithobservations"))
	        		colorNote = Color.YELLOW.darker();
	        	else 
	        		colorNote = Color.GREEN.darker();
	        */
	        reportHeader.addElement(LabelElementFactory.createLabelElement(
	        		null,new Rectangle2D.Float(x+65, y, 150, 15),
	                colorNote,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
	                messages.getText(registryData.getNote()).toUpperCase()));
	        y += 20;
        }
    	return y;    	
    }
    
}
