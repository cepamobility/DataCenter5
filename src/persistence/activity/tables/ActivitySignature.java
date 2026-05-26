package persistence.activity.tables;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import org.hibernate.Session;

import persistence.driver.tables.Driver;

public class ActivitySignature implements Serializable {	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private CEPAScheduleActivityRegistry registry;	
	private String signature;	
	
	public ActivitySignature() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CEPAScheduleActivityRegistry getRegistry() {
		return registry;
	}

	public void setRegistry(CEPAScheduleActivityRegistry registry) {
		this.registry = registry;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

}
