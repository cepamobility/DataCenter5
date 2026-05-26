package communication.activity.tables;


import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import communication.administration.corporation.tables.CorporationData;
import communication.datatype.tables.ActivityTypeDataType;
import communication.driver.tables.DriverData;
import communication.util.DateValue;

public class CEPAActivityScheduleRegistryData implements Serializable,DateValue,Comparable {

	/*
	 * Because implements Serializable it needs a serialVersionUID
	 */
	
	private Integer activityRegistryId;
	
    private static final long serialVersionUID = 1L;
	//CEPAActivityScheduleData activity; 

	public static final String APRUVED = "generic.activity.note.apruved";
	public static final String APRUVEDWITHOBSERVATIONS = "generic.activity.note.apruvedwithobservations";
	public static final String NOTAPRUVED = "generic.activity.note.notapruved";

	CEPAActivityScheduleData activity;
		
	// representan el reultado(son solo los items del formulario que fueron seleccionados)
	//private Set itemSet;
	private Collection<ItemData> itemSet;
	
	public CEPAActivityScheduleRegistryData() {
		super();
		// TODO Auto-generated constructor stub
	}

	private Collection<ItemValueData> itemValueSet = null;
	
	private DriverData driver;	
	private int drivingExperience;
	private boolean phrd = false;
	private String obs;	
	private String note;
	private Integer clasification;
	private String instructor;	
	private int classNumber = 0;
	private String cepaCar;
	
	/*
	 * Los siguientes atributos son para diferenciar
	 * las actividades de CEPA y las actividades de la Corporacion
	 */
	protected boolean cepaCourse; // indica si es un curso de Cepa o no.
	protected String partner; // compañero
    protected String conductionType; // Tipo de Conducción Comentada
    protected String plate; // Matricula
    
	// Cuando corresponde poner comentarios por cada punto
	// ademas de los generales del checklist, se guardan aca:
	protected Collection<PointCommentData> pointCommentSet;
	
	// Agregado por las Tablets:
	protected Collection<PointObservationSelectedData> pointObservations;
	protected Collection<CEPAScheduleActivityRegistryObservationSelectedData> obsSelected;
	
    protected String trade;	
    protected Integer initialKm;
    protected Integer finalKm;
    protected Time initialHr;
    protected Time finalHr;					
    protected String vehicleType;									
    
    protected boolean circulationAreaHighWay;
    protected boolean circulationAreaRoad;
    protected boolean circulationAreaSubUrban;
    protected boolean circulationAreaUrban;
    
    	 
    protected String velocityBox;											
    protected String light;			       
    
    protected boolean novato;
    
    protected boolean hasResult;
    
    protected boolean teoricoAsistence;
    protected boolean practicoAsistence;    
   
    private int corporation;
    
    protected boolean fia;
    protected boolean file;
    
    /*
     * Constructor que extiende el contructor con parametros para agregar los
     * itemValueSet para los Commentary Drive de J&J
     */
    public CEPAActivityScheduleRegistryData(CEPAActivityScheduleData activity,DriverData driver,
			Collection<ItemData> itemSet,String note, Integer clasification, String obs, String instructor,
			boolean cepaCourse, String partner, String conductionType, String plate, Collection<PointCommentData> pointCommentSet,
			String trade, Integer initialKm,	Integer finalKm, Time initialHr,
			Time finalHr, String vehicleType, boolean circulationAreaHighWay,										
			boolean circulationAreaRoad, boolean circulationAreaSubUrban, boolean circulationAreaUrban,
			String velocityBox,	String light, boolean novato, boolean teoricoAsistence, 
			boolean practicoAsistence,boolean hasResult, boolean fia, boolean file, Collection<ItemValueData> itemValueSet,
			int drivingExperience, boolean phrd, int classNumber, 
			Collection<PointObservationSelectedData> pointObservations,
			Collection<CEPAScheduleActivityRegistryObservationSelectedData> obsSelected) 
	{
    	this(activity,driver, itemSet,note,clasification,obs,instructor,
    		cepaCourse, partner, conductionType, plate, pointCommentSet, trade, initialKm,
    		finalKm, initialHr, finalHr, vehicleType, circulationAreaHighWay, 
    		circulationAreaRoad, circulationAreaSubUrban, circulationAreaUrban,
    		velocityBox, light, novato, teoricoAsistence, practicoAsistence, 
    		hasResult,fia,file,drivingExperience,phrd, classNumber, pointObservations,obsSelected);
    	this.itemValueSet = itemValueSet;
	}
    
    /*
     * Constructor por parametros.
     */
	public CEPAActivityScheduleRegistryData(CEPAActivityScheduleData activity,DriverData driver,
			Collection<ItemData> itemSet,String note, Integer clasification,String obs, String instructor,
			boolean cepaCourse, String partner, String conductionType, String plate, Collection<PointCommentData> pointCommentSet,
			String trade, Integer initialKm,	Integer finalKm, Time initialHr,
			Time finalHr, String vehicleType, boolean circulationAreaHighWay,										
			boolean circulationAreaRoad,boolean circulationAreaSubUrban,boolean circulationAreaUrban,
			String velocityBox,	String light, boolean novato, boolean teoricoAsistence, 
			boolean practicoAsistence, boolean hasResult, boolean fia, boolean file, int drivingExperience, boolean phrd, 
			int classNumber, Collection<PointObservationSelectedData> pointObservations,
			Collection<CEPAScheduleActivityRegistryObservationSelectedData> obsSelected) 
	{

		//this.activityRegistryId = activity;
		this.activity = activity;
		this.driver = driver;
		this.itemSet = itemSet;
		this.drivingExperience = drivingExperience;
		this.classNumber = classNumber;
		this.phrd = phrd;
		// ItemValueSet
		/*
		 
		if (itemSet != null)
		{
			Iterator it = itemSet.iterator();
			this.itemValueSet = new HashSet();
			while (it.hasNext())
			{
				Item item = (Item)it.next();
				ItemValue itemValue = new ItemValue();
				itemValue.setItem(item);
				itemValue.setItemValue(new Integer(0));
				this.itemValueSet.add(itemValue);
			}
		}
		else */
			this.itemValueSet = null;

		this.pointCommentSet = pointCommentSet;
		this.pointObservations = pointObservations;
		this.obsSelected = obsSelected;
		
		this.note = note;
		this.clasification = clasification;
		this.obs = obs;
		this.instructor = instructor;
		this.cepaCourse = cepaCourse;
		this.partner = partner;
		this.conductionType = conductionType;
		this.plate = plate;				
		
		this.trade=trade;	
		this.initialKm=initialKm;
		this.finalKm=finalKm;
		this.initialHr=initialHr;
		this.finalHr=finalHr;					
		this.vehicleType=vehicleType;									
		
		this.circulationAreaHighWay=circulationAreaHighWay;
		this.circulationAreaRoad=circulationAreaRoad;
		this.circulationAreaSubUrban=circulationAreaSubUrban;
		this.circulationAreaUrban=circulationAreaUrban;
		
		this.velocityBox=velocityBox;											
		this.light=light;
		
		this.novato=novato;
		
		this.hasResult=hasResult;		
		
		this.teoricoAsistence=teoricoAsistence;
		this.practicoAsistence=practicoAsistence;		
			
		this.fia = fia;
		this.file = file;
	}	
	
	public CEPAActivityScheduleRegistryData(Integer id,CEPAActivityScheduleData activity,DriverData driver,
			Collection<ItemData> itemSet,String note, Integer clasification, String obs, String instructor,
			boolean cepaCourse, String partner, String conductionType, String plate,Collection<PointCommentData> pointCommentSet,
			String trade, Integer initialKm,	Integer finalKm, Time initialHr,
			Time finalHr, String vehicleType, boolean circulationAreaHighWay,
			boolean circulationAreaRoad, boolean circulationAreaSubUrban, boolean circulationAreaUrban,
			String velocityBox,	String light, boolean novato, boolean teoricoAsistence, 
			boolean practicoAsistence,boolean hasResult ,boolean fia, boolean file, int drivingExperience, boolean phrd, 
			int classNumber, Collection<PointObservationSelectedData> pointObservations,
			Collection<CEPAScheduleActivityRegistryObservationSelectedData> obsSelected) 
	
	{
		this.classNumber = classNumber;
		this.activityRegistryId = id;
		this.activity = activity;
		this.driver = driver;
		this.drivingExperience = drivingExperience;
		this.itemSet = itemSet;
		this.phrd = phrd;
		this.itemValueSet = null;
			
		this.pointCommentSet = pointCommentSet;
		this.pointObservations = pointObservations;
		this.obsSelected = obsSelected;
		
		this.note = note;
		this.clasification = clasification;
		this.obs = obs;
		this.instructor = instructor;
		this.cepaCourse = cepaCourse;
		this.partner = partner;
		this.conductionType = conductionType;
		this.plate = plate;
		
		this.trade=trade;	
		this.initialKm=initialKm;
		this.finalKm=finalKm;
		this.initialHr=initialHr;
		this.finalHr=finalHr;					
		this.vehicleType=vehicleType;									
		
		this.circulationAreaHighWay=circulationAreaHighWay;
		this.circulationAreaRoad=circulationAreaRoad;
		this.circulationAreaSubUrban=circulationAreaSubUrban;
		this.circulationAreaUrban=circulationAreaUrban;
		
		this.velocityBox=velocityBox;											
		this.light=light;	
		
		this.novato=novato;
		
		this.hasResult=hasResult;		
		
		this.teoricoAsistence=teoricoAsistence;
		this.practicoAsistence=practicoAsistence;		
		
		this.fia = fia;
		this.file = file;
	}		
		
	public CEPAActivityScheduleRegistryData(String idActivity, String idDriver, String observations) {
        driver = new DriverData(new Integer(idDriver));
        activity = new CEPAActivityScheduleData(new Integer(idActivity));
        obs = observations;
    }

	public CEPAActivityScheduleRegistryData(CEPAActivityScheduleData activity, DriverData driver) {
        this.driver = driver;
        this.activity = activity;
    }

    public CEPAActivityScheduleRegistryData(CEPAActivityScheduleData activity,DriverData driver, String note, Integer clasification, String obs,
			String instructor, Collection<PointCommentData> commentValues,
			Collection<ItemValueData> itemSet,
			Collection<PointObservationSelectedData> pointObservations,
			Collection<CEPAScheduleActivityRegistryObservationSelectedData> obsSelected)  {
		this.activity = activity;
		this.driver = driver;
    	this.itemValueSet = itemSet;
    	this.instructor = instructor;
		this.pointCommentSet = commentValues;
		this.pointObservations = pointObservations;
		this.obsSelected = obsSelected;
		this.note = note;
		this.clasification = clasification;
		this.obs = obs;
	}

	public String getCepaCar() {
		return cepaCar;
	}

	public void setCepaCar(String cepaCar) {
		this.cepaCar = cepaCar;
	}

	/**
	 * @return the obsSelected
	 */
	public Collection<CEPAScheduleActivityRegistryObservationSelectedData> getObsSelected() {
		return obsSelected;
	}

	/**
	 * @param obsSelected the obsSelected to set
	 */
	public void setObsSelected(
			Collection<CEPAScheduleActivityRegistryObservationSelectedData> obsSelected) {
		this.obsSelected = obsSelected;
	}

	public CEPAActivityScheduleRegistryData(Integer driverId,Date date,String note,Integer clasification,String definition) {
        activity = new CEPAActivityScheduleData();
        activity.setDate(date);
        activity.setDefinition(new CEPAActivityDefinitionData(definition));
        activity.setType(new ActivityTypeDataType(definition));
        driver = new DriverData(new Integer(driverId));
        this.note = note;
        this.clasification = clasification;
	}

	public CEPAActivityScheduleRegistryData(CEPAActivityScheduleData activity, 
			DriverData driver, 
			Collection itemValueSet,
			Collection<PointCommentData> commentValues,
			String obs) {
		this.activity = activity;
		this.driver = driver;
    	this.itemValueSet = itemValueSet;
		this.pointCommentSet = commentValues;
		this.obs = obs;
	}

	/**
	 * @return Returns the checkList.
	 */
	public Collection<ItemData> getItemSet() {
		return itemSet;
	}

	/**
	 * @param checkList The checkList to set.
	 */
	public void setItemSet(Collection<ItemData> itemSet) {
		this.itemSet = itemSet;			
	}

	/**
	 * @return Returns the pointCommmentSet.
	 */
	public Collection<PointCommentData> getPointCommentSet() {		
		return this.pointCommentSet;
	}
	
	
	/*
	 * Dado un punto devuelve el comentario asociado a ese punto.
	 */
	public String getPointComment(PointData point) {	
		if (this.pointCommentSet != null) {
			Iterator<PointCommentData> it = this.pointCommentSet.iterator();
			while (it.hasNext()) {
				PointCommentData pc = it.next();
				if (pc.getPoint().getId().equals(point.getId()))
					return pc.getComment();
			}
		}
		return null;
	}
	
	/*
	 * Dado un punto devuelve las observaciones asociados a ese punto.
	 */
	public LinkedList<PointObservationSelectedData> getPointObservations(PointData point) {	
		
		LinkedList<PointObservationSelectedData> list = new LinkedList<PointObservationSelectedData>();
		if (this.pointObservations != null) {
			Iterator<PointObservationSelectedData> it = this.pointObservations.iterator();
			while (it.hasNext()) {
				PointObservationSelectedData pc = it.next();
				if (pc.getPoint().getId().equals(point.getId())){
					list.add(pc);
				}					
			}
		}
		// Collections.sort(list);
		return list;
	}
	
	
	/* Obtiene la lista de Puntos Comentados ordenados
	 * por el orden del punto
	 */
	public ArrayList<PointCommentData> getOrderedPointCommentSet()	{
		if(this.pointCommentSet == null || this.pointCommentSet.size() == 0) {
			return null;		
        }
        ArrayList<PointCommentData> orderedPointComment = new ArrayList<PointCommentData>();
        Iterator<PointCommentData> it = this.pointCommentSet.iterator();
		while (it.hasNext()) {
			orderedPointComment.add(it.next());			
		}
				
		// ordeno la lista por orden de los puntos
		Collections.sort(orderedPointComment);
		return orderedPointComment;
	}
	/**
	 * @param  The pointCommentSet to set.
	 */	
	public void setPointCommentSet(Collection<PointCommentData> pointCommentSet) {
		this.pointCommentSet = pointCommentSet;
	}
	/**
	 * @return Returns the activity.
	 */
	public CEPAActivityScheduleData getActivity() {
		return activity;
	}

	/**
	 * @return Returns the instructor.
	 */
	public String getInstructor() {
		return instructor;
	}

	/**
	 * @param instructor The instructor to set.
	 */
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public boolean isCepaCourse() {
		return cepaCourse;
	}

	public void setCepaCourse(boolean cepaCourse) {
		this.cepaCourse = cepaCourse;
	}

	public String getConductionType() {
		return conductionType;
	}

	public void setConductionType(String conductionType) {
		this.conductionType = conductionType;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public DriverData getDriver() {
		return driver;
	}

	public void setDriver(DriverData driver) {
		this.driver = driver;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getClasification() {
		return clasification;
	}

	public void setClasification(Integer clasification) {
		this.clasification = clasification;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	/**
	 * @return Returns the activityRegistryId.
	 */
	public Integer getActivityRegistryId() {
		return activityRegistryId;
	}

	/**
	 * @param activityRegistryId The activityRegistryId to set.
	 */
	public void setActivityRegistryId(Integer activityRegistryId) {
		this.activityRegistryId = activityRegistryId;
	}

	/**
	 * @return Returns the plate.
	 */
	public String getPlate() {
		return plate;
	}

	/**
	 * @param plate The plate to set.
	 */
	public void setPlate(String plate) {
		this.plate = plate;
	}

	

	public Time getFinalHr() {
		return finalHr;
	}

	public void setFinalHr(Time finalHr) {
		this.finalHr = finalHr;
	}

	public Integer getFinalKm() {
		return finalKm;
	}

	public void setFinalKm(Integer finalKm) {
		this.finalKm = finalKm;
	}

	public Time getInitialHr() {
		return initialHr;
	}

	public void setInitialHr(Time initialHr) {
		this.initialHr = initialHr;
	}

	public Integer getInitialKm() {
		return initialKm;
	}

	public void setInitialKm(Integer initialKm) {
		this.initialKm = initialKm;
	}

	public String getLight() {
		return light;
	}

	public void setLight(String light) {
		this.light = light;
	}

	public String getTrade() {
		return trade;
	}

	public void setTrade(String trade) {
		this.trade = trade;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getVelocityBox() {
		return velocityBox;
	}

	public void setVelocityBox(String velocityBox) {
		this.velocityBox = velocityBox;
	}

	public void setActivity(CEPAActivityScheduleData activity) {
		this.activity = activity;
	}

	/**
	 * @return Returns the date of CEPAActivity .
	 */
	public Date getDate(){
		return this.getActivity().getDate();
	};
	
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

	public boolean getNovato() {
		return novato;
	}

	public void setNovato(boolean novato) {
		this.novato = novato;
	}

	public boolean getPracticoAsistence() {
		return practicoAsistence;
	}

	public void setPracticoAsistence(boolean practicoAsistence) {
		this.practicoAsistence = practicoAsistence;
	}

	public boolean getTeoricoAsistence() {
		return teoricoAsistence;
	}

	public void setTeoricoAsistence(boolean teoricoAsistence) {
		this.teoricoAsistence = teoricoAsistence;
	}

	public boolean getHasResult() {
		return hasResult;
	}

	public void setHasResult(boolean hasResult) {
		this.hasResult = hasResult;
	}

	/**
	 * @return Returns the itemValueSet.
	 */
	public Collection<ItemValueData> getItemValueSet() {
		return itemValueSet;
	}

	/**
	 * @param itemValueSet The itemValueSet to set.
	 */
	public void setItemValueSet(Collection<ItemValueData> itemValueSet) {
		this.itemValueSet = itemValueSet;
	}
	
	/* Devuelve el valor (1,2,3 o null) de un item.
	 * Esto se utiliza para ls Commentary Drive donde los items
	 * pueden tomar valores 1 2 o 3 (segun el radiobuttom 
	 * que se indique: SI, NO, Necesita Mejorar.
	 * Para los demás casos devuelve null; 
	 */
	public Integer getValueFromItem(ItemData item) {	
		Integer result = null;
		if (this.itemValueSet != null) {
			Iterator<ItemValueData> it = this.itemValueSet.iterator();
			while (it.hasNext()) {
				ItemValueData itemValue = it.next();
				if (itemValue.getItem().equals(item)) {
					result = itemValue.getItemValue();
					break;
				}
			}
		}
		return result;
		
	}

	/**
	 * @return Returns the drivingExperience.
	 */
	public int getDrivingExperience() {
		return drivingExperience;
	}

	/**
	 * @param drivingExperience The drivingExperience to set.
	 */
	public void setDrivingExperience(int drivingExperience) {
		this.drivingExperience = drivingExperience;
	}

	/**
	 * @return Returns the phrd.
	 */
	public boolean getPhrd() {
		return phrd;
	}

	/**
	 * @param phrd The phrd to set.
	 */
	public void setPhrd(boolean phrd) {
		this.phrd = phrd;
	}

	public int getClassNumber() {
		return classNumber;
	}

	public void setClassNumber(int classNumber) {
		this.classNumber = classNumber;
	}

	/**
	 * @return the circulationAreaHighWay
	 */
	public boolean isCirculationAreaHighWay() {
		return circulationAreaHighWay;
	}

	/**
	 * @param circulationAreaHighWay the circulationAreaHighWay to set
	 */
	public void setCirculationAreaHighWay(boolean circulationAreaHighWay) {
		this.circulationAreaHighWay = circulationAreaHighWay;
	}

	/**
	 * @return the circulationAreaRoad
	 */
	public boolean isCirculationAreaRoad() {
		return circulationAreaRoad;
	}

	/**
	 * @param circulationAreaRoad the circulationAreaRoad to set
	 */
	public void setCirculationAreaRoad(boolean circulationAreaRoad) {
		this.circulationAreaRoad = circulationAreaRoad;
	}

	/**
	 * @return the circulationAreaSubUrban
	 */
	public boolean isCirculationAreaSubUrban() {
		return circulationAreaSubUrban;
	}

	/**
	 * @param circulationAreaSubUrban the circulationAreaSubUrban to set
	 */
	public void setCirculationAreaSubUrban(boolean circulationAreaSubUrban) {
		this.circulationAreaSubUrban = circulationAreaSubUrban;
	}

	/**
	 * @return the circulationAreaUrban
	 */
	public boolean isCirculationAreaUrban() {
		return circulationAreaUrban;
	}

	/**
	 * @param circulationAreaUrban the circulationAreaUrban to set
	 */
	public void setCirculationAreaUrban(boolean circulationAreaUrban) {
		this.circulationAreaUrban = circulationAreaUrban;
	}

	/**
	 * @return the pointObservations
	 */
	public Collection<PointObservationSelectedData> getPointObservations() {
		return pointObservations;
	}

	/**
	 * @param pointObservations the pointObservations to set
	 */
	public void setPointObservations(
			Collection<PointObservationSelectedData> pointObservations) {
		this.pointObservations = pointObservations;
	}

	public int getCorporation() {
		return corporation;
	}

	public void setCorporation(int corporation) {
		this.corporation = corporation;
	}
	
	public String getCorporationName() {
		switch(corporation) {
			case CorporationData.FORESTALORIENTAL:
				return "UPM";
			case CorporationData.WEYERHAEUSER:
				return "WY";
			case CorporationData.MONTES_DE_PLATA:
				return "MDP";
		}
		return "---";
	}

	public boolean isApprouved() {
		if(note != null) {
			return note.equals("generic.activity.note.apruved")
					|| note.equals("generic.activity.note.apruvedwithobservations")
					|| note.equals("generic.activity.note.apruved60");
		}
		return false;
	}
}
