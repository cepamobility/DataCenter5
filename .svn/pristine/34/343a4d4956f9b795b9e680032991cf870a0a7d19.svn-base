package business.ws;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import persistence.administration.corporation.CorporationABM;
import persistence.administration.corporation.CorporationABMUtil;
import persistence.administration.user.UserReport;
import persistence.administration.user.UserReportUtil;
import persistence.vehicle.VehicleReport;
import persistence.vehicle.VehicleReportUtil;
import sun.misc.IOUtils;
import business.SystemAccessRemote;
import business.alert.handler.Util;
import business.vehicle.VehicleReportFacade;
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
import communication.activity.tables.CheckListData;
import communication.activity.tables.ItemData;
import communication.activity.tables.PointData;
import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.util.DPAUnconformityItemFilter;
import communication.administration.dpa.UnconformityDPAData;
import communication.administration.dpa.UnconformityItemDPAData;
import communication.administration.dpa.UnconformityReportDPADataSource;
import communication.administration.dpa.UnconformityTypeDPADataType;
import communication.administration.user.UserSessionData;
import communication.alert.AlertConstants;
import communication.driver.tables.DriverData;
import communication.driver.tables.DriverMainAttributes;
import communication.driver.tables.TelematicControlData;
import communication.language.Text;
import communication.report.JJChSumData;
import communication.report.JJCrashByChampionData;
import communication.report.JJCrashByTypeData;
import communication.report.JJCrashSubtypesData;
import communication.report.JJGoalsData;
import communication.report.JJIncidentByTypeData;
import communication.report.JJOGSumData;
import communication.report.JJOGSumTotalData;
import communication.report.JJResumeLAData;
import communication.security.SecurityConstants;
import communication.util.FileTransfer;
import communication.util.ListResult;
import communication.util.MailSender;
import communication.vehicle.report.CheckListUPMDataSource;
import communication.vehicle.report.ChecklistReportHead;
import communication.vehicle.tables.DPAGoalData;
import communication.vehicle.tables.DPAVehicleActiveData;
import communication.vehicle.tables.TrailerMainData;
import communication.vehicle.tables.VehicleCheckData;
import communication.vehicle.tables.VehicleCheckReportGroupData;
import communication.vehicle.tables.VehicleCheckResumeData;
import communication.vehicle.tables.VehicleData;

@Stateless(mappedName = "WSLDCWebReportToolsJJ")
@WebService(name="WSLDCWebReportToolsJJ")
public class WSLDCWebReportToolsJJ implements WSLFacade {
	
	
	@WebMethod(operationName="login")
	public User login(@WebParam(name = "u") String u, @WebParam(name = "p")String p){
		User user=new User(u,p);
		 try { 
			 
			SystemAccessRemote sys=CacheManager.getInstance().getSys(user);
			if(!sys.getUserSessionData().isCepaSystemUser() && !sys.getUserSessionData().isCorporation(CorporationData.JJ) ){
				return null;
			}
			
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
	
	
	@WebMethod(operationName="jjResumeLA")
	public JJResumeLAData jjResumeLA(@WebParam(name = "year") Integer year, @WebParam(name = "month") Integer month, @WebParam(name = "u")User user){
		JJResumeLAData data=new JJResumeLAData();
		 try { 
			 
			SystemAccessRemote sys=CacheManager.getInstance().getSys(user,false);
			
			data=sys.getBIReportFacade().jjResumeLA(year, month, sys.getUserSessionData());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
         return data;
	}
	
	@WebMethod(operationName="jjGoals")
	public JJGoalsData jjGoals(@WebParam(name = "year") Integer year, @WebParam(name = "u")User user){
		JJGoalsData data=new JJGoalsData();
		 try { 
			 
			SystemAccessRemote sys=CacheManager.getInstance().getSys(user,false);
			
			data=sys.getBIReportFacade().jjGoals(year,  sys.getUserSessionData());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
         return data;
	}
	
	
	
	@WebMethod(operationName="jjDataByCountry")
	public JJOGSumData[] jjDataByCountry(@WebParam(name = "year") Integer year, @WebParam(name = "month") Integer month, @WebParam(name = "u")User user){
		 
		 try { 
			 
			SystemAccessRemote sys=CacheManager.getInstance().getSys(user,false);
			
			ArrayList<JJOGSumData> data=sys.getBIReportFacade().jjDataByCountry(year, month, sys.getUserSessionData());
			
			return data.toArray(new JJOGSumData[]{});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
         return new JJOGSumData[]{};
	}
	
	@WebMethod(operationName="jjTotalDataByOG")
	public JJOGSumTotalData[] jjTotalDataByOG(@WebParam(name = "year") Integer year, @WebParam(name = "month") Integer month, @WebParam(name = "u")User user){
		 
		 try { 
			 
			SystemAccessRemote sys=CacheManager.getInstance().getSys(user,false);
			
			ArrayList<JJOGSumTotalData> data=sys.getBIReportFacade().jjTotalDataByOP(year, month, sys.getUserSessionData());
			
			return data.toArray(new JJOGSumTotalData[]{});
		} catch (Exception e) {
			e.printStackTrace();
		}
         
         return new JJOGSumTotalData[]{};
	}
	
	@WebMethod(operationName="jjDataBySubtype")
	public JJCrashSubtypesData[] jjDataBySubtype(@WebParam(name = "year") Integer year, @WebParam(name = "month") Integer month, @WebParam(name = "u")User user){
		 
		 try { 
			 
			SystemAccessRemote sys=CacheManager.getInstance().getSys(user,false);
			
			ArrayList<JJCrashSubtypesData> data=sys.getBIReportFacade().jjDataBySubtype(year, month, sys.getUserSessionData());
			
			return data.toArray(new JJCrashSubtypesData[]{});
		} catch (Exception e) {
			e.printStackTrace();
		}
         
         return new JJCrashSubtypesData[]{};
	}
	
	@WebMethod(operationName="jjIncidentByType")
	public JJIncidentByTypeData[] jjIncidentByType(@WebParam(name = "year") Integer year, @WebParam(name = "month") Integer month, @WebParam(name = "u")User user){
		 
		 try { 
			 
			SystemAccessRemote sys=CacheManager.getInstance().getSys(user,false);
			
			ArrayList<JJIncidentByTypeData> data=sys.getBIReportFacade().jjIncidentByType(year, month, sys.getUserSessionData());
			
			return data.toArray(new JJIncidentByTypeData[]{});
		} catch (Exception e) {
			e.printStackTrace();
		}
         
         return new JJIncidentByTypeData[]{};
	}
	
	@WebMethod(operationName="jjDataByMonth")
	public JJOGSumTotalData[] jjDataByMonth(@WebParam(name = "year") Integer year, @WebParam(name = "month") Integer month, @WebParam(name = "u")User user){
		 
		 try { 
			 
			SystemAccessRemote sys=CacheManager.getInstance().getSys(user,false);
			
			ArrayList<JJOGSumTotalData> data=sys.getBIReportFacade().jjDataByMonth(year, month, sys.getUserSessionData());
			
			return data.toArray(new JJOGSumTotalData[]{});
		} catch (Exception e) {
			e.printStackTrace();
		}
         
         return new JJOGSumTotalData[]{};
	}
	
	@WebMethod(operationName="jjDataByChampion")
	public JJChSumData[] jjDataByChampion(@WebParam(name = "year") Integer year, @WebParam(name = "month") Integer month, @WebParam(name = "u")User user){
		 
		 try { 
			 
			SystemAccessRemote sys=CacheManager.getInstance().getSys(user,false);
			
			ArrayList<JJChSumData> data=sys.getBIReportFacade().jjDataByChampion(year, month, sys.getUserSessionData());
			
			return data.toArray(new JJChSumData[]{});
		} catch (Exception e) {
			e.printStackTrace();
		}
         
         return new JJChSumData[]{};
	}
	
	@WebMethod(operationName="jjCrashByType")
	public JJCrashByTypeData[] jjCrashByType(@WebParam(name = "year") Integer year, @WebParam(name = "month") Integer month, @WebParam(name = "u")User user){
		 
		 try { 
			 
			SystemAccessRemote sys=CacheManager.getInstance().getSys(user,false);
			
			ArrayList<JJCrashByTypeData> data=sys.getBIReportFacade().jjCrashBytype(year, month, sys.getUserSessionData());
			
			return data.toArray(new JJCrashByTypeData[]{});
		} catch (Exception e) {
			e.printStackTrace();
		}
         
         return new JJCrashByTypeData[]{};
	}
	
	@WebMethod(operationName="jjCrashByChampion")
	public JJCrashByChampionData[] jjCrashByChampion(@WebParam(name = "year") Integer year, @WebParam(name = "month") Integer month, @WebParam(name = "u")User user){
		 
		 try { 
			 
			SystemAccessRemote sys=CacheManager.getInstance().getSys(user,false);
			
			ArrayList<JJCrashByChampionData> data=sys.getBIReportFacade().JJCrashByChampion(year, month, sys.getUserSessionData());
			
			return data.toArray(new JJCrashByChampionData[]{});
		} catch (Exception e) {
			e.printStackTrace();
		}
         
         return new JJCrashByChampionData[]{};
	}
	
	
}
