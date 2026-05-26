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
import presentation.translator.web.vehicle.TrailersWOCheckListForm;
import business.SystemAccess;

import communication.language.Text;
import communication.vehicle.tables.VehicleData;

public class TrailerWithoutCheckListFactory extends ReportFactory {

    protected Text messages;
    
    private SystemAccess sys;

    private String[] labels;
    private int width;
    public TrailerWithoutCheckListFactory(String name, TableModel model, ActionForm form, SystemAccess sys, Text messages) {
        super(name, model, form);
        this.messages = messages;
        parseDivisionValues(sys,messages);
        this.sys = sys;
        labels = sys.getUserSessionData().getFilter().getPrincipalLabels();
        width = 450;
        for(int i = 0; i < labels.length; i++) {
            width += 90;
        }
    }


    protected int getWidth() {
        return width + 82;
    }

    protected int getReportWidth() {
    	return width;
    }
    
    protected GroupList createGroups() {
        GroupList grouplist = new GroupList();

        Group group = new Group();
        group.setName("vehicle");
        group.addField("type");
        
        GroupHeader header = new GroupHeader();
        header.setName("typeGroupHeader");

        header.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, DCGray3, null,
                new Rectangle2D.Float(0, 10, getReportWidth(), 15), false, true));
        header.addElement(TextFieldElementFactory.createStringElement("type",new Rectangle2D.Float(10, 10, 120, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,"","type"));

        int height = 30;
        header.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FirstLevelColor, null,
                new Rectangle2D.Float(0, height, getReportWidth(), 18), false, true));
        header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(0, height, 90, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("trailers.withoutchl.plate")));
        int x = 90;
        for(int i = 0; i < labels.length; i++) {
            header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 90, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(labels[i])));
            x += 90;
        }
        header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 90, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.country")));
        x += 90;
        header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 90, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("trailers.withoutchl.trade")));
        x += 90;
        header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 90, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("trailers.withoutchl.model")));
        x += 90;
        header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 90, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("trailers.withoutch.lastcheck")));
        
        group.setHeader(header);
        
        GroupFooter footer = new GroupFooter();
        footer.setName("typeGroupFooter");
        
        footer.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, null,
                new Rectangle2D.Float(0, 10, getReportWidth(), 15), false, true));
        footer.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(5, 10, 160, 15),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,messages.getText("query.driver.driverdata.total")));
        footer.addElement(NumberFieldElementFactory.createNumberElement(null,new Rectangle2D.Float(width-90, 10, 90, 15),
                Color.WHITE,ElementAlignment.RIGHT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,"", formatNoDecimals,"total"));

        group.setFooter(footer);
        
        grouplist.add(group);
        
        return grouplist;
    }

    protected ItemBand createItemBand() {
        ItemBand items = super.createItemBand();        
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,
                new Rectangle2D.Float(0, 0, getReportWidth(), 15), false, true));
        items.addElement(TextFieldElementFactory.createStringElement("plate",new Rectangle2D.Float(0, 0, 90, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","plate"));
        int x = 90;
        for(int i = 1; i <= labels.length; i++) {
            items.addElement(TextFieldElementFactory.createStringElement("divOrg"+i+"Name",new Rectangle2D.Float(x, 0, 90, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","divOrg"+i+"Name"));
            x += 90;
        }
        items.addElement(TextFieldElementFactory.createStringElement("country",new Rectangle2D.Float(x, 0, 90, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","country"));        
        x += 90;
        items.addElement(TextFieldElementFactory.createStringElement("trade",new Rectangle2D.Float(x, 0, 90, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","trade"));        
        x += 90;
        items.addElement(TextFieldElementFactory.createStringElement("model",new Rectangle2D.Float(x, 0, 90, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","model"));        
        x += 90;
        items.addElement(TextFieldElementFactory.createStringElement("last",new Rectangle2D.Float(x, 0, 90, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","last"));        
        return items;
    }
    
    protected ReportHeader createReportHeader() {
        
        ReportHeader reportHeader = new ReportHeader();
        
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,
                new Rectangle2D.Float(0, 0, getReportWidth(), 25), false, true));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(0, 0, getReportWidth(), 25),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("query.vehicleswochl.title")));

        int height = 43;

        height = writeParametersTitle(messages.getText("query.mileage.parameters"),reportHeader,height,10,260);
        height = writeParameter(messages.getText("query.mileage.from")+":",((TrailersWOCheckListForm)form).getFrom(messages),reportHeader,height,10,90,100,160);
        height = writeParameter(messages.getText("query.mileage.to")+":",((TrailersWOCheckListForm)form).getTo(messages),reportHeader,height,10,90,100,160);

        int statusId = Integer.parseInt(((TrailersWOCheckListForm)form).getStatus()); 
        String status = (statusId == VehicleData.ACTIVE)? messages.getText("generic.messages.active"): (statusId == VehicleData.EXCLUDED) ? messages.getText("generic.messages.excluded"): messages.getText("generic.messages.all.pl");
        height = writeParameter(messages.getText("generic.activity.driverdata.status")+":",status,reportHeader,height,10,90,100,160);

        height = writeParametersTitle(messages.getText("generic.fleet.divgeo"),reportHeader,height,10,260); 
        height = writeParameter(divGeoLabels[0]+":",divGeoValues[0],reportHeader,height,10,90,100,160);
        height = writeParameter(divGeoLabels[1]+":",divGeoValues[1],reportHeader,height,10,90,100,160);

        int maxHeight = height;
        
        height = 43;
        String[] divOrgs = sys.getUserSessionData().getFilter().getLevelLabels();
        for(int i = 0; i < divOrgs.length; i++) {
            if(!Util.empty(divOrgs[i])) {
                height = writeParametersTitle(messages.getText(divOrgs[i]),reportHeader,height,270,230);
                for(int j = 0; j < 3; j++) {
                    if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 1 && j== 0) && 
                            !(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 2 && j== 0)) {
                        if(!Util.empty(divOrgLabels[i][j])) {
                            height = writeParameter(divOrgLabels[i][j]+":",divOrgValues[i][j],reportHeader,height,270,80,350,150);
                        }
                    }
                }
            }
        }
        
        height = Math.max(maxHeight,height);
        
        return reportHeader;
    }

    protected ReportFooter createReportFooter() {
        ReportFooter reportFooter = new ReportFooter();
        
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
