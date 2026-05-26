package presentation.translator.web.dpa;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import business.SystemAccess;
import business.alert.handler.Util;
import business.vehicle.VehicleReportFacade;

import communication.activity.tables.CheckListData;
import communication.activity.tables.ItemData;
import communication.activity.tables.PointData;
import communication.administration.corporation.tables.CorporationData;
import communication.administration.dpa.UnconformityDPAData;
import communication.administration.dpa.UnconformityItemDPAData;
import communication.administration.dpa.UnconformityItemTypeDPADataType;
import communication.administration.dpa.UnconformityTypeDPADataType;
import communication.datatype.DataTypeConstants;
import communication.exception.CommunicationProblemException;
import communication.exception.DCSecurityException;
import communication.exception.DeslogedException;
import communication.vehicle.tables.VehicleCheckData;
import communication.vehicle.tables.VehicleData;

public class DPAUnconformityCheckListForm extends DPAUnconformityForm implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public void load(SystemAccess sys, Integer id,UnconformityDPAData d,Iterator points,VehicleCheckData vehicleCheck) throws DeslogedException, CommunicationProblemException, DCSecurityException, RemoteException, Exception{
		UnconformityDPAData data=d;
		if(d==null){
			data=sys.getCorporationReportFacade().findUnconformityDPAById(sys.getUserSessionData(), id);
		}
		setComment(data.getComment());
		setUnconformityType(data.getType().getResourceBundleId());
		Calendar cal=Calendar.getInstance();
		cal.setTime(data.getDate());
		setDateDay(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
		setDateMonth(String.valueOf(cal.get(Calendar.MONTH)+1));
		setDateYear(String.valueOf(cal.get(Calendar.YEAR)));
		
		
		this.id=id!=null?id:data.getId();
		setIdReferido(data.getCheck());
		
		
 		while(points.hasNext()) {
			PointData point = (PointData)points.next();
			Iterator items = point.getSortItems();
			if(items.hasNext()) {
				ItemData item = (ItemData)items.next();
				int value = -1;
				String pointComment = null;
				if(vehicleCheck != null) {
					String checkValue = vehicleCheck.getValueFromItem(item.getId()).trim();
					value = (checkValue == null || !checkValue.equals("2")) ? -1 : 2;
				}//
				if(value==2){
					Integer i=point.getId();
					UnconformityItemDPAData itemDPA=getItemDPA(item.getText(),data.getItems());
					if(itemDPA!=null){
						setText(i, itemDPA.getComment());
						Calendar cal2=Calendar.getInstance();
						cal2.setTime(itemDPA.getExpirity());
						int day=diferenciaEnDias(itemDPA.getExpirity(),data.getDate());
						setExpirity(i,String.valueOf(Integer.valueOf(day)));
						setNotify(i, itemDPA.getNotify());
					}
				}
			}
		}
		this.data=data;
	}
	
	public Boolean getNotify(int index){
		if(this.notify.containsKey(index)){
			Boolean r=this.notify.get(index);
			this.notify.remove(index);
			return r;
		}
		
		return true;
	}
	

	public UnconformityDPAData makeUnconformityDPAData(SystemAccess sys,HttpServletRequest request) throws DeslogedException, CommunicationProblemException, RemoteException, Exception{
		UnconformityDPAData data=new UnconformityDPAData();
		data.setId(id!=null && id>0?id:null);
		data.setUserId(sys.getUserSessionData().getFilter().getLoginName());
		data.setComment(comment);
		data.setDate(Util.getDate(dateDay, dateMonth, dateYear));
		
		data.setStatus(UnconformityDPAData.STATUS_NEW);
		data.setType(new UnconformityTypeDPADataType(unconformityType));
		data.setCheck( Integer.valueOf(idReferido));
		
		VehicleReportFacade vehicleReport = sys.getVehicleReportFacade(); 
		VehicleCheckData vehicleCheck = vehicleReport.findVehicleCheck(new Integer(checkId),sys.getUserSessionData());
		VehicleData vehicleData = vehicleCheck.getVehicle();
		
		Integer checkTypeId=0;
		if(sys.isCorporation(CorporationData.DPA) || sys.isCorporation(CorporationData.FORESTALORIENTAL)||sys.isCorporation(CorporationData.WEYERHAEUSER)||sys.isCorporation(CorporationData.MONTES_DE_PLATA)||sys.isCorporation(CorporationData.COFUSA) ){
			checkTypeId=new Integer(vehicleCheck.getName());
		}else{
			//Este es el general pero puede que no aplique para alguna empresa en particular
			checkTypeId=CheckListData.TRUCK_CHECKLIST;
		}
		
		CheckListData checkList = sys.getVehicleReportFacade().getCheckList(checkTypeId,sys.getUserSessionData());
		Iterator points = checkList.getSortPoints();
		
		while(points.hasNext()) {
			PointData point = (PointData)points.next();
			Iterator items = point.getSortItems();
			if(items.hasNext()) {
				ItemData item = (ItemData)items.next();
				int value = -1;
				String pointComment = null;
				if(vehicleCheck != null) {
					String checkValue = vehicleCheck.getValueFromItem(item.getId()).trim();
					value = (checkValue == null || !checkValue.equals("2")) ? -1 : 2;
				}//
				if(value==2){
					Integer itemType=point.getId();
					UnconformityItemDPAData itemDPA=new UnconformityItemDPAData();
					itemDPA.setComment(texts.containsKey(itemType)?texts.get(itemType):"");
					itemDPA.setNotify(request.getParameter("notify["+itemType+"]")!=null && request.getParameter("notify["+itemType+"]").equals("on"));
					itemDPA.setType(item.getText());
					Calendar cal=Calendar.getInstance();
					cal.setTime(data.getDate());
					cal.add( Calendar.DATE, Integer.valueOf(expirity.containsKey(itemType)?expirity.get(itemType):"0"));
					itemDPA.setExpirity(cal.getTime());
					data.getItems().add(itemDPA);
				}
			}
		}
		
		return data;
	}
	
	private UnconformityItemDPAData getItemDPA(String text, ArrayList items) {
		Iterator iter=items.iterator();
		while(iter.hasNext()){
			UnconformityItemDPAData item=(UnconformityItemDPAData)iter.next();
			if(item.getType().equalsIgnoreCase(text)){
				return item;
			}
		}
		return null;
	}

}
