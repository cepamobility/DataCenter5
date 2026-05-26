package presentation.report.driver;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

import javax.swing.table.TableModel;

import org.apache.struts.action.ActionForm;
import org.jfree.report.ElementAlignment;
import org.jfree.report.ItemBand;
import org.jfree.report.ReportFooter;
import org.jfree.report.ReportHeader;
import org.jfree.report.elementfactory.LabelElementFactory;
import org.jfree.report.elementfactory.NumberFieldElementFactory;
import org.jfree.report.elementfactory.StaticShapeElementFactory;
import org.jfree.report.elementfactory.TextFieldElementFactory;
import org.jfree.report.function.CreateHyperLinksFunction;
import org.jfree.report.function.ElementColorFunction;
import org.jfree.report.function.ExpressionCollection;
import org.jfree.report.function.TextFormatExpression;

import presentation.report.ReportFactory;
import presentation.translator.web.driver.DriverMultiReportForm;
import presentation.translator.web.driver.DriverReportForm;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.administration.corporation.tables.CorporationData;
import communication.driver.tables.DriverData;
import communication.language.Text;

public class DriverReportFactory extends ReportFactory {

    protected Text messages;
    protected int pages;
    protected int pageNumber;
    protected SystemAccess sys;
    protected String[] labels;

    protected int last;
    
    
    public DriverReportFactory(String name, TableModel model, ActionForm form, 
    		SystemAccess sys, Text messages, int count, int pages, int pageNumber) {
        super(name, model, form);
        this.messages = messages;
        this.count = count;
        this.pages = pages;
        this.pageNumber = pageNumber;
        this.sys = sys;
        labels = sys.getUserSessionData().getFilter().getDivOrgLabels();
        parseDivisionValues(sys,messages);
        last = 650;
        if(sys.isCorporation(new int[]{CorporationData.DOW,CorporationData.SYNGENTA_BR})) {
        	last -= 150;
        }
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
    }

    protected int getWidth() {
        int width = last+162;
        if(sys.getUserSessionData().isForest() && sys.getUserSessionData().getFilter().getCorporationId().intValue() != CorporationData.KLABIN) {
            width += 640;
        }
        if(sys.getCorporationId() != CorporationData.CIMSA) {
            width += 80;
        }
        return width;
    }

    protected int getReportWidth() {
        int width = last+80;
        if(sys.getUserSessionData().isForest() && sys.getUserSessionData().getFilter().getCorporationId().intValue() != CorporationData.KLABIN) {
            width += 640;
        }
        if(sys.getCorporationId() != CorporationData.CIMSA || sys.getCorporationId() != CorporationData.MONSANTO) {
            width += 80;
        }
        return width;
    }

    protected ItemBand createItemBand() {
        ItemBand items = super.createItemBand();
        int x = 10;
        if(sys.isCorporation(CorporationData.BAYER_MX)) {
	        items.addElement(TextFieldElementFactory.createStringElement("URL-Field",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","corporationId"));
	        x += 100;
        }
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,new Rectangle2D.Float(10, 0, getReportWidth(), 15), false, true));
        items.addElement(TextFieldElementFactory.createStringElement("URL-Field",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","name"));
        x += 80;
        items.addElement(TextFieldElementFactory.createStringElement("URL-Field",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","lastname"));
        x += 80;
        if(!sys.isCorporation(CorporationData.SYNGENTA_BR)) {
        	items.addElement(TextFieldElementFactory.createStringElement("director",new Rectangle2D.Float(x, 0, 60, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","director"));
        	x += 60;
        }
        items.addElement(TextFieldElementFactory.createStringElement("temporal",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","temporal"));
        x += 80;
        items.addElement(TextFieldElementFactory.createStringElement("country",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","country"));
        x += 80;
        
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
        if(!sys.isCorporation(CorporationData.BAYER_MX)) {
	        items.addElement(TextFieldElementFactory.createStringElement("corporationId",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","corporationId"));
	        x += 100;
        }
        if(sys.getCorporationId() == CorporationData.MONSANTO) {
            items.addElement(TextFieldElementFactory.createStringElement("assignedVehicle",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","assignedVehicle"));
            x += 80;
        }
        
        items.addElement(TextFieldElementFactory.createStringElement("birth",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","birthdate"));
        x += 80;
        items.addElement(TextFieldElementFactory.createStringElement("exclusion",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","exclusiondate"));
        if(!sys.isCorporation(new int[]{CorporationData.DOW,CorporationData.SYNGENTA_BR})) {
            x += 80;
        	items.addElement(TextFieldElementFactory.createStringElement("contract",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","contract"));
        }
        if(sys.getUserSessionData().isForest() && sys.getUserSessionData().getFilter().getCorporationId().intValue() != CorporationData.KLABIN) {
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
        return items;
    }
    
    protected ReportHeader createReportHeader() {
        ReportHeader reportHeader = new ReportHeader();
        int height = (sys.getCorporationId() == CorporationData.LOSPIQUES || sys.getCorporationId() == CorporationData.ALLERGAN) ? writeFilter((DriverMultiReportForm)form, reportHeader, last-160) : writeFilter((DriverReportForm)form, reportHeader, last-160);
                
        if(sys.isBrasilCorporation()) {
        	reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,
                    new Rectangle2D.Float(10, height, getReportWidth(), 15), false, true));
        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 160, 15),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,messages.getText("query.driver.driverdata.total")));
            int start = (sys.getUserSessionData().isForest() && sys.getUserSessionData().getFilter().getCorporationId().intValue() != CorporationData.KLABIN) ? last + 640 : last;
            reportHeader.addElement(NumberFieldElementFactory.createNumberElement(null,new Rectangle2D.Float(start, height, 80, 15),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,"", formatNoDecimals,"total"));
            height += 15;
        }
    
        int x = 10;
        if(sys.isCorporation(CorporationData.BAYER_MX)) {        
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 100, 18), Color.WHITE,
	                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.corporationid")));
	        x += 100;
        }
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FirstLevelColor, null,
                new Rectangle2D.Float(10, height, getReportWidth(), 18), false, true));
       
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18), 
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.firstname")));
        x += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18), 
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.lastname")));
        x += 80;
        if(!sys.isCorporation(CorporationData.SYNGENTA_BR)) {
        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 60, 18), 
        		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.director")));
        	x += 60;
        }
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18), 
        		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.temporal")));
        x += 80;

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.country")));
        x += 80;
       
        for(int i = 0; i < 9; i++) {
            if(!Util.empty(labels[i])) {
                if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 3) && 
                        !(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 6)) {
                    reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                        Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(labels[i])));
                    x += 80;
                }
            }
        }
        if(!sys.isCorporation(CorporationData.BAYER_MX)) {        
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 100, 18), Color.WHITE,
	                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.corporationid")));
	        x += 100;
        }
        if(sys.getCorporationId() == CorporationData.MONSANTO) {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("corporation.monsanto.assignedvehicles")));
            x += 80;
        }
        
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.birthdate")));
        x += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.query.exclusiondate")));
        if(!sys.isCorporation(new int[]{CorporationData.DOW,CorporationData.SYNGENTA_BR})) {
            x += 80;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.contact")));
        }
        if(sys.getUserSessionData().isForest() && sys.getUserSessionData().getFilter().getCorporationId().intValue() != CorporationData.KLABIN) {
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
        return reportHeader;
    }

	protected int writeFilter(DriverReportForm reportForm, ReportHeader reportHeader,int start) {
		reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 25), false, true));
        
		reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, getReportWidth(), 25),
        		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("query.driver.driverdata.title")));
        
        int height = 25;
        int maxHeight=height;
        height = writeParametersTitle(messages.getText("generic.driver.personaldata"),reportHeader,height,10,160);
        
        height = writeParameter(messages.getText("generic.driver.driverdata.firstname")+":",reportForm.getName(),reportHeader,height,10,80,90,80);
        
        switch(Integer.parseInt(reportForm.getStatus())) {
            case DriverData.ACTIVE:
                height = writeParameter(messages.getText("generic.event.eventdata.status")+":",messages.getText("generic.messages.active"),reportHeader,height,10,80,90,80);
                break;
            case DriverData.EXCLUDED:
                height = writeParameter(messages.getText("generic.event.eventdata.status")+
                		":",messages.getText("generic.messages.excluded"),reportHeader,height,10,80,90,80);
                break;
            case DriverData.ALL:
            	height = writeParameter(messages.getText("generic.event.eventdata.status")+
            			":",messages.getText("generic.messages.all.pl"),reportHeader,height,10,80,90,80);
            	break;
        }
        
        if (sys.getCorporationId() == CorporationData.MONSANTO ||
        		sys.getCorporationId() == CorporationData.LILLY){
        	
        	int withVehicles = DriverData.ALL;
        	if (reportForm.getWithVehicles() != null){
        		withVehicles = Integer.parseInt(reportForm.getWithVehicles());
        	}
        	
        	// Reporte con detalle de vehiculos:
        	switch(withVehicles) {
	        	case DriverData.WITH_VEHICLES:
	        		height = writeParameter(messages.getText("generic.messages.vehicleassociated")+
	            			":",messages.getText("generic.messages.withvehicles")
	            			,reportHeader,height,10,80,90,80);
	        		break;
	        	case DriverData.WITHOUT_VEHICLES:
	        		height = writeParameter(messages.getText("generic.messages.vehicleassociated")+
	            			":",messages.getText("generic.messages.withoutvehicles")
	            			,reportHeader,height,10,80,90,80);
	        		break;
		        case DriverData.ALL:
		        	height = writeParameter(messages.getText("generic.messages.vehicleassociated")+
	            			":",messages.getText("generic.messages.all.pl")
	            			,reportHeader,height,10,80,90,80);
	        		break;		       
		    }
        }
        
        height = writeParametersTitle(messages.getText("generic.driver.divgeo"),reportHeader,height,10,160); 
        height = writeParameter(divGeoLabels[0]+":",divGeoValues[0],reportHeader,height,10,80,90,80);
        height = writeParameter(divGeoLabels[1]+":",divGeoValues[1],reportHeader,height,10,80,90,80);

        maxHeight=Math.max(maxHeight,height);
        height = 25;
       
        String[] divOrgs = sys.getUserSessionData().getFilter().getLevelLabels();
        for(int i = 0; i < divOrgs.length; i++) {
            if(!Util.empty(divOrgs[i])) {
                height = writeParametersTitle(messages.getText(divOrgs[i]),reportHeader,height,230,160);
                for(int j = 0; j < 3; j++) {
                    if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 1 && j== 0) && 
                            !(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 2 && j== 0)) {
                        if(!Util.empty(divOrgLabels[i][j])) {
                            height = writeParameter(divOrgLabels[i][j]+":",divOrgValues[i][j],reportHeader,height,230,80,310,80);
                        }
                    }
                }
            }
        }

        maxHeight=Math.max(maxHeight,height);
        height = 25;
        
        height = writeParametersTitle(messages.getText("generic.event.driver.extradata"),reportHeader,height,start,160);
        switch(Integer.parseInt(reportForm.getAccident())) {
            case DriverReportForm.WITH:
                height = writeParameter(messages.getText("generic.driver.accident.pl")+":",messages.getText("driver.consult.accident.with"),reportHeader,height,start,80,start+80,80);
                break;
            case DriverReportForm.AT_LEAST:
                height = writeParameter(messages.getText("generic.driver.accident.pl")+":",messages.getText("driver.consult.accident.atleast")+" "+reportForm.getAccidentCount()+" "+messages.getText("generic.driver.accident.pl"),reportHeader,height,start,80,start+80,80);
                break;
            case DriverReportForm.NONE:
                height = writeParameter(messages.getText("generic.driver.accident.pl")+":",messages.getText("driver.consult.accident.none"),reportHeader,height,start,80,start+80,80);
                break;
            case DriverReportForm.ALL:
            	height = writeParameter(messages.getText("generic.driver.accident.pl")+":",messages.getText("driver.consult.accident.all"),reportHeader,height,start,80,start+80,80);
                break;
        
        }
        switch(Integer.parseInt(reportForm.getIncident())) {
            case DriverReportForm.WITH:
                height = writeParameter(messages.getText("generic.driver.incident.pl")+":",messages.getText("driver.consult.incident.with"),reportHeader,height,start,80,start+80,80);
                break;
            case DriverReportForm.AT_LEAST:
                height = writeParameter(messages.getText("generic.driver.incident.pl")+":",messages.getText("driver.consult.incident.atleast")+" "+reportForm.getIncidentCount()+" "+messages.getText("generic.driver.incident.pl"),reportHeader,height,start,80,start+80,80);
                break;
            case DriverReportForm.NONE:
                height = writeParameter(messages.getText("generic.driver.incident.pl")+":",messages.getText("driver.consult.incident.none"),reportHeader,height,start,80,start+80,80);
                break;
            case DriverReportForm.ALL:
            	height = writeParameter(messages.getText("generic.driver.incident.pl")+":",messages.getText("driver.consult.incident.all"),reportHeader,height,start,80,start+80,80);
                break;
        }
        switch(Integer.parseInt(reportForm.getFine())) {
            case DriverReportForm.WITH:
                height = writeParameter(messages.getText("generic.driver.fine.pl")+":", messages.getText("driver.consult.fine.with"),reportHeader,height,start,80,start+80,80);
                break;
            case DriverReportForm.AT_LEAST:
                height = writeParameter(messages.getText("generic.driver.fine.pl")+":",messages.getText("driver.consult.fine.atleast")+" "+reportForm.getFineCount()+" "+messages.getText("generic.driver.fine.pl"),reportHeader,height,start,80,start+80,80);
                break;
            case DriverReportForm.NONE:
                height = writeParameter(messages.getText("generic.driver.fine.pl")+":", messages.getText("driver.consult.fine.none"),reportHeader,height,start,80,start+80,80);
                break;
            case DriverReportForm.ALL:
            	height = writeParameter(messages.getText("generic.driver.fine.pl")+":",messages.getText("driver.consult.fine.all"),reportHeader,height,start,80,start+80,80);
                break;
        }
        maxHeight=Math.max(maxHeight,height);
        
        if(sys.getUserSessionData().isForest() && sys.getUserSessionData().getFilter().getCorporationId().intValue() != CorporationData.KLABIN) {
            height = 25;
            start += 240;
            height = writeParametersTitle(messages.getText("generic.driver.otherdata"),reportHeader,height,start,160);
            if(reportForm.getBps().equals("yes")) {
                height = writeParameter(messages.getText("generic.driver.driverdata.bpsregistry")+":", messages.getText("generic.messages.yes"),reportHeader,height,start,80,start+80,80);
            } else if(reportForm.getBps().equals("no")) {
                height = writeParameter(messages.getText("generic.driver.driverdata.bpsregistry")+":", messages.getText("generic.messages.no"),reportHeader,height,start,80,start+80,80);
            }else {
                height = writeParameter(messages.getText("generic.driver.driverdata.bpsregistry")+":", messages.getText("generic.messages.all.pl"),reportHeader,height,start,80,start+80,80);
            }

            height = writeParametersTitle(messages.getText("generic.driver.aptitudtest"),reportHeader,height,start,160);
            if(Integer.parseInt(reportForm.getAptitudStatus()) == DriverData.EXPIRED) {
                height = writeParameter(messages.getText("driver.aptitudtest.status")+":", messages.getText("driver.aptitudtest.expired"),reportHeader,height,start,80,start+80,80);
            } else if(Integer.parseInt(reportForm.getAptitudStatus()) == DriverData.NOT_EXPIRED) {
                height = writeParameter(messages.getText("driver.aptitudtest.status")+":", messages.getText("driver.aptitudtest.notexpired"),reportHeader,height,start,80,start+80,80);
            }else {
                height = writeParameter(messages.getText("driver.aptitudtest.status")+":", messages.getText("generic.messages.all.pl"),reportHeader,height,start,80,start+80,80);
            }
            if(Integer.parseInt(reportForm.getAptitudResult()) == DriverData.APT) {
                height = writeParameter(messages.getText("driver.aptitudtest.result")+":", messages.getText("driver.aptitudtest.apts"),reportHeader,height,start,80,start+80,80);
            } else if(Integer.parseInt(reportForm.getAptitudStatus()) == DriverData.NOT_APT) {
                height = writeParameter(messages.getText("driver.aptitudtest.result")+":", messages.getText("driver.aptitudtest.notapts"),reportHeader,height,start,80,start+80,80);
            }else {
                height = writeParameter(messages.getText("driver.aptitudtest.result")+":", messages.getText("generic.messages.all.pl"),reportHeader,height,start,80,start+80,80);
            }

            maxHeight=Math.max(maxHeight,height);
            height = 25;
            start += 240;

            height = writeParametersTitle(messages.getText("driver.modify.alcoholcontrol"),reportHeader,height,start,160);
            if(Integer.parseInt(reportForm.getAlcoholStatus()) == DriverData.EXPIRED) {
                height = writeParameter(messages.getText("driver.aptitudtest.status")+":", messages.getText("driver.aptitudtest.expired"),reportHeader,height,start,80,start+80,80);
            } else if(Integer.parseInt(reportForm.getAlcoholStatus()) == DriverData.NOT_EXPIRED) {
                height = writeParameter(messages.getText("driver.aptitudtest.status")+":", messages.getText("driver.aptitudtest.notexpired"),reportHeader,height,start,80,start+80,80);
            }else {
                height = writeParameter(messages.getText("driver.aptitudtest.status")+":", messages.getText("generic.messages.all.pl"),reportHeader,height,start,80,start+80,80);
            }
            if(Integer.parseInt(reportForm.getAlcoholResult()) == DriverData.POSITIVE) {
                height = writeParameter(messages.getText("driver.aptitudtest.result")+":", messages.getText("generic.messages.positive"),reportHeader,height,start,80,start+80,80);
            } else if(Integer.parseInt(reportForm.getAlcoholResult()) == DriverData.NEGATIVE) {
                height = writeParameter(messages.getText("driver.aptitudtest.result")+":", messages.getText("generic.messages.negative"),reportHeader,height,start,80,start+80,80);
            }else {
                height = writeParameter(messages.getText("driver.aptitudtest.result")+":", messages.getText("generic.messages.all.pl"),reportHeader,height,start,80,start+80,80);
            }
            if(Integer.parseInt(reportForm.getPostiveAlcohol()) == DriverData.ANY_POSITIVE) {
                height = writeParameter(messages.getText("driver.alcoholcontro.history")+":", messages.getText("driver.alcoholconotrol.withpositive"),reportHeader,height,start,80,start+80,80);
            } else if(Integer.parseInt(reportForm.getAlcoholResult()) == DriverData.WITHOUT_POSITIVE) {
                height = writeParameter(messages.getText("driver.alcoholcontro.history")+":", messages.getText("driver.alcoholconotrol.withoutpositive"),reportHeader,height,start,80,start+80,80);
            }else {
                height = writeParameter(messages.getText("driver.alcoholcontro.history")+":", messages.getText("generic.messages.all.pl"),reportHeader,height,start,80,start+80,80);
            }
            maxHeight=Math.max(maxHeight,height);
        }
        
		return maxHeight;
	}

	protected int writeFilter(DriverMultiReportForm reportForm, ReportHeader reportHeader,int start) {
		reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 25), false, true));
        
		reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, getReportWidth(), 25),
        		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("query.driver.driverdata.title")));
        
        int height = 25;
        int maxHeight=height;
        height = writeParametersTitle(messages.getText("generic.driver.personaldata"),reportHeader,height,10,160);
        
        height = writeParameter(messages.getText("generic.driver.driverdata.firstname")+":",reportForm.getName(),reportHeader,height,10,80,90,80);
        
        switch(Integer.parseInt(reportForm.getStatus())) {
            case DriverData.ACTIVE:
                height = writeParameter(messages.getText("generic.event.eventdata.status")+":",messages.getText("generic.messages.active"),reportHeader,height,10,80,90,80);
                break;
            case DriverData.EXCLUDED:
                height = writeParameter(messages.getText("generic.event.eventdata.status")+":",messages.getText("generic.messages.excluded"),reportHeader,height,10,80,90,80);
                break;
            case DriverData.ALL:
            	height = writeParameter(messages.getText("generic.event.eventdata.status")+":",messages.getText("generic.messages.all.pl"),reportHeader,height,10,80,90,80);
            	break;
        }
        
        height = writeParametersTitle(messages.getText("generic.driver.divgeo"),reportHeader,height,10,160); 
        height = writeParameter(divGeoLabels[0]+":",divGeoValues[0],reportHeader,height,10,80,90,80);
        height = writeParameter(divGeoLabels[1]+":",divGeoValues[1],reportHeader,height,10,80,90,80);

        maxHeight=Math.max(maxHeight,height);
        height = 25;
       
        String[] divOrgs = sys.getUserSessionData().getFilter().getLevelLabels();
        for(int i = 0; i < divOrgs.length; i++) {
            if(!Util.empty(divOrgs[i])) {
                height = writeParametersTitle(messages.getText(divOrgs[i]),reportHeader,height,250,160);
                for(int j = 0; j < 3; j++) {
                    if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 1 && j== 0) && 
                            !(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 2 && j== 0)) {
                        if(!Util.empty(divOrgLabels[i][j])) {
                            height = writeParameter(divOrgLabels[i][j]+":",divOrgValues[i][j],reportHeader,height,250,80,330,80);
                        }
                    }
                }
            }
        }

        maxHeight=Math.max(maxHeight,height);
        height = 25;
        
        height = writeParametersTitle(messages.getText("generic.event.driver.extradata"),reportHeader,height,start,160);
        switch(Integer.parseInt(reportForm.getAccident())) {
            case DriverMultiReportForm.WITH:
                height = writeParameter(messages.getText("generic.driver.accident.pl")+":",messages.getText("driver.consult.accident.with"),reportHeader,height,start,80,start+80,80);
                break;
            case DriverMultiReportForm.AT_LEAST:
                height = writeParameter(messages.getText("generic.driver.accident.pl")+":",messages.getText("driver.consult.accident.atleast")+" "+reportForm.getAccidentCount()+" "+messages.getText("generic.driver.accident.pl"),reportHeader,height,start,80,start+80,80);
                break;
            case DriverMultiReportForm.NONE:
                height = writeParameter(messages.getText("generic.driver.accident.pl")+":",messages.getText("driver.consult.accident.none"),reportHeader,height,start,80,start+80,80);
                break;
            case DriverMultiReportForm.ALL:
            	height = writeParameter(messages.getText("generic.driver.accident.pl")+":",messages.getText("driver.consult.accident.all"),reportHeader,height,start,80,start+80,80);
                break;
        
        }
        switch(Integer.parseInt(reportForm.getIncident())) {
            case DriverMultiReportForm.WITH:
                height = writeParameter(messages.getText("generic.driver.incident.pl")+":",messages.getText("driver.consult.incident.with"),reportHeader,height,start,80,start+80,80);
                break;
            case DriverMultiReportForm.AT_LEAST:
                height = writeParameter(messages.getText("generic.driver.incident.pl")+":",messages.getText("driver.consult.incident.atleast")+" "+reportForm.getIncidentCount()+" "+messages.getText("generic.driver.incident.pl"),reportHeader,height,start,80,start+80,80);
                break;
            case DriverMultiReportForm.NONE:
                height = writeParameter(messages.getText("generic.driver.incident.pl")+":",messages.getText("driver.consult.incident.none"),reportHeader,height,start,80,start+80,80);
                break;
            case DriverMultiReportForm.ALL:
            	height = writeParameter(messages.getText("generic.driver.incident.pl")+":",messages.getText("driver.consult.incident.all"),reportHeader,height,start,80,start+80,80);
                break;
        }
        switch(Integer.parseInt(reportForm.getFine())) {
            case DriverMultiReportForm.WITH:
                height = writeParameter(messages.getText("generic.driver.fine.pl")+":", messages.getText("driver.consult.fine.with"),reportHeader,height,start,80,start+80,80);
                break;
            case DriverMultiReportForm.AT_LEAST:
                height = writeParameter(messages.getText("generic.driver.fine.pl")+":",messages.getText("driver.consult.fine.atleast")+" "+reportForm.getFineCount()+" "+messages.getText("generic.driver.fine.pl"),reportHeader,height,start,80,start+80,80);
                break;
            case DriverMultiReportForm.NONE:
                height = writeParameter(messages.getText("generic.driver.fine.pl")+":", messages.getText("driver.consult.fine.none"),reportHeader,height,start,80,start+80,80);
                break;
            case DriverMultiReportForm.ALL:
            	height = writeParameter(messages.getText("generic.driver.fine.pl")+":",messages.getText("driver.consult.fine.all"),reportHeader,height,start,80,start+80,80);
                break;
        }
        maxHeight=Math.max(maxHeight,height);
        
        if(sys.getUserSessionData().isForest() && sys.getUserSessionData().getFilter().getCorporationId().intValue() != CorporationData.KLABIN) {
            height = 25;
            start += 240;
            height = writeParametersTitle(messages.getText("generic.driver.otherdata"),reportHeader,height,start,160);
            if(reportForm.getBps().equals("yes")) {
                height = writeParameter(messages.getText("generic.driver.driverdata.bpsregistry")+":", messages.getText("generic.messages.yes"),reportHeader,height,start,80,start+80,80);
            } else if(reportForm.getBps().equals("no")) {
                height = writeParameter(messages.getText("generic.driver.driverdata.bpsregistry")+":", messages.getText("generic.messages.no"),reportHeader,height,start,80,start+80,80);
            }else {
                height = writeParameter(messages.getText("generic.driver.driverdata.bpsregistry")+":", messages.getText("generic.messages.all.pl"),reportHeader,height,start,80,start+80,80);
            }

            height = writeParametersTitle(messages.getText("generic.driver.aptitudtest"),reportHeader,height,start,160);
            if(Integer.parseInt(reportForm.getAptitudStatus()) == DriverData.EXPIRED) {
                height = writeParameter(messages.getText("driver.aptitudtest.status")+":", messages.getText("driver.aptitudtest.expired"),reportHeader,height,start,80,start+80,80);
            } else if(Integer.parseInt(reportForm.getAptitudStatus()) == DriverData.NOT_EXPIRED) {
                height = writeParameter(messages.getText("driver.aptitudtest.status")+":", messages.getText("driver.aptitudtest.notexpired"),reportHeader,height,start,80,start+80,80);
            }else {
                height = writeParameter(messages.getText("driver.aptitudtest.status")+":", messages.getText("generic.messages.all.pl"),reportHeader,height,start,80,start+80,80);
            }
            if(Integer.parseInt(reportForm.getAptitudResult()) == DriverData.APT) {
                height = writeParameter(messages.getText("driver.aptitudtest.result")+":", messages.getText("driver.aptitudtest.apts"),reportHeader,height,start,80,start+80,80);
            } else if(Integer.parseInt(reportForm.getAptitudStatus()) == DriverData.NOT_APT) {
                height = writeParameter(messages.getText("driver.aptitudtest.result")+":", messages.getText("driver.aptitudtest.notapts"),reportHeader,height,start,80,start+80,80);
            }else {
                height = writeParameter(messages.getText("driver.aptitudtest.result")+":", messages.getText("generic.messages.all.pl"),reportHeader,height,start,80,start+80,80);
            }

            maxHeight=Math.max(maxHeight,height);
            height = 25;
            start += 240;

            height = writeParametersTitle(messages.getText("driver.modify.alcoholcontrol"),reportHeader,height,start,160);
            if(Integer.parseInt(reportForm.getAlcoholStatus()) == DriverData.EXPIRED) {
                height = writeParameter(messages.getText("driver.aptitudtest.status")+":", messages.getText("driver.aptitudtest.expired"),reportHeader,height,start,80,start+80,80);
            } else if(Integer.parseInt(reportForm.getAlcoholStatus()) == DriverData.NOT_EXPIRED) {
                height = writeParameter(messages.getText("driver.aptitudtest.status")+":", messages.getText("driver.aptitudtest.notexpired"),reportHeader,height,start,80,start+80,80);
            }else {
                height = writeParameter(messages.getText("driver.aptitudtest.status")+":", messages.getText("generic.messages.all.pl"),reportHeader,height,start,80,start+80,80);
            }
            if(Integer.parseInt(reportForm.getAlcoholResult()) == DriverData.POSITIVE) {
                height = writeParameter(messages.getText("driver.aptitudtest.result")+":", messages.getText("generic.messages.positive"),reportHeader,height,start,80,start+80,80);
            } else if(Integer.parseInt(reportForm.getAlcoholResult()) == DriverData.NEGATIVE) {
                height = writeParameter(messages.getText("driver.aptitudtest.result")+":", messages.getText("generic.messages.negative"),reportHeader,height,start,80,start+80,80);
            }else {
                height = writeParameter(messages.getText("driver.aptitudtest.result")+":", messages.getText("generic.messages.all.pl"),reportHeader,height,start,80,start+80,80);
            }
            if(Integer.parseInt(reportForm.getPostiveAlcohol()) == DriverData.ANY_POSITIVE) {
                height = writeParameter(messages.getText("driver.alcoholcontro.history")+":", messages.getText("driver.alcoholconotrol.withpositive"),reportHeader,height,start,80,start+80,80);
            } else if(Integer.parseInt(reportForm.getAlcoholResult()) == DriverData.WITHOUT_POSITIVE) {
                height = writeParameter(messages.getText("driver.alcoholcontro.history")+":", messages.getText("driver.alcoholconotrol.withoutpositive"),reportHeader,height,start,80,start+80,80);
            }else {
                height = writeParameter(messages.getText("driver.alcoholcontro.history")+":", messages.getText("generic.messages.all.pl"),reportHeader,height,start,80,start+80,80);
            }
            maxHeight=Math.max(maxHeight,height);
        }
        
		return maxHeight;
	}

    protected ReportFooter createReportFooter() {
        ReportFooter reportFooter = new ReportFooter();
        reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, null,
                new Rectangle2D.Float(10, 10, getReportWidth(), 15), false, true));
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 160, 15),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,messages.getText("query.driver.driverdata.total")));
        int start = (sys.getUserSessionData().isForest() && sys.getUserSessionData().getFilter().getCorporationId().intValue() != CorporationData.KLABIN) ? last + 640 : last;
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement(null,new Rectangle2D.Float(start, 10, 80, 15),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,"", formatNoDecimals,"total"));
        
        String output = (sys.getCorporationId() == CorporationData.LOSPIQUES || sys.getCorporationId() == CorporationData.ALLERGAN) ? ((DriverMultiReportForm)form).getOutput() : ((DriverReportForm)form).getOutput();
        if(output.equals("HTML")) {
        	return pagination (reportFooter,40);
        }
        
        return reportFooter;
    }

    protected ReportFooter pagination (ReportFooter reportFooter,int height){
    	int left = 60;
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
    
    
    protected ExpressionCollection createFunctions() {
        ExpressionCollection functions = super.createFunctions();
        
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

        String output = (sys.getCorporationId() == CorporationData.LOSPIQUES || sys.getCorporationId() == CorporationData.ALLERGAN) ? 
        		((DriverMultiReportForm)form).getOutput() : ((DriverReportForm)form).getOutput();
        String linkURL = (sys.getCorporationId() == CorporationData.LOSPIQUES || sys.getCorporationId() == CorporationData.ALLERGAN) ? 
        		((DriverMultiReportForm)form).getLink() : 
        			((DriverReportForm)form).getLink();
        if(output.equals("HTML")) {
            for(int i = 1; i <= pages; i++) {
                if(i != pageNumber) {
                    TextFormatExpression textExp2 = new TextFormatExpression();
                    textExp2.setName("URLCreateExpression"+i);
                    textExp2.setPattern("./layout.jsp?bookmark=true&refer=/driver/driverReportPage.jsp?page="+i+linkURL);
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
}
