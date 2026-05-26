package business.ws;

import java.text.SimpleDateFormat; 
import java.util.Calendar;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import com.sun.jersey.api.core.HttpRequestContext;

import business.SystemAccessRemote;
import business.ws.cache.CacheManager;
import business.ws.client.security.User;
import communication.administration.corporation.tables.EasyTrackEventData;
import communication.administration.corporation.tables.MobilTrackEventData;

@Stateless(mappedName = "WSLDCETTelematicControlAPI")
@WebService(name="WSLDCETTelematicControlAPI")
public class WSLDCETTelematicControlAPI{
	@Context
	private HttpServletRequest request;
	
	//Mutua exclusion
	private static Object o=new Object();
	private static Calendar lastCall=Calendar.getInstance();
	
	private static Long cKey;
	
	private class RunSaveTelematicEvent implements Runnable{

		public EasyTrackEventData dataEasy;
		public SystemAccessRemote sys;
		
		
		@Override
		public void run() {
			try{
				synchronized (o) {
					sys.getCorporationABMFacade().saveEasyTrackEvent(dataEasy, sys.getUserSessionData());
				}		
			}catch(Throwable th){
				String text="";
				text="EasyTrack";
				System.out.println("WSLDCETTelematicControlAPI: Error al salva un evento de "+text+"  "+th.getMessage());
				th.printStackTrace();
			}
		}
	}
	 

	@WebMethod(operationName="saveEasyTrackTelematicEvent")
	public void saveEasyTrackTelematicEvent(@WebParam(name = "u") String u, @WebParam(name = "p")String p,
			@WebParam(name = "fechaHora") String fechaHora,@WebParam(name = "matricula") String matricula,
			@WebParam(name = "ci") String ci,
			@WebParam(name = "empresa") String empresa, @WebParam(name = "dEvento") String dEvento,
			@WebParam(name = "velDef") String velDef, @WebParam(name = "velGps") String velGps,
			@WebParam(name = "velVehiculo") String velVehiculo,@WebParam(name = "viaje") String viaje,
			@WebParam(name = "idReporte") String idReporte) throws Exception{
		
		if(!(u.equalsIgnoreCase("etwsus") && p.equalsIgnoreCase("1335689ZP!"))){
			throw new Exception("User Invalid");
		}
		
		Integer vel=velGps!=null?Integer.valueOf(velGps):0;
		if(vel<90){
			return;
		}
		
		boolean next=false;
   		synchronized (o) {
			Calendar calNow=Calendar.getInstance();
			calNow.add(Calendar.SECOND, -10);
			if(calNow.compareTo(lastCall)<0){
				next=true;
				Thread.sleep(1000);
			}
		}
   		
		//Validado el usuario y contrasena, se usa uno espelcifico de la corporacion para no darle al
		//proveedor de telematica un usuario de dc.
		User user=new User("wstelematicet","132543");
		user.setcKey(cKey);
		if(!next){
			try { 
				lastCall=Calendar.getInstance();
				
				SystemAccessRemote sys= CacheManager.getInstance().getSys(user);
				cKey=user.getcKey();
				EasyTrackEventData data=new EasyTrackEventData();
				data.setdEvento(dEvento);
				data.setEmpresa(empresa);
				SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				data.setFechaHora(format.parse(fechaHora));
				System.out.println("ET: matricula "+matricula+" Hora que manda "+fechaHora+", hora que guardo "+format.format(data.getFechaHora()));
				data.setIdReporte(idReporte!=null?Long.valueOf(idReporte):0);
				data.setMatricula(matricula);
				data.setVelDef(velDef!=null?Integer.valueOf(velDef):0);
				data.setVelGps(velGps!=null?Integer.valueOf(velGps):0);
				data.setVelVehiculo(velVehiculo!=null?Integer.valueOf(velVehiculo):0);
				data.setViaje(viaje!=null?Long.valueOf(viaje):0);
				data.setCi(ci);
				
				RunSaveTelematicEvent runSave=new RunSaveTelematicEvent();
				runSave.dataEasy=data;
				runSave.sys=sys;
				
				new Thread(runSave).start();
				
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}else{
			saveEasyTrackTelematicEvent(u, p, fechaHora, matricula, ci,empresa, dEvento, velDef, velGps, velVehiculo, viaje, idReporte);
		}
		 
	}
}
