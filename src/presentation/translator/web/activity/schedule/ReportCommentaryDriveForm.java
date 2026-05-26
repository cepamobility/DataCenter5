package presentation.translator.web.activity.schedule;

import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import presentation.translator.web.driver.DriverDivOrgForm;
import presentation.translator.web.util.Util;

import communication.administration.bookmark.tables.BookmarkData;
import communication.driver.tables.DriverData;

/**
 * @author gbenaderet
 *
 */
public class ReportCommentaryDriveForm extends DriverDivOrgForm  {

	private static final long serialVersionUID = 1L;
	private String fromday;
	private String frommonth;
    private String fromyear;
    private String today;
    private String tomonth;
    private String toyear;
    private String output;
    private String state;
    private String vehicleAllowed;
    private String type;
    private String commentaryDriveType;
    private String commentaryDriveKind;
    private String oneCommentaryDrive;
    private String twoCommentaryDrive;
    private String moreThanTwoCommentaryDrive;
    private String withCommentaryDrive;
    private String pageNum = "1";
    private String all = "false";
    private int driverState = DriverData.ALL;

    public void reset(ActionMapping mapping, HttpServletRequest request) {
    	this.setWithCommentaryDrive("wocd");	
        commentaryDriveType = "all";
        commentaryDriveKind = "0";
	   /*
        oneCommentaryDrive = "false";
        twoCommentaryDrive = "false";
        moreThanTwoCommentaryDrive = "false";
        */
        oneCommentaryDrive = "off";
        twoCommentaryDrive = "off";
        moreThanTwoCommentaryDrive = "off";
	    driverState = DriverData.ALL;
    }
	  	       
    public ReportCommentaryDriveForm() {
    	this.setWithCommentaryDrive("wocd");
    	//Calendar calendar1 = Calendar.getInstance();
    	this.today = ""; //Integer.valueOf(calendar1.get(Calendar.DATE)).toString();
    	this.tomonth = ""; //Integer.valueOf(calendar1.get(Calendar.MONTH)+1).toString();
    	this.toyear = ""; //Integer.valueOf(calendar1.get(Calendar.YEAR)).toString();	    	
    	this.fromday = ""; //this.today;
    	this.frommonth = ""; //this.tomonth;
    	this.fromyear = ""; //Integer.valueOf(calendar1.get(Calendar.YEAR)-1).toString();

	    commentaryDriveType = "all";
        commentaryDriveKind = "0";
        oneCommentaryDrive = "off";
        twoCommentaryDrive = "off";
        moreThanTwoCommentaryDrive = "off";
	    driverState = DriverData.ALL;
	    
    }    
	  
    public ReportCommentaryDriveForm(String divOrg11, String divOrg12,String divOrg13, String divOrg21, String divOrg22, String divOrg23,
	            String divOrg31, String divOrg32, String divOrg33, String country1,String country2, String country3) {
        super(divOrg11, divOrg12, divOrg13, divOrg21, divOrg22, divOrg23,divOrg31, divOrg32, divOrg33, country1, country2, country3);
    }
	    
    public ReportCommentaryDriveForm(BookmarkData bookmark) {
        super(bookmark);
        Hashtable parameters = bookmark.getParameters();
        if(parameters.containsKey("fromday")) {
            fromday = (String)parameters.get("fromday");
        }
        if(parameters.containsKey("frommonth")) {
            frommonth = (String)parameters.get("frommonth");
        }
        if(parameters.containsKey("fromyear")) {
            fromyear = (String)parameters.get("fromyear");
        }
        if(parameters.containsKey("today")) {
            today = (String)parameters.get("today");
        }
        if(parameters.containsKey("tomonth")) {
            tomonth = (String)parameters.get("tomonth");
        }
        if(parameters.containsKey("toyear")) {
            toyear = (String)parameters.get("toyear");
        }
        if(parameters.containsKey("output")) {
            output = (String)parameters.get("output");
        }
        if(parameters.containsKey("state")) {
            state = (String)parameters.get("state");
        }
        if(parameters.containsKey("vehicleAllowed")) {
            vehicleAllowed = (String)parameters.get("vehicleAllowed");
        }
        if(parameters.containsKey("type")) {
            type = (String)parameters.get("type");
        }
        if(parameters.containsKey("commentaryDriveType")) {
            commentaryDriveType = (String)parameters.get("commentaryDriveType");
        }
        if(parameters.containsKey("commentaryDriveKind")) {
            commentaryDriveKind = (String)parameters.get("commentaryDriveKind");
        }
        if(parameters.containsKey("oneCommentaryDrive")) {
            oneCommentaryDrive = (String)parameters.get("oneCommentaryDrive");
        }
        if(parameters.containsKey("twoCommentaryDrive")) {
            twoCommentaryDrive = (String)parameters.get("twoCommentaryDrive");
        }
        if(parameters.containsKey("moreThanTwoCommentaryDrive")) {
            moreThanTwoCommentaryDrive = (String)parameters.get("moreThanTwoCommentaryDrive");
        }
        if(parameters.containsKey("withCommentaryDrive")) {
            withCommentaryDrive = (String)parameters.get("withCommentaryDrive");
        }
        if(parameters.containsKey("pageNum")) {
            pageNum = (String)parameters.get("pageNum");
        }
        if(parameters.containsKey("all")) {
            all = (String)parameters.get("all");
        }
        driverState = Integer.parseInt((String)parameters.get("driverState"));
    }
    
	/**
	 * @return Returns the moreThanTwoCommentaryDrive.
	 */
	public String getMoreThanTwoCommentaryDrive() {
	    return moreThanTwoCommentaryDrive;
	}

	/**
	 * @param moreThanTwoCommentaryDrive The moreThanTwoCommentaryDrive to set.
	 */
	public void setMoreThanTwoCommentaryDrive(String moreThanTwoCommentaryDrive) {
		this.moreThanTwoCommentaryDrive = moreThanTwoCommentaryDrive;
	}

	/**
	 * @return Returns the oneCommentaryDrive.
	 */
	public String getOneCommentaryDrive() {
		return oneCommentaryDrive;
	}

	/**
	 * @param oneCommentaryDrive The oneCommentaryDrive to set.
	 */
	public void setOneCommentaryDrive(String oneCommentaryDrive) {
		this.oneCommentaryDrive = oneCommentaryDrive;
	}

	/**
	 * @return Returns the twoCommentaryDrive.
	 */
	public String getTwoCommentaryDrive() {
		return twoCommentaryDrive;
	}

	/**
	 * @param twoCommentaryDrive The twoCommentaryDrive to set.
	 */
	public void setTwoCommentaryDrive(String twoCommentaryDrive) {
		this.twoCommentaryDrive = twoCommentaryDrive;
	}

	/**
	 * @return Returns the withCommentaryDrive.
	 */
	public String getWithCommentaryDrive() {
		return withCommentaryDrive;
	}

	/**
	 * @param withCommentaryDrive The withCommentaryDrive to set.
	 */
	public void setWithCommentaryDrive(String withCommentaryDrive) {
		this.withCommentaryDrive = withCommentaryDrive;
	}

	/**
	 * @return Returns the commentarydrivetype.
	 */
	public String getCommentaryDriveType() {
		return commentaryDriveType;
	}

	/**
	 * @param commentarydrivetype The commentarydrivetype to set.
	 */
	public void setCommentaryDriveType(String commentarydrivetype) {
		this.commentaryDriveType = commentarydrivetype;
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
    public String getState() {
        return state;
    }
    public String getType() {
        return type;
    }
	    
    public Date getInitialDate() {
    	if (isEmpty(fromday) || isEmpty(frommonth) || isEmpty(fromyear)) {
    		return null;
    	} else {
        	Calendar calendar = Calendar.getInstance();
    		calendar.set(Calendar.DATE,Integer.parseInt(fromday.trim()));
    		calendar.set(Calendar.MONTH,Integer.parseInt(frommonth.trim())-1);
    		calendar.set(Calendar.YEAR,Integer.parseInt(fromyear.trim()));
            return calendar.getTime();
    	}
   } 	
	    
    public Date getLastDate() {		
    	int year, day, month;
    	if (isEmpty(today) || isEmpty(tomonth) || isEmpty(toyear)) {
    		return null;
    	} else {	    		
    		year= Integer.parseInt(toyear.trim());
	 		month=Integer.parseInt(tomonth.trim())-1;
	 		day=Integer.parseInt(today.trim());
	 		if (month==12) {		 			
	 			month=1;
	 			year=year+1;
	 		}
	 		
        	Calendar calendar = Calendar.getInstance();
    		calendar.set(Calendar.DATE,day);
    		calendar.set(Calendar.MONTH,month);
    		calendar.set(Calendar.YEAR,year);
            return calendar.getTime();
    	}	    	
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
    public void setState(String state) {
        this.state = state;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
   
    private boolean isEmpty(String value) {
	    return ((value == null) || (value.trim().length()==0));
	}

	/**
	 * @return Returns the fromday.
	 */
	public String getFromday() {
		return fromday;
	}

	/**
	 * @param fromday The fromday to set.
	 */
	public void setFromday(String fromday) {
		this.fromday = fromday;
	}
	
	/**
	 * @return Returns the today.
	 */
	public String getToday() {
		return today;
	}

	/**
	 * @param today The today to set.
	 */
	public void setToday(String today) {
		this.today = today;
	}
		
	public String getAll() {
	    return all;
	}

	public String getPageNum() {
	    return pageNum;
	}

	public void setAll(String all) {
	    this.all = all;
	}

	public void setPageNum(String pageNum) {
	    this.pageNum = pageNum;
	}

	/**
	 * @return Returns the driverState.
	 */
	public int getDriverState() {
		return driverState;
	}

	/**
	 * @param driverState The driverState to set.
	 */
	public void setDriverState(int driverState) {
		this.driverState = driverState;
	}
    
	public void setDriverState(String driverState) {
		this.driverState = Integer.parseInt(driverState.trim());
	}
			
	/*
	 *  (non-Javadoc)
	 * @see presentation.translator.web.driver.DriverDivOrgForm#getLink()
	 * Genera un link con los parametros del form
	 */
	public String getLink() {
        String link = "";
        link += (!Util.empty(fromday)) ? "&fd="+fromday : "";
        link += (!Util.empty(frommonth)) ? "&fm="+frommonth : "";
        link += (!Util.empty(fromyear)) ? "&fy="+fromyear : "";
        link += (!Util.empty(today)) ? "&td="+today : "";
        link += (!Util.empty(tomonth)) ? "&tm="+tomonth : "";
        link += (!Util.empty(toyear)) ? "&ty="+toyear : "";
        link += (!Util.empty(output)) ? "&o="+output : "";
        link += (!Util.empty(state)) ? "&s="+state : "";
        link += (!Util.empty(vehicleAllowed)) ? "&va="+vehicleAllowed : "";
        link += (!Util.empty(type)) ? "&type="+type : "";
        link += (!Util.empty(commentaryDriveType)) ? "&cdt="+commentaryDriveType : "";
        link += (!Util.empty(commentaryDriveKind)) ? "&cdk="+commentaryDriveKind : "";
        link += (!Util.empty(oneCommentaryDrive)) ? "&ocd="+oneCommentaryDrive : "";
        link += (!Util.empty(twoCommentaryDrive)) ? "&tcd="+twoCommentaryDrive : "";
        link += (!Util.empty(moreThanTwoCommentaryDrive)) ? "&mttcd="+ this.moreThanTwoCommentaryDrive : "";
        link += (!Util.empty(withCommentaryDrive)) ? "&wcd="+withCommentaryDrive : "";
        link += (!Util.empty(Integer.toString(driverState))) ? "&ds="+Integer.toString(driverState) : "";
        link += super.getLink();
        return link;
    }

    public String getVehicleAllowed() {
        return vehicleAllowed;
    }

    public void setVehicleAllowed(String vehicleAllowed) {
        this.vehicleAllowed = vehicleAllowed;
    }

    public String getCommentaryDriveKind() {
        return commentaryDriveKind;
    }

    public void setCommentaryDriveKind(String commentaryDriveKind) {
        this.commentaryDriveKind = commentaryDriveKind;
    }

    public BookmarkData getBookmark() {
        BookmarkData data = super.getBookmark(BookmarkData.COMMENTARY_DRIVE_REPORT);
        if(!Util.empty(fromday)) {
            data.addParameter("fromday",fromday);
        }
        if(!Util.empty(frommonth)) {
            data.addParameter("frommonth",frommonth);
        }
        if(!Util.empty(fromyear)) {
            data.addParameter("fromyear",fromyear);
        }
        if(!Util.empty(today)) {
            data.addParameter("today",today);
        }
        if(!Util.empty(tomonth)) {
            data.addParameter("tomonth",tomonth);
        }
        if(!Util.empty(toyear)) {
            data.addParameter("toyear",toyear);
        }
        if(!Util.empty(output)) {
            data.addParameter("output",output);
        }
        if(!Util.empty(state)) {
            data.addParameter("state",state);
        }
        if(!Util.empty(vehicleAllowed)) {
            data.addParameter("vehicleAllowed",vehicleAllowed);
        }
        if(!Util.empty(type)) {
            data.addParameter("type",type);
        }
        if(!Util.empty(commentaryDriveType)) {
            data.addParameter("commentaryDriveType",commentaryDriveType);
        }
        if(!Util.empty(commentaryDriveKind)) {
            data.addParameter("commentaryDriveKind",commentaryDriveKind);
        }
        if(!Util.empty(oneCommentaryDrive)) {
            data.addParameter("oneCommentaryDrive",oneCommentaryDrive);
        }
        if(!Util.empty(twoCommentaryDrive)) {
            data.addParameter("twoCommentaryDrive",twoCommentaryDrive);
        }
        if(!Util.empty(moreThanTwoCommentaryDrive)) {
            data.addParameter("moreThanTwoCommentaryDrive",moreThanTwoCommentaryDrive);
        }
        if(!Util.empty(withCommentaryDrive)) {
            data.addParameter("withCommentaryDrive",withCommentaryDrive);
        }
        if(!Util.empty(pageNum)) {
            data.addParameter("pageNum",pageNum);
        }
        if(!Util.empty(all)) {
            data.addParameter("all",all);
        }
        data.addParameter("driverState",String.valueOf(driverState));
        return data;
    }

}	    

