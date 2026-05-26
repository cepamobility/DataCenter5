package presentation.report.vehicle;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

import org.apache.struts.action.DynaActionForm;
import org.jfree.report.ElementAlignment;
import org.jfree.report.ItemBand;
import org.jfree.report.ReportFooter;
import org.jfree.report.ReportHeader;
import org.jfree.report.elementfactory.LabelElementFactory;
import org.jfree.report.elementfactory.StaticShapeElementFactory;
import org.jfree.report.elementfactory.TextFieldElementFactory;
import org.jfree.report.function.ElementColorFunction;
import org.jfree.report.function.ExpressionCollection;

import presentation.report.ReportFactory;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.language.Text;
import communication.vehicle.report.ReportSyngentaCheckListData;
import communication.vehicle.tables.VehicleCheckData;
import communication.vehicle.tables.VehicleData;

public class ReportSyngentaCheckListFactory extends ReportFactory {

    protected Text messages;
    
    private VehicleCheckData vehicle;
    private int pages;

	private String[][] pneusTexts;
	private String[][] pneusValues;
    
    
   public ReportSyngentaCheckListFactory(String name,ReportSyngentaCheckListData model, DynaActionForm form, VehicleCheckData vehicleCheck, String[][] pneusTexts, String[][] pneusValues, SystemAccess sys) {
        super(name, model, form, sys);
        this.vehicle = vehicleCheck;
        this.messages = sys.getText();
        this.pneusValues = pneusValues;
        this.pneusTexts = pneusTexts;
	}

	protected int getWidth() {
        return 682;
    }

    protected int getReportWidth() {
        return 600;
        
    }
    
    protected ItemBand createItemBand() {
        
        ItemBand items = super.createItemBand();        
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 15), false, true));
        items.addElement(TextFieldElementFactory.createStringElement("data",new Rectangle2D.Float(10, 0, 200, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","data"));
        items.addElement(TextFieldElementFactory.createStringElement("result",new Rectangle2D.Float(210, 0, 100, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","result"));
        items.addElement(TextFieldElementFactory.createStringElement("observations",new Rectangle2D.Float(310, 0, 300, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","observations"));
        return items;
    }
    
    protected ReportHeader createReportHeader() {
        
        ReportHeader reportHeader = new ReportHeader();
        
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 25), false, true));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, getReportWidth(), 25),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("syngenta.checklist.title")));

        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, null,
                new Rectangle2D.Float(10, 40, getReportWidth(), 20), false, true));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 40, 200, 20),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("syngenta.checklist.data")));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 70, 40, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("generic.driver.name")+":"));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(50, 70, 200, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,vehicle.getName()));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 100, 40, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("generic.vehicle.vehicledata.matricula")+":"));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(50, 100, 100, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,vehicle.getVehicle().getMatricula()));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 130, 40, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("generic.vehicle.vehicledata.model")+":"));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(50, 130, 100, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,(vehicle.getVehicle().getModel() == null) ? "---" : vehicle.getVehicle().getModel()));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(250, 70, 40, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("generic.checklist.vehicleKm")+":"));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(290, 70, 100, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,(vehicle.getVehicleKm() == null) ? "---" : String.valueOf(vehicle.getVehicleKm())));
        
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(250, 100, 40, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("generic.vehicle.vehicledata.trade")+":"));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(290, 100, 100, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,vehicle.getVehicle().getTrade().getResourceBundleId()));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(250, 130, 40, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("generic.vehicle.vehicledata.colour")+":"));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(290, 130, 100, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,vehicle.getVehicle().getColour()));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(450, 70, 40, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("generic.driver.charge")+":"));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(490, 70, 100, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,(vehicle.getSerie() == null) ? "---" : vehicle.getSerie()));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(450, 100, 40, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("generic.activity.date")+":"));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(490, 100, 100, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,Util.formatDate(vehicle.getDate())));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(450, 130, 40, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("generic.vehicle.vehicledata.renavan")+":"));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(490, 130, 100, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,(vehicle.getLicenseNumber() == null) ? "---" : vehicle.getLicenseNumber()));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 160, 60, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("generic.vehicle.vehicledata.abs")+":"));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(70, 160, 200, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,(vehicle.getVehicle().getAbs()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no")));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(250, 160, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("generic.vehicle.vehicledata.aireAcondicionado")+":"));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(320, 160, 100, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,(vehicle.getVehicle().getAireAcondicionado()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no")));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(450, 160, 30, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("syngenta.checklist.srs")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(480, 160, 100, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,(vehicle.getVehicle().getAirBag()) ? messages.getText("generic.messages.yes") : messages.getText("generic.messages.no")));

        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, null,
                new Rectangle2D.Float(10, 190, getReportWidth(), 20), false, true));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 190, 200, 20),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("syngenta.checklist.condition")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(310, 190, 300, 20),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("syngenta.checklist.observations")));

        return reportHeader;
    }


    protected ReportFooter createReportFooter() {
        
        ReportFooter reportFooter = new ReportFooter();
        
        reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, null,
                new Rectangle2D.Float(10, 40, getReportWidth(), 20), false, true));
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 40, 200, 20),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.activity.observations")));
        
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 70, getReportWidth(), 50),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.TOP,STYLE_SIMPLE_FONT,(vehicle.getObs() == null) ? "---" : vehicle.getObs()));

        reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, null,
                new Rectangle2D.Float(10, 140, getReportWidth(), 20), false, true));
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 140, 200, 20),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("syngenta.checklist.cover")));

        int x = 50; 
        int y = 170;
        for(int i = 0; i < 4; i++) {
        	reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x+50, y, 100, 15),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,pneusTexts[i][0]));
        	reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x+50, y+15, 100, 15),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,pneusValues[i][0]));
        	reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x+50, y+30, 100, 15),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,pneusTexts[i][1]));
        	reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x+50, y+45, 100, 15),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,String.valueOf(pneusValues[i][1])));
        	if(i == 0 || i == 2) {
        		x += 150;
        	}else {
        		x = 50;
        	}
        	if(i == 1) {
        		reportFooter.addElement(org.jfree.report.elementfactory.ImageFieldElementFactory.createImageDataRowElement("vanImage", new Rectangle2D.Float(75, y+75, 300, 130), "vanImage"));
        		y += 200;
        	}
        }
        return reportFooter;
    }

    protected ExpressionCollection createFunctions() {
        ExpressionCollection functions = super.createFunctions();

        final ElementColorFunction color = new ElementColorFunction();
        color.setName("changeColor");
        color.setElement("Variance Field");
        color.setField("color");
        color.setColorTrue(gray1);
        color.setColorFalse(gray2);
        functions.add(color);
        
        return functions;
    }
}
