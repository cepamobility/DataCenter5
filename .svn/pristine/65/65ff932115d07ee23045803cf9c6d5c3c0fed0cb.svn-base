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

public class ReportFineCountFactory extends ReportFactory {

    protected Text messages;
    
    private boolean divisionsChart;
    private boolean subdivisionsChart;
    private boolean unitsChart;

    public ReportFineCountFactory(String name, TableModel model, ActionForm form, SystemAccess sys, Text messages) {
        super(name, model, form);
        this.messages = messages;
        parseDivisionValues(sys,messages);
    }

    protected int getWidth() {
        return 770;
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
                new Rectangle2D.Float(10, 10, 660, 25), false, true));
        chartFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 100, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,messages.getText("query.accidentcount.totals")));
        chartFooter.addElement(NumberFieldElementFactory.createNumberElement("total3",new Rectangle2D.Float(110, 10, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,"","#####","total3"));
        chartFooter.addElement(NumberFieldElementFactory.createNumberElement("total6",new Rectangle2D.Float(180, 10, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,"","#####","total6"));
        chartFooter.addElement(NumberFieldElementFactory.createNumberElement("total9",new Rectangle2D.Float(250, 10, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,"","#####","total9"));
        chartFooter.addElement(NumberFieldElementFactory.createNumberElement("total12",new Rectangle2D.Float(320, 10, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,"","#####","total12"));
        chartFooter.addElement(NumberFieldElementFactory.createNumberElement("total15",new Rectangle2D.Float(390, 10, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,"","#####","total15"));
        chartFooter.addElement(NumberFieldElementFactory.createNumberElement("total18",new Rectangle2D.Float(460, 10, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,"","#####","total18"));
        chartFooter.addElement(NumberFieldElementFactory.createNumberElement("total21",new Rectangle2D.Float(530, 10, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,"","#####","total21"));
        chartFooter.addElement(NumberFieldElementFactory.createNumberElement("total24",new Rectangle2D.Float(600, 10, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,"","#####","total24"));

        chartGroup.setHeader(chartHeader);
        chartGroup.setFooter(chartFooter);
        
        grouplist.add(chartGroup);
        
        Group unitGroup = new Group();
        unitGroup.setName("units");
        unitGroup.addField("id");
        unitGroup.addField("unit");

        GroupHeader unitHeader = new GroupHeader();
        unitHeader.setName("unitHeader");

        unitHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,DCGray1, null,
                new Rectangle2D.Float(10, 10, 660, 15), false, true));
        unitHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 70, 15),Color.BLACK,
                ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.accidentcount.unit")+": "));
        unitHeader.addElement(TextFieldElementFactory.createStringElement("unit",new Rectangle2D.Float(80, 10, 200, 15),Color.BLACK,
                ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","unit"));
        
        
        GroupFooter unitFooter = new GroupFooter();
        unitFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,  DCGray1, null,
                new Rectangle2D.Float(10, 10, 660, 15), false, true));
        unitFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 40, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.accidentcount.total")));
        unitFooter.addElement(TextFieldElementFactory.createStringElement("unit",new Rectangle2D.Float(50, 10, 60, 15),Color.BLACK,
                ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"","unit"));
        unitFooter.addElement(NumberFieldElementFactory.createNumberElement("total2",new Rectangle2D.Float(110, 10, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","total2"));
        unitFooter.addElement(NumberFieldElementFactory.createNumberElement("total5",new Rectangle2D.Float(180, 10, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","total5"));
        unitFooter.addElement(NumberFieldElementFactory.createNumberElement("total8",new Rectangle2D.Float(250, 10, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","total8"));
        unitFooter.addElement(NumberFieldElementFactory.createNumberElement("total11",new Rectangle2D.Float(320, 10, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","total11"));
        unitFooter.addElement(NumberFieldElementFactory.createNumberElement("total14",new Rectangle2D.Float(390, 10, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","total14"));
        unitFooter.addElement(NumberFieldElementFactory.createNumberElement("total17",new Rectangle2D.Float(460, 10, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","total17"));
        unitFooter.addElement(NumberFieldElementFactory.createNumberElement("total20",new Rectangle2D.Float(530, 10, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","total20"));
        unitFooter.addElement(NumberFieldElementFactory.createNumberElement("total23",new Rectangle2D.Float(600, 10, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","total23"));

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
                new Rectangle2D.Float(10, 10, 660, 15), false, true));
        divisionHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 70, 15),Color.BLACK,
                ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.accidentcount.division")+": "));
        divisionHeader.addElement(TextFieldElementFactory.createStringElement("division",new Rectangle2D.Float(90, 10, 200, 15),Color.BLACK,
                ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","division"));
        
        
        divisionHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, gray1, null,new Rectangle2D.Float(10, 25, 100, 18), false, true));
        divisionHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 25, 90, 18),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("query.fine.subdivision")));

        divisionHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, gray2, null,new Rectangle2D.Float(110, 25, 70, 18), false, true));
        divisionHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(110, 25, 70, 18),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("query.fine.type.extserious")));

        divisionHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, gray1, null,new Rectangle2D.Float(180, 25, 70, 18), false, true));
        divisionHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(180, 25, 70, 18),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("query.fine.type.serious")));

        divisionHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, gray2, null,new Rectangle2D.Float(250, 25, 70, 18), false, true));
        divisionHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(250, 25, 70, 18),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("query.fine.type.notserious")));

        divisionHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, gray1, null,new Rectangle2D.Float(320, 25, 150, 18), false, true));
        divisionHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(320, 25, 70, 18),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("query.fine.type.half")));

        divisionHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, gray2, null,new Rectangle2D.Float(390, 25, 70, 18), false, true));
        divisionHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(390, 25, 70, 18),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("query.fine.type.other")));

        divisionHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, gray1, null,new Rectangle2D.Float(460, 25, 70, 18), false, true));
        divisionHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(460, 25, 70, 18),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("query.fine.type.nodata")));

        divisionHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, gray2, null,new Rectangle2D.Float(530, 25, 70, 18), false, true));
        divisionHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(530, 25, 70, 18),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("query.fine.type.cost")));

        divisionHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, gray1, null,new Rectangle2D.Float(600, 25, 70, 18), false, true));
        divisionHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(600, 25, 70, 18),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("query.fine.type.count")));

        GroupFooter divisionFooter = new GroupFooter();
        divisionFooter.setName("divisionFooter");
        
        divisionFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, DCGray3, null,
                new Rectangle2D.Float(10, 0, 660, 15), false, true));
        divisionFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, 90, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.accidentcount.totals")));
        divisionFooter.addElement(NumberFieldElementFactory.createNumberElement("total1",new Rectangle2D.Float(110, 0, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","total1"));
        divisionFooter.addElement(NumberFieldElementFactory.createNumberElement("total4",new Rectangle2D.Float(180, 0, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","total4"));
        divisionFooter.addElement(NumberFieldElementFactory.createNumberElement("total7",new Rectangle2D.Float(250, 0, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","total7"));
        divisionFooter.addElement(NumberFieldElementFactory.createNumberElement("total10",new Rectangle2D.Float(320, 0, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","total10"));
        divisionFooter.addElement(NumberFieldElementFactory.createNumberElement("total13",new Rectangle2D.Float(390, 0, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","total13"));
        divisionFooter.addElement(NumberFieldElementFactory.createNumberElement("total16",new Rectangle2D.Float(460, 0, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","total16"));
        divisionFooter.addElement(NumberFieldElementFactory.createNumberElement("total19",new Rectangle2D.Float(530, 0, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","total19"));
        divisionFooter.addElement(NumberFieldElementFactory.createNumberElement("total22",new Rectangle2D.Float(600, 0, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","total22"));

        divisionGroup.setHeader(divisionHeader);
        divisionGroup.setFooter(divisionFooter);
        grouplist.add(divisionGroup);
        
        return grouplist;
    }

    protected ItemBand createItemBand() {
        ItemBand items = super.createItemBand();        
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,
                new Rectangle2D.Float(10, 0, 660, 15), false, true));
        items.addElement(TextFieldElementFactory.createStringElement("subdivision",new Rectangle2D.Float(10, 0, 100, 15)
                ,Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","subdivision"));
        items.addElement(NumberFieldElementFactory.createNumberElement("gravisima",new Rectangle2D.Float(110, 0, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","gravisima"));
        items.addElement(NumberFieldElementFactory.createNumberElement("grave",new Rectangle2D.Float(180, 0, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","grave"));
        items.addElement(NumberFieldElementFactory.createNumberElement("leve",new Rectangle2D.Float(250, 0, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","leve"));
        items.addElement(NumberFieldElementFactory.createNumberElement("media",new Rectangle2D.Float(320, 0, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","media"));
        items.addElement(NumberFieldElementFactory.createNumberElement("otro",new Rectangle2D.Float(390, 0, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","otros"));
        items.addElement(NumberFieldElementFactory.createNumberElement("sindatos",new Rectangle2D.Float(460, 0, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","sindatos"));
        items.addElement(NumberFieldElementFactory.createNumberElement("costo",new Rectangle2D.Float(530, 0, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","costo"));
        items.addElement(NumberFieldElementFactory.createNumberElement("cantidad",new Rectangle2D.Float(600, 0, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","cantidad"));
        return items;
    }
    
    protected ReportHeader createReportHeader() {
        
        ReportHeader reportHeader = new ReportHeader();
        
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, DCGray1, null,
                new Rectangle2D.Float(10, 0, 660, 25), false, true));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, 660, 25),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("query.fine.count.title")));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 30, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.fine.country")+": "));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(80, 30, 100, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,divGeoValues[1]));
        
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(200, 30, 90, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.fine.division")+": "));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(290, 30, 100, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,divOrgValues[1][1]));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(200, 50, 90, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.fine.subdivision")+": "));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(290, 50, 100, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,divOrgValues[1][2]));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(400, 30, 90, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.fine.from")+": "));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(500, 30, 100, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,((ReportFineForm)form).getFrom(messages)));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(400, 50, 90, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.fine.to")+": "));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(500, 50, 100, 15),
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
        total1.setField("gravisima");
        functions.add(total1);

        final ItemSumFunction total2 = new ItemSumFunction();
        total2.setName("total2");
        total2.setGroup("units");
        total2.setField("gravisima");
        functions.add(total2);
        
        final ItemSumFunction total3 = new ItemSumFunction();
        total3.setName("total3");
        total3.setField("gravisima");
        functions.add(total3);
        
        final ItemSumFunction total4 = new ItemSumFunction();
        total4.setName("total4");
        total4.setGroup("divisions");
        total4.setField("grave");
        functions.add(total4);

        final ItemSumFunction total5 = new ItemSumFunction();
        total5.setName("total5");
        total5.setGroup("units");
        total5.setField("grave");
        functions.add(total5);
        
        final ItemSumFunction total6 = new ItemSumFunction();
        total6.setName("total6");
        total6.setField("grave");
        functions.add(total6);
        
        final ItemSumFunction total7 = new ItemSumFunction();
        total7.setName("total7");
        total7.setGroup("divisions");
        total7.setField("leve");
        functions.add(total7);

        final ItemSumFunction total8 = new ItemSumFunction();
        total8.setName("total8");
        total8.setGroup("units");
        total8.setField("leve");
        functions.add(total8);
        
        final ItemSumFunction total9 = new ItemSumFunction();
        total9.setName("total9");
        total9.setField("leve");
        functions.add(total9);
        
        final ItemSumFunction total10 = new ItemSumFunction();
        total10.setName("total10");
        total10.setGroup("divisions");
        total10.setField("media");
        functions.add(total10);

        final ItemSumFunction total11 = new ItemSumFunction();
        total11.setName("total11");
        total11.setGroup("units");
        total11.setField("media");
        functions.add(total11);
        
        final ItemSumFunction total12 = new ItemSumFunction();
        total12.setName("total12");
        total12.setField("media");
        functions.add(total12);
        
        final ItemSumFunction total13 = new ItemSumFunction();
        total13.setName("total13");
        total13.setGroup("divisions");
        total13.setField("otros");
        functions.add(total13);

        final ItemSumFunction total14 = new ItemSumFunction();
        total14.setName("total14");
        total14.setGroup("units");
        total14.setField("otros");
        functions.add(total14);
        
        final ItemSumFunction total15 = new ItemSumFunction();
        total15.setName("total15");
        total15.setField("otros");
        functions.add(total15);
        
        final ItemSumFunction total16 = new ItemSumFunction();
        total16.setName("total16");
        total16.setGroup("divisions");
        total16.setField("sindatos");
        functions.add(total16);

        final ItemSumFunction total17 = new ItemSumFunction();
        total17.setName("total17");
        total17.setGroup("units");
        total17.setField("sindatos");
        functions.add(total17);
        
        final ItemSumFunction total18 = new ItemSumFunction();
        total18.setName("total18");
        total18.setField("sindatos");
        functions.add(total18);
        
        final ItemSumFunction total19 = new ItemSumFunction();
        total19.setName("total19");
        total19.setGroup("divisions");
        total19.setField("costo");
        functions.add(total19);

        final ItemSumFunction total20 = new ItemSumFunction();
        total20.setName("total20");
        total20.setGroup("units");
        total20.setField("costo");
        functions.add(total20);
        
        final ItemSumFunction total21 = new ItemSumFunction();
        total21.setName("total21");
        total21.setField("costo");
        functions.add(total21);
        
        final ItemSumFunction total22 = new ItemSumFunction();
        total22.setName("total22");
        total22.setGroup("divisions");
        total22.setField("cantidad");
        functions.add(total22);

        final ItemSumFunction total23 = new ItemSumFunction();
        total23.setName("total23");
        total23.setGroup("units");
        total23.setField("cantidad");
        functions.add(total23);
        
        final ItemSumFunction total24 = new ItemSumFunction();
        total24.setName("total24");
        total24.setField("cantidad");
        functions.add(total24);
        
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
