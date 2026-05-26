package presentation.report.activity;

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
import org.jfree.report.elementfactory.StaticShapeElementFactory;
import org.jfree.report.elementfactory.TextFieldElementFactory;
import org.jfree.report.function.ElementColorFunction;
import org.jfree.report.function.ExpressionCollection;
import org.jfree.report.style.FontDefinition;

import presentation.report.ReportFactory;
import business.SystemAccess;

import communication.language.Text;

public class HeavyReportFactory extends ReportFactory {

    protected Text messages;
    
    protected static final FontDefinition STYLE_TITLE= new FontDefinition("SansSerif",13,true,false,false,false);
    protected static final FontDefinition STYLE_SIMPLE_FONT= new FontDefinition("SansSerif",8);
    protected static final FontDefinition STYLE_SIMPLE_BOLD_FONT= new FontDefinition("SansSerif",8,true,false,false,false);
    protected static final FontDefinition STYLE_TOTAL_FONT= new FontDefinition("SansSerif",12,true,false,false,false);
    protected static final FontDefinition STYLE_TITLE_PARAM_FONT= new FontDefinition("SansSerif",9,true,false,true,false);
    
    public HeavyReportFactory(TableModel model, ActionForm form, SystemAccess sys, Text messages) {
        super("", model, form);
        this.messages = messages;
    }

    protected int getWidth() {
    	return 580;
    }
    
    protected int getReportWidth() {
    	return 495;
    }

    protected GroupList createGroups() {
        GroupList grouplist = super.createGroups();
        
        Group group = new Group();
        group.setName("point");
        group.addField("point");

        GroupHeader header = new GroupHeader();
        header.setName("header");
        header.addElement(TextFieldElementFactory.createStringElement("point",new Rectangle2D.Float(5, 15, getReportWidth()-5, 10),DarkBlue,
                ElementAlignment.LEFT,ElementAlignment.BOTTOM,STYLE_SIMPLE_BOLD_FONT,"","point"));

        header.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, groupsHeaderColors[3], null,
                new Rectangle2D.Float(5, 30, getReportWidth()-5, 15), false, true));
        header.addElement(LabelElementFactory.createLabelElement("yes",
                new Rectangle2D.Float(250, 30, 70, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("generic.activity.form.commentarydriveother.optionyes")));        
        header.addElement(LabelElementFactory.createLabelElement("no",
                new Rectangle2D.Float(320, 30, 70, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("generic.activity.form.commentarydriveother.optionno")));        
        header.addElement(LabelElementFactory.createLabelElement("must",
                new Rectangle2D.Float(390, 30, 105, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("generic.activity.form.commentarydriveother.requirespursuit")));        
        
        GroupFooter footer = new GroupFooter();
        footer.setName("footer");

        group.setHeader(header);
        group.setFooter(footer);
        grouplist.add(group);
        
        return grouplist;
    }
    
    protected ItemBand createItemBand() {
        ItemBand items = super.createItemBand();
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,new Rectangle2D.Float(0, 0, getReportWidth(), 15), false, true));
        items.addElement(TextFieldElementFactory.createStringElement("item",new Rectangle2D.Float(10, 0, 240, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","item"));        
        items.addElement(TextFieldElementFactory.createStringElement("yes",new Rectangle2D.Float(250, 0, 70, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","yes"));        
        items.addElement(TextFieldElementFactory.createStringElement("no",new Rectangle2D.Float(320, 0, 70, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","no"));        
        items.addElement(TextFieldElementFactory.createStringElement("must",new Rectangle2D.Float(390, 0, 105, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","must"));        
        return items;
    }
    
    protected ReportHeader createReportHeader() {
        ReportHeader reportHeader = new ReportHeader();
        
        reportHeader.addElement(org.jfree.report.elementfactory.ImageFieldElementFactory.
                createImageDataRowElement("logoCepa", new Rectangle2D.Float(0, 0, 50, 45), "logoCepa"));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(70, 10, getReportWidth()-70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("query.activity.generalreport.reporttitle"))); 
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(70, 30, getReportWidth()-70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("query.activity.generalreport.reportsubtitle")));
        
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, DarkBlue, null,
                new Rectangle2D.Float(0, 60, getReportWidth()-200, 20), false, true));        
        reportHeader.addElement(TextFieldElementFactory.createStringElement("activity", new Rectangle2D.Float(5, 60, getReportWidth()-195, 20),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"","activity"));

        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, DarkBlue, null,
                new Rectangle2D.Float(getReportWidth()-195, 60, 85, 20), false, true));        
        reportHeader.addElement(TextFieldElementFactory.createStringElement("date",new Rectangle2D.Float(getReportWidth()-190, 60, 80, 20),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT," ","date"));
       
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, DarkBlue, null,
                new Rectangle2D.Float(0, 85, getReportWidth()-110, 20), false, true));        
        reportHeader.addElement(TextFieldElementFactory.createStringElement("instructor",new Rectangle2D.Float(5, 85, getReportWidth()-105, 20),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"","instructor"));        

        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, DarkBlue, null,
                new Rectangle2D.Float(getReportWidth()-105, 60, 105, 45), false, true));        
        reportHeader.addElement(TextFieldElementFactory.createStringElement("country",new Rectangle2D.Float(getReportWidth()-100, 60,100 , 20),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"","country"));        
        reportHeader.addElement(TextFieldElementFactory.createStringElement("city",new Rectangle2D.Float(getReportWidth()-100, 85, 100, 20),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"","city"));        
        
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement
                (null, Color.LIGHT_GRAY, null,new Rectangle2D.Float(0, 110, getReportWidth(), 20), false, true)); 
        reportHeader.addElement(TextFieldElementFactory.createStringElement("driver",new Rectangle2D.Float(5, 110, getReportWidth()-5, 20),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"","driver"));        
        
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement
                (null, DarkBlue , null,new Rectangle2D.Float(0, 135, getReportWidth(), 20), false, true));
        reportHeader.addElement(TextFieldElementFactory.createStringElement("note",new Rectangle2D.Float(5, 135, getReportWidth()-5, 20),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"","note"));        

        
        return reportHeader;
    }
       
    protected ReportFooter createReportFooter() {
        ReportFooter reportFooter = new ReportFooter();
        
       reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, Color.LIGHT_GRAY, null,
               new Rectangle2D.Float(0, 30, getReportWidth(), 570), false, true));
       reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 30, getReportWidth()-10, 15),
               Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
               " "+messages.getText("generic.activity.observations")+": "));
        reportFooter.addElement(TextFieldElementFactory.createStringElement("observations",new 
        		Rectangle2D.Float(10, 50, getReportWidth()-10, 550),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.TOP,STYLE_SIMPLE_FONT,"","observations"));        

        return reportFooter;
    }

    protected ExpressionCollection createFunctions() {
        ExpressionCollection functions = super.createFunctions();
        
        final ElementColorFunction color = new ElementColorFunction();
        color.setName("changeColor");
        color.setElement("Variance Field");
        color.setField("color");
        color.setColorTrue(Color.WHITE);
        color.setColorFalse(DCGray2);
        functions.add(color);
        
        return functions;
    }
}
