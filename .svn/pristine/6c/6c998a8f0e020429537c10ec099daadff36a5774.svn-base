/*
 * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.cenapra;

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
import business.SystemAccess;

import communication.activity.tables.CEPAActivityConstants;
import communication.activity.tables.CheckListData;
import communication.activity.tables.PointData;
import communication.administration.corporation.tables.CorporationData;
import communication.cenapra.tables.ActivityData;
import communication.language.Text;

/**
 * @author jrodriguez
 */
public class CheckListAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        DynaActionForm checkForm = (DynaActionForm)form;
        String registryId = checkForm.getString("registry");
        String note = checkForm.getString("note");
        String number = checkForm.getString("number");
        
        Collection[] items = new LinkedList[3];

        String type = checkForm.getString("type");
        int[] checks = {0,22,0};
		if(sys.getCorporationId() == CorporationData.IMESEVI_UY) {
			checks[1] += 30;
		}
        switch(Integer.parseInt(type)) {
		    case CEPAActivityConstants.MULTIPLICADORES: case CEPAActivityConstants.FORMADORES:
		        checks[0] = 32;
		        checks[2] = 33;
		        break;
		    case CEPAActivityConstants.POBLACION_META:
		        checks[0] = 30;
		        checks[2] = 31;
		        break;
		    case CEPAActivityConstants.MULTIPLICADORES_NEW:  case CEPAActivityConstants.FORMADORES_VIEJO:
		        checks[0] = 25;
		        checks[2] = 26;
		        break;
		    case CEPAActivityConstants.MULTIPLICADORES_OLD: case CEPAActivityConstants.REPLICADORES:
		        checks[0] = 20;
		        checks[2] = 21;
		        break;
		    case CEPAActivityConstants.POBLACION_META_OLD:
		        checks[0] = 23;
		        checks[2] = 24;
		        break;
        }

        for(int i = 0; i < 3; i++) {
            items[i] = new LinkedList();
            CheckListData checkList = sys.getActivityReportFacade().findCheckList(new Integer(checks[i]),sys.getUserSessionData());
            Iterator it = checkList.getPoints().iterator();
            while(it.hasNext()) {
                String value = request.getParameter("item"+String.valueOf(((PointData)it.next()).getId()));
                try {
                    items[i].add(new Integer(value));
                }catch (Exception e) {
                    session.setAttribute("Msg", messages.getText("cenapra.activity.emptyvalues"));
                    session.setAttribute("registry",registryId);
                    return mapping.findForward("back");
                }
            }
        }

        sys.getCENAPRAABMFacade().saveActivityRegistryValues(items,new Integer(registryId),checkForm.getString("observations"),checks,new Integer(note),number,sys.getUserSessionData());
        session.setAttribute("activity",checkForm.getString("activity"));
		return mapping.findForward("success");
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        session.setAttribute("activity",((DynaActionForm)form).getString("activity"));
        return mapping.findForward("cancel");
    }
}
