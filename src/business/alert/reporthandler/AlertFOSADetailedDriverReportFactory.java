package business.alert.reporthandler;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import javax.swing.table.TableModel;

import org.jfree.report.ElementAlignment;
import org.jfree.report.ItemBand;
import org.jfree.report.ReportHeader;
import org.jfree.report.elementfactory.LabelElementFactory;
import org.jfree.report.elementfactory.StaticShapeElementFactory;
import org.jfree.report.elementfactory.TextFieldElementFactory;
import org.jfree.report.function.ElementColorFunction;
import org.jfree.report.function.ExpressionCollection;

import business.alert.access.AlertSystemAccess;

import communication.language.Text;
import communication.util.Filter;

public class AlertFOSADetailedDriverReportFactory extends AlertReportFactory implements Serializable {

	private int data = 0;
	private int i = 0;
	private int count = 0;
	private int last = 0;
	private int pages = 0;
	private int pageNumber = 0;
	private AlertSystemAccess sys = null;
	private Text messages;
	protected String[] labels;
	private boolean[] expired;


    //private ActionForm form; 
	
	/**
	 * 
	 */
    public AlertFOSADetailedDriverReportFactory(String name, TableModel model,AlertDivisionForm form, 
    		AlertSystemAccess sys, Text messages, int pages, int pageNumber, int count, boolean[] expired) {        
    	super(name, model, form);       
        this.data = Filter.CORPORATION;       
        this.messages = messages;
        this.count = count;
        this.pages = pages;
        this.pageNumber = pageNumber;
        this.sys = sys;
        labels = sys.getUserSessionData().getFilter().getDivOrgLabels();
        parseDivisionValues(sys,messages);
        last = 330;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 1 && j== 0) && 
                        !(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 2 && j== 0)) {
                    if(!this.empty(labels[i*3+j])) {
                        last += 80;
                    }
                }
            }
        }
        last += 720;
        this.expired = expired;
    }

    public int getWidth() {
        if(sys.getUserSessionData().isForest()) {
            return last;
        }
        return last;
    }
    
    public int getReportWidth() {
        if(sys.getUserSessionData().isForest()) {
            return last;
        }
        return last;
    }

    /**
     * 
     */
    protected ReportHeader createReportHeader() {        
		ReportHeader reportHeader = new ReportHeader();
		int height = 0;
		int x = 0;
		int heightHeader = 30;
		
		Color fontColor = Color.WHITE;
		reportHeader.addElement(StaticShapeElementFactory.createRectangleShapeElement(null, FirstLevelColor,null, 
				new Rectangle2D.Float(x, height, getReportWidth(), heightHeader),false, true));
        
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, heightHeader), fontColor,
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.firstname")));
        x += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, heightHeader), fontColor,
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.lastname")));
        x += 80;
/*        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, heightHeader), fontColor, 
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.birthdate")));
        x += 80;*/
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, heightHeader), fontColor,
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("generic.driver.driverdata.identitydocument")));
        
        x += 80;
        for(int i = 0; i < 9; i++) {
            if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 3) && 
                    !(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 6)) {
                if(!this.empty(labels[i])) {
                    reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, heightHeader),
                    		fontColor,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText(labels[i])));
                    x += 80;
                }
            }
        }
        // x += 80;
/*        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, heightHeader), fontColor, 
                ElementAlignment.LEFT,ElementAlignment.MIDDLE, STYLE_SIMPLE_BOLD_FONT, messages.getText("driver.query.licenseexpiry")));
        x += 80;*/
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, heightHeader),
        		fontColor,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.aptitudtest.query.lasttest")));
        x += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, heightHeader),
        		fontColor,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.aptitudtest.query.isapt")));
        x += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, heightHeader),
        		fontColor,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.aptitudtest.expiry")));
        x += 80;        
/*        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, heightHeader),
        		fontColor,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.activities.count")));
        x += 80;*/
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, heightHeader),
        		fontColor,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("activity.query.activityexpirybtw")));
        x += 80;
        reportHeader.addElement(LabelElementFactory.createLabelElement(null,new Rectangle2D.Float(x, height, 80, heightHeader),
        		fontColor,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_BOLD_FONT,messages.getText("driver.activities.lastec")));
		return reportHeader;
	}

    /**
     * 
     */
	protected ItemBand createItemBand() {
		ItemBand items = new ItemBand();
		int x = 0;
		
		Color fontColor = null;		
		
        items.addElement(StaticShapeElementFactory.createRectangleShapeElement(
        		"Variance Field", 
        		null, null, 
        		new Rectangle2D.Float(x, 0,getReportWidth(), 15), false, true));    
        
        items.addElement(TextFieldElementFactory.createStringElement("Variance Field 2", 
        		new Rectangle2D.Float(x, 0, 80, 15), null,
                ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "name"));
        x += 80;
        items.addElement(TextFieldElementFactory.createStringElement("Variance Field 2", 
        		new Rectangle2D.Float(x, 0, 80, 15), fontColor,
                ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "lastname"));
        x += 80;        
/*        items.addElement(TextFieldElementFactory.createStringElement("Variance Field 2",
        		new Rectangle2D.Float(x, 0, 80, 15), fontColor,
                ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "birthdate"));
        x += 80;*/
        items.addElement(TextFieldElementFactory.createStringElement("Variance Field 2",
        		new Rectangle2D.Float(x, 0, 80, 15), fontColor,
                ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "corporationid"));     

        x += 80;
        for(int i = 0; i < 3; i++) {
           // for(int j = 2; j >= 0; j--) {
        	 for(int j = 0; j < 3; j++) {
                if(!(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 1 && j== 0) && 
                        !(sys.getUserSessionData().getFilter().getCorporationId().intValue() == 1 && i == 2 && j== 0)) {
                    if(!this.empty(labels[i*3+j])) {
                        items.addElement(TextFieldElementFactory.createStringElement("Variance Field 2",
                                new Rectangle2D.Float(x, 0, 80, 15),fontColor,ElementAlignment.LEFT,
                                ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"-","divorg"+String.valueOf(i+1)+String.valueOf(j+1)));
                        x += 80;
                    }
                }
            }
        }
/*        //x += 80;
        items.addElement(TextFieldElementFactory.createStringElement("Variance Field 2", new Rectangle2D.Float(x, 0, 80, 15),
        		fontColor, ElementAlignment.LEFT, ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT, "-", "licenseExpiry"));
        x += 80;*/
        items.addElement(TextFieldElementFactory.createStringElement("Variance Field 2",new Rectangle2D.Float(x, 0, 80, 15),
        		fontColor,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","test"));
        x += 80;
        items.addElement(TextFieldElementFactory.createStringElement("Variance Field 2",new Rectangle2D.Float(x, 0, 80, 15),
        		fontColor,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","apt"));
        x += 80;
        items.addElement(TextFieldElementFactory.createStringElement("Variance Field 2",new Rectangle2D.Float(x, 0, 80, 15),
        		fontColor,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","expiry"));
        x += 80;
 /*       items.addElement(TextFieldElementFactory.createStringElement("Variance Field 2",new Rectangle2D.Float(x, 0, 80, 15),
        		fontColor,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","count"));
        x += 80;*/
        items.addElement(TextFieldElementFactory.createStringElement("Variance Field 2",new Rectangle2D.Float(x, 0, 80, 15),
        		fontColor,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","last"));
        x += 80;
        items.addElement(TextFieldElementFactory.createStringElement("Variance Field 2",new Rectangle2D.Float(x, 0, 80, 15),
        		fontColor,ElementAlignment.LEFT,ElementAlignment.MIDDLE,STYLE_SIMPLE_FONT,"---","lastec"));
		return items;
	}
	
	/**
	 * 
	 */
	protected ExpressionCollection createFunctions() {
        ExpressionCollection functions = super.createFunctions();
       
        final ElementColorFunction color = new ElementColorFunction();
        color.setName("changeColor");
        color.setElement("Variance Field");
        color.setField("color");
        color.setColorTrue(Color.WHITE);
        color.setColorFalse(DCGray2);
        functions.add(color);
        
        final ElementColorFunction fontColor = new ElementColorFunction();		
        fontColor.setName("changeFontColor");
        fontColor.setElement("Variance Field 2");
        fontColor.setField("expired");
        fontColor.setColorTrue(Color.RED);
		fontColor.setColorFalse(Color.BLACK);
        functions.add(fontColor);
        
        return functions;
	}

	/**
	 * @return the data
	 */
	public int getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(int data) {
		this.data = data;
	}

	/**
	 * @return the i
	 */
	public int getI() {
		return i;
	}

	/**
	 * @param i the i to set
	 */
	public void setI(int i) {
		this.i = i;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the last
	 */
	public int getLast() {
		return last;
	}

	/**
	 * @param last the last to set
	 */
	public void setLast(int last) {
		this.last = last;
	}

	/**
	 * @return the pages
	 */
	public int getPages() {
		return pages;
	}

	/**
	 * @param pages the pages to set
	 */
	public void setPages(int pages) {
		this.pages = pages;
	}

	/**
	 * @return the pageNumber
	 */
	public int getPageNumber() {
		return pageNumber;
	}

	/**
	 * @param pageNumber the pageNumber to set
	 */
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	


	/**
	 * @return the messages
	 */
	public Text getMessages() {
		return messages;
	}

	/**
	 * @param messages the messages to set
	 */
	public void setMessages(Text messages) {
		this.messages = messages;
	}

	/**
	 * @return the labels
	 */
	public String[] getLabels() {
		return labels;
	}

	/**
	 * @param labels the labels to set
	 */
	public void setLabels(String[] labels) {
		this.labels = labels;
	}

	/**
	 * @return the expired
	 */
	public boolean[] getExpired() {
		return expired;
	}

	/**
	 * @param expired the expired to set
	 */
	public void setExpired(boolean[] expired) {
		this.expired = expired;
	}	
	
	
}