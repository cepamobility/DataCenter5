/**
 * Created on 16-ago-2006
 * CEPA
 * DataCenter 5
 */
package presentation.translator.web.event;

import java.rmi.RemoteException;

import business.SystemAccess;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.CountryData;
import communication.administration.user.UserSessionData;
import communication.datatype.tables.AccidentClasificationDataType;
import communication.datatype.tables.IncidentClasificationDataType;
import communication.driver.tables.DriverData;
import communication.event.tables.EventDataType;

public class NewEventSuperviseForm extends NewEventForm {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String recomendationCheck;
    private String emailrecomendation;
        
    public NewEventSuperviseForm() {
        super();
    }

    public NewEventSuperviseForm(DriverData driver) {
        super(driver);
    }

    public NewEventSuperviseForm(EventDataType event,UserSessionData userSessionData) {
        super(event,userSessionData);
    }

    public String getEmailrecomendation() {
        return emailrecomendation;
    }

    public String getRecomendationCheck() {
        return recomendationCheck;
    }
    
    public void setEmailrecomendation(String emailrecomendation) {
        this.emailrecomendation = emailrecomendation;
    }

    public void setRecomendationCheck(String recomendationCheck) {
        this.recomendationCheck = recomendationCheck;
    }
    
    public EventDataType getEventDataType(SystemAccess sys) throws RemoteException {
        EventDataType event = super.getEventDataType(sys);
        event.setEvitability(evitability);
            
        return event;
    }

}
