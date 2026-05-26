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
public class BookmarkUpdateItemAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        BookmarkUpdateForm bookmarkForm = (BookmarkUpdateForm)form;
        BookmarkData data = new BookmarkData();
        data.setId(new Integer(bookmarkForm.getId()));
        data.setName(bookmarkForm.getName());
        data.setStart(Util.isChecked(bookmarkForm.getStart()));

        UserABMFacade userABM = sys.getUserABMFacade();
        userABM.udpateBookmarkItem(data,sys.getUserSessionData());
    	return mapping.findForward("success");
	}

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return mapping.findForward("success");
    }
}
