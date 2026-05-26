package presentation.report.driver;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

import javax.swing.table.TableModel;

import org.apache.struts.action.ActionForm;
import org.jfree.report.ElementAlignment;
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

import presentation.report.ReportFactory;
import presentation.translator.web.driver.DriverAccessLogForm;
import presentation.translator.web.vehicle.ReportUnconformityForm;
import business.SystemAccess;

import communication.language.Text;
import communication.util.Filter;

public class DriverAccessLogReportFactory extends ReportFactory {

    protected Text messages;
    protected SystemAccess sys;

    protected int last;
    
    
    public DriverAccessLogReportFactory(String name, TableModel model, ActionForm form, SystemAccess sys) {
        super(name, model, form);
        this.messages = sys.getText();
        this.sys = sys;
    }

    protected int getWidth() {
        return 692;
    }

    protected int getReportWidth() {
        return 610;
    }

    protected ItemBand createItemBand() {
        ItemBand items = super.createItemBand();
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,new Rectangle2D.Float(10, 0, getReportWidth(), 15), false, true));
        items.addElement(TextFieldElementFactory.createStringElement("id",new Rectangle2D.Float(10, 0, 90, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","id"));
        items.addElement(TextFieldElementFactory.createStringElement("name",new Rectangle2D.Float(100, 0, 150, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","name"));
        items.addElement(TextFieldElementFactory.createStringElement("contratist",new Rectangle2D.Float(250, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","contratist"));
        items.addElement(TextFieldElementFactory.createStringElement("access",new Rectangle2D.Float(350, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","access"));
        items.addElement(TextFieldElementFactory.createStringElement("date",new Rectangle2D.Float(450, 0, 80, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","date"));
        items.addElement(TextFieldElementFactory.createStringElement("user",new Rectangle2D.Float(530, 0, 80, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","user"));
        return items;
    }
    
    protected ReportHeader createReportHeader() {
        ReportHeader reportHeader = new ReportHeader();
        int height = writeFilter((DriverAccessLogForm)form, reportHeader);
                
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FirstLevelColor, null,
                new Rectangle2D.Float(10, height, getReportWidth(), 18), false, true));
       
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 90, 18), 
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.corporationid")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(100, height, 150, 18), 
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.firstname")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(250, height, 100, 18), 
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.messages.contractor")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(350, height, 100, 18), 
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.history.result")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(450, height, 80, 18), 
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.messages.date")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(530, height, 80, 18), 
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.data.user")));
        return reportHeader;
    }

	protected int writeFilter(DriverAccessLogForm reportForm, ReportHeader reportHeader) {
		reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 25), false, true));
        
		reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, getReportWidth(), 25),
        		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("query.driveraccesslog.title")));
        
        int height = 35;
        
        height = writeParameter(messages.getText("generic.driver.driverdata.corporationid")+":",reportForm.getCorporationId(),reportHeader,height,10,80,90,80);
        String[] s = ((DriverAccessLogForm)form).getDivOrg31();
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
        height = writeParameter(messages.getText("generic.messages.contractor")+":",transportists,reportHeader,height,10,80,90,200);
        
        switch(Integer.parseInt(reportForm.getAccess())) {
            case -1:
                height = writeParameter(messages.getText("driver.history.result")+":",messages.getText("generic.messages.all.pl"),reportHeader,height,10,80,90,80);
                break;
            case 0:
                height = writeParameter(messages.getText("driver.history.result")+":",messages.getText("doorcontrol.access.success"),reportHeader,height,10,80,90,80);
                break;
            case 1:
            	height = writeParameter(messages.getText("driver.history.result")+":",messages.getText("doorcontrol.access.forbidden"),reportHeader,height,10,80,90,80);
            	break;
        }
        
        height = writeParameter(messages.getText("driver.query.from")+":",reportForm.getStartReport(),reportHeader,height,10,80,90,80);
        height = writeParameter(messages.getText("driver.query.to")+":",reportForm.getEndReport(),reportHeader,height,10,80,90,80);
        
		return height;
	}
    
    protected ReportFooter createReportFooter() {
        ReportFooter reportFooter = new ReportFooter();
        reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, null,
                new Rectangle2D.Float(10, 10, getReportWidth(), 15), false, true));
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 160, 15),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,messages.getText("query.driver.driverdata.total")));
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement(null,new Rectangle2D.Float(getReportWidth()-90, 10, 90, 15),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,"", formatNoDecimals,"countExpression"));
        
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

        final ItemCountFunction countFunction = new ItemCountFunction();
        countFunction.setName("countExpression");
        countFunction.setGroup("dummy");
        functions.add(countFunction);

        return functions;
    }
}
