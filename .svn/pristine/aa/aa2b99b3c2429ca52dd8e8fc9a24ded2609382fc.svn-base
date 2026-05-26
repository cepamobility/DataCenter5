package presentation.translator.web.forms;

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

import communication.language.Text;

public class FormsUploadAction extends AbstractAction {

	/**
	 * 
	 */	
	public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
    
		try {
		
			FormsUploadForm uploadForm = (FormsUploadForm) form;
			FormFile file = (FormFile)uploadForm.getFile();		
			
			// Valido archivo de Form vacio o nulo
			if (file == null || file.getFileName() == null || file.getFileName().equals("")){
				session.setAttribute("Msg", 
						messages.getText("generic.supportmaterial.forms.nofile"));
		  	    return mapping.findForward("success");	
			}
			
			// Valido Nombre de Archivo
			/* if (this.validarString(file.getFileName())){
				session.setAttribute("Msg", 
						messages.getText("generic.supportmaterial.forms.badfile"));
		  	    return mapping.findForward("success");	
			}*/
			// Fin Valido Nombre de Archivo
			
			String directory = (String)uploadForm.getDirectory();		
			
			// Para cambiarle la fecha:
			String radiodate = (String)uploadForm.getRadioDate();		
			String otherdateday = (String)uploadForm.getOtherDateDay();		
			String otherdatemonth = (String)uploadForm.getOtherDateMonth();		
			String otherdateyear = (String)uploadForm.getOtherDateYear();		
			
			InputStream reportStream = file.getInputStream();
			String fileName = file.getFileName().replaceAll(" ", "_"); //convertString(file.getFileName());
			FileOutputStream fout = new FileOutputStream(directory+"/"+fileName);
			File file2;
			
			int c = 0;
			while ( (c=reportStream.read()) != -1){
				fout.write(c);
			}			
			fout.flush();
			fout.close();			
	
			if ( radiodate.trim().equals("otherdate")){
				//Valido fechas
		        int year = ( Util.empty(otherdateyear) || !Util.isNumber(otherdateyear) ? 0 : 
	    			Integer.valueOf(otherdateyear).intValue());
		        int month = (Util.empty(otherdatemonth) || !Util.isNumber(otherdatemonth) ? 0 : 
	    			Integer.valueOf(otherdatemonth).intValue());
		        int day = (Util.empty(otherdateday) || !Util.isNumber(otherdateday) ? 0 : 
	    			Integer.valueOf(otherdateday).intValue());
				if (!Util.checkDate(day,month,year)){ 
					// Mensaje de error en la fecha
					session.setAttribute("Msg", messages.getText("generic.supportmaterial.forms.baddate"));
			  	    return mapping.findForward("error");	
				}
				
				file2 = new File(directory+"/"+fileName);
				
				// Modifico la fecha del Form
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.YEAR,year);
				cal.set(Calendar.MONTH,month-1);
				cal.set(Calendar.DATE,day);		
				file2.setLastModified(cal.getTimeInMillis());				
			}
			session.setAttribute("Msg", messages.getText("generic.supportmaterial.forms.successuploadforms"));
	  	    return mapping.findForward("success");	
	    }
		catch (IOException io){
			session.setAttribute("Msg", messages.getText("generic.supportmaterial.forms.badpermissions"));
	    	io.printStackTrace();
	  	    return mapping.findForward("error");	
		}
	    catch(Exception e) {
	    	session.setAttribute("Msg", messages.getText("generic.supportmaterial.forms.baddate"));
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