/*
 * Created on 01-sep-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package persistence.administration.corporation.tables;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.jboss.util.threadpool.ThreadPoolFullException;

import persistence.util.LabelValue;
import persistence.util.Util;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.GeoLabelAttributes;
import communication.administration.corporation.tables.GeoLabelData;
import communication.administration.corporation.tables.InstructorRegistryData;

/**
 * @author fcarriquiry
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class InstructorRegistry implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String comment;
    private int count;
    private Date date;
    private String loginname;
    private String type;
   
	public InstructorRegistry() {
	}

	public InstructorRegistry(InstructorRegistryData data){
		try{
			BeanUtils.copyProperties(this, data);	
		}catch(Throwable th){
			th.printStackTrace();
		}
	  
	}
	
	public InstructorRegistryData getData(){
		InstructorRegistryData data=new InstructorRegistryData();
		try {
			BeanUtils.copyProperties(data,this);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
}