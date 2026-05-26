/*
 * Created on 24-feb-2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package presentation.translator.web.activity.schedule;

import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.ServletRequest;

import presentation.translator.web.driver.DriverDivOrgForm;
import presentation.translator.web.util.Util;

import communication.activity.tables.CEPAActivityScheduleRegistryData;
import communication.activity.tables.ItemData;
import communication.activity.tables.ItemValueData;
import communication.activity.tables.PointCommentData;
import communication.activity.tables.PointData;
import communication.language.Text;

public class ActivityCDCayacoRegistryForm extends DriverDivOrgForm {

	private static final long serialVersionUID = 1L;

	private String activity;
	private String driver;

	private String instructor;
	private String trade;
	private String plate;
	private String initialHr;
	private String initialMinute;
	private String finalHr;
	private String finalMinute;
	private String initialKm;
	private String finalKm;
	private String observations;
	
	public ActivityCDCayacoRegistryForm() {
	}

	public ActivityCDCayacoRegistryForm(CEPAActivityScheduleRegistryData registryData) {
		instructor = registryData.getInstructor();
		trade = registryData.getTrade();
		plate = registryData.getPlate();
		Calendar c = Calendar.getInstance();
		if(registryData.getInitialHr() != null) {
			c.setTimeInMillis(registryData.getInitialHr().getTime());
			initialHr = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
			initialMinute = String.valueOf(c.get(Calendar.MINUTE));
		}
		if(registryData.getFinalHr() != null) {
			c.setTimeInMillis(registryData.getFinalHr().getTime());
			finalHr = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
			finalMinute = String.valueOf(c.get(Calendar.MINUTE));
		}
		initialKm = (registryData.getInitialKm() == null) ? "" : String.valueOf(registryData.getInitialKm());
		finalKm = (registryData.getFinalKm() == null) ? "" : String.valueOf(registryData.getFinalKm());
		observations = registryData.getObs();
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public String getTrade() {
		return trade;
	}

	public void setTrade(String trade) {
		this.trade = trade;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getInitialHr() {
		return initialHr;
	}

	public void setInitialHr(String initialHr) {
		this.initialHr = initialHr;
	}

	public String getInitialMinute() {
		return initialMinute;
	}

	public void setInitialMinute(String initialMinute) {
		this.initialMinute = initialMinute;
	}

	public String getFinalHr() {
		return finalHr;
	}

	public void setFinalHr(String finalHr) {
		this.finalHr = finalHr;
	}

	public String getFinalMinute() {
		return finalMinute;
	}

	public void setFinalMinute(String finalMinute) {
		this.finalMinute = finalMinute;
	}

	public String getInitialKm() {
		return initialKm;
	}

	public void setInitialKm(String initialKm) {
		this.initialKm = initialKm;
	}

	public String getFinalKm() {
		return finalKm;
	}

	public void setFinalKm(String finalKm) {
		this.finalKm = finalKm;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}
	
	public CEPAActivityScheduleRegistryData getRegistryData(Collection<PointData> points,ServletRequest request) {
 		CEPAActivityScheduleRegistryData registryData= new CEPAActivityScheduleRegistryData(activity,driver,observations);
 		registryData.setInstructor(instructor);
 		registryData.setTrade(trade);
 		registryData.setPlate(plate);
 		registryData.setInitialHr(Util.getTime(initialHr,initialMinute));
 		registryData.setFinalHr(Util.getTime(finalHr,finalMinute));
 		if(Util.isNumber(initialKm)) {
 			registryData.setInitialKm(new Integer(initialKm));
 		}
 		if(Util.isNumber(finalKm)) {
 			registryData.setFinalKm(new Integer(finalKm));
 		}

 		Collection<PointCommentData> pointComments = new LinkedList<PointCommentData>();
		Collection<ItemValueData> itemsValues = new LinkedList<ItemValueData>(); // Para CommentaryDrive J&J
 		
 		Iterator<PointData> it = points.iterator();
 		while(it.hasNext()) {
 			PointData point = it.next();
 			pointComments.add(new PointCommentData(point,request.getParameter("observations_"+String.valueOf(point.getId()))));
 			
 			Iterator<ItemData> items = point.getSortItems();
 			while(items.hasNext()) {
 				ItemData item = items.next();
 				if(item.getId().intValue() != 2754) {
 					itemsValues.add(new ItemValueData(item,new Integer(request.getParameter("item"+String.valueOf(item.getId())))));
 				}
 			}
 		}
 		registryData.setPointCommentSet(pointComments);
 		registryData.setItemValueSet(itemsValues);
 		registryData.setHasResult(true);
 		
 		return registryData;
	}
	
	public String validate(Collection<PointData> points,ServletRequest request,Text messages) {
		if(!Util.checkHour(initialHr,initialMinute)) {
			return messages.getText("generic.activity.trainingonthejob.invalidHour");
		}
		if(!Util.empty(initialKm) && !Util.isNumber(initialKm)) {
			return messages.getText("generic.error.invaliddata")+" "+messages.getText("generic.activity.form.tranningonthejob.initialKm");
		}
			
		if(!Util.checkHour(finalHr,finalMinute)) {
			return messages.getText("generic.activity.trainingonthejob.invalidHour");
		}
		if(!Util.empty(finalKm) && !Util.isNumber(finalKm)) {
			return messages.getText("generic.error.invaliddata")+" "+messages.getText("generic.activity.form.tranningonthejob.finalKm");
		}

		Iterator<PointData> it = points.iterator();
 		while(it.hasNext()) {
 			Iterator<ItemData> items = it.next().getSortItems();
 			while(items.hasNext()) {
 				ItemData item = items.next();
 				if(item.getId().intValue() != 2754) {
 					if(Util.empty(request.getParameter("item"+String.valueOf(item.getId())))){
 						return messages.getText("generic.messages.missingdata")+" "+messages.getText(item.getText());
 					}
 				}
 			}
 		}
 		return null;
	}
}	    
