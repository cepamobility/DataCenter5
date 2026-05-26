/**
 * Created on 10-jul-2006
 * CEPA
 * DataCenter 5
 */
package presentation.report.activity;

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
import org.jfree.report.function.ItemSumFunction;
import org.jfree.report.style.FontDefinition;

import presentation.report.ReportFactory;
import presentation.translator.web.activity.schedule.ReportGeneralTrainingForm;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.language.Text;

public class ActivityReportGTFactory extends ReportFactory {

    protected Text messages;
    protected static final FontDefinition STYLE_TITLE= new FontDefinition("SansSerif",13,true,false,false,false);
    protected static final FontDefinition STYLE_SIMPLE_FONT= new FontDefinition("SansSerif",8);
    protected static final FontDefinition STYLE_SIMPLE_BOLD_FONT= new FontDefinition("SansSerif",8,true,false,false,false);
    protected static final FontDefinition STYLE_TOTAL_FONT= new FontDefinition("SansSerif",12,true,false,false,false);
    protected static final FontDefinition STYLE_TITLE_PARAM_FONT= new FontDefinition("SansSerif",9,true,false,true,false);
    
    public ActivityReportGTFactory(String name, TableModel model, ActionForm form, SystemAccess sys, Text messages) {
        super(name, model, form);
        this.messages = messages;
        parseDivisionValues(sys,messages);
    }

    protected int getWidth() {
        return 1000;
    }
    
//  it is declared but it is not used 
    protected int getReportWidth() {
    	return 0;
    }

   /* protected int writeFilterw(ReportGeneralTrainingForm reportForm, ReportHeader reportHeader)  {
    	reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, Color.ORANGE, null,
                new Rectangle2D.Float(0, 0, 700, 15), false, true));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(0, 0, 700, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,fontDefinitionBold,messages.getText("generic.query.activity.training_g")));

        int height = 25;
        int maxHeight=height;
        height = writeParametersTitle(messages.getText("generic.activity.activitydata.date"),reportHeader,height,0,150);
        
        
        header.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, gray1, null,
                new Rectangle2D.Float(0, 25, 98, 18), false, true));
        header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(0, 25, 98, 18),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,fontDefinitionSimple,messages.getText("generic.activity.activitydata.date")));

        header.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, gray2, null,
                new Rectangle2D.Float(100, 25, 98, 18), false, true));
        header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(100, 25, 98, 18),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,fontDefinitionSimple,messages.getText("query.activity.training.unit")));

        header.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, gray1, null,
                new Rectangle2D.Float(200, 25, 98, 18), false, true));
        header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(200, 25, 98, 18),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,fontDefinitionSimple,messages.getText("query.activity.training.division")));

        header.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, gray2, null,
                new Rectangle2D.Float(300, 25, 98, 18), false, true));
        header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(300, 25, 98, 18),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,fontDefinitionSimple,messages.getText("query.activity.training.subdivision")));

        header.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, gray1, null,
                new Rectangle2D.Float(400, 25, 98, 18), false, true));
        header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(400, 25, 98, 18),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,fontDefinitionSimple,messages.getText("query.activity.training.city")));

        header.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, gray2, null,
                new Rectangle2D.Float(500, 25, 48, 18), false, true));
        header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(500, 25, 48, 18),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,fontDefinitionSimple,messages.getText("generic.activity.activitydata.type")));

        header.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, gray1, null,
                new Rectangle2D.Float(550, 25, 68, 18), false, true));
        header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(550, 25, 68, 18),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,fontDefinitionSimple,messages.getText("query.activity.training.number_part")));

        group.setHeader(header);

        GroupFooter footer = new GroupFooter();
        footer.setName("divisionFooter");
        
        group.setFooter(footer);
        footer.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, Color.BLACK, null,
                new Rectangle2D.Float(0, 0, 700, 15), false, true));
        footer.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(0, 0, 60, 15),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,fontDefinitionBold,"Total"));
        footer.addElement(TextFieldElementFactory.createStringElement("division",new Rectangle2D.Float(60, 0, 200, 15),Color.WHITE,
                ElementAlignment.LEFT,ElementAlignment.BOTTOM,fontDefinitionBold,"","division"));
        
        footer.addElement(NumberFieldElementFactory.createNumberElement("number_participants",new Rectangle2D.Float(550, 0, 100, 15),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,fontDefinitionBold,"","#####", "number_participants"));
        grouplist.add(group);
        return grouplist;
    }
*/
    protected ItemBand createItemBand() {
        ItemBand items = super.createItemBand();        
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,new Rectangle2D.Float(0, 0, 700, 15), false, true));
        items.addElement(TextFieldElementFactory.createStringElement("date",new Rectangle2D.Float(0, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,fontDefinitionSimple,"-","date"));
        int w = 80;
        items.addElement(TextFieldElementFactory.createStringElement("unit",new Rectangle2D.Float(w, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,fontDefinitionSimple,"-","unit"));
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(!Util.empty(divOrgLabels[i][j])) {
                    items.addElement(TextFieldElementFactory.createStringElement("divorg"+String.valueOf(i+1)+String.valueOf(j+1),new Rectangle2D.Float(w, 0, 80, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,fontDefinitionSimple,"","divorg"+String.valueOf(i+1)+String.valueOf(j+1)));
                    w += 80;
                }    
            }
        }
        items.addElement(NumberFieldElementFactory.createStringElement("city",new Rectangle2D.Float(w, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,fontDefinitionSimple,"-","city"));
        w += 80;
        items.addElement(NumberFieldElementFactory.createStringElement("type",new Rectangle2D.Float(w, 0, 70, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,fontDefinitionSimple,"-","type"));
        w += 80;
        items.addElement(NumberFieldElementFactory.createNumberElement("participants",new Rectangle2D.Float(w, 0, 70, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,fontDefinitionSimple,"-","#####","participants"));
        w += 80;
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, Color.BLACK, null,new Rectangle2D.Float(0, 16, 700, 1), false, true));
        return items;
    }
    
    protected void writeFilter(ReportGeneralTrainingForm reportForm, ReportHeader reportHeader)  {
    	reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, Color.ORANGE, null,
                new Rectangle2D.Float(0, 0, 700, 15), false, true));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(0, 0, 700, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,fontDefinitionBold,messages.getText("generic.query.activity.training_g")));

        int height = 25;
        int maxHeight=height;
        height = writeParameter(messages.getText("query.activity.training.country")+":",divGeoValues[1],reportHeader,height,0,70,140,200);
        
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(0, 30, 70, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,fontDefinitionBold,messages.getText("query.activity.training.country")+": "));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(70, 30, 100, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,fontDefinitionSimple,divGeoValues[1]));
        
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(0, 50, 200, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,fontDefinitionBold,messages.getText("query.activity.training.unit")+": "));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(200, 30, 90, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,fontDefinitionBold,messages.getText("query.activity.training.division")+": "));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(290, 30, 100, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,fontDefinitionSimple,divOrgValues[1][1]));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(200, 50, 90, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,fontDefinitionBold,messages.getText("query.activity.training.subdivision")+": "));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(290, 50, 100, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,fontDefinitionSimple,divOrgValues[1][2]));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(400, 30, 150, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,fontDefinitionBold,messages.getText("generic.activity.activitydata.type")+": "));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(550, 30, 100, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,fontDefinitionSimple,divOrgValues[0][0]));
        
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(400, 50, 150, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,fontDefinitionBold,messages.getText("generic.activity.activitydata.state")+": "));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(550, 50, 100, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,fontDefinitionSimple,divOrgValues[0][0]));

    }
    protected ReportHeader createReportHeader() {
        
        ReportHeader reportHeader = new ReportHeader();
        
        
        
        return reportHeader;
    }

protected int writeParameter(String parameterName,String parameterValue,ReportHeader reportHeader,int height,int xLabel,int wLabel,int xValue,int wValue) {
    	 
		
    	if(!Util.empty(parameterValue)) {
    		reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(xLabel, height, wLabel, 18),
                    Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,parameterName));
    		reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(xValue, height, wValue, 18),
                    Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,parameterValue));
    		return height+20;
    	}else if(!Util.empty(parameterName)){ 
    		reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(xLabel, height, wLabel, 18),
                    Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,parameterName));
    		reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(xValue, height, wValue, 18),
                    Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"--"));
        	return height+20;
        }
    	return height;
    }
    protected int writeParametersTitle(String parameterName,ReportHeader reportHeader,int height,int xLabel,int wLabel) {
    		reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(xLabel, height, wLabel, 18),
                    Color.blue,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE_PARAM_FONT,parameterName)); 
    		return height+20;
    }

    protected ReportFooter createReportFooter() {
        ReportFooter reportFooter = new ReportFooter();
        reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, Color.GRAY, null,
                new Rectangle2D.Float(0, 10, 700, 30), false, true));
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(0, 15, 200, 20),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,new FontDefinition ("SansSerif",18),"Total"));
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement(null,new Rectangle2D.Float(200, 15, 200, 20),
                Color.BLACK,ElementAlignment.RIGHT,ElementAlignment.MIDDLE,new FontDefinition ("SansSerif",18),"", "#####","countExpression"));
        return reportFooter;
    }

    protected ExpressionCollection createFunctions() {
        ExpressionCollection functions = super.createFunctions();
        
        final ItemCountFunction countFunction = new ItemCountFunction();
        countFunction.setName("countExpression");
        countFunction.setGroup("dummy");
        functions.add(countFunction);
        
        final ItemSumFunction vehicle_countTotal = new ItemSumFunction();
        vehicle_countTotal.setName("number_participants");
        vehicle_countTotal.setField("vehicle_count");
        functions.add(vehicle_countTotal);

        
        final ElementColorFunction color = new ElementColorFunction();
        color.setName("changeColor");
        color.setElement("Variance Field");
        color.setField("color");
        color.setColorTrue(Color.WHITE);
        color.setColorFalse(Color.LIGHT_GRAY);
        functions.add(color);

        return functions;
    }
}