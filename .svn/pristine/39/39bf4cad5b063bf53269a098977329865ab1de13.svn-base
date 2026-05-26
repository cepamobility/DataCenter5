package persistence.activity.tables;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import persistence.driver.tables.Driver;
import persistence.hibernate.HibernateUtil;

import communication.activity.tables.CEPAActivityScheduleRegistryData;
import communication.activity.tables.CEPAScheduleActivityRegistryObservationSelectedData;
import communication.activity.tables.ItemData;
import communication.activity.tables.ItemValueData;
import communication.activity.tables.PointCommentData;
import communication.activity.tables.PointObservationSelectedData;

public class CEPAScheduleActivityRegistry implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Integer activityRegistryId;
	
	// esta faltando una referencia a la actividad programada
	// capaz que conviene tener un registry para cada actividad
	
	//protected CEPAScheduleActivity activity;
	
	// Para que funcione la funcion getParticipants hay que descomentar esto u la función setActivity que comenté
	//protected CEPAActivity activity;
	
	protected CEPAScheduleActivity activity;
	
	protected Driver driver;
	protected int drivingExperience = 0; // Años de experiencia de conduccion.
	protected boolean phrd = false;
	
	// representan el reultado(son solo los items del formulario que fueron seleccionados)
	protected Set<Item> itemSet;
	
	// Contiene el Set de itemSet y valores para cada item:
	// 1,2,3 segun corresponda o NULL
	protected Set<ItemValue> itemValueSet = null;
	

	// lista de parejas point-commentary
	//protected Set pointCommentarySet;

	protected String obs;
	
	protected String note;
	protected Integer clasification;
	
	protected String instructor;
	
	protected String cepaCar;
	
	/*
	 * Los siguientes atributos son para diferenciar
	 * las actividades de CEPA y las actividades de la Corporacion
	 */
	protected boolean cepaCourse = true; // indica si es un curso de Cepa o no.
	protected String partner; // compañero
    protected String conductionType; // Tipo de Conducción Comentada
    protected String plate; //Matricula del vehiculo en que se toma la prueba.

	// Cuando corresponde poner comentarios por cada punto
	// ademas de los generales del checklist, se guardan aca:
	protected Set<PointComment> pointCommentSet;
	
	/**
	 *  Observaciones elegidas para cada Punto de la Actividad para este regsitro.
	 *  Agregado por las Tablets.
	 */
    protected Set<PointObservationSelected> pointObservationSelectedSet;
    
    /**
     * Observaciones Generales del conductor en la actividad, no vinculadas
     * no ningun punto especifico. Agregado por las Tablets.
     */
    protected Set<CEPAScheduleActivityRegistryObservationSelected> observationsSelected;
    
    
    protected String trade;	
    protected Integer initialKm;
    protected Integer finalKm;
    protected Time initialHr;
    protected Time finalHr;					
    protected String vehicleType;									
    
    protected String circulationArea; //  25-03-2009: Esta borrarla despues de migrar los datos a las demas columnas
    
    protected boolean circulationAreaHighWay;
    protected boolean circulationAreaRoad;
    protected boolean circulationAreaSubUrban;
    protected boolean circulationAreaUrban;
    
    protected String velocityBox;											
    protected String light;	
    protected int classNumber = 0; // Numero de clase de la actividad (Se usa en la corporacion Escuela)
    
    protected boolean novato = false; // indica si es el conductor es novato (aplica solo para btw1).
    
    protected boolean hasResult= false;// indica si se ingresó resultado para el driver(si se ingresaron datos en el formulario).
    
    protected boolean teoricoAsistence = true;
    protected boolean practicoAsistence = true;    
	
    protected boolean fia = false;    
    protected boolean file = false;    

    public CEPAScheduleActivityRegistry() {

	}
	
	public Integer getActivityRegistryId() {
		return activityRegistryId;
	}

	public void setActivityRegistryId(Integer activityRegistryId) {
		this.activityRegistryId = activityRegistryId;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	/**
	 * @return Returns the checkList.
	 */
	public Set<Item> getItemSet() {
		return itemSet;
	}

	/**
	 * @param checkList The checkList to set.
	 */
	public void setItemSet(Set<Item> itemSet) {
		this.itemSet = itemSet;		
	}

	/**
	 * @return Returns the activity.
	 */
	//public Activity getActivity() {
	//public CEPAActivity getActivity() {
	public CEPAScheduleActivity getActivity() {
		return activity;
	}

	/**
	 * @param activity The activity to set.
	 */
	//public void setActivity(CEPAActivity activity) {
	public void setActivity(CEPAScheduleActivity activity) {
		this.activity = activity;
	} 
	
	
	public String getCepaCar() {
		return cepaCar;
	}

	public void setCepaCar(String cepaCar) {
		this.cepaCar = cepaCar;
	}

	// Devuelve una CEPAActivityData con los items sin cargar
	// tampoco carga los pointObservations ni obsSelected
	public CEPAActivityScheduleRegistryData getCEPAScheduleActivityData() { 
		
		
		/*
		Collection<PointObservationSelectedData> pointObservations = new LinkedList<PointObservationSelectedData>();
		if (this.pointObservationSelectedSet != null && this.pointObservationSelectedSet.size() > 0){
			Iterator<PointObservationSelected> it = this.pointObservationSelectedSet.iterator();
			while (it.hasNext()){
				PointObservationSelected p = it.next();
				PointObservationSelectedData pointObsData = p.getData();
				pointObservations.add(pointObsData);
			}
		}
		
		// Observations Selected
		*/
		
		CEPAActivityScheduleRegistryData c =  new CEPAActivityScheduleRegistryData(
				getActivityRegistryId(),
				activity.getCEPAScheduleActivityData(),
				driver.getDriverData(),null,note,clasification,obs,instructor,cepaCourse,partner,
				conductionType, plate, null,
				trade, initialKm,	finalKm, initialHr,	finalHr, vehicleType, 				
				circulationAreaHighWay,circulationAreaRoad,
				circulationAreaSubUrban,
				circulationAreaUrban,
				velocityBox,light,novato,teoricoAsistence,practicoAsistence,
				hasResult,fia, file,this.drivingExperience, this.phrd, this.classNumber,null,null);
		
		c.setCepaCar(cepaCar);
		
		return c;
	}	
	
	public CEPAScheduleActivityRegistry(CEPAActivityScheduleRegistryData data) throws HibernateException,Exception {
		
		this.note = data.getNote();
		this.clasification = data.getClasification(); 
		this.obs = data.getObs();		
		this.instructor = data.getInstructor();	
		this.partner = data.getPartner();
		this.conductionType = data.getConductionType();
		this.cepaCourse = data.isCepaCourse();	
		this.plate = data.getPlate();
		this.drivingExperience = data.getDrivingExperience();
		this.trade = data.getTrade();	
		this.initialKm = data.getInitialKm();
		this.finalKm = data.getFinalKm();
		this.initialHr = data.getInitialHr();
		this.finalHr = data.getFinalHr();					
		this.vehicleType = data.getVehicleType();								
		
		//this.circulationArea = data.getCirculationArea();
		
		this.circulationAreaHighWay = data.isCirculationAreaHighWay();
		this.circulationAreaRoad = data.isCirculationAreaRoad();
		this.circulationAreaSubUrban = data.isCirculationAreaSubUrban();
		this.circulationAreaUrban = data.isCirculationAreaUrban();
		
		this.velocityBox = data.getVelocityBox();											
		this.light = data.getLight();			
		
		this.novato = data.getNovato();
		this.phrd = data.getPhrd();
		this.hasResult = data.getHasResult();
		
		this.teoricoAsistence = data.getTeoricoAsistence();
		this.practicoAsistence = data.getPracticoAsistence();
		
		Session session = HibernateUtil.currentSession();
			
	    //driver = new Driver();
		driver = (Driver)session.load(Driver.class,data.getDriver().getId());
		
	    // activity = new CEPAScheduleActivity();
		activity = (CEPAScheduleActivity)session.load(CEPAScheduleActivity.class,data.getActivity().getActivityId());		
			
		itemSet = new HashSet<Item>();
		if (data.getItemSet() != null) {
			Iterator<ItemData> it = data.getItemSet().iterator();
	    	while(it.hasNext()){
	    		ItemData itemData = it.next();    		
	    		Item item = (Item)session.load(Item.class,itemData.getId());
	    		itemSet.add(item);	    	
	    	}
		}
		else
			itemSet = null;
		
		// itemValueSet
		itemValueSet = new HashSet<ItemValue>();
		if (data.getItemValueSet() != null) {
			Iterator<ItemValueData> it = data.getItemValueSet().iterator();
	    	while(it.hasNext()){
	    		ItemValueData itemValueData = it.next();    		
	    		Item item = (Item)session.load(Item.class,itemValueData.getItem().getId());
	    		Integer value = itemValueData.getItemValue();
	    		ItemValue itemValue = new ItemValue(item,value);
	    		itemValueSet.add(itemValue);	    	
	    	}
		}else
			itemValueSet = null;
			
		
		
		this.pointCommentSet = new HashSet<PointComment>();
		if (data.getPointCommentSet() != null) {
			Iterator<PointCommentData> it = data.getPointCommentSet().iterator();
	    	while(it.hasNext()){
	    		PointCommentData pointCommentData = it.next();    		
	    		Point point = (Point)session.load(Point.class, pointCommentData.getPoint().getId());
	    		PointComment pointComment = new PointComment(point, pointCommentData.getComment());
	    		this.pointCommentSet.add(pointComment);
	    	}
		}	
		
		this.classNumber = data.getClassNumber();
		
		this.pointObservationSelectedSet = new HashSet<PointObservationSelected>();
		if (data.getPointObservations() != null){
			Iterator<PointObservationSelectedData> it = data.getPointObservations().iterator();
			while (it.hasNext()){
				PointObservationSelectedData p = it.next();
				Point point = (Point)session.load(Point.class, p.getPoint().getId());
				PointObservation obs = (PointObservation) session.load(PointObservation.class, p.getObservation().getIdObservation());
				PointObservationSelected pp = new PointObservationSelected(point,obs);
				this.pointObservationSelectedSet.add(pp);
			}			
		}
		
		// Observaciones Seleccionadas
		this.observationsSelected = new HashSet<CEPAScheduleActivityRegistryObservationSelected>();
		if (data.getObsSelected() != null && data.getObsSelected().size() > 0){
			Iterator<CEPAScheduleActivityRegistryObservationSelectedData> it = 
				data.getObsSelected().iterator();
			while (it.hasNext()){
				CEPAScheduleActivityRegistryObservationSelectedData obsSel = it.next();
				if (obsSel != null){
					CEPAScheduleActivityRegistryObservation obs = 
							(CEPAScheduleActivityRegistryObservation)session.load(
							CEPAScheduleActivityRegistryObservation.class,obsSel.getObservation().getIdObservation());
					CEPAScheduleActivityRegistryObservationSelected sel = 
						new CEPAScheduleActivityRegistryObservationSelected(obs);
					this.observationsSelected.add(sel);
				}
			}
		}
		
		this.cepaCar=data.getCepaCar();
	    
	}

	public CEPAScheduleActivityRegistry(CEPAScheduleActivityRegistry data,Integer driverId, Integer activityId) {
		this.note = data.getNote();
		this.clasification = data.getClasification(); 
		this.obs = data.getObs();		
		this.instructor = data.getInstructor();	
		this.partner = data.getPartner();
		this.conductionType = data.getConductionType();
		this.cepaCourse = data.isCepaCourse();	
		this.plate = data.getPlate();
		this.drivingExperience = data.getDrivingExperience();
		this.trade = data.getTrade();	
		this.initialKm = data.getInitialKm();
		this.finalKm = data.getFinalKm();
		this.initialHr = data.getInitialHr();
		this.finalHr = data.getFinalHr();					
		this.vehicleType = data.getVehicleType();								
		
		//this.circulationArea = data.getCirculationArea();
		
		this.circulationAreaHighWay = data.isCirculationAreaHighWay();
		this.circulationAreaRoad = data.isCirculationAreaRoad();
		this.circulationAreaSubUrban = data.isCirculationAreaSubUrban();
		this.circulationAreaUrban = data.isCirculationAreaUrban();
		
		this.velocityBox = data.getVelocityBox();											
		this.light = data.getLight();			
		
		this.novato = data.getNovato();
		this.phrd = data.getPhrd();
		this.hasResult = data.getHasResult();
		
		this.teoricoAsistence = data.getTeoricoAsistence();
		this.practicoAsistence = data.getPracticoAsistence();
		
		Session session = HibernateUtil.currentSession();
			
	    //driver = new Driver();
		driver = (Driver)session.load(Driver.class,driverId);
		
	    // activity = new CEPAScheduleActivity();
		activity = (CEPAScheduleActivity)session.load(CEPAScheduleActivity.class,activityId);		
			
		itemSet = new HashSet<Item>();
		if (data.getItemSet() != null) {
			Iterator<Item> it = data.getItemSet().iterator();
	    	while(it.hasNext()){
	    		Item itemData = it.next();    		
	    		Item item = (Item)session.load(Item.class,itemData.getId());
	    		itemSet.add(item);	    	
	    	}
		}
		else
			itemSet = null;
		
		// itemValueSet
		itemValueSet = new HashSet<ItemValue>();
		if (data.getItemValueSet() != null) {
			Iterator<ItemValue> it = data.getItemValueSet().iterator();
	    	while(it.hasNext()){
	    		ItemValue itemValueData = it.next();    		
	    		Item item = (Item)session.load(Item.class,itemValueData.getItem().getId());
	    		Integer value = itemValueData.getItemValue();
	    		ItemValue itemValue = new ItemValue(item,value);
	    		itemValueSet.add(itemValue);	    	
	    	}
		}else
			itemValueSet = null;
			
		
		
		this.pointCommentSet = new HashSet<PointComment>();
		if (data.getPointCommentSet() != null) {
			Iterator<PointComment> it = data.getPointCommentSet().iterator();
	    	while(it.hasNext()){
	    		PointComment pointCommentData = it.next();    		
	    		Point point = (Point)session.load(Point.class, pointCommentData.getPoint().getId());
	    		PointComment pointComment = new PointComment(point, pointCommentData.getComment());
	    		this.pointCommentSet.add(pointComment);
	    	}
		}	
		
		this.classNumber = data.getClassNumber();
		
		this.pointObservationSelectedSet = new HashSet<PointObservationSelected>();
		if (data.getPointObservationSelectedSet() != null){
			Iterator<PointObservationSelected> it = data.getPointObservationSelectedSet().iterator();
			while (it.hasNext()){
				PointObservationSelected p = it.next();
				Point point = (Point)session.load(Point.class, p.getPoint().getId());
				PointObservation obs = (PointObservation) session.load(PointObservation.class, p.getObservation().getIdObservation());
				PointObservationSelected pp = new PointObservationSelected(point,obs);
				this.pointObservationSelectedSet.add(pp);
			}			
		}
		
		// Observaciones Seleccionadas
		this.observationsSelected = new HashSet<CEPAScheduleActivityRegistryObservationSelected>();
		if (data.getObservationsSelected() != null && data.getObservationsSelected().size() > 0){
			Iterator<CEPAScheduleActivityRegistryObservationSelected> it = 
				data.getObservationsSelected().iterator();
			while (it.hasNext()){
				CEPAScheduleActivityRegistryObservationSelected obsSel = it.next();
				if (obsSel != null){
					CEPAScheduleActivityRegistryObservation obs = 
							(CEPAScheduleActivityRegistryObservation)session.load(
							CEPAScheduleActivityRegistryObservation.class,obsSel.getObservation().getIdObservation());
					CEPAScheduleActivityRegistryObservationSelected sel = 
						new CEPAScheduleActivityRegistryObservationSelected(obs);
					this.observationsSelected.add(sel);
				}
			}
		}
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

	/**
	 * @return Returns the pointCommentSet.
	 */
	public Set<PointComment> getPointCommentSet() {
		return pointCommentSet;
	}

	public String getCirculationArea() {
		return circulationArea;
	}

	public void setCirculationArea(String circulationArea) {
		this.circulationArea = circulationArea;
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
	
	/*
	 * Dado un punto devuelve el comentario asociado a ese punto.
	 */
	public String getPointComment(Point point)
	{	
		if (this.pointCommentSet != null) 
		{
			Iterator<PointComment> it = this.pointCommentSet.iterator();
			while (it.hasNext())
			{
				PointComment pc = it.next();
				if (pc.getPoint().equals(point))
					return pc.getComment();
			}
		}
		return null;
	}
	
	
	/* Obtiene la lista de Puntos Comentados ordenados
	 * por el orden del punto
	 */
	public ArrayList<PointComment> getOrderedPointCommentSet(){
		// Creo una lista a partir del Set:
		if (this.pointCommentSet == null || this.pointCommentSet.size() == 0)
			return null;
		ArrayList<PointComment> orderedPointComment = new ArrayList<PointComment>();
		Iterator<PointComment> it = this.pointCommentSet.iterator();
		while (it.hasNext())
		{
			PointComment pc = it.next();
			orderedPointComment.add(pc);
		}
				
		// ordeno la lista por orden de los puntos
		java.util.Collections.sort(orderedPointComment, new PointComment());
		
		return orderedPointComment;
	}

	/**
	 * @param pointCommentSet The pointCommentSet to set.
	 */
	public void setPointCommentSet(Set<PointComment> pointCommentSet) {
		this.pointCommentSet = pointCommentSet;
	}

	public boolean getNovato() {
		return novato;
	}

	public void setNovato(boolean novato) {
		this.novato = novato;
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
	public Set<ItemValue> getItemValueSet() {
		return itemValueSet;
	}

	/**
	 * @param itemValueSet The itemValueSet to set.
	 */
	public void setItemValueSet(Set<ItemValue> itemValueSet) {
		this.itemValueSet = itemValueSet;
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

	public void setClassNumber(int classnumber) {
		this.classNumber = classnumber;
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
	 * @return the pointObservationSelectedSet
	 */
	public Set<PointObservationSelected> getPointObservationSelectedSet() {
		return pointObservationSelectedSet;
	}

	/**
	 * @param pointObservationSelectedSet the pointObservationSelectedSet to set
	 */
	public void setPointObservationSelectedSet(
			Set<PointObservationSelected> pointObservationSelectedSet) {
		this.pointObservationSelectedSet = pointObservationSelectedSet;
	}

	/**
	 * @return the observationsSelected
	 */
	public Set<CEPAScheduleActivityRegistryObservationSelected> getObservationsSelected() {
		return observationsSelected;
	}

	/**
	 * @param observationsSelected the observationsSelected to set
	 */
	public void setObservationsSelected(
			Set<CEPAScheduleActivityRegistryObservationSelected> observationsSelected) {
		this.observationsSelected = observationsSelected;
	}

	public Iterator<CEPAScheduleActivityRegistryObservationSelected> getObservationsSelectedIterator() {
		List<CEPAScheduleActivityRegistryObservationSelected> list = new LinkedList<CEPAScheduleActivityRegistryObservationSelected>();
		Iterator<CEPAScheduleActivityRegistryObservationSelected> it = observationsSelected.iterator();
		while(it.hasNext()) {
			list.add(it.next());
		}
		Collections.sort(list);
		return list.iterator();
	}

	public Iterator<PointObservationSelected> getPointObservationSelectedIterator() {
		List<PointObservationSelected> list = new LinkedList<PointObservationSelected>();
		Iterator<PointObservationSelected> it = pointObservationSelectedSet.iterator();
		while(it.hasNext()) {
			list.add(it.next());
		}
		Collections.sort(list);
		return list.iterator();
	}

	public Integer getClasification() {
		return clasification;
	}

	public void setClasification(Integer clasification) {
		this.clasification = clasification;
	}

	public boolean isFia() {
		return fia;
	}

	public void setFia(boolean fia) {
		this.fia = fia;
	}

	public boolean isFile() {
		return file;
	}

	public void setFile(boolean file) {
		this.file = file;
	}
}
