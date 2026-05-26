package presentation.translator.web.vehicle;

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

import presentation.translator.web.driver.DriverDivOrgForm;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;
import business.vehicle.VehicleReportFacade;

import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.user.UserSessionData;
import communication.util.ListResult;


public class VehicleAdvancedSearchAction extends AbstractAction { 

	public ActionForward action(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
			UserSessionData userSessionData = sys.getUserSessionData();
		
			VehicleAdvancedSearchForm vehicleSearchForm = (VehicleAdvancedSearchForm) form;
            
            Integer[][] divOrgs = getDivOrgs(sys,vehicleSearchForm);
            Integer[][] divGeos = getDivGeos(sys,vehicleSearchForm);

            VehicleReportFacade vehicleReportFacade = sys.getVehicleReportFacade();
            
            String chasisNumber = (!Util.empty(vehicleSearchForm.getChasisNumber())) ? vehicleSearchForm.getChasisNumber() : null;
            String registrationNumber = (!Util.empty(vehicleSearchForm.getRegistrationNumber())) ? vehicleSearchForm.getRegistrationNumber() : null;            
            String permissionNumber = (!Util.empty(vehicleSearchForm.getPermissionNumber())) ? vehicleSearchForm.getPermissionNumber() : null;            
            String costCenter = (!Util.empty(vehicleSearchForm.getCostCenter())) ? vehicleSearchForm.getCostCenter() : null;            
            String firstNameDriver = (!Util.empty(vehicleSearchForm.getFirstNameDriver())) ? vehicleSearchForm.getFirstNameDriver() : null; 
            String lastNameDriver = (!Util.empty(vehicleSearchForm.getLastNameDriver())) ? vehicleSearchForm.getLastNameDriver() : null; 
            int first =  (!Util.empty(vehicleSearchForm.getPageNum())) ? 
            		(Integer.parseInt(vehicleSearchForm.getPageNum())-1)*10 : 0;
            int count = (Boolean.parseBoolean(vehicleSearchForm.getAll())) ? -1 : 
            	((!Util.empty(vehicleSearchForm.getCount()))?Integer.parseInt(vehicleSearchForm.getCount()):10);
            
            String prefix = (!Util.empty(vehicleSearchForm.getPrefix())) ? vehicleSearchForm.getPrefix() : null; 
                       
            ListResult result = vehicleReportFacade.advancedSearch(
            		registrationNumber,costCenter,chasisNumber,permissionNumber,firstNameDriver,
            		lastNameDriver,divOrgs,divGeos,first,count,
            		userSessionData,
            		Integer.parseInt(vehicleSearchForm.getStatus()),prefix);
            

            
			session.setAttribute("vehicleSearchList", result);
            session.setAttribute("vehicleForm", vehicleSearchForm);
            
			return mapping.findForward((String)session.getAttribute("target"));
	}
	
    public static Integer[][] getDivOrgs(SystemAccess sys, DriverDivOrgForm driverSearchForm) throws RemoteException {
        Collection result = new LinkedList();
        for(int j = 0; j < 3; j++) {
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
                            Iterator it2 = sys.getUserSessionData().getFilter().getLastLevelItems(2,j,driverSearchForm.getDivOrg(j+1,1),null);
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
                            Iterator it2 = sys.getUserSessionData().getFilter().getLastLevelItems(3,j,driverSearchForm.getDivOrg(j+1,1),level2);
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
                        Iterator it2 = sys.getUserSessionData().getFilter().getLastLevelItems(2,driverSearchForm.getCountry1(),null);
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
