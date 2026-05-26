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
import org.jfree.report.Element;
import org.jfree.report.ElementAlignment;
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
import org.jfree.report.modules.misc.beanshell.BSHExpression;
import org.jfree.ui.FloatDimension;

import presentation.report.ReportFactory;
import presentation.translator.web.queries.APMMReport;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.administration.corporation.tables.CorporationData;
import communication.language.Text;
import communication.util.Filter;

public class ReportAPMMFactory extends ReportFactory {

    protected Text messages;
    private String reportType;
    
    private int months;
    private SystemAccess sys;
    private int corporationId;
    private int count;
    
    private boolean showDecimals;
    private String distanceUnit;
    
    public ReportAPMMFactory(String name, TableModel model, ActionForm form, SystemAccess sys, Text messages,int months,boolean showDecimals) {
        super(name, model, form, Filter.CORPORATION);
        this.messages = messages;
        this.months = months; 
        parseDivisionValues(sys,messages);
        this.sys = sys;
        this.corporationId = sys.getUserSessionData().getFilter().getCorporationId().intValue();
        this.showDecimals = showDecimals;
        this.distanceUnit = (corporationId != CorporationData.CIMSA) ? "query.apmm.miles" : "datatype.distanceunit.kilometers";
    }
    
    
    public ReportAPMMFactory(String name, TableModel model, ActionForm form, 
    		SystemAccess sys, Text messages, String reportType,int months,boolean showDecimals) {
        super(name, model, form, Filter.CORPORATION);
        this.messages = messages;
        parseDivisionValues(sys,messages);
        this.reportType = reportType;
        this.months = months; 
        this.sys = sys;
        this.corporationId = sys.getUserSessionData().getFilter().getCorporationId().intValue();
        this.showDecimals = showDecimals;
        this.distanceUnit = (!sys.isForestalUnified()) ? "query.apmm.miles" : "datatype.distanceunit.kilometers";
    }

    protected int getWidth() {
    	return getReportWidth()+84;
    }

//  it is declared but it is not used 
    protected int getReportWidth() {
    	int value = 700;
    	if(sys.isCorporation(new int[]{CorporationData.JJ,CorporationData.MONSANTO}))
    		value += 80;
    	if(sys.isBrasilCorporation())
    		value += 120;
    	return value;
    }
    
    protected GroupList createGroups() {
        
        String[] divOrgs = (sys.isCorporation(CorporationData.BAYER)) ? sys.getUserSessionData().getFilter().getDivOrgLabels() : sys.getUserSessionData().getFilter().getPrincipalLabels(); 

        GroupList grouplist = super.createGroups();
        
        int y = (reportType.equals("XLS")) ? 18 : 10;
        Collection fields = new LinkedList();
        String[] levelNames = (sys.isCorporation(CorporationData.BAYER)) ? sys.getUserSessionData().getFilter().getDivOrgLabels() : sys.getUserSessionData().getFilter().getLevelNames(messages);
        for(int i = 1; i <= levelNames.length; i++) {
            int order = getOrder(i);
            if(getOrder(i+1) != 4) {
                count = i+1;
                String totalName = (order == 3) ? "corporation.country" : levelNames[order];
                if(sys.isCorporation(CorporationData.BAYER) && order > 3) {
                	totalName = levelNames[order-2];
                }
                fields.add("divOrg"+i+"Id");
                int sizeFooter = 7;
                int sizeHeader = 12;
            	if(sys.isCorporation(new int[]{CorporationData.JJ,CorporationData.MONSANTO})) {
                    sizeFooter++;
                    sizeHeader++;
            	}
            	if(sys.isBrasilCorporation() && !sys.isCorporation(CorporationData.SYNGENTA_BR)){
                    sizeFooter += 2;
                    sizeHeader += 2;
            	}
                Element[] headerElements = new Element[sizeHeader];
                Element[] footerTotals = new Element[sizeFooter];
                if(getOrder(i+2) != 4) {
                    headerElements = new Element[3]; 
                    headerElements[0] = StaticShapeElementFactory.createRectangleShapeElement(null, groupsHeaderColors[i-1], null,
                            new Rectangle2D.Float(10, y, getReportWidth(), 15), false, true);
                    headerElements[1] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, y, 70, 15),Color.WHITE,
                        ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,messages.getText(totalName)+": ");
                    headerElements[2] = TextFieldElementFactory.createStringElement("divOrg"+i+"Name",new Rectangle2D.Float(80, y, 200, 15),Color.WHITE,
                            ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"","divOrg"+i+"Name");
                }else {
                    
                    headerElements[0] = StaticShapeElementFactory.createRectangleShapeElement(null, groupsHeaderColors[i-1], null,
                            new Rectangle2D.Float(10, y, getReportWidth(), 15), false, true);
                    headerElements[1] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, y, 70, 15),Color.WHITE,
                        ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,messages.getText(totalName)+": ");
                    headerElements[2] = TextFieldElementFactory.createStringElement("divOrg"+i+"Name",new Rectangle2D.Float(80, y, 180, 15),Color.WHITE,
                            ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"","divOrg"+i+"Name");

                    headerElements[3] = StaticShapeElementFactory.createRectangleShapeElement(null,FirstLevelColor, null,
                            new Rectangle2D.Float(10, y+15, getReportWidth(), 18), false, true);
                    int x = 10;
                    int count = 4;
                    if(getOrder(i+1) == 3) {
                        headerElements[count] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y+15, 160, 18),
                                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("corporation.country"));
                    }else {
                    	if(sys.isCorporation(CorporationData.BAYER) && getOrder(i+1) > 4) {
	                        headerElements[count] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y+15, 160, 18),
	                                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(divOrgs[getOrder(i+1)-2]));
                    	}else {
	                        headerElements[count] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y+15, 160, 18),
	                                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(divOrgs[getOrder(i+1)]));
                    	}
                    }
                    x += 160;
                    count++;
                    headerElements[count] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y+15, 90, 18),
                            Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.apmm.vehicle_count"));
                    x += 90;
                    count++;
                    
                    if(!sys.isCorporation(CorporationData.SYNGENTA_BR)) {
                    	headerElements[count] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y+15, 50, 18),
                            Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
                            messages.getText(distanceUnit));
                    	x += 50;
                    	count++;
                    }
                    if(sys.isBrasilCorporation()) {
                        headerElements[count] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y+15, 50, 18),
                                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
                                messages.getText("datatype.distanceunit.kilometers"));
                        x += 50;
                        count++;
                    }
                    
                    headerElements[count] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y+15, 80, 18),
                            Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.apmm.accident_count"));
                    x += 80;
                    count++;
                    if(!sys.isCorporation(CorporationData.SYNGENTA_BR)) {
                    	headerElements[count] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y+15, 70, 18),
                            Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.apmm.apmm"));
                    	x += 70;
                    	count++;
                    }
                    if(sys.isBrasilCorporation()) {
                        headerElements[count] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y+15, 70, 18),
                                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.apmm.apmk"));
                        x += 70;
                        count++;
                    }
                    
                    headerElements[count] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y+15, 90, 18),
                            Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.apmm.vehicle_percent"));
                    x += 90;
                    count++;

                    headerElements[count] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y+15, 80, 18),
                            Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.apmm.incident_count"));
                    x += 80;
                    count++;

                    if(corporationId == CorporationData.JJ) {
                    	headerElements[count] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y+15, 80, 18),
                                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.apmm.injuries_count"));
                        x+=80;
                        count++;
                    }
                    headerElements[count] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y+15, 70, 18),
                                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.apmm.ipmm"));
                	x += 70;
                    count++;
                    if(corporationId == CorporationData.MONSANTO) {
                        headerElements[count] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y+15, 80, 18),
                                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.apmm.nearmiss"));
                    	x += 80;
                        count++;
                    }
                   
                }
 
                
                int x = 170;
                int count = 0;
                if(showDecimals) {
                    footerTotals[count] = NumberFieldElementFactory.createNumberElement("total1"+i,new Rectangle2D.Float(x, y, 90, 15),
                        Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatTwoDecimals, "total1"+i);
                }else {
                    footerTotals[count] = NumberFieldElementFactory.createNumberElement("total1"+i,new Rectangle2D.Float(x, y, 90, 15),
                            Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatNoDecimals, "total1"+i);
                }
                x += 90;
                count++;
                if(!sys.isCorporation(CorporationData.SYNGENTA_BR)) {
                	footerTotals[count] = NumberFieldElementFactory.createNumberElement("total2"+i,new Rectangle2D.Float(x, y, 50, 15),
                        Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatNoDecimals,"total2"+i);
                	x += 50;
                	count++;
                }
                if(sys.isBrasilCorporation()) {
                    footerTotals[count] = NumberFieldElementFactory.createNumberElement("total12"+i,new Rectangle2D.Float(x, y, 50, 15),
                            Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatNoDecimals,"total12"+i);
                    x += 50;
                    count++;
                }
                footerTotals[count] = NumberFieldElementFactory.createNumberElement("total3"+i,new Rectangle2D.Float(x, y, 80, 15),
                        Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatNoDecimals,"total3"+i);
                x += 80;
                count++;
                if(!sys.isCorporation(CorporationData.SYNGENTA_BR)) {
                	footerTotals[count] = NumberFieldElementFactory.createNumberElement("total6"+i,new Rectangle2D.Float(x, y, 70, 15),
                        Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatTwoDecimals,"total6"+i);
                	x += 70;
                    count++;
                }
                if(sys.isBrasilCorporation()) {
                    footerTotals[count] = NumberFieldElementFactory.createNumberElement("total13"+i,new Rectangle2D.Float(x, y, 70, 15),
                            Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatTwoDecimals,"total13"+i);
                    x += 70;
                    count++;
                }
                footerTotals[count] = NumberFieldElementFactory.createNumberElement("total7"+i,new Rectangle2D.Float(x, y, 90, 15),
                        Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatOneDecimals,"total7"+i);
                x += 90;
                count++;
                footerTotals[count] = NumberFieldElementFactory.createNumberElement("total4"+i,new Rectangle2D.Float(x, y, 80, 15),
                        Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatNoDecimals,"total4"+i);
                x += 80;
                count++;
                if(corporationId == CorporationData.JJ) {
                	footerTotals[count] = NumberFieldElementFactory.createNumberElement("total9"+i,new Rectangle2D.Float(x, y, 80, 15),
                        Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatNoDecimals,"total9"+i);
                    x += 80;
                    count++;
                	footerTotals[count] = NumberFieldElementFactory.createNumberElement("total10"+i,new Rectangle2D.Float(x, y, 70, 15),
                        Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatTwoDecimals,"total10"+i);
                    x += 70;
                    count++;
                }else{
                    footerTotals[count] = NumberFieldElementFactory.createNumberElement("total5"+i,new Rectangle2D.Float(x, y, 70, 15),
                            Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatTwoDecimals,"total5"+i);
                    x += 70;
                    count++;
                    if(corporationId == CorporationData.MONSANTO) {
                    	footerTotals[count] = NumberFieldElementFactory.createNumberElement("total11"+i,new Rectangle2D.Float(x, y, 80, 15),
                            Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatTwoDecimals,"total11"+i);                    	
                        x += 80;
                        count++;
                    }
                }
                grouplist.add(createGroup(fields.toArray(), footerTotals, headerElements, "Group"+i, messages, messages.getText(totalName),160,y));
            }
        }
        
        return grouplist;
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

    protected ItemBand createItemBand() {
        
        ItemBand items = super.createItemBand();        
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 15), false, true));
        int x = 10;
        items.addElement(TextFieldElementFactory.createStringElement("divOrg"+count+"Name",new Rectangle2D.Float(x, 0, 160, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","divOrg"+count+"Name"));
        x += 160;
        if(showDecimals) {
            items.addElement(NumberFieldElementFactory.createNumberElement("vehicle_count",new Rectangle2D.Float(x, 0, 90, 15),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatTwoDecimals, "vehicle_count"));
        }else {
            items.addElement(NumberFieldElementFactory.createNumberElement("vehicle_count",new Rectangle2D.Float(x, 0, 90, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals, "vehicle_count"));
        }
        x += 90;
        if(!sys.isCorporation(CorporationData.SYNGENTA_BR)) {
        	items.addElement(NumberFieldElementFactory.createNumberElement("miles",new Rectangle2D.Float(x, 0, 50, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals,"miles"));
        	x += 50;
        }
        if(sys.isBrasilCorporation()) {
            items.addElement(NumberFieldElementFactory.createNumberElement("kms",new Rectangle2D.Float(x, 0, 50, 15),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals,"kms"));
            x += 50;
        }
        items.addElement(NumberFieldElementFactory.createNumberElement("accident_count",new Rectangle2D.Float(x, 0, 80, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals,"accident_count"));
        x += 80;
        if(!sys.isCorporation(CorporationData.SYNGENTA_BR)) {
        	items.addElement(NumberFieldElementFactory.createNumberElement("apmm",new Rectangle2D.Float(x, 0, 70, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatTwoDecimals,"apmm"));
        	x += 70;
        }
        if(sys.isBrasilCorporation()) {
            items.addElement(NumberFieldElementFactory.createNumberElement("apmm",new Rectangle2D.Float(x, 0, 70, 15),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatTwoDecimals,"apmk"));
            x += 70;
        }
        items.addElement(NumberFieldElementFactory.createNumberElement("vehicle_percent",new Rectangle2D.Float(x, 0, 90, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatOneDecimals,"vehicle_percent"));
        x += 90;
        items.addElement(NumberFieldElementFactory.createNumberElement("incident_count",new Rectangle2D.Float(x, 0, 80, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals,"incident_count"));
        x += 80;
        if(corporationId != CorporationData.JJ) {
            items.addElement(NumberFieldElementFactory.createNumberElement("ipmm",new Rectangle2D.Float(x, 0, 70, 15),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatTwoDecimals,"ipmm"));
            x += 70;
            if(corporationId == CorporationData.MONSANTO) {
                items.addElement(NumberFieldElementFactory.createNumberElement("nearmiss_count",new Rectangle2D.Float(x, 0, 80, 15),
                        Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatTwoDecimals,"nearmiss_count"));
                x += 80;
            }
        }else{
        	items.addElement(NumberFieldElementFactory.createNumberElement("injuries_count",new Rectangle2D.Float(x, 0, 80, 15),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals,"injuries_count"));
            x += 80;
        	items.addElement(NumberFieldElementFactory.createNumberElement("injuries",new Rectangle2D.Float(x, 0, 70, 15),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatTwoDecimals,"injuries"));
            x += 70;
        }
        return items;
    }
    
    protected ReportHeader createReportHeader() {
        
        ReportHeader reportHeader = new ReportHeader();
        
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 25), false, true));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, getReportWidth(), 25),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("fleet.query.apmm.division")));

        int height = 43;

        height = writeParametersTitle(messages.getText("query.mileage.parameters"),reportHeader,height,10,160);
        height = writeParameter(messages.getText("query.mileage.from")+":",((APMMReport)form).getFrom(messages),reportHeader,height,10,70,80,110);
        height = writeParameter(messages.getText("query.mileage.to")+":",((APMMReport)form).getTo(messages),reportHeader,height,10,70,80,110);

        height = writeParametersTitle(messages.getText("generic.fleet.divgeo"),reportHeader,height,10,160); 
        height = writeParameter(divGeoLabels[0]+":",divGeoValues[0],reportHeader,height,10,70,80,90);
        height = writeParameter(divGeoLabels[1]+":",divGeoValues[1],reportHeader,height,10,70,80,90);

        int maxHeight = height;
        
        height = 43;
        String[] divOrgs = sys.getUserSessionData().getFilter().getLevelLabels();
        for(int i = 0; i < divOrgs.length; i++) {
            if(!Util.empty(divOrgs[i])) {
                height = writeParametersTitle(messages.getText(divOrgs[i]),reportHeader,height,310,230);
                for(int j = 0; j < 3; j++) {
                    if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 1 && j== 0) && 
                            !(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 2 && j== 0)) {
                        if(!Util.empty(divOrgLabels[i][j])) {
                            height = writeParameter(divOrgLabels[i][j]+":",divOrgValues[i][j],reportHeader,height,310,80,390,150);
                        }
                    }
                }
            }
        }
        
        height = Math.max(maxHeight,height);
        
        if(getOrder(2) == 4) {
            count = 1;
            String[] levelNames = (sys.isCorporation(CorporationData.BAYER)) ? sys.getUserSessionData().getFilter().getDivOrgLabels() : sys.getUserSessionData().getFilter().getLevelNames(messages);
            reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FirstLevelColor, null,
                    new Rectangle2D.Float(10, height, getReportWidth(), 18), false, true));
            int order = getOrder(1);
            String s = (order == 3) ? "corporation.country" : levelNames[order];
            if(sys.isCorporation(CorporationData.BAYER) && order > 3) {
            	s = levelNames[order-2];
            }
            int x = 10;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 160, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(s)));
            x += 160;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 90, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.apmm.vehicle_count")));
            x += 90;
            if(!sys.isCorporation(CorporationData.SYNGENTA_BR)) {
            	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 50, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(distanceUnit)));
            	x += 50;
            }
            if(sys.isBrasilCorporation()) {
                reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 50, 18),
                        Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("datatype.distanceunit.kilometers")));
                x += 50;
            }
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.apmm.accident_count")));
            x += 80;
            if(!sys.isCorporation(CorporationData.SYNGENTA_BR)) {
            	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 70, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.apmm.apmm")));
            	x += 70;
            }
            if(sys.isBrasilCorporation()) {
                reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 70, 18),
                        Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.apmm.apmk")));
                x += 70;
            }
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 90, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.apmm.vehicle_percent")));
            x += 90;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.apmm.incident_count")));
            x += 80;
           if(corporationId == CorporationData.JJ) {
        	   reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                       Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.apmm.injuries_count")));
              x+=80;
           }
           reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 70, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.apmm.ipmm")));
    	   x += 70;
           if(corporationId == CorporationData.MONSANTO) {
               reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 70, 18),
                       Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.apmm.nearmiss")));
        	   x += 70;
           }
            
        }
        
        return reportHeader;
    }

    protected ReportFooter createReportFooter() {
        
        ReportFooter reportFooter = new ReportFooter();
        reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, null,
                new Rectangle2D.Float(10, 18, getReportWidth(), 18), false, true));
        int x = 10;
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, 18, 160, 18),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,messages.getText("generic.messages.total")));
        x += 160;
        if(showDecimals) {
            reportFooter.addElement(NumberFieldElementFactory.createNumberElement("vehicle_countTotal",new Rectangle2D.Float(x, 18, 90, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatTwoDecimals, "vehicle_countTotal"));
        }else {
            reportFooter.addElement(NumberFieldElementFactory.createNumberElement("vehicle_countTotal",new Rectangle2D.Float(x, 18, 90, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatNoDecimals, "vehicle_countTotal"));
        }
        x += 90;
        if(!sys.isCorporation(CorporationData.SYNGENTA_BR)) {
        	reportFooter.addElement(NumberFieldElementFactory.createNumberElement("milesTotal",new Rectangle2D.Float(x, 18, 50, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatNoDecimals,"milesTotal"));
        	x += 50;
        }
        if(sys.isBrasilCorporation()) {
            reportFooter.addElement(NumberFieldElementFactory.createNumberElement("apmmTotal",new Rectangle2D.Float(x, 18, 50, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatNoDecimals,"kmsTotal"));
            x += 50;
        }
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement("accident_countTotal",new Rectangle2D.Float(x, 18, 80, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatNoDecimals,"accident_countTotal"));
        x += 80;
        if(!sys.isCorporation(CorporationData.SYNGENTA_BR)) {
        	reportFooter.addElement(NumberFieldElementFactory.createNumberElement("apmmTotal",new Rectangle2D.Float(x, 18, 70, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatTwoDecimals,"apmmTotal"));
        	x += 70;
        }
        if(sys.isBrasilCorporation()) {
            reportFooter.addElement(NumberFieldElementFactory.createNumberElement("apmkTotal",new Rectangle2D.Float(x, 18, 70, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatTwoDecimals,"apmkTotal"));
            x += 70;
        }
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement("vehiclespercentTotal",new Rectangle2D.Float(x, 18, 90, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatOneDecimals,"vehiclespercentTotal"));
        x += 90;
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement("incident_countTotal",new Rectangle2D.Float(x, 18, 80, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatNoDecimals,"incident_countTotal"));
        x += 80;
        if(corporationId == CorporationData.JJ) {
        	reportFooter.addElement(NumberFieldElementFactory.createNumberElement("injuries_countTotal",new Rectangle2D.Float(x, 18, 80, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatNoDecimals,"injuries_countTotal"));
        	x+=80;
        }
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement("ipmmTotal",new Rectangle2D.Float(x, 18, 70, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatTwoDecimals,"ipmmTotal"));
    	x+=70;
        if(corporationId == CorporationData.MONSANTO) {
            reportFooter.addElement(NumberFieldElementFactory.createNumberElement("nearmiss_countTotal",new Rectangle2D.Float(x, 18, 70, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatTwoDecimals,"nearmiss_countTotal"));
        	x+=70;
        }
        
        DrawableFieldElementFactory factory = new DrawableFieldElementFactory();
        factory.setName("drawable-field");
        
        int y = 54;
        x = 10;
        for(int i = 1; i <= 4; i++) {
            if(getOrder(i) != 4) {
                // Para PDF y XLS
                if(!Util.empty(reportType) && !reportType.equals("HTML")) {
                    factory.setAbsolutePosition(new Point2D.Float(x, y));
                    factory.setMinimumSize(new FloatDimension(300, 400));
                    factory.setFieldname("chart"+i);
                    reportFooter.addElement(factory.createElement());
                    if(i == 1 || i == 3) {
                        x += 380; 
                    }else {
                        x -= 380; 
                    }
                    if(i == 2) {
                        y += 415;
                    }
                }
            }
        }

        return reportFooter;
    }

    protected ExpressionCollection createFunctions() {
        ExpressionCollection functions = super.createFunctions();

        for(int i = 1; i < 4; i++) {
            if(getOrder(i+1) != 4) {
                final ItemSumFunction total1 = new ItemSumFunction();
                total1.setName("total1"+i);
                total1.setGroup("Group"+i);
                total1.setField("vehicle_count");
                functions.add(total1);
        
                final ItemSumFunction total2 = new ItemSumFunction();
                total2.setName("total2"+i);
                total2.setGroup("Group"+i);
                total2.setField("miles");
                functions.add(total2);
        
            	if(sys.isCorporation(CorporationData.DOW)) {
                    BSHExpression total12 = new BSHExpression();
                    total12.setName("total12"+i);
                    total12.setExpression("getValue() {" +
                            "            Number miles = (Number) dataRow.get (\""+"total2"+i+"\");" +
                            "            double result = miles.floatValue() * 1.609344;" +
                            "            return new Double(result);" +
                            "}");
                    functions.add(total12);
            	}else {
	                final ItemSumFunction total12 = new ItemSumFunction();
	                total12.setName("total12"+i);
	                total12.setGroup("Group"+i);
	                total12.setField("kms");
	                functions.add(total12);
            	}
            	
                final ItemSumFunction total3 = new ItemSumFunction();
                total3.setName("total3"+i);
                total3.setGroup("Group"+i);
                total3.setField("accident_count");
                functions.add(total3);
               
                final ItemSumFunction total4 = new ItemSumFunction();
                total4.setName("total4"+i);
                total4.setGroup("Group"+i);
                total4.setField("incident_count");
                functions.add(total4);
                
                	
                if (corporationId==CorporationData.JJ){
                	final ItemSumFunction total9 = new ItemSumFunction();
                	total9.setName("total9"+i);
                	total9.setGroup("Group"+i);
                	total9.setField("injuries_count");
                	functions.add(total9);
                }

                BSHExpression bshExp1 = new BSHExpression();
                bshExp1.setName("total5"+i);
                bshExp1.setExpression("getValue() {" +
                		"            Number incidents = (Number) dataRow.get (\"total4"+i+"\");" +
                        "            Number miles = (Number) dataRow.get (\"total2"+i+"\");" +
                        "            float result = 0; "+
                        "            if (incidents != null && miles != null && miles.floatValue() != 0){ "+
                        "                result = incidents.floatValue() * 1000000 / miles.floatValue();" +
                        "            }"+
                        "            return new Float (result);" +
                        "}");
                functions.add(bshExp1);
        
                BSHExpression bshExp13 = new BSHExpression();
                bshExp13.setName("total13"+i);
                bshExp13.setExpression("getValue() {" +
                		"            Number incidents = (Number) dataRow.get (\"total3"+i+"\");" +
                        "            Number miles = (Number) dataRow.get (\"total12"+i+"\");" +
                        "            float result = 0; "+
                        "            if (incidents != null && miles != null && miles.floatValue() != 0){ "+
                        "                result = incidents.floatValue() * 1000000 / miles.floatValue();" +
                        "            }"+
                        "            return new Float (result);" +
                        "}");
                functions.add(bshExp13);
        
                BSHExpression bshExp3 = new BSHExpression();
                bshExp3.setName("total6"+i);
                bshExp3.setExpression("getValue() {" +
                        "            Number incidents = (Number) dataRow.get (\"total3"+i+"\");" +
                        "            Number miles = (Number) dataRow.get (\"total2"+i+"\");" +
                        "            float result = 0; "+
                        "            if (incidents != null && miles != null && miles.floatValue() != 0){ "+
                        "                result = incidents.floatValue() * 1000000 / miles.floatValue();" +
                        "            } "+
                        "            return new Float (result);" +
                        "}");
                functions.add(bshExp3);
                

                BSHExpression bshExp5 = new BSHExpression();
                bshExp5.setName("total7"+i);
                bshExp5.setExpression("getValue() {" +
                        "            Number incidents = (Number) dataRow.get (\"total1"+i+"\");" +
                        "            Number miles = (Number) dataRow.get (\"total3"+i+"\");" +
                        "            float result = 0; "+
                        "            if (incidents != null && miles != null && miles.floatValue() != 0){ "+
                        "                result = miles.floatValue() / incidents.floatValue() * 12 / "+months+";" +
                        "            } "+
                        "            return new Float (result * 100);" +
                        "}");
                functions.add(bshExp5);
                
                BSHExpression bshExp10 = new BSHExpression();
                bshExp10.setName("total10"+i);
                bshExp10.setExpression("getValue() {" +
                        "            Number injuries = (Number) dataRow.get (\"total9"+i+"\");" +
                        "            Number miles = (Number) dataRow.get (\"total2"+i+"\");" +
                        "            float result = 0; "+
                        "            if (injuries != null && miles != null && miles.floatValue() != 0){ "+
                        "                result = injuries.floatValue() * 1000000 / miles.floatValue();" +
                        "            } "+
                        "            return new Float (result);" +
                        "}");
                functions.add(bshExp10);

                final ItemSumFunction total11 = new ItemSumFunction();
                total11.setName("total11"+i);
                total11.setGroup("Group"+i);
                total11.setField("nearmiss_count");
                functions.add(total11);
            }
            
            
        }

        final ItemSumFunction vehicle_countTotal = new ItemSumFunction();
        vehicle_countTotal.setName("vehicle_countTotal");
        vehicle_countTotal.setField("vehicle_count");
        functions.add(vehicle_countTotal);

        final ItemSumFunction milesTotal = new ItemSumFunction();
        milesTotal.setName("milesTotal");
        milesTotal.setField("miles");
        functions.add(milesTotal);
        
        final ItemSumFunction accident_countTotal = new ItemSumFunction();
        accident_countTotal.setName("accident_countTotal");
        accident_countTotal.setField("accident_count");
        functions.add(accident_countTotal);

        final ItemSumFunction incident_countTotal = new ItemSumFunction();
        incident_countTotal.setName("incident_countTotal");
        incident_countTotal.setField("incident_count");
        functions.add(incident_countTotal);
        
        final ItemSumFunction injuries_countTotal = new ItemSumFunction();
        injuries_countTotal.setName("injuries_countTotal");
        injuries_countTotal.setField("injuries_count");
        functions.add(injuries_countTotal);
        
        final ElementColorFunction color = new ElementColorFunction();
        color.setName("changeColor");
        color.setElement("Variance Field");
        color.setField("color");
        color.setColorTrue(gray1);
        color.setColorFalse(gray2);
        functions.add(color);

        BSHExpression bshExp2 = new BSHExpression();
        if (corporationId!=4){
        	bshExp2.setName("ipmmTotal");
        	bshExp2.setExpression("getValue() {" +
                "            Number incidents = (Number) dataRow.get (\"incident_countTotal\");" +
                "            Number miles = (Number) dataRow.get (\"milesTotal\");" +
                "            float result = 0; "+
                "            if (incidents != null && miles != null && miles.floatValue() != 0){ "+
                "               result = incidents.floatValue() * 1000000 / miles.floatValue();" +
                "            } "+
                "            return new Float (result);" +
                "}");
        	functions.add(bshExp2);
        }else{
    		bshExp2.setName("ipmmTotal");
        	bshExp2.setExpression("getValue() {" +
                "            Number injuries = (Number) dataRow.get (\"injuries_countTotal\");" +
                "            Number miles = (Number) dataRow.get (\"milesTotal\");" +
                "            float result = 0; "+
                "            if (injuries != null && miles != null && miles.floatValue() != 0) { "+
                "               result = injuries.floatValue() * 1000000 / miles.floatValue();" +
                "            }"+
                "            return new Float (result);" +
                "}");
        	functions.add(bshExp2);
    	
    	}
        BSHExpression bshExp4 = new BSHExpression();
        bshExp4.setName("apmmTotal");
        bshExp4.setExpression("getValue() {" +
                "            Number incidents = (Number) dataRow.get (\"accident_countTotal\");" +
                "            Number miles = (Number) dataRow.get (\"milesTotal\");" +
                "            float result = 0; "+
                "            if (incidents != null && miles != null && miles.floatValue() != 0){ "+
                "                result = incidents.floatValue() * 1000000 / miles.floatValue();" +
                "            } "+
                "            return new Float (result);" +
                "}");
        functions.add(bshExp4);

        BSHExpression bshExp6 = new BSHExpression();
        bshExp6.setName("vehiclespercentTotal");
        bshExp6.setExpression("getValue() {" +
                "            Number vehicles = (Number) dataRow.get (\"vehicle_countTotal\");" +
                "            Number accidents = (Number) dataRow.get (\"accident_countTotal\");" +
                "            if(accidents == null || vehicles == null || vehicles.floatValue() == 0) {" +
                "               return new Float (0);" +
                "            }" +
                "            float result = accidents.floatValue() / vehicles.floatValue() * 12 / "+months+";" +
                "            return new Float (result * 100);" +
                "}");
        functions.add(bshExp6);

        if(sys.isBrasilCorporation()) {
        	if(sys.isCorporation(CorporationData.DOW)) {
                BSHExpression kmsTotal = new BSHExpression();
                kmsTotal.setName("kmsTotal");
                kmsTotal.setExpression("getValue() {" +
                        "            Number miles = (Number) dataRow.get (\"milesTotal\");" +
                        "            double result = miles.floatValue() * 1.609344;" +
                        "            return new Double(result);" +
                        "}");
                functions.add(kmsTotal);
        	}else {
	            final ItemSumFunction kmsTotal = new ItemSumFunction();
	            kmsTotal.setName("kmsTotal");
	            kmsTotal.setField("kms");
	            functions.add(kmsTotal);
        	}
        	
            BSHExpression bshExp7 = new BSHExpression();
            bshExp7.setName("apmkTotal");
            bshExp7.setExpression("getValue() {" +
                    "            Number accidents = (Number) dataRow.get (\"accident_countTotal\");" +
                    "            Number kms = (Number) dataRow.get (\"kmsTotal\");" +
                    "            float result = 0; "+
                    "            if (accidents != null && kms != null && kms.floatValue() != 0){ "+
                    "                result = accidents.floatValue() * 1000000 / kms.floatValue();" +
                    "            } "+
                    "            return new Float (result);" +
                    "}");
            functions.add(bshExp7);
        }
        final ItemSumFunction nearmiss_countTotal = new ItemSumFunction();
        nearmiss_countTotal.setName("nearmiss_countTotal");
        nearmiss_countTotal.setField("nearmiss_count");
        functions.add(nearmiss_countTotal);

        return functions;
    }
}
