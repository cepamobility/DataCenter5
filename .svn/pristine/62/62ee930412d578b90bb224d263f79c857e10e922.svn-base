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

import presentation.report.ReportFactory;
import presentation.translator.web.queries.ReportAccidentCountForm;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.administration.corporation.tables.CorporationData;
import communication.language.Text;

public class ReportAccidentCountFactory extends ReportFactory {

    protected Text messages;
    private String reportType;
    private SystemAccess sys;


    public ReportAccidentCountFactory(String name, TableModel model, ActionForm form, 
    		SystemAccess sys, Text messages,String reportType)
    {
        super(name, model, form);
        this.messages = messages;
        parseDivisionValues(sys,messages);
        this.reportType = reportType;
        this.sys = sys;
    }

    protected int getWidth() {
    	return getReportWidth()+82;
    }
    
    //it is declared but it is not used 
    protected int getReportWidth() {
    	int width = 650;
    	if(sys.getCorporationId() == CorporationData.MONSANTO) {
    		width += 160;
    	}
        return width;
    }

    protected GroupList createGroups() {
        
        String[] divOrgs = sys.getUserSessionData().getFilter().getPrincipalLabels(); 

        GroupList grouplist = super.createGroups();

        Group chartGroup = new Group();
        chartGroup.setName("charts");
        chartGroup.addField("id");

        GroupHeader chartHeader = new GroupHeader();
        chartHeader.setName("chartHeader");

        if(getOrder(2) == 4) {
            
            count = 1;
            chartHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,Color.LIGHT_GRAY, null,
                    new Rectangle2D.Float(10, 25, getReportWidth(), 25), false, true));
            if(getOrder(1) == 3) {
                chartHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 25, 90, 18),
                        Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("corporation.country")));
            }else {
                chartHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 25, 90, 18),
                        Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(divOrgs[getOrder(1)])));
            }
            chartHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(100, 25, 80, 18),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.eventcount.accidentcount")));
            chartHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(180, 25, 80, 18),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.eventcount.accidentcost")));
            chartHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(260, 25, 80, 18),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.eventcount.incidentcount")));
            chartHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(340, 25, 80, 18),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.eventcount.incidentcost")));
        	int x = 420;
            if(sys.getCorporationId() == CorporationData.MONSANTO) {
            	chartHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, 25, 80, 18),
                        Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.eventcount.nearmisscount")));
            	x += 80;
                chartHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, 25, 80, 18),
                        Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.eventcount.nearmisscost")));
            	x += 80;
        	}
        	chartHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, 25, 80, 18),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.eventcount.finecount")));
        	x += 80;
            chartHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, 25, 80, 18),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.eventcount.finecost")));
        	x += 80;
            chartHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, 25, 80, 18),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.eventcount.totalcosto")));
        }
        
        GroupFooter chartFooter = new GroupFooter();
        chartFooter.setName("chartFooter");

        chartFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(
        		null, FirstLevelColor, null, new Rectangle2D.Float(10, 10, getReportWidth(), 25), false, true));
        chartFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 90, 15),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,messages.getText("query.accidentcount.totals")));
        chartFooter.addElement(NumberFieldElementFactory.createNumberElement("accident_countTotal",new Rectangle2D.Float(100, 10, 80, 15),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatNoDecimals,"accidentCountTotal"));
        chartFooter.addElement(NumberFieldElementFactory.createNumberElement("accident_countTotal",new Rectangle2D.Float(180, 10, 80, 15),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatNoDecimals,"accidentCostTotal"));
        chartFooter.addElement(NumberFieldElementFactory.createNumberElement("incident_countTotal",new Rectangle2D.Float(260, 10, 80, 15),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatNoDecimals,"incidentCountTotal"));
        chartFooter.addElement(NumberFieldElementFactory.createNumberElement("incident_countTotal",new Rectangle2D.Float(340, 10, 80, 15),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatNoDecimals,"incidentCostTotal"));
        int x = 420;
        if(sys.getCorporationId() == CorporationData.MONSANTO) {
            chartFooter.addElement(NumberFieldElementFactory.createNumberElement("fine_countTotal",new Rectangle2D.Float(x, 10, 80, 15),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatNoDecimals,"nearmissCountTotal"));
            x += 80;
            chartFooter.addElement(NumberFieldElementFactory.createNumberElement("fine_countTotal",new Rectangle2D.Float(x, 10, 80, 15),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatNoDecimals,"nearmissCostTotal"));
            x += 80;
        }
        chartFooter.addElement(NumberFieldElementFactory.createNumberElement("fine_countTotal",new Rectangle2D.Float(x, 10, 80, 15),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatNoDecimals,"fineCountTotal"));
        x += 80;
        chartFooter.addElement(NumberFieldElementFactory.createNumberElement("fine_countTotal",new Rectangle2D.Float(x, 10, 80, 15),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatNoDecimals,"fineCostTotal"));
        x += 80;
        chartFooter.addElement(NumberFieldElementFactory.createNumberElement("fine_countTotal",new Rectangle2D.Float(x, 10, 80, 15),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatNoDecimals,"costTotal"));

        chartGroup.setHeader(chartHeader);
        chartGroup.setFooter(chartFooter);
        
        grouplist.add(chartGroup);

        
        Collection fields = new LinkedList();
        fields.add("id");
        String[] levelNames = sys.getUserSessionData().getFilter().getLevelNames(messages);
        for(int i = 1; i <= levelNames.length; i++) {
            int order = getOrder(i);
            if(getOrder(i+1) != 4) {
                count = i+1;
                String totalName = (order == 3) ? "corporation.country" : levelNames[order];
                fields.add("div"+i+"Id");
                Element[] headerElements = null;
                if(getOrder(i+2) != 4) {
                    headerElements = new Element[3]; 
                    headerElements[0] = StaticShapeElementFactory.createRectangleShapeElement(null, groupsHeaderColors[i], null,
                            new Rectangle2D.Float(10, 10, getReportWidth(), 15), false, true);
                    headerElements[1] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 90, 15),Color.WHITE,
                            ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,messages.getText(totalName)+": ");
                    headerElements[2] = TextFieldElementFactory.createStringElement("div"+i+"Name",new Rectangle2D.Float(100, 10, 160, 15),Color.WHITE,
                            ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"","div"+i+"Name");
                }else {
                	int size = (sys.getCorporationId() == CorporationData.MONSANTO) ? 14 : 12;
                    headerElements = new Element[size];
                    
                    headerElements[0] = StaticShapeElementFactory.createRectangleShapeElement(null, groupsHeaderColors[i], null,
                            new Rectangle2D.Float(10, 10, getReportWidth(), 15), false, true);
                    headerElements[1] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 90, 15),Color.WHITE,
                        ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,messages.getText(totalName)+": ");
                    headerElements[2] = TextFieldElementFactory.createStringElement("div"+i+"Name",new Rectangle2D.Float(100, 10, 160, 15),Color.WHITE,
                            ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"","div"+i+"Name");

                    headerElements[3] = StaticShapeElementFactory.createRectangleShapeElement(null,FourLevelColor, null,
                            new Rectangle2D.Float(10, 25, getReportWidth(), 25), false, true);
                    if(getOrder(i+1) == 3) {
                        headerElements[4] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 25, 90, 18),
                                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("corporation.country"));
                    }else {
                        headerElements[4] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 25, 90, 18),
                                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(divOrgs[getOrder(i+1)]));
                    }
                    headerElements[5] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(100, 25, 80, 18),
                            Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.eventcount.accidentcount"));
                    headerElements[6] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(180, 25, 80, 18),
                            Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.eventcount.accidentcost"));
                    headerElements[7] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(260, 25, 80, 18),
                            Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.eventcount.incidentcount"));
                    headerElements[8] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(340, 25, 80, 18),
                            Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.eventcount.accidentcost"));
                	x = 420;
                    if(sys.getCorporationId() == CorporationData.MONSANTO) {
                        headerElements[12] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, 25, 80, 18),
                                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.eventcount.nearmisscount"));
                        x += 80;
                        headerElements[13] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, 25, 80, 18),
                                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.eventcount.nearmisscost"));
                        x += 80;
                    }
                    headerElements[9] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, 25, 80, 18),
                            Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.eventcount.finecount"));
                    x += 80;
                    headerElements[10] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, 25, 80, 18),
                            Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.eventcount.finecost"));
                    x += 80;
                    headerElements[11] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, 25, 80, 18),
                            Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.eventcount.totalcosto"));
                }
 
                int size = (sys.getCorporationId() == CorporationData.MONSANTO) ? 9 : 7;
                Element[] footerTotals = new Element[size]; 
                footerTotals[0] = NumberFieldElementFactory.createNumberElement("total1"+i,new Rectangle2D.Float(100, 10, 80, 15),
                        Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatNoDecimals, "total1"+i);
                footerTotals[1] = NumberFieldElementFactory.createNumberElement("total2"+i,new Rectangle2D.Float(180, 10, 80, 15),
                        Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatNoDecimals,"total2"+i);
                footerTotals[2] = NumberFieldElementFactory.createNumberElement("total3"+i,new Rectangle2D.Float(260, 10, 80, 15),
                        Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatNoDecimals,"total3"+i);
                footerTotals[3] = NumberFieldElementFactory.createNumberElement("total4"+i,new Rectangle2D.Float(340, 10, 80, 15),
                        Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatNoDecimals,"total4"+i);
            	x = 420;
                if(sys.getCorporationId() == CorporationData.MONSANTO) {
                    footerTotals[7] = NumberFieldElementFactory.createNumberElement("total8"+i,new Rectangle2D.Float(x, 10, 80, 15),
                            Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatNoDecimals,"total8"+i);
                    x += 80;
                    footerTotals[8] = NumberFieldElementFactory.createNumberElement("total9"+i,new Rectangle2D.Float(x, 10, 80, 15),
                            Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatNoDecimals,"total9"+i);
                    x += 80;
                }
                footerTotals[4] = NumberFieldElementFactory.createNumberElement("total5"+i,new Rectangle2D.Float(x, 10, 80, 15),
                        Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatNoDecimals,"total5"+i);
                x += 80;
                footerTotals[5] = NumberFieldElementFactory.createNumberElement("total6"+i,new Rectangle2D.Float(x, 10, 80, 15),
                        Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatNoDecimals,"total6"+i);
                x += 80;
                footerTotals[6] = NumberFieldElementFactory.createNumberElement("total7"+i,new Rectangle2D.Float(x, 10, 80, 15),
                        Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatNoDecimals,"total7"+i);

                grouplist.add(createGroup(fields.toArray(), footerTotals, headerElements, "Group"+i, messages, messages.getText(totalName),90,10));
            }
        }

        
        return grouplist;
    }

    private int getOrder(int i) {
        switch(i) {
            case 1:
                if(((ReportAccidentCountForm)form).getOrder1() == null) {
                    return 4;
                }
                return Integer.parseInt(((ReportAccidentCountForm)form).getOrder1());
            case 2:
                if(((ReportAccidentCountForm)form).getOrder2() == null) {
                    return 4;
                }
                return Integer.parseInt(((ReportAccidentCountForm)form).getOrder2());
            case 3:
                if(((ReportAccidentCountForm)form).getOrder3() == null) {
                    return 4;
                }
                return Integer.parseInt(((ReportAccidentCountForm)form).getOrder3());
            case 4:
                if(((ReportAccidentCountForm)form).getOrder4() == null) {
                    return 4;
                }
                return Integer.parseInt(((ReportAccidentCountForm)form).getOrder4());
        }
        return 4;
    }

    protected ItemBand createItemBand() {
        ItemBand items = super.createItemBand();        
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 15), false, true));
        items.addElement(TextFieldElementFactory.createStringElement("div"+count+"Name",new Rectangle2D.Float(10, 0, 90, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","div"+count+"Name"));
        items.addElement(NumberFieldElementFactory.createNumberElement("accidentCount",new Rectangle2D.Float(100, 0, 80, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals, "accidentCount"));
        items.addElement(NumberFieldElementFactory.createNumberElement("accidentCost",new Rectangle2D.Float(180, 0, 80, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals, "accidentCost"));
        items.addElement(NumberFieldElementFactory.createNumberElement("incidentCount",new Rectangle2D.Float(260, 0, 80, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals, "incidentCount"));
        items.addElement(NumberFieldElementFactory.createNumberElement("incidentCost",new Rectangle2D.Float(340, 0, 80, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals, "incidentCost"));
    	int x = 420;
        if(sys.getCorporationId() == CorporationData.MONSANTO) {
            items.addElement(NumberFieldElementFactory.createNumberElement("fineCount",new Rectangle2D.Float(x, 0, 80, 15),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"0",formatNoDecimals, "nearmissCount"));
            x += 80;
            items.addElement(NumberFieldElementFactory.createNumberElement("fineCost",new Rectangle2D.Float(x, 0, 80, 15),   
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"0",formatNoDecimals, "nearmissCost"));
            x += 80;
        }
        items.addElement(NumberFieldElementFactory.createNumberElement("fineCount",new Rectangle2D.Float(x, 0, 80, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"0",formatNoDecimals, "fineCount"));
        x += 80;
        items.addElement(NumberFieldElementFactory.createNumberElement("fineCost",new Rectangle2D.Float(x, 0, 80, 15),   
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"0",formatNoDecimals, "fineCost"));
        x += 80;
        items.addElement(NumberFieldElementFactory.createNumberElement("totalCost",new Rectangle2D.Float(x, 0, 80, 15),   
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals, "totalCost"));
        return items;
    }
    
    protected ReportHeader createReportHeader() {
        
        ReportHeader reportHeader = new ReportHeader();
        
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 25), false, true));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, 590, 25),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("query.eventcount.title")));

        int height = 25;
        
        height = writeParametersTitle(messages.getText("generic.driver.divgeo"),reportHeader,height,10,170); 
        height = writeParameter(divGeoLabels[0]+":",divGeoValues[0],reportHeader,height,10,90,100,80);
        height = writeParameter(divGeoLabels[1]+":",divGeoValues[1],reportHeader,height,10,90,100,80);
        
        height = 25;
        String[] divOrgs = sys.getUserSessionData().getFilter().getLevelLabels();
        for(int i = 0; i < divOrgs.length; i++) {
            if(!Util.empty(divOrgs[i])) {
                height = writeParametersTitle(messages.getText(divOrgs[i]),reportHeader,height,260,160);
                for(int j = 0; j < 3; j++) {
                    if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 1 && j== 0) && 
                            !(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 2 && j== 0)) {
                        if(!Util.empty(divOrgLabels[i][j])) {
                            height = writeParameter(divOrgLabels[i][j]+":",divOrgValues[i][j],reportHeader,height,260,80,340,80);
                        }
                    }
                }
            }
        }

        height = 25;
        height = writeParametersTitle(messages.getText("generic.report.otherdata"),reportHeader,height,500,160); 
        height = writeParameter(messages.getText("query.eventbyperiod.from")+":",((ReportAccidentCountForm)form).getFrom(messages),reportHeader,height,500,80,580,80);
        height = writeParameter(messages.getText("query.eventbyperiod.to")+":",((ReportAccidentCountForm)form).getTo(messages),reportHeader,height,500,80,580,80);
        height = writeParameter(messages.getText("query.accidentcount.vehicleType")+": ",messages.getText(((ReportAccidentCountForm)form).getVehicletype()),reportHeader,height,500,80,580,80);


        return reportHeader;
    }

    protected ReportFooter createReportFooter() {
        ReportFooter reportFooter = new ReportFooter();
        return reportFooter;
    }

    protected ExpressionCollection createFunctions() {
        ExpressionCollection functions = super.createFunctions();

        for(int i = 1; i < 4; i++) {
            if(getOrder(i+1) != 4) {
                final ItemSumFunction total1 = new ItemSumFunction();
                total1.setName("total1"+i);
                total1.setGroup("Group"+i);
                total1.setField("accidentCount");
                functions.add(total1);
        
                final ItemSumFunction total2 = new ItemSumFunction();
                total2.setName("total2"+i);
                total2.setGroup("Group"+i);
                total2.setField("accidentCost");
                functions.add(total2);
        
                final ItemSumFunction total3 = new ItemSumFunction();
                total3.setName("total3"+i);
                total3.setGroup("Group"+i);
                total3.setField("incidentCount");
                functions.add(total3);
        
                final ItemSumFunction total4 = new ItemSumFunction();
                total4.setName("total4"+i);
                total4.setGroup("Group"+i);
                total4.setField("incidentCost");
                functions.add(total4);
        
                final ItemSumFunction total5 = new ItemSumFunction();
                total5.setName("total5"+i);
                total5.setGroup("Group"+i);
                total5.setField("fineCount");
                functions.add(total5);

                final ItemSumFunction total6 = new ItemSumFunction();
                total6.setName("total6"+i);
                total6.setGroup("Group"+i);
                total6.setField("fineCost");
                functions.add(total6);

                final ItemSumFunction total7 = new ItemSumFunction();
                total7.setName("total7"+i);
                total7.setGroup("Group"+i);
                total7.setField("totalCost");
                functions.add(total7);

                if(sys.getCorporationId() == CorporationData.MONSANTO) {
                    final ItemSumFunction total8 = new ItemSumFunction();
                    total8.setName("total8"+i);
                    total8.setGroup("Group"+i);
                    total8.setField("nearmissCount");
                    functions.add(total8);

                    final ItemSumFunction total9 = new ItemSumFunction();
                    total9.setName("total9"+i);
                    total9.setGroup("Group"+i);
                    total9.setField("nearmissCost");
                    functions.add(total9);
                	
                }
            }
        }

        final ItemSumFunction accidentCountTotal = new ItemSumFunction();
        accidentCountTotal.setName("accidentCountTotal");
        accidentCountTotal.setField("accidentCount");
        functions.add(accidentCountTotal);

        final ItemSumFunction accidentCostTotal = new ItemSumFunction();
        accidentCostTotal.setName("accidentCostTotal");
        accidentCostTotal.setField("accidentCost");
        functions.add(accidentCostTotal);

        final ItemSumFunction incidentCountTotal = new ItemSumFunction();
        incidentCountTotal.setName("incidentCountTotal");
        incidentCountTotal.setField("incidentCount");
        functions.add(incidentCountTotal);

        final ItemSumFunction incidentCostTotal = new ItemSumFunction();
        incidentCostTotal.setName("incidentCostTotal");
        incidentCostTotal.setField("incidentCost");
        functions.add(incidentCostTotal);

        final ItemSumFunction fineCountTotal = new ItemSumFunction();
        fineCountTotal.setName("fineCountTotal");
        fineCountTotal.setField("fineCount");
        functions.add(fineCountTotal);

        final ItemSumFunction fineCostTotal = new ItemSumFunction();
        fineCostTotal.setName("fineCostTotal");
        fineCostTotal.setField("fineCost");
        functions.add(fineCostTotal);
        
        if(sys.getCorporationId() == CorporationData.MONSANTO) {
            final ItemSumFunction nearmissCountTotal = new ItemSumFunction();
            nearmissCountTotal.setName("nearmissCountTotal");
            nearmissCountTotal.setField("nearmissCount");
            functions.add(nearmissCountTotal);

            final ItemSumFunction nearmissCostTotal = new ItemSumFunction();
            nearmissCostTotal.setName("nearmissCostTotal");
            nearmissCostTotal.setField("nearmissCost");
            functions.add(nearmissCostTotal);
        }
        
        final ItemSumFunction costTotal = new ItemSumFunction();
        costTotal.setName("costTotal");
        costTotal.setField("totalCost");
        functions.add(costTotal);

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
