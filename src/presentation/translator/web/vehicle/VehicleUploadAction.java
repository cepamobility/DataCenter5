package presentation.translator.web.vehicle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
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
import business.datatype.DataTypeReportFacade;
import business.vehicle.VehicleABMFacade;
import business.vehicle.VehicleReportFacade;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.GeoLabelData;
import communication.administration.corporation.tables.LabelItemData;
import communication.datatype.DataTypeConstants;
import communication.datatype.tables.EnsuranceDataType;
import communication.datatype.tables.TypeCenterCostDataType;
import communication.datatype.tables.TypeCoverDataType;
import communication.datatype.tables.TypeDistanceUnitDataType;
import communication.datatype.tables.TypeEngineDataType;
import communication.datatype.tables.TypeFuelDataType;
import communication.datatype.tables.TypeTradeDataType;
import communication.datatype.tables.TypeUseDataType;
import communication.datatype.tables.TypeVehicleDataType;
import communication.datatype.tables.TypeVolumeUnitDataType;
import communication.driver.tables.DriverData;
import communication.driver.tables.DriverMainAttributes;
import communication.language.Text;
import communication.security.SecurityConstants;
import communication.util.ListResult;
import communication.vehicle.tables.VehicleData;
import communication.vehicle.tables.VehicleOptionalInfoData;

public class VehicleUploadAction extends AbstractAction {

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
    	String fileName = "vehicles"+sys.getUserSessionData().getFilter().getCorporationId()+".csv";			   			
    	
		try {
		
			VehicleUploadForm uploadForm = (VehicleUploadForm) form;
			FormFile file = (FormFile)uploadForm.getFile();		
			
			// Valido archivo de CepaTips vacio o nulo
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
			File file2;
			
			fout = new FileOutputStream(directory+"/"+fileName);
			
			int c = 0;
			while ( (c=reportStream.read()) != -1){
				fout.write(c);
			}			
			fout.flush();
			fout.close();		
					
			file2 = new File(directory+"/"+fileName);				
			
			
			System.out.println("Guarde el archivo. Voy a insertar vehiculos:");	
			
			return insertVehicles(mapping,form,request,response);
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
	
	/**
	 * 
	 * @param s
	 * @return
	 */
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
	
    /**
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Throwable
     */
    public static ActionForward insertVehicles(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
  
    		HttpSession session = request.getSession();
	        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
	        // String directory = "/tmp/datacenter/images";
	        String directory = SecurityConstants.IMAGES_WAR_DIRECTORY;
	        Text messages = sys.getText();
	        String fileName = "vehicles"+sys.getUserSessionData().getFilter().getCorporationId()+".csv";
	        int lineNumber = 1;
	        int total = 0;
	        LinkedList<String> notInsertedVehicles = new LinkedList<String>();
	        ListResult result;
	        StringBuffer word = new StringBuffer();
	        
	        try {
	        	
	        	VehicleReportFacade vehicleReport = null;
	            vehicleReport = sys.getVehicleReportFacade();
	        	FileInputStream input = new FileInputStream(new File(directory + "/" + fileName));
	            Collection<VehicleData> vehicles = new LinkedList<VehicleData>();
	          
	            char car;
	            car = 0;
	            Collection groupLines = sys.getUserSessionData().getFilter().getCorporationGroupLines();
	            System.out.println("Inserto vehiculos:");    
	            
	            // Asumo que en el archivo hay 2 lineas explicativas de los diferentes
	            // campos a ingresar.
	            int lineasExplicativas = 0;
	            while (car != EOF && lineasExplicativas < 2){
	            	  while(car != '\n' && car != EOF) {
	                      car = (char)input.read();                   
	            	  }
	            	  car = 0;
	                  lineasExplicativas ++;
	                  lineNumber ++;
	            }
	           
	            // Ingreso los diferentes vehiculos
	            while(car != EOF) {
	            	Integer id = null;
	                VehicleData vehicle = new VehicleData(id);
	                
	                VehicleOptionalInfoData optionalInfo = new VehicleOptionalInfoData();
	                // Solo Klabin y Yoli tienen datos extras:
	                if (sys.getUserSessionData().getFilter().getCorporationId().
	                		equals(new Integer(CorporationData.KLABIN)) ||
	                		sys.getUserSessionData().getFilter().getCorporationId().
	                		equals(new Integer(CorporationData.YOLI)) 
	                	){
	                	// Klabin y Yoli tiene campos opcionales:	                	
	                	vehicle.setOptionalInfo(optionalInfo);
	                }
	                
	                ArrayList<DivOrgData>  divOrgs = new ArrayList<DivOrgData>();
	                int index = 0;
	                car = 0;
	                
	                String firstName = "";
	                String lastName = "";
	                
	                String region = "";
	                String divOrgName11 = null, divOrgName12 = null, divOrgName13 = null;
	                String divOrgName21 = null, divOrgName22 = null, divOrgName23 = null;
	                String divOrgName31 = null, divOrgName32 = null, divOrgName33 = null;
	                String use = null;
	               
	                while(car != '\n' && car != EOF) {
	                    car = (char)input.read();
	                    if(car == '\t' || car == '\n') {
	                        switch(index) {
	                            case 0:
	                            	// Vehiculo Institucional o Individual
	                            	if (!Util.empty(word.toString().trim())){
	                            		use = word.toString().trim();
	                            		if(use.equalsIgnoreCase("INDIVIDUAL")) {
	                            			vehicle.setUse(new TypeUseDataType("persistence.datatype.typeuse.individual"));   
	                            		}else if (use.equalsIgnoreCase("INSTITUTIONAL") || use.equalsIgnoreCase("INSTITUCIONAL")){
	                            			vehicle.setUse(new TypeUseDataType("persistence.datatype.typeuse.institutional"));   
	                            		}else{
	                            			session.setAttribute("Msg", 
		                            				  messages.getText("vehicle.messages.upload.wronguse")
		                            				  +". "+messages.getText("vehicle.messages.upload.linenumber")+" "+lineNumber);
	                            			System.out.println("Upload Vehiculos: error en use->"+use +"_linea->"+lineNumber);
	                            			return mapping.findForward("error");
	                            		}
	                            	}else{
	                            		  session.setAttribute("Msg", 
	                            				  messages.getText("vehicle.messages.upload.wronguse")
	                            				  +". "+messages.getText("vehicle.messages.upload.linenumber")+" "+lineNumber);
	                            		  System.out.println("Upload Vehiculos: error en use->"+use +"_linea->"+lineNumber);
	                            	  	  return mapping.findForward("error");
	                            	}
	                                break;
	                            case 1:
	                            	// First Name
	                            	 if (!Util.empty(word.toString().trim())){
	                            		 firstName = word.toString().trim();
	                            	 }else{
	                            		 if (use.equalsIgnoreCase("INDIVIDUAL")){
	                            			 // Si es individual necesito el nombre
		                            		  session.setAttribute("Msg", 
		                            				  messages.getText("driver.messages.upload.wrongname")
		                            				  +". "+messages.getText("driver.messages.upload.linenumber")+" "+lineNumber);
		                            	  	  return mapping.findForward("error");
	                            		 }
	                            	 }
	                                 break;
	                            case 2:
	                            	// Last Name:
	                            	if (!Util.empty(word.toString().trim())){
	                            		try{
			                                if(vehicle.getUse().getResourceBundleId().
			                                		equals("persistence.datatype.typeuse.individual")) {
			                                    lastName = word.toString().trim();
			                                    ListResult list = sys.getDriverReportFacade().advancedSearch(null,firstName,lastName,null,null,null,0,-1,DriverData.ALL,0,sys.getUserSessionData());
			                                    switch(list.getTotal()) {
			                                        case 0:
			                                            //throw new Exception("Conductor desconocido :"+firstName+" "+lastName);
			                                            session.setAttribute("Msg", 			                                        			
				  	                            				  messages.getText("vehicle.messages.upload.unknowndriver")+firstName+" "+lastName
				  	                            				  +". "+messages.getText("driver.messages.upload.linenumber")+" "+lineNumber);
			                                        	return mapping.findForward("error");
			                                        case 1:
			                                            vehicle.setDriver(new DriverData(((DriverMainAttributes)list.getElements().iterator().next()).getId()));
			                                            break;
			                                        default:
			                                        	// Un Conductor puede tener varios vehiculos asignados:
			                                        	vehicle.setDriver(new DriverData(((DriverMainAttributes)list.getElements().iterator().next()).getId()));
			                                        	break;
			                                    }
			                                }
	                            		}catch (Exception e){
	                            			session.setAttribute("Msg", 
		                            				  messages.getText("driver.messages.upload.wronglastname")+": "+firstName+" "+lastName
		                            				  +". "+messages.getText("driver.messages.upload.linenumber")+" "+lineNumber);
		                            	  	return mapping.findForward("error");
	                            		}
	                            	}else{
	                            		if (use.equalsIgnoreCase("INDIVIDUAL")){
		                            		 session.setAttribute("Msg", 
		                            				  messages.getText("driver.messages.upload.wronglastname")
		                            				  +". "+messages.getText("driver.messages.upload.linenumber")+" "+lineNumber);
		                            	  	 return mapping.findForward("error");
	                            		}
	                            	}
	                                break;
	                            case 3:
	                            	// Region:
	                            	if (word.length() > 0){
                            			  region = word.toString().trim();
                            		}else{
                            			  session.setAttribute("Msg", 
                            					  messages.getText("driver.messages.upload.wrongregion")
                            					  +". \\n"+messages.getText("driver.messages.upload.linenumber")+": "+lineNumber);
                            			  return mapping.findForward("error");    
                            		}
	                                break;
	                                
	                            case 4:
	                            	// Pais:
	                            	
	                            	if (word.length()>0){
                           			  String countryName = word.toString().trim();
                           			  System.out.println("Country: "+countryName);
                           			  GeoLabelData country = Util.getCountry(
                           					  groupLines,region,countryName,sys.getText());
                           			  if(country != null) {
                           				  vehicle.setCountry(country);
                           			  }else {
                           				  session.setAttribute("Msg", 
                           						  messages.getText("driver.messages.upload.wrongcountry")+region+" "+countryName
                           						  +". "+messages.getText("driver.messages.upload.linenumber")+": "+lineNumber);
                           				  return mapping.findForward("error");   
                           				  // throw new Exception("Región/País no encontrado: "+region+"/"+word.toString());
                           			  }
                           		  	}else{
                           			  session.setAttribute("Msg", 
                           					  messages.getText("driver.messages.upload.wrongcountry")
                           					  +": \\n"+messages.getText("driver.messages.upload.linenumber")+": "+lineNumber);
                           			  return mapping.findForward("error");    
                           		  	}
	                            	break;
	                            	
	                            	
	                            	
	                           
	                          // DivOrg 1:
                              case 5:
                            	  if (word.toString().trim().length() > 0){
                            		  divOrgName11 = word.toString().trim();
                            	  }
                                  break;
                              case 6:
                            	  if (word.toString().trim().length()>0){
                            		  divOrgName12 = word.toString().trim();
                            	  }
                                  break;
                              case 7:
                            	  try{
                            		  if (word.toString().trim().length()>0){
                            			  divOrgName13 = word.toString().trim();
                            		  }
                            		  
                            		  // Divisiones Organizacionales:
                            		  // DiOrg 1
                            		  LabelItemData divOrg1 = Util.getDivOrg(groupLines,0,
                            				  divOrgName11,divOrgName12, divOrgName13);
                            		  if(divOrg1 != null) {
                            			  divOrgs.add(new DivOrgData(sys.getUserSessionData().getFilter().getDivOrgsAttrs()[0].getId(),null,divOrg1));
                            		  }else {
                            			  session.setAttribute("Msg", 
                            					  messages.getText("driver.messages.upload.wrongdivorg")+": "+
                            					  ((divOrgName11 != null)?divOrgName11+"/":"")+
                            					  ((divOrgName12 != null)?divOrgName12+"/":"")+
                            					  ((divOrgName13 != null)?divOrgName13:"")+ 
                            					  ". \\n"+
                            					  messages.getText("driver.messages.upload.linenumber")+": "+lineNumber);
                            			  System.out.println("DivOrg1 No encontrada");
                            			  return mapping.findForward("error");       
                            			  // throw new Exception("División organizacional no encontrada: "+word.toString());
                            		  }
                            	  }catch(Exception e){                            		  
                            		  session.setAttribute("Msg", 
                        					  messages.getText("driver.messages.upload.wrongdivorg")+": "+
                        					  ((divOrgName11 != null)?divOrgName11+"/":"")+
                        					  ((divOrgName12 != null)?divOrgName12+"/":"")+
                        					  ((divOrgName13 != null)?divOrgName13:"")+ 
                        					  ". \\n"+
                        					  messages.getText("driver.messages.upload.linenumber")+": "+lineNumber);
                        			  System.out.println("DivOrg1 No encontrada");
                        			  return mapping.findForward("error"); 
                            	  }
                                  
                            	  break;
                            	  
                              // DivOrg2:
                              case 8:
                            	  if (word.toString().trim().length() > 0){
                            		  divOrgName21 = word.toString().trim();
                            	  }
                                  break;
                              case 9:
                            	  if (word.toString().trim().length() > 0){
                            		  divOrgName22 = word.toString().trim();
                            	  }
                                  break;
                              case 10:
                            	  try{
                            		  if (word.toString().trim().length() > 0){
                            			  divOrgName23 = word.toString().trim();
                            		  }
                            		  LabelItemData divOrg2 = Util.getDivOrg(groupLines,1,divOrgName21,divOrgName22,divOrgName23);
                            		  if(divOrg2 != null) {
                            			  divOrgs.add(new DivOrgData(sys.getUserSessionData().getFilter().getDivOrgsAttrs()[1].getId(),null,divOrg2));
                            		  }else {
                            			  if (divOrgName21 != null || divOrgName22 != null || divOrgName23 != null){
                            				  // Si alguno de los items es != null esta mal la division organizacional
                            				  // Si los 3 son null y divOrg3 es null es porque no tiene este boxla empresa.
	                            			  System.out.println("DivOrg2 No encontrada");
	                            			  session.setAttribute("Msg", 
	                                				  messages.getText("driver.messages.upload.wrongdivorg")+": "+
	                                				  ((divOrgName21 != null)?divOrgName21+"/":"")+
	                                				  ((divOrgName22 != null)?divOrgName22+"/":"")+
	                                				  ((divOrgName23 != null)?divOrgName23:"")+ 
	                                				  ". \\n"+
	                                				  messages.getText("driver.messages.upload.linenumber")+": "+lineNumber);
	                                	  	  return mapping.findForward("error");   
                            			  }
                            		  }
                            	  }catch(Exception e){
                            		  System.out.println("DivOrg2 No encontrada");
                        			  session.setAttribute("Msg", 
                            				  messages.getText("driver.messages.upload.wrongdivorg")+": "+
                            				  ((divOrgName21 != null)?divOrgName21+"/":"")+
                            				  ((divOrgName22 != null)?divOrgName22+"/":"")+
                            				  ((divOrgName23 != null)?divOrgName23:"")+ 
                            				  ". \\n"+
                            				  messages.getText("driver.messages.upload.linenumber")+": "+lineNumber);
                            	  	  return mapping.findForward("error");   
                            	  }
                                  break;
                                  
                              case 11:
                            	  if (word.toString().trim().length() > 0){
                            		  divOrgName31 = word.toString().trim();
                            	  }
                                  break;
                              case 12:
                            	  if (word.toString().trim().length() > 0){
                            		  divOrgName32 = word.toString().trim();
                            	  }
                            	  break;
                              case 13:
                            	  
                            	 try{
                            		  if (word.toString().trim().length() > 0){
                            			  divOrgName33 = word.toString().trim();
                            		  }                            	  
                            		  LabelItemData divOrg3 = Util.getDivOrg(groupLines,2,divOrgName31,
                            				  divOrgName32, divOrgName33);
                            		  if(divOrg3 != null) {
                            			  divOrgs.add(new DivOrgData(sys.getUserSessionData().getFilter().getDivOrgsAttrs()[2].getId(),null,divOrg3));
                            		  }else {
                            			  if (divOrgName31 != null || divOrgName32 != null || divOrgName33 != null){
                            				  // Si alguno de los items es != null esta mal la division organizacional
                            				  // Si los 3 son null y divOrg3 es null es porque no tiene este boxla empresa.
                            				  session.setAttribute("Msg", 
                            					  messages.getText("driver.messages.upload.wrongdivorg")+": "+
                            					  ((divOrgName31 != null)?divOrgName31+"/":"")+
                            					  ((divOrgName32 != null)?divOrgName32+"/":"")+
                            					  ((divOrgName33 != null)?divOrgName33:"")+
                            					  ". \\n"+messages.getText("driver.messages.upload.linenumber")+": "+lineNumber);
                            				  System.out.println("DivOrg3 No encontrada");
                            			  	  return mapping.findForward("error");                                 			  
                            			  }
                            		  }
                            	  }catch(Exception e){
                            		  session.setAttribute("Msg", 
                        					  messages.getText("driver.messages.upload.wrongdivorg")+": "+
                        					  ((divOrgName31 != null)?divOrgName31+"/":"")+
                        					  ((divOrgName32 != null)?divOrgName32+"/":"")+
                        					  ((divOrgName33 != null)?divOrgName33:"")+
                        					  ". \\n"+messages.getText("driver.messages.upload.linenumber")+": "+lineNumber);
                        			  System.out.println("DivOrg3 No encontrada");
                        			  return mapping.findForward("error");                                 			  
                            	  }
                                  break;
	                           
	                            
	                            case 14:
	                            	// Tipo de Vehiculo:	Campo Obligatorio                               
	                            	if (word.toString().trim().length() > 0){
		                            	String type = word.toString().trim();
		                                vehicle.setDivOrgs(divOrgs);
		                                if (type.equalsIgnoreCase("Automóvel") || 
		                                		type.equalsIgnoreCase("AutomÃ³vel") ||
		                                		type.equalsIgnoreCase("Automovil") ||
		                                		type.equalsIgnoreCase("Automóvil") ||
		                                		type.equalsIgnoreCase("Automobile") 	) {
		                                    vehicle.setType(new TypeVehicleDataType("datatype.vehicletype.automobile"));
		                                }else if (type.equalsIgnoreCase("pick up") ) {
		                                    vehicle.setType(new TypeVehicleDataType("datatype.vehicletype.pickup"));
		                                }else if (type.equalsIgnoreCase("pick up média") ) {
		                                    vehicle.setType(new TypeVehicleDataType("datatype.vehicletype.pickuplow"));
		                                }else if (type.equalsIgnoreCase("pick up leve") ) {
		                                    vehicle.setType(new TypeVehicleDataType("datatype.vehicletype.pickupmiddle"));
		                                }else if (type.equalsIgnoreCase("Utilitário")  || 
		                                		type.equalsIgnoreCase("UtilitÃ¡rio") ||
		                                		type.equalsIgnoreCase("Utility") ||
		                                		type.equalsIgnoreCase("Utilitario")) {
		                                    vehicle.setType(new TypeVehicleDataType("datatype.vehicletype.utility"));
		                                }else if (type.equalsIgnoreCase("Bus") ||
		                                		type.equalsIgnoreCase("Autobus") ||
		                                		type.equalsIgnoreCase("Ônibus") ||
		                                		type.equalsIgnoreCase("Onibus") ||
		                                		type.equalsIgnoreCase("Omnibus")){
		                                	 vehicle.setType(new TypeVehicleDataType("datatype.vehicletype.bus"));		                                	
		                                }else if (type.equalsIgnoreCase("Bicycle") ||
		                                		type.equalsIgnoreCase("Bicicleta")){
		                                	 vehicle.setType(new TypeVehicleDataType("datatype.vehicletype.bycycle"));		                                	
		                                }else if (type.equalsIgnoreCase("Minibus") ||
		                                		type.equalsIgnoreCase("MicroÔnibus") ||
		                                		type.equalsIgnoreCase("Microonibus")){
		                                	 vehicle.setType(new TypeVehicleDataType("datatype.vehicletype.minibus"));
		                                }else if(type.equalsIgnoreCase("Minivan") ||
		                                		type.equalsIgnoreCase("Mini Van") ||
		                                		type.equalsIgnoreCase("Mini-Van")){
		                                	 vehicle.setType(new TypeVehicleDataType("datatype.vehicletype.minivan"));		                                			                                	 
		                                }else if(type.equalsIgnoreCase("Motorcycle") ||
		                                		type.equalsIgnoreCase("Moto") ||
		                                		type.equalsIgnoreCase("Motocicleta")){
		                                	 vehicle.setType(new TypeVehicleDataType("datatype.vehicletype.motorcycle"));		                                	
		                                }else if(type.equalsIgnoreCase("No Data") ||
		                                		type.equalsIgnoreCase("Sin Datos") ||
		                                		type.equalsIgnoreCase("Sem Dados")){
		                                	 vehicle.setType(new TypeVehicleDataType("datatype.vehicletype.nodata"));		                                	
		                                }else if (type.equalsIgnoreCase("Other") ||
		                                		type.equalsIgnoreCase("Otros") ||
		                                		type.equalsIgnoreCase("Outros")){
		                                	 vehicle.setType(new TypeVehicleDataType("datatype.vehicletype.other"));		                                	
		                                }else if(type.equalsIgnoreCase("Pickup & Trailer") ||
		                                		type.equalsIgnoreCase("Pick up & Trailer") ||
		                                		type.equalsIgnoreCase("Caminhonete & Reboque")){
		                                	 vehicle.setType(new TypeVehicleDataType("datatype.vehicletype.pick"));		                                	
		                                }else if(type.equalsIgnoreCase("Pickup Truck") ||
		                                		type.equalsIgnoreCase("Camioneta") ||
		                                		type.equalsIgnoreCase("Caminhonete")){
		                                	 vehicle.setType(new TypeVehicleDataType("datatype.vehicletype.pickup"));
		                                	
		                                }else if(type.equalsIgnoreCase("Tractor Trailer") ||
		                                		type.equalsIgnoreCase("Camión con remolque") ||
		                                		type.equalsIgnoreCase("Camion con remolque") ||
		                                		type.equalsIgnoreCase("Caminhão c/reboque") ||
		                                		type.equalsIgnoreCase("Caminhão con reboque") ||
		                                		type.equalsIgnoreCase("Caminhao c/reboque") ||
		                                		type.equalsIgnoreCase("Caminhao con reboque")){
		                                	 vehicle.setType(new TypeVehicleDataType("datatype.vehicletype.tractortrailer"));
		                                }else if(type.equalsIgnoreCase("Tractor") ||
		                                		type.equalsIgnoreCase("Trator")){
		                                	 vehicle.setType(new TypeVehicleDataType("datatype.vehicletype.trator"));
		                                }else if(type.equalsIgnoreCase("Truck") ||
		                                		type.equalsIgnoreCase("Camión") ||
		                                		type.equalsIgnoreCase("Camion") ||
		                                		type.equalsIgnoreCase("Caminhão") ||
		                                		type.equalsIgnoreCase("Caminhao")){
		                                	 vehicle.setType(new TypeVehicleDataType("datatype.vehicletype.truck"));
		                                	
		                                }else if(type.equalsIgnoreCase("SUV")  ||
		                                		type.equalsIgnoreCase("Utilitarios") ||
		                                		type.equalsIgnoreCase("Utilitario") ||
		                                		type.equalsIgnoreCase("Utility") ||
		                                		type.equalsIgnoreCase("Utilitário esportivo") ||
		                                		type.equalsIgnoreCase("Utilitario esportivo")){
		                                	 vehicle.setType(new TypeVehicleDataType("datatype.vehicletype.utility"));

		                                }else if(sys.getCorporationId() == CorporationData.MONTES_DE_PLATA
		                                		&& (type.equalsIgnoreCase("Maquinaria Vial"))){
		                                	 vehicle.setType(new TypeVehicleDataType("datatype.vehicletype.ambulance"));
		                                }else if(type.equalsIgnoreCase("Ambulancia")
		                                		|| type.equalsIgnoreCase("Ambulance")
		                                		|| type.equalsIgnoreCase("Ambulância")){
		                                	 vehicle.setType(new TypeVehicleDataType("datatype.vehicletype.ambulance"));
		                                }else if(type.equalsIgnoreCase("Semi Reboque") ||
		                                		type.equalsIgnoreCase("Semi Remolque")){
		                                	 vehicle.setType(new TypeVehicleDataType("datatype.vehicletype.semireboque"));
		                                }else if(type.equalsIgnoreCase("Caminhão com Semi Reboque") ||
		                                		type.equalsIgnoreCase("Caminhao com Semi Reboque") ||
		                                		type.equalsIgnoreCase("Camión con Semi Remolque") ||
		                                		type.equalsIgnoreCase("Camion con Semi Remolque")){
		                                	 vehicle.setType(new TypeVehicleDataType("datatype.vehicletype.vansemireboque"));
		                                }else if(type.equalsIgnoreCase("VAN") ||
		                                		type.equalsIgnoreCase("Furgão") ||
		                                		type.equalsIgnoreCase("Furgao")){
		                                	 vehicle.setType(new TypeVehicleDataType("datatype.vehicletype.van"));
		                                }else {
		                                	session.setAttribute("Msg", 
                          						  messages.getText("driver.messages.upload.wrongvehicletype")+word.toString()
                          						  +". \\n"+messages.getText("driver.messages.upload.linenumber")+": "+lineNumber);
                          				  	return mapping.findForward("error");                              				  
		                                }
	                            	}else{
	                            		session.setAttribute("Msg", 
                      						  messages.getText("driver.messages.upload.wrongvehicletype")+word.toString()
                      						  +". \\n"+messages.getText("driver.messages.upload.linenumber")+": "+lineNumber);
	                            		return mapping.findForward("error");    
	                            	}
	                                break;
	                                
	                                
	                            case 15:
	                            	// Matricula: Campo Obligatorio
	                                if(word.toString().trim().length() > 0) {
	                                	if (sys.getUserSessionData().getFilter().getCorporationId().
	                        					equals(new Integer(CorporationData.KLABIN))){
	                                	
	                                		// En KLABIN la matricula se guarda en el campo prefix de
		                                	// los campos opcionales.
		                                	optionalInfo.setPrefix(word.toString().trim());
	                                	}else{
	                                		// Por si usamos esta funcion para otra empresa
	                                		// que no sea klabin
	                                		//vehicle.setMatricula(word.toString().trim());
	                                		//Matricula solo alfanumerico:
	                                		vehicle.setMatricula(VehicleUtil.getCleanMatricula(word.toString()));
	                                	}
	                                	
	                                }else{
	                                	session.setAttribute("Msg", 
	                      						  messages.getText("vehicle.messages.upload.wrongplate")+word.toString().trim()
	                      						  +". \\n"+messages.getText("driver.messages.upload.linenumber")+": "+lineNumber);
		                            	return mapping.findForward("error");    
	                                }
	                                break;
	                                
	                            case 16:
	                            	// Centro de Costos
	                                if(word.toString().trim().length() > 0) {
	                                    vehicle.setCostCenter(word.toString().trim());
	                                }
	                                break;
	                                
	                            case 17:
	                            	// Numero de Permiso o Registro
	                                if(word.toString().trim().length() > 0) {
	                                    vehicle.setPermissionNumber(word.toString().trim());
	                                }
	                                break;
	                                
	                            case 18:
	                            	// Fecha de Vencimiento del Permiso
	                                if(word.toString().trim().length() > 0) {
	                                	try{
	                                		vehicle.setPermissionExpiry(Util.getDate(word.toString().trim()).getTime());
	                                	}catch(Exception e){
	                                		session.setAttribute("Msg", 
		                      						  messages.getText("vehicle.messages.upload.wrongpermissionexpiry")+word.toString().trim()
		                      						  +". \\n"+messages.getText("driver.messages.upload.linenumber")+": "+lineNumber);
			                            	return mapping.findForward("error");  
	                                	}
	                                }
	                                break;
	                                
	                            case 19:
	                            	// Fecha de Compra
	                                if(word.toString().trim().length() > 0) {
	                                	try{
	                                		vehicle.setPurchaseDate(Util.getDate(word.toString().trim()).getTime());
	                                	}catch(Exception e){
	                                		session.setAttribute("Msg", 
		                      						  messages.getText("vehicle.messages.upload.wrongpurchasedate")+word.toString().trim()
		                      						  +". \\n"+messages.getText("driver.messages.upload.linenumber")+": "+lineNumber);
			                            	return mapping.findForward("error");  
	                                	}
	                                }
	                                break;
	                                
	                            case 20:
	                            	// OwnerShip
	                                if(word.toString().trim().length() > 0) {
	                                	String owner = word.toString().trim();
	                                    if(owner.equalsIgnoreCase("Company Car") ||
	                                    		owner.equalsIgnoreCase("Vehículo de la Empresa") ||
	                                    		owner.equalsIgnoreCase("vehiculo de la empresa")) {
	                                        vehicle.setOwnerShip(new TypeCenterCostDataType("datatype.ownership.companycar"));
	                                        
	                                    }else if (owner.equalsIgnoreCase("leasing")){
	                                    	vehicle.setOwnerShip(new TypeCenterCostDataType("datatype.ownership.leasing"));
	                                    	
	                                    }else if (owner.equalsIgnoreCase("owner") ||
	                                    		owner.equalsIgnoreCase("employee-owned") ||
	                                    		owner.equalsIgnoreCase("propio") ||
	                                    		owner.equalsIgnoreCase("próprio")){
	                                    	vehicle.setOwnerShip(new TypeCenterCostDataType("datatype.ownership.owner"));
	                                    	
	                                    }else if (owner.equalsIgnoreCase("withoutdata") ||
	                                    		owner.equalsIgnoreCase("no data") ||
	                                    		owner.equalsIgnoreCase("sin datos") ||
	                                    		owner.equalsIgnoreCase("sem dados")){
	                                    	vehicle.setOwnerShip(new TypeCenterCostDataType("datatype.ownership.withoutdata"));
	                                    	
	                                    }else if (owner.equalsIgnoreCase("contract") ||
	                                    		owner.equalsIgnoreCase("contractor") ||
	                                    		owner.equalsIgnoreCase("contratista") ||
	                                    		owner.equalsIgnoreCase("contratado")){
	                                    	vehicle.setOwnerShip(new TypeCenterCostDataType("datatype.ownership.contract"));
	                                    	
	                                    }else{
	                                    	session.setAttribute("Msg", 
		                      						  messages.getText("vehicle.messages.upload.wrongOwnerShip")+word.toString()
		                      						  +". \\n"+messages.getText("driver.messages.upload.linenumber")+": "+lineNumber);
			                            	return mapping.findForward("error");   
	                                    }
	                                }
	                                break;
	                                
	                            case 21:
	                            	// Marca: CAMPO OBLIGATORIO
	                                if(word.toString().trim().length() > 0) {
	                                		                                	
	                                	DataTypeReportFacade DTReportFacade = sys.getDataTypeReportFacade();
	                           			Iterator iter = DTReportFacade.findAll(DataTypeConstants.TYPETRADE,sys.getUserSessionData()).iterator();
	                           			boolean find = false;
	                        			while(iter.hasNext() && !find) {
	                        				String value = ((TypeTradeDataType)iter.next()).getResourceBundleId();
	                        				
	                        				if (word.toString().trim().equalsIgnoreCase(messages.getText(value))){
	    	                                    vehicle.setTrade(new TypeTradeDataType(value));
	    	                                    find = true;
	                        				}
	                        			}
	                        			if (!find){
	                        				// Marca desconocida:
	                        				session.setAttribute("Msg", 
		                      						  messages.getText("vehicle.messages.upload.wrongTrade")+word.toString().trim()
		                      						  +". \\n"+messages.getText("driver.messages.upload.linenumber")+": "+lineNumber);
			                            	return mapping.findForward("error");  
	                        			}
	                                }else{
	                                	// Marca desconocida:
                        				session.setAttribute("Msg", 
	                      						  messages.getText("vehicle.messages.upload.wrongTrade")+word.toString().trim()
	                      						  +". \\n"+messages.getText("driver.messages.upload.linenumber")+": "+lineNumber);
		                            	return mapping.findForward("error");  
	                                }
	                                break;
	                                
	                            case 22:
	                            	// Modelo: // CAMPO OBLIGATORIO
	                                if(word.toString().trim().length() > 0) {	                                	
	                                    vehicle.setModel(word.toString().trim());
	                                }else{
	                                	// Marca desconocida:
                        				session.setAttribute("Msg", 
	                      						  messages.getText("vehicle.messages.upload.wrongModel")+word.toString()
	                      						  +". \\n"+messages.getText("driver.messages.upload.linenumber")+": "+lineNumber);
		                            	return mapping.findForward("error");  
	                                }
	                                break;
	                                
	                            case 23:
	                            	// Año:
	                            	    if(word.toString().trim().length() > 0) {
		                                    try {
		                                        vehicle.setYear(new Integer(word.toString().trim()));
		                                    }catch(Exception e) {
		                                    	e.printStackTrace();
		                                    	session.setAttribute("Msg", 
			                      						  messages.getText("vehicle.messages.upload.wrongYear")+word.toString().trim()
			                      						  +". \\n"+messages.getText("driver.messages.upload.linenumber")+": "+lineNumber);
				                            	return mapping.findForward("error");   
		                                    }
		                                }else{
		                                	session.setAttribute("Msg", 
		                      						  messages.getText("vehicle.messages.upload.wrongYear")+word.toString().trim()
		                      						  +". \\n"+messages.getText("driver.messages.upload.linenumber")+": "+lineNumber);
			                            	return mapping.findForward("error");   
		                                }	                            	
	                                
	                                break;
	                                
	                                
	                            case 24:
	                            	// Colour
	                                if(word.toString().trim().length() > 0) {
	                                    vehicle.setColour(word.toString().trim());
	                                }
	                                break;
	                                
	                            case 25:
	                            	// Numero de Chasis
	                                if(word.toString().trim().length() > 0) {
	                                    vehicle.setChasisNumber(word.toString().trim());
	                                }
	                                break;
	                                
	                            case 26:
	                                if(word.toString().trim().length() > 0) {
	                                	String motor = word.toString().trim();
	                                	if (motor.equalsIgnoreCase("Normal")){
	                                		vehicle.setEngine(new TypeEngineDataType("datatype.engine.normal"));
	                                	}else if (motor.equalsIgnoreCase("Turbo")){
	                                		vehicle.setEngine(new TypeEngineDataType("datatype.engine.turbo"));
	                                	}else if (motor.equalsIgnoreCase("Injection") ||
	                                			motor.equalsIgnoreCase("Inyeccion") ||
	                                			motor.equalsIgnoreCase("Inyección") ||
	                                			motor.equalsIgnoreCase("Injeção") ||
	                                			motor.equalsIgnoreCase("Injecao")){
	                                		vehicle.setEngine(new TypeEngineDataType("datatype.engine.injection"));
	                                	}else if (motor.equalsIgnoreCase("other") ||
	                                			motor.equalsIgnoreCase("otros") ||
	                                			motor.equalsIgnoreCase("otro") ||
	                                			motor.equalsIgnoreCase("outros")){
	                                		vehicle.setEngine(new TypeEngineDataType("datatype.engine.other"));
	                                	}else{
		                                    //throw new Exception("Motor desconocido :"+word.toString());
		                                    //vehicle.setEngine(word.toString());		                                    
		                                    session.setAttribute("Msg", 
		                      						  messages.getText("vehicle.messages.upload.wrongEngine")+word.toString()
		                      						  +". \\n"+messages.getText("driver.messages.upload.linenumber")+": "+lineNumber);
			                            	return mapping.findForward("error");   
	                                	}	                                		
	                                }
	                                break;
	                                
	                            case 27:
	                            	// Tipo de Combustible
	                                if(word.toString().trim().length() > 0) {
	                                	String fuel = word.toString().trim();
	                                	if (fuel.equalsIgnoreCase("alcohol") ||
	                                			fuel.equalsIgnoreCase("alconafta") ||
	                                			fuel.equalsIgnoreCase("Álcool") ||
	                                			fuel.equalsIgnoreCase("Alcool")){
	                                		vehicle.setFuel(new TypeFuelDataType("datatype.fuel.alcohol"));
	                                	}else if (fuel.equalsIgnoreCase("alcohol/gasoline") ||
	                                			fuel.equalsIgnoreCase("alcohol/gasolina") ||
	                                			fuel.equalsIgnoreCase("Álcool/Gasolina") ||
	                                			fuel.equalsIgnoreCase("alcool/gasolina")){
	                                		vehicle.setFuel(new TypeFuelDataType("datatype.fuel.alcoholgaso"));
	                                	}else if (fuel.equalsIgnoreCase("Diesel") ||
	                                			fuel.equalsIgnoreCase("Gasoil") ){
	                                		vehicle.setFuel(new TypeFuelDataType("datatype.fuel.diesel"));
	                                	}else if (fuel.equalsIgnoreCase("gasoline") ||
	                                			fuel.equalsIgnoreCase("gasolina")){
	                                		vehicle.setFuel(new TypeFuelDataType("datatype.fuel.gasoline"));
	                                	}else if (fuel.equalsIgnoreCase("lpg") ||
	                                			fuel.equalsIgnoreCase("glp")){
	                                		vehicle.setFuel(new TypeFuelDataType("datatype.fuel.glp"));
	                                	}else if (fuel.equalsIgnoreCase("ngv") ||
	                                			fuel.equalsIgnoreCase("gnc") ||
	                                			fuel.equalsIgnoreCase("gnv")){
	                                		vehicle.setFuel(new TypeFuelDataType("datatype.fuel.ngv"));
	                                	}else if (fuel.equalsIgnoreCase("no data") ||
	                                			fuel.equalsIgnoreCase("sin datos") ||
	                                			fuel.equalsIgnoreCase("sem dados")){
	                                		vehicle.setFuel(new TypeFuelDataType("datatype.fuel.nodata"));
	                                	}else if (fuel.equalsIgnoreCase("Other") ||
	                                			fuel.equalsIgnoreCase("otro") ||
	                                			fuel.equalsIgnoreCase("outro")){
	                                		vehicle.setFuel(new TypeFuelDataType("datatype.fuel.other"));
	                                	}else{
	                                		// throw new Exception("Tipo de combustible desconocido :"+word.toString());
	                                		//vehicle.setFuel(word.toString());
	                                		session.setAttribute("Msg", 
		                      						  messages.getText("vehicle.messages.upload.wrongFuel")+word.toString()
		                      						  +". \\n"+messages.getText("driver.messages.upload.linenumber")+": "+lineNumber);
			                            	return mapping.findForward("error"); 
	                                	}
	                                }
	                                break;
	                                
	                            case 28:
	                            	// Capacidad del Tanque del Nafta
	                                if(word.toString().trim().length() > 0) {
	                                    try {
	                                        vehicle.setTankCapacity(new Integer(word.toString().trim()));
	                                    }catch(Exception e) {
	                                        // throw new Exception("Capacidad del tanque incorrecta: "+word.toString());
	                                        session.setAttribute("Msg", 
		                      						  messages.getText("vehicle.messages.upload.wrongtankcapacity")+word.toString()
		                      						  +". \\n"+messages.getText("driver.messages.upload.linenumber")+": "+lineNumber);
			                            	return mapping.findForward("error"); 
	                                    }
	                                }
	                                break;
	                                
	                            case 29:
	                            	// Unidad de Volumen de la Nafta: Litros, etc
	                                if(word.toString().trim().length() > 0) {
	                                	String unit = word.toString().trim();
	                                	if (unit.equalsIgnoreCase("Gallons") ||
	                                		unit.equalsIgnoreCase("galones") ||
	                                		unit.equalsIgnoreCase("Galões") ||
	                                		unit.equalsIgnoreCase("galoes")){
	                                		vehicle.setTankUnit(new TypeVolumeUnitDataType("datatype.volumeunit.gallons"));
	                                	}else if (unit.equalsIgnoreCase("liters") ||
	                                			unit.equalsIgnoreCase("litros")){
	                                		vehicle.setTankUnit(new TypeVolumeUnitDataType("datatype.volumeunit.liters"));
	                                	}else if (unit.equalsIgnoreCase("No Data") ||
	                                			unit.equalsIgnoreCase("Sin Datos") ||
	                                			unit.equalsIgnoreCase("Sem Dados")){
	                                		vehicle.setTankUnit(new TypeVolumeUnitDataType("datatype.volumeunit.nodata"));
	                                	}else{
	                                		session.setAttribute("Msg", 
		                      						  messages.getText("vehicle.messages.upload.wrongtankunit")+word.toString()
		                      						  +". \\n"+messages.getText("driver.messages.upload.linenumber")+": "+lineNumber);
			                            	return mapping.findForward("error"); 
	                                	}
	                                }
	                                break;
	                                
	                            case 30:
	                            	// Tipo de Cubierta
	                                if(word.toString().trim().length() > 0) {
	                                    vehicle.setCover(new TypeCoverDataType(word.toString().trim()));
	                                }
	                                break;
	                                
	                            case 31:
	                            	// Unidad del Odometro: Km, etc...
	                                if(word.length() > 0) {
	                                	String unit = word.toString().trim();
	                                    if(unit.equalsIgnoreCase("Kilometros") ||
	                                    	unit.equalsIgnoreCase("Kilometers")	||
	                                    	unit.equalsIgnoreCase("Quilômetros") ||
	                                    	unit.equalsIgnoreCase("quilometros")) {
	                                        vehicle.setOdometerUnit(new TypeDistanceUnitDataType("datatype.distanceunit.kilometers"));
	                                    }else if (unit.equalsIgnoreCase("miles") ||
	                                    		unit.equalsIgnoreCase("millas") ||
	                                    		unit.equalsIgnoreCase("milhas")){
	                                    	vehicle.setOdometerUnit(new TypeDistanceUnitDataType("datatype.distanceunit.miles"));
	                                    }else{
	                                    	session.setAttribute("Msg", 
		                      						  messages.getText("vehicle.messages.upload.wrongodometer")+word.toString()
		                      						  +". \\n"+messages.getText("driver.messages.upload.linenumber")+": "+lineNumber);
			                            	return mapping.findForward("error"); 
	                                        // throw new Exception("Odometro desconcido :"+word.toString());
	                                    }
	                                }
	                                break;
	                                
	                            case 32:
	                            	// ABS
	                                if(word.toString().trim().length() > 0) {
	                                	String abs = word.toString().trim();	                                	
	                                    vehicle.setAbs(abs.equals("1") || abs.equalsIgnoreCase("yes") ||
	                                    		abs.equalsIgnoreCase("si") || abs.equalsIgnoreCase("sim"));
	                                }
	                                break;
	                            case 33:
	                            	// Air Bag
	                                if(word.toString().trim().length() > 0) {
	                                	String airBag = word.toString().trim();
	                                    vehicle.setAirBag(airBag.equals("1") || airBag.equalsIgnoreCase("yes") ||
	                                    		airBag.equalsIgnoreCase("si") || airBag.equalsIgnoreCase("sim"));
	                                }
	                                break;
	                                
	                            case 34:
	                            	// Aire Acondicionado
	                                if(word.toString().trim().length() > 0) {
	                                	String aire = word.toString().trim();
	                                    vehicle.setAireAcondicionado(aire.equals("1") || aire.equalsIgnoreCase("yes") ||
	                                    		aire.equalsIgnoreCase("si") || aire.equalsIgnoreCase("sim"));
	                                }
	                                break;

	                            /* Optional Info Klabin y Yoli*/    
	                            case 35:
	                            	
	                            	// KLABIN - PREFIJO (se guarda como matricula)
	                            	if (sys.getUserSessionData().getFilter().getCorporationId().
                        					equals(new Integer(CorporationData.KLABIN))){
	                            		
	                            		// Leo el prefijo que lo pongo en el campo matricula
	                            		// del vehiculo para Klabin.
	                            		if(word.toString().trim().length() > 0) {
	                            				//vehicle.setMatricula(word.toString().trim());
	                            			//Matricula solo alfanumerico:
	                            				vehicle.setMatricula(VehicleUtil.getCleanMatricula(word.toString()));
	                            			// Por si usamos este script para otra empresa
	                            			// que no sea klabin:
	                            			//optionalInfo.setPrefix(word.toString().trim());
	                            		}else{
	                            			session.setAttribute("Msg", 
	                      						  messages.getText("vehicle.messages.upload.wrongprefix")+word.toString().trim()
	                      						  +". \\n"+messages.getText("driver.messages.upload.linenumber")+": "+lineNumber);
	                            			return mapping.findForward("error");    
	                            		}
	                            	}else {
	                            		// YOLI - PREFIX (a optional INFO)
	                            		if (sys.getUserSessionData().getFilter().getCorporationId().
                        					equals(new Integer(CorporationData.YOLI))){
	                            			if(word.toString().trim().length() > 0) {
	                            				optionalInfo.setPrefix(word.toString().trim());
	                            			}else{
	                            				session.setAttribute("Msg", 
	                            						messages.getText("vehicle.messages.upload.wrongprefix")+word.toString().trim()
	                            						+". \\n"+messages.getText("driver.messages.upload.linenumber")+": "+lineNumber);
	                            				return mapping.findForward("error");    
	                            			}
	                            		}
		                            	// J&J CO2
	                            	    if(sys.isCorporation(CorporationData.JJ) && word.toString().trim().length() > 0) {
		                                    try {
		                                        vehicle.setCo2emission(new Float(word.toString().trim()));
		                                    }catch(Exception e) {
		                                    	e.printStackTrace();
		                                    	session.setAttribute("Msg", 
			                      						  messages.getText("vehicle.messages.upload.wrongco2")+word.toString().trim()
			                      						  +". \\n"+messages.getText("driver.messages.upload.linenumber")+": "+lineNumber);
				                            	return mapping.findForward("error");   
		                                    }
		                                }	                                
	                            	}
	                                break;
	                                
	                            case 36:
	                            	
	                            	// KLABIN: 
	                            	// Total Gross Weight
	                            	if (sys.getUserSessionData().getFilter().getCorporationId().
                        					equals(new Integer(CorporationData.KLABIN))){
	                            	
		                            	if(word.toString().trim().length() > 0) {	
		                            		try{
		                            			optionalInfo.setTotalGrossWeight(new Integer(word.toString().trim()));
		                            		}catch(Exception e){	                                       
		                                        session.setAttribute("Msg", 
			                      						  messages.getText("vehicle.messages.upload.wrongtotalgrossweight")
			                      						  +word.toString()
			                      						  +". \\n"+messages.getText("driver.messages.upload.linenumber")
			                      						  +": "+lineNumber);
				                            	return mapping.findForward("error"); 	                                    
		                            		}                            		
		                            	}
	                            	}else {
		                            	// YOLI - LimitLoad:
		                            	if (sys.getUserSessionData().getFilter().getCorporationId().
	                    					equals(new Integer(CorporationData.YOLI))){
		                            		
		                            		if(word.toString().trim().length() > 0) {	
			                            		try{
			                            			optionalInfo.setLimitLoad(new Integer(word.toString().trim()));
			                            		}catch(Exception e){	                                       
			                                        session.setAttribute("Msg", 
				                      						  messages.getText("vehicle.messages.upload.wronglimitload")
				                      						  +word.toString()
				                      						  +". \\n"+messages.getText("driver.messages.upload.linenumber")
				                      						  +": "+lineNumber);
					                            	return mapping.findForward("error"); 	                                    
			                            		}   
			                            	}
		                            	}
		                            	// J&J CO2
	                            	    if(sys.isCorporation(CorporationData.JJ) && word.toString().trim().length() > 0) {
		                                    try {
		                                        vehicle.setConsume(new Float(word.toString().trim()));
		                                    }catch(Exception e) {
		                                    	e.printStackTrace();
		                                    	session.setAttribute("Msg", 
			                      						  messages.getText("vehicle.messages.upload.wrongconsume")+word.toString().trim()
			                      						  +". \\n"+messages.getText("driver.messages.upload.linenumber")+": "+lineNumber);
				                            	return mapping.findForward("error");   
		                                    }
		                                }
	                            	}
		                            break;
	                            case 37:
	                            	
	                            	// KLABIN: 
	                            	// total Combined Gross Weight
	                            	if (sys.getUserSessionData().getFilter().getCorporationId().
                        					equals(new Integer(CorporationData.KLABIN))){

		                            	if(word.toString().trim().length() > 0) {	
		                            		try{
		                            			optionalInfo.setTotalCombinedGrossWeight(new Integer(word.toString().trim()));
		                            		}catch(Exception e){	                                       
		                                        session.setAttribute("Msg", 
			                      						  messages.getText("vehicle.messages.upload.wrongtotalcombinedgrossweight")
			                      						  +word.toString()
			                      						  +". \\n"+messages.getText("driver.messages.upload.linenumber")
			                      						  +": "+lineNumber);
				                            	return mapping.findForward("error"); 	                                    
		                            		}                            		
		                            	}
	                            	}else
		                            
	                            	// YOLI: ASR
	                            	if (sys.getUserSessionData().getFilter().getCorporationId().
	                        					equals(new Integer(CorporationData.YOLI))){
	                            		
			                                if(word.toString().trim().length() > 0) {
			                                	String asr = word.toString().trim();
			                                    optionalInfo.setAsr(asr.equals("1") || asr.equalsIgnoreCase("yes") ||
			                                    		asr.equalsIgnoreCase("si") || asr.equalsIgnoreCase("sim"));
			                                }
		                            	
		                            }
	                            	
	                            	
	                            	break;
	                            case 38:
	                            	
	                            	// KLABIN:
	                            	// Monitoring System
	                            	if (sys.getUserSessionData().getFilter().getCorporationId().
                        					equals(new Integer(CorporationData.KLABIN))){
	                            		if(word.toString().trim().length() > 0) {	
		                            		if (word.toString().trim().equals("Mechanic") || 
	                            				word.toString().trim().equals("Mecanico") ||
	                            				word.toString().trim().equals("Mecánico") )
		                            		{
		                            			optionalInfo.setMonitoringSystem("vehicle.data.mechanic");
		                            		}else{
		                            			optionalInfo.setMonitoringSystem("vehicle.data.electronic");
		                            		}
		                            	}
	                            	}
	                            	
	                            	//YOLI: Curren KM
	                            	if (sys.getUserSessionData().getFilter().getCorporationId().
	                        					equals(new Integer(CorporationData.YOLI))){
	                            		if(word.toString().trim().length() > 0) {	
		                            		try{
		                            			optionalInfo.setCurrentKm(new Integer(word.toString().trim()));
		                            		}catch(Exception e){	                                       
		                                        session.setAttribute("Msg", 
			                      						  messages.getText("vehicle.messages.upload.wrongcurrentkm")
			                      						  +word.toString()
			                      						  +". \\n"+messages.getText("driver.messages.upload.linenumber")
			                      						  +": "+lineNumber);
				                            	return mapping.findForward("error"); 	                                    
		                            		}   
		                            	}
	                            	}
	                            	break;
	                            case 39:
	                            	
	                            	// KLABIN:
	                            	// Tachograph
	                            	if (sys.getUserSessionData().getFilter().getCorporationId().
                        					equals(new Integer(CorporationData.KLABIN))){

		                            	if(word.toString().trim().length() > 0) {	
		                            		if (word.toString().trim().equals("Mechanic") || 
	                            				word.toString().trim().equals("Mecanico") ||
	                            				word.toString().trim().equals("Mecánico") )
		                            		{
		                            			optionalInfo.setTachograph("vehicle.data.mechanic");
		                            		}else{
		                            			optionalInfo.setTachograph("vehicle.data.electronic");
		                            		}
		                            	}
	                            	}
	                            	
	                            	
//	                            	YOLI: Date Curren KM
	                            	if (sys.getUserSessionData().getFilter().getCorporationId().
	                        					equals(new Integer(CorporationData.YOLI))){
	                            		
	                            		if(word.toString().trim().length() > 0) {
		                                	try{
		                                		optionalInfo.setDateCurrentKm(Util.getDate(word.toString().trim()).getTime());
		                                	}catch(Exception e){
		                                		session.setAttribute("Msg", 
			                      						  messages.getText("vehicle.messages.upload.wrongdatecurrentkm")+word.toString().trim()
			                      						  +". \\n"+messages.getText("driver.messages.upload.linenumber")+": "+lineNumber);
				                            	return mapping.findForward("error");  
		                                	}
		                                }
	                            		
	                            	}
	                            	break;
	                            case 40:
	                            	
	                            	// KLABIN:
	                            	// Renavan
	                            	if (sys.getUserSessionData().getFilter().getCorporationId().
                        					equals(new Integer(CorporationData.KLABIN))){

		                            	if(word.toString().trim().length() > 0) {	
	                            			optionalInfo.setRenavan(word.toString().trim());
		                            	}
	                            	}
	                            	
//	                            	YOLI: Tracking system
	                            	if (sys.getUserSessionData().getFilter().getCorporationId().
	                        					equals(new Integer(CorporationData.YOLI))){
	                            		if(word.toString().trim().length() > 0) {	
	                            			optionalInfo.setMonitoringSystem(word.toString().trim());
		                            	}
	                            	}
	                            	break;
	                            case 41:
	                            	
	                            	// KLABIN:
	                            	// LimitLoad
	                            	if (sys.getUserSessionData().getFilter().getCorporationId().
                        					equals(new Integer(CorporationData.KLABIN))){
	
		                            	if(word.toString().trim().length() > 0) {	
		                            		try{
		                            			optionalInfo.setLimitLoad(new Integer(word.toString().trim()));
		                            		}catch(Exception e){	                                       
		                                        session.setAttribute("Msg", 
			                      						  messages.getText("vehicle.messages.upload.wronglimitload")
			                      						  +word.toString()
			                      						  +". \\n"+messages.getText("driver.messages.upload.linenumber")
			                      						  +": "+lineNumber);
				                            	return mapping.findForward("error"); 	                                    
		                            		}   
		                            	}
	                            	}
	                            	
//	                            	YOLI: axis count
	                            	if (sys.getUserSessionData().getFilter().getCorporationId().
	                        					equals(new Integer(CorporationData.YOLI))){
	                            		if(word.toString().trim().length() > 0) {	
		                            		try{
		                            			vehicle.setAxisCount(new Integer(word.toString().trim()));
		                            		}catch(Exception e){	                                       
		                                        session.setAttribute("Msg", 
			                      						  messages.getText("vehicle.messages.upload.wrongaxiscount")
			                      						  +word.toString()
			                      						  +". \\n"+messages.getText("driver.messages.upload.linenumber")
			                      						  +": "+lineNumber);
				                            	return mapping.findForward("error"); 	                                    
		                            		}   
		                            	}
	                            	}
	                            	break;
	                            case 42:
	                            	
	                            	// KLABIN:
	                            	// ASR
	                            	if (sys.getUserSessionData().getFilter().getCorporationId().
                        					equals(new Integer(CorporationData.KLABIN))){
	
		                                if(word.toString().trim().length() > 0) {
		                                	String asr = word.toString().trim();
		                                    optionalInfo.setAsr(asr.equals("1") || asr.equalsIgnoreCase("yes") ||
		                                    		asr.equalsIgnoreCase("si") || asr.equalsIgnoreCase("sim"));
		                                }
	                            	}
	                            	
	                            	
//	                            	YOLI: MTOP
	                            	if (sys.getUserSessionData().getFilter().getCorporationId().
	                        					equals(new Integer(CorporationData.YOLI))){
	                            		if(word.toString().trim().length() > 0) {
		                                	try{
		                                		vehicle.setMtopExpiry(Util.getDate(word.toString().trim()).getTime());
		                                	}catch(Exception e){
		                                		session.setAttribute("Msg", 
			                      						  messages.getText("vehicle.messages.upload.wrongmtopexpiry")+word.toString().trim()
			                      						  +". \\n"+messages.getText("driver.messages.upload.linenumber")+": "+lineNumber);
				                            	return mapping.findForward("error");  
		                                	}
		                                }
	                            	}
	                                break;
	                            case 43:
	                            	
	                            	// KLABIN:
	                            	// CurrentKM
	                            	if (sys.getUserSessionData().getFilter().getCorporationId().
                        					equals(new Integer(CorporationData.KLABIN))){

		                            	if(word.toString().trim().length() > 0) {	
		                            		try{
		                            			optionalInfo.setCurrentKm(new Integer(word.toString().trim()));
		                            		}catch(Exception e){	                                       
		                                        session.setAttribute("Msg", 
			                      						  messages.getText("vehicle.messages.upload.wrongcurrentkm")
			                      						  +word.toString()
			                      						  +". \\n"+messages.getText("driver.messages.upload.linenumber")
			                      						  +": "+lineNumber);
				                            	return mapping.findForward("error"); 	                                    
		                            		}   
		                            	}
	                            	}
	                            	
//	                            	YOLI: SUCTA
	                            	if (sys.getUserSessionData().getFilter().getCorporationId().
	                        					equals(new Integer(CorporationData.YOLI))){
	                            		if(word.toString().trim().length() > 0) {
		                                	try{
		                                		vehicle.setSuctaExpiry(Util.getDate(word.toString().trim()).getTime());
		                                	}catch(Exception e){
		                                		session.setAttribute("Msg", 
			                      						  messages.getText("vehicle.messages.upload.wrongsuctaexpiry")+word.toString().trim()
			                      						  +". \\n"+messages.getText("driver.messages.upload.linenumber")+": "+lineNumber);
				                            	return mapping.findForward("error");  
		                                	}
		                                }
	                            	}
	                            	break;
	                            case 44:
	                            	
	                            	// KLABIN:
	                            	// Date Current KM
	                            	if (sys.getUserSessionData().getFilter().getCorporationId().
                        					equals(new Integer(CorporationData.KLABIN))){

		                            	if(word.toString().trim().length() > 0) {
		                                	try{
		                                		optionalInfo.setDateCurrentKm(Util.getDate(word.toString().trim()).getTime());
		                                	}catch(Exception e){
		                                		session.setAttribute("Msg", 
			                      						  messages.getText("vehicle.messages.upload.wrongdatecurrentkm")+word.toString().trim()
			                      						  +". \\n"+messages.getText("driver.messages.upload.linenumber")+": "+lineNumber);
				                            	return mapping.findForward("error");  
		                                	}
		                                }
	                            	}
	                                break;
	                            case 45:
	                            	
	                            	// KLABIN:
	                            	// Antt Expiry
	                            	if (sys.getUserSessionData().getFilter().getCorporationId().
                        					equals(new Integer(CorporationData.KLABIN))){

		                            	if(word.toString().trim().length() > 0) {
		                                	try{
		                                		optionalInfo.setAnttExpiry(Util.getDate(word.toString().trim()).getTime());
		                                	}catch(Exception e){
		                                		session.setAttribute("Msg", 
			                      						  messages.getText("vehicle.messages.upload.wronganttexpiry")+word.toString().trim()
			                      						  +". \\n"+messages.getText("driver.messages.upload.linenumber")+": "+lineNumber);
				                            	return mapping.findForward("error");  
		                                	}
		                                }
	                            	}
	                                break;
	                            case 46:
	                            	
	                            	// KLABIN:
	                            	// Citv Expiry
	                            	if (sys.getUserSessionData().getFilter().getCorporationId().
                        					equals(new Integer(CorporationData.KLABIN))){

		                            	if(word.toString().trim().length() > 0) {
		                                	try{
		                                		optionalInfo.setCitvExpiry(Util.getDate(word.toString().trim()).getTime());
		                                	}catch(Exception e){
		                                		session.setAttribute("Msg", 
			                      						  messages.getText("vehicle.messages.upload.wrongcitvexpiry")+word.toString().trim()
			                      						  +". \\n"+messages.getText("driver.messages.upload.linenumber")+": "+lineNumber);
				                            	return mapping.findForward("error");  
		                                	}
		                                }
	                            	}
	                                break;
	                            case 47:
//	                            	 KLABIN - Axis Count
	                            	if (sys.getUserSessionData().getFilter().getCorporationId().
                        					equals(new Integer(CorporationData.KLABIN))){
	                            		if(word.toString().trim().length() > 0) {	
		                            		try{
		                            			vehicle.setAxisCount(new Integer(word.toString().trim()));
		                            		}catch(Exception e){	                                       
		                                        session.setAttribute("Msg", 
			                      						  messages.getText("vehicle.messages.upload.wrongaxiscount")
			                      						  +word.toString()
			                      						  +". \\n"+messages.getText("driver.messages.upload.linenumber")
			                      						  +": "+lineNumber);
				                            	return mapping.findForward("error"); 	                                    
		                            		}   
		                            	}	
	                            	}
	                            	break;
	                            case 48:
//	                            	 KLABIN - Ensurance Type
	                            	if (sys.getUserSessionData().getFilter().getCorporationId().
                       					equals(new Integer(CorporationData.KLABIN))){
	                            		if(word.toString().trim().length() > 0) {	
		                            		try{
                                                if(word.toString().toLowerCase().trim().equals("terceiros")
                                                        || word.toString().toLowerCase().trim().equals("terceiro")){
                                                    vehicle.setEnsuranceType(new EnsuranceDataType("datatype.ensurance.third"));
                                                }else if(word.toString().toLowerCase().trim().equals("rcf")){
                                                    vehicle.setEnsuranceType(new EnsuranceDataType("datatype.ensurance.rcf"));
                                                } else if(word.toString().toLowerCase().trim().equals("total")){
                                                    vehicle.setEnsuranceType(new EnsuranceDataType("datatype.ensurance.total"));
                                                }else {
                                                    session.setAttribute("Msg",messages.getText("vehicle.messages.upload.wrongensurancetype")
                                                          +word.toString()+". \\n"+messages.getText("driver.messages.upload.linenumber")
                                                          +": "+lineNumber);
                                                    return mapping.findForward("error");
                                                }
		                            		}catch(Exception e){	                                       
		                                        session.setAttribute("Msg", 
			                      						  messages.getText("vehicle.messages.upload.wrongensurancetype")
			                      						  +word.toString()
			                      						  +". \\n"+messages.getText("driver.messages.upload.linenumber")
			                      						  +": "+lineNumber);
				                            	return mapping.findForward("error"); 	                                    
		                            		}   
		                            	}	
	                            	}
	                            	break;
	                            case 49:
//	                            	 KLABIN - Ensurance
	                            	if (sys.getUserSessionData().getFilter().getCorporationId().equals(new Integer(CorporationData.KLABIN))){
	                            		if(word.toString().trim().length() > 0) {
                                            try{
		                            			vehicle.setEnsurance(word.toString().trim());
		                            		}catch(Exception e){	                                       
		                                        session.setAttribute("Msg", 
			                      						  messages.getText("vehicle.messages.upload.wrongensurancetype")
			                      						  +word.toString()
			                      						  +". \\n"+messages.getText("driver.messages.upload.linenumber")
			                      						  +": "+lineNumber);
				                            	return mapping.findForward("error"); 	                                    
		                            		}   
		                            	}	
	                            	}
	                            	break;
	                            	
	                        }
	                        word = new StringBuffer();	                        
	                        index++;
	                    }else {
	                        word.append(car);
	                    }
	                }
	           	                
	                // Coloco el vehiculo en la coleccion de vehiculos para insertar en la base de datos
	                if(!Util.empty(vehicle.getMatricula())){
	                	
	                	  // Antes de insertarlo valido que no exista ese vehiculo 
	                	  // en la base de datos
	                	  
	                	  // Region:
	                	  GeoItemData item = (GeoItemData)vehicle.getCountry().getItemIterator().next();
	                	  // Country
	                	  GeoItemData item2 = (GeoItemData)item.getSubItems().getItemIterator().next();
	                	  Integer country = item2.getId();
	                	
	                	  if (vehicleReport.existVehicle(sys.getUserSessionData(),
	                			vehicle.getMatricula(),country)){
	                		
	                		  // Si existe el driver le indico al usuario que no se va a insertar
	                		  // de nuevo.
	                		
	                		  String msg = vehicle.getMatricula()+ 
	                		  	" "+messages.getText(vehicle.getCountryName())+". ("+
	                		  	messages.getText("driver.messages.upload.linenumber")+": "+lineNumber+")";
	                		  notInsertedVehicles.add(msg);
	                	  }else{
	                		  // Si los vehiculos no existen los agrego:                	  
	                	      vehicles.add(vehicle);
	                	  }
	                  }  
	                  System.out.println("Linea: "+lineNumber);
	                  lineNumber++; // incremento numero de linea del archivo 
	              }          
	            
	              //Inserto los vehiculos              
	              VehicleABMFacade vehicleABM = null;
	              vehicleABM = sys.getVehicleABMFacade();              
	              vehicleABM.createVehicles(vehicles,((VehicleUploadForm)form).getNotes(),sys.getUserSessionData());
	              
	              // Total vehiculos insertados:
	              total = vehicles.size();              
	              // vehiclles no insertados:
	              result = new ListResult(notInsertedVehicles,notInsertedVehicles.size());
	              
	              // System.out.println("Inserte "+total+"Conductores.....");
	            
	            /*
	            vehicleReport = sys.getVehicleReportFacade();
	            vehicleReport.getChecksCount(new Integer(1),new Integer(2000),new Integer(1),new Integer(2010),null,null,sys.getText(),0, sys.getUserSessionData());
	            */
	              
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("Excepcion.....");
	        	throw e;
	        }
	        session.setAttribute("notupload",result);
	        session.setAttribute("total", (new Integer(total)).toString());        		  
	        session.setAttribute("entity","vehicle.messages.upload");
	        return mapping.findForward("success");	
	    }
	
}
