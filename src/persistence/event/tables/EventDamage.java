package persistence.event.tables;

import java.io.Serializable;

public class EventDamage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	/* Numero del CheckBox marcado */
	private Integer damage; 
	
	
	public EventDamage() {
		super();
	}

	/**
	 * @param eventid
	 * @param damage
	 */
	public EventDamage(Integer damage) {
		super();
		// TODO Auto-generated constructor stub
		
		this.damage = damage;
	}
	
	public EventDamage(Integer id, Integer damage) {
		super();
		// TODO Auto-generated constructor stub
		this.id = id;
		
		this.damage = damage;
	}
	
	
	

	/**
	 * @return Returns the id.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return Returns the damage.
	 */
	public Integer getDamage() {
		return damage;
	}

	/**
	 * @param damage The damage to set.
	 */
	public void setDamage(Integer damage) {
		this.damage = damage;
	}
	

}
