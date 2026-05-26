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

public class ReportKmLtrFactory extends ReportFactory {

    protected Text messages;
    
    private SystemAccess sys;
    private int count;
    
    public ReportKmLtrFactory(String name, TableModel model, ActionForm form, SystemAccess sys, Text messages) {
        super(name, model, form);
        this.messages = messages;
        parseDivisionValues(sys,messages);
        this.sys = sys;
    }


    protected int getWidth() {
        return 882;
    }

    protected int getReportWidth() {
    	return 800;
    }
    
    protected ItemBand createItemBand() {
        ItemBand items = super.createItemBand();        
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,
                new Rectangle2D.Float(0, 0, getReportWidth(), 15), false, true));
        items.addElement(TextFieldElementFactory.createStringElement("vehicleType",new Rectangle2D.Float(0, 0, 90, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","vehicleType"));        
        items.addElement(TextFieldElementFactory.createStringElement("divOrg"+count+"Name",new Rectangle2D.Float(90, 0, 90, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","divOrg1Name"));
        items.addElement(TextFieldElementFactory.createStringElement("plate",new Rectangle2D.Float(180, 0, 90, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","plate"));        
        items.addElement(TextFieldElementFactory.createStringElement("plate",new Rectangle2D.Float(270, 0, 90, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","trade"));        
        items.addElement(TextFieldElementFactory.createStringElement("plate",new Rectangle2D.Float(360, 0, 90, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","model"));        
        items.addElement(NumberFieldElementFactory.createNumberElement("km",new Rectangle2D.Float(450, 0, 70, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals,"km"));
        items.addElement(NumberFieldElementFactory.createNumberElement("liters",new Rectangle2D.Float(520, 0, 70, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals,"liters"));
        items.addElement(NumberFieldElementFactory.createNumberElement("average",new Rectangle2D.Float(590, 0, 100, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatTwoDecimals,"average"));
        items.addElement(TextFieldElementFactory.createStringElement("fuel",new Rectangle2D.Float(690, 0, 110, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","fuel"));
        return items;
    }
    
    protected ReportHeader createReportHeader() {
        
        ReportHeader reportHeader = new ReportHeader();
        
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,
                new Rectangle2D.Float(0, 0, getReportWidth(), 25), false, true));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(0, 0, getReportWidth(), 25),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("fleet.query.kmltr.title")));

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
        
        String[] levelNames = sys.getUserSessionData().getFilter().getLevelNames(messages);
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FirstLevelColor, null,
                new Rectangle2D.Float(0, height, getReportWidth(), 18), false, true));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(0, height, 90, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.kmltr.vehicletype")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(90, height, 90, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(levelNames[0])));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(180, height, 90, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.kmltr.plate")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(270, height, 90, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.kmltr.trade")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(360, height, 90, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.kmltr.model")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(450, height, 70, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.kmltr.miles")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(520, height, 70, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.kmltr.liters")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(590, height, 100, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.kmltr.average")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(690, height, 110, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.kmltr.fuel")));
        
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
