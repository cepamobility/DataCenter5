package communication.administration.corporation.tables;

import communication.language.Text;

public class PointDetailBayerData {

	private static final int PERFIL = 0;
	private static final int MULTA = 1;
	private static final int EVENTO = 2;
	private static final int ACTIVITY = 3;
	private static final int ASIGNADO = 4;

	private String key;
	private String reference;
	
	private int amount;
	private int sum;
	
	private int[] values; 
	
	public PointDetailBayerData(String key, int amount, String reference) {
		this.key = key;
		this.reference = reference;
		this.amount = amount;
		sum = 0;
		values = new int[4];
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int[] getValues() {
		return values;
	}

	public void setValues(int[] values) {
		this.values = values;
	}

	public void addData(Object[] data) {
		values[((Integer)data[6]).intValue()] = ((Integer)data[4]).intValue();
		sum += ((Integer)data[5]).intValue();
	}

	public String getLine(int category, Text messages) {
		String s = "<tr><td class='bayerDetailCell1'>"+getKeyText(category,messages)+"</td>";			
		s += "	<td class='bayerDetailCell2'>"+amount+"</td>";
		for(int i = 0; i < 4; i++)
			s += "	<td class='bayerDetailCell2'>"+values[i]+"</td>";
		s += "	<td class='bayerDetailCell2'>"+sum+"</td></tr>";
		return s;

	}

	private String getKeyText(int category, Text messages) {
		switch(category) {
			case PERFIL:
			case EVENTO:
				return messages.getText("point.definition."+key.toLowerCase());
			case MULTA:
			case ACTIVITY:
				return messages.getText(reference.toLowerCase());
			case ASIGNADO:
				return "---";
		}
		return null;
	}

	public void sumTotals(PointDetailBayerData detailBayerData) {
		sum += detailBayerData.getSum();
		for(int i = 0; i < 4; i++)
			values[i] += detailBayerData.getValues()[i];
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}
	
}
