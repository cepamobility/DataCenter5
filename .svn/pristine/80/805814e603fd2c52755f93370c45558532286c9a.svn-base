package presentation.report.activity;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.Date;

import javax.swing.table.TableModel;

import org.apache.struts.action.ActionForm;
import org.jfree.report.ElementAlignment;
import org.jfree.report.GroupList;
import org.jfree.report.ItemBand;
import org.jfree.report.ReportFooter;
import org.jfree.report.ReportHeader;
import org.jfree.report.elementfactory.DateFieldElementFactory;
import org.jfree.report.elementfactory.LabelElementFactory;
import org.jfree.report.elementfactory.NumberFieldElementFactory;
import org.jfree.report.elementfactory.StaticShapeElementFactory;
import org.jfree.report.elementfactory.TextFieldElementFactory;
import org.jfree.report.function.CreateHyperLinksFunction;
import org.jfree.report.function.ElementColorFunction;
import org.jfree.report.function.ExpressionCollection;
import org.jfree.report.function.ItemCountFunction;
import org.jfree.report.function.TextFormatExpression;

import presentation.report.ReportFactory;
import presentation.translator.web.activity.schedule.ReportCommentaryDriveForm;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.activity.report.ActivityReportCommentaryDriveData;
import communication.administration.corporation.tables.CorporationData;
import communication.driver.tables.DriverData;
import communication.language.Text;

public class  ActivityReportCommentaryDriveFactory extends ReportFactory {

    protected Text messages;    
    protected ActivityReportCommentaryDriveData activity;
    private String reportType;
    private String commentaryDriveType;
    private Date lastDate;
    private Date initialDate;
    private int pages;
    private int pageNumber;
    private int driverState;
    private SystemAccess sys;
    
	private String[] principalLabels;
    private int corporationId;
    private int last;
	
    public ActivityReportCommentaryDriveFactory(ActivityReportCommentaryDriveData activity,String name,	TableModel model, ActionForm form, SystemAccess sys, int pages, int pageNumber, int count) {
        super(name, model,form);
        this.messages = sys.getText(); 
        this.parseDivisionValues(sys, messages);
        this.activity = activity; 
        this.reportType = ((ReportCommentaryDriveForm)form).getOutput();
        this.commentaryDriveType = ((ReportCommentaryDriveForm)form).getCommentaryDriveKind();
        this.initialDate = ((ReportCommentaryDriveForm)form).getInitialDate();
        this.lastDate = ((ReportCommentaryDriveForm)form).getLastDate();       
        this.count = count;
        this.pages = pages;
        this.sys = sys;
        this.pageNumber = pageNumber;
        this.driverState = ((ReportCommentaryDriveForm)form).getDriverState();
        this.principalLabels = sys.getUserSessionData().getFilter().getPrincipalLabels();
        this.sys=sys;
        this.corporationId = sys.getUserSessionData().getFilter().getCorporationId().intValue();
        last = (corporationId == CorporationData.MONSANTO) ? 550 : 330;
        for(int i = 0; i < principalLabels.length; i++) {
            if(!Util.empty(principalLabels[i])) {
                last += 100;
            }
        }
    }
    
    protected GroupList createGroups() {
        GroupList grouplist = super.createGroups();        
        return grouplist;    	
    }
    
    protected int getWidth() {
    	return last+82;
    }
    
//  it is declared but it is not used 
    protected int getReportWidth() {
    	return last-10;
    }
    
    protected ItemBand createItemBand() {
        ItemBand items = super.createItemBand();       
        
       	items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null,null,new Rectangle2D.Float(10, 0, getReportWidth(), 10), false, true)); 
       	items.addElement(TextFieldElementFactory.createStringElement("URL-Field",new Rectangle2D.Float(10, 0, 100, 8),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"lastname", "lastname"));
       	items.addElement(TextFieldElementFactory.createStringElement("firstname",new Rectangle2D.Float(110, 0, 80, 8),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"firstname", "firstname"));
       	items.addElement(TextFieldElementFactory.createStringElement("country",new Rectangle2D.Float(190,0,80,8),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"country","country"));
        int x = 270;
        if(!Util.empty(principalLabels[0])) {
            items.addElement(TextFieldElementFactory.createStringElement("division",new Rectangle2D.Float(x, 0, 100, 8),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","division"));
            x += 100;
        }
        if(principalLabels.length > 1 && !Util.empty(principalLabels[1])) {
            items.addElement(TextFieldElementFactory.createStringElement("subdivision",new Rectangle2D.Float(x, 0, 100, 8),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","subdivision"));
            x += 100;
        }
        if(principalLabels.length > 2 && !Util.empty(principalLabels[2])) {
            items.addElement(TextFieldElementFactory.createStringElement("unit",new Rectangle2D.Float(x, 0, 100, 8),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","unit"));        
            x += 100;
        }
        if(principalLabels.length > 3 && !Util.empty(principalLabels[3])) {
            items.addElement(TextFieldElementFactory.createStringElement("safety",new Rectangle2D.Float(x, 0, 100, 8),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","safety"));        
            x += 100;
        }
        if(corporationId == CorporationData.MONSANTO) {
            items.addElement(TextFieldElementFactory.createStringElement("allowed",new Rectangle2D.Float(x, 0, 100, 8),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","allowed"));
            x += 100;
            items.addElement(TextFieldElementFactory.createStringElement("licenseObs",new Rectangle2D.Float(x, 0, 120, 8),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","licenseObs"));
            x += 120;
        }
       	items.addElement(TextFieldElementFactory.createStringElement("count",new Rectangle2D.Float(x,0,60,8),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"count","count"));
       	return items;
    }
    
    
    /*
     * Add an Activity Data section in the Header.
    */ 
    private int addActivityData(ReportHeader reportHeader, int x, int y) {
    	  
    	int xFondo = x;
    	int yFondo = y;
        int length1 = (getReportWidth() - 4) / 2;
        if(((ReportCommentaryDriveForm)form).getCommentaryDriveKind().equals("datatype.scheduledactivitytype.commentarydriverisk")) {
            reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,DarkBlue, null,
                    new Rectangle2D.Float(xFondo, yFondo, length1, 15), false, true));
            reportHeader.addElement(LabelElementFactory.createLabelElement("true",new Rectangle2D.Float(xFondo, yFondo, length1, 15),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT," "+ messages.getText("query.activity.training.from")+": "+Util.formatDate(this.initialDate)));
            xFondo = xFondo + length1 + 4;
            reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,DarkBlue, null,
                    new Rectangle2D.Float(xFondo, yFondo, length1, 15), false, true));
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(xFondo, yFondo, length1, 15),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT," "+ messages.getText("query.activity.training.to")+": "+Util.formatDate(this.lastDate) ));
        }else {
        	reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,DarkBlue, null,
                    new Rectangle2D.Float(xFondo, yFondo, length1, 15), false, true));
            reportHeader.addElement(LabelElementFactory.createLabelElement("true",new Rectangle2D.Float(xFondo, yFondo, length1, 15),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT," "+messages.getText("generic.activity.form.commentarydrive.commentarydrivetype")+": "+messages.getText(this.commentaryDriveType)));
        	xFondo = xFondo + length1 + 4;
        	reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,DarkBlue, null,
                    new Rectangle2D.Float(xFondo, yFondo, (length1 - 4) / 2, 15), false, true));
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(xFondo, yFondo, (length1 - 4) / 2, 15),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT," "+ messages.getText("query.activity.training.from")+": "+Util.formatDate(this.initialDate) ));
        	xFondo = xFondo + length1 / 2 + 2 ;
        	reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,DarkBlue, null,
                    new Rectangle2D.Float(xFondo, yFondo, (length1 - 4) / 2, 15), false, true));
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(xFondo, yFondo, (length1 - 4) / 2, 15),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT," "+messages.getText("query.activity.training.to")+": "+Util.formatDate(this.lastDate) ));
        }
    	
        y += 20;        
        return y;
    }
         
    /*
     * Add a Title in the Header.
     */
    private int addHeaderTitle(ReportHeader reportHeader, int x, int y) {
        String title = (((ReportCommentaryDriveForm)form).getCommentaryDriveKind().equals("datatype.scheduledactivitytype.commentarydriverisk")) ? "query.activity.commentarydriverisk.result" : "query.activity.commentarydrive.result";
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x+70, y, 300, 15),
        		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText(title))); 
        y += 40;
        return y;
    }
    
    
    /*
     * Add Assigned Vehicle Information
     */
    private int addDivOrg(ReportHeader reportHeader, int x, int y) {   
    	String driverStateMessage;
        int length1 = (getReportWidth() - 4) / 2;
        int xLength = 10 + length1 + 4;
    	reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(
				null,DarkBlue, null, new Rectangle2D.Float(10, y, length1, 15), false, true));
		reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(
				null,DarkBlue, null, new Rectangle2D.Float(xLength, y, length1, 15), false, true));
				
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, y, length1, 15),
         		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT," " + messages.getText(divGeoLabels[0]) +": " + messages.getText(this.divGeoValues[0]))); // Region
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(xLength, y, length1, 15),
         		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT," " + messages.getText(divGeoLabels[1]) +": " + messages.getText(this.divGeoValues[1]))); // Country
        y += 20;        
    	reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(
				null,DarkBlue, null, new Rectangle2D.Float(10, y, length1, 15), false, true));
		reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(
				null,DarkBlue, null, new Rectangle2D.Float(xLength, y, length1, 15), false, true));
		
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, y, length1, 15),
        		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT," " + messages.getText(divOrgLabels[1][1]) + ": " + messages.getText(divOrgValues[1][1]))); // Division
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(xLength, y, length1, 15),
        		Color.white,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT," " + messages.getText(divOrgLabels[1][2]) +": "+ messages.getText(divOrgValues[1][2]))); // Subdivision

		
        y += 20;        
    	reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(
				null,DarkBlue, null, new Rectangle2D.Float(10, y, length1, 15), false, true));
		reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(
				null,DarkBlue, null, new Rectangle2D.Float(xLength, y, length1, 15), false, true));
		
	    reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, y, length1, 15),
	         		Color.white,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT," " +messages.getText(divOrgLabels[2][1]) +": " + messages.getText(divOrgValues[2][1])));  // Unit  
	
	    switch (this.driverState){
	    	case DriverData.ALL : driverStateMessage = messages.getText("generic.messages.all.pl"); break;
	    	case DriverData.ACTIVE : driverStateMessage = messages.getText("generic.messages.active"); break;
	    	case DriverData.EXCLUDED : driverStateMessage = messages.getText("generic.messages.excluded"); break;
		    default: driverStateMessage = messages.getText("generic.messages.all.pl"); break;
	    }
	    
	    reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(xLength, y, length1, 15),
         		Color.white,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT," " +messages.getText("query.activityexpiry.driverstate") + 
         		": " + driverStateMessage ));  // Driver State  

        y += 20;
	    return y;
    }
    

    
    private int addResult(ReportHeader reportHeader, int x, int y) {
    	int y2 = y;
    	reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(
				null,ReportFactory.DCGray3, null, new Rectangle2D.Float(x, y2, getReportWidth(), 25), false, true));

    	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y, getReportWidth(), 20),
            		Color.black,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT," " + messages.getText("query.activity.commentarydrive.queryresult"))); 
       	y += 30;
       	
    	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y, 100, 15),Color.black,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.lastnamedriver"))); 
        x += 100;
    	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y, 80, 15),Color.black,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.firstnamedriver")));
        x += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y, 80, 15),Color.black,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(divGeoLabels[1])));
        x += 80;
        if(!Util.empty(principalLabels[0])) {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y, 100, 15),Color.black,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(principalLabels[0])));
            x += 100;
        }
        if(principalLabels.length > 1 && !Util.empty(principalLabels[1])) {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y, 100, 15),Color.black,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(principalLabels[1])));
            x += 100;
        }
        if(principalLabels.length > 2 && !Util.empty(principalLabels[2])) {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y, 100, 15),Color.black,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(principalLabels[2])));
            x += 100;
        }
        if(principalLabels.length > 3 && !Util.empty(principalLabels[3])) {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y, 100, 15),Color.black,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(principalLabels[3])));
            x += 100;
        }
        if(corporationId == CorporationData.MONSANTO) {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y, 100, 15),Color.black,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.vehicleallowed")));
            x += 100;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y, 120, 15),Color.black,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.licenseobs")));
            x += 120;
        }
    	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, y, 60, 15),Color.black,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.activity.commentarydrive.amountonly")));
    	
    	
    	return y; 
    	
    }
    
    protected ReportHeader createReportHeader() {        
        
    	ReportHeader reportHeader = new ReportHeader();        
    	int y1 = 0; //(Coordenadas para la primer columna)
        int x1 = 10;       
        
        if(!Util.empty(reportType) && !reportType.equals("HTML")){        	
        	// Si el reporte NO es HTML pongo titulo
        	// LOGO CEPA:
        	reportHeader.addElement(org.jfree.report.elementfactory.ImageFieldElementFactory.createImageDataRowElement("logoCepa", new Rectangle2D.Float(x1, y1, 50, 45), "logoCepa"));
        	if (corporationId == CorporationData.MONSANTO){
        		reportHeader.addElement(org.jfree.report.elementfactory.ImageFieldElementFactory.createImageDataRowElement("logoMonsanto", new Rectangle2D.Float(getReportWidth()-100, y1, 100, 45), "logoMonsanto"));
        	}
            y1 += 10;
            y1 = addHeaderTitle(reportHeader, x1, y1);
        }
    	 
        y1 = addActivityData(reportHeader,10, y1);
        y1 = addDivOrg(reportHeader, 10,y1);  
        y1 += 20;
        y1 = addResult(reportHeader, 10, y1);
        return reportHeader;
    }
   
    
    protected ReportFooter createReportFooter() {
    	ReportFooter reportFooter = super.createReportFooter();
    	
        reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, DCGray1, null,
                new Rectangle2D.Float(10, 10, getReportWidth(), 25), false, true));
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 15, 150, 20),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT," " + messages.getText("query.driver.driverdata.total")));
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement("total",new Rectangle2D.Float(getReportWidth()-100, 15, 100, 20),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,"", "#####","total"));
       
        if(((ReportCommentaryDriveForm)form).getOutput().equals("HTML")) {
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
        TextFormatExpression textExp2;
        CreateHyperLinksFunction link2;
        
        final ItemCountFunction countFunction = new ItemCountFunction();
        countFunction.setName("countExpression");
        countFunction.setGroup("dummy");
        functions.add(countFunction);
        
        final ElementColorFunction color = new ElementColorFunction();
        color.setName("changeColor");
        color.setElement("Variance Field");
        color.setField("color");
        color.setColorTrue(Color.WHITE);
        color.setColorFalse(Color.LIGHT_GRAY);
        functions.add(color);
        
        TextFormatExpression textExp = new TextFormatExpression();
        textExp.setName("URLCreateExpression");
        textExp.setPattern("./layout.jsp?refer=/driver/DriverView.jsp?id={0}");
        textExp.setField(0,"id");
        functions.add(textExp);

        CreateHyperLinksFunction link = new CreateHyperLinksFunction();
        link.setName("AddHRefs");
        link.setField("URLCreateExpression");
        link.setElement("URL-Field");
        functions.add(link);
        
        
        if(((ReportCommentaryDriveForm)form).getOutput().equals("HTML")) {
            for(int i = 1; i <= pages; i++) {
                if(i != pageNumber) {
                    textExp2 = new TextFormatExpression();
                    textExp2.setName("URLCreateExpression"+i);
                                      
                     textExp2.setPattern("./layout.jsp?refer=/activity/schedule/ReportCommentaryDriveResult.jsp?page="+i+((ReportCommentaryDriveForm)form).getLink());
                    
                    functions.add(textExp2);
            
                    link2 = new CreateHyperLinksFunction();
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
