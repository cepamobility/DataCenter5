/**
 * Created on 10-jul-2006
 * CEPA
 * DataCenter 5
 */
package presentation.report.activity;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
import java.util.LinkedList;

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
import org.jfree.report.elementfactory.DateFieldElementFactory;
import org.jfree.report.elementfactory.LabelElementFactory;
import org.jfree.report.elementfactory.StaticShapeElementFactory;
import org.jfree.report.elementfactory.TextFieldElementFactory;
import org.jfree.report.function.ElementColorFunction;
import org.jfree.report.function.ExpressionCollection;

import presentation.report.ReportFactory;
import presentation.translator.web.activity.schedule.ReportCommentaryDriveLogForm;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.language.Text;

public class ReportCommentaryDriverLogFactory extends ReportFactory {

    protected Text messages;
    
    private SystemAccess sys;
    private int corporationId;
    private int type;
    
    public ReportCommentaryDriverLogFactory(String name, TableModel model, ActionForm form, SystemAccess sys, Text messages, boolean html) {
        super(name, model, form);
        this.messages = messages;
        parseDivisionValues(sys,messages);
        this.sys = sys;
        this.corporationId = sys.getUserSessionData().getFilter().getCorporationId().intValue();
        type = (html) ? 1 : 0;
    }


    protected int getWidth() {
        return (corporationId != 4) ? 750 : 680;
    }

//  it is declared but it is not used 
    protected int getReportWidth() {
    	return (corporationId != 4) ? 660 : 590;
    }
    
    protected GroupList createGroups() {
        
        GroupList grouplist = super.createGroups();
        
        Collection fields = new LinkedList();
        String[] levelNames = sys.getUserSessionData().getFilter().getLevelNames(messages);
        for(int i = 1; i <= levelNames.length; i++) {
            int order = getOrder(i);
            if(getOrder(i) != 4) {
                count = i+1;
                String totalName = levelNames[order];
                fields.add("div"+i+"Id");
                
                Group group = new Group();
                group.setName("Group"+i);
                int level;
                String[] f = (String[])fields.toArray(new String[0]);
                for(level=0;level<f.length;level++){
                    group.addField(f[level]);
                }
                GroupHeader header = new GroupHeader();
                header.setName("header");
                header.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, groupsHeaderColors[i-1], null,
                        new Rectangle2D.Float(10*type, 10, getReportWidth(), 25), false, true));
                header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10*type, 10, 60, 25),Color.WHITE,
                    ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,messages.getText(totalName)+": "));
                header.addElement(TextFieldElementFactory.createStringElement("div"+i+"Name",new Rectangle2D.Float(10*type+60, 10, 120, 25),Color.WHITE,
                        ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"","div"+i+"Name"));
                
                if(getOrder(i+1) == 4) {
                    header.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, null,
                            new Rectangle2D.Float(10*type, 55, getReportWidth(), 18), false, true));
                    header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10*type, 55, 60, 18),
                            Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.cdlog.activitydate")));
                    header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10*type+60, 55, 120, 18),
                            Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.firstname")));
                    header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10*type+180, 55, 120, 18),
                            Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.lastname")));
                    header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10*type+300, 55, 70, 18),
                            Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.birthdate")));
                    header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10*type+370, 55, 120, 18),
                            Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.cdlog.loguser")));
                    header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10*type+490, 55, 70, 18),
                            Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.cdlog.logdate")));
                }
                GroupFooter footer = new GroupFooter();
                footer.setName("footer");
                
                group.setHeader(header);
                group.setFooter(footer);
                grouplist.add(group);
            }
        }
               
        return grouplist;
    }

    public int getOrder(int i) {
        switch(i) {
            case 1:
                return Integer.parseInt(((ReportCommentaryDriveLogForm)form).getOrder1());
            case 2:
                return Integer.parseInt(((ReportCommentaryDriveLogForm)form).getOrder2());
            case 3:
                return Integer.parseInt(((ReportCommentaryDriveLogForm)form).getOrder3());
            case 4:
                return Integer.parseInt(((ReportCommentaryDriveLogForm)form).getOrder4());
        }
        return 4;
    }

    protected ItemBand createItemBand() {
        
        ItemBand items = super.createItemBand();        
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,
                new Rectangle2D.Float(10*type, 0, getReportWidth(), 15), false, true));
        items.addElement(DateFieldElementFactory.createDateElement("activityDate",new Rectangle2D.Float(10*type, 0, 60, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",DATE_FORMAT, "activityDate"));
        items.addElement(TextFieldElementFactory.createStringElement("driver",new Rectangle2D.Float(10*type+60, 0, 120, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","driverfirstname"));
        items.addElement(TextFieldElementFactory.createStringElement("driverlastname",new Rectangle2D.Float(10*type+180, 0, 120, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","driverlastname"));
        items.addElement(DateFieldElementFactory.createDateElement("driverBirth",new Rectangle2D.Float(10*type+300, 0, 70, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",DATE_FORMAT,"driverBirth"));
        items.addElement(TextFieldElementFactory.createStringElement("driverUser",new Rectangle2D.Float(10*type+370, 0, 120, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","driverUser"));
        items.addElement(DateFieldElementFactory.createDateElement("driverDate",new Rectangle2D.Float(10*type+490, 0, 70, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",DATE_FORMAT,"driverDate"));
        return items;
    }
    
    protected ReportHeader createReportHeader() {
        
        ReportHeader reportHeader = new ReportHeader();
        
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,
                new Rectangle2D.Float(10*type, 0, getReportWidth(), 25), false, true));        
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10*type, 0, getReportWidth(), 25),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("generic.query.commentarydrivelog")));

        int height = 40;

        height = writeParametersTitle(messages.getText("query.mileage.parameters"),reportHeader,height,10*type,180);
        height = writeParameter(messages.getText("query.mileage.from")+":",((ReportCommentaryDriveLogForm)form).getFrom(messages),reportHeader,height,10*type,60,10*type+60,120);
        height = writeParameter(messages.getText("query.mileage.to")+":",((ReportCommentaryDriveLogForm)form).getTo(messages),reportHeader,height,10*type,60,10*type+60,120);

        height = writeParametersTitle(messages.getText("generic.fleet.divgeo"),reportHeader,height,10*type,170); 
        height = writeParameter(divGeoLabels[0]+":",divGeoValues[0],reportHeader,height,10*type,60,10*type+60,120);
        height = writeParameter(divGeoLabels[1]+":",divGeoValues[1],reportHeader,height,10*type,60,10*type+60,120);

        int maxHeight = height;
        
        height = 40;
        String[] divOrgs = sys.getUserSessionData().getFilter().getLevelLabels();
        for(int i = 0; i < divOrgs.length; i++) {
            if(!Util.empty(divOrgs[i])) {
                height = writeParametersTitle(messages.getText(divOrgs[i]),reportHeader,height,10*type+300,190);
                for(int j = 0; j < 3; j++) {
                    if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 1 && j== 0) && 
                            !(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 2 && j== 0)) {
                        if(!Util.empty(divOrgLabels[i][j])) {
                            height = writeParameter(divOrgLabels[i][j]+":",divOrgValues[i][j],reportHeader,height,10*type+300,70,10*type+370,120);
                        }
                    }
                }
            }
        }
        
        height = Math.max(height,maxHeight);
        
        if(getOrder(1) == 4) {
            reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FirstLevelColor, null,
                    new Rectangle2D.Float(10*type, height, getReportWidth(), 18), false, true));
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10*type, height, 60, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.cdlog.activitydate")));
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10*type+60, height, 120, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.firstname")));
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10*type+180, height, 120, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.lastname")));
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10*type+300, height, 70, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.birthdate")));
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10*type+370, height, 120, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.cdlog.loguser")));
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10*type+490, height, 70, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.cdlog.logdate")));
        }
        
        return reportHeader;
    }

    protected ReportFooter createReportFooter() {
        return super.createReportFooter();
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
