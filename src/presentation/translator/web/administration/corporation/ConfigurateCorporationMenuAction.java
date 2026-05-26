/*
 * Created on 24-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.administration.corporation;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

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
import business.datatype.DataTypeReportFacade;

import communication.datatype.tables.AccidentClasificationDataType;
import communication.datatype.tables.FineQualifyDataType;
import communication.datatype.tables.IncidentClasificationDataType;

public class ConfigurateCorporationMenuAction extends AbstractAction {
	
    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        String list = ((DynaActionForm)form).getString("list");
        StringTokenizer tokenizer = new StringTokenizer(list,",");
        Collection<Integer> itemsSelected = new LinkedList<Integer>(); 
        while(tokenizer.hasMoreTokens()) {
            itemsSelected.add(new Integer(tokenizer.nextToken()));   
        }

        sys.getCorporationABMFacade().configurateMenu(itemsSelected,sys.getUserSessionData());
        sys.getUserSessionData().setUrl("menu_configuration.jsp");
        return mapping.findForward("ok");
        
    }
    
    
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }

} 
    


