package persistence.administration.tables;

import java.io.Serializable;
import java.util.Comparator;

import persistence.activity.tables.Point;

import communication.administration.dpa.CorrectiveActionImageDPAData;
import communication.vehicle.report.CheckListImageData;

public class CorrectiveActionImageDPA  implements Serializable, Comparator, Comparable{

	private String imageName;
	private String text;
	
	/**
	 * Serializable:
	 */
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public CorrectiveActionImageDPA() {
		super();		
	}

	/**
	 * @param imageName
	 * @param text
	 */
	public CorrectiveActionImageDPA( String imageName,String text) {
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

	
	// Para la Interface Comparator
	public int compare(Object obj1, Object obj2) 
	{
		CorrectiveActionImageDPA pc1 = (CorrectiveActionImageDPA) obj1;
		CorrectiveActionImageDPA pc2 = (CorrectiveActionImageDPA) obj2;
		return pc1.getImageName().compareTo(pc2.getImageName());	}
	

	// Para la interface Comparable
	public int compareTo(Object obj){
		if (obj.getClass().isInstance(CorrectiveActionImageDPA.class))
		{
			CorrectiveActionImageDPA o = (CorrectiveActionImageDPA)obj; 			
			return getImageName().compareTo(o.getImageName());
		}
		return -1;
	}

    public CorrectiveActionImageDPAData getData() {
    	CorrectiveActionImageDPAData data=new CorrectiveActionImageDPAData();
    	data.setFileName(imageName);
    	data.setText(text);
        return data;
    }

	
}
