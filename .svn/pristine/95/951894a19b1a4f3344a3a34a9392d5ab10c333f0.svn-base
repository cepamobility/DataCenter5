/**
 * Created on 26-jul-2007
 * CEPA
 * DataCenter 5
 */
package communication.vehicle.report;

import java.io.Serializable;

import communication.language.Text;

public class VehicleCheckReportDataType implements Comparable,Serializable {

	private Integer vehicleId;
    private String name;
    private String plate;
    private String type;
    private String inspector;
    private String result;
    
    private int checkCount;
    private int[] items;
    
    private int order;
    
    public static final int PLATE = 0;
    public static final int CONTRACT_TYPE = 1;
    public static final int TYPE_CONTRACT = 2;
    public static final int CONTRACT = 3;
    public static final int TYPE = 4;
    
    public VehicleCheckReportDataType() {
    }

    public VehicleCheckReportDataType(Object[] s, Text messages, int order) {
    	vehicleId = (Integer)s[1];
        name = (String)s[2];
        plate = (String)s[3];
        type = messages.getText((String)s[4]);
        checkCount = 1;
        items = new int[14];
        this.order = order;
    }

    
    /**
     * @param name
     * @param plate
     * @param type
     * @param order
     */
    public VehicleCheckReportDataType(String name, String plate, String type, int order) {
        this.name = name;
        this.plate = plate;
        this.type = type;
        this.order = order;
        checkCount = 1;
        items = new int[14];
    }

    
    
    
    public String getInspector() {
		return inspector;
	}

	public void setInspector(String inspector) {
		this.inspector = inspector;
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

    public int getCount(int index) {
        return items[index];
    }

    public void setCheckCount(int checkCount) {
        this.checkCount = checkCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCount(int count, int index) {
        items[index] = count;
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
            if(!name.equals(name)) {
                return name.compareTo(name);
            }
        }
        if(order == TYPE) {
            if(!type.equals(type)) {
                return type.compareTo(type);
            }
        }
        if(order == CONTRACT_TYPE) {
            if(!name.equals(name)) {
                return name.compareTo(name);
            }
            if(!type.equals(type)) {
                return type.compareTo(type);
            }
        }
        if(order == TYPE_CONTRACT) {
            if(!type.equals(type)) {
                return type.compareTo(type);
            }
            if(!name.equals(name)) {
                return name.compareTo(name);
            }
        }
        return plate.compareTo(plate);
    }

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

}
