package communication.fleet.util;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

public class RegionBlockData implements Comparator, Comparable ,Serializable {

	private Integer corporationId;
	private Integer regionId;
	private String regionName;
	private Integer blockYear;
	private Integer blockMonth;
	private Date date;
	private String userId;
	private String comment;
	
	/**
	 * 
	 */
	public RegionBlockData() {
		super();		
	}
	
	/**
	 * @param regionId
	 * @param regionName
	 * @param blockYear
	 * @param blockMonth
	 */
	public RegionBlockData(Integer corporationId, Integer regionId, String regionName, 
			Integer blockYear, Integer blockMonth, Date date, String userId, String comment) {
		super();		
		this.regionId = regionId;
		this.regionName = regionName;
		this.blockYear = blockYear;
		this.blockMonth = blockMonth;
		this.corporationId = corporationId;
		this.date = date;
		this.userId = userId;
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

	/**
	 * Para ordenar una lista por nombre de region alfabeticamente.
	 * @param arg0
	 * @param arg1
	 * @return
	 */
	public int compare(Object arg0, Object arg1) {
		if (arg0.getClass().isInstance(RegionBlockData.class) &&
			arg1.getClass().isInstance(RegionBlockData.class)){
			RegionBlockData rbd0 = (RegionBlockData)arg0;
			RegionBlockData rbd1 = (RegionBlockData)arg1;
			return (rbd0.getRegionName().trim().
					compareToIgnoreCase(rbd1.getRegionName().trim()));
		}else
			return 0;
	}

	/**
 	 * Para ordenar una lista por nombre de region alfabeticamente.
	 * @param arg0
	 * @return
	 */
	public int compareTo(Object arg0) {
		RegionBlockData rbd0 = (RegionBlockData)arg0;
		return this.getRegionName().trim().compareTo(rbd0.getRegionName().trim());
		
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

	
	
}
