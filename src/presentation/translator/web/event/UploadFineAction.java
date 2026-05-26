package presentation.translator.web.event;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.rmi.RemoteException;
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

import presentation.translator.web.util.AbstractAction;
import presentation.translator.web.util.Util;
import business.SystemAccess;
import business.datatype.DataTypeReportFacade;
import business.event.EventABMFacade;

import communication.administration.corporation.tables.CorporationData;
import communication.datatype.DataTypeConstants;
import communication.datatype.tables.FineQualifyDataType;
import communication.datatype.tables.FineTypeDataType;
import communication.datatype.tables.GeneralDTData;
import communication.driver.tables.DriverData;
import communication.event.tables.FineDataType;
import communication.language.Text;
import communication.security.SecurityConstants;
import communication.util.ListResult;
import communication.vehicle.tables.VehicleData;

public class UploadFineAction extends AbstractAction {


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
        
        // String directory = "/tmp/datacenter/images";
        String directory = SecurityConstants.IMAGES_WAR_DIRECTORY;
    	String fileName = "fines"+sys.getUserSessionData().getFilter().getCorporationId()+".csv";			   			
		    
		try {
		
			UploadFineForm uploadForm = (UploadFineForm) form;
			FormFile file = (FormFile)uploadForm.getFile();		
			
			// Valido archivo de CepaTips vacio o nulo
			if (file == null || file.getFileName() == null || file.getFileName().equals("")){
				session.setAttribute("Msg", messages.getText("generic.supportmaterial.cepatips.nofile"));
				sys.getUserSessionData().setUrl("events/UploadFines.jsp");
		  	    return mapping.findForward("success");	
			}
			
			// Valido Nombre de Archivo
			if (!this.validarString(file.getFileName())){
				session.setAttribute("Msg", 
						messages.getText("generic.supportmaterial.cepatips.badfile"));
		  	    return mapping.findForward("error");	
			}
			// Fin Valido Nombre de Archivo
									
			InputStream reportStream = file.getInputStream();
			FileOutputStream fout;
			
			fout = new FileOutputStream(directory+"/"+fileName);
			
			int c = 0;
			while ( (c=reportStream.read()) != -1){
				fout.write(c);
			}			
			fout.flush();
			fout.close();				
			
			System.out.println("Guarde el archivo. Voy a insertar multas:");	
			
			if(sys.isCorporation(CorporationData.BAYER_MX)) 
				return insertBayerMXFines(mapping,form,request,response);
			else 
				return insertFines(mapping,form,request,response);
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
	
    public ActionForward insertFines(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        // String directory = "/tmp/datacenter/images";
        String directory = SecurityConstants.IMAGES_WAR_DIRECTORY;
        Text messages = sys.getText();
        String fileName = "fines"+sys.getUserSessionData().getFilter().getCorporationId()+".csv";
        int lineNumber = 1;
        int total = 0;
        LinkedList<String> notInsertedFines = new LinkedList<String>();
        ListResult result = null;
        StringBuffer word = new StringBuffer();
        
        try {
        	
        	
        	FileInputStream input = new FileInputStream(new File(directory + "/" + fileName));
        	 // Reader in = new InputStreamReader(input, "UTF-8");
        	Reader in = new InputStreamReader(input, "ISO-8859-1"); // portugues y español ISO-Latin1
        	

			DataTypeReportFacade dataTypeReport = sys.getDataTypeReportFacade();
			
			Hashtable<String,String> fineTypes = getDataType(sys, DataTypeConstants.FINE_TYPE, dataTypeReport);
			Hashtable<String,String> fineQualifies = getDataType(sys, DataTypeConstants.FINE_QUALIFY, dataTypeReport);

			Collection<FineDataType> fines = new LinkedList<FineDataType>();
            
            char car;
            car = 0;
            System.out.println("Inserto multas:");    
            
            // Asumo que en el archivo hay 3 lineas explicativas de los diferentes
            // campos a ingresar.
            int lineasExplicativas = 0;
            while (car != EOF && lineasExplicativas < 3){
            	  while(car != '\n' && car != EOF) {
                      // car = (char)input.read();                   
            		  car = (char)in.read(); // para compatibilidad con utf8
            	  }
            	  car = 0;
                  lineasExplicativas ++;
                  lineNumber ++;
            }
           
            // Ingreso las diferentes multas
            while(car != EOF && lineNumber <= 1000 ) {
            	int index = 0;
                car = 0;
                
                // campos a utilizar:
                // Datos del Conductor
                String firstName = "", lastName = "";
                Date birthdate = null;                 
                
                // Datos del Vehiculo
                String plate = "";                       
                            
                FineDataType fine = new FineDataType();
                
                // Leo la linea:
                while(car != '\n' && car != EOF) {
                	errorMessage = null;
                	car = (char)in.read();
                    if(car == '\t' || car == '\n') {
                    	String data = word.toString().trim();
                        switch(index) {

                        	// Datos del Conductor
                            case 0: firstName = eventData(data,messages,"driver.messages.upload.wrongname","NOMBRE CONDUCTOR "+data); break;
                            case 1: lastName = eventData(data,messages,"driver.messages.upload.wronglastname","APELLIDO CONDUCTOR "+data); break;
                            case 2: birthdate = birthdateData(data,messages); break;
                            
                            // Ubicación de la Multa
                            case 3: // Estado/Provincia del multa
                            		fine.setState(eventData(data,messages,null,null));
                            		break;
                            		
                            case 4:  // Ciudad/Localidad de la multa
                            		fine.setCity(eventData(data,messages,null,null));
                            		break;
                            
                            // Datos del Vehiculo, matricula o placa
                            case 5: plate = plateData(data,messages);break;
                            
                            // Datos de la Multa, FECHA de multa
                            case 6: fine.setDate(eventDateData(data,messages));
                            		break;
                            //Datos de muta, hora de multa
                            case 7: 
                            	if(sys.getCorporationId() == CorporationData.MONSANTO) {//MONSANTO) {
                            		fine.setHour(eventHourData(data,messages));
                            	}else {
	                            	if(!Util.empty(data) && data.trim().equalsIgnoreCase("Sin datos")){
	                            		fine.setHour(null);
	                            	}else {
	                            		fine.setHour(eventHourData(data,messages));
	                            	}
                            	}
                            	break;
                            
                            case 8: // Tipo de Multa: Grave, Gravisima, etc...
                            	fine.setFineType(fineTypeData(fineTypes,data.toLowerCase(),messages));
                        		break;
                                                
                            case 9: // Articulo de la Multa
                        		fine.setArticle(eventData(data,messages,"event.messages.upload.invalidarticle",null));
                        		break;
                            	
                            case 10: // Puntos de la multa
                        		fine.setPoints(pointsData(data,messages));                            	
                        		break;
                            
                            case 11: // Costo de la multa
                        		fine.setCost(costData(data,messages));
                        		break;
                        		
                          
                            case 12: // clasificacion de la multa 
                            		fine.setQualify(qualifyData(fineQualifies, data.toLowerCase(),messages)); 
                            		break;
                            		
                            case 13: // noticia o auto de infraccion
                            		fine.setInfringementNotice(eventData(data,messages,"generic.event.finedata.noticeInvalid",null));
                            		break;
                            		
                            case 14: // observaciones:
                            		fine.setObservations(eventData(data,messages,"generic.event.eventdata.observationsInvalid",null));
                            		break;
                            case 15: // lote:
                        		fine.setLote(loteData(data,messages));
                            
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
                
                
               
                // solo tengo en cuenta las lineas que tengan firstName y Lastname, las demas las ignoro:
                if (! Util.empty(firstName) && ! Util.empty(lastName)){
                	
                	/*
                     * Si hubo un error en algun campo salgo y muestro mensaje al usuario.
                     */
                    if (errorMessage != null){
                    	// Agreo número de linea al errorMessage:
                    	errorMessage += "\\n"+messages.getText("driver.messages.upload.linenumber")
        						  +": " + lineNumber;
                    	
                    	System.out.println("Error en el Upload de Multas: "+errorMessage);
                    	
                    	session.setAttribute("Msg",errorMessage); 
                    	return mapping.findForward("error"); 
                    }
                    
                	System.out.println("FirstName: "+firstName+" LastName: "+lastName
                     		+" Birthdate: " + birthdate);
                     
                
	                // Creo el evento:
	                // TODO: Le seteo todos los valores
	                
	                // ------------------
	                // 1. Busco el Driver 
	                // ------------------
	                
	                DriverData driver = getDriver(firstName, lastName, birthdate, messages);
	                if (driver == null){
	                	errorMessage += "\\n"+messages.getText("driver.messages.upload.linenumber")
	      					  +": "+lineNumber;
	                	session.setAttribute("Msg",errorMessage); 
	                	return mapping.findForward("error");
	                }                
	                
	                
	                // -----------------------
	                // 2. Busco el Vehiculo
	                // -----------------------
	                
	                VehicleData vehicle = getVehicle(plate,messages);
	                if (vehicle == null){
	                	
//	                	errorMessage += "\\n"+messages.getText("driver.messages.upload.linenumber")
//	  						  +": "+lineNumber;
//	                	session.setAttribute("Msg",errorMessage); 
//	                	return mapping.findForward("error");
	                	
	                	// Si el vehiculo es null cargo los datos basicos del vehiculo
	                	fine.setPlate(plate);
	                	fine.setModel("No Data"); // No Data
	                	fine.setTrade("No Data"); // No Data
	                	fine.setYear("No Data"); // No Data
	                	fine.setVehicle(null);
	                	
	                }else{
	                	fine.setVehicle(vehicle);
	                }
	                
	                // ----------------------------------
	                // 3. Seteo los valores en la multa
	                // ----------------------------------
	                fine.setDriver(driver);
	                	                
	                
	                // TODO: Coloco el evento en la coleccion de eventos para insertar en la base de datos
	                // Antes de insertarlo valido que no exista ese evento: fecha y conductor  en la base de datos
	                FineDataType fineExist = null;//sys.getEventReportFacade().existFine(sys.getUserSessionData(), driver.getId(), fine.getDate());
	                
	                if (fineExist != null){
	                	// Ya existe una multa cargada para ese conductor en esa fecha.
	                	// errorMessage = messages.getText("event.exist.forthatdriveranddate");
	                	// errorMessage.concat("\\n"+messages.getText("driver.messages.upload.linenumber")
	    				//	  +": "+lineNumber);
	                  	// session.setAttribute("Msg",errorMessage); 
	                  	// return mapping.findForward("error");
	                	
	                	// Si existe el evento le indico al usuario que no se va a insertar de nuevo.
	                	// Texto a mostrar: 
	                	String msg =  messages.getText("event.exist.forthatdriveranddate") + 
	                			": "+firstName+" "+lastName+" - "+
	                			messages.getText("driver.messages.upload.linenumber") +
	                			": "+lineNumber;
	                	
	                	notInsertedFines.add(msg);
	                }else{
	                	// Si los eventos no existen los agrego:  	
	                	fines.add(fine);
	                }
                } // fin if nombre y apellido diferentes de vacio.
                
                System.out.println("Linea: "+lineNumber);
                lineNumber++; // incremento numero de linea del archivo 
            }    
            
            // Inserto todas las multas que correspondan
            if (fines != null && fines.size() > 0){
            	EventABMFacade eventABM = sys.getEventABMFacade();
            	Iterator<FineDataType> it = fines.iterator();
            	while (it.hasNext()){
            		FineDataType fine = it.next();            		
                	eventABM.fineCreate(fine,sys.getUserSessionData(),true);
            	}
            }
                 
            // Total eventos insertados:
            total = fines.size();
            
            // eventos no insertados:
            result = new ListResult(notInsertedFines,notInsertedFines.size());
              
            System.out.println("Inserte "+total+" Multas.....");
            

        }catch(Exception e){
        	e.printStackTrace();
        }
    	
        session.setAttribute("notupload",result);
        session.setAttribute("total", (new Integer(total)).toString());        		  
        session.setAttribute("entity","fine.messages.upload");
        return mapping.findForward("success");	
    }
	
    public ActionForward insertBayerMXFines(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        String directory = SecurityConstants.IMAGES_WAR_DIRECTORY;
        Text messages = sys.getText();

        String fileName = "fines"+sys.getUserSessionData().getFilter().getCorporationId()+".csv";
        int lineNumber = 1;
        int total = 0;
        LinkedList<String> notInsertedFines = new LinkedList<String>();
        ListResult result = null;
        StringBuffer word = new StringBuffer();
        
        try {
        	FileInputStream input = new FileInputStream(new File(directory + "/" + fileName));
        	Reader in = new InputStreamReader(input, "ISO-8859-1"); // portugues y español ISO-Latin1

			DataTypeReportFacade dataTypeReport = sys.getDataTypeReportFacade();
			
			Hashtable<String,String> fineTypes = getDataType(sys, DataTypeConstants.FINE_TYPE, dataTypeReport);
			Hashtable<String,String> fineQualifies = getDataType(sys, DataTypeConstants.FINE_QUALIFY, dataTypeReport);

			Collection<FineDataType> fines = new LinkedList<FineDataType>();
            
            char car = 0;
            int lineasExplicativas = 0;
            while (car != EOF && lineasExplicativas < 3){
            	  while(car != '\n' && car != EOF) {
            		  car = (char)in.read(); 
            	  }
            	  car = 0;
                  lineasExplicativas ++;
                  lineNumber ++;
            }
           
            // Ingreso las diferentes multas
            while(car != EOF && lineNumber <= 1000 ) {
            	int index = 0;
                car = 0;
               
                // Datos del Vehiculo
                String plate = "";                       
                FineDataType fine = new FineDataType();
                String corporationId = null;
                
                // Leo la linea:
                while(car != '\n' && car != EOF) {
                	errorMessage = null;
                	car = (char)in.read();
                    if(car == '\t' || car == '\n') {
                    	String data = word.toString().trim();
                        switch(index) {
                        	// Datos del Conductor
                            case 0: 
                            	corporationId = eventData(data,messages,"driver.messages.upload.wrongname","ID CONDUCTOR "+data); break;
                            // Ubicación de la Multa
                            case 1: // Estado/Provincia del multa
                            		fine.setState(eventData(data,messages,null,null));
                            		break;
                            case 2:  // Ciudad/Localidad de la multa
                            		fine.setCity(eventData(data,messages,null,null));
                            		break;
                            // Datos del Vehiculo, matricula o placa
                            case 3: plate = plateData(data,messages);break;
                            // Datos de la Multa, FECHA de multa
                            case 4: fine.setDate(eventDateData(data,messages));
                            		break;
                            //Datos de muta, hora de multa
                            case 5: 
                            	if(!Util.empty(data) && data.trim().equalsIgnoreCase("Sin datos")){
                            		fine.setHour(null);
                            	}else {
                            		fine.setHour(eventHourData(data,messages));
                            	}
                            	break;
                            case 6: // Tipo de Multa: Grave, Gravisima, etc...
                            	fine.setFineType(fineTypeData(fineTypes,data.toLowerCase(),messages));
                        		break;
                            case 7: // Articulo de la Multa
                        		fine.setArticle(eventData(data,messages,"event.messages.upload.invalidarticle",null));
                        		break;
                            case 8: // Puntos de la multa
                        		fine.setPoints(pointsData(data,messages));                            	
                        		break;
                            case 9: // Costo de la multa
                        		fine.setCost(costData(data,messages));
                        		break;
                            case 10: // clasificacion de la multa 
                            	fine.setQualify(qualifyData(fineQualifies, data.toLowerCase(),messages)); 
                            	break;
                            case 11: // noticia o auto de infraccion
                            	fine.setInfringementNotice(eventData(data,messages,"generic.event.finedata.noticeInvalid",null));
                            	break;
                            case 12: // observaciones:
                            	fine.setObservations(eventData(data,messages,"generic.event.eventdata.observationsInvalid",null));
                            	break;
                            case 13: // lote:
                        		fine.setLote(loteData(data,messages));
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
               
                // solo tengo en cuenta las lineas que tengan firstName y Lastname, las demas las ignoro:
                if (!Util.empty(corporationId)){
                	// Si hubo un error en algun campo salgo y muestro mensaje al usuario.
                    if (errorMessage != null){
                    	// Agreo número de linea al errorMessage:
                    	errorMessage += "\\n"+messages.getText("driver.messages.upload.linenumber") + ": " + lineNumber;
                    	session.setAttribute("Msg",errorMessage); 
                    	return mapping.findForward("error"); 
                    }
	                
	                DriverData driver = getDriver(corporationId, messages);
	                if (driver == null){
	                	errorMessage += "\\n"+messages.getText("driver.messages.upload.linenumber") + ": " + lineNumber;
	                	session.setAttribute("Msg",errorMessage); 
	                	return mapping.findForward("error");
	                }                
	                fine.setDriver(driver);
	                
	                // -----------------------
	                // 2. Busco el Vehiculo
	                // -----------------------
	                VehicleData vehicle = getVehicle(plate,messages);
	                if (vehicle == null){
	                	fine.setPlate(plate);
	                	fine.setModel("No Data"); // No Data
	                	fine.setTrade("No Data"); // No Data
	                	fine.setYear("No Data"); // No Data
	                	fine.setVehicle(null);
	                	
	                }else{
	                	fine.setVehicle(vehicle);
	                }
	                
                	fines.add(fine);
                } // fin if nombre y apellido diferentes de vacio.
                lineNumber++; // incremento numero de linea del archivo 
            }    
            
            // Inserto todas las multas que correspondan
            if (fines != null && fines.size() > 0){
            	EventABMFacade eventABM = sys.getEventABMFacade();
            	Iterator<FineDataType> it = fines.iterator();
            	while (it.hasNext()){
            		FineDataType fine = it.next();            		
                	eventABM.fineCreate(fine,sys.getUserSessionData(),true);
            	}
            }
                 
            // Total eventos insertados:
            total = fines.size();
            // eventos no insertados:
            result = new ListResult(notInsertedFines,notInsertedFines.size());
        }catch(Exception e){
        	e.printStackTrace();
        }
    	
        session.setAttribute("notupload",result);
        session.setAttribute("total", (new Integer(total)).toString());        		  
        session.setAttribute("entity","fine.messages.upload");
        return mapping.findForward("success");	
    }

	private Hashtable<String,String> getDataType(SystemAccess sys, int type, DataTypeReportFacade dataTypeReport) throws Exception, RemoteException {
		Hashtable<String,String> fineTypes = new Hashtable<String,String>();
		Text messages = sys.getText();
		Collection<GeneralDTData> types = dataTypeReport.findAll(type,sys.getUserSessionData());
		if(types != null && types.size() > 0) {
		    Iterator<GeneralDTData> it = types.iterator();
		    while(it.hasNext()) {
		    	String dataType = it.next().getResourceBundleId();
		    	fineTypes.put(messages.getText(dataType).toLowerCase(),dataType);
		    }
		}
		return fineTypes;
	}
	
	/**
     * puntos de la mlta, obligatorio
     * @param data
     * @param messages
     * @return
     */
	private Integer pointsData(String data, Text messages) {
		try{
			if (!Util.empty(data)){
				if (Util.isNumber(data)){
					return new Integer(data);
				}else{
					errorMessage = messages.getText("generic.event.finedata.pointsInvalid")+". ";
					return null;
				}
			}else{
				errorMessage = messages.getText("generic.event.finedata.pointsEmpty")+". ";
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
			errorMessage = messages.getText("generic.event.finedata.pointsInvalid")+". ";
			return null;
		}
		
	}

	/**
	 * Clasificacion de multa, obligatorio
	 * @param fineQualifies 
	 * @param data
	 * @param messages
	 * @return
	 */
    private FineQualifyDataType qualifyData(Hashtable<String, String> fineQualifies, String data, Text messages) {
		try{
			if (!Util.empty(data)){
				if(fineQualifies.containsKey(data)) {
					return new FineQualifyDataType(fineQualifies.get(data));
				}
				errorMessage = messages.getText("event.messages.upload.invalidqualify")+". ";
				return null;
			}else{
				errorMessage = messages.getText("event.messages.upload.emptyqualify")+". ";
				return null;
			}
		}catch (Exception e){
			e.printStackTrace();
			errorMessage = messages.getText("event.messages.upload.invalidqualify")+". ";
			return null;
		}
			
	}

	private FineTypeDataType fineTypeData(Hashtable<String,String> fineTypes, String data, Text messages) {
		try{
			if (!Util.empty(data)){
				
				if(fineTypes.containsKey(data)) {
					return new FineTypeDataType(fineTypes.get(data));
				}
				errorMessage = messages.getText("event.messages.upload.invalidfinetype")+". ";
				return null;
				
			}else{
				errorMessage = messages.getText("event.messages.upload.emptyfinetype")+". ";
	  	  		return null;
			}
		}catch(Exception e){
			e.printStackTrace();
			errorMessage = messages.getText("event.messages.upload.invalidfinetype")+". ";
  	  		return null;
		}
	}

    /**
     * Fecha de Nacimiento: dd/mm/yyyy CAMPO NO OBLIGATORIO
     * @param data
     * @param messages
     * @param date
     * @return
     */
    public Date birthdateData(String data, Text messages){
    	try{
  		  if (!Util.empty(data)){                            			  
  			  return Util.getDate(data).getTime();
  			  
  		  }else{
      		  // errorMessage = messages.getText("driver.messages.upload.wrongbirthday") +". ";
      		  return null;
  		  }
        } catch (Exception e) {
            System.out.println("Sin Fecha de Nacimiento. ");
            errorMessage = messages.getText("driver.messages.upload.wrongbirthday")+". ";
            return null;
        }
        
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
        	if (data != null && !data.trim().equals("")){
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
    public Time eventHourData(String data, Text messages){
    	try {
    		if(!Util.empty(data) && data.length()==4 ){
                // por si viene una hora 8:30 sin el cero delante
                data = "0".concat(data);
    		}
    		if (!Util.empty(data) && data.length() >= 5){    
    			String hour = data.substring(0,2); //hh:mm
    			String minute = data.substring(3,5);
    			Time time = Util.getTime(hour,minute);
    			if(time == null) {
    				errorMessage = messages.getText("generic.event.eventdata.timeEmpty");
    				return null;
    			}
    			if(!Util.checkHour(Integer.parseInt(hour),Integer.parseInt(minute))) {
    				errorMessage = messages.getText("generic.event.eventdata.hourInvalid");
    				return null;
    			}
    			return time;
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
     * Daños materiales (Si,No o vacio) , campo NO obligatorio
     * @param data
     * @param messages
     * @return
     */
    public boolean materialsdamageData(String data, Text messages){
    	try{
    		if (!Util.empty(data)){
    			if (data.equalsIgnoreCase(messages.getText("generic.messages.yes"))){
    				return true;
    			}    	
    		}
    	}catch(Exception e){
    		System.out.println("Daños Materiales Invalido.");
    		errorMessage = messages.getText("generic.event.eventdata.materialsdamageinvalid")+". ";
    		return false;
    	}
    	return false;
    	
    }
    
	/**
	 * costo estimado de la multa
	 * @param data
	 * @param messages
	 * @return
	 */
	private Integer costData(String data, Text messages) {
		try{
			if (Util.isNumber(data)){
				return new Integer(data);
			}else{
				errorMessage = messages.getText("generic.event.eventdata.finecostEmpty")+". ";
				return null;
			}
		}catch(Exception e){
			errorMessage = messages.getText("generic.event.eventdata.finecostInvalid")+". ";
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

    private DriverData getDriver(String corporationId, Text messages){
    	try{
            Hashtable<String,Object> parameters = new Hashtable<String,Object>();
           	parameters.put("corporationid", corporationId);
	        Collection drivers = sys.getDriverReportFacade().findDriverByCorporationId(corporationId, sys.getUserSessionData());
	        
	        // No se encontro el driver, error
	        if (drivers == null || drivers.size() <= 0){
	        	errorMessage = messages.getText("driver.notfound");
	        	return null;
	        }
	        
	        // Si hay mas de un driver no se puede agregar el evento:
	        if (drivers.size() > 1){
	        	errorMessage = messages.getText("driver.repeatdata.canaddevent");
	        	return null;
	        }        
	        
	        return (DriverData) drivers.iterator().next();
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

