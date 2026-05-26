package communication.event.tables;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.Hibernate;

import communication.administration.corporation.tables.CorporationData;

public class SimpleReportEventAttributes implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int INCIDENT = 1; 
    public static final int ACCIDENT = 2; 
    public static final int FINE = 3;
    public static final int NEARMISS = 4;
    
	private int id;
	private int driverId;
	private Date eventdate;
	private String driverName;
	private String driverLastName;
    private String divOrg1;
    private String divOrg2;
    private String divOrg3;
    private String divOrg4;
    private String divOrg5;
    private String divOrg6;
    private String divOrg7;
    private String divOrg8;
    private String divOrg9;
	private String country;
	protected String accident;
	protected String incident;
	private String nearmiss;
	private String recomendation;
	private String story;
	private String qualify;
	private String fineType;
	private String observations;
	protected int type;
    private boolean recordable;
    private String supervisionObservations;
	private Integer extraCost1;
	private Integer extraCost2;
	private Integer extraCost3;
	private String evitability;
	
	private String supervisedCollision;
	private String supervisedAccident;
	private String supervisedIncident;
	
	//Nueva clasificacion
	private String clasification1;
	private String clasification2;
	private String clasification3;
	private String clasification4;
	private String clasificationText;
	
	private String clasificationcepa1;
	private String clasificationcepa2;
	private String clasificationcepa3;
	private String clasificationcepa4;
	private String clasificationcepaText;
	

	public SimpleReportEventAttributes(){};
	
	public SimpleReportEventAttributes(int id,int driverId,Date eventdate,String driverName,String driverLastName,String[] divOrgs, String country,
			        String accident,String incident,String recomendation,String story,String qualify,String fineType, String observations, boolean recordable,String supervisionObservations) {
        mainReportEventAttributes(id,driverId,eventdate,driverName,driverLastName,divOrgs,country,accident,incident,recomendation,story,qualify,fineType,observations,recordable,supervisionObservations);
    }
	
	public SimpleReportEventAttributes(Object[] data, int type, boolean constructor, int corporation) {
		this.id = ((Integer)data[0]).intValue();
		this.driverId = ((Integer)data[1]).intValue();
		this.eventdate = (Date)data[2];
		this.driverName = (String)data[3];
		this.driverLastName = (String)data[4];
        this.country = (String)data[5];
		if(type == EventDataType.FINE) {
        	this.type = FINE;
	        this.recomendation = (String)data[6];
        	this.qualify = (String)data[7];
            this.fineType = (String)data[8];
            this.observations = (String)data[9];
            this.recordable = (data[10] == null) ? true : ((Boolean)data[10]).booleanValue();
            if(constructor) {
                this.supervisionObservations = (String)data[11]; 
            	setDivOrgs(data,12);
            }
		}else {
			if(data[6] != null) {
	        	this.type = ACCIDENT;
	        	this.accident = (String)data[6]; 
			}else if(data[7] != null) {
	        	this.type = INCIDENT;
	        	this.incident = (String)data[7]; 
			}else if(data[8] != null) {
	        	this.type = NEARMISS;
	        	this.nearmiss = (String)data[8]; 
			}
			if(corporation == CorporationData.DPA) {
				if(data[16].equals("datatype.collisionclasification.incident")) {
		        	this.type = INCIDENT;					
					if(data[6] != null) {
			        	this.incident = (String)data[6]; 
					}else if(data[7] != null) {
			        	this.incident = (String)data[7];
					}
				}else {
		        	this.type = ACCIDENT;					
					if(data[6] != null) {
			        	this.accident = (String)data[6]; 
					}else if(data[7] != null) {
			        	this.accident = (String)data[7];
					}
				}
			}
			this.recomendation = (String)data[9];
            this.story = (String)data[10];
            this.recordable = (data[11] == null) ? true : ((Boolean)data[11]).booleanValue();
            this.supervisedCollision = (String)data[12];
            this.supervisedAccident = (String)data[13];
            this.supervisedIncident = (String)data[14];
            if(constructor) {
                this.supervisionObservations = (String)data[15];
                this.extraCost1 = (Integer)data[16];
                this.extraCost2 = (Integer)data[17];
                this.extraCost3 = (Integer)data[18];
                this.evitability = (String)data[20];
                this.clasification1=(String)data[21];
                this.clasification2=(String)data[22];
                this.clasification3=(String)data[23];
                this.clasification4=(String)data[24];
                this.clasificationText=(String)data[25];
                this.clasificationcepa1=(String)data[26];
                this.clasificationcepa2=(String)data[27];
                this.clasificationcepa3=(String)data[28];
                this.clasificationcepa4=(String)data[29];
                this.clasificationcepaText=(String)data[30];
                
            	setDivOrgs(data,31);
            }else {
                this.supervisionObservations = (String)data[66]; 
            }
		}
	}
	
	protected void setDivOrgs(Object[] data, int index) {
        if(data.length > index) {
            divOrg1 = (String)data[index];
        }
        index++;
        if(data.length > index) {
            divOrg2 = (String)data[index];
        }
        index++;
        if(data.length > index) {
            divOrg3 = (String)data[index];
        }
        index++;
        if(data.length > index) {
            divOrg4 = (String)data[index];
        }
        index++;
        if(data.length > index) {
            divOrg5 = (String)data[index];
        }
        index++;
        if(data.length > index) {
            divOrg6 = (String)data[index];
        }
        index++;
        if(data.length > index) {
            divOrg7 = (String)data[index];
        }
        index++;
        if(data.length > index) {
            divOrg8 = (String)data[index];
        }
        index++;
        if(data.length > index) {
            divOrg9 = (String)data[index];
        }
	}

	public void mainReportEventAttributes(int id,int driverId,Date eventdate,String driverName,String driverLastName,String[] divOrgs, String country
			     ,String accident,String incident,String recomendation,String story,String qualify,String fineType, String observations, boolean recordable,String supervisionObservations) {
        this.driverId=driverId;
        this.id=id;
        this.eventdate=eventdate;
        this.driverName=driverName;
        this.driverLastName=driverLastName;
        if(divOrgs.length > 0) {
            divOrg1 = divOrgs[0];
        }
        if(divOrgs.length > 1) {
            divOrg2 = divOrgs[1];
        }
        if(divOrgs.length > 2) {
            divOrg3 = divOrgs[2];
        }
        if(divOrgs.length > 3) {
            divOrg4 = divOrgs[3];
        }
        if(divOrgs.length > 4) {
            divOrg5 = divOrgs[4];
        }
        if(divOrgs.length > 5) {
            divOrg6 = divOrgs[5];
        }
        if(divOrgs.length > 6) {
            divOrg7 = divOrgs[6];
        }
        if(divOrgs.length > 7) {
            divOrg8 = divOrgs[7];
        }
        if(divOrgs.length > 8) {
            divOrg9 = divOrgs[8];
        }
        this.country=country;
        this.recomendation=recomendation;
        if ((accident==null)&&(incident==null))
        	this.type=FINE;
        else if (accident!=null)
        	this.type=ACCIDENT;
        else
        	this.type=INCIDENT;
        if (type!=FINE){
        	this.accident=accident;
            this.incident=incident;
            this.story=story;
        }else{
        	this.qualify=qualify;
            this.fineType=fineType;
            this.observations=observations;
        }
        this.recordable = recordable;
        this.supervisionObservations = supervisionObservations; 
    }
	
	public int getId(){
		return id;
	}
	public int getDriverId(){
		return driverId;
	}
	public Date getEventdate(){
		return eventdate;
	}
	public String getDriverName(){
		return driverName;
	}
	public String getDriverLastName(){
		return driverLastName;
	}
	public String getCountry(){
		return country;
	}
	
	
	public String getClasification1() {
		return clasification1;
	}

	public void setClasification1(String clasification1) {
		this.clasification1 = clasification1;
	}

	public String getClasification2() {
		return clasification2;
	}

	public void setClasification2(String clasification2) {
		this.clasification2 = clasification2;
	}

	public String getClasification3() {
		return clasification3;
	}

	public void setClasification3(String clasification3) {
		this.clasification3 = clasification3;
	}

	public String getClasification4() {
		return clasification4;
	}

	public void setClasification4(String clasification4) {
		this.clasification4 = clasification4;
	}

	public String getClasificationText() {
		return clasificationText;
	}

	public void setClasificationText(String clasificationText) {
		this.clasificationText = clasificationText;
	}

	public String getClasificationcepa1() {
		return clasificationcepa1;
	}

	public void setClasificationcepa1(String clasificationcepa1) {
		this.clasificationcepa1 = clasificationcepa1;
	}

	public String getClasificationcepa2() {
		return clasificationcepa2;
	}

	public void setClasificationcepa2(String clasificationcepa2) {
		this.clasificationcepa2 = clasificationcepa2;
	}

	public String getClasificationcepa3() {
		return clasificationcepa3;
	}

	public void setClasificationcepa3(String clasificationcepa3) {
		this.clasificationcepa3 = clasificationcepa3;
	}

	public String getClasificationcepa4() {
		return clasificationcepa4;
	}

	public void setClasificationcepa4(String clasificationcepa4) {
		this.clasificationcepa4 = clasificationcepa4;
	}

	public String getClasificationcepaText() {
		return clasificationcepaText;
	}

	public void setClasificationcepaText(String clasificationcepaText) {
		this.clasificationcepaText = clasificationcepaText;
	}

	public String getAccident(){
		return accident;
	}
	public String getIncident(){
		return incident;
	}
	public String getRecomendation(){
		return recomendation;
	}
	public String getStory(){
		return story;
	}
	public int getType(){
		return type;
	}
	public String getQualify(){
		return qualify;
	}
	public String getFineType(){
		return fineType;
	}
	public String getObservations(){
		return observations;
	}

    public String getDivOrg1() {
        return divOrg1;
    }

    public String getDivOrg2() {
        return divOrg2;
    }

    public String getDivOrg3() {
        return divOrg3;
    }

    public String getDivOrg4() {
        return divOrg4;
    }

    public String getDivOrg5() {
        return divOrg5;
    }

    public String getDivOrg6() {
        return divOrg6;
    }

    public String getDivOrg7() {
        return divOrg7;
    }

    public String getDivOrg8() {
        return divOrg8;
    }

    public String getDivOrg9() {
        return divOrg9;
    }

    public void setDivOrg1(String divOrg1) {
        this.divOrg1 = divOrg1;
    }

    public void setDivOrg2(String divOrg2) {
        this.divOrg2 = divOrg2;
    }

    public void setDivOrg3(String divOrg3) {
        this.divOrg3 = divOrg3;
    }

    public void setDivOrg4(String divOrg4) {
        this.divOrg4 = divOrg4;
    }

    public void setDivOrg5(String divOrg5) {
        this.divOrg5 = divOrg5;
    }

    public void setDivOrg6(String divOrg6) {
        this.divOrg6 = divOrg6;
    }

    public void setDivOrg7(String divOrg7) {
        this.divOrg7 = divOrg7;
    }

    public void setDivOrg8(String divOrg8) {
        this.divOrg8 = divOrg8;
    }

    public void setDivOrg9(String divOrg9) {
        this.divOrg9 = divOrg9;
    }

    public boolean getRecordable() {
        return recordable;
    }

    public String getSupervisionObservations() {
        return supervisionObservations;
    }

    public void setSupervisionObservations(String supervisionObservations) {
        this.supervisionObservations = supervisionObservations;
    }

	public String getNearmiss() {
		return nearmiss;
	}

	public Integer getExtraCost1() {
		return extraCost1;
	}

	public void setExtraCost1(Integer extraCost1) {
		this.extraCost1 = extraCost1;
	}
	
	public Integer getExtraCost2() {
		return extraCost2;
	}

	public void setExtraCost2(Integer extraCost2) {
		this.extraCost2 = extraCost2;
	}
	
	public Integer getExtraCost3() {
		return extraCost3;
	}

	public void setExtraCost3(Integer extraCost3) {
		this.extraCost3 = extraCost3;
	}

	public String getEvitability() {
		return evitability;
	}

	public void setEvitability(String evitability) {
		this.evitability = evitability;
	}

	public String getSupervisedCollision() {
		return supervisedCollision;
	}

	public void setSupervisedCollision(String supervisedCollision) {
		this.supervisedCollision = supervisedCollision;
	}

	public String getSupervisedAccident() {
		return supervisedAccident;
	}

	public void setSupervisedAccident(String supervisedAccident) {
		this.supervisedAccident = supervisedAccident;
	}

	public String getSupervisedIncident() {
		return supervisedIncident;
	}

	public void setSupervisedIncident(String supervisedIncident) {
		this.supervisedIncident = supervisedIncident;
	}
	
}
