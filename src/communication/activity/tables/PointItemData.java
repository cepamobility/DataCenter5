package communication.activity.tables;
import java.io.Serializable;

import communication.activity.tables.ItemData;
import communication.activity.tables.PointData;

public class PointItemData implements Serializable,Comparable {

    private static final long serialVersionUID = 1L;

    PointData point;
    ItemData item;

    /**
     * @return Returns the point.
     */
    public PointData getPoint() {
        return point;
    }

    /**
     * @param point The point to set.
     */
    public void setPoint(PointData point) {
        this.point = point;
    }
    
    public PointItemData(PointData point,ItemData item) {
        this.point = point;
        this.item = item;
    }

    /**
     * @return Returns the item.
     */
    public ItemData getItem() {
        return item;
    }

    /**
     * @param item The item to set.
     */
    public void setItem(ItemData item) {
        this.item = item;
    }
    
    public int compareTo(Object obj){
        PointItemData o = (PointItemData)obj;
        Integer thisOrder = new Integer(this.getPoint().getPointOrderReport());
        Integer objOrder = new Integer(o.getPoint().getPointOrderReport());
        int compare1 = (thisOrder).compareTo(objOrder);
        if (compare1 == 0){
        	Integer thisOrder2 = new Integer(this.getItem().getItemOrder());
        	Integer objOrder2 = new Integer(o.getItem().getItemOrder());
        	int compare2 = (thisOrder2).compareTo(objOrder2);
        	return compare2;
        }else 
             return compare1;
    }

}
