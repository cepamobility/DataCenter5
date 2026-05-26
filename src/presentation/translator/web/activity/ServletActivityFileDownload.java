package presentation.translator.web.activity;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.SystemAccess;
import communication.activity.tables.CEPAActivityScheduleData;
import communication.alert.AlertConstants;

public class ServletActivityFileDownload extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    protected void doGet(HttpServletRequest req, HttpServletResponse response)	throws ServletException, IOException{
    	try{
	    		
	    	
	    	String idActivity=req.getParameter("idActivity");
	    	SystemAccess sys = ((SystemAccess)req.getSession().getAttribute("SystemAccess"));
	    	
	    	CEPAActivityScheduleData activity=sys.getActivityReportFacade().findCEPAActivityScheduleByPrimaryKey(Integer.valueOf(idActivity), sys.getUserSessionData());
	    	
	    	if(activity.getFile()!=null && activity.getFile().trim().length()>0){
	        	File file=new File(activity.getFile());
	        	
	        	response.setHeader("Content-Type", getServletContext().getMimeType(file.getName()));
	        	response.setHeader("Content-Length", String.valueOf(file.length()));
	        	response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
	
	        	BufferedInputStream input = null;
	        	BufferedOutputStream output = null;
	
	        	try {
	        	    input = new BufferedInputStream(new FileInputStream(file));
	        	    output = new BufferedOutputStream(response.getOutputStream());
	        	    byte[] buffer = new byte[8192];
	        	    for (int length = 0; (length = input.read(buffer)) > 0;) {
	        	        output.write(buffer, 0, length);
	        	    }
	        	} finally {
	        	    if (output != null) try { output.close(); } catch (IOException logOrIgnore) {}
	        	    if (input != null) try { input.close(); } catch (IOException logOrIgnore) {}
	        	}
	    	}
    	}catch(Throwable th){
    		th.printStackTrace();
    	}
    }

}
