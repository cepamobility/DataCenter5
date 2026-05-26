package presentation.translator.web.documents;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import persistence.util.Util;
import presentation.translator.web.util.AbstractAction;
import business.SystemAccess;

import communication.administration.corporation.tables.CorporationData;
import communication.language.Text;
import communication.security.SecurityConstants;
import communication.util.tables.AppFileData;

public class AppFileAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
       
    	HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        Text messages = sys.getText();
         
        AppFileForm fileForm = (AppFileForm)form;
       
        AppFileData filaData=fileForm.getData(sys);
    	FormFile file=(FormFile) fileForm.getFile();
    	

		//Se controla que si cambia la categoria se tienen que mover los archivos al nuevo directorio.
    	if( !Util.empty(fileForm.getId()) ){
			AppFileData data= sys.getCorporationReportFacade().getAppFile(Integer.valueOf(fileForm.getId()), sys.getUserSessionData());
			
			String pathOrigen=SecurityConstants.APPJJ_DIRECTORY+"files/" + data.getCategoryName().toLowerCase()+"/"+data.getDocumentname();
			String pathDestino=SecurityConstants.APPJJ_DIRECTORY+"files/" + filaData.getCategoryName().toLowerCase()+"/"+filaData.getDocumentname();
			if(sys.isCorporation(CorporationData.CEPA)) {
				pathOrigen=SecurityConstants.APPCEPA_DIRECTORY+"/files/"+messages.getText(data.getLng()).toUpperCase()+"/" + data.getCategoryName().toLowerCase()+"/"+data.getDocumentname();
				pathDestino=SecurityConstants.APPCEPA_DIRECTORY+"/files/"+filaData.getLng().replaceFirst("module.data.name.", "").toUpperCase()+"/" + filaData.getCategoryName().toLowerCase()+"/"+filaData.getDocumentname();
			}

			if(data.getCategory()!=Integer.valueOf(fileForm.getCategory())){
				if(data.getDocumentname()!=null && data.getDocumentname().trim().length()>0){
				    File file1=new File(pathOrigen);
					if(file1.exists()){
						file1.renameTo(new File(pathDestino));
					}
				}
				if(data.getImagename()!=null && data.getImagename().trim().length()>0){
				    File file1=new File(pathOrigen);
					if(file1.exists()){
						file1.renameTo(new File(pathDestino));
					}
				}
			}
		}
    	
    	if(fileForm.getFile()!=null && fileForm.getFile().getFileName()!=null &&  fileForm.getFile().getFileName().length()>0){
        	fileForm.setDocumentname(file.getFileName());
        	filaData.setDocumentname(file.getFileName());
	        String path= SecurityConstants.APPJJ_DIRECTORY+"files/" + filaData.getCategoryName().toLowerCase()+"/"+filaData.getDocumentname();
	        if(sys.getCorporationId()==CorporationData.CEPA) {
	        	path = SecurityConstants.APPCEPA_DIRECTORY+"/files/"+filaData.getLng().replaceFirst("module.data.name.", "").toUpperCase()+"/" + filaData.getCategoryName().toLowerCase()+"/"+filaData.getDocumentname();
	        }
	        
	        File fileDelete=new File(path);
	        if(fileDelete.exists()){
	        	fileDelete.delete();
	        }
	        
	        FileOutputStream fout;
			
			InputStream reportStream = file.getInputStream();
			fout = new FileOutputStream(path);
			
			int c = 0;
			while ( (c=reportStream.read()) != -1){
				fout.write(c);
			}			
			fout.flush();
			fout.close();	
    	}else{
    		if(fileForm.getDocumentname()==null || fileForm.getDocumentname().trim().length()==0){
	    		session.setAttribute("Msg", sys.getText().getText("generic.messages.nofile"));
		  	    return mapping.findForward(sys.getCorporationId()==CorporationData.CEPA?"appcepafile":"appfile");
    		}
    	}
    	
		if(fileForm.getFileImage()!=null && fileForm.getFileImage().getFileName()!=null  && fileForm.getFileImage().getFileName().length()>0){
			file=(FormFile) fileForm.getFileImage();
			fileForm.setImagename(file.getFileName());
        	filaData.setImagename(file.getFileName());
	        String path= SecurityConstants.APPJJ_DIRECTORY+"files/" + filaData.getCategoryName().toLowerCase()+"/"+filaData.getDocumentname();
	        if(sys.getCorporationId()==CorporationData.CEPA) {
	        	path = SecurityConstants.APPCEPA_DIRECTORY+"/files/"+filaData.getLng().replaceFirst("module.data.name.", "").toUpperCase()+"/" + filaData.getCategoryName().toLowerCase()+"/"+filaData.getDocumentname();
	        }
	        
	        File fileDelete=new File(path);
	        if(fileDelete.exists()){
	        	fileDelete.delete();
	        }
	        	
			InputStream reportStream = file.getInputStream();
			FileOutputStream fout = new FileOutputStream(path);
			
			int c = 0;
			while ( (c=reportStream.read()) != -1){
				fout.write(c);
			}			
			fout.flush();
			fout.close();	
		}
		
		
		
		sys.getCorporationABMFacade().saveAppFile(filaData, sys.getUserSessionData());
 		sys.getUserSessionData().setUrl("/appfiles/files.jsp");
        return mapping.findForward("next");
 		
    }

	
	public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
 		sys.getUserSessionData().setUrl("/appfiles/files.jsp");
        return mapping.findForward("next");
    }

}
