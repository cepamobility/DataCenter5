package business.ws;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import business.SystemAccessRemote;
import business.ws.cache.CacheManager;
import business.ws.client.security.User;
import communication.administration.corporation.WSResultData;
import communication.administration.corporation.tables.ContractorPlateFOSAData;
import communication.driver.tables.DriverStateHistoryData;

@Stateless(mappedName = "WSLDCMdpAPI")
@WebService(name="WSLDCMdpAPI")
public class WSLDCMdpAPI{
		
	
	@WebMethod(operationName="driverState")
   	public WSResultData driverState(@WebParam(name = "u") String u, @WebParam(name = "p")String p, @WebParam(name = "ci")String ci, @WebParam(name = "state")String state, @WebParam(name = "stateDescription")String stateDescription,@WebParam(name = "stateDate")String stateDate ) throws Exception{
   		WSResultData result=new WSResultData(0,"OK");
   		if(!(u.equalsIgnoreCase("mdpwsus") && p.equals("132_6X9ZP!"))){
   			System.out.println("MDPWS:Usuario invalido llega usu("+u+") pass("+p+")");
			throw new Exception("User Invalid");
		}
   		
   		
   			try{
   				User user=new User("mdpwstelematic","132543");
   	   			SystemAccessRemote sys=CacheManager.getInstance().getSys(user);
   	   			DriverStateHistoryData data=new DriverStateHistoryData();
   	   			data.setReasonDescription(stateDescription);
   	   			data.setState(stateDate);
   	   			SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
   	   			data.setStateDate(format.parse(stateDate));
   	   			
   	   			return sys.getCorporationABMFacade().newDriverState(ci, data, sys.getUserSessionData());
   	   			
   	   		} catch (Exception e) {
   	   			e.printStackTrace();
   	   			result.setCode(1);
   	   			result.setDescription(e.getMessage());
   			}
   			
   			return result;
   	}
   	
	@WebMethod(operationName="offPlate")
   	public WSResultData suspendedPlate(@WebParam(name = "u") String u, @WebParam(name = "p")String p,@WebParam(name = "plate")String plate,@WebParam(name = "hours")Integer hours,@WebParam(name = "reasonDescription")String reasonDescription,@WebParam(name = "reasonCode")Integer reasonCode,@WebParam(name = "dateHourStartPenalty")String dateHourStartPenalty,@WebParam(name = "typeOfPenalty")Integer typeOfPenalty)  throws Exception{
   		WSResultData result=new WSResultData(0,"OK");
   		if(!(u.equalsIgnoreCase("mdpwsus") && p.equals("132_6X9ZP!"))){
   			System.out.println("MDPWS:Usuario invalido llega usu("+u+") pass("+p+")");
			throw new Exception("User Invalid");
		}
   		
   		try{
   			User user=new User("mdpwstelematic","132543");
   			SystemAccessRemote sys=CacheManager.getInstance().getSys(user);
   			return sys.getCorporationABMFacade().suspendedPlate(plate, hours, reasonDescription, reasonCode, dateHourStartPenalty, typeOfPenalty, sys.getUserSessionData());
   			
   		} catch (Exception e) {
   			e.printStackTrace();
   			result.setCode(1);
    		result.setDescription(e.getMessage());
		}	
   		return result;
   		
   	}
   	
	@WebMethod(operationName="onPlate")
   	public WSResultData activePlate(@WebParam(name = "u") String u, @WebParam(name = "p")String p,@WebParam(name = "plate")String plate)  throws Exception{
		WSResultData result=new WSResultData(0,"OK");
   		if(!(u.equalsIgnoreCase("mdpwsus") && p.equals("132_6X9ZP!"))){
   			System.out.println("MDPWS:Usuario invalido llega usu("+u+") pass("+p+")");
			throw new Exception("User Invalid");
		}
   		try{
   			User user=new User("mdpwstelematic","132543");
   			SystemAccessRemote sys=CacheManager.getInstance().getSys(user);
   			return sys.getCorporationABMFacade().activePlate(plate, sys.getUserSessionData());
   		} catch (Exception e) {
   			e.printStackTrace();
			result.setCode(1);
    		result.setDescription(e.getMessage());
		}	
   		
   		return result;
   	}
	
}
