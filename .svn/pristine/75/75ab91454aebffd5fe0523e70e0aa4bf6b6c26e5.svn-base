package communication.fleet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.jfree.data.Values2D;

import communication.language.Text;

public class ApmkData {

	private Integer id;
	private String name;
	
	private boolean tree;
	
	private Integer vehicles;
	private Integer kms;
	private Integer accidents;
	private Integer incidents;
	private Integer divisor;
	
	private HashMap<Integer,ApmkData> leaves;

	public ApmkData() {
		id = 0;
		name = "";
		leaves = new HashMap<Integer,ApmkData>();
		tree = true;
		vehicles = 0;
		kms = 0;
		divisor = 0;
	}

	public ApmkData(Integer id, String name) {
		this.id = id;
		this.name = name;
		leaves = new HashMap<Integer,ApmkData>();
		tree = true;
		vehicles = 0;
		kms = 0;
		divisor = 0;
	}


	public void addData(Object[] data, int index) {
		if(data.length - index == 4) {
			
		}else {
			if(leaves.containsKey(data[index])) {
				leaves.get(data[index]).addData(data,index+2);
			}else {
				ApmkData apmk = new ApmkData(data,index);
				leaves.put(apmk.getId(), apmk);
			}
		}
	}

	public ApmkData(Object[] data, int index) {
		id = (Integer)data[index];
		index++;
		name = (String)data[index];
		index++;

		tree = data.length - index > 4;
		if(tree) {
			ApmkData apmk = new ApmkData(data,index);
			if(leaves == null) { 
				leaves = new HashMap<Integer,ApmkData>();
			} 
			if(leaves.containsKey(apmk.getId())) {
				leaves.get(apmk.getId()).addData(apmk);
			}else {
				leaves.put(apmk.getId(), apmk);
			}
		}else {
			vehicles = (Integer)data[index];
			index++;
			kms = (Integer)data[index];
			divisor = 1;
		}
	}

	private void addData(ApmkData apmk) {
		vehicles = apmk.getVehicles();
		kms = apmk.getKms();
		divisor++;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isTree() {
		return tree;
	}

	public void setTree(boolean tree) {
		this.tree = tree;
	}

	public Integer getVehicles() {
		return vehicles;
	}

	public void setVehicles(Integer vehicles) {
		this.vehicles = vehicles;
	}

	public Integer getAccidents() {
		return accidents;
	}

	public void setAccidents(Integer accidents) {
		this.accidents = accidents;
	}

	public Integer getIncidents() {
		return incidents;
	}

	public void setIncidents(Integer incidents) {
		this.incidents = incidents;
	}

	public Integer getKms() {
		return kms;
	}

	public void setKms(Integer kms) {
		this.kms = kms;
	}

	public Integer getDivisor() {
		return divisor;
	}

	public void setDivisor(Integer divisor) {
		this.divisor = divisor;
	}

	public HashMap<Integer, ApmkData> getLeaves() {
		return leaves;
	}

	public void setLeaves(HashMap<Integer, ApmkData> leaves) {
		this.leaves = leaves;
	}

	public void addLine(Object[] data,int index) {
		if(data.length - index == 4) {
			vehicles += (Integer) data[index+2];
			kms += (Integer) data[index+3];
			divisor++;
		} else {
			Integer idNew = (Integer)data[index];
			index++;
			String nameNew = (String)data[index];
			index++;
			if(!leaves.containsKey(idNew)) {
				leaves.put(idNew, new ApmkData(idNew,nameNew));
			}
			leaves.get(idNew).addLine(data, index);
		}
	}

	public ArrayList<ArrayList<String>> getXLSValues(Text messages) {
		ArrayList<ArrayList<String>> values = new ArrayList<ArrayList<String>>();
		if(leaves.size() == 0) {
			ArrayList<String> leave = new ArrayList<String>();
			leave.add(name);
			leave.add(String.valueOf(Math.round(vehicles.doubleValue()/divisor.doubleValue())));
			leave.add(String.valueOf(kms));
			values.add(leave);
		}else {
			int index = 0;
			Iterator<ApmkData> it = leaves.values().iterator();
			int vehicles = 0;
			int kms = 0;
			while(it.hasNext()) {
				ArrayList<ArrayList<String>> values2 = it.next().getXLSValues(messages);
				Iterator<ArrayList<String>> it2 = values2.iterator();
				while(it2.hasNext()) {
					ArrayList<String> leave = new ArrayList<String>();
					ArrayList<String> leaveValue = it2.next();
					if(id.intValue() > 0) {
						if(name.startsWith("datatype.country"))
							leave.add(messages.getText(name));
						else
							leave.add(name);
					}
					Iterator<String> it3 = leaveValue.iterator();
					index = 0;
					boolean sum = true;
					while(it3.hasNext()) {
						String v = it3.next();
						if(v.equals("---"))
							sum = false;
						leave.add(v);
						index++;
						if(index+1 == leaveValue.size() && sum) {
							vehicles += Integer.parseInt(v);
						}
						if(index == leaveValue.size() && sum) {
							kms += Integer.parseInt(v);
						}
					}
					values.add(leave);
				}
			}
			ArrayList<String> total = new ArrayList<String>();
			if(id.intValue() > 0) {
				if(name.startsWith("datatype.country"))
					total.add(messages.getText(name));
				else
					total.add(name);
				for(int i = 0; i <= index-3; i++) {
					total.add("---");
				}
			}else{
				total.add("Total");
				for(int i = 0; i < index-3; i++) {
					total.add("---");
				}
			}
			total.add(String.valueOf(vehicles));
			total.add(String.valueOf(kms));
			values.add(total);
		}
		return values;
	}

	public String[][] getJasperValues() {
		if(leaves.size() == 0) {
			String[][] leave = new String[1][3];
			leave[0][0] = name;
			leave[0][2] = String.valueOf(Math.round(vehicles.doubleValue()/divisor.doubleValue()));
			leave[0][1] = String.valueOf(kms);
			return leave;
		}else {
			Iterator<ApmkData> it = leaves.values().iterator();
			int vehicles = 0;
			int kms = 0;
			ArrayList<String[]> list = new ArrayList<String[]>();
			while(it.hasNext()) {
				String[][] values2 = it.next().getJasperValues();
				for(int i = 0; i < values2.length; i++) {
					vehicles += Integer.parseInt(values2[i][values2[i].length-2]);
					kms += Integer.parseInt(values2[i][values2[i].length-1]);
					list.add(values2[i]);
				}
			}
			if(id.intValue() > 0) {
				String[][] values = new String[list.size()][list.get(0).length+3];
				Iterator<String[]> it2 = list.iterator();
				int index = 0;
				while(it2.hasNext()) {
					String[] listValue = it2.next();
					values[index][0] = name;
					values[index][1] = String.valueOf(vehicles);
					values[index][2] = String.valueOf(kms);
					for(int i = 0; i < listValue.length; i++)
						values[index][3+i] = listValue[i];
					index++;
				}
				return values;
			}else {
				return list.toArray(new String[0][0]);
			}
		}
	}

	public String[][] getHtmlValues() {
		if(leaves.size() == 0) {
			String[][] leave = new String[1][4];
			leave[0][0] = String.valueOf(id);
			leave[0][1] = name;
			leave[0][2] = String.valueOf(kms);
			leave[0][3] = String.valueOf(Math.round(vehicles.doubleValue()/divisor.doubleValue()));
			return leave;
		}else {
			Iterator<ApmkData> it = leaves.values().iterator();
			int vehicles = 0;
			int kms = 0;
			ArrayList<String[]> list = new ArrayList<String[]>();
			while(it.hasNext()) {
				String[][] values2 = it.next().getHtmlValues();
				for(int i = 0; i < values2.length; i++) {
					vehicles += Integer.parseInt(values2[i][values2[i].length-2]);
					kms += Integer.parseInt(values2[i][values2[i].length-1]);
					list.add(values2[i]);
				}
			}
			if(id.intValue() > 0) {
				String[][] values = new String[list.size()][list.get(0).length+4];
				Iterator<String[]> it2 = list.iterator();
				int index = 0;
				while(it2.hasNext()) {
					String[] listValue = it2.next();
					values[index][0] = String.valueOf(id);
					values[index][1] = name;
					values[index][2] = String.valueOf(vehicles);
					values[index][3] = String.valueOf(kms);
					for(int i = 0; i < listValue.length; i++)
						values[index][4+i] = listValue[i];
					index++;
				}
				return values;
			}else {
				return list.toArray(new String[0][0]);
			}
		}
	}

}
