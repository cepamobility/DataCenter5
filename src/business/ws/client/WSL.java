package business.ws.client;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import business.alert.handler.Util;
import business.ws.client.activity.WSLCEPAActivityScheduleRegistryData;
import business.ws.client.common.WSLDivOrgData;
import business.ws.client.driver.WSLDriverData;
import business.ws.client.driver.WSLDriverMainAttributesData;
import business.ws.client.driver.WSLDriverStatusData;
import business.ws.client.driver.WSLTelematicControlData;
import business.ws.client.vehicle.WSLVehicleData;
import business.ws.client.vehicle.WSLVehicleStatusData;
import communication.activity.tables.CEPAActivityScheduleRegistryData;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.ItemData;
import communication.datatype.tables.GeneralDTData;
import communication.driver.tables.AptitudTestData;
import communication.driver.tables.DriverData;
import communication.driver.tables.DriverMainAttributes;
import communication.driver.tables.DriverStatusData;
import communication.driver.tables.TelematicControlData;
import communication.language.Text;
import communication.vehicle.tables.VehicleData;
import communication.vehicle.tables.VehicleMainAttributes;
import communication.vehicle.tables.VehicleStatusData;

public class WSL {
	
	
	
	public synchronized static WSLDriverMainAttributesData t(DriverMainAttributes vData){
		WSLDriverMainAttributesData res=new WSLDriverMainAttributesData();
		res.setId(vData.getId());
		res.setFirstName(vData.getFirstName());
		res.setLastName(vData.getLastName());
		res.setCountry(vData.getCountry().getResourceKey());
		res.setBirthDate(Util.formatDate(vData.getBirthDate()));
		res.setCity(vData.getCity());
		res.setExclusionDate(Util.formatDate(vData.getExclusionDate()));
		res.setCorporationId(vData.getCorporationId());
		res.setLicenceExpirity(Util.formatDate(vData.getLicenceExpirity()));
		res.setDivOrgItem1(vData.getDivOrgItem1());
		res.setDivOrgItem2(vData.getDivOrgItem2());
		res.setDivOrgItem3(vData.getDivOrgItem3());
		res.setCorporationId(vData.getCorporationId());
		
    	return res;
	}
	
	public synchronized static WSLDriverData t(DriverData vData){
		WSLDriverData res=new WSLDriverData();
		SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd");
		res.setId(vData.getId());
		res.setFirstName(vData.getFirstName());
		res.setLastName(vData.getLastName());
		res.setCountry(vData.getCountry().getResourceKey());
		res.setBirthDate(vData.getBirthDate());
		res.setCity(vData.getCity());
		res.setExclusionDate(vData.getExclusionDate());
		res.setPontos(vData.getPoints());
		if(vData.getLicenceExpirity()!=null){
			res.setLicenceExpirity(format.format(vData.getLicenceExpirity()));
		}
		res.setSex(getValueDType(vData.getSex()));
		res.setNationality(vData.getNationality());
		res.setCorporation(vData.getCorporation());
		DivOrgData[] divOrgs=new DivOrgData[vData.getDivOrgs().size()];
		Iterator it=vData.getDivOrgsIterator();
		ArrayList<WSLDivOrgData> aux=new ArrayList<WSLDivOrgData>();
		while(it.hasNext()){
			DivOrgData d=(DivOrgData)it.next();
			aux.add(t(d));
		}
		res.setDivOrgs(aux.toArray(new WSLDivOrgData[]{}));
		res.setLicenseCategory(vData.getLicenseCategory());
		res.setLicenseGet(vData.getLicenseGet());
		res.setOfficeAddress(vData.getOfficeAddress());
		res.setEmail(vData.getEmail());
		res.setPersonalAddress(vData.getPersonalAddress());
		res.setStateOrRegion(vData.getStateOrRegion());
		res.setCorporationId(vData.getCorporationId());
		res.setPhoneNumber(vData.getPhoneNumber());
		res.setInclusionDate(vData.getInclusionDate());
		res.setHrdConditions(vData.getHrdConditions());
		res.setDrives(vData.isDrives());
	    res.setEmployee(vData.isEmployee());
	    res.setAptitudTest(new ArrayList<AptitudTestData>(vData.getAptitudTest()).toArray(new AptitudTestData[]{}));
	    DriverMainAttributes main=(DriverMainAttributes) vData;
	    res.setDivOrgItem1(main.getDivOrgItem1());
	    res.setDivOrgItem2(main.getDivOrgItem2());
	    res.setDivOrgItem3(main.getDivOrgItem3());
	    if(main.getLicenceExpirity()!=null){
			res.setLicenceExpirity(format.format(main.getLicenceExpirity()));
		}
    	return res;
	}
	public synchronized static WSLCEPAActivityScheduleRegistryData t(CEPAActivityScheduleRegistryData vData){
		WSLCEPAActivityScheduleRegistryData res=new WSLCEPAActivityScheduleRegistryData();
		res.setFecha(vData.getDate());
		res.setNote(vData.getNote());
		res.setType(vData.getActivity().getType().getResourceBundleId());
		res.setId(vData.getActivity().getActivityId());
		return res;
	}
	
	public static WSLTelematicControlData t(TelematicControlData data) {
		WSLTelematicControlData res=new WSLTelematicControlData();
		res.setOccurrences(data.getOccurrences());
		res.setSpeed(data.getSpeed());
		res.setLimitExceeded(data.getLimitExceeded());
		res.setLimitExceededRain(data.getLimitExceededRain());
		res.setTime(data.getTime());
		res.setDuration(data.getDuration());    
		res.setTotalDuration(data.getDuration());
		res.setDistance(data.getDistance());
		res.setAverageVelocity(data.getAverageVelocity());
		res.setGarage(data.getGarage());    
		res.setPoint(data.getPoint());    
		res.setStatus(data.getStatus());    
		res.setStatusComment(data.getStatusComment());
		res.setId(data.getId());
		
		if(data.getDriver()!=null){
			res.setDriver(t(data.getDriver()));	
		}
		
		if(data.getVehicle()!=null){
			res.setVehicle(t(data.getVehicle()));	
		}
		
		SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd");
		res.setDateStr(format.format(data.getDate()));
		res.setType(data.getType());	
		res.setRecomendation(data.getRecomendation());	
		res.setObservations(data.getObservations());
		res.setColor(data.getColor());    
		if(data.getDate2()!=null){
			res.setDate2Str(format.format(data.getDate2()));	
		}
		
		if(data.getDate3()!=null){
			res.setDate3Str(format.format(data.getDate3()));	
		}
		
		
		return res;
	}

	public synchronized static WSLVehicleStatusData t(VehicleStatusData vData,Text messages){
		WSLVehicleStatusData vStatus=new WSLVehicleStatusData();
		vStatus.setPointAccount(vData.getPointAccount());
		vStatus.setState(vData.getState());
		vStatus.setUnconformities(vData.getUnconformities());
		vStatus.setVehicle(t(vData.getVehicle(),messages));
		
		return vStatus;
	}
	
	public synchronized static WSLVehicleData t(VehicleData vData,Text messages){
		WSLVehicleData wslvData=new WSLVehicleData();
		
		wslvData.setType(messages.getText(getValueDType(vData.getType())));
		wslvData.setColour(vData.getColour());
		wslvData.setPermissionNumber(vData.getPermissionNumber());
		wslvData.setUse(getValueDType(vData.getUse()));
		wslvData.setPermissionExpiry(vData.getPermissionExpiry());
		wslvData.setOwnerShip(getValueDType(vData.getOwnerShip()));
		wslvData.setFuel(getValueDType(vData.getFuel()));
		wslvData.setPurchaseDate(vData.getPurchaseDate());
		wslvData.setChasisNumber(vData.getChasisNumber());
		wslvData.setCo2emission(vData.getCo2emission());
		wslvData.setConsume(vData.getConsume());
		wslvData.setEngine(getValueDType(vData.getEngine()));
		wslvData.setTankCapacity(vData.getTankCapacity());
		wslvData.setTankUnit(getValueDType(vData.getTankUnit()));
		wslvData.setAbs(vData.getAbs());
		wslvData.setAirBag(vData.getAirBag());
		wslvData.setAireAcondicionado(vData.getAireAcondicionado());
		wslvData.setOdometerUnit(getValueDType(vData.getOdometerUnit()));
		wslvData.setCorporation(vData.getCorporation());
		DivOrgData[] divOrgs=new DivOrgData[vData.getDivOrgs().size()];
		Iterator it=vData.getDivOrgsIterator();
		ArrayList<WSLDivOrgData> aux=new ArrayList<WSLDivOrgData>();
		while(it.hasNext()){
			DivOrgData d=(DivOrgData)it.next();
			aux.add(t(d));
		}
		wslvData.setDivOrgs(aux.toArray(new WSLDivOrgData[]{}));	
	   
		wslvData.setModel(vData.getModel());
		wslvData.setTrade(messages.getText(getValueDType(vData.getTrade())));
		
		wslvData.setCover(getValueDType(vData.getCover()));
		wslvData.setCostCenter(vData.getCostCenter());
		wslvData.setInclusionDate(vData.getInclusionDate());
	    
		wslvData.setMtopExpiryStr(Util.formatDate(vData.getMtopExpiry()));
		wslvData.setSuctaExpiryStr(Util.formatDate(vData.getSuctaExpiry()));
		wslvData.setMtopExpiry(vData.getMtopExpiry());
		wslvData.setSuctaExpiry(vData.getSuctaExpiry());
		wslvData.setGreenCardExpiry(vData.getGreenCardExpiry());
	    
		wslvData.setEnsuranceType(getValueDType(vData.getEnsuranceType()));
		wslvData.setEnsurance(vData.getEnsurance());
		
		wslvData.setOwnerCard(vData.isOwnerCard());
	           
		wslvData.setAxisCount(vData.getAxisCount());
		wslvData.setHp(vData.getHp());
	    
		wslvData.setObservations(vData.getObservations());
		wslvData.setOptionalInfo(vData.getOptionalInfo());
	    
		wslvData.setCost(vData.getCost());
		wslvData.setOdometer(vData.getOdometer());
		wslvData.setAssignedVehicle(getValueDType(vData.getAssignedVehicle()));	    
		wslvData.setComment(vData.getComment());
		
		wslvData.setId(vData.getId());
		wslvData.setExclusionDate(vData.getExclusionDate());
		wslvData.setYear(vData.getYear());
		wslvData.setMatricula(vData.getMatricula());
		
		return wslvData;
	}
	
	public synchronized static WSLDivOrgData t(DivOrgData divOrgData){
		WSLDivOrgData result=new WSLDivOrgData();
		ArrayList<String> aux=new ArrayList<String>();
		ArrayList<Integer> auxIds=new ArrayList<Integer>();
		Iterator it=divOrgData.getDivOrgLevels().getItemIterator();
		while(it.hasNext()){
            ItemData level1=(ItemData)it.next();
            aux.add(level1.getName());
            auxIds.add(level1.getId());
        }
		result.setLabels(aux.toArray(new String[]{}));
		result.setIds(auxIds.toArray(new Integer[]{}));
		
		return result;
	}

	public synchronized static WSLVehicleData t(VehicleMainAttributes vData){
		WSLVehicleData wslvData=new WSLVehicleData();
		
		wslvData.setId(vData.getId());
		wslvData.setExclusionDate(vData.getExclusionDate());
		wslvData.setYear(vData.getYear());
		wslvData.setMatricula(vData.getMatricula());
		
		return wslvData;
	}
	
	public synchronized static WSLVehicleData[] t(VehicleData[] vData){
		WSLVehicleData[] s=new WSLVehicleData[vData.length];
		for(int i=0; i<vData.length;i++){
			s[i]=t(vData[i]);
		}
		return s;
	}
	
	private static String getValueDType(GeneralDTData dt) {
		if(dt!=null){
			return dt.getResourceBundleId();
		}else{
			return "value.nodata";
		}
	}

	public synchronized static  WSLDriverStatusData t(DriverStatusData next, Text text) {
		// TODO Auto-generated method stub
		WSLDriverStatusData status=new WSLDriverStatusData();
		status.setPointAccount(next.getPointAccount());
		status.setDriver(t(next.getDriver()));
		return status;
	}


}
