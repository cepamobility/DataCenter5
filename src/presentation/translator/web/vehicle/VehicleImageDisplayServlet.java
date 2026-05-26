package presentation.translator.web.vehicle;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import communication.alert.AlertConstants;

public class VehicleImageDisplayServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    protected void doGet(HttpServletRequest req, HttpServletResponse response)	throws ServletException, IOException{
    	
    	String fileImage=req.getParameter("file");
    	File file=new File(AlertConstants.TEMP_DIRECTORY_IMAGE_DPA+fileImage);
    	String corp=req.getParameter("corp");
    	if(corp!=null){
    		file=new File(AlertConstants.getTEMPImage(Integer.valueOf(corp))+fileImage);
    		
    	}
    	
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

}
