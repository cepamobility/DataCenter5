package presentation.translator.web.driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Collection;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;
import business.driver.DriverReportFacade;

import communication.administration.corporation.tables.PointsDefinitionData;
import communication.driver.tables.DriverData;
import communication.language.Text;
import communication.security.SecurityConstants;

public class WorkAccidentUploadAction extends AbstractAction {

	static final int EOF = 65535;
	
	/**
	 * 
	 */	
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{

				
		HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
        String directory = SecurityConstants.IMAGES_WAR_DIRECTORY;
        String fileName = "accidentes_laborales.csv";
    
		try {
			DriverUploadForm uploadForm = (DriverUploadForm) form;
			FormFile file = (FormFile)uploadForm.getFile();		
					
			// Valido archivo de vacio o nulo
			if (file == null || file.getFileName() == null || file.getFileName().equals("")){
				session.setAttribute("Msg", "Archivo vacío");
				sys.getUserSessionData().setUrl("/driver/insertWA.jsp");
		  	    return mapping.findForward("next");	
			}
			
			// Valido Nombre de Archivo
			if (!validarString(file.getFileName())){
				session.setAttribute("Msg", "Error en nombre de archivo");
				sys.getUserSessionData().setUrl("/driver/insertWA.jsp");
		  	    return mapping.findForward("next");	
			}
			// Fin Valido Nombre de Archivo
									
			InputStream reportStream = file.getInputStream();		
			FileOutputStream fout = new FileOutputStream(directory + fileName);
			
			int c = 0;
			while ( (c=reportStream.read()) != -1){
				fout.write(c);
			}			
			fout.flush();
			fout.close();				
			
			sys.getUserSessionData().setValue(insertWA(mapping,form,request,response));			
			sys.getUserSessionData().setUrl("/driver/confirmUploadWA.jsp");
			return mapping.findForward("next");	
	    } catch (Exception io){
			session.setAttribute("Msg", messages.getText("generic.supportmaterial.cepatips.badpermissions"));
	    	io.printStackTrace();
	  	    return mapping.findForward("next");	
		}  
	}
	
    public Hashtable<Integer,Object[]> insertWA(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	 
    	Hashtable<String, DriverData> drivers = new Hashtable<String, DriverData>();
    	Hashtable<Integer,Object[]> values = new Hashtable<Integer,Object[]> ();
    	HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        int lineNumber = 0;

        try {
        	DriverReportFacade driverReport = sys.getDriverReportFacade();
        	  
            FileInputStream input = new FileInputStream(new File(SecurityConstants.IMAGES_WAR_DIRECTORY+"accidentes_laborales.csv"));
            char car = 0;

            while (car != EOF && lineNumber < 1){
            	while(car != '\n' && car != EOF) {
            		car = (char)input.read();                   
            	}
            	car = 0;
                lineNumber ++;
            }
              
            StringBuffer word = new StringBuffer();
            while(car != EOF) {
            	int index = 0;
                car = 0;
                String[] s = new String[5];
                while(car != '\n' && car != EOF) {
                	car = (char)input.read();
                    if(car == '\t' || car == '\n') {
                    	s[index] = word.toString().trim();
                    	word = new StringBuffer();
                    	index++;
                    }else {
                    	word.append(car);
                    }
                }
                if(s[1] != null) {
	            	Object[] data = {new Integer(-1),"",s[1],s[0],null,Util.getDate(s[2]), "", s[4]};
	            	if(s[3].equalsIgnoreCase("CON BAJA")) {
	            		data[4] = PointsDefinitionData.ACCIDENTE_LABORAL_CON_BAJA;
	            		data[6] = "Con Baja";
	            	}
	            	if(s[3].equalsIgnoreCase("SIN BAJA")) {
	            		data[4] = PointsDefinitionData.ACCIDENTE_LABORAL_SIN_BAJA;
	            		data[6] = "Sin Baja";
	            	}
	            	if(s[3].equalsIgnoreCase("NO CONFORME")) {
	            		data[4] = PointsDefinitionData.ACCIDENTE_LABORAL_NO_CONFORME;
	            		data[6] = "No Conforme";
	            	}
	            	if(drivers.containsKey(s[1])) {
	                	DriverData driver = drivers.get(s[1]);
	                	data[0] = driver.getId();
	                	data[1] = driver.getFirstName()+" "+driver.getLastName();	            		
	            	}else {
		                Collection c = driverReport.findDriverByCorporationId(s[1], sys.getUserSessionData());
		                if(c.size() > 0) {
		                	DriverData driver = (DriverData)c.iterator().next();
		                	data[0] = driver.getId();
		                	data[1] = driver.getFirstName()+" "+driver.getLastName();
		                	drivers.put(s[1], driver);
		                }
	            	}
	                values.put(lineNumber, data);
	                lineNumber++;
                }
            }            
            input.close();
              
          } catch (Exception e) {
              e.printStackTrace();
          }
          return values;
    }
    
    


	public ActionForward cancel(HttpSession session, ActionMapping mapping, ActionForm form) {		
		return null;
	}
	
	
	/**
	 * Devuelve false si tiene caracteres que no son digito, letra,
	 * espacio en blanco o punto o subrayado o guion.
	 * @param s : String a validar
	 * @return boolean
	 */
	public boolean validarString(String s){
		int count = 0;
		char c [] = s.toCharArray();
		while (count < s.length()){
			if (!Character.isLetterOrDigit(c[count]) && 
				c[count] != ' ' && c[count] != '.' 
				&& c[count] != '_' && c[count] != '-' ){			
				return false;
			}
			count ++;
		}
		return true;
	}
	
	public String convertString(String s){		
		StringBuffer aux = new StringBuffer();
    	int count = 1;
    	aux.append(s.substring(0,0).toUpperCase());
    	while (count < s.length())
    	{
    		// Por mÃ¡s codigos referise a:
    		// http://webdesign.about.com/library/bl_htmlcodes.htm
    		switch (s.charAt(count)){
    			case (char)225: aux.append("a"); //'Ã¡' 
    				break;
    			case (char)233: aux.append("e"); //'Ã©' 
    				break;
    			case (char)237: aux.append("i"); //'Ã­'
    				break;
    			case (char)243: aux.append("o"); // 'Ã³'
    				break;
    			case (char)250: aux.append("u"); //'Ãº'
    				break;    			
    			case (char)231: aux.append("c"); // 'Ã§'
    				break;
    			case (char)228: aux.append("a"); // 'Ã£'
    				break;
    			case (char)227: aux.append("a");
    				break;
    			case (char)226: aux.append("a");
    				break;
    			case (char)229: aux.append("a");
    				break;
    			case (char)196: aux.append("A");
    				break;
    			case (char)230: aux.append("a");
    				break;
    			// Masyusculas
    			case (char)193: aux.append("A"); //Ã�
    				break;
    			case (char)201: aux.append("E"); //Ã‰
    			    break;
    			case (char)205: aux.append("I"); //Ã�
    			    break;
    			case (char)211: aux.append("O"); //Ã“
    				break;
    			case (char)218: aux.append("U"); //Ãš
    				break;
    			case (char)199: aux.append("C"); //Ã‡
    				break;
    			case (char)195: aux.append("A"); //Ãƒ
    				break;
    			default: aux.append(String.valueOf(s.charAt(count)));
    				break;
   	    		// Por mÃ¡s codigos referise a:
   	    		// http://webdesign.about.com/library/bl_htmlcodes.htm
    		}
    		count ++;
    	}
    	return aux.toString().replace(" ","_");
		
	}
	
}

