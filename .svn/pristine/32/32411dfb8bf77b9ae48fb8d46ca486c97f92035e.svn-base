/**
 * Created on 10-jul-2006
 * CEPA
 * DataCenter 5
 */
package presentation.report.queries;

import java.awt.Color;
import java.awt.geom.Point2D;
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
import org.jfree.report.elementfactory.DrawableFieldElementFactory;
import org.jfree.report.elementfactory.LabelElementFactory;
import org.jfree.report.elementfactory.NumberFieldElementFactory;
import org.jfree.report.elementfactory.StaticShapeElementFactory;
import org.jfree.report.elementfactory.TextFieldElementFactory;
import org.jfree.report.function.ElementColorFunction;
import org.jfree.report.function.ExpressionCollection;
import org.jfree.report.function.ItemSumFunction;
import org.jfree.report.util.PageFormatFactory;
import org.jfree.ui.FloatDimension;

import presentation.report.ReportFactory;
import presentation.translator.web.queries.AccidentType;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.language.Text;
import communication.queries.report.ReportAccidentTypeData;
import communication.util.Filter;

public class ReportAccidentTypeFactory extends ReportFactory {

    protected Text messages;
    private String reportType;
    private SystemAccess sys;

    private ReportAccidentTypeData data;
    
    private int order1;
    private int order2;
    private int order3;
    private int order4;
    
    protected static final int YLOCATION =10;
    protected static final int YDIVISION =250;
    protected static final int YPERIOD =500;

    public ReportAccidentTypeFactory(String name, TableModel model, ActionForm form, SystemAccess sys, Text messages,String reportType,int order1,int order2,int order3,int order4) {
        super(name, model, form, Filter.CORPORATION);
        this.messages = messages;
        this.data = (ReportAccidentTypeData)model;
        parseDivisionValues(sys,messages);
        this.reportType = reportType;
        this.sys = sys;
        this.order1 = order1;
        this.order2 = order1;
        this.order3 = order1;
        this.order4 = order1;
    }

    protected int getWidth() {
        return (reportType.equals("XLS")) ? 982 : 882;
    }
    
//  it is declared but it is not used 
    protected int getReportWidth() {
        return (reportType.equals("XLS")) ? 900 : 800;
    }

    protected int getHeight() {
        return PageFormatFactory.A4[0];
    }

    public int getOrder(int i) {
        switch(i) {
            case 1:
                return Integer.parseInt(((AccidentType)form).getOrder1());
            case 2:
                return Integer.parseInt(((AccidentType)form).getOrder2());
            case 3:
                return Integer.parseInt(((AccidentType)form).getOrder3());
            case 4:
                return Integer.parseInt(((AccidentType)form).getOrder4());
        }
        return 4;
    }

    protected GroupList createGroups() {
        GroupList grouplist = super.createGroups();

        Group chartGroup = new Group();
        chartGroup.setName("charts");

        int w = (reportType.equals("XLS")) ? 100 : 0;

        Collection totalFields = new LinkedList();
        String[] levelNames = sys.getUserSessionData().getFilter().getLevelNames(messages);
        for(int i = 1; i <= levelNames.length; i++) {
            int order = getOrder(i);
            if(getOrder(i) != 4) {
                totalFields.add("div"+i+"Id");
                String[] fields = (String[])totalFields.toArray(new String[0]);
                
                Group group = new Group();
                group.setName("Group"+i);
                for(int level=0;level<fields.length;level++){
                    group.addField((String)fields[level]);
                }
                GroupHeader header = new GroupHeader();
                header.setName("header");

                chartGroup.addField("div"+i+"Id");
                
                header.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, groupsHeaderColors[i-1], null,
                        new Rectangle2D.Float(10, 10, getReportWidth(), 15), false, true));
                if(order != 3){
                    header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 70, 15),Color.WHITE,
                            ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,messages.getText(levelNames[order])+": "));
                }else {
                    header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 70, 15),Color.WHITE,
                            ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,messages.getText("corporation.country")+": "));
                }
                header.addElement(TextFieldElementFactory.createStringElement("div"+i+"Name",new Rectangle2D.Float(80, 10, 200, 15),Color.WHITE,
                    ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"","div"+i+"Name"));
 
                GroupFooter footer = new GroupFooter();
                footer.setName("footer");
                
                group.setHeader(header);
                group.setFooter(footer);
                
                grouplist.add(group);
            }
        }
        
        chartGroup.addField("type");

        GroupHeader chartHeader = new GroupHeader();
        chartHeader.setName("chartHeader");

        chartHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, null,
                new Rectangle2D.Float(10, 20, getReportWidth(), 15), false, true));
        chartHeader.addElement(TextFieldElementFactory.createStringElement("typeHeader",new Rectangle2D.Float(10, 20, 100+w, 15),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"", "type"));

        chartHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, DCGray3, null,
                new Rectangle2D.Float(10, 35, getReportWidth(), 35), false, true));

        chartHeader.addElement(TextFieldElementFactory.createStringElement("type",new Rectangle2D.Float(10, 35, 100+w, 35),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"", "type"));
        chartHeader.addElement(TextFieldElementFactory.createStringElement("title1",new Rectangle2D.Float(110+w, 35, 70, 35),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"", "title1"));
        chartHeader.addElement(TextFieldElementFactory.createStringElement("title2",new Rectangle2D.Float(180+w, 35, 70, 35),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"", "title2"));
        chartHeader.addElement(TextFieldElementFactory.createStringElement("title3",new Rectangle2D.Float(250+w, 35, 70, 35),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"", "title3"));
        chartHeader.addElement(TextFieldElementFactory.createStringElement("title4",new Rectangle2D.Float(320+w, 35, 70, 35),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"", "title4"));
        chartHeader.addElement(TextFieldElementFactory.createStringElement("title5",new Rectangle2D.Float(390+w, 35, 70, 35),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"", "title5"));
        chartHeader.addElement(TextFieldElementFactory.createStringElement("title6",new Rectangle2D.Float(460+w, 35, 70, 35),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"", "title6"));
        chartHeader.addElement(TextFieldElementFactory.createStringElement("title7",new Rectangle2D.Float(530+w, 35, 70, 35),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"", "title7"));
        chartHeader.addElement(TextFieldElementFactory.createStringElement("title8",new Rectangle2D.Float(600+w, 35, 50, 35),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"", "title8"));
        chartHeader.addElement(TextFieldElementFactory.createStringElement("title9",new Rectangle2D.Float(650+w, 35, 50, 35),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"", "title9"));
        chartHeader.addElement(TextFieldElementFactory.createStringElement("title10",new Rectangle2D.Float(700+w, 35, 50, 35),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"", "title10"));
        chartHeader.addElement(TextFieldElementFactory.createStringElement("title11",new Rectangle2D.Float(750+w, 35, 50, 35),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"", "title11"));
        
        GroupFooter chartFooter = new GroupFooter();
        chartFooter.setName("chartFooter");

        chartFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 15), false, true));
        chartFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, 100+w, 15),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.accidentcount.totals")));
        chartFooter.addElement(NumberFieldElementFactory.createNumberElement("total1",new Rectangle2D.Float(110+w, 0, 70, 15),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals, "total1"));
        chartFooter.addElement(NumberFieldElementFactory.createNumberElement("total2",new Rectangle2D.Float(180+w, 0, 70, 15),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals, "total2"));
        chartFooter.addElement(NumberFieldElementFactory.createNumberElement("total3",new Rectangle2D.Float(250+w, 0, 70, 15),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals, "total3"));
        chartFooter.addElement(NumberFieldElementFactory.createNumberElement("total4",new Rectangle2D.Float(320+w, 0, 70, 15),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals, "total4"));
        chartFooter.addElement(NumberFieldElementFactory.createNumberElement("total5",new Rectangle2D.Float(390+w, 0, 70, 15),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals, "total5"));
        chartFooter.addElement(NumberFieldElementFactory.createNumberElement("total6",new Rectangle2D.Float(460+w, 0, 70, 15),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals, "total6"));
        chartFooter.addElement(NumberFieldElementFactory.createNumberElement("total7",new Rectangle2D.Float(530+w, 0, 70, 15),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals, "total7"));
        chartFooter.addElement(NumberFieldElementFactory.createNumberElement("total8",new Rectangle2D.Float(600+w, 0, 50, 15),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals, "total8"));
        chartFooter.addElement(NumberFieldElementFactory.createNumberElement("Variance Field 2",new Rectangle2D.Float(650+w, 0, 50, 15),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals, "total9"));
        chartFooter.addElement(NumberFieldElementFactory.createNumberElement("Variance Field 2",new Rectangle2D.Float(700+w, 0, 50, 15),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals, "total10"));
        chartFooter.addElement(NumberFieldElementFactory.createNumberElement("Variance Field 2",new Rectangle2D.Float(750+w, 0, 50, 15),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals, "total11"));

        chartGroup.setHeader(chartHeader);
        chartGroup.setFooter(chartFooter);
        
        grouplist.add(chartGroup);
        
        return grouplist;
    }

    protected ItemBand createItemBand() {
        ItemBand items = super.createItemBand();        
        int w = (reportType.equals("XLS")) ? 100 : 0;
        int h = (reportType.equals("PDF")) ? 30 : 15;
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), h), false, true));
        items.addElement(TextFieldElementFactory.createStringElement("accident",new Rectangle2D.Float(10, 0, 100+w, h)
                ,Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","eventType"));
        items.addElement(TextFieldElementFactory.createStringElement("damage",new Rectangle2D.Float(110+w, 0, 70, h)
                ,Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","field1"));
        items.addElement(NumberFieldElementFactory.createNumberElement("driverInjured",new Rectangle2D.Float(180+w, 0, 70, h)
                ,Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals, "field2"));
        items.addElement(NumberFieldElementFactory.createNumberElement("funcInjured",new Rectangle2D.Float(250+w, 0, 70, h)
                ,Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals,"field3"));
        items.addElement(NumberFieldElementFactory.createNumberElement("otherInjured",new Rectangle2D.Float(320+w, 0, 70, h)
                ,Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals,"field4"));
        items.addElement(NumberFieldElementFactory.createNumberElement("driverFatal",new Rectangle2D.Float(390+w, 0, 70, h)
                ,Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals,"field5"));
        items.addElement(NumberFieldElementFactory.createNumberElement("funcFatal",new Rectangle2D.Float(460+w, 0, 70, h)
                ,Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals,"field6"));
        items.addElement(NumberFieldElementFactory.createNumberElement("otherFatal",new Rectangle2D.Float(530+w, 0, 70, h)
                ,Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals,"field7"));
        items.addElement(NumberFieldElementFactory.createNumberElement("field8",new Rectangle2D.Float(600+w, 0, 50, h)
                ,Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals,"field8"));
        items.addElement(NumberFieldElementFactory.createNumberElement("field9",new Rectangle2D.Float(650+w, 0, 50, h)
                ,Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals,"field9"));
        items.addElement(NumberFieldElementFactory.createNumberElement("fieldCount",new Rectangle2D.Float(700+w, 0, 50, h)
                ,Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals,"field10"));
        items.addElement(NumberFieldElementFactory.createNumberElement("fieldCost",new Rectangle2D.Float(750+w, 0, 50, h)
                ,Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals,"field11"));
        return items;
    }
    
    protected ReportHeader createReportHeader() {
        
        ReportHeader reportHeader = new ReportHeader();
        
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 25), false, true));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, getReportWidth(), 25),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("generic.query.accidentType")));

        int height = 25;
        
        height = writeParametersTitle(messages.getText("generic.driver.divgeo"),reportHeader,height,10,200); 
        height = writeParameter(divGeoLabels[0]+":",divGeoValues[0],reportHeader,height,10,70,80,130);
        height = writeParameter(divGeoLabels[1]+":",divGeoValues[1],reportHeader,height,10,70,80,130);

        height = writeParametersTitle(messages.getText("generic.report.otherdata"),reportHeader,height,10,200); 
        height = writeParameter(messages.getText("query.eventbyperiod.from")+":",((AccidentType)form).getFrom(messages),reportHeader,height,10,70,80,130);
        height = writeParameter(messages.getText("query.eventbyperiod.to")+":",((AccidentType)form).getTo(messages),reportHeader,height,10,70,80,130);
        height = writeParameter(messages.getText("query.accidenttype.accidents")+": ",messages.getText(((AccidentType)form).getType()),reportHeader,height,10,70,80,130);

        height = 25;
        String[] divOrgs = sys.getUserSessionData().getFilter().getLevelLabels();
        for(int i = 0; i < divOrgs.length; i++) {
            if(!Util.empty(divOrgs[i])) {
                height = writeParametersTitle(messages.getText(divOrgs[i]),reportHeader,height,350,210);
                for(int j = 0; j < 3; j++) {
                    if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 1 && j== 0) && 
                            !(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 2 && j== 0)) {
                        if(!Util.empty(divOrgLabels[i][j])) {
                            height = writeParameter(divOrgLabels[i][j]+":",divOrgValues[i][j],reportHeader,height,350,70,420,140);
                        }
                    }
                }
            }
        }

        return reportHeader;
    }

    protected ReportFooter createReportFooter() {
        ReportFooter reportFooter = new ReportFooter();

        if(order1 == 4) {
            DrawableFieldElementFactory factory = new DrawableFieldElementFactory();
            factory.setName("drawable-field");
            
            int x = 10;
            if(!Util.empty(reportType) && !reportType.equals("HTML")){
                if(data.accidentsChart()) {
                	factory.setAbsolutePosition(new Point2D.Float(x, 18));
                	factory.setMinimumSize(new FloatDimension(270, 500));
                	factory.setFieldname("Accidents");
                	reportFooter.addElement(factory.createElement());
                    x += 270;
                }
           
                if(data.incidentsChart()) {
                	factory.setAbsolutePosition(new Point2D.Float(x, 18));
                	factory.setMinimumSize(new FloatDimension(280, 500));
                	factory.setFieldname("Incidents");
                	reportFooter.addElement(factory.createElement());
                    x += 280;
                }
    
                if(data.fineChart()) {
                	factory.setAbsolutePosition(new Point2D.Float(x, 18));
                	factory.setMinimumSize(new FloatDimension(240, 500));
                	factory.setFieldname("Fines");
                	reportFooter.addElement(factory.createElement());
                }
            }
        }
        return reportFooter;
    }

    protected ExpressionCollection createFunctions() {
        ExpressionCollection functions = super.createFunctions();
        
        final ItemSumFunction total1 = new ItemSumFunction();
        total1.setName("total1");
        total1.setGroup("charts");
        total1.setField("field1");
        functions.add(total1);

        final ItemSumFunction total2 = new ItemSumFunction();
        total2.setName("total2");
        total2.setGroup("charts");
        total2.setField("field2");
        functions.add(total2);

        final ItemSumFunction total3 = new ItemSumFunction();
        total3.setName("total3");
        total3.setGroup("charts");
        total3.setField("field3");
        functions.add(total3);

        final ItemSumFunction total4 = new ItemSumFunction();
        total4.setName("total4");
        total4.setGroup("charts");
        total4.setField("field4");
        functions.add(total4);

        final ItemSumFunction total5 = new ItemSumFunction();
        total5.setName("total5");
        total5.setGroup("charts");
        total5.setField("field5");
        functions.add(total5);

        final ItemSumFunction total6 = new ItemSumFunction();
        total6.setName("total6");
        total6.setGroup("charts");
        total6.setField("field6");
        functions.add(total6);

        final ItemSumFunction total7 = new ItemSumFunction();
        total7.setName("total7");
        total7.setGroup("charts");
        total7.setField("field7");
        functions.add(total7);

        final ItemSumFunction total8 = new ItemSumFunction();
        total8.setName("total8");
        total8.setGroup("charts");
        total8.setField("field8");
        functions.add(total8);

        final ItemSumFunction total9 = new ItemSumFunction();
        total9.setName("total9");
        total9.setGroup("charts");
        total9.setField("field9");
        functions.add(total9);

        final ItemSumFunction totalCount = new ItemSumFunction();
        totalCount.setName("total10");
        totalCount.setGroup("charts");
        totalCount.setField("field10");
        functions.add(totalCount);

        final ItemSumFunction totalCost = new ItemSumFunction();
        totalCost.setName("total11");
        totalCost.setGroup("charts");
        totalCost.setField("field11");
        functions.add(totalCost);
        
        final ElementColorFunction color = new ElementColorFunction();
        color.setName("changeColor");
        color.setElement("Variance Field");
        color.setField("color");
        color.setColorTrue(gray2);
        color.setColorFalse(gray3);
        functions.add(color);

        final ElementColorFunction color2 = new ElementColorFunction();
        color2.setName("changeColor2");
        color2.setElement("Variance Field 2");
        color2.setField("color2");
        color2.setColorTrue(Color.WHITE);
        color2.setColorFalse(FirstLevelColor);
        functions.add(color2);

        return functions;
    }
}
