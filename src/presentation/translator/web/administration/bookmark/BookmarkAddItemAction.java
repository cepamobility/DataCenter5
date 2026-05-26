/*
 * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.administration.bookmark;

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
public class BookmarkAddItemAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        DynaActionForm bookmarkForm = (DynaActionForm)form;
        BookmarkData data = (BookmarkData)request.getSession().getAttribute("Bookmark");
        data.setName(bookmarkForm.getString("name"));
        data.setStart(Util.isChecked(bookmarkForm.getString("start")));

        UserABMFacade userABM = sys.getUserABMFacade();
        userABM.addBookmarkItem(data,sys.getUserSessionData());
        return mapping.findForward("success");
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return mapping.findForward("sucess");
    }
}
