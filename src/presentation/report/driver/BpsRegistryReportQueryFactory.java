/**
 * Created on 22-jun-2007
 * CEPA
 * DataCenter 5
 */
package presentation.report.driver;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

import javax.swing.table.TableModel;

import org.apache.struts.action.ActionForm;
import org.jfree.report.ElementAlignment;
import org.jfree.report.ReportHeader;
import org.jfree.report.elementfactory.LabelElementFactory;
import org.jfree.report.elementfactory.StaticShapeElementFactory;
import org.jfree.report.function.CreateHyperLinksFunction;
import org.jfree.report.function.ElementColorFunction;
import org.jfree.report.function.ExpressionCollection;
import org.jfree.report.function.TextFormatExpression;

import presentation.translator.web.driver.DriverReportForm;
import presentation.translator.web.util.Util;

import business.SystemAccess;

import communication.driver.tables.DriverData;
import communication.language.Text;

public class BpsRegistryReportQueryFactory extends DriverReportFactory {

    public BpsRegistryReportQueryFactory(String name, TableModel model, ActionForm form, SystemAccess sys, Text messages, int count, int pages, int pageNumber) {
        super(name, model, form, sys, messages, count, pages, pageNumber);
    }

    protected int writeFilter(DriverReportForm reportForm, ReportHeader reportHeader,int start) {
        reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 25), false, true));
        
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, getReportWidth(), 25),
                Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("driver.query.bpsregistry.query")));
        
        int height = 25;
        int maxHeight=height;
        height = writeParametersTitle(messages.getText("generic.driver.personaldata"),reportHeader,height,10,160);
        
        height = writeParameter(messages.getText("generic.driver.driverdata.firstname")+":",reportForm.getName(),reportHeader,height,10,80,90,80);
        
        switch(Integer.parseInt(reportForm.getStatus())) {
            case DriverData.ACTIVE:
                height = writeParameter(messages.getText("generic.event.eventdata.status")+":",messages.getText("generic.messages.active"),reportHeader,height,10,80,90,80);
                break;
            case DriverData.EXCLUDED:
                height = writeParameter(messages.getText("generic.event.eventdata.status")+":",messages.getText("generic.messages.excluded"),reportHeader,height,10,80,90,80);
                break;
            case DriverData.ALL:
                height = writeParameter(messages.getText("generic.event.eventdata.status")+":",messages.getText("generic.messages.all.pl"),reportHeader,height,10,80,90,80);
                break;
        }
        
        height = writeParametersTitle(messages.getText("generic.driver.divgeo"),reportHeader,height,10,160); 
        height = writeParameter(divGeoLabels[0]+":",divGeoValues[0],reportHeader,height,10,80,90,80);
        height = writeParameter(divGeoLabels[1]+":",divGeoValues[1],reportHeader,height,10,80,90,80);

        maxHeight=Math.max(maxHeight,height);
        height = 25;
       
        String[] divOrgs = sys.getUserSessionData().getFilter().getLevelLabels();
        for(int i = 0; i < divOrgs.length; i++) {
            if(!Util.empty(divOrgs[i])) {
                height = writeParametersTitle(messages.getText(divOrgs[i]),reportHeader,height,250,160);
                for(int j = 0; j < 3; j++) {
                    if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 1 && j== 0) && 
                            !(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 2 && j== 0)) {
                        if(!Util.empty(divOrgLabels[i][j])) {
                            height = writeParameter(divOrgLabels[i][j]+":",divOrgValues[i][j],reportHeader,height,250,80,330,80);
                        }
                    }
                }
            }
        }

        maxHeight=Math.max(maxHeight,height);
        return maxHeight;
    }

    protected ExpressionCollection createFunctions() {
        ExpressionCollection functions = new ExpressionCollection();
        
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
        textExp.setField(0,"id");
        functions.add(textExp);

        CreateHyperLinksFunction link = new CreateHyperLinksFunction();
        link.setName("AddHRefs");
        link.setField("URLCreateExpression");
        link.setElement("URL-Field");
        functions.add(link);

        if(((DriverReportForm)form).getOutput().equals("HTML")) {
            for(int i = 1; i <= pages; i++) {
                if(i != pageNumber) {
                    TextFormatExpression textExp2 = new TextFormatExpression();
                    textExp2.setName("URLCreateExpression"+i);
                    textExp2.setPattern("./layout.jsp?refer=/driver/bpsRegistryReportPage.jsp?page="+i+((DriverReportForm)form).getLink());
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
