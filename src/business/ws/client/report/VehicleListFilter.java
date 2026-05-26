package business.ws.client.report;

public class VehicleListFilter {
	
	private String text;
	private int first;
	private int count;
	private int status;
	
	 
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getFirst() {
		return first;
	}
	public void setFirst(int first) {
		this.first = first;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

}
