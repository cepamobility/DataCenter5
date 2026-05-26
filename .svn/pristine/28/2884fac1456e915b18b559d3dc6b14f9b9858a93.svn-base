package presentation.translator.web.event;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

public class UploadFineForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	private FormFile file;
	private String notes;
	
	/**
	* Reset all properties to their default values.
	*
	* @param mapping The mapping used to select this instance
	* @param request The servlet request we are processing
	*/
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		file = null;
		notes = null;
	}
		
	public UploadFineForm(FormFile file, String notes) {
		super();
		this.file = file;
		this.notes = notes;
	}
	
	public UploadFineForm() {
		super();
		file = null;
		notes = null;
	}

	public FormFile getFile() {
		return file;
	}

	public void setFile(FormFile file) {
		this.file = file;
	}
	
	public String getNotes(){
		return this.notes;
	}
	public void setNotes(String notes){
		this.notes = notes;
	}

}
