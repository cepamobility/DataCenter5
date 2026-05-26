package presentation.translator.web.documents;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import business.SystemAccess;

import communication.util.tables.AppFileData;


public class AppFileForm extends ActionForm {

	private String id;
	private String title;    	
	private String subtitle;    	
	private String description; 
	private String category;
	private String doctype;    	
	private String documentname; 
	private FormFile file;
	private FormFile fileImage;
	 

	private String imagename;    
	private String order;   
	private String language;
		

    public AppFileForm() {
    }

	public AppFileForm(AppFileData file) {
		this.id = String.valueOf(file.getId());
		this.title = file.getTitle();
		this.subtitle = file.getSubtitle();
		this.description = file.getDescription();
		this.category = String.valueOf(file.getCategory());
		this.doctype = String.valueOf(file.getDoctype());
		this.documentname = file.getDocumentname();
		this.imagename = file.getImagename();
		this.order = String.valueOf(file.getOrder());
		this.language=file.getLng();
		 
	}

	
	
	
	public FormFile getFileImage() {
		return fileImage;
	}

	public void setFileImage(FormFile fileImage) {
		this.fileImage = fileImage;
	}

	public FormFile getFile() {
		return file;
	}

	public void setFile(FormFile file) {
		this.file = file;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getSubtitle() {
		return subtitle;
	}


	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	
	public String getDoctype() {
		return doctype;
	}


	public void setDoctype(String doctype) {
		this.doctype = doctype;
	}


	public String getDocumentname() {
		return documentname;
	}


	public void setDocumentname(String documentname) {
		this.documentname = documentname;
	}


	public String getImagename() {
		return imagename;
	}


	public void setImagename(String imagename) {
		this.imagename = imagename;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public AppFileData getData(SystemAccess sys) {
		Integer idFile = (id == null || id.length() == 0) ? null : new Integer(id);
		return new AppFileData(idFile,title,subtitle,description,new Integer(category),new Integer(doctype),documentname,imagename,new Integer(sys.getCorporationId()),new Integer(order),this.language);
	}
}
