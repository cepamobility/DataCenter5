package presentation.translator.web.administration.corporation;

import java.util.Collection;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.GeoLabelAttributes;
import communication.administration.corporation.tables.LabelItemAttribute;
import communication.administration.user.UserSessionData;

public class CorporationCreateAction extends AbstractAction {


    public ActionForward cancel(HttpSession session, ActionMapping mapping, ActionForm form) {
        return mapping.findForward("cancel");
    }

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {
        
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();

        Collection texts = new LinkedList();
        CorporationForm corporationForm = (CorporationForm)form;
        
        String validate = corporationForm.validate();
        if(validate != null) {
            session.setAttribute("Msg",validate);
            return mapping.findForward("back");
        }
        String corporationKey = corporationForm.getCorporationName().toLowerCase().replace(' ','_');
        int corporationType = Integer.parseInt(corporationForm.getType());
        CorporationData corporation = new CorporationData(corporationForm.getCorporationName(),new Integer(corporationType));
        if(corporationType != CorporationData.CEPA_SYSTEM) {
	        for(int i = 1; i <= Integer.parseInt(corporationForm.getDivOrgCount()); i++) {
	            String key = "corporation."+corporationKey+"."+corporationForm.getEnglish(i).toLowerCase().replace(' ','_');
	            String[] s = {key,corporationForm.getSpanish(i),corporationForm.getEnglish(i),corporationForm.getPortuguese(i)};
	            texts.add(s);
	            DivOrgData divOrg = new DivOrgData(key,corporationForm.getLevelCount(i));
	            for(int j = 0; j < corporationForm.getLevelCount(i); j++) {
	                String key1 = "divorg."+corporationKey+"."+corporationForm.getEnglish(i,j+1).toLowerCase().replace(' ','_');
	                String[] s1 = {key1,corporationForm.getSpanish(i,j+1),corporationForm.getEnglish(i,j+1),corporationForm.getPortuguese(i,j+1)};
	                texts.add(s1);
	                Integer showLevel = (Util.isChecked(corporationForm.getShowLevel(i,j+1))) ? new Integer(1) : new Integer(0);
	                divOrg.getLabels()[j] = new LabelItemAttribute(key1,showLevel);
	            }
	            corporation.getDivOrgDataSet().add(divOrg);
	        }
        }
        corporation.createGeoLabels(corporationForm.getLevelCount(4));
        for(int j = 0; j < corporationForm.getLevelCount(4); j++) {
            String key1 = "divgeo."+corporationKey+"."+corporationForm.getEnglish(4,j+1).toLowerCase().replace(' ','_');
            String[] s1 = {key1,corporationForm.getSpanish(4,j+1),corporationForm.getEnglish(4,j+1),corporationForm.getPortuguese(4,j+1)};
            texts.add(s1);
            corporation.getGeoLabels()[j] = new GeoLabelAttributes(null,key1,new Integer(j+1));
        }
        
        sys.getCorporationABMFacade().corporationCreate(corporation,texts,sys.getUserSessionData());
        
        return null;
    }
}