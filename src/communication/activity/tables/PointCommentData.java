package communication.activity.tables;

import java.io.Serializable;
import java.util.Comparator;

public class PointCommentData  implements Serializable, Comparator, Comparable{

	private PointData point;
	private String comment;
	
	private static final long serialVersionUID = 1L;
	
	public PointCommentData() {
	}

	/**
	 * @param point
	 * @param comment
	 */
	public PointCommentData(PointData point, String comment) {
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
	public PointData getPoint() {
		return point;
	}

	/**
	 * @param point The point to set.
	 */
	public void setPoint(PointData point) {
		this.point = point;
	}

	// Para la Interface Comparator
	public int compare(Object obj1, Object obj2)	{
		PointCommentData pc1 = (PointCommentData) obj1;
		PointCommentData pc2 = (PointCommentData) obj2;
		return pc1.getPoint().getPointOrder() - pc2.getPoint().getPointOrder();
	}
	

	// Para la interface Comparable
	public int compareTo(Object obj){
		if (obj.getClass().isInstance(PointCommentData.class)) {
			PointCommentData o = (PointCommentData)obj; 			
			return (new Integer (this.getPoint().getPointOrderReport())).compareTo(new Integer(o.getPoint().getPointOrderReport()));
		}
		return -1;
	}

	
}
