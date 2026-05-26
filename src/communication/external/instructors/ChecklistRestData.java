package communication.external.instructors;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChecklistRestData {
	
	private Integer id;
	
	private Integer version;
	
	private ArrayList<PointRestData> points;
	
	
	
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ArrayList<PointRestData> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<PointRestData> points) {
		this.points = points;
	}
	


}
