package presentation.translator.web.activity.schedule;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import persistence.util.Util;
import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;
import business.activity.ActivityABMFacade;
import business.driver.DriverReportFacade;

import com.csvreader.CsvReader;
import communication.activity.tables.CEPAActivityConstants;
import communication.activity.tables.CEPAActivityScheduleData;
import communication.administration.user.UserSessionData;
import communication.driver.tables.DriverData;
import communication.language.Text;
import communication.security.SecurityConstants;


/**
 * @author adocarmo
 */
public class InternalActivityCreateAction extends AbstractAction {

	/**
	 * 
	 */	
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
        
		UserSessionData userSessionData = sys.getUserSessionData();
		
		ActivityForm createData = (ActivityForm)form;
		
		String message = null;
    	String fileName = SecurityConstants.IMAGES_WAR_DIRECTORY+"/internalbayermx"+System.currentTimeMillis()+".csv";			   			

		message = createData.validate(userSessionData);
		if(message != null && !message.equals("generic.activity.activitydata.commentInvalid")) {				
			userSessionData.setUrl("/activity/schedule/InternalActivityCreate.jsp");
			userSessionData.setMsg(messages.getText(message));
			return mapping.findForward("layout");		
		}
		
		FormFile file = (FormFile)createData.getFileUpload();		
		
		// Valido archivo de CepaTips vacio o nulo
		if (file == null || file.getFileName() == null || file.getFileName().equals("")){
			userSessionData.setUrl("/activity/schedule/InternalActivityCreate.jsp");
			userSessionData.setMsg(messages.getText("generic.supportmaterial.cepatips.nofile"));
			return mapping.findForward("layout");		
		}
		
		// Valido Nombre de Archivo
		if (!this.validarString(file.getFileName())){
			userSessionData.setMsg(messages.getText("generic.supportmaterial.cepatips.badfile"));
			userSessionData.setUrl("/activity/schedule/InternalActivityCreate.jsp");
			return mapping.findForward("layout");		
		}
		// Fin Valido Nombre de Archivo
		
		InputStream reportStream = file.getInputStream();
		FileOutputStream fout = new FileOutputStream(fileName);
		
		int c = 0;
		while ( (c=reportStream.read()) != -1){
			fout.write(c);
		}			
		fout.flush();
		fout.close();

        CsvReader reader = new CsvReader(fileName);		
		// ASIGNO SEPARADOR DE VALORES (; /t /n...etc) SI NO LO CAMBIO POR DEFECTO ES LA COMA
		reader.setDelimiter('\t');// /t , /n ...
		Collection<DriverData> activityDrivers = new LinkedList<DriverData>();
		DriverReportFacade driverReport = sys.getDriverReportFacade();
		int line = 1;
		while(reader.readRecord()) {
			String driverId = reader.get(0);
			if(Util.empty(driverId)) {
				userSessionData.setMsg("Línea vacía: "+line);
				userSessionData.setUrl("/activity/schedule/InternalActivityCreate.jsp");
				return mapping.findForward("layout");		
			}else {
				Collection drivers = driverReport.findDriverByCorporationId(driverId, userSessionData);
				if(drivers == null || drivers.size() == 0) {
					userSessionData.setMsg("Conductor ("+driverId+") no encontrado, línea: "+line);
					userSessionData.setUrl("/activity/schedule/InternalActivityCreate.jsp");
					return mapping.findForward("layout");		
				}else {
					activityDrivers.add((DriverData)drivers.iterator().next());
				}
			}
			line++;
		}

		CEPAActivityScheduleData activity = ActivityUtil.createCEPAActivityScheduleData(userSessionData,createData,false,sys);
		Integer activityId = sys.getActivityABMFacade().createActivity(activity,activityDrivers,createData.getComment(),userSessionData);					
		
		session.setAttribute("activityview",new Boolean(true));
		
		session.setAttribute("id",activityId.toString());
		
		// 	Clean session
		session.removeAttribute("ActivityForm");
		session.removeAttribute("cityJavascript");
		session.removeAttribute("typeJavascript");
		session.removeAttribute("diaJavascript");
		session.removeAttribute("mesJavascript");
		session.removeAttribute("anioJavascript");		
		
		userSessionData.setUrl("/success.jsp?entity=activity.create");
		return mapping.findForward("layout");				
	}
    
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
    	
    	SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();
     	userSessionData.setUrl("/activity/schedule/ActivityList.jsp");
     	
     	return mapping.findForward("layout");
    }    
	    
	public boolean validarString(String s){
		int count = 0;
		char c [] = s.toCharArray();
		while (count < s.length()){
			if (!Character.isLetterOrDigit(c[count]) && 
				c[count] != ' ' && c[count] != '.' 
				&& c[count] != '_' && c[count] != '-' ){			
				return false;
			}
			count ++;
		}
		return true;
	}
	

}
