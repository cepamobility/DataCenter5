package presentation.translator.web.vehicle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import business.SystemAccess;

import persistence.activity.tables.Point;
import persistence.activity.tables.PointComment;
import presentation.translator.web.driver.DriverDivOrgForm;
import presentation.translator.web.util.Util;

import communication.activity.tables.CheckListData;
import communication.activity.tables.ItemData;
import communication.activity.tables.ItemValueStrData;
import communication.activity.tables.PointCommentData;
import communication.activity.tables.PointData;
import communication.administration.corporation.tables.CorporationData;
import communication.administration.user.UserSessionData;
import communication.datatype.tables.AssignedVehicleDataType;
import communication.vehicle.tables.VehicleCheckData;
import communication.vehicle.tables.VehicleData;

/**
 * @author fcarriquiry
 *
 */
public class DPAVehicleCheckListEditForm extends DriverDivOrgForm {

	private static final long serialVersionUID = 1L;

	private String day;
	private String month;
	private String year;
	
	private String palet;
	private String obs;
	
	private HashMap<Integer, ItemValueStrData> items=new HashMap<Integer, ItemValueStrData>();
	private HashMap<Integer, PointCommentData> pointComments=new HashMap<Integer, PointCommentData>();
	
	private String inspectedby;
	private String serie;
	private String result;
	
	
	
	public String getInspectedby() {
		return inspectedby;
	}

	public void setInspectedby(String inspectedby) {
		this.inspectedby = inspectedby;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
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

	public String getPalet() {
		return palet;
	}

	public void setPalet(String palet) {
		this.palet = palet;
	}
	
	
	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}
	
	public void setItem(int itemId,String value){
		addItem(itemId);
		this.items.get(itemId).setItemValue(value);
	}
	
	public String getItem(int itemId){
		addItem(itemId);
		return this.items.get(itemId).getItemValue();
	}
	
	public void setItemTag(int itemId,String value){
		addItem(itemId);
		this.items.get(itemId).setItemTag(value);
	}
	
	public String getItemTag(int itemId){
		addItem(itemId);
		return this.items.get(itemId).getItemTag();
	}
	
	public void setComment(int pointId,String value){
		addPointComment(pointId);
		this.pointComments.get(pointId).setComment(value);
	}
	
	public String getComment(int pointId){
		addPointComment(pointId);
		return this.pointComments.get(pointId).getComment();
	}
	
	public VehicleCheckData toVehicleCheckData(VehicleCheckData vehicleCheckData){
		vehicleCheckData.setDate(Util.getDate(day, month, year));
		vehicleCheckData.setItemValueSet(this.items.values());
		vehicleCheckData.setPointCommentSet(this.pointComments.values());
		vehicleCheckData.setResult(Integer.valueOf(result));
		vehicleCheckData.setInspectedby(inspectedby);
		
		vehicleCheckData.setObs(obs);
		vehicleCheckData.setSerie(serie);
		
		return vehicleCheckData;
	}
	
	public void load(SystemAccess sys,VehicleCheckData vehicleCheck,VehicleData vehicleData,CheckListData checlist){
		this.palet=vehicleData.getMatricula();
		Calendar c = Calendar.getInstance();
        c.setTime(vehicleCheck.getDate());
        day = String.valueOf(c.get(Calendar.DATE));
        month = String.valueOf(c.get(Calendar.MONTH)+1);
        year = String.valueOf(c.get(Calendar.YEAR));
        obs = vehicleCheck.getObs();
        serie=vehicleCheck.getSerie();
        inspectedby=vehicleCheck.getInspectedby();
        result=vehicleCheck.getResult().toString();
        
       Iterator iter=  vehicleCheck.getItemValueSet().iterator();
       while (iter.hasNext()) {
		ItemValueStrData itemStr=(ItemValueStrData)iter.next();
		this.items.put(itemStr.getItem().getId(), itemStr);
       }
       
       Iterator iter2=  vehicleCheck.getPointCommentSet().iterator();
       while (iter2.hasNext()) {
	    PointCommentData data=(PointCommentData)iter2.next();
		this.pointComments.put(data.getPoint().getId(), data);
       }
       
	}
	
	public String validate(){
		return Util.getDate(day, month, year)==null?"generica.messages.invaliddate":null;
	}
	
	public void addItem(Integer i){
		if(!this.items.containsKey(i)){
			ItemValueStrData itemdata=new ItemValueStrData(new ItemData(i),"");
			this.items.put(i, itemdata);
		}
	}
	
	public void addPointComment(Integer i){
		if(!this.pointComments.containsKey(i)){
			PointCommentData data=new PointCommentData(new PointData(i), "");
			this.pointComments.put(i, data);
		}
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}
	
}	
