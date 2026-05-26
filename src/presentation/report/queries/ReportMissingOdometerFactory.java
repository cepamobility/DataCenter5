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
import presentation.translator.web.driver.DriverReportForm;
import presentation.translator.web.queries.ReportMissingOdometerForm;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.administration.corporation.tables.CorporationData;
import communication.language.Text;
import communication.vehicle.tables.VehicleData;

public class ReportMissingOdometerFactory extends ReportFactory {

    protected Text messages;
    private String[] fleets;
    private SystemAccess sys;
    private int[][] principalDivOrgs;
    
    public ReportMissingOdometerFactory(String name, TableModel model, ActionForm form, SystemAccess sys, String[] fleets) {
        super(name, model, form);
        this.fleets = fleets;
        this.sys = sys;
        messages = sys.getText();
        parseDivisionValues(sys, messages);
        principalDivOrgs = sys.getUserSessionData().getFilter().getPrincipalDivOrgs();
    }
    
    protected int getWidth() {
    	return getReportWidth() + 82;
    }
    
    protected int getReportWidth() {
    	int size = 800;
        if(principalDivOrgs[1][0] > 0 && principalDivOrgs[1][1] > 0)
        	size += 100;
        if(principalDivOrgs[2][0] > 0 && principalDivOrgs[2][1] > 0)
        	size += 100;
        if(sys.isCorporation(CorporationData.MONSANTO))
            	size += 100;
    	return size;
    }

    protected ItemBand createItemBand() {
        ItemBand items = super.createItemBand();        
        
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,new Rectangle2D.Float(10, 0, getReportWidth(), 15), false, true));
        
        items.addElement(TextFieldElementFactory.createStringElement("fleet",new Rectangle2D.Float(10, 0, 100, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","fleet"));
        items.addElement(TextFieldElementFactory.createStringElement("URL-Field",new Rectangle2D.Float(110, 0, 90, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","driverFirstName"));
        items.addElement(TextFieldElementFactory.createStringElement("URL-Field",new Rectangle2D.Float(200, 0, 90, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","driverLastName"));
        items.addElement(TextFieldElementFactory.createStringElement("director",new Rectangle2D.Float(290, 0, 60, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","director"));
        items.addElement(TextFieldElementFactory.createStringElement("URL-Field3",new Rectangle2D.Float(350, 0, 170, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","email"));
        items.addElement(TextFieldElementFactory.createStringElement("URL-Field2",new Rectangle2D.Float(520, 0, 80, 15),Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","plate"));
        items.addElement(TextFieldElementFactory.createStringElement("country",new Rectangle2D.Float(600, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","country"));
        items.addElement(TextFieldElementFactory.createStringElement("divorg1",new Rectangle2D.Float(700, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","divorg1"));
        if(principalDivOrgs[1][0] > 0 && principalDivOrgs[1][1] > 0)
        	items.addElement(TextFieldElementFactory.createStringElement("divorg2",new Rectangle2D.Float(800, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","divorg2"));
        if(principalDivOrgs[2][0] > 0 && principalDivOrgs[2][1] > 0)
        	items.addElement(TextFieldElementFactory.createStringElement("divorg3",new Rectangle2D.Float(900, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","divorg3"));
        if(sys.getCorporationId() == CorporationData.MONSANTO) 
        	items.addElement(TextFieldElementFactory.createStringElement("divorg4",new Rectangle2D.Float(1000, 0, 100, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","divorg4"));
        
        return items;
    }
    
    protected ReportHeader createReportHeader() {
        
        ReportHeader reportHeader = new ReportHeader();
        
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,new Rectangle2D.Float(10, 0, getReportWidth(), 25), false, true));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, getReportWidth(), 25),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("query.missingodometer.title")));

        int height = 25;
        height = writeParametersTitle(messages.getText("query.missingodometer.data"),reportHeader,height,10,280);
        height = writeParameter(messages.getText("query.missingodometer.period")+":",((ReportMissingOdometerForm)form).getPeriod(messages),reportHeader,height,10,100,110,180);
        height = writeParameter(messages.getText("generic.vehicle.vehicledata.matricula")+":",((ReportMissingOdometerForm)form).getPlate(),reportHeader,height,10,100,110,180);
        if(((ReportMissingOdometerForm)form).getStatus().equals(String.valueOf(VehicleData.ACTIVE))) {
            height = writeParameter(messages.getText("generic.messages.active"),"",reportHeader,height,10,100,110,0);
        }
        if(((ReportMissingOdometerForm)form).getStatus().equals(String.valueOf(VehicleData.EXCLUDED))) {
            height = writeParameter(messages.getText("generic.messages.excluded"),"",reportHeader,height,10,100,110,0);
        }

        height = writeParametersTitle(messages.getText("query.mileage.fleet"),reportHeader,height,10,280);
        for(int i = 0; i < fleets.length; i++) {
            height = writeParameter("","- "+fleets[i],reportHeader,height,10,0,10,280);
        }


        int maxHeight=height;
        height = 25;
       
        String[] divOrgs = sys.getUserSessionData().getFilter().getLevelLabels();
        for(int i = 0; i < divOrgs.length; i++) {
            if(!Util.empty(divOrgs[i])) {
                height = writeParametersTitle(messages.getText(divOrgs[i]),reportHeader,height,470,180);
                for(int j = 0; j < 3; j++) {
                    if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 1 && j== 0) && 
                            !(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 2 && j== 0)) {
                        if(!Util.empty(divOrgLabels[i][j])) {
                            height = writeParameter(divOrgLabels[i][j]+":",divOrgValues[i][j],reportHeader,height,470,80,550,100);
                        }
                    }
                }
            }
        }

        height = Math.max(height,maxHeight);
        
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FirstLevelColor, null,new Rectangle2D.Float(10, height, getReportWidth(), 18), false, true));

        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, height, 100, 15),Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.data.fleet")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(110, height, 90, 15),Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.firstname")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(200, height, 90, 15),Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.lastname")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(290, height, 60, 15),Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.driver.driverdata.director")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(350, height, 170, 15),Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("user.data.mail")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(520, height, 80, 15),Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("vehicle.trailer.plate")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(600, height, 100, 15),Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.messages.country")));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(700, height, 100, 15),Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,divOrgLabels[principalDivOrgs[0][0]-1][principalDivOrgs[0][1]-1]));
        if(principalDivOrgs[1][0] > 0 && principalDivOrgs[1][1] > 0)
        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(800, height, 100, 15),Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,divOrgLabels[principalDivOrgs[1][0]-1][principalDivOrgs[1][1]-1]));
        if(principalDivOrgs[2][0] > 0 && principalDivOrgs[2][1] > 0)
        	reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(900, height, 100, 15),Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,divOrgLabels[principalDivOrgs[2][0]-1][principalDivOrgs[2][1]-1]));
        if(sys.getCorporationId() == CorporationData.MONSANTO) 
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(1000, height, 100, 15),Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,divOrgLabels[principalDivOrgs[3][0]-1][principalDivOrgs[3][1]-1]));

        return reportHeader;
    }

    protected ReportFooter createReportFooter() {
        ReportFooter reportFooter = new ReportFooter();
        reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, null,
                new Rectangle2D.Float(10, 10, getReportWidth(), 15), false, true));
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 100, 15),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,messages.getText("query.driver.driverdata.total")));
        reportFooter.addElement(NumberFieldElementFactory.createNumberElement(null,new Rectangle2D.Float(getReportWidth()-100, 10, 100, 15),
                Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,"", formatNoDecimals,"total"));
        
        return reportFooter;
    }

    protected ExpressionCollection createFunctions() {
        ExpressionCollection functions = super.createFunctions();
        
        final ItemCountFunction countFunction = new ItemCountFunction();
        countFunction.setName("total");
        countFunction.setGroup("dummy");
        functions.add(countFunction);

        final ElementColorFunction color = new ElementColorFunction();
        color.setName("changeColor");
        color.setElement("Variance Field");
        color.setField("color");
        color.setColorTrue(gray1);
        color.setColorFalse(gray2);
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

        TextFormatExpression textExp2 = new TextFormatExpression();
        textExp2.setName("URLCreateExpression2");
        textExp2.setPattern("./layout.jsp?refer=vehicle/VehicleView.jsp?id={0}");
        textExp2.setField(0,"idVehicle");
        functions.add(textExp2);

        CreateHyperLinksFunction link2 = new CreateHyperLinksFunction();
        link2.setName("AddHRefs2");
        link2.setField("URLCreateExpression2");
        link2.setElement("URL-Field2");
        functions.add(link2);

        TextFormatExpression textExp3 = new TextFormatExpression();
        textExp3.setName("URLCreateExpression3");
        textExp3.setPattern("mailto:{0}");
        textExp3.setField(0,"email");
        functions.add(textExp3);

        CreateHyperLinksFunction link3 = new CreateHyperLinksFunction();
        link3.setName("AddHRefs3");
        link3.setField("URLCreateExpression3");
        link3.setElement("URL-Field3");
        functions.add(link3);

        return functions;
    }
}
