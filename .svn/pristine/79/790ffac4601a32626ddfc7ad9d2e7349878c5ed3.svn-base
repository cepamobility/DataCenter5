package presentation.translator.web.driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Time;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.jfree.data.time.Hour;

import presentation.translator.web.event.UploadFineForm;
import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;
import business.event.EventABMFacade;

import communication.administration.corporation.tables.CorporationDirectoryData;
import communication.datatype.tables.FineQualifyDataType;
import communication.datatype.tables.FineTypeDataType;
import communication.driver.tables.DriverData;
import communication.driver.tables.TelematicControlData;
import communication.event.tables.FineDataType;
import communication.language.Text;
import communication.security.SecurityConstants;
import communication.util.ListResult;
import communication.vehicle.tables.VehicleData;

public class UploadTelematicControlAction extends AbstractAction {


	static final int EOF = 65535;
	private String errorMessage = null;
	private SystemAccess sys;
	private Text messages;
	
	/**
	 * 
	 */	
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		HttpSession session = request.getSession();
        sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        messages = sys.getText();
        
		try {
		
			UploadFineForm uploadForm = (UploadFineForm) form;
			FormFile file = (FormFile)uploadForm.getFile();		
			
			// Valido archivo de CepaTips vacio o nulo
			if (file == null || file.getFileName() == null || file.getFileName().equals("")){
				session.setAttribute("Msg", messages.getText("generic.supportmaterial.cepatips.nofile"));
				sys.getUserSessionData().setUrl("driver/UploadTelematicControls.jsp");
		  	    return mapping.findForward("next");	
			}
			
			// Valido Nombre de Archivo
			if (!this.validarString(file.getFileName())){
				session.setAttribute("Msg",  messages.getText("generic.supportmaterial.cepatips.badfile"));
				sys.getUserSessionData().setUrl("driver/UploadTelematicControls.jsp");
		  	    return mapping.findForward("next");	
			}
			// Fin Valido Nombre de Archivo
									
	    	CorporationDirectoryData cdd = sys.getCorporationReportFacade().findCorporationDirectoryByPrimaryKey(sys.getCorporationId(),sys.getUserSessionData());
			// Filtro por Corporacion:
	    	String directory = SecurityConstants.DATACENTER5_WAR_DIRECTORY;
			directory += (cdd == null || Util.empty(cdd.getDirectory())) ?  "cepa" : cdd.getDirectory();

			int type = Integer.parseInt(uploadForm.getNotes());
	        String fileName =  (type == TelematicControlData.TYPE_EXCESSIVE_SPEED) ? "velocidade" : "noturna";
	        fileName += "_"+System.currentTimeMillis()+".csv";

	        InputStream reportStream = file.getInputStream();
			FileOutputStream fout;
			
			fout = new FileOutputStream(directory+"/"+fileName);
			
			int c = 0;
			while ( (c=reportStream.read()) != -1){
				fout.write(c);
			}			
			fout.flush();
			fout.close();				
			
			return insertData(mapping,uploadForm,request,response,directory+"/"+fileName,type);

	    }
		catch (IOException io){
	    	io.printStackTrace();
			session.setAttribute("Msg", messages.getText("generic.supportmaterial.cepatips.badpermissions"));
			sys.getUserSessionData().setUrl("driver/UploadTelematicControls.jsp");
	  	    return mapping.findForward("next");	
		}
	    catch(Exception e) {
	    	e.printStackTrace();
	    	session.setAttribute("Msg", messages.getText("generic.supportmaterial.cepatips.error"));
			sys.getUserSessionData().setUrl("driver/UploadTelematicControls.jsp");
	  	    return mapping.findForward("next");	
	    }       
	}
	
	/**
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
	 * @param type 
     * @return
     * @throws Throwable
     */
    public ActionForward insertData(ActionMapping mapping, UploadFineForm form, HttpServletRequest request, HttpServletResponse response, String fileName, int type) throws Exception {
    	
    	HttpSession session = request.getSession();
        		
        int lineNumber = 1;
        int total = 0;
//        ListResult result = null;
        StringBuffer word = new StringBuffer();
        
        try {
        	Collection<TelematicControlData> controls = new LinkedList<TelematicControlData>();
        	
        	FileInputStream input = new FileInputStream(new File(fileName));
        	Reader in = new InputStreamReader(input, "ISO-8859-1");

            char car = 0;
            int lineasExplicativas = 0;
            while (car != EOF && lineasExplicativas < 1){
            	while(car != '\n' && car != EOF) {
            		car = (char)in.read(); // para compatibilidad con utf8
            	 }
            	 car = 0;
                 lineasExplicativas ++;
                 lineNumber ++;
            }
            
            int columns = (type == TelematicControlData.TYPE_EXCESSIVE_SPEED) ? 7: 8;
           
            // Ingreso las diferentes multas
            while(car != EOF) {
            	int index = 0;
                car = 0;
                
                String firstName = "", lastName = "";
                String plate = "";                       
                            
                TelematicControlData tc = new TelematicControlData();
                tc.setType(type);
                tc.setRecomendation(TelematicControlData.RECOMENDATION_EMPTY);
                Calendar c = Calendar.getInstance();
                // Leo la linea:
                while((car != '\n' || index < columns) && car != EOF) {
                	errorMessage = null;
                	car = (char)in.read();
                    if(car == '\t' || (car == '\n' && index == columns)) {
                    	String data = word.toString().trim();
                        switch(index) {

                        	// Datos del Conductor
                            case 0: 
                            	firstName = eventData(data,messages,"driver.messages.upload.wrongname","NOMBRE CONDUCTOR "+data); 
                            	break;
                            case 1: 
                            	lastName = eventData(data,messages,"driver.messages.upload.wronglastname","APELLIDO CONDUCTOR "+data); 
                                if (! Util.empty(firstName) && ! Util.empty(lastName)){
                	                DriverData driver = getDriver(firstName, lastName, null, messages);
                	                if (driver == null){
                	                	errorMessage += "\\n"+messages.getText("driver.messages.upload.linenumber") +": "+lineNumber+" - "+firstName+" "+lastName;
                	                	session.setAttribute("Msg",errorMessage); 
                	        			sys.getUserSessionData().setUrl("driver/UploadTelematicControls.jsp");
                	              	    return mapping.findForward("next");	
                	                }                
                                    tc.setDriver(driver);
                                }   
                	            break;
                            case 2: 
                            	tc.setGarage(plateData(data,messages));
                            	break;
                            case 3:
                            	Date date = eventDateData(data,messages);
                            	if(date == null) {
            	                	errorMessage += "\\n"+messages.getText("driver.messages.upload.linenumber") +": "+lineNumber;
            	                	session.setAttribute("Msg",errorMessage); 
            	        			sys.getUserSessionData().setUrl("driver/UploadTelematicControls.jsp");
            	              	    return mapping.findForward("next");	
                            	}
                            	tc.setDate(date);
                            	break;
                            case 4: 
                        		c = eventHourData(data,messages,c);
                            	if(c == null) {
            	                	errorMessage += "\\n"+messages.getText("driver.messages.upload.linenumber") +": "+lineNumber;
            	                	session.setAttribute("Msg",errorMessage); 
            	        			sys.getUserSessionData().setUrl("driver/UploadTelematicControls.jsp");
            	              	    return mapping.findForward("next");	
                            	}
                            	tc.setTime(data);
                        		break;
                            case 5: 
                            	tc.setObservations(eventData(data,messages,null,null));
                            	break;
                            case 6: 
                            	tc.setSpeed(speedData(data,messages));
                            	break;
                            case 7:  
                            	tc.setStatusComment(eventData(data,messages,null,null));
                            	break;
                            case 8:
                            	if(type == TelematicControlData.TYPE_NIGTH_DRIVE)
                            		tc.setDuration(eventData(data,messages,null,null));
                            	lineNumber++;
                            	break;
                            
                        }
                        word = new StringBuffer();	                        
                        index++;
                    }else {
                        word.append(car);
                    }
                    if (errorMessage != null){
                    	break;
                    }
                } // Termine de leer toda la linea
                if(tc.getDate() != null)
                	controls.add(tc);
            }
            
            // Inserto todas las multas que correspondan
            if (controls != null && controls.size() > 0){
            	sys.getDriverABMFacade().addTelematicControls(controls, sys.getUserSessionData());
                total = controls.size();
            }
                 
        }catch(Exception e){
        	e.printStackTrace();
        }
    	
		sys.getUserSessionData().setUrl("driver/UploadTelematicControlSuccess.jsp?total="+total);
  	    return mapping.findForward("next");	
    }
	
    
    /**
     * Ubicacion del Evento 1
     * @param data
     * @param messages
     * @return
     */
    public String eventData(String data,Text messages,String message, String logMessage){
    	try{
    		return (!Util.empty(data)) ? data : null;                            			  
      	} catch (Exception e) {
      		if(logMessage != null) {
      			System.out.println(logMessage);
      		}
      		if(message != null) {
      			errorMessage = message + ". ";
      		}
      		e.printStackTrace();
      	}
  		return null;
    }  
    
    /**
     * Matricula del Vehiculo, CAMPO OBLIGATORIO
     * @param data
     * @param messages
     * @return
     */
    public String plateData(String data,Text messages){
    	try{
		  if (!Util.empty(data)){                            			  
			  return data;
			  
		  }else{
    		  errorMessage = messages.getText("vehicle.messages.upload.wrongplate") +". ";
    		  return null;
		  }
      } catch (Exception e) {
          System.out.println("Sin Matricula. ");
          errorMessage = messages.getText("vehicle.messages.upload.wrongplate")+". ";
          return null;
      }
  
    }
    
    /**
     * Fecha del Evento, CAMPO OBLIGATORIO
     * @param data
     * @param messages
     * @return
     */
    public Date eventDateData(String data, Text messages){
    	try{
    		if (!Util.empty(data)){                            			  
    			return Util.getDate(data).getTime();			  
    		}else{
    			errorMessage = messages.getText("generic.event.eventdata.dateEmpty") +". ";
    			return null;
    		}
    	} catch (Exception e) {
    		System.out.println("Sin Fecha de Evento o Fecha Invalida. ");
    		errorMessage = messages.getText("generic.event.eventdata.dateInvalid")+". ";
    		return null;
    	}
    }
    
    /**
     * Fecha del Evento, CAMPO OBLIGATORIO
     * @param data
     * @param messages
     * @return
     */
    public Date loteData(String data, Text messages){
    	try{
        	if (data != null && data.trim() != ""){
    	        StringTokenizer tokDate = new StringTokenizer(data,"/");
    	        Calendar c = Calendar.getInstance();
    	        tokDate.nextToken();
    	        c.set(Calendar.MONTH,Integer.parseInt(tokDate.nextToken())-1);
    	        c.set(Calendar.YEAR,Integer.parseInt(tokDate.nextToken()));
    	        
    	        c.set(Calendar.DAY_OF_MONTH,1);
    	        c.set(Calendar.HOUR, 0);
    	        c.set(Calendar.MINUTE, 0);
    	        c.set(Calendar.SECOND,0);
    	        c.set(Calendar.MILLISECOND,0);
    	        
    	        return c.getTime();
        	}
    	} catch (Exception e) {
    		errorMessage = messages.getText("generic.event.lotedata.dateInvalid")+". ";
    	}
		return null;
    }

    /**
     * Hora del Evento, CAMPO OBLIGATORIO
     * @param data
     * @param messages
     * @return
     */
    public Calendar eventHourData(String data, Text messages, Calendar c){
    	try {
    		if(!Util.empty(data) && data.length()==4 ){
                // por si viene una hora 8:30 sin el cero delante
                data = "0".concat(data);
    		}
    		if (!Util.empty(data) && data.length() >= 5){    
    			c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(data.substring(0,2))); //hh:mm
    			c.set(Calendar.MINUTE, Integer.parseInt(data.substring(3,5)));
    			return c;
    		}else{
    			errorMessage = messages.getText("generic.event.eventdata.timeEmpty");
				return null;
    		}
        }catch(Exception e) {
        	System.out.println("Sin Hora del Evento o Hora Invalida. ");
    		errorMessage = messages.getText("generic.event.eventdata.hourInvalid")+". ";
    		return null;
        }
    }
       
	/**
	 * costo estimado de la multa
	 * @param data
	 * @param messages
	 * @return
	 */
	private Integer speedData(String data, Text messages) {
		try{
			if (Util.isNumber(data)){
				return new Integer(data);
			}else{
				errorMessage = messages.getText("generic.telematiccontrol.speedempty")+". ";
				return null;
			}
		}catch(Exception e){
			errorMessage = messages.getText("generic.telematiccontrol.speedinvalid")+". ";
			return null;
		}
	}

	
	/**
	 * Devuelve false si tiene caracteres que no son digito, letra,
	 * espacio en blanco o punto o subrayado o guion.
	 * @param s : String a validar
	 * @return boolean
	 */
	private boolean validarString(String s){
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
	
	/**
	 * Convierte caracteres raros a letras comunes.
	 * @param s
	 * @return
	 */
	@SuppressWarnings("unused")
	private String convertString(String s){		
		StringBuffer aux = new StringBuffer();
    	int count = 0;
    	aux.append(s.substring(0,0).toUpperCase());
    	while (count < s.length())
    	{
    		// Por m�s codigos referise a:
    		// http://webdesign.about.com/library/bl_htmlcodes.htm
    		switch (s.charAt(count)){
    			case (char)225: aux.append("a"); //'�' 
    				break;
    			case (char)233: aux.append("e"); //'�' 
    				break;
    			case (char)237: aux.append("i"); //'�'
    				break;
    			case (char)243: aux.append("o"); // '�'
    				break;
    			case (char)250: aux.append("u"); //'�'
    				break;    			
    			case (char)231: aux.append("c"); // '�'
    				break;
    			case (char)228: aux.append("a"); // '�'
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
    			case (char)193: aux.append("A"); //�
    				break;
    			case (char)201: aux.append("E"); //�
    			    break;
    			case (char)205: aux.append("I"); //�
    			    break;
    			case (char)211: aux.append("O"); //�
    				break;
    			case (char)218: aux.append("U"); //�
    				break;
    			case (char)199: aux.append("C"); //�
    				break;
    			case (char)195: aux.append("A"); //�
    				break;
    			default: aux.append(String.valueOf(s.charAt(count)));
    				break;
   	    		// Por m�s codigos referise a:
   	    		// http://webdesign.about.com/library/bl_htmlcodes.htm
    		}
    		count ++;
    	}
    	return aux.toString().replace(" ","_");
		
	}
	
    /**
     * Obtiene el driverData a partir del nombre, apellido y fecha de naciemiento
     * Si existen mas de 1 driver retorna null (seteando el error)
     * Si existe 0 driver retorna null.(seteando el error) 
     * @param firstName
     * @param lastName
     * @param birthdate
     * @param messages
     */
    private DriverData getDriver(String firstName, String lastName, Date birthdate, Text messages){
    	try{
	    	// Si hay mas de un driver es error, tiene que ingresar,
	        // el evento a mano para coincidir bien el driver.
	        ListResult drivers = sys.getDriverReportFacade().findByMainData(firstName,lastName,birthdate,sys.getUserSessionData());
	        
	        
	        // No se encontro el driver, error
	        if (drivers == null || drivers.getTotal() <= 0){
	        	errorMessage = messages.getText("driver.notfound");
	        	return null;
	        }
	        
	        // Si hay mas de un driver con ese nombre, apellido y fecha de nacimeinto en la 
	        // corproacion no se puede agregar el evento:
	        if (drivers.getTotal() > 1){
	        	errorMessage = messages.getText("driver.repeatdata.canaddevent");
	        	return null;
	        }        
	        
	        
	        return (DriverData) drivers.getElements().iterator().next();
	        
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return null;
    	
    }

    /**
     * Obtiene el vehiculo a partir de la matricula.
     * Si existen mas de 1 vehiculo retorna null (seteando el error)
     * Si existe 0 vehiculo retorna null.(seteando el error)
     * 
     * @param plate
     * @param messages
     * @return
     */
    private VehicleData getVehicle(String plate, Text messages){
    	try{
    		ListResult vehicles = sys.getVehicleReportFacade().findVehiclesByPlate(plate,sys.getUserSessionData());

    		// Si hay mas de un vehiculo con esa matricula en la 
            // corproacion no se puede agregar el evento:
            if (vehicles != null && vehicles.getTotal() > 1){
            	// errorMessage = messages.getText("vehicle.repeatdata.canaddevent");
            	return null;
            }
            
            // No se encontro el vehiculo, error
            if (vehicles == null || vehicles.getTotal() <= 0){
            	// errorMessage = messages.getText("vehicle.notfound");
            	return null;
            }
            
            return (VehicleData) vehicles.getElements().iterator().next();
    	}catch(Exception e){
    		System.out.println("UploadEventAction: Error al traer las zonas: getVehicle() ");
    		e.printStackTrace();
    	}
    	return null;
    }
    
    

    
    /**
     * Devuelve true si el string que se pasa por parametro indicando un check
     * es igual a "on", y false en caso contrario.
     *  
     * @param label
     * @return
     */
    public boolean isChecked(String label) {
        if (!Util.empty(label) && label.toLowerCase().equals("on"))
        	return true;
        else 
        	return false;
    }

	@Override
	public ActionForward cancel(HttpSession session, ActionMapping mapping,
			ActionForm form) {
		// TODO Auto-generated method stub
		return null;
	}
}

