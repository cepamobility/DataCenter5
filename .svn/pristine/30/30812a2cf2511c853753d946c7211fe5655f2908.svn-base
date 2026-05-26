package persistence.driver.tables;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import persistence.administration.corporation.tables.Corporation;
import persistence.administration.user.tables.User;
import persistence.datatype.tables.CsvEntityType;

import communication.administration.user.tables.UserAttributes;
import communication.datatype.tables.CsvEntityTypeDataType;
import communication.driver.tables.CsvEntityData;

public class CsvEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer idCsv;
	private Date downloadDate;
	private Date uploadDate;
	private CsvEntityType entity;
	private User user; // Usuario que solicito la actualizacion...
	private Corporation corporation; // Corporacion de las entidades a actualizar. 
	// (para restrinjir en caso de cambiar los ids de las entidades en los archivos csv)
	
	private Set<CsvEntityField> fieldSet;
	
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
	public CsvEntityType getEntity() {
		return entity;
	}
	/**
	 * @param entity the entity to set
	 */
	public void setEntity(CsvEntityType entity) {
		this.entity = entity;
	}
	
	
	
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	public CsvEntity(Integer idCsv, Date downloadDate, Date uploadDate,
			CsvEntityType entity, User user) {
		super();
		this.idCsv = idCsv;
		this.downloadDate = downloadDate;
		this.uploadDate = uploadDate;
		this.entity = entity;
		this.user = user;
		fieldSet = null;
	}
	
	public CsvEntity(Integer idCsv, Date downloadDate, Date uploadDate,
			CsvEntityType entity, User user,Set<CsvEntityField> fieldSet) {
		super();
		this.idCsv = idCsv;
		this.downloadDate = downloadDate;
		this.uploadDate = uploadDate;
		this.entity = entity;
		this.user = user;
		this.fieldSet = fieldSet;
	}
	
	public CsvEntity() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCsv == null) ? 0 : idCsv.hashCode());
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CsvEntity other = (CsvEntity) obj;
		if (idCsv == null) {
			if (other.idCsv != null)
				return false;
		} else if (!idCsv.equals(other.idCsv))
			return false;
		return true;
	}
	/**
	 * @return the fieldSet
	 */
	public Set<CsvEntityField> getFieldSet() {
		return fieldSet;
	}
	/**
	 * @param fieldSet the fieldSet to set
	 */
	public void setFieldSet(Set<CsvEntityField> fieldSet) {
		this.fieldSet = fieldSet;
	}
	
	
	/**
	 * @return the corporation
	 */
	public Corporation getCorporation() {
		return corporation;
	}
	/**
	 * @param corporation the corporation to set
	 */
	public void setCorporation(Corporation corporation) {
		this.corporation = corporation;
	}
	public CsvEntityData getCsvEntityData(){
		CsvEntityTypeDataType entityDataType = 
			(entity != null) ? new CsvEntityTypeDataType(entity.getResourceBundleId()):null;
		UserAttributes userAtt = (user != null)? user.getPrincipalAttributes():null; 
		return new CsvEntityData(idCsv,downloadDate,uploadDate,entityDataType,userAtt);
	}
	
	/**
	 * 
	 * @param data CsvEntityData
	 */
	public CsvEntity(CsvEntityData data){
		this.idCsv = data.getIdCsv();
		this.downloadDate = data.getDownloadDate();
		this.uploadDate = data.getUploadDate();
		this.entity = new CsvEntityType(data.getEntity());
		this.user = new User(data.getUser());
		
		if (data.getFields() != null){
			Iterator it = data.getFields().iterator();
			this.fieldSet = new HashSet<CsvEntityField>();
			while (it.hasNext()){
				CsvEntityField field = (CsvEntityField)it.next();
				this.fieldSet.add(field);
			}
		}
	}
	
	/**
	 * 
	 * @param data
	 */
	public void updateData(CsvEntityData data){
		this.idCsv = data.getIdCsv();
		this.downloadDate = data.getDownloadDate();
		this.uploadDate = data.getUploadDate();
		this.entity = new CsvEntityType(data.getEntity());
		this.user = new User(data.getUser());
		
		if (data.getFields() != null){
			Iterator it = data.getFields().iterator();
			this.fieldSet = new HashSet<CsvEntityField>();
			while (it.hasNext()){
				CsvEntityField field = (CsvEntityField)it.next();
				this.fieldSet.add(field);
			}
		}else{
			this.fieldSet = null;
		}
	}
	
}
