package persistence.driver.tables;

import java.io.Serializable;

public class CsvEntityField implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private CsvEntityFieldPK pk;
	private Integer order;
	
	public CsvEntityField() {
		super();
	}

	public CsvEntityField(CsvEntityFieldPK pk, Integer order) {
		super();
		this.pk = pk;
		this.order = order;
	}

	/**
	 * @return the pk
	 */
	public CsvEntityFieldPK getPk() {
		return pk;
	}

	/**
	 * @param pk the pk to set
	 */
	public void setPk(CsvEntityFieldPK pk) {
		this.pk = pk;
	}

	/**
	 * @return the order
	 */
	public Integer getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
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
		CsvEntityField other = (CsvEntityField) obj;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}
	
	
	
	

}
