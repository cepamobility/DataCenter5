package communication.vehicle.report;

import java.io.Serializable;
import java.util.Date;

import communication.language.Text;

public class VehiclePursuitReportDataType implements Comparable , Serializable  {

    private String name;
    private String plate;
    private String type;
    private String id;
    
    private int checkCount;
    private int[] items;
    
    private int order;
    
    private Date date;
    
    public static final int PLATE = 0;
    public static final int CONTRACT_TYPE = 1;
    public static final int TYPE_CONTRACT = 2;
    public static final int CONTRACT = 3;
    public static final int TYPE = 4;
    
    public VehiclePursuitReportDataType() {
    }

    public VehiclePursuitReportDataType(Object[] s, Text messages, int order) {
        name = (String)s[2];
        plate = (String)s[3];
        type = messages.getText((String)s[4]);
        id = ((Integer)s[1]).toString();
        checkCount = 1;
        items = new int[14];
        this.order = order;
    }

    public VehiclePursuitReportDataType(Object[] s, Text messages, int order, int count) {
        name = (String)s[2];
        plate = (String)s[3];
        type = messages.getText((String)s[4]);
        id = ((Integer)s[1]).toString();
        checkCount = 1;
        items = new int[count];
        this.order = order;
    }
    
    /**
     * @param name
     * @param plate
     * @param type
     * @param order
     */
    public VehiclePursuitReportDataType(String name, String plate, String type, int order, String id) {
        this.name = name;
        this.plate = plate;
        this.type = type;
        this.order = order;
        this.id = id;
        checkCount = 1;
        items = new int[14];
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
    
    

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int compareTo(Object o) {
		VehiclePursuitReportDataType d = (VehiclePursuitReportDataType)o;
        if(order == CONTRACT) {
            if(!name.equals(d.name)) {
                return name.compareTo(d.name);
            }
        }
        if(order == TYPE) {
            if(!type.equals(d.type)) {
                return type.compareTo(d.type);
            }
        }
        if(order == CONTRACT_TYPE) {
            if(!name.equals(d.name)) {
                return name.compareTo(d.name);
            }
            if(!type.equals(d.type)) {
                return type.compareTo(d.type);
            }
        }
        if(order == TYPE_CONTRACT) {
            if(!type.equals(d.type)) {
                return type.compareTo(d.type);
            }
            if(!name.equals(d.name)) {
                return name.compareTo(d.name);
            }
        }
        if(date != null && d.date != null) {
        	return date.compareTo(d.date);
        }
        return plate.compareTo(d.plate);
    }

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}

