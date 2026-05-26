/**
 * Created on 24-nov-2008
 * CEPA
 * DataCenter 5
 */
package communication.cenapra;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;

import communication.language.Text;

public class CENAPRAParticipantListData implements Serializable {
    
    protected Integer id;
    protected String firstName;
    protected String lastName;
    protected String activity;
    protected Date date;
    protected String city;
    protected String instructor;
    protected String divOrg1;
    protected String divOrg2;
    
    protected String checkList;
    protected Integer registry;
    
    protected Hashtable results;
    
    public CENAPRAParticipantListData() {
        results = new Hashtable();
    }
    
    public CENAPRAParticipantListData(Object[] data) {
        id = (Integer)data[0];
        firstName = (String)data[1];
        lastName = (String)data[2];
        divOrg1 = (String)data[3];
        divOrg2 = (String)data[4];
        if(data.length > 5) {
            activity = (String)data[5];
            date = (Date)data[6];
            city = (String)data[7];
            instructor = (String)data[8];
            if(data.length > 9)
            	registry = (Integer)data[9];
        }
        results = new Hashtable();
    }

    public String getActivity() {
        return activity;
    }

    public String getCity() {
        return city;
    }

    public Date getDate() {
        return date;
    }

    public String getFirstName() {
        return firstName;
    }

    public Integer getId() {
        return id;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getLastName() {
        return lastName;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void addItem(Object key, Object result) {
        results.put(key,result);
    }

    public int hasResult(Object key,Collection answers) {
    	if(results.size() == 0) {
    		return -1;
    	}else {
    		if(results.containsKey(key)) {
    			return ((Integer)results.get(key)).intValue();
    		}
        }
    	return -1;
    }
    
    public String getValue(Text messages,Object key) {
        if(!results.containsKey(key)) {
            return null;
        }
        return messages.getText((String)results.get(key));
    }

	public String getDivOrg1() {
		return divOrg1;
	}

	public String getDivOrg2() {
		return divOrg2;
	}

	public void setDivOrg1(String divOrg1) {
		this.divOrg1 = divOrg1;
	}

	public void setDivOrg2(String divOrg2) {
		this.divOrg2 = divOrg2;
	}

	public String getCheckList() {
		return checkList;
	}

	public void setCheckList(String checkList) {
		this.checkList = checkList;
	}

	public Integer getRegistry() {
		return registry;
	}

	public void setRegistry(Integer registry) {
		this.registry = registry;
	}

}
