/**
 * Created on 06-jul-2006
 * CEPA
 * DataCenter 5
 */
package presentation.translator.web.driver;

import java.util.Date;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import presentation.translator.web.util.Util;

import communication.administration.bookmark.tables.BookmarkData;
import communication.driver.tables.DriverData;

public class FOSADriverReportForm extends DriverDivOrgForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String lastname;
    
    private String birthDate;
    private String birthMonth;
    private String birthYear;

    private String output;
    private String status;
    
    private String accident;
    private String accidentCount;
    
    private String incident;
    private String incidentCount;

    private String fine;
    private String fineCount;
    protected String type;

    protected String bps;
    protected String aptitudStatus;
    protected String aptitudResult;
    protected String alcoholStatus;
    protected String alcoholResult;
    protected String postiveAlcohol;
    
    public static final int ALL = 0; 
    public static final int WITH = 1; 
    public static final int AT_LEAST = 2; 
    public static final int NONE = 3;
    
    
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
    public FOSADriverReportForm(String divOrg11, String divOrg12, String divOrg13, String divOrg21, String divOrg22, String divOrg23, String divOrg31, String divOrg32, String divOrg33, String country1, String country2, String country3) {
        super(divOrg11, divOrg12, divOrg13, divOrg21, divOrg22, divOrg23, divOrg31, divOrg32, divOrg33, country1, country2, country3);
    }


    public FOSADriverReportForm() {
        accident = String.valueOf(ALL);
        incident = String.valueOf(ALL);
        fine = String.valueOf(ALL);
        status = String.valueOf(DriverData.ALL);
    }
    
    public FOSADriverReportForm(BookmarkData bookmark) {
        super(bookmark);
        Hashtable parameters = bookmark.getParameters();
        if(parameters.containsKey("id")) {
            id = (String)parameters.get("id"); 
        }
        if(parameters.containsKey("name")) {
            name = (String)parameters.get("name"); 
        }
        if(parameters.containsKey("lastname")) {
            lastname = (String)parameters.get("lastname"); 
        }
        if(parameters.containsKey("birthDate")) {
            birthDate = (String)parameters.get("birthDate"); 
        }
        if(parameters.containsKey("birthMonth")) {
            birthMonth = (String)parameters.get("birthMonth"); 
        }
        if(parameters.containsKey("birthYear")) {
            birthYear = (String)parameters.get("birthYear"); 
        }
        if(parameters.containsKey("output")) {
            output = (String)parameters.get("output"); 
        }
        if(parameters.containsKey("status")) {
            status = (String)parameters.get("status"); 
        }
        if(parameters.containsKey("accident")) {
            accident = (String)parameters.get("accident"); 
        }
        if(parameters.containsKey("accidentCount")) {
            accidentCount = (String)parameters.get("accidentCount"); 
        }
        if(parameters.containsKey("incident")) {
            incident = (String)parameters.get("incident"); 
        }
        if(parameters.containsKey("incidentCount")) {
            incidentCount = (String)parameters.get("incidentCount"); 
        }
        if(parameters.containsKey("fine")) {
            fine = (String)parameters.get("fine"); 
        }
        if(parameters.containsKey("fineCount")) {
            fineCount = (String)parameters.get("fineCount"); 
        }
        if(parameters.containsKey("type")) {
            type = (String)parameters.get("type"); 
        }
        if(parameters.containsKey("bps")) {
            bps = (String)parameters.get("bps"); 
        }
        if(parameters.containsKey("aptitudStatus")) {
            aptitudStatus = (String)parameters.get("aptitudStatus"); 
        }
        if(parameters.containsKey("aptitudResult")) {
            aptitudResult = (String)parameters.get("aptitudResult"); 
        }
        if(parameters.containsKey("alcoholStatus")) {
            alcoholStatus = (String)parameters.get("alcoholStatus"); 
        }
        if(parameters.containsKey("alcoholResult")) {
            alcoholResult = (String)parameters.get("alcoholResult"); 
        }
        if(parameters.containsKey("postiveAlcohol")) {
            postiveAlcohol = (String)parameters.get("postiveAlcohol"); 
        }
    }
    
    public void reset(ActionMapping mapping, HttpServletRequest request) {
    	accident = String.valueOf(ALL);
        incident = String.valueOf(ALL);
        fine = String.valueOf(ALL);
        status = String.valueOf(DriverData.ALL);
    }

    /**
     * @return Returns the accident.
     */
    public String getAccident() {
        return accident;
    }

    /**
     * @return Returns the accidentCount.
     */
    public String getAccidentCount() {
        return accidentCount;
    }

    /**
     * @return Returns the fine.
     */
    public String getFine() {
        return fine;
    }

    /**
     * @return Returns the fineCount.
     */
    public String getFineCount() {
        return fineCount;
    }

    /**
     * @return Returns the incident.
     */
    public String getIncident() {
        return incident;
    }

    /**
     * @return Returns the incidentCount.
     */
    public String getIncidentCount() {
        return incidentCount;
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Returns the output.
     */
    public String getOutput() {
        return output;
    }

    /**
     * @return Returns the status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param accident The accident to set.
     */
    public void setAccident(String accident) {
        this.accident = accident;
    }

    /**
     * @param accidentCount The accidentCount to set.
     */
    public void setAccidentCount(String accidentCount) {
        this.accidentCount = accidentCount;
    }

    /**
     * @param fine The fine to set.
     */
    public void setFine(String fine) {
        this.fine = fine;
    }

    /**
     * @param fineCount The fineCount to set.
     */
    public void setFineCount(String fineCount) {
        this.fineCount = fineCount;
    }

    /**
     * @param incident The incident to set.
     */
    public void setIncident(String incident) {
        this.incident = incident;
    }

    /**
     * @param incidentCount The incidentCount to set.
     */
    public void setIncidentCount(String incidentCount) {
        this.incidentCount = incidentCount;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param output The output to set.
     */
    public void setOutput(String output) {
        this.output = output;
    }

    /**
     * @param status The status to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


    public String getLink() {
        String link = "";
        link += (!Util.empty(id)) ? "&id="+id : "";
        link += (!Util.empty(name)) ? "&name="+name : "";
        link += (!Util.empty(lastname)) ? "&lastname="+lastname : "";
        link += (!Util.empty(birthDate)) ? "&birthDate="+birthDate : "";
        link += (!Util.empty(birthMonth)) ? "&birthMonth="+birthMonth : "";
        link += (!Util.empty(birthYear)) ? "&birthYear="+birthYear : "";
        link += (!Util.empty(status)) ? "&status="+status : "";
        link += (!Util.empty(accident)) ? "&accident="+accident : "";
        link += (!Util.empty(accidentCount)) ? "&accidentCount="+accidentCount : "";
        link += (!Util.empty(incident)) ? "&incident="+incident : "";
        link += (!Util.empty(incidentCount)) ? "&incidentCount="+incidentCount : "";
        link += (!Util.empty(fine)) ? "&fine="+fine : "";
        link += (!Util.empty(fineCount)) ? "&fineCount="+fineCount : "";
        link += (!Util.empty(type)) ? "&type="+type : "";
        link += (!Util.empty(output)) ? "&output="+output : "";

        link += (!Util.empty(bps)) ? "&bps="+bps : "";
        link += (!Util.empty(alcoholResult)) ? "&alcoholResult="+alcoholResult : "";
        link += (!Util.empty(alcoholStatus)) ? "&alcoholStatus="+alcoholStatus : "";
        link += (!Util.empty(aptitudStatus)) ? "&aptitudStatus="+aptitudStatus : "";
        link += (!Util.empty(aptitudResult)) ? "&aptitudResult="+aptitudResult : "";
        link += (!Util.empty(postiveAlcohol)) ? "&postiveAlcohol="+postiveAlcohol : "";

        link += super.getLink();
        return link;
    }
    
	/**
	 * @return Returns the country1.
	 */
	public String getCountry1() {
		return country1;
	}
	/**
	 * @param country1 The country1 to set.
	 */
	public void setCountry1(String country1) {
		this.country1 = country1;
	}
	/**
	 * @return Returns the country2.
	 */
	public String getCountry2() {
		return country2;
	}
	/**
	 * @param country2 The country2 to set.
	 */
	public void setCountry2(String country2) {
		this.country2 = country2;
	}
	/**
	 * @return Returns the country3.
	 */
	public String getCountry3() {
		return country3;
	}
	/**
	 * @param country3 The country3 to set.
	 */
	public void setCountry3(String country3) {
		this.country3 = country3;
	}


    public String getAptitudResult() {
        return aptitudResult;
    }


    public String getAptitudStatus() {
        return aptitudStatus;
    }


    public String getBps() {
        return bps;
    }


    public void setAptitudResult(String aptitudResult) {
        this.aptitudResult = aptitudResult;
    }


    public void setAptitudStatus(String aptitudStatus) {
        this.aptitudStatus = aptitudStatus;
    }


    public void setBps(String bps) {
        this.bps = bps;
    }


    public String getAlcoholResult() {
        return alcoholResult;
    }


    public String getAlcoholStatus() {
        return alcoholStatus;
    }


    public void setAlcoholResult(String alcoholResult) {
        this.alcoholResult = alcoholResult;
    }


    public void setAlcoholStatus(String alcoholStatus) {
        this.alcoholStatus = alcoholStatus;
    }


    public String getPostiveAlcohol() {
        return postiveAlcohol;
    }


    public void setPostiveAlcohol(String postiveAlcohol) {
        this.postiveAlcohol = postiveAlcohol;
    }


    public BookmarkData getBookmark() {
        BookmarkData bookmark = super.getBookmark(BookmarkData.FOSA_DRIVER_REPORT);
        if(!Util.empty(id)) {
            bookmark.addParameter("id",id);
        }
        if(!Util.empty(name)) {
            bookmark.addParameter("name",name);
        }
        if(!Util.empty(lastname)) {
            bookmark.addParameter("lastname",lastname);
        }
        if(!Util.empty(birthDate)) {
            bookmark.addParameter("birthDate",birthDate);
        }
        if(!Util.empty(birthMonth)) {
            bookmark.addParameter("birthMonth",birthMonth);
        }
        if(!Util.empty(birthYear)) {
            bookmark.addParameter("birthYear",birthYear);
        }
        if(!Util.empty(output)) {
            bookmark.addParameter("output",output);
        }
        if(!Util.empty(status)) {
            bookmark.addParameter("status",status);
        }
        if(!Util.empty(accident)) {
            bookmark.addParameter("accident",accident);
        }
        if(!Util.empty(accidentCount)) {
            bookmark.addParameter("accidentCount",accidentCount);
        }
        if(!Util.empty(incident)) {
            bookmark.addParameter("incident",incident);
        }
        if(!Util.empty(incidentCount)) {
            bookmark.addParameter("incidentCount",incidentCount);
        }
        if(!Util.empty(fine)) {
            bookmark.addParameter("fine",fine);
        }
        if(!Util.empty(fineCount)) {
            bookmark.addParameter("fineCount",fineCount);
        }
        if(!Util.empty(type)) {
            bookmark.addParameter("type",type);
        }
        if(!Util.empty(bps)) {
            bookmark.addParameter("bps",bps);
        }
        if(!Util.empty(aptitudStatus)) {
            bookmark.addParameter("aptitudStatus",aptitudStatus);
        }
        if(!Util.empty(aptitudResult)) {
            bookmark.addParameter("aptitudResult",aptitudResult);
        }
        if(!Util.empty(alcoholStatus)) {
            bookmark.addParameter("alcoholStatus",alcoholStatus);
        }
        if(!Util.empty(alcoholResult)) {
            bookmark.addParameter("alcoholResult",alcoholResult);
        }
        if(!Util.empty(postiveAlcohol)) {
            bookmark.addParameter("postiveAlcohol",postiveAlcohol);
        }
        return bookmark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getBirthDate() {
        return birthDate;
    }


    public String getBirthMonth() {
        return birthMonth;
    }


    public String getBirthYear() {
        return birthYear;
    }


    public String getLastname() {
        return lastname;
    }


    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }


    public void setBirthMonth(String birthMonth) {
        this.birthMonth = birthMonth;
    }


    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }


    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public Date getBirth() {
        return Util.getDate(birthDate,birthMonth,birthYear);
    }

}
