/**
 * Created on 10-jul-2006
 * CEPA
 * DataCenter 5
 */
package business.alert.reporthandler;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.table.TableModel;

import org.jfree.report.Element;
import org.jfree.report.ElementAlignment;
import org.jfree.report.Group;
import org.jfree.report.GroupFooter;
import org.jfree.report.GroupHeader;
import org.jfree.report.GroupList;
import org.jfree.report.ItemBand;
import org.jfree.report.JFreeReport;
import org.jfree.report.PageDefinition;
import org.jfree.report.PageFooter;
import org.jfree.report.PageHeader;
import org.jfree.report.ReportFooter;
import org.jfree.report.ReportHeader;
import org.jfree.report.SimplePageDefinition;
import org.jfree.report.elementfactory.LabelElementFactory;
import org.jfree.report.elementfactory.StaticShapeElementFactory;
import org.jfree.report.function.ExpressionCollection;
import org.jfree.report.style.FontDefinition;
import org.jfree.report.util.PageFormatFactory;

import business.SystemAccess;
import business.alert.access.AlertSystemAccess;

import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.ItemData;
import communication.language.Text;
import communication.util.Filter;

public abstract class AlertReportFactory implements Serializable{
    
	protected Text messages;
    protected String name;
    protected TableModel model;
    protected AlertDivisionForm form;
    
    protected String[][] divOrgLabels = new String[3][3];
    protected String[][] divOrgValues = new String[3][3];
    
    protected String[] divGeoLabels = new String[3];
    protected String[] divGeoValues = new String[3];
    
    protected int count = 0;

    protected FontDefinition fontDefinitionSimple = new FontDefinition ("SansSerif",10,false,false,false,false);
    protected FontDefinition fontDefinitionBold = new FontDefinition ("SansSerif",12,true,false,false,false);
    private int data;
    
    protected NumberFormat formatNoDecimals;
    protected NumberFormat formatOneDecimals;
    protected NumberFormat formatTwoDecimals;
    protected NumberFormat formatFourDecimals;
    
    protected static final float PAGE_MARGIN_TOP = 36.0f;
    protected static final float PAGE_MARGIN_BOTTOM = 36.0f;
    protected static final float PAGE_MARGIN_LEFT = 36.0f;
    protected static final float PAGE_MARGIN_RIGHT = 36.0f;
    //new Colors agree the CEPA DC5 standard
    public final static Color gray1 = new Color(233, 233, 233);
    public final static Color gray2 = new Color(247, 247, 247);
    public final static Color gray3 = new Color(250, 250, 250);
    
    public final static Color DCGray1 = new Color(233, 233, 233);//escuro
    public final static Color DCGray2 = new Color(247, 247, 247);//claro
    public final static Color DCGray3 = new Color(199, 199, 199);//mas claro
    public final static Color DCGray4 = new Color(125, 125, 125);//escuro
    public final static Color DCGray5 = new Color(130, 130, 130);
    public final static Color DCGray6 = new Color(150, 150, 150);
    public final static Color DCGray7 = new Color(225, 225, 225);//escuro
    
    //colors for event's reports
    public final static Color FirstLevelColor = new Color(90, 90, 90);
    public final static Color SecondLevelColor = new Color(114, 114, 114);
    public final static Color ThirtdLevelColor = new Color(182, 182, 182);
    public final static Color FourLevelColor = new Color(210, 210, 210);
    public final static Color FiveLevelColor = new Color(240, 240, 240);

    public final static Color SixLevelColor = new Color(233, 233, 233);
    public final static Color SevenLevelColor = new Color(233, 233, 233);
    
    public final static Color[] groupsHeaderColors = {FirstLevelColor,ThirtdLevelColor,FourLevelColor,FiveLevelColor};
    //public final static Color[] groupsHeaderColors = {DCGray4,DCGray6,DCGray3,DCGray4,DCGray6};
   
    /* Nuevos Colores para el Reporte */
    public final static Color DarkBlue = new Color(55,100,146); // azul oscuro
    public final static Color RedColor = new Color(139,0,0);
    /* Fin Nuevos Colores */
    
    protected static final FontDefinition STYLE_TITLE= new FontDefinition("SansSerif",13,true,false,false,false);
    protected static final FontDefinition STYLE_SIMPLE_FONT= new FontDefinition("SansSerif",8);
    protected static final FontDefinition STYLE_SMALL_BOLD_FONT= new FontDefinition("SansSerif",6,true,false,false,false);
    protected static final FontDefinition STYLE_SIMPLE_BOLD_FONT= new FontDefinition("SansSerif",8,true,false,false,false);
    protected static final FontDefinition STYLE_SUBTOTAL_BOLD_FONT= new FontDefinition("SansSerif",9,true,true,false,false);
    protected static final FontDefinition STYLE_TOTAL_FONT= new FontDefinition("SansSerif",12,true,false,false,false);
    protected static final FontDefinition STYLE_TITLE_PARAM_FONT= new FontDefinition("SansSerif",9,true,false,false,false);

    /**
     * Necesitamos el systemAccess en principio para poder cargar 
     * solo el logo que nos interesa
     * y no todos los logos de las compañias.
     */
    private SystemAccess sys = null;
    
    /**
     * Constructor que recibe tambien el SystemAccess para poder
     * acceder a los datos del usuario, para obtener el id de la Corporation.
     * @param name
     * @param model
     * @param form
     * @param sys
     */
    public AlertReportFactory(String name, TableModel model, AlertDivisionForm form, SystemAccess sys){
    	this(name,model,form);
    	this.sys = sys;    	
    }
    
    public AlertReportFactory(String name, TableModel model, AlertDivisionForm form) {
        this(name,model,form,Filter.USER);
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        symbols.setGroupingSeparator(',');
        formatNoDecimals = new DecimalFormat("#,###",symbols);
        formatOneDecimals = new DecimalFormat("#,###.#",symbols);
        formatTwoDecimals = new DecimalFormat("#,###.##",symbols);
        formatFourDecimals = new DecimalFormat("#,###.####",symbols);
    }

    public AlertReportFactory(String name, TableModel model, AlertDivisionForm form,int data) {
        this.name = name;
        this.model = model;
        this.form = form;
        this.data = data;
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        symbols.setGroupingSeparator(',');
        formatNoDecimals = new DecimalFormat("#,###",symbols);
        formatOneDecimals = new DecimalFormat("#,###.#",symbols);
        formatTwoDecimals = new DecimalFormat("#,###.##",symbols);
        formatFourDecimals = new DecimalFormat("#,###.####",symbols);
    }

    public JFreeReport createReport() {
        final JFreeReport report = new JFreeReport();
        report.setName(name);
        report.setPageDefinition(getPageDefinition());
        report.setGroups(createGroups());
        report.setReportHeader(createReportHeader());
        report.setReportFooter(createReportFooter());
        report.setItemBand(createItemBand());
        report.setExpressions(createFunctions());
        report.setPageHeader(createPageHeader());
        report.setPageFooter(createPageFooter());
        report.setData(model);       
       
        return report;
    }

    protected PageDefinition getPageDefinition() {
        final PageFormatFactory pff = PageFormatFactory.getInstance();
        final Paper paper = pff.createPaper(getWidth(),getHeight());
        pff.setBorders(paper, PAGE_MARGIN_TOP, PAGE_MARGIN_LEFT, PAGE_MARGIN_BOTTOM, PAGE_MARGIN_RIGHT);
        final PageFormat format = pff.createPageFormat(paper, PageFormat.PORTRAIT);
        return new SimplePageDefinition(format);
    }
    protected abstract int getReportWidth();
    
    
    
    protected int getWidth() {
        return PageFormatFactory.A4[0];
    }

    protected int getHeight() {
        return PageFormatFactory.A4[1];
    }

    protected ReportHeader createReportHeader() {
        ReportHeader reportHeader = new ReportHeader();
        return reportHeader;
    }

    private PageHeader createPageHeader() {
        PageHeader pageHeader = new PageHeader();
        return pageHeader;
    }

    protected GroupList createGroups() {
        GroupList grouplist = new GroupList();

        Group group = new Group();
        group.setName("dummy");
        
        GroupHeader header = new GroupHeader();
        header.setName("dummyGroupHeader");
        group.setHeader(header);
        
        GroupFooter footer = new GroupFooter();
        footer.setName("dummyGroupFooter");
        group.setFooter(footer);
        
        grouplist.add(group);
        
        return grouplist;
    }

    protected ItemBand createItemBand() {
        ItemBand items = new ItemBand();
        return items;
    }

    private PageFooter createPageFooter() {
        PageFooter pageFooter = new PageFooter();
        return pageFooter;
    }

    protected ReportFooter createReportFooter() {
        ReportFooter reportFooter = new ReportFooter();
        return reportFooter;
    }

    protected ExpressionCollection createFunctions() {
        ExpressionCollection functions = new ExpressionCollection();
        return functions;
    }

    protected void parseDivisionValues(AlertSystemAccess sys, Text messages) {
    	AlertDivisionForm reportForm = (AlertDivisionForm)form;
        String[] labels = sys.getUserSessionData().getFilter().getDivOrgLabels();
        for(int i = 0; i < sys.getUserSessionData().getDivOrgCount(); i++) {
            for(int j = 0; j < sys.getUserSessionData().getFilter().getDivOrgLevel(i); j++) {
                if(!this.empty(labels[i*3+j])) {
                    divOrgLabels[i][j] = messages.getText(labels[i*3+j]);
                    String[] values = reportForm.getDivOrgValues(i+1,j+1);
                    if((values == null) || (values.length == 0) || (values.length == 1 && values[0].equals("0"))){
                        divOrgValues[i][j] = messages.getText("generic.messages.all.pl");
                    }else {
                        divOrgValues[i][j] = "";
                        for(int k = 0; k < values.length; k++) {
                            if(!values[k].equals("0")) {
                                ItemData item = sys.getUserSessionData().getFilter().getDivOrgItem(i,j+1,new Integer(values[k]),data);
                                divOrgValues[i][j] += (k+1 == values.length) ? item.getName() : item.getName()+",";
                            }
                        }
                    }
                }
            }
        }
        
        labels = sys.getUserSessionData().getFilter().getDivGeoLabels();
        for(int i = 1; i <= sys.getUserSessionData().getFilter().getGeoLevelCount(); i++) {
            if(!this.empty(labels[i-1])) {
                divGeoLabels[i-1] = messages.getText(labels[i-1]);
                String[] values = reportForm.getDivGeoValues(i);
                if((values == null) || (values.length == 0) || (values.length == 1 && values[0].equals("0"))){
                    divGeoValues[i-1] = messages.getText("generic.messages.all.pl");
                }else {
                    divGeoValues[i-1] = "";
                    for(int k = 0; k < values.length; k++) {
                        if(!values[k].equals("0")) {
                            GeoItemData item = sys.getUserSessionData().getFilter().getDivGeoItem(i,new Integer(values[k]),data);
                            if(i == sys.getUserSessionData().getFilter().getGeoLevelCount()) {
                                divGeoValues[i-1] += (k+1 == values.length) ?  messages.getText(item.getName()) : messages.getText(item.getName())+",";
                            }else {
                                divGeoValues[i-1]  += (k+1 == values.length) ? item.getName() : item.getName()+",";
                            }
                        }
                    }
                }
            }
        }
    }

    protected int writeParameter(String parameterName,String parameterValue,ReportHeader reportHeader,int height,int xLabel,int wLabel,int xValue,int wValue) {
        if(!this.empty(parameterValue)) {
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(xLabel, height, wLabel, 18),
                    Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,parameterName));
            reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(xValue, height, wValue, 18),
                    Color.BLACK,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,parameterValue));
            return height+20;
        }else if(!this.empty(parameterName)){ 
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
    
    /* Divide un texto en varias lineas, separando en algun blanco las lineas */
    protected ArrayList<String> cutText(String text, int largo)
    {
    	ArrayList<String> textos = new ArrayList<String>();
    	String sub;
    	int inicioString = 0; 
    	int finString = largo;
    	int ultimoblanco;
    	
    	
    	if (text.length() < largo)
    	{
    		textos.add(text);
    	}
    	else
    	{
    		while (inicioString <= text.length())
    		{
    			
    			if (finString >= text.length())
    			{ 
    				finString = text.length();
    				sub = text.substring(inicioString,finString);
    				textos.add(sub);
    				inicioString = text.length()+1;
    			}
    			else
    			{
    				sub = text.substring(inicioString,finString);
    				if (sub.endsWith(" "))
    				{
    					textos.add(sub);
    					inicioString += largo;
    					finString = inicioString + largo;    					
    				}
    				else	
    				{
    					ultimoblanco = sub.lastIndexOf(" ");
    					sub = text.substring(inicioString, inicioString+ultimoblanco);
    					textos.add(sub);
    					inicioString += ultimoblanco + 1;
    					finString = inicioString + largo;    					
    				}
    			}
    		}
    	}    			
    	return textos; 
    }    
    
    public Group createGroup(Object[] fields , Element[] footerTotals,Element[] headerElements, String groupName,Text messages,String totalName){
        return createGroup(fields ,footerTotals,headerElements,groupName,messages,totalName,160,10,true);
    }
    
    public Group createGroup(Object[] fields, Element[] footerTotals, Element[] headerElements, String groupName, Text messages, String totalName, boolean total) {
        return createGroup(fields ,footerTotals,headerElements,groupName,messages,totalName,160,10,total);
    }
    
    public Group createGroup(Object[] fields , Element[] footerTotals,Element[] headerElements, String groupName,Text messages,String totalName,int totalW,int y){
        return createGroup(fields ,footerTotals,headerElements,groupName,messages,totalName,totalW,y,true);
    }
    
    public Group createGroup(Object[] fields , Element[] footerTotals,Element[] headerElements, String groupName,Text messages,String totalName,int totalW,int y,boolean totals){
    	
    	Group group = new Group();
    	group.setName(groupName);
    	int level;
    	for(level=0;level<fields.length;level++){
    		group.addField((String)fields[level]);
    	}
        GroupHeader header = new GroupHeader();
        header.setName("header");
        for(int index = 0; index < headerElements.length;index++) {
        	header.addElement(headerElements[index]);
        }
        GroupFooter footer = new GroupFooter();
        if(totals) {
            footer.setName("footer");
            footer.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, groupsHeaderColors[level-1], null,
                    new Rectangle2D.Float(10, y, getReportWidth(), 15), false, true));
            footer.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(10, y, totalW, 15),Color.WHITE,
                    ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SUBTOTAL_BOLD_FONT,messages.getText("generic.messages.total")+" "+totalName));
            for(int index = 0; index < footerTotals.length;index++) {
                footer.addElement(footerTotals[index]);
            }
        }
        group.setHeader(header);
        group.setFooter(footer);
        
        return group;
    }
    
    protected boolean empty(String label) {
        return label == null || label.trim().length() == 0;
    }

	/**
	 * @return the sys
	 */
	public SystemAccess getSys() {
		return sys;
	}

	/**
	 * @param sys the sys to set
	 */
	public void setSys(SystemAccess sys) {
		this.sys = sys;
	}
    
    
}
