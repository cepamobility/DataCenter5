/**
 * Created on 10-jul-2006
 * CEPA
 * DataCenter 5
 */
package presentation.report.queries;

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
import org.jfree.report.function.ItemSumFunction;
import org.jfree.report.modules.misc.beanshell.BSHExpression;

import presentation.report.ReportFactory;
import presentation.translator.web.queries.ReportMileageForm;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.administration.corporation.tables.CorporationData;
import communication.language.Text;

public class ReportMileageTotalFactory extends ReportFactory {

    protected Text messages;
    private String[] fleets;
    private SystemAccess sys;
    private String[] labels;
    private int columns;
    private int months;
	private int corporationId;
	private int w;
  
    public ReportMileageTotalFactory(String name, TableModel model, ActionForm form, SystemAccess sys, Text messages, String[] fleets,int months) {
        super(name, model, form);
        this.messages = messages;
        this.fleets = fleets;
        this.months=months;
        this.sys = sys;
        corporationId = sys.getCorporationId();
        labels = sys.getUserSessionData().getFilter().getOrderDivOrgLabels();
        columns = 0;
        for(int i = 0; i < 9; i++) {
            if(!Util.empty(labels[i])) {
                if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i > 3)) {
                    columns++;
                }
            }
        }
    	w = 820+columns*80;
        switch(corporationId) {
	    	case CorporationData.CIMSA:
	    		w += 80;
	    		break;
	    	case CorporationData.MONSANTO:
	    		w += 240;
	    		break;
        }
    }
    
    protected int getWidth() {
    	return w + 82;
    }
    
//  it is declared but it is not used 
    protected int getReportWidth() {
    	return w;
    }
    
    protected ItemBand createItemBand() {
        ItemBand items = super.createItemBand();        
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 15), false, true));
        items.addElement(TextFieldElementFactory.createStringElement("country",new Rectangle2D.Float(10, 0, 80, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","country"));
        int x = 90;
        int corporation=sys.getUserSessionData().getFilter().getCorporationId().intValue();
        if(corporation == 1){
        	items.addElement(TextFieldElementFactory.createStringElement("divOrg3Name",new Rectangle2D.Float(x, 0, 80, 15),
        			Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","divOrg3Name"));
            x += 80;
        } 
        for(int i = 1; i <= columns; i++) {
        	if(!(corporation == 1 && i==3)){
        		items.addElement(TextFieldElementFactory.createStringElement("divOrg"+i+"Name",new Rectangle2D.Float(x, 0, 80, 15),
        				Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","divOrg"+i+"Name"));
        		x += 80;
        	}
        }
        items.addElement(NumberFieldElementFactory.createStringElement("vehicleType",new Rectangle2D.Float(x, 0, 80, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","vehicleType"));
        x += 80;
        items.addElement(NumberFieldElementFactory.createNumberElement("vehicleCount",new Rectangle2D.Float(x, 0, 80, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####", "vehicleCount"));
        if (sys.isCorporation(new int[]{CorporationData.CIMSA,CorporationData.SYNGENTA_BR})){
	        x += 80;
	        items.addElement(NumberFieldElementFactory.createNumberElement("miles",new Rectangle2D.Float(x, 0, 80, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","miles"));
        }
        if(!sys.isForestalUnified()) {
        	x += 80;
        	items.addElement(NumberFieldElementFactory.createNumberElement("kilometers",new Rectangle2D.Float(x, 0, 80, 15),
        		Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","kilometers"));
    	}
        x += 80;
        items.addElement(NumberFieldElementFactory.createNumberElement("accidentCount",new Rectangle2D.Float(x, 0, 80, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","accidentCount"));
        x += 80;
        items.addElement(NumberFieldElementFactory.createNumberElement("incidentCount",new Rectangle2D.Float(x, 0, 80, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","incidentCount"));
        x += 80;
        if(corporationId == CorporationData.MONSANTO) {
            items.addElement(NumberFieldElementFactory.createNumberElement("nearmissCount",new Rectangle2D.Float(x, 0, 80, 15),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","nearmissCount"));
            x += 80;
        }
        items.addElement(NumberFieldElementFactory.createNumberElement("apmm",new Rectangle2D.Float(x, 0, 50, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####.##","apmm"));
        x += 50;
        items.addElement(NumberFieldElementFactory.createNumberElement("ipmm",new Rectangle2D.Float(x, 0, 50, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####.##","ipmm"));
        x += 50;
        items.addElement(NumberFieldElementFactory.createNumberElement("vehicle_percent",new Rectangle2D.Float(x, 0, 80, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",formatOneDecimals,"vehicle_percent"));
        x += 80;
        items.addElement(NumberFieldElementFactory.createNumberElement("accidentCost",new Rectangle2D.Float(x, 0, 80, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####.##","accidentCost"));
        x += 80;
        items.addElement(NumberFieldElementFactory.createNumberElement("incidentCost",new Rectangle2D.Float(x, 0, 80, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####.##","incidentCost"));
        if(corporationId == CorporationData.MONSANTO) {
            x += 80;
            items.addElement(NumberFieldElementFactory.createNumberElement("nearmissCost",new Rectangle2D.Float(x, 0, 80, 15),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","nearmissCost"));
        }
        return items;
    }
    
    protected ReportHeader createReportHeader() {
        
        ReportHeader header = new ReportHeader();
        
        header.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 25), false, true));
        header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, getReportWidth(), 25),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("query.mileage.title")));

        int height = 25;
        height = writeParametersTitle(messages.getText("query.mileage.parameters"),header,height,10,160);
        height = writeParameter(messages.getText("query.mileage.from")+":",((ReportMileageForm)form).getFrom(messages),header,height,10,80,90,160);
        height = writeParameter(messages.getText("query.mileage.to")+":",((ReportMileageForm)form).getTo(messages),header,height,10,80,90,160);
        
        String type = (((ReportMileageForm)form).getType().equals("period")) ? "query.mileage.byperiod" : "query.mileage.total";
        height = writeParameter(messages.getText("query.mileage.type")+":",messages.getText(type),header,height,10,80,90,160);
        
        int maxheight = height;
        
        height = 25;
        height = writeParametersTitle(messages.getText("query.mileage.fleet"),header,height,330,160);
        for(int i = 0; i < fleets.length; i++) {
            height = writeParameter("",fleets[i],header,height,330,0,330,240);
        }

        height = Math.max(height,maxheight);
        
        header.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,ThirtdLevelColor, null,
                new Rectangle2D.Float(10, height, getReportWidth(), 18), false, true));        
        header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 80, 18),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.mileage.country")));
        int x = 90;
        columns = 0;
        int corporation=sys.getUserSessionData().getFilter().getCorporationId().intValue();
        if((corporation == 1)&&(!Util.empty(labels[2]))){
        	header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(labels[2])));
        	x += 80;
        	columns++;
        }
        for(int i = 0; i < 9; i++) {
            if(!Util.empty(labels[i])) {
            	if(!(corporation == 1 && (i == 2|| i>3))) {
                    header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                        Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(labels[i])));
                    x += 80;
                    columns++;
                }
            }
        }
        header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.mileage.vehicletype")));
        x += 80;
        header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.mileage.vehicles")));
        if (sys.isCorporation(new int[]{CorporationData.CIMSA,CorporationData.SYNGENTA_BR})){
        	x += 80;
        	header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.mileage.miles")));
        }
	    if(!sys.isForestalUnified()) {
	        x += 80;
	        header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
	                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("datatype.distanceunit.kilometers")));
	    }
        x += 80;
        header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.mileage.accidents")));
        x += 80;
        header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.mileage.incidents")));
        x += 80;
        if(corporationId == CorporationData.MONSANTO) {
            header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.mileage.nearmiss")));
            x += 80;
        }
        header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 50, 18),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.mileage.apmm")));
        x += 50;
        header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 50, 18),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.mileage.ipmm")));
        x += 50;
        header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x,height, 80, 18),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.apmm.vehicle_percent")));
        x += 80;
        header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.mileage.accident.cost")));
        x += 80;
        header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.mileage.incident.cost")));
        if(corporationId == CorporationData.MONSANTO) {
            x += 80;
            header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.mileage.nearmiss.cost")));
        }

        return header;
    }

    protected ReportFooter createReportFooter() {
        ReportFooter reportFooter = new ReportFooter();
        reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, ThirtdLevelColor, null,
                new Rectangle2D.Float(10, 10, getReportWidth(), 15), false, true));
        int x = columns * 80 + 160;
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, x, 15),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,fontDefinitionBold,messages.getText("generic.messages.total")));
        x += 10;
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement("vehicleCountTotal",new Rectangle2D.Float(x, 10, 80, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####", "vehicleCountTotal"));
        if (sys.isCorporation(new int[]{CorporationData.CIMSA,CorporationData.SYNGENTA_BR})){
	        x += 80;
	        reportFooter.addElement(NumberFieldElementFactory.createNumberElement("milesTotal",new Rectangle2D.Float(x, 10, 80, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","milesTotal"));
        }
        if(!sys.isForestalUnified()) {
    		x += 80;
    		reportFooter.addElement(NumberFieldElementFactory.createNumberElement("kilometersTotal",new Rectangle2D.Float(x, 10, 80, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","kilometersTotal"));
        }
        x += 80;
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement("accident_countTotal",new Rectangle2D.Float(x, 10, 80, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","accidentCountTotal"));
        x += 80;
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement("incidentCountTotal",new Rectangle2D.Float(x, 10, 80, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","incidentCountTotal"));
        x += 80;
        if(corporationId == CorporationData.MONSANTO) {
            reportFooter.addElement(NumberFieldElementFactory.createNumberElement("nearmissCountTotal",new Rectangle2D.Float(x, 10, 80, 15),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","nearmissCountTotal"));
            x += 80;
        }
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement("apmmTotal",new Rectangle2D.Float(x, 10, 50, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####.##","apmmTotal"));
        x += 50;
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement("ipmmTotal",new Rectangle2D.Float(x, 10, 50, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####.##","ipmmTotal"));
        x += 50;
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement("vehiclespercentTotal",new Rectangle2D.Float(x, 10, 80, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####.##","vehiclespercentTotal"));
        x += 80;
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement("accidentCostTotal",new Rectangle2D.Float(x, 10, 80, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","accidentCostTotal"));
        x += 80;
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement("incidentCostTotal",new Rectangle2D.Float(x, 10, 80, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","incidentCostTotal"));

        if(corporationId == CorporationData.MONSANTO) {
            x += 80;
            reportFooter.addElement(NumberFieldElementFactory.createNumberElement("nearmissCostTotal",new Rectangle2D.Float(x, 10, 80, 15),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","nearmissCostTotal"));
        }
        return reportFooter;
    }

    protected ExpressionCollection createFunctions() {
        ExpressionCollection functions = super.createFunctions();
        
        final ElementColorFunction color = new ElementColorFunction();
        color.setName("changeColor");
        color.setElement("Variance Field");
        color.setField("color");
        color.setColorTrue(gray1);
        color.setColorFalse(gray2);
        functions.add(color);

        final ItemSumFunction vehicleCount = new ItemSumFunction();
        vehicleCount.setName("vehicleCountTotal");
        vehicleCount.setField("vehicleCount");
        functions.add(vehicleCount);

        final ItemSumFunction miles = new ItemSumFunction();
        miles.setName("milesTotal");
        miles.setField("miles");
        functions.add(miles);

        final ItemSumFunction kilometers = new ItemSumFunction();
        kilometers.setName("kilometersTotal");
        kilometers.setField("kilometers");
        functions.add(kilometers);

        final ItemSumFunction accidentCount = new ItemSumFunction();
        accidentCount.setName("accidentCountTotal");
        accidentCount.setField("accidentCount");
        functions.add(accidentCount);

        final ItemSumFunction incidentCount = new ItemSumFunction();
        incidentCount.setName("incidentCountTotal");
        incidentCount.setField("incidentCount");
        functions.add(incidentCount);

        final ItemSumFunction nearmissCount = new ItemSumFunction();
        nearmissCount.setName("nearmissCountTotal");
        nearmissCount.setField("nearmissCount");
        functions.add(nearmissCount);

        final ItemSumFunction accidentCost = new ItemSumFunction();
        accidentCost.setName("accidentCostTotal");
        accidentCost.setField("accidentCost");
        functions.add(accidentCost);

        final ItemSumFunction incidentCost = new ItemSumFunction();
        incidentCost.setName("incidentCostTotal");
        incidentCost.setField("incidentCost");
        functions.add(incidentCost);

        final ItemSumFunction nearmissCost = new ItemSumFunction();
        nearmissCost.setName("nearmissCostTotal");
        nearmissCost.setField("nearmissCost");
        functions.add(nearmissCost);

        BSHExpression apmmTotal = new BSHExpression();
        apmmTotal.setName("apmmTotal");
        apmmTotal.setExpression("getValue() {" +
                "            Number accidents = (Number) dataRow.get (\"accidentCountTotal\");" +
                "            Number miles = (Number) dataRow.get (\"milesTotal\");" +
                "            float result = accidents.floatValue() * 1000000 / miles.floatValue();" +
                "            return new Float (result);" +
                "}");
        functions.add(apmmTotal);

        BSHExpression ipmmTotal = new BSHExpression();
        ipmmTotal.setName("ipmmTotal");
        ipmmTotal.setExpression("getValue() {" +
                "            Number incidents = (Number) dataRow.get (\"incidentCountTotal\");" +
                "            Number miles = (Number) dataRow.get (\"milesTotal\");" +
                "            float result = incidents.floatValue() * 1000000 / miles.floatValue();" +
                "            return new Float (result);" +
                "}");
        functions.add(ipmmTotal);
        

        BSHExpression bshExp6 = new BSHExpression();
        bshExp6.setName("vehiclespercentTotal");
        bshExp6.setExpression("getValue() {" +
                "            Number vehicles = (Number) dataRow.get (\"vehicleCountTotal\");" +
                "            Number accidents = (Number) dataRow.get (\"accidentCountTotal\");" +
                "            if(vehicles.floatValue() == 0) {" +
                "               return new Float (0);" +
                "            }" +
                "            float result = accidents.floatValue() / vehicles.floatValue() * 12 / "+months+";" +
                "            return new Float (result * 100);" +
                "}");
        functions.add(bshExp6);

        return functions;
    }
}
