package business.ws;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import business.SystemAccessRemote;
import business.alert.handler.Util;
import business.ws.cache.CacheManager;
import business.ws.client.WSL;
import business.ws.client.common.ListWSLDriverStatusDataResult;
import business.ws.client.common.ListWSLVehicleStatusDataResult;
import business.ws.client.driver.WSLDriverStatusData;
import business.ws.client.report.VehicleListFilter;
import business.ws.client.security.User;
import business.ws.client.vehicle.WSLVehicleStatusData;
import communication.administration.corporation.tables.CorporationData;
import communication.driver.tables.DriverStatusData;
import communication.report.FilterChecks;
import communication.report.UnconformityAction;
import communication.util.ListResult;
import communication.vehicle.report.ChecklistReportHead;
import communication.vehicle.report.ChecklistReportItem;
import communication.vehicle.tables.VehicleStatusData;

@Stateless(mappedName = "WSLDCWebReportToolsMDP")
@WebService(name="WSLDCWebReportToolsMDP")
public class WSLDCWebReportToolsMDP extends WSLGeneric {
	
	 

	@WebMethod(operationName="login")
	public User login(@WebParam(name = "u") String u, @WebParam(name = "p")String p){
		User user=new User(u,p);
		 try { 
			 
			SystemAccessRemote sys=CacheManager.getInstance().getSys(user);
			user.setFullName(sys.getUserSessionData().getFullName());
			
			if(!sys.getUserSessionData().isCepaSystemUser() && !sys.getUserSessionData().isCorporation(CorporationData.MONTES_DE_PLATA) ){
				return null;
			}
			
			Object[][] o= sys.getText().getContents();
			String[][] m=new String[o.length][];
			int i=0;
			for(Object[] a:o){
				m[i]=new String[] {a[0].toString(),a[1].toString()};
				i++;
			}
			user.setMessages(m);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			user=null;
		}
         
         return user;
	}
	
	
	@WebMethod(operationName="forestalesUnconformities")
	public UnconformityAction[] forestalesUnconformities(@WebParam(name = "filter") FilterChecks filter, @WebParam(name = "u")User user){
		
		 try { 
			 
			SystemAccessRemote sys=CacheManager.getInstance().getSys(user,false);
			Date start=null;
			if(!filter.getStartNo()){
				start=Util.getDate(filter.getStartDay().toString(), filter.getStartMonth().toString(), filter.getStartYear().toString());
			}
			Date end=null;
			if(!filter.getEndNo()){ 
				end=Util.getDate(filter.getEndDay().toString(), filter.getEndMonth().toString(), filter.getEndYear().toString());
			}
			
			ArrayList<UnconformityAction> data=sys.getBIReportFacade().forestalesUnconformities(null,"'80','84'",start, end, filter.getType(), filter.getTime(), sys.getUserSessionData(), sys.getText());
			
			return data.toArray(new UnconformityAction[]{});
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
        return new UnconformityAction[]{};
	}
	
	@WebMethod(operationName="findCheckReportHead")
	public ChecklistReportHead[] findChecklistReportHead(@WebParam(name = "filter") FilterChecks filter, @WebParam(name = "u")User user){
		
		 try { 
			 
			SystemAccessRemote sys=CacheManager.getInstance().getSys(user,false);
			Integer startYear=null,endYear=null;
			if(!filter.getStartNo()){
				startYear=filter.getStartYear();
				
			}
			if(!filter.getStartNo()){
				endYear=filter.getEndYear();
			}
			
			ArrayList<ChecklistReportHead> data=sys.getBIReportFacade().findCheckReportHead(filter.getStartDay(), filter.getStartMonth(), startYear, filter.getEndDay(), filter.getEndMonth(), endYear, "C", sys.getUserSessionData());
			
			return data.toArray(new ChecklistReportHead[]{});
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
        return new ChecklistReportHead[]{};
	}
	
	@WebMethod(operationName="findSeguimientoReportHead")
	public ChecklistReportHead[] findSeguimientoReportHead(@WebParam(name = "filter") FilterChecks filter, @WebParam(name = "u")User user){
		
		 try { 
			 
			SystemAccessRemote sys=CacheManager.getInstance().getSys(user,false);
			Integer startYear=null,endYear=null;
			if(!filter.getStartNo()){
				startYear=filter.getStartYear();
				
			}
			if(!filter.getStartNo()){
				endYear=filter.getEndYear();
			}
			
			ArrayList<ChecklistReportHead> data=sys.getBIReportFacade().findCheckReportHead(filter.getStartDay(), filter.getStartMonth(), startYear, filter.getEndDay(), filter.getEndMonth(), endYear, "S", sys.getUserSessionData());
			
			return data.toArray(new ChecklistReportHead[]{});
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
        return new ChecklistReportHead[]{};
	}
	
	@WebMethod(operationName="findSeguimientoReportItem")
	public ChecklistReportItem[] findSeguimientoReportItem(@WebParam(name = "filter") FilterChecks filter, @WebParam(name = "u")User user){
		
		 try { 
			 
			SystemAccessRemote sys=CacheManager.getInstance().getSys(user,false);
			Integer startYear=null,endYear=null;
			if(!filter.getStartNo()){
				startYear=filter.getStartYear();
				
			}
			if(!filter.getStartNo()){
				endYear=filter.getEndYear();
			}
			
			ArrayList<ChecklistReportItem> data=sys.getBIReportFacade().findCheckReportItem(filter.getStartDay(), filter.getStartMonth(), startYear, filter.getEndDay(), filter.getEndMonth(), endYear, "S", sys.getUserSessionData());
			
			return data.toArray(new ChecklistReportItem[]{});
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
        return new ChecklistReportItem[]{};
	}	
	
	@WebMethod(operationName="findChecklistReportItem")
	public ChecklistReportItem[] findChecklistReportItem(@WebParam(name = "filter") FilterChecks filter, @WebParam(name = "u")User user){
		
		 try { 
			 
			SystemAccessRemote sys=CacheManager.getInstance().getSys(user,false);
			Integer startYear=null,endYear=null;
			if(!filter.getStartNo()){
				startYear=filter.getStartYear();
				
			}
			if(!filter.getStartNo()){
				endYear=filter.getEndYear();
			}
			
			ArrayList<ChecklistReportItem> data=sys.getBIReportFacade().findCheckReportItem(filter.getStartDay(), filter.getStartMonth(), startYear, filter.getEndDay(), filter.getEndMonth(), endYear, "C", sys.getUserSessionData());
			
			return data.toArray(new ChecklistReportItem[]{});
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
        return new ChecklistReportItem[]{};
	}	
	
	
	
	@WebMethod(operationName="forestalesVehicleUnconformities")
	public UnconformityAction[] forestalesVehicleUnconformities(@WebParam(name = "vehicleId") Integer vehicleid, @WebParam(name = "time") Integer time, @WebParam(name = "type") Integer type, @WebParam(name = "u")User user){
		
		 try { 
			 
			SystemAccessRemote sys=CacheManager.getInstance().getSys(user,false);
			 
			
			ArrayList<UnconformityAction> data=sys.getBIReportFacade().forestalesUnconformities(vehicleid,"'80'",null, null, type,time, sys.getUserSessionData(), sys.getText());
			
			return data.toArray(new UnconformityAction[]{});
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
        return new UnconformityAction[]{};
	}
	
	@WebMethod(operationName="findDriverByCorporationIdFirstNameLastName")
	public ListWSLDriverStatusDataResult findDriverByCorporationIdFirstNameLastName(@WebParam(name = "stringSearch")String  value,@WebParam(name = "level")Integer  level, @WebParam(name = "u")User user){
		ListWSLDriverStatusDataResult result=new ListWSLDriverStatusDataResult();
		 try { 
			 
			 
				SystemAccessRemote sys=CacheManager.getInstance().getSys(user,false);
				
				ListResult res=sys.getBIReportFacade().findDriverByCorporationIdFirstNameLastName(value,level, sys.getUserSessionData());
				
				WSLDriverStatusData[] resArray=new WSLDriverStatusData[res.getElements().size()];
				
				int i=0;
				Iterator<DriverStatusData>  iter=res.getElements().iterator();
				while(iter.hasNext()){
					resArray[i++]=WSL.t(iter.next(),sys.getText());
				}
				 
				result.setElements(resArray);
				result.setTotal(res.getTotal());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
		  return result;
	}	
	
	@WebMethod(operationName="findDriverWihtStatus")
	public WSLDriverStatusData findDriverWihtStatus(@WebParam(name = "driverId")Integer  driverId, @WebParam(name = "u")User user){
		WSLDriverStatusData result=new WSLDriverStatusData();
		 try { 
			 
				SystemAccessRemote sys=CacheManager.getInstance().getSys(user,false);
				
				DriverStatusData res=sys.getBIReportFacade().findDriverWithStatus(driverId, sys.getUserSessionData(), sys.getText());
				
				result=WSL.t(res, sys.getText());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
		  return result;
	}	
	
}
