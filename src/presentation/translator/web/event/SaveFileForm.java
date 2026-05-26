package presentation.translator.web.event;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import business.SystemAccess;

import communication.util.tables.AppFileData;


public class SaveFileForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String name; 
	private String event; 
	private String type;
	private FormFile file;
	 
    public SaveFileForm() {
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FormFile getFile() {
		return file;
	}

	public void setFile(FormFile file) {
		this.file = file;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
