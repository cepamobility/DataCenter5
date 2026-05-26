package presentation.translator.web.vehicle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Collection;
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
import business.SystemAccess;
import business.vehicle.VehicleABMFacade;
import business.vehicle.VehicleReportFacade;

import communication.activity.tables.CheckListData;
import communication.activity.tables.ItemData;
import communication.activity.tables.ItemValueStrData;
import communication.activity.tables.PointData;
import communication.language.Text;
import communication.security.SecurityConstants;
import communication.util.ListResult;
import communication.vehicle.tables.VehicleCheckData;
import communication.vehicle.tables.VehicleData;

public class CheckListUploadAction extends AbstractAction {

	static final int EOF = 65535;
	private static String errorMsg;
	/**
	 * 
	 */	
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
        String directory = SecurityConstants.IMAGES_WAR_DIRECTORY;
//        String directory = "C:/datacenter/images";
    	String fileName = "checklist"+sys.getUserSessionData().getFilter().getCorporationId()+".csv";			   			
		    
		try {		
			VehicleUploadForm uploadForm = (VehicleUploadForm) form;
			FormFile file = (FormFile)uploadForm.getFile();		
			
			// Valido archivo de CepaTips vacio o nulo
			if (file == null || file.getFileName() == null || file.getFileName().equals("")){
				session.setAttribute("Msg", messages.getText("generic.supportmaterial.cepatips.nofile"));
				sys.getUserSessionData().setUrl("vehicle/uploadCheckLists.jsp");
		  	    return mapping.findForward("success");	
			}
			
			// Valido Nombre de Archivo
			if (!this.validarString(file.getFileName())){
				session.setAttribute("Msg",messages.getText("generic.supportmaterial.cepatips.badfile"));
				sys.getUserSessionData().setUrl("vehicle/uploadCheckLists.jsp");
		  	    return mapping.findForward("success");	
			}
			// Fin Valido Nombre de Archivo
									
			InputStream reportStream = file.getInputStream();
			FileOutputStream fout = new FileOutputStream(directory+"/"+fileName);			
			int c = 0;
			while ( (c=reportStream.read()) != -1){
				fout.write(c);
			}			
			fout.flush();
			fout.close();		
					
			File file2 = new File(directory+"/"+fileName);				
			
			System.out.println("Guarde el archivo. Voy a insertar checklists:");	
			
			return insertCheckLists(mapping,form,request,response);
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
	
	public String convertString(String s){		
		StringBuffer aux = new StringBuffer();
    	int count = 1;
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
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Throwable
     */
    public ActionForward insertCheckLists(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
  
    		HttpSession session = request.getSession();
	        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
	        String directory = SecurityConstants.IMAGES_WAR_DIRECTORY;
	        Text messages = sys.getText();
	        String fileName = "checklist"+sys.getUserSessionData().getFilter().getCorporationId()+".csv";
	        int lineNumber = 1;
	        int total = 0;
	        ListResult result;
	        StringBuffer word = new StringBuffer();
	        
	        try {
	        	
	        	VehicleReportFacade vehicleReport = null;
	            vehicleReport = sys.getVehicleReportFacade();
	        	FileInputStream input = new FileInputStream(new File(directory + "/" + fileName));
	            
	            char car;
	            car = 0;
	            
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
	           
                CheckListData checkList = sys.getVehicleReportFacade().getCheckList(CheckListData.CIMSA_CHECKLIST,sys.getUserSessionData());

	            boolean end = false;
	            String[] s = new String[100];
	            int index = 0;
	            int line = 1;
	            Collection<VehicleCheckData> list = new LinkedList<VehicleCheckData>();
	            while(!end) {
                    car = (char)input.read();
                    if(car == EOF) {
                    	if(word.length() > 0) {
                        	VehicleCheckData data = getVehicleCheckData(checkList,s,line,sys);
                        	if(data != null) {
                        		list.add(data);
                        	}else {
                				session.setAttribute("Msg", messages.getText(errorMsg));
                				sys.getUserSessionData().setUrl("vehicle/uploadCheckLists.jsp");
                		  	    return mapping.findForward("success");	
                        	}
                        	word = new StringBuffer();
                    	}
                    	end = true;
                    	line++;
                    }else if(car == '\n') {
                    	s[index] = word.toString().trim();
                    	VehicleCheckData data = getVehicleCheckData(checkList,s,line,sys);
                    	if(data != null) {
                    		list.add(data);
                    	}else {
            				session.setAttribute("Msg", messages.getText(errorMsg));
            				sys.getUserSessionData().setUrl("vehicle/uploadCheckLists.jsp");
            		  	    return mapping.findForward("success");	
                    	}
                    	s = new String[100];
                    	word = new StringBuffer();
                    	index = 0;
                    	line++;
                    }else if(car == '\t') {
                    	s[index] = word.toString().trim();
                    	word = new StringBuffer();
                    	index++;
                    }else {
                        word.append(car);
                    }
	            }
	            		            
	              //Inserto los vehiculos              
	             VehicleABMFacade vehicleABM = null;
	             vehicleABM = sys.getVehicleABMFacade();              
	             vehicleABM.createCheckLists(list,sys.getUserSessionData());
	              
	              
	        } catch (Exception e) {
	            e.printStackTrace();
	        	throw e;
	        }
	        
			sys.getUserSessionData().setUrl("success.jsp?entity=checklist.upload");
	  	    return mapping.findForward("success");	
	    }

	private VehicleCheckData getVehicleCheckData(CheckListData checkList, String[] s, int line,SystemAccess sys) {
		VehicleCheckData vehicleCheckData = new VehicleCheckData();
		Text messages = sys.getText();
		try {
			VehicleData vehicle = sys.getVehicleReportFacade().findVehicleByPlate(s[0], sys.getUserSessionData());
			if(vehicle != null) {
				vehicleCheckData.setVehicle(vehicle);
			}else {
				errorMsg = messages.getText("messages.uploadchecklist.errorvehicle")+" "+messages.getText("messages.uploadchecklist.errorline")+" "+line;
				return null;
			}
		}catch (Exception e) {
			errorMsg = messages.getText("messages.uploadchecklist.errorvehicle")+" "+messages.getText("messages.uploadchecklist.errorline")+" "+line;
			return null;
		}
		try {
			Calendar c = Calendar.getInstance();
			StringTokenizer tokenizer = new StringTokenizer(s[1],"/");
			c.set(Calendar.DATE, Integer.parseInt(tokenizer.nextToken()));
			c.set(Calendar.MONTH, Integer.parseInt(tokenizer.nextToken())-1);
			c.set(Calendar.YEAR, Integer.parseInt(tokenizer.nextToken()));
			vehicleCheckData.setDate(c.getTime());
		}catch (Exception e) {
			errorMsg = messages.getText("messages.uploadchecklist.errordate")+" "+messages.getText("messages.uploadchecklist.errorline")+" "+line;
			return null;
		}
		try {
			vehicleCheckData.setName(s[2]);
	        Collection itemValueSet = new LinkedList();
	        Iterator points = checkList.getSortPoints();
	        int index = 3;
	        while(points.hasNext()) {
	        	PointData point = (PointData)points.next();
	        	Iterator itemsIt = point.getSortItems();
	            while(itemsIt.hasNext()) {
	                ItemData item = (ItemData)itemsIt.next();
	                System.out.println(messages.getText(item.getText()));
	                switch(item.getId().intValue()) {
						case 4802: case 4803: case 4804: case 4805: case 4806:
							if(s[index].equals("10") || s[index].equals("8") || s[index].equals("6") || s[index].equals("4")) {
				                itemValueSet.add(new ItemValueStrData(new ItemData(item.getId()),s[index]));
							}else {
								errorMsg = messages.getText("messages.uploadchecklist.error")+" - "+messages.getText(item.getText())+" "+messages.getText("messages.uploadchecklist.errorline")+" "+line;
								return null;
							}
							break;
						case 4770:	
							if(s[index].equals("Refresco")) {
				                itemValueSet.add(new ItemValueStrData(new ItemData(item.getId()),"1"));
							}else if(s[index].equals("Agua")) {
				                itemValueSet.add(new ItemValueStrData(new ItemData(item.getId()),"2"));
							}else if(s[index].equals("JDV")) {
				                itemValueSet.add(new ItemValueStrData(new ItemData(item.getId()),"3"));
							}else if(s[index].equals("CFDLA")) {
				                itemValueSet.add(new ItemValueStrData(new ItemData(item.getId()),"4"));
							}else if(s[index].equals("No tiene")) {
				                itemValueSet.add(new ItemValueStrData(new ItemData(item.getId()),"5"));
							}else {
								errorMsg = messages.getText("messages.uploadchecklist.error")+" - "+messages.getText(item.getText())+" "+messages.getText("messages.uploadchecklist.errorline")+" "+line;
								return null;						
							}
							break;
						case 4795:
							try {
								int value = Integer.parseInt(s[index]);
								itemValueSet.add(new ItemValueStrData(new ItemData(item.getId()),s[index]));
							}catch (Exception e) {
								errorMsg = messages.getText("messages.uploadchecklist.error")+" - "+messages.getText(item.getText())+" "+messages.getText("messages.uploadchecklist.errorline")+" "+line;
								return null;						
							}
							break;
						default:  
							if(s[index].equals(messages.getText("generic.messages.rigth"))) {
				                itemValueSet.add(new ItemValueStrData(new ItemData(item.getId()),"1"));
							}else if(s[index].equals(messages.getText("generic.messages.wrong"))) {
				                itemValueSet.add(new ItemValueStrData(new ItemData(item.getId()),"2"));
							}else {
								errorMsg = messages.getText("messages.uploadchecklist.error")+" - "+messages.getText(item.getText())+" "+messages.getText("messages.uploadchecklist.errorline")+" "+line;
								return null;						
							}
							break;
	                }
	                index++;
	            }
	        }
	        vehicleCheckData.setItemValueSet(itemValueSet);
	        vehicleCheckData.setObs(s[index]);
	        return vehicleCheckData;
		}catch (Exception e) {
			errorMsg = messages.getText("messages.uploadchecklist.errorfile");
			return null;
		}
	}
	
}
