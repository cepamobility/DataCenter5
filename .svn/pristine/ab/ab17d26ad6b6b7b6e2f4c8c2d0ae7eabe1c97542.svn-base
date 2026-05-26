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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;
import business.datatype.DataTypeReportFacade;

import communication.datatype.tables.AccidentClasificationDataType;
import communication.datatype.tables.FineQualifyDataType;
import communication.datatype.tables.IncidentClasificationDataType;

public class ConfigurateCorporationAction extends AbstractAction {
	
    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));

        DataTypeReportFacade dataTypeReport = sys.getDataTypeReportFacade();        

        Collection finesIn = new LinkedList();
        Iterator datatypeIt = dataTypeReport.findAll(communication.datatype.DataTypeConstants.ALL_FINE_QUALIFY,sys.getUserSessionData()).iterator();
        while(datatypeIt.hasNext()) {
            FineQualifyDataType data = (FineQualifyDataType)datatypeIt.next();
            if(Util.isChecked(request.getParameter(data.getResourceBundleId()))) {
                finesIn.add(data);   
            }
        }

        Collection accidentIn = new LinkedList();
        datatypeIt = dataTypeReport.findAll(communication.datatype.DataTypeConstants.ALL_ACCIDENT_CLASIFICATION,sys.getUserSessionData()).iterator();
        while(datatypeIt.hasNext()) {
            AccidentClasificationDataType data = (AccidentClasificationDataType)datatypeIt.next();
            if(Util.isChecked(request.getParameter(data.getResourceBundleId()))) {
                accidentIn.add(data);   
            }
        }

        Collection incidentIn = new LinkedList();
        datatypeIt = dataTypeReport.findAll(communication.datatype.DataTypeConstants.ALL_INCIDENT_CLASIFICATION,sys.getUserSessionData()).iterator();
        while(datatypeIt.hasNext()) {
            IncidentClasificationDataType data = (IncidentClasificationDataType)datatypeIt.next();
            if(Util.isChecked(request.getParameter(data.getResourceBundleId()))) {
                incidentIn.add(data);   
            }
        }

        sys.getCorporationABMFacade().configurate(accidentIn,incidentIn,finesIn,sys.getUserSessionData());
        return mapping.findForward("ok");
        
    }
    
    
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }

} 
    


