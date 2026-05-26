package presentation.translator.web.dpa;



import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;

import business.SystemAccess;
import business.alert.handler.Util;

import communication.administration.dpa.UnconformityDPAData;
import communication.administration.dpa.UnconformityItemDPAData;
import communication.administration.dpa.UnconformityItemTypeDPADataType;
import communication.administration.dpa.UnconformityTypeDPADataType;
import communication.datatype.DataTypeConstants;
import communication.exception.CommunicationProblemException;
import communication.exception.DCSecurityException;
import communication.exception.DeslogedException;



public class DPAUnconformityForm extends ActionForm {

	 
	private static final long serialVersionUID = 1L;
;
	protected String href;
	protected Integer idReferido;
	
	protected String unconformityType;
	
	protected Date date;
			
	protected String dateDay;
	protected String dateMonth;
	protected String dateYear;
	
	protected String comment;
	
	protected String teleControl;
	protected String checkId;
	protected Integer status;
	
	protected String mode;
	protected Integer id;
	
	protected HashMap<Integer, String> texts;
	protected HashMap<Integer, Boolean> notify;
	protected HashMap<Integer, String> expirity;
	protected HashMap<Integer, String> items;
	protected UnconformityDPAData data;
	
	 

	public DPAUnconformityForm() {
		this.texts=new HashMap<Integer, String>();
		this.items=new HashMap<Integer, String>();
		this.expirity=new HashMap<Integer, String>();
		this.notify=new HashMap<Integer, Boolean>();
    }

	public void setItem(int index,String b){
		addItem(index,b);
	}

	public String getItem(int index){
		if(this.items.containsKey(index)){
			return this.items.get(index);
		}
		
		return "off";
	}

	public Boolean isItem(int index){
		if(this.items.containsKey(index)){
			return this.items.get(index).equalsIgnoreCase("on");
		}
		
		return false;
	}
	
	public void setText(int index,String text){
		addText(index,text);
	}

	public String getText(int index){
		if(this.texts.containsKey(index)){
			return this.texts.get(index);
		}
		
		return "";
	}
	
	public void setExpirity(int index,String text){
		addExpirity(index,text);
	}

	public String getExpirity(int index){
		if(this.expirity.containsKey(index)){
			return this.expirity.get(index);
		}
		
		return "";
	}
	
	public void setNotify(int index,Boolean b){
		addNotify(index,b);
	}

	public Boolean getNotify(int index){
		if(this.notify.containsKey(index)){
			return this.notify.get(index);
		}
		
		return false;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIdReferido() {
		return idReferido;
	}


	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIdReferido(Integer idReferido) {
		this.idReferido = idReferido;
	}
	 

	public String getUnconformityType() {
		return unconformityType;
	}


	public UnconformityDPAData getData() {
		return data;
	}

	public void setData(UnconformityDPAData data) {
		this.data = data;
	}

	public void setUnconformityType(String unconformityType) {
		this.unconformityType = unconformityType;
	}


	public String getTeleControl() {
		return teleControl;
	}


	public void setTeleControl(String teleControl) {
		this.teleControl = teleControl;
	}


	public String getCheckId() {
		return checkId;
	}


	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}

	public String getDateDay() {
		return dateDay;
	}


	public void setDateDay(String dateDay) {
		this.dateDay = dateDay;
	}


	public String getDateMonth() {
		return dateMonth;
	}


	public String getDateYear() {
		return dateYear;
	}


	public void setDateYear(String dateYear) {
		this.dateYear = dateYear;
	}


	public void setDateMonth(String dateMonth) {
		this.dateMonth = dateMonth;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}

	
	

	public void addExpirity(Integer i,String text){
		if(!this.expirity.containsKey(i)){
			this.expirity.put(i, text);
		}else{
			this.expirity.remove(i);
			this.expirity.put(i, text);
		}
	}

	public void addNotify(Integer i,Boolean b){
		if(!this.notify.containsKey(i)){
			this.notify.put(i, b);
		}else{
			this.notify.remove(i);
			this.notify.put(i, b);
		}
	}
	
	public void addText(Integer i,String text){
		if(!this.texts.containsKey(i)){
			this.texts.put(i, text);
		}else{
			this.texts.remove(i);
			this.texts.put(i, text);
		}
	}

	public void addItem(Integer i,String b){
		if(!this.items.containsKey(i)){
			this.items.put(i, b);
		}else{
			this.items.remove(i);
			this.items.put(i, b);
		}
	}
	
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void load(SystemAccess sys, Integer id,UnconformityDPAData d) throws DeslogedException, CommunicationProblemException, DCSecurityException, RemoteException, Exception{		UnconformityDPAData data=d;
		if(d==null){
			data=sys.getCorporationReportFacade().findUnconformityDPAById(sys.getUserSessionData(), id);
		}
		setComment(data.getComment());
		setUnconformityType(data.getType().getResourceBundleId());
		Calendar cal=Calendar.getInstance();
		cal.setTime(data.getDate());
		setDate(data.getDate());
		setDateDay(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
		setDateMonth(String.valueOf(cal.get(Calendar.MONTH)+1));
		setDateYear(String.valueOf(cal.get(Calendar.YEAR)));
		
		
		this.id=id!=null?id:data.getId();
		if(data.getType().getResourceBundleId().equals(UnconformityTypeDPADataType.TYPE_VEHICLE_CHECK)){
			setIdReferido(data.getCheck());
		}else if(data.getType().getResourceBundleId().equals(UnconformityTypeDPADataType.TYPE_TELEMATIC_CONTROL)){
			setIdReferido(data.getTelematicControl().getId());
		}else if(data.getType().getResourceBundleId().equals(UnconformityTypeDPADataType.TYPE_DRIVER)){
			setIdReferido(data.getDriverId());
		}
		
		Iterator iter=data.getItems().iterator();
		Integer index=0;
		while(iter.hasNext()){
			UnconformityItemDPAData item=(UnconformityItemDPAData)iter.next();
			Integer i=0;
			if(data.getType().getResourceBundleId().equals(UnconformityTypeDPADataType.TYPE_DRIVER)){
				 i=Integer.valueOf(item.getType().substring(item.getType().lastIndexOf(".")+1));
			}else{
				i=item.getId();
			}
			setItem(i, "on");	
			setText(i, item.getComment());
			Calendar cal2=Calendar.getInstance();
			cal2.setTime(item.getExpirity());
			int day=diferenciaEnDias(item.getExpirity(),data.getDate());
			setExpirity(i,String.valueOf(Integer.valueOf(day)));
			setNotify(i, item.getNotify());
		}
		this.data=data;
	}
	
	public UnconformityDPAData makeUnconformityDPAData(SystemAccess sys,HttpServletRequest request) throws DeslogedException, CommunicationProblemException, RemoteException, Exception{
		UnconformityDPAData data=new UnconformityDPAData();
		data.setId(id!=null && id>0?id:null);
		data.setUserId(sys.getUserSessionData().getFilter().getLoginName());
		data.setComment(comment);
		data.setDate(Util.getDate(dateDay, dateMonth, dateYear));
		
		data.setStatus(UnconformityDPAData.STATUS_NEW);
		data.setType(new UnconformityTypeDPADataType(unconformityType));
		if(unconformityType.equals(UnconformityTypeDPADataType.TYPE_VEHICLE_CHECK)){
			data.setCheck( Integer.valueOf(idReferido));
		}else if(unconformityType.equals(UnconformityTypeDPADataType.TYPE_TELEMATIC_CONTROL)){
			data.setTelematicControl(sys.getDriverReportFacade().findTelematicControl(Integer.valueOf(idReferido), sys.getUserSessionData()));
		}else if(unconformityType.equals(UnconformityTypeDPADataType.TYPE_DRIVER)){
			data.setDriverId( Integer.valueOf(idReferido));
		}
		
		Collection itemTypes=sys.getDataTypeReportFacade().findAll(DataTypeConstants.DPA_UNCONFORMITY_ITEM_TYPE, sys.getUserSessionData());
		Iterator<Integer> iter =this.items.keySet().iterator();
		while(iter.hasNext()){
			Integer itemType=iter.next();
			if(request.getParameter("item["+itemType+"]")!=null && request.getParameter("item["+itemType+"]").equalsIgnoreCase("on")){
				UnconformityItemDPAData item=new UnconformityItemDPAData();
				item.setComment(texts.containsKey(itemType)?texts.get(itemType):"");
				item.setNotify(notify.containsKey(itemType)?(notify.get(itemType)==null?false:notify.get(itemType)):false);
				item.setType(getItemType(itemType,itemTypes));
				Calendar cal=Calendar.getInstance();
				cal.setTime(data.getDate());
				cal.add( Calendar.DATE, Integer.valueOf(expirity.containsKey(itemType)?expirity.get(itemType):"0"));
				item.setExpirity(cal.getTime());
				
				data.getItems().add(item);
			}
		}
		
		return data;
	}
	
	public String getItemType(Integer i,Collection itemTypes){
		UnconformityItemTypeDPADataType type=null;
		
		Iterator iter=itemTypes.iterator();
		while(iter.hasNext()){
			UnconformityItemTypeDPADataType aux=(UnconformityItemTypeDPADataType)iter.next();
			if(aux.getResourceBundleId().substring(aux.getResourceBundleId().lastIndexOf(".")+1).equals(i.toString())){
				type=aux;
				break;
			}
		}
		
		return type.getResourceBundleId();
	}
	
	public static int diferenciaEnDias(Date fechaMayor,Date fechaMenor){
		/* CREAMOS LOS OBJETOS GREGORIAN CALENDAR PARA EFECTUAR LA RESTA */
		GregorianCalendar date1 = new GregorianCalendar();
		date1.setTime(fechaMenor); //dateIni es el objeto Date
		GregorianCalendar date2 = new GregorianCalendar();
		date2.setTime(fechaMayor); //dateFin es el objeto Date
		int rango = 0;
		/* COMPROBAMOS SI ESTAMOS EN EL MISMO AÑO */
		if (date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR)) {
			rango = date2.get(Calendar.DAY_OF_YEAR)-date1.get(Calendar.DAY_OF_YEAR);
		} else {
		/* SI ESTAMOS EN DISTINTO AÑO COMPROBAMOS QUE EL AÑO DEL DATEINI NO SEA BISIESTO
		* SI ES BISIESTO SON 366 DIAS EL AÑO
		* SINO SON 365
		*/
		int diasAnyo = date1.isLeapYear(date1.get(Calendar.YEAR)) ? 366 : 365;
		/* CALCULAMOS EL RANGO DE AÑOS */
		int rangoAnyos = date2.get(Calendar.YEAR)- date1.get(Calendar.YEAR);
		/* CALCULAMOS EL RANGO DE DIAS QUE HAY */
		rango = (rangoAnyos * diasAnyo) + (date2.get(Calendar.DAY_OF_YEAR) - date1.get(Calendar.DAY_OF_YEAR));
		}
		return rango;
		}
    
}
