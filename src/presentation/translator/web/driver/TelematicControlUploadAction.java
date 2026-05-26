// 5368208171
package presentation.translator.web.driver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

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
import business.driver.DriverABMFacade;
import business.driver.DriverReportFacade;

import com.csvreader.CsvReader;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.user.UserSessionData;
import communication.driver.tables.DriverData;
import communication.driver.tables.TelematicControlData;
import communication.language.Text;
import communication.security.SecurityConstants;
import communication.util.ListResult;
import communication.vehicle.tables.VehicleData;

public class TelematicControlUploadAction extends AbstractAction {

	static final int EOF = 65535;
	
	/**
	 * 
	 */	
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
				
		HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
        // String directory = "/tmp/datacenter/images";
        String directory = SecurityConstants.IMAGES_WAR_DIRECTORY;
        String fileName = "clasificacionTelematica"+sys.getUserSessionData().getFilter().getCorporationId()+".csv";
    
		try {
		
			System.out.println("Entro al Action TelematicControlUploadAction");
			TelematicControlUploadForm uploadForm = (TelematicControlUploadForm) form;
			FormFile file = (FormFile)uploadForm.getFile();		
					
			// Valido archivo de vacio o nulo
			if (file == null || file.getFileName() == null || file.getFileName().equals("")){
				session.setAttribute("Msg", 
						messages.getText("generic.supportmaterial.cepatips.nofile"));
		  	    return mapping.findForward("error");	
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
					
			fout = new FileOutputStream(directory + fileName);
			
			int c = 0;
			while ( (c=reportStream.read()) != -1){
				fout.write(c);
			}			
			fout.flush();
			fout.close();				
			
			System.out.println("Guarde el archivo. Voy a insertar controles telematicos:");
			
			return insertTelematicControls(mapping,form,request,response);			
	    }
		catch (IOException io){
			session.setAttribute("Msg", messages.getText("generic.supportmaterial.cepatips.badpermissions"));
	    	io.printStackTrace();
	  	    return mapping.findForward("error");	
		}catch(Exception e) {
	    	session.setAttribute("Msg", messages.getText("generic.telematiccontrolupload.error"));
	    	e.printStackTrace();
	  	    return mapping.findForward("error");	
	    }	   
	   
	}
	
    /**
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Throwable
     */
	public static ActionForward insertTelematicControls(
			ActionMapping mapping, ActionForm form, HttpServletRequest request, 
    		HttpServletResponse response) throws Exception {
    	  	HttpSession session = request.getSession();
    	  	SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
    	  	UserSessionData userSessionData = sys.getUserSessionData();
    		
	  		int total = 0;
	  		LinkedList<String> notInsertedControls = new LinkedList<String>(); // cuando el iddallas no existe
	  		LinkedList<String> existedControls = new LinkedList<String>(); // cuando ya existe un evento/fecha para ese iddallas
	  		ListResult result;
	  		
    	  	try {
    	  		Text messages = sys.getText();
    	  		String directory = SecurityConstants.IMAGES_WAR_DIRECTORY;
    	  		String fileName = "clasificacionTelematica"+sys.getUserSessionData().getFilter().getCorporationId()+".csv";
    	  
    	  		// Atributos del control telematico:
    	  		Integer id;
    	  		DriverData driver;
    	  		VehicleData vehicle = null;
    	  		Date date;
	  			Integer type;
	  			Integer recomendation;
	  			String observations;
	  			
	  			String corporationId;    	  
	  			String fullName;
	  			
	  			String dateStr;
	  			String eventStr;
	  			String recomendationStr;
	  			
	  			// Nuevos Campos 17/07/2012
	  			Integer occurrences = null;
	  			String occurrencesStr = "";
	  		    Integer speed = null; // Velocidad maxima a la que llego el conductor en km/h
	  		    String speedStr = "";
	  		    Integer limitExceeded = null; // velocidad maxima permitida
	  		    String limitExceededStr = "";
	  		    Integer limitExceededRain = null; // velocidad maxima permitida en lluvia
	  		    String limitExceededRainStr = "";
	  		    String time = "";; // Formato: hh:mm:ss
	  		    String duration = ""; // Duracion en hh:mm:ss
	  		    String totalDuration = ""; // Duracion en hh:mm:ss
	  		    Float distance; // Distancia total recorrida en exceso de velocidad
	  		    String distanceStr = "";
	  		    Float averageVelocity;
	  		    String averageVelocityStr = "";
	  		    String vehiclePlate = "";
	  		    String color = null;
	  			
    	  	
    	  		DriverReportFacade driverReport = null;
    	  		driverReport = sys.getDriverReportFacade();
    	  		Collection<TelematicControlData> telematicControls = new LinkedList<TelematicControlData>();
    	  		
    	  		int row = 1;
    	  		String msg = "";
    	  		
    	  		CsvReader reader  = null;
    	  		TelematicControlUploadForm uploadForm = (TelematicControlUploadForm) form;
    	  		int colorType = Integer.parseInt(uploadForm.getType());
    	  		String notes = (String)uploadForm.getNotes();
		      	reader = new CsvReader(directory + fileName);
		      	// asigno separador de valores TAB
    	  		reader.setDelimiter('\t');
    	  		
    	  		// recorremos las filas del fichero			
    	  		while (reader.readRecord()) {
    	  			
    	  			//1 filas de cabeceras
    	  			// Maximo 505 filas en el archivo
    	  			if (row >= 2 && row <= 1000){
    	  				
    	  				corporationId = reader.get(0).trim();
    	  				fullName = reader.get(1).trim();
    	  				
    	  				int error = 0;
    	  				if (!Util.empty(corporationId)){
	                       	// Busco el conductor con ese idDallas
	    	  				// Si no existe un conductor con ese id sigo con el siguiente registro
	    	  				corporationId = corporationId.trim().toUpperCase();
	    	  				Collection drivers = null;
	    	  				if(corporationId.equals("-1")) {
	    	  					drivers = driverReport.findDriverByFullName(fullName, userSessionData);
	    	  				}else {
	    	  					drivers = driverReport.findDriverByCorporationId(corporationId, userSessionData);
	    	  				}
	    	  				if (drivers != null && drivers.size() == 1) {
	   	  				   	  	driver = (DriverData)drivers.iterator().next();
	    	  					 // Existe el conductor, leo los deemas datos del evento telematico:

	   	  				   	  	if(colorType == 1) {

	   	  				   	  		// Numero de Ocurrencias:
		    	  					 occurrencesStr = reader.get(2).trim();
		    	  					 if (!Util.empty(occurrencesStr) && Util.isNumber(occurrencesStr)){                            			  
				  						occurrences = new Integer(occurrencesStr);
				  					 }else{
				  						 session.setAttribute("Msg", 
			                   				  messages.getText("driver.messages.upload.wrongocurrences")
			                   				  +". \\n"+messages.getText("driver.messages.upload.linenumber")+" "+row);                                  	  	
				  						 return mapping.findForward("error");                            			  
				  					 }
			                         
			                         // Velocidad maxima alcanzada
		    	  					 speedStr = reader.get(3).trim();
		                     		 if (!Util.empty(speedStr) && Util.isNumber(speedStr)){                            			  
		    	  						speed = new Integer(speedStr);
		    	  					 }else{
		    	  						 session.setAttribute("Msg", 
		                       				  messages.getText("driver.messages.upload.wrongspeed")
		                       				  +". \\n"+messages.getText("driver.messages.upload.linenumber")+" "+row);                                  	  	
		    	  						 return mapping.findForward("error");                            			  
		    	  					 }
			                         
			                         // Limite excedido
			                         limitExceededStr = reader.get(4).trim();
		                    		 if (!Util.empty(limitExceededStr)) {
		                    			 if (Util.isNumber(limitExceededStr)){                            			  
		                    				 limitExceeded = new Integer(limitExceededStr);
		                    			 }else{
		                    				 // no ingresron solo numeros
		                    				 session.setAttribute("Msg", 
		                    						 messages.getText("driver.messages.upload.wronglimitExceeded")
		                    						 +". \\n"+messages.getText("driver.messages.upload.linenumber")+" "+row);                                  	  	
		                    				 return mapping.findForward("error");
		                    			 }
		    	  					 }else{
		    	  						 // Si viene vacio, es porque viene con datos para lluvia
		    	  						 limitExceeded = 0;
		    	  					 }
		                    		 
		                    		 // Limite Excedido bajo Lluvia
			                         limitExceededRainStr = reader.get(5).trim();
		                    		 if (!Util.empty(limitExceededRainStr)){
		                    			 if (Util.isNumber(limitExceededRainStr)){                            			  
		                    				 limitExceededRain = new Integer(limitExceededRainStr);
		                    			 }else{    	
		                    				 // no ingresaron solo numeros
			    	  						 session.setAttribute("Msg", 
			                       				  messages.getText("driver.messages.upload.wronglimitExceededRain")
			                       				  +". \\n"+messages.getText("driver.messages.upload.linenumber")+" "+row);                                  	  	
			    	  						 return mapping.findForward("error");  
		    	  						}                          			  
		    	  					 }else{
		    	  						 // si viene vacio es porque vino con limite excedido normal
		    	  						 limitExceededRain = 0;
		    	  					 }
			                  	
			                         // Fecha
			    	  				dateStr = reader.get(6).trim();
			    	  				try{
			    	  					 if (!Util.empty(dateStr)){                            			  
			    	  						 date = (Util.getDate(dateStr).getTime());
			    	  					 }else{
			    	  						 session.setAttribute("Msg", 
			                       				  messages.getText("driver.messages.upload.wrongdate")
			                       				  +". \\n"+messages.getText("driver.messages.upload.linenumber")+" "+row);                                  	  	
			    	  						 return mapping.findForward("error");                            			  
			    	  					 }
			                         } catch (Exception e) {
			                             System.out.println("Control Telematico sin fecha, linea: " + row);
			                             session.setAttribute("Msg", 
			                   				  messages.getText("driver.messages.upload.wrongdate")
			                   				  +". \\n"+messages.getText("driver.messages.upload.linenumber")+" "+row);
			                   	  	 	return mapping.findForward("error");    
			                        }
		                         
			                        // Hora	                        
		                            time = reader.get(7).trim();
		                           
		                            try{
		                            	if (!Util.empty(time)){
		                            		time =  java.sql.Time.valueOf(time).toString();
		                            	}else{
		    	  						 session.setAttribute("Msg", 
		                       				  messages.getText("driver.messages.upload.wrongtime")
		                       				  +". \\n"+messages.getText("driver.messages.upload.linenumber")+" "+row);                                  	  	
		    	  						 return mapping.findForward("error");                            			  
		                            	}
		                            }catch(Throwable e){
		                            	session.setAttribute("Msg", 
		                         				  messages.getText("driver.messages.upload.wrongtime")
		                         				  +". \\n"+messages.getText("driver.messages.upload.linenumber")+" "+row);                                  	  	
		      	  						 return mapping.findForward("error");
		                            }
		                             
			                         // Duracion
			                         duration = reader.get(8).trim();
			                         try{
			                            if (!Util.empty(duration)){
			                            	duration =  java.sql.Time.valueOf(duration).toString();
			                            } else{                        			  
			    	  						 session.setAttribute("Msg", 
			                       				  messages.getText("driver.messages.upload.wrongduration")
			                       				  +". \\n"+messages.getText("driver.messages.upload.linenumber")+" "+row);                                  	  	
			    	  						 return mapping.findForward("error");   
			                            }
			                         }catch(Throwable e){
			                        	 session.setAttribute("Msg", 
			                       				  messages.getText("driver.messages.upload.wrongduration")
			                       				  +". \\n"+messages.getText("driver.messages.upload.linenumber")+" "+row);                                  	  	
			    	  						 return mapping.findForward("error");   
			                         }
			                        
			                         // Duracion Total
			                         totalDuration = reader.get(9).trim();
			                         try{
			                            	if (!Util.empty(totalDuration)){
			                            		totalDuration =  java.sql.Time.valueOf(totalDuration).toString();
			                            	}else{        
			    	  						 session.setAttribute("Msg", 
			                       				  messages.getText("driver.messages.upload.wrongtotalduration")
			                       				  +". \\n"+messages.getText("driver.messages.upload.linenumber")+" "+row);                                  	  	
			    	  						 return mapping.findForward("error");        
			                            	}
			    	  				 }catch (Throwable e){
			    	  					 session.setAttribute("Msg", 
			                       				  messages.getText("driver.messages.upload.wrongtotalduration")
			                       				  +". \\n"+messages.getText("driver.messages.upload.linenumber")+" "+row);                                  	  	
			    	  						 return mapping.findForward("error");  
			    	  				 }
			                         
			                        // Distancia total recorrida
			                        distanceStr = reader.get(10).trim().replace(",", ".");
			                        if (!Util.empty(distanceStr) && Util.isFloat(distanceStr)){
			                        	 distance = new Float(distanceStr);
			                        }else{
			                        	 distance = null;
		    	  					}
			                         
			                         // VelocidadMedia
			                        averageVelocityStr = reader.get(11).trim().replace(",", ".");
			                        if (!Util.empty(averageVelocityStr) && Util.isFloat(averageVelocityStr)){
			                        	averageVelocity = new Float(averageVelocityStr);
			                        }else{
			                        	averageVelocity = null;
		    	  					}

			                        vehiclePlate = reader.get(12).trim();
			                        if(!Util.empty(vehiclePlate)) {
			                        	ListResult list = sys.getVehicleReportFacade().findVehicleByPlateWithRepeats(vehiclePlate, userSessionData);
			                        	switch(list.getTotal()) {
			                        		case 0:
			                        			session.setAttribute("Msg", 
						                   				  messages.getText("driver.messages.upload.wrongvehicle")
						                   				  +". \\n"+messages.getText("driver.messages.upload.linenumber")+" "+row);                                  	  	
							  					return mapping.findForward("error");                            			  
			                        		case 1:
			                        			vehicle = (VehicleData)list.getElements().iterator().next();
			                        			break;
			                        		default:
			                        			session.setAttribute("Msg", 
						                   				  messages.getText("driver.messages.upload.repeatedvehicle")
						                   				  +". \\n"+messages.getText("driver.messages.upload.linenumber")+" "+row);                                  	  	
							  					return mapping.findForward("error"); 
			                        	}
			                        }
			                        // Event type:
			                         type = TelematicControlData.TYPE_EXCESSIVE_SPEED;
			                         
			                         /* No va, actualizado al 17/07/2012. Solo va EXCESO DE VELOCIDAD */
			                         /*
			                        eventStr = reader.get(4).trim();
			                        try{
			                        	if(!Util.empty(eventStr)) {
			                        		// Esta hecho solo en portugués:
			                        		if(eventStr.equalsIgnoreCase("Evento vazio")) {
			                        			type = TelematicControlData.TYPE_EMPTY;
			                        		}else if (eventStr.equalsIgnoreCase("Arrancada Brusca")){
			                        			type = TelematicControlData.TYPE_HARD_START;
			                        		}else if (eventStr.equalsIgnoreCase("Freada Brusca")){
			                        			type = TelematicControlData.TYPE_HARD_STOP;
			                        		}else if (eventStr.equalsIgnoreCase("Excesso de Rotação do Motor")){
			                        			type = TelematicControlData.TYPE_EXCESIVE_ROTATION;
			                        		}else if (eventStr.equalsIgnoreCase("Direção com câmbio em neutro ou ponto morto")){
			                        			type = TelematicControlData.TYPE_BAD_DIRECTION;
			                        		}else if (eventStr.equalsIgnoreCase("Velocidade Máx. Operacional")){
			                        			type = TelematicControlData.TYPE_OPERATIONAL;
			                        		}else if (eventStr.equalsIgnoreCase("Velocidade Máx. sob Chuva")){
			                        			type = TelematicControlData.TYPE_RAIN;
			                        		}else {
			                        			session.setAttribute("Msg", 
			                   						  messages.getText("driver.messages.upload.wrongtype")+eventStr
			                   						  +". \\n"+messages.getText("driver.messages.upload.linenumber")+": "+row);
			                   				  return mapping.findForward("error");    
			                   			  }
			                   		  }else{
			                   			  session.setAttribute("Msg", 
			             						  messages.getText("driver.messages.upload.wrongtype")+eventStr
			             						  +". \\n"+messages.getText("driver.messages.upload.linenumber")+": "+row);
			             				  return mapping.findForward("error");    
			                   		  }
			                   	  }catch(Exception e){
			                   		  session.setAttribute("Msg", 
			           						  messages.getText("driver.messages.upload.wrongtype")+eventStr
			           						  +". \\n"+messages.getText("driver.messages.upload.linenumber")+": "+ row);
			           				  return mapping.findForward("error");                       			
			                   	  }
			                   	  
			                   	// Tratamiento
			                   	recomendationStr = reader.get(5).trim();
			                   	try{
			                   		if(!Util.empty(recomendationStr)) {
			                   			// Esta hecho solo en portugués:
			                    		if(recomendationStr.equalsIgnoreCase("Ação Preventiva vazia")) {
			                    			recomendation = TelematicControlData.RECOMENDATION_EMPTY;
			                    		}else if (recomendationStr.equalsIgnoreCase("IPG")){
			                    			recomendation = TelematicControlData.RECOMENDATION_IPG;
			                    		}else if (recomendationStr.equalsIgnoreCase("DCT")){
			                    			recomendation = TelematicControlData.RECOMENDATION_DCT;
			                    		}else if (recomendationStr.equalsIgnoreCase("DCC")){
			                    			recomendation = TelematicControlData.RECOMENDATION_DCC;
			                    		}else if (recomendationStr.equalsIgnoreCase("ATSV")){
			                    			recomendation = TelematicControlData.RECOMENDATION_ATSV;                    	
			                    		}else {
			                    			session.setAttribute("Msg", 
			               						  messages.getText("driver.messages.upload.wrongrecomendation")+recomendationStr
			               						  +". \\n"+messages.getText("driver.messages.upload.linenumber")+": "+row);
			               				  	return mapping.findForward("error");    
			               			  	}
			               		  	}else{
			               			  session.setAttribute("Msg", 
			         						  messages.getText("driver.messages.upload.wrongrecomendation")+recomendationStr
			         						  +". \\n"+messages.getText("driver.messages.upload.linenumber")+": "+row);
			         				  return mapping.findForward("error");    
			               		  	}
			                   	}catch(Exception e){
			                   		session.setAttribute("Msg", 
			       						  messages.getText("driver.messages.upload.wrongrecomendation")+recomendationStr
			       						  +". \\n"+messages.getText("driver.messages.upload.linenumber")+": "+ row);
			                   		return mapping.findForward("error");                       			
			                   	}
			                   	
			                   	// Observaciones
			                	observations = reader.get(6).trim();
			                   	*/
		    	  			
				                    recomendation = TelematicControlData.RECOMENDATION_EMPTY;
				                    observations = null;
				                    
				                    /* Pre-identificacion del color del control telematico */
				                    // RED
				                    if ( (occurrences >= 4 && speed >= 121 && speed <= 130 && limitExceeded > 0) || 
				                    	 (occurrences >= 1 && speed >= 131 && limitExceeded > 0) ) 
				                    	color = TelematicControlData.COLOR_RED;
				                    if ( (occurrences >= 1 && occurrences <= 3 && speed >= 121 && speed <= 130 && limitExceeded > 0) ||
				                    	 (occurrences >= 4 && speed >= 101 && limitExceededRain > 0)	)
				                    	color = TelematicControlData.COLOR_YELLOW;
				                  
				                    // FIN COLOR
				                    
				                    
			    	  				// Existe el driver, le cargo el control telematico:
			                   	  	// Agrego el control telematico a la coleccion
				                    // id del control telematico = null para que lo genere.
			    	  				TelematicControlData control = new TelematicControlData(occurrences, 
			    	  						speed, limitExceeded, time,duration, totalDuration,distance,
			    	  						averageVelocity,null,driver,date,type,recomendation,
			    	  						observations,color, limitExceededRain, null, null, null, vehicle); 
			    	  				
			    	  				// Validar que no exista el control telematico
			    	  				// 	Si existe el idDallas cargo el control telematico
			      					if (driverReport.existTelematicControl(driver.getId(),date,type,userSessionData)){
			      						// Si existe idDallas y control telematico (evento,fecha) no se vuelve a insertar
			          					// se marca como existedControls
			      						msg = " - " + fullName + " - " + corporationId + ". ("+
			              		  			messages.getText("driver.messages.upload.linenumber")+": "+ row +")";
			        	  				existedControls.add(msg);
			      					}else{
			      						// Si no existe ese control telematico, lo agrego a la BD.
			      						telematicControls.add(control);
			      					}
	   	  				   	  	}else {
			                         // Fecha
			    	  				dateStr = reader.get(2).trim();
			    	  				try{
			    	  					 if (!Util.empty(dateStr)){                            			  
			    	  						 date = (Util.getDate(dateStr).getTime());
			    	  					 }else{
			    	  						 session.setAttribute("Msg", 
			                       				  messages.getText("driver.messages.upload.wrongdate")
			                       				  +". \\n"+messages.getText("driver.messages.upload.linenumber")+" "+row);                                  	  	
			    	  						 return mapping.findForward("error");                            			  
			    	  					 }
			                         } catch (Exception e) {
			                             System.out.println("Control Telematico sin fecha, linea: " + row);
			                             session.setAttribute("Msg", 
			                   				  messages.getText("driver.messages.upload.wrongdate")
			                   				  +". \\n"+messages.getText("driver.messages.upload.linenumber")+" "+row);
			                   	  	 	return mapping.findForward("error");    
			                        }

			                        vehiclePlate = reader.get(3).trim();
			                        boolean insert = true;
			                        if(!Util.empty(vehiclePlate)) {
			                        	ListResult list = sys.getVehicleReportFacade().findVehicleByPlateWithRepeats(vehiclePlate, userSessionData);
			                        	if(list == null || list.getTotal() == 0) {
			                        		list = sys.getVehicleReportFacade().findVehicleByPlateWithRepeats(vehiclePlate.replaceAll(" ", ""), userSessionData);
			                        	}
			                        	if(list == null) {
					    	  				msg = " - " + vehiclePlate + " - " + messages.getText("driver.messages.upload.wrongvehicle") + ". ("+
						              		  		messages.getText("driver.messages.upload.linenumber")+": "+ row +")";
						    	  			notInsertedControls.add(msg);
						    	  			insert = false;
			                        	}else {
				                        	switch(list.getTotal()) {
				                        		case 0:
							    	  				msg = " - " + vehiclePlate + " - " + messages.getText("driver.messages.upload.wrongvehicle") + ". ("+
								              		  		messages.getText("driver.messages.upload.linenumber")+": "+ row +")";
								    	  			notInsertedControls.add(msg);
								    	  			insert = false;
								    	  			break;
				                        		case 1:
				                        			vehicle = (VehicleData)list.getElements().iterator().next();
				                        			break;
				                        		default:
							    	  				msg = " - " + vehiclePlate + " - " + messages.getText("driver.messages.upload.repeatedvehicle") + ". ("+
								              		  		messages.getText("driver.messages.upload.linenumber")+": "+ row +")";
								    	  			notInsertedControls.add(msg);
								    	  			insert = false;
								    	  			break;
				                        	}
			                        	}
			                        }

			                        if(insert) {
				                        // Existe el driver, le cargo el control telematico:
				                   	  	// Agrego el control telematico a la coleccion
					                    // id del control telematico = null para que lo genere.
				    	  				TelematicControlData control = new TelematicControlData(driver,vehicle,date); 
				    	  				
				    	  				// Validar que no exista el control telematico
				    	  				// 	Si existe el idDallas cargo el control telematico
				      					if (driverReport.existTelematicControl(driver.getId(),date,TelematicControlData.TYPE_EMPTY,userSessionData)){
				      						// Si existe idDallas y control telematico (evento,fecha) no se vuelve a insertar
				          					// se marca como existedControls
				      						msg = " - " + fullName + " - " + corporationId + ". ("+
				              		  			messages.getText("driver.messages.upload.linenumber")+": "+ row +")";
				        	  				existedControls.add(msg);
				      					}else{
				      						// Si no existe ese control telematico, lo agrego a la BD.
				      						telematicControls.add(control);
				      					}
			                        }
	   	  				   	  	}
	    	  				}else{
		    	  				// No existe un driver con ese idDallas
		    	  				// Si no existe el idDallas se guarda el control telematico en notInsertedControls
		    	  				// nombre, apellido, iddallas, numero de linea.
		    	  				msg = " - " + fullName + " - " + corporationId + ". ("+
		              		  		messages.getText("driver.messages.upload.linenumber")+": "+ row +")";
		    	  				notInsertedControls.add(msg);
		  					
		    	  			} // fin if(driver != null)
    	  				} // fin if (!Util.empty(idDallas)) 
    	  			}  // fin if(row > 2)
    	  			row++;
    	  		}
			      
             
              // Inserto los controles telematicos:
              DriverABMFacade driverABM = null;
              driverABM = sys.getDriverABMFacade();    
              driverABM.addManyTelematicControls(telematicControls,sys.getUserSessionData());
              
              // Total Controles Telematicos insertados:
              total = telematicControls.size();              
              // System.out.println("Inserte "+total+"Conductores.....");
              
          } catch (Exception e) {
              e.printStackTrace();
        	  System.out.println("Excepcion....."+e.getMessage());
        	  throw e;
          }
          
          session.setAttribute("total", (new Integer(total)).toString());       
          session.setAttribute("notInsertedControls",new ListResult(notInsertedControls,notInsertedControls.size()));
          session.setAttribute("existedControls",new ListResult(existedControls,existedControls.size()));
          session.setAttribute("entity","driver.messages.upload.telematiccontrols");
          return mapping.findForward("success");	
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
    		// Por más codigos referise a:
    		// http://webdesign.about.com/library/bl_htmlcodes.htm
    		switch (s.charAt(count)){
    			case (char)225: aux.append("a"); //'á' 
    				break;
    			case (char)233: aux.append("e"); //'é' 
    				break;
    			case (char)237: aux.append("i"); //'í'
    				break;
    			case (char)243: aux.append("o"); // 'ó'
    				break;
    			case (char)250: aux.append("u"); //'ú'
    				break;    			
    			case (char)231: aux.append("c"); // 'ç'
    				break;
    			case (char)228: aux.append("a"); // 'ã'
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
    			case (char)193: aux.append("A"); //Á
    				break;
    			case (char)201: aux.append("E"); //É
    			    break;
    			case (char)205: aux.append("I"); //Í
    			    break;
    			case (char)211: aux.append("O"); //Ó
    				break;
    			case (char)218: aux.append("U"); //Ú
    				break;
    			case (char)199: aux.append("C"); //Ç
    				break;
    			case (char)195: aux.append("A"); //Ã
    				break;
    			default: aux.append(String.valueOf(s.charAt(count)));
    				break;
   	    		// Por más codigos referise a:
   	    		// http://webdesign.about.com/library/bl_htmlcodes.htm
    		}
    		count ++;
    	}
    	return aux.toString().replace(" ","_");
		
	}
	
}

