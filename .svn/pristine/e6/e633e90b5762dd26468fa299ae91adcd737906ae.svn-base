/*
 * Created on 19-ago-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.user;

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
import business.administration.user.UserABMFacade;

import communication.administration.user.tables.UserData;

/**
 * @author fcarriquiry
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class UserDeleteAction extends AbstractAction {

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return mapping.findForward("error");
    }

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        DynaActionForm userDeleteForm = (DynaActionForm) form;
        String stringList = (String) userDeleteForm.get("checkboxParamList");
        Collection colUid= stringToCollection(stringList);
        UserABMFacade userABM = sys.getUserABMFacade();
        Iterator iterColUid=colUid.iterator();
        while(iterColUid.hasNext()){
            String uid=(String) iterColUid.next();
            UserData userData=new UserData(null,null,null,null,uid,null,sys.getUserSessionData().getFilter().getCorporationId(),null,null,null); 
            userABM.userDelete(userData,sys.getUserSessionData());
        }   
        return mapping.findForward("UserNEDList");
    }

    private Collection stringToCollection(String stringList){
        String[] stringArray=stringList.split("<");
        Collection col=new LinkedList();
        
        for(int i=0; i < stringArray.length; i++){
            col.add(stringArray[i]);
        }
        
        return col;
    }

}