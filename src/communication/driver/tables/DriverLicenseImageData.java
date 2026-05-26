package communication.driver.tables;

import java.util.Date;


public class DriverLicenseImageData {
	
	private Integer id;
	private String image;
	private String file;
	private Date date;
	
	public DriverLicenseImageData() {
	}

	
	public DriverLicenseImageData(Integer id, String image, String file, Date date) {
		super();
		this.id = id;
		this.image = image;
		this.file = file;
		this.date = date;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
