package persistence.fleet.tables;

import java.io.Serializable;
import java.util.Date;

public class RegionBlockPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer regionId;
	private Date date;
	private String userId;

	public RegionBlockPK() {
		super();		
	}

	
	/**
	 * @param regionId
	 * @param date
	 * @param userId
	 */
	public RegionBlockPK(Integer regionId, Date date, String userId) {
		super();
		this.regionId = regionId;
		this.date = date;
		this.userId = userId;
	}


	/**
	 * @return Returns the date.
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date The date to set.
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return Returns the regionId.
	 */
	public Integer getRegionId() {
		return regionId;
	}

	/**
	 * @param regionId The regionId to set.
	 */
	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	/**
	 * @return Returns the userId.
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId The userId to set.
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public boolean equals(Object o){
		if (o.getClass().isInstance(RegionBlockPK.class)){
			RegionBlockPK rb = (RegionBlockPK)o;
			if ((this.regionId.equals(rb.regionId)) &&
				(this.date.equals(rb.date)) &&
				(this.userId.equals(rb.userId))){
				return true;
			}
		}				
		return false;
		
	}

	public int hashCode(){
		return super.hashCode();
	}

}
