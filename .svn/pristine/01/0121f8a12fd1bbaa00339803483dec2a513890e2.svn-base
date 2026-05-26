package communication.util.tables;

public class AppFileData {

	private Integer id;
	private String title;    	
	private String subtitle;    	
	private String description; 
	private Integer category;
	private Integer doctype;    	
	private String documentname;    	
	private String imagename;
	private Integer corporation;
	private Integer order;    
	private String lng;
		
	public AppFileData() {
		super();
	}

	public AppFileData(Integer id, String title, String subtitle,
			String description, Integer category, Integer doctype,
			String documentname, String imagename, Integer corporation, Integer order,String lng) {
		super();
		this.id = id;
		this.title = title;
		this.subtitle = subtitle;
		this.description = description;
		this.category = category;
		this.doctype = doctype;
		this.documentname = documentname;
		this.imagename = imagename;
		this.corporation = corporation;
		this.order = order;
		this.lng=lng;
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

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getDoctypeName() {
		switch(doctype.intValue()) {
			case 1:
				return "PDF";
			case 2:
				return "PPS";
			case 3:
				return "HTML";
		}
		return "---";
	}

	public String getCategoryName() {
		switch(category.intValue()) {
			case 1:
				return "Politics";
			case 2:
				return "Tips";
			case 3:
				return "News";
		}
		return "---";
	}

	public Integer getCorporation() {
		return corporation;
	}

	public Integer getOrder() {
		return order;
	}

	public void setCorporation(Integer corporation) {
		this.corporation = corporation;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

}
