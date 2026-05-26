/**
 * Created on 10-jul-2006
 * CEPA
 * DataCenter 5
 */
package presentation.report.vehicle;

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

import presentation.translator.web.util.Util;
import presentation.translator.web.vehicle.VehicleMultiReportForm;
import presentation.translator.web.vehicle.VehicleReportForm;
import business.SystemAccess;

import communication.administration.corporation.tables.CorporationData;
import communication.language.Text;

public class VehicleReportExtendedFactory extends VehicleReportFactory {

    //private Text messages;
	private int pages ;
	private int pageNumber;
    private int last;
    private int corporationId;
    
    public VehicleReportExtendedFactory(String name, TableModel model, 
    		ActionForm form, SystemAccess sys, Text messages, int count,
    		int pages, int pageNumber) {
        super(name,model,form,sys,messages,count,pages,pageNumber);
        this.corporationId = sys.getUserSessionData().getFilter().getCorporationId().intValue();
        this.name = name;
        this.model = model;
        this.form = form;        
        this.messages = messages;
        this.count = count;//22;
        this.pages = pages;
        this.pageNumber = pageNumber;

        switch(corporationId) {
        	case CorporationData.KLABIN: 
        		last = 2930;
        		break;
        	case CorporationData.YOLI:
        		last = 3090;
        		break;
        	case CorporationData.MONSANTO: 
        		last = 3800;
        		break;
        	case CorporationData.JJ:
        		last = 3300;
        		break;
        	case CorporationData.ALLERGAN:
        		last = 1520;
        		break;
        	case CorporationData.DOW:
        		last = 1900;
        		break;
        	case CorporationData.MONTES_DE_PLATA:
        		last = 3150+200;
        		break;
        	case CorporationData.FORESTALORIENTAL:
        		last = 2950+300;
        		break;
        	default:
        		last = 2850;
        }
        if(sys.getUserSessionData().isForest()) {
            last += 700;
        }
        for(int i = 0; i <  3; i++) {
            for(int j =  principalCount-1; j >= 0; j--) {
                if(!(sys.isCorporation(CorporationData.MONSANTO) && i == 1 && j== 0) && 
                        !(sys.isCorporation(CorporationData.MONSANTO) && i == 2 && j== 0)) {
                    if(!Util.empty(labels[i* principalCount+j])) {
                        last += 80;
                    }
                }
            }
        }
    }

 
    protected int getWidth() {
        return last+82;//sume de 100 a 800
    }
    
    protected int getReportWidth() {
        return last;//sume de 100 a 800
    }

    
    protected ItemBand createItemBand() {
        ItemBand items = new ItemBand();           
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance-Field", null, null,
        		new Rectangle2D.Float(10, 0, getReportWidth(), 15), false, true));
        items.addElement(TextFieldElementFactory.createStringElement("URL-Field",new Rectangle2D.Float(10, 0, 80, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,fontDefinitionSimple,"","registrationNumber"));
        int w = 90;
        if(corporationId == CorporationData.KLABIN || corporationId == CorporationData.YOLI) {
            items.addElement(TextFieldElementFactory.createStringElement("prefix",new Rectangle2D.Float(w, 0, 80, 15),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","prefix"));
            w += 80;
        }
        if(corporationId != CorporationData.ALLERGAN) {
        	items.addElement(TextFieldElementFactory.createStringElement("country",new Rectangle2D.Float(w, 0, 80, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","country"));
        	w += 80;
        }
        int index = 1;
        for(int i = 0; i <  3; i++) {
            //for(int j =  principalCount -1; j >= 0; j--) {
        	for(int j = 0; j <= 2; j++) {
                if(!(sys.isCorporation(CorporationData.MONSANTO) && i == 1 && j== 0) && 
                        !(sys.isCorporation(CorporationData.MONSANTO) && i == 2 && j== 0)) {
                    if(!Util.empty(labels[i* 3+j])) {
                        items.addElement(TextFieldElementFactory.createStringElement("divorg"+String.valueOf(index),
                                new Rectangle2D.Float(w, 0, 80, 15),Color.BLACK,ElementAlignment.CENTER,
                                ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","divorg"+String.valueOf(i+1)+String.valueOf(j+1)));
                        w += 80;
                        index++;
                    }
                }
            }
        }
        items.addElement(TextFieldElementFactory.createStringElement("type",new Rectangle2D.Float(w, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","type"));
        w += 100;
        items.addElement(TextFieldElementFactory.createStringElement("trade",new Rectangle2D.Float(w, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","trade"));
        w += 100;
        items.addElement(TextFieldElementFactory.createStringElement("model",new Rectangle2D.Float(w, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","model"));
        w += 100;
        items.addElement(TextFieldElementFactory.createStringElement("year",new Rectangle2D.Float(w, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","year"));
        w += 100;
        if(sys.isCorporation(CorporationData.DOW)) {
	        items.addElement(TextFieldElementFactory.createStringElement("cost_center",new Rectangle2D.Float(w, 0, 100, 15),
	        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","cost_center"));
            w += 100;
        }
        if(corporationId != CorporationData.ALLERGAN) {
        	items.addElement(TextFieldElementFactory.createStringElement("country",new Rectangle2D.Float(w, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","country"));
        	w += 100;
        }
        items.addElement(TextFieldElementFactory.createStringElement("colour",new Rectangle2D.Float(w, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","colour"));
        w += 100;
        if(corporationId != CorporationData.JJ) {
	        items.addElement(TextFieldElementFactory.createStringElement("permissionNumber",new Rectangle2D.Float(w, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","permissionNumber"));
	        w += 100;
        }
        if(corporationId != CorporationData.ALLERGAN) {
        	items.addElement(TextFieldElementFactory.createStringElement("use",new Rectangle2D.Float(w, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","use"));
        	w += 100;
            if(!sys.isCorporation(CorporationData.DOW)) {      
		        items.addElement(TextFieldElementFactory.createStringElement("permissionExpiry",new Rectangle2D.Float(w, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","permissionExpiry"));
		        w += 100;
            }
        }
        if(sys.isCorporation(CorporationData.ALLERGAN)) {      
	        items.addElement(TextFieldElementFactory.createStringElement("cost_center",new Rectangle2D.Float(w, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","cost_center"));
	        w += 100;
        } else if(!sys.isCorporation(CorporationData.DOW)) {      
	        items.addElement(TextFieldElementFactory.createStringElement("ownerShip",new Rectangle2D.Float(w, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","ownerShip"));
	        w += 100;
        }
        if(sys.getUserSessionData().isForest()) {
        	
        	// Si es klabin muestro antt
        	if (sys.getUserSessionData().getFilter().getCorporationId().equals(new Integer(CorporationData.KLABIN))){
                items.addElement(TextFieldElementFactory.createStringElement("antt",new Rectangle2D.Float(w, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","antt"));
        	}else{
        		items.addElement(TextFieldElementFactory.createStringElement("sucta",new Rectangle2D.Float(w, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","sucta"));
        	}
            w += 100;
            
            if (sys.getUserSessionData().getFilter().getCorporationId().equals(new Integer(CorporationData.KLABIN))){
                items.addElement(TextFieldElementFactory.createStringElement("citv",new Rectangle2D.Float(w, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","citv"));
        	}else{
        	    items.addElement(TextFieldElementFactory.createStringElement("mtop",new Rectangle2D.Float(w, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","mtop"));
        	}
            w += 100;
            items.addElement(TextFieldElementFactory.createStringElement("ownercard",new Rectangle2D.Float(w, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","ownercard"));
            w += 100;
            items.addElement(TextFieldElementFactory.createStringElement("ensurance_comp",new Rectangle2D.Float(w, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","ensurance_comp"));
            w += 100;
            items.addElement(TextFieldElementFactory.createStringElement("ensurance",new Rectangle2D.Float(w, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","ensurance"));
            w += 100;
        }
        if(corporationId != CorporationData.ALLERGAN) {
	        items.addElement(TextFieldElementFactory.createStringElement("observations",new Rectangle2D.Float(w, 0, 250, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","observations"));
	        w += 250;
        }
        items.addElement(TextFieldElementFactory.createStringElement("fuel",new Rectangle2D.Float(w, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","fuel"));
        w += 100;
        items.addElement(TextFieldElementFactory.createStringElement("purchaseDate",new Rectangle2D.Float(w, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","purchaseDate"));
        w += 100;
        items.addElement(TextFieldElementFactory.createStringElement("chasisNumber",new Rectangle2D.Float(w, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","chasisNumber"));
        w += 100;
        if(sys.getUserSessionData().isForest()) {
            items.addElement(TextFieldElementFactory.createStringElement("axis_count",new Rectangle2D.Float(170, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"", "axis_count"));
            w += 100;
        }
        if(!sys.isCorporation(new int[]{CorporationData.ALLERGAN,CorporationData.DOW})) {      
        	items.addElement(TextFieldElementFactory.createStringElement("engine",new Rectangle2D.Float(w, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","engine"));
        	w += 100;
        }
        if(sys.getUserSessionData().isForest() || corporationId == CorporationData.ALLERGAN) {
            items.addElement(TextFieldElementFactory.createStringElement("hp",new Rectangle2D.Float(170, 0, 100, 15),
            		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","hp"));
            w += 100;
        }
        if(!sys.isCorporation(new int[]{CorporationData.ALLERGAN,CorporationData.DOW})) {      
	        items.addElement(TextFieldElementFactory.createStringElement("tankCapacity",new Rectangle2D.Float(w, 0, 100, 15),
	        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","tankCapacity"));
	        w += 100;
	        items.addElement(TextFieldElementFactory.createStringElement("tankUnit",new Rectangle2D.Float(w, 0, 100, 15),
	        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","tankUnit"));
	        w += 100;
	        if(sys.isCorporation(CorporationData.FORESTALORIENTAL)) {
		        items.addElement(TextFieldElementFactory.createStringElement("suspension",new Rectangle2D.Float(w, 0, 100, 15),
		        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","suspension"));
		        w += 100;
		        items.addElement(TextFieldElementFactory.createStringElement("renavan",new Rectangle2D.Float(w, 0, 100, 15),
		        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","renavan"));
		        w += 100;
		        items.addElement(TextFieldElementFactory.createStringElement("prefix",new Rectangle2D.Float(w, 0, 100, 15),
		        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","prefix"));
		        w += 100;
		        items.addElement(TextFieldElementFactory.createStringElement("monitoringsystem",new Rectangle2D.Float(w, 0, 100, 15),
		        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","monitoringsystem"));
		        w += 100;
	        }
	        items.addElement(TextFieldElementFactory.createStringElement("abs",new Rectangle2D.Float(w, 0, 100, 15),
	        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","abs"));
	        w += 100;
	        items.addElement(TextFieldElementFactory.createStringElement("airBag",new Rectangle2D.Float(w, 0, 100, 15),
	        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","airBag"));
	        w += 100;
	        items.addElement(TextFieldElementFactory.createStringElement("aireAcondicionado",new Rectangle2D.Float(w, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","aireAcondicionado"));
	        w += 100;
	        items.addElement(TextFieldElementFactory.createStringElement("cover",new Rectangle2D.Float(w, 0, 100, 15),
	        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","cover"));
	        w += 100;
	        items.addElement(TextFieldElementFactory.createStringElement("odometerUnit",new Rectangle2D.Float(w, 0, 100, 15),
	        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","odometerUnit"));
	        w += 100;
        }
        if(corporationId == CorporationData.MONSANTO) {
           // w += 100;
            items.addElement(TextFieldElementFactory.createStringElement("odometer",new Rectangle2D.Float(w, 0, 100, 15),
            		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","odometer"));
        w += 100;
        }
      
        if(corporationId == CorporationData.JJ) {
	        items.addElement(TextFieldElementFactory.createStringElement("cost",new Rectangle2D.Float(w, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","cost"));
	        w += 100;
	        items.addElement(TextFieldElementFactory.createStringElement("permissionNumber",new Rectangle2D.Float(w, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","permissionNumber"));
	        w += 100;
        }
       // w += 100;
        if(!sys.isCorporation(new int[]{CorporationData.ALLERGAN,CorporationData.DOW})) {      
	        items.addElement(TextFieldElementFactory.createStringElement("cost_center",new Rectangle2D.Float(w, 0, 80, 15),
	        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","cost_center"));
	        w += 80;
        }
        items.addElement(TextFieldElementFactory.createStringElement("driver",new Rectangle2D.Float(w, 0, 75, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","driverFirstName"));
        w += 75;
        items.addElement(TextFieldElementFactory.createStringElement("driver",new Rectangle2D.Float(w, 0, 75, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","driverLastName"));
        w+=75;
        if (corporationId == CorporationData.BAYER_MX){
            items.addElement(TextFieldElementFactory.createStringElement("wwwid",new Rectangle2D.Float(w, 0, 100, 15),
            		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","wwwid"));
            w += 100;
        }
        if (corporationId == CorporationData.JJ){
        	
        	//w += 75;
            items.addElement(TextFieldElementFactory.createStringElement("wwwid",new Rectangle2D.Float(w, 0, 100, 15),
            		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","wwwid"));
            w += 100;
            items.addElement(TextFieldElementFactory.createStringElement("co2emission",new Rectangle2D.Float(w, 0, 100, 15),
            		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","co2emission"));
            w += 100;
            items.addElement(TextFieldElementFactory.createStringElement("consume",new Rectangle2D.Float(w, 0, 100, 15),
            		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","consume"));
            w+=100;
        }
        //EXCLUSIONDATE
        items.addElement(TextFieldElementFactory.createStringElement("exclusionDate",new Rectangle2D.Float(w, 0, 100, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,fontDefinitionSimple,"","exclusionDate"));
        w+=100;
        if(corporationId == CorporationData.MONSANTO) {
            // w += 100;
             items.addElement(TextFieldElementFactory.createStringElement("comments",new Rectangle2D.Float(w, 0, 150, 15),
             		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","comments"));
             w += 150;
             
             //PERSONALADDRESS
             items.addElement(TextFieldElementFactory.createStringElement("personaladdress",new Rectangle2D.Float(w, 0, 100, 15),
             		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,fontDefinitionSimple,"","personaladdress"));
             w+=100;
             //CITY
             items.addElement(TextFieldElementFactory.createStringElement("city",new Rectangle2D.Float(w, 0, 100, 15),
              		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,fontDefinitionSimple,"","city"));
              w+=100;
            //PHONENUMBER
              items.addElement(TextFieldElementFactory.createStringElement("phonenumber",new Rectangle2D.Float(w, 0, 100, 15),
               		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,fontDefinitionSimple,"","phonenumber"));
               w+=100;
        }
        if(corporationId == CorporationData.MONSANTO || corporationId == CorporationData.MONTES_DE_PLATA) {
            //PREFIX
              items.addElement(TextFieldElementFactory.createStringElement("prefix",new Rectangle2D.Float(w, 0, 100, 15),
              		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,fontDefinitionSimple,"","prefix"));
                w+=100;
            //MONITORINGSYSTEM
              items.addElement(TextFieldElementFactory.createStringElement("monitoringsystem",new Rectangle2D.Float(w, 0, 100, 15),
               		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,fontDefinitionSimple,"","monitoringsystem"));
                 w+=100;
        }
        if(corporationId == CorporationData.MONSANTO) {
            //TACHOGRAPH
              items.addElement(TextFieldElementFactory.createStringElement("tachograph",new Rectangle2D.Float(w, 0, 100, 15),
               		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,fontDefinitionSimple,"","tachograph"));
                  w+=100;
        }
        if(corporationId == CorporationData.MONSANTO || corporationId == CorporationData.MONTES_DE_PLATA) {
            //RENAVAN
              items.addElement(TextFieldElementFactory.createStringElement("renavan",new Rectangle2D.Float(w, 0, 100, 15),
              		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,fontDefinitionSimple,"","renavan"));
                  w+=100;      
         }
        if(corporationId == CorporationData.MONTES_DE_PLATA) {
          //Type Semi/Zorra solo para plataformas        
          items.addElement(TextFieldElementFactory.createStringElement("semiZorra",new Rectangle2D.Float(w, 0, 100, 15),
            		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,fontDefinitionSimple,"","semiZorra"));
          w+=100;   
         }
        
        return items;
    }
    
    protected ReportHeader createReportHeader() {
        ReportHeader reportHeader = new ReportHeader();
        int height = writeFilter(reportHeader);

        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FirstLevelColor, null,
                new Rectangle2D.Float(10, height, getReportWidth(), 18), false, true));
        
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 80, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.registrationNumber")));
        int w = 90;//2300;
        if(corporationId == CorporationData.KLABIN || corporationId == CorporationData.YOLI) {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.prefix")));
            w += 80;
        }
        if(corporationId != CorporationData.ALLERGAN) {
        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,divGeoLabels[1]));
        	w += 80;
        }
        for(int i = 0; i < 9; i++) {
            if(!Util.empty(labels[i])) {
                if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 3) && 
                        !(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 6)) {
                    reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 18),
                            Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(labels[i])));
                        w += 80;
                }
            }
        }
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.vehicletype")));
        w += 100;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
                messages.getText("generic.vehicle.vehicledata.trade")));
        w += 100;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,
                messages.getText("generic.vehicle.vehicledata.model")));
        w += 100;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.year")));
        w += 100;
        if(sys.isCorporation(CorporationData.DOW)) {
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
	                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.costCenter")));
	        w += 100;
        }
        if(corporationId != CorporationData.ALLERGAN) {
        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.country")));
        	w += 100;
        }
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.colour")));
        w += 100;
        if(corporationId != CorporationData.JJ) {
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
	                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.permissionNumber")));
	        w += 100;
        }
        if(corporationId != CorporationData.ALLERGAN) {
        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.use")));
        	w += 100;
            if(!sys.isCorporation(CorporationData.DOW)) {      
		        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
		                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.permissionExpiryDate")));
		        w += 100;
            }
        }
        if(!sys.isCorporation(CorporationData.DOW)) {      
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
	                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicledata.ownership")));
	        w += 100;
        }
        if(sys.getUserSessionData().isForest()) {
        	
        	if (sys.getUserSessionData().getFilter().getCorporationId().equals(new Integer(CorporationData.KLABIN))){
        		reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("vehicle.expiry.antt")));            	
        	}else{
        		reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("vehicle.expiry.sucta")));
        	}
        	
        	w += 100;
        	if (sys.getUserSessionData().getFilter().getCorporationId().equals(new Integer(CorporationData.KLABIN))){
        		reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("vehicle.expiry.citv")));            	
        	}else{
        		reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("vehicle.expiry.mtop")));
        	}
            w += 100;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("vehicle.data.ownercard")));
            w += 100;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("vehicle.data.ensurancetype")));
            w += 100;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("vehicle.data.ensurance")));
            w += 100;
        }
        if(corporationId != CorporationData.ALLERGAN) {
        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 250, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.messages.observations")));
        	w += 250;
        }
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.fuel")));
        w += 100;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.purchaseDate")));
        w += 100;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.chasisNumber")));
        w += 100;
        if(sys.getUserSessionData().isForest()) {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("vehicle.data.axiscount")));
            w += 100;
        }
        if(!sys.isCorporation(new int[]{CorporationData.ALLERGAN,CorporationData.DOW})) {      
        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.engine")));
        	w += 100;
        }
        if(sys.getUserSessionData().isForest() || corporationId == CorporationData.ALLERGAN) {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("vehicle.data.hp")));
            w += 100;
        }
        if(!sys.isCorporation(new int[]{CorporationData.ALLERGAN,CorporationData.DOW})) {      
        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.tankCapacity")));
        	w += 100;
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
	                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.volumeunit")));
	        w += 100;
	        if(sys.isCorporation(CorporationData.FORESTALORIENTAL)) {
	        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
		                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.neumaticsuspension")));
		        w += 100;
		        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
		                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.optionalinfo.renavan")));
		        w += 100;
		        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
		                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.optionalinfo.prefix")));
		        w += 100;
		        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
		                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.optionalinfo.monitoringsystem")));
		        w += 100;
	        }
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
	                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.abs")));
	        w += 100;
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
	                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.airBag")));
	        w += 100;
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
	                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.aireAcondicionado")));
	        
	        w += 100;
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
	                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.cover")));
	        
	        w += 100;
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
	                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.distanceunit")));
	        w += 100;//gfds
        }
        if(corporationId == CorporationData.MONSANTO) {
           // w += 100;
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.messages.odometer")));
        w += 100; 
        }        
        if(corporationId == CorporationData.JJ) {
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
	                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.cost")));
	        w += 100;
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
	                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.permissionNumber")));
	        w += 100;
        }
        if(!sys.isCorporation(new int[]{CorporationData.ALLERGAN,CorporationData.DOW})) {
	        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 80, 18),
	                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.costCenter")));
	        w += 80;
        }
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 75, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.firstname")));
        w += 75;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 75, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.lastname")));
        w += 75;//gfd
        if (corporationId == CorporationData.BAYER_MX){
            //w += 75;
            // System.out.println("Reporte JJ Titulo ----------------------------------");
            reportHeader.addElement(LabelElementFactory.createLabelElement(
            		null,new Rectangle2D.Float(w, height, 100, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,
                    STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.driver.corporationid")));        
            
            w += 100;
        }
        if (corporationId == CorporationData.JJ){
            //w += 75;
            // System.out.println("Reporte JJ Titulo ----------------------------------");
            reportHeader.addElement(LabelElementFactory.createLabelElement(
            		null,new Rectangle2D.Float(w, height, 100, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,
                    STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.driver.wwwid")));        
            
            w += 100;
            // System.out.println("Reporte JJ Titulo ----------------------------------");
            reportHeader.addElement(LabelElementFactory.createLabelElement(
            		null,new Rectangle2D.Float(w, height, 100, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,
                    STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.co2emission")));        
            w += 100;
            // System.out.println("Reporte JJ Titulo ----------------------------------");
            reportHeader.addElement(LabelElementFactory.createLabelElement(
            		null,new Rectangle2D.Float(w, height, 100, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,
                    STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.consume")));
            w += 100;//gfd
        }
        //EXCLUSIONDATE
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.exclusiondate")));
        w += 100;
     
        if(corporationId == CorporationData.MONSANTO) {
            // w += 100;
             reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                     Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.messages.observations")));
             w += 150; 
             
             //PERSONALADDRESS
             reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                     Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.personaladdress")));
             w += 100;
             //CITY
             reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                     Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.city")));
             w += 100;
             //PHONENUMBER
             reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                     Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.phonenumber")));
             w += 100;
           //PREFIX
             reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                     Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.active")));
             w += 100;
           //MONITORINGSYSTEM
             reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                     Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.responsible")));
             w += 100;
           //TACHOGRAF
             reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                     Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.company")));
             w += 100;
           //RENVAN
             reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                     Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.plate")));
             w += 100;
        }        
        if(corporationId == CorporationData.MONTES_DE_PLATA) {
            //PREFIX
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.neumaticsuspension")));
            w += 100;
          //MONITORINGSYSTEM
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.weightcontrol")));
            w += 100;
            //RENVAN
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.euromotor")));
            w += 100;
            //Type Semi/Zorra plataformas
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(w, height, 100, 18),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"Semi/Zorra"));
            w += 100;
        }

        return reportHeader;
    }   

    /**
     * 
     */
    protected ReportFooter createReportFooter() {
        ReportFooter reportFooter = new ReportFooter();
        reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, null,
                new Rectangle2D.Float(10, 10, getReportWidth(), 20), false, true));
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 160, 20),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.messages.total")));
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement("total",new Rectangle2D.Float(getReportWidth()-100, 10, 100, 20),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"", formatNoDecimals,"total"));

   
        String output = (corporationId == CorporationData.ALLERGAN) ? ((VehicleMultiReportForm)form).getOutput() : ((VehicleReportForm)form).getOutput();
        if(output.equals("HTML")) {
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
    	CreateHyperLinksFunction link2, link;
    	TextFormatExpression textExp;
    	TextFormatExpression textExp2;
        ExpressionCollection functions = new ExpressionCollection();
        
        final ItemCountFunction countFunction = new ItemCountFunction();
        countFunction.setName("countExpression");
        countFunction.setGroup("dummy");
        functions.add(countFunction);
        
        final ElementColorFunction color = new ElementColorFunction();
        color.setName("changeColor");
        color.setElement("Variance-Field");
        color.setField("color");
        color.setColorTrue(Color.WHITE);
        color.setColorFalse(DCGray2);
        functions.add(color);

        textExp = new TextFormatExpression();
        textExp.setName("URLCreateExpression");
    	textExp.setPattern("./layout.jsp?refer=/vehicle/VehicleOrTrailerView.jsp?id={0}&isTrailer={1}");
        textExp.setField(0,"id");
        textExp.setField(1,"isTrailer"); 
        functions.add(textExp);

        link = new CreateHyperLinksFunction();
        link.setName("AddHRefs");
        link.setField("URLCreateExpression");
        link.setElement("URL-Field");
        functions.add(link);
 
        
        String output = (corporationId == CorporationData.ALLERGAN) ? ((VehicleMultiReportForm)form).getOutput() : ((VehicleReportForm)form).getOutput();
        String linkPage = (corporationId == CorporationData.ALLERGAN) ? ((VehicleMultiReportForm)form).getLink() : ((VehicleReportForm)form).getLink();
        if(output.equals("HTML")) {
            for(int i = 1; i <= pages; i++) {
                if(i != pageNumber) {
                    textExp2 = new TextFormatExpression();
                    textExp2.setName("URLCreateExpression"+i);                                      
                    textExp2.setPattern("./layout.jsp?refer=/vehicle/VehicleReportResult.jsp?page="+String.valueOf(i)+linkPage);
                    
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
