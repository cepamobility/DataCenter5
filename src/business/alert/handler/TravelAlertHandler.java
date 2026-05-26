package business.alert.handler;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import persistence.alert.AlertPersistence;

import communication.alert.AlertConstants;
import communication.alert.AlertData;
import communication.alert.Message;
import communication.alert.TravelAlertData;

/**
 * Alerta que avisa de los viajes (de ANGLO u otra corporacion) para hoy a un email determinado.
 *  
 * @author gbenaderet
 *
 */
public class TravelAlertHandler extends AlertHandler {

	
	/**
	 * 
	 * @param alertId
	 * @param alertType
	 * @param body
	 * @param subject
	 * @param corporationId
	 * @param sys
	 */
	public TravelAlertHandler(AlertData alertData) {
		
		super(alertData.getAlertType().getResourceBundleId(),alertData.getAlertId());
		
		Collection persons = this.getMessagesToSend(alertData.getBody(),alertData.getSubject(),
				alertData.getCorporation(), alertData.getTargets());
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
	private Collection getMessagesToSend(String body, String subject, Integer corporationId, Collection toEmail) {
	
		
		Collection messages = new LinkedList();
		try{	
				
			LinkedList<TravelAlertData> travels = getTravelsForToday(corporationId);
			
			
			if (travels != null){
				
				// String subject = "";
				
				String attachment = null;
				Collection to = new LinkedList();
				
				// Agrego al "to" todos los emails que correspondan:
				if (toEmail != null && toEmail.size() > 0){
					to.addAll(toEmail);
				}else{
					System.out.println("ToEmails null o vacio.");
				}
				
				body = createPortugueseBody(travels); 
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
			return messages;
		
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		return null;
	}
	
	/**
	 * Devuelve los Travels para el dia de hoy para la corporacion corporationId
	 * @return
	 * @throws Exception
	 */
	private LinkedList<TravelAlertData> getTravelsForToday(Integer corporationId) throws Exception{
		
		LinkedList<TravelAlertData> list = null;
		try{
			
			AlertPersistence persistence = new AlertPersistence();
			list = persistence.getTravelsForToday(corporationId);
			return list;
			
		}catch(Exception e){
			e.printStackTrace();
			
		}		
		return list;
	}
	
	private String createPortugueseBody(LinkedList<TravelAlertData> travels){
		
		String body = "";
		if (travels != null && travels.size() >= 1){
			body = 
				"Bom dia<br/><br/>"+
				"Abaixo est&aacute; uma lista de viagem para hoje:<br/><br/>"+
				"<table border='1'>"+				
					"<tr>" +
						"<td><strong>Motorista</strong></td>"+
						"<td><strong>Data de sa&iacute;da</strong></td>"+
						"<td><strong>Hora de Partida</strong></td>"+
						"<td><strong>Estado</strong></td>"+
					"</tr>";
			
			Iterator<TravelAlertData> it = travels.iterator();
			Format dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			// API de SimpleDateFormat:
			// http://download.oracle.com/javase/1.4.2/docs/api/java/text/SimpleDateFormat.html			
			Format hourFormat = new SimpleDateFormat("HH:mm");
			while (it.hasNext()){
				TravelAlertData travel = it.next();
				body += 
					"<tr>"+
						"<td>"+travel.getFirstName()+" "+travel.getLastName()+"</td>"+
						"<td>"+dateFormat.format(travel.getDateleave())+"</td>"+
						"<td>"+hourFormat.format(travel.getHourleave())+"</td>"+
						"<td>"+travel.getStateText("pt")+"</td>"+
					"</tr>";
			}
			
			body += "</table><br/><br/>Tenha um bom dia.<br/><br/>";
			
		}else{
			body = 
				"Bom dia<br/><br/>"+
				"Nenhum viagens para este dia. <br/><br/>"+
				"Tenha um bom dia.<br/><br/>";			
		}
		return body;
		
	}
	
}
