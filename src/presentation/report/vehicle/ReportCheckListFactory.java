/**
 * Created on 10-jul-2006
 * CEPA
 * DataCenter 5
 */
package presentation.report.vehicle;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

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
import org.jfree.report.function.CreateHyperLinksFunction;
import org.jfree.report.function.ElementColorFunction;
import org.jfree.report.function.ExpressionCollection;
import org.jfree.report.function.ItemSumFunction;
import org.jfree.report.function.TextFormatExpression;

import presentation.report.ReportFactory;
import presentation.translator.web.util.Util;
import presentation.translator.web.vehicle.ReportCheckListForm;
import business.SystemAccess;

import communication.administration.corporation.tables.CorporationData;
import communication.language.Text;
import communication.vehicle.report.ReportCheckListData;
import communication.vehicle.report.VehicleCheckReportDataType;

public class ReportCheckListFactory extends ReportFactory {

    protected Text messages;
    
    private SystemAccess sys;

    private int order;

    private int reportWidth;
    private int totalLength;
    private String label;
    
    public ReportCheckListFactory(String name, ReportCheckListData model,ActionForm form, SystemAccess sys, Text messages, int order) {
        super(name, model, form);
        this.messages = messages;
        this.sys = sys;
        this.order = order;
        parseDivisionValues(sys,messages);
        label = (sys.getUserSessionData().getFilter().getCorporationId().intValue() == CorporationData.LOSPIQUES) ? divOrgLabels[2][0] : divOrgLabels[0][1];

        switch(order) {
            case VehicleCheckReportDataType.CONTRACT: case VehicleCheckReportDataType.CONTRACT_TYPE: case VehicleCheckReportDataType.TYPE_CONTRACT:
                reportWidth = 530;
                totalLength = 160;
                break;
            case VehicleCheckReportDataType.TYPE:
                reportWidth = 610;
                totalLength = 240;
                break;
            default:
                reportWidth = 690; 
                totalLength = 320;
        }
    }
    
    protected int getWidth() {
        return getReportWidth()+64;
    }

    protected int getReportWidth() {
        return reportWidth;
        
    }
    
    protected GroupList createGroups() {
        GroupList grouplist = new GroupList();

        GroupHeader header = null;
        if(order == VehicleCheckReportDataType.CONTRACT || order == VehicleCheckReportDataType.CONTRACT_TYPE || 
                order == VehicleCheckReportDataType.TYPE || order == VehicleCheckReportDataType.TYPE_CONTRACT) {

            String field1 = (order == VehicleCheckReportDataType.CONTRACT || order == VehicleCheckReportDataType.CONTRACT_TYPE) ? "name" : "type";
            Group div1Group = new Group();
            div1Group.setName("group1");
            div1Group.addField(field1);
    
            GroupHeader div1Header = new GroupHeader();
            div1Header.setName("div1Header");
            header = div1Header;
            
            div1Header.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, SecondLevelColor, null,
                    new Rectangle2D.Float(10, 10, getReportWidth(), 15), false, true));
            if(order == VehicleCheckReportDataType.CONTRACT || order == VehicleCheckReportDataType.CONTRACT_TYPE) {
                div1Header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 80, 15),
                        Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,messages.getText(label)));
                div1Header.addElement(TextFieldElementFactory.createStringElement("headerName",new Rectangle2D.Float(90, 10, 240, 15),
                      Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"", "name"));
            }else {
                div1Header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 80, 15),
                        Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.vehicletype")));
                div1Header.addElement(TextFieldElementFactory.createStringElement("headerName",new Rectangle2D.Float(90, 10, 240, 15),
                      Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"", "type"));
            }
            
            GroupFooter div1Footer = new GroupFooter();
            div1Footer.setName("div1Footer");
            div1Footer.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, SecondLevelColor, null,
                    new Rectangle2D.Float(10, 10, getReportWidth(), 15), false, true));
            div1Footer.addElement(TextFieldElementFactory.createStringElement("total"+field1,new Rectangle2D.Float(10, 10, totalLength, 15),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"","total"+field1));
            div1Footer.addElement(NumberFieldElementFactory.createNumberElement("countTotal1",new Rectangle2D.Float(10+totalLength, 10, 80, 15),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"",formatNoDecimals,"countTotal1"));
            for(int i = 0; i < 14; i++) {
                div1Footer.addElement(NumberFieldElementFactory.createNumberElement("totalGroup1"+i,new Rectangle2D.Float(90+totalLength+(i*20), 10, 20, 15),
                        Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"",formatNoDecimals,"totalGroup1"+i));
            }
    
            div1Group.setHeader(div1Header);
            div1Group.setFooter(div1Footer);
            
            grouplist.add(div1Group);

            if(order == VehicleCheckReportDataType.CONTRACT_TYPE || order == VehicleCheckReportDataType.TYPE_CONTRACT) {
                
                String field2 = (order == VehicleCheckReportDataType.CONTRACT_TYPE) ? "type" : "name";

                Group div2Group = new Group();
                div2Group.setName("group2");
                div2Group.addField(field1);
                div2Group.addField(field2);
        
                GroupHeader div2Header = new GroupHeader();
                div2Header.setName("div2Header");
                header = div2Header;
                
                div2Header.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, ThirtdLevelColor, null,
                        new Rectangle2D.Float(10, 10, getReportWidth(), 15), false, true));
                if(order == VehicleCheckReportDataType.CONTRACT_TYPE) {
                    div2Header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 80, 15),
                            Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.vehicletype")));
                    div2Header.addElement(TextFieldElementFactory.createStringElement("headerName",new Rectangle2D.Float(90, 10, 240, 15),
                          Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"", "type"));
                }else {
                    div2Header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 80, 15),
                          Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,messages.getText(label)));
                    div2Header.addElement(TextFieldElementFactory.createStringElement("headerName",new Rectangle2D.Float(90, 10, 240, 15),
                          Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"", "name"));
                }
                
                GroupFooter div2Footer = new GroupFooter();
                div2Footer.setName("div2Footer");
                div2Footer.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, ThirtdLevelColor, null,
                        new Rectangle2D.Float(10, 10, getReportWidth(), 15), false, true));
                div2Footer.addElement(TextFieldElementFactory.createStringElement("total"+field2,new Rectangle2D.Float(10, 10, totalLength, 15),
                        Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"","total"+field2));
                div2Footer.addElement(NumberFieldElementFactory.createNumberElement("countTotal2",new Rectangle2D.Float(10+totalLength, 10, 80, 15),
                        Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"",formatNoDecimals,"countTotal2"));
                for(int i = 0; i < 14; i++) {
                    div2Footer.addElement(NumberFieldElementFactory.createNumberElement("totalGroup2"+i,new Rectangle2D.Float(90+totalLength+(i*20), 10, 20, 15),
                            Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"",formatNoDecimals,"totalGroup2"+i));
                }
        
                div2Group.setHeader(div2Header);
                div2Group.setFooter(div2Footer);
                
                grouplist.add(div2Group);
            }

            int x = 10;
            header.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FourLevelColor, null,
                    new Rectangle2D.Float(10, 40, getReportWidth(), 18), false, true));
            switch(order) {
                case VehicleCheckReportDataType.CONTRACT:
                    header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, 40, 80, 18),
                            Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.registrationNumber")));
                    header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x+80, 40, 80, 18),
                            Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.vehicletype")));
                    x += 160; 
                    break;
                case VehicleCheckReportDataType.CONTRACT_TYPE:case VehicleCheckReportDataType.TYPE_CONTRACT:
                    header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, 40, 160, 18),
                            Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.registrationNumber")));
                    x += 160; 
                    break;
                case VehicleCheckReportDataType.TYPE:
                    header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, 40, 160, 18),
                            Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(label)));
                    header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x+160, 40, 80, 18),
                            Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.registrationNumber")));
                    x += 240;
                    break;
                default:
                    header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, 40, 160, 18),
                            Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(label)));
                    header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x+160, 40, 80, 18),
                            Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.registrationNumber")));
                    header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x+240, 40, 80, 18),
                            Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.vehicletype")));
                    x += 320;
            }
            header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, 40, 80, 18),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.messages.count")));
            x += 80;
            for(int i = 0; i < 14; i++) {
                header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, 40, 20, 18),
                        Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("vehicle.checklist.query.key"+(i+1))));
                x += 20;
            }
        }
        
        return grouplist;
    }
    
    protected ItemBand createItemBand() {
        
        ItemBand items = super.createItemBand();        
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 15), false, true));
        int x = 10;
        switch(order) {
            case VehicleCheckReportDataType.CONTRACT:
                items.addElement(TextFieldElementFactory.createStringElement("URL-Field",new Rectangle2D.Float(x, 0, 80, 15),
                        Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","plate"));
                items.addElement(TextFieldElementFactory.createStringElement("type",new Rectangle2D.Float(x+80, 0, 80, 15),
                        Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","type"));
                x += 160; 
                break;
            case VehicleCheckReportDataType.CONTRACT_TYPE:case VehicleCheckReportDataType.TYPE_CONTRACT:
                items.addElement(TextFieldElementFactory.createStringElement("URL-Field",new Rectangle2D.Float(x, 0, 160, 15),
                        Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","plate"));
                x += 160; 
                break;
            case VehicleCheckReportDataType.TYPE:
                items.addElement(TextFieldElementFactory.createStringElement("name",new Rectangle2D.Float(x, 0, 160, 15),
                        Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","name"));
                items.addElement(TextFieldElementFactory.createStringElement("URL-Field",new Rectangle2D.Float(x+160, 0, 80, 15),
                        Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","plate"));
                x += 240;
                break;
            default:
                items.addElement(TextFieldElementFactory.createStringElement("name",new Rectangle2D.Float(x, 0, 160, 15),
                        Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","name"));
                items.addElement(TextFieldElementFactory.createStringElement("URL-Field",new Rectangle2D.Float(x+160, 0, 80, 15),
                        Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","plate"));
                items.addElement(TextFieldElementFactory.createStringElement("type",new Rectangle2D.Float(x+240, 0, 80, 15),
                        Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","type"));
                x += 320;
        } 

        items.addElement(NumberFieldElementFactory.createNumberElement("count",new Rectangle2D.Float(x, 0, 80, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals,"count"));
        x+=80;
        for(int i = 0; i < 14; i++) {
            items.addElement(NumberFieldElementFactory.createNumberElement("check"+i,new Rectangle2D.Float(x, 0, 20, 15),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals,"check"+i));
            x += 20;
        }
        return items;
    }
    
    protected ReportHeader createReportHeader() {
        
        ReportHeader reportHeader = new ReportHeader();
        
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 25), false, true));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, getReportWidth(), 25),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("truck.checklist.report")));

        int height = 43;

        height = writeParametersTitle(messages.getText("query.mileage.parameters"),reportHeader,height,10,160);
        height = writeParameter(messages.getText("query.mileage.from")+":",((ReportCheckListForm)form).getFrom(messages),reportHeader,height,10,80,90,80);
        height = writeParameter(messages.getText("query.mileage.to")+":",((ReportCheckListForm)form).getTo(messages),reportHeader,height,10,80,90,80);

        height = writeParametersTitle(messages.getText("generic.fleet.divgeo"),reportHeader,height,10,160); 
        height = writeParameter(divGeoLabels[0]+":",divGeoValues[0],reportHeader,height,10,80,90,80);
        height = writeParameter(divGeoLabels[1]+":",divGeoValues[1],reportHeader,height,10,80,90,80);

        int maxHeight = height;
        
        height = 43;
        String[] divOrgs = sys.getUserSessionData().getFilter().getLevelLabels();
        for(int i = 0; i < divOrgs.length; i++) {
            if(!Util.empty(divOrgs[i])) {
                height = writeParametersTitle(messages.getText(divOrgs[i]),reportHeader,height,250,240);
                for(int j = 0; j < 3; j++) {
                    if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 1 && j== 0) && 
                            !(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 2 && j== 0)) {
                        if(!Util.empty(divOrgLabels[i][j])) {
                            height = writeParameter(divOrgLabels[i][j]+":",divOrgValues[i][j],reportHeader,height,250,80,330,160);
                        }
                    }
                }
            }
        }
        
        height = Math.max(maxHeight,height);

        if(!(order == VehicleCheckReportDataType.CONTRACT || order == VehicleCheckReportDataType.CONTRACT_TYPE || 
                order == VehicleCheckReportDataType.TYPE || order == VehicleCheckReportDataType.TYPE_CONTRACT)) {
            reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FourLevelColor, null,
                    new Rectangle2D.Float(10, height, getReportWidth(), 18), false, true));
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 160, 18),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(label)));
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(170, height, 80, 18),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.registrationNumber")));
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(250, height, 80, 18),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.vehicletype")));
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(330, height, 80, 18),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.messages.count")));
            for(int i = 0; i < 14; i++) {
                reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(410+(i*20), height, 20, 18),
                        Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("vehicle.checklist.query.key"+(i+1))));
            }
        }
        
        return reportHeader;
    }

    protected ReportFooter createReportFooter() {
        
        ReportFooter reportFooter = new ReportFooter();

        if(!(order == VehicleCheckReportDataType.CONTRACT || order == VehicleCheckReportDataType.CONTRACT_TYPE || 
                order == VehicleCheckReportDataType.TYPE || order == VehicleCheckReportDataType.TYPE_CONTRACT)) {
        	reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FourLevelColor, null,
                    new Rectangle2D.Float(10, 0, getReportWidth(), 18), false, true));
        	reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, 160, 18),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(label)));
        	reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(170, 0, 80, 18),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.registrationNumber")));
        	reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(250, 0, 80, 18),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.vehicletype")));
        	reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(330, 0, 80, 18),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.messages.count")));
            for(int i = 0; i < 14; i++) {
            	reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(410+(i*20), 0, 20, 18),
                        Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("vehicle.checklist.query.key"+(i+1))));
            }
        }

        reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, null,
                new Rectangle2D.Float(10, 30, getReportWidth(), 18), false, true));
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 30, totalLength, 18),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,messages.getText("generic.messages.total")));
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement("countTotal",new Rectangle2D.Float(10+totalLength, 30, 80, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"",formatNoDecimals,"countTotal"));
        for(int i = 0; i < 14; i++) {
            reportFooter.addElement(NumberFieldElementFactory.createNumberElement("total"+i,new Rectangle2D.Float(90+totalLength+(i*20), 30, 20, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"",formatNoDecimals,"total"+i));
        }

//        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 18, 320, 18),
//                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,messages.getText("vehicle.checklist.query.key1")+" "+messages.getText("vehicle.checklist.query.error1")));
        for(int i = 1; i <= 14; i++) {
            Color c = (i % 2 == 0) ? gray1 : gray2;
            reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, c, null, 
                    new Rectangle2D.Float(10, 55+(i*18), 320, 18), false, true));
            reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 55+(i*18), 320, 18),
                    Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("vehicle.checklist.query.key"+i)+" "+messages.getText("vehicle.checklist.query.error"+i)));
        }

        return reportFooter;
    }

    protected ExpressionCollection createFunctions() {
        ExpressionCollection functions = super.createFunctions();

        TextFormatExpression textExp = new TextFormatExpression();
        textExp.setName("URLCreateExpression");
        textExp.setPattern("./layout.jsp?refer=/vehicle/VehicleView.jsp?id={0}");
        textExp.setField(0,"vehicleId");
        functions.add(textExp);

        CreateHyperLinksFunction link = new CreateHyperLinksFunction();
        link.setName("AddHRefs");
        link.setField("URLCreateExpression");
        link.setElement("URL-Field");
        functions.add(link);

        final ItemSumFunction countTotal = new ItemSumFunction();
        countTotal.setName("countTotal");
        countTotal.setField("count");
        functions.add(countTotal);

        final ItemSumFunction countTotal1 = new ItemSumFunction();
        countTotal1.setName("countTotal1");
        countTotal1.setGroup("group1");
        countTotal1.setField("count");
        functions.add(countTotal1);

        final ItemSumFunction countTotal2 = new ItemSumFunction();
        countTotal2.setName("countTotal2");
        countTotal2.setGroup("group2");
        countTotal2.setField("count");
        functions.add(countTotal2);

        final ElementColorFunction color = new ElementColorFunction();
        color.setName("changeColor");
        color.setElement("Variance Field");
        color.setField("color");
        color.setColorTrue(gray1);
        color.setColorFalse(gray2);
        functions.add(color);
        
        for(int i = 0; i < 14; i++) {
            final ItemSumFunction count = new ItemSumFunction();
            count.setName("total"+i);
            count.setField("check"+i);
            functions.add(count);
        }

        for(int i = 0; i < 14; i++) {
            final ItemSumFunction count = new ItemSumFunction();
            count.setName("totalGroup1"+i);
            count.setGroup("group1");
            count.setField("check"+i);
            functions.add(count);

            final ItemSumFunction count2 = new ItemSumFunction();
            count2.setName("totalGroup2"+i);
            count2.setGroup("group2");
            count2.setField("check"+i);
            functions.add(count2);
        }
        return functions;
    }
}
