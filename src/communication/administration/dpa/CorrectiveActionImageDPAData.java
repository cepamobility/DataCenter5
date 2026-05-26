package communication.administration.dpa;

import java.io.InputStream;

public class CorrectiveActionImageDPAData {
	
	private InputStream inputStream;
	
	private String fileName;
	
	private String text="A";
	
	public CorrectiveActionImageDPAData(){}

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

}
