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

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.user.UserSessionData;
import communication.exception.InvalidDataException;
import communication.language.Text;

/**
 * @author jrodriguez
 */
public class CorporationDeleteItemAction extends AbstractAction {


    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
        UserSessionData userSession=sys.getUserSessionData();
        CorporationData corporation = sys.getCorporationReportFacade().findCorporationByPrimaryKey(userSession.getFilter().getCorporationId(),userSession);
        String newLabel1 = null;
        String newLabel2 = null;
        CorporationAddItemForm itemForm = (CorporationAddItemForm)form;
        try {
            
            String target=(String)session.getAttribute("target");
            session.removeAttribute("target");
            
            StringTokenizer items = new StringTokenizer(itemForm.getLabel3(),"<");

            String divOrg = itemForm.getId();
            DivOrgData divOrgData = corporation.getDivOrgData(Integer.parseInt(divOrg));
            int intLevel = divOrgData.getLabels().length;
            String level1 = itemForm.getLevel1();
            String level2 = itemForm.getLevel2();
            switch(Integer.parseInt(itemForm.getLevel())) {
                case 2:
                    ItemData itemData = divOrgData.getDivOrgLevels().getItem(new Integer(level1));
                    if(itemData.getSubItems().getItems().size() == items.countTokens()){
                        newLabel1 = itemData.getName();
                    }
                    break;
                case 3:
                    itemData = divOrgData.getDivOrgLevels().getItem(new Integer(level1));
                    if(itemData.getSubItems().getItems().size() == 1){
                        newLabel1 = itemData.getName();
                    }
                    ItemData itemData2 = itemData.getSubItems().getItem(new Integer(level2));
                    if(itemData2.getSubItems().getItems().size() == items.countTokens()){
                        newLabel2 = itemData2.getName();
                    }
                    break;
            }    
            switch(Integer.parseInt(itemForm.getLevel())) {
                case 3:
                    intLevel = 1;
                    break;
                case 2:
                    intLevel -= 1;
                    break;
            }
            
            Hashtable hashItems = new Hashtable();
            while(items.hasMoreTokens()) {
                hashItems.put(new Integer(items.nextToken()),"");
            }
            
            sys.getCorporationABMFacade().corporationDeleteItem(corporation,new Integer(divOrg), hashItems, intLevel,userSession);

            if(newLabel1 != null) {
                session.setAttribute("newLabel1",newLabel1);
            }
            if(newLabel2 != null) {
                session.setAttribute("newLabel2",newLabel2);
            }
            sys.loadFilter(sys.getUserSessionData().getFilter().getCorporationId());
            if(target != null) {
                return mapping.findForward(target);
            }
            return mapping.findForward("level"+String.valueOf(itemForm.getLevel()));
            
        }catch(Exception e) {
            Throwable thw = (e instanceof ServletException) ? ((ServletException)e).getRootCause() : e;
            while (thw.getCause() != null) {
                thw = thw.getCause();
            }
            if (thw.getClass().toString().indexOf("InvalidDataException") > 0) {
                session.setAttribute("Msg",messages.getText(((InvalidDataException)thw).getSystemMessage()));
                session.setAttribute("id",String.valueOf(corporation.getId()));
                return mapping.findForward("level"+itemForm.getLevel());
            }else {
                throw e;
            }
        }
    }

}
