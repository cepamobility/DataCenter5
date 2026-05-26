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
import org.jfree.report.function.CreateHyperLinksFunction;
import org.jfree.report.function.ElementColorFunction;
import org.jfree.report.function.ExpressionCollection;
import org.jfree.report.function.ItemCountFunction;
import org.jfree.report.function.TextFormatExpression;
import org.jfree.report.style.FontDefinition;

import presentation.report.ReportFactory;
import presentation.translator.web.activity.schedule.AssessmentResultForm;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.language.Text;

public class AssessmentResultReportFactory extends ReportFactory {

	String[] principalLabels;
	protected Text messages;
    private int pages;
    private int pageNumber;
    private int last;

   
    protected static final FontDefinition STYLE_TITLE= new FontDefinition("SansSerif",13,true,false,false,false);
    protected static final FontDefinition STYLE_SIMPLE_FONT= new FontDefinition("SansSerif",8);
    protected static final FontDefinition STYLE_SIMPLE_BOLD_FONT= new FontDefinition("SansSerif",8,true,false,false,false);
    protected static final FontDefinition STYLE_TOTAL_FONT= new FontDefinition("SansSerif",12,true,false,false,false);
    protected static final FontDefinition STYLE_TITLE_PARAM_FONT= new FontDefinition("SansSerif",9,true,false,true,false);
    
    /**
     * 
     * @param name
     * @param model
     * @param form
     * @param sys
     * @param messages
     * @param pages
     * @param pageNumber
     */
    public AssessmentResultReportFactory(String name, TableModel model, ActionForm form, 
    		SystemAccess sys, Text messages,int pages,int pageNumber) {
        super(name, model, form);
        this.pages = pages;
        this.pageNumber = pageNumber;
        this.messages = messages;
        count = 1;
        //parseDivisionValues(sys,messages);
        this.principalLabels = sys.getUserSessionData().getFilter().getPrincipalLabels();
        this.divGeoLabels = sys.getUserSessionData().getFilter().getDivGeoLabels();
        this.sys=sys;
        last = 600;
        for(int i = 0; i < principalLabels.length; i++) {
            if(!Util.empty(principalLabels[i])) {
                last += 100;
            }
        }
    }

    /**
     * Constructor
     * @param name
     * @param model
     * @param form
     * @param sys
     * @param messages
     * @param count
     * @param pages
     * @param pageNumber
     */
    public AssessmentResultReportFactory(String name, TableModel model, ActionForm form, 
    		SystemAccess sys, Text messages, int count,int pages,int pageNumber) {
        this(name,model,form,sys,messages,pages,pageNumber);
        this.count = count;
    }

    protected int getWidth() {
    	return last + 82;
    }
    
    protected int getReportWidth() {
    	return last;
    }

    protected ItemBand createItemBand() {
        ItemBand items = super.createItemBand();
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement(
    		"Variance Field", null, null,new Rectangle2D.Float(10, 0, 
    		getReportWidth(), 15), false, true));
        
        items.addElement(TextFieldElementFactory.createStringElement(
    		"URL-Field",new Rectangle2D.Float(10, 0, 160, 15),Color.BLACK,
    		ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","name"));
        
        items.addElement(TextFieldElementFactory.createStringElement(
    		"country",new Rectangle2D.Float(170, 0, 100, 15),Color.BLACK,
    		ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","country"));        
        int x = 270;
        
        
        // Divisiones
        if(!Util.empty(principalLabels[0])) {
            items.addElement(TextFieldElementFactory.createStringElement(
            		"division",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,
            		ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","division"));
            x += 100;
        }
        
        if(principalLabels.length > 1) {
            if(!Util.empty(principalLabels[1])) {
                items.addElement(TextFieldElementFactory.createStringElement(
                	"subdivision",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,
                	ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","subdivision"));
                x += 100;
            }
            if(principalLabels.length > 2) {
                if(!Util.empty(principalLabels[2])) {
                    items.addElement(TextFieldElementFactory.createStringElement(
                    	"unit",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,
                    	ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","unit"));        
                    x += 100;                    
                }
//                if(principalLabels.length > 3) {
//                    if(!Util.empty(principalLabels[3])) {
//                        items.addElement(TextFieldElementFactory.createStringElement("safety",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","safety"));        
//                        x += 100;
//                    }
//                }
            }
        }
        
        // ACA van los datos del Assessment
        // psiResult, psiNote, testResult, testNote

        items.addElement(TextFieldElementFactory.createStringElement(
        		"psiResult",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,
        		ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","psiResult"));    
        x += 100;
        items.addElement(TextFieldElementFactory.createStringElement(
        		"psiNote",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,
        		ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","psiNote"));    
        x += 100;
        items.addElement(TextFieldElementFactory.createStringElement(
        		"testResult",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,
        		ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","testResult"));    
        x += 100;
        items.addElement(TextFieldElementFactory.createStringElement(
        		"testNote",new Rectangle2D.Float(x, 0, 100, 15),Color.BLACK,
        		ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","testNote"));    
        x += 100;
        
        
        
        return items;
    }
    
    protected ReportHeader createReportHeader() {
    	AssessmentResultForm reportForm = (AssessmentResultForm)form;
        ReportHeader reportHeader = new ReportHeader();
        int height = writeFilter(reportForm, reportHeader);
        
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FirstLevelColor, null,
                new Rectangle2D.Float(10, height, getReportWidth(), 18), false, true));
       
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 160, 18), 
        		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.firstname")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(170, height, 100, 18),
         		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(divGeoLabels[1])));        
        
        int x = 270;
        for(int i = 0; i < Math.min(principalLabels.length,3); i++) {
            if(!Util.empty(principalLabels[i])) {
                reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 100, 18),
                       Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(principalLabels[i])));
                x += 100;
            }
        }
        
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 100, 18),
         		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.assessmentresult.psiresult")));
        x += 100;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 100, 18),
         		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.assessmentresult.psinote")));
        x += 100;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 100, 18),
         		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.assessmentresult.testresult")));
        x += 100;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 100, 18),
         		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.assessmentresult.testnote")));
        x += 100;
          
        return reportHeader;
    }

	protected int writeFilter(AssessmentResultForm reportForm, ReportHeader reportHeader) {
		reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 25), false, true));        
		reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, getReportWidth(), 25),
        		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText(name)));
		reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, Color.WHITE, null,
                new Rectangle2D.Float(10, 25, getReportWidth(), 25), false, true));        
		
        int height = 50;
        int maxHeight=height;
       
        maxHeight=Math.max(maxHeight,height);
              
       return maxHeight;
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
    	int height = 40;
    	
        ReportFooter reportFooter = new ReportFooter();
        reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, null,
                new Rectangle2D.Float(10, 10, getReportWidth(), 20), false, true));
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 160, 20),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,messages.getText("query.driver.driverdata.total")));
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement(null,new Rectangle2D.Float(getReportWidth()-50, 10, 90, 20),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,"", "#####","total"));
        
        String format = (String)((AssessmentResultForm)form).getFormat();
        if(format.equals("HTML")) {
            int left = 60;
            reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(0, 40, 50, 15),
                    Color.BLUE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_TITLE_PARAM_FONT,messages.getText("generic.messages.pages")));
            for(int i = 1; i <= pages; i++) {
                int width = (i < 10) ? 15 : 20; 
                
                if (left>getReportWidth()){
                	left=60;
                	height+=15;
                }                
                
                if(i != pageNumber) {
                    reportFooter.addElement(LabelElementFactory.createLabelElement("URL-Field-"+i,new Rectangle2D.Float(left, height, width, 15),
                            Color.BLUE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_TITLE_PARAM_FONT,String.valueOf(i)));
                }else {
                    reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(left, height, width, 15),
                            Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(i)));
                }
                left += width;
            }
        }
        return reportFooter;
    }

    protected ExpressionCollection createFunctions() {
        ExpressionCollection functions = super.createFunctions();
        
        final ItemCountFunction countFunction = new ItemCountFunction();
        countFunction.setName("countExpression");
        countFunction.setGroup("dummy");
        functions.add(countFunction);
        
        final ElementColorFunction color = new ElementColorFunction();
        color.setName("changeColor");
        color.setElement("Variance Field");
        color.setField("color");
        color.setColorTrue(Color.WHITE);
        color.setColorFalse(DCGray2);
        functions.add(color);

        TextFormatExpression textExp = new TextFormatExpression();
        textExp.setName("URLCreateExpression");
        textExp.setPattern("./layout.jsp?refer=/driver/DriverView.jsp?id={0}");
        textExp.setField(0,"idDriver");
        functions.add(textExp);

        CreateHyperLinksFunction link = new CreateHyperLinksFunction();
        link.setName("AddHRefs");
        link.setField("URLCreateExpression");
        link.setElement("URL-Field");
        functions.add(link);
        
        String format = (String) ((AssessmentResultForm)form).getFormat();
        String assessment = (String) ((AssessmentResultForm)form).getAssessment();
        
        if(format.equals("HTML")) {
            for(int i = 1; i <= pages; i++) {
                if(i != pageNumber) {
                    TextFormatExpression textExp2 = new TextFormatExpression();
                    textExp2.setName("URLCreateExpression"+i);
                    textExp2.setPattern(
                    		"./layout.jsp?refer=/activity/schedule/assessmentResultReportPage.jsp?"+
                    		"page="+i+
                    		"&assessment="+assessment+
                    		"&format="+format);
                    functions.add(textExp2);
            
                    CreateHyperLinksFunction link2 = new CreateHyperLinksFunction();
                    link2.setName("AddHRefs"+i);
                    link2.setField("URLCreateExpression"+i);
                    link2.setElement("URL-Field-"+i);
                    functions.add(link2);
                }
            }
        }
        

        return functions;
    }
}
