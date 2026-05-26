package presentation.web.external;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import communication.external.instructors.ChecklistRestData;
import communication.external.instructors.ChecklistResultRestData;
import communication.external.instructors.ChecklistResultsRestData;
import communication.external.instructors.CorporationRestData;
import communication.language.Text;
import business.SystemAccessRemote;
import business.ws.cache.CacheManager;
import business.ws.client.security.User;


@Path("instructor")
public class AppInstructorsRest {

	@GET
	@Path("sync")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public ArrayList<CorporationRestData> sync(@QueryParam("lastId")Integer lastId ,@QueryParam("activitiesId") String activitiesId ,@QueryParam("lastUpdate") String lastUpdate,@QueryParam("user") String user,@QueryParam("password") String password) throws Exception{
		User userSys =new User(user,password);
		if(activitiesId==null||activitiesId.trim().length()==0){
			activitiesId="0";
		}else if(activitiesId.trim().endsWith(",")){
			activitiesId=activitiesId.trim().substring(0,activitiesId.trim().length()-1);
		}
		
		SystemAccessRemote sys=CacheManager.getInstance().login(userSys);
		SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy");		
		Text m=sys.getText();
		return sys.getExternalFacade().sync(lastId,activitiesId,format.parse(lastUpdate),m,sys.getUserSessionData());
	}
	
	@POST
	@Path("syncUp")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public boolean syncUp(ChecklistResultsRestData res) throws Exception{
		User userSys =new User(res.getUser(),res.getPassword());
		 
		
		SystemAccessRemote sys=CacheManager.getInstance().login(userSys);
		SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy");		
		Text m=sys.getText();
		return sys.getExternalFacade().saveResults(res.getData(),m,sys.getUserSessionData());
	}
	
	@GET
	@Path("checklists")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public ArrayList<ChecklistRestData> checklists(@QueryParam("ids")String ids ,@QueryParam("user") String user,@QueryParam("password") String password) throws Exception{
		User userSys =new User(user,password);
		
		SystemAccessRemote sys=CacheManager.getInstance().login(userSys);
		SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy");		
		Text m=sys.getText();
		ArrayList<ChecklistRestData> res=new ArrayList<ChecklistRestData>();
		for(String idS:ids.split(",")){
			res.add(sys.getExternalFacade().getChecklist(Integer.valueOf(idS), m, sys.getUserSessionData()));
		}
		
		return res;
	}
	
	@GET
	@Path("checklist")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public ChecklistRestData checklist(@QueryParam("id")Integer id ,@QueryParam("user") String user,@QueryParam("password") String password) throws Exception{
		User userSys =new User(user,password);
		
		SystemAccessRemote sys=CacheManager.getInstance().login(userSys);
		SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyyy");		
		Text m=sys.getText();
		 		 
		return sys.getExternalFacade().getChecklist(id, m, sys.getUserSessionData());
	}
	

	@GET
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public String login(@QueryParam("user") String user,@QueryParam("password") String password) throws Exception{
		User userSys =new User(user,password);
		try{
			SystemAccessRemote sys=CacheManager.getInstance().login(userSys);
			return "{\"lng\":\""+sys.getUserSessionData().getLenguage()+"\"}";
		}catch(Throwable th){
			
		}
		return null;
	}
}
