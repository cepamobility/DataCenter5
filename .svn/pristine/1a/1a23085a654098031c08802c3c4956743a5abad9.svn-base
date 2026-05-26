package presentation.translator.web.driver;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import presentation.translator.web.util.Util;
import business.SystemAccess;
import business.driver.DriverReportFacade;

import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.ItemData;
import communication.util.Filter;
import communication.util.ListResult;

public class CsvUpdateDriversAction {

	public ActionForward action(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {

		// Filtro por Busqueda Avanzada:  
		
	    HttpSession session = request.getSession();
	    SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
	
	    CsvUpdateDriversForm driverSearchForm = (CsvUpdateDriversForm) form;
	        
		Integer[][] divOrgs = getDivOrgs(sys,driverSearchForm);
	    Integer[][] divGeos = getDivGeos(sys,driverSearchForm);
	
	    DriverReportFacade driverReportFacade = sys.getDriverReportFacade();
	        
	    String id = (!Util.empty(driverSearchForm.getId())) ? driverSearchForm.getId() : null; 
	    String firstName = (!Util.empty(driverSearchForm.getFirstName())) ? driverSearchForm.getFirstName() : null; 
	    String lastName = (!Util.empty(driverSearchForm.getLastName())) ? driverSearchForm.getLastName() : null; 
	    int first =  0;
	    int count = -1; // TODOS
	        
	    ListResult result = driverReportFacade.advancedSearch(id,firstName,lastName,driverSearchForm.getBirth(),divOrgs,divGeos,first,count,Integer.parseInt(driverSearchForm.getStatus()),0,sys.getUserSessionData());
	
		//session.setAttribute("driverSearchList", result);
	    //session.setAttribute("driverForm", driverSearchForm);	
		//return mapping.findForward((String)session.getAttribute("target"));
	    
	    
	    // Campos a Actualizar:
	    
	    
	    return null;
	}
	
	public static Integer[][] getDivOrgs(SystemAccess sys, DriverDivOrgForm driverSearchForm) throws RemoteException {
        return getDivOrgs(sys,driverSearchForm,Filter.USER);
    }
    
	public static Integer[][] getDivOrgs(SystemAccess sys, DriverDivOrgForm driverSearchForm,int data) throws RemoteException {
        Collection result = new LinkedList();
        for(int j = 0; j < sys.getUserSessionData().getFilter().getPrincipalCount(); j++) {
            Collection divOrgs = new LinkedList();
            switch(sys.getUserSessionData().getFilter().getDivOrgLevel(j)) {
                case 1:
                    if(!Util.empty(driverSearchForm.getDivOrg(j+1,1))) {
                        divOrgs.add(new Integer(driverSearchForm.getDivOrg(j+1,1)));
                    }
                    break;
                case 2:
                    if(!Util.empty(driverSearchForm.getDivOrg(j+1,1))) {
                        if(!Util.empty(driverSearchForm.getDivOrg(j+1,2))) {
                            divOrgs.add(new Integer(driverSearchForm.getDivOrg(j+1,2)));
                        }else {
                            Iterator it2 = sys.getUserSessionData().getFilter().getLastLevelItems(2,j,driverSearchForm.getDivOrg(j+1,1),null,data);
                            while(it2.hasNext()) {
                                ItemData itemData = (ItemData)it2.next();
                                divOrgs.add(itemData.getId());
                            }
                        }
                    }
                    break;
                case 3:
                    if(!Util.empty(driverSearchForm.getDivOrg(j+1,3))) {
                        divOrgs.add(new Integer(driverSearchForm.getDivOrg(j+1,3)));
                    }else {
                        String level2 = null;
                        if(!Util.empty(driverSearchForm.getDivOrg(j+1,1))) {
                            if(!Util.empty(driverSearchForm.getDivOrg(j+1,2))) {
                                level2 = driverSearchForm.getDivOrg(j+1,2);
                            }
                            Iterator it2 = sys.getUserSessionData().getFilter().getLastLevelItems(3,j,driverSearchForm.getDivOrg(j+1,1),level2,data);
                            while(it2.hasNext()) {
                                ItemData itemData = (ItemData)it2.next();
                                divOrgs.add(itemData.getId());
                            }
                        }
                    }
                    break;
            }
            result.add((Integer[])divOrgs.toArray(new Integer[0]));
        }
        return (Integer[][])result.toArray(new Integer[0][0]);
    }

    public static Integer[][] getDivGeos(SystemAccess sys, DriverDivOrgForm driverSearchForm) throws RemoteException {
        return getDivGeos(sys,driverSearchForm,Filter.USER);
    }
    
    public static Integer[][] getDivGeos(SystemAccess sys, DriverDivOrgForm driverSearchForm,int data) throws RemoteException {
        Integer[][] result = new Integer[2][0]; 
        Collection countries = new LinkedList();
        Collection divGeos = new LinkedList();
        switch(sys.getUserSessionData().getFilter().getGeoLevelCount()) {
            case 1:
                if(!Util.empty(driverSearchForm.getCountry1())) {
                    countries.add(new Integer(driverSearchForm.getCountry1()));
                }
                break;
            case 2:
                if(!Util.empty(driverSearchForm.getCountry1())) {
                    divGeos.add(new Integer(driverSearchForm.getCountry1()));
                    if(!Util.empty(driverSearchForm.getCountry2())) {
                        countries.add(new Integer(driverSearchForm.getCountry2()));
                    }else {
                        Iterator it2 = sys.getUserSessionData().getFilter().getLastLevelItems(2,driverSearchForm.getCountry1(),null,data);
                        while(it2.hasNext()) {
                            GeoItemData itemData = (GeoItemData)it2.next();
                            countries.add(itemData.getId());
                        }
                    }
                }
                break;
            case 3:
                if(!Util.empty(driverSearchForm.getCountry3())) {
                    countries.add(new Integer(driverSearchForm.getCountry3()));
                }else {
                    String level2 = null;
                    if(!Util.empty(driverSearchForm.getCountry1())) {
                        if(!Util.empty(driverSearchForm.getCountry2())) {
                            level2 = driverSearchForm.getCountry2();
                        }
                        Iterator it2 = sys.getUserSessionData().getFilter().getLastLevelItems(3,driverSearchForm.getCountry1(),level2);
                        while(it2.hasNext()) {
                            GeoItemData itemData = (GeoItemData)it2.next();
                            countries.add(itemData.getId());
                        }
                    }
                }
                break;
        }
        result[0] = (Integer[])countries.toArray(new Integer[0]);
        result[1] = (Integer[])divGeos.toArray(new Integer[0]);
        return result;
    }

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
}
