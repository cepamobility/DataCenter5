/**
 * Created on 10-jul-2006
 * CEPA
 * DataCenter 5
 */
package presentation.report.queries;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
import java.util.LinkedList;

import javax.swing.table.TableModel;

import org.apache.struts.action.ActionForm;
import org.jfree.report.Element;
import org.jfree.report.ElementAlignment;
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
import org.jfree.report.function.ItemCountFunction;
import org.jfree.report.function.ItemSumFunction;
import org.jfree.report.modules.misc.beanshell.BSHExpression;

import presentation.report.ReportFactory;
import presentation.translator.web.queries.APMMReport;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.administration.corporation.tables.CorporationData;
import communication.language.Text;

public class ReportAPMHByDivisionFactory extends ReportFactory {

    protected Text messages;

    private SystemAccess sys;

    private String reportType;

    private int corporationId;
    private boolean apmk;
    private String[] header = {"query.apmmbydivision.apmm","query.apmmbydivision.ipmm"}; 
        
    public ReportAPMHByDivisionFactory(String name, TableModel model, ActionForm form, SystemAccess sys, Text messages, boolean apmk) {
        super(name, model, form);
        this.messages = messages;
        parseDivisionValues(sys,messages);
        this.sys = sys;
        reportType = ((APMMReport)form).getOutput();
        corporationId = sys.getUserSessionData().getFilter().getCorporationId().intValue();
        this.apmk = apmk;
        if(apmk) {
        	header[0] = "query.apmmbydivision.apmk";
        	header[1] = "query.apmmbydivision.ipmk";
        }
    }

    protected GroupList createGroups() {
        
/*        ReportAPMMForm reportForm = (ReportAPMMForm)form;
        String[] divOrgs = sys.getUserSessionData().getFilter().getPrincipalLabels(); 

        GroupList grouplist = super.createGroups();
        
        Group div1Group = new Group();
        div1Group.setName("div1");
        div1Group.addField("divOrg1Id");

        GroupHeader div1Header = new GroupHeader();
        div1Header.setName("div1Header");

        div1Header.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, null,
                new Rectangle2D.Float(10, 10, getReportWidth(), 25), false, true));
        if(Integer.parseInt(reportForm.getOrder1()) != 3) {
            div1Header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 60, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,messages.getText(divOrgs[Integer.parseInt(reportForm.getOrder1())])));
        }else {
            div1Header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 60, 25),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,messages.getText("generic.messages.country")));
        }
        div1Header.addElement(TextFieldElementFactory.createStringElement("divOrg1Name",new Rectangle2D.Float(70, 10, 100, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"", "divOrg1Name"));


        GroupFooter div1Footer = new GroupFooter();
        div1Footer.setName("div1Footer");
        div1Footer.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, null,
                new Rectangle2D.Float(10, 10, getReportWidth(), 25), false, true));
        div1Footer.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 60, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,messages.getText("generic.messages.total")));
        div1Footer.addElement(TextFieldElementFactory.createStringElement("divOrg1Name",new Rectangle2D.Float(70, 10, 100, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"", "divOrg1Name"));
        div1Footer.addElement(NumberFieldElementFactory.createNumberElement("total1",new Rectangle2D.Float(170, 10, 100, 25),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"", formatTwoDecimals, "total1"));
        div1Footer.addElement(NumberFieldElementFactory.createNumberElement("total2",new Rectangle2D.Float(270, 10, 100, 25),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"", formatTwoDecimals, "total2"));

        div1Group.setHeader(div1Header);
        div1Group.setFooter(div1Footer);
        
        grouplist.add(div1Group);

        Group div2Group = new Group();
        div2Group.setName("div2");
        div2Group.addField("divOrg1Id");
        div2Group.addField("divOrg2Id");

        GroupHeader div2Header = new GroupHeader();
        div2Header.setName("div2Header");

        div2Header.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, ThirtdLevelColor, null,
                new Rectangle2D.Float(10, 10, getReportWidth(), 25), false, true));
        div2Header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 60, 25),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(divOrgs[Integer.parseInt(reportForm.getOrder2())])+": "));
        if(Integer.parseInt(reportForm.getOrder2()) == 3) {
            div1Header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 60, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,messages.getText(divOrgs[Integer.parseInt(reportForm.getOrder1())])));
        }else {
            div2Header.addElement(TextFieldElementFactory.createStringElement("divOrg2Name",new Rectangle2D.Float(70, 10, 100, 25),
                    Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"","divOrg2Name"));
        }
        div2Header.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FirstLevelColor, null,
                new Rectangle2D.Float(10, 35, getReportWidth(), 18), false, true));
        div2Header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 35, 160, 18), 
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SMALL_BOLD_FONT,messages.getText(divOrgs[Integer.parseInt(reportForm.getOrder3())])));
        div2Header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(170, 35, 100, 18), 
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SMALL_BOLD_FONT,"APMM"));
        div2Header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(270, 35, 100, 18), 
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SMALL_BOLD_FONT,"IPMM"));
        
        GroupFooter div2Footer = new GroupFooter();
        div2Footer.setName("div2Footer");
        div2Footer.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, ThirtdLevelColor, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 25), false, true));
        div2Footer.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, 60, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.messages.total")));
        div2Footer.addElement(TextFieldElementFactory.createStringElement("division",new Rectangle2D.Float(70, 0, 100, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"", "divOrg2Name"));
        div2Footer.addElement(NumberFieldElementFactory.createNumberElement("total3",new Rectangle2D.Float(170, 0, 100, 25),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"", formatTwoDecimals, "total3"));
        div2Footer.addElement(NumberFieldElementFactory.createNumberElement("total4",new Rectangle2D.Float(270, 0, 100, 25),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"", formatTwoDecimals, "total4"));

        div2Group.setHeader(div2Header);
        div2Group.setFooter(div2Footer);
        
        grouplist.add(div2Group);
     
        return grouplist;
*/        
        String[] divOrgs = sys.getUserSessionData().getFilter().getPrincipalLabels(); 

        GroupList grouplist = super.createGroups();
        
        int y = (reportType.equals("XLS")) ? 18 : 10;
        int y2 = (reportType.equals("XLS")) ? 33 : 35;
        Collection fields = new LinkedList();
        String[] levelNames = sys.getUserSessionData().getFilter().getLevelNames(messages);
        for(int i = 1; i <= levelNames.length; i++) {
            int order = getOrder(i);
            if(getOrder(i+1) != 4) {
                count = i+1;
                String totalName = (order == 3) ? "corporation.country" : levelNames[order]; 
                fields.add("divOrg"+i+"Id");
                Element[] headerElements = null;
                if(getOrder(i+2) != 4) {
                    headerElements = new Element[3]; 
                    headerElements[0] = StaticShapeElementFactory.createRectangleShapeElement(null, groupsHeaderColors[i-1], null,
                            new Rectangle2D.Float(10, y, getReportWidth(), 15), false, true);
                    headerElements[1] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, y, 70, 15),Color.WHITE,
                        ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,messages.getText(totalName)+": ");
                    headerElements[2] = TextFieldElementFactory.createStringElement("divOrg"+i+"Name",new Rectangle2D.Float(80, y, 200, 15),Color.WHITE,
                            ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"","divOrg"+i+"Name");
                }else {
                    if(corporationId != 4) {
                        headerElements = new Element[7];
                    }else {
                        headerElements = new Element[6];
                    }
                    
                    headerElements[0] = StaticShapeElementFactory.createRectangleShapeElement(null, groupsHeaderColors[i-1], null,
                            new Rectangle2D.Float(10, y, getReportWidth(), 15), false, true);
                    headerElements[1] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, y, 70, 15),Color.WHITE,
                        ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,messages.getText(totalName)+": ");
                    headerElements[2] = TextFieldElementFactory.createStringElement("divOrg"+i+"Name",new Rectangle2D.Float(80, y, 180, 15),Color.WHITE,
                            ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"","divOrg"+i+"Name");

                    headerElements[3] = StaticShapeElementFactory.createRectangleShapeElement(null,FirstLevelColor, null,
                            new Rectangle2D.Float(10, y2, getReportWidth(), 18), false, true);
                    if(getOrder(i+1) == 3) {
                        headerElements[4] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, y2, 160, 18), 
                            Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SMALL_BOLD_FONT,messages.getText("corporation.country"));
                    }else {
                        headerElements[4] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, y2, 160, 18), 
                                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SMALL_BOLD_FONT,messages.getText(divOrgs[getOrder(i+1)]));
                    }
                    headerElements[5] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(170, y2, 100, 18), 
                            Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SMALL_BOLD_FONT,messages.getText(header[0]));
                    if(corporationId != 4) {
                        headerElements[6] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(270, y2, 100, 18), 
                                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SMALL_BOLD_FONT,messages.getText(header[1]));
                    }
                }
 
                Element[] footerTotals = (corporationId != 4) ? new Element[2] : new Element[1]; 
                footerTotals[0] = NumberFieldElementFactory.createNumberElement("totalAPMM"+i,new Rectangle2D.Float(170, y, 100, 15),
                        Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"", formatTwoDecimals, "totalAPMM"+i);
                if(corporationId != 4) {
                    footerTotals[1] = NumberFieldElementFactory.createNumberElement("totalIPMM"+i,new Rectangle2D.Float(270, y, 100, 15),
                        Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"", formatTwoDecimals, "totalIPMM"+i);
                }
                grouplist.add(createGroup(fields.toArray(), footerTotals, headerElements, "Group"+i, messages, messages.getText(totalName),160,y));
            }
        }
        
        return grouplist;
    }

    public int getOrder(int i) {
        try{        
            switch(i) {
                case 1:
                    return Integer.parseInt(((APMMReport)form).getOrder1());
                case 2:
                    return Integer.parseInt(((APMMReport)form).getOrder2());
                case 3:
                    return Integer.parseInt(((APMMReport)form).getOrder3());
                case 4:
                    return Integer.parseInt(((APMMReport)form).getOrder4());
            }
        }catch (Exception e) {}
        return 4;
    }
    
    protected int getWidth() {
        return 542;
    }
    
//  it is declared but it is not used 
    protected int getReportWidth() {
    	return 460;
    }

    protected ItemBand createItemBand() {
        ItemBand items = super.createItemBand();
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,new Rectangle2D.Float(10, 0, getReportWidth(), 15), false, true));
        items.addElement(TextFieldElementFactory.createStringElement("divOrg"+count+"Name",new Rectangle2D.Float(10, 0, 160, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","divOrg"+count+"Name"));
        if(apmk) {
	        items.addElement(NumberFieldElementFactory.createNumberElement("apmk",new Rectangle2D.Float(170, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatTwoDecimals, "apmk"));
	        if(corporationId != CorporationData.JJ) {
	            items.addElement(NumberFieldElementFactory.createNumberElement("ipmk",new Rectangle2D.Float(270, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatTwoDecimals, "ipmk"));
	        }
        }else {
	        items.addElement(NumberFieldElementFactory.createNumberElement("apmm",new Rectangle2D.Float(170, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatTwoDecimals, "apmm"));
	        if(corporationId != CorporationData.JJ) {
	            items.addElement(NumberFieldElementFactory.createNumberElement("ipmm",new Rectangle2D.Float(270, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatTwoDecimals, "ipmm"));
	        }
        }
        return items;
    }
    
    protected ReportHeader createReportHeader() {
        
        APMMReport reportForm = (APMMReport)form;
        ReportHeader reportHeader = new ReportHeader();

		reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 25), false, true));
        
		reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, getReportWidth(), 25),
        		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("query.apmmbydivision.title")));
        
        int height = 25;

        height = writeParametersTitle(messages.getText("query.eventbyperiod.period"),reportHeader,height,10,160);
        height = writeParameter(messages.getText("query.eventbyperiod.from")+":",reportForm.getFrom(messages),reportHeader,height,10,70,80,90);
        height = writeParameter(messages.getText("query.eventbyperiod.to")+":",reportForm.getTo(messages),reportHeader,height,10,70,80,90);

        height = writeParametersTitle(messages.getText("generic.driver.divgeo"),reportHeader,height,10,160); 
        height = writeParameter(divGeoLabels[0]+":",divGeoValues[0],reportHeader,height,10,70,80,90);
        height = writeParameter(divGeoLabels[1]+":",divGeoValues[1],reportHeader,height,10,70,80,90);

        int maxHeight = height;
        
        height = 25;
        String[] divOrgs = sys.getUserSessionData().getFilter().getLevelLabels();
        for(int i = 0; i < divOrgs.length; i++) {
            if(!Util.empty(divOrgs[i])) {
                height = writeParametersTitle(messages.getText(divOrgs[i]),reportHeader,height,270,200);
                for(int j = 0; j < 3; j++) {
                    if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 1 && j== 0) && 
                            !(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 2 && j== 0)) {
                        if(!Util.empty(divOrgLabels[i][j])) {
                            height = writeParameter(divOrgLabels[i][j]+":",divOrgValues[i][j],reportHeader,height,270,100,370,100);
                        }
                    }
                }
            }
        }
        
        height = Math.max(maxHeight,height);
        
        if(getOrder(2) == 4) {
            count = 1;
            String[] levelNames = sys.getUserSessionData().getFilter().getLevelNames(messages);
            reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FirstLevelColor, null,
                    new Rectangle2D.Float(10, height, getReportWidth(), 18), false, true));
            String s = (getOrder(1) == 3) ? "corporation.country" : levelNames[getOrder(1)]; 
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 160, 18), 
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SMALL_BOLD_FONT,messages.getText(s)));
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(170, height, 100, 18), 
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SMALL_BOLD_FONT,messages.getText(header[0])));
            if(corporationId != CorporationData.JJ) {
                reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(270, height, 100, 18), 
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SMALL_BOLD_FONT,messages.getText(header[1])));
            }
        }
        
        return reportHeader;
	}

    protected ReportFooter createReportFooter() {
        ReportFooter reportFooter = new ReportFooter();
        reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, null,
                new Rectangle2D.Float(10, 18, getReportWidth(), 18), false, true));
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 18, 160, 18),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,messages.getText("generic.messages.total")));
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement("totalAPMM",new Rectangle2D.Float(170, 18, 100, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatTwoDecimals,"totalAPMM"));
        if(corporationId != 4) {
            reportFooter.addElement(NumberFieldElementFactory.createNumberElement("totalIPMM",new Rectangle2D.Float(270, 18, 100, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatTwoDecimals,"totalIPMM"));
        }
        return reportFooter;
    }

    protected ExpressionCollection createFunctions() {
        ExpressionCollection functions = super.createFunctions();
        
    	String distance = (apmk) ? "kms" : "miles";
        for(int i = 1; i < 4; i++) {
            if(getOrder(i+1) != 4) {
        
                final ItemSumFunction total2 = new ItemSumFunction();
                total2.setName("totalMiles"+i);
                total2.setGroup("Group"+i);
                total2.setField(distance);
                functions.add(total2);
        
                final ItemSumFunction total3 = new ItemSumFunction();
                total3.setName("totalAccidents"+i);
                total3.setGroup("Group"+i);
                total3.setField("accidents");
                functions.add(total3);
        
                final ItemSumFunction total4 = new ItemSumFunction();
                total4.setName("totalIncidents"+i);
                total4.setGroup("Group"+i);
                total4.setField("incidents");
                functions.add(total4);
        
                
                BSHExpression bshExp1 = new BSHExpression();
                bshExp1.setName("totalAPMM"+i);
                bshExp1.setExpression("getValue() {" +
                        "            Number accidents = (Number) dataRow.get (\"totalAccidents"+i+"\");" +
                        "            Number miles = (Number) dataRow.get (\"totalMiles"+i+"\");" +
                        "            if(miles.floatValue() == 0) {" +
                        "               return new Float(0);" +
                        "            }" +
                        "            float result = accidents.floatValue()  * 1000000 / miles.floatValue();" +
                        "            return new Float (result);" +
                        "}");
                functions.add(bshExp1);

                BSHExpression bshExp2 = new BSHExpression();
                bshExp2.setName("totalIPMM"+i);
                bshExp2.setExpression("getValue() {" +
                        "            Number incidents = (Number) dataRow.get (\"totalIncidents"+i+"\");" +
                        "            Number miles = (Number) dataRow.get (\"totalMiles"+i+"\");" +
                        "            if(miles.floatValue() == 0) {" +
                        "               return new Float(0);" +
                        "            }" +
                        "            float result = incidents.floatValue()  * 1000000 / miles.floatValue();" +
                        "            return new Float (result);" +
                        "}");
                functions.add(bshExp2);
            }
        }
        
        final ItemSumFunction totalAccidents = new ItemSumFunction();
        totalAccidents.setName("totalAccidents");
        totalAccidents.setField("accidents");
        functions.add(totalAccidents);

        final ItemSumFunction totalIncidents = new ItemSumFunction();
        totalIncidents.setName("totalIncidents");
        totalIncidents.setField("incidents");
        functions.add(totalIncidents);

        final ItemSumFunction totalMiles = new ItemSumFunction();
        totalMiles.setName("totalMiles");
        totalMiles.setField(distance);
        functions.add(totalMiles);

        BSHExpression bshExp1 = new BSHExpression();
        bshExp1.setName("totalAPMM");
        bshExp1.setExpression("getValue() {" +
                "            Number accidents = (Number) dataRow.get (\"totalAccidents\");" +
                "            Number miles = (Number) dataRow.get (\"totalMiles\");" +
                "            if(miles.floatValue() == 0) {" +
                "               return new Float(0);" +
                "            }" +
                "            float result = accidents.floatValue()  * 1000000 / miles.floatValue();" +
                "            return new Float (result);" +
                "}");
        functions.add(bshExp1);

        BSHExpression bshExp2 = new BSHExpression();
        bshExp2.setName("totalIPMM");
        bshExp2.setExpression("getValue() {" +
                "            Number incidents = (Number) dataRow.get (\"totalIncidents\");" +
                "            Number miles = (Number) dataRow.get (\"totalMiles\");" +
                "            if(miles.floatValue() == 0) {" +
                "               return new Float(0);" +
                "            }" +
                "            float result = incidents.floatValue()  * 1000000 / miles.floatValue();" +
                "            return new Float (result);" +
                "}");
        functions.add(bshExp2);


        final ItemCountFunction countFunction = new ItemCountFunction();
        countFunction.setName("countExpression");
        countFunction.setGroup("dummy");
        functions.add(countFunction);
        
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
