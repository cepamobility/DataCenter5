package communication.event.tables;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import org.hibernate.Hibernate;

import communication.administration.corporation.tables.CorporationData;
public class DetailedReportEventAttributes extends SimpleReportEventAttributes implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Time hour;
	private boolean damage;
	private String zone;
	private String working;
	private String policereport;
	private String collisionclasification;
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
	//Fin nueva clasificacion
	
	private String handle;
	private String hitrun;
	private String principalFactor;
	private String collisionAngle;
	private Integer maxSpeed;
	private Integer collisionSpeed;
	private Integer vehicleSpeed;
	private Integer reportedSpeed;
	private String speedUnit;
	private String selfbelt;
	private String alcohol;
	private String otherCompanyVehicles;
	private String cellular;
	private String way;
	private String direction;
	private String control;
	private String state;
	private String city;
	private String condition;
	private String climate;
	private String lighting;
	private Integer cost;
	private String driverstate;
	private Integer dayslost;
	private Integer otherpartydayslost;
	private Integer safefunccount;
	private Integer safeothercount;
	private Integer injuredfunccount;
	private Integer injuredothercount;
	private Integer fatalitiesfunccount;
	private Integer fatalitiesothercount;
	private String pavement;
	private String plate;
	private String model;
	private String trade;
	private String year;
	private Date birthDate;
	private Date inclusionDate;
	private String licenseCategory;
	private Date licenseExpiry;
	private boolean companycar;
	private String vehiclePlate;
	private String vehicleModel;
	private String vehicleTrade;
	private String vehicleYear;
	private String vehicleType;
	private Integer fcost;
	private Integer vehicleId;
    private String evitability;
    private String corporationId; // WWID
    private Integer severity;    
    private Integer workingHours;
    private Integer points;
    private Date expiry;
    
    /**
     * Campos solicitados por CIMSA:
     */
    private boolean realCost;
    private Integer companyAmount;
   	private Integer driverAmount;
   	private Integer thirdAmount;
   	private String whoDrives;
   	
   	/* Monsanto: */
   	private String assignedVehicle;
   	private String infringementNotice;
	private String article;
	
	/*Se usa en BYER para saber si si era de Casa/Trabajo o Trabajo/Casa o No Data*/
	private Integer sapNumber=0;
	
	private boolean doubleCabin;
	private Integer extracost1;
	private Integer extracost2;
	private Integer extracost3;
	private String alcoholbreath;
	private String stoppedvehicle;
	private String charged;

	public DetailedReportEventAttributes(Object[] row,String[] divOrgs,int length, boolean accidents,boolean incidents,boolean fines) {
		super();
		// System.out.println("Row size:"+row.length);
		if(!fines){
			mainReportEventAttributes(((Integer)row[0]).intValue(),((Integer)row[1]).intValue(),(Date)row[2],(String)row[3],(String)row[4],divOrgs,(String)row[length+5],(String)row[length+6],(String)row[length+7],(String)row[length+8],(String)row[length+9],null,null,null,((Boolean)row[length+10]).booleanValue(),(String) row[length+62]);
			allReportEventAttributes((Time)row[length+11],(Boolean) row[length+12], (String) row[length+13],(String) row[length+14],(String) row[length+15],(String) row[length+16],(String) row[length+17],(String) row[length+18], (String) row[length+19], (String) row[length+20],(Integer) row[length+21],(Integer) row[length+22],
					(Integer) row[length+23],(Integer) row[length+24],(String) row[length+25],(String) row[length+26],(String) row[length+27],(String) row[length+28],(String) row[length+29],(String) row[length+30],(String) row[length+31],(String) row[length+32],(String) row[length+33],(String) row[length+34],(String) row[length+35],
					(String) row[length+36],(Integer) row[length+37],(String) row[length+38],(Integer) row[length+39],(Integer) row[length+40],(Integer) row[length+41],(Integer) row[length+42],(Integer) row[length+43],(Integer) row[length+44],(Integer) row[length+45],(Integer) row[length+46],(String) row[length+47],
					(String) row[length+48], (String) row[length+49],(String) row[length+50],(String) row[length+51],(Date) row[length+52],(Date) row[length+53],(String) row[length+54],(Date) row[length+55],(Boolean) row[length+56],(String) row[length+57],(String) row[length+58],(String) row[length+59],(String) row[length+60],
                    null,(Integer) row[length+61],(String)row[length+63],(String)row[length+64], (Integer)row[length+65], (Integer)row[length+66], (String)row[length+67],
                    (Boolean) row[length+68], (Integer) row[length+69], (Integer) row[length+70], (Integer) row[length+71], (String) row[length+72], (Boolean) row[length+73]);                                                                                                                                                               
					
		}else if ((accidents || incidents)&&(fines)){
			mainReportEventAttributes(((Integer)row[0]).intValue(),((Integer)row[1]).intValue(),(Date)row[2],(String)row[3],(String)row[4],divOrgs,(String)row[length+5],(String)row[length+6],(String)row[length+7],(String)row[length+8],(String)row[length+9],(String)row[length+10],(String)row[length+11],(String)row[length+12],((Boolean)row[length+13]).booleanValue(),(String)row[length+66]);
			allReportEventAttributes((Time)row[length+14],(Boolean) row[length+15], (String) row[length+16],(String) row[length+17],(String) row[length+18],(String) row[length+19],(String) row[length+20],(String) row[length+21], (String) row[length+22], (String) row[length+23],(Integer) row[length+24],(Integer) row[length+25],
					(Integer) row[length+26],(Integer) row[length+27],(String) row[length+28],(String) row[length+29],(String) row[length+30],(String) row[length+31],(String) row[length+32],(String) row[length+33],(String) row[length+34],(String) row[length+35],(String) row[length+36],(String) row[length+37],(String) row[length+38],
					(String) row[length+39],(Integer) row[length+40],(String) row[length+41],(Integer) row[length+42],(Integer) row[length+43],(Integer) row[length+44],(Integer) row[length+45],(Integer) row[length+46],(Integer) row[length+47],(Integer) row[length+48],(Integer) row[length+49],(String) row[length+50],
					(String) row[length+51], (String) row[length+52],(String) row[length+53],(String) row[length+54],(Date) row[length+55],(Date) row[length+56],(String) row[length+57],(Date) row[length+58],(Boolean) row[length+59],(String) row[length+60],(String) row[length+61],(String) row[length+62],(String) row[length+63],
                    (Integer) row[length+64],(Integer) row[length+65],(String)row[length+67],(String)row[length+68],null,null, (String)row[length+69],
                    (Boolean) row[length+70], (Integer) row[length+71], (Integer) row[length+72], (Integer) row[length+73], (String) row[length+74], (Boolean) row[length+75]);
		}else{
			
			// Solo multas
			mainReportEventAttributes(((Integer)row[0]).intValue(),((Integer)row[1]).intValue(),(Date)row[2],(String)row[3],(String)row[4],divOrgs,(String)row[length+5],null,null,(String)row[length+6],null,(String)row[length+7],(String)row[length+8],(String)row[length+9],((Boolean)row[length+10]).booleanValue(),(String)row[length+28]);
			allReportEventAttributes((Time)row[length+11],null,null,null,null,null,null,null, null, null,null,null,null,null,null,null,null,null,null,null,
					null,null,(String) row[length+12],null,null,null,null,null,null,null,null,null,null,null,null,null,null,
					(String) row[length+13], (String) row[length+14],(String) row[length+15],(String) row[length+16],(Date) row[length+17],(Date) row[length+18],(String) row[length+19],
					 (Date) row[length+20],(Boolean) row[length+21],(String) row[length+22],(String) row[length+23],(String) row[length+24],(String) row[length+25],(Integer) row[length+26],
                     (Integer) row[length+27],(String)row[length+29],(String)row[length+30],null,null,(String)row[length+31],
                     null,null,null,null,null,false);
		}
	}
	
	public DetailedReportEventAttributes(Object[] data, int type, int corporation) {
		super(data,type,false,0);
		if(type == EventDataType.FINE) {
			// Multas:
			hour = (Time)data[11];
			state = (String)data[12];
			birthDate = (Date)data[17];
			inclusionDate = (Date)data[18];
			licenseCategory = (String)data[19];
			licenseExpiry = (Date)data[20];
			companycar = ((Boolean)data[21]).booleanValue();
			vehicleId = (Integer)data[27];
			if(vehicleId != null) {
				vehiclePlate = (String)data[22];
				vehicleModel =(String)data[23];
				vehicleTrade = (String)data[24];
				vehicleYear = (String)data[25];
				
				// Monsanto, vehicleType
				vehicleType = (String)data[31];
				
			}else {
				plate = (String)data[13];
				model =(String)data[14];
				trade = (String)data[15];
				year = (String)data[16];
			}
			fcost = (Integer)data[26];
			city = (String)data[28];
			evitability = (String)data[29];
			corporationId = (String)data[30];
			
			// Monsanto: assignedVehicle
			assignedVehicle = (String)data[32];
			infringementNotice = (String)data[33];
			this.points = (Integer)data[34];
			this.article = (String)data[35];
			this.expiry = (Date)data[36];
			
			
			setDivOrgs(data, 37);
		}else {
			 this.hour = (Time)data[15];
			 this.damage = (data[16]!=null)? ((Boolean)data[16]).booleanValue():false;
			 this.zone = (String)data[17];
			 this.working = (String)data[18];
			 this.policereport = (String)data[19];
			 this.collisionclasification = (String)data[20];
			if(corporation == CorporationData.DPA) {
				if(data[20].equals("datatype.collisionclasification.incident")) {
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
			 this.handle = (String)data[21];
			 this.hitrun = (String)data[22];
			 this.principalFactor = (String)data[23];
			 this.collisionAngle = (String)data[24];
			 this.maxSpeed = (Integer)data[25];
			 this.collisionSpeed = (Integer)data[26];
			 this.vehicleSpeed = (Integer)data[27];
			 this.reportedSpeed = (Integer)data[28];
			 this.speedUnit = (String)data[29];
			 this.selfbelt = (String)data[30];
			 this.alcohol = (String)data[31];
			 this.otherCompanyVehicles = (String)data[32];
			 this.cellular = (String)data[33];
			 this.way = (String)data[34];
			 this.direction = (String)data[35];
			 this.control = (String)data[36];
			 this.state = (String)data[37];
			 this.condition = (String)data[38];
			 this.climate = (String)data[39];
			 this.lighting = (String)data[40];
			 this.cost = (Integer)data[41];
			 this.driverstate = (String)data[42];
			 this.dayslost = (Integer)data[43];
			 this.otherpartydayslost = (Integer)data[44];
			 this.safefunccount = (Integer)data[45];
			 this.safeothercount = (Integer)data[46];
			 this.injuredfunccount = (Integer)data[47];
			 this.injuredothercount = (Integer)data[48];
			 this.fatalitiesfunccount = (Integer)data[49];
			 this.fatalitiesothercount = (Integer)data[50];
			 this.pavement = (String)data[51];
			 this.plate = (String)data[52];
			 this.model = (String)data[53];
			 this.trade = (String)data[54];
			 this.year = (String)data[55];
			 this.birthDate = (Date)data[56];
			 this.inclusionDate = (Date)data[57];
			 this.licenseCategory = (String)data[58];
			 this.licenseExpiry = (Date)data[59];
			 this.companycar=(data[60]!=null)? ((Boolean)data[60]).booleanValue():false;
			 this.vehiclePlate = (String)data[61];
			 this.vehicleModel = (String)data[62];
			 this.vehicleTrade = (String)data[63];
			 this.vehicleYear = (String)data[64];
			 this.vehicleId = (Integer)data[65];
			 this.city = (String)data[67];
	         this.evitability = (String)data[68];
	         this.workingHours = (Integer)data[69];
	         this.severity = (Integer)data[70];
	         this.corporationId = (String)data[71];
	         this.realCost = (data[72]!=null)? ((Boolean)data[72]).booleanValue():false;
	         this.companyAmount = (Integer)data[73];
	         this.driverAmount = (Integer)data[74];
	         this.thirdAmount = (Integer)data[75];
	         this.whoDrives = (String)data[76];
	         this.vehicleType = (String)data[77]; // monsanto, 28-09-2011
	         
	         this.assignedVehicle = (String)data[78];
	         this.points = (Integer)data[79];
	         this.expiry = (Date)data[80];
	         /*Se usa en BYER para saber si si era de Casa/Trabajo o Trabajo/Casa o No Data*/
	         this.sapNumber = (Integer)data[81]; 
	         this.doubleCabin = (Boolean)data[82];
	         this.clasification1 = (String)data[83]; 
	         this.clasification2 = (String)data[84];
	         this.clasification3 = (String)data[85];
	         this.clasification4 = (String)data[86];
	         this.clasificationText = (String)data[87];
	         this.clasificationcepa1 = (String)data[88]; 
	         this.clasificationcepa2 = (String)data[89];
	         this.clasificationcepa3 = (String)data[90];
	         this.clasificationcepa4 = (String)data[91];
	         this.clasificationcepaText = (String)data[92];
	         this.extracost1 = (Integer)data[93];
	         this.extracost2 = (Integer)data[94];
	         this.extracost3 = (Integer)data[95];
	         this.alcoholbreath = (String)data[96];
	         this.stoppedvehicle = (String)data[97];
	         this.charged = (String)data[98];

	         
	         setDivOrgs(data, 99);
		}
	}

	public void allReportEventAttributes(Time time,Boolean damage, String zone,String working,String policereport,String collision,String handle,
		 String hitrun, String pfactor, String cangle,Integer mspeed,Integer cspeed,Integer vspeed,Integer rspeed,
		 String speedunit,String selfbelt,String alcohol,String ovehicles,String cellular,String way,String direction,
		 String control,String state,String condition,String climate,String lighting,Integer cost,String driverstate,
		 Integer daylost,Integer otherpartydayslost,Integer safefunccount,Integer safeothercount,Integer injuredfunccount,
		 Integer injuredothercount,Integer fatalitiesfunccount,Integer fatalitiesothercount,String pavement,
		 String plate, String model,String trade,String year,Date birthDate,Date inclusionDate,String licenseCategory,
		 Date licenseExpiry,Boolean companycar,String matricula,String vmodel,String vtrade,String vyear,Integer fcost,
		 Integer vehicleId,String city,String evitability, Integer workingHours, Integer severity, String corporationId,
		 Boolean realCost, Integer companyAmount, Integer driverAmount , Integer thirdAmount, String whoDrives, Boolean doubleCabin ){
		
		 this.hour=time;
		 this.damage=(damage!=null)? damage.booleanValue():false;
		 this.zone=zone;
		 this.working=working;
		 this.policereport=policereport;
		 this.collisionclasification=collision;
		 this.handle=handle;
		 this.hitrun=hitrun;
		 this.principalFactor=pfactor;
		 this.collisionAngle=cangle;
		 this.maxSpeed=mspeed;
		 this.collisionSpeed=cspeed;
		 this.vehicleSpeed=vspeed;
		 this.reportedSpeed=rspeed;
		 this.speedUnit=speedunit;
		 this.selfbelt=selfbelt;
		 this.alcohol=alcohol;
		 this.otherCompanyVehicles=ovehicles;
		 this.cellular=cellular;
		 this.way=way;
		 this.direction=direction;
		 this.control=control;
		 this.state=state;
		 this.city=city;
		 this.condition=condition;
		 this.climate=climate;
		 this.lighting=lighting;
		 this.cost=cost;
		 this.driverstate=driverstate;
		 this.dayslost=daylost;
		 this.otherpartydayslost=otherpartydayslost;
		 this.safefunccount=safefunccount;
		 this.safeothercount=safeothercount;
		 this.injuredfunccount=injuredfunccount;
		 this.injuredothercount=injuredothercount;
		 this.fatalitiesfunccount=fatalitiesfunccount;
		 this.fatalitiesothercount=fatalitiesothercount;
		 this.pavement=pavement;
		 this.plate=plate;
		 this.model=model;
		 this.trade=trade;
		 this.year=year;
		 this.birthDate=birthDate;
		 this.inclusionDate=inclusionDate;
		 this.licenseCategory=licenseCategory;
		 this.licenseExpiry=licenseExpiry;
		 this.companycar=(companycar!=null)? companycar.booleanValue():false;
		 this.vehiclePlate=matricula;
		 this.vehicleModel=vmodel;
		 this.vehicleTrade=vtrade;
		 this.vehicleYear=vyear;
		 this.fcost=fcost;
		 this.vehicleId=vehicleId;
         this.evitability = evitability;
         this.workingHours = workingHours;
         this.severity = severity;
         this.corporationId = corporationId;
         this.realCost = (realCost != null) ? realCost.booleanValue():false;
         this.companyAmount = companyAmount;
         this.driverAmount = driverAmount;
         this.thirdAmount = thirdAmount; 
         this.whoDrives = whoDrives; 
         this.doubleCabin = doubleCabin;
	}
	
	
	
    public String getClasification4() {
		return clasification4;
	}

	public void setClasification4(String clasification4) {
		this.clasification4 = clasification4;
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

	public Integer getSapNumber() {
		return sapNumber;
	}

	public void setSapNumber(Integer sapNumber) {
		this.sapNumber = sapNumber;
	}

	/**
     * @return Returns the alcohol.
     */
    public String getAlcohol() {
        return alcohol;
    }
    
    /**
     * @return Returns the othervehicles.
     */
    public String getOtherCompanyVehicles() {
        return otherCompanyVehicles;
    }

    /**
     * @return Returns the cellular.
     */
    public String getCellular() {
        return cellular;
    }

  

    /**
     * @return Returns the climate.
     */
    public String getClimate() {
        return climate;
    }

    /**
     * @return Returns the collisionclasification.
     */
    public String getCollisionclasification() {
        return collisionclasification;
    }

    /**
     * @return Returns the collisionangle.
     */
    public String getCollisionAngle() {
        return collisionAngle;
    }

    /**
     * @return Returns the collisionspeed.
     */
    public Integer getCollisionSpeed() {
        return collisionSpeed;
    }

    /**
     * @return Returns the condition.
     */
    public String getCondition() {
        return condition;
    }

    /**
     * @return Returns the control.
     */
    public String getControl() {
        return control;
    }
    /**
     * @return Returns the state.
     */
    public String getState() {
        return state;
    }
    
    /**
     * @return Returns the city.
     */
    public String getCity() {
        return city;
    }

    /**
     * @return Returns the dayslost.
     */
    public Integer getDayslost() {
        return dayslost;
    }

    /**
     * @return Returns the otherpartydayslost.
     */
    public Integer getOtherpartydayslost() {
        return otherpartydayslost;
    }
    
    /**
     * @return Returns the direction.
     */
    public String getDirection() {
        return direction;
    }



    /**
     * @return Returns the driverstate.
     */
    public String getDriverstate() {
        return driverstate;
    }

   

    /**
     * @return Returns the fatalitiesfunccount.
     */
    public Integer getFatalitiesfunccount() {
        return fatalitiesfunccount;
    }

    /**
     * @return Returns the fatalitiesothercount.
     */
    public Integer getFatalitiesothercount() {
        return fatalitiesothercount;
    }

    /**
     * @return Returns the handle.
     */
    public String getHandle() {
        return handle;
    }

    /**
     * @return Returns the hitrun.
     */
    public String getHitrun() {
        return hitrun;
    }

    /**
     * @return Returns the hour.
     */
    public Time getHour() {
        return hour;
    }

    /**
     * @return Returns the damage.
     */
    public boolean isDamage() {
        return damage;
    }
  

    /**
     * @return Returns the injuredfunccount.
     */
    public Integer getInjuredfunccount() {
        return injuredfunccount;
    }

    /**
     * @return Returns the injuredothercount.
     */
    public Integer getInjuredothercount() {
        return injuredothercount;
    }
    
    /**
     * @return Returns the safefunccount.
     */
    public Integer getSafefunccount() {
        return safefunccount;
    }

    /**
     * @return Returns the safeothercount.
     */
    public Integer getSafeothercount() {
        return safeothercount;
    }

    /**
     * @return Returns the lighting.
     */
    public String getLighting() {
        return lighting;
    }
    
    /**
     * @return Returns the cost.
     */
    public Integer getCost() {
        return cost;
    }
    
    /**
     * @return Returns the maxspeed.
     */
    public Integer getMaxSpeed() {
        return maxSpeed;
    }

   
    /**
     * @return Returns the pavement.
     */
    public String getPavement() {
        return pavement;
    }


    /**
     * @return Returns the principalFactor.
     */
    public String getPrincipalFactor() {
        return principalFactor;
    }

    /**
     * @return Returns the reportedspeed.
     */
    public Integer getReportedSpeed() {
        return reportedSpeed;
    }

    /**
     * @return Returns the selfbelt.
     */
    public String getSelfbelt() {
        return selfbelt;
    }

    /**
     * @return Returns the speedunit.
     */
    public String getSpeedUnit() {
        return speedUnit;
    }    

    /**
     * @return Returns the vehiclespeed.
     */
    public Integer getVehicleSpeed() {
        return vehicleSpeed;
    }

    /**
     * @return Returns the way.
     */
    public String getWay() {
        return way;
    }

    /**
     * @return Returns the working.
     */
    public String getWorking() {
        return working;
    }

    /**
     * @return Returns the policereport.
     */
    public String getPoliceReport() {
        return policereport;
    }
    
    /**
     * @return Returns the zone.
     */
    public String getZone() {
        return zone;
    }

    /**
     * @return Returns the model.
     */
    public String getModel() {
        return model;
    }

    /**
     * @return Returns the plate.
     */
    public String getPlate() {
        return plate;
    }

    /**
     * @return Returns the trade.
     */
    public String getTrade() {
        return trade;
    }
    
    /**
     * @return Returns the year.
     */
    public String getYear() {
        return year;
    }
    
    /**
     * @return Returns the birthDate.
     */
    public Date getBirthDate() {
        return birthDate;
    }
    
    public Date getInclusionDate() {
        return inclusionDate;
    }
    
    /**
	 * @return Returns the licenseCategory.
	 */
	public String getLicenseCategory() {
		return licenseCategory;
	}

	/**
	 * @return Returns the licenseExpiry.
	 */
	public Date getLicenseExpiry() {
		return licenseExpiry;
	}
	
	 public boolean isCompanyCar() {
	        return companycar;
	    }
	
	 /**
	     * @return Returns the vehicleModel.
	     */
	    public String getVehicleModel() {
	        return vehicleModel;
	    }

	    /**
	     * @return Returns the vehiclePlate.
	     */
	    public String getVehiclePlate() {
	        return vehiclePlate;
	    }

	    /**
	     * @return Returns the trade.
	     */
	    public String getVehicleTrade() {
	        return vehicleTrade;
	    }
	    
	    /**
	     * @return Returns the vehicleYear.
	     */
	    public String getVehicleYear() {
	        return vehicleYear;
	    }
	    
	    /**
	     * @return Returns the fcost
	     */
	    public Integer getFcost() {
	        return fcost;
	    }
	    
	    /**
	     * @return Returns the vehicleReference.
	     */
	    public Integer getVehicleId(){
	        return vehicleId;
	    }

        public String getEvitability() {
            return evitability;
        }

		/**
		 * @return the workingHours
		 */
		public Integer getWorkingHours() {
			return workingHours;
		}

		/**
		 * @param workingHours the workingHours to set
		 */
		public void setWorkingHours(Integer workingHours) {
			this.workingHours = workingHours;
		}

		/**
		 * @return the severity
		 */
		public Integer getSeverity() {
			return severity;
		}

		/**
		 * @param severity the severity to set
		 */
		public void setSeverity(Integer severity) {
			this.severity = severity;
		}

		/**
		 * @return the corporationId
		 */
		public String getCorporationId() {
			return corporationId;
		}

		/**
		 * @param corporationId the corporationId to set
		 */
		public void setCorporationId(String corporationId) {
			this.corporationId = corporationId;
		}

		/**
		 * @return the policereport
		 */
		public String getPolicereport() {
			return policereport;
		}

		/**
		 * @param policereport the policereport to set
		 */
		public void setPolicereport(String policereport) {
			this.policereport = policereport;
		}

		/**
		 * @return the companycar
		 */
		public boolean isCompanycar() {
			return companycar;
		}

		/**
		 * @param companycar the companycar to set
		 */
		public void setCompanycar(boolean companycar) {
			this.companycar = companycar;
		}

		/**
		 * @return the realCost
		 */
		public boolean isRealCost() {
			return realCost;
		}

		/**
		 * @param realCost the realCost to set
		 */
		public void setRealCost(boolean realCost) {
			this.realCost = realCost;
		}

		/**
		 * @return the companyAmount
		 */
		public Integer getCompanyAmount() {
			return companyAmount;
		}

		/**
		 * @param companyAmount the companyAmount to set
		 */
		public void setCompanyAmount(Integer companyAmount) {
			this.companyAmount = companyAmount;
		}

		/**
		 * @return the driverAmount
		 */
		public Integer getDriverAmount() {
			return driverAmount;
		}

		/**
		 * @param driverAmount the driverAmount to set
		 */
		public void setDriverAmount(Integer driverAmount) {
			this.driverAmount = driverAmount;
		}

		/**
		 * @return the thirdAmount
		 */
		public Integer getThirdAmount() {
			return thirdAmount;
		}

		/**
		 * @param thirdAmount the thirdAmount to set
		 */
		public void setThirdAmount(Integer thirdAmount) {
			this.thirdAmount = thirdAmount;
		}

		/**
		 * @param hour the hour to set
		 */
		public void setHour(Time hour) {
			this.hour = hour;
		}

		/**
		 * @param damage the damage to set
		 */
		public void setDamage(boolean damage) {
			this.damage = damage;
		}

		/**
		 * @param zone the zone to set
		 */
		public void setZone(String zone) {
			this.zone = zone;
		}

		/**
		 * @param working the working to set
		 */
		public void setWorking(String working) {
			this.working = working;
		}

		/**
		 * @param collisionclasification the collisionclasification to set
		 */
		public void setCollisionclasification(String collisionclasification) {
			this.collisionclasification = collisionclasification;
		}

		/**
		 * @param handle the handle to set
		 */
		public void setHandle(String handle) {
			this.handle = handle;
		}

		/**
		 * @param hitrun the hitrun to set
		 */
		public void setHitrun(String hitrun) {
			this.hitrun = hitrun;
		}

		/**
		 * @param principalFactor the principalFactor to set
		 */
		public void setPrincipalFactor(String principalFactor) {
			this.principalFactor = principalFactor;
		}

		/**
		 * @param collisionAngle the collisionAngle to set
		 */
		public void setCollisionAngle(String collisionAngle) {
			this.collisionAngle = collisionAngle;
		}

		/**
		 * @param maxSpeed the maxSpeed to set
		 */
		public void setMaxSpeed(Integer maxSpeed) {
			this.maxSpeed = maxSpeed;
		}

		/**
		 * @param collisionSpeed the collisionSpeed to set
		 */
		public void setCollisionSpeed(Integer collisionSpeed) {
			this.collisionSpeed = collisionSpeed;
		}

		/**
		 * @param vehicleSpeed the vehicleSpeed to set
		 */
		public void setVehicleSpeed(Integer vehicleSpeed) {
			this.vehicleSpeed = vehicleSpeed;
		}

		/**
		 * @param reportedSpeed the reportedSpeed to set
		 */
		public void setReportedSpeed(Integer reportedSpeed) {
			this.reportedSpeed = reportedSpeed;
		}

		/**
		 * @param speedUnit the speedUnit to set
		 */
		public void setSpeedUnit(String speedUnit) {
			this.speedUnit = speedUnit;
		}

		/**
		 * @param selfbelt the selfbelt to set
		 */
		public void setSelfbelt(String selfbelt) {
			this.selfbelt = selfbelt;
		}

		/**
		 * @param alcohol the alcohol to set
		 */
		public void setAlcohol(String alcohol) {
			this.alcohol = alcohol;
		}

		/**
		 * @param otherCompanyVehicles the otherCompanyVehicles to set
		 */
		public void setOtherCompanyVehicles(String otherCompanyVehicles) {
			this.otherCompanyVehicles = otherCompanyVehicles;
		}

		/**
		 * @param cellular the cellular to set
		 */
		public void setCellular(String cellular) {
			this.cellular = cellular;
		}

		/**
		 * @param way the way to set
		 */
		public void setWay(String way) {
			this.way = way;
		}

		/**
		 * @param direction the direction to set
		 */
		public void setDirection(String direction) {
			this.direction = direction;
		}

		/**
		 * @param control the control to set
		 */
		public void setControl(String control) {
			this.control = control;
		}

		/**
		 * @param state the state to set
		 */
		public void setState(String state) {
			this.state = state;
		}

		/**
		 * @param city the city to set
		 */
		public void setCity(String city) {
			this.city = city;
		}

		/**
		 * @param condition the condition to set
		 */
		public void setCondition(String condition) {
			this.condition = condition;
		}

		/**
		 * @param climate the climate to set
		 */
		public void setClimate(String climate) {
			this.climate = climate;
		}

		/**
		 * @param lighting the lighting to set
		 */
		public void setLighting(String lighting) {
			this.lighting = lighting;
		}

		/**
		 * @param cost the cost to set
		 */
		public void setCost(Integer cost) {
			this.cost = cost;
		}

		/**
		 * @param driverstate the driverstate to set
		 */
		public void setDriverstate(String driverstate) {
			this.driverstate = driverstate;
		}

		/**
		 * @param dayslost the dayslost to set
		 */
		public void setDayslost(Integer dayslost) {
			this.dayslost = dayslost;
		}

		/**
		 * @param otherpartydayslost the otherpartydayslost to set
		 */
		public void setOtherpartydayslost(Integer otherpartydayslost) {
			this.otherpartydayslost = otherpartydayslost;
		}

		/**
		 * @param safefunccount the safefunccount to set
		 */
		public void setSafefunccount(Integer safefunccount) {
			this.safefunccount = safefunccount;
		}

		/**
		 * @param safeothercount the safeothercount to set
		 */
		public void setSafeothercount(Integer safeothercount) {
			this.safeothercount = safeothercount;
		}

		/**
		 * @param injuredfunccount the injuredfunccount to set
		 */
		public void setInjuredfunccount(Integer injuredfunccount) {
			this.injuredfunccount = injuredfunccount;
		}

		/**
		 * @param injuredothercount the injuredothercount to set
		 */
		public void setInjuredothercount(Integer injuredothercount) {
			this.injuredothercount = injuredothercount;
		}

		/**
		 * @param fatalitiesfunccount the fatalitiesfunccount to set
		 */
		public void setFatalitiesfunccount(Integer fatalitiesfunccount) {
			this.fatalitiesfunccount = fatalitiesfunccount;
		}

		/**
		 * @param fatalitiesothercount the fatalitiesothercount to set
		 */
		public void setFatalitiesothercount(Integer fatalitiesothercount) {
			this.fatalitiesothercount = fatalitiesothercount;
		}

		/**
		 * @param pavement the pavement to set
		 */
		public void setPavement(String pavement) {
			this.pavement = pavement;
		}

		/**
		 * @param plate the plate to set
		 */
		public void setPlate(String plate) {
			this.plate = plate;
		}

		/**
		 * @param model the model to set
		 */
		public void setModel(String model) {
			this.model = model;
		}

		/**
		 * @param trade the trade to set
		 */
		public void setTrade(String trade) {
			this.trade = trade;
		}

		/**
		 * @param year the year to set
		 */
		public void setYear(String year) {
			this.year = year;
		}

		/**
		 * @param birthDate the birthDate to set
		 */
		public void setBirthDate(Date birthDate) {
			this.birthDate = birthDate;
		}

		/**
		 * @param inclusionDate the inclusionDate to set
		 */
		public void setInclusionDate(Date inclusionDate) {
			this.inclusionDate = inclusionDate;
		}

		/**
		 * @param licenseCategory the licenseCategory to set
		 */
		public void setLicenseCategory(String licenseCategory) {
			this.licenseCategory = licenseCategory;
		}

		/**
		 * @param licenseExpiry the licenseExpiry to set
		 */
		public void setLicenseExpiry(Date licenseExpiry) {
			this.licenseExpiry = licenseExpiry;
		}

		/**
		 * @param vehiclePlate the vehiclePlate to set
		 */
		public void setVehiclePlate(String vehiclePlate) {
			this.vehiclePlate = vehiclePlate;
		}

		/**
		 * @param vehicleModel the vehicleModel to set
		 */
		public void setVehicleModel(String vehicleModel) {
			this.vehicleModel = vehicleModel;
		}

		/**
		 * @param vehicleTrade the vehicleTrade to set
		 */
		public void setVehicleTrade(String vehicleTrade) {
			this.vehicleTrade = vehicleTrade;
		}

		/**
		 * @param vehicleYear the vehicleYear to set
		 */
		public void setVehicleYear(String vehicleYear) {
			this.vehicleYear = vehicleYear;
		}

		/**
		 * @param fcost the fcost to set
		 */
		public void setFcost(Integer fcost) {
			this.fcost = fcost;
		}

		/**
		 * @param vehicleId the vehicleId to set
		 */
		public void setVehicleId(Integer vehicleId) {
			this.vehicleId = vehicleId;
		}

		/**
		 * @param evitability the evitability to set
		 */
		public void setEvitability(String evitability) {
			this.evitability = evitability;
		}

		public String getWhoDrives() {
			return whoDrives;
		}

		public void setWhoDrives(String whoDrives) {
			this.whoDrives = whoDrives;
		}

		
		
		public String getVehicleType() {
			return vehicleType;
		}

		public void setVehicleType(String vehicleType) {
			this.vehicleType = vehicleType;
		}

		public String getAssignedVehicle() {
			return assignedVehicle;
		}

		public void setAssignedVehicle(String assignedVehicle) {
			this.assignedVehicle = assignedVehicle;
		}

		public String getInfringementNotice() {
			return infringementNotice;
		}

		public void setInfringementNotice(String infringementNotice) {
			this.infringementNotice = infringementNotice;
		}

		public Integer getPoints() {
			return points;
		}

		public void setPoints(Integer points) {
			this.points = points;
		}

		public String getArticle() {
			return article;
		}

		public void setArticle(String article) {
			this.article = article;
		}

		public Date getExpiry() {
			return expiry;
		}

		public void setExpiry(Date expiry) {
			this.expiry = expiry;
		}

		public boolean isDoubleCabin() {
			return doubleCabin;
		}

		public void setDoubleCabin(boolean doubleCabin) {
			this.doubleCabin = doubleCabin;
		}

		public Integer getExtracost1() {
			return extracost1;
		}

		public Integer getExtracost2() {
			return extracost2;
		}

		public Integer getExtracost3() {
			return extracost3;
		}

		public String getAlcoholbreath() {
			return alcoholbreath;
		}

		public String getStoppedvehicle() {
			return stoppedvehicle;
		}

		public String getCharged() {
			return charged;
		}
		
}
