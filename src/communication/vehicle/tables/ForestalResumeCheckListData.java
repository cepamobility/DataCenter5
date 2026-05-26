package communication.vehicle.tables;

import java.util.ArrayList;

import communication.language.Text;


public class ForestalResumeCheckListData 
	implements Comparable<ForestalResumeCheckListData> {

	private String contractor;
	private int count;
	private int type;
	private int observations;
	private int[] results;
	private int observationCount;
	
	public ForestalResumeCheckListData() {
		count = 1;
		observations = 0;
		observationCount = 0;
		results = new int[]{0,0,0};
	}

	public String getContractor() {
		return contractor;
	}

	public void setContractor(String contractor) {
		this.contractor = contractor;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getObservations() {
		return observations;
	}

	public void setObservations(int observations) {
		this.observations = observations;
	}

	public int[] getResults() {
		return results;
	}

	public void setResults(int[] results) {
		this.results = results;
	}

	public void setObservationCount(int obsCount) {
		observationCount = obsCount;
	}

	public int getObservationCount() {
		return observationCount;
	}

	public String getKey() {
		return contractor + "-" + type;
	}

	public void addData(ForestalResumeCheckListData resume) {
		count += resume.getCount();
		observations += resume.getObservations();
		observationCount += resume.getObservationCount();
		for(int i = 0; i < 3; i++)
			results[i] += resume.results[i];
	}

	public void addResult(Integer result) {
		results[result-1] = 1;
	}

	public ArrayList<String> getLine(Text messages) {
		ArrayList<String> line = new ArrayList<String>();
		line.add(contractor);
		switch(type) {
			case 79:
				line.add(messages.getText("generic.messages.addvehiclecheckupm1"));
				break;
			case 80:
				line.add(messages.getText("generic.messages.addvehiclecheckupm2"));
				break;
			case 84:
				line.add(messages.getText("generic.messages.checkinternal"));
				break;
			case 86:
				line.add("Checklist Formulario ACU");
				break;
			case 92:
				line.add(messages.getText("datatype.scheduledactivitytype.dangerousmaterials"));
				break;
		}
		line.add(String.valueOf(count));
		line.add(String.valueOf(observations));
		line.add(String.valueOf(observationCount));
		for(int i = 0; i < 3; i++)
			line.add(String.valueOf(results[i]));
		return line;
	}

	@Override
	public int compareTo(ForestalResumeCheckListData o) {
		if(type == o.type) {
			return contractor.compareTo(o.contractor);
		}
		return new Integer(type).compareTo(new Integer(o.type));
	}

}