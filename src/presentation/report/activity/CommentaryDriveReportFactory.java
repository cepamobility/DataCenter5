/**
 * Created on 10-jul-2006
 * CEPA
 * DataCenter 5
 */
package presentation.report.activity;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.TableModel;

import org.apache.struts.action.ActionForm;
import org.jfree.report.ElementAlignment;
import org.jfree.report.ItemBand;
import org.jfree.report.ReportFooter;
import org.jfree.report.ReportHeader;
import org.jfree.report.elementfactory.DateFieldElementFactory;
import org.jfree.report.elementfactory.LabelElementFactory;
import org.jfree.report.elementfactory.StaticShapeElementFactory;
import org.jfree.report.elementfactory.TextFieldElementFactory;
import org.jfree.report.function.ElementColorFunction;
import org.jfree.report.function.ExpressionCollection;

import presentation.report.ReportFactory;
import presentation.translator.web.activity.schedule.CommentaryDriveReportForm;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.activity.tables.ItemData;
import communication.activity.tables.PointData;
import communication.administration.corporation.tables.CorporationData;
import communication.driver.tables.DriverData;
import communication.language.Text;

public class CommentaryDriveReportFactory extends ReportFactory {

    protected Text messages;
    private SystemAccess sys;
    private Collection points;
    private int width = 0;
    private int[][] principal;
    private int corporationId;
    
    public CommentaryDriveReportFactory(String name, TableModel model, ActionForm form, Collection points, SystemAccess sys, int items) {
        super(name, model, form);
        this.sys = sys;
        this.messages = sys.getText();
        this.points = points;
        principal = sys.getUserSessionData().getFilter().getPrincipalDivOrgs();
        parseDivisionValues(sys,messages);
        corporationId = sys.getUserSessionData().getFilter().getCorporationId().intValue();
        width = (corporationId == CorporationData.MONSANTO) ? 900 : 680; 
        width += items * 20;
        for(int i = 0; i < principal.length; i++) {
            if(principal[i][0] != 0) {
                width += 100;
            }
        } 
    }

    protected int getWidth() {
        return width+82;
    }

    protected int getReportWidth() {
    	return width ;
    }
    
    protected ItemBand createItemBand() {
        
        ItemBand items = super.createItemBand();        
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,
                new Rectangle2D.Float(0, 0, getReportWidth(), 15), false, true));
        items.addElement(DateFieldElementFactory.createDateElement("activityDate",new Rectangle2D.Float(0, 0, 60, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",DATE_FORMAT, "activityDate"));
        items.addElement(TextFieldElementFactory.createStringElement("type",new Rectangle2D.Float(60, 0, 100, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","type"));
        items.addElement(TextFieldElementFactory.createStringElement("driver",new Rectangle2D.Float(160, 0, 120, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","firstname"));
        items.addElement(TextFieldElementFactory.createStringElement("driverlastname",new Rectangle2D.Float(280, 0, 120, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","lastname"));
        items.addElement(TextFieldElementFactory.createStringElement("country",new Rectangle2D.Float(400, 0, 80, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","country"));
        int x = 480;
        for(int i = 0; i < principal.length; i++) {
            if(principal[i][0] != 0) {
                items.addElement(TextFieldElementFactory.createStringElement("divorg"+String.valueOf(i+1),new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,
                        ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","divorg"+String.valueOf(i+1)));
                x += 100;
            }
        } 
        if(corporationId == CorporationData.MONSANTO) {
            items.addElement(TextFieldElementFactory.createStringElement("allowed",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,
                    ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","allowed"));
            x += 100;
            items.addElement(TextFieldElementFactory.createStringElement("licenseObs",new Rectangle2D.Float(x, 0, 120, 15),Color.BLACK,
                    ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","licenseObs"));
            x += 120;
        }
        Collections.sort((List)points);
        Iterator it = points.iterator();
        while(it.hasNext()) {
            PointData point = (PointData)it.next();
            Collection pointItems = point.getItems();
            Collections.sort((List)pointItems);
            Iterator itemIt = pointItems.iterator();
            while(itemIt.hasNext()) {
                ItemData item = (ItemData)itemIt.next();
                items.addElement(TextFieldElementFactory.createStringElement(String.valueOf(item.getId()),new Rectangle2D.Float(x, 0, 20, 15),Color.BLACK,
                        ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",String.valueOf(item.getId())));
                x += 20;
            }
        }
        items.addElement(TextFieldElementFactory.createStringElement("observations",new Rectangle2D.Float(x, 0, 200, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","observations"));
        return items;
    }
    
    protected ReportHeader createReportHeader() {
        
        ReportHeader reportHeader = new ReportHeader();
        CommentaryDriveReportForm reportForm = (CommentaryDriveReportForm)form;
        
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,
                new Rectangle2D.Float(0, 0, getReportWidth(), 25), false, true));
        
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(0, 0, getReportWidth(), 25),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("query.commentarydrive.title")));

        int height = 40;

        height = writeParametersTitle(messages.getText("query.commentarydrive.parameters"),reportHeader,height,10,180);
        height = writeParameter(messages.getText("generic.driver.driverdata.firstname")+":",reportForm.getFirstName(),reportHeader,height,10,60,70,120);
        height = writeParameter(messages.getText("generic.driver.driverdata.lastname")+":",reportForm.getLastName(),reportHeader,height,10,60,70,120);
        switch(Integer.parseInt(reportForm.getStatus())) {
            case DriverData.ACTIVE:
                height = writeParameter(messages.getText("generic.event.eventdata.status")+":",messages.getText("generic.messages.active"),reportHeader,height,10,60,70,120);
                break;
            case DriverData.EXCLUDED:
                height = writeParameter(messages.getText("generic.event.eventdata.status")+":",messages.getText("generic.messages.excluded"),reportHeader,height,10,60,70,120);
                break;
            case DriverData.ALL:
                height = writeParameter(messages.getText("generic.event.eventdata.status")+":",messages.getText("generic.messages.all.pl"),reportHeader,height,10,60,70,120);
                break;
        }
        if(corporationId == CorporationData.MONSANTO) {
            String allowed = (reportForm.getVehicleAllowed() != null && reportForm.getVehicleAllowed().length() == 0) ? "generic.messages.all" : reportForm.getVehicleAllowed();
            height = writeParameter(messages.getText("generic.driver.driverdata.vehicleallowed")+":",messages.getText(allowed),reportHeader,height,10,60,70,100);
        }
        height = writeParameter(messages.getText("query.mileage.from")+":",reportForm.getFrom(messages),reportHeader,height,10,60,70,120);
        height = writeParameter(messages.getText("query.mileage.to")+":",reportForm.getTo(messages),reportHeader,height,10,60,70,120);

        height = writeParametersTitle(messages.getText("generic.fleet.divgeo"),reportHeader,height,10,170); 
        height = writeParameter(divGeoLabels[0]+":",divGeoValues[0],reportHeader,height,10,60,70,120);
        height = writeParameter(divGeoLabels[1]+":",divGeoValues[1],reportHeader,height,10,60,70,120);

        int maxHeight = height;
        
        height = 40;
        String[] divOrgs = sys.getUserSessionData().getFilter().getLevelLabels();
        for(int i = 0; i < divOrgs.length; i++) {
            if(!Util.empty(divOrgs[i])) {
                height = writeParametersTitle(messages.getText(divOrgs[i]),reportHeader,height,300,190);
                for(int j = 0; j < 3; j++) {
                    if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 1 && j== 0) && 
                            !(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 2 && j== 0)) {
                        if(!Util.empty(divOrgLabels[i][j])) {
                            height = writeParameter(divOrgLabels[i][j]+":",divOrgValues[i][j],reportHeader,height,300,70,370,120);
                        }
                    }
                }
            }
        }
        
        int titleHeight = Math.max(height,maxHeight);
        
        height = titleHeight + 18;
        
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FirstLevelColor, null,
                new Rectangle2D.Float(0, height, getReportWidth(), 18), false, true));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(0, height, 60, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.commentarydrive.activitydate")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(60, height, 100, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.commentarydrive.type")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(160, height, 120, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.firstname")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(280, height, 120, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.lastname")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(400, height, 80, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.country")));
        int x = 480;
        for(int i = 0; i < principal.length; i++) {
            if(principal[i][0] != 0) {
                reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 100, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,divOrgLabels[principal[i][0]-1][principal[i][1]-1]));
                x += 100;
            }
        }
        if(corporationId == CorporationData.MONSANTO) {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 100, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.vehicleallowed")));
            x += 100;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 120, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.licenseobs")));
            x += 120;
        }
        
        Collections.sort((List)points);
        Iterator it = points.iterator();
        while(it.hasNext()) {
            PointData point = (PointData)it.next();
            reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, SecondLevelColor, null,
                    new Rectangle2D.Float(x, titleHeight, 20*point.getItems().size(), 18), false, true));
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, titleHeight, 20*point.getItems().size(), 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(point.getText())));
            Collection pointItems = point.getItems();
            Collections.sort((List)pointItems);
            Iterator itemIt = pointItems.iterator();
            int i = 1;
            while(itemIt.hasNext()) {
                itemIt.next();
                reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 20, 18),
                        Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(i)));
                i++;
                x += 20;
            }
        }
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 200, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.commentarydrive.observations")));
        
        return reportHeader;
    }

    protected ReportFooter createReportFooter() {
        ReportFooter footer = super.createReportFooter();
        Collections.sort((List)points);
        Iterator it = points.iterator();
        int y = 10;
        while(it.hasNext()) {
            PointData point = (PointData)it.next();
            footer.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(0, y, getReportWidth(), 18),
                    Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(point.getText())));
            y += 18;
            Collection pointItems = point.getItems();
            Collections.sort((List)pointItems);
            Iterator itemIt = pointItems.iterator();
            int i = 1;
            while(itemIt.hasNext()) {
                ItemData item = (ItemData)itemIt.next();
                footer.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(0, y, getReportWidth(), 18),
                        Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,i + " " + messages.getText(item.getText())));
                y += 18;
                i++;
            }
            y += 18;
        }
        return footer;
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
