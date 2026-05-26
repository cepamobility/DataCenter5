/**
 * Created on 10-jul-2006
 * CEPA
 * DataCenter 5
 */
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
import presentation.translator.web.driver.DriverReportForm;
import presentation.translator.web.driver.FOSADriverReportForm;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.administration.corporation.tables.CorporationData;
import communication.driver.tables.DriverData;
import communication.language.Text;

public class FOSADriverReportFactory extends ReportFactory {

    protected Text messages;
    protected int pages;
    protected int pageNumber;
    protected SystemAccess sys;
    protected String[] labels;
    
    
    public FOSADriverReportFactory(String name, TableModel model, ActionForm form, SystemAccess sys, Text messages, int count, int pages, int pageNumber) {
        super(name, model, form);
        this.messages = messages;
        this.count = count;
        this.pages = pages;
        this.pageNumber = pageNumber;
        this.sys = sys;
        labels = sys.getUserSessionData().getFilter().getDivOrgLabels();
        parseDivisionValues(sys,messages);
    }

    protected int getWidth() {
        if(sys.getCorporationId() == CorporationData.WEYERHAEUSER) {
            return 1422;
        }
        return 1122;
    }

    protected int getReportWidth() {
        if(sys.getCorporationId() == CorporationData.WEYERHAEUSER) {
            return 1340;
        }
        return 1040;
    }

    protected ItemBand createItemBand() {
        String divOrgName = (sys.isCorporation(new int[] {CorporationData.FORESTALORIENTAL,CorporationData.MONTES_DE_PLATA})) ? "divorg11" : "divorg31";

        ItemBand items = super.createItemBand();
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,new Rectangle2D.Float(10, 0, getReportWidth(), 15), false, true));
        items.addElement(TextFieldElementFactory.createStringElement("URL-Field",new Rectangle2D.Float(10, 0, 80, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","corporationId"));
        items.addElement(TextFieldElementFactory.createStringElement("URL-Field",new Rectangle2D.Float(90, 0, 180, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","name"));
        items.addElement(TextFieldElementFactory.createStringElement(divOrgName, new Rectangle2D.Float(270, 0, 180, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-",divOrgName));
        items.addElement(TextFieldElementFactory.createStringElement("birth",new Rectangle2D.Float(450, 0, 80, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","birthdate"));
        items.addElement(TextFieldElementFactory.createStringElement("test",new Rectangle2D.Float(530, 0, 150, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","test"));
        items.addElement(TextFieldElementFactory.createStringElement("apt",new Rectangle2D.Float(680, 0, 60, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","apt"));
        items.addElement(TextFieldElementFactory.createStringElement("expiry",new Rectangle2D.Float(740, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","expiry"));
        items.addElement(TextFieldElementFactory.createStringElement("lastBTW",new Rectangle2D.Float(840, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","lastBTW"));
        items.addElement(TextFieldElementFactory.createStringElement("expiryBTW",new Rectangle2D.Float(940, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","expiryBTW"));
        
        if(sys.getCorporationId() == CorporationData.WEYERHAEUSER) {
            items.addElement(TextFieldElementFactory.createStringElement("lastRadar",new Rectangle2D.Float(1040, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","lastRadar"));
            items.addElement(TextFieldElementFactory.createStringElement("noteRadar",new Rectangle2D.Float(1140, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","noteRadar"));
            items.addElement(TextFieldElementFactory.createStringElement("lastInduction",new Rectangle2D.Float(1240, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","lastInduction"));
        }
        return items;
    }
    
    protected ReportHeader createReportHeader() {
        FOSADriverReportForm reportForm = (FOSADriverReportForm)form;
        ReportHeader reportHeader = new ReportHeader();
        int height = writeFilter(reportForm, reportHeader);
                
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FirstLevelColor, null,
                new Rectangle2D.Float(10, height, getReportWidth(), 18), false, true));
       
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 80, 18), 
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.identitydocument")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(90, height, 180, 18), 
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.lastname")+" "+messages.getText("generic.driver.driverdata.firstname")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(270, height, 180, 18),
        		Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.messages.contractor")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(450, height, 80, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.birthdate")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(530, height, 150, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.aptitudtest.query.lasttest")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(680, height, 60, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.aptitudtest.query.isapt")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(740, height, 100, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.aptitudtest.expiry")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(840, height, 100, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.activities.last")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(940, height, 100, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("activity.query.activityexpirybtw")));
        
        if(sys.getCorporationId() == CorporationData.WEYERHAEUSER) {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(1040, height, 100, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("activity.query.lastradar")));
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(1140, height, 100, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("activity.query.noteradar")));
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(1240, height, 100, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("activity.query.lastinduction")));
        }
        return reportHeader;
    }

	protected int writeFilter(FOSADriverReportForm reportForm, ReportHeader reportHeader) {
		reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 25), false, true));
        
		reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, getReportWidth(), 25),
        		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("query.driver.driverdata.title")));
        
        int height = 25;
        int maxHeight=height;
        height = writeParametersTitle(messages.getText("generic.driver.personaldata"),reportHeader,height,10,240);
        
        height = writeParameter(messages.getText("generic.driver.driverdata.firstname")+":",reportForm.getName(),reportHeader,height,10,80,90,180);
        
        switch(Integer.parseInt(reportForm.getStatus())) {
            case DriverData.ACTIVE:
                height = writeParameter(messages.getText("generic.event.eventdata.status")+":",messages.getText("generic.messages.active"),reportHeader,height,10,80,90,180);
                break;
            case DriverData.EXCLUDED:
                height = writeParameter(messages.getText("generic.event.eventdata.status")+":",messages.getText("generic.messages.excluded"),reportHeader,height,10,80,90,180);
                break;
            case DriverData.ALL:
            	height = writeParameter(messages.getText("generic.event.eventdata.status")+":",messages.getText("generic.messages.all.pl"),reportHeader,height,10,80,90,180);
            	break;
        }
        
        height = writeParametersTitle(messages.getText("generic.driver.divgeo"),reportHeader,height,10,240); 
        height = writeParameter(divGeoLabels[0]+":",divGeoValues[0],reportHeader,height,10,80,90,160);
        height = writeParameter(divGeoLabels[1]+":",divGeoValues[1],reportHeader,height,10,80,90,160);

        maxHeight=Math.max(maxHeight,height);
        height = 25;
       
        String[] divOrgs = sys.getUserSessionData().getFilter().getLevelLabels();
        for(int i = 0; i < divOrgs.length; i++) {
            if(!Util.empty(divOrgs[i])) {
                height = writeParametersTitle(messages.getText(divOrgs[i]),reportHeader,height,270,180);
                for(int j = 0; j < 3; j++) {
                    if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 1 && j== 0) && 
                            !(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 2 && j== 0)) {
                        if(!Util.empty(divOrgLabels[i][j])) {
                            height = writeParameter(divOrgLabels[i][j]+":",divOrgValues[i][j],reportHeader,height,270,80,350,100);
                        }
                    }
                }
            }
        }

        maxHeight=Math.max(maxHeight,height);
        height = 25;
        
        height = writeParametersTitle(messages.getText("generic.event.driver.extradata"),reportHeader,height,270,180);
        switch(Integer.parseInt(reportForm.getAccident())) {
            case DriverReportForm.WITH:
                height = writeParameter(messages.getText("generic.driver.accident.pl")+":",messages.getText("driver.consult.accident.with"),reportHeader,height,270,80,350,100);
                break;
            case DriverReportForm.AT_LEAST:
                height = writeParameter(messages.getText("generic.driver.accident.pl")+":",messages.getText("driver.consult.accident.atleast")+" "+reportForm.getAccidentCount()+" "+messages.getText("generic.driver.accident.pl"),reportHeader,height,270,80,350,100);
                break;
            case DriverReportForm.NONE:
                height = writeParameter(messages.getText("generic.driver.accident.pl")+":",messages.getText("driver.consult.accident.none"),reportHeader,height,270,80,350,100);
                break;
            case DriverReportForm.ALL:
            	height = writeParameter(messages.getText("generic.driver.accident.pl")+":",messages.getText("driver.consult.accident.all"),reportHeader,height,270,80,350,100);
                break;
        
        }
        switch(Integer.parseInt(reportForm.getIncident())) {
            case DriverReportForm.WITH:
                height = writeParameter(messages.getText("generic.driver.incident.pl")+":",messages.getText("driver.consult.incident.with"),reportHeader,height,270,80,350,100);
                break;
            case DriverReportForm.AT_LEAST:
                height = writeParameter(messages.getText("generic.driver.incident.pl")+":",messages.getText("driver.consult.incident.atleast")+" "+reportForm.getIncidentCount()+" "+messages.getText("generic.driver.incident.pl"),reportHeader,height,270,80,350,100);
                break;
            case DriverReportForm.NONE:
                height = writeParameter(messages.getText("generic.driver.incident.pl")+":",messages.getText("driver.consult.incident.none"),reportHeader,height,270,80,350,100);
                break;
            case DriverReportForm.ALL:
            	height = writeParameter(messages.getText("generic.driver.incident.pl")+":",messages.getText("driver.consult.incident.all"),reportHeader,height,270,80,350,100);
                break;
        }
        switch(Integer.parseInt(reportForm.getFine())) {
            case DriverReportForm.WITH:
                height = writeParameter(messages.getText("generic.driver.fine.pl")+":", messages.getText("driver.consult.fine.with"),reportHeader,height,270,80,350,100);
                break;
            case DriverReportForm.AT_LEAST:
                height = writeParameter(messages.getText("generic.driver.fine.pl")+":",messages.getText("driver.consult.fine.atleast")+" "+reportForm.getFineCount()+" "+messages.getText("generic.driver.fine.pl"),reportHeader,height,270,80,350,100);
                break;
            case DriverReportForm.NONE:
                height = writeParameter(messages.getText("generic.driver.fine.pl")+":", messages.getText("driver.consult.fine.none"),reportHeader,height,270,80,350,100);
                break;
            case DriverReportForm.ALL:
            	height = writeParameter(messages.getText("generic.driver.fine.pl")+":",messages.getText("driver.consult.fine.all"),reportHeader,height,270,80,350,100);
                break;
        }
        maxHeight=Math.max(maxHeight,height);
        
        height = 25;
        height = writeParametersTitle(messages.getText("generic.driver.otherdata"),reportHeader,height,530,140);
        if(sys.getCorporationId() != 4) {
	        if(reportForm.getBps().equals("yes")) {
	            height = writeParameter(messages.getText("generic.driver.driverdata.bpsregistry")+":", messages.getText("generic.messages.yes"),reportHeader,height,530,80,610,60);
	        } else if(reportForm.getBps().equals("no")) {
	            height = writeParameter(messages.getText("generic.driver.driverdata.bpsregistry")+":", messages.getText("generic.messages.no"),reportHeader,height,530,80,610,60);
	        }else {
	            height = writeParameter(messages.getText("generic.driver.driverdata.bpsregistry")+":", messages.getText("generic.messages.all.pl"),reportHeader,height,530,80,610,60);
	        }
	
	        height = writeParametersTitle(messages.getText("generic.driver.aptitudtest"),reportHeader,height,530,150);
	        if(Integer.parseInt(reportForm.getAptitudStatus()) == DriverData.EXPIRED) {
	            height = writeParameter(messages.getText("driver.aptitudtest.status")+":", messages.getText("driver.aptitudtest.expired"),reportHeader,height,530,80,610,70);
	        } else if(Integer.parseInt(reportForm.getAptitudStatus()) == DriverData.NOT_EXPIRED) {
	            height = writeParameter(messages.getText("driver.aptitudtest.status")+":", messages.getText("driver.aptitudtest.notexpired"),reportHeader,height,530,80,610,70);
	        }else {
	            height = writeParameter(messages.getText("driver.aptitudtest.status")+":", messages.getText("generic.messages.all.pl"),reportHeader,height,530,80,610,70);;
	        }
	        if(Integer.parseInt(reportForm.getAptitudResult()) == DriverData.APT) {
	            height = writeParameter(messages.getText("driver.aptitudtest.result")+":", messages.getText("driver.aptitudtest.apts"),reportHeader,height,530,80,610,70);
	        } else if(Integer.parseInt(reportForm.getAptitudStatus()) == DriverData.NOT_APT) {
	            height = writeParameter(messages.getText("driver.aptitudtest.result")+":", messages.getText("driver.aptitudtest.notapts"),reportHeader,height,530,80,610,70);
	        }else {
	            height = writeParameter(messages.getText("driver.aptitudtest.result")+":", messages.getText("generic.messages.all.pl"),reportHeader,height,530,80,610,70);
	        }
	
	        maxHeight=Math.max(maxHeight,height);
	        height = 25;
	
	        height = writeParametersTitle(messages.getText("driver.modify.alcoholcontrol"),reportHeader,height,840,200);
	        if(Integer.parseInt(reportForm.getAlcoholStatus()) == DriverData.EXPIRED) {
	            height = writeParameter(messages.getText("driver.aptitudtest.status")+":", messages.getText("driver.aptitudtest.expired"),reportHeader,height,840,100,940,100);
	        } else if(Integer.parseInt(reportForm.getAlcoholStatus()) == DriverData.NOT_EXPIRED) {
	            height = writeParameter(messages.getText("driver.aptitudtest.status")+":", messages.getText("driver.aptitudtest.notexpired"),reportHeader,height,840,100,940,100);
	        }else {
	            height = writeParameter(messages.getText("driver.aptitudtest.status")+":", messages.getText("generic.messages.all.pl"),reportHeader,height,840,100,940,100);
	        }
	        if(Integer.parseInt(reportForm.getAlcoholResult()) == DriverData.POSITIVE) {
	            height = writeParameter(messages.getText("driver.aptitudtest.result")+":", messages.getText("generic.messages.positive"),reportHeader,height,840,100,940,100);
	        } else if(Integer.parseInt(reportForm.getAlcoholResult()) == DriverData.NEGATIVE) {
	            height = writeParameter(messages.getText("driver.aptitudtest.result")+":", messages.getText("generic.messages.negative"),reportHeader,height,840,100,940,100);
	        }else {
	            height = writeParameter(messages.getText("driver.aptitudtest.result")+":", messages.getText("generic.messages.all.pl"),reportHeader,height,840,100,940,100);
	        }
	        if(Integer.parseInt(reportForm.getPostiveAlcohol()) == DriverData.ANY_POSITIVE) {
	            height = writeParameter(messages.getText("driver.alcoholcontro.history")+":", messages.getText("driver.alcoholconotrol.withpositive"),reportHeader,height,840,100,940,100);
	        } else if(Integer.parseInt(reportForm.getAlcoholResult()) == DriverData.WITHOUT_POSITIVE) {
	            height = writeParameter(messages.getText("driver.alcoholcontro.history")+":", messages.getText("driver.alcoholconotrol.withoutpositive"),reportHeader,height,840,100,940,100);
	        }else {
	            height = writeParameter(messages.getText("driver.alcoholcontro.history")+":", messages.getText("generic.messages.all.pl"),reportHeader,height,840,100,940,100);
	        }
        }
        maxHeight=Math.max(maxHeight,height);
        
		return maxHeight;
	}

    protected ReportFooter createReportFooter() {
        ReportFooter reportFooter = new ReportFooter();
        reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, null,
                new Rectangle2D.Float(10, 10, getReportWidth(), 15), false, true));
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 160, 15),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,messages.getText("query.driver.driverdata.total")));
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement(null,new Rectangle2D.Float(getReportWidth()-80, 10, 80, 15),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,"", formatNoDecimals,"total"));
        
        if(((FOSADriverReportForm)form).getOutput().equals("HTML")) {
        	return pagination (reportFooter,40);
        }else return reportFooter;
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

        if(((FOSADriverReportForm)form).getOutput().equals("HTML")) {
            for(int i = 1; i <= pages; i++) {
                if(i != pageNumber) {
                    TextFormatExpression textExp2 = new TextFormatExpression();
                    textExp2.setName("URLCreateExpression"+i);
                    textExp2.setPattern("./layout.jsp?bookmark=true&refer=/driver/driverReportPage.jsp?page="+i+((FOSADriverReportForm)form).getLink());
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
