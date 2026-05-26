package presentation.translator.web.fleet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;

import communication.administration.user.UserSessionData;
import communication.language.Text;
import communication.security.SecurityConstants;
import communication.util.ListResult;

public class UploadOdometerAction extends AbstractAction {


	static final int EOF = 65535;
	private String errorMessage = null;
	private SystemAccess sys;
	
	/**
	 * 
	 */	
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		HttpSession session = request.getSession();
        sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
        
        String directory = SecurityConstants.IMAGES_WAR_DIRECTORY;
    	String fileName = "odometers"+sys.getUserSessionData().getFilter().getCorporationId()+".csv";			   			
		    
		try {
		
			UploadOdometerForm uploadForm = (UploadOdometerForm) form;
			FormFile file = (FormFile)uploadForm.getFile();		
			
			// Valido archivo de CepaTips vacio o nulo
			if (file == null || file.getFileName() == null || file.getFileName().equals("")){
				session.setAttribute("Msg", messages.getText("generic.supportmaterial.cepatips.nofile"));
				sys.getUserSessionData().setUrl("fleet/uploadOdometers.jsp");
		  	    return mapping.findForward("next");	
			}
			
									
			InputStream reportStream = file.getInputStream();
			FileOutputStream fout;
			
			fout = new FileOutputStream(directory+"/"+fileName);
			
			int c = 0;
			while ( (c=reportStream.read()) != -1){
				fout.write(c);
			}			
			fout.flush();
			fout.close();				
			
	        StringBuffer word = new StringBuffer();

	        int lineasExplicativas = 0;
            char car = 0;
        	FileInputStream input = new FileInputStream(new File(directory + "/" + fileName));
        	Reader in = new InputStreamReader(input, "ISO-8859-1"); 

        	while (car != EOF && lineasExplicativas < 1){
            	  while(car != '\n' && car != EOF) {
                      // car = (char)input.read();                   
            		  car = (char)in.read(); // para compatibilidad con utf8
            	  }
            	  car = 0;
                  lineasExplicativas ++;
            }

            Hashtable values = new Hashtable();
            // Ingreso los diferentes eventos
        	int index = 0;
        	String vehicle = "";
            while(car != EOF) {
            	// Leo la linea:
                while(car != '\n' && car != EOF) {
                	errorMessage = null;
                	car = (char)in.read();
                    if(car == '\t' || car == '\n') {
                    	switch(index) {
                    		case 0:
                    			vehicle = word.toString().trim();
                    			break;
                    		case 1:
                        		String newOdometer = word.toString().trim();
                        		values.put(vehicle, newOdometer);
                            	index = -1;
                            	break;
                        	default:
                				session.setAttribute("Msg", messages.getText("uploadodometers.error.columncount"));
                				sys.getUserSessionData().setUrl("fleet/uploadOdometers.jsp");
                		  	    return mapping.findForward("next");	
                    	}
                    
                    	word = new StringBuffer();	                        
                        index++;
                    }else {
                        word.append(car);
                    }
                }
                if(car != EOF) {
                	car = '\t';
                }
            }

            session.setAttribute("odometerData", values);
        	session.setAttribute("period",uploadForm.getPeriod());
            session.setAttribute("year",uploadForm.getYear());
        	session.setAttribute("fleet",uploadForm.getFleet());
    		session.removeAttribute("OdometerUploadForm");

    		sys.getUserSessionData().setUrl("fleet/uploadOdometerReview.jsp");
        	
            return mapping.findForward("next");
	    }
		catch (IOException io){
			session.setAttribute("Msg", messages.getText("generic.supportmaterial.cepatips.badpermissions"));
	    	io.printStackTrace();
	  	    return mapping.findForward("error");	
		}
	    catch(Exception e) {
	    	session.setAttribute("Msg", messages.getText("generic.supportmaterial.cepatips.error"));
	    	e.printStackTrace();
	  	    return mapping.findForward("error");	
	    }
       
	}
	

	@Override
	public ActionForward cancel(HttpSession session, ActionMapping mapping,
			ActionForm form) {
		// TODO Auto-generated method stub
        sys = ((SystemAccess)session.getAttribute("SystemAccess"));
		sys.getUserSessionData().setUrl("fleet/uploadOdometers.jsp");
  	    return mapping.findForward("next");	
	}
}

