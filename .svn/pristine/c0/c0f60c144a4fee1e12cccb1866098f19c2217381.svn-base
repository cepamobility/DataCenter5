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
import org.jfree.report.function.ItemSumFunction;
import org.jfree.report.modules.misc.beanshell.BSHExpression;

import presentation.report.ReportFactory;
import presentation.translator.web.queries.APMMReport;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.administration.corporation.tables.CorporationData;
import communication.event.tables.EventDataType;
import communication.language.Text;

public class ReportNewAPMMByPeriodFactory extends ReportFactory {

    protected Text messages;

    private Integer periodFrom;
    private Integer yearFrom;
    
    private Integer periodTo;
    private Integer yearTo;

    private boolean accumulated;

    private int dataType;
    private String reportType;
    private SystemAccess sys;
    private int reportWidth;
    private int count;

    private int order1;
    private int order2;
    private int order3;
    private int order4;
    
    private int orderCount;

    public ReportNewAPMMByPeriodFactory(String name, TableModel model, ActionForm form, SystemAccess sys, Text messages,Integer periodFrom,Integer yearFrom,Integer periodTo,Integer yearTo,boolean accumulated,int dataType,int order1,int order2,int order3,int order4) {
        super(name, model, form);
        this.messages = messages;
        this.periodFrom = periodFrom;
        this.yearFrom = yearFrom;
        this.periodTo = periodTo;
        this.yearTo = yearTo;
        parseDivisionValues(sys,messages);
        this.accumulated = accumulated;
        this.dataType = dataType;
        this.sys = sys;
        reportType = ((APMMReport)form).getOutput();
        count = (12 - periodFrom.intValue() + 1) + (yearTo.intValue() - yearFrom.intValue() - 1) * 12 + periodTo.intValue();
        if(count < 8) {
            reportWidth = 400;
        }else {
            reportWidth = count * 40 + 90;
        }
        this.order1 = order1;
        this.order2 = order2;
        this.order3 = order3;
        this.order4 = order4;
    }

    protected int getReportWidth() {
        return reportWidth;
    }
    
    protected int getWidth() {
        return reportWidth+82;
    }
    
    protected GroupList createGroups() {
        APMMReport reportForm = (APMMReport)form;
        String[] divOrgs = sys.getUserSessionData().getFilter().getPrincipalLabels(); 
        GroupList grouplist = super.createGroups();
        
        int y1 = (reportType.equals("XLS")) ? 15 : 10;
        int y2 = (reportType.equals("XLS")) ? 0 : 10;
        
        int xls = (reportType.equals("XLS")) ? 2 : 0;
        int yls = (reportType.equals("XLS")) ? 1 : 0;
        
        orderCount=1;
        if(order2 != 4) {
            orderCount=2;
            Group div1Group = new Group();
            div1Group.setName("div1");
            div1Group.addField("divOrg1Id");
    
            GroupHeader div1Header = new GroupHeader();
            div1Header.setName("div1Header");
    
            
            div1Header.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, null,
                    new Rectangle2D.Float(10, y1, getReportWidth(), 15), false, true));
            if(order1 == 3) {
                div1Header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(12-xls, y1, 88+xls, 15),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,messages.getText("generic.messages.country")));
            }else {
                div1Header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(12-xls, y1, 88+xls, 15),
                        Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,messages.getText(divOrgs[order1])));
            }
            div1Header.addElement(TextFieldElementFactory.createStringElement("headerName",new Rectangle2D.Float(100, y1, getReportWidth()-90, 15),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"", "divOrg1Name"));
            int x = 100;
            if(order3 == 4) {
                for(int i = yearFrom.intValue(); i <= yearTo.intValue(); i++) {
                    int first = (i == yearFrom.intValue()) ? periodFrom.intValue() : 1;
                    int last = (i == yearTo.intValue()) ? periodTo.intValue() : 12;
                    int x2 = x;
                    for(int j = first; j <= last; j++) {
                        String month = (accumulated && j > first) ? messages.getText("generic.apmm.accumulated.ytd")+" "+messages.getText("generic.shortmonth."+j) : messages.getText("generic.shortmonth."+j);
                        div1Header.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FourLevelColor, null,
                                new Rectangle2D.Float(x2, y1+34, 38+xls, 17+yls), false, true));
                        div1Header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x2, y1+34, 38+xls, 17+yls), 
                                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SMALL_BOLD_FONT,month));
                        x2 += 40;
                    }
                    div1Header.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,DCGray6, null,
                            new Rectangle2D.Float(x, y1+16-yls, x2 -x - 2 + xls, 16+xls+yls), false, true));
                    div1Header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y1+16-yls, x2 -x - 2 + xls, 16+xls+yls), 
                            Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(i)));
                    x += x2 -x;
                }
                
                div1Header.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FourLevelColor, null,
                        new Rectangle2D.Float(10, y1+34, 88+xls, 17+yls), false, true));
                if(order2 == 3) {
                    div1Header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, y1+34, 88+xls, 17+yls), 
                        Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.messages.country")));
                }else {
                    div1Header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, y1+34, 88+xls, 17+yls), 
                            Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(divOrgs[Integer.parseInt(reportForm.getOrder2())])));
                }
            }
            
            GroupFooter div1Footer = new GroupFooter();
            div1Footer.setName("div1Footer");
            div1Footer.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, null,
                    new Rectangle2D.Float(10, y1, getReportWidth(), 15), false, true));
            if(order2 == 3) {
                div1Footer.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(12-xls, y1, 88+xls, 15),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,messages.getText("generic.messages.total")+" "+messages.getText("generic.messages.country")));
            }else {
                div1Footer.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(12-xls, y1, 88+xls, 15),
                        Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,messages.getText("generic.messages.total")+" "+messages.getText(divOrgs[order2])));
            }
            x = 100;
            for(int index = 0; index < count; index++) {
                div1Footer.addElement(NumberFieldElementFactory.createNumberElement("total"+index,new Rectangle2D.Float(x, y1, 38+xls, 15),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatTwoDecimals,"total1"+index));
                x += 40;
            }
    
            div1Group.setHeader(div1Header);
            div1Group.setFooter(div1Footer);
            
            grouplist.add(div1Group);
            
            if(order3 != 4) {
                orderCount=3;
                Group div2Group = new Group();
                div2Group.setName("div2");
                div2Group.addField("divOrg1Id");
                div2Group.addField("divOrg2Id");

                GroupHeader div2Header = new GroupHeader();
                div2Header.setName("div2Header");

                div2Header.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, ThirtdLevelColor, null,
                        new Rectangle2D.Float(10, y1, getReportWidth(), 15), false, true));
                if(order3 == 3) {
                    div2Header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(12-xls, y1, 88+xls, 15),
                        Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,messages.getText("generic.messages.country")));
                }else {
                    div2Header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(12-xls, y1, 88+xls, 15),
                        Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(divOrgs[order2])));
                }
                div2Header.addElement(TextFieldElementFactory.createStringElement("headerName",new Rectangle2D.Float(100, y1, getReportWidth()-90, 15),
                        Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"", "divOrg2Name"));
                
                if(order4 == 4) {
                    x = 100;
                    for(int i = yearFrom.intValue(); i <= yearTo.intValue(); i++) {
                        int first = (i == yearFrom.intValue()) ? periodFrom.intValue() : 1;
                        int last = (i == yearTo.intValue()) ? periodTo.intValue() : 12;
                        int x2 = x;
                        for(int j = first; j <= last; j++) {
                            String month = (accumulated && j > first) ? messages.getText("generic.apmm.accumulated.ytd")+" "+messages.getText("generic.shortmonth."+j) : messages.getText("generic.shortmonth."+j);
                            div2Header.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FourLevelColor, null,
                                    new Rectangle2D.Float(x2, y1+34, 38+xls, 17+yls), false, true));
                            div2Header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x2, y1+34, 38+xls, 17+yls), 
                                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SMALL_BOLD_FONT,month));
                            x2 += 40;
                        }
                        div2Header.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,DCGray6, null,
                                new Rectangle2D.Float(x, y1+16-yls, x2 -x - 2 + xls, 16+xls+yls), false, true));
                        div2Header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y1+16-yls, x2 -x - 2 + xls, 16+xls+yls), 
                                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(i)));
                        x += x2 -x;
                    }
                    
                    div2Header.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FourLevelColor, null,
                            new Rectangle2D.Float(10, y1+34, 88+xls, 17+yls), false, true));
                    div2Header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, y1+34, 88+xls, 17+yls), 
                            Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(divOrgs[order3])));
                }
                
                GroupFooter div2Footer = new GroupFooter();
                div2Footer.setName("div2Footer");
                div2Footer.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, ThirtdLevelColor, null,
                        new Rectangle2D.Float(10, y2, getReportWidth(), 15), false, true));
                if(order3 == 3) {
                    div2Footer.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(12-xls, y1, 88+xls, 15),
                        Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,messages.getText("generic.messages.total")+" "+messages.getText("generic.messages.country")));
                }else {
                    div2Footer.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(12-xls, y2, 88+xls, 15),
                            Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.messages.total")+" "+messages.getText(divOrgs[Integer.parseInt(reportForm.getOrder2())])));
                }
                x = 100;
                for(int index = 0; index < count; index++) {
                    div2Footer.addElement(NumberFieldElementFactory.createNumberElement("total"+index,new Rectangle2D.Float(x, y2, 38+xls, 15),
                            Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"",formatTwoDecimals,"total2"+index));
                    x += 40;
                }

                div2Group.setHeader(div2Header);
                div2Group.setFooter(div2Footer);
                
                grouplist.add(div2Group);
                
                if(order4 != 4) {
                    orderCount=4;
                    Group div3Group = new Group();
                    div3Group.setName("div3");
                    div3Group.addField("divOrg1Id");
                    div3Group.addField("divOrg2Id");
                    div3Group.addField("divOrg3Id");

                    GroupHeader div3Header = new GroupHeader();
                    div3Header.setName("div3Header");

                    div3Header.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, ThirtdLevelColor, null,
                            new Rectangle2D.Float(10, y1, getReportWidth(), 15), false, true));
                    if(order4 == 3) {
                        div3Header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(12-xls, y1, 88+xls, 15),
                            Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,messages.getText("generic.messages.country")));
                    }else {
                        div3Header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(12-xls, y1, 88+xls, 15),
                            Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(divOrgs[order3])));
                    }
                    div3Header.addElement(TextFieldElementFactory.createStringElement("headerName",new Rectangle2D.Float(100, y1, getReportWidth()-90, 15),
                            Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"", "divOrg3Name"));
                    
                    x = 100;
                    for(int i = yearFrom.intValue(); i <= yearTo.intValue(); i++) {
                        int first = (i == yearFrom.intValue()) ? periodFrom.intValue() : 1;
                        int last = (i == yearTo.intValue()) ? periodTo.intValue() : 12;
                        int x2 = x;
                        for(int j = first; j <= last; j++) {
                            String month = (accumulated && j > first) ? messages.getText("generic.apmm.accumulated.ytd")+" "+messages.getText("generic.shortmonth."+j) : messages.getText("generic.shortmonth."+j);
                            div3Header.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,SixLevelColor, null,
                                    new Rectangle2D.Float(x2, y1+34, 38+xls, 17+yls), false, true));
                            div3Header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x2, y1+34, 38+xls, 17+yls), 
                                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SMALL_BOLD_FONT,month));
                            x2 += 40;
                        }
                        div3Header.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,DCGray6, null,
                                new Rectangle2D.Float(x, y1+16-yls, x2 -x - 2 + xls, 16+xls+yls), false, true));
                        div3Header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y1+16-yls, x2 -x - 2 + xls, 16+xls+yls), 
                                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(i)));
                        x += x2 -x;
                    }
                    
                    div3Header.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FourLevelColor, null,
                            new Rectangle2D.Float(10, y1+34, 88+xls, 17+yls), false, true));
                    div3Header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, y1+34, 88+xls, 17+yls), 
                            Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(divOrgs[order3])));
                    
                    GroupFooter div3Footer = new GroupFooter();
                    div3Footer.setName("div3Footer");
                    div3Footer.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, ThirtdLevelColor, null,
                            new Rectangle2D.Float(10, y2, getReportWidth(), 15), false, true));
                    if(order3 == 3) {
                        div3Footer.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(12-xls, y1, 88+xls, 15),
                            Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,messages.getText("generic.messages.total")+" "+messages.getText("generic.messages.country")));
                    }else {
                        div3Footer.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(12-xls, y2, 88+xls, 15),
                                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.messages.total")+" "+messages.getText(divOrgs[order3])));
                    }
                    x = 100;
                    for(int index = 0; index < count; index++) {
                        div3Footer.addElement(NumberFieldElementFactory.createNumberElement("total3"+index,new Rectangle2D.Float(x, y2, 38+xls, 15),
                                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"",formatTwoDecimals,"total3"+index));
                        x += 40;
                    }

                    div3Group.setHeader(div3Header);
                    div3Group.setFooter(div3Footer);
                    
                    grouplist.add(div3Group);
                }
            }
        }

        
     
        return grouplist;
    }

    protected ItemBand createItemBand() {
        ItemBand items = super.createItemBand();
        if(!reportType.equals("XLS")) {
            items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,new Rectangle2D.Float(10, 1, 89, 15), false, true));
            items.addElement(TextFieldElementFactory.createStringElement("divOrg"+orderCount+"Name",new Rectangle2D.Float(10, 1, 89, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","divOrg"+orderCount+"Name"));
        }else {
            items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,new Rectangle2D.Float(10, 0, 90, 15), false, true));
            items.addElement(TextFieldElementFactory.createStringElement("divOrg"+orderCount+"Name",new Rectangle2D.Float(10, 0, 90, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","divOrg"+orderCount+"Name"));
        }
        int x = 100;
        int count = (12 - periodFrom.intValue() + 1) + ((yearTo.intValue() - yearFrom.intValue() - 1) * 12) + periodTo.intValue();
        for(int index = 0; index < count; index++) {
            if(!reportType.equals("XLS")) {
                items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,new Rectangle2D.Float(x, 1, 38, 15), false, true));
                items.addElement(NumberFieldElementFactory.createNumberElement("apmm"+index,new Rectangle2D.Float(x, 1, 38, 15),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatTwoDecimals,"apmm"+index));
            }else {
                items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,new Rectangle2D.Float(x, 0, 40, 15), false, true));
                items.addElement(NumberFieldElementFactory.createNumberElement("apmm"+index,new Rectangle2D.Float(x, 0, 40, 15),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatTwoDecimals,"apmm"+index));
            }
            x += 40;
        }
        return items;
    }
    
    protected ReportHeader createReportHeader() {
        APMMReport reportForm = (APMMReport)form;
        ReportHeader reportHeader = new ReportHeader();

		reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 25), false, true));
        
        String title = "query.";
        title += (dataType == EventDataType.ACCIDENT) ? "a" : "i";
        title += name.substring(1).toLowerCase() + "byperiod";
        title += (accumulated) ? ".accumulated" : "";
        title += ".title";

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, getReportWidth(), 25),
        		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText(title)));
        
        int height = 25;

        height = writeParametersTitle(messages.getText("query.eventbyperiod.period"),reportHeader,height,10,210);
        height = writeParameter(messages.getText("query.eventbyperiod.from")+":",reportForm.getFrom(messages),reportHeader,height,10,90,100,120);
        height = writeParameter(messages.getText("query.eventbyperiod.to")+":",reportForm.getTo(messages),reportHeader,height,10,90,100,120);

        height = writeParametersTitle(messages.getText("generic.driver.divgeo"),reportHeader,height,10,210); 
        height = writeParameter(divGeoLabels[0]+":",divGeoValues[0],reportHeader,height,10,90,100,120);
        height = writeParameter(divGeoLabels[1]+":",divGeoValues[1],reportHeader,height,10,90,100,120);

        int maxHeight = height;
        
        height = 25;
       
        String[] divOrgs = sys.getUserSessionData().getFilter().getLevelLabels();
        for(int i = 0; i < divOrgs.length; i++) {
            if(!Util.empty(divOrgs[i])) {
                height = writeParametersTitle(messages.getText(divOrgs[i]),reportHeader,height,260,200);
                for(int j = 0; j < 3; j++) {
                    if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 1 && j== 0) && 
                            !(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 2 && j== 0)) {
                        if(!Util.empty(divOrgLabels[i][j])) {
                            height = writeParameter(divOrgLabels[i][j]+":",divOrgValues[i][j],reportHeader,height,260,80,340,120);
                        }
                    }
                }
            }
        }

        height = Math.max(maxHeight,height);
        if(order2 == 4) {
            int x = 100;
            int y1 = (reportType.equals("XLS")) ? height+15 : height+10;
            
            int xls = (reportType.equals("XLS")) ? 2 : 0;
            int yls = (reportType.equals("XLS")) ? 1 : 0;
            for(int i = yearFrom.intValue(); i <= yearTo.intValue(); i++) {
                int first = (i == yearFrom.intValue()) ? periodFrom.intValue() : 1;
                int last = (i == yearTo.intValue()) ? periodTo.intValue() : 12;
                int x2 = x;
                for(int j = first; j <= last; j++) {
                    String month = (accumulated && j > first) ? messages.getText("generic.apmm.accumulated.ytd")+" "+messages.getText("generic.shortmonth."+j) : messages.getText("generic.shortmonth."+j);
                    reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FourLevelColor, null,
                            new Rectangle2D.Float(x2, y1+34, 38+xls, 17+yls), false, true));
                    reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x2, y1+34, 38+xls, 17+yls), 
                            Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SMALL_BOLD_FONT,month));
                    x2 += 40;
                }
                reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,DCGray6, null,
                        new Rectangle2D.Float(x, y1+16-yls, x2 -x - 2 + xls, 16+xls+yls), false, true));
                reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y1+16-yls, x2 -x - 2 + xls, 16+xls+yls), 
                        Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(i)));
                x += x2 -x;
            }
            
            reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FourLevelColor, null,
                    new Rectangle2D.Float(10, y1+34, 88+xls, 17+yls), false, true));
            if(order1 == 3) {
                reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, y1+34, 88+xls, 17+yls), 
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.messages.country")));
            }else {
                String[] levelNames = (sys.isCorporation(CorporationData.BAYER)) ? sys.getUserSessionData().getFilter().getDivOrgLabels() : sys.getUserSessionData().getFilter().getLevelNames(messages);
                reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FirstLevelColor, null,
                        new Rectangle2D.Float(10, height, getReportWidth(), 18), false, true));
                int order = getOrder(1);
                String s = (order == 3) ? "corporation.country" : levelNames[order];
                if(sys.isCorporation(CorporationData.BAYER) && order > 3) {
                	s = levelNames[order-2];
                }
                reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, y1+34, 88+xls, 17+yls), 
                        Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(s)));
            }
        }            
        return reportHeader;
	}

    public int getOrder(int i) {
        try {
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
        }catch(Exception e) {}
        return 4;
    }

    protected ReportFooter createReportFooter() {
        ReportFooter reportFooter = new ReportFooter();

        
        if(!reportType.equals("XLS")) {
            reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, DCGray4, null,
                    new Rectangle2D.Float(10, 10, getReportWidth(), 15), false, true));
            reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(12, 10, 88, 15),Color.BLACK,
                ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.messages.totals")));
        }else {
            reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, DCGray4, null,
                    new Rectangle2D.Float(10, 0, getReportWidth(), 15), false, true));
            reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, 90, 15),Color.BLACK,
                    ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.messages.totals")));
        }
        int count = (12 - periodFrom.intValue() + 1) + ((yearTo.intValue() - yearFrom.intValue() - 1) * 12) + periodTo.intValue();
        int x = 100;
        for(int index = 0; index < count; index++) {
            if(!reportType.equals("XLS")) {
                reportFooter.addElement(NumberFieldElementFactory.createNumberElement("total"+index,new Rectangle2D.Float(x, 10, 40, 15),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"",formatTwoDecimals,"totalAPMM"+index));
            }else {
                reportFooter.addElement(NumberFieldElementFactory.createNumberElement("total"+index,new Rectangle2D.Float(x, 0, 40, 15),
                        Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"",formatTwoDecimals,"totalAPMM"+index));
            }
            x += 40;
        }

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

        int count = (12 - periodFrom.intValue() + 1) + ((yearTo.intValue() - yearFrom.intValue() - 1) * 12) + periodTo.intValue();
        for(int i = 0; i < count; i++) {
            
            if(order2 != 4) {
                final ItemSumFunction total1 = new ItemSumFunction();
                total1.setName("accidentTotal1"+i);
                total1.setGroup("div1");
                total1.setField("accidents"+i);
                functions.add(total1);
    
                final ItemSumFunction total2 = new ItemSumFunction();
                total2.setName("milesTotal1"+i);
                total2.setGroup("div1");
                total2.setField("miles"+i);
                functions.add(total2);
    
                BSHExpression bshExp1 = new BSHExpression();
                bshExp1.setName("total1"+i);
                bshExp1.setExpression("getValue() {" +
                        "            Number accidents = (Number) dataRow.get (\"accidentTotal1"+i+"\");" +
                        "            Number miles = (Number) dataRow.get (\"milesTotal1"+i+"\");" +
                        "            if(miles.floatValue() == 0) {" +
                        "               return new Float (0);" +
                        "            }" +
                        "            float result = accidents.floatValue() * 1000000 / miles.floatValue();" +
                        "            return new Float (result);" +
                        "}");
                functions.add(bshExp1);
                
                if(order3 != 4) {
                    final ItemSumFunction total3 = new ItemSumFunction();
                    total3.setName("accidentTotal2"+i);
                    total3.setGroup("div2");
                    total3.setField("accidents"+i);
                    functions.add(total3);

                    final ItemSumFunction total4 = new ItemSumFunction();
                    total4.setName("milesTotal2"+i);
                    total4.setGroup("div2");
                    total4.setField("miles"+i);
                    functions.add(total4);

                    BSHExpression bshExp2 = new BSHExpression();
                    bshExp2.setName("total2"+i);
                    bshExp2.setExpression("getValue() {" +
                            "            Number accidents = (Number) dataRow.get (\"accidentTotal2"+i+"\");" +
                            "            Number miles = (Number) dataRow.get (\"milesTotal2"+i+"\");" +
                            "            if(miles.floatValue() == 0) {" +
                            "               return new Float (0);" +
                            "            }" +
                            "            float result = accidents.floatValue() * 1000000 / miles.floatValue();" +
                            "            return new Float (result);" +
                            "}");
                    functions.add(bshExp2);
                    
                    if(order4 != 4) {
                        final ItemSumFunction total5 = new ItemSumFunction();
                        total5.setName("accidentTotal3"+i);
                        total5.setGroup("div3");
                        total5.setField("accidents"+i);
                        functions.add(total5);

                        final ItemSumFunction total6 = new ItemSumFunction();
                        total6.setName("milesTotal3"+i);
                        total6.setGroup("div3");
                        total6.setField("miles"+i);
                        functions.add(total6);

                        BSHExpression bshExp3 = new BSHExpression();
                        bshExp3.setName("total3"+i);
                        bshExp3.setExpression("getValue() {" +
                                "            Number accidents = (Number) dataRow.get (\"accidentTotal3"+i+"\");" +
                                "            Number miles = (Number) dataRow.get (\"milesTotal3"+i+"\");" +
                                "            if(miles.floatValue() == 0) {" +
                                "               return new Float (0);" +
                                "            }" +
                                "            float result = accidents.floatValue() * 1000000 / miles.floatValue();" +
                                "            return new Float (result);" +
                                "}");
                        functions.add(bshExp3);

                    }
                }
            }


            final ItemSumFunction total5 = new ItemSumFunction();
            total5.setName("accidentT"+i);
            total5.setField("accidents"+i);
            functions.add(total5);

            final ItemSumFunction total6 = new ItemSumFunction();
            total6.setName("milesT"+i);
            total6.setField("miles"+i);
            functions.add(total6);

            BSHExpression bshExp3 = new BSHExpression();
            bshExp3.setName("totalAPMM"+i);
            bshExp3.setExpression("getValue() {" +
                    "            Number accidents = (Number) dataRow.get (\"accidentT"+i+"\");" +
                    "            Number miles = (Number) dataRow.get (\"milesT"+i+"\");" +
                    "            if(miles.floatValue() == 0) {" +
                    "               return new Float (0);" +
                    "            }" +
                    "            float result = accidents.floatValue() * 1000000 / miles.floatValue();" +
                    "            return new Float (result);" +
                    "}");
            functions.add(bshExp3);
        }
        
        return functions;
    }

}
