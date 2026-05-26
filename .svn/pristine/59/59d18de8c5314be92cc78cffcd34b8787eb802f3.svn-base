package communication.vehicle.report;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import communication.language.Text;

public class VehicleCheckReportDetailedDataType implements Comparable ,Serializable {

	private Integer vehicleId;
    private String contractName;
    private String plate;
    private String type;
    private Date date;
    private Integer vehicleCheckId;
    private String observations;
    private String inspectedBy;
    private String result;
    
    private int checkCount;
    // Hash de items del checklist
    //	Key (String): item
    //	Value (Integer): value del item
    private Hashtable items;
    
    private int order;
    
    public static final int PLATE = 0; // Matricula
    public static final int CONTRACT_TYPE = 1;
    public static final int TYPE_CONTRACT = 2;
    public static final int CONTRACT = 3; // Contratista
    public static final int TYPE = 4; // Tipo de vehiculo
    
	public VehicleCheckReportDetailedDataType() {
		super();
	}


    public VehicleCheckReportDetailedDataType(Object[] s, Text messages, int order) {
    	super();
    	vehicleCheckId = (Integer)s[0];
    	vehicleId = (Integer)s[1];
        contractName = (String)s[2];
        plate = (String)s[3];
        type = messages.getText((String)s[4]);
        date = (Date)s[5];
        checkCount = 1;
        items = new Hashtable();
        this.order = order;
        if(s.length > 6) {
        	this.observations = (String) s[6];
        }
        if(s[s.length-2] instanceof String){
        	inspectedBy=(String)s[s.length-2];	
        }
        if(s[s.length-1] instanceof String){
        	result=String.valueOf(s[s.length-1]);
        }
    }

    
    
    
    /**
     * @param name
     * @param plate
     * @param type
     * @param order
     */
    public VehicleCheckReportDetailedDataType(String contractName, 
    		String plate, String type, int order, Date date) {
        this.contractName = contractName;
        this.plate = plate;
        this.type = type;
        this.date = date;
        this.order = order;
        checkCount = 1;
        items = new Hashtable();
    }

    
    
    public String getInspectedBy() {
		return inspectedBy;
	}


	public void setInspectedBy(String inspectedBy) {
		this.inspectedBy = inspectedBy;
	}


	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}


	public void sumCount() {
        checkCount++;
    }
    
    public int getCheckCount() {
        return checkCount;
    }

    /**
     * Devuelve el valor que tiene el item 
     * del checklist.
     * 		1 (item correcto)
     * 		2 (item incorrecto)
     *      -1 valor del item no cargado
     * @param item
     * @return 
     */
    public Integer getValue(Integer item) {
    	if (items.containsKey(item)){
    		return (Integer)items.get(item);
    	}else{
    		return new Integer(-1);
    	}
    }
    
    

    public void setCheckCount(int checkCount) {
        this.checkCount = checkCount;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    /**
     * Setea el valor para el item.
     * @param item
     * @param value
     */
    public void setItemValue(Integer item, Integer value) {    	
   		items.put(item,value);
    }
    
    
    /**
     * Setea todo el hash de items.
     * @param items
     */
    public void setItems(Hashtable items){
    	this.items = items;
    }
    
    /**
     * 
     * @return
     */
    public Hashtable getItems(){
    	return this.items;
    }
    
    
    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int compareTo(Object o) {
        if(order == CONTRACT) {
            if(!contractName.equals(contractName)) {
                return contractName.compareTo(contractName);
            }
        }
        if(order == TYPE) {
            if(!type.equals(type)) {
                return type.compareTo(type);
            }
        }
        if(order == CONTRACT_TYPE) {
            if(!contractName.equals(contractName)) {
                return contractName.compareTo(contractName);
            }
            if(!type.equals(type)) {
                return type.compareTo(type);
            }
        }
        if(order == TYPE_CONTRACT) {
            if(!type.equals(type)) {
                return type.compareTo(type);
            }
            if(!contractName.equals(contractName)) {
                return contractName.compareTo(contractName);
            }
        }
        return plate.compareTo(plate);
    }


	/**
	 * @return Returns the date.
	 */
	public Date getDate() {
		return date;
	}


	/**
	 * @param date The date to set.
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
	 * Devuelve la fecha como un string con formato: dd-mm-yyyy
	 * @return
	 */
	public String getStringDate(){
		Format dateFormat;	
		dateFormat = new SimpleDateFormat("dd-MM-yyyy");	
		return dateFormat.format(this.date);
	}


	/**
	 * @return Returns the vehicleCheckId.
	 */
	public Integer getVehicleCheckId() {
		return vehicleCheckId;
	}


	/**
	 * @param vehicleCheckId The vehicleCheckId to set.
	 */
	public void setVehicleCheckId(Integer vehicleCheckId) {
		this.vehicleCheckId = vehicleCheckId;
	}


	public String getObservations() {
		return observations;
	}


	public void setObservations(String observations) {
		this.observations = observations;
	}


	public Integer getVehicleId() {
		return vehicleId;
	}


	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	
    
}