package business.ws;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import business.SystemAccessRemote;
import business.ws.cache.CacheManager;
import business.ws.client.security.User;
import communication.administration.corporation.WSResultData;
import communication.administration.corporation.tables.ContractorPlateFOSAData;
import communication.external.instructors.CorporationRestData;
import communication.language.Text;

@Stateless(mappedName = "WSLDCInstructos")
@WebService(name="WSLDCInstructos")
public class WSLDCInstructos{
		
	 
   	
	@WebMethod(operationName="sync")
	public ArrayList<CorporationRestData> sync(@WebParam(name="lastId")Integer lastId ,@WebParam(name="activitiesId") String activitiesId ,@WebParam(name="lastUpdate") String lastUpdate,@WebParam(name="user") String user,@WebParam(name="password") String password) throws Exception{
		User userSys =new User(user,password);
		
		SystemAccessRemote sys=CacheManager.getInstance().login(userSys);
		SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy");		
		Text m=sys.getText();
		return sys.getExternalFacade().sync(lastId,activitiesId,format.parse(lastUpdate),m,sys.getUserSessionData());
	}
	

	@WebMethod(operationName="loginAppInstructors")
	public boolean loginAppInstructors(@WebParam(name="u")String user,@WebParam(name="p") String password) throws Exception{
		User userSys =new User(user,password);
		try{
			SystemAccessRemote sys=CacheManager.getInstance().login(userSys);
			return sys.getUserSessionData()!=null;
		}catch(Throwable th){
			
		}
		return false;
	}
	
}
