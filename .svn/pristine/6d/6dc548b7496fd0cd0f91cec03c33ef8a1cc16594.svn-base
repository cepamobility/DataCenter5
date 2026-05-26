/**
 * 
 */
package persistence.fleet.tables;

import java.io.Serializable;
import java.util.Date;

import communication.fleet.util.RegionBlockData;

/**
 * @author gbenaderet
 *
 */
public class RegionBlock implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer corporationId;	
	private String regionName;
	private Integer blockMonth;
	private Integer blockYear;
	private String comment;
	private RegionBlockPK id;
	
	/**
	 * 
	 */
	public RegionBlock() {
		super();	
	}

	
	/**
	 * @param corporationId
	 * @param regionId
	 * @param regionName
	 * @param blockMonth
	 * @param blockYear
	 * @param date
	 * @param userId
	 */
	public RegionBlock(Integer corporationId, Integer regionId, String regionName, Integer blockMonth, Integer blockYear, Date date, String userId, String comment) {
		super();
		this.id = new RegionBlockPK(regionId,date,userId);		
		this.corporationId = corporationId;
		this.regionName = regionName;
		this.blockMonth = blockMonth;
		this.blockYear = blockYear;
		this.comment = comment;
	}


	/**
	 * @return Returns the blockMonth.
	 */
	public Integer getBlockMonth() {
		return blockMonth;
	}

	/**
	 * @param blockMonth The blockMonth to set.
	 */
	public void setBlockMonth(Integer blockMonth) {
		this.blockMonth = blockMonth;
	}

	/**
	 * @return Returns the blockYear.
	 */
	public Integer getBlockYear() {
		return blockYear;
	}

	/**
	 * @param blockYear The blockYear to set.
	 */
	public void setBlockYear(Integer blockYear) {
		this.blockYear = blockYear;
	}

	/**
	 * @return Returns the corporationId.
	 */
	public Integer getCorporationId() {
		return corporationId;
	}

	/**
	 * @param corporationId The corporationId to set.
	 */
	public void setCorporationId(Integer corporationId) {
		this.corporationId = corporationId;
	}


	/**
	 * @return Returns the regionName.
	 */
	public String getRegionName() {
		return regionName;
	}

	/**
	 * @param regionName The regionName to set.
	 */
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	
	/**
	 * 
	 * @param data
	 */
	public RegionBlock(RegionBlockData data)
	{
		this.id = new RegionBlockPK(data.getRegionId(),data.getDate(),data.getUserId());
		this.blockMonth = data.getBlockMonth();
		this.blockYear = data.getBlockYear();
		this.corporationId = data.getCorporationId();
		this.regionName = data.getRegionName();
		this.comment = data.getComment();

	}
	
	public boolean equals(Object o){
		if (o.getClass().isInstance(RegionBlock.class)){
			RegionBlock rb = (RegionBlock)o;
			if (this.id.equals(rb.id)){
				return true;
			}
		}				
		return false;
		
	}


	/**
	 * @return Returns the comment.
	 */
	public String getComment() {
		return comment;
	}


	/**
	 * @param comment The comment to set.
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}


	/**
	 * @return Returns the id.
	 */
	public RegionBlockPK getId() {
		return id;
	}


	/**
	 * @param id The id to set.
	 */
	public void setId(RegionBlockPK id) {
		this.id = id;
	}
	
	

}
