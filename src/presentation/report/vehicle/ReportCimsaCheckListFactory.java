package presentation.report.vehicle;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
import java.util.Hashtable;
import java.util.LinkedList;

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
import org.jfree.report.function.ItemSumFunction;
import org.jfree.report.function.TextFormatExpression;

import presentation.report.ReportFactory;
import presentation.translator.web.util.Util;
import presentation.translator.web.vehicle.ReportCheckListForm;
import business.SystemAccess;

import communication.activity.tables.ItemData;
import communication.administration.corporation.tables.CorporationData;
import communication.language.Text;
import communication.vehicle.report.ReportCheckListDetailedData;
import communication.vehicle.report.ReportCimsaCheckListData;

public class ReportCimsaCheckListFactory extends ReportFactory {

    protected Text messages;
    
    private int totalLength;
    private int totalItems;
    private ItemData[] checkItems;
    private int pages;
    private int pageNumber;
    private String label;
    
    
    public ReportCimsaCheckListFactory(String name,ReportCimsaCheckListData model, ReportCheckListForm form, ItemData[] items, SystemAccess sys) {
        super(name, model, form, sys);
        parseDivisionValues(sys,sys.getText());
        this.checkItems = items;
        this.messages = sys.getText();
	}

	protected int getWidth() {
        return getReportWidth()+82;
    }

    protected int getReportWidth() {
        return checkItems.length*20+390;
        
    }
    
    protected ItemBand createItemBand() {
        
        ItemBand items = super.createItemBand();        
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 15), false, true));
        int x = 10;
        items.addElement(TextFieldElementFactory.createStringElement("name",new Rectangle2D.Float(x, 0, 160, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","name"));
        x += 160;
        items.addElement(TextFieldElementFactory.createStringElement("date",new Rectangle2D.Float(x, 0, 60, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","date"));
        x += 60;
        items.addElement(TextFieldElementFactory.createStringElement("plate",new Rectangle2D.Float(x, 0, 80, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","plate"));
        x += 80;
        items.addElement(TextFieldElementFactory.createStringElement("type",new Rectangle2D.Float(x, 0, 80, 15),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","type"));
        x += 80;
        for(int i = 0; i < checkItems.length; i++) {
            items.addElement(TextFieldElementFactory.createStringElement("check"+i,new Rectangle2D.Float(x, 0, 20, 15),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"","check"+i));
            x += 20;
        }
        return items;
    }
    
    protected ReportHeader createReportHeader() {
        
        ReportHeader reportHeader = new ReportHeader();
        
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 25), false, true));
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, getReportWidth(), 25),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("truck.checklist.report")));

        int height = 43;

        height = writeParametersTitle(messages.getText("query.mileage.parameters"),reportHeader,height,10,160);
        height = writeParameter(messages.getText("query.mileage.from")+":",((ReportCheckListForm)form).getFrom(messages),reportHeader,height,10,80,90,80);
        height = writeParameter(messages.getText("query.mileage.to")+":",((ReportCheckListForm)form).getTo(messages),reportHeader,height,10,80,90,80);

        height = writeParametersTitle(messages.getText("generic.fleet.divgeo"),reportHeader,height,10,160); 
        height = writeParameter(divGeoLabels[0]+":",divGeoValues[0],reportHeader,height,10,80,90,80);
        height = writeParameter(divGeoLabels[1]+":",divGeoValues[1],reportHeader,height,10,80,90,80);

        int maxHeight = height;
        
        height = 43;
        String[] divOrgs = sys.getUserSessionData().getFilter().getLevelLabels();
        for(int i = 0; i < divOrgs.length; i++) {
            if(!Util.empty(divOrgs[i])) {
                height = writeParametersTitle(messages.getText(divOrgs[i]),reportHeader,height,250,240);
                for(int j = 0; j < 3; j++) {
                    if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 1 && j== 0) && 
                            !(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 2 && j== 0)) {
                        if(!Util.empty(divOrgLabels[i][j])) {
                            height = writeParameter(divOrgLabels[i][j]+":",divOrgValues[i][j],reportHeader,height,250,80,330,160);
                        }
                    }
                }
            }
        }
        
        height = Math.max(maxHeight,height);

        label = divOrgLabels[0][0];
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null,FourLevelColor, null,
                    new Rectangle2D.Float(10, height, getReportWidth(), 18), false, true));
        int x = 10;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 160, 18),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(label)));
        x += 160;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 60, 18),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.messages.date")));
        x += 60;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.registrationNumber")));
        x += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, 18),
                Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("generic.vehicle.vehicledata.vehicletype")));
        x += 80;
        
        for(int i = 0; i < checkItems.length; i++) {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 20, 18),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,""+(i+1)));
            x += 20;
        }
        
        return reportHeader;
    }


    protected ReportFooter createReportFooter() {
        
        ReportFooter reportFooter = new ReportFooter();
        reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, null,
                new Rectangle2D.Float(10, 18, getReportWidth(), 18), false, true));
        
        reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor, null,
                new Rectangle2D.Float(10, 10, getReportWidth(), 20), false, true));
        reportFooter.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 160, 20),
                Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,messages.getText("generic.messages.total")));
//        reportFooter.addElement(NumberFieldElementFactory.createNumberElement("totalchecks",new Rectangle2D.Float(getReportWidth()-60, 10, 60, 20),
//                Color.WHITE,ElementAlignment.RIGHT,ElementAlignment.MIDDLE,STYLE_TOTAL_FONT,"", formatNoDecimals,"totalchecks"));


        
        // Referencias:
              
        for(int i = 0; i < checkItems.length; i++) {
            Color c = (i % 2 == 0) ? gray1 : gray2;
            
            reportFooter.addElement(StaticShapeElementFactory.createRectangleShapeElement
            		(null, c, null, 
                    new Rectangle2D.Float(10, 58+(i*18), 600, 18), false, true));
            
              reportFooter.addElement(LabelElementFactory.createLabelElement(
                		null,new Rectangle2D.Float(10, 58+(i*18),  200, 18),
                        Color.BLACK,ElementAlignment.LEFT,ElementAlignment.
                        MIDDLE,STYLE_SIMPLE_BOLD_FONT,
                        ""+(i+1)+". "+ messages.getText(checkItems[i].getText())));
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
        
        TextFormatExpression textExp2;
        CreateHyperLinksFunction link2;
        if(((ReportCheckListForm)form).getOutput().equals("HTML")) {
            for(int i = 1; i <= pages; i++) {
                if(i != pageNumber) { 
                    textExp2 = new TextFormatExpression();
                    textExp2.setName("URLCreateExpression"+i);                                      
                    textExp2.setPattern("./layout.jsp?refer=/vehicle/checkListReportResult.jsp?page="+i+((ReportCheckListForm)form).getLink());
                    
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
