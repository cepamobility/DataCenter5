package presentation.report.driver;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

import javax.swing.table.TableModel;

import org.apache.struts.action.ActionForm;
import org.jfree.report.ElementAlignment;
import org.jfree.report.ItemBand;
import org.jfree.report.ReportHeader;
import org.jfree.report.elementfactory.LabelElementFactory;
import org.jfree.report.elementfactory.NumberFieldElementFactory;
import org.jfree.report.elementfactory.StaticShapeElementFactory;
import org.jfree.report.elementfactory.TextFieldElementFactory;

import presentation.translator.web.driver.DriverMultiReportForm;
import presentation.translator.web.driver.DriverReportForm;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.administration.corporation.tables.CorporationData;
import communication.language.Text;

public class DetailedDriverReportWithVehiclesFactory extends DriverReportFactory {

    public DetailedDriverReportWithVehiclesFactory(String name, TableModel model,ActionForm form, SystemAccess sys, Text messages, int pages, int pageNumber) {
        super(name, model, form, sys, messages, 13, pages,pageNumber);
        if(sys.getUserSessionData().isForest()) {
            last += 842;
        }
        if(sys.getCorporationId() != CorporationData.CIMSA) {
        	last += 80;
        }
        if(sys.isCorporation(new int[]{CorporationData.JJ,CorporationData.MONSANTO,CorporationData.DOW,CorporationData.SYNGENTA_BR,CorporationData.BAYER_MX})) {
        	last += 220; // last += 80;
        }
        
        last = last + 560; // sumo los campos de vehiculos 7 campos de 80 de ancho
        
        last = last + 2480; // sumo 14 campos nuevos de vehiculos, pedidos el 8/11/2011 
    }

    public int getWidth() {        
        return last+82;
    }
    
    public int getReportWidth() {
        return last; 
    }

    protected ReportHeader createReportHeader() {
		//DriverReportForm reportForm = (DriverReportForm) form;
		ReportHeader reportHeader = new ReportHeader();
		int height = (sys.isCorporation(new int[]{CorporationData.LOSPIQUES,CorporationData.ALLERGAN})) ? writeFilter((DriverMultiReportForm) form, reportHeader, last - 1380) : writeFilter((DriverReportForm) form, reportHeader, last - 1380);
        if(sys.isBrasilCorporation()) {
        	reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,
                    new Rectangle2D.Float(10, height, getReportWidth(), 15), false, true));
        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 160, 15),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,messages.getText("query.driver.driverdata.total")));
            int start = (sys.getUserSessionData().isForest() && sys.getUserSessionData().getFilter().getCorporationId().intValue() != CorporationData.KLABIN) ? last + 640 : last;
            reportHeader.addElement(NumberFieldElementFactory.createNumberElement(null,new Rectangle2D.Float(start-80, height, 80, 15),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,"", formatNoDecimals,"total"));
            height += 15;
        }
		
		reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor,null, new Rectangle2D.Float(10, height, getReportWidth(), 18),false, true));
        
		int x = 10;
        if(sys.isCorporation(CorporationData.BAYER_MX)) {
        	reportHeader.addElement(LabelElementFactory.createLabelElement(
        		null,new Rectangle2D.Float(x, height, 80, 18), Color.WHITE,
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, 
                messages.getText("generic.driver.driverdata.corporationid")));
        	x += 80;
        }
        reportHeader.addElement(LabelElementFactory.createLabelElement(
        		null,new Rectangle2D.Float(x, height, 80, 18), Color.WHITE,
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.firstname")));
        
        x+= 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(
        		null,new Rectangle2D.Float(x, height, 80, 18), Color.WHITE,
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, 
                messages.getText("generic.driver.driverdata.lastname")));
        x+= 80;
        if(!sys.isCorporation(CorporationData.SYNGENTA_BR)) {
        	reportHeader.addElement(LabelElementFactory.createLabelElement(
        		null,new Rectangle2D.Float(x, height, 60, 18), Color.WHITE,
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, 
                messages.getText("generic.driver.driverdata.director")));
        	x+= 60;
        }
        reportHeader.addElement(LabelElementFactory.createLabelElement(
        		null,new Rectangle2D.Float(x, height, 80, 18), Color.WHITE,
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, 
                messages.getText("generic.driver.driverdata.temporal")));
        x+= 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(
        		null,new Rectangle2D.Float(x, height, 80, 18), Color.WHITE, 
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, 
                messages.getText("generic.driver.driverdata.birthdate")));
        x+= 80;
        

        if(!sys.isCorporation(CorporationData.BAYER_MX)) {
        	reportHeader.addElement(LabelElementFactory.createLabelElement(
        		null,new Rectangle2D.Float(x, height, 80, 18), Color.WHITE,
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, 
                messages.getText("generic.driver.driverdata.corporationid")));
        	x += 80;
        }
        // ID Dallas
        reportHeader.addElement(LabelElementFactory.createLabelElement(
        		null,new Rectangle2D.Float(x, height, 80, 18), Color.WHITE,
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, 
                messages.getText("generic.driver.driverdata.iddallas")));
        x += 80;
        
        reportHeader.addElement(LabelElementFactory.createLabelElement(
        		null,new Rectangle2D.Float(x, height, 80, 18), Color.WHITE, 
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT,
                messages.getText("generic.driver.driverdata.nationality")));
        x+= 80;
        
        reportHeader.addElement(LabelElementFactory.createLabelElement(
        		null,new Rectangle2D.Float(x, height, 70, 18), Color.WHITE, 
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, 
                messages.getText("generic.driver.driverdata.sex")));
        x += 70;
        
        reportHeader.addElement(LabelElementFactory.createLabelElement(
        		null,new Rectangle2D.Float(x, height, 90, 18), Color.WHITE, 
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, 
                messages.getText("generic.driver.driverdata.country")));
        x += 90;
        
        reportHeader.addElement(LabelElementFactory.createLabelElement(
        		null,new Rectangle2D.Float(x, height, 90, 18), Color.WHITE, 
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, 
                messages.getText("generic.vehicle.vehicledata.costCenter")));
        x += 90;

        for(int i = 0; i < 9; i++) {
            if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 3) && 
                    !(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 6)) {
                if(!Util.empty(labels[i])) {
                    reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                        Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(labels[i])));
                    x += 80;
                }
            }
        }
        if(sys.getCorporationId() == CorporationData.CIMSA) {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("corporation.monsanto.assignedvehicles")));
            x += 80;
        }
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18), Color.WHITE, 
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.city")));
        x += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18), Color.WHITE, 
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("driver.query.licenseexpiry")));
        x += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18), Color.WHITE, 
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("driver.query.licensecategory")));
        x += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18), Color.WHITE, 
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("driver.query.licenseget")));
        x += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18), Color.WHITE, 
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.vehicleallowed")));
        x += 80;
        
        if (sys.getCorporationId() != CorporationData.CIMSA){
        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18), Color.WHITE, 
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.officeaddress")));
        	x += 80;
        	
        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 100, 18), Color.WHITE, 
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.email")));
        	x += 100;
        }
        
        if(sys.isCorporation(new int[] {CorporationData.JJ,CorporationData.MONSANTO,CorporationData.BAYER_MX,CorporationData.SYNGENTA_BR})) {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 90, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.superviser")));
            x += 90;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 90, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.manager")));
            x += 90;
        }
        if(sys.isCorporation(CorporationData.DOW)) {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 90, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.focalpointid")));
            x += 90;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 90, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.focalpointname")));
            x += 90;
        }
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18), Color.WHITE, 
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.personaladdress")));
        x += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18), Color.WHITE, 
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.licensenumber")));
        x += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18), Color.WHITE, 
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.phonenumber")));
        x += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18), Color.WHITE, 
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("driver.query.inclusiondate")));
        x += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18), Color.WHITE, 
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("driver.query.exclusiondate")));
        x += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18), Color.WHITE, 
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.drives")));
        x += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18), Color.WHITE, 
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.employee")));
        x += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18), Color.WHITE, 
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.contact")));
       
        if(sys.getUserSessionData().isForest()) {
            x += 80;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.bpsregistry")));
            x += 80;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.aptitudtest.query.lasttest")));
            x += 80;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.aptitudtest.query.isapt")));
            x += 80;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.aptitudtest.expiry")));
            x += 80;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.alcoholcontrol.query.lasttest")));
            x += 80;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.alcoholcontrol.query.result")));
            x += 80;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.alcoholcontrol.expiry")));
            x += 80;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.alcoholcontrol.anypositive")));
        }
        if(sys.getCorporationId() == CorporationData.CIMSA) {
            x += 80;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.finedata.points")));
        }
        
        //  Datos del Vehiculo:
      
        	x += 80;
        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.registrationNumber")));
        	x += 80;
        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.vehicletype")));
        	x += 80;
        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.trade")));
        	x += 80;
        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 100, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.model")));
        	x += 100;
        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.year")));
        	 x += 80;
        	 
        	// COLOR:
        	 reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                     Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
                     messages.getText("generic.vehicle.vehicledata.colour")));
        	 x += 80;
        	 
        	 // Numero de Renavam:
        	 reportHeader.addElement(LabelElementFactory.createLabelElement(
        			 null,new Rectangle2D.Float(x, height, 80, 18),
                     Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,
                     STYLE_SIMPLE_BOLD_FONT,
                     messages.getText("generic.vehicle.vehicledata.permissionNumber")));
             
        	 x += 80;
        	 reportHeader.addElement(LabelElementFactory.createLabelElement(
        			 null,new Rectangle2D.Float(x, height, 80, 18),
                     Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,
                     STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.fuel")));
             x += 80;
             
             reportHeader.addElement(LabelElementFactory.createLabelElement(
            		 null,new Rectangle2D.Float(x, height, 80, 18),
                     Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,
                     STYLE_SIMPLE_BOLD_FONT,
                     messages.getText("generic.vehicle.vehicledata.purchaseDate")));
             
             x += 80;
             reportHeader.addElement(LabelElementFactory.createLabelElement(
            		 null,new Rectangle2D.Float(x, height, 80, 18),
                     Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,
                     STYLE_SIMPLE_BOLD_FONT,
                     messages.getText("generic.vehicle.vehicledata.chasisNumber")));
             x += 80;
        	
             reportHeader.addElement(LabelElementFactory.createLabelElement(
            		 null,new Rectangle2D.Float(x, height, 80, 18),
                     Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,
                     STYLE_SIMPLE_BOLD_FONT,
                     messages.getText("generic.vehicle.vehicledata.engine")));
             x += 80;
             
             reportHeader.addElement(LabelElementFactory.createLabelElement(
            		 null,new Rectangle2D.Float(x, height, 80, 18),
                     Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,
                     STYLE_SIMPLE_BOLD_FONT,
                     messages.getText("generic.vehicle.vehicledata.tankCapacity")));
             x += 80;
             
             reportHeader.addElement(LabelElementFactory.createLabelElement(
            		 null,new Rectangle2D.Float(x, height, 80, 18),
                     Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,
                     STYLE_SIMPLE_BOLD_FONT,
                     messages.getText("generic.vehicle.vehicledata.volumeunit")));
             x += 80;
             
             reportHeader.addElement(LabelElementFactory.createLabelElement(
            		 null,new Rectangle2D.Float(x, height, 80, 18),
                     Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,
                     STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.abs")));
             x += 80;
             
             reportHeader.addElement(LabelElementFactory.createLabelElement(
            		 null,new Rectangle2D.Float(x, height, 80, 18),
                     Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,
                     STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.airBag")));
             x += 80;
             
             reportHeader.addElement(LabelElementFactory.createLabelElement(
            		 null,new Rectangle2D.Float(x, height, 80, 18),
                     Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,
                     STYLE_SIMPLE_BOLD_FONT,
                     messages.getText("generic.vehicle.vehicledata.aireAcondicionado")));
             
             x += 80;
             reportHeader.addElement(LabelElementFactory.createLabelElement(
            		 null,new Rectangle2D.Float(x, height, 80, 18),
                     Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,
                     STYLE_SIMPLE_BOLD_FONT,
                     messages.getText("generic.vehicle.vehicledata.cover")));
             
             x += 80;
             reportHeader.addElement(LabelElementFactory.createLabelElement(
            		 null,new Rectangle2D.Float(x, height, 80, 18),
                     Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,
                     STYLE_SIMPLE_BOLD_FONT,
                     messages.getText("generic.vehicle.vehicledata.distanceunit")));
             x += 80;
             
        	reportHeader.addElement(LabelElementFactory.createLabelElement(
        			null,new Rectangle2D.Float(x, height, 80, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,
                    STYLE_SIMPLE_BOLD_FONT,
                    messages.getText("generic.driver.driverdata.inclusiondate")));
        	x += 80;
        	reportHeader.addElement(LabelElementFactory.createLabelElement(
        			null,new Rectangle2D.Float(x, height, 80, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,
                    STYLE_SIMPLE_BOLD_FONT,
                    messages.getText("corporation.monsanto.assignedvehicles")));
        	
        	 if(sys.getCorporationId() == CorporationData.MONSANTO) {
                 x += 80;
                 reportHeader.addElement(LabelElementFactory.createLabelElement(
                		 null,new Rectangle2D.Float(x, height, 80, 18),
                         Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,
                         STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.messages.odometer")));        
             }   
        	
        
        
		return reportHeader;
	}

	protected ItemBand createItemBand() {
		ItemBand items = new ItemBand();
		int x = 10;
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement
        		("Variance Field", null, null, new Rectangle2D.Float(10, 0,getReportWidth(), 15), false, true));
        
        if(sys.isCorporation(CorporationData.BAYER_MX)) {
        	items.addElement(TextFieldElementFactory.createStringElement(
        		"URL-Field",new Rectangle2D.Float(x, 0, 80, 15), Color.BLACK,
                ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "corporationid"));
        
        	x += 80;
        }
        items.addElement(TextFieldElementFactory.createStringElement(
        		"URL-Field", new Rectangle2D.Float(x, 0, 80, 15), Color.BLACK,
                ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "name"));
        
        x += 80;
        items.addElement(TextFieldElementFactory.createStringElement(
        		"URL-Field", new Rectangle2D.Float(x, 0, 80, 15), Color.BLACK,
                ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "lastname"));
        x += 80;  
        if(!sys.isCorporation(CorporationData.SYNGENTA_BR)) {
        	items.addElement(TextFieldElementFactory.createStringElement(
        		"director", new Rectangle2D.Float(x, 0, 60, 15), Color.BLACK,
                ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "director"));
        	x += 60;
        }
        items.addElement(TextFieldElementFactory.createStringElement(
        		"temporal", new Rectangle2D.Float(x, 0, 80, 15), Color.BLACK,
                ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "temporal"));
        x += 80;  
        items.addElement(TextFieldElementFactory.createStringElement(
        		"birth",new Rectangle2D.Float(x, 0, 80, 15), Color.BLACK,
                ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "birthdate"));
        
        x += 80;
        
        if(!sys.isCorporation(CorporationData.BAYER_MX)) {
        	items.addElement(TextFieldElementFactory.createStringElement(
        		"corporationid",new Rectangle2D.Float(x, 0, 80, 15), Color.BLACK,
                ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "corporationid"));
        
        	x += 80;
        }
        // ID Dallas:
        items.addElement(TextFieldElementFactory.createStringElement(
        		"idDallas",new Rectangle2D.Float(x, 0, 80, 15), Color.BLACK,
                ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "idDallas"));
        x += 80;        
        
        items.addElement(TextFieldElementFactory.createStringElement(
        		"nationality", new Rectangle2D.Float(x, 0, 80, 15),
                Color.BLACK, ElementAlignment.LEFT, 
                ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "nationality"));
        x += 80;
        
        items.addElement(TextFieldElementFactory.createStringElement(
        		"sex",new Rectangle2D.Float(x, 0, 70, 15), Color.BLACK,
                ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "sex"));
        x+= 70;
        
        items.addElement(TextFieldElementFactory.createStringElement(
        		"country", new Rectangle2D.Float(x, 0, 90, 15),
                Color.BLACK, ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "country"));
        x += 90;
        
        items.addElement(TextFieldElementFactory.createStringElement(
        		"costCenter", new Rectangle2D.Float(x, 0, 90, 15),
                Color.BLACK, ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "costCenter"));
        x += 90;
        
        
        for(int i = 0; i < 3; i++) {
           // for(int j = 2; j >= 0; j--) {
        	 for(int j = 0; j < 3; j++) {
                if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 1 && j== 0) && 
                        !(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 2 && j== 0)) {
                    if(!Util.empty(labels[i*3+j])) {
                        items.addElement(TextFieldElementFactory.createStringElement("divorg"+String.valueOf(i+1)+String.valueOf(j+1),
                                new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,
                                ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","divorg"+String.valueOf(i+1)+String.valueOf(j+1)));
                        x += 80;
                    }
                }
            }
        }
        if(sys.getCorporationId() == CorporationData.CIMSA) {
            items.addElement(TextFieldElementFactory.createStringElement("assignedVehicle",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","assignedVehicle"));
            x += 80;
        }
        items.addElement(TextFieldElementFactory.createStringElement("city",new Rectangle2D.Float(x, 0, 80, 15), Color.BLACK,
                ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "city"));
        x += 80;
        items.addElement(TextFieldElementFactory.createStringElement("licenseexpiry", new Rectangle2D.Float(x, 0, 80, 15),
                Color.BLACK, ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "licenseExpiry"));
        x += 80;
        items.addElement(TextFieldElementFactory.createStringElement("licensecategory", new Rectangle2D.Float(x, 0, 80, 15),
                Color.BLACK, ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "licenseCategory"));
        x += 80;
        items.addElement(TextFieldElementFactory.createStringElement("licenseget", new Rectangle2D.Float(x, 0, 80, 15),
                Color.BLACK, ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "licenseGet"));
        x += 80;
        items.addElement(TextFieldElementFactory.createStringElement("vehicleallowed", new Rectangle2D.Float(x, 0, 80, 15),
                Color.BLACK, ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "vehicleallowed"));
        x += 80;
        
        if (sys.getCorporationId() != CorporationData.CIMSA){
        	items.addElement(TextFieldElementFactory.createStringElement("officeaddress", new Rectangle2D.Float(x, 0, 80, 15),
                Color.BLACK, ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "officeaddress"));
        	x += 80;
        	items.addElement(TextFieldElementFactory.createStringElement("email",new Rectangle2D.Float(x, 0, 100, 15), Color.BLACK,
                ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "email"));
        	x += 100;
        }
        
        if(sys.isCorporation(new int[]{CorporationData.JJ,CorporationData.MONSANTO,CorporationData.DOW,CorporationData.BAYER_MX,CorporationData.SYNGENTA_BR})) {
        	items.addElement(TextFieldElementFactory.createStringElement("superviser", new Rectangle2D.Float(x, 0, 90, 15),
                    Color.BLACK, ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "superviser"));
            x += 90;
        	items.addElement(TextFieldElementFactory.createStringElement("manager", new Rectangle2D.Float(x, 0, 90, 15),
                    Color.BLACK, ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "manager"));
            x += 90;
        }
        items.addElement(TextFieldElementFactory.createStringElement("personaladdress", new Rectangle2D.Float(x, 0, 80, 15),
                Color.BLACK, ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "personaladdress"));
        x += 80;
        items.addElement(TextFieldElementFactory.createStringElement("licensenumber", new Rectangle2D.Float(x, 0, 80, 15),
                Color.BLACK, ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "licensenumber"));
        x += 80;
        items.addElement(TextFieldElementFactory.createStringElement("phonenumber", new Rectangle2D.Float(x, 0, 80, 15),
                Color.BLACK, ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "phonenumber"));
        x += 80;
        items.addElement(TextFieldElementFactory.createStringElement("inclusiondate", new Rectangle2D.Float(x, 0, 80, 15),
                Color.BLACK, ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "---", "inclusiondate"));
        x += 80;
        items.addElement(TextFieldElementFactory.createStringElement("exclusion",new Rectangle2D.Float(x, 0, 80, 15), Color.BLACK,
                ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "---", "exclusiondate"));
        x += 80;
        items.addElement(TextFieldElementFactory.createStringElement("drives",new Rectangle2D.Float(x, 0, 80, 15), Color.BLACK,
                ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "drives"));
        x += 80;
        items.addElement(TextFieldElementFactory.createStringElement("employee", new Rectangle2D.Float(x, 0, 80, 15), Color.BLACK,
                ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "employee"));
        x += 80;
        items.addElement(TextFieldElementFactory.createStringElement("contract", new Rectangle2D.Float(x, 0, 80, 15), Color.BLACK,
                ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "contract"));
        if(sys.getUserSessionData().isForest()) {
            x += 80;
            items.addElement(TextFieldElementFactory.createStringElement("bps",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","bps"));
            x += 80;
            items.addElement(TextFieldElementFactory.createStringElement("test",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","test"));
            x += 80;
            items.addElement(TextFieldElementFactory.createStringElement("apt",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","apt"));
            x += 80;
            items.addElement(TextFieldElementFactory.createStringElement("expiry",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","expiry"));
            x += 80;
            items.addElement(TextFieldElementFactory.createStringElement("lastControl",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","lastControl"));
            x += 80;
            items.addElement(TextFieldElementFactory.createStringElement("result",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","result"));
            x += 80;
            items.addElement(TextFieldElementFactory.createStringElement("status",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","status"));
            x += 80;
            items.addElement(TextFieldElementFactory.createStringElement("anypositive",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","anypositive"));
        }
        if(sys.getCorporationId() == CorporationData.CIMSA) {
            x += 80;
            items.addElement(TextFieldElementFactory.createStringElement("points",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","points"));
        }
        
        // Datos del Vehiculo:
       
            x += 80;
            items.addElement(TextFieldElementFactory.createStringElement("plate",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","plate"));
            x += 80;
            items.addElement(TextFieldElementFactory.createStringElement("vehicleType",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","vehicleType"));
            x += 80;
            items.addElement(TextFieldElementFactory.createStringElement("trade",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","trade"));
            x += 80;
            items.addElement(TextFieldElementFactory.createStringElement("model",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","model"));
            x += 100;
            items.addElement(TextFieldElementFactory.createStringElement("year",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","year"));
            x += 80;
            
            items.addElement(TextFieldElementFactory.createStringElement("colour",
            		new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,
            		ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","colour"));
            x += 80;
            
            items.addElement(TextFieldElementFactory.createStringElement("permissionNumber",
            		new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,
            		ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","permissionNumber"));
            x += 80;
            
            items.addElement(TextFieldElementFactory.createStringElement("fuel",
            		new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,
            		ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","fuel"));
            x += 80;
            
            items.addElement(TextFieldElementFactory.createStringElement("purchaseDate",
            		new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,
            		ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","purchaseDate"));
            x += 80;
            
            items.addElement(TextFieldElementFactory.createStringElement("chasisNumber",
            		new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,
            		ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","chasisNumber"));
            x += 80;
            
            items.addElement(TextFieldElementFactory.createStringElement("engine",
            		new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,
            		ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","engine"));
            x += 80;
            
            items.addElement(TextFieldElementFactory.createStringElement("tankCapacity",
            		new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,
            		ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","tankCapacity"));
            x += 80;
            
            items.addElement(TextFieldElementFactory.createStringElement("tankUnit",
            		new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,
            		ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","tankUnit"));
            x += 80;
            
            items.addElement(TextFieldElementFactory.createStringElement("abs",
            		new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,
            		ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","abs"));
            x += 80;
            
            items.addElement(TextFieldElementFactory.createStringElement("airBag",
            		new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,
            		ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","airBag"));
            x += 80;
            
            items.addElement(TextFieldElementFactory.createStringElement("aireAcondicionado",
            		new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,
            		ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","aireAcondicionado"));
            x += 80;
            
            items.addElement(TextFieldElementFactory.createStringElement("cover",
            		new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,
            		ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","cover"));
            x += 80;
            
            items.addElement(TextFieldElementFactory.createStringElement("odometerUnit",
            		new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,
            		ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","odometerUnit"));
            x += 80;
            
            items.addElement(TextFieldElementFactory.createStringElement("vehicleInclusionDate",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","vehicleInclusionDate"));
            x += 80;
            items.addElement(TextFieldElementFactory.createStringElement("assignedVehicle",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","assignedVehicle"));
        
            if (sys.getCorporationId() == CorporationData.MONSANTO){
            	x += 80;
            	items.addElement(TextFieldElementFactory.createStringElement("odometer",
            		new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,
            		ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","odometer"));
            }
            
        
		return items;
	}
	    
	

}
