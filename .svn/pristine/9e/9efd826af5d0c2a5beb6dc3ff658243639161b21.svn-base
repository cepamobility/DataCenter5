package business.alert;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;

import persistence.alert.AlertABM;
import persistence.alert.AlertABMHome;
import persistence.alert.AlertABMUtil;
import persistence.alert.AlertReport;
import persistence.alert.AlertReportHome;
import persistence.alert.AlertReportUtil;
import business.alert.handler.Util;
import business.util.ExceptionHandler;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.user.UserSessionData;
import communication.alert.AlertData;
import communication.alert.FleetAlertReminderData;
import communication.datatype.tables.AlertTypeDataType;
import communication.exception.CreateBeanException;
import communication.exception.DeslogedException;
import communication.exception.InvalidDataException;
import communication.fleet.tables.FleetDivisionDetailData;

/**
 * @ejb.bean name="AlertABMFacade"
 *           display-name="Name for AlertABMFacade"
 *           description="Description for AlertABMFacade" 
 *           jndi-name="ejb/AlertABMFacade"
 *           type="Stateless"
 *           view-type="remote"
 * 			 transaction-type = "Container"
 * 
 * @ejb.ejb-ref ejb-name = "AlertABM" ref-name = "ejb/AlertABM" view-type =
 *              "remote"
 * 
 * @jboss.ejb-ref-jndi jndi-name = "ejb/AlertABM" ref-name = "AlertABM"
 * 
 * @ejb.util generate="physical"
 */

public abstract class AlertABMFacadeBean implements SessionBean {

    private ExceptionHandler handler = new ExceptionHandler(AlertABMFacadeBean.class);

	
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
	 * @ejb.interface-method view-type = "remote"
	 * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,alertview"
	 * @param data
	 * @throws InvalidDataException
	 * @throws DeslogedException
	 */
	public Integer createAlert(AlertData data,String comment,
			UserSessionData userSessionData) throws Exception {
		try{
			AlertABM alertABM=AlertABMUtil.getHome().create();
			Integer alertId = alertABM.createAlert(data,comment,userSessionData);
			return alertId;
			
		} catch (Exception e) {
			Object[] parameters = new Object[2];
			parameters[0] = data;
			parameters[1] = comment;
			handler.handleException("createAlert", e, userSessionData,parameters);
        }	
		return null;
	}

	/**
	 * @ejb.interface-method view-type = "remote"
	 * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,alertview,fleetinsertdata"
	 * @param data
	 * @throws InvalidDataException
	 * @throws DeslogedException
	 */
	
	public void updateAlert(AlertData data,String comment, 
			UserSessionData userSessionData) throws Exception {
		try{
			
			if (userSessionData == null) {
				throw new DeslogedException("Class business.alert.AlertABMFacade(updateAlert(AlertData)), " +
						"session data is empty", "generic.error.deslogued");
			}
			if (data == null) {
				throw new InvalidDataException("Class business.alert.AlertABMFacade(updateAlert(AlertData)), " +
						"alert data is empty","generic.alert.isempty");
			}
			AlertABM alertABM=AlertABMUtil.getHome().create();
			alertABM.updateAlert(data, comment, userSessionData);
			
			
		} catch (Exception e) {
			Object[] parameters = new Object[2];
			parameters[0] = data;
			parameters[1] = comment;
			handler.handleException("updateAlert", e, userSessionData, parameters);
		}	
	}
	
	
	/**
	 * @ejb.interface-method view-type = "remote"
	 * @ejb.transaction type="Required" 
     * @ejb.permission role-name = "administrator,alertview"
	 * @param data
	 * @throws InvalidDataException
	 * @throws DeslogedException
	 */
	
	public  void deleteAlert(AlertData data, String comment) throws Exception {
		
		try{			
			AlertABM alertABM=AlertABMUtil.getHome().create();
			alertABM.deleteAlert(data,comment);
			
		}catch(Exception e){
			Object[] parameters = new Object[2];
			parameters[0] = data;
			parameters[1] = comment;
			handler.handleException("DeletAlert", e,null,parameters);
			
		}
		
	}
	
	
	

	
	/**
	 * @ejb.permission role-name = "administrator,alertview"
	 * @ejb.interface-method
	 */
	public void finalizeAlertsWithExtraInformation(String idVehicle,String type,String[] information,UserSessionData userSessionData)throws Exception {
		try{
			AlertABM alertABM=AlertABMUtil.getHome().create();
			alertABM.finalizeAlertsWithExtraInformation(idVehicle,type,information,userSessionData);
				
				
		}catch(Exception e){
			Object [] parameters = new Object[3];
			parameters[0] = idVehicle;
			parameters[1] = type;
			parameters[2] = information;
			handler.handleException("finalizeAlertsWithExtraInformation",e, userSessionData, parameters);
		}
		
	}
	
	/**
	 * @ejb.permission role-name = "administrator,alertview"
	 * @ejb.interface-method
	 */
	public void finishAlert(Integer idAlert,UserSessionData userSessionData)throws Exception {
		try{
			AlertABM alertABM=AlertABMUtil.getHome().create();
			alertABM.finishAlert(idAlert,userSessionData);			
				
		}catch(Exception e){
			Object [] parameters = new Object[1];
			parameters[0] = idAlert;
			handler.handleException("finishAlert",e, userSessionData, parameters);
		}
		
	}
	
	/**
	 * 
	 * @param s
	 * @return
	 */
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
     * @ejb.permission role-name = "administrator,alertview,fleetinsertdata"
	 */	
	public void stopCorporationReminderAlert(FleetDivisionDetailData detail,
			UserSessionData userSessionData) throws Exception {
		try{
			Integer corporation=userSessionData.getFilter().getCorporationId();
			Integer country=detail.getFleetDivision().getCountryId();
			Integer divOrg21=null;
			Integer divOrg22=null;
			Integer divOrg23=null;
			AlertTypeDataType alertType;
			FleetAlertReminderData reminder=null;
			Integer [] divOrgIds=detail.getFleetDivision().getDivOrgIds();
			if(corporation.intValue()==CorporationData.JJ){
				reminder= new FleetAlertReminderData(null,null,corporation,country,divOrgIds[1],divOrgIds[0],null);
			}else if(corporation.intValue()==CorporationData.MONSANTO){
				reminder= new FleetAlertReminderData(null,null,corporation,country,divOrg21,divOrg22,divOrg23);
			}else if(corporation.intValue()==CorporationData.YOLI){
				reminder= new FleetAlertReminderData(null,null,corporation,null,divOrg21,divOrg22,divOrg23);
			}
			if((corporation.intValue()==CorporationData.MONSANTO)||
					(corporation.intValue()==CorporationData.JJ)
				||(corporation.intValue()==CorporationData.YOLI)){
				
				AlertABMHome home = AlertABMUtil.getHome();
				AlertABM alertABM = home.create();
				
				AlertReportHome reportHome = AlertReportUtil.getHome();
				AlertReport alertReport = reportHome.create();
				
				List alerts = alertReport.getCorporationReminderAlert(reminder,userSessionData);
				if (alerts != null){
					Iterator alertsIterator = alerts.iterator(); 
					AlertData alert=null;
					Date nextdate=null;
					while(alertsIterator.hasNext()) {
						alert = (AlertData)alertsIterator.next();
						if (alert != null){
							nextdate = getNextDateReminder(alert);
							alert.setNextdate(nextdate);
							alertABM.updateAlert(alert,"",userSessionData);
						}					
						alert = null;
						nextdate = null;
					}
				}
				
			}
		
			
		} catch(Exception e){
			Object[] parameters = new Object[1];
			parameters[0] = detail;
			handler.handleException("stopCorporationReminderAlert",e, userSessionData, parameters);
		}
	}
	
	
	
	
	
	/**
	 * @ejb.interface-method
     * @ejb.permission role-name = "administrator,alertview"
	 */	
	public void createUpdateContractorsMails(Collection mails,UserSessionData userSessionData) throws Exception {
		try{
			
			Iterator it = mails.iterator();
			AlertABM alertABM=AlertABMUtil.getHome().create();
			while (it.hasNext()){
				String[] values = ((String)it.next()).split("	");
				if (values.length==2){
					alertABM.createUpdateContractorMail(values,userSessionData);
				}
			}
		}catch(Exception e){
			Object[] parameters = new Object[1];
			parameters[0] = mails;
			handler.handleException("updateContractorsMails",e, userSessionData, parameters);
		}
	}
}


