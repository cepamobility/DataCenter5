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
import communication.vehicle.tables.VehicleData;

public class RememberAlertHandler extends AlertHandler {
	
	

	/**
	 * 
	 * @param alertId
	 * @param alertType
	 * @param to
	 * @param body
	 * @param subject
	 * @param type
	 */
	public RememberAlertHandler(AlertData alertData) {
		

		super(alertData.getAlertType().getResourceBundleId(),alertData.getAlertId().intValue());

		int corporationId = alertData.getCorporation().intValue();
		Integer fleetId = alertData.getFleetId();
		
		if(corporationId == CorporationData.MONSANTO && fleetId != null ){
			
			// MONSANTO
			
			if  (fleetId.intValue() == AlertConstants.FLEET_MONSANTO_UY){
				/*
				 * Recordatorio Monsanto Uruguay (Activo a Agosto 2011)
				 * 
				 * Manda recordatorio a aquellos usuarios activos 
				 * que no ingresaron sus odometros para el mes que finaliza.
				 */		
				System.out.println("ALERTA: Monsanto UY, Uruguay");
				Integer [] divGeos = new Integer[1];
				divGeos[0] = 32; // Uruguay
				// Flota: Monsanto Uruguay
				
				Collection drivers = getMessagesToSend(alertData, divGeos);
				constructPersonsMessages(drivers);
			} else if  (fleetId.intValue() == AlertConstants.FLEET_MONSANTO_PY){
				/*
				 * Recordatorio Monsanto Paraguay (Activo a Agosto 2011)
				 * 
				 * Manda recordatorio a aquellos usuarios activos 
				 * que no ingresaron sus odometros para el mes que finaliza.
				 */		
				System.out.println("ALERTA: Monsanto PY, Paraguay");
				Integer [] divGeos = new Integer[1];
				divGeos[0] = 34; // Paraguay
				// Flota: Monsanto Paraguay
				
				Collection drivers = getMessagesToSend(alertData, divGeos);
				constructPersonsMessages(drivers);
				
				
			}else if(fleetId.intValue() == AlertConstants.FLEET_MONSANTO_AR){
				
				/*
				 * Recordatorio Monsanto Argentina (Activo a Junio 2010)
				 * 
				 * Manda recordatorio a aquellos usuarios activos 
				 * que no ingresaron sus odometros para el mes que finaliza.
				 */		
				
				System.out.println("ALERTA: Monsanto AR");
				Integer [] divGeos = new Integer[1];
				divGeos[0] = 33; // Argentina
				// Flota: Monsanto Argentina		
				
				Collection drivers = getMessagesToSend(alertData, divGeos);
				constructPersonsMessages(drivers);
			
			}else if (fleetId.intValue() == AlertConstants.FLEET_MONSANTO_BR){
				
				/*
				 * Recordatorio Monsanto Brasil (Activo a Junio 2010)
				 * 
				 * Manda recordatorio con link personal 
				 * a todos los usuarios de la flota
				 * cuya clave sea una doble encriptacion del login.
				 * 
				 */	
				
				System.out.println("ALERTA: Monsanto BR");
				Integer [] divGeos = new Integer[1];
				divGeos[0] = 32; // Brasil
				
				Collection drivers = getMessagesToSend(alertData, divGeos);
				constructPersonsMessages(drivers);
				
				// FleetReportFacadeBena getFleetDrivers(Integer fleet,UserSessionData userSessionData)
			}
			
		}else if(corporationId == CorporationData.LILLY && fleetId != null ){
				// LILLY
			
				Collection drivers = getMessagesToSend(alertData, null);
				constructPersonsMessages(drivers);
		}
		
			
	}
	
	/** 
	 * Obtiene los mails de los conductores que no ingresaron los odometros.
	 * @return
	 * @throws Exception
	 */
	private LinkedList<PersonData> getFleetDriversMailsToSend(AlertData alertData, 
			Integer[] divGeos) throws Exception{
		
		LinkedList<PersonData> list = null;
		Integer corporationId = alertData.getCorporation().intValue();
		int fleetId = alertData.getFleetId().intValue();
		String fleetList = alertData.getFleetList();
		String fileOut = null;
		
		try{
			
			Calendar c = Calendar.getInstance();
			String year = String.valueOf(c.get(Calendar.YEAR));
			String period = String.valueOf(c.get(Calendar.MONTH) + 1); // enero = 0
			
			int status = VehicleData.ACTIVE;
			
			AlertPersistence persistence = new AlertPersistence();
			
			if (corporationId.intValue() == CorporationData.MONSANTO) {
				// Alertas de Monsanto:
				
				if (fleetId == AlertConstants.FLEET_MONSANTO_BR
						|| fleetId == AlertConstants.FLEET_MONSANTO_AR
						|| fleetId == AlertConstants.FLEET_MONSANTO_PY
						|| fleetId == AlertConstants.FLEET_MONSANTO_UY){
					// Monsanto:
					list = persistence.getMonsantoFleetDrivers(fleetId,period, year);
				}
			
			}else if (corporationId.intValue() == CorporationData.LILLY){
					// Lilly
					// 	odometro del mes anterior al actual, por eso no va +1 en este caso
					period =  String.valueOf(c.get(Calendar.MONTH)); 
					list = persistence.getLillyActiveDrivers(period, year, status, fleetId,fleetList);
				
					if (alertData.getTo() != null && alertData.getTo().size() > 0 &&
							list != null && list.size() > 0){
						/* Coordinadores que tambien reciben la alerta:
						 * Solo mandamos a los coordinadores si hay algun conductor
						 * en la lista para mandar, sino no. 
						 */
												 
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
			
			
			// Log de Alertas:
			if (list != null && list.size() > 0){
				Iterator <PersonData> it2 = list.iterator();
				fileOut = "\n\nALERT: " + alertData.getDescription() + "\n";
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
				switch (alertData.getAlertId()){
					case AlertConstants.MONSANTO_BRASIL_REMEMBER_ALERT:
							fileName = AlertConstants.TEMP_DIRECTORY_ALERT_MONSANTO_BRASIL;
							break;
					case AlertConstants.MONSANTO_ARGENTINA_REMEMBER_ALERT:
							fileName = AlertConstants.TEMP_DIRECTORY_ALERT_MONSANTO_ARGENTINA;
							break;
					case AlertConstants.MONSANTO_PARAGUAY_REMEMBER_ALERT:
						fileName = AlertConstants.TEMP_DIRECTORY_ALERT_MONSANTO_PARAGUAY;
						break;
					default:
						fileName = AlertConstants.TEMP_DIRECTORY_ALERT_GENERIC;
				}
				if (alertData.getCorporation().intValue() == CorporationData.LILLY){
					fileName = AlertConstants.TEMP_DIRECTORY_ALERT_LILLY;
				}
				
				if (fileName != null){
					File f = new File(fileName);
					FileOutputStream fos = new FileOutputStream(f);
					fos.write(fileOut.getBytes());
					fos.flush();
					fos.close();
				}
			}
			
			return list;
			
		}catch(Exception e){
			e.printStackTrace();
			
		}		
		return list;
	}
	
	
	/** 
	 * @param body
	 * @param subject
	 * @param corporationId
	 * @param sys
	 * @return
	 */
	private Collection getMessagesToSend(AlertData alertData, Integer[] divGeos) {
	
		String body = alertData.getBody();
		String subject = alertData.getSubject();
		Integer corporationId = alertData.getCorporation().intValue();
		int fleetId = alertData.getFleetId().intValue();
		
		Collection messages = null;
		try{	
				
			LinkedList<PersonData> drivers = getFleetDriversMailsToSend(alertData, divGeos);
			
			if (drivers != null){
				
				System.out.println("Cantidad de Emails a enviar:"+drivers.size());

				
				Iterator it = drivers.iterator();
				messages = new LinkedList();

				while(it.hasNext()){					
					Collection to = new LinkedList();
					PersonData person = (PersonData)it.next();
					Collection<PersonData> persons = new LinkedList<PersonData>();
					Message message = null;
					
					
					if ( !person.getEmail().trim().equalsIgnoreCase("") &&
							person.getEmail().contains("@")){

						// Solo agrego e email si no es vacio y tiene algun @
						
						to.add(person.getEmail());	
						persons.add(person);
						
						if (corporationId.intValue() == CorporationData.MONSANTO){
						// Monsanto
							
							if (fleetId == AlertConstants.FLEET_MONSANTO_PY
									|| fleetId == AlertConstants.FLEET_MONSANTO_UY){
								
								// Mensaje para Monsanto Paraguay:
								String attachment = alertData.getAttachment();
								String fullPathAttachment = AlertConstants.TEMP_DIRECTORY_ALERT_MONSANTO +
									"/" + attachment;
								message = new Message(
											AlertConstants.FROM_MONSANTO_PY,
											AlertConstants.FROMNAME_MONSANTO_AR,
											AlertConstants.FROMPASSWORD_MONSANTO_PY,
											to,
											subject,
											Util.getCompleteHTMLMail(
													replaceMonsantoTexts(body, fleetId, person.getLogin()),
													AlertConstants.DIGITAL_SIGNATURE_MONSANTO_PY,
													AlertConstants.FROM_MONSANTO_PY
											),fullPathAttachment,attachment
									);
								message.setReplyTo(AlertConstants.FROM_MONSANTO_PY);	
								message.setPersons(persons);
								
							}else if (fleetId == AlertConstants.FLEET_MONSANTO_AR){
								
								// Mensaje para Monsanto Paraguay:
								String attachment = alertData.getAttachment();
								String fullPathAttachment = AlertConstants.TEMP_DIRECTORY_ALERT_MONSANTO +
								"/" + attachment;

								// Mensaje para Monsanto Argentina:
								message = new Message(
											AlertConstants.FROM_MONSANTO_AR,
											AlertConstants.FROMNAME_MONSANTO_AR,
											AlertConstants.FROMPASSWORD_MONSANTO_AR,
											to,
											subject,
											Util.getCompleteHTMLMail(
													replaceMonsantoTexts(body, fleetId, person.getLogin()),
													AlertConstants.DIGITAL_SIGNATURE_MONSANTO_AR,
													AlertConstants.FROM_MONSANTO_AR
											),fullPathAttachment,attachment
									);
								message.setReplyTo(AlertConstants.FROM_MONSANTO_AR);	
								message.setPersons(persons);
								
							}else if (fleetId == AlertConstants.FLEET_MONSANTO_BR){
								
								// Mensaje para Monsanto Brasil:
								message = new Message(
										AlertConstants.FROM_MONSANTO_BR,
										AlertConstants.FROMNAME_MONSANTO_BR,
										AlertConstants.FROMPASSWORD_MONSANTO_BR,
										to,
										subject,
										getMonsantoBrasilOdometersMail(person.getLogin(),body)								
								);
								message.setReplyTo(AlertConstants.FROM_MONSANTO_BR);
								message.setPersons(persons);
								// System.out.println("Login del usuario de Monsanto BR: "+person.getLogin());
							}
						
							// fin if monsanto
						}else if (corporationId.intValue() == CorporationData.LILLY){
						// Lilly		
								message = new Message(
											AlertConstants.FROM_LILLY,
											AlertConstants.FROMNAME_LILLY,
											AlertConstants.FROMPASSWORD_LILLY,
											to,
											subject,
											Util.getCompleteHTMLMail(
													replaceLillyTexts(body, fleetId, person.getLogin()),
													AlertConstants.DIGITAL_SIGNATURE_LILLY,
													AlertConstants.FROM_LILLY
											)
									);
								message.setReplyTo(AlertConstants.FROM_LILLY);	
								message.setPersons(persons);
						}
						
					} // fin if valid email
										
					
					if (message != null){
						messages.add(message);
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
	 * Reemplazo los textos del Body del mensaje de Lilly:
	 * MESACTUAL por el mes en el idioma correspondiente
	 * MESANTERIOR por el mes en el idioma correspondiente
	 * ANIOACTUAL por el anio actual
	 * 
	 * @param body
	 * @param fleetId
	 * @return
	 */
	private String replaceLillyTexts(String body, int fleetId, String login){
		String language = "es";
		if (fleetId == AlertConstants.FLEET_LILLY_ENGLISH) language= "en";
		
		Calendar c = Calendar.getInstance();
		int month = c.get(Calendar.MONTH);
		int year = c.get(Calendar.YEAR);
		body = body.replace("MESACTUAL", intMonthToString(month,language));
		month--;
		body = body.replace("MESANTERIOR", intMonthToString(month,language));
		body = body.replace("ANIOACTUAL", (new Integer(year)).toString());
		String link = "https://"+AlertConstants.DC_DOMAIN+"/datacenter/home.jsp?login="+login;
		body = body.replace("_link_", "<a href='"+link+"'>"+link+"</a>");
		return body;
	}
	
	private String replaceMonsantoTexts(String body, int fleetId, String login){
		String link = "https://"+AlertConstants.DC_DOMAIN+"/datacenter/home.jsp?login="+login;
		body = body.replace("_link_", "<a href='"+link+"'>"+link+"</a>");
		return body;
	}

	/**
	 * Convierte el mes del 0 al 11 a su respectivo texto en el language indicado.
	 * 
	 * @param month
	 * @param language
	 * @return
	 */
	private String intMonthToString(int month, String language){
		String actualMonth = "";
		if (language.equalsIgnoreCase("en")){
			switch (month){
				case 0: actualMonth="January"; break;
				case 1: actualMonth="February"; break;
				case 2: actualMonth="March"; break;
				case 3: actualMonth="April"; break;
				case 4: actualMonth="May"; break;
				case 5: actualMonth="June"; break;
				case 6: actualMonth="July"; break;
				case 7: actualMonth="August"; break;
				case 8: actualMonth="September"; break;
				case 9: actualMonth="October"; break;
				case 10: actualMonth="November"; break;
				case 11: actualMonth="December"; break;
			}
		}else if (language.equalsIgnoreCase("es")){
			switch (month){
				case 0: actualMonth="Enero"; break;
				case 1: actualMonth="Febrero"; break;
				case 2: actualMonth="Marzo"; break;
				case 3: actualMonth="Abril"; break;
				case 4: actualMonth="Mayo"; break;
				case 5: actualMonth="Junio"; break;
				case 6: actualMonth="Julio"; break;
				case 7: actualMonth="Agosto"; break;
				case 8: actualMonth="Septiembre"; break;
				case 9: actualMonth="Octubre"; break;
				case 10: actualMonth="Noviembre"; break;
				case 11: actualMonth="Diciembre"; break;
			}
		}
		return actualMonth;
	}
	
	/**
	 * Body del email a enviar a los conductores de Monsanto Brasil 
	 * para la Alerta del Recordatorio de Odometros 
	 * @param login
	 * @return
	 */
    private String getMonsantoBrasilOdometersMail(String login, String body) {
    	
    	// Para traducir el body a codigo HTML antes de cargarlo a la base de datos ir a :
    	// http://corz.org/public/machine/source/php/text.to.html.converter.php
    	// poner en donde irira el link: _link_ para luego ser reemplazado aca.
    	
    	body = body.replace("_link_", "<a href='https://"+AlertConstants.DC_DOMAIN+"/datacenter/home.jsp?login="+login+"'>aqui</a>");
    	
    	String firma = 
        
        "<br> "+
        "<br> "+
        "<table width='500' border='0' cellpadding='0' cellspacing='0'>" +
        "<tr> "+
        "  <td width='105' valign='top' style='border-right:solid; color:#7F7F7F; border-width:thin'>"+
        "    <img src='http://www.cepasafedrive.com/images/logo_firma.jpg' width='96' height='28' /> "+
      	"  </td>"+
        "  <td valign='top'> "+
      	"	<table width='395' border='0' cellpadding='0' cellspacing='0'> "+
      	"		<tr> "+
      	"			<td width='2'>&nbsp;</td> "+
      	"			<td valign='top'> "+
      	"				<span style='font-family:Arial Narrow; font-size:12pt;'> "+
      	"				<strong> "+
      	"				CEPA Data Center Brasil"+
      	"				</strong> "+
      	"				</span> <br /> "+
      	"				<span style='font-family:Arial Narrow; font-size:10pt; color:#7F7F7F'> "+
      	"				<a href='http://www.cepasafedrive.com'>www.cepasafedrive.com</a> "+
      	"				</span></p> "+
      	"		  </td> "+
      	"		</tr> "+
      	"	</table> "+
      	"</td> " +
        "</tr> "+
        "<tr>"+
        "	<td colspan='2'> "+
      	"	<p align='left'><img src='http://www.cepasafedrive.com/images/icono_firma.jpg' width='22' height='22'/>&nbsp;<span style='font-size:10.0pt;font-family: \"Arial Narrow\",\"sans-serif\";color:#7F7F7F'>Please consider the environment before printing this email.</span></p>"+	
      	"</td>"+
        "</tr>"+
      "</table>"+        
        
        "<table class=MsoNormalTable border=0 cellspacing=0 cellpadding=0 width=823 "+
         "style='width:617.25pt;mso-cellspacing:0cm;mso-padding-alt:0cm 0cm 0cm 0cm'> "+
         "<tr style='mso-yfti-irow:0;mso-yfti-lastrow:yes;mso-yfti-firstrow: yes'> "+
          "<td valign=top style='padding:0cm 0cm 0cm 0cm'> "+
          "<p class=MsoNormal align=center style='mso-margin-top-alt:auto;mso-margin-bottom-alt: "+
          "auto;text-align:center'><span lang=EN-US style='mso-ansi-language:EN-US'>&nbsp;<o:p></o:p></span></p> "+
          "</td> "+
          "<td valign=top style='padding:0cm 0cm 0cm 0cm'> "+
          "<p class=MsoNormal style='mso-margin-top-alt:auto;mso-margin-bottom-alt:auto'><span "+
          "lang=EN-US style='mso-ansi-language:EN-US'>&nbsp;<o:p></o:p></span></p> "+
          "</td> "+
          "<td valign=top style='padding:0cm 0cm 0cm 0cm'> "+
          "<div class=MsoNormal align=center style='text-align:center'><span "+
          "style='font-size:7.5pt'> "+
          "<hr size=2 width='100%' align=center> "+
          "</span></div> "+
          "<div align=center> "+
          "<table class=MsoNormalTable border=0 cellspacing=0 cellpadding=0 width=827 "+
          " style='width:620.25pt;mso-cellspacing:0cm;mso-padding-alt:0cm 0cm 0cm 0cm'> "+
           "<tr style='mso-yfti-irow:0;mso-yfti-lastrow:yes;mso-yfti-firstrow: yes'> "+
           "<td valign=top style='padding:0cm 0cm 0cm 0cm'> "+
            "<p class=MsoNormal align=center style='mso-margin-top-alt:auto;mso-margin-bottom-alt: "+
            "auto;text-align:center'><span lang=EN-GB style='font-size:7.5pt;color:navy; "+
            "mso-ansi-language:EN-GB'>&nbsp; *Confidentiality Notice*<br> "+
            "The information contained in this e-mail and any accompanying documents is "+
            "confidential, may be privileged. It is intended solely for the person "+
            "and/or entity to whom it is addressed (i.e. those identified in the "+
            "&quot;To&quot; and &quot;cc&quot; box). The message and any attachments are "+
            "the property of CEPA International LLC, its group companies, or clients. "+
            "Unauthorized review, use, disclosure, or copying of this communication, or "+
            "any part thereof, is strictly prohibited and may be unlawful. If you have "+
            "received this e-mail in error, please return it and any attachment to the "+
            "sender and delete the e-mail and attachments and any copy from your system. "+
            "</span><span style='font-size:7.5pt;color:navy'>CEPA <span class=SpellE>International</span> "+
            "<span class=SpellE><span class=spelle>thanks</span></span> <span "+
            "class=SpellE><span class=spelle>you</span></span> <span class=SpellE><span "+
            "class=spelle>for</span></span> <span class=SpellE><span class=spelle>your</span></span> "+
            "<span class=SpellE><span class=spelle>cooperation</span></span>. </span></p> "+
            "<p align=center style='text-align:center'>&nbsp;</p> "+
            "</td> "+
           "</tr> "+
          "</table> "+
          "</div> "+
          "<p class=MsoNormal align=center style='text-align:center'><o:p></o:p></p> "+
          "</td> "+
         "</tr> "+
        "</table> ";
    	
    	String mail = body.concat(firma);
		return mail;
    }
		
}
