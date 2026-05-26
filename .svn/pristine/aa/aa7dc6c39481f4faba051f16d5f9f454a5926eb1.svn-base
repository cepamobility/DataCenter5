package communication.driver.tables;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import communication.administration.user.tables.UserAttributes;
import communication.administration.user.tables.UserData;
import communication.datatype.tables.CsvEntityTypeDataType;

public class CsvEntityData implements Serializable {

	private Integer idCsv;
	private Date downloadDate;
	private Date uploadDate;
	
	private CsvEntityTypeDataType entity;
	private UserAttributes user;
	
	private Collection fields;
	
	/**
	 * 
	 * @param idCsv
	 * @param downloadDate
	 * @param uploadDate
	 * @param entity
	 * @param user
	 */
	public CsvEntityData(Integer idCsv, Date downloadDate, Date uploadDate,
			CsvEntityTypeDataType entity, UserAttributes user) {
		super();
		this.idCsv = idCsv;
		this.downloadDate = downloadDate;
		this.uploadDate = uploadDate;
		this.entity = entity;
		this.user = user;
		this.fields = null;
	}

	/**
	 * 
	 * @param idCsv
	 * @param downloadDate
	 * @param uploadDate
	 * @param entity
	 * @param user
	 */
	public CsvEntityData(Integer idCsv, Date downloadDate, Date uploadDate,
			CsvEntityTypeDataType entity, UserAttributes user, Collection fields) {
		super();
		this.idCsv = idCsv;
		this.downloadDate = downloadDate;
		this.uploadDate = uploadDate;
		this.entity = entity;
		this.user = user;
		this.fields = fields;
	}
	
	public CsvEntityData() {
		super();
	}

	/**
	 * @return the idCsv
	 */
	public Integer getIdCsv() {
		return idCsv;
	}

	/**
	 * @param idCsv the idCsv to set
	 */
	public void setIdCsv(Integer idCsv) {
		this.idCsv = idCsv;
	}

	/**
	 * @return the downloadDate
	 */
	public Date getDownloadDate() {
		return downloadDate;
	}

	/**
	 * @param downloadDate the downloadDate to set
	 */
	public void setDownloadDate(Date downloadDate) {
		this.downloadDate = downloadDate;
	}

	/**
	 * @return the uploadDate
	 */
	public Date getUploadDate() {
		return uploadDate;
	}

	/**
	 * @param uploadDate the uploadDate to set
	 */
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	/**
	 * @return the entity
	 */
	public CsvEntityTypeDataType getEntity() {
		return entity;
	}

	/**
	 * @param entity the entity to set
	 */
	public void setEntity(CsvEntityTypeDataType entity) {
		this.entity = entity;
	}

	/**
	 * @return the user
	 */
	public UserAttributes getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(UserData user) {
		this.user = user;
	}

	/**
	 * @return the fields
	 */
	public Collection getFields() {
		return fields;
	}

	/**
	 * @param fields the fields to set
	 */
	public void setFields(Collection fields) {
		this.fields = fields;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(UserAttributes user) {
		this.user = user;
	}
	
	
	
	
	
}
