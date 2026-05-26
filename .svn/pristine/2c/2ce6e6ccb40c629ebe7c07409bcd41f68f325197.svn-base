
package presentation.report.event;
import java.awt.Color;
import java.awt.geom.Rectangle2D;

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
import org.jfree.report.elementfactory.TextFieldElementFactory;
import org.jfree.report.function.CreateHyperLinksFunction;
import org.jfree.report.function.ElementColorFunction;
import org.jfree.report.function.ExpressionCollection;
import org.jfree.report.function.ItemCountFunction;
import org.jfree.report.function.TextFormatExpression;
import org.jfree.report.modules.misc.beanshell.BSHExpression;

import presentation.report.ReportFactory;
import presentation.translator.web.event.EventReportForm;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.administration.corporation.tables.CorporationData;
import communication.language.Text;

public class DetailedEventReportFactory extends ReportFactory {

    protected Text messages;
    private int pages;
    private int pageNumber;
    private SystemAccess sys;
    private int[][] allDivOrgs;
    private int last;
    private int corporationId;

    int width = 0;
    public DetailedEventReportFactory(String name, TableModel model, ActionForm form, 
    		SystemAccess sys, Text messages,int pages,int pageNumber) {
        super(name, model, form);
        this.messages = messages;
        count = 2;
        this.pages = pages;
        this.pageNumber = pageNumber;
        this.sys = sys;
        corporationId = sys.getUserSessionData().getFilter().getCorporationId().intValue();
        allDivOrgs = sys.getUserSessionData().getFilter().getAllDivOrgs();
        parseDivisionValues(sys,messages);
        calculateWidth();
    }

    public DetailedEventReportFactory(String name, TableModel model, ActionForm form, 
    		SystemAccess sys, Text messages, int count,int pages,int pageNumber) {
        super(name, model, form);
        this.messages = messages;
        this.count = count;
        this.pages = pages;
        this.pageNumber = pageNumber;
        this.sys = sys;
        corporationId = sys.getUserSessionData().getFilter().getCorporationId().intValue();
        allDivOrgs = sys.getUserSessionData().getFilter().getAllDivOrgs();
        parseDivisionValues(sys,messages);
        calculateWidth();
    }

    public void calculateWidth() {
        int length = allDivOrgs.length;
        if(corporationId == CorporationData.MONSANTO) {
            length = 5;
        }
    	width = 6210 + length * 80;
        if(sys.isCorporation(CorporationData.BAYER_MX)) {
        	width += 90;
        }     
        if(sys.getCorporationId() == CorporationData.MONSANTO) {
        	width += 750;
        }
         if(sys.isCorporation(CorporationData.JJ)) {
        	 width += 750;
         }
         // Solo para FOSA: workinghours
         if(sys.isCorporation(CorporationData.FORESTALORIENTAL)) {
        	 width += 140;
         }
         // Solo para FOSA: Gravedad, Severity
         if (sys.isCorporation(new int[] {CorporationData.FORESTALORIENTAL,CorporationData.MONTES_DE_PLATA,CorporationData.WEYERHAEUSER,CorporationData.SYNGENTA})){
        	 width += 100;
         }
         
         if (corporationId == CorporationData.CIMSA){
        	 width += 400;
         }
         if(corporationId == CorporationData.MONSANTO 
        		 || corporationId == CorporationData.LILLY 
                 || corporationId == CorporationData.JJ
                 || corporationId == CorporationData.DPA
                 || corporationId == CorporationData.MONTES_DE_PLATA
                 || corporationId == CorporationData.SYNGENTA
                 || corporationId == CorporationData.FORESTALORIENTAL) {
        	 width += 80;
         }
         if(sys.isCorporation(new int[]{CorporationData.DOW,CorporationData.MONTES_DE_PLATA,CorporationData.ALLERGAN,CorporationData.ASTRAZENECA})) {
        	 width += 580;
         }
         if(sys.isBrasilCorporation() || sys.isCorporation(CorporationData.JJ) || sys.isForestalUnified()) {
        	 width += 240;
         }
         if(sys.isCorporation(CorporationData.SYNGENTA_BR)) {
        	 width += 760;
         }
         System.out.println("width  ---> "+width);
    }
    /**
     * 
     */
    protected int getWidth() {
    	return width+80;
    	
    }

    //  it is declared but it is not used 
    protected int getReportWidth() {
    	return width;
    }
    
    protected ItemBand createItemBand() {
        ItemBand items = super.createItemBand();
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,new Rectangle2D.Float(10, 0, getReportWidth(), 25), false, true));
        int w = 10;
        items.addElement(DateFieldElementFactory.createDateElement("URL-Field",new Rectangle2D.Float(w, 0,50, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-",DATE_FORMAT,"eventdate"));
        w += 50;
        if(sys.isCorporation(CorporationData.BAYER_MX)) {
       		items.addElement(TextFieldElementFactory.createStringElement("URL-Field-2",new Rectangle2D.Float(w, 0, 90, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","corporationId"));
       		w += 90;
        }     
        items.addElement(TextFieldElementFactory.createStringElement("URL-Field-2",new Rectangle2D.Float(w, 0, 100, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","driverFirstName"));
        w += 100;
        items.addElement(TextFieldElementFactory.createStringElement("URL-Field-2",new Rectangle2D.Float(w, 0, 100, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","driverLastName"));
        w += 100;
        
        
        if(sys.isCorporation(new int[]{CorporationData.JJ,CorporationData.FORESTALORIENTAL})) {
       		items.addElement(TextFieldElementFactory.createStringElement("corporationId",new Rectangle2D.Float(w, 0, 90, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","corporationId"));
       		w += 90;
        }     
        
        items.addElement(DateFieldElementFactory.createDateElement("birthdate",new Rectangle2D.Float(w, 0, 50, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-",DATE_FORMAT,"birthdate"));
        w += 50;
        items.addElement(DateFieldElementFactory.createDateElement("inclusiondate",new Rectangle2D.Float(w, 0, 70, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-",DATE_FORMAT,"inclusiondate"));
        w += 70;
        items.addElement(TextFieldElementFactory.createStringElement("licensecategory",new Rectangle2D.Float(w, 0, 70, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","licensecategory"));
        w += 70;
        items.addElement(DateFieldElementFactory.createDateElement("licenseexpiry",new Rectangle2D.Float(w, 0, 50, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-",DATE_FORMAT,"licenseexpiry"));
        w += 50;
        items.addElement(TextFieldElementFactory.createStringElement("country",new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","country"));
        w += 80;
        int length = allDivOrgs.length;
        if(corporationId == CorporationData.MONSANTO) {
            length = 5;
        }
        
        for(int i = 1; i <= length; i++) {
            items.addElement(TextFieldElementFactory.createStringElement("divOrg"+i,new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","divOrg"+i));
            w += 80;
        } 
        
        if(sys.getCorporationId() == CorporationData.MONSANTO) {
        	items.addElement(TextFieldElementFactory.createStringElement("assignedVehicle",new Rectangle2D.Float(w, 0, 80, 25),
        		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","assignedVehicle"));
        	w += 80;
        }
        
        
        items.addElement(TextFieldElementFactory.createStringElement("plate",new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","plate"));
        w += 80;
        
        if(corporationId == CorporationData.MONSANTO) {
        	 items.addElement(TextFieldElementFactory.createStringElement("vehicleType",new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","vehicleType"));
             w += 80;
        }
        
        items.addElement(TextFieldElementFactory.createStringElement("trade",new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","trade"));
        w += 80;
        items.addElement(TextFieldElementFactory.createStringElement("model",new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","model"));
        w += 80;
        items.addElement(TextFieldElementFactory.createStringElement("year",new Rectangle2D.Float(w, 0, 50, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","year"));
        w += 50;
        items.addElement(TextFieldElementFactory.createStringElement("companycar",new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","companycar"));
        w += 80;
        items.addElement(TextFieldElementFactory.createStringElement("type",new Rectangle2D.Float(w, 0, 50, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","type"));
        w += 50;
        /*items.addElement(TextFieldElementFactory.createStringElement("finetype",new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","finetype"));
         w += 80;*/
         items.addElement(TextFieldElementFactory.createStringElement("hour",new Rectangle2D.Float(w, 0, 50, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","hour"));
         w += 50;
         if(sys.isCorporation(CorporationData.SYNGENTA_BR)) {
             items.addElement(TextFieldElementFactory.createStringElement("consequences",new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","consequences"));
             w += 80;
         }
         if(sys.isCorporation(CorporationData.JJ)) {
             items.addElement(DateFieldElementFactory.createDateElement("expiry",new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-",DATE_FORMAT,"expiry"));
             w += 80;
         }
         if (corporationId == CorporationData.CIMSA){
        	 // Real Cost:
             items.addElement(TextFieldElementFactory.createStringElement("whoDrives",new Rectangle2D.Float(w, 0, 80, 25),
            		 Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","whoDrives"));
        	 w += 80;
         }
         items.addElement(TextFieldElementFactory.createStringElement("working",new Rectangle2D.Float(w, 0, 50, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","working"));
         w += 50;
         
         // Solo para FOSA: workinghours
         if(sys.isCorporation(CorporationData.FORESTALORIENTAL)) {
        	 items.addElement(TextFieldElementFactory.createStringElement("workinghours",new Rectangle2D.Float(w, 0, 50, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","workinghours"));
        	 w += 50;
         }
         
         
         
         items.addElement(TextFieldElementFactory.createStringElement("damage",new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","damage"));
         w += 80;
         if(sys.isCorporation(CorporationData.MONTES_DE_PLATA)) {
             items.addElement(TextFieldElementFactory.createStringElement("doubleCabin",new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","doubleCabin"));
             w += 80;
         }
         items.addElement(TextFieldElementFactory.createStringElement("zone",new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","zone"));
         w += 80;
         items.addElement(TextFieldElementFactory.createStringElement("driverstate",new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","driverstate"));
         w += 80;
         items.addElement(NumberFieldElementFactory.createNumberElement("dayslost",new Rectangle2D.Float(w, 0, 100, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals,"dayslost"));
         w += 100;
         items.addElement(NumberFieldElementFactory.createNumberElement("safefunccount",new Rectangle2D.Float(w, 0, 100, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-",formatNoDecimals,"safefunccount"));
         w += 100;
         items.addElement(NumberFieldElementFactory.createNumberElement("injuredfunccount",new Rectangle2D.Float(w, 0, 100, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-",formatNoDecimals,"injuredfunccount"));
         w += 100;
         items.addElement(NumberFieldElementFactory.createNumberElement("fatalitiesfunccount",new Rectangle2D.Float(w, 0, 100, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-",formatNoDecimals,"fatalitiesfunccount"));
         w += 100;
         items.addElement(NumberFieldElementFactory.createNumberElement("otherpartydayslost",new Rectangle2D.Float(w, 0, 100, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-",formatNoDecimals,"otherpartydayslost"));
         w += 100;
         items.addElement(NumberFieldElementFactory.createNumberElement("safeothercount",new Rectangle2D.Float(w, 0, 100, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-",formatNoDecimals,"safeothercount"));
         w += 100;
         items.addElement(NumberFieldElementFactory.createNumberElement("injuredothercount",new Rectangle2D.Float(w, 0, 100, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-",formatNoDecimals,"injuredothercount"));
         w += 100;
         items.addElement(NumberFieldElementFactory.createNumberElement("fatalitiesothercount",new Rectangle2D.Float(w, 0, 100, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-",formatNoDecimals,"fatalitiesothercount"));
         w += 100;
         items.addElement(TextFieldElementFactory.createStringElement("policereport",new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","policereport"));
         w += 80;
         items.addElement(TextFieldElementFactory.createStringElement("collisionclasification",new Rectangle2D.Float(w, 0, 100, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","collisionclasification"));
         w += 100;
         items.addElement(TextFieldElementFactory.createStringElement("clasification",new Rectangle2D.Float(w, 0, 100, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","clasification"));
         w += 100;
         items.addElement(TextFieldElementFactory.createStringElement("clasification1",new Rectangle2D.Float(w, 0, 100, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","clasification1"));
         w += 100;
         if(!sys.isCorporation(CorporationData.SYNGENTA_BR)) {
        	 items.addElement(TextFieldElementFactory.createStringElement("clasification2",new Rectangle2D.Float(w, 0, 100, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","clasification2"));
        	 w += 100;
         }
         items.addElement(TextFieldElementFactory.createStringElement("clasification3",new Rectangle2D.Float(w, 0, 100, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","clasification3"));
         w += 100;
         items.addElement(TextFieldElementFactory.createStringElement("clasification4",new Rectangle2D.Float(w, 0, 100, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","clasification4"));
         w += 100;
         items.addElement(TextFieldElementFactory.createStringElement("clasificationText",new Rectangle2D.Float(w, 0, 100, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","clasificationText"));
         w += 100;
         items.addElement(TextFieldElementFactory.createStringElement("clasificationcepa1",new Rectangle2D.Float(w, 0, 100, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","clasificationcepa1"));
         w += 100;
         items.addElement(TextFieldElementFactory.createStringElement("clasificationcepa2",new Rectangle2D.Float(w, 0, 100, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","clasificationcepa2"));
         w += 100;
         items.addElement(TextFieldElementFactory.createStringElement("clasificationcepa3",new Rectangle2D.Float(w, 0, 100, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","clasificationcepa3"));
         w += 100;
         items.addElement(TextFieldElementFactory.createStringElement("clasificationcepa4",new Rectangle2D.Float(w, 0, 100, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","clasificationcepa4"));
         w += 100;
         items.addElement(TextFieldElementFactory.createStringElement("clasificationcepaText",new Rectangle2D.Float(w, 0, 100, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","clasificationcepaText"));
         w += 100;
         
         if(sys.isCorporation(CorporationData.SYNGENTA_BR)) {
             items.addElement(TextFieldElementFactory.createStringElement("severity2",new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","severity2"));
             w += 80;
             items.addElement(TextFieldElementFactory.createStringElement("severityJustification",new Rectangle2D.Float(w, 0, 100, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","severityJustification"));
             w += 100;
             items.addElement(TextFieldElementFactory.createStringElement("evitability2",new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","evitability2"));
             w += 80;
             items.addElement(TextFieldElementFactory.createStringElement("evitabilityJustification",new Rectangle2D.Float(w, 0, 100, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","evitabilityJustification"));
             w += 100;
             items.addElement(TextFieldElementFactory.createStringElement("responsability",new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","responsability"));
             w += 80;
         }
         
         // Solo para FOSA: Gravedad, Severity
         if (sys.isCorporation(new int[] {CorporationData.FORESTALORIENTAL,CorporationData.MONTES_DE_PLATA,CorporationData.WEYERHAEUSER,CorporationData.SYNGENTA})){
        	 items.addElement(TextFieldElementFactory.createStringElement("severity",new Rectangle2D.Float(w, 0, 50, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","severity"));
        	 w += 100;
         }
         
         items.addElement(TextFieldElementFactory.createStringElement("handle",new Rectangle2D.Float(w, 0, 100, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","handle"));
         w += 100;
         items.addElement(TextFieldElementFactory.createStringElement("hitrun",new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","hitrun"));
         w += 80;
         items.addElement(TextFieldElementFactory.createStringElement("principalfactor",new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","principalfactor"));
         w += 80;
         //items.addElement(TextFieldElementFactory.createStringElement("otherfactors",new Rectangle2D.Float(w, 0, 120, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","otherfactors"));
         //w += 120;
         items.addElement(TextFieldElementFactory.createStringElement("collisionangle",new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","collisionangle"));
         w += 80;
         items.addElement(NumberFieldElementFactory.createNumberElement("maxspeed",new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-",formatNoDecimals,"maxspeed"));
         w += 80;
         items.addElement(NumberFieldElementFactory.createNumberElement("collisionspeed",new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-",formatNoDecimals,"collisionspeed"));
         w += 80;
         items.addElement(NumberFieldElementFactory.createNumberElement("getVehicleSpeed",new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-",formatNoDecimals,"getVehicleSpeed"));
         w += 80;
         if(!sys.isCorporation(CorporationData.SYNGENTA_BR)) {
	         items.addElement(NumberFieldElementFactory.createNumberElement("reportedSpeed",new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-",formatNoDecimals,"reportedSpeed"));
	         w += 80;
	         items.addElement(TextFieldElementFactory.createStringElement("speedunit",new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","speedunit"));
	         w += 80;
         }
         items.addElement(TextFieldElementFactory.createStringElement("selfbelt",new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","selfbelt"));
         w += 80;
         if(!sys.isCorporation(CorporationData.SYNGENTA_BR)) {
        	 items.addElement(TextFieldElementFactory.createStringElement("alcohol",new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","alcohol"));
        	 w += 80;
         }
         items.addElement(NumberFieldElementFactory.createNumberElement("othercompanyvehicles",new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-",formatNoDecimals,"othercompanyvehicles"));
         w += 80;
         items.addElement(TextFieldElementFactory.createStringElement("cellular",new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","cellular"));
         w += 80;
         items.addElement(TextFieldElementFactory.createStringElement("way",new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","way"));
         w += 80;
         items.addElement(TextFieldElementFactory.createStringElement("direction",new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","direction"));
         w += 80;
         items.addElement(TextFieldElementFactory.createStringElement("control",new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","control"));
         w += 80;
         items.addElement(TextFieldElementFactory.createStringElement("stateevent",new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","stateevent"));
         w += 80;
         items.addElement(TextFieldElementFactory.createStringElement("city",new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","city"));
         w += 80;
         items.addElement(TextFieldElementFactory.createStringElement("condition",new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","condition"));
         w += 80;
         items.addElement(TextFieldElementFactory.createStringElement("climate",new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","climate"));
         w += 80;
         items.addElement(TextFieldElementFactory.createStringElement("lighting",new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","lighting"));
         w += 80;
         items.addElement(NumberFieldElementFactory.createNumberElement("cost",new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatNoDecimals, "cost"));
         if(sys.isCorporation(CorporationData.MONSANTO)) {
             w += 80;
             items.addElement(TextFieldElementFactory.createStringElement("doubleCabin",new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","doubleCabin"));
         }
         if (corporationId == CorporationData.CIMSA){
        	 // Real Cost:
        	 w += 80;
             items.addElement(TextFieldElementFactory.createStringElement("realCost",new Rectangle2D.Float(w, 0, 80, 25),
            		 Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","realCost"));
             
             // companyAmount
             w += 80;
             items.addElement(NumberFieldElementFactory.createNumberElement("companyAmount",
            		 new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,
            		 STYLE_SIMPLE_FONT,"",formatNoDecimals, "companyAmount"));
             
             // driverAmount
             w += 80;
             items.addElement(NumberFieldElementFactory.createNumberElement("driverAmount",
            		 new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,
            		 STYLE_SIMPLE_FONT,"",formatNoDecimals, "driverAmount"));
             
         	 // thirdAmount
             w += 80;
             items.addElement(NumberFieldElementFactory.createNumberElement("thirdAmount",
            		 new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,
            		 STYLE_SIMPLE_FONT,"",formatNoDecimals, "thirdAmount"));
         }
         
         w += 80;
         items.addElement(TextFieldElementFactory.createStringElement("infringementNotice",new Rectangle2D.Float(w, 0,80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","infringementNotice"));
         w += 80;
         items.addElement(TextFieldElementFactory.createStringElement("points",new Rectangle2D.Float(w, 0,80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","points"));
         w += 80;
         items.addElement(TextFieldElementFactory.createStringElement("article",new Rectangle2D.Float(w, 0,100, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","article"));
         w += 100;
         items.addElement(TextFieldElementFactory.createStringElement("story",new Rectangle2D.Float(w, 0,500, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","story"));
         w += 500;
         items.addElement(TextFieldElementFactory.createStringElement("recomendation",new Rectangle2D.Float(w, 0,335, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","recomendation"));
         w += 335;
         items.addElement(TextFieldElementFactory.createStringElement("supervisionObservations",new Rectangle2D.Float(w, 0,335, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","supervisionObservations"));
         w += 335;
         if(corporationId == CorporationData.MONSANTO 
        		 || corporationId == CorporationData.ASTRAZENECA 
        		 || corporationId == CorporationData.LILLY 
                 || corporationId == CorporationData.JJ
                 || corporationId == CorporationData.DPA
                 || corporationId == CorporationData.DOW
                 || corporationId == CorporationData.SYNGENTA
                 || corporationId == CorporationData.SYNGENTA_BR
                 || corporationId == CorporationData.FORESTALORIENTAL
                 || corporationId == CorporationData.MONTES_DE_PLATA
        		 || corporationId == CorporationData.ALLERGAN) {
             items.addElement(TextFieldElementFactory.createStringElement("evitability",new Rectangle2D.Float(w, 0,80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","evitability"));
             w += 80;
         }
         items.addElement(TextFieldElementFactory.createStringElement("id",new Rectangle2D.Float(w, 0, 80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","id"));
         w += 80;
         if(sys.isBrasilCorporation() || sys.isCorporation(CorporationData.JJ) || sys.isForestalUnified()) {
             items.addElement(TextFieldElementFactory.createStringElement("supervisionClasification",new Rectangle2D.Float(w, 0,80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","supervisionClasification"));
             w += 120;
             items.addElement(TextFieldElementFactory.createStringElement("supervisionCollision",new Rectangle2D.Float(w, 0,80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","supervisionCollision"));        	
             w += 120;
         }

         if(sys.isCorporation(CorporationData.JJ)) {
             items.addElement(TextFieldElementFactory.createStringElement("recordable",new Rectangle2D.Float(w, 0,80, 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","recordable"));
             w += 80;
         }
         System.out.println("w --------> "+w+" --> "+length);
        return items;
    }
    
    protected ReportHeader createReportHeader() {
        EventReportForm reportForm = (EventReportForm)form;
        ReportHeader reportHeader = new ReportHeader();
        int height = writeFilter(reportForm, reportHeader);
                
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FirstLevelColor, null,
                new Rectangle2D.Float(10, height, getReportWidth(), 25), false, true));
        int w = 10;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 50, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.observation.date")));
        w += 50;
        if(sys.isCorporation(CorporationData.BAYER_MX)) {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 90, 25),
            		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.corporationid")));
       		w += 90;
        }  
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.firstname")));
        w += 100; 
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.lastname")));
        w += 100; 
        
        
        if(sys.isCorporation(new int[]{CorporationData.JJ,CorporationData.FORESTALORIENTAL})) {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 90, 25),
            		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.corporationid")));
       		w += 90;
        }  
        
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 50, 25),
        		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.driver.birthdate")));
        w += 50;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 70, 25),
        		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.driver.startdate")));
        w += 70;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 70, 25),
        		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.driver.licensecategory")));
        w += 70;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 50, 25),
        		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.driver.licenseexpiry")));
        w += 50;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,divGeoLabels[1]));
        w += 80;
        int length = allDivOrgs.length;
        
        if(corporationId == CorporationData.MONSANTO) {
            length = 5;
        }
        for(int i = 1; i <= length; i++) {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,divOrgLabels[allDivOrgs[i-1][0]-1][allDivOrgs[i-1][1]-1]));
            w += 80;
        }
        
        if(sys.getCorporationId() == CorporationData.MONSANTO) {
        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
        		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("corporation.monsanto.assignedvehicles")));
        	w += 80;
        }
        
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
        		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.plate")));
        w += 80;
        
        if(sys.isCorporation(CorporationData.MONSANTO)) {
        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
        		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.vehicleType")));
        	w += 80;
        }
        
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
        		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.trade")));
        w += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
        		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.model")));
        w += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 50, 25),
        		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.finedata.year")));
        w += 50;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
        		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.vehicle.companycar")));
        w += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 50, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.type")));
        w += 50;
       /* reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 18),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.type")));
        w += 80;*/
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 50, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.hour")));
        w += 50;
        if(sys.isCorporation(CorporationData.SYNGENTA_BR)) {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
            		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.charged")));
            w += 80;
        }
        if(sys.isCorporation(CorporationData.JJ)) {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
            		Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.expiry")));
            w += 80;
        }
        if (corporationId == CorporationData.CIMSA){
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.whodrives")));
       	 	w += 80;
        }
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 50, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.working")));
        w += 50;
        
        // Solo para FOSA: workingHours
        if(sys.isCorporation(CorporationData.FORESTALORIENTAL)) {
        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 50, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.workinghours")));
        	w += 50;
        }
        if (sys.isCorporation(CorporationData.BAYER)){
        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("datatype.byer.path")));
        }else{
        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("datatype.consequence.damage")));
        }
        w += 80;
        if (sys.isCorporation(CorporationData.MONTES_DE_PLATA)){
        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("datatype.event.environmentdamage")));
            w += 80;
        }        
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.zone")));
        w += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.consequences")+" - "+messages.getText("generic.event.eventdata.driver")));
        w += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("event.eventdata.lostdays")+" - "+messages.getText("generic.event.eventdata.driver")));
        w += 100;
        
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("event.eventdata.functionaries.state")+"  -  "+messages.getText("datatype.consequence.safe")));
        w += 100;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("event.eventdata.functionaries.state")+"  -  "+messages.getText("datatype.consequence.injured")));
        w += 100;  
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("event.eventdata.functionaries.state")+"  -  "+messages.getText("datatype.consequence.fatalities")));
        w += 100;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("event.eventdata.functionaries.state")+"  -  "+messages.getText("event.eventdata.lostdays")));
        w += 100;
        
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("event.eventdata.otherparty.state")+" - "+messages.getText("datatype.consequence.safe")));
        w += 100;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("event.eventdata.otherparty.state")+" - "+messages.getText("datatype.consequence.injured")));
        w += 100;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("event.eventdata.otherparty.state")+" - "+messages.getText("datatype.consequence.fatalities")));
        w += 100;

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.policereport")));
        w += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.colisiontype")));
        w += 100;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.clasification")));
        w += 100;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("general.eventdata.newclasification1")));
        w += 100;
        if(!sys.isCorporation(CorporationData.SYNGENTA_BR)) {
        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("general.eventdata.newclasification2")));
        	w += 100;
        }
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("general.eventdata.newclasification3")));
        w += 100;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("general.eventdata.newclasification4")));
        w += 100;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.clasification")+" Desc."));
        w += 100;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("general.eventdata.newclasification1")+"-CEPA"));
        w += 100;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("general.eventdata.newclasification2")+"-CEPA"));
        w += 100;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("general.eventdata.newclasification3")+"-CEPA"));
        w += 100;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("general.eventdata.newclasification4")+"-CEPA"));
        w += 100;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.clasification")+" Desc."+"-CEPA"));
        w += 100;
        if(sys.isCorporation(CorporationData.SYNGENTA_BR)) {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("event.syngentaclasification.severity")));
            w += 80;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 25),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("event.syngentaclasification.severityjustification")));
            w += 100;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("event.syngentaclasification.evitability")));
            w += 80;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 25),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("event.syngentaclasification.evitabilityjustification")));
            w += 100;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("event.syngentaclasification.responsability")));
            w += 80;
        	
        }
        if (sys.isCorporation(new int[] {CorporationData.FORESTALORIENTAL,CorporationData.MONTES_DE_PLATA,CorporationData.WEYERHAEUSER,CorporationData.SYNGENTA})){
        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 25),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.eventdata.severityindex")));
            w += 100;
        	
        }
        
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.handle")));
        w += 100;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.hitrun")));
        w += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.principalfactor")));
        w += 80;
        //reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 120, 18),
        //        Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.factors")));
        //w += 120;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.collisionangle")));
        w += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.activity.form.hrd.velocity")+" - "+messages.getText("generic.event.eventdata.maxspeed")));
        w += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.activity.form.hrd.velocity")+" - "+messages.getText("generic.event.eventdata.collisionspeed")));
        w += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.activity.form.hrd.velocity")+" - "+messages.getText("generic.event.eventdata.vehiclespeed")));
        w += 80;
        if(!sys.isCorporation(CorporationData.SYNGENTA_BR)) {
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
	                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.activity.form.hrd.velocity")+" - "+messages.getText("generic.event.eventdata.reportedspeed")));
	        w += 80;
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
	                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.activity.form.hrd.velocity")+" - "+messages.getText("generic.event.eventdata.speedunit")));
	        w += 80;
        }
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.selfbelt")));
        w += 80;
        if(!sys.isCorporation(CorporationData.SYNGENTA_BR)) {
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
	                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.alcohollevel")));
	        w += 80;
        }
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.othervehicles")));
        w += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.cellular")));
        w += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.way")));
        w += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.circulationdirection")));
        w += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.trafficcontrol")));
        w += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("web.event.state")));
        w += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("web.driver.city")));
        w += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.conditionrout")));
        w += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.climate")));
        w += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.lighting")));
        w += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.finedata.cost")));
        w += 80;
        if(sys.isCorporation(CorporationData.MONSANTO)) {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"TFLEET"));
            w += 80;
        }
        if (corporationId == CorporationData.CIMSA){
        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.finedata.realcost")));
            w += 80;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("event.extracost.companyamount")));
            w += 80;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("event.extracost.driveramount")));
            w += 80;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("event.extracost.thirdamount")));
            w += 80;            
        }
        
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("event.eventdata.infringementnotice")));
        w += 80;                    
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.finedata.points")));
        w += 80;                    
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.finedata.article")));
        w += 100;                    
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 500, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.story")));
        w += 500;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 335, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.recomendation")));
        w += 335;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 335, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.finedata.observations")));
        w += 335;
        if(corporationId == CorporationData.MONSANTO 
        		|| corporationId == CorporationData.ASTRAZENECA
        		|| corporationId == CorporationData.LILLY
        		|| corporationId == CorporationData.JJ
                || corporationId == CorporationData.DPA
                || corporationId == CorporationData.DOW
                || corporationId == CorporationData.SYNGENTA
                || corporationId == CorporationData.SYNGENTA_BR
                || corporationId == CorporationData.FORESTALORIENTAL
                || corporationId == CorporationData.MONTES_DE_PLATA
        		|| corporationId == CorporationData.ALLERGAN)  {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 25),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.supervise.evitability")));
            w += 80;
        }
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 18),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("id")));
        w += 80;
        if(sys.isBrasilCorporation() || sys.isCorporation(CorporationData.JJ) || sys.isForestalUnified()) {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 120, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("event.messages.cepaclasification")));
            w += 120;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 120, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.eventdata.colisionclasification")));
            w += 120;        	
        }
        if(sys.isCorporation(CorporationData.JJ)) {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 18),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.event.nonrecordable")));
            w += 80;
        }
        last = w;
        
        return reportHeader;
    }

	protected int writeFilter(EventReportForm reportForm, ReportHeader reportHeader) {
		reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 25), false, true));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, getReportWidth(), 25),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("generic.events.consulting")));
        
        int height = 25;
        int maxHeight = height;
            
        height = writeParametersTitle(messages.getText("query.eventbyperiod.period"),reportHeader,height,10,150);
        height = writeParameter(messages.getText("query.eventbyperiod.from")+":",reportForm.getFrom(messages),reportHeader,height,10,50,60,100);
        height = writeParameter(messages.getText("query.eventbyperiod.to")+":",reportForm.getTo(messages),reportHeader,height,10,50,60,100);

        height = writeParametersTitle(messages.getText("generic.driver.divgeo"),reportHeader,height,10,150); 
        height = writeParameter(divGeoLabels[0]+":",divGeoValues[0],reportHeader,height,10,50,60,100);
        height = writeParameter(divGeoLabels[1]+":",divGeoValues[1],reportHeader,height,10,50,60,100);

        maxHeight=Math.max(maxHeight,height);

        height = 25;
       
        String[] divOrgs = sys.getUserSessionData().getFilter().getLevelLabels();
        for(int i = 0; i < divOrgs.length; i++) {
            if(!Util.empty(divOrgs[i])) {
                height = writeParametersTitle(messages.getText(divOrgs[i]),reportHeader,height,280,200);
                for(int j = 0; j < 3; j++) {
                    if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 1 && j== 0) && 
                            !(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 2 && j== 0)) {
                        if(!Util.empty(divOrgLabels[i][j])) {
                            height = writeParameter(divOrgLabels[i][j]+":",divOrgValues[i][j],reportHeader,height,280,70,350,130);
                        }
                    }
                }
            }
        }
       
        maxHeight=Math.max(maxHeight,height);
        return maxHeight;
	}

    

    protected ReportFooter createReportFooter() {
        ReportFooter reportFooter = new ReportFooter();
        reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 20), false, true));
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, 200, 20),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.driver.driverdata.total")));
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement(null,new Rectangle2D.Float(last, 0, 20, 20),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"", "#####","total"));
        if(((EventReportForm)form).getOutput().equals("HTML")) {
            int left = 60;
            reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(0, 40, 50, 15),
                    Color.BLUE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE_PARAM_FONT,messages.getText("generic.messages.pages")));
            for(int i = 1; i <= pages; i++) {
                int width = (i < 10) ? 15 : 20; 
                if(i != pageNumber) {
                    reportFooter.addElement(LabelElementFactory.createLabelElement("URL-Field--"+i,new Rectangle2D.Float(left, 40, width, 15),
                            Color.BLUE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE_PARAM_FONT,String.valueOf(i)));
                }else {
                    reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(left, 40, width, 15),
                            Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(i)));
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
        
        if(model.getRowCount() > 0) {
            BSHExpression bshExp = new BSHExpression();
            bshExp.setName("optionpath");
            bshExp.setExpression("getValue() {" +
                    "            String event = dataRow.get (\"typenumber\");" +
                    "            String id =  dataRow.get (\"id\");" +
                    "            if (!event.equals(\"3\"))" +
                    "              return \"./layout.jsp?refer=/event/EventView.jsp?eventId=\"+id;" +
                    "            else return \"./layout.jsp?refer=/event/FineView.jsp?fine=\"+id;" +
                    "}");
            functions.add(bshExp);
            TextFormatExpression textExp = new TextFormatExpression();
            textExp.setName("URLCreateExpression");
            textExp.setPattern("{0}");
            textExp.setField(0,"optionpath");
            functions.add(textExp);
        }
        
        CreateHyperLinksFunction link = new CreateHyperLinksFunction();
        link.setName("AddHRefs");
        link.setField("URLCreateExpression");
        link.setElement("URL-Field");
        functions.add(link);
        

        TextFormatExpression textExp2 = new TextFormatExpression();
        textExp2.setName("URLCreateExpression2");
        textExp2.setPattern("./layout.jsp?refer=/driver/DriverView.jsp?id={0}");
        textExp2.setField(0,"driverId");
        functions.add(textExp2);

        CreateHyperLinksFunction link2 = new CreateHyperLinksFunction();
        link2.setName("AddHRefs2");
        link2.setField("URLCreateExpression2");
        link2.setElement("URL-Field-2");
        functions.add(link2);
        
        if(((EventReportForm)form).getOutput().equals("HTML")) {
            for(int i = 1; i <= pages; i++) {
                if(i != pageNumber) {
                    TextFormatExpression textExp3 = new TextFormatExpression();
                    textExp3.setName("URLCreateExpressions"+i);
                    textExp3.setPattern("./layout.jsp?refer=/event/eventReportPage.jsp?page="+i+((EventReportForm)form).getLink());
                    functions.add(textExp3);
            
                    CreateHyperLinksFunction link3 = new CreateHyperLinksFunction();
                    link3.setName("AddHRefs-"+i);
                    link3.setField("URLCreateExpressions"+i);
                    link3.setElement("URL-Field--"+i);
                    functions.add(link3);
                }
            }
        }
        
        BSHExpression bshExp3 = new BSHExpression();
        bshExp3.setName("vehiclelink");
        bshExp3.setExpression("getValue() {" +
                "            Integer vehicleId = dataRow.get (\"vehicleid\");" +
                "            if (vehicleId!=null)" +
                "              return \"./layout.jsp?refer=/vehicle/VehicleView.jsp?id=\"+vehicleId.intValue();" +
                "            else return vehicleId;" +
                "}");
        functions.add(bshExp3);
        TextFormatExpression textExp4 = new TextFormatExpression();
        textExp4.setName("URLCreateExpression3");
        textExp4.setPattern("{0}");
        textExp4.setField(0,"vehiclelink");
        functions.add(textExp4);

        CreateHyperLinksFunction link4 = new CreateHyperLinksFunction();
        link4.setName("AddHRefs3");
        link4.setField("URLCreateExpression3");
        link4.setElement("URL-Field-3");
        functions.add(link4);
     
        return functions;
    }
}

