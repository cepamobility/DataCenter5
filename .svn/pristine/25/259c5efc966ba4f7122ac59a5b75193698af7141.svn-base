/*
  * Created on 27-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.vehicle;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.codehaus.jackson.map.ObjectMapper;

import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;

import communication.administration.corporation.tables.CorporationData;
import communication.datatype.tables.TypeTradeDataType;
import communication.util.ListResult;
import communication.vehicle.tables.TrailerData;
import communication.vehicle.tables.VehicleData;

/**
 * @author fcarriquiry
 */
public class TrailersAction extends AbstractAction {

	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Throwable {

			HttpSession session = request.getSession();
	       
			if(request.getParameter("op")!=null && request.getParameter("op").equals("delete")){
				return delete(mapping,form,request,response);
			}
			else
			if(request.getParameter("op")!=null && request.getParameter("op").equals("create")){
				return create(mapping,form,request,response);
			}else
			if(request.getParameter("op")!=null && request.getParameter("op").equals("search")){
				return search(mapping,form,request,response);
			}else
			if(request.getParameter("op")!=null && request.getParameter("op").equals("associate")){
				return associate(mapping,form,request,response);
			}else
			if(request.getParameter("op")!=null && request.getParameter("op").equals("getTrailer")){
				return getTrailer(mapping,form,request,response);
			}else
			if(request.getParameter("op")!=null && request.getParameter("op").equals("update")){
				return update(mapping,form,request,response);
			}
			
			
	        return null;
        	
	}



	private ActionForward update(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)  throws Exception  {

		HttpSession session = request.getSession();
		SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
		 
		Integer trailerId=Integer.valueOf(request.getParameter("trailerId"));
		String plate=request.getParameter("plate");
		String trade=request.getParameter("trade");
		String model=request.getParameter("model");
		String year=request.getParameter("year");
		boolean control=request.getParameter("control").equalsIgnoreCase("true");
		boolean suspension=request.getParameter("suspension").equalsIgnoreCase("true");
		boolean abs=request.getParameter("abs").equalsIgnoreCase("true");
		String type=request.getParameter("type");
		
		
		TrailerData trailerData=sys.getVehicleReportFacade().findTrailer(trailerId, sys.getUserSessionData());
		
		trailerData.setPlate(plate);
		trailerData.setModel(model);
		trailerData.setYear(Integer.valueOf(year));
		trailerData.setTrade(new TypeTradeDataType(trade));
		trailerData.setType(type);
		trailerData.setSuspension(suspension);
		trailerData.setAbs(abs);
		trailerData.setWeightControl(control);
		
        sys.getVehicleABMFacade().updateTrailer(trailerData, sys.getUserSessionData());
        
		return null;
	}



	private ActionForward getTrailer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)   throws Exception  {
		HttpSession session = request.getSession();
		SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
		Integer trailerId=Integer.valueOf(request.getParameter("trailerId"));
		
		TrailerData data= sys.getVehicleReportFacade().findTrailer(trailerId, sys.getUserSessionData());
		ObjectMapper mapper=new ObjectMapper();
		response.getWriter().write(mapper.writeValueAsString(data));
		
		return null;
	}



	private ActionForward associate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)  throws Exception {
		HttpSession session = request.getSession();
		SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
		Integer vehicleId=Integer.valueOf(request.getParameter("vehicle"));
		Integer trailerId=Integer.valueOf(request.getParameter("trailerId"));
		
		ArrayList<String> list=new ArrayList<String>();
        list.add(trailerId.toString());
        sys.getVehicleABMFacade().associateTrailer(vehicleId, list, sys.getUserSessionData());
			
	        
		return null;
	}



	private ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
		String plate=request.getParameter("plate");
		ListResult res= sys.getVehicleReportFacade().getTrailers(plate, 0, 99999, VehicleData.ALL, sys.getUserSessionData());
		
		StringBuilder text=new StringBuilder("{ \"trailers\":[ ");
		Iterator<TrailerData> iter= res.getElements().iterator();
		boolean coma=false;
		while(iter.hasNext()){
			TrailerData data=iter.next();
			if(coma){
				text.append(",");	
			}
			text.append("{\"trade\":\""+sys.getText().getText(data.getTrade().getResourceBundleId())+"\",\"plate\":\""+data.getPlate()+"\",\"model\":\""+data.getModel()+"\",\"year\":\""+data.getYear()+"\",\"id\":\""+data.getId()+"\"}");
			coma=true;
		}
		
		text.append("] }");
		
		response.getWriter().write(text.toString());
		return null;
	}



	private ActionForward create(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
		 
		Integer vehicleId=Integer.valueOf(request.getParameter("vehicle"));
		String plate=request.getParameter("plate");
		String trade=request.getParameter("trade");
		String model=request.getParameter("model");
		String year=request.getParameter("year");
		boolean control=request.getParameter("control").equalsIgnoreCase("true");
		boolean suspension=request.getParameter("suspension").equalsIgnoreCase("true");
		boolean abs=request.getParameter("abs").equalsIgnoreCase("true");
		String type=request.getParameter("type");
		
		VehicleData vehicleData=sys.getVehicleReportFacade().findVehicleById(vehicleId,sys.getUserSessionData());
		
		TrailerData trailerData=new TrailerData();
		
		trailerData.setPlate(plate);
		trailerData.setModel(model);
		trailerData.setYear(Integer.valueOf(year));
		trailerData.setTrade(new TypeTradeDataType(trade));
		trailerData.setDivOrgs(vehicleData.getDivOrgs());
		trailerData.setCountry(vehicleData.getCountry());
		trailerData.setCorporation(new CorporationData(sys.getCorporationId()));
		trailerData.setType(type);
		trailerData.setSuspension(suspension);
		trailerData.setAbs(abs);
		trailerData.setWeightControl(control);
		
        Integer trailerId= sys.getVehicleABMFacade().createTrailer(trailerData, sys.getUserSessionData());
        ArrayList<String> list=new ArrayList<String>();
        list.add(trailerId.toString());
        sys.getVehicleABMFacade().associateTrailer(vehicleId, list, sys.getUserSessionData());
		
        PrintWriter writer=response.getWriter();
        
        writer.write("{\"trailerId\":\""+trailerId+"\"}");
        
        
		return null;
	}



	private ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
		 
		Integer vehicleId=Integer.valueOf(request.getParameter("vehicle"));
		String trailerId=request.getParameter("trailer");
		ArrayList<String> list=new ArrayList<String>();
		list.add(trailerId);
		
		sys.getVehicleABMFacade().desassociateTrailer(vehicleId, list, sys.getUserSessionData());
		
		return null;
	}



	@Override
	public ActionForward cancel(HttpSession session, ActionMapping mapping,
			ActionForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	
    
}
