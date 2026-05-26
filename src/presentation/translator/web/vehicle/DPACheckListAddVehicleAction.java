package presentation.translator.web.vehicle;

import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import persistence.administration.tables.TransporterLink;
import presentation.translator.web.driver.DriverDivOrgForm;
import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.CountryData;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.ItemData;
import communication.datatype.tables.TypeTradeDataType;
import communication.datatype.tables.TypeUseDataType;
import communication.datatype.tables.TypeVehicleDataType;
import communication.language.Text;
import communication.util.Filter;
import communication.vehicle.tables.VehicleData;

public class DPACheckListAddVehicleAction extends AbstractAction {


	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
        if(request.getParameter("getItems")!=null){
        	return getItems(mapping, form, request, response);
        }else{
        	return altaVehiculo(mapping, form, request, response);
        }

	}

	public ActionForward altaVehiculo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
        DPACheckListAddVehicleForm formDPA=(DPACheckListAddVehicleForm)form;
        String sufix = "fosa";
        if(sys.isCorporation(CorporationData.DPA)) {
        	sufix = "";
        } else if(sys.getUserSessionData().isMobileCheckList()) {
        	sufix = "Mobile";
        } 
        if(formDPA.validate()!=null){
        	request.setAttribute("DPACheckListAddVehicleForm", formDPA);
        	session.setAttribute("Msg", messages.getText(formDPA.validate()));
        	 return mapping.findForward("back"+sufix);
        }
        
        VehicleData vehicle=new VehicleData();
        vehicle.setMatricula(formDPA.getPlate().toUpperCase());
        vehicle.setType(new TypeVehicleDataType(formDPA.getType()));
        vehicle.setUse(new TypeUseDataType("persistence.datatype.typeuse.institutional"));
        vehicle.setModel(formDPA.getModel());
        vehicle.setTrade(new TypeTradeDataType(formDPA.getBrand()));
        vehicle.setYear(Integer.valueOf(formDPA.getYear()));
        DriverDivOrgForm aux=new DriverDivOrgForm();
        if(sys.isCorporation(CorporationData.SYNGENTA_BR)){
        	aux.setDivOrg11(TransporterLink.SYNGENTA_BR_FUNCTION_NA.toString());
        	aux.setDivOrg12(TransporterLink.SYNGENTA_BR_AREA_NA.toString());
        	aux.setDivOrg21(TransporterLink.SYNGENTA_BR_REGIONAL_NA.toString());
        	aux.setDivOrg22(TransporterLink.SYNGENTA_BR_LOCATION_NA.toString());
        } else if(sys.isCorporation(CorporationData.WEYERHAEUSER)){
        	aux.setDivOrg31(formDPA.getDivOrg1());
        	aux.setDivOrg11(TransporterLink.WEYERHAEUSER_DESTINY.toString());
        	aux.setDivOrg21(TransporterLink.WEYERHAEUSER_PRODUCT.toString());
        }else if(sys.isCorporation(CorporationData.CEPA) ){
        	aux.setDivOrg11(formDPA.getDivOrg1());
        	aux.setDivOrg12(formDPA.getDivOrg2());
        	
        	aux.setDivOrg21(DivOrgData.CEPA_LOCATION_DEFAULT);
        	
        }else {
        	aux.setDivOrg11(formDPA.getDivOrg1());	
        }
        
        
        
        vehicle.setDivOrgs(aux.getDivOrgs(sys.getUserSessionData()));
        vehicle.setCorporation(new CorporationData(sys.getUserSessionData().getFilter().getCorporationId()));
        
        vehicle.setCountry(sys.getUserSessionData().getFilter().getDivGeoLabelData(1, Filter.USER));
        
        Integer id=sys.getVehicleABMFacade().vehicleCreate(vehicle,"",sys.getUserSessionData());
       
        sys.getUserSessionData().setValue(String.valueOf(id));	
      	session.setAttribute("Msg", "vehicle.create.message");
     	request.setAttribute("DPACheckListAddVehicleForm", new DPACheckListAddVehicleForm());
   
        return mapping.findForward("next"+sufix);
	}

	public ActionForward getItems(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Integer index= Integer.valueOf(request.getParameter("index"));
        String level1=request.getParameter("level1");
        String level2=request.getParameter("level2");
        
        int divOrg=0;
        String salida="[";
        if(sys.isCorporation(CorporationData.WEYERHAEUSER)){
        	divOrg=2;
        } 
        
        Iterator iter=sys.getUserSessionData().getFilter().getLevelItems(index, divOrg, level1, level2, Filter.USER);
        boolean first=true;
        while(iter.hasNext()){
        	if(!first){
        		salida+=",";
        	}
        	ItemData item=(ItemData)iter.next();
        	salida+="{\"id\":\""+item.getId()+"\",\"name\":\""+item.getName()+"\"}";
        		
        	first=false;
        }
        
        salida+="]";
        
        response.setContentType("application/json");
	    PrintWriter out = response.getWriter();
	    out.print(salida);
	    out.flush();
	       
        return null;
	}

	
	public ActionForward cancel(HttpSession session, ActionMapping mapping, ActionForm form) {		
		return null;
	}
	
	
	
}
