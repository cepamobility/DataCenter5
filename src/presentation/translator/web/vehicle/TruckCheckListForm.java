package presentation.translator.web.vehicle;

import java.util.Calendar;

import org.apache.struts.action.ActionForm;

import communication.language.Text;
import communication.vehicle.tables.VehicleCheckData;
import communication.vehicle.tables.VehicleData;

/**
 * @author jrodriguez
 */
public class TruckCheckListForm extends ActionForm {

    private static final long serialVersionUID = 1L;
	
    private String id;

    private String name;
    private String day;
    private String month;
    private String year;
    private String licenseNumber;
    private String dayLicenseExpiry;
    private String monthLicenseExpiry;
    private String yearLicenseExpiry;
    private String trade;
    private String model;
    private String plate;
    private String serie;
    private String vehicleKm;
    private String obs;
    
    private String list;

    private String trailerId;

    private String tradeTrailer;
    private String modelTrailer;
    private String plateTrailer;
    private String serieTrailer;

    private String trailerCheck;
    private String vehicleType;
    
    public TruckCheckListForm() {
    }
    
    public TruckCheckListForm(VehicleCheckData vehicleCheck, Text messages) {
        id = String.valueOf(vehicleCheck.getVehicleCheckId());
        name = vehicleCheck.getName();
        Calendar c = Calendar.getInstance();
        c.setTime(vehicleCheck.getDate());
        day = String.valueOf(c.get(Calendar.DATE));
        month = String.valueOf(c.get(Calendar.MONTH)+1);
        year = String.valueOf(c.get(Calendar.YEAR));
        licenseNumber = vehicleCheck.getLicenseNumber();
        if(vehicleCheck.getDateLicenseExpiry() != null) {
            c = Calendar.getInstance();
            c.setTime(vehicleCheck.getDateLicenseExpiry());
            dayLicenseExpiry = String.valueOf(c.get(Calendar.DATE));
            monthLicenseExpiry = String.valueOf(c.get(Calendar.MONTH)+1);
            yearLicenseExpiry = String.valueOf(c.get(Calendar.YEAR));
        }
        trade = messages.getText(vehicleCheck.getVehicle().getTrade().getResourceBundleId());
        if(trade.equals("generic.messages.nodata")) {
            trade = messages.getText(trade);
        }
        model = vehicleCheck.getVehicle().getModel();
        if(model.equals("generic.messages.nodata")) {
            model = messages.getText(model);
        }
        vehicleType = vehicleCheck.getVehicle().getType().getResourceBundleId();
        plate = vehicleCheck.getVehicle().getMatricula();
        serie = vehicleCheck.getSerie();
        vehicleKm = String.valueOf(vehicleCheck.getVehicleKm());
        obs = vehicleCheck.getObs();
        
        /*
        if(vehicleCheck.getTrailerCheckData() != null) {
            trailerCheck = "1";
            trailerId = String.valueOf(vehicleCheck.getTrailerCheckData().getTrailerCheckId());
            tradeTrailer = messages.getText(vehicleCheck.getTrailerCheckData().getTrailer().getTrade().getResourceBundleId());
            if(tradeTrailer.equals("generic.messages.nodata")) {
                tradeTrailer = messages.getText(tradeTrailer);
            }
            modelTrailer = vehicleCheck.getTrailerCheckData().getTrailer().getModel();
            if(modelTrailer.equals("generic.messages.nodata")) {
                modelTrailer = messages.getText(modelTrailer);
            }
            plateTrailer = vehicleCheck.getTrailerCheckData().getTrailer().getPlate();
            serieTrailer = vehicleCheck.getTrailerCheckData().getSerie();
        }else {
            if(vehicleCheck.getVehicle().getTrailer() != null) {
                plateTrailer = vehicleCheck.getVehicle().getTrailer().getPlate();
                modelTrailer = vehicleCheck.getVehicle().getTrailer().getModel();
                if(modelTrailer.equals("generic.messages.nodata")) {
                    modelTrailer = messages.getText(modelTrailer);
                }
                tradeTrailer = vehicleCheck.getVehicle().getTrailer().getTrade().getResourceBundleId();
                if(tradeTrailer.equals("generic.messages.nodata")) {
                    tradeTrailer = messages.getText(tradeTrailer);
                }
            }
        }*/
    }
    
    public TruckCheckListForm(VehicleData vehicle,Text messages) {
        this.model = vehicle.getModel();
        if(model.equals("generic.messages.nodata")) {
            model = messages.getText(model);
        }
        this.trade = vehicle.getTrade().getResourceBundleId();
        if(trade.equals("generic.messages.nodata")) {
            trade = messages.getText(trade);
        }
        this.plate = vehicle.getMatricula();
        vehicleType = vehicle.getType().getResourceBundleId();
        /*
        if(vehicle.getTrailer() != null) {
            plateTrailer = vehicle.getTrailer().getPlate();
            modelTrailer = vehicle.getTrailer().getModel();
            if(modelTrailer.equals("generic.messages.nodata")) {
                modelTrailer = messages.getText(modelTrailer);
            }
            tradeTrailer = vehicle.getTrailer().getTrade().getResourceBundleId();
            if(tradeTrailer.equals("generic.messages.nodata")) {
                tradeTrailer = messages.getText(tradeTrailer);
            }
        }*/
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getDay() {
        return day;
    }
    public String getDayLicenseExpiry() {
        return dayLicenseExpiry;
    }
    public String getLicenseNumber() {
        return licenseNumber;
    }
    public String getModel() {
        return model;
    }
    public String getMonth() {
        return month;
    }
    public String getMonthLicenseExpiry() {
        return monthLicenseExpiry;
    }
    public String getObs() {
        return obs;
    }
    public String getPlate() {
        return plate;
    }
    public String getSerie() {
        return serie;
    }
    public String getTrade() {
        return trade;
    }
    public String getVehicleKm() {
        return vehicleKm;
    }
    public String getYear() {
        return year;
    }
    public String getYearLicenseExpiry() {
        return yearLicenseExpiry;
    }
    public void setDay(String day) {
        this.day = day;
    }
    public void setDayLicenseExpiry(String dayLicenseExpiry) {
        this.dayLicenseExpiry = dayLicenseExpiry;
    }
    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setMonth(String month) {
        this.month = month;
    }
    public void setMonthLicenseExpiry(String monthLicenseExpiry) {
        this.monthLicenseExpiry = monthLicenseExpiry;
    }
    public void setObs(String obs) {
        this.obs = obs;
    }
    public void setPlate(String plate) {
        this.plate = plate;
    }
    public void setSerie(String serie) {
        this.serie = serie;
    }
    public void setTrade(String trade) {
        this.trade = trade;
    }
    public void setVehicleKm(String vehicleKm) {
        this.vehicleKm = vehicleKm;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public void setYearLicenseExpiry(String yearLicenseExpiry) {
        this.yearLicenseExpiry = yearLicenseExpiry;
    }


    public String getList() {
        return list;
    }


    public void setList(String list) {
        this.list = list;
    }

    public String getModelTrailer() {
        return modelTrailer;
    }

    public String getPlateTrailer() {
        return plateTrailer;
    }

    public String getSerieTrailer() {
        return serieTrailer;
    }

    public String getTradeTrailer() {
        return tradeTrailer;
    }

    public void setModelTrailer(String modelTrailer) {
        this.modelTrailer = modelTrailer;
    }

    public void setPlateTrailer(String plateTrailer) {
        this.plateTrailer = plateTrailer;
    }

    public void setSerieTrailer(String serieTrailer) {
        this.serieTrailer = serieTrailer;
    }

    public void setTradeTrailer(String tradeTrailer) {
        this.tradeTrailer = tradeTrailer;
    }

    public String getTrailerCheck() {
        return trailerCheck;
    }

    public void setTrailerCheck(String trailerCheck) {
        this.trailerCheck = trailerCheck;
    }

    public Integer getCheckId() {
        if(id != null && id.length() > 0) {
            return new Integer(id);
        }
        return null;
    }

    public Integer getTrailerCheckId() {
        if(trailerId != null && trailerId.length() > 0) {
            return new Integer(trailerId);
        }
        return null;
    }

    public String getId() {
        return id;
    }

    public String getTrailerId() {
        return trailerId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTrailerId(String trailerId) {
        this.trailerId = trailerId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

}	    
