/*
 * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.administration.bookmark;

import java.util.Collection;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;
import business.administration.user.UserABMFacade;

import communication.administration.bookmark.tables.BookmarkData;

/**
 * @author jrodriguez
 */
public class BookmarkDeleteItemAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        Collection delete = new LinkedList();
        String[][] bookmarks = sys.getUserReportFacade().getBookmark(sys.getUserSessionData());
        for(int i = 0; i < bookmarks.length; i++) {
            if(Util.isChecked(request.getParameter("bookmark"+bookmarks[i][0]))){
                delete.add(bookmarks[i][0]);
            }
        }

        UserABMFacade userABM = sys.getUserABMFacade();
        userABM.deleteBookmarkItems(delete,sys.getUserSessionData());
    	return mapping.findForward("success");
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return mapping.findForward("sucess");
    }
}
