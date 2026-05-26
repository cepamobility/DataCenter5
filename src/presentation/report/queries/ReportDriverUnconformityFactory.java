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
import org.jfree.report.elementfactory.DateFieldElementFactory;
import org.jfree.report.elementfactory.DrawableFieldElementFactory;
import org.jfree.report.elementfactory.LabelElementFactory;
import org.jfree.report.elementfactory.NumberFieldElementFactory;
import org.jfree.report.elementfactory.StaticShapeElementFactory;
import org.jfree.report.elementfactory.TextFieldElementFactory;
import org.jfree.report.function.ElementColorFunction;
import org.jfree.report.function.ExpressionCollection;
import org.jfree.report.function.ItemCountFunction;
import org.jfree.report.function.ItemSumFunction;
import org.jfree.report.modules.misc.beanshell.BSHExpression;
import org.jfree.ui.FloatDimension;

import presentation.report.ReportFactory;
import presentation.translator.web.queries.APMMReport;
import presentation.translator.web.util.Util;
import presentation.translator.web.driver.ReportUnconformityForm;
import business.SystemAccess;

import communication.driver.tables.DriverData;
import communication.language.Text;
import communication.util.Filter;

public class ReportDriverUnconformityFactory extends ReportFactory {

    protected Text messages;
    private String reportType;
    
    private SystemAccess sys;
    private int corporationId;
    
    public ReportDriverUnconformityFactory(String name, TableModel model, ActionForm form, SystemAccess sys, Text messages) {
        super(name, model, form, Filter.CORPORATION);
        this.messages = messages;
        this.sys = sys;
        this.corporationId = sys.getUserSessionData().getFilter().getCorporationId().intValue();
    }

    protected int getWidth() {
        return 722;
    }

//  it is declared but it is not used 
    protected int getReportWidth() {
    	return 640;
    }
    
    protected ItemBand createItemBand() {
        
        ItemBand items = super.createItemBand();        
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 15), false, true));
        items.addElement(TextFieldElementFactory.createStringElement("driver",new Rectangle2D.Float(10, 0, 200, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","driver"));
        items.addElement(TextFieldElementFactory.createStringElement("date",new Rectangle2D.Float(210, 0, 80, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","date"));
        items.addElement(TextFieldElementFactory.createStringElement("type",new Rectangle2D.Float(290, 0, 150, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","type"));
        items.addElement(TextFieldElementFactory.createStringElement("observations",new Rectangle2D.Float(440, 0, 200, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","observations"));
        return items;
    }
    
    protected ReportHeader createReportHeader() {
        
        ReportHeader reportHeader = new ReportHeader();
        
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 25), false, true));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, getReportWidth(), 25),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("vehicle.unconformity.query")));

        int height = 45;
        int maxHeight = height;

        height = writeParametersTitle(messages.getText("query.mileage.parameters"),reportHeader,height,10,160);
        height = writeParameter(messages.getText("query.mileage.from")+":",((ReportUnconformityForm)form).getFrom(messages),reportHeader,height,10,70,80,90);
        height = writeParameter(messages.getText("query.mileage.to")+":",((ReportUnconformityForm)form).getTo(messages),reportHeader,height,10,70,80,90);

        switch(Integer.parseInt(((ReportUnconformityForm)form).getStatus())) {
        	case DriverData.ACTIVE:
        		height = writeParameter(messages.getText("generic.event.eventdata.status")+":",messages.getText("generic.messages.active"),reportHeader,height,10,80,90,80);
        		break;
        	case DriverData.EXCLUDED:
        		height = writeParameter(messages.getText("generic.event.eventdata.status")+":",messages.getText("generic.messages.excluded"),reportHeader,height,10,80,90,80);
        		break;
        	case DriverData.ALL:
        		height = writeParameter(messages.getText("generic.event.eventdata.status")+":",messages.getText("generic.messages.all.pl"),reportHeader,height,10,80,90,80);
        		break;
        }

        maxHeight = Math.max(maxHeight, height);
        
        height = 45;
        height = writeParametersTitle(messages.getText("generic.driver.divgeo"),reportHeader,height,10,160); 
        height = writeParameter(divGeoLabels[0]+":",divGeoValues[0],reportHeader,height,10,80,90,80);
        height = writeParameter(divGeoLabels[1]+":",divGeoValues[1],reportHeader,height,10,80,90,80);

        maxHeight=Math.max(maxHeight,height);
        height = 25;
       
        String[] divOrgs = sys.getUserSessionData().getFilter().getLevelLabels();
        for(int i = 0; i < divOrgs.length; i++) {
            if(!Util.empty(divOrgs[i])) {
                height = writeParametersTitle(messages.getText(divOrgs[i]),reportHeader,height,250,160);
                for(int j = 0; j < 3; j++) {
                    if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 1 && j== 0) && 
                            !(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 2 && j== 0)) {
                        if(!Util.empty(divOrgLabels[i][j])) {
                            height = writeParameter(divOrgLabels[i][j]+":",divOrgValues[i][j],reportHeader,height,250,80,330,80);
                        }
                    }
                }
            }
        }

        maxHeight = Math.max(maxHeight, height);

        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, ThirtdLevelColor, null,
                new Rectangle2D.Float(10, maxHeight, getReportWidth(), 15), false, true));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, maxHeight, 200, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.data.driver")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(210, maxHeight, 80, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.prizes.date")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(290, maxHeight, 150, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.unconformities.type")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(440, maxHeight, 200, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.unconformities.observations")));
        
        return reportHeader;
    }

    protected ReportFooter createReportFooter() {
    	ReportFooter reportFooter = super.createReportFooter();

        reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,
                new Rectangle2D.Float(10, 20, getReportWidth(), 15), false, true));
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 20, 200, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("generic.messages.total")));

        return reportFooter;
    }
    
    protected ExpressionCollection createFunctions() {
        ExpressionCollection functions = super.createFunctions();

        final ElementColorFunction color = new ElementColorFunction();
        color.setName("changeColor");
        color.setElement("Variance Field");
        color.setField("color");
        color.setColorFalse(Color.WHITE);
        color.setColorTrue(FourLevelColor);
        functions.add(color);

        return functions;
    }
}
