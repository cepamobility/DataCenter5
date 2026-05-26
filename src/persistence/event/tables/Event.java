package persistence.event.tables;

import java.util.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import org.hibernate.Session;

import persistence.administration.corporation.tables.CorporationCountry;
import persistence.administration.corporation.tables.CorporationCountryPK;
import persistence.administration.corporation.tables.DivOrgItemLevel1;
import persistence.administration.corporation.tables.GeoLevel1;
import persistence.datatype.tables.AccidentClasification;
import persistence.datatype.tables.AccidentClasificationLevel1;
import persistence.datatype.tables.AccidentClasificationLevel2;
import persistence.datatype.tables.AccidentClasificationLevel3;
import persistence.datatype.tables.AccidentClasificationLevel4;
import persistence.datatype.tables.AccidentFactors;
import persistence.datatype.tables.CirculationDirection;
import persistence.datatype.tables.Climate;
import persistence.datatype.tables.CollisionClasification;
import persistence.datatype.tables.ConditionRout;
import persistence.datatype.tables.IncidentClasification;
import persistence.datatype.tables.Lighting;
import persistence.datatype.tables.Pavement;
import persistence.datatype.tables.TrafficControl;
import persistence.datatype.tables.VehicleBehaviour;
import persistence.datatype.tables.Way;
import persistence.datatype.tables.Zone;
import persistence.driver.tables.Driver;
import persistence.vehicle.tables.Vehicle;
import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.GeoLabelAttributes;
import communication.administration.corporation.tables.GeoLabelData;
import communication.administration.corporation.tables.ItemData;
import communication.datatype.tables.AccidentClasificationDataType;
import communication.datatype.tables.AccidentClasificationLevel1DataType;
import communication.datatype.tables.AccidentClasificationLevel2DataType;
import communication.datatype.tables.AccidentClasificationLevel3DataType;
import communication.datatype.tables.AccidentClasificationLevel4DataType;
import communication.datatype.tables.AccidentFactorsDataType;
import communication.datatype.tables.CirculationDirectionDataType;
import communication.datatype.tables.ClimateDataType;
import communication.datatype.tables.CollisionClasificationDataType;
import communication.datatype.tables.ConditionRoutDataType;
import communication.datatype.tables.IncidentClasificationDataType;
import communication.datatype.tables.LightingDataType;
import communication.datatype.tables.PavementDataType;
import communication.datatype.tables.TrafficControlDataType;
import communication.datatype.tables.VehicleBehaviourDataType;
import communication.datatype.tables.WayDataType;
import communication.datatype.tables.ZoneDataType;
import communication.event.tables.EventDataType;
import communication.event.tables.EventMainAttributes;
import communication.event.tables.VehicleInvolvedDataType;

/**
 * @author jrodriguez
 */
public class Event extends EventAbstract {

    private Zone zone;
    
    private boolean damage;
        
    private String story;
    
    private CollisionClasification collision;
    
    private AccidentClasification accident;
    
    private IncidentClasification incident;
    
    private AccidentClasification nearmiss;

    private VehicleBehaviour handle;
    private String handleText;
    
    private String hitrun;
    
    private AccidentFactors principalFactor;
    private String principalFactorText;
    
    private String distractor;
    private boolean factor1;
    private boolean factor2;
    private boolean factor3;
    private boolean factor4;
    private boolean factor5;
    private boolean factor6;
    private boolean factor7;
    private boolean factor8;
    private boolean factor9;
    private boolean factor10;
    private boolean factor11;
    private boolean factor12;
    private boolean factor13;
    private boolean factor14;
    private boolean factor15;
    private boolean factor16;
    private boolean factor17;
    private boolean factor18;
    private boolean factor19;
    private boolean factor20;
    private boolean factor21;
    private boolean factor22;
    
    private String collisionAngle;
    
    private String driverstate;
    private Integer dayslost;
    private Integer otherpartydayslost;
    private Integer fatalitiesothercount;
    private Integer injuredothercount;
    private Integer safefunccount;
    private Integer safeothercount;
    private Integer fatalitiesfunccount;
    private Integer injuredfunccount;
    
    private boolean damage1;
    private boolean damage2;
    private boolean damage3;
    private boolean damage4;
    private boolean damage5;
    private boolean damage6;
    private boolean damage7;
    private boolean damage8;
    private boolean damage9;
    private boolean damage10;
    private boolean damage11;
    private boolean damage12;
    private boolean damage13;
    private boolean damage14;
    private boolean damage15;
    private boolean damage16;
    
    private String selfbelt;
    private String alcohol;
    private String otherVehicles;
    private String cellular;
    
    private String policeReport;
    private Integer maxSpeed;
    private Integer vehicleSpeed;
    private Integer collisionSpeed;
    private Integer reportedSpeed;
    private String speedUnit;
    
    
    private Climate climate;
    private Lighting lighting;
    private Way way;
    private CirculationDirection direction;
    private TrafficControl control;
    private String controlMultiple; // varios controles de transito a la vez, pepsico
    private Pavement pavement;
    private ConditionRout condition;
    
    private Integer pedestrianCount;
    private String pedestrian1;
    private String pedestrianComment1;
    private String pedestrian2;
    private String pedestrianComment2;
    private String pedestrian3;
    private String pedestrianComment3;
    
    private Integer cost;

    private Set vehiclesInvolved;

    private CollisionClasification superivisedCollision;
    private AccidentClasification superivisedAccident;
    private IncidentClasification superivisedIncident;
    
    //Estos campos sustituyen a accident, incident..etc es la nueva clasificacion
    //Para determinar si es accidente o incidente hay que ver el valor de clasification1
    private AccidentClasificationLevel1 clasification1;
    private AccidentClasificationLevel2 clasification2;
    private AccidentClasificationLevel3 clasification3;
    private AccidentClasificationLevel4 clasification4;
    //Este campo se usa para las opciones otro especificar
    private String clasificationText;
    
    //Estos campos sustituyen a accident, incident..etc es la nueva clasificacion
    //Para determinar si es accidente o incidente hay que ver el valor de clasification1
    private AccidentClasificationLevel1 clasificationCEPA1;
    private AccidentClasificationLevel2 clasificationCEPA2;
    private AccidentClasificationLevel3 clasificationCEPA3;
    private AccidentClasificationLevel4 clasificationCEPA4;
    //Este campo se usa para las opciones otro especificar
    private String clasificationCEPAText;
    
    private Integer carcost;
    private Integer severity; 
    
    private Set eventDamages;
    
    private Integer workingHours; // Solicitado para FOSA
    private String charged;
    private String whoDrives;
    private String damageCharge;
    
    private boolean realCost; // Solicitado por CIMSA

    private String alcoholBreath;
    private String stoppedVehicle;
   		
   	private Integer extraCost1;
   	private Integer extraCost2;
   	private Integer extraCost3;
   	private Integer extraCost4;
   	private Integer extraCost5;
   	private Integer extraCost6;
   	private Integer extraCost7;
   	private Integer extraCost8;
   		
   	private Date ensuranceDate;
   	private Integer companyAmount;
   	private Integer driverAmount;
   	private Integer thirdAmount;
   	
   	private Integer folioNumber; // para CIMSA
   	private Integer sapNumber;  // para CIMSA
   	
   	private boolean doubleCabin; // con zorra para CIMSA


   	public Event() {
        super();
        //this.eventDamages = new HashSet();
    }

    /**
     * @param event
     */
   public Event(EventAbstract event) {
        super(event);
        //this.eventDamages = new HashSet();
    }

   public Event(EventDataType event, Session session,boolean isForest) {
	   this(event,session,isForest,false);
   }
   
    /**
     * @param eventData
     * @param session
     */
    public Event(EventDataType event, Session session,boolean isForest, boolean withDriverData) {
        super(event, session, withDriverData);
        points = event.getPoints();
        damage = event.isDamage();

        date = event.getDate();
        hour = event.getHour();

        companycar = event.getCompanyCar();
        
        zone = (Zone)session.load(Zone.class,event.getZone().getResourceBundleId());
        working = event.getWorking();
       
        policeReport= event.getPoliceReport();
        collision = (event.getCollision()==null || event.getCollision().getResourceBundleId()==null || event.getCollision().getResourceBundleId().trim().length()==0)? null: (CollisionClasification)session.load(CollisionClasification.class,event.getCollision().getResourceBundleId());

        accident = (event.getAccident() == null) ? null : (AccidentClasification)session.load(AccidentClasification.class,event.getAccident().getResourceBundleId());
        incident = (event.getIncident() == null) ? null : (IncidentClasification)session.load(IncidentClasification.class,event.getIncident().getResourceBundleId());
        nearmiss = (event.getNearmiss() == null) ? null : (AccidentClasification)session.load(AccidentClasification.class,event.getNearmiss().getResourceBundleId());

        clasification1 = (event.getClasification1() == null) ? null : (AccidentClasificationLevel1)session.load(AccidentClasificationLevel1.class,event.getClasification1().getResourceBundleId());
        clasification2 = (event.getClasification2() == null) ? null : (AccidentClasificationLevel2)session.load(AccidentClasificationLevel2.class,event.getClasification2().getResourceBundleId());
        clasification3 = (event.getClasification3() == null) ? null : (AccidentClasificationLevel3)session.load(AccidentClasificationLevel3.class,event.getClasification3().getResourceBundleId());
        clasification4 = (event.getClasification4() == null) ? null : (AccidentClasificationLevel4)session.load(AccidentClasificationLevel4.class,event.getClasification4().getResourceBundleId());
        clasificationText=event.getClasificationText();
        
        clasificationCEPA1 = (event.getClasificationCEPA1() == null) ? null : (AccidentClasificationLevel1)session.load(AccidentClasificationLevel1.class,event.getClasificationCEPA1().getResourceBundleId());
        clasificationCEPA2 = (event.getClasificationCEPA2() == null) ? null : (AccidentClasificationLevel2)session.load(AccidentClasificationLevel2.class,event.getClasificationCEPA2().getResourceBundleId());
        clasificationCEPA3 = (event.getClasificationCEPA3() == null) ? null : (AccidentClasificationLevel3)session.load(AccidentClasificationLevel3.class,event.getClasificationCEPA3().getResourceBundleId());
        clasificationCEPA4 = (event.getClasificationCEPA4() == null) ? null : (AccidentClasificationLevel4)session.load(AccidentClasificationLevel4.class,event.getClasificationCEPA4().getResourceBundleId());
        clasificationCEPAText=event.getClasificationCEPAText();
        
        story = event.getStory();

        handle = (VehicleBehaviour)session.load(VehicleBehaviour.class,event.getHandle().getResourceBundleId());
        handleText=event.getHandleText();
        
        hitrun = event.getHitrun();
        
        if(event.getPrincipalFactor() != null && event.getPrincipalFactor().getResourceBundleId() != null) {
        	principalFactor = (AccidentFactors)session.load(AccidentFactors.class,event.getPrincipalFactor().getResourceBundleId());
        	principalFactorText=event.getPrincipalFactorText();
        }
        
        factor1 = event.isFactor1();
        factor2 = event.isFactor2();
        factor3 = event.isFactor3();
        factor4 = event.isFactor4();
        factor5 = event.isFactor5();
        factor6 = event.isFactor6();
        factor7 = event.isFactor7();
        factor8 = event.isFactor8();
        factor9 = event.isFactor9();
        factor10 = event.isFactor10();
        factor11 = event.isFactor11();
        factor12 = event.isFactor12();
        factor13 = event.isFactor13();
        factor14 = event.isFactor14();
        factor15 = event.isFactor15();
        factor16 = event.isFactor16();
        factor17 = event.isFactor17();
        factor18 = event.isFactor18();
        factor19 = event.isFactor19();
        factor20 = event.isFactor20();
        factor21 = event.isFactor21();
        factor22 = event.isFactor22();
        collisionAngle = event.getCollisionAngle();
        
        selfbelt = event.getSelfbelt();
        alcohol = event.getAlcohol();
        otherVehicles = event.getOthervehicles();
        cellular = event.getCellular();
        
        driverstate = event.getDriverstate();
        dayslost = event.getDayslost();
        otherpartydayslost = event.getOtherpartydayslost();
        fatalitiesfunccount = event.getFatalitiesfunccount();
        injuredfunccount = event.getInjuredfunccount();
        fatalitiesothercount = event.getFatalitiesothercount();
        injuredothercount = event.getInjuredothercount();
        safefunccount = event.getSafefunccount();
        safeothercount = event.getSafeothercount();
        damage1 = event.isDamage1();
        damage2 = event.isDamage2();
        damage3 = event.isDamage3();
        damage4 = event.isDamage4();
        damage5 = event.isDamage5();
        damage6 = event.isDamage6();
        damage7 = event.isDamage7();
        damage8 = event.isDamage8();
        damage9 = event.isDamage9();
        damage10 = event.isDamage10();
        damage11 = event.isDamage11();
        damage12 = event.isDamage12();
        damage13 = event.isDamage13();
        damage14 = event.isDamage14();
        damage15 = event.isDamage15();
        damage16 = event.isDamage16();

        maxSpeed = event.getMaxSpeed();
        collisionSpeed = event.getCollisionSpeed();
        vehicleSpeed = event.getVehicleSpeed();
        reportedSpeed = event.getReportedSpeed();
        speedUnit = event.getSpeedUnit();
        
        climate = (Climate) session.load(Climate.class,event.getClimate().getResourceBundleId());
        lighting = (Lighting) session.load(Lighting.class,event.getLighting().getResourceBundleId());
        way = (Way) session.load(Way.class,event.getWay().getResourceBundleId());
        direction = (CirculationDirection) session.load(CirculationDirection.class,event.getDirection().getResourceBundleId());
        control = (TrafficControl) session.load(TrafficControl.class,event.getControl().getResourceBundleId());
        pavement = (Pavement) session.load(Pavement.class,event.getPavement().getResourceBundleId());
        condition = (ConditionRout) session.load(ConditionRout.class,event.getCondition().getResourceBundleId());

        pedestrianCount = event.getPedestrianCount();
        pedestrian1 = event.getPedestrian1();
        pedestrianComment1 = event.getPedestrianComment1();
        pedestrian2 = event.getPedestrian2();
        pedestrianComment2 = event.getPedestrianComment2();
        pedestrian3 = event.getPedestrian3();
        pedestrianComment3 = event.getPedestrianComment3();
        
        cost = event.getCost();
        carcost = event.getCarCost();
        severity = event.getSeverity();
        
        vehiclesInvolved = new HashSet();
        Iterator it = event.getVehiclesInvolved().iterator();
        while(it.hasNext())  {
            vehiclesInvolved.add(new VehicleInvolved(((VehicleInvolvedDataType)it.next()),session));
        }

        superivisedCollision = (event.getSupervisedCollision() == null) ? null : (CollisionClasification)session.load(CollisionClasification.class,event.getSupervisedCollision().getResourceBundleId());
        superivisedAccident = (event.getSupervisedAccident() == null) ? null : (AccidentClasification)session.load(AccidentClasification.class,event.getSupervisedAccident().getResourceBundleId());
        superivisedIncident = (event.getSupervisedIncident() == null) ? null : (IncidentClasification)session.load(IncidentClasification.class,event.getSupervisedIncident().getResourceBundleId());
        
        if (isForest || event.getCorporation().getId().equals(CorporationData.CIMSA)
        		|| event.getCorporation().getId().equals(CorporationData.PEPSICO)){
        	 if (eventDamages != null)
             	eventDamages.clear();
             else
             	eventDamages = new HashSet();	
             eventDamages.addAll(event.getEventDamages());
        }
        
        workingHours = event.getWorkingHours();
        charged = event.getCharged();
        whoDrives = event.getWhoDrives();
        damageCharge = event.getDamageCharge();
        realCost = event.isRealCost();
        
        alcoholBreath = event.getAlcoholBreath();
        stoppedVehicle = event.getStoppedVehicle();
       		
        extraCost1 = event.getExtraCost1();
        extraCost2 = event.getExtraCost2();
        extraCost3 = event.getExtraCost3();
        extraCost4 = event.getExtraCost4();
        extraCost5 = event.getExtraCost5();
        extraCost6 = event.getExtraCost6();
        extraCost7 = event.getExtraCost7();
        extraCost8 = event.getExtraCost8();
       		
        ensuranceDate = event.getEnsuranceDate();
        companyAmount = event.getCompanyAmount();
        driverAmount = event.getDriverAmount();
        thirdAmount = event.getThirdAmount();
        
        folioNumber = event.getFolioNumber();
        sapNumber = event.getSapNumber();
        doubleCabin = event.isDoubleCabin();
        distractor = event.getDistractor();
        controlMultiple = event.getControlMultiple();
       
    }

    public void setEventData(EventDataType event,Session session,boolean isForest) {
    	super.setEventData(event, session);
        points = event.getPoints();
    	damage = event.isDamage();

        date = event.getDate();
        hour = event.getHour();

        companycar = event.getCompanyCar();
        
        zone = (Zone)session.load(Zone.class,event.getZone().getResourceBundleId());
        working = event.getWorking();
       
        policeReport= event.getPoliceReport();
        collision = (CollisionClasification)session.load(CollisionClasification.class,event.getCollision().getResourceBundleId());

        accident = (event.getAccident() == null) ? null : (AccidentClasification)session.load(AccidentClasification.class,event.getAccident().getResourceBundleId());
        incident = (event.getIncident() == null) ? null : (IncidentClasification)session.load(IncidentClasification.class,event.getIncident().getResourceBundleId());
        nearmiss = (event.getNearmiss() == null) ? null : (AccidentClasification)session.load(AccidentClasification.class,event.getNearmiss().getResourceBundleId());

        clasification1 = (event.getClasification1() == null) ? null : (AccidentClasificationLevel1)session.load(AccidentClasificationLevel1.class,event.getClasification1().getResourceBundleId());
        clasification2 = (event.getClasification2() == null) ? null : (AccidentClasificationLevel2)session.load(AccidentClasificationLevel2.class,event.getClasification2().getResourceBundleId());
        clasification3 = (event.getClasification3() == null) ? null : (AccidentClasificationLevel3)session.load(AccidentClasificationLevel3.class,event.getClasification3().getResourceBundleId());
        clasification4 = (event.getClasification4() == null) ? null : (AccidentClasificationLevel4)session.load(AccidentClasificationLevel4.class,event.getClasification4().getResourceBundleId());
        
        clasificationText=event.getClasificationText();
        
        clasificationCEPA1 = (event.getClasificationCEPA1() == null) ? null : (AccidentClasificationLevel1)session.load(AccidentClasificationLevel1.class,event.getClasificationCEPA1().getResourceBundleId());
        clasificationCEPA2 = (event.getClasificationCEPA2() == null) ? null : (AccidentClasificationLevel2)session.load(AccidentClasificationLevel2.class,event.getClasificationCEPA2().getResourceBundleId());
        clasificationCEPA3 = (event.getClasificationCEPA3() == null) ? null : (AccidentClasificationLevel3)session.load(AccidentClasificationLevel3.class,event.getClasificationCEPA3().getResourceBundleId());
        clasificationCEPA4 = (event.getClasificationCEPA4() == null) ? null : (AccidentClasificationLevel4)session.load(AccidentClasificationLevel4.class,event.getClasificationCEPA4().getResourceBundleId());
        clasificationCEPAText=event.getClasificationCEPAText();

        story = event.getStory();

        handle = (VehicleBehaviour)session.load(VehicleBehaviour.class,event.getHandle().getResourceBundleId());
        handleText=event.getHandleText();
        hitrun = event.getHitrun();
        
        principalFactor = (AccidentFactors)session.load(AccidentFactors.class,event.getPrincipalFactor().getResourceBundleId());
        principalFactorText=event.getPrincipalFactorText();
        
        factor1 = event.isFactor1();
        factor2 = event.isFactor2();
        factor3 = event.isFactor3();
        factor4 = event.isFactor4();
        factor5 = event.isFactor5();
        factor6 = event.isFactor6();
        factor7 = event.isFactor7();
        factor8 = event.isFactor8();
        factor9 = event.isFactor9();
        factor10 = event.isFactor10();
        factor11 = event.isFactor11();
        factor12 = event.isFactor12();
        factor13 = event.isFactor13();
        factor14 = event.isFactor14();
        factor15 = event.isFactor15();
        factor16 = event.isFactor16();
        factor17 = event.isFactor17();
        factor18 = event.isFactor18();
        factor19 = event.isFactor19();
        factor20 = event.isFactor20();
        factor21 = event.isFactor21();
        factor22 = event.isFactor22();
        collisionAngle = event.getCollisionAngle();
        
        selfbelt = event.getSelfbelt();
        alcohol = event.getAlcohol();
        otherVehicles = event.getOthervehicles();
        cellular = event.getCellular();
        
        driverstate = event.getDriverstate();
        dayslost = event.getDayslost();
        otherpartydayslost = event.getOtherpartydayslost();
        fatalitiesfunccount = event.getFatalitiesfunccount();
        injuredfunccount = event.getInjuredfunccount();
        fatalitiesothercount = event.getFatalitiesothercount();
        injuredothercount = event.getInjuredothercount();
        safefunccount = event.getSafefunccount();
        safeothercount = event.getSafeothercount();
        damage1 = event.isDamage1();
        damage2 = event.isDamage2();
        damage3 = event.isDamage3();
        damage4 = event.isDamage4();
        damage5 = event.isDamage5();
        damage6 = event.isDamage6();
        damage7 = event.isDamage7();
        damage8 = event.isDamage8();
        damage9 = event.isDamage9();
        damage10 = event.isDamage10();
        damage11 = event.isDamage11();
        damage12 = event.isDamage12();
        damage13 = event.isDamage13();
        damage14 = event.isDamage14();
        damage15 = event.isDamage15();
        damage16 = event.isDamage16();
        
        maxSpeed = event.getMaxSpeed();
        collisionSpeed = event.getCollisionSpeed();
        vehicleSpeed = event.getVehicleSpeed();
        reportedSpeed = event.getReportedSpeed();
        speedUnit = event.getSpeedUnit();
        
        climate = (Climate) session.load(Climate.class,event.getClimate().getResourceBundleId());
        lighting = (Lighting) session.load(Lighting.class,event.getLighting().getResourceBundleId());
        way = (Way) session.load(Way.class,event.getWay().getResourceBundleId());
        direction = (CirculationDirection) session.load(CirculationDirection.class,event.getDirection().getResourceBundleId());
        control = (TrafficControl) session.load(TrafficControl.class,event.getControl().getResourceBundleId());
        pavement = (Pavement) session.load(Pavement.class,event.getPavement().getResourceBundleId());
        condition = (ConditionRout) session.load(ConditionRout.class,event.getCondition().getResourceBundleId());

        pedestrianCount = event.getPedestrianCount();
        pedestrian1 = event.getPedestrian1();
        pedestrianComment1 = event.getPedestrianComment1();
        pedestrian2 = event.getPedestrian2();
        pedestrianComment2 = event.getPedestrianComment2();
        pedestrian3 = event.getPedestrian3();
        pedestrianComment3 = event.getPedestrianComment3();
        
        cost = event.getCost();
        carcost = event.getCarCost();
        severity = event.getSeverity();
        
        vehiclesInvolved = new HashSet();
        Iterator it = event.getVehiclesInvolved().iterator();
        while(it.hasNext())  {
            vehiclesInvolved.add(new VehicleInvolved(((VehicleInvolvedDataType)it.next()),session));
        }

        superivisedCollision = (event.getSupervisedCollision() == null) ? null : (CollisionClasification)session.load(CollisionClasification.class,event.getSupervisedCollision().getResourceBundleId());
        superivisedAccident = (event.getSupervisedAccident() == null) ? null : (AccidentClasification)session.load(AccidentClasification.class,event.getSupervisedAccident().getResourceBundleId());
        superivisedIncident = (event.getSupervisedIncident() == null) ? null : (IncidentClasification)session.load(IncidentClasification.class,event.getSupervisedIncident().getResourceBundleId());
        if (isForest || event.getCorporation().getId().equals(CorporationData.CIMSA)){
        	 if (eventDamages != null)
             	eventDamages.clear();
             else
             	eventDamages = new HashSet();	
             eventDamages.addAll(event.getEventDamages());
        }
        
        workingHours = event.getWorkingHours();
        charged = event.getCharged();
        whoDrives = event.getWhoDrives();
        damageCharge = event.getDamageCharge();
        realCost = event.isRealCost();
        
        alcoholBreath = event.getAlcoholBreath();
        stoppedVehicle = event.getStoppedVehicle();
       		
        extraCost1 = event.getExtraCost1();
        extraCost2 = event.getExtraCost2();
        extraCost3 = event.getExtraCost3();
        extraCost4 = event.getExtraCost4();
        extraCost5 = event.getExtraCost5();
        extraCost6 = event.getExtraCost6();
        extraCost7 = event.getExtraCost7();
        extraCost8 = event.getExtraCost8();
       		
        ensuranceDate = event.getEnsuranceDate();
        companyAmount = event.getCompanyAmount();
        driverAmount = event.getDriverAmount();
        thirdAmount = event.getThirdAmount();
        
        folioNumber = event.getFolioNumber();
        sapNumber = event.getSapNumber();
        doubleCabin = event.isDoubleCabin();
        
        distractor = event.getDistractor();
        economicNumber = event.getEconomicNumber();
        controlMultiple = event.getControlMultiple();
        
    }

    
    
    

	public AccidentClasificationLevel1 getClasificationCEPA1() {
		return clasificationCEPA1;
	}

	public void setClasificationCEPA1(AccidentClasificationLevel1 clasificationCEPA1) {
		this.clasificationCEPA1 = clasificationCEPA1;
	}

	public AccidentClasificationLevel2 getClasificationCEPA2() {
		return clasificationCEPA2;
	}

	public void setClasificationCEPA2(AccidentClasificationLevel2 clasificationCEPA2) {
		this.clasificationCEPA2 = clasificationCEPA2;
	}

	public AccidentClasificationLevel3 getClasificationCEPA3() {
		return clasificationCEPA3;
	}

	public void setClasificationCEPA3(AccidentClasificationLevel3 clasificationCEPA3) {
		this.clasificationCEPA3 = clasificationCEPA3;
	}

	public AccidentClasificationLevel4 getClasificationCEPA4() {
		return clasificationCEPA4;
	}

	public void setClasificationCEPA4(AccidentClasificationLevel4 clasificationCEPA4) {
		this.clasificationCEPA4 = clasificationCEPA4;
	}

	public String getClasificationCEPAText() {
		return clasificationCEPAText;
	}

	public void setClasificationCEPAText(String clasificationCEPAText) {
		this.clasificationCEPAText = clasificationCEPAText;
	}

	public boolean isDamage13() {
		return damage13;
	}

	public void setDamage13(boolean damage13) {
		this.damage13 = damage13;
	}

	public boolean isDamage14() {
		return damage14;
	}

	public void setDamage14(boolean damage14) {
		this.damage14 = damage14;
	}

	public boolean isDamage11() {
		return damage11;
	}

	public void setDamage11(boolean damage11) {
		this.damage11 = damage11;
	}

	public boolean isDamage12() {
		return damage12;
	}

	public void setDamage12(boolean damage12) {
		this.damage12 = damage12;
	}

	public String getHandleText() {
		return handleText;
	}

	public void setHandleText(String handleText) {
		this.handleText = handleText;
	}

	public String getPrincipalFactorText() {
		return principalFactorText;
	}

	public void setPrincipalFactorText(String principalFactorText) {
		this.principalFactorText = principalFactorText;
	}

	public AccidentClasificationLevel4 getClasification4() {
		return clasification4;
	}

	public void setClasification4(AccidentClasificationLevel4 clasification4) {
		this.clasification4 = clasification4;
	}

	public String getClasificationText() {
		return clasificationText;
	}

	public void setClasificationText(String clasificationText) {
		this.clasificationText = clasificationText;
	}

	public AccidentClasificationLevel1 getClasification1() {
		return clasification1;
	}

	public void setClasification1(AccidentClasificationLevel1 clasification1) {
		this.clasification1 = clasification1;
	}

	public AccidentClasificationLevel2 getClasification2() {
		return clasification2;
	}

	public void setClasification2(AccidentClasificationLevel2 clasification2) {
		this.clasification2 = clasification2;
	}

	public AccidentClasificationLevel3 getClasification3() {
		return clasification3;
	}

	public void setClasification3(AccidentClasificationLevel3 clasification3) {
		this.clasification3 = clasification3;
	}

	/**
     * @return Returns the accident.
     */
    public AccidentClasification getAccident() {
        return accident;
    }

    /**
     * @return Returns the alcohol.
     */
    public String getAlcohol() {
        return alcohol;
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
    public Climate getClimate() {
        return climate;
    }

    /**
     * @return Returns the collision.
     */
    public CollisionClasification getCollision() {
        return collision;
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
    public ConditionRout getCondition() {
        return condition;
    }

    /**
     * @return Returns the control.
     */
    public TrafficControl getControl() {
        return control;
    }

    /**
     * @return Returns the damage.
     */
    public boolean isDamage() {
        return damage;
    }

    /**
     * @return Returns the damage1.
     */
    public boolean isDamage1() {
        return damage1;
    }

    /**
     * @return Returns the damage10.
     */
    public boolean isDamage10() {
        return damage10;
    }

    /**
     * @return Returns the damage2.
     */
    public boolean isDamage2() {
        return damage2;
    }

    /**
     * @return Returns the damage3.
     */
    public boolean isDamage3() {
        return damage3;
    }

    /**
     * @return Returns the damage4.
     */
    public boolean isDamage4() {
        return damage4;
    }

    /**
     * @return Returns the damage5.
     */
    public boolean isDamage5() {
        return damage5;
    }

    /**
     * @return Returns the damage6.
     */
    public boolean isDamage6() {
        return damage6;
    }

    /**
     * @return Returns the damage7.
     */
    public boolean isDamage7() {
        return damage7;
    }

    /**
     * @return Returns the damage8.
     */
    public boolean isDamage8() {
        return damage8;
    }

    /**
     * @return Returns the damage9.
     */
    public boolean isDamage9() {
        return damage9;
    }

    /**
     * @return Returns the dayslost.
     */
    public Integer getDayslost() {
        return dayslost;
    }

    /**
     * @return Returns the direction.
     */
    public CirculationDirection getDirection() {
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
    public VehicleBehaviour getHandle() {
        return handle;
    }

    /**
     * @return Returns the hitrun.
     */
    public String getHitrun() {
        return hitrun;
    }

    /**
     * @return Returns the incident.
     */
    public IncidentClasification getIncident() {
        return incident;
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
    public Lighting getLighting() {
        return lighting;
    }

    /**
     * @return Returns the maxspeed.
     */
    public Integer getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * @return Returns the othervehicles.
     */
    public String getOtherVehicles() {
        return otherVehicles;
    }

    /**
     * @return Returns the pavement.
     */
    public Pavement getPavement() {
        return pavement;
    }

    /**
     * @return Returns the pedestrian1.
     */
    public String getPedestrian1() {
        return pedestrian1;
    }

    /**
     * @return Returns the pedestrian2.
     */
    public String getPedestrian2() {
        return pedestrian2;
    }

    /**
     * @return Returns the pedestrian3.
     */
    public String getPedestrian3() {
        return pedestrian3;
    }

    /**
     * @return Returns the pedestrianComment1.
     */
    public String getPedestrianComment1() {
        return pedestrianComment1;
    }

    /**
     * @return Returns the pedestrianComment2.
     */
    public String getPedestrianComment2() {
        return pedestrianComment2;
    }

    /**
     * @return Returns the pedestrianComment3.
     */
    public String getPedestrianComment3() {
        return pedestrianComment3;
    }

    /**
     * @return Returns the pedestrianCount.
     */
    public Integer getPedestrianCount() {
        return pedestrianCount;
    }

    /**
     * @return Returns the principalFactor.
     */
    public AccidentFactors getPrincipalFactor() {
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
     * @return Returns the story.
     */
    public String getStory() {
        return story;
    }

    /**
     * @return Returns the vehiclesInvolved.
     */
    public Set getVehiclesInvolved() {
        return vehiclesInvolved;
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
    public Way getWay() {
        return way;
    }

    /**
     * @return Returns the zone.
     */
    public Zone getZone() {
        return zone;
    }
    
    
    /**
     * @return Returns the PoliceReport.
     */
    public String getPoliceReport() {
        return policeReport;
    }

    /**
     * @param accident The accident to set.
     */
    public void setAccident(AccidentClasification accident) {
        this.accident = accident;
    }

    /**
     * @param alcohol The alcohol to set.
     */
    public void setAlcohol(String alcohol) {
        this.alcohol = alcohol;
    }

    /**
     * @param cellular The cellular to set.
     */
    public void setCellular(String cellular) {
        this.cellular = cellular;
    }

    /**
     * @param climate The climate to set.
     */
    public void setClimate(Climate climate) {
        this.climate = climate;
    }

    /**
     * @param collision The collision to set.
     */
    public void setCollision(CollisionClasification collision) {
        this.collision = collision;
    }

    /**
     * @param collisionangle The collisionangle to set.
     */
    public void setCollisionAngle(String collisionAngle) {
        this.collisionAngle = collisionAngle;
    }

    /**
     * @param collisionspeed The collisionspeed to set.
     */
    public void setCollisionSpeed(Integer collisionSpeed) {
        this.collisionSpeed = collisionSpeed;
    }

    /**
     * @param condition The condition to set.
     */
    public void setCondition(ConditionRout condition) {
        this.condition = condition;
    }

    /**
     * @param control The control to set.
     */
    public void setControl(TrafficControl control) {
        this.control = control;
    }

    /**
     * @param damage The damage to set.
     */
    public void setDamage(boolean damage) {
        this.damage = damage;
    }

    /**
     * @param damage1 The damage1 to set.
     */
    public void setDamage1(boolean damage1) {
        this.damage1 = damage1;
    }

    /**
     * @param damage10 The damage10 to set.
     */
    public void setDamage10(boolean damage10) {
        this.damage10 = damage10;
    }

    /**
     * @param damage2 The damage2 to set.
     */
    public void setDamage2(boolean damage2) {
        this.damage2 = damage2;
    }

    /**
     * @param damage3 The damage3 to set.
     */
    public void setDamage3(boolean damage3) {
        this.damage3 = damage3;
    }

    /**
     * @param damage4 The damage4 to set.
     */
    public void setDamage4(boolean damage4) {
        this.damage4 = damage4;
    }

    /**
     * @param damage5 The damage5 to set.
     */
    public void setDamage5(boolean damage5) {
        this.damage5 = damage5;
    }

    /**
     * @param damage6 The damage6 to set.
     */
    public void setDamage6(boolean damage6) {
        this.damage6 = damage6;
    }

    /**
     * @param damage7 The damage7 to set.
     */
    public void setDamage7(boolean damage7) {
        this.damage7 = damage7;
    }

    /**
     * @param damage8 The damage8 to set.
     */
    public void setDamage8(boolean damage8) {
        this.damage8 = damage8;
    }

    /**
     * @param damage9 The damage9 to set.
     */
    public void setDamage9(boolean damage9) {
        this.damage9 = damage9;
    }

    /**
     * @param dayslost The dayslost to set.
     */
    public void setDayslost(Integer dayslost) {
        this.dayslost = dayslost;
    }

    /**
     * @param direction The direction to set.
     */
    public void setDirection(CirculationDirection direction) {
        this.direction = direction;
    }

    /**
     * @param driverstate The driverstate to set.
     */
    public void setDriverstate(String driverstate) {
        this.driverstate = driverstate;
    }
 /**
     * @param fatalitiesfunccount The fatalitiesfunccount to set.
     */
    public void setFatalitiesfunccount(Integer fatalitiesfunccount) {
        this.fatalitiesfunccount = fatalitiesfunccount;
    }

    /**
     * @param fatalitiesothercount The fatalitiesothercount to set.
     */
    public void setFatalitiesothercount(Integer fatalitiesothercount) {
        this.fatalitiesothercount = fatalitiesothercount;
    }

    /**
     * @param handle The handle to set.
     */
    public void setHandle(VehicleBehaviour handle) {
        this.handle = handle;
    }

    /**
     * @param hitrun The hitrun to set.
     */
    public void setHitrun(String hitrun) {
        this.hitrun = hitrun;
    }

    /**
     * @param incident The incident to set.
     */
    public void setIncident(IncidentClasification incident) {
        this.incident = incident;
    }

    /**
     * @param injuredfunccount The injuredfunccount to set.
     */
    public void setInjuredfunccount(Integer injuredfunccount) {
        this.injuredfunccount = injuredfunccount;
    }

    /**
     * @param injuredothercount The injuredothercount to set.
     */
    public void setInjuredothercount(Integer injuredothercount) {
        this.injuredothercount = injuredothercount;
    }
    
    /**
     * @param safefunccount The safefunccount to set.
     */
    public void setSafefunccount(Integer safefunccount) {
        this.safefunccount = safefunccount;
    }

    /**
     * @param safeothercount The safeothercount to set.
     */
    public void setSafeothercount(Integer safeothercount) {
        this.safeothercount = safeothercount;
    }

    /**
     * @param lighting The lighting to set.
     */
    public void setLighting(Lighting lighting) {
        this.lighting = lighting;
    }

    /**
     * @param maxspeed The maxspeed to set.
     */
    public void setMaxSpeed(Integer maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    /**
     * @param othervehicles The othervehicles to set.
     */
    public void setOtherVehicles(String othervehicles) {
        this.otherVehicles = othervehicles;
    }

    /**
     * @param pavement The pavement to set.
     */
    public void setPavement(Pavement pavement) {
        this.pavement = pavement;
    }

    /**
     * @param pedestrian1 The pedestrian1 to set.
     */
    public void setPedestrian1(String pedestrian1) {
        this.pedestrian1 = pedestrian1;
    }

    /**
     * @param pedestrian2 The pedestrian2 to set.
     */
    public void setPedestrian2(String pedestrian2) {
        this.pedestrian2 = pedestrian2;
    }

    /**
     * @param pedestrian3 The pedestrian3 to set.
     */
    public void setPedestrian3(String pedestrian3) {
        this.pedestrian3 = pedestrian3;
    }

    /**
     * @param pedestrianComment1 The pedestrianComment1 to set.
     */
    public void setPedestrianComment1(String pedestrianComment1) {
        this.pedestrianComment1 = pedestrianComment1;
    }

    /**
     * @param pedestrianComment2 The pedestrianComment2 to set.
     */
    public void setPedestrianComment2(String pedestrianComment2) {
        this.pedestrianComment2 = pedestrianComment2;
    }

    /**
     * @param pedestrianComment3 The pedestrianComment3 to set.
     */
    public void setPedestrianComment3(String pedestrianComment3) {
        this.pedestrianComment3 = pedestrianComment3;
    }

    /**
     * @param pedestrianCount The pedestrianCount to set.
     */
    public void setPedestrianCount(Integer pedestrianCount) {
        this.pedestrianCount = pedestrianCount;
    }

    /**
     * @param principalFactor The principalFactor to set.
     */
    public void setPrincipalFactor(AccidentFactors principalFactor) {
        this.principalFactor = principalFactor;
    }

    /**
     * @param reportedspeed The reportedspeed to set.
     */
    public void setReportedSpeed(Integer reportedSpeed) {
        this.reportedSpeed = reportedSpeed;
    }

    /**
     * @param selfbelt The selfbelt to set.
     */
    public void setSelfbelt(String selfbelt) {
        this.selfbelt = selfbelt;
    }

    /**
     * @param speedunit The speedunit to set.
     */
    public void setSpeedUnit(String speedUnit) {
        this.speedUnit = speedUnit;
    }

    /**
     * @param story The story to set.
     */
    public void setStory(String story) {
        this.story = story;
    }

    /**
     * @param vehiclesInvolved The vehiclesInvolved to set.
     */
    public void setVehiclesInvolved(Set vehiclesInvolved) {
        this.vehiclesInvolved = vehiclesInvolved;
    }

    /**
     * @param vehiclespeed The vehiclespeed to set.
     */
    public void setVehicleSpeed(Integer vehicleSpeed) {
        this.vehicleSpeed = vehicleSpeed;
    }

    /**
     * @param way The way to set.
     */
    public void setWay(Way way) {
        this.way = way;
    }

    /**
     * @param zone The zone to set.
     */
    public void setZone(Zone zone) {
        this.zone = zone;
    }
    /**
     * @param PoliceReport The PoliceReport to set.
     */
    public void setPoliceReport(String policeReport) {
        this.policeReport = policeReport;
    }

    
  
    
    
    public EventDataType getEventData() {
        EventDataType event = new EventDataType();
        if(accident == null) {
            event.setType(EventDataType.INCIDENT);
        }else if(incident == null) {
            event.setType(EventDataType.ACCIDENT);
        }else {
            event.setType(EventDataType.NEARMISS);
        }
        event.setId(id);
        event.setPoints(points);
        event.setStatus(status);
        event.setCorporation(corpCountry.getPk().getCorporation().getData());
        
        GeoLabelAttributes[] geoAttrs = event.getCorporation().getGeoLabels();
        GeoItemData countryData = (GeoItemData)corpCountry.getData();
        Collection countries = new LinkedList();
        countries.add(countryData);
        GeoLabelData labelData = null; 
        if(geoLevel1 != null) {
            GeoItemData level1 = (GeoItemData)geoLevel1.getData();
            Collection level1s = new LinkedList();
            level1s.add(level1);
            if(geoLevel1.getGeoLevel2() == null) {
                level1.setComponents(new GeoLabelData(geoAttrs[1].getId(),geoAttrs[1].getResourceKey(),geoAttrs[1].getLevel(),countries));
                labelData = new GeoLabelData(geoAttrs[0].getId(),geoAttrs[0].getResourceKey(),geoAttrs[0].getLevel(),level1s);
            }else {
                
                level1.setComponents(new GeoLabelData(geoAttrs[2].getId(),geoAttrs[2].getResourceKey(),geoAttrs[2].getLevel(),countries));
                
                GeoItemData level2 = (GeoItemData)geoLevel1.getGeoLevel2().getData();
                level2.setComponents(new GeoLabelData(geoAttrs[1].getId(),geoAttrs[1].getResourceKey(),geoAttrs[1].getLevel(),level1s));

                Collection level2s = new LinkedList();
                level2s.add(level2);
                
                labelData = new GeoLabelData(geoAttrs[0].getId(),geoAttrs[0].getResourceKey(),geoAttrs[0].getLevel(),level2s);
            }
        }else {
            labelData = new GeoLabelData(geoAttrs[0].getId(),geoAttrs[0].getResourceKey(),geoAttrs[0].getLevel(),countries);
        }
        event.setCountry(labelData);
        
        Collection divOrgs = new LinkedList();
        if(divOrg1 != null) {
            DivOrgData divOrgData = getDivOrgData(divOrg1,event.getCorporation());
            if(divOrgData != null) {
                divOrgs.add(divOrgData);
            }
        }
        if(divOrg2 != null) {
            DivOrgData divOrgData = getDivOrgData(divOrg2,event.getCorporation());
            if(divOrgData != null) {
                divOrgs.add(divOrgData);
            }
        }
        if(divOrg3 != null) {
            DivOrgData divOrgData = getDivOrgData(divOrg3,event.getCorporation());
            if(divOrgData != null) {
                divOrgs.add(divOrgData);
            }
        }
        
        event.setDriver(driver.getDriverData());
        
        event.setDivOrgs(divOrgs);
        
        if(vehicle != null) {
            event.setVehicle(vehicle.getVehicleData()); 
        }else {
            event.setPlate(plate);
            event.setModel(model);
            event.setTrade(trade);
            event.setYear(year);
            event.setEconomicNumber(economicNumber);
        }
        event.setState(state);
        event.setCity(city);
        
        event.setDate(date);
        event.setHour(hour);
        
        event.setCompanyCar(companycar);
        
        event.setZone((ZoneDataType)zone.getData());
        event.setWorking(working);
        event.setPoliceReport(policeReport);
        
        event.setDamage(damage);
            
        event.setStory(story);

        if(collision!=null){
        	event.setCollision((CollisionClasificationDataType)collision.getData());	
        }else{
        	event.setCollision(new CollisionClasificationDataType(""));
        }
        
        if(clasification1!=null){
        	event.setClasification1((AccidentClasificationLevel1DataType)clasification1.getData());
        }
        if(clasification2!=null){
        	event.setClasification2((AccidentClasificationLevel2DataType)clasification2.getData());
        }
        if(clasification3!=null){
        	event.setClasification3((AccidentClasificationLevel3DataType)clasification3.getData());
        }
        if(clasification4!=null){
        	event.setClasification4((AccidentClasificationLevel4DataType)clasification4.getData());
        }
        
        event.setClasificationText(clasificationText);
        
        if(clasificationCEPA1!=null){
        	event.setClasificationCEPA1((AccidentClasificationLevel1DataType)clasificationCEPA1.getData());
        }
        if(clasificationCEPA2!=null){
        	event.setClasificationCEPA2((AccidentClasificationLevel2DataType)clasificationCEPA2.getData());
        }
        if(clasificationCEPA3!=null){
        	event.setClasificationCEPA3((AccidentClasificationLevel3DataType)clasificationCEPA3.getData());
        }
        if(clasificationCEPA4!=null){
        	event.setClasificationCEPA4((AccidentClasificationLevel4DataType)clasificationCEPA4.getData());
        }
        
        event.setClasificationCEPAText(clasificationCEPAText);
        
        if(accident != null) {
            event.setAccident((AccidentClasificationDataType)accident.getData());
        }
        if(incident != null) {
            event.setIncident((IncidentClasificationDataType)incident.getData());
        }
        if(nearmiss != null) {
            event.setNearmiss((AccidentClasificationDataType)nearmiss.getData());
        }
        
        event.setHandle((VehicleBehaviourDataType)handle.getData());
        event.setHandleText(handleText);

        event.setHitrun(hitrun);

        if(principalFactor != null) {
	        event.setPrincipalFactor((AccidentFactorsDataType) principalFactor.getData());
	        event.setPrincipalFactorText(principalFactorText);
        }
        
        event.setFactor1(factor1);
        event.setFactor2(factor2);
        event.setFactor3(factor3);
        event.setFactor4(factor4);
        event.setFactor5(factor5);
        event.setFactor6(factor6);
        event.setFactor7(factor7);
        event.setFactor8(factor8);
        event.setFactor9(factor9);
        event.setFactor10(factor10);
        event.setFactor11(factor11);
        event.setFactor12(factor12);
        event.setFactor13(factor13);
        event.setFactor14(factor14);
        event.setFactor15(factor15);
        event.setFactor16(factor16);
        event.setFactor17(factor17);
        event.setFactor18(factor18);
        event.setFactor19(factor19);
        event.setFactor20(factor20);
        event.setFactor21(factor21);
        event.setFactor22(factor22);
        
        event.setCollisionAngle(collisionAngle);
        
        event.setDriverstate(driverstate);
        event.setDayslost(dayslost);
        event.setOtherpartydayslost(otherpartydayslost);
        event.setFatalitiesfunccount(fatalitiesfunccount);
        event.setFatalitiesothercount(fatalitiesothercount);
        event.setInjuredfunccount(injuredfunccount);
        event.setInjuredothercount(injuredothercount);
        event.setSafefunccount(safefunccount);
        event.setSafeothercount(safeothercount);
        
        event.setDamage1(damage1);
        event.setDamage2(damage2);
        event.setDamage3(damage3);
        event.setDamage4(damage4);
        event.setDamage5(damage5);
        event.setDamage6(damage6);
        event.setDamage7(damage7);
        event.setDamage8(damage8);
        event.setDamage9(damage9);
        event.setDamage10(damage10);
        event.setDamage11(damage11);
        event.setDamage12(damage12);
        event.setDamage13(damage13);
        event.setDamage14(damage14);
        event.setDamage15(damage15);
        event.setDamage16(damage16);

        event.setSelfbelt(selfbelt);
        event.setAlcohol(alcohol);
        event.setOthervehicles(otherVehicles);
        event.setCellular(cellular);
        
        event.setMaxSpeed(maxSpeed);
        event.setCollisionSpeed(collisionSpeed);
        event.setVehicleSpeed(vehicleSpeed);
        event.setReportedSpeed(reportedSpeed);
        event.setSpeedUnit(speedUnit);

        event.setClimate((ClimateDataType)climate.getData());
        event.setLighting((LightingDataType)lighting.getData());
        event.setWay((WayDataType)way.getData());
        event.setDirection((CirculationDirectionDataType)direction.getData());
        event.setControl((TrafficControlDataType)control.getData());
        event.setPavement((PavementDataType)pavement.getData());
        event.setCondition((ConditionRoutDataType)condition.getData());

        event.setPedestrianCount(pedestrianCount);
        event.setPedestrian1(pedestrian1);
        event.setPedestrianComment1(pedestrianComment1);
        event.setPedestrian2(pedestrian2);
        event.setPedestrianComment2(pedestrianComment2);
        event.setPedestrian3(pedestrian3);
        event.setPedestrianComment3(pedestrianComment3);

        event.setCost(cost);
        event.setCarCost(carcost);
        event.setSeverity(severity);
        
        Collection vehicles = new LinkedList();
        Iterator it = vehiclesInvolved.iterator();
        while(it.hasNext()) {
            vehicles.add(((VehicleInvolved)it.next()).getVehicleData());
        }
        event.setVehiclesInvolved(vehicles);
        
        event.setEvitability(evitability);
        event.setRecomendation(recomendation);
        
        if(superivisedCollision != null) {
            event.setSupervisedCollision((CollisionClasificationDataType)superivisedCollision.getData());
        }
        
        if(superivisedAccident != null) {
            event.setSupervisedAccident((AccidentClasificationDataType)superivisedAccident.getData());
        }
        if(superivisedIncident != null) {
            event.setSupervisedIncident((IncidentClasificationDataType)superivisedIncident.getData());
        }

        event.setNonrecordable(nonrecordable);
        event.setSuperivisionObservations(supervisionObservations);
        
        event.setEventDamages(this.eventDamages);
        
        event.setWorkingHours(workingHours);
        event.setCharged(charged);
        event.setWhoDrives(whoDrives);
        event.setDamageCharge(damageCharge);
        event.setRealCost(realCost);
        
        event.setLote(lote);
        
        event.setAlcoholBreath(alcoholBreath);
        event.setStoppedVehicle(stoppedVehicle);
       		
        event.setExtraCost1(extraCost1);
        event.setExtraCost2(extraCost2);
        event.setExtraCost3(extraCost3);
        event.setExtraCost4(extraCost4);
        event.setExtraCost5(extraCost5);
        event.setExtraCost6(extraCost6);
        event.setExtraCost7(extraCost7);
        event.setExtraCost8(extraCost8);
       		
        event.setEnsuranceDate(ensuranceDate);
       	event.setCompanyAmount(companyAmount);
       	event.setDriverAmount(driverAmount);
       	event.setThirdAmount(thirdAmount);
       	
       	event.setFolioNumber(folioNumber);
       	event.setSapNumber(sapNumber);
       	event.setDoubleCabin(doubleCabin);
       	event.setDistractor(distractor);
       	event.setControlMultiple(controlMultiple);
   
       	
        return event;
    }

    public EventMainAttributes getMainAttributes() {
        EventMainAttributes event = super.getMainAttributes();
        if(accident != null) {
            event.setType(EventDataType.ACCIDENT);
        }else if(incident != null) {
            event.setType(EventDataType.INCIDENT);
        }else {
        	event.setType(EventDataType.NEARMISS);
        }
        return event;
    }

    /**
     * @return Returns the cost.
     */
    public Integer getCost() {
        return cost;
    }

    /**
     * @param cost The cost to set.
     */
    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getOtherpartydayslost() {
        return otherpartydayslost;
    }

    public void setOtherpartydayslost(Integer otherpartydayslost) {
        this.otherpartydayslost = otherpartydayslost;
    }

    public AccidentClasification getSuperivisedAccident() {
        return superivisedAccident;
    }

    public CollisionClasification getSuperivisedCollision() {
        return superivisedCollision;
    }

    public IncidentClasification getSuperivisedIncident() {
        return superivisedIncident;
    }

    public void setSuperivisedAccident(AccidentClasification superivisedAccident) {
        this.superivisedAccident = superivisedAccident;
    }

    public void setSuperivisedCollision(CollisionClasification superivisedCollision) {
        this.superivisedCollision = superivisedCollision;
    }

    public void setSuperivisedIncident(IncidentClasification superivisedIncident) {
        this.superivisedIncident = superivisedIncident;
    }

	/**
	 * @return Returns the eventDamages.
	 */
	public Set getEventDamages() {
		return eventDamages;
	}

	/**
	 * @param eventDamages The eventDamages to set.
	 */
	public void setEventDamages(Set eventDamages) {
		this.eventDamages = eventDamages;
	}

    public Integer getCarcost() {
        return carcost;
    }

    public Integer getSeverity() {
        return severity;
    }

    public void setCarcost(Integer carcost) {
        this.carcost = carcost;
    }

    public void setSeverity(Integer severity) {
        this.severity = severity;
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

	public String getDamageCharge() {
		return damageCharge;
	}

	public void setDamageCharge(String damageCharge) {
		this.damageCharge = damageCharge;
	}

	public String getCharged() {
		return charged;
	}

	public void setCharged(String charged) {
		this.charged = charged;
	}

	public String getAlcoholBreath() {
		return alcoholBreath;
	}

	public void setAlcoholBreath(String alcoholBreath) {
		this.alcoholBreath = alcoholBreath;
	}

	public String getStoppedVehicle() {
		return stoppedVehicle;
	}

	public void setStoppedVehicle(String stoppedVehicle) {
		this.stoppedVehicle = stoppedVehicle;
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

	public Integer getExtraCost4() {
		return extraCost4;
	}

	public void setExtraCost4(Integer extraCost4) {
		this.extraCost4 = extraCost4;
	}

	public Integer getExtraCost5() {
		return extraCost5;
	}

	public void setExtraCost5(Integer extraCost5) {
		this.extraCost5 = extraCost5;
	}

	public Integer getExtraCost6() {
		return extraCost6;
	}

	public void setExtraCost6(Integer extraCost6) {
		this.extraCost6 = extraCost6;
	}

	public Integer getExtraCost7() {
		return extraCost7;
	}

	public void setExtraCost7(Integer extraCost7) {
		this.extraCost7 = extraCost7;
	}

	public Integer getExtraCost8() {
		return extraCost8;
	}

	public void setExtraCost8(Integer extraCost8) {
		this.extraCost8 = extraCost8;
	}

	public Date getEnsuranceDate() {
		return ensuranceDate;
	}

	public void setEnsuranceDate(Date ensuranceDate) {
		this.ensuranceDate = ensuranceDate;
	}

	public Integer getCompanyAmount() {
		return companyAmount;
	}

	public void setCompanyAmount(Integer companyAmount) {
		this.companyAmount = companyAmount;
	}

	public Integer getDriverAmount() {
		return driverAmount;
	}

	public void setDriverAmount(Integer driverAmount) {
		this.driverAmount = driverAmount;
	}

	public Integer getThirdAmount() {
		return thirdAmount;
	}

	public void setThirdAmount(Integer thirdAmount) {
		this.thirdAmount = thirdAmount;
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
	 * @return the folioNumber
	 */
	public Integer getFolioNumber() {
		return folioNumber;
	}

	/**
	 * @param folioNumber the folioNumber to set
	 */
	public void setFolioNumber(Integer folioNumber) {
		this.folioNumber = folioNumber;
	}

	/**
	 * @return the sapNumber
	 */
	public Integer getSapNumber() {
		return sapNumber;
	}

	/**
	 * @param sapNumber the sapNumber to set
	 */
	public void setSapNumber(Integer sapNumber) {
		this.sapNumber = sapNumber;
	}

	public String getWhoDrives() {
		return whoDrives;
	}

	public void setWhoDrives(String whoDrives) {
		this.whoDrives = whoDrives;
	}

	/**
	 * @return the doubleCabin
	 */
	public boolean isDoubleCabin() {
		return doubleCabin;
	}

	/**
	 * @param doubleCabin the doubleCabin to set
	 */
	public void setDoubleCabin(boolean doubleCabin) {
		this.doubleCabin = doubleCabin;
	}

	public AccidentClasification getNearmiss() {
		return nearmiss;
	}

	public void setNearmiss(AccidentClasification nearmiss) {
		this.nearmiss = nearmiss;
	}


	/**
	 * @return the distrctor
	 */
	public String getDistractor() {
		return distractor;
	}

	/**
	 * @param distrctor the distrctor to set
	 */
	public void setDistractor(String distractor) {
		this.distractor = distractor;
	}

	/**
	 * @return the controlMultiple
	 */
	public String getControlMultiple() {
		return controlMultiple;
	}

	/**
	 * @param controlMultiple the controlMultiple to set
	 */
	public void setControlMultiple(String controlMultiple) {
		this.controlMultiple = controlMultiple;
	}

	public boolean isDamage15() {
		return damage15;
	}

	public void setDamage15(boolean damage15) {
		this.damage15 = damage15;
	}

	public boolean isDamage16() {
		return damage16;
	}

	public void setDamage16(boolean damage16) {
		this.damage16 = damage16;
	}

	public void updateData(EventDataType event, Session session) {
        this.points = event.getPoints();
        state = event.getState();
        
        this.date = event.getDate();
        this.expiry = event.getExpiry();
        this.hour = event.getHour();
        this.working = event.getWorking();
        

        status = event.getStatus();
        city = event.getCity();
        
        evitability = event.getEvitability();
        recomendation = event.getRecomendation();
        nonrecordable =event.getNonrecordable();
        supervisionObservations = event.getSuperivisionObservations();
        
        lote = event.getLote();
        points = event.getPoints();
    	damage = event.isDamage();

        date = event.getDate();
        hour = event.getHour();

        companycar = event.getCompanyCar();
        
        zone = (Zone)session.load(Zone.class,event.getZone().getResourceBundleId());
        working = event.getWorking();
       
        policeReport= event.getPoliceReport();

        clasification1 = (event.getClasification1() == null) ? null : (AccidentClasificationLevel1)session.load(AccidentClasificationLevel1.class,event.getClasification1().getResourceBundleId());
        clasification2 = (event.getClasification2() == null) ? null : (AccidentClasificationLevel2)session.load(AccidentClasificationLevel2.class,event.getClasification2().getResourceBundleId());
        clasification3 = (event.getClasification3() == null) ? null : (AccidentClasificationLevel3)session.load(AccidentClasificationLevel3.class,event.getClasification3().getResourceBundleId());
        clasification4 = (event.getClasification4() == null) ? null : (AccidentClasificationLevel4)session.load(AccidentClasificationLevel4.class,event.getClasification4().getResourceBundleId());
        
        clasificationText=event.getClasificationText();
        
        clasificationCEPA1 = (event.getClasificationCEPA1() == null) ? null : (AccidentClasificationLevel1)session.load(AccidentClasificationLevel1.class,event.getClasificationCEPA1().getResourceBundleId());
        clasificationCEPA2 = (event.getClasificationCEPA2() == null) ? null : (AccidentClasificationLevel2)session.load(AccidentClasificationLevel2.class,event.getClasificationCEPA2().getResourceBundleId());
        clasificationCEPA3 = (event.getClasificationCEPA3() == null) ? null : (AccidentClasificationLevel3)session.load(AccidentClasificationLevel3.class,event.getClasificationCEPA3().getResourceBundleId());
        clasificationCEPA4 = (event.getClasificationCEPA4() == null) ? null : (AccidentClasificationLevel4)session.load(AccidentClasificationLevel4.class,event.getClasificationCEPA4().getResourceBundleId());
        clasificationCEPAText=event.getClasificationCEPAText();

        story = event.getStory();

        handle = (VehicleBehaviour)session.load(VehicleBehaviour.class,event.getHandle().getResourceBundleId());
        handleText=event.getHandleText();
        hitrun = event.getHitrun();
        
        principalFactor = (AccidentFactors)session.load(AccidentFactors.class,event.getPrincipalFactor().getResourceBundleId());
        principalFactorText=event.getPrincipalFactorText();
        
        factor1 = event.isFactor1();
        factor2 = event.isFactor2();
        factor3 = event.isFactor3();
        factor4 = event.isFactor4();
        factor5 = event.isFactor5();
        factor6 = event.isFactor6();
        factor7 = event.isFactor7();
        factor8 = event.isFactor8();
        factor9 = event.isFactor9();
        factor10 = event.isFactor10();
        factor11 = event.isFactor11();
        factor12 = event.isFactor12();
        factor13 = event.isFactor13();
        factor14 = event.isFactor14();
        factor15 = event.isFactor15();
        factor16 = event.isFactor16();
        factor17 = event.isFactor17();
        factor18 = event.isFactor18();
        factor19 = event.isFactor19();
        factor20 = event.isFactor20();
        factor21 = event.isFactor21();
        factor22 = event.isFactor22();

        collisionAngle = event.getCollisionAngle();
        
        selfbelt = event.getSelfbelt();
        alcohol = event.getAlcohol();
        otherVehicles = event.getOthervehicles();
        cellular = event.getCellular();
        
        driverstate = event.getDriverstate();
        dayslost = event.getDayslost();
        otherpartydayslost = event.getOtherpartydayslost();
        fatalitiesfunccount = event.getFatalitiesfunccount();
        injuredfunccount = event.getInjuredfunccount();
        fatalitiesothercount = event.getFatalitiesothercount();
        injuredothercount = event.getInjuredothercount();
        safefunccount = event.getSafefunccount();
        safeothercount = event.getSafeothercount();
        damage1 = event.isDamage1();
        damage2 = event.isDamage2();
        damage3 = event.isDamage3();
        damage4 = event.isDamage4();
        damage5 = event.isDamage5();
        damage6 = event.isDamage6();
        damage7 = event.isDamage7();
        damage8 = event.isDamage8();
        damage9 = event.isDamage9();
        damage10 = event.isDamage10();
        damage11 = event.isDamage11();
        damage12 = event.isDamage12();
        damage13 = event.isDamage13();
        damage14 = event.isDamage14();
        damage15 = event.isDamage15();
        damage16 = event.isDamage16();
        
        maxSpeed = event.getMaxSpeed();
        collisionSpeed = event.getCollisionSpeed();
        vehicleSpeed = event.getVehicleSpeed();
        reportedSpeed = event.getReportedSpeed();
        speedUnit = event.getSpeedUnit();
        
        climate = (Climate) session.load(Climate.class,event.getClimate().getResourceBundleId());
        lighting = (Lighting) session.load(Lighting.class,event.getLighting().getResourceBundleId());
        way = (Way) session.load(Way.class,event.getWay().getResourceBundleId());
        direction = (CirculationDirection) session.load(CirculationDirection.class,event.getDirection().getResourceBundleId());
        control = (TrafficControl) session.load(TrafficControl.class,event.getControl().getResourceBundleId());
        pavement = (Pavement) session.load(Pavement.class,event.getPavement().getResourceBundleId());
        condition = (ConditionRout) session.load(ConditionRout.class,event.getCondition().getResourceBundleId());

        pedestrianCount = event.getPedestrianCount();
        pedestrian1 = event.getPedestrian1();
        pedestrianComment1 = event.getPedestrianComment1();
        pedestrian2 = event.getPedestrian2();
        pedestrianComment2 = event.getPedestrianComment2();
        pedestrian3 = event.getPedestrian3();
        pedestrianComment3 = event.getPedestrianComment3();
        
        cost = event.getCost();
        carcost = event.getCarCost();
        severity = event.getSeverity();
        
        superivisedCollision = (event.getSupervisedCollision() == null) ? null : (CollisionClasification)session.load(CollisionClasification.class,event.getSupervisedCollision().getResourceBundleId());
        superivisedAccident = (event.getSupervisedAccident() == null) ? null : (AccidentClasification)session.load(AccidentClasification.class,event.getSupervisedAccident().getResourceBundleId());
        superivisedIncident = (event.getSupervisedIncident() == null) ? null : (IncidentClasification)session.load(IncidentClasification.class,event.getSupervisedIncident().getResourceBundleId());
    	 if (eventDamages != null)
         	eventDamages.clear();
         else
         	eventDamages = new HashSet();	
         eventDamages.addAll(event.getEventDamages());
        
        workingHours = event.getWorkingHours();
        charged = event.getCharged();
        whoDrives = event.getWhoDrives();
        damageCharge = event.getDamageCharge();
        realCost = event.isRealCost();
        
        alcoholBreath = event.getAlcoholBreath();
        stoppedVehicle = event.getStoppedVehicle();
       		
        extraCost1 = event.getExtraCost1();
        extraCost2 = event.getExtraCost2();
        extraCost3 = event.getExtraCost3();
        extraCost4 = event.getExtraCost4();
        extraCost5 = event.getExtraCost5();
        extraCost6 = event.getExtraCost6();
        extraCost7 = event.getExtraCost7();
        extraCost8 = event.getExtraCost8();
       		
        ensuranceDate = event.getEnsuranceDate();
        companyAmount = event.getCompanyAmount();
        driverAmount = event.getDriverAmount();
        thirdAmount = event.getThirdAmount();
        
        folioNumber = event.getFolioNumber();
        sapNumber = event.getSapNumber();
        doubleCabin = event.isDoubleCabin();
        
        distractor = event.getDistractor();
        economicNumber = event.getEconomicNumber();
        controlMultiple = event.getControlMultiple();
	}

	public boolean isFactor1() {
		return factor1;
	}

	public void setFactor1(boolean factor1) {
		this.factor1 = factor1;
	}

	public boolean isFactor2() {
		return factor2;
	}

	public void setFactor2(boolean factor2) {
		this.factor2 = factor2;
	}

	public boolean isFactor3() {
		return factor3;
	}

	public void setFactor3(boolean factor3) {
		this.factor3 = factor3;
	}

	public boolean isFactor4() {
		return factor4;
	}

	public void setFactor4(boolean factor4) {
		this.factor4 = factor4;
	}

	public boolean isFactor5() {
		return factor5;
	}

	public void setFactor5(boolean factor5) {
		this.factor5 = factor5;
	}

	public boolean isFactor6() {
		return factor6;
	}

	public void setFactor6(boolean factor6) {
		this.factor6 = factor6;
	}

	public boolean isFactor7() {
		return factor7;
	}

	public void setFactor7(boolean factor7) {
		this.factor7 = factor7;
	}

	public boolean isFactor8() {
		return factor8;
	}

	public void setFactor8(boolean factor8) {
		this.factor8 = factor8;
	}

	public boolean isFactor9() {
		return factor9;
	}

	public void setFactor9(boolean factor9) {
		this.factor9 = factor9;
	}

	public boolean isFactor10() {
		return factor10;
	}

	public void setFactor10(boolean factor10) {
		this.factor10 = factor10;
	}

	public boolean isFactor11() {
		return factor11;
	}

	public void setFactor11(boolean factor11) {
		this.factor11 = factor11;
	}

	public boolean isFactor12() {
		return factor12;
	}

	public void setFactor12(boolean factor12) {
		this.factor12 = factor12;
	}

	public boolean isFactor13() {
		return factor13;
	}

	public void setFactor13(boolean factor13) {
		this.factor13 = factor13;
	}

	public boolean isFactor14() {
		return factor14;
	}

	public void setFactor14(boolean factor14) {
		this.factor14 = factor14;
	}

	public boolean isFactor15() {
		return factor15;
	}

	public void setFactor15(boolean factor15) {
		this.factor15 = factor15;
	}

	public boolean isFactor16() {
		return factor16;
	}

	public void setFactor16(boolean factor16) {
		this.factor16 = factor16;
	}

	public boolean isFactor17() {
		return factor17;
	}

	public void setFactor17(boolean factor17) {
		this.factor17 = factor17;
	}

	public boolean isFactor18() {
		return factor18;
	}

	public void setFactor18(boolean factor18) {
		this.factor18 = factor18;
	}

	public boolean isFactor19() {
		return factor19;
	}

	public void setFactor19(boolean factor19) {
		this.factor19 = factor19;
	}

	public boolean isFactor20() {
		return factor20;
	}

	public void setFactor20(boolean factor20) {
		this.factor20 = factor20;
	}

	public boolean isFactor21() {
		return factor21;
	}

	public void setFactor21(boolean factor21) {
		this.factor21 = factor21;
	}

	public boolean isFactor22() {
		return factor22;
	}

	public void setFactor22(boolean factor22) {
		this.factor22 = factor22;
	}
    
    
    
    
}
