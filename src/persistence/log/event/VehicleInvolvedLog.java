/**
 * Created on 07-jun-2006
 * CEPA
 * DataCenter 5
 */
package persistence.log.event;

import java.util.Date;

import persistence.event.tables.VehicleInvolved;
import persistence.log.IdLogPK;


public class VehicleInvolvedLog {

    private IdLogPK pk;
    private EventLog event;

    private Integer number;

    private String typeVehicle;
    private String behaviourVehicle;

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
    private String contact;

    public VehicleInvolvedLog() {
    }
    
    public VehicleInvolvedLog(VehicleInvolved vehicle,EventLog event,String user,Date date) {
        pk = new IdLogPK(vehicle.getInvolvedid(),user,date);
        this.event = event;
        this.number = vehicle.getNumber();
        this.typeVehicle = vehicle.getTypeVehicle().getResourceBundleId();
        this.behaviourVehicle = vehicle.getBehaviourVehicle().getResourceBundleId();
        this.hitrun = vehicle.getHitrun();
        this.principalFactor = vehicle.getPrincipalFactor().getResourceBundleId();
        this.factortooclose = vehicle.isFactortooclose();
        this.factorhighspeed = vehicle.isFactorhighspeed();
        this.factorsigns = vehicle.isFactorsigns();
        this.factorturn = vehicle.isFactorturn();
        this.factorlight = vehicle.isFactorlight();
        this.factorstop = vehicle.isFactorstop();
        this.factorother = vehicle.isFactorother();
        this.factorchangelane = vehicle.isFactorchangelane();
        this.factoralcohol = vehicle.isFactoralcohol();
        this.factorovertaking = vehicle.isFactorovertaking();
        this.factorsleep = vehicle.isFactorsleep();
        this.factordistraction = vehicle.isFactordistraction();
        this.factorConcience = vehicle.isFactorConcience();
        this.contact = vehicle.getContact();
    }


    /**
     * @return Returns the factoralcohol.
     */
    public boolean isFactoralcohol() {
        return factoralcohol;
    }

    /**
     * @return Returns the factorchangelane.
     */
    public boolean isFactorchangelane() {
        return factorchangelane;
    }

    /**
     * @return Returns the factorhighspeed.
     */
    public boolean isFactorhighspeed() {
        return factorhighspeed;
    }

    /**
     * @return Returns the factorlight.
     */
    public boolean isFactorlight() {
        return factorlight;
    }

    /**
     * @return Returns the factorother.
     */
    public boolean isFactorother() {
        return factorother;
    }

    /**
     * @return Returns the factorovertaking.
     */
    public boolean isFactorovertaking() {
        return factorovertaking;
    }

    /**
     * @return Returns the factorsigns.
     */
    public boolean isFactorsigns() {
        return factorsigns;
    }

    /**
     * @return Returns the factorstop.
     */
    public boolean isFactorstop() {
        return factorstop;
    }

    /**
     * @return Returns the factortooclose.
     */
    public boolean isFactortooclose() {
        return factortooclose;
    }

    /**
     * @return Returns the factorturn.
     */
    public boolean isFactorturn() {
        return factorturn;
    }

    /**
     * @return Returns the involvedid.
     */
    public IdLogPK getPk() {
        return pk;
    }

    /**
     * @return Returns the number.
     */
    public Integer getNumber() {
        return number;
    }


    /**
     * @return Returns the vehiclebehaviour.
     */
    public String getBehaviourVehicle() {
        return behaviourVehicle;
    }

    /**
     * @return Returns the vehicletype.
     */
    public String getTypeVehicle() {
        return typeVehicle;
    }

    /**
     * @param factoralcohol The factoralcohol to set.
     */
    public void setFactoralcohol(boolean factoralcohol) {
        this.factoralcohol = factoralcohol;
    }

    /**
     * @param factorchangelane The factorchangelane to set.
     */
    public void setFactorchangelane(boolean factorchangelane) {
        this.factorchangelane = factorchangelane;
    }

    /**
     * @param factorhighspeed The factorhighspeed to set.
     */
    public void setFactorhighspeed(boolean factorhighspeed) {
        this.factorhighspeed = factorhighspeed;
    }

    /**
     * @param factorlight The factorlight to set.
     */
    public void setFactorlight(boolean factorlight) {
        this.factorlight = factorlight;
    }

    /**
     * @param factorother The factorother to set.
     */
    public void setFactorother(boolean factorother) {
        this.factorother = factorother;
    }

    /**
     * @param factorovertaking The factorovertaking to set.
     */
    public void setFactorovertaking(boolean factorovertaking) {
        this.factorovertaking = factorovertaking;
    }

    /**
     * @param factorsigns The factorsigns to set.
     */
    public void setFactorsigns(boolean factorsigns) {
        this.factorsigns = factorsigns;
    }

    /**
     * @param factorstop The factorstop to set.
     */
    public void setFactorstop(boolean factorstop) {
        this.factorstop = factorstop;
    }

    /**
     * @param factortooclose The factortooclose to set.
     */
    public void setFactortooclose(boolean factortooclose) {
        this.factortooclose = factortooclose;
    }

    /**
     * @param factorturn The factorturn to set.
     */
    public void setFactorturn(boolean factorturn) {
        this.factorturn = factorturn;
    }

    /**
     * @param involvedid The involvedid to set.
     */
    public void setPk(IdLogPK pk) {
        this.pk = pk;
    }

    /**
     * @param number The number to set.
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * @param vehiclebehaviour The vehiclebehaviour to set.
     */
    public void setBehaviourVehicle(String behaviourVehicle) {
        this.behaviourVehicle = behaviourVehicle;
    }

    /**
     * @param vehicletype The vehicletype to set.
     */
    public void setTypeVehicle(String typeVehicle) {
        this.typeVehicle = typeVehicle;
    }

    /**
     * @return Returns the contact.
     */
    public String getContact() {
        return contact;
    }

    /**
     * @return Returns the factordistraction.
     */
    public boolean isFactordistraction() {
        return factordistraction;
    }
    
    /**
     * @return Returns the factorConcience.
     */
    public boolean isFactorConcience() {
        return factorConcience;
    }

    /**
     * @return Returns the factorsleep.
     */
    public boolean isFactorsleep() {
        return factorsleep;
    }

    /**
     * @return Returns the hitrun.
     */
    public String getHitrun() {
        return hitrun;
    }

    /**
     * @param contact The contact to set.
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * @param factordistraction The factordistract to set.
     */
    public void setFactordistraction(boolean factordistraction) {
        this.factordistraction = factordistraction;
    }
    
    /**
     * @param factorConcience The factorConcience to set.
     */
    public void setFactorConcience(boolean factorConcience) {
        this.factorConcience = factorConcience;
    }

    /**
     * @param factorsleep The factorsleep to set.
     */
    public void setFactorsleep(boolean factorsleep) {
        this.factorsleep = factorsleep;
    }

    /**
     * @param hitrun The hitrun to set.
     */
    public void setHitrun(String hitrun) {
        this.hitrun = hitrun;
    }

    /**
     * @return Returns the principalFactor.
     */
    public String getPrincipalFactor() {
        return principalFactor;
    }

    /**
     * @param principalFactor The principalFactor to set.
     */
    public void setPrincipalFactor(String principalFactor) {
        this.principalFactor = principalFactor;
    }

    public EventLog getEvent() {
        return event;
    }

    public void setEvent(EventLog event) {
        this.event = event;
    }

    
}
