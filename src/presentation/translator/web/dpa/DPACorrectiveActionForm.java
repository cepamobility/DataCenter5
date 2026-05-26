package presentation.translator.web.dpa;



import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import presentation.translator.web.vehicle.DPAImageUploadForm.ItemAux;

import business.SystemAccess;
import business.alert.handler.Util;

import communication.administration.dpa.CorrectiveActionDPAData;
import communication.administration.dpa.CorrectiveActionImageDPAData;
import communication.administration.dpa.UnconformityDPAData;
import communication.administration.dpa.UnconformityItemDPAData;
import communication.administration.dpa.UnconformityItemTypeDPADataType;
import communication.administration.dpa.UnconformityTypeDPADataType;
import communication.administration.user.UserSessionData;
import communication.datatype.DataTypeConstants;
import communication.exception.CommunicationProblemException;
import communication.exception.DCSecurityException;
import communication.exception.DeslogedException;



public class DPACorrectiveActionForm extends ActionForm {

	private HashMap<Integer, FormFile> map;
	private HashMap<Integer, Boolean> items;
	
	private static final long serialVersionUID = 1L;

	private Integer unconformityDPAId; 

	private String type;
	private String comment;
	
	private String day;
	private String month;
	private String year;
	private String hour;
	private String minute;
	
	private Integer id;
	
	public DPACorrectiveActionForm() {
		map=new HashMap<Integer, FormFile>();
		items=new HashMap<Integer, Boolean>();
    }
	
	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUnconformityDPAId() {
		return unconformityDPAId;
	}

	public void setUnconformityDPAId(Integer unconformityDPAId) {
		this.unconformityDPAId = unconformityDPAId;
	}

	public void setItem(int index,Boolean s){
		this.items.put(index, s);
	}
	
	public Boolean getItem(int index){
		return this.items.containsKey(index) && this.items.get(index);
	}
	
	public Boolean isItem(int index){
		return this.items.containsKey(index) && this.items.get(index);
	}
	
	public void setFiles(int index,FormFile file) {
		remove(index);
		this.map.put(index,file);
	}

	public Collection getFiles() {
		return map.values();
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	private void remove(int index) {
		if(map.containsKey(new Integer(index))){
			map.remove(index);
		}
	}
	
	public CorrectiveActionDPAData load(Integer id,SystemAccess sys) throws DeslogedException, CommunicationProblemException, DCSecurityException, RemoteException, Exception{
		CorrectiveActionDPAData data=sys.getCorporationReportFacade().findCorrectiveActionById(sys.getUserSessionData(), id);
		this.id=data.getId();
		this.comment=data.getComment();
		Calendar cal=Calendar.getInstance();
		cal.setTime(data.getDate());
		setDay(String.valueOf(cal.get(Calendar.DATE)));
		setMonth(String.valueOf(cal.get(Calendar.MONTH)+1));
		setYear(String.valueOf(cal.get(Calendar.YEAR)));
		setHour(Integer.valueOf(cal.get(Calendar.HOUR_OF_DAY)).toString());
		setMinute(Integer.valueOf(cal.get(Calendar.MINUTE)).toString());
		setType(String.valueOf(data.getType()));
		return data;
	}
	
	public CorrectiveActionDPAData makeCorrectiveActionDPAData(SystemAccess sys,HttpServletRequest request) throws DeslogedException, CommunicationProblemException, DCSecurityException, RemoteException, Exception{
		CorrectiveActionDPAData data=new CorrectiveActionDPAData();
		data.setId(id);
		data.setComment(comment);
		Calendar cal=Calendar.getInstance();
		cal.setTime(Util.getDate(day, month, year));
		cal.set(Calendar.HOUR_OF_DAY, Integer.valueOf(hour));
		cal.set(Calendar.MINUTE, Integer.valueOf(minute));
		data.setDate(cal.getTime());
		
		UnconformityDPAData uData=sys.getCorporationReportFacade().findUnconformityDPAById(sys.getUserSessionData(),Integer.valueOf(unconformityDPAId));
		data.setUnconformityDPAData(uData);
		
		Iterator iter=uData.getItems().iterator();
		while(iter.hasNext()){
			UnconformityItemDPAData itemId=(UnconformityItemDPAData)iter.next();
			if(request.getParameter("item["+itemId.getId()+"]")!=null && request.getParameter("item["+itemId.getId()+"]").equals("on")){
				data.getItems().add(itemId);
			}
		}
		
		iter=this.map.values().iterator();
		while(iter.hasNext()){
			FormFile file=(FormFile)iter.next();
			CorrectiveActionImageDPAData image=new CorrectiveActionImageDPAData();
			image.setFileName(file.getFileName());
			image.setInputStream(file.getInputStream());
			data.getImages().add(image);
		}
		
		if(id!=null && id>0){
			CorrectiveActionDPAData cData=sys.getCorporationReportFacade().findCorrectiveActionById(sys.getUserSessionData(), id);
			iter=cData.getImages().iterator();
			while(iter.hasNext()){
				CorrectiveActionImageDPAData itemId=(CorrectiveActionImageDPAData)iter.next();
				if(request.getParameter("fileB["+itemId.getFileName()+"]")!=null && request.getParameter("fileB["+itemId.getFileName()+"]").equals("on")){
					data.getImages().add(itemId);
				}
			}
		}
		
		
		return data;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	
}
