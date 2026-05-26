package presentation.translator.web.driver;



import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;

import business.SystemAccess;
import business.alert.handler.Util;
import communication.administration.dpa.UnconformityDPAData;
import communication.administration.dpa.UnconformityItemDPAData;
import communication.administration.dpa.UnconformityItemTypeDPADataType;
import communication.administration.dpa.UnconformityTypeDPADataType;
import communication.datatype.DataTypeConstants;
import communication.driver.tables.DriverLogisticData;
import communication.exception.CommunicationProblemException;
import communication.exception.DCSecurityException;
import communication.exception.DeslogedException;



public class DriverLogisticForm extends ActionForm {

	 
	private static final long serialVersionUID = 1L;
	
	protected Date date;
	
	private Integer driverId;
	private String oldActivity;		

	private String activity;		

	protected String cancel;
	protected String dateDay;
	protected String dateMonth;
	protected String dateYear;
	
	protected String status;
	
	protected String city;
	 
	protected String observations;

	public DriverLogisticForm() {
    }

	public String getCancel() {
		return cancel;
	}

	public void setCancel(String cancel) {
		this.cancel = cancel;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public void setDateMonth(String dateMonth) {
		this.dateMonth = dateMonth;
	}

	public String getDateYear() {
		return dateYear;
	}

	public void setDateYear(String dateYear) {
		this.dateYear = dateYear;
	}

	public String  getStatus() {
		return status;
	}

	public void setStatus(String  status) {
		this.status = status;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getDriverId() {
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

	public void load(SystemAccess sys, Integer id, String activity) throws DeslogedException, CommunicationProblemException, DCSecurityException, RemoteException, Exception{		
		DriverLogisticData data = (id != null && activity != null) ? sys.getDriverReportFacade().getDriverLogistic(id, activity, sys.getUserSessionData()) : null;
		setDriverId(id);
		if(data!=null){
			setDate(data.getDateLastSchedule());
			setActivity(activity);
			setOldActivity(activity);
			if(data.getDateLastSchedule() != null) {
				Calendar cal=Calendar.getInstance();
				cal.setTime(data.getDateLastSchedule());
				setDateDay(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
				setDateMonth(String.valueOf(cal.get(Calendar.MONTH)+1));
				setDateYear(String.valueOf(cal.get(Calendar.YEAR)));
			}
			setStatus(String.valueOf(data.getStatus()));
			setObservations(data.getObservations());
			setCity(data.getCity());
		}else{
			Calendar cal=Calendar.getInstance();
			setDate(new Date());
			setDateDay(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
			setDateMonth(String.valueOf(cal.get(Calendar.MONTH)+1));
			setDateYear(String.valueOf(cal.get(Calendar.YEAR)));
			setStatus(String.valueOf(DriverLogisticData.ASSIGNED));
			setCity("");
			setObservations("");
		}
	}
	
	public DriverLogisticData makeDriverLogisticData(SystemAccess sys,HttpServletRequest request) throws DeslogedException, CommunicationProblemException, RemoteException, Exception{
		DriverLogisticData data=new DriverLogisticData();
		data.setDriverId(driverId);
		data.setActivity(activity);
		data.setStatus(new Integer(status));
		data.setObservations(observations);
		data.setCity(city);
		data.setDateLastSchedule(Util.getDate(dateDay, dateMonth, dateYear));
		return data;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public String getOldActivity() {
		return oldActivity;
	}

	public void setOldActivity(String oldActivity) {
		this.oldActivity = oldActivity;
	}

	public Collection getDeleteCollection() {
		Collection<String[]> s = new LinkedList<String[]>();
		s.add(new String[]{String.valueOf(driverId),activity});
		return s;
	}
	 
}
