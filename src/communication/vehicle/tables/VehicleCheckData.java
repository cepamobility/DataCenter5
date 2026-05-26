package communication.vehicle.tables;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import communication.activity.tables.ItemData;
import communication.activity.tables.ItemValueStrData;
import communication.activity.tables.PointCommentData;
import communication.administration.corporation.tables.CorporationData;
import communication.driver.tables.DriverData;
import communication.event.tables.ReparationData;
import communication.util.DateValue;
import communication.vehicle.report.CheckListImageData;


public class VehicleCheckData implements Serializable,DateValue,Comparable {

	/*
	 * Because implements Serializable it needs a serialVersionUID
	 */
	
	private Integer vehicleCheckId;
	
    private static final long serialVersionUID = 1L;

    // representan el reultado(son solo los items del formulario que fueron seleccionados)
	//private Set itemSet;
	private Collection itemValueSet = null;
	
	private String name;
	
	private Date date;

    protected Integer vehicleKm;
    
    protected String obs;
    
    protected String serie;
    
    protected VehicleData vehicle;
    
    private String licenseNumber;
    
    private Date dateLicenseExpiry;

    private Integer result;
    protected Collection<PointCommentData> pointCommentSet;
    protected Collection<CheckListImageData> pointImageSet;
    
    // Para Nalco:
    private String inspectedby;
    private String distrito;
    private String grupo;

	private Integer vehicleType;
	private Integer chargeType;
	private Integer checkPlace;
    
	public VehicleCheckData(Object[] dataValues) {
        date = (Date)dataValues[0];
        name = (String)dataValues[1];
        licenseNumber = (String)dataValues[2];
        dateLicenseExpiry = (Date)dataValues[3];
        obs = (String)dataValues[4];
        serie = (String)dataValues[5];
        vehicleKm = (Integer)dataValues[6];
        result = (Integer)dataValues[7];
        inspectedby = (String)dataValues[9];
        distrito = (String)dataValues[10];
        grupo = (String)dataValues[11];
        vehicleType = (Integer)dataValues[12];
        chargeType = (Integer)dataValues[13];
        checkPlace = (Integer)dataValues[14];
        itemValueSet = new LinkedList();
        pointCommentSet = new HashSet<PointCommentData>();
        pointImageSet = new HashSet<CheckListImageData>();
	}

    public String getObs() {
		return obs;
	}



	public void setObs(String obs) {
		this.obs = obs;
	}



	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public VehicleCheckData(){
		
	}
	
	/**
	 * 
	 * @param id
	 * @param vehicle
	 * @param date
	 * @param name
	 * @param vehicleKm
	 * @param obs
	 * @param serie
	 * @param itemValueSet
	 * @param licenseNumber
	 * @param dateLicenseExpiry
	 * @param result
	 */
	public VehicleCheckData(Integer id,VehicleData vehicle,Date date,String name,Integer vehicleKm,String obs, String serie, Collection itemValueSet,String licenseNumber,Date dateLicenseExpiry, Integer result) 
	{
		this.vehicleCheckId = id;
		this.vehicle = vehicle;
		this.date = date;
		this.itemValueSet = itemValueSet;
		this.name = name;
		this.vehicleKm = vehicleKm;				
		this.obs = obs;
		this.serie = serie;
		this.licenseNumber = licenseNumber;
		this.dateLicenseExpiry = dateLicenseExpiry;
		this.result = result;
	}	
	
	/**
	 * Constructor para el CehckList de Nalco
	 * @param id
	 * @param vehicle
	 * @param date
	 * @param name
	 * @param vehicleKm
	 * @param obs
	 * @param serie
	 * @param itemValueSet
	 * @param licenseNumber
	 * @param dateLicenseExpiry
	 * @param result
	 */
	public VehicleCheckData(Integer id,VehicleData vehicle,Date date,String name,
			String obs, Collection itemValueSet, Integer result, String inspectedby,
			String distrito, String grupo) 
	{
		this.vehicleCheckId = id;
		this.vehicle = vehicle;
		this.date = date;
		this.itemValueSet = itemValueSet;
		this.name = name;
		this.obs = obs;
		this.result = result;
		this.inspectedby = inspectedby;
		this.distrito = distrito;
		this.grupo = grupo;
	}	
	
		
	
	/**
	 * @return Returns the instructor.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param instructor The instructor to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Returns the activityRegistryId.
	 */
	public Integer getVehicleCheckId() {
		return vehicleCheckId;
	}

	/**
	 * @param activityRegistryId The activityRegistryId to set.
	 */
	public void setVehicleCheckId(Integer vehicleCheckId) {
		this.vehicleCheckId = vehicleCheckId;
	}

	public Integer getVehicleKm() {
		return vehicleKm;
	}

	public void setVehicleKm(Integer vehicleKm) {
		this.vehicleKm = vehicleKm;
	}

	public int compareTo(Object anotherobject){
		DateValue another = (DateValue)anotherobject; 
    	Date element1=another.getDate();
    	Date element2=this.getDate();
    	if (element1 instanceof Timestamp)
    		element1= new Date(element1.getTime());
    	if (element2 instanceof Timestamp)
    		element2= new Date(element2.getTime());
        return element1.compareTo(element2);    
      }

	/**
	 * Redefine el metodo equals. Dos checklist son iguales
	 * si y solo si tienen el mismo vehiculo (id del vehiculo)
	 * y tienen la misma fecha.
	 * @param o - Objeto
	 * @return boolean - True si son iguales, false en caso contrario.
	 * @author gbenaderet
	 */
	public boolean equals(Object o){
		VehicleCheckData vcd;
		if (o instanceof VehicleCheckData){
			vcd = (VehicleCheckData)o;
			// Asumo que es la misma checklist si esta asociada
			// al mismo vehiculo para la misma fecha.
			// Si las fechas son diferentes son checklist diferentes
			if (	
				(vcd.vehicleCheckId != null && this.vehicleCheckId != null
				&& vcd.vehicleCheckId.equals(this.vehicleCheckId) ) 
				||
				(vcd.getVehicle().getId().equals(this.getVehicle().getId())
				&& vcd.getDate().equals(this.getDate()))
				){
				return true;
			}else{
				return false;
			}
		}else
			return false;
	}

	/**
	 * @return Returns the itemValueSet.
	 */
	public Collection getItemValueSet() {
		return itemValueSet;
	}

	/**
	 * @param itemValueSet The itemValueSet to set.
	 */
	public void setItemValueSet(Collection itemValueSet) {
		this.itemValueSet = itemValueSet;
	}
	
	/* Devuelve el valor (1,2,3 o null) de un item.
	 * Esto se utiliza para ls Commentary Drive donde los items
	 * pueden tomar valores 1 2 o 3 (segun el radiobuttom 
	 * que se indique: SI, NO, Necesita Mejorar.
	 * Para los demÃ¡s casos devuelve null; 
	 */
	public String getValueFromItem(ItemData item)
	{
		if (this.itemValueSet != null)
		{
			Iterator it = this.itemValueSet.iterator();
			while (it.hasNext())
			{
				ItemValueStrData itemValue = (ItemValueStrData)it.next();
				if (itemValue.getItem().equals(item))
					return itemValue.getItemValue();
			}
		}
		return null;
		
	}

    public String getValueFromItem(Integer item) {
        if (itemValueSet != null) {
            Iterator it = itemValueSet.iterator();
            while (it.hasNext()) {
                ItemValueStrData itemValue = (ItemValueStrData)it.next();
                if(itemValue.getItem().getId().equals(item)) {
                    return itemValue.getItemValue();
                } 
            }
        }
        return null;
    }
    
    public String getValueTagFromItem(Integer item) {
        if (itemValueSet != null) {
            Iterator it = itemValueSet.iterator();
            while (it.hasNext()) {
                ItemValueStrData itemValue = (ItemValueStrData)it.next();
                if(itemValue.getItem().getId().equals(item)) {
                    return itemValue.getItemTag();
                } 
            }
        }
        return null;
    }

    public String getValueFromCommentPoint(Integer point) {
        if (pointCommentSet != null) {
            Iterator it = pointCommentSet.iterator();
            while (it.hasNext()) {
                PointCommentData pointValue = (PointCommentData)it.next();
                if(pointValue.getPoint().getId().equals(point)) {
                    return pointValue.getComment();
                }
            }
        }
        return null;
    }
    
    
    
    
    public Collection<CheckListImageData> getPointImageSet() {
		return pointImageSet;
	}



	public void setPointImageSet(Collection<CheckListImageData> pointImageSet) {
		this.pointImageSet = pointImageSet;
	}



	public Date getDate() {
		return date;
	}

	public VehicleData getVehicle() {
		return vehicle;
	}



	public void setVehicle(VehicleData vehicle) {
		this.vehicle = vehicle;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public Date getDateLicenseExpiry() {
		return dateLicenseExpiry;
	}



	public void setDateLicenseExpiry(Date dateLicenseExpiry) {
		this.dateLicenseExpiry = dateLicenseExpiry;
	}



	public String getLicenseNumber() {
		return licenseNumber;
	}



	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}



	public Integer getResult() {
		return result;
	}



	public void setResult(Integer result) {
		this.result = result;
	}

	/**
	 * @return the inspectedby
	 */
	public String getInspectedby() {
		return inspectedby;
	}



	/**
	 * @param inspectedby the inspectedby to set
	 */
	public void setInspectedby(String inspectedby) {
		this.inspectedby = inspectedby;
	}



	/**
	 * @return the distrito
	 */
	public String getDistrito() {
		return distrito;
	}



	/**
	 * @param distrito the distrito to set
	 */
	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}



	/**
	 * @return the grupo
	 */
	public String getGrupo() {
		return grupo;
	}



	/**
	 * @param grupo the grupo to set
	 */
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	


	public Collection<PointCommentData> getPointCommentSet() {
		return pointCommentSet;
	}



	public void setPointCommentSet(Collection<PointCommentData> pointCommentSet) {
		this.pointCommentSet = pointCommentSet;
	}

	public void setVehicleType(Integer vehicleType){
		this.vehicleType = vehicleType;
	}
	
	public void setChargeType(Integer chargeType){
		this.chargeType = chargeType;
	}
	
	public void setCheckPlace(Integer checkPlace){
		this.checkPlace = checkPlace;
	}

	public Integer getVehicleType() {
		return vehicleType;
	}

	public Integer getChargeType() {
		return chargeType;
	}

	public Integer getCheckPlace() {
		return checkPlace;
	}

	public ReparationData getFraylogReparation(String firstName, String lastName, int corporation) {
		ReparationData reparation = new ReparationData();
		
		HashMap<Integer, String> commentValues = new HashMap<Integer, String>(); 
        Iterator it = pointCommentSet.iterator();
        while (it.hasNext()) {
            PointCommentData pointValue = (PointCommentData)it.next();
            commentValues.put(pointValue.getPoint().getOrderedItems().iterator().next().getId(),pointValue.getComment());
        }

        it = itemValueSet.iterator();
        while (it.hasNext()) {
            ItemValueStrData itemValue = (ItemValueStrData)it.next();
            commentValues.get(itemValue.getItem().getId());
            if(itemValue.getItemValue() != null && itemValue.getItemValue().equals("2")) {
            	switch(itemValue.getItem().getId().intValue()) {
            	case 11584:
            	     reparation.addStrNewItem(11,1);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11588:
            	     reparation.addStrNewItem(11,8);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11503:
            	     reparation.addStrNewItem(11,9);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11587:
            	     reparation.addStrNewItem(11,9);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11555:	
            	     reparation.addStrNewItem(12,9);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11556:
            	     reparation.addStrNewItem(12,1);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11557:
            	     reparation.addStrNewItem(12,1);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 21828:
            	     reparation.addStrNewItem(12,1);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11558:
            	     reparation.addStrNewItem(12,1);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11559:
            	     reparation.addStrNewItem(12,1);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 21827:
            	     reparation.addStrNewItem(12,1);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11498:
            	     reparation.addStrNewItem(12,2);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11564:
            	     reparation.addStrNewItem(12,3);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11496:
            	     reparation.addStrNewItem(12,4);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11495:
            	     reparation.addStrNewItem(12,5);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 21829:
            	     reparation.addStrNewItem(12,5);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 21830:
            	     reparation.addStrNewItem(12,6);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11554:
            	     reparation.addStrNewItem(12,6);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11560:
            	     reparation.addStrNewItem(12,7);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11500:
            	     reparation.addStrNewItem(13,7);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11543:
            	     reparation.addStrNewItem(14,1);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11538:
            	     reparation.addStrNewItem(14,1);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11539:
            	     reparation.addStrNewItem(14,2);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11540:
            	     reparation.addStrNewItem(14,2);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11541:
            	     reparation.addStrNewItem(14,2);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11547:
            	     reparation.addStrNewItem(14,2);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11542:
            	     reparation.addStrNewItem(14,2);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11546:
            	     reparation.addStrNewItem(14,3);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 16339:
            	     reparation.addStrNewItem(15,3);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11561:
            	     reparation.addStrNewItem(15,4);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11567:
            	     reparation.addStrNewItem(15,5);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11568:
            	     reparation.addStrNewItem(15,5);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11569:
            	     reparation.addStrNewItem(15,5);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11570:
            	     reparation.addStrNewItem(15,5);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11571:
            	     reparation.addStrNewItem(15,5);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11577:
            	     reparation.addStrNewItem(16,1);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11572:
            	     reparation.addStrNewItem(16,1);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11574:
            	     reparation.addStrNewItem(16,2);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11575:
            	     reparation.addStrNewItem(16,2);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11576:
            	     reparation.addStrNewItem(16,2);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11573:
            	     reparation.addStrNewItem(16,3);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11499:
            	     reparation.addStrNewItem(17,1);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11562:
            	     reparation.addStrNewItem(17,1);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11563:
            	     reparation.addStrNewItem(17,1);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 21831:
            	     reparation.addStrNewItem(17,1);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11586:
            	     reparation.addStrNewItem(17,1);
            	     addComment(reparation, 1, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11534:
            	     reparation.addStrNewItem(21,1);
            	     addComment(reparation, 2, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11535:
            	     reparation.addStrNewItem(21,1);
            	     addComment(reparation, 2, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11536:
            	     reparation.addStrNewItem(21,1);
            	     addComment(reparation, 2, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11537:
            	     reparation.addStrNewItem(21,2);
            	     addComment(reparation, 2, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11545:
            	     reparation.addStrNewItem(21,3);
            	     addComment(reparation, 2, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11523:
            	     reparation.addStrNewItem(24,1);
            	     addComment(reparation, 2, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11497:
            	     reparation.addStrNewItem(24,3);
            	     addComment(reparation, 2, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11516:
            	     reparation.addStrNewItem(24,3);
            	     addComment(reparation, 2, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11517:
            	     reparation.addStrNewItem(24,3);
            	     addComment(reparation, 2, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11518:
            	     reparation.addStrNewItem(24,3);
            	     addComment(reparation, 2, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11519:
            	     reparation.addStrNewItem(24,3);
            	     addComment(reparation, 2, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11522:
            	     reparation.addStrNewItem(24,3);
            	     addComment(reparation, 2, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11524:
            	     reparation.addStrNewItem(24,3);
            	     addComment(reparation, 2, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11525:
            	     reparation.addStrNewItem(24,3);
            	     addComment(reparation, 2, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11526:
            	     reparation.addStrNewItem(24,3);
            	     addComment(reparation, 2, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11527:
            	     reparation.addStrNewItem(24,3);
            	     addComment(reparation, 2, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11528:
            	     reparation.addStrNewItem(24,3);
            	     addComment(reparation, 2, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11529:
            	     reparation.addStrNewItem(24,3);
            	     addComment(reparation, 2, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11530:
            	     reparation.addStrNewItem(24,3);
            	     addComment(reparation, 2, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11531:
            	     reparation.addStrNewItem(24,3);
            	     addComment(reparation, 2, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11532:
            	     reparation.addStrNewItem(24,3);
            	     addComment(reparation, 2, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11533:
            	     reparation.addStrNewItem(24,3);
            	     addComment(reparation, 2, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11548:
            	     reparation.addStrNewItem(24,3);
            	     addComment(reparation, 2, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11549:
            	     reparation.addStrNewItem(24,3);
            	     addComment(reparation, 2, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11550:
            	     reparation.addStrNewItem(24,3);
            	     addComment(reparation, 2, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11551:
            	     reparation.addStrNewItem(24,3);
            	     addComment(reparation, 2, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11552:
            	     reparation.addStrNewItem(24,3);
            	     addComment(reparation, 2, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11553:
            	     reparation.addStrNewItem(24,3);
            	     addComment(reparation, 2, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11520:
            	     reparation.addStrNewItem(24,6);
            	     addComment(reparation, 2, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11521:
            	     reparation.addStrNewItem(24,6);
            	     addComment(reparation, 2, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11504:
            	     reparation.addStrNewItem(31,1);
            	     addComment(reparation, 3, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11505:
            	     reparation.addStrNewItem(31,2);
            	     addComment(reparation, 3, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11506:
            	     reparation.addStrNewItem(31,3);
            	     addComment(reparation, 3, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11507:
            	     reparation.addStrNewItem(31,4);
            	     addComment(reparation, 3, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11508:
            	     reparation.addStrNewItem(31,5);
            	     addComment(reparation, 3, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11509:
            	     reparation.addStrNewItem(31,6);
            	     addComment(reparation, 3, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11510:
            	     reparation.addStrNewItem(31,6);
            	     addComment(reparation, 3, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11511:
            	     reparation.addStrNewItem(31,6);
            	     addComment(reparation, 3, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11512:
            	     reparation.addStrNewItem(31,6);
            	     addComment(reparation, 3, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11513:
            	     reparation.addStrNewItem(31,6);
            	     addComment(reparation, 3, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11514:
            	     reparation.addStrNewItem(31,6);
            	     addComment(reparation, 3, commentValues, itemValue.getItem().getId());
            	     break;
            	case 21823:
            	     reparation.addStrNewItem(31,6);
            	     addComment(reparation, 3, commentValues, itemValue.getItem().getId());
            	     break;
            	case 21824:
            	     reparation.addStrNewItem(31,6);
            	     addComment(reparation, 3, commentValues, itemValue.getItem().getId());
            	     break;
            	case 21825:
            	     reparation.addStrNewItem(31,6);
            	     addComment(reparation, 3, commentValues, itemValue.getItem().getId());
            	     break;
            	case 21826:
            	     reparation.addStrNewItem(31,6);
            	     addComment(reparation, 3, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11515:
            	     reparation.addStrNewItem(31,7);
            	     addComment(reparation, 3, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11592:
            	     reparation.addStrNewItem(31,8);
            	     addComment(reparation, 3, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11593:
            	     reparation.addStrNewItem(31,9);
            	     addComment(reparation, 3, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11580:
            	     reparation.addStrNewItem(41,1);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11579:
            	     reparation.addStrNewItem(41,2);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11482:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11483:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11484:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11485:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11486:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11487:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11488:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11489:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11490:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11491:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 21822:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11492:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11493:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11501:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11502:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11544:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11585:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11589:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11590:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11494: case 11595:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11581:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11582:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11583:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 11578:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 17000:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 17001:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 17002:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 17003:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 17004:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 17005:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 17006:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 17007:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 17008:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 17009:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 17010:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 17011:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 17012:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 17013:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 17014:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 17015:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 17016:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 17017:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 17018:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 17019:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
            	case 17020:
            	     reparation.addStrNewItem(41,3);
            	     addComment(reparation, 4, commentValues, itemValue.getItem().getId());
            	     break;
	            } 
            }
        }
        if(reparation.getItems().size() == 0) {
    		return null;        	
        }else {
        	reparation.setVehicle(vehicle);
        	reparation.setDriver(getDriver(firstName, lastName, corporation));
        	reparation.setDate(date);
        	reparation.setStatus(ReparationData.PENDING);
        	reparation.setKms(0);
        	reparation.setObservation("Por chequeo vehicular");
        	return reparation;
        }
	}

	private void addComment(ReparationData reparation, int index, HashMap<Integer, String> commentValues, Integer item) {
		if(commentValues.containsKey(item)) {
			String newComment = commentValues.get(item);
			String comment = reparation.getComment(index);
			if(comment == null || comment.length() == 0) {
				comment = newComment;
			}else {
				comment += "\n"+newComment;
			}
			reparation.addComment(index, comment);
		}
	}

	private DriverData getDriver(String firstname, String lastname, int corporation) {
		DriverData driver = new DriverData();
		if(corporation == CorporationData.FRAYLOG) {
			driver.setId(350143);
			if(firstname.contains("martin") && lastname.contains("muñoz")) {
				driver.setId(288815);
			}
			if(firstname.contains("sebastian") && lastname.contains("gonzalez")) {
				driver.setId(288577);
			}
			if(firstname.contains("federico") && lastname.contains("terra")) {
				driver.setId(288813);
			}
			if(firstname.contains("danilo") && lastname.contains("linale")) {
				driver.setId(350137);
			}
			if(firstname.contains("fernando") && lastname.contains("zaffaroni")) {
				driver.setId(350141);
			}
			if(firstname.contains("fabian") && lastname.contains("zecca")) {
				driver.setId(350140);
			}
			if(firstname.contains("alfredo") && lastname.contains("dallo")) {
				driver.setId(350142);
			}
		}else {
			driver.setId(386187);
		}
		driver.setFirstName(firstname.toUpperCase());
		driver.setLastName(lastname.toUpperCase());
		driver.setCorporationId("----");
		return driver;
	}
}
