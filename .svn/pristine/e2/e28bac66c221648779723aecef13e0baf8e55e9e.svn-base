package persistence.driver.tables;

import java.io.Serializable;

public class CsvEntityFieldPK implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private CsvEntity csvEntity;
	private String field;
	/**
	 * @return the csvEntity
	 */
	public CsvEntity getCsvEntity() {
		return csvEntity;
	}
	/**
	 * @param csvEntity the csvEntity to set
	 */
	public void setCsvEntity(CsvEntity csvEntity) {
		this.csvEntity = csvEntity;
	}
	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}
	/**
	 * @param field the field to set
	 */
	public void setField(String field) {
		this.field = field;
	}
	public CsvEntityFieldPK(CsvEntity csvEntity, String field) {
		super();
		this.csvEntity = csvEntity;
		this.field = field;
	}
	
	public CsvEntityFieldPK() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((csvEntity == null) ? 0 : csvEntity.hashCode());
		result = prime * result + ((field == null) ? 0 : field.hashCode());
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
		CsvEntityFieldPK other = (CsvEntityFieldPK) obj;
		if (csvEntity == null) {
			if (other.csvEntity != null)
				return false;
		} else if (!csvEntity.equals(other.csvEntity))
			return false;
		if (field == null) {
			if (other.field != null)
				return false;
		} else if (!field.equals(other.field))
			return false;
		return true;
	}
	
	
	
}
