package communication.vehicle.report;

import java.io.InputStream;
import java.io.Serializable;

public class CheckListImageData implements Serializable {
	
	private InputStream inputStream;
	
	private String fileName;
	
	private Integer pointId;
	
	private Integer checkId;
	
	private String text;
	
	private String imageBase64;
	

	public CheckListImageData(){}


	public CheckListImageData(Integer pointId, String fileName, String text) {
		this.pointId = pointId;
		this.fileName = fileName;
		this.text = text;
	}
	public String getImageBase64() {
		return imageBase64;
	}

	public void setImageBase64(String imageBase64) {
		this.imageBase64 = imageBase64;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getPointId() {
		return pointId;
	}

	public void setPointId(Integer pointId) {
		this.pointId = pointId;
	}

	public Integer getCheckId() {
		return checkId;
	}

	public void setCheckId(Integer checkId) {
		this.checkId = checkId;
	}
	
	

}
