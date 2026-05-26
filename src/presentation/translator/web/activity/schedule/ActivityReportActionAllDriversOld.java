package presentation.translator.web.activity.schedule;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jfree.report.JFreeReport;
import org.jfree.report.JFreeReportBoot;

import presentation.report.ReportUtil;
import presentation.report.activity.ActivityReportOldFactory;
import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;
import business.activity.ActivityReportFacade;

import communication.activity.report.ActivityReportOldData;
import communication.activity.tables.CEPAActivityData;
import communication.activity.tables.CEPAActivityScheduleData;
import communication.activity.tables.CEPAActivityScheduleRegistryData;
import communication.activity.tables.ItemData;
import communication.activity.tables.OldResultData;
import communication.activity.tables.PointData;
import communication.driver.tables.DriverData;
import communication.language.Text;

public class ActivityReportActionAllDriversOld extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
    	 
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
        ActivityReportFacade facade;
        CEPAActivityScheduleRegistryData completeResult;
        String idDriver = "";
        CEPAActivityData activity;
        String idActivity = request.getParameter("idActivity");    	  	
    	String idDrivers = request.getParameter("idDrivers");
    	String formatOutput = "PDF";
    	session.setAttribute("output",formatOutput);           	
        JFreeReport report;        
        DriverData driver;
        Collection drivers = this.stringToCollection(idDrivers);
        facade = sys.getActivityReportFacade();
        Collection consulta;
        ActivityReportOldData result;
        CEPAActivityScheduleData activitySchedule;
        ActivityReportOldFactory factory;
        Collection reports = new LinkedList();
        OldResultData or;
        String notaCorta, notaLarga, observaciones;
        boolean tieneItems;
        
        Iterator it = drivers.iterator();
        while (it.hasNext()){
        	idDriver = (String)it.next();        
            completeResult = facade.getCompleteResult(new Integer(idActivity),
            		new Integer(idDriver),sys.getUserSessionData());        
            activity = completeResult.getActivity();
            driver = completeResult.getDriver();
            consulta = consulta(new Integer(idActivity),new Integer(idDriver));
            result = new ActivityReportOldData(consulta);
            result.setMessages(messages);        
            activitySchedule = facade.findCEPAActivityScheduleByPrimaryKey(new Integer(idActivity)
        		,sys.getUserSessionData());
            notaCorta = ""; 
            notaLarga = "";
            observaciones = "";
            if(consulta != null && consulta.size() > 0) {
                or = (OldResultData)consulta.iterator().next();
                notaCorta = or.getNddesccorta();
                notaLarga = or.getNdtexto();
                observaciones = or.getCrobservaciones();
            }
            tieneItems = (consulta.size() > 0);
        	
            factory = new ActivityReportOldFactory(completeResult,
            		activitySchedule,driver,"Activity",result,form,sys,
            		messages,notaCorta,notaLarga,observaciones,tieneItems,
            		formatOutput); 

            report = factory.createReport();
            reports.add(report);
        }
        
        JFreeReportBoot.getInstance().start();

        //return ReportUtil.executeReport(mapping,response,report,"HTML",session,"activity");
        
        return new ReportUtil().executeReport(mapping,response,reports,formatOutput,session,"activity");
               
    }

    /**
     * 
     */
    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
    

    /**
     * 
     * @param stringList
     * @return
     */
    private Collection stringToCollection(String stringList){
		String[] stringArray=stringList.trim().split(";",0);
		Collection col = new LinkedList();
		
		for(int i=0; i < stringArray.length; i++){
			col.add(stringArray[i]);
		}
		
		return col;
	}
	
    /**
     * 
     * @param points
     * @param item
     * @return
     */   
    private PointData getPoint(Set points,ItemData item) {
    	Iterator iter = points.iterator();
    	boolean exists = false;
    	PointData point = null;
    	while (iter.hasNext() && (!exists)) {
    		point = (PointData) iter.next();
    		exists = point.getItems().contains(item); 
    	}
    	
    	return point;
    }
    
    
    /**
     * 
     * @param idActivity
     * @param idDriver
     * @return
     */
    private Collection consulta (Integer idActivity,Integer idDriver) {
    	Collection result = new LinkedList(); 
	try {
            
            Class.forName("org.postgresql.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/datacenter5","postgres","pr0v1s0r1A");
            //Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dc5","postgres","postgres");
            
            String sql = "SELECT * FROM resulatdosactividadesdc4 r where " +
            		"r.crcursoid = '" + idActivity + "' and r.crconductorid = '" + idDriver + "'";
             Statement st1 = conn.createStatement();
            ResultSet set = st1.executeQuery(sql);    
            
            OldResultData or = null;
                       
            while(set.next()) {
            	or = new OldResultData();
            	or.setCrconductorid(new Integer (set.getInt("crconductorid")));
            	or.setCrcursoid(new Integer (set.getInt("crcursoid")));
            	or.setNddesccorta(set.getString("nddesccorta"));
            	or.setNdtexto(set.getString("ndtexto"));
            	or.setTextoinformetexto(set.getString("textoinformetexto"));
            	or.setCrobservaciones(set.getString("crobservaciones"));
            	result.add(or);
            }      
        
        } catch (Exception e) {
        	try {
                
                Class.forName("org.postgresql.Driver");
                
//                Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/datacenter","postgres","postgres");
                Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/datacenter","postgres","pr0v1s0r1A");                
                
                
                String sql = "SELECT * FROM resulatdosactividadesdc4 r where " +
                		"r.crcursoid = '" + idActivity + "' and r.crconductorid = '" + idDriver + "'";
                 Statement st1 = conn.createStatement();
                ResultSet set = st1.executeQuery(sql);    
                
                OldResultData or = null;
                           
                while(set.next()) {
                	or = new OldResultData();
                	or.setCrconductorid(new Integer (set.getInt("crconductorid")));
                	or.setCrcursoid(new Integer (set.getInt("crcursoid")));
                	or.setNddesccorta(set.getString("nddesccorta"));
                	or.setNdtexto(set.getString("ndtexto"));
                	or.setTextoinformetexto(set.getString("textoinformetexto"));
                	or.setCrobservaciones(set.getString("crobservaciones"));
                	result.add(or);
                }      
            
            } 
        	catch (Exception e1) {
        		
        	}

        }
    return result;
    }
} 
    


