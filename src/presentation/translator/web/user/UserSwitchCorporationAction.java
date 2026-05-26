/*
 * Created on 31-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.user;

import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.jboss.security.SimpleGroup;
import org.jboss.security.SimplePrincipal;
import org.jboss.security.plugins.JaasSecurityManager;

import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;
import business.administration.user.UserABMFacade;

import communication.administration.user.UserSessionData;
import communication.administration.user.tables.UserData;

/**
 * @author fcarriquiry
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UserSwitchCorporationAction extends AbstractAction {
		
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return mapping.findForward("cancel");
    }

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        session.removeAttribute("menuItems");
        UserSessionData userSession=sys.getUserSessionData();
        DynaActionForm formData = (DynaActionForm) form;

        Integer corporationId = new Integer((String)formData.get("corporation"));
        userSession.getFilter().setCorporationId(corporationId);
        UserABMFacade userABM = sys.getUserABMFacade();
        userABM.userSwitchCorporation(corporationId,sys.getUserSessionData());
        
        UserData user = sys.getUserReportFacade().findUserByPrimaryKey(userSession.getFilter().getLoginName(),userSession);
        sys.loadText();
        sys.updatePermissions(user);
        sys.updateFilter(user);
        Iterator newPerm = sys.getUserSessionData().getPermissions().iterator();
        
        JaasSecurityManager manager = getHome();
        Iterator it = manager.getActiveSubject().getPrincipals().iterator();
        while(it.hasNext()) {
            Object o = it.next();
            if(o instanceof SimpleGroup) {
                SimpleGroup group = (SimpleGroup)o;
                Enumeration enume = group.members();
                Collection principals = new LinkedList();
                while(enume.hasMoreElements()) {
                    principals.add(enume.nextElement());
                }
                Iterator itPri = principals.iterator();
                while(itPri.hasNext()) {
                    group.removeMember((Principal)itPri.next());
                }
                while(newPerm.hasNext()) {
                    group.addMember(new SimplePrincipal((String)newPerm.next()));
                }
            }
        }
        manager.flushCache();
        session.removeAttribute("Breadcrumb");
        session.removeAttribute("Bookmark");
        session.removeAttribute("lastQuery");
        return mapping.findForward("home");
    }

    protected Context getInitialContext() throws Exception {
        Hashtable props = new Hashtable();
        props.put(
            Context.INITIAL_CONTEXT_FACTORY,
            "org.jnp.interfaces.NamingContextFactory");
        props.put(
            Context.URL_PKG_PREFIXES,
            "org.jboss.naming:org.jnp.interfaces");
        props.put(Context.PROVIDER_URL, "jnp://localhost:1099");
        Context ctx = new InitialContext(props);
        return ctx;
    }

    /**
     * Get the home interface
     */
    protected JaasSecurityManager getHome() throws Exception {
        Context ctx = this.getInitialContext();
        JaasSecurityManager o = (JaasSecurityManager)ctx.lookup("java:/jaas/other");
        return o;
    }
}
