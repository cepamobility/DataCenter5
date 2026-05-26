/**
 * Created on 10-jul-2006
 * CEPA
 * DataCenter 5
 */
package presentation.report.event;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
import java.util.LinkedList;

import javax.swing.table.TableModel;

import org.jfree.report.Element;
import org.jfree.report.ElementAlignment;
import org.jfree.report.Group;
import org.jfree.report.GroupList;
import org.jfree.report.ItemBand;
import org.jfree.report.ReportFooter;
import org.jfree.report.ReportHeader;
import org.jfree.report.elementfactory.LabelElementFactory;
import org.jfree.report.elementfactory.NumberFieldElementFactory;
import org.jfree.report.elementfactory.StaticShapeElementFactory;
import org.jfree.report.elementfactory.TextFieldElementFactory;
import org.jfree.report.function.ElementColorFunction;
import org.jfree.report.function.ExpressionCollection;
import org.jfree.report.function.ItemCountFunction;
import org.jfree.report.function.ItemSumFunction;

import presentation.report.ReportFactory;
import presentation.translator.web.event.EventReportForm;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.language.Text;


public class EventReportSeverityFactory extends ReportFactory {
	protected Text messages;

    private Integer periodFrom;
    private Integer yearFrom;
    
    private Integer periodTo;
    private Integer yearTo;

    private SystemAccess sys;
    private String[] levelNames;
    private int lastLevel;

    private String reportType;
    
    protected static final int YPERSONA_LDATA =10;
    protected static final int YLOCATION =10;
    protected static final int YDIVISION =300;


    public EventReportSeverityFactory(String name, TableModel model, EventReportForm form, SystemAccess sys, Text messages,Integer periodFrom,Integer yearFrom,Integer periodTo,Integer yearTo) {
        super(name, model, form);
        parseDivisionValues(sys,messages);
        this.messages = messages;
        this.periodFrom = periodFrom;
        this.yearFrom = yearFrom;
        this.periodTo = periodTo;
        this.yearTo = yearTo;
        this.sys = sys;
        reportType = form.getOutput();
    }
    protected int getReportWidth() {
        return 520;
    }
    
    protected String getOrderText(String order){
    	if (order.equals("0"))
    		return "divOrg1Name";
    	else if(order.equals("1"))
    	    return "divOrg2Name";
    	else if (order.equals("2"))
    		return "divOrg3Name";	
    	else if (order.equals("3")) 
    		return "divOrg4Name";
    	else return null;
    }
    
    protected String getOrderGroup(String order){
        if (order.equals("0"))
            return "divOrg1Id";
        else if(order.equals("1"))
            return "divOrg2Id";
        else if (order.equals("2"))
            return "divOrg3Id";   
        else if (order.equals("3")) 
            return "divOrg4Id";
        else return null;
    }

    protected String getOrder(int order){
    	
    	switch(order){
        	case 0:
        		return ((EventReportForm)form).getOrder1();
        	case 1:
        		return ((EventReportForm)form).getOrder2();
        	case 2:
        		return ((EventReportForm)form).getOrder3();
        	case 3:
        		return ((EventReportForm)form).getOrder4();
    	}
    	return null;
    }
    
    protected int lastGroup(){
    	int order=0;
    	int lastLevel=0;
    	while(order+1 < sys.getUserSessionData().getFilter().getPrincipalCount() && order<3 && Integer.parseInt(getOrder(order))!=4){
    		if (order < sys.getUserSessionData().getFilter().getPrincipalCount() && getOrder(order+1)!=null && Integer.parseInt(getOrder(order+1))!=4)
    			lastLevel+=1;
    		order+=1;
    	}
    	
    	return lastLevel;
    }
    
    protected GroupList createGroups() {
    	levelNames = sys.getUserSessionData().getFilter().getLevelNames(messages);
       // String[] divOrgs = sys.getUserSessionData().getFilter().getPrincipalLabels(); 
    	
    	lastLevel=lastGroup();
        GroupList grouplist = super.createGroups();
        int count = (12 - periodFrom.intValue() + 1) + ((yearTo.intValue() - yearFrom.intValue() - 1) * 12) + periodTo.intValue();
        int order=0;
        
        Collection fields = new LinkedList();
        
        //Se crea un grupo afuera porque es el del período
        Element[] footerTotals= new Element[count+1]; 
    	int x = 100;
        int w = (reportType.equals("XLS")) ? 40 : 38; 
        for(int index = 0; index <= count;index++) {
            String field = "totalType"+String.valueOf(index);
            footerTotals[index]=NumberFieldElementFactory.createNumberElement(field,new Rectangle2D.Float(x, 10, w, 15),Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"",formatNoDecimals, field);
            x += 40;
        }
        fields.add("type");
        Group group= createGroup(fields.toArray(), footerTotals,getGroupHeader(4),"type",messages,messages.getText("generic.data.event.pl"),90,10);
        grouplist.add(group);
        
        // Se crean los cortes de control seleccionados, hasta 4, si opción viene con valor -1 no se toma en cuenta
        while(order< lastLevel){
        	if (Integer.parseInt(getOrder(order+1))!=4){
        		footerTotals= new Element[count+1]; 
        		x = 100;
        		
        		for(int index = 0; index <= count;index++) {
        			String field = "totalGroup"+order+String.valueOf(index);
        			footerTotals[index]=NumberFieldElementFactory.createNumberElement(field,new Rectangle2D.Float(x, 10, w, 15),Color.WHITE,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"",formatNoDecimals, field);
        			x += 40;
        		}
        		fields.add(getOrderGroup(Integer.toString(order)));
                String s = (Integer.parseInt(getOrder(order)) == 3) ? "corporation.country" : levelNames[Integer.parseInt(getOrder(order))];
        		grouplist.add(createGroup(fields.toArray(), footerTotals,getGroupHeader(order),"Group"+order,messages,messages.getText(s),90,10));
        		
        	}
        	order+=1;
        }
        return grouplist;
     }
    
    protected Element[] getGroupHeader(int order){
    	
        int w = (reportType.equals("XLS")) ? 40 : 38; 

        if ((order==(lastLevel-3))&&(order>=0)){
    		Element[] header= new Element[3];
    		header[0]=StaticShapeElementFactory.createRectangleShapeElement(null, groupsHeaderColors[order+1], null,
                    new Rectangle2D.Float(10, 10, getReportWidth(), 15), false, true);
            if(!reportType.equals("XLS")) {
                header[1]=LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(12, 10, 88, 15),Color.WHITE,
                    ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,messages.getText(levelNames[Integer.parseInt(getOrder(order))]));
            }else {
                header[1]=LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 90, 15),Color.WHITE,
                        ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,messages.getText(levelNames[Integer.parseInt(getOrder(order))]));
            }
    		header[2]= TextFieldElementFactory.createStringElement("headerName",new Rectangle2D.Float(100, 10, 300, 15),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"", getOrderText(Integer.toString(order)));
    		return header;
    	}
    	if ((order==(lastLevel-2))&&(order>=0)){
    		Element[] header= new Element[3];
    		header[0]=StaticShapeElementFactory.createRectangleShapeElement(null, groupsHeaderColors[order+1], null,
                    new Rectangle2D.Float(10, 10, getReportWidth(), 15), false, true);
            if(!reportType.equals("XLS")) {
                header[1]=LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(12, 10, 88, 15),Color.WHITE,
                    ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(levelNames[Integer.parseInt(getOrder(order))]));
            }else {
                header[1]=LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 90, 15),Color.WHITE,
                        ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(levelNames[Integer.parseInt(getOrder(order))]));
            }
            header[2]=TextFieldElementFactory.createStringElement("headerName",new Rectangle2D.Float(100, 10, 300, 15),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"", getOrderText(Integer.toString(order)));
            return header;
    	}
    	if ((order==lastLevel-1)||(lastLevel<= 0)){
    		Collection headerCol = new LinkedList();
    		
    		if(lastLevel > 0){
    			headerCol.add(StaticShapeElementFactory.createRectangleShapeElement(null, groupsHeaderColors[order+1], null,
    					new Rectangle2D.Float(10, 10, getReportWidth(), 15), false, true));
                String s = (Integer.parseInt(getOrder(order)) == 3) ? "corporation.country" : levelNames[Integer.parseInt(getOrder(order))];
                if(!reportType.equals("XLS")) {
                    headerCol.add(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(12, 10, 88, 15),Color.WHITE,
    					ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(s)));
                }else {
                    headerCol.add(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 10, 90, 15),Color.WHITE,
                            ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(s)));
                }
    			headerCol.add(TextFieldElementFactory.createStringElement("headerName",new Rectangle2D.Float(100, 10, 300, 15),
    					Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"", getOrderText(Integer.toString(order))));
    		}else{
    			headerCol.add(StaticShapeElementFactory.createRectangleShapeElement(null, groupsHeaderColors[order-4], null,
                        new Rectangle2D.Float(10, 10, getReportWidth(), 15), false, true));
    			headerCol.add(TextFieldElementFactory.createStringElement("typeTitle",new Rectangle2D.Float(10, 10, 105, 15),
                        Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,fontDefinitionBold,"", "type"));
    		}
            int x = 100;
            int h = (reportType.equals("XLS")) ? 18 : 17; 
            for(int i = yearFrom.intValue(); i <= yearTo.intValue(); i++) {
                int first = (i == yearFrom.intValue()) ? periodFrom.intValue() : 1;
                int last = (i == yearTo.intValue()) ? periodTo.intValue() : 12;
                int x2 = x;
                for(int j = first; j <= last; j++) {
                    String month = messages.getText("generic.shortmonth."+j);
                    headerCol.add(StaticShapeElementFactory.createRectangleShapeElement(null,FourLevelColor, null,
                            new Rectangle2D.Float(x2, 44, w, 17), false, true));
                    headerCol.add(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x2, 44, w, h), 
                            Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SMALL_BOLD_FONT,month));
                    x2 += 40;
                }
                
               
                if(!reportType.equals("XLS")) {
                    headerCol.add(StaticShapeElementFactory.createRectangleShapeElement(null,DCGray6, null,
                        new Rectangle2D.Float(x, 26, x2 -x , 16), false, true));
                    headerCol.add(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, 26, x2 -x - 2, 16), 
                        Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(i)));
                }else {
                    headerCol.add(StaticShapeElementFactory.createRectangleShapeElement(null,DCGray6, null,
                            new Rectangle2D.Float(x, 25, x2 -x , 19), false, true));
                    headerCol.add(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, 25, x2 -x , 19), 
                            Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,String.valueOf(i)));
                }
                x += x2 -x;
            }
            headerCol.add(StaticShapeElementFactory.createRectangleShapeElement(null,DCGray6, null,
                    new Rectangle2D.Float(x, 44, w, 17), false, true));
            headerCol.add(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, 44, w, h), 
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SMALL_BOLD_FONT,messages.getText("generic.messages.total")));
            x += 40;
            
            
            int w2 = (!reportType.equals("XLS")) ? 88 : 90;
            headerCol.add(StaticShapeElementFactory.createRectangleShapeElement(null,FourLevelColor, null,
                    new Rectangle2D.Float(10, 44, w2, 17), false, true));
            if(lastLevel > 0){
            	headerCol.add(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 44, w2, 17), 
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(levelNames[Integer.parseInt(getOrder(order+1))])));
            }else{
                if(Integer.parseInt(getOrder(lastLevel)) == 3) {
                    headerCol.add(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 44, w2, 17), 
                            Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("corporation.country")));
                    
                }else {
                    headerCol.add(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 44, w2, 17), 
                        Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(levelNames[Integer.parseInt(getOrder(lastLevel))])));
                }
            }
            Element[] header= new Element[headerCol.size()];
            header=(Element[])headerCol.toArray(new Element[headerCol.size()]);
            return header;
    	}
        if (order==4){
    		Element[] header= new Element[2]; 
    		header[0]=StaticShapeElementFactory.createRectangleShapeElement(null, groupsHeaderColors[order-4], null,
                    new Rectangle2D.Float(10, 10, getReportWidth(), 15), false, true);
    		header[1]=TextFieldElementFactory.createStringElement("typeTitle",new Rectangle2D.Float(10, 10, 105, 15),
                    Color.WHITE,ElementAlignment.LEFT,ElementAlignment.MIDDLE,fontDefinitionBold,"", "type");
           return header;
    	}
    	return null;
    	
    }
    protected int getWidth() {
        return getReportWidth()+80;
    }   
        
    protected ItemBand createItemBand() {
        int w = (reportType.equals("XLS")) ? 40 : 38; 
        int y = (reportType.equals("XLS")) ? 0 : 1; 
        ItemBand items = super.createItemBand();
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement("Variance Field", null, null,new Rectangle2D.Float(10, 0, getReportWidth(), 15), false, true));
        items.addElement(TextFieldElementFactory.createStringElement(getOrderText(Integer.toString(lastLevel)),new Rectangle2D.Float(10, 0, 190, 15),Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"",getOrderText(Integer.toString(lastLevel))));
        int x = 200;
        for(int index = 0; index <= 8; index++) {
        	items.addElement(NumberFieldElementFactory.createNumberElement("total_"+index,new Rectangle2D.Float(x, 10, 40, 15),
                    Color.BLACK,ElementAlignment.CENTER,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,"",formatNoDecimals,"total_"+index));
            x += 40;
        }
        return items;
    }    


    protected ReportHeader createReportHeader() {
        EventReportForm reportForm = (EventReportForm)form;
        ReportHeader reportHeader = new ReportHeader();
        writeFilter(reportForm, reportHeader);
        return reportHeader;
    }

	protected int writeFilter(EventReportForm reportForm, ReportHeader reportHeader) {
		reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FourLevelColor, null,
                new Rectangle2D.Float(10, 0, getReportWidth(), 25), false, true));
        
		reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, 0, getReportWidth(), 25),
        		Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_TITLE,messages.getText("query.eventbyperiod.title")));
        
        int height = 25;
        int maxHeight=height;

        height = writeParametersTitle(messages.getText("query.eventbyperiod.period"),reportHeader,height,10,190);
        height = writeParameter(messages.getText("query.eventbyperiod.from")+":",reportForm.getFrom(messages),reportHeader,height,10,90,100,100);
        height = writeParameter(messages.getText("query.eventbyperiod.to")+":",reportForm.getTo(messages),reportHeader,height,10,90,100,100);

        height = writeParametersTitle(messages.getText("generic.driver.divgeo"),reportHeader,height,10,190); 
        for(int i = 0; i < 2; i++) {
            if(divGeoLabels[i]!=null)
        	height = writeParameter(divGeoLabels[i]+":",divGeoValues[i],reportHeader,height,10,90,100,100);
        }
        maxHeight=Math.max(maxHeight,height);

        height = 25;
       
        String[] divOrgs = sys.getUserSessionData().getFilter().getLevelLabels();
        for(int i = 0; i < divOrgs.length; i++) {
            if(!Util.empty(divOrgs[i])) {
                height = writeParametersTitle(messages.getText(divOrgs[i]),reportHeader,height,YDIVISION,240);
                for(int j = 0; j < 3; j++) {
                    if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 1 && j== 0) && 
                            !(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 2 && j== 0)) {
                        if(!Util.empty(divOrgLabels[i][j])) {
                            height = writeParameter(divOrgLabels[i][j]+":",divOrgValues[i][j],reportHeader,height,YDIVISION,120,YDIVISION+120,120);
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
    

    protected ExpressionCollection createFunctions() {
        ExpressionCollection functions = super.createFunctions();
       
        final ElementColorFunction color = new ElementColorFunction();
        color.setName("changeColor");
        color.setElement("Variance Field");
        color.setField("color");
        color.setColorTrue(Color.WHITE);
        color.setColorFalse(DCGray2);
        functions.add(color);
        
        return functions;
    }
}
