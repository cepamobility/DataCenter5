package communication.fleet;


public class ResumePointData  {

	private String text;
	private int count=0;
	private int points=0;
	
	public ResumePointData() {
	}

	public ResumePointData(String description) {
		this.text = description;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}

	public void addPoints(int movePoints) {
		count++;
		points += movePoints;
	}

	
  
}
