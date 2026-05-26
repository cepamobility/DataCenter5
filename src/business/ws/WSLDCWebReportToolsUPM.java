package business.ws;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import persistence.alert.tables.ContractorData;
import persistence.report.BIReportUtil;
import business.SystemAccessRemote;
import business.ws.cache.CacheManager;
import business.ws.client.WSL;
import business.ws.client.common.ListWSLVehicleStatusDataResult;
import business.ws.client.report.VehicleListFilter;
import business.ws.client.security.User;
import business.ws.client.vehicle.WSLVehicleStatusData;
import communication.administration.corporation.CalculateImpactData;
import communication.administration.corporation.tables.CorporationConfigData;
import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.PointMoveData;
import communication.administration.corporation.tables.PointsDefinitionData;
import communication.administration.user.PermissionData;
import communication.administration.user.UserSessionData;
import communication.fleet.ContractorPointData;
import communication.fleet.FleetStatusData;
import communication.util.ListResult;
import communication.vehicle.tables.VehicleStatusData;

@Stateless(mappedName = "WSLDCWebReportToolsUPM")
@WebService(name="WSLDCWebReportToolsUPM")
public class WSLDCWebReportToolsUPM extends WSLDCWebReportToolsMDP {
	
    @Resource
    private WebServiceContext wsContext;
    
	 

	@WebMethod(operationName="login")
	public User login(@WebParam(name = "u") String u, @WebParam(name = "p")String p){
		User user=new User(u,p);
		 try { 
			 
			SystemAccessRemote sys=CacheManager.getInstance().getSys(user);
			user.setFullName(sys.getUserSessionData().getFullName());
			user.setFlags(new String[]{"configpanelnoaccess"});
			
			Collection<PermissionData> permissions=sys.getUserSessionData().getPermissionsData(); 
			for(PermissionData permission: permissions){
				if(permission.getName().equalsIgnoreCase("pointconfupdate")){
					user.setFlags(new String[]{"configpanelaccess"});
					break;
				}
			}
			
			if(!sys.getUserSessionData().isCorporation(CorporationData.FORESTALORIENTAL) ){
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
			//MessageContext mc = wsContext.getMessageContext();
	        /*HttpSession session = ((javax.servlet.http.HttpServletRequest)mc.get(MessageContext.SERVLET_REQUEST)).getSession();
	        session.setAttribute("user", user);
	        session.setAttribute("sys", sys);
	        MessageContext mc = wsContext.getMessageContext();
	        HttpSession session = ((javax.servlet.http.HttpServletRequest)mc.get(MessageContext.SERVLET_REQUEST)).getSession();
	        SystemAccessRemote sys= (SystemAccessRemote) session.getAttribute("sys");
	        */
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			user=null;
		}
         
         return user;
	}
	
	
	@WebMethod(operationName="getCorporationConfig")
	public CorporationConfigData[] getCorporationConfig(@WebParam(name = "u") User user) throws Exception{
		SystemAccessRemote sys= CacheManager.getInstance().getSys(user);
        
        try{
        	ArrayList<CorporationConfigData> conf =new ArrayList<CorporationConfigData>(sys.getCorporationReportFacade().getCorporationConfig(sys.getCorporationId(), sys.getUserSessionData()).values());
           	return conf.toArray(new CorporationConfigData[]{});
        }catch(Exception ex){
        	ex.printStackTrace();
        }
        
        return new CorporationConfigData[]{};
	}
	
	@WebMethod(operationName="saveCorporationConfig")
	public void saveCorporationConfig(@WebParam(name = "u") User user,@WebParam(name = "configs") CorporationConfigData[] configs) throws Exception{
        SystemAccessRemote sys= CacheManager.getInstance().getSys(user);
        
        try{
        	 ArrayList<CorporationConfigData> list=new ArrayList<CorporationConfigData>();
        	 for(CorporationConfigData data:configs){
        		 list.add(data);
        	 }
        	 sys.getCorporationABMFacade().saveCorporationConfig(sys.getCorporationId(), list, sys.getUserSessionData());
        	 
        }catch(Exception ex){
        	ex.printStackTrace();
        	
        }
	}
	
	
	@WebMethod(operationName="getPointsDefinitions")
	public PointsDefinitionData[] getPointsDefinitions(@WebParam(name = "u") User user) throws Exception{
		SystemAccessRemote sys= CacheManager.getInstance().getSys(user);
        try{
        	
        	ArrayList<PointsDefinitionData> defsSeg=sys.getCorporationReportFacade().getPointsDefinitions(PointsDefinitionData.SEG_ROUTE,sys.getUserSessionData());
        	ArrayList<PointsDefinitionData> defsSpeed=sys.getCorporationReportFacade().getPointsDefinitions(PointsDefinitionData.FOSA_SPEED,sys.getUserSessionData());
        	ArrayList<PointsDefinitionData> defsSpeed2=sys.getCorporationReportFacade().getPointsDefinitions(PointsDefinitionData.FOSA_SPEED_2,sys.getUserSessionData());
        	ArrayList<PointsDefinitionData> def=new ArrayList<PointsDefinitionData>(defsSeg);
        	def.addAll(defsSpeed);
        	def.addAll(defsSpeed2);
        	
        	return def.toArray(new PointsDefinitionData[]{});
        }catch(Exception ex){
        	ex.printStackTrace();
        	
        }
       
        return new PointsDefinitionData[]{};
	}
	
	@WebMethod(operationName="savePointsDefinitions")
	public void savePointsDefinitions(@WebParam(name = "u") User user, @WebParam(name = "pd") PointsDefinitionData[] pointsDef) throws Exception{
		SystemAccessRemote sys= CacheManager.getInstance().getSys(user);

		try{
        	
        	ArrayList<PointsDefinitionData> values=new ArrayList<PointsDefinitionData>();
        	for(PointsDefinitionData data:pointsDef){
        		values.add(data);
        	}
        	
        	sys.getCorporationABMFacade().savePointsDefinitions(values, sys.getUserSessionData());
        	 
        }catch(Exception ex){
        	ex.printStackTrace();        	
        }
       
	}
	
	@WebMethod(operationName="findVehicleWithStatusList")
	public ListWSLVehicleStatusDataResult findVehicleWithStatusList(@WebParam(name = "filter") VehicleListFilter filter, @WebParam(name = "u")User user){
		ListWSLVehicleStatusDataResult result=new ListWSLVehicleStatusDataResult();
		 try { 
			    System.out.println("CACHE PROBLEM ANTES "+user.getUserlogin());
			 
				SystemAccessRemote sys=CacheManager.getInstance().getSys(user,false);
				 
			    System.out.println("CACHE PROBLEM DESPUES "+sys.getUserSessionData().getFilter().getLoginName());
				
				ListResult res=sys.getBIReportFacade().findVehicleWithStatusList(filter.getText(), sys.getUserSessionData(), 6, filter.getFirst(), filter.getCount(), filter.getStatus(),sys.getText());
				
				WSLVehicleStatusData[] resArray=new WSLVehicleStatusData[res.getElements().size()];
				
				int i=0;
				Iterator<VehicleStatusData>  iter=res.getElements().iterator();
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
	
	@WebMethod(operationName="findVehicleWithStatus")
	public WSLVehicleStatusData findVehicleWithStatus(@WebParam(name = "vehicleId") Integer vehicleId, @WebParam(name = "u")User user){
		 try { 
			SystemAccessRemote sys=CacheManager.getInstance().getSys(user,false);
			 
			VehicleStatusData res=sys.getBIReportFacade().findVehicleWithStatus(vehicleId, sys.getUserSessionData(),sys.getText());
		
			return WSL.t(res, sys.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}
         
 	   return null;
	}
	
	 @WebMethod(operationName="findFOSAContractors")
	 public ArrayList<ContractorData> findFOSAContractors( @WebParam(name = "u")User user) throws Exception {
    	try{    
    		SystemAccessRemote sys=CacheManager.getInstance().getSys(user,false);
    		
    		return sys.getBIReportFacade().findFOSAContractors(sys.getUserSessionData());
    				
        }catch(Throwable th){
			th.printStackTrace();
		}
        return new ArrayList<ContractorData>();    
    }
	 
	@WebMethod(operationName="findContractorsPointData")
    public ArrayList<ContractorPointData> findContractorsPointData(@WebParam(name = "startYear") Integer startYear, @WebParam(name = "startMonth") Integer startMonth, @WebParam(name = "startDay") Integer startDay,@WebParam(name = "endYear") Integer endYear, 
    		@WebParam(name = "endMonth") Integer endMonth, @WebParam(name = "endDay") Integer endDay,
    		@WebParam(name = "contractorId") Integer contractorId,@WebParam(name = "u")User user ) throws Exception {
		try{   
			SystemAccessRemote sys=CacheManager.getInstance().getSys(user,false);
			Calendar calStart=Calendar.getInstance();
			calStart.set(Calendar.YEAR, startYear);
			calStart.set(Calendar.MONTH, startMonth-1);
			calStart.set(Calendar.DATE, startDay);
			Calendar calEnd=Calendar.getInstance();
			calEnd.set(Calendar.YEAR, endYear);
			calEnd.set(Calendar.MONTH, endMonth-1);
			calEnd.set(Calendar.DATE, endDay);
			
			return sys.getBIReportFacade().findContractorsPointData(calStart.getTime(), calEnd.getTime(), contractorId, sys.getUserSessionData());
			
			
        }catch(Throwable th){
			th.printStackTrace();
		}
		return new ArrayList<ContractorPointData>();
    }
    
	@WebMethod(operationName="findFleetStatus")
	public FleetStatusData findFleetStatus(@WebParam(name = "u")User user){
		 try { 
			SystemAccessRemote sys=CacheManager.getInstance().getSys(user,false);
			Calendar cal=Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.HOUR,6);
			FleetStatusData res=sys.getBIReportFacade().findFleetWithStatusList(cal.getTime(), sys.getUserSessionData(), sys.getText());
			
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
         
 	   return null;
	}	
	
	
	@WebMethod(operationName="savePointMove")
	public void savePointMove(@WebParam(name = "u") User user, @WebParam(name = "moveData") PointMoveData moveData) throws Exception{
		SystemAccessRemote sys= CacheManager.getInstance().getSys(user);

		try{
        	if(user.getFlags().length>0 && user.getFlags()[0].equalsIgnoreCase("configpanelaccess")){
        		PointsDefinitionData def= sys.getCorporationReportFacade().getPointsDefinitions(PointsDefinitionData.MANUAL,"", sys.getUserSessionData());
        		moveData.setDefinition(def.getId());
        		moveData.setReference("0");
        		sys.getCorporationABMFacade().savePointMove(moveData, sys.getUserSessionData());	
        	}
        	 
        }catch(Exception ex){
        	ex.printStackTrace();        	
        }
       
	}
	
	@WebMethod(operationName="calculateImpact")
	public CalculateImpactData calculateImpact(@WebParam(name = "u") User user,  PointsDefinitionData[] pointsDef, CorporationConfigData[] configs, Integer backDays) throws Exception{
		SystemAccessRemote sys= CacheManager.getInstance().getSys(user);
		CalculateImpactData res=new CalculateImpactData();
		try{
        	if(user.getFlags().length>0 && user.getFlags()[0].equalsIgnoreCase("configpanelaccess")){
        		ArrayList<PointsDefinitionData> auxDefinitions=new ArrayList<PointsDefinitionData>();
        		for(PointsDefinitionData data:pointsDef){
        			auxDefinitions.add(data);
        		}
        		ArrayList<CorporationConfigData> auxCorporationConfigData=new ArrayList<CorporationConfigData>();
        		for(CorporationConfigData data:configs){
        			auxCorporationConfigData.add(data);
        		}
        		res=sys.getBIReportFacade().pointsCalculateRetroactiveImapact(auxDefinitions,auxCorporationConfigData, backDays, sys.getUserSessionData());
        	}
        	 
        }catch(Exception ex){
        	ex.printStackTrace();        	
        }
		
		return res;
       
	}
	
}
