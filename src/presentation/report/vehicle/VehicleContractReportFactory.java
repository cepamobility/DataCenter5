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
import presentation.translator.web.vehicle.VehicleContractReportForm;
import business.SystemAccess;

import communication.language.Text;

public class VehicleContractReportFactory extends ReportFactory {

    protected Text messages;
    
    private int pages;
    private int pageNumber;
    protected SystemAccess sys;
    protected String[] labels;
    private int last;
    protected int principalCount;
    
    public VehicleContractReportFactory(String name, TableModel model, ActionForm form, 
    		SystemAccess sys, Text messages,int count, int pages, int pageNumber) {
        super(name, model, form);
        this.messages = messages;
        this.count = count;
        this.pages = pages;
        this.pageNumber = pageNumber;
        this.sys = sys;
        labels = sys.getUserSessionData().getFilter().getDivOrgLabels();
        parseDivisionValues(sys,messages);
        last = 950;
        principalCount = sys.getUserSessionData().getFilter().getDivOrgCount();
        for(int i = 0; i < principalCount; i++) {
            for(int j = principalCount-1; j >= 0; j--) {
                if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 1 && j== 0) && 
                        !(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 2 && j== 0)) {
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
        return last+142;
    }

    protected ItemBand createItemBand() {
        ItemBand items = super.createItemBand();        
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,
        		new Rectangle2D.Float(10, 0, getReportWidth(), 15), false, true));
        items.addElement(TextFieldElementFactory.createStringElement("URL-Field",new Rectangle2D.Float(10, 0, 80, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","plate"));
        int w = 90;//420;
        items.addElement(TextFieldElementFactory.createStringElement("country",new Rectangle2D.Float(w, 0, 80, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","country"));
        w += 80;
        int index = 1;
        for(int i = 0; i < principalCount; i++) {
        	for(int j = 0; j <= principalCount-1; j++) {
                if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 1 && j== 0) && 
                        !(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 2 && j== 0)) {
                    if(!Util.empty(labels[i*principalCount+j])) {
                        items.addElement(TextFieldElementFactory.createStringElement("divorg"+String.valueOf(index),
                                new Rectangle2D.Float(w, 0, 80, 15),Color.BLACK,ElementAlignment.CENTER,
                                ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","divorg"+String.valueOf(i+1)+String.valueOf(j+1)));
                        index++;
                        w += 80;
                    }
                }
            }
        }
        items.addElement(TextFieldElementFactory.createStringElement("type",new Rectangle2D.Float(w, 0, 80, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","type"));
        w += 80;
        items.addElement(TextFieldElementFactory.createStringElement("trade",new Rectangle2D.Float(w, 0, 100, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","trade"));
        w += 100;
        items.addElement(TextFieldElementFactory.createStringElement("model",new Rectangle2D.Float(w, 0, 100, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","model"));
        w += 100;
        
        items.addElement(TextFieldElementFactory.createStringElement("year",new Rectangle2D.Float(w, 0, 60, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","year"));
        w += 60;
        
        items.addElement(TextFieldElementFactory.createStringElement("renavam",new Rectangle2D.Float(w, 0, 100, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","renavam"));
        w += 100;
        
        items.addElement(TextFieldElementFactory.createStringElement("driverName",new Rectangle2D.Float(w, 0, 100, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","driverName"));
        w += 100;
        items.addElement(TextFieldElementFactory.createStringElement("driverLastName",new Rectangle2D.Float(w, 0, 100, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","driverLastName"));
        w += 100;
        items.addElement(TextFieldElementFactory.createStringElement("contractDate",new Rectangle2D.Float(w, 0, 100, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","contractDate"));
        w += 100;
        items.addElement(TextFieldElementFactory.createStringElement("contractType",new Rectangle2D.Float(w, 0, 100, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","contractType"));
        w += 100;
        
       
        return items;
    }
    
    protected ReportHeader createReportHeader() {
        VehicleContractReportForm reportForm = (VehicleContractReportForm)form;
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
        for(int i = 0; i < principalCount; i++) {
            for(int j = 0; j <= principalCount-1; j++) {
                if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 1 && j== 0) && 
                        !(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 2 && j== 0)) {
                    if(!Util.empty(labels[i*principalCount+j])) {
                        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 18),
                            Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(labels[i*principalCount+j])));
                        w += 80;
                    }
                }
            }
        }
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.vehicletype")));
        w += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.trade")));
        w += 100;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.model")));
        w += 100;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height,60, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.year")));       
        w += 60;
        
        // Numero de Renavam:
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.permissionNumber")));
       
        w += 100;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.firstname")));
        w += 100;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.lastname")));
        w += 100;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.contract.date")));
       
        w += 100;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.contract.type")));
        
        return reportHeader;
    }

    /**
     * 
     * @param reportForm
     * @param reportHeader
     * @return
     */
    protected int writeFilter(VehicleContractReportForm reportForm, ReportHeader reportHeader) {
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 25), false, true));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, getReportWidth(), 25),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("query.vehicle.vehicledata.title")));
        
        int height = 25;
        int maxHeight=height;
        
        height = writeParametersTitle(messages.getText("generic.vehicle.data"),reportHeader,height,10,240);
        
       	height = writeParameter(messages.getText("generic.vehicle.vehicledata.vehicletype")+":",messages.getText(reportForm.getVehicleType()),reportHeader,height,10,80,90,160);
        height = writeParameter(messages.getText("generic.vehicle.contract.type")+":",messages.getText(reportForm.getType()),reportHeader,height,10,80,90,160);
       
        /*
        height = writeParameter(messages.getText("generic.vehicle.vehicledata.model")+":",reportForm.getModel(),reportHeader,height,10,80,90,160);
        height = writeParameter(messages.getText("generic.vehicle.vehicledata.matricula")+":",reportForm.getPlate(),reportHeader,height,10,80,90,160);
        height = writeParameter(messages.getText("generic.vehicle.vehicledata.year")+":",reportForm.getYear(),reportHeader,height,10,80,90,160);
        height = writeParameter(messages.getText("generic.vehicle.vehicledata.fuel")+":",messages.getText(reportForm.getFuel()),reportHeader,height,10,80,90,160);
        height = writeParameter(messages.getText("generic.vehicle.vehicledata.costCenter")+":",reportForm.getCenterCost(),reportHeader,height,10,80,90,160);
*/
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

        if(((VehicleContractReportForm)form).getOutput().equals("HTML")) {
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
        
       	textExp.setPattern("./layout.jsp?refer=/vehicle/VehicleOrTrailerView.jsp?id={0}&isTrailer={1}");
        textExp.setField(0,"id");
        textExp.setField(1,"isTrailer");        
        functions.add(textExp);

        link = new CreateHyperLinksFunction();
        link.setName("AddHRefs");
        link.setField("URLCreateExpression");
        link.setElement("URL-Field");
        functions.add(link);
 
        
        if(((VehicleContractReportForm)form).getOutput().equals("HTML")) {
            for(int i = 1; i <= pages; i++) {
                if(i != pageNumber) {
                    textExp2 = new TextFormatExpression();
                    textExp2.setName("URLCreateExpression"+i);  
                   	textExp2.setPattern("./layout.jsp?refer=/vehicle/ContractReportResult.jsp?page="+i+((VehicleContractReportForm)form).getLink());
                   
                    
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
