package persistence.activity.tables;

import java.io.Serializable;

public class PointItem implements Serializable,Comparable {

    private static final long serialVersionUID = 1L;

    Point point;
    Item item;

    /**
     * @return Returns the point.
     */
    public Point getPoint() {
        return point;
    }

    /**
     * @param point The point to set.
     */
    public void setPoint(Point point) {
        this.point = point;
    }
    
    public PointItem (Point point,Item item) {
        this.point = point;
        this.item = item;
    }

    /**
     * @return Returns the item.
     */
    public Item getItem() {
        return item;
    }

    /**
     * @param item The item to set.
     */
    public void setItem(Item item) {
        this.item = item;
    }
    
    public int compareTo(Object obj){
        PointItem o = (PointItem)obj;
        Integer thisOrder = new Integer(this.getPoint().getPointOrderReport());
        Integer objOrder = new Integer(o.getPoint().getPointOrderReport());
        return (thisOrder).compareTo(objOrder);
    }

}
