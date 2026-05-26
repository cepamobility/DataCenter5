package persistence.travel.tables;

import java.sql.Time;
import java.util.Date;

import org.hibernate.classic.Session;

import persistence.event.tables.Travel;

import communication.travel.tables.CheckPointData;

public class CheckPoint {

	private Integer id;
	private Date date;
	private Time hour;
	private String local;
	private String way;
	private String communication;
	private boolean incidents;
	private String comment;
	private Integer number;
	private Travel travel;

	public CheckPoint() {
	}

	public CheckPoint(CheckPointData data, Integer travelId, Session session) {
		this.id = data.getId();
		this.travel = (Travel)session.load(Travel.class, travelId);
		this.date = data.getDate();
		this.hour = data.getHour();
		this.local = data.getLocal();
		this.way = data.getWay();
		this.communication = data.getCommunication();
		this.incidents = data.isIncidents();
		this.comment = data.getComment();
		this.number = data.getNumber();
	}

	public void updateData(CheckPointData data) {
		this.date = data.getDate();
		this.hour = data.getHour();
		this.local = data.getLocal();
		this.way = data.getWay();
		this.communication = data.getCommunication();
		this.incidents = data.isIncidents();
		this.comment = data.getComment();
		this.number = data.getNumber();
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

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getWay() {
		return way;
	}

	public void setWay(String way) {
		this.way = way;
	}

	public String getCommunication() {
		return communication;
	}

	public void setCommunication(String communication) {
		this.communication = communication;
	}

	public boolean isIncidents() {
		return incidents;
	}

	public void setIncidents(boolean incidents) {
		this.incidents = incidents;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Travel getTravel() {
		return travel;
	}

	public void setTravel(Travel travel) {
		this.travel = travel;
	}

	public CheckPointData getData() {
		return new CheckPointData(id, date, hour, local, way, communication, incidents, comment, number);
	}

	public Time getHour() {
		return hour;
	}

	public void setHour(Time hour) {
		this.hour = hour;
	}

	
}
