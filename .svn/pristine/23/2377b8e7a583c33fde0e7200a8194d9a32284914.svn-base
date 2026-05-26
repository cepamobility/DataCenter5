/*
 * Created on 24-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
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


/**
 * @author adocarmo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ActivityReportActionOld extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
    	 
        HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();

        String idActivity=request.getParameter("idActivity");
    	  	
    	String idDriver=request.getParameter("idDriver");

    	String formatOutput = request.getParameter("output");
    	session.setAttribute("output",formatOutput);
           	
        JFreeReport report;
        
        
        ActivityReportFacade facade = sys.getActivityReportFacade();
        CEPAActivityScheduleRegistryData completeResult = facade.
        	getCompleteResult(new Integer(idActivity),
        	new Integer(idDriver),sys.getUserSessionData());        
        CEPAActivityData activity = completeResult.getActivity();
        DriverData driver = completeResult.getDriver();

        Collection consulta = consulta(new Integer(idActivity),new Integer(idDriver));
        
        ActivityReportOldData result = new ActivityReportOldData(consulta);
        
        result.setMessages(messages);
        
        CEPAActivityScheduleData activitySchedule = facade.findCEPAActivityScheduleByPrimaryKey(new Integer(idActivity)
        		,sys.getUserSessionData());
        
//        session.setAttribute("idCorp",activitySchedule.getCorpooration().getId());

        String notaCorta= "";
    	String notaLarga= "";
    	String observaciones= "";
        ActivityReportOldFactory factory;
        if (!consulta.isEmpty()){
        	OldResultData or = (OldResultData)consulta.iterator().next();
        	notaCorta= or.getNddesccorta();
        	notaLarga= or.getNdtexto();
        	observaciones= or.getCrobservaciones();
        }
        //boolean tieneItems = (consulta.size() > 1);
        boolean tieneItems = (consulta.size() > 0);
        	
        factory = new ActivityReportOldFactory(completeResult,
        		activitySchedule,driver,"Activity",result,form,sys,
        		messages,notaCorta,notaLarga,observaciones,tieneItems,
        		formatOutput); 

        report = factory.createReport();	
        
        JFreeReportBoot.getInstance().start();
        session.setAttribute("guide","guide");
        //return ReportUtil.executeReport(mapping,response,report,"HTML",session,"activity");
        
        return new ReportUtil().executeReport(mapping,response,report,formatOutput,session,"activity",true);
               
    }

    public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return null;
    }
    
    /*
    ActivityReportData getReportActivity(CEPAActivityScheduleRegistryData registry) {
    	
        Collection items =  registry.getItemSet(); 
        Collection result = new LinkedList();
        Iterator iter2 = items.iterator();           
        while (iter2.hasNext()) {
        	Item item = (Item) iter2.next();
        	Point point = getPoint(registry.getActivity().getDefinition().getCheckList().getPoints(),item);
        	PointItem ip = new PointItem(point,item);
            result.add(ip);
        }       
        return new ActivityReportData(result);        
    }
    */
    
    public PointData getPoint(Set points,ItemData item) {
    	Iterator iter = points.iterator();
    	boolean exists = false;
    	PointData point = null;
    	while (iter.hasNext() && (!exists)) {
    		point = (PointData) iter.next();
    		exists = point.getItems().contains(item); 
    	}
    	
    	return point;
    }
    
    private Collection consulta (Integer idActivity,Integer idDriver) {

    	Collection result = new LinkedList(); 
    	Connection conn = null;
    	try {
          
    		// Servidor de Testing:
    		Class.forName("org.postgresql.Driver");
    		conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dc5","postgres","postgres");
    	}catch(Exception e){
    		try{
				// Servidor de Poduccion:
				Class.forName("org.postgresql.Driver");
				conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/datacenter5","postgres","pr0v1s0r1A");                
			}catch(Exception ee){
				try{
					// Maquinas de Desarrollo:
					Class.forName("org.postgresql.Driver");
					conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/datacenter","postgres","postgres");                			
				}catch (Exception eee){
					System.out.println("Eception in ActivityReportActionOld: "+eee.getMessage());
					eee.printStackTrace();									
				}
			}
    	}

    	try{    
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
			System.out.println("Eception in ActivityReportActionOld: "+e.getMessage());
			e.printStackTrace();				
        }
        return result;
    }
} 
    


