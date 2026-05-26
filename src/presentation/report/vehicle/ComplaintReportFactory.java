/**
 * Created on 10-jul-2006
 * CEPA
 * DataCenter 5
 */
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
import presentation.translator.web.vehicle.ComplaintReportForm;
import business.SystemAccess;

import communication.language.Text;

public class ComplaintReportFactory extends ReportFactory {

    protected Text messages;
    
    private int pages;
    private int pageNumber;
    protected SystemAccess sys;
    protected String[] labels;
    
    public ComplaintReportFactory(String name, TableModel model, ActionForm form, 
    		SystemAccess sys, Text messages,int count, int pages, int pageNumber) {
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
        return 1882;
    }

    protected int getReportWidth() {
        return 1800;
    }

    protected ItemBand createItemBand() {
        ItemBand items = super.createItemBand();        
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,
        		new Rectangle2D.Float(10, 0, getReportWidth(), 15), false, true));
        items.addElement(TextFieldElementFactory.createStringElement("URL-Field02",new Rectangle2D.Float(10, 0, 60, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","date"));
        items.addElement(TextFieldElementFactory.createStringElement("hour",new Rectangle2D.Float(70, 0, 50, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","hour"));
        items.addElement(TextFieldElementFactory.createStringElement("URL-Field01",new Rectangle2D.Float(120, 0, 80, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","matricula"));
        items.addElement(TextFieldElementFactory.createStringElement("URL-Field01",new Rectangle2D.Float(200, 0, 80, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","prefijo"));
        items.addElement(TextFieldElementFactory.createStringElement("location",new Rectangle2D.Float(280, 0, 150, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","location"));
        items.addElement(TextFieldElementFactory.createStringElement("description",new Rectangle2D.Float(430, 0, 150, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","description"));
        items.addElement(TextFieldElementFactory.createStringElement("callType",new Rectangle2D.Float(580, 0, 150, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","callType"));
        items.addElement(TextFieldElementFactory.createStringElement("specification",new Rectangle2D.Float(730, 0, 150, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","specification"));
        items.addElement(TextFieldElementFactory.createStringElement("reportDate",new Rectangle2D.Float(880, 0, 60, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","reportDate"));
        items.addElement(TextFieldElementFactory.createStringElement("ticket",new Rectangle2D.Float(940, 0, 60, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","ticket"));
        items.addElement(TextFieldElementFactory.createStringElement("phoneId",new Rectangle2D.Float(1000, 0, 60, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","phoneId"));
        items.addElement(TextFieldElementFactory.createStringElement("agent",new Rectangle2D.Float(1060, 0, 100, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","agent"));
        items.addElement(TextFieldElementFactory.createStringElement("reporterName",new Rectangle2D.Float(1160, 0, 100, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","reporterName"));
        items.addElement(TextFieldElementFactory.createStringElement("reporterPhone",new Rectangle2D.Float(1260, 0, 60, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","reporterPhone"));
        items.addElement(TextFieldElementFactory.createStringElement("reporterMobile",new Rectangle2D.Float(1320, 0, 60, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","reporterMobile"));
        items.addElement(TextFieldElementFactory.createStringElement("reporterEmail",new Rectangle2D.Float(1380, 0, 100, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","reporterEmail"));
        items.addElement(TextFieldElementFactory.createStringElement("options",new Rectangle2D.Float(1480, 0, 200, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","options"));
        items.addElement(TextFieldElementFactory.createStringElement("actions",new Rectangle2D.Float(1680, 0, 200, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","actions"));

       
        return items;
    }
    
    protected ReportHeader createReportHeader() {
        ComplaintReportForm reportForm = (ComplaintReportForm)form;
        ReportHeader reportHeader = new ReportHeader();
        int height = writeFilter(reportForm, reportHeader);
                
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FirstLevelColor, null,
                        new Rectangle2D.Float(10, height, getReportWidth(), 18), false, true));
        
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 60, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("vehicle.complaint.date")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(70, height, 50, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("vehicle.complaint.hour")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(120, height, 80, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.registrationNumber")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(200, height, 80, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.prefix")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(280, height, 150, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("vehicle.complaint.location")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(430, height, 150, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("vehicle.complaint.description")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(580, height, 150, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("vehicle.complaint.phonetype")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(730, height, 150, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("vehicle.complaint.specification")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(880, height, 60, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("vehicle.complaint.reportdate")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(940, height, 60, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("vehicle.complaint.ticket")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(1000, height, 60, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("vehicle.complaint.phoneId")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(1060, height, 100, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("vehicle.complaint.agent")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(1160, height, 100, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("vehicle.complaint.reportername")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(1260, height, 60, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("vehicle.complaint.reporterphone")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(1320, height, 60, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("vehicle.complaint.reportermobile")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(1380, height, 100, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("vehicle.complaint.reporteremail")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(1480, height, 200, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("vehicle.complaint.options")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(1680, height, 200, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("vehicle.complaint.actions")));
       
        return reportHeader;
    }

    protected int writeFilter(ComplaintReportForm reportForm, ReportHeader reportHeader) {
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 25), false, true));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, getReportWidth(), 25),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("vehicle.complaint.report")));
        
        int height = 25;
        int maxHeight=height;
        
        height = writeParametersTitle(messages.getText("generic.vehicle.data"),reportHeader,height,10,240);
        
        height = writeParameter(messages.getText("generic.vehicle.vehicledata.matricula")+":",reportForm.getPlate(),reportHeader,height,10,80,90,160);
        height = writeParameter(messages.getText("complaint.report.from")+":",reportForm.getFrom(),reportHeader,height,10,80,90,160);
        height = writeParameter(messages.getText("complaint.report.to")+":",reportForm.getTo(),reportHeader,height,10,80,90,160);
        height = writeParameter(messages.getText("complaint.report.status")+":",messages.getText(reportForm.getVehicleStatus()),reportHeader,height,10,80,90,160);

        height = writeParametersTitle(messages.getText("generic.driver.divgeo"),reportHeader,height,10,240); 
        height = writeParameter(divGeoLabels[0]+":",divGeoValues[0],reportHeader,height,10,80,90,160);
        height = writeParameter(divGeoLabels[1]+":",divGeoValues[1],reportHeader,height,10,80,90,160);

        maxHeight=Math.max(maxHeight,height);
        height = 25;
       
        int principalCount = sys.getUserSessionData().getFilter().getDivOrgCount();
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


        maxHeight=Math.max(maxHeight,height);
        
        return maxHeight;
    }

    protected ReportFooter createReportFooter() {
        ReportFooter reportFooter = new ReportFooter();
        reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, null,
                new Rectangle2D.Float(10, 10, getReportWidth(), 20), false, true));
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 160, 20),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.messages.total")));
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement("total",new Rectangle2D.Float(1600, 10, 200, 20),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"", formatNoDecimals,"total"));

        if(((ComplaintReportForm)form).getOutput().equals("HTML")) {
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
    	CreateHyperLinksFunction link1, link2, link3;
    	TextFormatExpression textExp1, textExp2, textExp3;
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

        textExp1 = new TextFormatExpression();
        textExp1.setName("URLCreateExpression01");
        
       	textExp1.setPattern("./layout.jsp?refer=/vehicle/VehicleView.jsp?id={0}");
        textExp1.setField(0,"vehicle");
        functions.add(textExp1);

        link1 = new CreateHyperLinksFunction();
        link1.setName("AddHRefs01");
        link1.setField("URLCreateExpression01");
        link1.setElement("URL-Field01");
        functions.add(link1);
 
        textExp2 = new TextFormatExpression();
        textExp2.setName("URLCreateExpression02");
        
       	textExp2.setPattern("./layout.jsp?refer=/vehicle/complaint.jsp?id={0}&type=Edit");
        textExp2.setField(0,"id");
        functions.add(textExp2);

        link2 = new CreateHyperLinksFunction();
        link2.setName("AddHRefs02");
        link2.setField("URLCreateExpression02");
        link2.setElement("URL-Field02");
        functions.add(link2);
        
        if(((ComplaintReportForm)form).getOutput().equals("HTML")) {
            for(int i = 1; i <= pages; i++) {
                if(i != pageNumber) {
                    textExp2 = new TextFormatExpression();
                    textExp2.setName("URLCreateExpression"+i);  
                   	textExp2.setPattern("./layout.jsp?refer=/vehicle/ComplaintReportResult.jsp?page="+i+((ComplaintReportForm)form).getLink());
                   
                    
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
