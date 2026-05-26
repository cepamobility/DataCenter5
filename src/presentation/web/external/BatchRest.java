package presentation.web.external;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import business.SystemAccessRemote;
import business.ws.cache.CacheManager;
import business.ws.client.security.User;
import communication.driver.tables.DriverData;
import communication.external.instructors.ChecklistRestData;
import communication.external.instructors.ChecklistResultsRestData;
import communication.language.Text;
import communication.security.SecurityConstants;
import presentation.translator.web.activity.report.CertificateReport;
import presentation.translator.web.util.Util;


@Path("batch")
public class BatchRest {

	@GET
	@Path("processChecklist")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public void processChecklist(@QueryParam("vehicleCheckId")Integer vehicleCheckId,@QueryParam("u")String u,@QueryParam("p")String p ) throws Exception{
		User userSys =new User(u,p);
		
		SystemAccessRemote sys=CacheManager.getInstance().login(userSys);
		System.out.println("procesarCheckList("+vehicleCheckId+");");
		sys.getVehicleABMFacade().procesarCheckList(vehicleCheckId, sys.getText(), sys.getUserSessionData());
		
	}
	
	@GET
	@Path("notifyMP")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public void notifyMDP(@QueryParam("vehicleCheckId")Integer vehicleCheckId,@QueryParam("u")String u,@QueryParam("p")String p  ) throws Exception{
		User userSys =new User(u,p);
		
		SystemAccessRemote sys=CacheManager.getInstance().login(userSys);
		
		sys.getVehicleABMFacade().notifyChecklistMP(vehicleCheckId, sys.getText(), sys.getUserSessionData());
		
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
	@Path("bayercertificate")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public String bayercertificate(@QueryParam("user") String user, @QueryParam("password") String password, @QueryParam("idDriver") Integer idDriver, @QueryParam("activity") String activity, @QueryParam("date") String date) throws Exception{
		SystemAccessRemote sys;
		Text m;
		try {
			User userSys = new User(user,password);
			sys=CacheManager.getInstance().login(userSys);
			m=sys.getText();
		}catch (Exception e) {
			User userSys =new User("bayer.api.access2",password);
			sys=CacheManager.getInstance().login(userSys);
			m=sys.getText();
		}	
		if(sys != null) {
			DriverData driver = sys.getDriverReportFacade().findDriverByPrimaryKey(idDriver, sys.getUserSessionData());
			CertificateReport gCertificate = new CertificateReport(sys, driver, activity, date);
			String fileName = idDriver+"_"+activity.toLowerCase().replaceAll(" ", "_")+"_"+date.replaceAll("/", "_").replaceAll("-", "_")+".pdf";
	        fileName = SecurityConstants.DATACENTER5_WAR_DIRECTORY+"/certificate/"+fileName.toLowerCase();
	        FileOutputStream fos = new FileOutputStream(fileName);
			gCertificate.executeReport(fos);
	        
	    	return Base64.encode(new Util().read(new File(fileName)));
		}
		return null;
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
