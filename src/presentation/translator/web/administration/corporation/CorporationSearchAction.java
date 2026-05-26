/*
 * Created on 30-sep-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.administration.corporation;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import business.SystemAccess;
import business.administration.corporation.CorporationReportFacade;

import communication.exception.CommunicationProblemException;
import communication.exception.DeslogedException;
import communication.exception.InvalidDataException;

/**
 * @author fcarriquiry
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CorporationSearchAction extends Action {
	
    /* para testeo 
    public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();

		try {
			SystemAccess sys = (SystemAccess) session.getAttribute("SystemAccess");

			DynaActionForm userSearchForm = (DynaActionForm) form;
			String value = (String) userSearchForm.get("value");

			CorporationReportFacade crpReportFacade = sys.getCorporationReportFacade();
			
			CorporationData data = crpReportFacade.findCorporationByPrimaryKey(new Integer(value));
			
			showCorporation(data);
		}catch(Exception e) {
		    e.printStackTrace();
		}
		return mapping.findForward("login");
				
	}

    private void showCorporation(CorporationData data) {
        System.out.println("Id "+data.getId());
        System.out.println("Name "+data.getName());
        Iterator it = data.getDivOrgDataSet().iterator();
        while(it.hasNext()) {
            DivOrgData divOrgData = (DivOrgData)it.next();
            System.out.println(divOrgData.getName());
            imprimirLID(divOrgData.getDivOrgLevels(), "		");
        }
    }
    
    private void imprimirLID(LabelItemData lid,String tabs) {
        if(lid != null) {
            System.out.println(tabs+" |--> "+lid.getName());
            Iterator it = lid.getItems().iterator();
            while(it.hasNext()) {
                ItemData item = (ItemData)it.next();
                System.out.println(tabs+" 	|--> "+item.getName());
                imprimirLID(item.getSubItems(), tabs+"		");
            }
        }
    }
	    
	/**
	 * Form type: org.apache.struts.action.DynaActionForm
	 * 
	 * Form parameters:
	 * 					value: corporation name or similar   
	 * 					target: mapped name for requested page
	 */
	
	/* (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();

		try {
			
			DynaActionForm userSearchForm = (DynaActionForm) form;
			String value = (String) userSearchForm.get("value");
			String target = (String) userSearchForm.get("target");

			SystemAccess sys = (SystemAccess) session.getAttribute("SystemAccess");

			if (sys == null) {
				//IF NOT SystemAccess precent in session
				session.setAttribute("Msg","You need login for this operation");
				
				return mapping.findForward("error");
			}
			
			CorporationReportFacade crpReportFacade=null;
			
			//Access CorporationReportFacade
			
			try{
				
				crpReportFacade = sys.getCorporationReportFacade();
				
			}
			/*catch(RemoteException remote){
				session.setAttribute("Msg","Has ocurred error in communication server, remote exception, try again");
				
				return mapping.findForward("create");
			}*/
			catch(CommunicationProblemException com){
				session.setAttribute("Msg","Has ocurred error in communication server, communication exception, please contact CEPA");
				
				return mapping.findForward("create");
			}
			catch(DeslogedException des){
				session.setAttribute("Msg","You need login for this operation");
		
				return mapping.findForward("create");
			}
			
			
			//Find corporation like name
			Collection col=null;
			try{
				col = crpReportFacade.findCorporationByLikeName(value,sys.getUserSessionData());
			}
			catch(InvalidDataException invData){
				session.setAttribute("Msg",invData.getSystemMessage());
				
				return mapping.findForward("create");
			}
			catch(CommunicationProblemException com){
				session.setAttribute("Msg","Has ocurred error in communication server, communication exception, please contact CEPA");
				
				return mapping.findForward("create");
			}
			catch(DeslogedException des){
				session.setAttribute("Msg","You need login for this operation");
		
				return mapping.findForward("create");
			}
			
			//Load serch list in scopi session 
			
			session.setAttribute("corporationSearchList", col);
			
			return mapping.findForward(target);

		}
		catch (Exception e) {
			e.printStackTrace();
			
			session.setAttribute("Msg", "For security reason you are desloged");
		
			return mapping.findForward("error");
		}

		
		
	}
	

}
