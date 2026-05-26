/**
 * 
 */
package presentation.translator.web.reports;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

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

import communication.administration.corporation.tables.CorporationData;
import communication.language.Text;
/**
 * @author gbenaderet
 *
 */
public class ReportUploadAction extends AbstractAction {

	/**
	 * 
	 */	
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
    
		try {
			
			ReportUploadForm uploadForm = (ReportUploadForm) form;			
			FormFile file = (FormFile)uploadForm.getFile();	
			// Valido file vacio o null
			if (file == null || file.getFileName() == null || file.getFileName().equals("")){
				session.setAttribute("Msg", 
						messages.getText("generic.supportmaterial.cepatips.nofile"));
				if(sys.getCorporationId() == CorporationData.ANGLO) {
			  	    return mapping.findForward("anglo");	
				}
		  	    return mapping.findForward("success");	
			}
			if (!this.validarString(file.getFileName())){
				session.setAttribute("Msg", 
						messages.getText("generic.supportmaterial.wrongname"));
				if(sys.getCorporationId() == CorporationData.ANGLO) {
			  	    return mapping.findForward("anglo");	
				}
		  	    return mapping.findForward("error");	
			}
			
			if(sys.isCorporation(CorporationData.MONTES_DE_PLATA)){
				if(uploadForm.getDivOrg1()==null || uploadForm.getDivOrg1()==0){
					session.setAttribute("Msg", 
							messages.getText("generic.supportmaterial.wrongcontractor"));
			  	    return mapping.findForward("error");
				}
				
				return processMDP(mapping,uploadForm,request,response);
			}
			
			String year = (String)uploadForm.getYear();		
			String directory = (String)uploadForm.getDirectory();		
			
			// Para cambiarle la fecha:
			String radiodate = (String)uploadForm.getRadioDate();		
			String otherdateday = (String)uploadForm.getOtherDateDay();		
			String otherdatemonth = (String)uploadForm.getOtherDateMonth();		
			String otherdateyear = (String)uploadForm.getOtherDateYear();
			
			
			InputStream reportStream = file.getInputStream();
			String fileName = file.getFileName().replaceAll(" ", "_");
			
			Calendar cal = Calendar.getInstance();
			int year_1 = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH)+1;
			int day = cal.get(Calendar.DATE);
			
					
			// Me fijo si existe el directorio, sino lo creo.
			File dir = new File(directory+"/"+year);
			if (!dir.exists()){
				boolean success = dir.mkdir();
			    if (!success) {
					new Exception("No se pudo crear el directorio: " + dir.getName());
				}
			}
			FileOutputStream fout = new FileOutputStream(directory+
					"/"+year+"/"+fileName);
			int c = 0;
			while ( (c=reportStream.read()) != -1){
				fout.write(c);
			}			
			fout.flush();
			fout.close();			

			if ( radiodate.trim().equals("otherdate")){
//				Valido fechas
		        year_1 = ( Util.empty(otherdateyear) || !Util.isNumber(otherdateyear) ? 0 : 
	    			Integer.valueOf(otherdateyear).intValue());
		        month = (Util.empty(otherdatemonth) || !Util.isNumber(otherdatemonth) ? 0 : 
	    			Integer.valueOf(otherdatemonth).intValue());
		        day = (Util.empty(otherdateday) || !Util.isNumber(otherdateday) ? 0 : 
	    			Integer.valueOf(otherdateday).intValue());
				if (!Util.checkDate(day,month,year_1)){ 
					// Mensaje de error en la fecha
					session.setAttribute("Msg", messages.getText("generic.supportmaterial.cepatips.baddate"));
					if(sys.getCorporationId() == CorporationData.ANGLO) {
				  	    return mapping.findForward("anglo");	
					}
			  	    return mapping.findForward("error");	
				}
				File file2 = new File(directory+"/"+year+"/"+fileName);
				cal = Calendar.getInstance();
				cal.set(Calendar.YEAR,year_1);
				cal.set(Calendar.MONTH,month-1);
				cal.set(Calendar.DATE,day);		
				file2.setLastModified(cal.getTimeInMillis());						
			}			
			
			session.setAttribute("Msg", messages.getText("generic.supportmaterial.reports.successuploadreport"));
			if (sys.getUserSessionData().getFilter().getCorporationId().equals(new Integer(1))){
					session.setAttribute("country",directory.substring(directory.length()-2));
			}
			if(sys.getCorporationId() == CorporationData.ANGLO) {
		  	    return mapping.findForward("anglo");	
			}
			return mapping.findForward("success");	
        }
		catch (IOException io){
			session.setAttribute("Msg", messages.getText("generic.supportmaterial.cepatips.badpermissions"));
	    	io.printStackTrace();
			if(sys.getCorporationId() == CorporationData.ANGLO) {
		  	    return mapping.findForward("anglo");	
			}
	  	    return mapping.findForward("error");	
		}
        catch(Exception e) {
    		session.setAttribute("Msg", messages.getText("generic.supportmaterial.cepatips.baddate"));
    		System.out.println("ReportUpdateAction.java: "+e.getMessage());
			if(sys.getCorporationId() == CorporationData.ANGLO) {
		  	    return mapping.findForward("anglo");	
			}
	  	    return mapping.findForward("error");		
        }       
			
    }


	private ActionForward processMDP(ActionMapping mapping,	ReportUploadForm uploadForm, HttpServletRequest request, HttpServletResponse response) {
			HttpSession session = request.getSession();
	        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
	        Text messages = sys.getText();
        
			try{
					FormFile file = (FormFile)uploadForm.getFile();	
					String year = (String)uploadForm.getYear();		
					String directory = (String)uploadForm.getDirectory();		
					
					// Para cambiarle la fecha:
					String radiodate = (String)uploadForm.getRadioDate();		
					String otherdateday = (String)uploadForm.getOtherDateDay();		
					String otherdatemonth = (String)uploadForm.getOtherDateMonth();		
					String otherdateyear = (String)uploadForm.getOtherDateYear();
					
					InputStream reportStream = file.getInputStream();
					String fileName = file.getFileName().replaceAll(" ", "_");
					
					Calendar cal = Calendar.getInstance();
					int year_1 = cal.get(Calendar.YEAR);
					int month = cal.get(Calendar.MONTH)+1;
					int day = cal.get(Calendar.DATE);
					
							
					// Me fijo si existe el directorio, sino lo creo.
					File dir = new File(directory+"/"+year);
					if (!dir.exists()){
						boolean success = dir.mkdir();
					    if (!success) {
							new Exception("No se pudo crear el directorio: " + dir.getName());
						}
					}
					dir = new File(directory+"/"+year+"/"+uploadForm.getDivOrg1());
					if (!dir.exists()){
						boolean success = dir.mkdir();
					    if (!success) {
							new Exception("No se pudo crear el directorio: " + dir.getName());
						}
					}
					FileOutputStream fout = new FileOutputStream(directory+
							"/"+year+"/"+uploadForm.getDivOrg1()+"/"+fileName);
					int c = 0;
					while ( (c=reportStream.read()) != -1){
						fout.write(c);
					}			
					fout.flush();
					fout.close();			
			
					if ( radiodate.trim().equals("otherdate")){
			//			Valido fechas
				        year_1 = ( Util.empty(otherdateyear) || !Util.isNumber(otherdateyear) ? 0 : 
			    			Integer.valueOf(otherdateyear).intValue());
				        month = (Util.empty(otherdatemonth) || !Util.isNumber(otherdatemonth) ? 0 : 
			    			Integer.valueOf(otherdatemonth).intValue());
				        day = (Util.empty(otherdateday) || !Util.isNumber(otherdateday) ? 0 : 
			    			Integer.valueOf(otherdateday).intValue());
						if (!Util.checkDate(day,month,year_1)){ 
							// Mensaje de error en la fecha
							session.setAttribute("Msg", messages.getText("generic.supportmaterial.cepatips.baddate"));
							return mapping.findForward("error");	
						}
						File file2 = new File(directory+"/"+year+"/"+uploadForm.getDivOrg1()+"/"+fileName);
						cal = Calendar.getInstance();
						cal.set(Calendar.YEAR,year_1);
						cal.set(Calendar.MONTH,month-1);
						cal.set(Calendar.DATE,day);		
						file2.setLastModified(cal.getTimeInMillis());						
					}			
					
					session.setAttribute("Msg", messages.getText("generic.supportmaterial.reports.successuploadreport"));
					if (sys.getUserSessionData().getFilter().getCorporationId().equals(new Integer(1))){
							session.setAttribute("country",directory.substring(directory.length()-2));
					}
				 
					return mapping.findForward("success");	
			    }
				catch (IOException io){
					session.setAttribute("Msg", messages.getText("generic.supportmaterial.cepatips.badpermissions"));
			    	io.printStackTrace();
					if(sys.getCorporationId() == CorporationData.ANGLO) {
				  	    return mapping.findForward("anglo");	
					}
			  	    return mapping.findForward("error");	
				}
			    catch(Exception e) {
					session.setAttribute("Msg", messages.getText("generic.supportmaterial.cepatips.baddate"));
					System.out.println("ReportUpdateAction.java: "+e.getMessage());
					if(sys.getCorporationId() == CorporationData.ANGLO) {
				  	    return mapping.findForward("anglo");	
					}
			  	    return mapping.findForward("error");		
			    }       
		 
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
	
		
	

	public ActionForward cancel(HttpSession session, ActionMapping mapping, ActionForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
	
	
