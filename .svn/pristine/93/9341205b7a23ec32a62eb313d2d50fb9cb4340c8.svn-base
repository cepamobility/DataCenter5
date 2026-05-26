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

public class DetailedDriverReportFactory extends DriverReportFactory {

    public DetailedDriverReportFactory(String name, TableModel model,ActionForm form, SystemAccess sys, Text messages, int pages, int pageNumber) {
        super(name, model, form, sys, messages, 13, pages,pageNumber);
        last += 940;
        if(sys.getUserSessionData().isForest()) {
        	last += 760;
        }
        if(sys.getCorporationId() != CorporationData.CIMSA) {
        	last += 260; // last += 80;
        }
        if(sys.getCorporationId() != CorporationData.DOW) {
        	last += 360; // last += 80;
        }
        if(sys.getCorporationId() != CorporationData.MONSANTO) {
        	last += 260;
        }
        if(sys.isCorporation(new int[]{CorporationData.BAYER,CorporationData.COVESTRO})) {
        	last += 80;
        }
        if(sys.isCorporation(CorporationData.ASTRAZENECA)) {
        	last += 120;
        }
        if(sys.isCorporation(CorporationData.JJ)) {
        	last += 180;
        }
        if(sys.isCorporation(CorporationData.MONSANTO)) {
        	last += 180;
        }
        if(sys.isCorporation(CorporationData.BAYER_MX)) {
        	last += 270;
        }
        if(sys.isCorporation(CorporationData.SYNGENTA_BR)) {
        	last += 210;
        }
    }

    public int getWidth() {
    	return last + 82;
    }
    
    public int getReportWidth() {
        return last;
    }

    protected ReportHeader createReportHeader() {
		//DriverReportForm reportForm = (DriverReportForm) form;
		ReportHeader reportHeader = new ReportHeader();
		int height = (sys.getCorporationId() == CorporationData.LOSPIQUES || sys.getCorporationId() == CorporationData.ALLERGAN) ? writeFilter((DriverMultiReportForm) form, reportHeader, last - 1380) : writeFilter((DriverReportForm) form, reportHeader, last - 1380);
		
        if(sys.isBrasilCorporation()) {
        	reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,
                    new Rectangle2D.Float(10, height, getReportWidth(), 15), false, true));
        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 160, 15),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,messages.getText("query.driver.driverdata.total")));
            int start = (sys.getUserSessionData().isForest() && sys.getUserSessionData().getFilter().getCorporationId().intValue() != CorporationData.KLABIN) ? last + 640 : last;
            reportHeader.addElement(NumberFieldElementFactory.createNumberElement(null,new Rectangle2D.Float(start - 80, height, 80, 15),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,"", formatNoDecimals,"total"));
            height += 15;
        }
		
		reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor,null, new Rectangle2D.Float(10, height, getReportWidth(), 18),false, true));

		int x = 10;
        if(sys.isCorporation(CorporationData.BAYER_MX)) {
        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18), Color.WHITE,
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.corporationid")));
        	x += 80;
        }
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18), Color.WHITE,
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.firstname")));
    	x += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18), Color.WHITE,
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.lastname")));
    	x += 80;
        if(!sys.isCorporation(CorporationData.SYNGENTA_BR)) {
        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 60, 18), 
        		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.director")));
        	x += 60;
        }
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18), 
        		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.temporal")));
    	x += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18), Color.WHITE, 
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.birthdate")));
    	x += 80;
        
        if(!sys.isCorporation(CorporationData.BAYER_MX)) {
        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18), Color.WHITE,
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.corporationid")));
        	x += 80;
        }

        if(sys.getCorporationId() != CorporationData.ALLERGAN) {
        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18), Color.WHITE, 
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.nationality")));
        	x += 80;
        }
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 70, 18), Color.WHITE, 
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.sex")));
    	x += 70;
        if(sys.getCorporationId() != CorporationData.ALLERGAN) {
        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 90, 18), Color.WHITE, 
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.country")));
        	x += 90;
        }
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
        if(sys.isCorporation(new int[]{CorporationData.MONSANTO,CorporationData.BAYER,CorporationData.COVESTRO})) {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("corporation.monsanto.assignedvehicles")));
            x += 80;
        }
        if(sys.isCorporation(CorporationData.ASTRAZENECA)) {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 120, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.authorizatewho")));
            x += 120;
        }
        if(sys.isCorporation(new int[]{CorporationData.ALLERGAN,CorporationData.BAYER_MX,CorporationData.SYNGENTA_BR})) {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 90, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.iddallas")));
            x += 90;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 90, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.superviser")));
            x += 90;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 90, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.manager")));
            x += 90;
            if(sys.isCorporation(CorporationData.ALLERGAN)) {
	            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 90, 18),
	                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.bpsregistry")));
	            x += 90;
	            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 90, 18),
	                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.officeaddress")));
	            x += 90;
            }
        }
        if(sys.isCorporation(new int[] {CorporationData.JJ,CorporationData.MONSANTO})) {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 90, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.superviser")));
            x += 90;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 90, 18),
                   Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.manager")));
            x += 90;
        }
        if(sys.getCorporationId() == CorporationData.DOW) {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 90, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.superviserid")));
            x += 90;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 90, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.supervisername")));
            x += 90;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 90, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.focalpointid")));
            x += 90;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 90, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.focalpointname")));
            x += 90;
        }
        if(sys.isCorporation(CorporationData.JJ)) {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 90, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.bpsregistry")));
            x += 90;
        }
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18), Color.WHITE, 
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.city")));
        x += 80;
    	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18), Color.WHITE, 
            ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("driver.query.licenseexpiry")));
    	x += 80;
        if(!sys.isCorporation(CorporationData.DOW)) {
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18), Color.WHITE, 
	                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("driver.query.licensecategory")));
	        x += 80;
	        if(sys.getCorporationId() != CorporationData.ALLERGAN) {
	        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18), Color.WHITE, 
	                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("driver.query.licenseget")));
	        	x += 80;
	        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18), Color.WHITE, 
	                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.vehicleallowed")));
	        	x += 80;
	        }
        }
        if(sys.isCorporation(CorporationData.MONSANTO)) {
        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 180, 18), Color.WHITE, 
	                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.licenseobs")));
	        	x += 180;
        }
        if (sys.getCorporationId() != CorporationData.CIMSA){
            if (sys.getCorporationId() != CorporationData.ALLERGAN){
            	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18), Color.WHITE, 
            			ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.officeaddress")));
            	x += 80;
            }
        	
        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 100, 18), Color.WHITE, 
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.email")));
        	x += 100;
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
        if(!sys.isCorporation(CorporationData.SYNGENTA)) {
	        if(!sys.isCorporation(new int[] {CorporationData.DOW,CorporationData.ALLERGAN})) {
	        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18), Color.WHITE, 
	                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.drives")));
	        	x += 80;
	        }
	        if(!sys.isCorporation(new int[] {CorporationData.DOW})) {
		        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18), Color.WHITE, 
		                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.employee")));
		        x += 80;
		        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18), Color.WHITE, 
		                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.contact")));
	        }
        }else {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 90, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.manager")));
            x += 90;
        }
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
        
        
        if (sys.getCorporationId() == CorporationData.ALLERGAN ||
        		sys.getCorporationId() == CorporationData.ASTRAZENECA){
        	// CPF y RG
        	   x += 80;
               reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                       Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.licensecoverage")));
               x += 80;
               reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                       Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.federativeentity")));
        }
        
		return reportHeader;
	}

	protected ItemBand createItemBand() {
		ItemBand items = new ItemBand();
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null, new Rectangle2D.Float(10, 0,getReportWidth(), 15), false, true));
        int x = 10;
        if(sys.isCorporation(CorporationData.BAYER_MX)) {
        	items.addElement(TextFieldElementFactory.createStringElement("URL-Field",new Rectangle2D.Float(x, 0, 80, 15), Color.BLACK,
                ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "corporationid"));
            x += 80;
        }
        items.addElement(TextFieldElementFactory.createStringElement("URL-Field", new Rectangle2D.Float(x, 0, 80, 15), Color.BLACK,
                ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "name"));
        x += 80;
        items.addElement(TextFieldElementFactory.createStringElement("URL-Field", new Rectangle2D.Float(x, 0, 80, 15), Color.BLACK,
                ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "lastname"));
        x += 80;
        if(!sys.isCorporation(CorporationData.SYNGENTA_BR)) {
        	items.addElement(TextFieldElementFactory.createStringElement("director",new Rectangle2D.Float(x, 0, 60, 15),Color.BLACK,
        		ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","director"));
        	x += 60;
        }
        items.addElement(TextFieldElementFactory.createStringElement("temporal",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,
        		ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","temporal"));
        x += 80;
        items.addElement(TextFieldElementFactory.createStringElement("birth",new Rectangle2D.Float(x, 0, 80, 15), Color.BLACK,
                ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "birthdate"));
        x += 80;
        
        if(!sys.isCorporation(CorporationData.BAYER_MX)) {
        	items.addElement(TextFieldElementFactory.createStringElement("corporationid",new Rectangle2D.Float(x, 0, 80, 15), Color.BLACK,
                ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "corporationid"));
            x += 80;
        }

        if(sys.getCorporationId() != CorporationData.ALLERGAN) {
        	items.addElement(TextFieldElementFactory.createStringElement("nationality", new Rectangle2D.Float(x, 0, 80, 15),
                Color.BLACK, ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "nationality"));
        	x += 80;
        }
        items.addElement(TextFieldElementFactory.createStringElement("sex",new Rectangle2D.Float(x, 0, 70, 15), Color.BLACK,
                ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "sex"));
        x += 70;
        if(sys.getCorporationId() != CorporationData.ALLERGAN) {
        	items.addElement(TextFieldElementFactory.createStringElement("country", new Rectangle2D.Float(x, 0, 90, 15),
                Color.BLACK, ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "country"));
        	x += 90;
        }
        for(int i = 0; i < 3; i++) {
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
        if(sys.isCorporation(new int[]{CorporationData.ALLERGAN,CorporationData.BAYER_MX,CorporationData.SYNGENTA_BR})) {
        	items.addElement(TextFieldElementFactory.createStringElement("iddallas", new Rectangle2D.Float(x, 0, 90, 15),
                    Color.BLACK, ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "iddallas"));
            x += 90;
        	items.addElement(TextFieldElementFactory.createStringElement("superviser", new Rectangle2D.Float(x, 0, 90, 15),
                    Color.BLACK, ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "superviser"));
            x += 90;
        	items.addElement(TextFieldElementFactory.createStringElement("manager", new Rectangle2D.Float(x, 0, 90, 15),
                    Color.BLACK, ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "manager"));
            x += 90;
            if(sys.isCorporation(CorporationData.ALLERGAN)) {
	        	items.addElement(TextFieldElementFactory.createStringElement("bps", new Rectangle2D.Float(x, 0, 90, 15),
	                    Color.BLACK, ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "bps"));
	            x += 90;
	        	items.addElement(TextFieldElementFactory.createStringElement("officeaddress", new Rectangle2D.Float(x, 0, 90, 15),
	                    Color.BLACK, ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "officeaddress"));
	            x += 90;
            }
        }
        if(sys.isCorporation(new int[]{CorporationData.MONSANTO,CorporationData.BAYER,CorporationData.COVESTRO})) {
            items.addElement(TextFieldElementFactory.createStringElement("assignedVehicle",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","assignedVehicle"));
            x += 80;
        }
        if(sys.isCorporation(new int[] {CorporationData.JJ,CorporationData.MONSANTO})) {
        	items.addElement(TextFieldElementFactory.createStringElement("superviser", new Rectangle2D.Float(x, 0, 90, 15),
                    Color.BLACK, ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "superviser"));
            x += 90;
        	items.addElement(TextFieldElementFactory.createStringElement("manager", new Rectangle2D.Float(x, 0, 90, 15),
                    Color.BLACK, ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "manager"));
            x += 90;
        }
        if(sys.getCorporationId() == CorporationData.DOW) {
        	items.addElement(TextFieldElementFactory.createStringElement("iddallas", new Rectangle2D.Float(x, 0, 90, 15),
                    Color.BLACK, ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "iddallas"));
            x += 90;
        	items.addElement(TextFieldElementFactory.createStringElement("bps", new Rectangle2D.Float(x, 0, 90, 15),
                    Color.BLACK, ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "bps"));
            x += 90;
        	items.addElement(TextFieldElementFactory.createStringElement("superviser", new Rectangle2D.Float(x, 0, 90, 15),
                    Color.BLACK, ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "superviser"));
            x += 90;
        	items.addElement(TextFieldElementFactory.createStringElement("manager", new Rectangle2D.Float(x, 0, 90, 15),
                    Color.BLACK, ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "manager"));
            x += 90;
        }
        if(sys.isCorporation(CorporationData.JJ)) {
        	items.addElement(TextFieldElementFactory.createStringElement("bps", new Rectangle2D.Float(x, 0, 90, 15),
                    Color.BLACK, ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "bps"));
            x += 90;
        }
        if(sys.isCorporation(CorporationData.ASTRAZENECA)) {
            items.addElement(TextFieldElementFactory.createStringElement("authorizatewho",new Rectangle2D.Float(x, 0, 120, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","authorizatewho"));
            x += 120;
        }
        items.addElement(TextFieldElementFactory.createStringElement("city",new Rectangle2D.Float(x, 0, 80, 15), Color.BLACK,
                ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "city"));
        x += 80;
    	items.addElement(TextFieldElementFactory.createStringElement("licenseexpiry", new Rectangle2D.Float(x, 0, 80, 15),
            Color.BLACK, ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "licenseExpiry"));
    	x += 80;
        if(!sys.isCorporation(CorporationData.DOW)) {
	        items.addElement(TextFieldElementFactory.createStringElement("licensecategory", new Rectangle2D.Float(x, 0, 80, 15),
	                Color.BLACK, ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "licenseCategory"));
	        x += 80;
	        if(sys.getCorporationId() != CorporationData.ALLERGAN) {
		        items.addElement(TextFieldElementFactory.createStringElement("licenseget", new Rectangle2D.Float(x, 0, 80, 15),
		                Color.BLACK, ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "licenseGet"));
		        x += 80;
		        items.addElement(TextFieldElementFactory.createStringElement("vehicleallowed", new Rectangle2D.Float(x, 0, 80, 15),
		                Color.BLACK, ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "vehicleallowed"));
		        x += 80;
	        }
        }        
        if(sys.isCorporation(CorporationData.MONSANTO)) {
	        items.addElement(TextFieldElementFactory.createStringElement("licenseobs", new Rectangle2D.Float(x, 0, 180, 15),
	                Color.BLACK, ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "licenseobs"));
        	x += 180;
        }
        if (sys.getCorporationId() != CorporationData.CIMSA){
            if (sys.getCorporationId() != CorporationData.ALLERGAN){
            	items.addElement(TextFieldElementFactory.createStringElement("officeaddress", new Rectangle2D.Float(x, 0, 80, 15),
                Color.BLACK, ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "officeaddress"));
            	x += 80;
            }
        	items.addElement(TextFieldElementFactory.createStringElement("email",new Rectangle2D.Float(x, 0, 100, 15), Color.BLACK,
                ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "email"));
        	x += 100;
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
        if(!sys.isCorporation(CorporationData.SYNGENTA)) {
	        if(!sys.isCorporation(new int[] {CorporationData.DOW,CorporationData.ALLERGAN})) {
	        	items.addElement(TextFieldElementFactory.createStringElement("drives",new Rectangle2D.Float(x, 0, 80, 15), Color.BLACK,
	                ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "drives"));
	        	x += 80;
	        }
	        if(!sys.isCorporation(CorporationData.DOW)) {
		        items.addElement(TextFieldElementFactory.createStringElement("employee", new Rectangle2D.Float(x, 0, 80, 15), Color.BLACK,
		                ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "employee"));
		        x += 80;
		        items.addElement(TextFieldElementFactory.createStringElement("contract", new Rectangle2D.Float(x, 0, 80, 15), Color.BLACK,
		                ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "contract"));
	        }
        }else {
        	items.addElement(TextFieldElementFactory.createStringElement("manager", new Rectangle2D.Float(x, 0, 90, 15),
                    Color.BLACK, ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "manager"));
            x += 90;
        }
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
        
        if (sys.getCorporationId() == CorporationData.ALLERGAN ||
        		sys.getCorporationId() == CorporationData.ASTRAZENECA){
        	// CPF y RG
        	x += 80;
            items.addElement(TextFieldElementFactory.createStringElement("licenseCoverage",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","licenseCoverage"));
            x += 80;
            items.addElement(TextFieldElementFactory.createStringElement("federativeEntity",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","federativeEntity"));
        }
        
		return items;
	}
	    
	

}
