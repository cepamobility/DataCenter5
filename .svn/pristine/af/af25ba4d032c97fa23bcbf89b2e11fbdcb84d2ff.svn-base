/**
 * Created on 25-nov-2005
 * CEPA
 * DataCenter 5
 */
package presentation.translator.web.administration.corporation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;
import business.administration.corporation.CorporationABMFacade;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.ItemData;

/**
 * @author jrodriguez
 */
public class CorporationRenameItemAction extends AbstractAction {

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {
        
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        CorporationData corporation = sys.getCorporationReportFacade().findCorporationByPrimaryKey(sys.getUserSessionData().getFilter().getCorporationId(),sys.getUserSessionData());
        CorporationAddItemForm itemForm = (CorporationAddItemForm)form;
        
        String newName = itemForm.getLabel3();
        
        DivOrgData divOrgData = corporation.getDivOrgData(Integer.parseInt(itemForm.getId()));
        if(!Util.empty(itemForm.getLevel1())) {
           ItemData item1 = divOrgData.getDivOrgLevels().getItem(new Integer(itemForm.getLevel1()));
            if(Util.empty(itemForm.getLevel2())) {
               item1.setName(newName); 
            }else {
               ItemData item2 = item1.getSubItems().getItem(new Integer(itemForm.getLevel2()));
               if(Util.empty(itemForm.getLevel3())) {
                   item2.setName(newName); 
               }else{
                   ItemData item3 = item2.getSubItems().getItem(new Integer(itemForm.getLevel3()));
                   item3.setName(newName); 
               }
            }
        }
        
        CorporationABMFacade corpABM = sys.getCorporationABMFacade();
        corpABM.corporationUpdate(corporation,sys.getUserSessionData());

        sys.loadFilter(sys.getUserSessionData().getFilter().getCorporationId());
        
        return mapping.findForward("level"+itemForm.getLevel());
    }

}
