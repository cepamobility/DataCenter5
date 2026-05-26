package presentation.report.vehicle;

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
import org.jfree.report.function.ItemCountFunction;
import org.jfree.report.function.TextFormatExpression;

import presentation.report.ReportFactory;
import presentation.translator.web.util.Util;
import presentation.translator.web.vehicle.SuctaMTOPQueryForm;
import business.SystemAccess;

import communication.language.Text;

public class SuctaMTOPQueryFactory extends ReportFactory {

    protected Text messages;
    
    private int pages;
    private int pageNumber;
    protected SystemAccess sys;
    protected String[] labels;
    private int last;
    private int principalCount;
    private String suctaormtop;
    private String vehicleorplataform;
    
    public SuctaMTOPQueryFactory(String name, TableModel model, ActionForm form, 
    		SystemAccess sys, Text messages,int count, int pages, int pageNumber, 
    		String suctaormtop, String vehicleorplataform) {
        super(name, model, form);
        this.messages = messages;
        this.vehicleorplataform = vehicleorplataform;
        this.count = count;
        this.suctaormtop = suctaormtop;
        this.pages = pages;
        this.pageNumber = pageNumber;
        this.sys = sys;
        labels = sys.getUserSessionData().getFilter().getDivOrgLabels();
        parseDivisionValues(sys,messages);
        last = 450;
        principalCount = sys.getUserSessionData().getFilter().getPrincipalCount();
        for(int i = 0; i < principalCount; i++) {
            for(int j = principalCount-1; j >= 0; j--) {
                if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 &&
                		i == 1 && j== 0) && 
                        !(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 
                        		&& i == 2 && j== 0)) {
                    if(!Util.empty(labels[i*principalCount+j])) {
                        last += 80;
                    }
                }
            }
        }
    }

    protected int getWidth() {
        return last+142;
    }

    protected int getReportWidth() {
        return last+60;
    }

    protected ItemBand createItemBand() {
        ItemBand items = super.createItemBand();        
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,
        		new Rectangle2D.Float(10, 0, getReportWidth(), 15), false, true));
        items.addElement(TextFieldElementFactory.createStringElement("URL-Field",
        		new Rectangle2D.Float(10, 0, 80, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,
        		"","plate"));
        int w = 90;//420;
        items.addElement(TextFieldElementFactory.createStringElement("country",
        		new Rectangle2D.Float(w, 0, 80, 15),Color.BLACK,
        		ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","country"));
        w += 80;
        for(int i = 0; i < principalCount; i++) {
            for(int j = principalCount-1; j >= 0; j--) {
                if(!(sys.getUserSessionData().getFilter().getCorporationId().
                		intValue() == 1 && i == 1 && j== 0) && 
                        !(sys.getUserSessionData().getFilter().getCorporationId().
                        		intValue() == 1 && i == 2 && j== 0)) {
                    if(!Util.empty(labels[i*principalCount+j])) {
                        items.addElement(TextFieldElementFactory.createStringElement
                        		("divorg"+String.valueOf(i+1)+String.valueOf(j+1),
                                new Rectangle2D.Float(w, 0, 80, 15),Color.BLACK,
                                ElementAlignment.CENTER,
                                ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-",
                                "divorg"+String.valueOf(i+1)+String.valueOf(j+1)));
                        w += 80;
                    }
                }
            }
        }
        if (suctaormtop.equalsIgnoreCase("sucta")){
        	items.addElement(TextFieldElementFactory.createStringElement(
        			"sucta",new Rectangle2D.Float(w, 0, 80, 15),
        			Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,
        			STYLE_SIMPLE_FONT,"","sucta"));
        }else{
        	items.addElement(TextFieldElementFactory.createStringElement(
        			"mtop",new Rectangle2D.Float(w, 0, 80, 15),
        			Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,
        			STYLE_SIMPLE_FONT,"","mtop"));
        }
        w += 80;
        
       
        return items;
    }
    
    protected ReportHeader createReportHeader() {
        SuctaMTOPQueryForm reportForm = (SuctaMTOPQueryForm)form;
        ReportHeader reportHeader = new ReportHeader();
        int height = writeFilter(reportForm, reportHeader);
                
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FirstLevelColor, null,
                        new Rectangle2D.Float(10, height, getReportWidth(), 18), false, true));
        
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 80, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.registrationNumber")));
        
        
        int w = 90; 
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,divGeoLabels[1]));
        w += 80;
        for(int i = 0; i < 9; i++) {
            if(!Util.empty(labels[i])) {
                if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 3) && 
                        !(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 6)) {
                    reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 18),
                            Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(labels[i])));
                        w += 80;
                }
            }
        }
        
        if (suctaormtop.equalsIgnoreCase("sucta")){
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.messages.suctamtopquery.sucta")));
        }else{
        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.messages.suctamtopquery.mtop")));
        }         
        w += 80;
                
        return reportHeader;
    }

    protected int writeFilter(SuctaMTOPQueryForm reportForm, ReportHeader reportHeader) {
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 25), false, true));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, getReportWidth(), 25),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("generic.messages.suctamtopquery.title.query")));
        
        int height = 25;
        int maxHeight=height;
        
        height = writeParametersTitle(messages.getText("generic.vehicle.data"),reportHeader,height,10,240);
        height = writeParameter(
        		messages.getText("generic.messages.suctamtopquery.vehicleorplataform")+":",
        		messages.getText(
        				(reportForm.getVehicleorplataform().equalsIgnoreCase("vehicle")?
        				"generic.messages.suctamtopquery.vehicle"
        				:"generic.messages.suctamtopquery.plataform")),
        				reportHeader,height,10,80,90,160);
        height = writeParameter(messages.getText("generic.messages.suctamtopquery.status")+":",
        		messages.getText(reportForm.getStatusLabel()),reportHeader,height,10,80,90,160);
        height = writeParameter(messages.getText("query.apmm.from")+":",
        		reportForm.getFrommonth()+"-"+reportForm.getFromyear(),
        		reportHeader,height,10,80,90,160);       
        height = writeParameter(messages.getText("query.apmm.to")+":",
        		reportForm.getTomonth()+"-"+reportForm.getToyear(),
        		reportHeader,height,10,80,90,160);       
        
        maxHeight=Math.max(maxHeight,height);
        height = 25;
       
        String[] divOrgs = sys.getUserSessionData().getFilter().getLevelLabels();
        for(int i = 0; i < divOrgs.length; i++) {
            if(!Util.empty(divOrgs[i])) {
                height = writeParametersTitle(messages.getText(divOrgs[i]),reportHeader,height,330,240);
                for(int j = 0; j < principalCount; j++) {
                    if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 1 && j== 0) && 
                            !(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 2 && j== 0)) {
                        if(!Util.empty(divOrgLabels[i][j])) {
                            height = writeParameter(divOrgLabels[i][j]+":",divOrgValues[i][j],reportHeader,height,330,80,410,160);
                        }
                    }
                }
            }
        }

        height = writeParametersTitle(messages.getText("generic.driver.divgeo"),reportHeader,height,330,240); 
        height = writeParameter(divGeoLabels[0]+":",divGeoValues[0],reportHeader,height,330,80,410,160);
        height = writeParameter(divGeoLabels[1]+":",divGeoValues[1],reportHeader,height,330,80,410,160);

        maxHeight=Math.max(maxHeight,height);
        
        return maxHeight;
    }

    protected ReportFooter createReportFooter() {
        ReportFooter reportFooter = new ReportFooter();
        reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, null,
                new Rectangle2D.Float(10, 10, getReportWidth(), 20), false, true));
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 160, 20),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.messages.total")));
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement("total",new Rectangle2D.Float(last, 10, 60, 20),
                Color.WHITE,ElementAlignment.RIGHT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"", formatNoDecimals,"total"));

        if(((SuctaMTOPQueryForm)form).getOutput().equals("HTML")) {
            int left = 60;
            reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(0, 40, 50, 15),
                    Color.BLUE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_TITLE_PARAM_FONT,messages.getText("generic.messages.pages")));
            for(int i = 1; i <= pages; i++) {
                int width = (i < 10) ? 15 : 20; 
                if(i != pageNumber) {
                    reportFooter.addElement(LabelElementFactory.createLabelElement("URL-Field-"+i,new Rectangle2D.Float(left, 40, width, 15),
                            Color.BLUE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_TITLE_PARAM_FONT,String.valueOf(i)));
                }else {
                    reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(left, 40, width, 15),
                            Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(i)));
                }
                left += width;
            }
        }        
        return reportFooter;
    }

    protected ExpressionCollection createFunctions() {
    	CreateHyperLinksFunction link2, link;
    	TextFormatExpression textExp;
    	TextFormatExpression textExp2;
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

        textExp = new TextFormatExpression();
        textExp.setName("URLCreateExpression");
        if (vehicleorplataform.equals("vehicle")){
        	textExp.setPattern("./layout.jsp?refer=/vehicle/VehicleView.jsp?id={0}");
            textExp.setField(0,"id");
        }else{
        	textExp.setPattern("./layout.jsp?refer=/vehicle/TrailerView.jsp?trailer={0}");
            textExp.setField(0,"id");
        }

        functions.add(textExp);

        link = new CreateHyperLinksFunction();
        link.setName("AddHRefs");
        link.setField("URLCreateExpression");
        link.setElement("URL-Field");
        functions.add(link);
 
        
        if(((SuctaMTOPQueryForm)form).getOutput().equals("HTML")) {
            for(int i = 1; i <= pages; i++) {
                if(i != pageNumber) { 
                    textExp2 = new TextFormatExpression();
                    textExp2.setName("URLCreateExpression"+i);                                      
                    textExp2.setPattern("./layout.jsp?refer=/vehicle/SuctaMTOPQueryResult.jsp?page="+i+((SuctaMTOPQueryForm)form).getLink());
                    
                    functions.add(textExp2);
            
                    link2 = new CreateHyperLinksFunction();
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

