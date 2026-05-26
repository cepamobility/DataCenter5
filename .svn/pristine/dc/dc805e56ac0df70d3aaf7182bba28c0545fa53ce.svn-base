package presentation.translator.web.event;

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
import business.event.EventABMFacade;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.user.UserSessionData;
import communication.alert.AlertConstants;
import communication.language.Text;
import communication.security.SecurityConstants;
import communication.util.tables.AppFileData;

public class SaveFileAction extends AbstractAction {

    public ActionForward action(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
       
    	HttpSession session = request.getSession();
        SystemAccess sys = ((SystemAccess)session.getAttribute("SystemAccess"));
        UserSessionData userSessionData = sys.getUserSessionData();
        Text messages = sys.getText();
         
        SaveFileForm fileForm = (SaveFileForm)form;
    	
        
    	if(fileForm.getFile()!= null && fileForm.getFile().getFileName() != null &&  fileForm.getFile().getFileName().length() > 0){
    		FormFile inputFile = fileForm.getFile();
    		if(sys.isCorporation(CorporationData.MONSANTO) && !inputFile.getFileName().toLowerCase().endsWith(".pdf") && !inputFile.getFileName().toLowerCase().endsWith(".jpg")){
        		userSessionData.setMsg(messages.getText("event.file.wrongfiletype"));
    		} else if(!inputFile.getFileName().toLowerCase().endsWith(".pdf") && !sys.isCorporation(CorporationData.MONSANTO)) {
        		userSessionData.setMsg(messages.getText("event.file.wrongfiletype"));
    		} else if(inputFile.getFileSize() > 2097152) {
        		userSessionData.setMsg(messages.getText("event.file.bigfile"));
    		} else {
	    		EventABMFacade facade = sys.getEventABMFacade();
	    		Integer id = facade.createFile(new Integer(fileForm.getEvent()), userSessionData);
	    		
	    		boolean jpg = inputFile.getFileName().toLowerCase().endsWith(".jpg");
	    		int fileType = (jpg) ? 2 : 1;
		        String path= (jpg) ? AlertConstants.EVENT_DIRECTORY+"event_" + id + ".jpg" : AlertConstants.EVENT_DIRECTORY+"event_" + id + ".pdf";
		        
		        File fileDelete=new File(path);
		        if(fileDelete.exists()){
		        	fileDelete.delete();
		        }

		        InputStream reportStream = inputFile.getInputStream();
				FileOutputStream fout = new FileOutputStream(path);
				
				int c = 0;
				while ( (c=reportStream.read()) != -1){
					fout.write(c);
				}			
				fout.flush();
				fout.close();	
				
				String name = (Util.empty(fileForm.getName())) ? inputFile.getFileName().substring(0,inputFile.getFileName().length()-4) : fileForm.getName();
				facade.saveFile(id, name, fileType, userSessionData);
		    	session.removeAttribute("EventSaveFileForm");
    		}
    	} else {
    		userSessionData.setMsg(messages.getText("event.file.emptyfile"));
    	}

    	userSessionData.setUrl("event/files.jsp?type="+fileForm.getType()+"event="+fileForm.getEvent());
        return mapping.findForward("layout"); 		
    }

	public ActionForward cancel(HttpSession session,ActionMapping mapping, ActionForm form) {
        return mapping.findForward("layout"); 		
    }

}
