/*
 * Created on 01-sep-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package persistence.administration.corporation.tables;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import communication.administration.corporation.tables.PointAccountData;
import communication.administration.corporation.tables.PointMoveData;

/**
 * @author fcarriquiry
 * 
 */
public class PointAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
	private Integer type;
    private Integer reference;
   	private Double total;
	private Date created;
	 
	public PointAccount() {
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}


	public Integer getReference() {
		return reference;
	}


	public void setReference(Integer reference) {
		this.reference = reference;
	}


	public Double getTotal() {
		return total;
	}


	public void setTotal(Double total) {
		this.total = total;
	}


	public Date getCreated() {
		return created;
	}


	public void setCreated(Date created) {
		this.created = created;
	}


	public PointAccountData getData() {
		PointAccountData data=new PointAccountData();
		data.setId(id);
		data.setMoves(new ArrayList<PointMoveData>());
		data.setReference(reference);
		data.setTotal(total);
		data.setCreated(created);
		data.setType(type);
		 
		
		return data;
	}


}