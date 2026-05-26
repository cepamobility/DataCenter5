package presentation.translator.web.driver;

import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.administration.bookmark.tables.BookmarkData;
import communication.driver.tables.DriverData;

public class CombinedReportForm extends DriverDivOrgForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String firstName;
	private String lastName;
    private String status;

    private String activities;
    
    private String fromMonthActivity;
    private String fromYearActivity;
    private String toMonthActivity;
    private String toYearActivity;
    
    private String events;
    
    private String fromMonthEvent;
    private String fromYearEvent;
    private String toMonthEvent;
    private String toYearEvent;
    
    private String[] activityType;
    
    private String[] accidentType;
    private String accidentCount;
    private String evitability;
    
    private String[] incidentType;
    private String incidentCount;

    private String[] fineType;
    private String fineCount;

    private String relationAccident;
    private String relationIncident;
    private String relationFine;
    
    private String relationAE;
    private String relationEA;

    private String output;

	private Date lastUpdate;
        
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
    public CombinedReportForm(String divOrg11, String divOrg12, String divOrg13, String divOrg21, String divOrg22, String divOrg23, String divOrg31, String divOrg32, String divOrg33, String country1, String country2, String country3) {
        super(divOrg11, divOrg12, divOrg13, divOrg21, divOrg22, divOrg23, divOrg31, divOrg32, divOrg33, country1, country2, country3);
        activities = "0";
        events = "0";
    }
    
    


    public CombinedReportForm(String divOrg11, String divOrg12, String divOrg13, String divOrg21, String divOrg22, String divOrg23, String divOrg31, String divOrg32, String divOrg33, String country1, String country2, String country3,
    		String firstName, String lastName, String status,String activities, String fromMonthActivity,String fromYearActivity, String toMonthActivity,
			String toYearActivity, String events, String fromMonthEvent,String fromYearEvent, String toMonthEvent, String toYearEvent,String activityType, String accidentType, String accidentCount, String evitability,
			String incidentType, String incidentCount, String fineType,String fineCount, String relationEA, String relationAE, String relationAccident, String relationIncident, String relationFine,String output) {
        super(divOrg11, divOrg12, divOrg13, divOrg21, divOrg22, divOrg23, divOrg31, divOrg32, divOrg33, country1, country2, country3);
		this.firstName = firstName;
		this.lastName = lastName;
		this.status = status;
		this.activities = activities;
		this.fromMonthActivity = fromMonthActivity;
		this.fromYearActivity = fromYearActivity;
		this.toMonthActivity = toMonthActivity;
		this.toYearActivity = toYearActivity;
		this.events = events;
		this.fromMonthEvent = fromMonthEvent;
		this.fromYearEvent = fromYearEvent;
		this.toMonthEvent = toMonthEvent;
		this.toYearEvent = toYearEvent;
		this.activityType = getData(activityType);
		this.accidentType = getData(accidentType);
		this.accidentCount = accidentCount;
		this.evitability = evitability;
		this.incidentType = getData(incidentType);
		this.incidentCount = incidentCount;
		this.fineType = getData(fineType);
		this.fineCount = fineCount;
		this.relationAE = relationAE;
		this.relationEA = relationEA;
		this.relationAccident = relationAccident;
		this.relationIncident = relationIncident;
		this.relationFine = relationFine;
		this.output = output;
	}




	private String[] getData(String string) {
		if(string == null) {
			return new String[0];
		}
		StringTokenizer tokenizer = new StringTokenizer(string,",");
		Collection elements = new LinkedList();
		while(tokenizer.hasMoreTokens()) {
			elements.add(tokenizer.nextToken());
		}
		return (String[])elements.toArray(new String[0]);
	}




	public CombinedReportForm() {
        activities = "0";
        events = "0";
    }
    
    public CombinedReportForm(BookmarkData bookmark) {
        super(bookmark);
        Hashtable parameters = bookmark.getParameters();
        if(parameters.containsKey("firstName")) {
        	firstName = (String)parameters.get("firstName"); 
        }
        if(parameters.containsKey("lastName")) {
        	lastName = (String)parameters.get("lastName"); 
        }
        if(parameters.containsKey("status")) {
            status = (String)parameters.get("status"); 
        }

        if(parameters.containsKey("output")) {
            output = (String)parameters.get("output"); 
        }
        if(parameters.containsKey("accidentType")) {
            accidentType = (String[])parameters.get("accidentType"); 
        }
        if(parameters.containsKey("accidentCount")) {
            accidentCount = (String)parameters.get("accidentCount"); 
        }
        if(parameters.containsKey("evitability")) {
            evitability = (String)parameters.get("evitability"); 
        }
        if(parameters.containsKey("incidentType")) {
            incidentType = (String[])parameters.get("incidentType"); 
        }
        if(parameters.containsKey("incidentCount")) {
            incidentCount = (String)parameters.get("incidentCount"); 
        }
        if(parameters.containsKey("fineType")) {
            fineType = (String[])parameters.get("fineType"); 
        }
        if(parameters.containsKey("fineCount")) {
            fineCount = (String)parameters.get("fineCount"); 
        }
    }
    
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        status = String.valueOf(DriverData.ALL);
    }

    public BookmarkData getBookmark() {
        BookmarkData bookmark = super.getBookmark(BookmarkData.DRIVER_REPORT);
        if(!Util.empty(firstName)) {
            bookmark.addParameter("firstName",firstName);
        }
        if(!Util.empty(lastName)) {
            bookmark.addParameter("lastName",lastName);
        }
        if(!Util.empty(output)) {
            bookmark.addParameter("output",output);
        }
        if(!Util.empty(status)) {
            bookmark.addParameter("status",status);
        }
//        if(!Util.empty(accidentType)) {
//            bookmark.addParameter("accident",accidentType);
//        }
//        if(!Util.empty(accidentCount)) {
//            bookmark.addParameter("accidentCount",accidentCount);
//        }
//        if(!Util.empty(incidentType)) {
//            bookmark.addParameter("incident",incidentType);
//        }
//        if(!Util.empty(incidentCount)) {
//            bookmark.addParameter("incidentCount",incidentCount);
//        }
//        if(!Util.empty(fineType)) {
//            bookmark.addParameter("fine",fineType);
//        }
        if(!Util.empty(fineCount)) {
            bookmark.addParameter("fineCount",fineCount);
        }
         return bookmark;
    }


	public String getActivities() {
		return activities;
	}


	public void setActivities(String activities) {
		this.activities = activities;
	}


	public String getFromMonthActivity() {
		return fromMonthActivity;
	}


	public void setFromMonthActivity(String fromMonthActivity) {
		this.fromMonthActivity = fromMonthActivity;
	}


	public String getFromYearActivity() {
		return fromYearActivity;
	}


	public void setFromYearActivity(String fromYearActivity) {
		this.fromYearActivity = fromYearActivity;
	}


	public String getToMonthActivity() {
		return toMonthActivity;
	}


	public void setToMonthActivity(String toMonthActivity) {
		this.toMonthActivity = toMonthActivity;
	}


	public String getToYearActivity() {
		return toYearActivity;
	}


	public void setToYearActivity(String toYearActivity) {
		this.toYearActivity = toYearActivity;
	}


	public String[] getActivityType() {
		return activityType;
	}


	public void setActivityType(String[] activityType) {
		this.activityType = activityType;
	}


	public String getEvents() {
		return events;
	}


	public void setEvents(String events) {
		this.events = events;
	}


	public String getFromMonthEvent() {
		return fromMonthEvent;
	}


	public void setFromMonthEvent(String fromMonthEvent) {
		this.fromMonthEvent = fromMonthEvent;
	}


	public String getFromYearEvent() {
		return fromYearEvent;
	}


	public void setFromYearEvent(String fromYearEvent) {
		this.fromYearEvent = fromYearEvent;
	}


	public String getToMonthEvent() {
		return toMonthEvent;
	}


	public void setToMonthEvent(String toMonthEvent) {
		this.toMonthEvent = toMonthEvent;
	}


	public String getToYearEvent() {
		return toYearEvent;
	}


	public void setToYearEvent(String toYearEvent) {
		this.toYearEvent = toYearEvent;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String[] getAccidentType() {
		return accidentType;
	}


	public void setAccidentType(String[] accidentType) {
		this.accidentType = accidentType;
	}


	public String getAccidentCount() {
		return accidentCount;
	}


	public void setAccidentCount(String accidentCount) {
		this.accidentCount = accidentCount;
	}

	public String getEvitability() {
		return evitability;
	}

	public void setEvitability(String evitability) {
		this.evitability = evitability;
	}

	public String[] getIncidentType() {
		return incidentType;
	}


	public void setIncidentType(String[] incidentType) {
		this.incidentType = incidentType;
	}


	public String getIncidentCount() {
		return incidentCount;
	}


	public void setIncidentCount(String incidentCount) {
		this.incidentCount = incidentCount;
	}


	public String[] getFineType() {
		return fineType;
	}


	public void setFineType(String[] fineType) {
		this.fineType = fineType;
	}


	public String getFineCount() {
		return fineCount;
	}


	public void setFineCount(String fineCount) {
		this.fineCount = fineCount;
	}


	public String getOutput() {
		return output;
	}


	public void setOutput(String output) {
		this.output = output;
	}

	public String getRelationAE() {
		return relationAE;
	}

	public void setRelationAE(String relationAE) {
		this.relationAE = relationAE;
	}

	public String getRelationEA() {
		return relationEA;
	}

	public void setRelationEA(String relationEA) {
		this.relationEA = relationEA;
	}

	public String getRelationAccident() {
		return relationAccident;
	}

	public void setRelationAccident(String relationAccident) {
		this.relationAccident = relationAccident;
	}

	public String getRelationIncident() {
		return relationIncident;
	}

	public void setRelationIncident(String relationIncident) {
		this.relationIncident = relationIncident;
	}

	public String getRelationFine() {
		return relationFine;
	}

	public void setRelationFine(String relationFine) {
		this.relationFine = relationFine;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;		
	}


	public Date getLastUpdate() {
		return lastUpdate;
	}


	public Hashtable getParameters(SystemAccess sys) throws Exception {
		Hashtable parameters = new Hashtable();
		
		if(!Util.empty(firstName)) {
			parameters.put("firstName", firstName);
		}
		if(!Util.empty(lastName)) {
			parameters.put("lastName", lastName);
		}
		if(!Util.empty(status)) {
			parameters.put("status", status);
		}
		
		parameters.put("divorgs",DriverAdvancedSearchAction.getDivOrgs(sys,this));
        parameters.put("divgeos",DriverAdvancedSearchAction.getDivGeos(sys,this));

        parameters.put("activities", activities);
		if(Integer.parseInt(activities) > 0) {
			if(!Util.empty(fromMonthActivity) && !Util.empty(fromYearActivity)) {
				parameters.put("fromMonthActivity", fromMonthActivity);
				parameters.put("fromYearActivity", fromYearActivity);
			}
			if(!Util.empty(toMonthActivity) && !Util.empty(toYearActivity)) {
				parameters.put("toMonthActivity", toMonthActivity);
				parameters.put("toYearActivity", toYearActivity);
			}
			if(activityType != null && activityType.length > 0) {
				String aTypes = "";
				boolean put = true;
				for(int i = 0; i < activityType.length; i++) {
					if(activityType[i].equals("generic.messages.all")) {
						put = false;
						break;
					}else {
						aTypes += "'"+activityType[i]+"'";
						if(i < activityType.length-1) {
							aTypes += ",";
						}
					}
				}
				if(put) {
					parameters.put("activityType", aTypes);
				}
			}
		}
		
		parameters.put("events", events);
		if(Integer.parseInt(events) > 0) {
			if(!Util.empty(fromMonthEvent) && !Util.empty(fromYearEvent)) {
				parameters.put("fromMonthEvent", fromMonthEvent);
				parameters.put("fromYearEvent", fromYearEvent);
			}
			if(!Util.empty(toMonthEvent) && !Util.empty(toYearEvent)) {
				parameters.put("toMonthEvent", toMonthEvent);
				parameters.put("toYearEvent", toYearEvent);
			}

			parameters.put("accidentCount", accidentCount);
			parameters.put("evitability", evitability);
			if(accidentType != null && accidentType.length > 0) {
				String aTypes = "";
				boolean put = true;
				for(int i = 0; i < accidentType.length; i++) {
					if(accidentType[i].equals("generic.messages.all")) {
						put = false;
						break;
					}else {
						aTypes += "'"+accidentType[i]+"'";
						if(i < accidentType.length-1) {
							aTypes += ",";
						}
					}
				}
				if(put) {
					parameters.put("accidentType", aTypes);
				}
			}

			parameters.put("incidentCount", incidentCount);
			if(incidentType != null && incidentType.length > 0) {
				String aTypes = "";
				boolean put = true;
				for(int i = 0; i < incidentType.length; i++) {
					if(incidentType[i].equals("generic.messages.all")) {
						put = false;
						break;
					}else {
						aTypes += "'"+incidentType[i]+"'";
						if(i < incidentType.length-1) {
							aTypes += ",";
						}
					}
				}
				if(put) {
					parameters.put("incidentType", aTypes);
				}
			}

			parameters.put("fineCount", fineCount);
			if(fineType != null && fineType.length > 0) {
				String aTypes = "";
				boolean put = true;
				for(int i = 0; i < fineType.length; i++) {
					if(fineType[i].equals("generic.messages.all")) {
						put = false;
						break;
					}else {
						aTypes += "'"+fineType[i]+"'";
						if(i < fineType.length-1) {
							aTypes += ",";
						}
					}
				}
				if(put) {
					parameters.put("fineType", aTypes);
				}
			}
		}

		if(Integer.parseInt(events) > 0 && Integer.parseInt(activities) > 0 && relationAE!= null) {
			parameters.put("relationAE", relationAE);
		}
		if(Integer.parseInt(events) > 0 && Integer.parseInt(activities) > 0 && relationEA!= null) {
			parameters.put("relationEA", relationEA);
		}
		
		parameters.put("relationAccident", String.valueOf(Util.isChecked(relationAccident)));
		parameters.put("relationIncident", String.valueOf(Util.isChecked(relationIncident)));
		parameters.put("relationFine", String.valueOf(Util.isChecked(relationFine)));
        
		return parameters;
	}
    public String getLink() {
    	String link = super.getLink();
    	link += "&combined=true";
        link += (!Util.empty(firstName)) ? "&firstName="+firstName : "";
        link += (!Util.empty(lastName)) ? "&lastName="+lastName : "";
        link += (!Util.empty(status)) ? "&status="+status : "";

        link += (!Util.empty(activities)) ? "&activities="+activities : "";
        link += (!Util.empty(fromMonthActivity)) ? "&fromMonthActivity="+fromMonthActivity : "";
        link += (!Util.empty(fromYearActivity)) ? "&fromYearActivity="+fromYearActivity : "";
        link += (!Util.empty(toMonthActivity)) ? "&toMonthActivity="+toMonthActivity : "";
        link += (!Util.empty(toYearActivity)) ? "&toYearActivity="+toYearActivity : "";
        
        link += (!Util.empty(events)) ? "&events="+events : "";
        link += (!Util.empty(fromMonthEvent)) ? "&fromMonthEvent="+fromMonthEvent : "";
        link += (!Util.empty(fromYearEvent)) ? "&fromYearEvent="+fromYearEvent : "";
        link += (!Util.empty(toMonthEvent)) ? "&toMonthEvent="+toMonthEvent : "";
        link += (!Util.empty(toYearEvent)) ? "&toYearEvent="+toYearEvent : "";
        
        link += (activityType != null && activityType.length > 0) ? "&activityType="+getIds(activityType) : "";
        link += (accidentType != null && accidentType.length > 0) ? "&accidentType="+getIds(accidentType) : "";
        link += (!Util.empty(accidentCount)) ? "&accidentCount="+accidentCount : "";
        link += (!Util.empty(evitability)) ? "&evitability="+evitability : "";
        link += (incidentType != null && incidentType.length > 0) ? "&incidentType="+getIds(incidentType) : "";
        link += (!Util.empty(incidentCount)) ? "&incidentCount="+incidentCount : "";
        link += (fineType != null && fineType.length > 0) ? "&fineType="+getIds(fineType) : "";
        link += (!Util.empty(fineCount)) ? "&fineCount="+fineCount : "";

        link += (!Util.empty(relationAE)) ? "&relationAE="+relationAE : "";
        link += (!Util.empty(relationEA)) ? "&relationEA="+relationEA : "";
        link += (!Util.empty(relationAccident)) ? "&relationAccident="+relationAccident : "";
        link += (!Util.empty(relationIncident)) ? "&relationIncident="+relationIncident : "";
        link += (!Util.empty(relationFine)) ? "&relationFine="+relationFine : "";
        link += (!Util.empty(output)) ? "&output="+output : "";
    	return link;
    }

	private String getIds(String[] list) {
		String s = "";
		if(list != null) {
			for (String string : list) {
				if(s.length() > 0) {
					s += ",";
				}
				s += string;
			}
		}
		return s;
	}
}
