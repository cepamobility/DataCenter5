package communication.vehicle.tables;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.GeoItemData;
import communication.administration.corporation.tables.GeoLabelData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.corporation.tables.LabelItemData;

public class SuctaMTOPData implements Comparable , Serializable {

	private static String VEHICLE = "vehicle";
	private static String PLATAFORM = "plataform";
	
	private Integer id;
	private String plate;
	private Date suctaExpiry;
	private Date mtopExpiry;
    private Collection divOrgs;	
	protected GeoLabelData country;
    private Date exclusionDate;
    private String vehicleorplataform;
    
	
	public SuctaMTOPData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	


	/**
	 * @param plate
	 * @param suctaExpiry
	 * @param mtopExpiry
	 * @param divOrgs
	 * @param country
	 * @param exclusionDate
	 */
	public SuctaMTOPData(Integer id, String plate, Date suctaExpiry, Date mtopExpiry, 
			Collection divOrgs, GeoLabelData country, Date exclusionDate, String vehicleorplataform) {
		super();
		// TODO Auto-generated constructor stub
		this.plate = plate;
		this.id = id;
		this.suctaExpiry = suctaExpiry;
		this.mtopExpiry = mtopExpiry;
		this.divOrgs = divOrgs;
		this.country = country;
		this.exclusionDate = exclusionDate;
		this.vehicleorplataform = vehicleorplataform;
	}
	
	/**
	 * 
	 * @param vehicleData
	 */
	public SuctaMTOPData(VehicleData vehicleData){
		this.plate = vehicleData.getMatricula();
		this.id = vehicleData.getId();
		this.suctaExpiry = vehicleData.getSuctaExpiry();
		this.mtopExpiry = vehicleData.getMtopExpiry();
		this.divOrgs = vehicleData.getDivOrgs();
		this.country = vehicleData.getCountry();
		this.exclusionDate = vehicleData.getExclusionDate();	
		this.vehicleorplataform = SuctaMTOPData.VEHICLE;
	}
	
	
	
	/**
	 * 
	 * @param trailerData
	 */
	public SuctaMTOPData(TrailerData trailerData){
		this.plate = trailerData.getPlate();
		this.id = trailerData.getId();
		this.suctaExpiry = trailerData.getSuctaExpiry();
		this.mtopExpiry = trailerData.getMtopExpiry();
		this.divOrgs = trailerData.getDivOrgs();
		this.country = trailerData.getCountry();
		this.exclusionDate = trailerData.getExclusionDate();		
		this.vehicleorplataform = SuctaMTOPData.PLATAFORM;
	}

    public String getData(int index, int j) {
        Iterator it = getDivOrgsIterator();
        DivOrgData data = null;
        for(int i = 0; i < index; i++) {
            data = (DivOrgData)it.next();
        }
        LabelItemData label = data.getDivOrgLevels(); 
        for(int i = 1; i < j; i++) {
            label = ((ItemData)label.getItemIterator().next()).getSubItems();
        }
        return ((ItemData)label.getItemIterator().next()).getName();
    }
    
    public String getCountryName(){
		GeoLabelData label = null;
		for(int i = 1; i < 2; i++) {
            label = ((GeoItemData)country.getItemIterator().next()).getSubItems();
        }
       return ((GeoItemData)label.getItemIterator().next()).getName();
	}
    
    public Iterator getDivOrgsIterator() {
        if(divOrgs == null) {
            return null;
        }
        Collections.sort((List)divOrgs);
        return divOrgs.iterator();
    }


	public GeoLabelData getCountry() {
		return country;
	}


	public void setCountry(GeoLabelData country) {
		this.country = country;
	}


	public Collection getDivOrgs() {
		return divOrgs;
	}


	public void setDivOrgs(Collection divOrgs) {
		this.divOrgs = divOrgs;
	}


	public Date getExclusionDate() {
		return exclusionDate;
	}


	public void setExclusionDate(Date exclusionDate) {
		this.exclusionDate = exclusionDate;
	}


	public Date getMtopExpiry() {
		return mtopExpiry;
	}


	public void setMtopExpiry(Date mtopExpiry) {
		this.mtopExpiry = mtopExpiry;
	}


	public String getPlate() {
		return plate;
	}


	public void setPlate(String plate) {
		this.plate = plate;
	}


	public Date getSuctaExpiry() {
		return suctaExpiry;
	}


	public void setSuctaExpiry(Date suctaExpiry) {
		this.suctaExpiry = suctaExpiry;
	}


	public int compareTo(Object arg0) {
		return plate.compareTo(((SuctaMTOPData)arg0).plate);		
	}




	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public String getVehicleorplataform() {
		return vehicleorplataform;
	}




	public void setVehicleorplataform(String vehicleorplataform) {
		this.vehicleorplataform = vehicleorplataform;
	}
	
	
	

}
