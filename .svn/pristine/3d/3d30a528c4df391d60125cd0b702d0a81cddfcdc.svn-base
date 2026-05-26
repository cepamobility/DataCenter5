/**
 * Created on 01-nov-2006
 * CEPA
 * DataCenter 5
 */
package presentation.translator.web.activity.schedule;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.StringTokenizer;

import presentation.translator.web.driver.DriverDivOrgForm;
import presentation.translator.web.util.Util;

import communication.activity.tables.CEPAActivityConstants;
import communication.administration.bookmark.tables.BookmarkData;
import communication.administration.user.UserSessionData;
import communication.language.Text;

public class ActivityGeneralReportForm extends DriverDivOrgForm {

    private String month1;
    private String year1;
    private String month2;
    private String year2;
    
    private String[] type;
    
    private String output;
    private String id;
    private String reportAction;
    
    private String all;
    private String pageNum;
    
    private String prevista;
    private String enSupervision;
    private String dictada;

    public ActivityGeneralReportForm() {
        super();
        Calendar calendar = Calendar.getInstance();
        month1 = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        month2 = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        year1 = String.valueOf(calendar.get(Calendar.YEAR) - 1);
        year2 = String.valueOf(calendar.get(Calendar.YEAR));
        pageNum = "1";
        all = "false";
    }

    public ActivityGeneralReportForm(String divOrg11, String divOrg12,
            String divOrg13, String divOrg21, String divOrg22, String divOrg23,
            String divOrg31, String divOrg32, String divOrg33, String country1,
            String country2, String country3) {
        super(divOrg11, divOrg12, divOrg13, divOrg21, divOrg22, divOrg23,
                divOrg31, divOrg32, divOrg33, country1, country2, country3);
    }

    public ActivityGeneralReportForm(BookmarkData data) {
        super(data);
        Hashtable parameters = data.getParameters();
        if(parameters.containsKey("month1")) {
            month1 = (String)parameters.get("month1");
        }
        if(parameters.containsKey("year1")) {
            year1 = (String)parameters.get("year1");
        }
        if(parameters.containsKey("month2")) {
            month2 = (String)parameters.get("month2");
        }
        if(parameters.containsKey("year2")) {
            year2 = (String)parameters.get("year2");
        }
        type = getList(data,"type");
        if(parameters.containsKey("output")) {
            output = (String)parameters.get("output");
        }
        if(parameters.containsKey("id")) {
            id = (String)parameters.get("id");
        }
        if(parameters.containsKey("reportAction")) {
            reportAction = (String)parameters.get("reportAction");
        }
        if(parameters.containsKey("all")) {
            all = (String)parameters.get("all");
        }
        if(parameters.containsKey("pageNum")) {
            pageNum = (String)parameters.get("pageNum");
        }
    }   

    public String getMonth1() {
        return month1;
    }

    public String getMonth2() {
        return month2;
    }

    /*public String getType() {
        return type;
    }
    */
    public void setType(String type[]) {
        this.type = type;
    }    

    public String getYear1() {
        return year1;
    }

    public String getYear2() {
        return year2;
    }

    public void setMonth1(String month1) {
        this.month1 = month1;
    }

    public void setMonth2(String month2) {
        this.month2 = month2;
    }

    /*public void setType(String type) {
        this.type = type;
    }*/

    public String[] getType() {
		return type;
	}  
    
    public void setYear1(String year1) {
        this.year1 = year1;
    }

    public void setYear2(String year2) {
        this.year2 = year2;
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

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getId() {
        return id;
    }

    public String getReportAction() {
        return reportAction;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setReportAction(String reportAction) {
        this.reportAction = reportAction;
    }
    
    public String getLink() {
        String link = "";
        
        link += (!Util.empty(month1)) ? "&month1="+month1 : "";
        link += (!Util.empty(month2)) ? "&month2="+month2 : "";        
        link += (!Util.empty(year1)) ? "&year1="+year1 : "";
        link += (!Util.empty(year2)) ? "&year2="+year2 : "";

        //link += (!Util.empty(type)) ? "&type="+type : "";
        
        String strType ="";
        int i = 0;
        while (i< (type.length -1)) {
        	strType = strType + type[i] + ",";
        	i++;
        }
        strType += type[i];
        
        link += "&type="+strType;        
        link += "&dictada="+dictada;        
        link += "&prevista="+prevista;        
        link += "&ensupervision="+enSupervision;        
        
        link += (!Util.empty(output)) ? "&output="+output : "";
        link += super.getLink();
        return link;
    }
    
    
    private boolean isEmpty(String value) {
	    return ((value == null) || (value.trim().length()==0));
	}	 	   
    
	public String validate(UserSessionData userRequest) {

		// Tipo inválido
		String[] type = getType();
        if((type== null) || (type.length == 0) || ((type.length == 1) && type[0].equals("")) ) {
            return "activityexpiryquery.error.emptytype";
        }				
		/*
        if((getType()== null) || (getType().length == 0) || ((getType().length == 1) && ) ) {
            return "activityexpiryquery.error.emptytype";
        }
		*/
        
				        
	    // Fecha inicial válida		
        
        String day1 = "1";
        String day2 = "1";
	    if (anyEmpty(day1,month1,year1)) 
	    	return "generic.activity.activitydata.dateInvalid";
	    else {
		    if((! isEmpty(day1) && notNumber(day1.trim())) || 
		    		(! isEmpty(month1) && notNumber(month1.trim()) ||
		    		(! isEmpty(year1) && notNumber(year1.trim())))) {
		    	return "generic.activity.activitydata.dateInvalid";
		    }
	    }
	    
	    if (!isEmpty(day1) && !isEmpty(month1) && !isEmpty(year1)) {		    
		    if (!Util.checkDate(Integer.parseInt(day1.trim()),Integer.parseInt(month1.trim()),Integer.parseInt(year1.trim()))) {
		    	return "generic.activity.activitydata.dateInvalid";
		    }
	    }

	    // Fecha final válida
	    if (anyEmpty(day1,month2,year2)) 
	    	return "generic.activity.activitydata.dateInvalid";
	    else {
		    if((! isEmpty(day2) && notNumber(day2.trim())) || 
		    		(! isEmpty(month2) && notNumber(month2.trim()) ||
		    		(! isEmpty(year2) && notNumber(year2.trim())))) {
		    	return "generic.activity.activitydata.dateInvalid";
		    }
	    }
	    if (!isEmpty(day2) && !isEmpty(month2) && !isEmpty(year2)) {
	    	if (!Util.checkDate(Integer.parseInt(day2.trim()),Integer.parseInt(month2.trim()),Integer.parseInt(year2.trim()))) {
	    		return "generic.activity.activitydata.dateInvalid";
	    	}
	    	
	    }	
	    
	    Calendar lastDate =Calendar.getInstance();
	    lastDate.setTime(getLastDate());
	    Calendar initialDate = Calendar.getInstance();
	    initialDate.setTime(getInitialDate());
	    if (lastDate.compareTo(initialDate) < 0) {
			return "activityexpiryquery.error.periodInvalid";
	    }
	    	    
	    return null;
	}   
	
    private boolean notNumber(String value) {
    	
	    try {
	    	new Integer(value.trim());
	    }
	    catch (Exception e) {
	    	return true;
	    }	
	    return false;
	    }		
    
    private boolean anyEmpty(String value1,String value2,String value3) {
    	int any = 0;
    	if ((value1 == null) || (value1.trim().length()==0))
    		any ++;
    	if ((value2 == null) || (value2.trim().length()==0))
    		any ++;
    	if ((value3 == null) || (value3.trim().length()==0))
    		any ++;
    	return (any > 0);
	    //return ((any > 0 && any < 3));
	}
	
   public Date getInitialDate() {
	    String day1="1";
    	if (isEmpty(day1) || isEmpty(month1) || isEmpty(year1)) {
    		return null;
    	} else {
        	Calendar calendar = Calendar.getInstance();
    		calendar.set(Calendar.DATE,Integer.parseInt(day1.trim()));
    		calendar.set(Calendar.MONTH,Integer.parseInt(month1.trim())-1);
    		calendar.set(Calendar.YEAR,Integer.parseInt(year1.trim()));
            return calendar.getTime();
    	}
   } 	
   
   public Date getLastDate() {
       String day2="1";
	   if (isEmpty(day2) || isEmpty(month2) || isEmpty(year2)) {
	       return null;
	    } else {
	        Calendar calendar = Calendar.getInstance();
	    	calendar.set(Calendar.DATE,Integer.parseInt(day2.trim()));
	    	calendar.set(Calendar.MONTH,Integer.parseInt(month2.trim())-1);
	    	calendar.set(Calendar.YEAR,Integer.parseInt(year2.trim()));
	        return calendar.getTime();
	    }	    	
   }
 	   
   public BookmarkData getBookmark() {
       BookmarkData data = super.getBookmark(BookmarkData.ACTIVITY_GENERAL_REPORT);
       if(!Util.empty(month1)) {
           data.addParameter("month1",month1);
       }
       if(!Util.empty(year1)) {
           data.addParameter("year1",year1);
       }
       if(!Util.empty(month2)) {
           data.addParameter("month2",month2);
       }
       if(!Util.empty(year2)) {
           data.addParameter("year2",year2);
       }
       if(type != null && type.length > 0) {
           data.addParameter("type",getString(type));
       }
       if(!Util.empty(output)) {
           data.addParameter("output",output);
       }
       if(!Util.empty(id)) {
           data.addParameter("id",id);
       }
       if(!Util.empty(reportAction)) {
           data.addParameter("reportAction",reportAction);
       }
       if(!Util.empty(all)) {
           data.addParameter("all",all);
       }
       if(!Util.empty(pageNum)) {
           data.addParameter("pageNum",pageNum);
       }
       return data;
   }	
 	       
   private String getString(String[] list) {
       String s = "";
       for(int i = 0; i < list.length; i++) {
           s += list[i] + " ";
       }
       return s.trim();
   }

   private String[] getList(BookmarkData bookmark, String key) {
       Hashtable parameters = bookmark.getParameters(); 
       if(!parameters.containsKey(key)) {
          return new String[0]; 
       }else {
           Collection list = new LinkedList();
           StringTokenizer tokenizer = new StringTokenizer((String)parameters.get(key));
           while(tokenizer.hasMoreTokens()) {
               list.add(tokenizer.nextToken());
           }
           return (String[])list.toArray(new String[0]);
       }
   }
	
	public String getPrevista() {
		return prevista;
	}
	
	public void setPrevista(String prevista) {
		this.prevista = prevista;
	}
	
	public String getEnSupervision() {
		return enSupervision;
	}
	
	public void setEnSupervision(String enSupervision) {
		this.enSupervision = enSupervision;
	}
	
	public String getDictada() {
		return dictada;
	}
	
	public void setDictada(String dictada) {
		this.dictada = dictada;
	}

	public String getStates(Text messages) {
		if(!Util.isChecked(prevista) && !Util.isChecked(enSupervision) && !Util.isChecked(dictada))
			return messages.getText("generic.messages.all.pl");
		String s = "";
		if(Util.isChecked(prevista))
			s += messages.getText(CEPAActivityConstants.PREVISTA);
		if(Util.isChecked(enSupervision))
			s += (s.length() == 0) ? messages.getText(CEPAActivityConstants.ENSUPERVISION) : ", " + messages.getText(CEPAActivityConstants.ENSUPERVISION);
		if(Util.isChecked(dictada))
			s += (s.length() == 0) ? messages.getText(CEPAActivityConstants.DICTADA) : ", " + messages.getText(CEPAActivityConstants.DICTADA);
		return s;
	}

	public String getPeriod(Text messages, int period) {
		return (period == 1) ? messages.getText("generic.shortmonth."+month1)+"/"+year1 : messages.getText("generic.shortmonth."+month2)+"/"+year2;
	}
	
}
