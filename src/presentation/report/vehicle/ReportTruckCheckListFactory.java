package presentation.report.vehicle;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

import org.apache.struts.action.DynaActionForm;
import org.jfree.report.ElementAlignment;
import org.jfree.report.Group;
import org.jfree.report.GroupFooter;
import org.jfree.report.GroupHeader;
import org.jfree.report.GroupList;
import org.jfree.report.ItemBand;
import org.jfree.report.ReportFooter;
import org.jfree.report.ReportHeader;
import org.jfree.report.elementfactory.LabelElementFactory;
import org.jfree.report.elementfactory.NumberFieldElementFactory;
import org.jfree.report.elementfactory.StaticShapeElementFactory;
import org.jfree.report.elementfactory.TextFieldElementFactory;
import org.jfree.report.function.ElementColorFunction;
import org.jfree.report.function.ExpressionCollection;

import presentation.report.ReportFactory;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.language.Text;
import communication.vehicle.report.ReportTruckCheckListData;
import communication.vehicle.report.VehiclePursuitReportDataType;
import communication.vehicle.tables.VehicleCheckData;

public class ReportTruckCheckListFactory extends ReportFactory {

    protected Text messages;
    
    private VehicleCheckData vehicle;
    private int pages;

    
   public ReportTruckCheckListFactory(String name,ReportTruckCheckListData model, DynaActionForm form, VehicleCheckData vehicleCheck, SystemAccess sys) {
        super(name, model, form, sys);
        this.vehicle = vehicleCheck;
        this.messages = sys.getText();
	}

	protected int getWidth() {
        return 532;
    }

    protected int getReportWidth() {
        return 450;
        
    }
    
    protected GroupList createGroups() {
        GroupList grouplist = new GroupList();

        Group pointGroup = new Group();
        pointGroup.setName("point");
        pointGroup.addField("point");
    
        GroupHeader header = new GroupHeader();
            
        header.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, null,
                new Rectangle2D.Float(10, 10, getReportWidth(), 20), false, true));
        header.addElement(TextFieldElementFactory.createStringElement("data",new Rectangle2D.Float(10, 10, 200, 20),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"","point"));
        
        pointGroup.setHeader(header);
        grouplist.add(pointGroup);

        return grouplist;
    }
    
    protected ItemBand createItemBand() {
        ItemBand items = super.createItemBand();
        
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 15), false, true));
        items.addElement(TextFieldElementFactory.createStringElement("data",new Rectangle2D.Float(10, 0, 300, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","data"));
        items.addElement(TextFieldElementFactory.createStringElement("result",new Rectangle2D.Float(310, 0, 100, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","result"));
        return items;
    }
    
    protected ReportHeader createReportHeader() {
        
        ReportHeader reportHeader = new ReportHeader();
        
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 25), false, true));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, getReportWidth(), 25),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("truck.checklist.title")));

        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, null,
                new Rectangle2D.Float(10, 40, getReportWidth(), 20), false, true));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 40, 200, 20),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("truck.checklist.data")));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 70, 40, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("generic.activity.date")+":"));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(50, 70, 100, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,Util.formatDate(vehicle.getDate())));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 90, 40, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("generic.driver.name")+":"));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(50, 90, 160, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,vehicle.getName()));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 110, 100, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("generic.driver.licenseNumber")+":"));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(110, 110, 100, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,vehicle.getLicenseNumber()));
        
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 130, 100, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("generic.activity.dateLicenseExpiry")+":"));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(110, 130, 100, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,Util.formatDate(vehicle.getDateLicenseExpiry())));

        
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(220, 70, 40, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("generic.vehicle.vehicledata.matricula")+":"));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(260, 70, 100, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,vehicle.getVehicle().getMatricula()));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(220, 90, 40, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("generic.vehicle.vehicledata.trade")+":"));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(260, 90, 100, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText(vehicle.getVehicle().getTrade().getResourceBundleId())));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(220, 110, 40, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("generic.vehicle.vehicledata.model")+":"));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(260, 110, 100, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,(vehicle.getVehicle().getModel() == null) ? "---" : vehicle.getVehicle().getModel()));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(220, 130, 40, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("generic.vehicle.serie")+":"));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(260, 130, 100, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,(vehicle.getSerie() == null) ? "---" : vehicle.getSerie()));
        
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(220, 150, 40, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("generic.checklist.vehicleKm")+":"));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(260, 150, 100, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,(vehicle.getVehicleKm() == null) ? "---" : String.valueOf(vehicle.getVehicleKm())));

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
