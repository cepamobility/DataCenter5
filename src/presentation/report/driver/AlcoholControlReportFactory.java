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
import org.jfree.report.elementfactory.DateFieldElementFactory;
import org.jfree.report.elementfactory.LabelElementFactory;
import org.jfree.report.elementfactory.NumberFieldElementFactory;
import org.jfree.report.elementfactory.StaticShapeElementFactory;
import org.jfree.report.elementfactory.TextFieldElementFactory;
import org.jfree.report.function.CreateHyperLinksFunction;
import org.jfree.report.function.ElementColorFunction;
import org.jfree.report.function.ExpressionCollection;
import org.jfree.report.function.TextFormatExpression;

import presentation.report.ReportFactory;
import presentation.translator.web.driver.AlcoholControlReportForm;
import presentation.translator.web.driver.AptitudTestReportForm;
import presentation.translator.web.driver.DriverReportForm;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.driver.tables.DriverData;
import communication.language.Text;

public class AlcoholControlReportFactory extends ReportFactory {

    protected Text messages;
    protected int pages;
    protected int pageNumber;
    protected SystemAccess sys;
    protected String[] labels;

    protected int last;
    
    
    public AlcoholControlReportFactory(String name, TableModel model, ActionForm form, 
    		SystemAccess sys, Text messages, int count, int pages, int pageNumber) {
        super(name, model, form);
        this.messages = messages;
        this.count = count;
        this.pages = pages;
        this.pageNumber = pageNumber;
        this.sys = sys;
        labels = sys.getUserSessionData().getFilter().getDivOrgLabels();
        parseDivisionValues(sys,messages);
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
    }

    protected int getWidth() {
        return last+412;
    }

    protected int getReportWidth() {
        return last+330;
    }

    protected ItemBand createItemBand() {
        ItemBand items = super.createItemBand();
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,new Rectangle2D.Float(10, 0, getReportWidth(), 15), false, true));
        items.addElement(TextFieldElementFactory.createStringElement("URL-Field",new Rectangle2D.Float(10, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","name"));
        items.addElement(TextFieldElementFactory.createStringElement("URL-Field",new Rectangle2D.Float(90, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","lastname"));
        items.addElement(TextFieldElementFactory.createStringElement("country",new Rectangle2D.Float(170, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","country"));
        
        int x = 250;
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
        items.addElement(DateFieldElementFactory.createDateElement("birth",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-",DATE_FORMAT,"birthdate"));
        x += 80;
        items.addElement(DateFieldElementFactory.createDateElement("exclusion",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---",DATE_FORMAT,"exclusiondate"));
        x += 80;
        items.addElement(TextFieldElementFactory.createStringElement("contract",new Rectangle2D.Float(x, 0, 90, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","contract"));
        x += 90;
        items.addElement(DateFieldElementFactory.createDateElement("lastControl",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---",DATE_FORMAT,"lastControl"));
        x += 80;
        items.addElement(TextFieldElementFactory.createStringElement("result",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","result"));
        x += 80;
        items.addElement(TextFieldElementFactory.createStringElement("status",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","status"));
        return items;
    }
    
    protected ReportHeader createReportHeader() {
        AlcoholControlReportForm reportForm = (AlcoholControlReportForm)form;
        ReportHeader reportHeader = new ReportHeader();
        int height = writeFilter(reportForm, reportHeader, last-160);
                
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FirstLevelColor, null,
                new Rectangle2D.Float(10, height, getReportWidth(), 18), false, true));
       
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 80, 18), 
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.firstname")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(90, height, 80, 18), 
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.lastname")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(170, height, 80, 18),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.country")));
       
        int x = 250;
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
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.birthdate")));
        x += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.exclusiondate")));
        x += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 90, 18),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.contact")));
        x += 90;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.alcoholcontrol.query.lasttest")));
        x += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.alcoholcontrol.query.result")));
        x += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.alcoholcontrol.expiry")));
        return reportHeader;
    }

	protected int writeFilter(AlcoholControlReportForm reportForm, ReportHeader reportHeader,int start) {
		reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 25), false, true));
        
		reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, getReportWidth(), 25),
        		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("driver.query.alcoholcontrol.query")));
        
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
        
        switch(Integer.parseInt(reportForm.getType())) {
            case DriverData.EXPIRED:
                height = writeParameter(messages.getText("driver.alcoholcontrol.expired")+":",messages.getText("generic.messages.yes"),reportHeader,height,10,80,90,80);
                break;
            case DriverData.NOT_EXPIRED:
                height = writeParameter(messages.getText("driver.alcoholcontrol.expired")+":",messages.getText("generic.messages.no"),reportHeader,height,10,80,90,80);
                break;
            case DriverData.ALL:
                height = writeParameter(messages.getText("driver.alcoholcontrol.expired")+":",messages.getText("generic.messages.all.pl"),reportHeader,height,10,80,90,80);
                break;
        }

        switch(Integer.parseInt(reportForm.getResult())) {
            case DriverData.POSITIVE:
                height = writeParameter(messages.getText("driver.alcoholcontrol.result")+":",messages.getText("generic.messages.positive"),reportHeader,height,10,80,90,80);
                break;
            case DriverData.NEGATIVE:
                height = writeParameter(messages.getText("driver.alcoholcontrol.result")+":",messages.getText("generic.messages.negative"),reportHeader,height,10,80,90,80);
                break;
            case DriverData.ALL:
                height = writeParameter(messages.getText("driver.alcoholcontrol.result")+":",messages.getText("generic.messages.all.pl"),reportHeader,height,10,80,90,80);
                break;
        }
        
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

        height = writeParametersTitle(messages.getText("generic.driver.divgeo"),reportHeader,height,start,160); 
        height = writeParameter(divGeoLabels[0]+":",divGeoValues[0],reportHeader,height,start,80,start+80,80);
        height = writeParameter(divGeoLabels[1]+":",divGeoValues[1],reportHeader,height,start,80,start+80,80);
        
        maxHeight=Math.max(maxHeight,height);
		return maxHeight;
	}

    protected ReportFooter createReportFooter() {
        ReportFooter reportFooter = new ReportFooter();
        reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, null,
                new Rectangle2D.Float(10, 10, getReportWidth(), 15), false, true));
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 160, 15),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,messages.getText("query.driver.driverdata.total")));
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement(null,new Rectangle2D.Float(last+250, 10, 90, 15),
                Color.WHITE,ElementAlignment.RIGHT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,"", formatNoDecimals,"total"));
        
        if(((DriverReportForm)form).getOutput().equals("HTML")) {
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

        if(((DriverReportForm)form).getOutput().equals("HTML")) {
            for(int i = 1; i <= pages; i++) {
                if(i != pageNumber) {
                    TextFormatExpression textExp2 = new TextFormatExpression();
                    textExp2.setName("URLCreateExpression"+i);
                    textExp2.setPattern("./layout.jsp?refer=/driver/driverReportPage.jsp?page="+i+((DriverReportForm)form).getLink());
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
