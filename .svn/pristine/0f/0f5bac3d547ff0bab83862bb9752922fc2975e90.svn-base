package communication.driver.report;

import java.io.Serializable;
import java.util.Date;

public class EventCombinedDataType implements Comparable, Serializable {
	
    protected Date date;
    protected String type;
    protected String clasification;   
    protected String result;
    protected String story;
    protected String evitability;
    protected String recomendation;
    protected String observations;
    
    public EventCombinedDataType(Object[] data) {
        date = (Date)data[1];
        type = (String)data[2];
        clasification = (String)data[3];   
        result = (String)data[4];
        story = (String)data[5];
        evitability = (String)data[6];
        recomendation = (String)data[7];
        observations = (String)data[8];
    }

    public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getClasification() {
		return clasification;
	}

	public void setClasification(String clasification) {
		this.clasification = clasification;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getStory() {
		return story;
	}

	public void setStory(String story) {
		this.story = story;
	}

	public String getEvitability() {
		return evitability;
	}

	public void setEvitability(String evitability) {
		this.evitability = evitability;
	}

	public String getRecomendation() {
		return recomendation;
	}

	public void setRecomendation(String recomendation) {
		this.recomendation = recomendation;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public int compareTo(Object o) {
        EventCombinedDataType data = (EventCombinedDataType)o;
        if(!date.equals(data.date)) {
            return date.compareTo(data.date);
        }
        if(!type.equals(data.type)) {
            return type.compareTo(data.type);
        }
        return 0;
    }
    
    public boolean equals(Object obj) {
    	try {
    		if (obj instanceof EventCombinedDataType) {
    			EventCombinedDataType last = (EventCombinedDataType)obj;
    			return date.equals(last.date) && type.equals(last.type) && clasification.equals(last.clasification); 
    		}
    	}catch (Exception e) {
			e.printStackTrace();
		}
        return false;
    }

}

