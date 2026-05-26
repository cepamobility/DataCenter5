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
import org.jfree.report.GroupList;
import org.jfree.report.ItemBand;
import org.jfree.report.ReportHeader;
import org.jfree.report.elementfactory.LabelElementFactory;
import org.jfree.report.elementfactory.NumberFieldElementFactory;
import org.jfree.report.elementfactory.StaticShapeElementFactory;
import org.jfree.report.elementfactory.TextFieldElementFactory;
import org.jfree.report.function.ElementColorFunction;
import org.jfree.report.function.ExpressionCollection;

import presentation.report.ReportFactory;
import presentation.translator.web.queries.ReportConsumedFuelForm;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.language.Text;

public class ReportConsumedFuelFactory extends ReportFactory {

    protected Text messages;
    
    private SystemAccess sys;
    private int count;
    
    public ReportConsumedFuelFactory(String name, TableModel model, ActionForm form, SystemAccess sys, Text messages) {
        super(name, model, form);
        this.messages = messages;
        parseDivisionValues(sys,messages);
        this.sys = sys;
    }


    protected int getWidth() {
        return 582;
    }

    protected int getReportWidth() {
    	return 500;
    }
    
    protected GroupList createGroups() {
        
        String[] divOrgs = sys.getUserSessionData().getFilter().getPrincipalLabels(); 

        GroupList grouplist = super.createGroups();
        
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
                            new Rectangle2D.Float(10, 10, getReportWidth(), 15), false, true);
                    headerElements[1] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 90, 15),Color.WHITE,
                        ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,messages.getText(totalName)+": ");
                    headerElements[2] = TextFieldElementFactory.createStringElement("divOrg"+i+"Name",new Rectangle2D.Float(100, 10, 160, 15),Color.WHITE,
                            ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"","divOrg"+i+"Name");
                }else {
                    headerElements = new Element[10];
                    
                    headerElements[0] = StaticShapeElementFactory.createRectangleShapeElement(null, groupsHeaderColors[i-1], null,
                            new Rectangle2D.Float(10, 10, getReportWidth(), 15), false, true);
                    headerElements[1] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 90, 15),Color.WHITE,
                        ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,messages.getText(totalName)+": ");
                    headerElements[2] = TextFieldElementFactory.createStringElement("divOrg"+i+"Name",new Rectangle2D.Float(100, 10, 160, 15),Color.WHITE,
                            ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"","divOrg"+i+"Name");

                    headerElements[3] = StaticShapeElementFactory.createRectangleShapeElement(null,FirstLevelColor, null,
                            new Rectangle2D.Float(10, 25, getReportWidth(), 18), false, true);
                    if(getOrder(i+1) == 3) {
                        headerElements[4] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 25, 90, 18),
                                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("corporation.country"));
                    }else {
                        headerElements[4] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 25, 90, 18),
                                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(divOrgs[getOrder(i+1)]));
                    }
                    headerElements[5] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(100, 25, 90, 18),
                            Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.consumedfuel.vehicletype"));
                    headerElements[6] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(190, 25, 80, 18),
                            Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.consumedfuel.vehicles"));
                    headerElements[7] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(270, 25, 80, 18),
                            Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.consumedfuel.miles"));
                    headerElements[8] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(350, 25, 80, 18),
                            Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.consumedfuel.fuel"));
                    headerElements[9] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(430, 25, 70, 18),
                            Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.consumedfuel.liters"));
                }
 
                Element[] footerTotals = new Element[0]; 
                grouplist.add(createGroup(fields.toArray(), footerTotals, headerElements, "Group"+i, messages, messages.getText(totalName),false));
            }
        }
        
        return grouplist;
    }


    public int getOrder(int i) {
        switch(i) {
            case 1:
                return Integer.parseInt(((ReportConsumedFuelForm)form).getOrder1());
            case 2:
                return Integer.parseInt(((ReportConsumedFuelForm)form).getOrder2());
            case 3:
                return Integer.parseInt(((ReportConsumedFuelForm)form).getOrder3());
            case 4:
                return Integer.parseInt(((ReportConsumedFuelForm)form).getOrder4());
        }
        return 4;
    }

    protected ItemBand createItemBand() {
        
        ItemBand items = super.createItemBand();        
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 15), false, true));
        items.addElement(TextFieldElementFactory.createStringElement("divOrg"+count+"Name",new Rectangle2D.Float(10, 0, 90, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","divOrg"+count+"Name"));
        items.addElement(TextFieldElementFactory.createStringElement("vehicleType",new Rectangle2D.Float(100, 0, 90, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","vehicleType"));
        items.addElement(NumberFieldElementFactory.createNumberElement("vehicles",new Rectangle2D.Float(190, 0, 80, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals,"vehicles"));
        items.addElement(NumberFieldElementFactory.createNumberElement("miles",new Rectangle2D.Float(270, 0, 80, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals,"miles"));
        items.addElement(TextFieldElementFactory.createStringElement("fuel",new Rectangle2D.Float(350, 0, 80, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","fuel"));
        items.addElement(NumberFieldElementFactory.createNumberElement("liters",new Rectangle2D.Float(430, 0, 70, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals,"liters"));
        return items;
    }
    
    protected ReportHeader createReportHeader() {
        
        ReportHeader reportHeader = new ReportHeader();
        
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 25), false, true));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, getReportWidth(), 25),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("fleet.query.consumedfuel.title")));

        int height = 43;

        height = writeParametersTitle(messages.getText("query.mileage.parameters"),reportHeader,height,10,260);
        height = writeParameter(messages.getText("query.mileage.from")+":",((ReportConsumedFuelForm)form).getFrom(messages),reportHeader,height,10,90,100,160);
        height = writeParameter(messages.getText("query.mileage.to")+":",((ReportConsumedFuelForm)form).getTo(messages),reportHeader,height,10,90,100,160);

        height = writeParametersTitle(messages.getText("generic.fleet.divgeo"),reportHeader,height,10,260); 
        height = writeParameter(divGeoLabels[0]+":",divGeoValues[0],reportHeader,height,10,90,100,160);
        height = writeParameter(divGeoLabels[1]+":",divGeoValues[1],reportHeader,height,10,90,100,160);

        int maxHeight = height;
        
        height = 43;
        String[] divOrgs = sys.getUserSessionData().getFilter().getLevelLabels();
        for(int i = 0; i < divOrgs.length; i++) {
            if(!Util.empty(divOrgs[i])) {
                height = writeParametersTitle(messages.getText(divOrgs[i]),reportHeader,height,270,230);
                for(int j = 0; j < 3; j++) {
                    if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 1 && j== 0) && 
                            !(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 2 && j== 0)) {
                        if(!Util.empty(divOrgLabels[i][j])) {
                            height = writeParameter(divOrgLabels[i][j]+":",divOrgValues[i][j],reportHeader,height,270,80,350,150);
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
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 90, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(s)));
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(100, height, 90, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.consumedfuel.vehicletype")));
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(190, height, 80, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.consumedfuel.vehicles")));
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(270, height, 80, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.consumedfuel.miles")));
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(350, height, 80, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.consumedfuel.fuel")));
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(430, height, 70, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.consumedfuel.liters")));
        }
        
        return reportHeader;
    }

    protected ExpressionCollection createFunctions() {
        ExpressionCollection functions = super.createFunctions();

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
