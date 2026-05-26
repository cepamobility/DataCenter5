package communication.activity.report;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import communication.activity.tables.PointItemData;

/**
 * @author gbenaderet
 *
 */
public class ActivityReportTabletDataType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<PointItemData> pointItem;	

	public ActivityReportTabletDataType() {		
		pointItem = new ArrayList<PointItemData>();
	}

	/**
	 * @param pointComment
	 * @param pointItem
	 */
	public ActivityReportTabletDataType(ArrayList<PointItemData> pointItem) {		
		this.pointItem = pointItem;
	}

	

	/**
	 * @return Returns the pointItem.
	 */
	public ArrayList<PointItemData> getPointItem() {
		return pointItem;
	}

	/**
	 * @param pointItem The pointItem to set.
	 */
	public void setPointItem(ArrayList<PointItemData> pointItem) {
		this.pointItem = pointItem;
	}

	public int getPointCount(Integer id) {
		Iterator<PointItemData> it = pointItem.iterator();
		int count = 0;
		while(it.hasNext()) {
			PointItemData point = it.next();
			if(point.getPoint().getId().equals(id) && point.getItem().getType() != null && point.getItem().getType().intValue() == 1)
				count++;
		}
		return count;
	}

	public boolean isItem(Integer pointId, Integer itemId) {
		Iterator<PointItemData> it = pointItem.iterator();
		while(it.hasNext()) {
			PointItemData point = it.next();
			if(point.getPoint().getId().equals(pointId) && point.getItem().getId().equals(itemId)) {
				return true;
			}
		}
		return false;
	}	
	
}
