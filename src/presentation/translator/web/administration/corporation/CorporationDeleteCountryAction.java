/**
 * Created on 25-nov-2005
 * CEPA
 * DataCenter 5
 */
package presentation.translator.web.administration.corporation;

import java.util.Hashtable;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;
import business.administration.corporation.CorporationABMFacade;

import communication.administration.corporation.tables.CorporationData;
import communication.exception.InvalidDataException;
import communication.language.Text;

/**
 * @author jrodriguez
 */
public class CorporationDeleteCountryAction extends AbstractAction {

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {
        
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        CorporationData corporation = sys.getCorporationReportFacade().findCorporationByPrimaryKey(sys.getUserSessionData().getFilter().getCorporationId(),sys.getUserSessionData());
        CorporationCountryForm itemForm = (CorporationCountryForm)form;
        String level = itemForm.getLevel();

        try {
            String target=(String)session.getAttribute("target");
            session.removeAttribute("target");

            StringTokenizer items = null;
            String levelContainer = null;
    
            switch(Integer.parseInt(level)) {
                case 1:case 0:
                    items = new StringTokenizer(itemForm.getLabel1(),"<");
                    break;
                case 2:
                    levelContainer = itemForm.getLevel1();
                    items = new StringTokenizer(itemForm.getLabel2(),"<");
                    break;
                case 3:
                    levelContainer = itemForm.getLevel2();
                    items = new StringTokenizer(itemForm.getLabel3(),"<");
                    break;
            }
            
            Hashtable hashItems = new Hashtable();
            while(items.hasMoreTokens()) {
                hashItems.put(new Integer(items.nextToken()),"");
            }
            
            CorporationABMFacade corpABM = sys.getCorporationABMFacade();
            
            int levelId = (Integer.parseInt(level) == 0) ? 1 : Integer.parseInt(level);
            
            corpABM.corporationDeleteCountry(corporation, hashItems, levelId, levelContainer,sys.getUserSessionData());
    
            sys.loadFilter(sys.getUserSessionData().getFilter().getCorporationId());
            if(target != null) {
                return mapping.findForward(target);
            }
            return mapping.findForward("level"+level);
        }catch(Exception e) {
            Throwable thw = (e instanceof ServletException) ? ((ServletException)e).getRootCause() : e;
            while (thw.getCause() != null) {
                thw = thw.getCause();
            }
            if (thw.getClass().toString().indexOf("InvalidDataException") > 0) {
                session.setAttribute("Msg",messages.getText(((InvalidDataException)thw).getSystemMessage()));
                session.setAttribute("id",String.valueOf(corporation.getId()));
                return mapping.findForward("level"+level);
            }else {
                throw e;
            }
        }
    }

}
