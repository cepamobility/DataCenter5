package communication.report;


//
import java.io.Serializable;

/**
 * @author fcarriquiry
 */
public class JJCrashSubtypesData implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;

	
	private String collition;
	
	private String subtype;
	
	private Double percentage;
	
	private Integer count;
	
	private Integer cost;
	
	private Integer order;
	
	private Integer countNonRecordable=0;

	
	public JJCrashSubtypesData() {
		
	}


	public Integer getCountNonRecordable() {
		return countNonRecordable;
	}


	public void setCountNonRecordable(Integer countNonRecordable) {
		this.countNonRecordable = countNonRecordable;
	}


	public Integer getOrder() {
		return order;
	}


	public void setOrder(Integer order) {
		this.order = order;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public String getCollition() {
		return collition;
	}


	public void setCollition(String collition) {
		this.collition = collition;
	}


	public String getSubtype() {
		return subtype;
	}


	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}


	public Double getPercentage() {
		return percentage;
	}


	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}


	public Integer getCount() {
		return count;
	}


	public void setCount(Integer count) {
		this.count = count;
	}


	public Integer getCost() {
		return cost;
	}


	public void setCost(Integer cost) {
		this.cost = cost;
	}

}
