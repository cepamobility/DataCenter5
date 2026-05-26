package presentation.translator.web.activity.schedule;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.sql.Time;
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
import business.activity.ActivityABMFacade;
import business.activity.ActivityReportFacade;
import business.driver.DriverReportFacade;

import communication.activity.tables.CEPAActivityConstants;
import communication.activity.tables.CEPAActivityScheduleData;
import communication.activity.tables.CEPAActivityScheduleRegistryData;
import communication.activity.tables.CheckListData;
import communication.activity.tables.ItemData;
import communication.activity.tables.ItemValueData;
import communication.activity.tables.PointCommentData;
import communication.activity.tables.PointData;
import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.GeoLabelData;
import communication.administration.user.UserSessionData;
import communication.datatype.tables.ActivityTypeDataType;
import communication.driver.tables.DriverData;
import communication.exception.CommunicationProblemException;
import communication.exception.DCSecurityException;
import communication.exception.DeslogedException;
import communication.language.Text;
import communication.security.SecurityConstants;
import communication.util.ListResult;

public class UploadCDCimsaAction extends AbstractAction {

	static final int EOF = 65535;
	private static String errorMsg;
	
	/**
	 * CIMSA, subida de Conducciones Comentadas
	 */	
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception 
	{

				
		HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();       
        String directory = SecurityConstants.IMAGES_WAR_DIRECTORY;
        String fileName = "conduccionesComentadas"+sys.getUserSessionData().getFilter().getCorporationId()+".csv";
    
		try {
		
			System.out.println("Entro al Action UploadCDCimsaAction");
			UploadCDCimsaForm uploadForm = (UploadCDCimsaForm) form;
			FormFile file = (FormFile)uploadForm.getFile();		
					
			// Valido archivo de vacio o nulo
			if (file == null || file.getFileName() == null || file.getFileName().equals("")){
				sys.getUserSessionData().setMsg(messages.getText("generic.supportmaterial.cepatips.nofile"));
				sys.getUserSessionData().setUrl("/activity/schedule/uploadCDCimsa.jsp");
				return mapping.findForward("layout");	
		  	    	
			}
			
			// Valido Nombre de Archivo
			if (!this.validarString(file.getFileName())){
				sys.getUserSessionData().setMsg(messages.getText("generic.supportmaterial.cepatips.badfile"));
				sys.getUserSessionData().setUrl("/activity/schedule/uploadCDCimsa.jsp");
				return mapping.findForward("layout");	
		  	    
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
			
			System.out.println("Guarde el archivo. Voy a insertar commentary drives de cimsa:");
			
			return insertCD(mapping,form,request,response);			
	    	
		}catch(Exception e) {
	    	e.printStackTrace();
	    	sys.getUserSessionData().setMsg(messages.getText("generic.supportmaterial.cepatips.badpermissions"));
			sys.getUserSessionData().setUrl("/activity/schedule/uploadCDCimsa.jsp");
	  	    return mapping.findForward("layout");	
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
    public static ActionForward insertCD(ActionMapping mapping, ActionForm form, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	  HttpSession session = request.getSession();
          SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
          UserSessionData userSessionData = sys.getUserSessionData();
          
          Text messages = sys.getText();
          
          String directory = SecurityConstants.IMAGES_WAR_DIRECTORY;
          
          String fileName = "conduccionesComentadas"+sys.getUserSessionData().getFilter().getCorporationId()+".csv";
          int lineNumber = 1;
          
          UploadCDCimsaForm uploadForm = (UploadCDCimsaForm) form;
		  String notes = (String)uploadForm.getNotes();
		  
          StringBuffer word = new StringBuffer();
         
          try {
        	  
        	  ActivityReportFacade activityReport = null;
              activityReport = sys.getActivityReportFacade();
        	  
              FileInputStream input = new FileInputStream(new File(directory + fileName));
              char car;
              car = 0;
             
              System.out.println("Inserto Conducciones Comentadas de CIMSA:"); 
              
              
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
              
              Integer checkListCDCimsa = 34;
              CheckListData checkList = activityReport.findCheckList(checkListCDCimsa,userSessionData);
             
              
              boolean end = false;
              String[] s = new String[100];
              int index = 0;
              int line = 1;
              Collection<CEPAActivityScheduleRegistryData> list = new LinkedList<CEPAActivityScheduleRegistryData>();
              while(!end) {
            	  car = (char)input.read();
            	  if(car == EOF) {
            		  if(word.length() > 0) {
            			  CEPAActivityScheduleRegistryData data = getCommentaryDriveData(checkList,s,line,sys);
            			  if(data != null) {
            				  list.add(data);
            			  }else {
            				  session.setAttribute("Msg", messages.getText(errorMsg));
            				  sys.getUserSessionData().setUrl("/activity/schedule/uploadCDCimsa.jsp");
            				  return mapping.findForward("layout");	
            			  }
            			  word = new StringBuffer();
            		  }
            		  end = true;
            	  }else if(car == '\n') {
            		  s[index] = word.toString().trim();
            		  CEPAActivityScheduleRegistryData data = getCommentaryDriveData(checkList,s,line,sys);
            		  if(data != null) {
            			  list.add(data);
            		  }else {
            			  session.setAttribute("Msg", messages.getText(errorMsg));
            			  sys.getUserSessionData().setUrl("/activity/schedule/uploadCDCimsa.jsp");
            			  return mapping.findForward("layout");	
            		  }
            		  s = new String[100];
            		  word = new StringBuffer();
            		  index = 0;
            	  }else if(car == '\t') {
            		  s[index] = word.toString().trim();
            		  word = new StringBuffer();
            		  index++;
            	  }else {
            		  word.append(car);
            	  }
              }                        

              ActivityABMFacade activityABM = null;
              activityABM = sys.getActivityABMFacade();
              Object[] insertResult = activityABM.addCimsaCommentaryDrives(list, sys.getUserSessionData());
                            
              ListResult resultNotInserted = (ListResult)insertResult[0];
              Integer totalInserted = (Integer)insertResult[1];
             
              session.setAttribute("notupload",resultNotInserted);
              session.setAttribute("total", totalInserted.toString());        		  
              session.setAttribute("entity","activity.messages.upload");              
              
              sys.getUserSessionData().setUrl("/activity/schedule/successUploadCDCimsa.jsp");
      	      return mapping.findForward("layout");
      	      
              
          } catch (Exception e) {
              e.printStackTrace();
        	  System.out.println("Excepcion....."+e.getMessage());
        	  throw e;
          }
    }
    
    /**
     * 
     * @param checkList
     * @param s
     * @param line
     * @param sys
     * @return
     * @throws Exception 
     * @throws RemoteException 
     * @throws DCSecurityException 
     * @throws CommunicationProblemException 
     * @throws DeslogedException 
     */
    private static CEPAActivityScheduleRegistryData getCommentaryDriveData(
    		CheckListData checkList, String[] s, int line,SystemAccess sys)	throws Exception {
		
    	CEPAActivityScheduleRegistryData registryData = new CEPAActivityScheduleRegistryData();
    	registryData.setHasResult(true);
    	
    	CEPAActivityScheduleData activityData = new CEPAActivityScheduleData();
    	
    	
    	// Seteo los campos fijos de la actividad:
    	// Corporacion CIMSA:
    	CorporationData corporationData = sys.getCorporationReportFacade().
    		findCorporationByPrimaryKey(CorporationData.CIMSA, sys.getUserSessionData());
    	activityData.setCorpooration(corporationData);
    	
    	// Ubicacion geografica:
    	GeoLabelData countrygeo = null;
    	Collection items1 = new LinkedList();
    	Collection items2 = new LinkedList();
    	// En el nombre de la divisiÃ³n geogrÃ¡fica guarda un string vacÃ­o. En las capas de abajo,
    	// este campo lo carga hibernate a partir del id que viene en el form 	
    	// MEXICO
        items2.add(new GeoItemData(Integer.valueOf(42),"",true,null));
        GeoLabelData level1 = new GeoLabelData(null,null,null,items2);
    	// En el nombre de la divisiÃ³n geogrÃ¡fica guarda un string vacÃ­o. En las capas de abajo,
    	// este campo lo carga hibernate a partir del id que viene en el form
        // 
        items1.add(new GeoItemData(Integer.valueOf(432738),"",true,level1));
   
        countrygeo = new GeoLabelData(null,null,null,items1);
        activityData.setCountrygeo(countrygeo);
        
        // Tipo de Actividad, Commentary Drive CAYACO
        activityData.setType(new ActivityTypeDataType(CEPAActivityConstants.COMMENTARYDRIVECAYACO));

		Text messages = sys.getText();		
		
		// Fecha Conduccion Comentada
		try{
	     	if (!Util.empty(s[0])){
	     		activityData.setDate(Util.getDate(s[0].trim()).getTime());
	     	}else{
	     		errorMsg = messages.getText("activity.messages.upload.wrongdate")
	     				  +". "+messages.getText("driver.messages.upload.linenumber")+" " + String.valueOf(line);
	     		return null;
	     	}
		}catch(Exception e){
			errorMsg = messages.getText("activity.messages.upload.wrongdate")
				+". "+messages.getText("driver.messages.upload.linenumber")+" "+line;
			return null;
		}
		
        
		// Ciudad: 
		try{
			if (!Util.empty(s[1])){
				activityData.setCity(s[1].trim());
			}else{
				errorMsg = messages.getText("activity.messages.upload.wrongcity")
						+". "+messages.getText("driver.messages.upload.linenumber")+" "+line;
				return null;
			}
		}catch(Exception e){
			errorMsg = messages.getText("activity.messages.upload.wrongcity")
				+". "+messages.getText("driver.messages.upload.linenumber")+" "+line;
			return null;

		}
  	  	
  	  	// Nombre
  	  	String nombre = null;
  	  	String apellido = null;
  	  	if (!Util.empty(s[2])){  nombre = s[2]; }
  		// Apellido
  	  	if (!Util.empty(s[3])){  apellido = s[3]; }
		// Corporation Id
  	  	String corporationId = null;
		if (!Util.empty(s[4]) && Util.isNumber(s[4])){ corporationId = s[4]; }
		
		DriverReportFacade driverReport = sys.getDriverReportFacade();
		Integer driverId = driverReport.existDriverByCorporationId(sys.getUserSessionData(),
        			nombre,apellido,corporationId,CorporationData.CIMSA);
        			
		if(!Util.empty(nombre) && !Util.empty(apellido) && driverId != null){
			
			DriverData driverData = driverReport.findDriverByPrimaryKey(driverId, sys.getUserSessionData());
			registryData.setDriver(driverData);
        	  
		}else{
			
			System.out.println("Conductor no encontrado:"+nombre+" "+apellido+" - "+corporationId);
			errorMsg = messages.getText("activity.messages.upload.wrongdriver")+": "
					+nombre+" "+apellido+". \\n"+
 				  messages.getText("driver.messages.upload.linenumber")+": "+line;
 	  	  	return null;
		}
		
		
		// Instructor
		String instructor = null;
		if (!Util.empty(s[5])){  
			instructor = s[5]; 
  			registryData.setInstructor(instructor);
      	}
	
    	// Marca
		String trade = null;
		if (!Util.empty(s[6])){  
			trade = s[6]; 
			registryData.setTrade(trade);
      	}
		
		// Matricula
		String plate = null;
		if (!Util.empty(s[7])){  
			plate = s[7]; 
			registryData.setPlate(plate);
      	}
      	
		// Hora Inicio
		try{
			if (!Util.empty(s[8])){ 
				// Asumo hh:mm
				System.out.println("Hora Inicio:"+s[8].trim());
				String hour = s[8].trim().substring(0,2);
				String minute = s[8].trim().substring(3,5);
				Time initialHr = Util.getTime(hour, minute);
      			registryData.setInitialHr(initialHr);
			}
		}catch(Exception e){
			System.out.println("Hora Inicio Incorrecta");
      	  	errorMsg = messages.getText("activity.messages.upload.wronginitialhour")+": "+
      				  ((s[8] != null)?s[8]:"")+". \\n"+
      				  messages.getText("driver.messages.upload.linenumber")+": "+line;
      	 	return null;
      	}
		
		// Hora fin
		try{
			if (!Util.empty(s[9])){ 
				// Asumo hh:mm
				System.out.println("Hora Fin:"+s[9].trim());
				String hour = s[9].trim().substring(0,2);
				String minute = s[9].trim().substring(3,5);
	      		Time finalHr = Util.getTime(hour, minute);
	      		registryData.setFinalHr(finalHr);
			}
		}catch(Exception e){
			System.out.println("Hora Fin Incorrecta");
      	  	errorMsg = messages.getText("activity.messages.upload.wrongfinalhour")+": "+
      				  ((s[9] != null)?s[9]:"")+". \\n"+
      				  messages.getText("driver.messages.upload.linenumber")+": "+line;
   	  		return null;
      	}

		// Cuenta km inicial
		if (!Util.empty(s[10])){ 
			try{
				Integer initialKm = new Integer(s[10]);
      			registryData.setInitialKm(initialKm);
			}catch(Exception e){
				System.out.println("Cuenta KM Inicial Incorrectoa");
      	  		errorMsg = messages.getText("activity.messages.upload.wronginitialkm")+": "+
      				  ((s[10] != null)?s[10]:"")+". \\n"+
      				  messages.getText("driver.messages.upload.linenumber")+": "+line;
      	  		return null;
			}
      	}
		
		
		// Cuenta km final
		if (!Util.empty(s[11])){ 
			try{
				Integer finalKm = new Integer(s[11]);
      			registryData.setFinalKm(finalKm);
			}catch(Exception e){
				System.out.println("Cuenta KM Final Incorrectoa");
      	  		errorMsg = messages.getText("activity.messages.upload.wrongfinalkm")+": "+
      				  ((s[11] != null)?s[11]:"")+". \\n"+
      				  messages.getText("driver.messages.upload.linenumber")+": "+line;
      	  		return null;
			}
      	}
		
	
		
     	// Cosas traidas de checklist:
     	try {
		    Collection itemValueSet = new LinkedList();
		    Collection pointCommentSet = new LinkedList();
	        Iterator points = checkList.getSortPoints();
	        int index = 12;
	        while(points.hasNext()) {
	        	PointData point = (PointData)points.next();
	        	Iterator itemsIt = point.getSortItems();
	        	if (itemsIt != null){
		            while(itemsIt.hasNext()) {
		            	
		                ItemData item = (ItemData)itemsIt.next();
		                
		                // El punto 40 tiene un item que no soporta valores 1 o 2, sino texto
		                // lo pongo como comentarios del punto.
		                
		                if (item.getId() != 2754){
		                	
		                	if (index >= 17 && index <= 19){
		                		// Beuno, Regular, Malo
		                		
		                		System.out.println(messages.getText(item.getText()) +": "+ s[index]);
				                if(s[index].equalsIgnoreCase("Bueno")){
				                	itemValueSet.add(new ItemValueData(new ItemData(item.getId()),1));
								}else if (s[index].equalsIgnoreCase("Regular")){
									itemValueSet.add(new ItemValueData(new ItemData(item.getId()),2));
								}else if (s[index].equalsIgnoreCase("Malo")){
									itemValueSet.add(new ItemValueData(new ItemData(item.getId()),3));
				            	}else{
									errorMsg = messages.getText("activity.messages.upload.wrongvalue.b.r.m")+" - "+
										messages.getText(item.getText())+". \\n "+
										messages.getText("driver.messages.upload.linenumber")+": "+line;
									return null;
				                }
		                		
		                	}else{
		                		// Si, No
			                	System.out.println(messages.getText(item.getText()) +": "+ s[index]);
				                if(s[index].equalsIgnoreCase("SI")){
				                	itemValueSet.add(new ItemValueData(new ItemData(item.getId()),1));
								}else if (s[index].equalsIgnoreCase("NO")){
									itemValueSet.add(new ItemValueData(new ItemData(item.getId()),2));
				            	}else{
									errorMsg = messages.getText("activity.messages.upload.wrongvalue")+" - "+
										messages.getText(item.getText())+". \\n "+
										messages.getText("driver.messages.upload.linenumber")+": "+line;
									return null;
				                }
		                	}
			                index++;
		                }
		            }
	        	}
	            
	            PointCommentData pointComment = new PointCommentData(point,s[index]);
	            pointCommentSet.add(pointComment);
	            System.out.println(messages.getText(point.getText())+" - Observaciones: "+
	            		s[index]);
	            index++;
	            
	        }
	        
	        // Observaciones Generales
	        if (!Util.empty(s[60])){ 
	        	registryData.setObs(s[60].trim());
	        }
	    		
	        registryData.setPointCommentSet(pointCommentSet);
	        registryData.setItemValueSet(itemValueSet);
	        registryData.setActivity(activityData);
	        
	        return registryData;
	        
		}catch (Exception e) {
			errorMsg = messages.getText("messages.uploadchecklist.errorfile");
			return null;
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

