package presentation.translator.web.activity.schedule;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;
import business.activity.ActivityABMFacade;
import business.activity.ActivityReportFacade;
import business.driver.DriverReportFacade;

import communication.activity.tables.CEPAActivityConstants;
import communication.activity.tables.CEPAActivityScheduleData;
import communication.activity.tables.CEPAActivityScheduleRegistryData;
import communication.administration.user.UserSessionData;
import communication.driver.tables.DriverData;

/**
 * @author jrodriguez
 */
public class AddDriverToActivityAction extends AbstractAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
        
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
		UserSessionData userSessionData = sys.getUserSessionData();		
		
        DynaActionForm activityRegistryForm = (DynaActionForm) form;			
		String stringList = activityRegistryForm.getString("checkboxParamList");
		String activity = activityRegistryForm.getString("activity");
        String idActivity = ( Util.isNumber(activity) ) ? activity :"0";           

        String note = "";
		boolean hasResult = false;		
		String obs = "";
		String instructor = "";
		if(userSessionData.isCepaSystemUser()) {
			instructor = userSessionData.getFullName(); 
		}
		Collection<String> colUid= stringToCollection(stringList);
		
		// Comentary Drive:
		String partner = "";
		boolean cepaCourse = true;
		String conductionType = "";
		String plate= "";
		boolean novato= false;		
		boolean phrd = false;
		boolean teoricoasistence= true;
		boolean practicoasistence= true;		
				
		ActivityReportFacade activityReport = sys.getActivityReportFacade();
		//CEPAActivityScheduleData activityData = new CEPAActivityScheduleData(new Integer(idActivity));		
		CEPAActivityScheduleData activityData = activityReport.
			findCEPAActivityScheduleByPrimaryKey(new Integer(idActivity),userSessionData);
		
		if (activityData != null &&
			activityData.getDefinition() != null && 
			!activityData.getDefinition().getHasForm()) {
			note = "generic.activity.note.withoutnote";
			hasResult = true;
		}
		
		// TODO: validar si idDriver es number, porque salta muy seguido una excepciotn de numberformatexpetion
		
		DriverReportFacade driverReport = sys.getDriverReportFacade();
		ActivityABMFacade activityABM = sys.getActivityABMFacade();
		Iterator<String> iterColUid=colUid.iterator();
		while(iterColUid.hasNext()){
			String idDriver=(String) iterColUid.next();
			DriverData driverData=new DriverData(new Integer(idDriver));
			if (!activityReport.existsRegistry(new Integer(idActivity), new Integer(idDriver),sys.getUserSessionData())) {
				CEPAActivityScheduleRegistryData registryData =  
					new CEPAActivityScheduleRegistryData(activityData,driverData,
					null,note,null,obs,instructor, cepaCourse, partner, conductionType, 
					plate, null,"", new Integer(-1), new Integer(-1), null,null, 
					"",false,false,false,false,
					"","",novato,teoricoasistence,practicoasistence,hasResult,false,false,0,phrd,0,null,null);
				activityABM.createCEPAScheduleActivityRegistry(registryData,userSessionData,false);
				
				/*
				 * Si la actividad es:
				 * 		commentartydrive (Monsanto), 
				 * 		commentarydriveargentina (Monsanto),
				 * 		commentarydriverisk (Monsanto) 
				 * recargo divorg y divgeo con los divorg y divgeo
				 * del driver y hago update de la actividad
				*/
				String activityType = (activityData != null && activityData.getType() != null)?
						activityData.getType().getResourceBundleId():null; 
				
				if ( activityType != null && (
					activityType.equals(CEPAActivityConstants.COMMENTARYDRIVE) ||
					activityType.equals(CEPAActivityConstants.COMMENTARYDRIVEHIGHRISK) ||
					activityType.equals(CEPAActivityConstants.COMMENTARYDRIVEARGENTINA))) {
					
					driverData = driverReport.findDriverByPrimaryKey(new Integer(idDriver),userSessionData);
					activityData.setCountrygeo(driverData.getCountry());
					activityData.setDivOrgSet(driverData.getDivOrgs());
					activityABM.updateCEPAScheduleActivity(activityData,"",userSessionData);
				}				
				
			}	
		}
		
        session.removeAttribute("AddDriverToActivityForm");
        session.setAttribute("id",idActivity);
		return mapping.findForward("ActivityView");
	}	

	public Collection<String> stringToCollection(String stringList){
		String[] stringArray=stringList.split("<");
		Collection<String> col=new LinkedList<String>();
		for(int i=0; i < stringArray.length; i++){
			col.add(stringArray[i]);
		}
		return col;
	}
	
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return mapping.findForward("ActivityList");
    }	

}
