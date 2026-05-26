/*
 * Created on 01-sep-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package persistence.administration.corporation.tables;

import java.io.Serializable;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

import communication.administration.corporation.tables.PointMoveData;

public class PointMoveLog implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String userId;
    private Integer action;
    private String comment;
    private Date actionDate;
    private Integer move;
    private Integer account;
	private Date moveDate;
	private Time hour;
	private Double amount;
	private Double actualTotal;
	private Integer definition;
	private String reference;
	private String description;
	
	
	public PointMoveLog() {
	}

	public PointMoveLog(PointMoveData data) {
		this.move=data.getId();
		this.account=data.getAccount();
		this.amount=data.getAmount();
		this.moveDate=data.getDate().getTime();
		this.definition = data.getDefinition();
		Time time=new Time(data.getDate().get(Calendar.HOUR_OF_DAY), data.getDate().get(Calendar.MINUTE), data.getDate().get(Calendar.SECOND));
		this.hour=time;
		this.reference=data.getReference();
		this.actualTotal=data.getActualTotal();
		this.description=data.getDescription();
	}


	public Double getActualTotal() {
		return actualTotal;
	}

	public void setActualTotal(Double actualTotal) {
		this.actualTotal = actualTotal;
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Date getMoveDate() {
		return moveDate;
	}


	public void setMoveDate(Date date) {
		this.moveDate = date;
	}


	public Time getHour() {
		return hour;
	}


	public void setHour(Time hour) {
		this.hour = hour;
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


	public Integer getAccount() {
		return account;
	}


	public void setAccount(Integer account) {
		this.account = account;
	}


	public Double getAmount() {
		return amount;
	}


	public void setAmount(Double amount) {
		this.amount = amount;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getAction() {
		return action;
	}

	public void setAction(Integer action) {
		this.action = action;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getActionDate() {
		return actionDate;
	}

	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}

	public Integer getMove() {
		return move;
	}

	public void setMove(Integer move) {
		this.move = move;
	}


}