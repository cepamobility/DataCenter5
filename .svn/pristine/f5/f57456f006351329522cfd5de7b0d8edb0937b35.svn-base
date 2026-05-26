package persistence.vehicle.tables;

import java.io.Serializable;

import org.hibernate.classic.Session;

import communication.vehicle.tables.UnconformityData;

public class Unconformity  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private UnconformityPK pk;

    private String origin;
    private String department;
    private Integer charge;
    private String observations;
    private Integer contractor;
    
	public Unconformity() {
	}

	public Unconformity(UnconformityPK pk) {
		this.pk = pk;
	}

	public Unconformity(Integer vehicleId, UnconformityData data, Session session) {
		Vehicle vehicle=(Vehicle)session.load(Vehicle.class, vehicleId);
		pk = new UnconformityPK(data.getDate(),vehicle);
		origin = data.getOrigin();
		department = data.getDepartment();
		charge = data.getCharge();
		observations = data.getObservations();
		if(vehicle.getDivOrg3() != null) {
			contractor = vehicle.getDivOrg3().getId();
		}
	}

	public UnconformityPK getPk() {
		return pk;
	}

	public void setPk(UnconformityPK pk) {
		this.pk = pk;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Integer getCharge() {
		return charge;
	}

	public void setCharge(Integer charge) {
		this.charge = charge;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public UnconformityData getData() {
        return new UnconformityData(pk.getDate(),origin,department,charge,observations);
	}

	public Integer getContractor() {
		return contractor;
	}

	public void setContractor(Integer contractor) {
		this.contractor = contractor;
	}
        
}
