/**
 * Created on 25-nov-2005
 * CEPA
 * DataCenter 5
 */
package presentation.translator.web.administration.corporation;

import java.util.Collection;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;

import communication.administration.corporation.tables.CorporationData;

/**
 * @author jrodriguez
 */
public class CorporationUpdateCountryAction extends AbstractAction {

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        switch(Integer.parseInt(((CorporationCountryForm)form).getLevel())) {
            case 1:
                return mapping.findForward("edit");
            case 2:
                session.setAttribute("level1","");
                return mapping.findForward("level1");
            case 3:
                session.setAttribute("level2","");
                return mapping.findForward("level2");
        }
        return mapping.findForward("error");
    }

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {
        
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        CorporationData corporation = sys.getCorporationReportFacade().findCorporationByPrimaryKey(sys.getUserSessionData().getFilter().getCorporationId(),sys.getUserSessionData());
        CorporationCountryForm itemForm = (CorporationCountryForm)form;
        
        StringTokenizer tokenizer = new StringTokenizer(itemForm.getLabel3(),"<");
        Collection countries = new LinkedList();
        while(tokenizer.hasMoreTokens()) {
            countries.add(Integer.valueOf((String)tokenizer.nextToken()));
        }
        itemForm.setLabel3(null);

        sys.getCorporationABMFacade().corporationUpdateCountry(corporation,countries,sys.getUserSessionData());
        sys.loadFilter(sys.getUserSessionData().getFilter().getCorporationId());
        
        return mapping.findForward("level"+itemForm.getLevel());
    }
}
