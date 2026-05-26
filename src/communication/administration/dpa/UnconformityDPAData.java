package communication.administration.dpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import communication.driver.tables.TelematicControlData;
import communication.vehicle.tables.VehicleCheckData;


/**
 * 
 * @author fcarriquiry
 *
 */
public class UnconformityDPAData implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	
	public static int STATUS_NEW=1;
    public static int STATUS_NOTIFIED=2;
    public static int STATUS_CLOSE=3;
	
	private Integer check;
	
	private Integer driverId;
	private String userId;
	private Integer corpId;
	
	private UnconformityTypeDPADataType type;
	
	private TelematicControlData telematicControl;
	
	private Date date;
	
	private String comment;
	
	private int status;
	
	private Integer id;
	
	private ArrayList items;
	
	private HashMap<String, Object> relatedinformation;
	
	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public Integer getDriverId() {
		return driverId;
	}


	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

	
	public UnconformityDPAData(){
		this.items=new ArrayList();
		this.relatedinformation=new HashMap<String, Object>();
	}
	
	
	public Integer getCorpId() {
		return corpId;
	}


	public void setCorpId(Integer corpId) {
		this.corpId = corpId;
	}


	public void setRelatedInformation(String name,Object value){
		this.relatedinformation.put(name, value);
	}
	public Object getRelatedInformation(String name){
		return this.relatedinformation.get(name);
	}
	

	public ArrayList getItems() {
		return items;
	}

	public void setItems(ArrayList items) {
		this.items = items;
	}

	public Integer getCheck() {
		return check;
	}

	public void setCheck(Integer check) {
		this.check = check;
	}

	public TelematicControlData getTelematicControl() {
		return telematicControl;
	}

	public void setTelematicControl(TelematicControlData telematicControl) {
		this.telematicControl = telematicControl;
	}

	 

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public UnconformityTypeDPADataType getType() {
		return type;
	}

	public void setType(UnconformityTypeDPADataType type) {
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	 
	
	
	
	
}
