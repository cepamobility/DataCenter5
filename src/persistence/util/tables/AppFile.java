package persistence.util.tables;

import communication.util.tables.AppFileData;

public class AppFile {

	private Integer id;
	private String title;    	
	private String subtitle;    	
	private String description; 
	private Integer category;
	private Integer doctype;    	
	private String documenturl;    	
	private String documentname;    	
	private String imageurl;    	
	private String imagename;   
	private Integer corporation;
	private Integer fileorder;
	private String  lng;
		
	public AppFile() {
	}

	public AppFile(AppFileData fileData) {
		this.id = fileData.getId();
		this.title = fileData.getTitle();
		this.subtitle = fileData.getSubtitle();
		this.description = fileData.getDescription();
		this.category = fileData.getCategory();
		this.doctype = fileData.getDoctype();
		this.documentname = fileData.getDocumentname();
		this.imagename = fileData.getImagename();
		this.corporation = fileData.getCorporation();
		this.fileorder = fileData.getOrder();
		this.lng= fileData.getLng();
	}

	
	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Integer getDoctype() {
		return doctype;
	}

	public void setDoctype(Integer doctype) {
		this.doctype = doctype;
	}

	public String getDocumenturl() {
		return documenturl;
	}

	public void setDocumenturl(String documenturl) {
		this.documenturl = documenturl;
	}

	public String getDocumentname() {
		return documentname;
	}

	public void setDocumentname(String documentname) {
		this.documentname = documentname;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public String getImagename() {
		return imagename;
	}

	public void setImagename(String imagename) {
		this.imagename = imagename;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public AppFileData getFileData() {
		return new AppFileData(id,title,subtitle,description,category,doctype,documentname,imagename,corporation,fileorder,this.lng);
	}

	public Integer getCorporation() {
		return corporation;
	}

	public void setCorporation(Integer corporation) {
		this.corporation = corporation;
	}

	public Integer getFileorder() {
		return fileorder;
	}

	public void setFileorder(Integer order) {
		this.fileorder = order;
	}

}
