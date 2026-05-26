package presentation.translator.web.vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import business.SystemAccess;

import communication.util.tables.AppFileData;


public class DPAImageUploadForm extends ActionForm {

	public class ItemAux{
		
		private String point;
		private FormFile file;
		private String text;
		public String getPoint() {
			return point;
		}
		public void setPoint(String point) {
			this.point = point;
		}
		public FormFile getFile() {
			return file;
		}
		public void setFile(FormFile file) {
			this.file = file;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		
	}
	
	private String id;
	private String documentname; 
	private String point;
	private HashMap<Integer, ItemAux> map;
		

    public DPAImageUploadForm() {
    	this.map=new HashMap<Integer, DPAImageUploadForm.ItemAux>();
    }
	  

	public String getPoint() {
		return point;
	}


	public void setPoint(String point) {
		this.point = point;
	}



	public void setFiles(int index,FormFile file) {
		add(index);
		this.map.get(index).file=file;
	}




	public void setFilesOptions(int index,String op) {
		add(index);	
		this.map.get(index).point=op;
	}
	

	public void setFilesTexts(int index,String op) {
		add(index);	
		this.map.get(index).text=op;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	public HashMap<Integer, ItemAux> getItems(){
		return map;
	}


	public String getDocumentname() {
		return documentname;
	}


	public void setDocumentname(String documentname) {
		this.documentname = documentname;
	}


	private void add(int index) {
		if(!map.containsKey(new Integer(index))){
			map.put(new Integer(index), new ItemAux());
		}
	}


}
