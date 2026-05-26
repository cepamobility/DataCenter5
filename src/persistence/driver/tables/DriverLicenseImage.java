package persistence.driver.tables;

import java.util.Date;

import communication.driver.tables.DriverLicenseImageData;


public class DriverLicenseImage {
	
	private Integer id;
	private String image;
	private String file;
	private Date date;
	
	public DriverLicenseImage() {
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

	public DriverLicenseImageData getData() {
		return new DriverLicenseImageData(id, image, file, date);
	}
}
