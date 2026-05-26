package communication.vehicle.tables;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import persistence.activity.tables.ItemValueStr;
import persistence.vehicle.tables.VehicleCheck;

import communication.activity.tables.ItemData;

public class CimsaCheckListData implements Comparable {
	
	private int[] order;
	private String contractor;
	private String plate;
	private String type;
	private Date date;
	private String[] itemValues;
	private String obs;
	private String name;

	public CimsaCheckListData(VehicleCheck check, int[] order, ItemData[] items) {
		this.order = order;
		contractor = check.getVehicle().getDivOrg1().getResourceKey();
		plate = check.getVehicle().getMatricula();
		type = check.getVehicle().getType().getResourceBundleId();
		date = check.getDate();
		itemValues = new String[items.length];
		for(int i = 0; i < items.length; i++) {
			itemValues[i] = getValue(check.getItemValueSet(), items[i].getId()); 
		}
	}

	public CimsaCheckListData() {
		itemValues = new String[100];
	}

	public int[] getOrder() {
		return order;
	}

	public void setOrder(int[] order) {
		this.order = order;
	}

	public String getContractor() {
		return contractor;
	}

	public void setContractor(String contractor) {
		this.contractor = contractor;
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

	public String getValue(Set itemValues, int idItem) {
		String value = "";
		Iterator it = itemValues.iterator();
		boolean encontre = false;
		ItemValueStr itemValue = null;
		while (it.hasNext() && !encontre) {
			itemValue = (ItemValueStr) it.next();
			encontre = (itemValue.getItem().getId().intValue() == idItem);
			if (encontre) {
				value = itemValue.getItemValue();
			}
		}
		return value;
	}

	public String getValue(int index) {
		return itemValues[index];
	}
	
	public int compareTo(Object arg0) {
		CimsaCheckListData data = (CimsaCheckListData)arg0;
		switch(order[0]) {
			case 1:
				if(!contractor.equals(data.contractor)) {
					return contractor.compareTo(data.contractor);
				}else {
					switch(order[1]) {
						case 2:
							if(!type.equals(data.type)) {
								return type.compareTo(data.type);
							}else {
								if(order[2] == 3) {
									return plate.compareTo(data.plate);
								}
							}
							break;
						case 3:
							if(!plate.equals(data.plate)) {
								return plate.compareTo(data.plate);
							}else {
								if(order[2] == 2) {
									return type.compareTo(data.type);
								}
							}
							break;
					}
				}
 				break;
			case 2:
				if(!type.equals(data.type)) {
					return type.compareTo(data.type);
				}else {
					switch(order[1]) {
						case 1:
							if(!contractor.equals(data.contractor)) {
								return contractor.compareTo(data.contractor);
							}else {
								if(order[2] == 3) {
									return plate.compareTo(data.plate);
								}
							}
							break;
						case 3:
							if(!plate.equals(data.plate)) {
								return plate.compareTo(data.plate);
							}else {
								if(order[2] == 1) {
									return contractor.compareTo(data.contractor);
								}
							}
							break;
					}
				}
				break;
			case 3:
				if(!plate.equals(data.plate)) {
					return plate.compareTo(data.plate);
				}else {
					switch(order[1]) {
						case 2:
							if(!type.equals(data.type)) {
								return type.compareTo(data.type);
							}else {
								if(order[2] == 1) {
									return contractor.compareTo(data.contractor);
								}
							}
							break;
						case 1:
							if(!contractor.equals(data.contractor)) {
								return contractor.compareTo(data.contractor);
							}else {
								if(order[2] == 2) {
									return type.compareTo(data.type);
								}
							}
							break;
						default:
							break;
					}
				}
				break;
		}
		return 0;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setItemValue(int i, String value) {
		itemValues[i] = value;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getObs() {
		return obs;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getValues(int value,int count) {
		int values = 0;
		for(int i = 0; i < count; i++) {
			if(Integer.parseInt(itemValues[i]) == value) {
				values++;
			}
		}
		return String.valueOf(values);
	}
}
