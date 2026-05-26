package business.alert.handler;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import persistence.alert.AlertPersistence;

import communication.administration.corporation.tables.CorporationData;
import communication.alert.AlertConstants;
import communication.alert.AlertData;
import communication.alert.AlertToData;
import communication.alert.Message;
import communication.alert.PersonData;

public class LicenseExpiryHandler extends AlertHandler  {

	
	/**
	 * 
	 * @param alertId
	 * @param alertType
	 * @param body
	 * @param subject
	 * @param corporationId
	 * @param sys
	 */
	public LicenseExpiryHandler(AlertData alertData,int daysLeft, boolean onlyEquals, Integer countryId) {
		super(alertData.getAlertType().getResourceBundleId(),alertData.getAlertId().intValue());
		this.idCorporation = alertData.getCorporation();
		
		Collection persons = this.getMessagesToSend(alertData,daysLeft,onlyEquals, countryId);
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
	private Collection getMessagesToSend(AlertData alertData,
			int daysLeft, boolean onlyEquals, Integer countryId) {
	
		
		Collection messages = new LinkedList();
		String body = alertData.getBody();
		String subject = alertData.getSubject();
		Integer corporationId = alertData.getCorporation().intValue();
		// int fleetId = alertData.getFleetId().intValue();
		
		try{	
				
			LinkedList<PersonData> persons = getDriversMailsToSend(alertData, daysLeft,onlyEquals,countryId);
			
			if (persons != null){
				
				// String subject = "";
				
				String attachment = null;				
				
				Iterator it = persons.iterator();
				
				while(it.hasNext()){
					
					Collection to = new LinkedList();
					PersonData person = (PersonData)it.next();
					
					System.out.println("Person: "+person.getFirstName()+" "+person.getLastName()+
							" email: "+person.getEmail());
					
					// TODO: Temporalmente las alertas de BAYER solo van a Gaby Marini: 
					if (corporationId == CorporationData.BAYER){
						to.add(AlertConstants.EMAIL_GABI_MARINI);
						
					}else{
						if (person != null && person.getEmail() != null && 
							!person.getEmail().trim().equalsIgnoreCase("") &&
							person.getEmail().contains("@")){
							
							to.add(person.getEmail());
						}
					}
					
					
	
					// TODO: Temporalmente las alertas de BAYER solo van a Gaby Marini
//					if (corporationId == CorporationData.BAYER &&
//							(this.alertId == AlertData.BAYER_LICENSE_EXPIRY_30DAYS ||
//							this.alertId == AlertData.BAYER_LICENSE_EXPIRY_1DAY_PASS)
//							){
//						// si es Bayer y tipo de mail: 30 dias o 1 dia pasado
//						// agrego el email del supervisor del conductor:
//						if (person.getSupervisorEmail() != null &&
//							person.getSupervisorEmail().trim() != ""){
//							
//							to.add(person.getSupervisorEmail());
//							System.out.println("Supervisor: "+person.getSupervisorName()+
//									" email: "+person.getSupervisorEmail());
//						}						
//					}
					
					// Agrego el email de los Respaldos de Alertas
					// to.add(AlertConstants.BCC);
					
					/* 
					 * Le agrego al body la fecha de vencimiento de la licencia:
					 * reemplazando el patrón: "<date>" por la fecha real. 
					 */
					// String DATE_FORMAT = "dd-MM-yyyy";
					//java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
					// body = body.replace("<date>", sdf.format(person.getLicenseExpiry()));
					// System.out.println("LicenseExpiry:"+sdf.format(person.getLicenseExpiry()));
					
					
					if (to.size() > 0){
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
	private LinkedList<PersonData> getDriversMailsToSend(
			AlertData alertData, int daysLeft, boolean onlyEquals, Integer countryId)
			throws Exception{
		
		LinkedList<PersonData> list = null;
		Integer corporationId = alertData.getCorporation().intValue();
		try{
			
			AlertPersistence persistence = new AlertPersistence();
			list = persistence.getLicenseExpiryDrivers(corporationId, daysLeft, onlyEquals,countryId);
			
			if (alertData.getTo() != null && alertData.getTo().size() > 0){
				/* Mandamos al los AlertTO, indistintamente si mandamos a los drivers */
				
				if (list == null) list = new LinkedList<PersonData>();
										 
				Iterator it = alertData.getTo().iterator();
				while (it.hasNext()){
					AlertToData alertTo = (AlertToData)it.next();
					if (alertTo.getCorporation().equals(corporationId.intValue())){
						PersonData p = new PersonData(null,null,alertTo.getEmail());
						p.setIdDriver(null);
						p.setIdCorporation(corporationId);
						list.add(p);
					}
				}
			}	
			
			return list;
			
		}catch(Exception e){
			e.printStackTrace();
			
		}		
		return list;
	}
	
	
	
	
}
