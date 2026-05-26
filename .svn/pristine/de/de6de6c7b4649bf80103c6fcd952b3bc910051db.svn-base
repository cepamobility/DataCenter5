package presentation.report.reporterjj;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;

import javax.swing.table.TableModel;

import org.apache.struts.action.ActionForm;
import org.jfree.report.Element;
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

import communication.administration.corporation.tables.CorporationData;
import communication.administration.user.UserSessionData;
import communication.driver.tables.DriverData;
import communication.language.Text;

public class CommentaryDriveReportFactory extends ReportFactory {

    protected Text messages;
    protected SystemAccess sys;
	
    private Integer activity;
	private DriverData driver;
	private UserSessionData userSessionData;
	private String instructor;
	
    public CommentaryDriveReportFactory(String name, TableModel model, ActionForm form, SystemAccess sys, Integer activity, String instructor) throws Exception {
        super(name, model, form);
    
        this.sys = sys;
        this.messages = sys.getText();
        userSessionData = sys.getUserSessionData();
        this.activity = activity;
        this.driver = userSessionData.getDriverData();
        this.instructor = instructor;
    }

    protected int getWidth() {
        return 682;
    }

    protected int getReportWidth() {
        return 600;
    }
    
    protected GroupList createGroups() {
        GroupList grouplist = super.createGroups();

    	Group group = new Group();
    	group.setName("Point");
    	group.addField("point");

    	GroupHeader header = new GroupHeader();
        header.setName("header");
        header.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, null,
        		new Rectangle2D.Float(10, 10, getReportWidth(), 15), false, true));
        header.addElement(TextFieldElementFactory.createStringElement("point",new Rectangle2D.Float(10, 10, getReportWidth(), 15),Color.WHITE,
                ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"","point"));

        GroupFooter footer = new GroupFooter();
        footer.setName("footer");
        footer.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, 200, 15),
        		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.messages.observations")));
        footer.addElement(TextFieldElementFactory.createStringElement("obs1",new Rectangle2D.Float(10, 30, getReportWidth(), 35),Color.BLACK,
                ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","obs1"));

        group.setHeader(header);
        group.setFooter(footer);

        grouplist.add(group);
        
        return grouplist;
    }

    protected ReportHeader createReportHeader() {
        ReportHeader reportHeader = new ReportHeader();
        
		reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, getReportWidth()-200, 25),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("scheduledactivitytype.reporterjj.commentarydrive")));
       
    	reportHeader.addElement(org.jfree.report.elementfactory.ImageFieldElementFactory.
     			createImageDataRowElement("logoCepa", new Rectangle2D.Float(getReportWidth()-150, 0, 60, 45), "logoCepa"));
		reportHeader.addElement(org.jfree.report.elementfactory.ImageFieldElementFactory.
     			createImageDataRowElement("logoSafeFleetLA", new Rectangle2D.Float(getReportWidth()-70, 0, 47, 45), "logoSafeFleetLA"));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 30, 60, 18), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.firstname")+":"));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(70, 30, 300, 18), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,driver.getFirstName()+" "+driver.getLastName()));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 50, 60, 18), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.wwid")+":"));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(70, 50, 300, 18), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,driver.getCorporationId()));
        
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 70, 60, 18), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.activity.date")+":"));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(70, 70, 300, 18), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,Util.formatDate(Calendar.getInstance().getTime())));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 90, 60, 18), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.activity.gerente")+":"));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(70, 90, 300, 18), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,instructor));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 110, getReportWidth(), 40), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_REPORTEJJ_IT,messages.getText("reporterjj.commentarydrive.text1")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 150, getReportWidth(), 40), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_REPORTEJJ_IT,messages.getText("reporterjj.commentarydrive.text2")));

        return reportHeader;
    }

    protected ItemBand createItemBand() {
        ItemBand items = super.createItemBand();
    
        items.addElement(TextFieldElementFactory.createStringElement("item",new Rectangle2D.Float(10, 0, getReportWidth()-120, 18),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","item"));
        items.addElement(TextFieldElementFactory.createStringElement("Variance Field",new Rectangle2D.Float(getReportWidth()-110, 0, 120, 18),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","result"));

        return items;
    }
    
    protected ReportFooter createReportFooter() {
        ReportFooter reportFooter = new ReportFooter();

        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, getReportWidth(), 20), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.BOTTOM,STYLE_TITLE,messages.getText("generic.messages.observations")));					
        reportFooter.addElement(TextFieldElementFactory.createStringElement("obs2",new Rectangle2D.Float(10, 30, getReportWidth(), 50),
        		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","obs2"));
        return reportFooter;
    }

    protected ExpressionCollection createFunctions() {
        ExpressionCollection functions = super.createFunctions();
        
        final ElementColorFunction color = new ElementColorFunction();
        color.setName("changeColor");
        color.setElement("Variance Field");
        color.setField("color");
        color.setColorTrue(Color.GREEN);
        color.setColorFalse(Color.RED);
        functions.add(color);
        
        return functions;
    }
}
