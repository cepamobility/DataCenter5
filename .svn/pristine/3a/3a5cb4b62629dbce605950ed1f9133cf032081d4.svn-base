package presentation.translator.web.documents;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javapns.Push;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import persistence.util.Util;
import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;
import business.administration.corporation.CorporationReportFacade;

import communication.administration.corporation.tables.CorporationData;
import communication.alert.AlertConstants;
import communication.language.LanguageConstants;
import communication.security.SecurityConstants;
import communication.util.tables.AppFileData;

public class UpdateAppFileDatabaseAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
       
    	HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
         
    
   
 
        if(sys.isCorporation(CorporationData.JJ)) {
        	 return actionJJ(mapping, form, request, response);
        }else {
        	 return actionCEPA(mapping, form, request, response);        	 
        }
 		
    }
    
    public ActionForward actionCEPA(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
    	HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
         
        String updateLine = ((DynaActionForm)form).getString("updateLine");
    	String sDriverName = "org.sqlite.JDBC";
    	Class.forName(sDriverName);
     
    	// now we set up a set of fairly basic string variables to use in the body of the code proper
        String certificate = "";
        String passwd = "";
        String sTempDb = ""; 
        String appId = "";
     
    	sTempDb = SecurityConstants.APPCEPA_DIRECTORY+"db/CEPA_DB.db";
    	certificate = SecurityConstants.APPCEPA_DIRECTORY+"PushCepaCER.p12";
    	passwd = "CepaApp2";
    	appId = "Cepa";
  

    	File f = new File(sTempDb);
    	f.delete();
    	
    	String sJdbc = "jdbc:sqlite";
    	String sDbUrl = sJdbc + ":" + sTempDb;
    	// which will produce a legitimate Url for SqlLite JDBC :
    	// jdbc:sqlite:hello.db
    	int iTimeout = 30;
     
    	// create a database connection
    	Connection conn = DriverManager.getConnection(sDbUrl);
		CorporationReportFacade report = sys.getCorporationReportFacade();
    	try {
    		Statement stmt = conn.createStatement();
    		try {
    			stmt.setQueryTimeout(iTimeout);

    			stmt.executeUpdate("CREATE TABLE Categories_ES (serial integer  PRIMARY KEY AUTOINCREMENT,categoryType TEXT(10) DEFAULT NULL)");
    			stmt.executeUpdate("INSERT INTO Categories_ES VALUES(1,'Politics_ES')");
    			stmt.executeUpdate("INSERT INTO Categories_ES VALUES(2,'Tips_ES')");
    			stmt.executeUpdate("INSERT INTO Categories_ES VALUES(3,'News_ES')");;

    			stmt.executeUpdate("CREATE TABLE Categories_IN (serial integer  PRIMARY KEY AUTOINCREMENT,categoryType TEXT(10) DEFAULT NULL)");
    			stmt.executeUpdate("INSERT INTO Categories_IN VALUES(1,'Politics_IN')");
    			stmt.executeUpdate("INSERT INTO Categories_IN VALUES(2,'Tips_IN')");
    			stmt.executeUpdate("INSERT INTO Categories_IN VALUES(3,'News_IN')");;

    			stmt.executeUpdate("CREATE TABLE Categories_PT (serial integer  PRIMARY KEY AUTOINCREMENT,categoryType TEXT(10) DEFAULT NULL)");
    			stmt.executeUpdate("INSERT INTO Categories_PT VALUES(1,'Politics_PT')");
    			stmt.executeUpdate("INSERT INTO Categories_PT VALUES(2,'Tips_PT')");
    			stmt.executeUpdate("INSERT INTO Categories_PT VALUES(3,'News_PT')");;

    			//La tabla Led no está del todo claro para que se usa
    			stmt.executeUpdate("CREATE TABLE Led_PT (ledID integer  PRIMARY KEY AUTOINCREMENT DEFAULT NULL,Messages TEXT DEFAULT NULL)");
    			stmt.executeUpdate("INSERT INTO Led_PT VALUES(1,'BEM-VINDO AO NOVO APLICATIVO DO CEPA. UM CANAL DE COMUNICAÇÃO COM NOTÍCIAS E INFORMAÇÕES PARA AJUDAR NA SUA SEGURANÇA NO TRÂNSITO.')");

    			stmt.executeUpdate("CREATE TABLE Led_IN (ledID integer  PRIMARY KEY AUTOINCREMENT DEFAULT NULL,Messages TEXT DEFAULT NULL)");
    			stmt.executeUpdate("INSERT INTO Led_IN VALUES(1,'WELCOME! A NEW APPLICATION SECURITY FLEET. COMMUNICATION CHANNEL WITH NEWS AND INFORMATION TO HELP YOUR TRAFFIC SAFETY')");

    			stmt.executeUpdate("CREATE TABLE Led_ES (ledID integer  PRIMARY KEY AUTOINCREMENT DEFAULT NULL,Messages TEXT DEFAULT NULL)");
    			stmt.executeUpdate("INSERT INTO Led_ES VALUES(1,'BIENVENIDO! A LA NUEVA APLICACIÓN DE SEGURIDAD EN FLOTAS. UN CANAL DE COMUNICACIÓN CON NOTICIAS E INFORMACIÓN PARA AYUDAR A SU SEGURIDAD EN EL TRÁNSITO')");

    			stmt.executeUpdate("CREATE TABLE News_ES (id integer  PRIMARY KEY AUTOINCREMENT DEFAULT NULL,Title TEXT(45) DEFAULT NULL,SubTitle TEXT(60)  DEFAULT NULL,Description TEXT(370) DEFAULT NULL,Category TEXT(60)  DEFAULT NULL,DocumentType TEXT(4) DEFAULT NULL,DocumentURL TEXT(100) DEFAULT NULL,DocumentName TEXT(30),ImagePreviewURL TEXT(150) DEFAULT NULL,ImagePreviewName TEXT(30),lastUpdate TEXT(10) DEFAULT NULL)");   			
    			stmt.executeUpdate("CREATE TABLE Politics_ES (id integer  PRIMARY KEY AUTOINCREMENT DEFAULT NULL,Title TEXT(45) DEFAULT NULL,SubTitle TEXT(60)  DEFAULT NULL,Description TEXT(370) DEFAULT NULL,Category TEXT(60)  DEFAULT NULL,DocumentType TEXT(4) DEFAULT NULL,DocumentURL TEXT(100) DEFAULT NULL,DocumentName TEXT(30),ImagePreviewURL TEXT(150) DEFAULT NULL,ImagePreviewName TEXT(30),lastUpdate TEXT(10))");
    			stmt.executeUpdate("CREATE TABLE Tips_ES (id integer  PRIMARY KEY AUTOINCREMENT DEFAULT NULL,Title TEXT(45) DEFAULT NULL,SubTitle TEXT(60)  DEFAULT NULL,Description TEXT(370) DEFAULT NULL,Category TEXT(60)  DEFAULT NULL,DocumentType TEXT(4) DEFAULT NULL,DocumentURL TEXT(100) DEFAULT NULL,DocumentName TEXT(30),ImagePreviewURL TEXT(150) DEFAULT NULL,ImagePreviewName TEXT(30),lastUpdate TEXT(10))");

    			stmt.executeUpdate("CREATE TABLE News_IN (id integer  PRIMARY KEY AUTOINCREMENT DEFAULT NULL,Title TEXT(45) DEFAULT NULL,SubTitle TEXT(60)  DEFAULT NULL,Description TEXT(370) DEFAULT NULL,Category TEXT(60)  DEFAULT NULL,DocumentType TEXT(4) DEFAULT NULL,DocumentURL TEXT(100) DEFAULT NULL,DocumentName TEXT(30),ImagePreviewURL TEXT(150) DEFAULT NULL,ImagePreviewName TEXT(30),lastUpdate TEXT(10) DEFAULT NULL)");   			
    			stmt.executeUpdate("CREATE TABLE Politics_IN (id integer  PRIMARY KEY AUTOINCREMENT DEFAULT NULL,Title TEXT(45) DEFAULT NULL,SubTitle TEXT(60)  DEFAULT NULL,Description TEXT(370) DEFAULT NULL,Category TEXT(60)  DEFAULT NULL,DocumentType TEXT(4) DEFAULT NULL,DocumentURL TEXT(100) DEFAULT NULL,DocumentName TEXT(30),ImagePreviewURL TEXT(150) DEFAULT NULL,ImagePreviewName TEXT(30),lastUpdate TEXT(10))");
    			stmt.executeUpdate("CREATE TABLE Tips_IN (id integer  PRIMARY KEY AUTOINCREMENT DEFAULT NULL,Title TEXT(45) DEFAULT NULL,SubTitle TEXT(60)  DEFAULT NULL,Description TEXT(370) DEFAULT NULL,Category TEXT(60)  DEFAULT NULL,DocumentType TEXT(4) DEFAULT NULL,DocumentURL TEXT(100) DEFAULT NULL,DocumentName TEXT(30),ImagePreviewURL TEXT(150) DEFAULT NULL,ImagePreviewName TEXT(30),lastUpdate TEXT(10))");

    			stmt.executeUpdate("CREATE TABLE News_PT (id integer  PRIMARY KEY AUTOINCREMENT DEFAULT NULL,Title TEXT(45) DEFAULT NULL,SubTitle TEXT(60)  DEFAULT NULL,Description TEXT(370) DEFAULT NULL,Category TEXT(60)  DEFAULT NULL,DocumentType TEXT(4) DEFAULT NULL,DocumentURL TEXT(100) DEFAULT NULL,DocumentName TEXT(30),ImagePreviewURL TEXT(150) DEFAULT NULL,ImagePreviewName TEXT(30),lastUpdate TEXT(10) DEFAULT NULL)");   			
    			stmt.executeUpdate("CREATE TABLE Politics_PT (id integer  PRIMARY KEY AUTOINCREMENT DEFAULT NULL,Title TEXT(45) DEFAULT NULL,SubTitle TEXT(60)  DEFAULT NULL,Description TEXT(370) DEFAULT NULL,Category TEXT(60)  DEFAULT NULL,DocumentType TEXT(4) DEFAULT NULL,DocumentURL TEXT(100) DEFAULT NULL,DocumentName TEXT(30),ImagePreviewURL TEXT(150) DEFAULT NULL,ImagePreviewName TEXT(30),lastUpdate TEXT(10))");
    			stmt.executeUpdate("CREATE TABLE Tips_PT (id integer  PRIMARY KEY AUTOINCREMENT DEFAULT NULL,Title TEXT(45) DEFAULT NULL,SubTitle TEXT(60)  DEFAULT NULL,Description TEXT(370) DEFAULT NULL,Category TEXT(60)  DEFAULT NULL,DocumentType TEXT(4) DEFAULT NULL,DocumentURL TEXT(100) DEFAULT NULL,DocumentName TEXT(30),ImagePreviewURL TEXT(150) DEFAULT NULL,ImagePreviewName TEXT(30),lastUpdate TEXT(10))");

    			Collection<AppFileData> files = report.getAppFiles(sys.getUserSessionData());
    			Iterator<AppFileData> it = files.iterator();
    			Calendar c = Calendar.getInstance();
    			String date = Util.formatDate(c.getTime());
    			int id = files.size();
    			while(it.hasNext()) {
    				AppFileData file = it.next();
    				String url = SecurityConstants.APPCEPA_URL;
    				String documentUrl =  url + "files/"+ file.getLng().replaceAll("module.data.name.", "").toUpperCase() +"/" + file.getCategoryName().toLowerCase()+"/"+file.getDocumentname();
    				String imageUrl = url + "files/"+ file.getLng().replaceAll("module.data.name.", "").toUpperCase() +"/" + file.getCategoryName().toLowerCase()+"/"+file.getImagename();			
    				stmt.executeUpdate("INSERT INTO "+file.getCategoryName()+getLanguageName(file.getLng())+" VALUES (" +
    						id+","+
    						"'"+file.getTitle()+"',"+
    						"'"+file.getSubtitle()+"',"+
    						"'"+file.getDescription()+"',"+
    						"'"+file.getCategoryName()+"',"+
    						"'"+file.getDoctypeName()+"',"+
    						"'"+documentUrl+"',"+
    						"'"+file.getDocumentname()+"',"+
    						"'"+imageUrl+"',"+
    						"'"+file.getImagename()+"'," +
    						"'"+date+"')");
    				id--;
   						
    			}
    			
    		} finally {
    			try { 
    				stmt.close(); 
    			} catch (Exception ignore) {}
    		}
    	} finally {
    		try { 
    			conn.close(); 
    		} catch (Exception ignore) {}
    	}
    	
    	
    	   // Badge
        int BADGE = 1;
        try {
        	BADGE = Integer.parseInt(((DynaActionForm)form).getString("badge"));
        }catch (Exception e) {
		}

        if (!AlertConstants.isProductionServer()){
	       // iPhone's UDID (64-char device token)
	        Collection<String> iPhoneIds = report.getAppDevices(sys.getUserSessionData(),appId);
	        Iterator<String> it = iPhoneIds.iterator();
	        
	        while(it.hasNext()) {
	        	try {
	    		
			    	PushNotificationPayload payload = PushNotificationPayload.complex();
			
			        //payload.addAlert(updateLine);
			        payload.addBadge(BADGE);
			        payload.addSound("default");
			        payload.addCustomDictionary("id", "1");
			        payload.addAlert("NewVersion");
			
			
			        List<PushedNotification> NOTIFICATIONS = Push.payload(payload, certificate, passwd, true, it.next());
			        
	        	}catch (Exception e) {
	    			e.printStackTrace();
	    		}
			}
        }
	
        session.removeAttribute("UpdateAppFileDatabaseForm");
        sys.getUserSessionData().setUrl("/appfiles/files.jsp");
        return mapping.findForward("next");
 		
    }
    
    public ActionForward actionJJ(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
    	HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
         
        String updateLine = ((DynaActionForm)form).getString("updateLine");
    	String sDriverName = "org.sqlite.JDBC";
    	Class.forName(sDriverName);
     
    	// now we set up a set of fairly basic string variables to use in the body of the code proper
        String certificate = "";
        String passwd = "";
        String sTempDb = ""; 
        String appId = "";
        if(sys.isCorporation(CorporationData.JJ)) {
        	sTempDb = SecurityConstants.APPJJ_DIRECTORY+"db/CEPA_DB.db";
        	certificate = SecurityConstants.APPJJ_DIRECTORY+"PushCepa.p12";
        	passwd = "SafeFleetApp";
        	appId = "SafeFleet";
        }else {
        	sTempDb = SecurityConstants.APPCEPA_DIRECTORY+"db/CEPA_DB.db";
        	certificate = SecurityConstants.APPCEPA_DIRECTORY+"PushCepa.p12";
        	passwd = "CepaApp";
        	appId = "Cepa";
        }

    	File f = new File(sTempDb);
    	f.delete();
    	
    	String sJdbc = "jdbc:sqlite";
    	String sDbUrl = sJdbc + ":" + sTempDb;
    	// which will produce a legitimate Url for SqlLite JDBC :
    	// jdbc:sqlite:hello.db
    	int iTimeout = 30;
     
    	// create a database connection
    	Connection conn = DriverManager.getConnection(sDbUrl);
		CorporationReportFacade report = sys.getCorporationReportFacade();
    	try {
    		Statement stmt = conn.createStatement();
    		try {
    			stmt.setQueryTimeout(iTimeout);

    			stmt.executeUpdate("CREATE TABLE Categories (serial integer  PRIMARY KEY AUTOINCREMENT,categoryType TEXT(10) DEFAULT NULL)");
    			stmt.executeUpdate("INSERT INTO Categories VALUES(1,'Politics')");
    			stmt.executeUpdate("INSERT INTO Categories VALUES(2,'Tips')");
    			stmt.executeUpdate("INSERT INTO Categories VALUES(3,'News')");;

    			stmt.executeUpdate("CREATE TABLE Led (ledID integer  PRIMARY KEY AUTOINCREMENT DEFAULT NULL,Messages TEXT DEFAULT NULL)");
    			stmt.executeUpdate("INSERT INTO Led VALUES(1,'BEM-VINDO AO NOVO APLICATIVO DO SAFE FLEET. UM CANAL DE COMUNICAÇÃO COM NOTÍCIAS E INFORMAÇÕES PARA AJUDAR NA SUA SEGURANÇA NO TRÂNSITO.')");
    			
    			stmt.executeUpdate("CREATE TABLE News (id integer  PRIMARY KEY AUTOINCREMENT DEFAULT NULL,Title TEXT(45) DEFAULT NULL,SubTitle TEXT(60)  DEFAULT NULL,Description TEXT(370) DEFAULT NULL,Category TEXT(60)  DEFAULT NULL,DocumentType TEXT(4) DEFAULT NULL,DocumentURL TEXT(100) DEFAULT NULL,DocumentName TEXT(30),ImagePreviewURL TEXT(150) DEFAULT NULL,ImagePreviewName TEXT(30),lastUpdate TEXT(10) DEFAULT NULL)");   			
    			stmt.executeUpdate("CREATE TABLE Politics (id integer  PRIMARY KEY AUTOINCREMENT DEFAULT NULL,Title TEXT(45) DEFAULT NULL,SubTitle TEXT(60)  DEFAULT NULL,Description TEXT(370) DEFAULT NULL,Category TEXT(60)  DEFAULT NULL,DocumentType TEXT(4) DEFAULT NULL,DocumentURL TEXT(100) DEFAULT NULL,DocumentName TEXT(30),ImagePreviewURL TEXT(150) DEFAULT NULL,ImagePreviewName TEXT(30),lastUpdate TEXT(10))");
    			stmt.executeUpdate("CREATE TABLE Tips (id integer  PRIMARY KEY AUTOINCREMENT DEFAULT NULL,Title TEXT(45) DEFAULT NULL,SubTitle TEXT(60)  DEFAULT NULL,Description TEXT(370) DEFAULT NULL,Category TEXT(60)  DEFAULT NULL,DocumentType TEXT(4) DEFAULT NULL,DocumentURL TEXT(100) DEFAULT NULL,DocumentName TEXT(30),ImagePreviewURL TEXT(150) DEFAULT NULL,ImagePreviewName TEXT(30),lastUpdate TEXT(10))");

    			Collection<AppFileData> files = report.getAppFiles(sys.getUserSessionData());
    			Iterator<AppFileData> it = files.iterator();
    			Calendar c = Calendar.getInstance();
    			String date = Util.formatDate(c.getTime());
    			int id = files.size();
    			while(it.hasNext()) {
    				AppFileData file = it.next();
    				String url = (sys.isCorporation(CorporationData.JJ)) ? SecurityConstants.APPJJ_URL : SecurityConstants.APPCEPA_URL;
    				String documentUrl =  url + "files/" + file.getCategoryName().toLowerCase() + "/" + file.getDocumentname();			
    				String imageUrl = url + "files/" + file.getCategoryName().toLowerCase() + "/" + file.getImagename();			
    				
    				stmt.executeUpdate("INSERT INTO "+file.getCategoryName()+" VALUES (" +
    						id+","+
    						"'"+file.getTitle()+"',"+
    						"'"+file.getSubtitle()+"',"+
    						"'"+file.getDescription()+"',"+
    						"'"+file.getCategoryName()+"',"+
    						"'"+file.getDoctypeName()+"',"+
    						"'"+documentUrl+"',"+
    						"'"+file.getDocumentname()+"',"+
    						"'"+imageUrl+"',"+
    						"'"+file.getImagename()+"'," +
    						"'"+date+"')");
    				id--;
   						
    			}
    			
    		} finally {
    			try { 
    				stmt.close(); 
    			} catch (Exception ignore) {}
    		}
    	} finally {
    		try { 
    			conn.close(); 
    		} catch (Exception ignore) {}
    	}
    	
    	
    	   // Badge
        int BADGE = Integer.parseInt(((DynaActionForm)form).getString("badge"));

        if (!AlertConstants.isProductionServer()){
	       // iPhone's UDID (64-char device token)
	        Collection<String> iPhoneIds = report.getAppDevices(sys.getUserSessionData(),appId);
	        Iterator<String> it = iPhoneIds.iterator();
	        
	        while(it.hasNext()) {
	        	try {
	        		String device = it.next();
	    		
	        		if(device != null) {
				    	PushNotificationPayload payload = PushNotificationPayload.complex();
				
				        payload.addAlert(updateLine);
				        payload.addBadge(BADGE);
				        payload.addSound("default");
				        payload.addCustomDictionary("id", "1");
				
				
				        List<PushedNotification> NOTIFICATIONS = Push.payload(payload, certificate, passwd, true, device);
	        		}			        
	        	}catch (Exception e) {
	    			e.printStackTrace();
	    		}
			}
        }
	
    	
/*		
    	PushNotificationPayload payload = PushNotificationPayload.complex();

        payload.addAlert(updateLine);
        payload.addBadge(BADGE);
        payload.addSound("default");
        payload.addCustomDictionary("id", "1");
*/

//        List<PushedNotification> NOTIFICATIONS = Push.payload(payload, certificate, passwd, true, "5e2b5232ccc05d88c1120b7942e5b88922d5183d271bcd48a388a8fb5a9e611b");

        session.removeAttribute("UpdateAppFileDatabaseForm");
        sys.getUserSessionData().setUrl("/appfiles/files.jsp");
        return mapping.findForward("next");
 		
    }

	
	public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
 		sys.getUserSessionData().setUrl("/appfiles/files.jsp");
        return mapping.findForward("next");
    }

	private String getLanguageName(String keyLanguage){
		if(LanguageConstants.ES.equalsIgnoreCase(keyLanguage)){
			return "_ES";
		}
		if(LanguageConstants.EN.equalsIgnoreCase(keyLanguage)){
			return "_IN";
		}
		if(LanguageConstants.PT.equalsIgnoreCase(keyLanguage)){
			return "_PT";
		}
		return "undefined";
	}
}
