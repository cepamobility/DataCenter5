package business.alert.handler;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
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

public class CepaTipsAlertHandler extends AlertHandler {

	public CepaTipsAlertHandler() {
	}

	public CepaTipsAlertHandler(int alertId) {
		super(alertId);
	}

	public CepaTipsAlertHandler(String type, int alertId) {
		super(type, alertId);
	}
	
	public CepaTipsAlertHandler(AlertData alertData){
		super(alertData.getAlertType().getResourceBundleId(),alertData.getAlertId().intValue(),
				alertData.getCorporation());

		Collection persons = this.getMessagesToSend(alertData);
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
	private Collection<Message> getMessagesToSend(AlertData alertData) {
	
		String body = alertData.getBody();
		String subject = alertData.getSubject();
		// Integer corporationId = alertData.getCorporation().intValue();
		// int fleetId = alertData.getFleetId().intValue();
		
		Collection<Message> messages = null;
		LinkedList<PersonData> drivers;
		String attachment = null;
		String fullPathAttachment = null;
				
		try{	
					
			drivers = getDriversMailsToSend(alertData); // 1500 conductores , mandar 30 emails de 50 bcc
			
			if (drivers != null){
				
				if (!Util.empty(alertData.getAttachment())){
					// Si el attachment no es vacio, asigno el nombre del archivo adjunto
					// y el full path para leerlo.
					attachment = alertData.getAttachment();
					fullPathAttachment = AlertConstants.TEMP_DIRECTORY_ALERT_BAYER +
						"/" + attachment;
				}
				
				messages = new LinkedList();
				Iterator<PersonData> it = drivers.iterator();
				int count = 1;
				Collection<String> to = new LinkedList<String>(); // to vacio, pongo todo en bcc
				Collection<String> bcc = new LinkedList<String>(); 
				Collection<PersonData> persons = new LinkedList<PersonData>();
				PersonData person = null;
				Message message = null;
				
				while(it.hasNext()){
					person = (PersonData)it.next();		
					
					System.out.println("Person: "+person.getFirstName()+" "+person.getLastName()+
							" email: "+person.getEmail());
					
					if (person != null && person.getEmail() != null && 
							!person.getEmail().trim().equalsIgnoreCase("") &&
							person.getEmail().contains("@")){							
							bcc.add(person.getEmail());
							persons.add(person);							
					}
					count ++; // cuento agregados y no agregados para llegar al drivers.size().
					
					// El mensaje es el mismo para todos, por lo que acumulo de a 50 conductores
					// y lo mando en bcc
					if (count >= 20 || count >= drivers.size() ){
						if (bcc.size() > 0){
							if (idCorporation == CorporationData.BAYER){
								// Bayer BR
								body = body.replace("\r\n","<br/>");
								body = body.replace("\n","<br/>");
							
								message = new Message(
									AlertConstants.FROM_BAYER_BR, // From
									AlertConstants.FROMNAME_BAYER_BR, // From Name
									AlertConstants.FROMPASSWORD_BAYER_BR, // From password
									to, // To
									subject, // Subject
									getCompleteHTMLMailPT(
											body,AlertConstants.DIGITAL_SIGNATURE_BAYER_BR,
											AlertConstants.FROM_BAYER_BR), // Body
									fullPathAttachment, // atachement full path
									attachment  // atachment name
								);
								bcc.add(AlertConstants.EMAIL_JUAN);
								message.setBcc(bcc);								
								message.setPersons(persons);
							
								if (message != null){
									messages.add(message);
								}
							}							
						}
						count = 1;
						bcc = new LinkedList(); 
						persons = new LinkedList<PersonData>();
						
					} // fin if count >= 50					
				}
			}
			return messages;
		
		}catch(Exception e){
			e.printStackTrace();
			
		}		
		return null;
	}
	
	
	/**
	 * Completa email con el footer de CEPA Brasil con signature e email especificos.
	 * Contiene logo CEPA y logo de no imprimir a no ser que sea necesario.
	 * 
	 * @param text
	 * @param signature
	 * @param email
	 * @return
	 */
	public String getCompleteHTMLMailPT(String text,String signature,String email){
		/* Telefono de Brasil */
		String phone = "+55(12) 2139 -1955";
		
		String mail =			
			"<p class=MsoNormal style='mso-margin-top-alt:auto;mso-margin-bottom-alt:auto'>"+
        		"<span style='font-family:Arial;'> "+
        		text +
        		"</span> "+
        	"</p> "+
        	"<br/><br/>"+	
			"<table width='500' border='0' cellpadding='0' cellspacing='0'>"+
				"<tr>"+
			    	"<td width='105' valign='top' style='border-right:solid; color:#7F7F7F; border-width:thin'>"+
			    		"<img src='http://www.cepasafedrive.com/images/logo_firma.jpg' width='96' height='28' />"+
			    	"</td>"+
			    	"<td valign='top'>"+
			    		"<table width='395' border='0' cellpadding='0' cellspacing='0'>"+
			    			"<tr>"+
			    				"<td width='2'>&nbsp;</td>"+
			    				"<td valign='top'>"+
			    					"<span style='font-family:&quot;Arial Narrow&quot;; font-size:12pt'><strong>"+
			    					signature +
			    					"</strong></span>"+
			    					"<p style='margin-top: 4px'>"+
			    					"<span style='font-family:Arial Narrow; font-size:12pt;'><strong>"+
			    					"CEPA Brasil"+
			    					"</strong></span> <br />"+
			    					"<span style='font-family:Arial Narrow; font-size:10pt; color:#7F7F7F'>"+
			    					"Phone: "+phone+" / <a href='mailto:"+email+"'>"+email+"</a><br />"+					
			    					"<a href='http://www.cepasafedrive.com'>www.cepasafedrive.com</a> </span></p>"+
			    				"</td>"+
			    			"</tr>"+
			    		"</table>"+
				"</td>"+
			  "</tr>"+
			  "<tr>"+
			  	"<td colspan='2'>"+
			  		"<p align='left'><img src='http://www.cepasafedrive.com/images/icono_firma.jpg' "+ 
					" width='22' height='22'/>&nbsp; "+
					"<span style='font-size:10.0pt;font-family: 'Arial Narrow','sans-serif';color:#7F7F7F'>"+
							"Por favor, considere o meio ambiente antes de imprimir este correio eletrônico."+
						"</span>"+
					"</p>"+	
				"</td>"+
			  "</tr>"+
			"</table>"+
			"<hr width='85%' style='color:#7F7F7F;' />"+
			"<span style='font-size:8.0pt; font-family: 'Arial Narrow','sans-serif';color: #7F7F7F'>"+
			"<div align='center'>"+
			"*Confidentiality Notice*<br />"+
			"The information contained in this e-mail and any accompanying documents is confidential, "+
			"may be privileged. It is intended solely for the person and/or entity to whom it is addressed "+
			"(i.e. those identified in the 'To' and 'cc' box). The message and any attachments are the "+
			"property of CEPA International LLC, its group companies, or clients. Unauthorized review, "+
			"use, disclosure, or copying of this communication, or any part thereof, is strictly prohibited "+
			"and may be unlawful. If you have received this e-mail in error, please return it and any "+
			"attachment to the sender and delete the e-mail and attachments and any copy from your"+
			" system. CEPA International thanks you for your cooperation."+ 
			"</div>"+
		"</span>"	;
		return mail;
	}
	
	
	/** 
	 * 
	 * @return
	 * @throws Exception
	 */
	private LinkedList<PersonData> getDriversMailsToSend(AlertData alertData) throws Exception{
		
		LinkedList<PersonData> list = null;
		Integer corporationId = alertData.getCorporation().intValue();
		// int fleetId = alertData.getFleetId().intValue();
		// String fleetList = alertData.getFleetList();
		String fileOut = null;
		
		try{
			
			Calendar c = Calendar.getInstance();
			// String year = String.valueOf(c.get(Calendar.YEAR));
			// String period = String.valueOf(c.get(Calendar.MONTH) + 1); // enero = 0
			// 	int status = VehicleData.ACTIVE;
			
			AlertPersistence persistence = new AlertPersistence();
			
			if (corporationId.intValue() == CorporationData.BAYER) {
				// Alertas de Bayer
				
				list = persistence.getBayerActiveDrivers();
			
				if (alertData.getTo() != null && alertData.getTo().size() > 0 ){
						/* Otras personas que reciben el CEPA Tips 	 */
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
				
			}
			
			
			/* Log de Alertas */
			if (list != null && list.size() > 0){
				System.out.println("Cantidad de emails a enviar: "+list.size());
				
				Iterator <PersonData> it2 = list.iterator();
				fileOut = "\n\nALERT: " + alertData.getDescription() + "\n";
				fileOut +="\nCantidad de emails a enviar: "+list.size() + "\n";
				c = Calendar.getInstance();
				fileOut += c.get(Calendar.DATE)+"-"+c.get(Calendar.MONTH)+"-"+c.get(Calendar.YEAR)+"\n\n";
				while (it2.hasNext()){
					PersonData p = it2.next();
					String name = (p.getFirstName() != null && p.getLastName() != null)?
							p.getFirstName() + " " + p.getLastName():"---";
					String link = (p.getLink() != null)?" Link: "+p.getLink(): "";
					fileOut += "Name: " + name + " Email: " + p.getEmail() + link + "\n";
				}
				
				// Nombre del archivo de log segun alerta:
				String fileName = null;
				switch (alertData.getCorporation().intValue()){
					case CorporationData.BAYER:
							fileName = AlertConstants.TEMP_DIRECTORY_ALERT_BAYER+"/bayer_log.txt";
							break;
					default:
						fileName = AlertConstants.TEMP_DIRECTORY_ALERT_GENERIC;
				}				
				
				if (fileName != null){
					File f = new File(fileName);
					FileOutputStream fos = new FileOutputStream(f);
					fos.write(fileOut.getBytes());
					fos.flush();
					fos.close();
				}
				/* Fin Log de Alertas */
			}
			
			return list;
			
		}catch(Exception e){
			e.printStackTrace();
			
		}		
		return list;
	}

}
