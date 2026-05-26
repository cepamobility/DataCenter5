package business.alert;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;

import persistence.alert.AlertABM;
import persistence.alert.AlertABMUtil;
import persistence.alert.AlertReport;
import persistence.alert.AlertReportUtil;
import business.alert.handler.Util;
import business.util.ExceptionHandler;

import communication.administration.corporation.tables.ItemData;
import communication.administration.user.UserSessionData;
import communication.alert.AlertConstants;
import communication.alert.AlertData;
import communication.alert.ContractorDataData;
import communication.exception.CreateBeanException;
import communication.language.Text;
import communication.util.ListResult;

/**
 * @ejb.bean name="AlertReportFacade"
 *           display-name="Name for AlertReportFacade"
 *           description="Description for AlertReportFacade" 
 *           jndi-name="ejb/AlertReportFacade"
 *           type="Stateless"
 *           view-type="remote"
 * 			 transaction-type = "Container"
 * 
 * @ejb.ejb-ref ejb-name = "AlertReport" ref-name = "ejb/AlertReport" view-type =
 *              "remote"
 * 
 * @jboss.ejb-ref-jndi jndi-name = "ejb/AlertReport" ref-name = "AlertReport"
 * 
 * @ejb.util generate="physical"
 */

public abstract class AlertReportFacadeBean implements SessionBean {

    private ExceptionHandler handler = new ExceptionHandler(AlertReportFacadeBean.class);

	
	/**
	 * @ejb.permission role-name = "administrator,fleetinsertdata,alertview"
	 * Default create method
	 * 
	 * @throws CreateException
	 * @ejb.create-method
	 */
	public void ejbCreate()
			throws CreateBeanException {
			
	}
	
	
	/**
     * @ejb.permission role-name = "administrator,alertview"
     * @ejb.interface-method
     */
	public ListResult findAlertList(String startDay,String startMonth,String startYear,
			String endDay,String endMonth,String endYear,String type,String priority,
			String email,Integer finalized,int first,int count,UserSessionData userSessionData) throws Exception{
	    ListResult result = null;
	    try{
	    	
	    	AlertReport alertReport = AlertReportUtil.getHome().create();
            
	    	result = alertReport.findAlertList(startDay,startMonth,startYear,endDay,endMonth,endYear,type,priority,email,finalized,first,count,userSessionData);
	  
	    } catch(Exception e){           
	    	Object[] parameters = new Object[12];
	    	parameters[0] = startDay;
	    	parameters[1] = startMonth;
	    	parameters[2] = startYear;
	    	parameters[3] = endDay;
	    	parameters[4] = endMonth;
	    	parameters[5] = endYear;
	    	parameters[6] = type;
	    	parameters[7] = priority;
	    	parameters[8] = email;
	    	parameters[9] = finalized;
	    	parameters[10] = first;
	    	parameters[11] = count;
	        handler.handleException("findAlertList",e,userSessionData, parameters);
	    }
	    return result;
	}
	
	/**
     * @ejb.permission role-name = "administrator,alertview"
     * @ejb.interface-method
     */
	public  AlertData findAlertByType(Integer corporation,String alertType,UserSessionData userSessionData) throws Exception {
		
		AlertData alertData = null;
		
		try{
			AlertReport alertReport = AlertReportUtil.getHome().create();
			alertData = alertReport.findAlertByType(corporation,alertType,userSessionData);
				
		}catch(Exception e){
			Object[] parameters = new Object[2];
			parameters[0] = corporation;
			parameters[1] = alertType;
			handler.handleException("findAlertByType",e, userSessionData, parameters);
		}
		
		return alertData;
	}
	
	/**
     * @ejb.permission role-name = "administrator,alertview"
     * @ejb.interface-method
     */
	public  AlertData findAlertByPrimaryKey(Integer alertPrimaryKey,UserSessionData userSessionData) throws Exception {
		
		AlertData alertData = null;
		
		try{
			AlertReport alertReport = AlertReportUtil.getHome().create();
			alertData = alertReport.findAlertByPrimaryKey(alertPrimaryKey,userSessionData);
				
		}catch(Exception e){
			Object[] parameters = new Object[1];
			parameters[0] = alertPrimaryKey;
			handler.handleException("findAlertByPrimaryKey",e, userSessionData, parameters);
		}
		
		return alertData;
	}
	
	/**
     * @ejb.permission role-name = "administrator,alertview"
     * @ejb.interface-method
     */
	public  Collection getTodayAlert() throws Exception {
		
		Collection result = null;
		
		try{	
			AlertReport alertReport = AlertReportUtil.getHome().create();
			result = alertReport.getTodayAlert();

		}catch(Exception e){
			handler.handleException("getTodayAlert", e,null,null);
		}
		return result;
	}
	
	/**
     * @ejb.permission role-name = "administrator,alertview"
     * @ejb.interface-method
     */
	public  AlertData findAlertWithExtraInformation(String type,String extrainformation,UserSessionData userSessionData) throws Exception {
		
		AlertData alertData = null;
		
		try{
			AlertReport alertReport = AlertReportUtil.getHome().create();
			alertData = alertReport.findAlertWithExtraInformation(type,extrainformation,userSessionData);
				
		}catch(Exception e){
			Object[] parameters = new Object[2];
			parameters[0] = type;
			parameters[1] = extrainformation;			                                 
			handler.handleException("findAlertWithExtraInformation", e, userSessionData, parameters);
		}
		
		return alertData;
	}
	
	/**
     * @ejb.permission role-name = "administrator,alertview"
     * @ejb.interface-method
     */
	public  Collection getTo(Integer corporation,int alertType,UserSessionData userSessionData) throws Exception {
		Collection result =null;
		try {
			AlertReport alertReport = AlertReportUtil.getHome().create();
			result = alertReport.getTo(corporation,alertType,userSessionData);
	
		} catch (Exception e) {
			Object[] parameters = new Object[2];
			parameters[0] = corporation;
			parameters[1] = alertType;
			handler.handleException("getTo", e, userSessionData, parameters);
		}
		return result;
	}
	

	/**
	 * @ejb.permission role-name = "administrator,alertview"
	 * @ejb.interface-method
	 */
	public List getAlerts(String alerts,UserSessionData userSessionData) throws Exception {
		List result = new LinkedList();
		try{
			AlertReport alertReport = AlertReportUtil.getHome().create();
			result = alertReport.getAlerts(alerts,userSessionData);
				
		}catch(Exception e){
			Object[] parameters = new Object[1];
			parameters[0] = alerts;
			handler.handleException("getAlerts",e, userSessionData, parameters);
		}
		
		return result;
	}
	
	
	
	
	private String convertString(String s){		
		StringBuffer aux = new StringBuffer();
    	int count = 0;
    	//aux.append(s.substring(0,0).toUpperCase());
    	while (count < s.length())
    	{
    		// Por más codigos referise a:
    		// http://webdesign.about.com/library/bl_htmlcodes.htm
    		switch (s.charAt(count)){
    			case (char)225: aux.append("&aacute;");  
    				break;
    			case (char)233: aux.append("&eacute;");  
    				break;
    			case (char)237: aux.append("&iacute;"); 
    				break;
    			case (char)243: aux.append("&oacute;"); 
    				break;
    			case (char)250: aux.append("&uacute;"); 
    				break;    			
    			case (char)231: aux.append("&ccedil;"); 
    				break;
    			case (char)228: aux.append("&auml;"); 
    				break;
    			case (char)227: aux.append("&atilde;");
    				break;
    			case (char)226: aux.append("&acirc;");
    				break;
    			case (char)229: aux.append("&aring;");
    				break;
    			case (char)196: aux.append("&Auml;");
    				break;
    			case (char)230: aux.append("&aelig;");
    				break;
    			case (char)241: aux.append("&ntilde;");
					break;
    			case (char)186: aux.append("&ordm;");
					break;
    			case (char)170: aux.append("&ordf;");
					break;
    			// Masyusculas
    			case (char)193: aux.append("&Aacute;"); 
    				break;
    			case (char)201: aux.append("&Eacute;"); 
    				break;
    			case (char)205: aux.append("&Iacute;"); 
    			    break;
    			case (char)211: aux.append("&Oacute;"); 
    				break;
    			case (char)218: aux.append("&Uacute;");
    				break;
    			case (char)199: aux.append("&Ccedil;"); 
    				break;
    			case (char)195: aux.append("&Atilde;");
    				break;
    			case (char)209: aux.append("&Ntilde;"); 
					break;
    			default: aux.append(String.valueOf(s.charAt(count)));
    				break;
   	    		// Por mas codigos referise a:
   	    		// http://webdesign.about.com/library/bl_htmlcodes.htm
    		}
    		count ++;
    	}
    	return aux.toString();
		
	}
	
	/**
	 * 
	 * @param alertData
	 * @return
	 */
	private  Date getNextDate(AlertData alertData){
		Date nextDate=alertData.getNextdate();
		Date lastDate= alertData.getLastdate();
		int dia = 1 * (24 * 60 * 60 * 1000);
		int dias2=2 * (24 * 60 * 60 * 1000);
		int addDay;
		Date nextDate2=new Date(nextDate.getTime()+dias2);
		if((lastDate.after(nextDate2))||(lastDate.equals(nextDate2))){
			nextDate= new Date(lastDate.getTime()-dia);
		}else if(lastDate.before(nextDate2)){
			String frecuency = alertData.getFrecuency().getResourceBundleId();
			if (frecuency.equals(AlertConstants.ALERT_FRECUENCY_DAILY))
				addDay=1 * (24 * 60 * 60 * 1000);
			else if(frecuency.equals( AlertConstants.ALERT_FRECUENCY_WEEKLY))
				addDay=7 * (24 * 60 * 60 * 1000);
			else if (frecuency.equals( AlertConstants.ALERT_FRECUENCY_MONTHLY))
				addDay=30 * (24 * 60 * 60 * 1000);
			else
				addDay=365 * (24 * 60 * 60 * 1000);
			
			nextDate=new Date(nextDate.getTime()+addDay);
			
		}
		return nextDate;

	}
	
	/**
	 * 
	 * @param alertData
	 * @return
	 */
	private Date getNextDateReminder(AlertData alertData){
		Date nextDate=null;
		Date startDate= alertData.getStartdate();
		Date today = new Date();
		Calendar todaycal=Util.getCalendar(today);
		Calendar startDateCal=Util.getCalendar(startDate);
		int monthToday=(todaycal.get(Calendar.MONTH)+1);
		int yearToday=todaycal.get(Calendar.YEAR);
		int dayStartDay=startDateCal.get(Calendar.DAY_OF_MONTH);
		if (monthToday<12){
			nextDate=Util.getDate(String.valueOf(dayStartDay), String.valueOf((monthToday+1)),String.valueOf(yearToday));
		}else{
			nextDate=Util.getDate(String.valueOf(dayStartDay), String.valueOf(1),String.valueOf(yearToday+1));

		}
		
		return nextDate;
	}
	
	private String formatDate(Date date) {
        if(date == null) {
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.YEAR);
    }
	
	private  String getBodyCompleteCheckList(Collection itemsList,String type,String vehiclePlate, Text messages)throws Exception{
	  String body="";
	  try{
		body="<html>"+
		"<head>"+
		"</head>"+
		"<body>"+
			"<table align=\"center\" cellspacing=\"0\" cellpadding=\"2\" border=\"0\" width=\"700px\">"+
				"<tr bgcolor=\"#a0a0ff\" >"+
		    	"		<td align=\"center\" colspan=\"3\"><font color=\"white\" size=\"4\"><strong>"+convertString(messages.getText("generic.alert.data"))+"-"+convertString(messages.getText("generic.event.eventdata.plate"))+": "+vehiclePlate+"</strong></font></td>"+
				"</tr>"+
				"<tr bgcolor=\"#a0c0c0\">"+
		    	"		<td width=\"60%\" align=\"left\" ><font color=\"white\" size=\"3\"><strong>"+convertString(messages.getText("alert.checklist.items"))+"</strong></font></td>"+
		    	"		<td width=\"20%\" align=\"left\"><font color=\"white\" size=\"3\"><strong>"+convertString(messages.getText("generic.alert.search.priority"))+"</strong></font></td>"+
		    	"		<td width=\"20%\" align=\"left\"><font color=\"white\" size=\"3\"><strong>"+convertString(messages.getText("generic.alert.search.lastdate"))+"</strong></font></td>"+
				"</tr>";
		Iterator it = itemsList.iterator();
		int count=0;
		String background;
		while(it.hasNext()) {
			String[] values = ((String)it.next()).split("	");
				if (count==0){
					background="#F7F7F7";
					count=1;
				}else{
					background="#F1F1E1";
					count=0;
				}
				body+="<tr bgcolor="+background+">"+
		    	"<td width=\"60%\" align=\"left\"><font size=\"2\">"+convertString(messages.getText(values[0]))+"</font></td>"+
		    	"<td width=\"20%\" align=\"left\"><font size=\"2\">"+convertString(messages.getText(values[1]))+"</font></td>"+
		    	"<td width=\"20%\" align=\"left\"><font size=\"2\">"+convertString(messages.getText(values[2]))+"</font></td>"+
				"</tr>";
		}
		/*if(type.equals(AlertData.ALERT_TRUCK)||type.equals(AlertData.ALERT_TRAILER)||type.equals(AlertData.ALERT_COMPLETE_TRAILER)||type.equals(AlertData.ALERT_COMPLETE_TRUCK)){
			body+="<tr>" +
			"<td><a htref=\"https://www.cepadc.com/datacenter/layout.jsp?refer=/vehicle/TruckCheckView.jsp?check=2784\">"messages.getText("generic.contact")+;	
		}else if (type.equals(AlertData.ALERT_VEHICLE){
			
		}*/
		body+="</table>"+
		"</body>"+
		"</html>";
	  }catch(Exception e){
		  	Object [] parameters = new Object[4];
		  	parameters[0] = itemsList;
		  	parameters[1] = type;
		  	parameters[2] = vehiclePlate;
		  	parameters[3] = messages;
			handler.handleException("getBodyCompleteCheckList",e, null, parameters);
	  }
		return body;
	}
	
//	/**
//	 * @ ejb.interface-method
//   * @ ejb.permission role-name = "administrator,alertview"
//	 */	
//	public void vehicleChecklistAlertGenerate(String type,Collection itemsDate, Collection emailDate,Integer vehicleId,String vehiclePlate,String trailerPlate, String comment,
//			Text messages,UserSessionData userSessionData) throws Exception {
//
//	// Agrego Alerta checklist
//		try{
//			AlertData alertData;
//			AlertTypeDataType alertType;
//			AlertPriorityDataType alertPriority;
//			AlertFrecuencyDataType alertFrecuency;
//			AlertExtraInformationData alertdata;
//			String emailValue;
//			Date nextdate;
//			Collection extradata= new LinkedList();
//			Collection itemsBody= new LinkedList();
//			Collection itemBody=new LinkedList();
//			Collection to= new LinkedList();
//			int count=0;
//			String body="";
//			String[] information = new String [itemsDate.size()];
//			int corporationId=userSessionData.getFilter().getCorporationId().intValue();
//			Iterator itdate = itemsDate.iterator(); 
//			Iterator emaildate = emailDate.iterator(); 
//			String subjectpunctual=null;
//			String subjectgeneral=null;
//			if(trailerPlate!=null){
//			  subjectpunctual= messages.getText(type)+"-"+messages.getText("generic.event.eventdata.plate")+": "+trailerPlate+" ("+vehiclePlate+")"+" ("+messages.getText("generic.alert.data.punctual")+")";
//			  subjectgeneral=messages.getText(type)+"-"+messages.getText("generic.event.eventdata.plate")+": "+trailerPlate+" ("+vehiclePlate+")";
//			}else{
//				subjectpunctual= messages.getText(type)+"-"+messages.getText("generic.event.eventdata.plate")+": "+vehiclePlate+" ("+messages.getText("generic.alert.data.punctual")+")";
//				subjectgeneral=messages.getText(type)+"-"+messages.getText("generic.event.eventdata.plate")+": "+vehiclePlate;
//			}
//			
//			AlertABMHome home = AlertABMUtil.getHome();
//			AlertABM alertABM = home.create();
//			// generamos una alerta puntual para cada item nuevo y actualizamos las alertas ya existentes.
//			alertType = new AlertTypeDataType(type);
//			while(itdate.hasNext()) {
//				extradata.clear();
//				String[] values = ((String)itdate.next()).split("	");
//				if(values.length == 5) {
//					information[count]=(vehicleId.toString()+"-"+values[0]);
//					while(emaildate.hasNext()) {
//						emailValue=(String)emaildate.next();
//						AlertToData alertto= new AlertToData(null,corporationId,emailValue);
//						to.add(alertto);
//					}
//					alertData=alertABM.findAlertWithExtraInformation(type,vehicleId.toString()+"-"+values[0],userSessionData);
//					alertPriority = new AlertPriorityDataType(values[2]);
//					alertFrecuency = new AlertFrecuencyDataType(values[3]);
//					
//        	
//					if (alertData==null){
//						alertdata=new AlertExtraInformationData(null,alertType,vehicleId.toString()+"-"+values[0]);
//						extradata.add(alertdata);
//						itemsBody.add(values[4]+"	"+values[2]+"	"+formatDate(new Date(values[1])));
//						itemBody.add(values[4]+"	"+values[2]+"	"+formatDate(new Date(values[1])));
//						int dia = 1 * (24 * 60 * 60 * 1000);
//						long lastdate=new Date(values[1]).getTime();
//						nextdate=new Date(lastdate-dia);
//						if (trailerPlate!=null)
//							body=getBodyCompleteCheckList(itemBody,type,trailerPlate+" ("+vehiclePlate+")",messages);
//						else
//							body=getBodyCompleteCheckList(itemBody,type,vehiclePlate,messages);
//						itemBody.clear();
//						alertABM.createAlert(new AlertData(null,alertType,subjectpunctual,to,extradata,new Date(),new Date(values[1]),nextdate,null,body,corporationId,alertPriority,alertFrecuency,new Integer(0),new Integer(AlertData.CHECKLIST_TYPE)),comment,userSessionData);
//					}else{
//						//resto un dia, antes del update a la fecha limite para cargar nextdate
//						nextdate= getNextDate(alertData);
//						alertData.setFrecuency(alertFrecuency);
//						alertData.setPriority(alertPriority);
//						alertData.setNextdate(nextdate);
//						alertData.setLastdate(new Date(values[1]));
//						alertABM.updateAlert(alertData,comment,userSessionData);
//					}
//        	
//				}
//				count++;
//			}
//			
//			// crear alerta inicial con todos los campos incorrectos(nuevos) del checklist.
//			if(itemsBody.size()>0){
//				alertType=new AlertTypeDataType(AlertData.ALERT_GENERAL);
//				alertPriority = new AlertPriorityDataType(AlertData.ALERT_PRIORITY_LOW);
//				alertFrecuency = new AlertFrecuencyDataType(AlertData.ALERT_FRECUENCY_YEARLY);
//				if(AlertData.ALERT_TRAILER.equals(type)){
//					alertdata=new AlertExtraInformationData(null,alertType,AlertData.ALERT_COMPLETE_TRAILER);
//				}else if(AlertData.ALERT_TRUCK.equals(type)){
//					alertdata=new AlertExtraInformationData(null,alertType,AlertData.ALERT_COMPLETE_TRUCK);
//				}else{
//					alertdata=new AlertExtraInformationData(null,alertType,AlertData.ALERT_COMPLETE_VEHICLE);
//				}
//				extradata.clear();
//				extradata.add(alertdata);
//				String bodyCompleteMail=null;
//				if (trailerPlate!=null)
//					bodyCompleteMail=getBodyCompleteCheckList(itemsBody,type,trailerPlate+" ("+vehiclePlate+")",messages);
//				else
//					bodyCompleteMail=getBodyCompleteCheckList(itemsBody,type,vehiclePlate,messages);
//				Date today = new Date();
//				alertABM.createAlert(new AlertData(null,alertType,subjectgeneral,to,extradata,today,today,today,null,bodyCompleteMail,corporationId,alertPriority,alertFrecuency,new Integer(0),new Integer(AlertData.CHECKLIST_TYPE)),comment,userSessionData);
//			}
//			alertABM.finalizeAlertsWithExtraInformation(vehicleId.toString(),type,information,userSessionData);
//			
//		}catch(Exception e){
//			Object[] parameters = new Object[8];
//			parameters[0] = type;
//			parameters[1] = itemsDate;
//			parameters[2] = emailDate;
//			parameters[3] = vehicleId;
//			parameters[4] = vehiclePlate;
//			parameters[5] = trailerPlate;
//			parameters[6] = comment;
//			parameters[7] = messages;
//			handler.handleException("vehicleChecklistAlertGenerate",e, userSessionData, parameters);
//		}
//	}
	
	
	
	
	
	/**
	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,alertview"
	 */	
	public Hashtable getContractorsMail(LinkedList items,UserSessionData userSessionData) throws Exception {
		Hashtable listresult = new Hashtable();
		Hashtable result= new Hashtable();
		Collection mailId= new LinkedList();
		try{
			AlertReport alertReport = AlertReportUtil.getHome().create();
			Integer corporation = userSessionData.getFilter().getCorporationId();
			listresult = alertReport.getContractorsMail(userSessionData);
			
			for(int i = 0; i < items.size(); i++) {
				ItemData item = (ItemData)items.get(i);
				ContractorDataData contractor=new ContractorDataData(item.getId());
				contractor.setState(new Integer(0));
				contractor.setCorporation(corporation);
				if (listresult.containsKey(item.getId())){
					ContractorDataData contractorsaved = (ContractorDataData)listresult.get(item.getId());
					contractor.setMail(contractorsaved.getMail());
					mailId.add(item.getId());
				}
				result.put(item.getName(),contractor);
			}
			
			//AlertABM alertABM = AlertABMUtil.getHome().create();
			//alertABM.deleteContractorMail(mailId,userSessionData);
			
			return result;
		}catch(Exception e){
			Object[] parameters = new Object[1];
			parameters[0] = items;
			handler.handleException("getCorporationMail",e, userSessionData, parameters);
		}
		return null;
	}
	
	
}


