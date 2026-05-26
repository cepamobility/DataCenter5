package presentation.translator.web.driver;

import java.util.Calendar;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
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
import business.administration.corporation.CorporationABMFacade;

import communication.administration.corporation.tables.PointAccountData;
import communication.administration.corporation.tables.PointMoveData;
import communication.administration.corporation.tables.PointsDefinitionData;
import communication.administration.user.UserSessionData;

public class ConfirmWorkAccidentUploadAction extends AbstractAction {

	static final int EOF = 65535;
	
	/**
	 * 
	 */	
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

				
		HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSession = sys.getUserSessionData();
    
		try {
			int valids = Integer.parseInt(((DynaActionForm) form).getString("valids"));

			Hashtable <Integer,PointAccountData> accounts = new Hashtable <Integer,PointAccountData>();
			Hashtable <Integer,Collection<PointMoveData>> moves = new Hashtable <Integer,Collection<PointMoveData>>(); 
			HashMap map = sys.getCorporationReportFacade().getPointsDefinition(new String[]{PointsDefinitionData.ACCIDENTE_LABORAL_CON_BAJA,PointsDefinitionData.ACCIDENTE_LABORAL_SIN_BAJA,PointsDefinitionData.ACCIDENTE_LABORAL_NO_CONFORME}, userSession);
			for(int i = 1; i <= valids; i++) {
				Integer driver = new Integer(request.getParameter("driver"+i));
				Calendar date = Util.getDate(request.getParameter("date"+i));
				String definition = request.getParameter("definition"+i);
				String id = request.getParameter("id"+i);

				if(!accounts.containsKey(driver)) {
					accounts.put(driver, sys.getCorporationReportFacade().getPointAccount(PointAccountData.DRIVER, driver, sys.getUserSessionData()));
				}
				if(moves.containsKey(driver)) {
					moves.get(driver).add(new PointMoveData((PointsDefinitionData)map.get(definition), date, id));
				}else {
					Collection<PointMoveData> list = new LinkedList<PointMoveData>();
					list.add(new PointMoveData((PointsDefinitionData)map.get(definition), date, id));
					moves.put(driver, list);
				}
			}
			Enumeration<Integer> keys = accounts.keys();
			CorporationABMFacade corporationABM = sys.getCorporationABMFacade();
			while(keys.hasMoreElements()) {
				Integer driver = keys.nextElement();
				corporationABM.addMoves(accounts.get(driver), moves.get(driver), sys.getUserSessionData());
			}
			switch(valids) {
				case 0:
					sys.getUserSessionData().setValue("No se ingresaron accidentes laborales");
					break;
				case 1:
					sys.getUserSessionData().setValue("Se ingresó 1 accidente laboral");
					break;
				default:
					sys.getUserSessionData().setValue("Se ingresaron "+valids+" accidentes laborales");
					break;
			}
			sys.getUserSessionData().setUrl("/driver/successUploadWA.jsp");
			return mapping.findForward("next");	
			
	    } catch (Exception io){
			sys.getUserSessionData().setUrl("/driver/insertWA.jsp");
	  	    return mapping.findForward("next");	
		}  
	}

	@Override
	public ActionForward cancel(HttpSession session, ActionMapping mapping,
			ActionForm form) {
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
		sys.getUserSessionData().setUrl("/driver/insertWA.jsp");
        return mapping.findForward("next");	
	}
	
}

