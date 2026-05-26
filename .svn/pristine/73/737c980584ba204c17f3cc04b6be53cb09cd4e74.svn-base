package presentation.translator.web.vehicle;

import java.util.Hashtable;

import presentation.translator.web.driver.DriverDivOrgForm;
import presentation.translator.web.util.Util;

import communication.administration.bookmark.tables.BookmarkData;
import communication.language.Text;

/**
 * @author jrodriguez
 */
public class ReportCheckListForm extends DriverDivOrgForm {

    private static final long serialVersionUID = 1L;
	
    private String frommonth;
    private String fromyear;
    private String tomonth;
    private String toyear;
    private String output;
    private String reportType;
    private String type; // Plataforma de Carga o Camion
    private String typeCheck; //Tipo Para DPA
    
    private String order1;
    private String order2;
    private String order3;

    /*
	 *  (non-Javadoc)
	 * @see presentation.translator.web.driver.DriverDivOrgForm#getLink()
	 * Genera un link con los parametros del form
	 */
	public String getLink() {
	        StringBuffer link = new StringBuffer("");
	        link.append((!Util.empty(frommonth)) ? "&fm="+frommonth : "");
	        link.append((!Util.empty(fromyear)) ? "&fy="+fromyear : "");
	        link.append((!Util.empty(output)) ? "&o="+output : "");
            link.append((!Util.empty(tomonth)) ? "&tm="+tomonth : "");
            link.append((!Util.empty(toyear)) ? "&ty="+toyear : "");
            link.append((!Util.empty(reportType)) ? "&rt="+reportType : "");
            link.append((!Util.empty(type)) ? "&type="+type : "");           
            link.append((!Util.empty(order1)) ? "&o1="+order1 : "");
            link.append((!Util.empty(order2)) ? "&o2="+order2 : "");
            link.append((!Util.empty(order3)) ? "&o3="+order3 : "");
	        link.append(super.getLink());
	        return link.toString();
	}
	
    /**
     * @param divOrg11
     * @param divOrg12
     * @param divOrg13
     * @param divOrg21
     * @param divOrg22
     * @param divOrg23
     * @param divOrg31
     * @param divOrg32
     * @param divOrg33
     * @param country1
     * @param country2
     * @param country3
     */
    public ReportCheckListForm(String divOrg11, String divOrg12, String divOrg13, 
    		String divOrg21, String divOrg22, String divOrg23, String divOrg31, 
    		String divOrg32, String divOrg33, String country1, 
    		String country2, String country3) {
        super(divOrg11, divOrg12, divOrg13, divOrg21, divOrg22, 
        		divOrg23, divOrg31, divOrg32, divOrg33, country1, country2, country3);
    }

    
    public ReportCheckListForm(BookmarkData bookmark) {
        super(bookmark);
        Hashtable parameters = bookmark.getParameters();
        if(parameters.containsKey("reportType")) {
            reportType = (String)parameters.get("reportType");
        }
        if(parameters.containsKey("type")) {
            type = (String)parameters.get("type");
        }
        if(parameters.containsKey("order1")) {
            order1 = (String)parameters.get("order1");
        }
        if(parameters.containsKey("order2")) {
            order2 = (String)parameters.get("order2");
        }
        if(parameters.containsKey("order3")) {
            order3 = (String)parameters.get("order3");
        }
        if(parameters.containsKey("output")) {
            output = (String)parameters.get("output");
        }
        if(parameters.containsKey("frommonth")) {
            frommonth = (String)parameters.get("frommonth");
        }      
        if(parameters.containsKey("fromyear")) {
            fromyear = (String)parameters.get("fromyear");
        }
        if(parameters.containsKey("tomonth")) {
            tomonth = (String)parameters.get("tomonth");
        }
        if(parameters.containsKey("toyear")) {
            toyear = (String)parameters.get("toyear");
        }
    }
    
    public void reset(){
    	frommonth = ""; 
        tomonth = ""; 
        fromyear = "";
        toyear = "";
        reportType="resumed";
        type="vehicle";
        
    }
    
    public ReportCheckListForm() {
        frommonth = ""; 
        tomonth = ""; 
        fromyear = "";
        toyear = "";
    }

    public String getFrommonth() {
        return frommonth;
    }

    public String getFromyear() {
        return fromyear;
    }

    public String getTomonth() {
        return tomonth;
    }

    public String getToyear() {
        return toyear;
    }

    public void setFrommonth(String frommonth) {
        this.frommonth = frommonth;
    }

    public void setFromyear(String fromyear) {
        this.fromyear = fromyear;
    }

    public void setTomonth(String tomonth) {
        this.tomonth = tomonth;
    }

    public void setToyear(String toyear) {
        this.toyear = toyear;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getFrom(Text messages) {
        if(Util.empty(frommonth) || Util.empty(fromyear)) {
            return "---";
        }else {
            if(frommonth.startsWith("Q")) {
                return frommonth+" / "+fromyear;
            }
            return messages.getText("generic.month."+frommonth)+" / "+fromyear;
        }
    }

    public String getTo(Text messages) {
        if(Util.empty(tomonth) || Util.empty(toyear)) {
            return "---";
        }else {
            if(tomonth.startsWith("Q")) {
                return tomonth+" / "+toyear;
            }
            return messages.getText("generic.month."+tomonth)+" / "+toyear;
        }
    }

    public String getOrder1() {
        return order1;
    }

    public String getOrder2() {
        return order2;
    }

    public String getOrder3() {
        return order3;
    }

    public void setOrder1(String order1) {
        this.order1 = order1;
    }

    public void setOrder2(String order2) {
        this.order2 = order2;
    }

    public void setOrder3(String order3) {
        this.order3 = order3;
    }

	/**
	 * @return Returns the reportType.
	 */
	public String getReportType() {
		return reportType;
	}

	/**
	 * @param reportType The reportType to set.
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	/**
	 * Devuelve el tipo de Vehiculo: Plataforma de Carga o Camion
	 * Opciones: plataform, truck.
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}

	/**	 * 
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}
	

    public String getTypeCheck() {
		return typeCheck;
	}

	public void setTypeCheck(String typeCheck) {
		this.typeCheck = typeCheck;
	}

	public BookmarkData getBookmark() {
        BookmarkData data = super.getBookmark(BookmarkData.CHECK_LIST_REPORT);
        if(!Util.empty(reportType)) {
            data.addParameter("reportType",reportType);
        }
        if(!Util.empty(type)) {
            data.addParameter("type",type);
        }
        if(!Util.empty(order1)) {
            data.addParameter("order1",order1);
        }
        if(!Util.empty(order2)) {
            data.addParameter("order2",order2);
        }
        if(!Util.empty(order3)) {
            data.addParameter("order3",order3);
        }
        if(!Util.empty(output)) {
            data.addParameter("output",output);
        }
        if(!Util.empty(frommonth)) {
            data.addParameter("frommonth",frommonth);
        }      
        if(!Util.empty(fromyear)) {
            data.addParameter("fromyear",fromyear);
        }
        if(!Util.empty(tomonth)) {
            data.addParameter("tomonth",tomonth);
        }
        if(!Util.empty(toyear)) {
            data.addParameter("toyear",toyear);
        }
        return data;
    }

	
    
    
}	    
