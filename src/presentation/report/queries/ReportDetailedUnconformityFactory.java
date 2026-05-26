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

public class ReportDetailedUnconformityFactory extends ReportFactory {

    protected Text messages;
    private String reportType;
    
    private SystemAccess sys;
    private int corporationId;
    
    public ReportDetailedUnconformityFactory(String name, TableModel model, ActionForm form, SystemAccess sys, Text messages) {
        super(name, model, form, Filter.CORPORATION);
        this.messages = messages;
        this.sys = sys;
        this.corporationId = sys.getUserSessionData().getFilter().getCorporationId().intValue();
    }

    protected int getWidth() {
        return 734;
    }

//  it is declared but it is not used 
    protected int getReportWidth() {
    	return 650;
    }
    
    protected GroupList createGroups() {
        GroupList grouplist = super.createGroups();
        
        Object[] fields = {"id"};
        

        Element[] headerElements = new Element[10]; 
        headerElements[0] = StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, null,
                new Rectangle2D.Float(10, 10, getReportWidth(), 15), false, true);
        headerElements[1] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 70, 15),Color.WHITE,
            ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,messages.getText("corporation.weyerhaeuser.transportist"));
        headerElements[2] = TextFieldElementFactory.createStringElement("resourcekey",new Rectangle2D.Float(80, 10, 200, 15),Color.WHITE,
            ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"","resourcekey");
        
        headerElements[3] = StaticShapeElementFactory.createRectangleShapeElement(null, ThirtdLevelColor, null,
                new Rectangle2D.Float(10, 25, getReportWidth(), 15), false, true);
        headerElements[4] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 25, 70, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.data.vehicle"));
        headerElements[5] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(110, 25, 60, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.prizes.date"));
        headerElements[6] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(170, 25, 100, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.unconformities.origin"));
        headerElements[7] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(270, 25, 100, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.unconformities.department"));
        headerElements[8] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(370, 25, 80, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.unconformities.charge"));
        headerElements[9] = LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(450, 25, 200, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.unconformities.observations"));
 
        Element[] footerTotals = new Element[3];
        footerTotals[0] = StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, null,
                new Rectangle2D.Float(10, 10, getReportWidth(), 15), false, true);
        footerTotals[1] = TextFieldElementFactory.createStringElement("resourcekey",new Rectangle2D.Float(50, 10, 150, 15),Color.WHITE,
                ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"","resourcekey");
        footerTotals[2] = NumberFieldElementFactory.createNumberElement("countExpression",new Rectangle2D.Float(450, 10, 200, 15),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,"",formatNoDecimals,"countExpression");
        grouplist.add(createGroup(fields, footerTotals, headerElements, "Group1", messages, "",40,10));
        
        return grouplist;
    }

    protected ItemBand createItemBand() {
        
        ItemBand items = super.createItemBand();        
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 15), false, true));
        items.addElement(TextFieldElementFactory.createStringElement("matricula",new Rectangle2D.Float(10, 0, 100, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","matricula"));
        items.addElement(DateFieldElementFactory.createDateElement("date",new Rectangle2D.Float(110, 0, 60, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-",DATE_FORMAT,"date"));
        items.addElement(TextFieldElementFactory.createStringElement("origin",new Rectangle2D.Float(170, 0, 100, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","origin"));
        items.addElement(TextFieldElementFactory.createStringElement("department",new Rectangle2D.Float(270, 0, 100, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","department"));
        items.addElement(NumberFieldElementFactory.createNumberElement("charge",new Rectangle2D.Float(370, 0, 80, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals,"charge"));
        items.addElement(TextFieldElementFactory.createStringElement("observations",new Rectangle2D.Float(450, 0, 200, 15),
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

        height = writeParametersTitle(messages.getText("query.mileage.parameters"),reportHeader,height,10,160);
        height = writeParameter(messages.getText("query.mileage.from")+":",((ReportUnconformityForm)form).getFrom(messages),reportHeader,height,10,70,80,90);
        height = writeParameter(messages.getText("query.mileage.to")+":",((ReportUnconformityForm)form).getTo(messages),reportHeader,height,10,70,80,90);

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

        return reportHeader;
    }

    protected ReportFooter createReportFooter() {
    	ReportFooter reportFooter = super.createReportFooter();

        reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,
                new Rectangle2D.Float(10, 20, getReportWidth(), 15), false, true));
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 20, 160, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("generic.messages.total")));
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement("totalExpression",new Rectangle2D.Float(450, 20, 200, 15),
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

        final ItemCountFunction countFunction = new ItemCountFunction();
        countFunction.setName("countExpression");
        countFunction.setGroup("Group1");
        functions.add(countFunction);

        final ItemCountFunction totalCountFunction = new ItemCountFunction();
        totalCountFunction.setName("totalExpression");
        totalCountFunction.setGroup("dummy");
        functions.add(totalCountFunction);
        
        return functions;
    }
}
