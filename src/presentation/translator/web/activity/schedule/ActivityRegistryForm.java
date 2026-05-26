/*
 * Created on 24-feb-2006
 */
package presentation.translator.web.activity.schedule;

import org.apache.struts.action.ActionForm;

import business.alert.handler.Util;

import communication.activity.tables.CEPAActivityScheduleRegistryData;
/**
 * @author adocarmo
 */
public class ActivityRegistryForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String checkboxParamList;
	private String idActivity;
	private String idDriver;
	private String url;
	private String note;
	private String obs;
	private String instructor;
	private String partner;
	private String cepaCourse;
	private String conductionType;
	private String skidcar;
	private String frenaje;
	private String plate;
	private String pointCommentList;
	private String evaluator;
	private String trade;
	private String initialKm;
	private String finalKm;
	private String initialHr;
	private String initialMinute;
	private String finalHr;
	private String finalMinute;
	private String vehicleType;			
	private String circulationAreaHighWay;
	private String circulationAreaRoad;			
	private String circulationAreaSubUrban;
	private String circulationAreaUrban;
	private String commentaryDriveOther;
	private String velocityBox;
	private String light;
	private String novato;
	private String noteoricoasistence;
	private String nopracticoasistence;
	private String drivingExperience;
	private String phrd;
	private String classNumber;
	
	public ActivityRegistryForm() {
	}

	public ActivityRegistryForm(CEPAActivityScheduleRegistryData registry) {
		super();
		this.idActivity = registry.getActivity().getActivityId().toString();
		this.idDriver = registry.getDriver().getId().toString();
		this.note = registry.getNote();
		this.obs = registry.getObs();
		this.instructor = registry.getInstructor();
		this.partner = registry.getPartner();
		this.conductionType = registry.getConductionType();
		this.plate = registry.getPlate();
		this.trade = registry.getTrade();
		this.initialKm = (registry.getInitialKm() == null) ? "" : registry.getInitialKm().toString();
		this.finalKm = (registry.getFinalKm() == null) ? "" : registry.getFinalKm().toString();
	/*	this.initialHr = Util.getTime(hour, minute) registry.getInitialHr();
		this.initialMinute = registry.getinitialMinute;
		this.finalHr = registry.getfinalHr;
		this.finalMinute = registry.getfinalMinute;
		this.vehicleType = registry.getvehicleType;
		this.circulationAreaHighWay = registry.getcirculationAreaHighWay;
		this.circulationAreaRoad = registry.getcirculationAreaRoad;
		this.circulationAreaSubUrban = registry.getcirculationAreaSubUrban;
		this.circulationAreaUrban = registry.getcirculationAreaUrban;
		this.commentaryDriveOther = registry.getcommentaryDriveOther;
		this.velocityBox = registry.getvelocityBox;
		this.light = registry.getlight;
		this.novato = registry.getnovato;
		this.noteoricoasistence = registry.getnoteoricoasistence;
		this.nopracticoasistence = registry.getnopracticoasistence;
		this.drivingExperience = registry.getdrivingExperience;
		this.phrd = registry.getphrd;
		this.classNumber = registry.getclassNumber;*/
	}
	
	
}	    
