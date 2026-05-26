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
import communication.administration.corporation.tables.GeoItemData;

/**
 * @author jrodriguez
 */
public class CorporationRenameCountryAction extends AbstractAction {

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        CorporationData corporation = sys.getCorporationReportFacade().findCorporationByPrimaryKey(sys.getUserSessionData().getFilter().getCorporationId(),sys.getUserSessionData());
        CorporationCountryForm itemForm = (CorporationCountryForm)form;
        
        String newName = itemForm.getLabel3();
        GeoItemData item = null;
        int modLevel = 0;
        if(!Util.empty(itemForm.getLevel1())) {
           item = corporation.getDivGeoDataSet().getItem(new Integer(itemForm.getLevel1()));
            if(Util.empty(itemForm.getLevel2())) {
                modLevel = (corporation.getGeoLabels().length == 3) ? 2 : 1; 
            }else {
               item = item.getSubItems().getItem(new Integer(itemForm.getLevel2()));
               if(Util.empty(itemForm.getLevel3())) {
                    modLevel = 1; 
               }
            }
        }
        
        CorporationABMFacade corpABM = sys.getCorporationABMFacade();
        if(item != null) {
            item.setName(newName); 
            corpABM.renameDivGeo(modLevel,item,sys.getUserSessionData());
        }
        
        sys.loadFilter(sys.getUserSessionData().getFilter().getCorporationId());
        return mapping.findForward("level"+itemForm.getLevel());
    }

}
