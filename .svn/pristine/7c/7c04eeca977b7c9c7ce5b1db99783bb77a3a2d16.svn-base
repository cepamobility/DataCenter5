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
import presentation.translator.web.vehicle.ReportUnconformityForm;
import business.SystemAccess;

import communication.language.Text;
import communication.util.Filter;

public class ReportUnconformityFactory extends ReportFactory {

    protected Text messages;
    private String reportType;
    
    private SystemAccess sys;
    private int corporationId;
    
    public ReportUnconformityFactory(String name, TableModel model, ActionForm form, SystemAccess sys, Text messages) {
        super(name, model, form, Filter.CORPORATION);
        this.messages = messages;
        this.sys = sys;
        this.corporationId = sys.getUserSessionData().getFilter().getCorporationId().intValue();
    }

    protected int getWidth() {
        return 580;
    }

//  it is declared but it is not used 
    protected int getReportWidth() {
    	return 500;
    }
    
    protected ItemBand createItemBand() {
        
        ItemBand items = super.createItemBand();        
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 15), false, true));
        items.addElement(TextFieldElementFactory.createStringElement("matricula",new Rectangle2D.Float(10, 0, 200, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","resourcekey"));
        items.addElement(NumberFieldElementFactory.createNumberElement("count",new Rectangle2D.Float(210, 0, 100, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals,"count"));
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

        maxHeight = Math.max(maxHeight, height);
        
        height = 45;
        height = writeParametersTitle(messages.getText("corporation.weyerhaeuser.transportist"),reportHeader,height,170,200);
        String[] s = ((ReportUnconformityForm)form).getDivOrg31();
        String transportists = "";
        if(s == null || s.length == 0 || (s.length == 1 && s[0].equals("0"))) {
        	transportists = messages.getText("generic.messages.all.pl");
        }else {
        	for(int i = 0; i < s.length; i++) {
        		if(transportists.length() > 0) {
        			transportists += ", ";
        		}
        		transportists += sys.getUserSessionData().getFilter().getDivOrgItem(2, 1,  new Integer(s[i]), Filter.CORPORATION).getName();
        	}
        }
        height = writeParameter("",transportists,reportHeader,height,170,0,170,200);

        maxHeight = Math.max(maxHeight, height);

        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, ThirtdLevelColor, null,
                new Rectangle2D.Float(10, maxHeight, getReportWidth(), 15), false, true));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, maxHeight, 200, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("corporation.weyerhaeuser.transportist")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(210, maxHeight, 100, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.messages.count")));
        
        return reportHeader;
    }

    protected ReportFooter createReportFooter() {
    	ReportFooter reportFooter = super.createReportFooter();

        reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,
                new Rectangle2D.Float(10, 20, getReportWidth(), 15), false, true));
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 20, 200, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("generic.messages.total")));
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement("totalExpression",new Rectangle2D.Float(210, 20, 100, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatNoDecimals,"totalExpression"));

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

        final ItemSumFunction sumFunction = new ItemSumFunction();
        sumFunction.setName("totalExpression");
        sumFunction.setField("count");
        sumFunction.setGroup("dummy");
        functions.add(sumFunction);
        
        return functions;
    }
}
