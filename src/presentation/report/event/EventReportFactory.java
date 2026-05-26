/**
 * Created on 10-jul-2006
 * CEPA
 * DataCenter 5
 */
package presentation.report.event;
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
import org.jfree.report.function.ItemCountFunction;
import org.jfree.report.function.TextFormatExpression;
import org.jfree.report.modules.misc.beanshell.BSHExpression;

import presentation.report.ReportFactory;
import presentation.translator.web.event.EventReportForm;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.administration.corporation.tables.CorporationData;
import communication.driver.tables.DriverData;
import communication.language.Text;

public class EventReportFactory extends ReportFactory {

    protected Text messages;
    private int pages;
    private int pageNumber;
    private SystemAccess sys;
    private int[][] allDivOrgs;
    private int last;

    public EventReportFactory(String name, TableModel model, ActionForm form, SystemAccess sys, Text messages,int pages,int pageNumber) {
        super(name, model, form);
        this.messages = messages;
        //count = 3;
        this.pages = pages;
        this.pageNumber = pageNumber;
        parseDivisionValues(sys,messages);
        this.sys = sys;
        allDivOrgs = sys.getUserSessionData().getFilter().getAllDivOrgs();
    }

    public EventReportFactory(String name, TableModel model, ActionForm form, SystemAccess sys, Text messages, int count,int pages,int pageNumber) {
        super(name, model, form);
        this.messages = messages;
        this.count = count;
        this.pages = pages;
        this.pageNumber = pageNumber;
        parseDivisionValues(sys,messages);
        this.sys = sys;
        allDivOrgs = sys.getUserSessionData().getFilter().getAllDivOrgs();
    }

    protected int getWidth() {
       	int w = 2820;
        switch(sys.getCorporationId()) {
        	case CorporationData.MONSANTO:
        		w = 2070;
        		break;
        	case CorporationData.DOW:
        		w = 1920;
        		break;
        	case CorporationData.FORESTALORIENTAL:
        	case CorporationData.MONTES_DE_PLATA:
        	case CorporationData.WEYERHAEUSER:
        		w += 1720;
        		break;
        }
        if(sys.isBrasilCorporation() || sys.isCorporation(CorporationData.JJ)) {
             w+= 240;
        }
        return w;
    }
    
    protected ItemBand createItemBand() {
        ItemBand items = super.createItemBand();
        int h = 25;
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,new Rectangle2D.Float(10, 0, getWidth(), h), false, true));
        items.addElement(DateFieldElementFactory.createDateElement("URL-Field",new Rectangle2D.Float(10, 0, 50, h),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-",DATE_FORMAT,"eventdate"));
        items.addElement(TextFieldElementFactory.createStringElement("URL-Field-2",new Rectangle2D.Float(60, 0, 50, h),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","driverFirstName"));
        items.addElement(TextFieldElementFactory.createStringElement("URL-Field-2",new Rectangle2D.Float(110, 0, 50, h),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","driverLastName"));
        items.addElement(TextFieldElementFactory.createStringElement("country",new Rectangle2D.Float(160, 0, 80, h),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","country"));
        int w = 240;
        int length = allDivOrgs.length;
        if(sys.isCorporation(CorporationData.MONSANTO)) {
            length = 4;
        }
        for(int i = 1; i <= length; i++) {
            items.addElement(TextFieldElementFactory.createStringElement("divOrg"+i,new Rectangle2D.Float(w, 0, 80, h),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","divOrg"+i));
            w += 80;
        }
        items.addElement(TextFieldElementFactory.createStringElement("type",new Rectangle2D.Float(w, 0, 50, h),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","type"));
        w += 50;
        if(sys.isCorporation(CorporationData.DOW)) {
            items.addElement(TextFieldElementFactory.createStringElement("extraCost1",new Rectangle2D.Float(w, 0, 50, h),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","extraCost1"));
            w += 50;
            items.addElement(TextFieldElementFactory.createStringElement("extraCost2",new Rectangle2D.Float(w, 0, 50, h),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","extraCost2"));
            w += 50;
            items.addElement(TextFieldElementFactory.createStringElement("extraCost3",new Rectangle2D.Float(w, 0, 50, h),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","extraCost3"));
            w += 50;
        }
        items.addElement(TextFieldElementFactory.createStringElement("clasification",new Rectangle2D.Float(w, 0, 110, h),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","clasification"));
        w += 110;
        items.addElement(TextFieldElementFactory.createStringElement("clasification1",new Rectangle2D.Float(w, 0, 110, h),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","clasification1"));
        w += 110;
        if(!sys.isCorporation(CorporationData.SYNGENTA_BR)) {
        	items.addElement(TextFieldElementFactory.createStringElement("clasification2",new Rectangle2D.Float(w, 0, 110, h),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","clasification2"));
        	w += 110;
    	}
        items.addElement(TextFieldElementFactory.createStringElement("clasification3",new Rectangle2D.Float(w, 0, 110, h),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","clasification3"));
        w += 110;
        items.addElement(TextFieldElementFactory.createStringElement("clasification4",new Rectangle2D.Float(w, 0, 110, h),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","clasification4"));
        w += 110;
        items.addElement(TextFieldElementFactory.createStringElement("clasificationText",new Rectangle2D.Float(w, 0, 110, h),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","clasificationText"));
        w += 110;
        items.addElement(TextFieldElementFactory.createStringElement("clasificationcepa1",new Rectangle2D.Float(w, 0, 110, h),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","clasificationcepa1"));
        w += 110;
        items.addElement(TextFieldElementFactory.createStringElement("clasificationcepa2",new Rectangle2D.Float(w, 0, 110, h),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","clasificationcepa2"));
        w += 110;
        items.addElement(TextFieldElementFactory.createStringElement("clasificationcepa3",new Rectangle2D.Float(w, 0, 110, h),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","clasificationcepa3"));
        w += 110;
        items.addElement(TextFieldElementFactory.createStringElement("clasificationcepa4",new Rectangle2D.Float(w, 0, 110, h),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","clasificationcepa4"));
        w += 110;
        items.addElement(TextFieldElementFactory.createStringElement("clasificationcepaText",new Rectangle2D.Float(w, 0, 110, h),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","clasificationcepaText"));
        w += 110;
        items.addElement(TextFieldElementFactory.createStringElement("story",new Rectangle2D.Float(w, 0,550, h),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","story"));
        w += 555;
        items.addElement(TextFieldElementFactory.createStringElement("recomendation",new Rectangle2D.Float(w, 0,350, h),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","recomendation"));
        w += 350;
        if(sys.isCorporation(CorporationData.MONSANTO)) {
            items.addElement(TextFieldElementFactory.createStringElement("supervisionobs",new Rectangle2D.Float(w, 0,350, h),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","supervisionobs"));
            w += 350;
        }
        if(sys.isCorporation(CorporationData.FORESTALORIENTAL)
                || sys.isCorporation(CorporationData.MONTES_DE_PLATA)
       		 || sys.isCorporation(CorporationData.WEYERHAEUSER)) {
            items.addElement(TextFieldElementFactory.createStringElement("evitability",new Rectangle2D.Float(w, 0,80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","evitability"));
            w += 80;
        }
        if(sys.isBrasilCorporation() || sys.isCorporation(CorporationData.JJ)) {
            items.addElement(TextFieldElementFactory.createStringElement("supervisionClasification",new Rectangle2D.Float(w, 0,80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","supervisionClasification"));
            w += 120;
            items.addElement(TextFieldElementFactory.createStringElement("supervisionCollision",new Rectangle2D.Float(w, 0,80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","supervisionCollision"));        	
            w += 120;
        }
        return items;
    }
    
    protected ReportHeader createReportHeader() {
        EventReportForm reportForm = (EventReportForm)form;
        ReportHeader reportHeader = new ReportHeader();
        int height = writeFilter(reportForm, reportHeader);
                
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FirstLevelColor, null,
                new Rectangle2D.Float(10, height, getWidth(), 18), false, true));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 50, 18),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.observation.date")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(60, height, 50, 18),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.firstname")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(110, height, 50, 18),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.lastname")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(160, height, 80, 18),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,divGeoLabels[1]));
        int w = 240;
        int length = allDivOrgs.length;
        if(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1) {
            length = 4;
        }
        for(int i = 1; i <= length; i++) {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,divOrgLabels[allDivOrgs[i-1][0]-1][allDivOrgs[i-1][1]-1]));
            w += 80;
        }
        
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 50, 18),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.type")));
        w += 50;
        if(sys.isCorporation(CorporationData.DOW)) {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 50, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.type")));
            w += 50;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 50, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.gravity")));
            w += 50;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 50, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.quarter")));
            w += 50;
        }
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 110, 18),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.clasification")));
        w += 110;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 110, 18),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("general.eventdata.newclasification1")));
        w += 110;
        if(!sys.isCorporation(CorporationData.SYNGENTA_BR)) {
        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 110, 18),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("general.eventdata.newclasification2")));
        	w += 110;
        }
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 110, 18),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("general.eventdata.newclasification3")));
        w += 110;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 110, 18),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("general.eventdata.newclasification4")));
        w += 110;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 110, 18),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.clasification")+" Desc."));
        w += 110;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 110, 18),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("general.eventdata.newclasification1")+"-CEPA"));
        w += 110;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 110, 18),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("general.eventdata.newclasification1")+"-CEPA"));
        w += 110;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 110, 18),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("general.eventdata.newclasification1")+"-CEPA"));
        w += 110;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 110, 18),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("general.eventdata.newclasification1")+"-CEPA"));
        w += 110;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 110, 18),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.clasification")+" Desc."+"-CEPA"));
        w += 110;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 550, 18),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.story")));
        w += 550;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 350, 18),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.recomendation")));
        w += 350;
        if(sys.getUserSessionData().getFilter().getCorporationId().intValue() == CorporationData.MONSANTO) {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 350, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.finedata.observations")));
            w += 350;
        }
        if(sys.isCorporation(CorporationData.FORESTALORIENTAL)
                || sys.isCorporation(CorporationData.MONTES_DE_PLATA)
       		 || sys.isCorporation(CorporationData.WEYERHAEUSER)) {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.supervise.evitability")));
            w += 80;
        }
        if(sys.isBrasilCorporation() || sys.isCorporation(CorporationData.JJ)) {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 120, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("event.messages.cepaclasification")));
            w += 120;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 120, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.colisionclasification")));
            w += 120;        	
        }
        last = w;
        
        return reportHeader;
    }

	protected int writeFilter(EventReportForm reportForm, ReportHeader reportHeader) {
		reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,
                new Rectangle2D.Float(10, 0, getWidth(), 25), false, true));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, getWidth(), 25),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("generic.events.consulting")));

        int height = 25;
        int maxHeight = height;
            
        height = writeParametersTitle(messages.getText("query.eventbyperiod.period"),reportHeader,height,10,150);
        height = writeParameter(messages.getText("query.eventbyperiod.from")+":",reportForm.getFrom(messages),reportHeader,height,10,50,60,100);
        height = writeParameter(messages.getText("query.eventbyperiod.to")+":",reportForm.getTo(messages),reportHeader,height,10,50,60,100);

        String driverStatus = "generic.messages.all.pl";
        switch(Integer.parseInt(reportForm.getDriverStatus())) {
            case DriverData.ACTIVE:
                driverStatus = "generic.messages.active";
                break;
            case DriverData.EXCLUDED:
                driverStatus = "generic.messages.excluded";
                break;
        }
 
        height = writeParametersTitle(messages.getText("generic.report.otherdata"),reportHeader,height,10,150);
        height = writeParameter(messages.getText("generic.data.driver.pl")+":",messages.getText(driverStatus),reportHeader,height,10,50,60,100);

        height = writeParametersTitle(messages.getText("generic.driver.divgeo"),reportHeader,height,10,150); 
        height = writeParameter(divGeoLabels[0]+":",divGeoValues[0],reportHeader,height,10,50,60,100);
        height = writeParameter(divGeoLabels[1]+":",divGeoValues[1],reportHeader,height,10,50,60,100);

        maxHeight=Math.max(maxHeight,height);

        height = 25;
       
        String[] divOrgs = sys.getUserSessionData().getFilter().getLevelLabels();
        for(int i = 0; i < divOrgs.length; i++) {
            if(!Util.empty(divOrgs[i])) {
                height = writeParametersTitle(messages.getText(divOrgs[i]),reportHeader,height,240,150);
                for(int j = 0; j < 3; j++) {
                    if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 1 && j== 0) && 
                            !(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 2 && j== 0)) {
                        if(!Util.empty(divOrgLabels[i][j])) {
                            height = writeParameter(divOrgLabels[i][j]+":",divOrgValues[i][j],reportHeader,height,240,80,320,160);
                        }
                    }
                }
            }
        }
       
        maxHeight=Math.max(maxHeight,height);
        return maxHeight;
	}


    protected ReportFooter createReportFooter() {
        ReportFooter reportFooter = new ReportFooter();
        reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, null,
                new Rectangle2D.Float(10, 0, getWidth(), 20), false, true));
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, 200, 20),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.driver.driverdata.total")));
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement(null,new Rectangle2D.Float(last, 0, 20, 20),
                Color.WHITE,ElementAlignment.RIGHT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"", "#####","total"));
        
        if(((EventReportForm)form).getOutput().equals("HTML")) {
            int left = 70;
            reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 40, 50, 15),
                    Color.BLUE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE_PARAM_FONT,messages.getText("generic.messages.pages")));
            for(int i = 1; i <= pages; i++) {
                int width = (i < 10) ? 15 : 20; 
                if(i != pageNumber) {
                    reportFooter.addElement(LabelElementFactory.createLabelElement("URL-Field--"+i,new Rectangle2D.Float(left, 40, width, 15),
                            Color.BLUE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE_PARAM_FONT,String.valueOf(i)));
                }else {
                    reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(left, 40, width, 15),
                            Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(i)));
                }
                left += width;
            }
        }
        return reportFooter;
    }

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
        color.setColorFalse(DCGray2);
        functions.add(color);

        if(model.getRowCount() > 0) {
            BSHExpression bshExp = new BSHExpression();
            bshExp.setName("optionpath");
            bshExp.setExpression("getValue() {" +
                "               String event = dataRow.get (\"typenumber\");" +
                "               String id =  dataRow.get (\"id\");" +
                "               if (!event.equals(\"3\"))" +
                "                   return \"./layout.jsp?refer=/event/EventView.jsp?eventId=\"+id;" +
                "               else "+
                "                   return \"./layout.jsp?refer=/event/FineView.jsp?fine=\"+id;" +
                    "}");
            functions.add(bshExp);
            TextFormatExpression textExp = new TextFormatExpression();
            textExp.setName("URLCreateExpression");
            textExp.setPattern("{0}");
            textExp.setField(0,"optionpath");
            functions.add(textExp);
        }
        
        CreateHyperLinksFunction link = new CreateHyperLinksFunction();
        link.setName("AddHRefs");
        link.setField("URLCreateExpression");
        link.setElement("URL-Field");
        functions.add(link);

        TextFormatExpression textExp2 = new TextFormatExpression();
        textExp2.setName("URLCreateExpression2");
        textExp2.setPattern("./layout.jsp?refer=/driver/DriverView.jsp?id={0}");
        textExp2.setField(0,"driverId");
        functions.add(textExp2);

        CreateHyperLinksFunction link2 = new CreateHyperLinksFunction();
        link2.setName("AddHRefs2");
        link2.setField("URLCreateExpression2");
        link2.setElement("URL-Field-2");
        functions.add(link2);
        
        if(((EventReportForm)form).getOutput().equals("HTML")) {
            for(int i = 1; i <= pages; i++) {
                if(i != pageNumber) {
                    TextFormatExpression textExp3 = new TextFormatExpression();
                    textExp3.setName("URLCreateExpressions"+i);
                    textExp3.setPattern("./layout.jsp?refer=/event/eventReportPage.jsp?page="+i+((EventReportForm)form).getLink());
                    functions.add(textExp3);
            
                    CreateHyperLinksFunction link3 = new CreateHyperLinksFunction();
                    link3.setName("AddHRefs-"+i);
                    link3.setField("URLCreateExpressions"+i);
                    link3.setElement("URL-Field--"+i);
                    functions.add(link3);
                }
            }
        }

        return functions;
    }

	@Override
	protected int getReportWidth() {
		return 0;
	}
}
