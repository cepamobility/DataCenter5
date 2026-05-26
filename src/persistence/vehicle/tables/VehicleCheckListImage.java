package persistence.vehicle.tables;

import java.io.Serializable;
import java.util.Comparator;

import persistence.activity.tables.Point;

import communication.vehicle.report.CheckListImageData;

public class VehicleCheckListImage  implements Serializable, Comparator, Comparable{

	private Point point;
	private String imageName;
	private String text;
	
	/**
	 * Serializable:
	 */
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public VehicleCheckListImage() {
		super();		
	}

	/**
	 * @param point
	 * @param imageName
	 * @param text
	 */
	public VehicleCheckListImage(Point point, String imageName,String text) {
		this.point = point;
		this.imageName = imageName;
		this.text=text;
	}


	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
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
		VehicleCheckListImage pc1 = (VehicleCheckListImage) obj1;
		VehicleCheckListImage pc2 = (VehicleCheckListImage) obj2;
		return pc1.getPoint().getPointOrder() -
			pc2.getPoint().getPointOrder();
	}
	

	// Para la interface Comparable
	public int compareTo(Object obj){
		if (obj.getClass().isInstance(VehicleCheckListImage.class))
		{
			VehicleCheckListImage o = (VehicleCheckListImage)obj; 			
			return (new Integer (this.getPoint().getPointOrderReport())).compareTo(new Integer(o.getPoint().getPointOrderReport()));
		}
		return -1;
	}

    public CheckListImageData getData() {
    	CheckListImageData data=new CheckListImageData();
    	data.setPointId(point.getId());
    	data.setFileName(imageName);
    	data.setText(text);
        return data;
    }

	
}
