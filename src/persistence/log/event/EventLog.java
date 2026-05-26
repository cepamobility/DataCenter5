/**
 * Created on 24-may-2006
 * CEPA
 * DataCenter 5
 */
package persistence.log.event;

import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import persistence.event.tables.Event;
import persistence.event.tables.Fine;
import persistence.event.tables.VehicleInvolved;

import communication.util.ObservationData;

public class EventLog {

    private EventLogPK pk;
    private Integer action;
    private String discriminator;
    private Integer driver;
    private Integer corporation;
    private Integer country;
    private Integer geoLevel1;
    private String state;
    private Integer divOrg1;
    private Integer divOrg2;
    private Integer divOrg3;
    private String city;
    private Integer vehicle;

    private String plate;

    private String trade;

    private String model;

    private String year;

    private String companycar;

    private Date eventDate;

    private Time hour;

    private String working;

    private Integer status;

    private String evitability;

    private String recomendation;

    private String type;

    private Integer points;

    private String qualify;

    private String observations;

    private String zone;

    private String policeReport;
    
    private boolean damage;

    private String story;

    private String collision;

    private String accident;

    private String incident;

    private String handle;

    private String hitrun;

    private String principalFactor;

    private boolean factortooclose;

    private boolean factorhighspeed;

    private boolean factorsigns;

    private boolean factorturn;

    private boolean factorlight;

    private boolean factorstop;

    private boolean factorother;

    private boolean factorchangelane;

    private boolean factoralcohol;

    private boolean factorovertaking;

    private boolean factorsleep;

    private boolean factordistraction;
    
    private boolean factorConcience;

    private String collisionAngle;

    private String driverstate;

    private Integer dayslost;

    private Integer otherpartydayslost;

    private Integer fatalitiesothercount;

    private Integer injuredothercount;

    private Integer fatalitiesfunccount;

    private Integer injuredfunccount;
    
    private Integer safefunccount;
    
    private Integer safeothercount;

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

    private String selfbelt;

    private String alcohol;

    private String otherVehicles;

    private String cellular;

    private Integer maxSpeed;

    private Integer vehicleSpeed;

    private Integer collisionSpeed;

    private Integer reportedSpeed;

    private String speedUnit;

    private String climate;

    private String lighting;

    private String way;

    private String direction;

    private String control;

    private String pavement;

    private String condition;

    private Integer cost;

    private Integer pedestrianCount;

    private String pedestrian1;

    private String pedestrianComment1;

    private String pedestrian2;

    private String pedestrianComment2;

    private String pedestrian3;

    private String pedestrianComment3;

    private String comment;
    
    private Set vehiclesInvolved;
    
    private boolean nonrecordable;

    public EventLog() {
    }

    public EventLog(String loginName, Date date, int action, Event event, String comment) {
        pk = new EventLogPK(event.getId(),loginName,date);
        this.action = new Integer(action);
        this.discriminator = "event";
        this.driver = event.getDriver().getId();
        this.corporation = event.getCorpCountry().getPk().getCorporation().getId();
        this.country = event.getCorpCountry().getPk().getCountry().getId();
        this.geoLevel1 = event.getGeoLevel1().getId();
        this.state = event.getState();
        this.divOrg1 = (event.getDivOrg1() == null) ? null : event.getDivOrg1().getId();
        this.divOrg2 = (event.getDivOrg2() == null) ? null : event.getDivOrg2().getId();
        this.divOrg3 = (event.getDivOrg3() == null) ? null : event.getDivOrg3().getId();
        this.city = event.getCity();
        this.vehicle = (event.getVehicle() == null) ? null : event.getVehicle().getId();
        this.plate = event.getPlate();
        this.trade = event.getTrade();
        this.model = event.getModel();
        this.year = event.getYear();
        this.companycar = String.valueOf(event.isCompanycar());
        this.nonrecordable = event.isNonrecordable();
        this.eventDate = event.getDate();
        this.hour = event.getHour();
        this.working = event.getWorking();
        this.status = event.getStatus();
        this.evitability = event.getEvitability();
        this.recomendation = event.getRecomendation();
        this.zone = (event.getZone() == null) ? null : event.getZone().getResourceBundleId();
        this.policeReport = event.getPoliceReport();
        this.damage = event.isDamage();
        this.story = event.getStory();
        this.collision = (event.getCollision() == null) ? null : event.getCollision().getResourceBundleId();
        this.accident = (event.getAccident() == null) ? null : event.getAccident().getResourceBundleId();
        this.incident = (event.getIncident() == null) ? null : event.getIncident().getResourceBundleId();
        this.handle = (event.getHandle() == null) ? null : event.getHandle().getResourceBundleId();
        this.hitrun = event.getHitrun();
        this.principalFactor = (event.getPrincipalFactor() == null) ? null : event.getPrincipalFactor().getResourceBundleId();
        this.collisionAngle = event.getCollisionAngle();
        this.driverstate = event.getDriverstate();
        this.dayslost = event.getDayslost();
        this.otherpartydayslost = event.getOtherpartydayslost();
        this.fatalitiesothercount = event.getFatalitiesothercount();
        this.injuredothercount = event.getInjuredothercount();
        this.fatalitiesfunccount = event.getFatalitiesfunccount();
        this.injuredfunccount = event.getInjuredfunccount();
        this.safefunccount = event.getSafefunccount();
        this.safeothercount = event.getSafeothercount();
        this.damage1 = event.isDamage1();
        this.damage2 = event.isDamage2();
        this.damage3 = event.isDamage3();
        this.damage4 = event.isDamage4();
        this.damage5 = event.isDamage5();
        this.damage6 = event.isDamage6();
        this.damage7 = event.isDamage7();
        this.damage8 = event.isDamage8();
        this.damage9 = event.isDamage9();
        this.damage10 = event.isDamage10();
        this.selfbelt = event.getSelfbelt();
        this.alcohol = event.getAlcohol();
        this.otherVehicles = event.getOtherVehicles();
        this.cellular = event.getCellular();
        this.maxSpeed = event.getMaxSpeed();
        this.vehicleSpeed = event.getVehicleSpeed();
        this.collisionSpeed = event.getCollisionSpeed();
        this.reportedSpeed = event.getReportedSpeed();
        this.speedUnit = event.getSpeedUnit();
        this.climate = (event.getClimate() == null) ? null : event.getClimate().getResourceBundleId();
        this.lighting = (event.getLighting() == null) ? null : event.getLighting().getResourceBundleId();
        this.way = (event.getWay() == null) ? null : event.getWay().getResourceBundleId();
        this.direction = (event.getDirection() == null) ? null : event.getDirection().getResourceBundleId();
        this.control = (event.getControl() == null) ? null : event.getControl().getResourceBundleId();
        this.pavement = (event.getPavement() == null) ? null : event.getPavement().getResourceBundleId();
        this.condition = (event.getCondition() == null) ? null : event.getCondition().getResourceBundleId();
        this.cost = event.getCost();
        this.pedestrianCount = event.getPedestrianCount();
        this.pedestrian1 = event.getPedestrian1();
        this.pedestrianComment1 = event.getPedestrianComment1();
        this.pedestrian2 = event.getPedestrian2();
        this.pedestrianComment2 = event.getPedestrianComment2();
        this.pedestrian3 = event.getPedestrian3();
        this.pedestrianComment3 = event.getPedestrianComment3();
        vehiclesInvolved = new HashSet();
        Iterator vehicles = event.getVehiclesInvolved().iterator();
        while(vehicles.hasNext()) {
            vehiclesInvolved.add(new VehicleInvolvedLog((VehicleInvolved) vehicles.next(),this,loginName,date));
        }
        this.comment = comment;
    }

    public EventLog(String loginName, Date date, int action, Fine fine,String comment) {
        pk = new EventLogPK(fine.getId(),loginName,date);
        this.action = new Integer(action);
        this.discriminator = "fine";
        this.driver = fine.getDriver().getId();
        this.corporation = fine.getCorpCountry().getPk().getCorporation().getId();
        this.country = fine.getCorpCountry().getPk().getCountry().getId();
        this.geoLevel1 = (fine.getGeoLevel1() == null) ? null : fine.getGeoLevel1().getId();
        this.state = fine.getState();
        this.divOrg1 = (fine.getDivOrg1() == null) ? null : fine.getDivOrg1().getId();
        this.divOrg2 = (fine.getDivOrg2() == null) ? null : fine.getDivOrg2().getId();
        this.divOrg3 = (fine.getDivOrg3() == null) ? null : fine.getDivOrg3().getId();
        this.city = fine.getCity();
        this.vehicle = (fine.getVehicle() == null) ? null : fine.getVehicle().getId();
        this.plate = fine.getPlate();
        this.trade = fine.getTrade();
        this.model = fine.getModel();
        this.year = fine.getYear();
        this.nonrecordable = fine.isNonrecordable();
        this.eventDate = fine.getDate();
        this.hour = fine.getHour();
        this.cost = fine.getCost();
        this.evitability = fine.getEvitability();
        this.recomendation = fine.getRecomendation();
        this.observations = fine.getObservations();
        this.points = fine.getPoints();
        this.qualify = fine.getQualify().getResourceBundleId();
        this.status = fine.getStatus();
        this.type = fine.getType().getResourceBundleId();
        this.working = fine.getWorking();
        this.comment = comment;
    }

    public String getAccident() {
        return accident;
    }

    public Integer getAction() {
        return action;
    }

    public String getAlcohol() {
        return alcohol;
    }

    public String getCellular() {
        return cellular;
    }

    public String getCity() {
        return city;
    }

    public String getClimate() {
        return climate;
    }

    public String getCollision() {
        return collision;
    }

    public String getCollisionAngle() {
        return collisionAngle;
    }

    public Integer getCollisionSpeed() {
        return collisionSpeed;
    }

    public String getCompanycar() {
        return companycar;
    }
    public boolean isNonrecordable() {
        return nonrecordable;
    }

    public String getCondition() {
        return condition;
    }

    public String getControl() {
        return control;
    }

    public Integer getCorporation() {
        return corporation;
    }

    public Integer getCost() {
        return cost;
    }

    public Integer getCountry() {
        return country;
    }

    public boolean isDamage() {
        return damage;
    }

    public boolean isDamage1() {
        return damage1;
    }

    public boolean isDamage10() {
        return damage10;
    }

    public boolean isDamage2() {
        return damage2;
    }

    public boolean isDamage3() {
        return damage3;
    }

    public boolean isDamage4() {
        return damage4;
    }

    public boolean isDamage5() {
        return damage5;
    }

    public boolean isDamage6() {
        return damage6;
    }

    public boolean isDamage7() {
        return damage7;
    }

    public boolean isDamage8() {
        return damage8;
    }

    public boolean isDamage9() {
        return damage9;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public Integer getDayslost() {
        return dayslost;
    }

    public String getDirection() {
        return direction;
    }

    public String getDiscriminator() {
        return discriminator;
    }

    public Integer getDivOrg1() {
        return divOrg1;
    }

    public Integer getDivOrg2() {
        return divOrg2;
    }

    public Integer getDivOrg3() {
        return divOrg3;
    }

    public Integer getDriver() {
        return driver;
    }

    public String getDriverstate() {
        return driverstate;
    }

    public String getEvitability() {
        return evitability;
    }

    public boolean isFactoralcohol() {
        return factoralcohol;
    }

    public boolean isFactorchangelane() {
        return factorchangelane;
    }

    public boolean isFactordistraction() {
        return factordistraction;
    }
    
    public boolean isFactorConcience() {
        return factorConcience;
    }

    public boolean isFactorhighspeed() {
        return factorhighspeed;
    }

    public boolean isFactorlight() {
        return factorlight;
    }

    public boolean isFactorother() {
        return factorother;
    }

    public boolean isFactorovertaking() {
        return factorovertaking;
    }

    public boolean isFactorsigns() {
        return factorsigns;
    }

    public boolean isFactorsleep() {
        return factorsleep;
    }

    public boolean isFactorstop() {
        return factorstop;
    }

    public boolean isFactortooclose() {
        return factortooclose;
    }

    public boolean isFactorturn() {
        return factorturn;
    }

    public Integer getFatalitiesfunccount() {
        return fatalitiesfunccount;
    }

    public Integer getFatalitiesothercount() {
        return fatalitiesothercount;
    }

    public Integer getGeoLevel1() {
        return geoLevel1;
    }

    public String getHandle() {
        return handle;
    }

    public String getHitrun() {
        return hitrun;
    }

    public Time getHour() {
        return hour;
    }

    public String getIncident() {
        return incident;
    }

    public Integer getInjuredfunccount() {
        return injuredfunccount;
    }

    public Integer getInjuredothercount() {
        return injuredothercount;
    }
    
    public Integer getSafefunccount() {
        return safefunccount;
    }

    public Integer getSafeothercount() {
        return safeothercount;
    }

    public String getLighting() {
        return lighting;
    }

    public Integer getMaxSpeed() {
        return maxSpeed;
    }

    public String getModel() {
        return model;
    }

    public String getObservations() {
        return observations;
    }

    public Integer getOtherpartydayslost() {
        return otherpartydayslost;
    }

    public String getOtherVehicles() {
        return otherVehicles;
    }

    public String getPavement() {
        return pavement;
    }

    public String getPedestrian1() {
        return pedestrian1;
    }

    public String getPedestrian2() {
        return pedestrian2;
    }

    public String getPedestrian3() {
        return pedestrian3;
    }

    public String getPedestrianComment1() {
        return pedestrianComment1;
    }

    public String getPedestrianComment2() {
        return pedestrianComment2;
    }

    public String getPedestrianComment3() {
        return pedestrianComment3;
    }

    public Integer getPedestrianCount() {
        return pedestrianCount;
    }

    public EventLogPK getPk() {
        return pk;
    }

    public String getPlate() {
        return plate;
    }

    public Integer getPoints() {
        return points;
    }

    public String getPrincipalFactor() {
        return principalFactor;
    }

    public String getQualify() {
        return qualify;
    }

    public String getRecomendation() {
        return recomendation;
    }

    public Integer getReportedSpeed() {
        return reportedSpeed;
    }

    public String getSelfbelt() {
        return selfbelt;
    }

    public String getSpeedUnit() {
        return speedUnit;
    }

    public String getState() {
        return state;
    }

    public Integer getStatus() {
        return status;
    }

    public String getStory() {
        return story;
    }

    public String getTrade() {
        return trade;
    }

    public String getType() {
        return type;
    }

    public Integer getVehicle() {
        return vehicle;
    }

    public Set getVehiclesInvolved() {
        return vehiclesInvolved;
    }

    public Integer getVehicleSpeed() {
        return vehicleSpeed;
    }

    public String getWay() {
        return way;
    }

    public String getWorking() {
        return working;
    }

    public String getYear() {
        return year;
    }

    public String getZone() {
        return zone;
    }
    
    public String getPoliceReport() {
        return policeReport;
    }
    

    public void setAccident(String accident) {
        this.accident = accident;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public void setAlcohol(String alcohol) {
        this.alcohol = alcohol;
    }

    public void setCellular(String cellular) {
        this.cellular = cellular;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public void setCollision(String collision) {
        this.collision = collision;
    }

    public void setCollisionAngle(String collisionAngle) {
        this.collisionAngle = collisionAngle;
    }

    public void setCollisionSpeed(Integer collisionSpeed) {
        this.collisionSpeed = collisionSpeed;
    }

    public void setCompanycar(String companycar) {
        this.companycar = companycar;
    }
    
    public void setNonrecordable(boolean nonrecordable) {
        this.nonrecordable = nonrecordable;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public void setCorporation(Integer corporation) {
        this.corporation = corporation;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }

    public void setDamage(boolean damage) {
        this.damage = damage;
    }

    public void setDamage1(boolean damage1) {
        this.damage1 = damage1;
    }

    public void setDamage10(boolean damage10) {
        this.damage10 = damage10;
    }

    public void setDamage2(boolean damage2) {
        this.damage2 = damage2;
    }

    public void setDamage3(boolean damage3) {
        this.damage3 = damage3;
    }

    public void setDamage4(boolean damage4) {
        this.damage4 = damage4;
    }

    public void setDamage5(boolean damage5) {
        this.damage5 = damage5;
    }

    public void setDamage6(boolean damage6) {
        this.damage6 = damage6;
    }

    public void setDamage7(boolean damage7) {
        this.damage7 = damage7;
    }

    public void setDamage8(boolean damage8) {
        this.damage8 = damage8;
    }

    public void setDamage9(boolean damage9) {
        this.damage9 = damage9;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public void setDayslost(Integer dayslost) {
        this.dayslost = dayslost;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setDiscriminator(String discriminator) {
        this.discriminator = discriminator;
    }

    public void setDivOrg1(Integer divOrg1) {
        this.divOrg1 = divOrg1;
    }

    public void setDivOrg2(Integer divOrg2) {
        this.divOrg2 = divOrg2;
    }

    public void setDivOrg3(Integer divOrg3) {
        this.divOrg3 = divOrg3;
    }

    public void setDriver(Integer driver) {
        this.driver = driver;
    }

    public void setDriverstate(String driverstate) {
        this.driverstate = driverstate;
    }

    public void setEvitability(String evitability) {
        this.evitability = evitability;
    }

    public void setFactoralcohol(boolean factoralcohol) {
        this.factoralcohol = factoralcohol;
    }

    public void setFactorchangelane(boolean factorchangelane) {
        this.factorchangelane = factorchangelane;
    }

    public void setFactordistraction(boolean factordistraction) {
        this.factordistraction = factordistraction;
    }
    
    public void setFactorConcience(boolean factorConcience) {
        this.factorConcience = factorConcience;
    }

    public void setFactorhighspeed(boolean factorhighspeed) {
        this.factorhighspeed = factorhighspeed;
    }

    public void setFactorlight(boolean factorlight) {
        this.factorlight = factorlight;
    }

    public void setFactorother(boolean factorother) {
        this.factorother = factorother;
    }

    public void setFactorovertaking(boolean factorovertaking) {
        this.factorovertaking = factorovertaking;
    }

    public void setFactorsigns(boolean factorsigns) {
        this.factorsigns = factorsigns;
    }

    public void setFactorsleep(boolean factorsleep) {
        this.factorsleep = factorsleep;
    }

    public void setFactorstop(boolean factorstop) {
        this.factorstop = factorstop;
    }

    public void setFactortooclose(boolean factortooclose) {
        this.factortooclose = factortooclose;
    }

    public void setFactorturn(boolean factorturn) {
        this.factorturn = factorturn;
    }

    public void setFatalitiesfunccount(Integer fatalitiesfunccount) {
        this.fatalitiesfunccount = fatalitiesfunccount;
    }

    public void setFatalitiesothercount(Integer fatalitiesothercount) {
        this.fatalitiesothercount = fatalitiesothercount;
    }

    public void setGeoLevel1(Integer geoLevel1) {
        this.geoLevel1 = geoLevel1;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public void setHitrun(String hitrun) {
        this.hitrun = hitrun;
    }

    public void setHour(Time hour) {
        this.hour = hour;
    }

    public void setIncident(String incident) {
        this.incident = incident;
    }

    public void setInjuredfunccount(Integer injuredfunccount) {
        this.injuredfunccount = injuredfunccount;
    }

    public void setInjuredothercount(Integer injuredothercount) {
        this.injuredothercount = injuredothercount;
    }
    
    public void setSafefunccount(Integer safefunccount) {
        this.safefunccount = safefunccount;
    }

    public void setSafeothercount(Integer safeothercount) {
        this.safeothercount = safeothercount;
    }

    public void setLighting(String lighting) {
        this.lighting = lighting;
    }

    public void setMaxSpeed(Integer maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public void setOtherpartydayslost(Integer otherpartydayslost) {
        this.otherpartydayslost = otherpartydayslost;
    }

    public void setOtherVehicles(String otherVehicles) {
        this.otherVehicles = otherVehicles;
    }

    public void setPavement(String pavement) {
        this.pavement = pavement;
    }

    public void setPedestrian1(String pedestrian1) {
        this.pedestrian1 = pedestrian1;
    }

    public void setPedestrian2(String pedestrian2) {
        this.pedestrian2 = pedestrian2;
    }

    public void setPedestrian3(String pedestrian3) {
        this.pedestrian3 = pedestrian3;
    }

    public void setPedestrianComment1(String pedestrianComment1) {
        this.pedestrianComment1 = pedestrianComment1;
    }

    public void setPedestrianComment2(String pedestrianComment2) {
        this.pedestrianComment2 = pedestrianComment2;
    }

    public void setPedestrianComment3(String pedestrianComment3) {
        this.pedestrianComment3 = pedestrianComment3;
    }

    public void setPedestrianCount(Integer pedestrianCount) {
        this.pedestrianCount = pedestrianCount;
    }

    public void setPk(EventLogPK pk) {
        this.pk = pk;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public void setPrincipalFactor(String principalFactor) {
        this.principalFactor = principalFactor;
    }

    public void setQualify(String qualify) {
        this.qualify = qualify;
    }

    public void setRecomendation(String recomendation) {
        this.recomendation = recomendation;
    }

    public void setReportedSpeed(Integer reportedSpeed) {
        this.reportedSpeed = reportedSpeed;
    }

    public void setSelfbelt(String selfbelt) {
        this.selfbelt = selfbelt;
    }

    public void setSpeedUnit(String speedUnit) {
        this.speedUnit = speedUnit;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setVehicle(Integer vehicle) {
        this.vehicle = vehicle;
    }

    public void setVehiclesInvolved(Set vehiclesInvolved) {
        this.vehiclesInvolved = vehiclesInvolved;
    }

    public void setVehicleSpeed(Integer vehicleSpeed) {
        this.vehicleSpeed = vehicleSpeed;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public void setWorking(String working) {
        this.working = working;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }
    
    public void setPoliceReport(String policeReport) {
        this.policeReport = policeReport;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ObservationData getEventObservations() {
        return new ObservationData(pk.getEvent(),pk.getDate(),pk.getUserId(),action,comment);
    }

    
        
}
