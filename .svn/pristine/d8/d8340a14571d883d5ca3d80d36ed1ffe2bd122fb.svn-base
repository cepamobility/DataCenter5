package presentation.translator.web.driver;

import java.util.Calendar;
import java.util.Hashtable;

import presentation.translator.web.util.Util;

import communication.administration.bookmark.tables.BookmarkData;
import communication.driver.tables.DriverData;
import communication.driver.tables.TelematicControlData;
import communication.language.Text;

public class TelematicControlQueryForm extends DriverDivOrgForm {

	private static final long serialVersionUID = 1L;

    private String firstname;
    private String lastname;
    
    private String idDallas;
    
    // Periodo
    private String frommonth;
    private String fromyear;
    private String fromday;
    private String tomonth;
    private String toyear;
    private String today;
    
    // Activos, Excluidos o Todos:    
    private String status;
    
    // Formato de Salida
    private String output;
    // Tipo de Salida: resumida o detallada
    private String reportType;
    
    // Por tipo de Evento
    private String type;
    
    // Por Accion Preventiva
    private String recomendation;
    
    private String color;
    private String colorOrType; // filtra por color o por tipo de evento.

    
    public String getLink() {
        String link = "";
        link += "&licExpiry=true";
        link += (!Util.empty(this.firstname)) ? "&fn="+this.firstname : "";       
        link += (!Util.empty(this.lastname)) ? "&ln="+this.lastname : "";
        link += (!Util.empty(this.idDallas)) ? "&idd="+this.idDallas : "";        
        link += (!Util.empty(this.fromday)) ? "&fd="+fromday : "";
        link += (!Util.empty(this.frommonth)) ? "&fm="+frommonth : "";
        link += (!Util.empty(this.fromyear)) ? "&fy="+fromyear : "";
        link += (!Util.empty(type)) ? "&type="+type : "";
        link += (!Util.empty(recomendation)) ? "&r="+recomendation : "";
        link += (!Util.empty(today)) ? "&td="+today : "";
        link += (!Util.empty(tomonth)) ? "&tm="+tomonth : "";
        link += (!Util.empty(toyear)) ? "&ty="+toyear : "";
        link += "&status="+status ;
        link += (!Util.empty(output)) ? "&output="+output : "";
        link += (!Util.empty(reportType)) ? "&rt="+reportType : "";
        link += (!Util.empty(color)) ? "&color="+color : "";   
        link += (!Util.empty(colorOrType)) ? "&cort="+colorOrType : "";  
        
        link += super.getLink();
        System.out.println("link");
        return link;
    }
    
    /**
     * Constructor por defecto
     */
    public TelematicControlQueryForm(String status) {
        this.status = status;
    }
    
    /**
     * Constructor por defecto
     */
    public TelematicControlQueryForm() {
        status = "all";
        this.colorOrType = "color";
        
        /* Pongo por defecto que la fecha desde los controles telematicos
         * es el primero del mes anterior al corriente
         */
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int day = c.get(Calendar.DATE);
        int month = c.get(Calendar.MONTH); // si hoy es 7, muestra 6. Mantengo el valor porqu lo paso a string.
        
        if (month == 0){
        	year = year - 1;
        	month = 12;
        }
        
        this.fromday = "1";
        this.frommonth = Integer.valueOf(month).toString();
        this.fromyear = Integer.valueOf(year).toString();
        
        
        
    }
    
    public TelematicControlQueryForm(BookmarkData bookmark) {
        super(bookmark);
        Hashtable parameters = bookmark.getParameters();
        if(parameters.containsKey("firstname")) {
            firstname = (String)parameters.get("firstname"); 
        }
        if(parameters.containsKey("lastname")) {
            lastname = (String)parameters.get("lastname"); 
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
        if(parameters.containsKey("color")) {
            color = (String)parameters.get("color"); 
        }
        if(parameters.containsKey("colorOrType")) {
            colorOrType = (String)parameters.get("colorOrType"); 
        }
        if(parameters.containsKey("output")) {
            output = (String)parameters.get("output"); 
        }
        if(parameters.containsKey("reportType")) {
            reportType = (String)parameters.get("reportType"); 
        }
        if(parameters.containsKey("status")) {
            status = (String)parameters.get("status"); 
        }
        if(parameters.containsKey("recomendation")) {
            recomendation = (String)parameters.get("recomendation"); 
        }
        if(parameters.containsKey("type")) {
            type = (String)parameters.get("type"); 
        }
        if(parameters.containsKey("idDallas")) {
            idDallas = (String)parameters.get("idDallas"); 
        }
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
    public TelematicControlQueryForm(String divOrg11, String divOrg12, String divOrg13, String divOrg21, String divOrg22, String divOrg23, String divOrg31, String divOrg32, String divOrg33, String country1, String country2, String country3) {
        super(divOrg11, divOrg12, divOrg13, divOrg21, divOrg22, divOrg23, divOrg31, divOrg32, divOrg33, country1, country2, country3);
    }
   

    public String getFirstname() {
        return firstname;
    }

    public String getFrommonth() {
        return frommonth;
    }

    public String getFromyear() {
        return fromyear;
    }

    public String getLastname() {
        return lastname;
    }

    public String getOutput() {
        return output;
    }

    public String getStatus() {
        return status;
    }

    public String getTomonth() {
        return tomonth;
    }

    public String getToyear() {
        return toyear;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setFrommonth(String frommonth) {
        this.frommonth = frommonth;
    }

    public void setFromyear(String fromyear) {
        this.fromyear = fromyear;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTomonth(String tomonth) {
        this.tomonth = tomonth;
    }

    public void setToyear(String toyear) {
        this.toyear = toyear;
    }

    public String getFrom(Text messages) {
        if(Util.empty(frommonth) || Util.empty(fromyear) ) {
            return "---";
        }else {
            return fromday + " / " + messages.getText("generic.month."+frommonth)+" / "+fromyear;
        }
    }

    public String getTo(Text messages) {
        if(Util.empty(tomonth) || Util.empty(toyear)) {
            return "---";
        }else {
            return today + " / " +  messages.getText("generic.month."+tomonth)+" / "+toyear;
        }
    }

    /**
     * 
     * @return
     */
    public BookmarkData getBookmark() {
        BookmarkData bookmark = super.getBookmark(BookmarkData.DRIVER_LICENSE_EXPIRY);
        if(!Util.empty(firstname)) {
            bookmark.addParameter("firstname",firstname);
        }
        if(!Util.empty(lastname)) {
            bookmark.addParameter("lastname",lastname);
        }
        if(!Util.empty(output)) {
            bookmark.addParameter("output",output);
        }
        if(!Util.empty(color)) {
            bookmark.addParameter("color",color);
        }
        if(!Util.empty(colorOrType)) {
            bookmark.addParameter("colorOrType",colorOrType);
        }
        if(!Util.empty(reportType)) {
            bookmark.addParameter("reportType",reportType);
        }
        if(!Util.empty(status)) {
            bookmark.addParameter("status",status);
        }
        if(!Util.empty(fromday)) {
            bookmark.addParameter("fromday",fromday);
        }
        if(!Util.empty(frommonth)) {
            bookmark.addParameter("frommonth",frommonth);
        }
        if(!Util.empty(fromyear)) {
            bookmark.addParameter("fromyear",fromyear);
        }
        if(!Util.empty(today)) {
            bookmark.addParameter("today",today);
        }
        if(!Util.empty(tomonth)) {
            bookmark.addParameter("tomonth",tomonth);
        }
        if(!Util.empty(toyear)) {
            bookmark.addParameter("toyear",toyear);
        }
        if(!Util.empty(idDallas)) {
            bookmark.addParameter("idDallas",idDallas);
        }
        if(!Util.empty(type)) {
            bookmark.addParameter("type",type);
        }
        if(!Util.empty(recomendation)) {
            bookmark.addParameter("recomendation",recomendation);
        }
        return bookmark;
    }

	/**
	 * @return the idDallas
	 */
	public String getIdDallas() {
		return idDallas;
	}

	/**
	 * @param idDallas the idDallas to set
	 */
	public void setIdDallas(String idDallas) {
		this.idDallas = idDallas;
	}

	/**
	 * @return the fromday
	 */
	public String getFromday() {
		return fromday;
	}

	/**
	 * @param fromday the fromday to set
	 */
	public void setFromday(String fromday) {
		this.fromday = fromday;
	}

	/**
	 * @return the today
	 */
	public String getToday() {
		return today;
	}

	/**
	 * @param today the today to set
	 */
	public void setToday(String today) {
		this.today = today;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the recomendation
	 */
	public String getRecomendation() {
		return recomendation;
	}

	/**
	 * @param recomendation the recomendation to set
	 */
	public void setRecomendation(String recomendation) {
		this.recomendation = recomendation;
	}
    

	
	/**
	 * 
	 * @param type
	 * @return
	 */
	public String getTypeString(){
		if (!Util.empty(type) && Util.isNumber(type)){ 
			int t = Integer.parseInt(type); 
			return TelematicControlData.getTypeString(t);
		}else{
			return TelematicControlData.getTypeString(0);
		}			
	}   
	
	public String getRecomendationString(){
		if (!Util.empty(recomendation) && Util.isNumber(recomendation)){
			int r = Integer.parseInt(recomendation);
			return TelematicControlData.getRecomendationString(r);
		}else{
			return TelematicControlData.getRecomendationString(0);
		}
			
	}
	
	public String getStatusString(){
		if (status.equalsIgnoreCase(Integer.valueOf(DriverData.ACTIVE).toString())){
			return "generic.messages.active";
		}else if (status.equalsIgnoreCase(Integer.valueOf(DriverData.EXCLUDED).toString())){
			return "generic.messages.excluded";
		}else{
			return "generic.messages.all.pl";
		}
	}

	/**
	 * @return the reportType
	 */
	public String getReportType() {
		return reportType;
	}

	/**
	 * @param reportType the reportType to set
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return the colorOrType
	 */
	public String getColorOrType() {
		return colorOrType;
	}

	/**
	 * @param colorOrType the colorOrType to set
	 */
	public void setColorOrType(String colorOrType) {
		this.colorOrType = colorOrType;
	}

	public String getType(Text messages) {
		if(type.equals("0")) {
			return messages.getText("generic.messages.all.pl");
		}
		return messages.getText(TelematicControlData.getTypeString(Integer.parseInt(type)));
	}
	
	

}