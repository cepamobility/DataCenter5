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

/**
 * @author fcarriquiry
 * 
 */
public class PointMove implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer account;
	private Date date;
	private Time hour;
	private Double amount;
	private Double actualTotal;
	private Integer definition;
	private String reference;
	private String description;
	private Integer state;	
	
	public PointMove() {
	}

	public PointMove(PointMoveData data) {
		this.account=data.getAccount();
		this.amount=data.getAmount();
		this.date=data.getDate().getTime();
		this.definition = data.getDefinition();
		Time time=new Time(data.getDate().get(Calendar.HOUR_OF_DAY), data.getDate().get(Calendar.MINUTE), data.getDate().get(Calendar.SECOND));
		this.hour=time;
		this.reference=data.getReference();
		this.actualTotal=data.getActualTotal();
		this.description=data.getDescription();
		this.state=data.getState();
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


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
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

	public PointMoveData getData() {
		PointMoveData data=new PointMoveData();
		data.setId(id);
		data.setAmount(amount);
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR,hour.getHours());
		cal.set(Calendar.MINUTE,hour.getMinutes());
		cal.set(Calendar.SECOND,hour.getSeconds());
		data.setDate(cal);
		data.setDefinition(definition);
		data.setAccount(account);
		data.setReference(reference);
		data.setActualTotal(actualTotal);
		data.setDescription(description);
		data.setState(state);
		return data;
	}

	public void setData(PointMoveData data) {
		this.account=data.getAccount();
		this.amount=data.getAmount();
		this.date=data.getDate().getTime();
		this.definition = data.getDefinition();
		Time time=new Time(data.getDate().get(Calendar.HOUR), data.getDate().get(Calendar.MINUTE), data.getDate().get(Calendar.SECOND));
		this.hour=time;
		this.reference=data.getReference();
		this.description=data.getDescription();
		this.state=data.getState();
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

 
}