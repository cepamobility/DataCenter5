/**
 * Created on 10-jul-2006
 * CEPA
 * DataCenter 5
 */
package presentation.report.queries;

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

import presentation.report.ReportFactory;
import presentation.translator.web.queries.ReportOdometerLogForm;
import business.SystemAccess;

import communication.language.Text;
import communication.vehicle.tables.VehicleData;

public class ReportOdometerLogFactory extends ReportFactory {

    protected Text messages;
    private String[] fleets;
    private int columns;
    private SystemAccess sys;
    
    public ReportOdometerLogFactory(String name, TableModel model, ActionForm form, SystemAccess sys, String[] fleets) {
        super(name, model, form);
        this.sys = sys;
        this.fleets = fleets;
        messages = sys.getText();
    }
    
    protected int getWidth() {
        return getReportWidth()+82;
    }
    
    protected int getReportWidth() {
    	return 1090+sys.getUserSessionData().getFilter().getPrincipalCount()*100;
    }

    protected ItemBand createItemBand() {
        ItemBand items = super.createItemBand();        
        
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,new Rectangle2D.Float(10, 0, getReportWidth(), 15), false, true));
        
        items.addElement(TextFieldElementFactory.createStringElement("URL-Field2",new Rectangle2D.Float(10, 0, 80, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","plate"));
        items.addElement(TextFieldElementFactory.createStringElement("trade",new Rectangle2D.Float(90, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","trade"));
        items.addElement(TextFieldElementFactory.createStringElement("model",new Rectangle2D.Float(170, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","model"));

        items.addElement(TextFieldElementFactory.createStringElement("URL-Field1",new Rectangle2D.Float(270, 0, 65, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","driverFirstName"));
        items.addElement(TextFieldElementFactory.createStringElement("URL-Field1",new Rectangle2D.Float(335, 0, 65, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","driverLastName"));
        items.addElement(TextFieldElementFactory.createStringElement("director",new Rectangle2D.Float(400, 0, 60, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","director"));
        int x = 460;
        for(int i = 1; i <= sys.getUserSessionData().getFilter().getPrincipalCount(); i++) {
            items.addElement(TextFieldElementFactory.createStringElement("divorg"+i,new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","divorg"+i));
            x += 100;
        }
        
        items.addElement(TextFieldElementFactory.createStringElement("fleet",new Rectangle2D.Float(x, 0, 120, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","fleet"));
        x += 120;
        items.addElement(TextFieldElementFactory.createStringElement("period",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","period"));
        x += 80;
        items.addElement(NumberFieldElementFactory.createNumberElement("odometer",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","odometer"));
        x += 80;
        items.addElement(NumberFieldElementFactory.createNumberElement("accidents",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","accidents"));
        x += 80;
        items.addElement(NumberFieldElementFactory.createNumberElement("incidents",new Rectangle2D.Float(x, 0, 80, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","incidents"));
        x += 80;
        items.addElement(TextFieldElementFactory.createStringElement("user",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","user"));
        x += 100;
        items.addElement(DateFieldElementFactory.createDateElement("date",new Rectangle2D.Float(x, 0, 90, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",DATE_FORMAT,"date"));
        
        return items;
    }
    
    protected ReportHeader createReportHeader() {
        
        ReportHeader reportHeader = new ReportHeader();
        
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,new Rectangle2D.Float(10, 0, getReportWidth(), 25), false, true));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, getReportWidth(), 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("query.odometerlog.title")));

        int height = 25;
        height = writeParametersTitle(messages.getText("query.odometerlog.date"),reportHeader,height,10,160);
        height = writeParameter(messages.getText("query.mileage.from")+":",((ReportOdometerLogForm)form).getDateFrom(messages),reportHeader,height,10,80,90,160);
        height = writeParameter(messages.getText("query.mileage.to")+":",((ReportOdometerLogForm)form).getDateTo(messages),reportHeader,height,10,80,90,160);

        height = writeParametersTitle(messages.getText("query.odometerlog.period"),reportHeader,height,10,160);
        height = writeParameter(messages.getText("query.mileage.from")+":",((ReportOdometerLogForm)form).getPeriodFrom(messages),reportHeader,height,10,80,90,160);
        height = writeParameter(messages.getText("query.mileage.to")+":",((ReportOdometerLogForm)form).getPeriodTo(messages),reportHeader,height,10,80,90,160);
        
        int maxHeight = height;
        
        height = 25;
        height = writeParametersTitle(messages.getText("generic.vehicle.data"),reportHeader,height,260,120);
        height = writeParameter(messages.getText("generic.vehicle.vehicledata.matricula")+":",((ReportOdometerLogForm)form).getPlate(),reportHeader,height,270,80,350,150);
        height = writeParameter(messages.getText("generic.vehicle.vehicledata.vehicletype")+":",messages.getText(((ReportOdometerLogForm)form).getType()),reportHeader,height,270,80,350,150);
        if(((ReportOdometerLogForm)form).getUse().equals("persistence.datatype.typeuse.institutional") || ((ReportOdometerLogForm)form).getUse().equals("persistence.datatype.typeuse.individual")) {
            height = writeParameter(messages.getText(((ReportOdometerLogForm)form).getUse()),"",reportHeader,height,270,230,500,0);
        }
        if(((ReportOdometerLogForm)form).getStatus().equals(String.valueOf(VehicleData.ACTIVE))) {
            height = writeParameter(messages.getText("generic.messages.active"),"",reportHeader,height,270,230,500,0);
        }
        if(((ReportOdometerLogForm)form).getStatus().equals(String.valueOf(VehicleData.EXCLUDED))) {
            height = writeParameter(messages.getText("generic.messages.excluded"),"",reportHeader,height,270,230,500,0);
        }

        maxHeight = Math.max(height,maxHeight);

        height = 25;
        height = writeParametersTitle(messages.getText("query.mileage.fleet"),reportHeader,height,510,120);
        for(int i = 0; i < fleets.length; i++) {
            height = writeParameter("","- "+fleets[i],reportHeader,height,510,0,510,320);
        }

        height = Math.max(height,maxHeight);
        
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FirstLevelColor, null,new Rectangle2D.Float(10, height, getReportWidth(), 18), false, true));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 80, 15),Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("vehicle.trailer.plate")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(90, height, 80, 15),Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.trade")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(170, height, 100, 15),Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.model")));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(270, height, 65, 15),Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.firstname")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(335, height, 65, 15),Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.lastname")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(400, height, 60, 15),Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.director")));
        int x = 460;
        String[] labels = sys.getUserSessionData().getFilter().getPrincipalLabels();
        for(int i = 0; i < labels.length; i++) {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 100, 15),Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(labels[i])));
            x += 100;
        }

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 120, 15),Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.data.fleet")));
        x += 120;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 15),Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.fleet.fleetdata.period")));
        x += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 15),Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.messages.odometer")));
        x += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 15),Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.messages.accident")));
        x += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 15),Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.messages.incident")));
        x += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 100, 15),Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.data.user")));
        x += 100;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 90, 15),Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.inclusiondate")));
        
        return reportHeader;
    }

    protected ReportFooter createReportFooter() {
        ReportFooter reportFooter = new ReportFooter();
        reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, null,
                new Rectangle2D.Float(10, 10, getReportWidth(), 15), false, true));
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 160, 15),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,messages.getText("query.driver.driverdata.total")));
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement(null,new Rectangle2D.Float(getReportWidth()-90, 10, 90, 15),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,"", formatNoDecimals,"countExpression"));
        
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

        final ItemCountFunction countFunction = new ItemCountFunction();
        countFunction.setName("countExpression");
        countFunction.setGroup("dummy");
        functions.add(countFunction);
        
        TextFormatExpression textExp1 = new TextFormatExpression();
        textExp1.setName("URLCreateExpression1");
        textExp1.setPattern("./layout.jsp?refer=/driver/DriverView.jsp?id={0}");
        textExp1.setField(0,"driverId");
        functions.add(textExp1);

        CreateHyperLinksFunction link1 = new CreateHyperLinksFunction();
        link1.setName("AddHRefs1");
        link1.setField("URLCreateExpression1");
        link1.setElement("URL-Field1");
        functions.add(link1);

        TextFormatExpression textExp2 = new TextFormatExpression();
        textExp2.setName("URLCreateExpression2");
        textExp2.setPattern("./layout.jsp?refer=/vehicle/VehicleView.jsp?id={0}");
        textExp2.setField(0,"vehicle");
        functions.add(textExp2);

        CreateHyperLinksFunction link2 = new CreateHyperLinksFunction();
        link2.setName("AddHRefs2");
        link2.setField("URLCreateExpression2");
        link2.setElement("URL-Field2");
        functions.add(link2);

        return functions;
    }
}
