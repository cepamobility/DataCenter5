package persistence.vehicle.tables;

import java.io.Serializable;
import java.util.Comparator;

import persistence.activity.tables.Point;

import communication.activity.tables.PointCommentData;

public class PointComment  implements Serializable, Comparator, Comparable{

	private Point point;
	private String comment;
	/**
	 * Serializable:
	 */
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public PointComment() {
		super();		
	}

	/**
	 * @param point
	 * @param comment
	 */
	public PointComment(Point point, String comment) {
		this.point = point;
		this.comment = comment;
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

	// Para la Interface Comparator
	public int compare(Object obj1, Object obj2) 
	{
		PointComment pc1 = (PointComment) obj1;
		PointComment pc2 = (PointComment) obj2;
		return pc1.getPoint().getPointOrder() -
			pc2.getPoint().getPointOrder();
	}
	

	// Para la interface Comparable
	public int compareTo(Object obj){
		if (obj.getClass().isInstance(PointComment.class))
		{
			PointComment o = (PointComment)obj; 			
			return (new Integer (this.getPoint().getPointOrderReport())).compareTo(new Integer(o.getPoint().getPointOrderReport()));
		}
		return -1;
	}

    public PointCommentData getPointCommentData() {
        return new PointCommentData(point.getPointData(),comment);
    }

	
}
