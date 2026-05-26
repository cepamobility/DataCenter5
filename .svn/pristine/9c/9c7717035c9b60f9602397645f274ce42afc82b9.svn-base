/**
 * Created on 10-jul-2006
 * CEPA
 * DataCenter 5
 */
package presentation.report.activity;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.table.TableModel;

import org.jfree.report.ElementAlignment;
import org.jfree.report.ItemBand;
import org.jfree.report.ReportFooter;
import org.jfree.report.ReportHeader;
import org.jfree.report.elementfactory.DateFieldElementFactory;
import org.jfree.report.elementfactory.DrawableFieldElementFactory;
import org.jfree.report.elementfactory.LabelElementFactory;
import org.jfree.report.elementfactory.StaticShapeElementFactory;
import org.jfree.report.elementfactory.TextFieldElementFactory;
import org.jfree.report.function.CreateHyperLinksFunction;
import org.jfree.report.function.ElementColorFunction;
import org.jfree.report.function.ExpressionCollection;
import org.jfree.report.function.TextFormatExpression;
import org.jfree.ui.FloatDimension;

import presentation.report.ReportFactory;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.activity.tables.CEPAActivityConstants;
import communication.activity.tables.CEPAActivityScheduleData;
import communication.language.Text;

public class ActivityGeneralReportFactory extends ReportFactory {

    private Text messages;
    private CEPAActivityScheduleData data;
    //private int[][] clasifications = {{1,2},{2,3},{4,5}};
    private int[][] clasifications;
    private String reportType; 
    
    protected SystemAccess sys;
    protected String[] labels;    
    
    public ActivityGeneralReportFactory(String name,TableModel model,SystemAccess sys,CEPAActivityScheduleData data,int[][] clasifications,String reportType) {
        super(name, model, null);
        this.messages = sys.getText();
        this.data = data;
        this.clasifications = clasifications;
        this.reportType = reportType; 
        this.sys = sys;
        labels = sys.getUserSessionData().getFilter().getDivOrgLabels();
    }

    protected int getWidth() {
        return 650;
    }

//  it is declared but it is not used 
    protected int getReportWidth() {
    	return 0;
    }
    protected ItemBand createItemBand() {
        ItemBand items = super.createItemBand();
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,new Rectangle2D.Float(10, 0, 460, 15), false, true));
        items.addElement(TextFieldElementFactory.createStringElement("URL-Field",new Rectangle2D.Float(10, 0, 120, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","firstname"));
        items.addElement(TextFieldElementFactory.createStringElement("URL-Field",new Rectangle2D.Float(130, 0, 120, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","lastname"));
        items.addElement(DateFieldElementFactory.createDateElement("birth",new Rectangle2D.Float(250, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-",DATE_FORMAT,"birthdate"));
        items.addElement(TextFieldElementFactory.createStringElement("result",new Rectangle2D.Float(350, 0, 120, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","note"));
        return items;
    }
    
    protected ReportHeader createReportHeader() {
        ReportHeader reportHeader = new ReportHeader();
                
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, 460, 25),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("query.activity.generalreport.title")+" "+messages.getText(data.getType().getResourceBundleId())));

        int height = 25;
        int maxHeight=height;

        height = writeParametersTitle(messages.getText("query.activity.generalreport.data"),reportHeader,height,10,150); 
        height = writeParameter(messages.getText("query.activity.generalreport.date")+":",Util.formatDate(data.getDate()),reportHeader,height,10,70,80,120);
        height = writeParameter(messages.getText("query.activity.generalreport.country")+":",messages.getText(data.getCountryName()),reportHeader,height,10,70,80,120);
        height = writeParameter(messages.getText("query.activity.generalreport.city")+":",data.getCity(),reportHeader,height,10,70,80,120);
        int count = 0;
        
        int cantNotas;
        if (data.getIsOld()) { 
        	cantNotas = 12;
        }
        else {
        	cantNotas = 6;
        }
        		
        //for(int i = 0; i < 4; i++) {
        for(int i = 0; i < cantNotas; i++) {
            count += clasifications[i][0]; 
        }
        
        
        height = writeParameter(messages.getText("query.activity.generalreport.participants")+":",String.valueOf(count),reportHeader,height,10,110,120,20);

        maxHeight=Math.max(maxHeight,height);
        height = 25;
        
        /*
        height = writeParametersTitle(messages.getText("monsanto.divisions.assignedvehicle"),reportHeader,height,200,150);
        //height = writeParameter(messages.getText(data.getLabel(ActivityPrincipalAttributes.DIV_ORG,1,1))+":",data.getData(ActivityPrincipalAttributes.DIV_ORG,1,1),reportHeader,height,200,100,300,120);
        height = writeParameter(messages.getText(data.getLabel(ActivityPrincipalAttributes.DIV_ORG,1,1))+":",data.getAssignedVehicle(),reportHeader,height,200,100,300,120);
        
        height = writeParametersTitle(messages.getText("corporation.monsanto.divisions"),reportHeader,height,200,150);
        //height = writeParameter(messages.getText(data.getLabel(ActivityPrincipalAttributes.DIV_ORG,2,2))+":",data.getData(ActivityPrincipalAttributes.DIV_ORG,2,2),reportHeader,height,200,100,300,120);
        height = writeParameter(messages.getText(data.getLabel(ActivityPrincipalAttributes.DIV_ORG,2,2))+":",data.getDivision(),reportHeader,height,200,100,300,120);
        //height = writeParameter(messages.getText(data.getLabel(ActivityPrincipalAttributes.DIV_ORG,2,3))+":",data.getData(ActivityPrincipalAttributes.DIV_ORG,2,3),reportHeader,height,200,100,300,120);
        height = writeParameter(messages.getText(data.getLabel(ActivityPrincipalAttributes.DIV_ORG,2,3))+":",data.getSubdivision(),reportHeader,height,200,100,300,120);

        height = writeParametersTitle(messages.getText("corporation.monsanto.units"),reportHeader,height,200,150);
        //height = writeParameter(messages.getText(data.getLabel(ActivityPrincipalAttributes.DIV_ORG,3,2))+":",data.getData(ActivityPrincipalAttributes.DIV_ORG,3,2),reportHeader,height,200,100,300,120);
        //height = writeParameter(messages.getText(data.getLabel(ActivityPrincipalAttributes.DIV_ORG,3,2))+":",data.getData(ActivityPrincipalAttributes.DIV_ORG,3,2),reportHeader,height,200,100,300,120);
        height = writeParameter(messages.getText(data.getLabel(ActivityPrincipalAttributes.DIV_ORG,3,2))+":",data.getUnit(),reportHeader,height,200,100,300,120);
*/
       int j=0;
        for(int i = 0; i < 9; i++) {
            j++;
            if(!Util.empty(labels[i])) {
                if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 3) && 
                        !(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 6)) {
        //            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 90, 18),
         //               Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(labels[i])));
                    //height = writeParametersTitle(messages.getText(labels[i]),reportHeader,height,200,150);
                	
                	String div = data.getData(CEPAActivityScheduleData.DIV_ORG,(i/3)+1,j);
                    height = writeParameter(messages.getText(labels[i])+":",div,reportHeader,height,200,70,300,90);
                    
                    //x += 90;
                }
            }
        	if (j==3) {
        		j = 0;
        	}            
        }
        
        
        height=Math.max(maxHeight,height);
        
        

       
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 460, 18),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("query.activity.generalreport.clasification.distribution")));
        height += 25;
        
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,DCGray1, null,
                new Rectangle2D.Float(10, height, 460, 18), false, true));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 200, 18), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.activity.generalreport.calification")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(210, height, 150, 18), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.activity.generalreport.drivercount")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(360, height, 100, 18),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.activity.generalreport.percentage")));
        height += 18;
        
        if (!data.getIsOld()) {

	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 200, 18), 
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(CEPAActivityConstants.APRUVED)));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(210, height, 150, 18), 
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(clasifications[0][0])));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(360, height, 100, 18),
	                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(clasifications[0][1])));
	        height += 18;
	        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,DCGray2, null,
	                new Rectangle2D.Float(10, height, 460, 18), false, true));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 200, 18), 
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(CEPAActivityConstants.APRUVEDWITHOBSERVATIONS)));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(210, height, 150, 18), 
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(clasifications[1][0])));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(360, height, 100, 18),
	                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(clasifications[1][1])));
	        height += 18;
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 200, 18), 
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(CEPAActivityConstants.NOTAPRUVED)));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(210, height, 150, 18), 
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(clasifications[2][0])));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(360, height, 100, 18),
	                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(clasifications[2][1])));

	        height += 18;
	        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,DCGray2, null,
	                new Rectangle2D.Float(10, height, 460, 18), false, true));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 200, 18), 
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(CEPAActivityConstants.THEORETICALPENDING)));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(210, height, 150, 18), 
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(clasifications[3][0])));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(360, height, 100, 18),
	                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(clasifications[3][1])));
	        
	        height += 18;
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 200, 18), 
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(CEPAActivityConstants.PRACTICEPENDING)));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(210, height, 150, 18), 
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(clasifications[4][0])));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(360, height, 100, 18),
	                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(clasifications[4][1])));
	   
	        
	        height += 18;
	        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,DCGray2, null,
	                new Rectangle2D.Float(10, height, 460, 18), false, true));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 200, 18), 
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(CEPAActivityConstants.WITHOUTNOTE)));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(210, height, 150, 18), 
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(clasifications[5][0])));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(360, height, 100, 18),
	                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(clasifications[5][1])));
	        
	        height += 30;        	
        	
        }
        else {
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 200, 18), 
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("dc4.notasdescripcion.nddesccorta1")));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(210, height, 150, 18), 
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(clasifications[0][0])));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(360, height, 100, 18),
	                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(clasifications[0][1])));                
	        height += 18;
	        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,DCGray2, null,
	                new Rectangle2D.Float(10, height, 460, 18), false, true));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 200, 18), 
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("dc4.notasdescripcion.nddesccorta2")));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(210, height, 150, 18), 
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(clasifications[1][0])));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(360, height, 100, 18),
	                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(clasifications[1][1])));
	        height += 18;
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 200, 18), 
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("dc4.notasdescripcion.nddesccorta3")));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(210, height, 150, 18), 
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(clasifications[2][0])));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(360, height, 100, 18),
	                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(clasifications[2][1])));
	        height += 18;
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 200, 18), 
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("dc4.notasdescripcion.nddesccorta4")));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(210, height, 150, 18), 
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(clasifications[3][0])));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(360, height, 100, 18),
	                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(clasifications[3][1])));
	        height += 18;
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 200, 18), 
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("dc4.notasdescripcion.nddesccorta5")));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(210, height, 150, 18), 
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(clasifications[4][0])));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(360, height, 100, 18),
	                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(clasifications[4][1])));
	        height += 18;
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 200, 18), 
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("dc4.notasdescripcion.nddesccorta6")));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(210, height, 150, 18), 
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(clasifications[5][0])));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(360, height, 100, 18),
	                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(clasifications[5][1])));
	        height += 18;
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 200, 18), 
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("dc4.notasdescripcion.nddesccorta7")));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(210, height, 150, 18), 
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(clasifications[6][0])));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(360, height, 100, 18),
	                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(clasifications[6][1])));
	        height += 18;
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 200, 18), 
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("dc4.notasdescripcion.nddesccorta8")));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(210, height, 150, 18), 
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(clasifications[7][0])));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(360, height, 100, 18),
	                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(clasifications[7][1])));
	        height += 18;
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 200, 18), 
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("dc4.notasdescripcion.nddesccorta9")));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(210, height, 150, 18), 
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(clasifications[8][0])));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(360, height, 100, 18),
	                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(clasifications[8][1])));
	        height += 18;
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 200, 18), 
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("dc4.notasdescripcion.nddesccorta10")));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(210, height, 150, 18), 
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(clasifications[9][0])));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(360, height, 100, 18),
	                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(clasifications[9][1])));
	        height += 18;
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 200, 18), 
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("dc4.notasdescripcion.nddesccorta11")));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(210, height, 150, 18), 
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(clasifications[10][0])));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(360, height, 100, 18),
	                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(clasifications[10][1])));               
	        
	        height += 18;
	        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,DCGray2, null,
	                new Rectangle2D.Float(10, height, 460, 18), false, true));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 200, 18), 
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.activity.note.withoutnote")));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(210, height, 150, 18), 
	                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(clasifications[11][0])));
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(360, height, 100, 18),
	                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(clasifications[11][1])));
	        
	        height += 30;
        }
        
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 460, 18),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,messages.getText("query.activity.generalreport.drivers")));
        height += 25;

        DrawableFieldElementFactory factory = new DrawableFieldElementFactory();
        factory.setName("drawable-field");
        if(!Util.empty(reportType) && !reportType.equals("HTML")) {
            factory.setAbsolutePosition(new Point2D.Float(0, height));
            factory.setMinimumSize(new FloatDimension(500, 300));
            factory.setFieldname("Resultados");
            reportHeader.addElement(factory.createElement());
            height += 325;
        }


        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,DCGray1, null,
                new Rectangle2D.Float(10, height, 460, 25), false, true));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 120, 18), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.firstname")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(130, height, 120, 18), 
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.lastname")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(250, height, 100, 18),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.birthdate")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(350, height, 120, 18),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.activity.generalreport.result")));
        
        
        return reportHeader;
    }


    protected ReportFooter createReportFooter() {
        ReportFooter reportFooter = new ReportFooter();
/*        DrawableFieldElementFactory factory = new DrawableFieldElementFactory();
        factory.setName("drawable-field");
        if(!Util.empty(reportType) && !reportType.equals("HTML")) {
            factory.setAbsolutePosition(new Point2D.Float(0, 0));
            factory.setMinimumSize(new FloatDimension(600, 400));
            factory.setFieldname("Resultados");
            reportFooter.addElement(factory.createElement());
        }*/
        return reportFooter;
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
    

    protected ExpressionCollection createFunctions() {
        ExpressionCollection functions = super.createFunctions();
        
        final ElementColorFunction color = new ElementColorFunction();
        color.setName("changeColor");
        color.setElement("Variance Field");
        color.setField("color");
        color.setColorTrue(Color.WHITE);
        color.setColorFalse(DCGray2);
        functions.add(color);

        TextFormatExpression textExp = new TextFormatExpression();
        textExp.setName("URLCreateExpression");
        
        
        //String idActivity = String.valueOf(data.getActivityId());
        if (data.getIsOld()) {
        	textExp.setPattern("./ActivityReportOld.do?idDriver={0}&output=HTML&idActivity={1}");
        }
        else {
        	textExp.setPattern("./ActivityReportNew.do?idDriver={0}&output=HTML&idActivity={1}");        			
        }
        //textExp.setPattern("./layout.jsp?refer=/driver/DriverView.jsp?id={0}");
        textExp.setField(0,"id");
        textExp.setField(1,"idActivity");        
        functions.add(textExp);

        CreateHyperLinksFunction link = new CreateHyperLinksFunction();
        link.setName("AddHRefs");
        link.setField("URLCreateExpression");
        link.setElement("URL-Field");
            functions.add(link);

        return functions;
    }
}
