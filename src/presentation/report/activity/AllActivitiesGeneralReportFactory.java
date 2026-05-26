/**
 * Created on 10-jul-2006
 * CEPA
 * DataCenter 5
 */
package presentation.report.activity;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.StringTokenizer;

import javax.swing.table.TableModel;

import org.apache.struts.action.ActionForm;
import org.jfree.report.ElementAlignment;
import org.jfree.report.ItemBand;
import org.jfree.report.ReportFooter;
import org.jfree.report.ReportHeader;
import org.jfree.report.elementfactory.DateFieldElementFactory;
import org.jfree.report.elementfactory.LabelElementFactory;
import org.jfree.report.elementfactory.NumberFieldElementFactory;
import org.jfree.report.elementfactory.StaticShapeElementFactory;
import org.jfree.report.elementfactory.TextElementFactory;
import org.jfree.report.elementfactory.TextFieldElementFactory;
import org.jfree.report.function.CreateHyperLinksFunction;
import org.jfree.report.function.ElementColorFunction;
import org.jfree.report.function.ExpressionCollection;
import org.jfree.report.function.ItemCountFunction;
import org.jfree.report.function.TextFormatExpression;
import org.jfree.report.style.FontDefinition;

import presentation.report.ReportFactory;
import presentation.translator.web.activity.schedule.ActivityGeneralReportForm;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.administration.corporation.tables.CorporationData;
import communication.language.Text;

public class AllActivitiesGeneralReportFactory extends ReportFactory {
	
	String[] principalLabels;
	
	private SystemAccess sys;	

    protected Text messages;
    
    private int pages;
    private int pageNumber;

    private int width;
    
    protected static final int YPERSONA_LDATA =10;
    protected static final int YLOCATION =10;
    protected static final int YDIVISION =200;
    protected static final FontDefinition STYLE_TITLE= new FontDefinition("SansSerif",13,true,false,false,false);
    protected static final FontDefinition STYLE_SIMPLE_FONT= new FontDefinition("SansSerif",8);
    protected static final FontDefinition STYLE_SIMPLE_BOLD_FONT= new FontDefinition("SansSerif",8,true,false,false,false);
    protected static final FontDefinition STYLE_TOTAL_FONT= new FontDefinition("SansSerif",12,true,false,false,false);
    protected static final FontDefinition STYLE_TITLE_PARAM_FONT= new FontDefinition("SansSerif",9,true,false,true,false);
    
    public AllActivitiesGeneralReportFactory(String name, TableModel model, ActionForm form, SystemAccess sys, Text messages, int count,int pages,int pageNumber) {
        super(name, model, form);
        this.pages = pages;
        this.pageNumber = pageNumber;
        this.messages = messages;
        this.count = count;
        parseDivisionValues(sys,messages);
        this.sys=sys;
        this.principalLabels = sys.getUserSessionData().getFilter().getPrincipalLabels();
        width = 530;
        for(int i = 0; i < principalLabels.length; i++) {
            if(!Util.empty(principalLabels[i])) {
                width += 120;
            }
        }
    }

    protected int getWidth() {
    	return width+82;
    	
    }
    
    protected int getReportWidth() {
    	return width;
    }

    protected ItemBand createItemBand() {
        ItemBand items = super.createItemBand();
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,new Rectangle2D.Float(10, 0, getReportWidth(), 15), false, true));
        items.addElement(DateFieldElementFactory.createDateElement("URL-Field",new Rectangle2D.Float(10, 0, 60, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-",DATE_FORMAT,"activityDate"));        
        items.addElement(TextFieldElementFactory.createStringElement("type",new Rectangle2D.Float(70, 0, 150, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","type"));
        items.addElement(TextFieldElementFactory.createStringElement("state",new Rectangle2D.Float(220, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","state"));
        items.addElement(TextFieldElementFactory.createStringElement("country",new Rectangle2D.Float(320, 0, 90, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","country"));        
        int x = 410; 
        if(principalLabels.length > 0) {
            items.addElement(TextFieldElementFactory.createStringElement("division",new Rectangle2D.Float(x, 0, 120, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","division"));
            x += 120;
            if(principalLabels.length > 1) {
                items.addElement(TextFieldElementFactory.createStringElement("subdivision",new Rectangle2D.Float(x, 0, 120, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","subdivision"));
                x += 120;
                if(principalLabels.length > 2) {
                    items.addElement(TextFieldElementFactory.createStringElement("unit",new Rectangle2D.Float(x, 0, 120, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","unit"));
                    x += 120;
                    if(principalLabels.length > 3) {
                        items.addElement(TextFieldElementFactory.createStringElement("security",new Rectangle2D.Float(x, 0, 120, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","security"));
                        x += 120;
                    } 
                } 
            }
        }
        items.addElement(TextFieldElementFactory.createStringElement("participants",new Rectangle2D.Float(x, 0, 120, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","participants"));        
        return items;
    }
    
    protected ReportHeader createReportHeader() {
    	ActivityGeneralReportForm reportForm = (ActivityGeneralReportForm)form;
        ReportHeader reportHeader = new ReportHeader();
        int height = writeFilter(reportForm, reportHeader);
                
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FirstLevelColor, null,
                new Rectangle2D.Float(10, height, getReportWidth(), 18), false, true));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 60, 18), 
        		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.activity.activitydata.date")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(70, height, 150, 18), 
        		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.activity.activitydata.type")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(220, height, 100, 18), 
        		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.activity.activitydata.state")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(320, height, 90, 18),
         		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(divGeoLabels[1])));
        int x = 410; 
        for(int i = 0; i < principalLabels.length; i++) {
            if(!Util.empty(principalLabels[i])) {
                reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 120, 18),
                       Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(principalLabels[i])));
                x += 120;
            }
        }
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 120, 18),
        		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.activity.activitydata.drivercount")));
        
        return reportHeader;
    }

	protected int writeFilter(ActivityGeneralReportForm reportForm, ReportHeader reportHeader) {
		reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 25), false, true));
        
		reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, getReportWidth(), 25),
        		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText(name)));
        
        int height = 25;
        int maxHeight=height;
        height = writeParametersTitle(messages.getText("generic.activity.activitydata"),reportHeader,height,10,210);
        
        String strType ="";
        String[] type = reportForm.getType();
        StringTokenizer str;
        String[] typeCol;
        int cont = 0;
        
        if (type.length == 1) {
        	str = new StringTokenizer(type[0],",");
        	typeCol = new String[str.countTokens()];
        	while (str.hasMoreTokens()) {
        		typeCol[cont] =str.nextToken();
        		cont++;
        	}
        	type = typeCol;
        }
        
        int k = 0;
        while (k< (type.length -1)) {
        	strType += messages.getText(type[k]) + ",";
        	k++;
        }
        strType += messages.getText(type[k]); 

        //strType += messages.getText(type);        
        
        height = writeParameter(messages.getText("generic.activity.activitydata.type")+":",strType,reportHeader,height,10,60,70,150);
        height = writeParameter(messages.getText("generic.activity.activitydata.state")+":",reportForm.getStates(messages),reportHeader,height,10,60,70,150);
               
        height = writeParametersTitle(messages.getText("driver.query.period"),reportHeader,height,10,210);
        height = writeParameter(messages.getText("driver.query.from")+":",reportForm.getPeriod(messages,1),reportHeader,height,10,60,70,150);
        height = writeParameter(messages.getText("driver.query.to")+":",reportForm.getPeriod(messages,2),reportHeader,height,10,60,70,150);

        height = writeParametersTitle(messages.getText("generic.driver.divgeo"),reportHeader,height,10,210);
        for(int i = 0; i < 3; i++) {
           if(divGeoLabels[i]!=null) {
               height = writeParameter(divGeoLabels[i]+":",divGeoValues[i],reportHeader,height,10,60,70,150);
           }
        }        
        maxHeight=Math.max(maxHeight,height);
        height = 25;
      
      
      String[] divOrgs = sys.getUserSessionData().getFilter().getLevelLabels();
      for(int i = 0; i < divOrgs.length; i++) {
          if(!Util.empty(divOrgs[i])) {
              height = writeParametersTitle(messages.getText(divOrgs[i]),reportHeader,height,220,210);
              for(int j = 0; j < 3; j++) {
                  if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 1 && j== 0) && 
                          !(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 2 && j== 0)) {
                      if(!Util.empty(divOrgLabels[i][j])) {
                          height = writeParameter(divOrgLabels[i][j]+":",divOrgValues[i][j],reportHeader,height,220,90,310,120);
                      }
                  }
              }
          }
      }
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
        ReportFooter reportFooter = new ReportFooter();
        
        reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,
                new Rectangle2D.Float(10, 18, getReportWidth(), 20), false, true));
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 18, 60, 20),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,messages.getText("query.driver.driverdata.total")));
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement(null,new Rectangle2D.Float(70, 18, 120, 20),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,"", "#####","total"));
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(getReportWidth()-360, 18, 240, 20),
                Color.BLACK,ElementAlignment.RIGHT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,messages.getText("query.driver.driverdata.totalparticipants")));
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement(null,new Rectangle2D.Float(getReportWidth()-120, 18, 120, 20),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,"", "#####","totalparticipants"));
        
        
        if(((ActivityGeneralReportForm)form).getOutput().equals("HTML")) {
            int left = 60;
            reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(0, 40, 50, 15),
                    Color.BLUE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_TITLE_PARAM_FONT,messages.getText("generic.messages.pages")));
            for(int i = 1; i <= pages; i++) {
                int width = (i < 10) ? 15 : 20; 
                if(i != pageNumber) {
                    reportFooter.addElement(LabelElementFactory.createLabelElement("URL-Field-"+i,new Rectangle2D.Float(left, 40, width, 15),
                            Color.BLUE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_TITLE_PARAM_FONT,String.valueOf(i)));
                }else {
                    reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(left, 40, width, 15),
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
/*
        TextFormatExpression textExp = new TextFormatExpression();
        textExp.setName("URLCreateExpression");
        textExp.setPattern("./layout.jsp?refer=/activity/schedule/OneActivityGeneralReport.jsp?activityId={0}");
        textExp.setField(0,"id");
        functions.add(textExp);
*/
        
        CreateHyperLinksFunction link = new CreateHyperLinksFunction();
        link.setName("AddHRefs");
        link.setField("link");
        link.setElement("URL-Field");
        functions.add(link);
        
        if(((ActivityGeneralReportForm)form).getOutput().equals("HTML")) {
            for(int i = 1; i <= pages; i++) {
                if(i != pageNumber) {
                    TextFormatExpression textExp2 = new TextFormatExpression();
                    textExp2.setName("URLCreateExpression"+i);
                    textExp2.setPattern("./layout.jsp?refer=/activity/schedule/AllActivitiesGeneralReportPage.jsp?page="+i+((ActivityGeneralReportForm)form).getLink());
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
