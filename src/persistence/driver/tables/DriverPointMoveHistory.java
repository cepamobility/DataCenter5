/**
 * Created on 22-jun-2007
 * CEPA
 * DataCenter 5
 */
package persistence.driver.tables;

import java.util.Date;


public class DriverPointMoveHistory {

    private DriverPointMoveHistoryPK pk;
    private Double amount;
    private Date date;
    private Integer definition;
    private String reference;
    private String moveReference;
    
    public DriverPointMoveHistory() {
    }

	public DriverPointMoveHistoryPK getPk() {
		return pk;
	}

	public void setPk(DriverPointMoveHistoryPK pk) {
		this.pk = pk;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getDefinition() {
		return definition;
	}

	public void setDefinition(Integer definition) {
		this.definition = definition;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getMoveReference() {
		return moveReference;
	}

	public void setMoveReference(String moveReference) {
		this.moveReference = moveReference;
	}
}