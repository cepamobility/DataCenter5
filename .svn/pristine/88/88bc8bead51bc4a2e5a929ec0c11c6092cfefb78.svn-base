/**
 * Created on 10-jul-2006
 * CEPA
 * DataCenter 5
 */
package presentation.report.queries;

import java.awt.Color;
import java.awt.geom.Point2D;
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
import org.jfree.report.elementfactory.DrawableFieldElementFactory;
import org.jfree.report.elementfactory.LabelElementFactory;
import org.jfree.report.elementfactory.NumberFieldElementFactory;
import org.jfree.report.elementfactory.StaticShapeElementFactory;
import org.jfree.report.elementfactory.TextFieldElementFactory;
import org.jfree.report.function.ElementColorFunction;
import org.jfree.report.function.ExpressionCollection;
import org.jfree.report.function.ItemSumFunction;
import org.jfree.ui.FloatDimension;

import presentation.report.ReportFactory;
import presentation.translator.web.queries.ReportFineForm;
import business.SystemAccess;

import communication.language.Text;

public class ReportFineTypeFactory extends ReportFactory {

    protected Text messages;
    
    private boolean unitsChart;
    private boolean divisionsChart;
    private boolean subdivisionsChart;

    public ReportFineTypeFactory(String name, TableModel model, ActionForm form, SystemAccess sys, Text messages) {
        super(name, model, form);
        this.messages = messages;
        parseDivisionValues(sys,messages);
    }

    protected int getWidth() {
        return 600;
    }
    
//  it is declared but it is not used 
    protected int getReportWidth() {
    	return 0;
    }
    
    public void setDivisionsChart(boolean divisionsChart) {
        this.divisionsChart = divisionsChart;
    }

    public void setSubdivisionsChart(boolean subdivisionsChart) {
        this.subdivisionsChart = subdivisionsChart;
    }

    protected GroupList createGroups() {
        GroupList grouplist = super.createGroups();

        Group chartGroup = new Group();
        chartGroup.setName("charts");
        chartGroup.addField("id");

        GroupHeader chartHeader = new GroupHeader();
        chartHeader.setName("chartHeader");

        GroupFooter chartFooter = new GroupFooter();
        chartFooter.setName("chartFooter");

        chartFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, DCGray1, null,
                new Rectangle2D.Float(10, 10, 450, 25), false, true));
        chartFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 100, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,messages.getText("query.accidentcount.totals")));
        chartFooter.addElement(NumberFieldElementFactory.createNumberElement("total3",new Rectangle2D.Float(320, 10, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,"","#####","total3"));
        chartFooter.addElement(NumberFieldElementFactory.createNumberElement("total6",new Rectangle2D.Float(390, 10, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,"","#####","total6"));

        chartGroup.setHeader(chartHeader);
        chartGroup.setFooter(chartFooter);
        
        grouplist.add(chartGroup);
        
        Group unitGroup = new Group();
        unitGroup.setName("units");
        unitGroup.addField("id");
        unitGroup.addField("unit");

        GroupHeader unitHeader = new GroupHeader();
        unitHeader.setName("unitHeader");

        unitHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, DCGray1, null,
                new Rectangle2D.Float(10, 10, 450, 15), false, true));
        unitHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 70, 15),Color.BLACK,
                ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.accidentcount.unit")+": "));
        unitHeader.addElement(TextFieldElementFactory.createStringElement("unit",new Rectangle2D.Float(80, 10, 200, 15),Color.BLACK,
                ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","unit"));
        
        
        GroupFooter unitFooter = new GroupFooter();
        unitFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, DCGray1, null,
                new Rectangle2D.Float(10, 10, 450, 15), false, true));
        unitFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 40, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.accidentcount.total")));
        unitFooter.addElement(TextFieldElementFactory.createStringElement("unit",new Rectangle2D.Float(50, 10, 150, 15),Color.BLACK,
                ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"","unit"));
        unitFooter.addElement(NumberFieldElementFactory.createNumberElement("total2",new Rectangle2D.Float(320, 10, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","total2"));
        unitFooter.addElement(NumberFieldElementFactory.createNumberElement("total5",new Rectangle2D.Float(390, 10, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","total5"));

        unitGroup.setHeader(unitHeader);
        unitGroup.setFooter(unitFooter);
        grouplist.add(unitGroup);
        
        Group divisionGroup = new Group();
        divisionGroup.setName("divisions");
        divisionGroup.addField("id");
        divisionGroup.addField("unit");
        divisionGroup.addField("division");

        GroupHeader divisionHeader = new GroupHeader();
        divisionHeader.setName("divisionHeader");
        
        divisionHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, DCGray3, null,
                new Rectangle2D.Float(10, 10, 450, 15), false, true));
        divisionHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 70, 15),Color.BLACK,
                ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.accidentcount.division")+": "));
        divisionHeader.addElement(TextFieldElementFactory.createStringElement("division",new Rectangle2D.Float(90, 10, 200, 15),Color.BLACK,
                ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","division"));
        
        
        divisionHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, gray1, null,new Rectangle2D.Float(10, 25, 150, 18), false, true));
        divisionHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 25, 150, 18),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("query.fine.subdivision")));

        divisionHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, gray2, null,new Rectangle2D.Float(160, 25, 160, 18), false, true));
        divisionHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(160, 25, 160, 18),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("query.fine.type")));

                divisionHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, gray1, null,new Rectangle2D.Float(320, 25, 70, 18), false, true));
        divisionHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(320, 25, 70, 18),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("query.fine.type.count")));

        divisionHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, gray2, null,new Rectangle2D.Float(390, 25, 70, 18), false, true));
        divisionHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(390, 25, 70, 18),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("query.fine.type.cost")));

        GroupFooter divisionFooter = new GroupFooter();
        divisionFooter.setName("divisionFooter");
        
        divisionFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, DCGray3, null,
                new Rectangle2D.Float(10, 0, 450, 15), false, true));
        divisionFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, 100, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.accidentcount.totals")));
        divisionFooter.addElement(NumberFieldElementFactory.createNumberElement("total1",new Rectangle2D.Float(320, 0, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","total1"));
        divisionFooter.addElement(NumberFieldElementFactory.createNumberElement("total4",new Rectangle2D.Float(390, 0, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","total4"));

        divisionGroup.setHeader(divisionHeader);
        divisionGroup.setFooter(divisionFooter);
        grouplist.add(divisionGroup);
        
        return grouplist;
    }

    protected ItemBand createItemBand() {
        ItemBand items = super.createItemBand();        
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,
                new Rectangle2D.Float(10, 0, 450, 15), false, true));
        items.addElement(TextFieldElementFactory.createStringElement("subdivision",new Rectangle2D.Float( 10, 0, 150, 15)
                ,Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","subdivision"));
        items.addElement(NumberFieldElementFactory.createStringElement("tipo",new Rectangle2D.Float(160, 0, 160, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","tipo"));
        items.addElement(NumberFieldElementFactory.createNumberElement("cantidad",new Rectangle2D.Float(320, 0, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","cantidad"));
        items.addElement(NumberFieldElementFactory.createNumberElement("costo",new Rectangle2D.Float(390, 0, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","costo"));
        return items;
    }
    
    protected ReportHeader createReportHeader() {
        
        ReportHeader reportHeader = new ReportHeader();
        
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, DCGray1, null,
                new Rectangle2D.Float(10, 0, 450, 25), false, true));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, 450, 25),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("query.fine.type.title")));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 30, 50, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.fine.country")+": "));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(60, 30, 80, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,divGeoValues[1]));
        
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 50, 50, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.fine.unit")+": "));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(60, 50, 80, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,divGeoValues[3]));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(150, 30, 50, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.fine.division")+": "));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(200, 30, 90, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,divOrgValues[1][1]));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(150, 50, 50, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.fine.subdivision")+": "));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(200, 50, 90, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,divOrgValues[1][2]));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(300, 30, 50, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.fine.from")+": "));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(350, 30, 90, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,((ReportFineForm)form).getFrom(messages)));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(300, 50, 50, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.fine.to")+": "));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(350, 50, 90, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,((ReportFineForm)form).getTo(messages)));

        return reportHeader;
    }

    protected ReportFooter createReportFooter() {
        ReportFooter reportFooter = new ReportFooter();

        DrawableFieldElementFactory factory = new DrawableFieldElementFactory();
        factory.setName("drawable-field");
        
        int y = 5;
        if(unitsChart) {
            factory.setAbsolutePosition(new Point2D.Float(50, y));
            factory.setMinimumSize(new FloatDimension(550, 250));
            factory.setFieldname("Units");
            reportFooter.addElement(factory.createElement());
            y += 260;
        }

        if(divisionsChart) {
            factory.setAbsolutePosition(new Point2D.Float(50, y));
            factory.setMinimumSize(new FloatDimension(550, 250));
            factory.setFieldname("Divisions");
            reportFooter.addElement(factory.createElement());
            y += 260;
        }

        if(subdivisionsChart) {
            factory.setAbsolutePosition(new Point2D.Float(50, y));
            factory.setMinimumSize(new FloatDimension(550, 250));
            factory.setFieldname("Subdivisions");
            reportFooter.addElement(factory.createElement());
        }

        return reportFooter;
    }

    protected ExpressionCollection createFunctions() {
        ExpressionCollection functions = super.createFunctions();
        
        final ItemSumFunction total1 = new ItemSumFunction();
        total1.setName("total1");
        total1.setGroup("divisions");
        total1.setField("cantidad");
        functions.add(total1);

        final ItemSumFunction total2 = new ItemSumFunction();
        total2.setName("total2");
        total2.setGroup("units");
        total2.setField("cantidad");
        functions.add(total2);
        
        final ItemSumFunction total3 = new ItemSumFunction();
        total3.setName("total3");
        total3.setField("cantidad");
        functions.add(total3);
        
        final ItemSumFunction total4 = new ItemSumFunction();
        total4.setName("total4");
        total4.setGroup("divisions");
        total4.setField("costo");
        functions.add(total4);

        final ItemSumFunction total5 = new ItemSumFunction();
        total5.setName("total5");
        total5.setGroup("units");
        total5.setField("costo");
        functions.add(total5);
        
        final ItemSumFunction total6 = new ItemSumFunction();
        total6.setName("total6");
        total6.setField("costo");
        functions.add(total6);

        final ElementColorFunction color = new ElementColorFunction();
        color.setName("changeColor");
        color.setElement("Variance Field");
        color.setField("color");
        color.setColorTrue(gray1);
        color.setColorFalse(gray2);
        functions.add(color);

        return functions;
    }

    public void setUnitsChart(boolean b) {
        unitsChart = b;
    }
}
