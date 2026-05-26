package business.ws;

import java.util.Collection;
import java.util.Iterator;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import business.SystemAccessRemote;
import business.fleet.FleetABMFacade;
import business.ws.cache.CacheManager;
import business.ws.client.security.User;
import business.ws.client.vehicle.WSLVehicleOdometerData;

import communication.fleet.tables.OdometerData;
import communication.util.ListResult;
import communication.util.MD5;
import communication.vehicle.tables.VehicleData;

@Stateless(mappedName = "WSLOdometer")
@WebService(name="WSLOdometer")
public class WSLOdometer {
	
	@WebMethod(operationName="loginodometer")
	public User loginodometer(@WebParam(name = "d") String d){
		MD5 md5 = new MD5();
		String u = md5.encriptar("fleetUser"+d+"_1");
		String p = md5.encriptar(u);
		User user=new User(u,p);
		try { 
			SystemAccessRemote sys=CacheManager.getInstance().getSys(user);
			user.setFullName(sys.getUserSessionData().getFullName());
			if(sys.getUserSessionData().getDriverData() != null) {
				user.setDriver(sys.getUserSessionData().getDriverData().getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			user=null;
		}
        return user;
	}
	
	@WebMethod(operationName="findVehicles")
	public WSLVehicleOdometerData[] findVehicles(@WebParam(name = "d")Integer d){
		MD5 md5 = new MD5();
		String u = md5.encriptar("fleetUser"+d+"_1");
		String p = md5.encriptar(u);
		User user=new User(u,p);
		 try {
			SystemAccessRemote sys=CacheManager.getInstance().getSys(user);
			ListResult result = sys.getVehicleReportFacade().findVehicleList("matricula","",0,-1,VehicleData.ACTIVE,sys.getUserSessionData());
			WSLVehicleOdometerData[] values = new WSLVehicleOdometerData[result.getTotal()];
			Iterator it = result.getElements().iterator();
			int index = 0;
			while(it.hasNext()) {
				VehicleData vehicleData = (VehicleData)it.next();
				WSLVehicleOdometerData vehicleWSL = new WSLVehicleOdometerData(vehicleData.getId(),vehicleData.getMatricula());
				Collection odometers = sys.getFleetReportFacade().findVehicleOdometers(vehicleData.getId(), sys.getUserSessionData());
				if(odometers != null) {
					Iterator itOdometer = odometers.iterator();
					if(itOdometer.hasNext()) {
						Object[] data = (Object[])itOdometer.next();
						vehicleWSL.setLastOdometer((Integer)data[0]);
						vehicleWSL.setLastPeriod((Integer)data[1]);
						vehicleWSL.setLastYear((Integer)data[2]);
					}
					while(itOdometer.hasNext() && vehicleWSL.getPreviousOdometer() == null) {
						Object[] data = (Object[])itOdometer.next();
						if(!(data[1].equals(vehicleWSL.getLastPeriod()) && data[2].equals(vehicleWSL.getLastYear()))) {
							vehicleWSL.setPreviousOdometer((Integer)data[0]);
							vehicleWSL.setPreviousPeriod((Integer)data[1]);
							vehicleWSL.setPreviousYear((Integer)data[2]);
						}
					}
				}
				values[index] = vehicleWSL;
				index++;
			}
			return values;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
   }

	@WebMethod(operationName="saveOdometer")
	public void saveOdometer(@WebParam(name = "v") Integer v, @WebParam(name = "o") Integer o, @WebParam(name = "p") Integer p,@WebParam(name = "y") Integer y, @WebParam(name = "d")Integer d){
		MD5 md5 = new MD5();
		String user = md5.encriptar("fleetUser"+d+"_1");
		String pass = md5.encriptar(user);
		User u=new User(user,pass);
		try {
			SystemAccessRemote sys=CacheManager.getInstance().getSys(u);
            FleetABMFacade fleetABM = sys.getFleetABMFacade();
            OdometerData odometer = new OdometerData();
            odometer.setVehicle(new VehicleData(v));
            odometer.setPeriod(p);
            odometer.setYear(y);
            odometer.setOdometer(o);
            odometer.setAccidents(0);
            odometer.setIncidents(0);
            fleetABM.insertOwnOdometer(odometer,sys.getUserSessionData());
		} catch (Exception e) {
			e.printStackTrace();
		}
   }
}
