package business.ws;

import java.util.ArrayList;
import java.util.Iterator;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import business.SystemAccessRemote;
import business.ws.cache.CacheManager;
import business.ws.client.WSL;
import business.ws.client.common.ListWSLDriverMainAttributeDataResult;
import business.ws.client.common.ListWSLVehicleDataResult;
import business.ws.client.driver.WSLDriverData;
import business.ws.client.driver.WSLDriverMainAttributesData;
import business.ws.client.report.VehicleListFilter;
import business.ws.client.security.User;
import business.ws.client.vehicle.WSLVehicleData;

import communication.driver.tables.DriverMainAttributes;
import communication.util.ListResult;
import communication.vehicle.tables.VehicleData;

@Stateless(mappedName = "WSLGeneric")
@WebService(name="WSLGeneric")
public class WSLGeneric {
	
	 

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
	
	@WebMethod(operationName="find2VehicleList")
	public ListWSLVehicleDataResult find2VehicleList(@WebParam(name = "filter") VehicleListFilter filter, @WebParam(name = "u")User user){
		ListWSLVehicleDataResult result=new ListWSLVehicleDataResult();
		 try { 
			 
			 
				SystemAccessRemote sys=CacheManager.getInstance().getSys(user,false);
				 
				
				ListResult res=sys.getBIReportFacade().findVehicleList(filter.getText(), sys.getUserSessionData(), 6, filter.getFirst(), filter.getCount(), filter.getStatus());
				
				WSLVehicleData[] resArray=new WSLVehicleData[res.getElements().size()];
				
				int i=0;
				Iterator<VehicleData>  iter=res.getElements().iterator();
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
	
	@WebMethod(operationName="findVehicleById")
	public WSLVehicleData findVehicleById(@WebParam(name = "id") Integer id, @WebParam(name = "u")User user){
		
		 try { 
			 
			SystemAccessRemote sys=CacheManager.getInstance().getSys(user);
			 return WSL.t(sys.getVehicleReportFacade().findVehicleById(id, sys.getUserSessionData()), sys.getText());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			user=null;
		}
         
         return null;
	}
	

	@WebMethod(operationName="find2DriverList")
	public ListWSLDriverMainAttributeDataResult find2DriverList(@WebParam(name = "a")String attribute, @WebParam(name = "v")String value,@WebParam(name = "f")int first, @WebParam(name = "c")int count, @WebParam(name = "s")int status, @WebParam(name = "u")User u){
		
		 try {
			SystemAccessRemote sys=CacheManager.getInstance().getSys(u,false);
			
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
	
	
	@WebMethod(operationName="find2DriverByPrimaryKey")
	public WSLDriverData find2DriverByPrimaryKey(@WebParam(name = "id")Integer id,@WebParam(name = "u")User u){
		
		 try {
			SystemAccessRemote sys=CacheManager.getInstance().getSys(u,false);
			
			return WSL.t(sys.getDriverReportFacade().findDriverByPrimaryKey(id, sys.getUserSessionData()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 return null;
   }
	
	
	
}
