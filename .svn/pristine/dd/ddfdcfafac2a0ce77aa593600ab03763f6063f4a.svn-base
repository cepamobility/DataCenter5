/**
 * Created on 07-jun-2006
 * CEPA
 * DataCenter 5
 */
package persistence.event.tables;

import org.hibernate.Session;

import persistence.datatype.tables.AccidentFactors;
import persistence.datatype.tables.TypeVehicle;
import persistence.datatype.tables.VehicleBehaviour;

import communication.datatype.tables.AccidentFactorsDataType;
import communication.datatype.tables.TypeVehicleDataType;
import communication.datatype.tables.VehicleBehaviourDataType;
import communication.event.tables.VehicleInvolvedDataType;

public class VehicleInvolved {

    private Integer involvedid;

    private Integer number;

    private TypeVehicle typeVehicle;
    private VehicleBehaviour behaviourVehicle;
    private String behaviourVehicleText;

    private String hitrun;

    private AccidentFactors principalFactor;
    private String principalFactorText;
    
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
    private boolean factorOtherParking;
    private boolean factorOtherCrossing;
    private boolean factorOtherStanding;

    private String contact;

    public VehicleInvolved() {
        super();
    }

    public VehicleInvolved(VehicleInvolvedDataType vehicleInvolvedDataType, Session session) {
        involvedid = vehicleInvolvedDataType.getInvolvedid();

        number = vehicleInvolvedDataType.getNumber();

        typeVehicle = (TypeVehicle)session.load(TypeVehicle.class,vehicleInvolvedDataType.getTypeVehicle().getResourceBundleId());
        behaviourVehicle = (VehicleBehaviour)session.load(VehicleBehaviour.class,vehicleInvolvedDataType.getBehaviourVehicle().getResourceBundleId());
        behaviourVehicleText=vehicleInvolvedDataType.getBehaviourVehicleText();
        
        hitrun = vehicleInvolvedDataType.getHitrun();

        principalFactor = (AccidentFactors)session.load(AccidentFactors.class,vehicleInvolvedDataType.getPrincipalFactor().getResourceBundleId());
        principalFactorText=vehicleInvolvedDataType.getPrincipalFactorText();
        
        factortooclose = vehicleInvolvedDataType.isFactortooclose();
        factorhighspeed = vehicleInvolvedDataType.isFactorhighspeed();
        factorsigns = vehicleInvolvedDataType.isFactorsigns();
        factorturn = vehicleInvolvedDataType.isFactorturn();
        factorlight = vehicleInvolvedDataType.isFactorlight();
        factorstop = vehicleInvolvedDataType.isFactorstop();
        factorother = vehicleInvolvedDataType.isFactorother();
        factorchangelane = vehicleInvolvedDataType.isFactorchangelane();
        factoralcohol = vehicleInvolvedDataType.isFactoralcohol();
        factorovertaking = vehicleInvolvedDataType.isFactorovertaking();
        factorsleep = vehicleInvolvedDataType.isFactorsleep();
        factordistraction = vehicleInvolvedDataType.isFactordistract();
        factorConcience = vehicleInvolvedDataType.isFactorConcience();
        factorOtherParking = vehicleInvolvedDataType.isFactorOtherParking();
        factorOtherCrossing = vehicleInvolvedDataType.isFactorOtherCrossing();
        factorOtherStanding = vehicleInvolvedDataType.isFactorOtherStanding();
        
        contact = vehicleInvolvedDataType.getContact();

    }

    
    
    public String getBehaviourVehicleText() {
		return behaviourVehicleText;
	}

	public void setBehaviourVehicleText(String behaviourVehicleText) {
		this.behaviourVehicleText = behaviourVehicleText;
	}

	public String getPrincipalFactorText() {
		return principalFactorText;
	}

	public void setPrincipalFactorText(String principalFactorText) {
		this.principalFactorText = principalFactorText;
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
    public Integer getInvolvedid() {
        return involvedid;
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
    public VehicleBehaviour getBehaviourVehicle() {
        return behaviourVehicle;
    }

    /**
     * @return Returns the vehicletype.
     */
    public TypeVehicle getTypeVehicle() {
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
    public void setInvolvedid(Integer involvedid) {
        this.involvedid = involvedid;
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
    public void setBehaviourVehicle(VehicleBehaviour behaviourVehicle) {
        this.behaviourVehicle = behaviourVehicle;
    }

    /**
     * @param vehicletype The vehicletype to set.
     */
    public void setTypeVehicle(TypeVehicle typeVehicle) {
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
    public AccidentFactors getPrincipalFactor() {
        return principalFactor;
    }

    /**
     * @param principalFactor The principalFactor to set.
     */
    public void setPrincipalFactor(AccidentFactors principalFactor) {
        this.principalFactor = principalFactor;
    }

    
    public boolean isFactorOtherCrossing() {
        return factorOtherCrossing;
    }

    public boolean isFactorOtherParking() {
        return factorOtherParking;
    }

    public boolean isFactorOtherStanding() {
        return factorOtherStanding;
    }

    public void setFactorOtherCrossing(boolean factorOtherCrossing) {
        this.factorOtherCrossing = factorOtherCrossing;
    }

    public void setFactorOtherParking(boolean factorOtherParking) {
        this.factorOtherParking = factorOtherParking;
    }

    public void setFactorOtherStanding(boolean factorOtherStanding) {
        this.factorOtherStanding = factorOtherStanding;
    }

    public VehicleInvolvedDataType getVehicleData() {
        VehicleInvolvedDataType vehicle = new VehicleInvolvedDataType(); 
        vehicle.setInvolvedid(involvedid);

        vehicle.setNumber(number);

        vehicle.setTypeVehicle((TypeVehicleDataType) typeVehicle.getData());
        vehicle.setBehaviourVehicle((VehicleBehaviourDataType) behaviourVehicle.getData());

        vehicle.setHitrun(hitrun);

        vehicle.setPrincipalFactor((AccidentFactorsDataType) principalFactor.getData());
        
        vehicle.setFactortooclose(factortooclose);
        vehicle.setFactorhighspeed(factorhighspeed);
        vehicle.setFactorsigns(factorsigns);
        vehicle.setFactorturn(factorturn);
        vehicle.setFactorlight(factorlight);
        vehicle.setFactorstop(factorstop);
        vehicle.setFactorother(factorother);
        vehicle.setFactorchangelane(factorchangelane);
        vehicle.setFactoralcohol(factoralcohol);
        vehicle.setFactorovertaking(factorovertaking);
        vehicle.setFactorsleep(factorsleep);
        vehicle.setFactordistract(factordistraction);
        vehicle.setFactorConcience(factorConcience);
        vehicle.setFactorOtherParking(factorOtherParking);
        vehicle.setFactorOtherCrossing(factorOtherCrossing);
        vehicle.setFactorOtherStanding(factorOtherStanding);
        vehicle.setPrincipalFactorText(principalFactorText);
        vehicle.setBehaviourVehicleText(behaviourVehicleText);
        
        vehicle.setContact(contact);
        
        return vehicle;
    }

    
}
