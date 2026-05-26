/*
 * Created on 24-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.activity.schedule;

//import java.sql.Date;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;

import presentation.translator.web.util.Util;
import business.SystemAccess;
import business.activity.ActivityReportFacade;

import communication.activity.tables.CEPAActivityDefinitionData;
import communication.activity.tables.CEPAActivityScheduleData;
import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.DivOrgAttribute;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.GeoLabelData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.corporation.tables.LabelItemData;
import communication.administration.user.UserSessionData;
import communication.language.Text;

/**
 * @author adocarmo
 */
public class ActivityUtil {
	
	public static final int VIEW = 0;
	public static final int EDIT = 1;
	
	
	/**
	 * 
	 * @param userSession
	 * @param createData
	 * @param isOld
	 * @return
	 */
	public static CEPAActivityScheduleData createCEPAActivityScheduleData(UserSessionData userSession,
			ActivityForm createData, boolean isOld, SystemAccess sys) throws Exception{	
	    
		//System.out.println("ENTRE A CREATEactivityDATA");
		
		GeoLabelData countrygeo = null;
        Collection items1 = new LinkedList();
        
	    if(createData.getCountry2() == null) {
	    	// En el nombre de la división geográfica guarda un string vacío. En las capas de abajo,
	    	// este campo lo carga hibernate a partir del id que viene en el form 
	        items1.add(new GeoItemData(Integer.valueOf(createData.getCountry1()),"",true,null));
	        countrygeo = new GeoLabelData(null,null,null,items1);
	    }else{
	        if(createData.getCountry3() == null) {
		        Collection items2 = new LinkedList();
		    	// En el nombre de la división geográfica guarda un string vacío. En las capas de abajo,
		    	// este campo lo carga hibernate a partir del id que viene en el form 		        
		        items2.add(new GeoItemData(Integer.valueOf(createData.getCountry2()),"",true,null));
		        GeoLabelData level1 = new GeoLabelData(null,null,null,items2);
		    	// En el nombre de la división geográfica guarda un string vacío. En las capas de abajo,
		    	// este campo lo carga hibernate a partir del id que viene en el form 		        
		        items1.add(new GeoItemData(Integer.valueOf(createData.getCountry1()),"",true,level1));
	        }else {
		        Collection items3 = new LinkedList();
		    	// En el nombre de la división geográfica guarda un string vacío. En las capas de abajo,
		    	// este campo lo carga hibernate a partir del id que viene en el form 		        
		        items3.add(new GeoItemData(Integer.valueOf(createData.getCountry3()),"",true,null));
		        GeoLabelData level2 = new GeoLabelData(null,null,null,items3);
		        
		        Collection items2 = new LinkedList();
		        items2.add(new GeoItemData(Integer.valueOf(createData.getCountry2()),"",true,level2));
		        GeoLabelData level1 = new GeoLabelData(null,null,null,items2);
		        
		        items1.add(new GeoItemData(Integer.valueOf(createData.getCountry1()),"",true,level1));
	            
	        }
	    }
        countrygeo = new GeoLabelData(null,null,null,items1);
        CEPAActivityDefinitionData definition = new CEPAActivityDefinitionData(createData.getType());

        Integer id = null;
        if(createData.getId() != null) {
            id = new Integer(createData.getId());
        }
        
        DivOrgAttribute[] divOrgs = userSession.getFilter().getDivOrgsAttrs();
        Collection list = new LinkedList();
        
        // Arma rama del arbol correspondiente a los 3 niveles de la primera división organizativa
        if(divOrgs[0] != null) {
            DivOrgAttribute divOrgAttribute = divOrgs[0];
            LabelItemData labelData = getDivOrgData(createData.getDivOrg11(),createData.getDivOrg12(),createData.getDivOrg13());
            if(labelData != null) {
                list.add(new DivOrgData(divOrgAttribute.getId(),divOrgAttribute.getName(),labelData));
            }
        }
        
        // Arma rama del arbol correspondiente a los 3 niveles de la segunda división organizativa
        if(divOrgs[1] != null) {
            DivOrgAttribute divOrgAttribute = divOrgs[1];
            LabelItemData labelData = getDivOrgData(createData.getDivOrg21(),createData.getDivOrg22(),createData.getDivOrg23());
            if(labelData != null) {
                list.add(new DivOrgData(divOrgAttribute.getId(),divOrgAttribute.getName(),labelData));
            }
        }
        
        // Arma rama del arbol correspondiente a los 3 niveles de la tercera división organizativa
        if(divOrgs[2] != null) {
            DivOrgAttribute divOrgAttribute = divOrgs[2];
            LabelItemData labelData = getDivOrgData(createData.getDivOrg31(),createData.getDivOrg32(),createData.getDivOrg33());
            if(labelData != null) {
                list.add(new DivOrgData(divOrgAttribute.getId(),divOrgAttribute.getName(),labelData));
            }
        }              
      	      
        Integer participants = Util.isNumber(createData.getParticipants()) ? new Integer(createData.getParticipants()) : null;
        Date dateExpiry = getDateExpiry(createData.getType(),createData.getDate(),createData.getCountry2(),sys);
        Integer duration = null;
        if(Util.isNumber(createData.getDurMinute())) {
        	duration = new Integer(createData.getDurMinute());
        	if(Util.isNumber(createData.getDurHour())) {
        		duration += Integer.parseInt(createData.getDurHour()) * 60;
        	}
        }
        return new CEPAActivityScheduleData(list,countrygeo,createData.getState(),id,definition,createData.getType(),
        		new CorporationData(userSession.getFilter().getCorporationId(),"",null,null,null,null),
        		createData.getCity(),createData.getDate(),isOld,dateExpiry,0,createData.getObservations(),participants, createData.getTheme(), duration, createData.getFile());        
	}   
	
	
	/**
	 * Arma una rama de un arbol con 3 niveles de profundidad
	 */
	public static LabelItemData getDivOrgData(String item1,String item2,String item3) {
	    Collection items1 = new LinkedList();
	    if(Util.empty(item2)) {
	        items1.add(new ItemData(Integer.valueOf(item1),"",true,null));
	    }else{
	        if(Util.empty(item3)) {
		        Collection items2 = new LinkedList();
		        items2.add(new ItemData(Integer.valueOf(item2),"",true,null));
		        LabelItemData level1 = new LabelItemData(null,null,items2,null);
		        items1.add(new ItemData(Integer.valueOf(item1),"",true,level1));
	        }else {
		        Collection items3 = new LinkedList();
		        items3.add(new ItemData(Integer.valueOf(item3),"",true,null));
		        LabelItemData level2 = new LabelItemData(null,null,items3,null);
		        
		        Collection items2 = new LinkedList();
		        items2.add(new ItemData(Integer.valueOf(item2),"",true,level2));
		        LabelItemData level1 = new LabelItemData(null,null,items2,null);
		        
		        items1.add(new ItemData(Integer.valueOf(item1),"",true,level1));
	            
	        }
	    }
        return new LabelItemData(null,null,items1,null);	    
	}
	
	/**
	 * @param licenseExpiryMonth
	 * @return
	 */
	public static boolean notEmpty(String value) {
		return (value != null && !value.trim().equals(""));
	}
	
	/*
	public static boolean PuedeModificarSegunEstado (String activityState,String userRole) { 
		
		if (userRole.equals("administrator"))
			 return true;							
		if (activityState.equals("datatype.scheduledactivitystate.prevista"))
				 return true;
		if (((activityState.equals("datatype.scheduledactivitystate.ensupervision"))  
				|| (activityState.equals("datatype.scheduledactivitystate.dictada"))) 
				&& (userRole.equals("manager")))
			 return true;			
		return false;		
	}		
*/
	public static ActivityForm createActivityForm(CEPAActivityScheduleData data, int function, Text messages) {
		ActivityForm activityForm = new ActivityForm();
		activityForm.setId(String.valueOf(data.getActivityId()));
		
		Calendar date = Calendar.getInstance();
		date.setTime(data.getDate());
		activityForm.setDay(String.valueOf(date.get(Calendar.DATE)));
		//activityForm.setMonth(String.valueOf(date.get(Calendar.MONTH))); 
		activityForm.setMonth(String.valueOf(date.get(Calendar.MONTH)+1));		
		activityForm.setYear(String.valueOf(date.get(Calendar.YEAR)));
	    activityForm.setFile(data.getFile());
	    
	    GeoItemData geoItem1 = (GeoItemData)data.getCountrygeo().getItemIterator().next();
	    activityForm.setCountry1(String.valueOf(geoItem1.getId()));
	    if(geoItem1.getSubItems() != null) {
		    GeoItemData geoItem2 = (GeoItemData)geoItem1.getSubItems().getItemIterator().next();
		    activityForm.setCountry2(String.valueOf(geoItem2.getId()));
		    if(geoItem2.getSubItems() != null) {
			    GeoItemData geoItem3 = (GeoItemData)geoItem2.getSubItems().getItemIterator().next();
			    activityForm.setCountry3(String.valueOf(geoItem3.getId()));
		    }
        }
	    
		activityForm.setCity(data.getCity());	    

	    Iterator it = data.getDivOrgSet().iterator();
		int i = 1;
		while(it.hasNext()) {
		    DivOrgData divOrgData = (DivOrgData)it.next();
		    ItemData item1 = (ItemData)divOrgData.getDivOrgLevels().getItemIterator().next();
		    activityForm.setDivOrg(String.valueOf(item1.getId()),i,1);
		    if(item1.getSubItems() != null) {
			    ItemData item2 = (ItemData)item1.getSubItems().getItemIterator().next();
			    activityForm.setDivOrg(String.valueOf(item2.getId()),i,2);
			    if(item2.getSubItems() != null) {
				    ItemData item3 = (ItemData)item2.getSubItems().getItemIterator().next();
				    activityForm.setDivOrg(String.valueOf(item3.getId()),i,3);
			    }
		    }
		    i++;
		}
		
		if(function == EDIT) {
			if (data.getType() != null) 
				activityForm.setType(data.getType().getResourceBundleId());
			if (data.getState() != null) 
				activityForm.setState(data.getState().getResourceBundleId());					
		}else {			
			if (data.getType() != null)
				activityForm.setType(messages.getText(data.getType().getResourceBundleId()));
			if (data.getState() != null)
				activityForm.setState(messages.getText(data.getState().getResourceBundleId()));
		}
		if(data.getParticipants() != null) {
			activityForm.setParticipants(String.valueOf(data.getParticipants()));
		}
		activityForm.setTheme(data.getTheme());
		if(data.getDuration() != null && data.getDuration() > 0) {
			activityForm.setDurHour(String.valueOf(data.getDuration()/60));
			activityForm.setDurMinute(String.valueOf(data.getDuration()%60));
		}
		return activityForm;
	}
	
    public static Date getDateExpiry(String type,Date date, String country, SystemAccess sys) throws Exception
    {
    	UserSessionData userRequest = sys.getUserSessionData();
    	
    	ActivityReportFacade activityReport = sys.getActivityReportFacade();
    	int timeExpiry = 0;
    	timeExpiry = activityReport.getTimeExpiry(type,country,userRequest);
    	if(timeExpiry > 0 && userRequest.isCorporation(CorporationData.TGS))
    		timeExpiry = 5;
        Date dateExpiry = null;
        
        
        // Si la actividad no expira el campo dateExpiry vale null
        if (timeExpiry > 0) {
        	Calendar calendar = Calendar.getInstance();
        	calendar.setTime(date);
        	calendar.add(Calendar.YEAR,+timeExpiry);
        	dateExpiry = calendar.getTime();
        }	
       
        return (dateExpiry);
    }
    
    public static int getYearsToExpiry(String type,Date date, UserSessionData userRequest, SystemAccess sys) throws Exception
    {
    	
    	ActivityReportFacade activityReport = sys.getActivityReportFacade();
    	int timeExpiry = 0;
    	timeExpiry = activityReport.getTimeExpiry(type,"",userRequest);
       
       
        return (timeExpiry);
    }
	
    public static Hashtable getParameters(HttpServletRequest request) {
    	Hashtable parameters = new Hashtable();
		parameters.put("startDay",(Util.empty(request.getParameter("startDay"))) ? "" : request.getParameter("startDay"));
		parameters.put("startMonth",(Util.empty(request.getParameter("startMonth"))) ? "" : request.getParameter("startMonth"));
		parameters.put("startYear",(Util.empty(request.getParameter("startYear"))) ? "" : request.getParameter("startYear"));
		parameters.put("endDay",(Util.empty(request.getParameter("endDay"))) ? "" : request.getParameter("endDay"));
		parameters.put("endMonth",(Util.empty(request.getParameter("endMonth"))) ? "" : request.getParameter("endMonth"));
		parameters.put("endYear",(Util.empty(request.getParameter("endYear"))) ? "" : request.getParameter("endYear"));	
		parameters.put("type",(Util.empty(request.getParameter("type"))) ? "" : request.getParameter("type"));
		parameters.put("country",(Util.empty(request.getParameter("country"))) ? "" : request.getParameter("country"));
		parameters.put("city",(Util.empty(request.getParameter("city"))) ? "" : request.getParameter("city"));
		parameters.put("status",(Util.empty(request.getParameter("status"))) ? "" : request.getParameter("status"));
		return parameters;
    }
    	
}
