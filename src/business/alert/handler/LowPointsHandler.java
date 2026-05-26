package business.alert.handler;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import persistence.alert.AlertPersistence;

import communication.alert.AlertConstants;
import communication.alert.AlertData;
import communication.alert.Message;
import communication.alert.PersonData;

/**
 * Alerta que avisa a los drivers de CIMSA que tienen menos de X puntos.
 * Donde X en ppio es 10.
 *  
 * @author gbenaderet
 *
 */
public class LowPointsHandler extends AlertHandler {

	
	/**
	 * 
	 * @param alertId
	 * @param alertType
	 * @param body
	 * @param subject
	 * @param corporationId
	 * @param sys
	 */
	public LowPointsHandler(AlertData alertData, int maxPoints) {
		super(alertData.getAlertType().getResourceBundleId(),alertData.getAlertId().intValue());
		Collection persons = this.getMessagesToSend(alertData.getBody(),alertData.getSubject(),
				maxPoints, alertData.getCorporation());
		this.constructPersonsMessages(persons);		
	}

	/**
	 * 
	 * @param body
	 * @param subject
	 * @param corporationId
	 * @param sys
	 * @return
	 */
	private Collection getMessagesToSend(String body, String subject, int maxPoints, Integer corporationId) {
	
		
		Collection messages = new LinkedList();
		try{	
				
			LinkedList<PersonData> persons = getDriversMailsToSend(maxPoints,corporationId);
			
			if (persons != null){
				
				// String subject = "";
				
				String attachment = null;
				
				
				Iterator it = persons.iterator();
				
				while(it.hasNext()){
					
					Collection to = new LinkedList();
					PersonData person = (PersonData)it.next();
					
					to.add(person.getEmail());
					to.add(AlertConstants.BCC);
					
					messages.add(
						new Message(
							AlertConstants.FROM_DC, // From
							AlertConstants.FROMNAME_DC, // From Name
							AlertConstants.FROMPASSWORD_DC, // From password
							to, // To
							subject, // Subject
							Util.getCompleteHTMLMail(body,AlertConstants.DIGITAL_SIGNATURE_DC, AlertConstants.FROM_DC), // Body
							attachment, // atachement
							attachment  // atachment name
						) 
					);
				}
			}
			return messages;
		
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		return null;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	private LinkedList<PersonData> getDriversMailsToSend(int maxPoints, Integer corporationId)throws Exception{
		
		LinkedList<PersonData> list = null;
		try{
			
			AlertPersistence persistence = new AlertPersistence();
			list = persistence.getDriversWithPointsLessOrEquals(maxPoints, corporationId);
			return list;
			
		}catch(Exception e){
			e.printStackTrace();
			
		}		
		return list;
	}
	
	
}
