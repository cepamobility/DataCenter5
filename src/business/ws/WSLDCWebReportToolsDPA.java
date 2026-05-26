package business.ws;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.DatatypeConverter;

import business.SystemAccessRemote;
import business.ws.cache.CacheManager;
import business.ws.client.WSL;
import business.ws.client.activity.WSLCEPAActivityScheduleRegistryData;
import business.ws.client.common.ListWSLDriverMainAttributeDataResult;
import business.ws.client.common.ListWSLVehicleDataResult;
import business.ws.client.driver.WSLDriverData;
import business.ws.client.driver.WSLDriverMainAttributesData;
import business.ws.client.driver.WSLTelematicControlData;
import business.ws.client.security.User;
import business.ws.client.vehicle.WSLVehicleData;

import communication.activity.tables.CEPAActivityScheduleRegistryData;
import communication.administration.corporation.util.DPAUnconformityItemFilter;
import communication.driver.tables.DriverData;
import communication.driver.tables.DriverMainAttributes;
import communication.driver.tables.TelematicControlData;
import communication.util.ListResult;
import communication.vehicle.tables.DPAGoalData;
import communication.vehicle.tables.DPAVehicleActiveData;
import communication.vehicle.tables.TrailerMainData;
import communication.vehicle.tables.VehicleCheckReportGroupData;
import communication.vehicle.tables.VehicleCheckResumeData;
import communication.vehicle.tables.VehicleData;

@Stateless(mappedName = "WSLDCWebReportToolsDPA")
@WebService(name="WSLDCWebReportToolsDPA")
public class WSLDCWebReportToolsDPA implements WSLFacade {
	
	 

	@WebMethod(operationName="login")
	public User login(@WebParam(name = "u") String u, @WebParam(name = "p")String p){
		User user=new User(u,p);
		 try { 
			 
			SystemAccessRemote sys=CacheManager.getInstance().getSys(user);
			user.setFullName(sys.getUserSessionData().getFullName());
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
	
	
	@WebMethod(operationName="addDCMessageObserver")
	public String addDCMessageObserver(@WebParam(name = "u") User u){
		
		 try {
			SystemAccessRemote sys=CacheManager.getInstance().getSys(u);
			
			return sys.getCorporationReportFacade().addDCMessageObserver(sys.getUserSessionData());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
         return "no result - error";
	}
	
	
	@WebMethod(operationName="sendDCMessageObserver")
	public void sendDCMessageObserver(@WebParam(name = "m")String m,@WebParam(name = "u")User u){
		
		 try {
			SystemAccessRemote sys=CacheManager.getInstance().getSys(u);
			
			sys.getCorporationReportFacade().sendDCMessageObserver(m,sys.getUserSessionData());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
	
	@WebMethod(operationName="findVehicleById")
	public WSLVehicleData findVehicleById(@WebParam(name = "id") Integer id,@WebParam(name = "u")User u){
		
		 try {
			SystemAccessRemote sys=CacheManager.getInstance().getSys(u);
			
			return WSL.t(sys.getVehicleReportFacade().findVehicleById(id,sys.getUserSessionData()),sys.getText());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return null;
   }
	
	@WebMethod(operationName="findUnconformityItemsCheckListFORESTAL")
	public String[][] findUnconformityItemsCheckListFORESTAL(@WebParam(name = "filter") DPAUnconformityItemFilter filter,@WebParam(name = "u")User u){
		
		 try {
			 
			SystemAccessRemote sys=CacheManager.getInstance().getSys(u);
			ArrayList<ArrayList<String>> s1=sys.getCorporationReportFacade().findUnconformityItemsCheckListFORESTAL(sys.getUserSessionData(),sys.getText(),filter);
			String[][] a=new String[s1.size()][];
			int i=0;
			for(ArrayList<String> s:s1){
				a[i]=s.toArray(new String[]{});
				i++;
			}
			
			return a;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return new String[][]{};
   }
	
	@WebMethod(operationName="getVehicleTrailers")
	public TrailerMainData[] getVehicleTrailers(@WebParam(name = "id")Integer id,@WebParam(name = "u")User u){
		
		 try {
			SystemAccessRemote sys=CacheManager.getInstance().getSys(u);
			
			Collection<TrailerMainData> col= sys.getVehicleReportFacade().getVehicleTrailers(id,sys.getUserSessionData());
			return col.toArray(new TrailerMainData[]{} );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return null;
   }
	
	
	@WebMethod(operationName="findVehicleList")
	public ListWSLVehicleDataResult findVehicleList(@WebParam(name = "a")String attribute, @WebParam(name = "v")String value,@WebParam(name = "f")int first, @WebParam(name = "c")int count, @WebParam(name = "s")int status, @WebParam(name = "u")User u){
		
		 try {
			SystemAccessRemote sys=CacheManager.getInstance().getSys(u);
			
			ListResult lr= sys.getVehicleReportFacade().findVehicleList(attribute, value, first, count, status, sys.getUserSessionData());
			
			ListWSLVehicleDataResult result=new ListWSLVehicleDataResult();
			result.setTotal(lr.getTotal());
			ArrayList<WSLVehicleData> aux=new ArrayList<WSLVehicleData>();
			Iterator<VehicleData> it=lr.getElements().iterator();
			while(it.hasNext()){
				aux.add(WSL.t(it.next(),sys.getText()));
			}
			
			result.setElements(aux.toArray(new WSLVehicleData[]{}));
			
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return null;
   }
	
	@WebMethod(operationName="findVehicleCheckListAbstract")
	public VehicleCheckResumeData[] findVehicleCheckListAbstract(@WebParam(name = "se")Integer startYear, @WebParam(name = "sm")Integer startMonth,@WebParam(name = "ey")Integer endYear, @WebParam(name = "em")Integer endMonth, @WebParam(name = "u")User u){
		
		 try {
			SystemAccessRemote sys=CacheManager.getInstance().getSys(u);
			
			ArrayList<VehicleCheckResumeData> res= sys.getVehicleReportFacade().findVehicleCheckReportResume( startYear,endYear, startMonth, endMonth, sys.getUserSessionData());
			
			return res.toArray(new VehicleCheckResumeData[]{});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return new VehicleCheckResumeData[]{};
   }

	@WebMethod(operationName="findVehicleCheckReportGroupItems")
	public VehicleCheckReportGroupData[] findVehicleCheckReportGroupItems(@WebParam(name = "se")Integer startYear, @WebParam(name = "sm")Integer startMonth,@WebParam(name = "ey")Integer endYear, @WebParam(name = "em")Integer endMonth, @WebParam(name = "u")User u){
		
		 try {
			SystemAccessRemote sys=CacheManager.getInstance().getSys(u);
			
			ArrayList<VehicleCheckReportGroupData> res= sys.getVehicleReportFacade().findVehicleCheckReportResumeGroupItems( startYear,endYear, startMonth, endMonth, sys.getUserSessionData());
			VehicleCheckReportGroupData[] arr=new VehicleCheckReportGroupData[res.size()];
			int i=0;
			for(VehicleCheckReportGroupData data: res){
				data.setText(DatatypeConverter.printBase64Binary(data.getText().getBytes()));
				arr[i++]=data;
			}
			

			return arr;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return new VehicleCheckReportGroupData[]{};
   }
	
	@WebMethod(operationName="findVehicleCheckReportGroupChecks")
	public VehicleCheckReportGroupData[] findVehicleCheckReportGroupChecks(@WebParam(name = "se")Integer startYear, @WebParam(name = "sm")Integer startMonth,@WebParam(name = "ey")Integer endYear, @WebParam(name = "em")Integer endMonth, @WebParam(name = "u")User u){
		
		 try {
			SystemAccessRemote sys=CacheManager.getInstance().getSys(u);
			
			ArrayList<VehicleCheckReportGroupData> res= sys.getVehicleReportFacade().findVehicleCheckReportResumeGroupChecks( startYear,endYear, startMonth, endMonth, sys.getUserSessionData());
			
			return res.toArray(new VehicleCheckReportGroupData[]{});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return new VehicleCheckReportGroupData[]{};
   }
	
	@WebMethod(operationName="findDPAGoals")
	public DPAGoalData[] findDPAGoals(@WebParam(name = "year")Integer year, @WebParam(name = "month")Integer month, @WebParam(name = "u")User u){
		
		 try {
			SystemAccessRemote sys=CacheManager.getInstance().getSys(u);
			
			ArrayList<DPAGoalData> res= sys.getVehicleReportFacade().getDPAGoals(year,month ,sys.getUserSessionData());
			
			return res.toArray(new DPAGoalData[]{});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return new DPAGoalData[]{};
   }
	
	@WebMethod(operationName="findDPAVehicleActive")
	public DPAVehicleActiveData[] findDPAVehicleActive(@WebParam(name = "year")Integer year, @WebParam(name = "month")Integer month, @WebParam(name = "u")User u){
		
		 try {
			SystemAccessRemote sys=CacheManager.getInstance().getSys(u);
			
			ArrayList<DPAVehicleActiveData> res= sys.getVehicleReportFacade().getDPAVehicleActive(year,month ,sys.getUserSessionData());
			
			return res.toArray(new DPAVehicleActiveData[]{});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return new DPAVehicleActiveData[]{};
   }
	
	@WebMethod(operationName="findDriverList")
	public ListWSLDriverMainAttributeDataResult findDriverList(@WebParam(name = "a")String attribute, @WebParam(name = "v")String value,@WebParam(name = "f")int first, @WebParam(name = "c")int count, @WebParam(name = "s")int status, @WebParam(name = "u")User u){
		
		 try {
			SystemAccessRemote sys=CacheManager.getInstance().getSys(u);
			
			ListResult lr= sys.getDriverReportFacade().findDriverList(attribute, value, first, count, status, sys.getUserSessionData());
			
			ListWSLDriverMainAttributeDataResult result=new ListWSLDriverMainAttributeDataResult();
			result.setTotal(lr.getTotal());
			
			ArrayList<WSLDriverMainAttributesData> aux=new ArrayList<WSLDriverMainAttributesData>();
			Iterator<DriverMainAttributes> it=lr.getElements().iterator();
			while(it.hasNext()){
				aux.add(WSL.t(it.next()));
			}
			
			result.setElements(aux.toArray(new WSLDriverMainAttributesData[]{}));
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return null;
   }
	
	
	@WebMethod(operationName="findDriverByPrimaryKey")
	public WSLDriverData findDriverByPrimaryKey(@WebParam(name = "id")Integer id,@WebParam(name = "u")User u){
		
		 try {
			SystemAccessRemote sys=CacheManager.getInstance().getSys(u);
			
			return WSL.t(sys.getDriverReportFacade().findDriverByPrimaryKey(id, sys.getUserSessionData()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return null;
   }
	
	@WebMethod(operationName="findCEPAActivityByDriver")
	public WSLCEPAActivityScheduleRegistryData[] findCEPAActivityByDriver(@WebParam(name = "id")Integer id,@WebParam(name = "u")User u){
		
		 try {
			SystemAccessRemote sys=CacheManager.getInstance().getSys(u);
			Iterator itAc= sys.getActivityReportFacade().findActivityByDriver(id,sys.getUserSessionData(),true).iterator();
			ArrayList<WSLCEPAActivityScheduleRegistryData> res=new ArrayList<WSLCEPAActivityScheduleRegistryData>();
			while(itAc.hasNext()){
		        Object oData= itAc.next();
		        if(oData instanceof CEPAActivityScheduleRegistryData){
		        	CEPAActivityScheduleRegistryData data=(CEPAActivityScheduleRegistryData)oData;
		        	res.add(WSL.t(data));
		        }
			}
			return res.toArray(new WSLCEPAActivityScheduleRegistryData[]{});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return new WSLCEPAActivityScheduleRegistryData[]{};
   }
	
	
	 

	@WebMethod(operationName="findTelematicControls")
	public WSLTelematicControlData[] findTelematicControls(@WebParam(name = "year")Integer year,@WebParam(name = "monthstart")Integer monthstart,@WebParam(name = "monthend")Integer monthend,@WebParam(name = "u")User u){
		
		 try {
			SystemAccessRemote sys=CacheManager.getInstance().getSys(u);
			ArrayList<WSLTelematicControlData> res=new ArrayList<WSLTelematicControlData>();
			Collection telematicsData=sys.getDriverReportFacade().findTelematicControls(year, monthstart, monthend, sys.getUserSessionData());
			Iterator iter=telematicsData.iterator();
			while(iter.hasNext()){
				res.add(WSL.t((TelematicControlData)iter.next()));
			}
			
			return res.toArray( new WSLTelematicControlData[]{});
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return new WSLTelematicControlData[]{};
   }
	
	@WebMethod(operationName="findDriversDPAPontos")
	public WSLDriverData[] findDriversDPAPontos(@WebParam(name = "year")Integer year,@WebParam(name = "monthend")Integer monthend,@WebParam(name = "u")User u){
		
		 try {
			SystemAccessRemote sys=CacheManager.getInstance().getSys(u);
			ArrayList<WSLDriverData> res=new ArrayList<WSLDriverData>();
			Collection driversData=sys.getDriverReportFacade().findDriversDPAPontos(year,   monthend, sys.getUserSessionData());
			Iterator iter=driversData.iterator();
			while(iter.hasNext()){
				res.add(WSL.t((DriverData)iter.next()));
			}
			
			return res.toArray( new WSLDriverData[]{});
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return new WSLDriverData[]{};
  }
	
	 
}
