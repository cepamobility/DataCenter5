/**
 * Created on 24-jul-2006
 * CEPA
 * DataCenter 5
 */
package persistence.fleet.tables;

import java.io.Serializable;

import org.hibernate.classic.Session;

import persistence.hibernate.HibernateUtil;


public class FleetDivisionDetailPK implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FleetDivision fleet;
    private Integer period;
    private Integer year;

    public FleetDivisionDetailPK() {
    }

    /**
     * @param fleet
     * @param period
     * @param year
     */
    public FleetDivisionDetailPK(FleetDivision fleet, Integer period, Integer year) {
        this.fleet = fleet;
        this.period = period;
        this.year = year;
    }

    public FleetDivisionDetailPK(Integer id, Integer period, Integer year) {
		Session session=HibernateUtil.currentSession();
        fleet = (FleetDivision)session.load(FleetDivision.class, id);
        this.period = period;
        this.year = year;
	}

	public FleetDivision getFleet() {
        return fleet;
    }

    
    public Integer getPeriod() {
        return period;
    }

    public Integer getYear() {
        return year;
    }

    public void setFleet(FleetDivision fleet) {
        this.fleet = fleet;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
