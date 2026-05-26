package persistence.administration.corporation.tables;

import java.io.Serializable;

public class CorporationDirectory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer corporationId;
	private String directory;
	
	public CorporationDirectory() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CorporationDirectory(Integer corporationId, String directory){
		this.corporationId = corporationId;
		this.directory = directory;
	}

	/**
	 * @return Returns the directory.
	 */
	public String getDirectory() {
		return directory;
	}

	/**
	 * @param directory The directory to set.
	 */
	public void setDirectory(String directory) {
		this.directory = directory;
	}

	/**
	 * @return Returns the corporationId.
	 */
	public Integer getCorporationId() {
		return corporationId;
	}

	/**
	 * @param corporationId The corporationId to set.
	 */
	public void setCorporationId(Integer corporationId) {
		this.corporationId = corporationId;
	}

	
	
	
	

}
