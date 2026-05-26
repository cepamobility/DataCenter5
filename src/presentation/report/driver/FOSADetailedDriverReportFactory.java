package presentation.report.driver;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

import javax.swing.table.TableModel;

import org.apache.struts.action.ActionForm;
import org.jfree.report.ElementAlignment;
import org.jfree.report.ItemBand;
import org.jfree.report.ReportHeader;
import org.jfree.report.elementfactory.LabelElementFactory;
import org.jfree.report.elementfactory.StaticShapeElementFactory;
import org.jfree.report.elementfactory.TextFieldElementFactory;

import presentation.translator.web.driver.FOSADriverReportForm;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.administration.corporation.tables.CorporationData;
import communication.language.Text;

public class FOSADetailedDriverReportFactory extends FOSADriverReportFactory {

	private int last;
	
    public FOSADetailedDriverReportFactory(String name, TableModel model,ActionForm form, 
    		SystemAccess sys, Text messages, int pages, int pageNumber) {
        super(name, model, form, sys, messages, 13, pages,pageNumber);
        last = 410;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 1 && j== 0) && 
                        !(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 2 && j== 0)) {
                    if(!Util.empty(labels[i*3+j])) {
                        last += 80;
                    }
                }
            }
        }
        last += 1420;
    }

    public int getWidth() {
        if(sys.getCorporationId() == CorporationData.LOSPIQUES) {
        	last += 380;
        }
        if(sys.getUserSessionData().isForest()) {
            return last + 922;
        }
        return last;
    }
    
    public int getReportWidth() {
        if(sys.getCorporationId() == CorporationData.LOSPIQUES) {
        	last += 380;
        }
        if(sys.getUserSessionData().isForest()) {
            return last + 840;
        }
        return last;
    }

    protected ReportHeader createReportHeader() {
        FOSADriverReportForm reportForm = (FOSADriverReportForm) form;
		ReportHeader reportHeader = new ReportHeader();
		int height = writeFilter(reportForm, reportHeader);
		reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor,null, new Rectangle2D.Float(10, height, getReportWidth(), 18),false, true));
        
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 80, 18), Color.WHITE,
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.firstname")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(90, height, 80, 18), Color.WHITE,
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.lastname")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(170, height, 80, 18), Color.WHITE, 
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.birthdate")));
        
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(250, height, 80, 18), Color.WHITE,
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.corporationid")));
        
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(330, height, 80, 18), Color.WHITE, 
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.nationality")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(410, height, 70, 18), Color.WHITE, 
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.sex")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(480, height, 90, 18), Color.WHITE, 
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.country")));
        int x = 570;
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
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18), Color.WHITE, 
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.officeaddress")));
        x += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 100, 18), Color.WHITE, 
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.email")));
        x += 100;
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
        x += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.bpsregistry")));
        x += 80;
        if(sys.getCorporationId() == CorporationData.WEYERHAEUSER) {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.healthExpiry")));
            x += 80;
        }
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
        x += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.activities.count")));
        x += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
        		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.activities.last")));
        x += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.activities.lastec")));

        if(sys.getCorporationId() == CorporationData.WEYERHAEUSER) {
            x += 80;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 100, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("activity.query.lastradar")));
            x += 100;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 100, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("activity.query.noteradar")));
            x += 100;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 100, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("activity.query.lastinduction")));
        }
		return reportHeader;
	}

	protected ItemBand createItemBand() {
		ItemBand items = new ItemBand();
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null, new Rectangle2D.Float(10, 0,getReportWidth(), 15), false, true));
        items.addElement(TextFieldElementFactory.createStringElement("URL-Field", new Rectangle2D.Float(10, 0, 80, 15), Color.BLACK,
                ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "name"));
        items.addElement(TextFieldElementFactory.createStringElement("URL-Field", new Rectangle2D.Float(90, 0, 80, 15), Color.BLACK,
                ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "lastname"));
        
        
        items.addElement(TextFieldElementFactory.createStringElement("birth",new Rectangle2D.Float(170, 0, 80, 15), Color.BLACK,
                ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "birthdate"));
        
        items.addElement(TextFieldElementFactory.createStringElement("corporationid",new Rectangle2D.Float(250, 0, 70, 15), Color.BLACK,
                ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "corporationid"));
        
        
        items.addElement(TextFieldElementFactory.createStringElement("nationality", new Rectangle2D.Float(330, 0, 80, 15),
                Color.BLACK, ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "nationality"));
        items.addElement(TextFieldElementFactory.createStringElement("sex",new Rectangle2D.Float(410, 0, 70, 15), Color.BLACK,
                ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "sex"));
        items.addElement(TextFieldElementFactory.createStringElement("country", new Rectangle2D.Float(480, 0, 90, 15),
                Color.BLACK, ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "country"));
        int x = 570;
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
        items.addElement(TextFieldElementFactory.createStringElement("officeaddress", new Rectangle2D.Float(x, 0, 80, 15),
                Color.BLACK, ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "officeaddress"));
        x += 80;
        items.addElement(TextFieldElementFactory.createStringElement("email",new Rectangle2D.Float(x, 0, 100, 15), Color.BLACK,
                ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "email"));
        x += 100;
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
        x += 80;
        items.addElement(TextFieldElementFactory.createStringElement("bps",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,
        		ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","bps"));

        if(sys.getCorporationId() == CorporationData.WEYERHAEUSER) {
        	x += 80;
        	items.addElement(TextFieldElementFactory.createStringElement("health",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,
        			ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","health"));
        }
        
        x += 80;
        items.addElement(TextFieldElementFactory.createStringElement("test",new Rectangle2D.Float(x, 0, 80, 15),
        		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","test"));
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
        x += 80;
        items.addElement(TextFieldElementFactory.createStringElement("count",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","count"));
        x += 80;
        items.addElement(TextFieldElementFactory.createStringElement("last",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","last"));
        x += 80;
        items.addElement(TextFieldElementFactory.createStringElement("lastec",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","lastec"));
        if(sys.getCorporationId() == CorporationData.LOSPIQUES) {
            x += 80;
            items.addElement(TextFieldElementFactory.createStringElement("lastRadar",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","lastRadar"));
            x += 100;
            items.addElement(TextFieldElementFactory.createStringElement("noteRadar",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","noteRadar"));
            x += 100;
            items.addElement(TextFieldElementFactory.createStringElement("lastInduction",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","lastInduction"));
        }
		return items;
	}
}