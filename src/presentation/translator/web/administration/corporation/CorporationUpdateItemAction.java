/**
 * Created on 25-nov-2005
 * CEPA
 * DataCenter 5
 */
package presentation.translator.web.administration.corporation;

import java.util.Hashtable;
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
public class CorporationUpdateItemAction extends AbstractAction {

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        CorporationAddItemForm itemForm = (CorporationAddItemForm)form;
        switch(Integer.parseInt(itemForm.getLevel())) {
            case 1:
                return mapping.findForward("edit");
            case 2:
                session.setAttribute("level1","");
                return mapping.findForward("level1");
            case 3:
                session.setAttribute("level2","");
                return mapping.findForward("level2");
            case 4:
                return mapping.findForward("level3");
        }
        return mapping.findForward("error");
    }

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        CorporationData corporation = sys.getCorporationReportFacade().findCorporationByPrimaryKey(sys.getUserSessionData().getFilter().getCorporationId(),sys.getUserSessionData());
        CorporationAddItemForm itemForm = (CorporationAddItemForm)form;
        
        StringTokenizer items = new StringTokenizer(itemForm.getLabel3(),"<");
        Hashtable hashItems = new Hashtable();
        while(items.hasMoreTokens()) {
            hashItems.put(new Integer(items.nextToken()),"");
        }

        sys.getCorporationABMFacade().corporationUpdateItem(corporation,new Integer(itemForm.getId()), hashItems, Integer.parseInt(itemForm.getLevel()),sys.getUserSessionData());
        
        sys.loadFilter(sys.getUserSessionData().getFilter().getCorporationId());
        return mapping.findForward("level"+itemForm.getLevel());
    }

}
