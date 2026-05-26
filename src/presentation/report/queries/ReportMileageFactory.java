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
import org.jfree.report.Group;
import org.jfree.report.GroupFooter;
import org.jfree.report.GroupHeader;
import org.jfree.report.GroupList;
import org.jfree.report.ItemBand;
import org.jfree.report.ReportFooter;
import org.jfree.report.ReportHeader;
import org.jfree.report.elementfactory.DrawableFieldElementFactory;
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

public class ReportMileageFactory extends ReportFactory {

    protected Text messages;
    private String[] fleets;
    private SystemAccess sys;
    private String[] labels;
    private int columns;
    private int months;
    private int corporationId;
    
    public ReportMileageFactory(String name, TableModel model, ActionForm form, SystemAccess sys, Text messages,String[] fleets,int months) {
        super(name, model, form);
        this.messages = messages;
        this.fleets = fleets;
        this.months = months; 
        this.sys = sys;
        labels = sys.getUserSessionData().getFilter().getOrderDivOrgLabels();
        columns = 0;
        for(int i = 0; i < 9; i++) {
            if(!Util.empty(labels[i])) {
                if(!(sys.getCorporationId() == 1 && i > 3)) {
                    columns++;
                }
            }
        }
        
        this.corporationId = sys.getCorporationId();
    }
    
    protected int getWidth() {
    	int start = (corporationId == CorporationData.MONSANTO) ? 1142 : 982;
    	return start+columns*80;
    }
    
//  it is declared but it is not used 
    protected int getReportWidth() {
    	int start = (corporationId == CorporationData.MONSANTO) ? 1060 : 900;
    	return start+columns*80;
    }

    protected GroupList createGroups() {
        GroupList grouplist = super.createGroups();
        
        Group group = new Group();
        group.setName("periods");
        group.addField("period");

        GroupHeader header = new GroupHeader();
        header.setName("periodHeader");
        
        header.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, null,
                new Rectangle2D.Float(10, 10, getReportWidth(), 15), false, true));
        header.addElement(TextFieldElementFactory.createStringElement("period",new Rectangle2D.Float(10, 10, getReportWidth(), 15),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","period"));

        header.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,ThirtdLevelColor, null,
                new Rectangle2D.Float(10, 30, getReportWidth(), 18), false, true));
        
        header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 30, 80, 18),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.mileage.country")));
        int x = 90;
        columns = 0;
        int corporation=sys.getUserSessionData().getFilter().getCorporationId().intValue();
        /*if((corporation == 1)&&(!Util.empty(labels[2]))){
        	header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, 30, 80, 18),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(labels[2])));
        	x += 80;
        	columns++;
        }*/
        for(int i = 0; i < 9; i++) {
            if(!Util.empty(labels[i])) {
            	if(!(corporation == 1 && (i == 4|| i>3))) {
                    header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, 30, 80, 18),
                        Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(labels[i])));
                    x += 80;
                    columns++;
                }
            }
        }
        header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, 30, 80, 18),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.mileage.vehicletype")));
        x += 80;
        header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, 30, 80, 18),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.mileage.vehicles")));
        
        if (sys.isCorporation(new int[]{CorporationData.CIMSA,CorporationData.SYNGENTA_BR})){
        	x += 80;
        	header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, 30, 80, 18),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.mileage.miles")));
        }
        
        x += 80;
        if(!sys.isForestalUnified()) {
	        header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, 30, 80, 18),
	                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("datatype.distanceunit.kilometers")));
	        x += 80;
        }
        header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, 30, 80, 18),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.mileage.accidents")));
        x += 80;
        header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, 30, 80, 18),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.mileage.incidents")));
        x += 80;
        if(corporationId == CorporationData.MONSANTO) {
            header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, 30, 80, 18),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.mileage.nearmiss")));
            x += 80;
        	
        }
        header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, 30, 50, 18),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.mileage.apmm")));
        x += 50;
        header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, 30, 50, 18),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.mileage.ipmm")));
        x += 50;
        
        header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, 30, 80, 18),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.apmm.vehicle_percent")));
        x += 80;
        
        
        header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, 30, 80, 18),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.mileage.accident.cost")));
        x += 80;
        header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, 30, 80, 18),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.mileage.incident.cost")));
        x += 80;

        if(corporationId == CorporationData.MONSANTO) {
            header.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, 30, 80, 18),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("query.mileage.nearmiss.cost")));
            x += 80;
        	
        }
        group.setHeader(header);

        GroupFooter footer = new GroupFooter();
        footer.setName("divisionFooter");
        
        group.setFooter(footer);
        footer.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, 
                null,new Rectangle2D.Float(10, 0, getReportWidth(), 15), false, true));
        x = columns * 80 + 160;
        footer.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, x, 15),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.messages.total")));
        x += 10;
        footer.addElement(NumberFieldElementFactory.createNumberElement("total1",new Rectangle2D.Float(x, 0, 80, 15),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####", "total1"));
        
        if (sys.isCorporation(new int[]{CorporationData.CIMSA,CorporationData.SYNGENTA_BR})){
	        x += 80;
	        footer.addElement(NumberFieldElementFactory.createNumberElement("total2",new Rectangle2D.Float(x, 0, 80, 15),
	                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","total2"));
        }    	
        if(!sys.isForestalUnified()) {
    		x += 80;
        	footer.addElement(NumberFieldElementFactory.createNumberElement("total10",new Rectangle2D.Float(x, 0, 80, 15),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","total10"));
        }
        x += 80;
        footer.addElement(NumberFieldElementFactory.createNumberElement("total3",new Rectangle2D.Float(x, 0, 80, 15),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","total3"));
        x += 80;
        footer.addElement(NumberFieldElementFactory.createNumberElement("total4",new Rectangle2D.Float(x, 0, 80, 15),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","total4"));
        x += 80;
        if(corporationId == CorporationData.MONSANTO) {
            footer.addElement(NumberFieldElementFactory.createNumberElement("total9",new Rectangle2D.Float(x, 0, 80, 15),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","total9"));
            x += 80;
        }
        footer.addElement(NumberFieldElementFactory.createNumberElement("total5",new Rectangle2D.Float(x, 0, 50, 15),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####.##","total5"));
        x += 50;
        footer.addElement(NumberFieldElementFactory.createNumberElement("total8",new Rectangle2D.Float(x, 0, 50, 15),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####.##","total8"));
        x += 50;
        footer.addElement(NumberFieldElementFactory.createNumberElement("total15",new Rectangle2D.Float(x, 0, 80, 15),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####.##","total15"));
        x += 80;
        footer.addElement(NumberFieldElementFactory.createNumberElement("total6",new Rectangle2D.Float(x, 0, 80, 15),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","total6"));
        x += 80;
        footer.addElement(NumberFieldElementFactory.createNumberElement("total7",new Rectangle2D.Float(x, 0, 80, 15),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","total7"));
        if(corporationId == CorporationData.MONSANTO) {
            x += 80;
            footer.addElement(NumberFieldElementFactory.createNumberElement("total75",new Rectangle2D.Float(x, 0, 80, 15),
                    Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","total75"));
        }

        grouplist.add(group);
        return grouplist;
    }

    protected ItemBand createItemBand() {
        ItemBand items = super.createItemBand();        
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 15), false, true));
        items.addElement(TextFieldElementFactory.createStringElement("country",new Rectangle2D.Float(10, 0, 80, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","country"));
        int x = 90;
        int corporation=sys.getUserSessionData().getFilter().getCorporationId().intValue();
/*        if(corporation == 1){
        	items.addElement(TextFieldElementFactory.createStringElement("divOrg3Name",new Rectangle2D.Float(x, 0, 80, 15),
        			Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","divOrg3Name"));
            x += 80;
        }*/ 
        for(int i = 1; i <= columns; i++) {
    		items.addElement(TextFieldElementFactory.createStringElement("divOrg"+i+"Name",new Rectangle2D.Float(x, 0, 80, 15),
    				Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","divOrg"+i+"Name"));
    		x += 80;
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
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","accidentCost"));
        x += 80;
        items.addElement(NumberFieldElementFactory.createNumberElement("incidentCost",new Rectangle2D.Float(x, 0, 80, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","incidentCost"));
        x += 80;
        if(corporationId == CorporationData.MONSANTO) {
            items.addElement(NumberFieldElementFactory.createNumberElement("nearmissCost",new Rectangle2D.Float(x, 0, 80, 15),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","nearmissCost"));
            x += 80;        	
        }
        return items;
    }
    
    protected ReportHeader createReportHeader() {
        
        ReportHeader reportHeader = new ReportHeader();
        
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 25), false, true));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, getReportWidth(), 25),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("query.mileage.title")));

        int height = 25;
        height = writeParametersTitle(messages.getText("query.mileage.parameters"),reportHeader,height,10,160);
        height = writeParameter(messages.getText("query.mileage.from")+":",((ReportMileageForm)form).getFrom(messages),reportHeader,height,10,80,90,160);
        height = writeParameter(messages.getText("query.mileage.to")+":",((ReportMileageForm)form).getTo(messages),reportHeader,height,10,80,90,160);
        
        String type = (((ReportMileageForm)form).getType().equals("period")) ? "query.mileage.byperiod" : "query.mileage.total";
        height = writeParameter(messages.getText("query.mileage.type")+":",messages.getText(type),reportHeader,height,10,80,90,160);

        height = 25;
        height = writeParametersTitle(messages.getText("query.mileage.fleet"),reportHeader,height,330,80);
        for(int i = 0; i < fleets.length; i++) {
            height = writeParameter("","- "+fleets[i],reportHeader,height,330,0,330,320);
        }

        return reportHeader;
    }

    protected ReportFooter createReportFooter() {
        ReportFooter reportFooter = new ReportFooter();
        reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, null,
                new Rectangle2D.Float(10, 10, getReportWidth(), 15), false, true));
        int x = columns * 80 + 160;
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, x, 15),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.messages.total")));
        x += 10;
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement("vehicleCountTotal",new Rectangle2D.Float(x, 10, 80, 15),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####", "vehicleCountTotal"));
        if (sys.isCorporation(new int[]{CorporationData.CIMSA,CorporationData.SYNGENTA_BR})){
		    x += 80;
		    reportFooter.addElement(NumberFieldElementFactory.createNumberElement("milesTotal",new Rectangle2D.Float(x, 10, 80, 15),
		                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","milesTotal"));
        }
        if(!sys.isForestalUnified()) {
	        x += 80;
        	reportFooter.addElement(NumberFieldElementFactory.createNumberElement("kilometersTotal",new Rectangle2D.Float(x, 10, 80, 15),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","kilometersTotal"));
        }
        x += 80;
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement("accident_countTotal",new Rectangle2D.Float(x, 10, 80, 15),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","accidentCountTotal"));
        x += 80;
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement("incidentCountTotal",new Rectangle2D.Float(x, 10, 80, 15),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","incidentCountTotal"));
        x += 80;
        if(corporationId == CorporationData.MONSANTO) {
        	reportFooter.addElement(NumberFieldElementFactory.createNumberElement("nearmissCountTotal",new Rectangle2D.Float(x, 10, 80, 15),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","nearmissCountTotal"));
        	x += 80;
        }
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement("apmmTotal",new Rectangle2D.Float(x, 10, 50, 15),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####.##","apmmTotal"));
        x += 50;
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement("ipmmTotal",new Rectangle2D.Float(x, 10, 50, 15),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####.##","ipmmTotal"));
        x += 50;
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement("vehiclespercentTotal",new Rectangle2D.Float(x, 10, 80, 15),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####.##","vehiclespercentTotal"));
        x += 80;
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement("accidentCostTotal",new Rectangle2D.Float(x, 10, 80, 15),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","accidentCostTotal"));
        x += 80;
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement("incidentCostTotal",new Rectangle2D.Float(x, 10, 80, 15),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","incidentCostTotal"));
        if(corporationId == CorporationData.MONSANTO) {
        	x += 80;
        	reportFooter.addElement(NumberFieldElementFactory.createNumberElement("nearmissCostTotal",new Rectangle2D.Float(x, 10, 80, 15),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","#####","nearmissCostTotal"));
        }

        DrawableFieldElementFactory factory = new DrawableFieldElementFactory();
        factory.setName("drawable-field");
        
        int y = 35;
     /*   // Para PDF y XLS
        if(!Util.empty(reportType) && !reportType.equals("HTML") && divisionsChart) {
            factory.setAbsolutePosition(new Point2D.Float(100, y));
            factory.setMinimumSize(new FloatDimension(600, 400));
            factory.setFieldname("Divisions");
            reportFooter.addElement(factory.createElement());
            y += 415;
        }
        
        // Para PDF y XLS
        if(!Util.empty(reportType) && !reportType.equals("HTML") && subdivisionsChart) {
            factory.setAbsolutePosition(new Point2D.Float(100, y));
            factory.setMinimumSize(new FloatDimension(600, 400));
            factory.setFieldname("Subdivisions");
            reportFooter.addElement(factory.createElement());
        }
*/
        return reportFooter;
    }

    protected ExpressionCollection createFunctions() {
        ExpressionCollection functions = super.createFunctions();
        
        /*final ItemSumFunction vehicle_countTotal = new ItemSumFunction();
        vehicle_countTotal.setName("vehicleCountTotal");
        vehicle_countTotal.setField("vehicleCount");
        functions.add(vehicle_countTotal);*/

        final ItemSumFunction total1 = new ItemSumFunction();
        total1.setName("total1");
        total1.setGroup("periods");
        total1.setField("vehicleCount");
        functions.add(total1);

        final ItemSumFunction total2 = new ItemSumFunction();
        total2.setName("total2");
        total2.setGroup("periods");
        total2.setField("miles");
        functions.add(total2);

        final ItemSumFunction total3 = new ItemSumFunction();
        total3.setName("total3");
        total3.setGroup("periods");
        total3.setField("accidentCount");
        functions.add(total3);

        final ItemSumFunction total4 = new ItemSumFunction();
        total4.setName("total4");
        total4.setGroup("periods");
        total4.setField("incidentCount");
        functions.add(total4);
        
        final ItemSumFunction total9 = new ItemSumFunction();
        total9.setName("total9");
        total9.setGroup("periods");
        total9.setField("nearmissCount");
        functions.add(total9);

        BSHExpression total15 = new BSHExpression();
        total15.setName("total15");
        total15.setExpression("getValue() {" +
                "            Number vehicles = (Number) dataRow.get (\"total1\");" +
                "            Number accidents = (Number) dataRow.get (\"total3\");" +
                "            if(vehicles.floatValue() == 0) {" +
                "               return new Float (0);" +
                "            }" +
                "			 float result = 0;" +
                "			 if (accidents != null && vehicles != null && vehicles.floatValue() != 0){ "+			
                "            	result = accidents.floatValue() / vehicles.floatValue() * 12 / "+months+";" +
                "			 } "+
                "            return new Float (result * 100);" +
                "}");
        functions.add(total15);
        
        

        final ItemSumFunction total6 = new ItemSumFunction();
        total6.setName("total6");
        total6.setGroup("periods");
        total6.setField("accidentCost");
        functions.add(total6);

        final ItemSumFunction total7 = new ItemSumFunction();
        total7.setName("total7");
        total7.setGroup("periods");
        total7.setField("incidentCost");
        functions.add(total7);

        final ItemSumFunction total75 = new ItemSumFunction();
        total75.setName("total75");
        total75.setGroup("periods");
        total75.setField("nearmissCost");
        functions.add(total75);

        final ElementColorFunction color = new ElementColorFunction();
        color.setName("changeColor");
        color.setElement("Variance Field");
        color.setField("color");
        color.setColorTrue(gray1);
        color.setColorFalse(gray2);
        functions.add(color);

        BSHExpression bshExp1 = new BSHExpression();
        bshExp1.setName("total5");
        bshExp1.setExpression("getValue() {" +
                "            Number accidents = (Number) dataRow.get (\"total3\");" +
                "            Number miles = (Number) dataRow.get (\"total2\");" +
                "            float result = 0;" +
                "            if (accidents != null && miles != null && miles.floatValue() != 0){ "+
                "                result = accidents.floatValue() * 1000000 / miles.floatValue();" +
                "            } "+
                "            return new Float (result);" +
                "}");
        functions.add(bshExp1);

        BSHExpression bshExp2 = new BSHExpression();
        bshExp2.setName("total8");
        bshExp2.setExpression("getValue() {" +
                "            Number incidents = (Number) dataRow.get (\"total4\");" +
                "            Number miles = (Number) dataRow.get (\"total2\");" +
                "            float result = 0; "+
                "		     if (incidents != null && miles != null && miles.floatValue() != 0){ "+
                "            	result = incidents.floatValue() * 1000000 / miles.floatValue();" +
                "            }"+
                "            return new Float (result);" +
                "}");
        functions.add(bshExp2);

        final ItemSumFunction total10 = new ItemSumFunction();
        total10.setName("total10");
        total10.setGroup("periods");
        total10.setField("kilometers");
        functions.add(total10);

        final ItemSumFunction miles = new ItemSumFunction();
        miles.setName("milesTotal");
        miles.setField("miles");
        functions.add(miles);
        
        final ItemSumFunction kilometers = new ItemSumFunction();
        kilometers.setName("kilometersTotal");
        kilometers.setField("kilometers");
        functions.add(kilometers);
        
        final ItemSumFunction vehicleCount = new ItemSumFunction();
        vehicleCount.setName("vehicleCountTotal");
        vehicleCount.setField("vehicleCount");
        functions.add(vehicleCount);
        
        final ItemSumFunction accidentCount = new ItemSumFunction();
        accidentCount.setName("accidentCountTotal");
        accidentCount.setField("accidentCount");
        functions.add(accidentCount);

        final ItemSumFunction incidentCount = new ItemSumFunction();
        incidentCount.setName("incidentCountTotal");
        incidentCount.setField("incidentCount");
        functions.add(incidentCount);

        final ItemSumFunction accidentCost = new ItemSumFunction();
        accidentCost.setName("accidentCostTotal");
        accidentCost.setField("accidentCost");
        functions.add(accidentCost);

        final ItemSumFunction nearmissCount = new ItemSumFunction();
        nearmissCount.setName("nearmissCountTotal");
        nearmissCount.setField("nearmissCount");
        functions.add(nearmissCount);

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
                "	         float result = 0;"+
                "            if (accidents != null && miles != null && miles.floatValue() != 0){ "+
                "               result = accidents.floatValue() * 1000000 / miles.floatValue();" +
                "            }"+
                "            return new Float (result);" +
                "}");
        functions.add(apmmTotal);

        BSHExpression ipmmTotal = new BSHExpression();
        ipmmTotal.setName("ipmmTotal");
        ipmmTotal.setExpression("getValue() {" +
                "            Number incidents = (Number) dataRow.get (\"incidentCountTotal\");" +
                "            Number miles = (Number) dataRow.get (\"milesTotal\");" +
                "            float result = 0;"+
                "            if (incidents != null && miles != null && miles.floatValue() != 0){ "+
                "                result = incidents.floatValue() * 1000000 / miles.floatValue();" +
                "            }"+
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
                "            float result = 0;"+
                "            if (accidents != null && vehicles != null && vehicles.floatValue() != 0){ "+
                "                result = accidents.floatValue() / vehicles.floatValue() * 12 / "+months+";" +
                "            } "+
                "            return new Float (result * 100);" +
                "}");
        functions.add(bshExp6);

        return functions;
    }
}
