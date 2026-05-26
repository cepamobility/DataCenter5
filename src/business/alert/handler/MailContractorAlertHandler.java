package business.alert.handler;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.classic.Session;

import persistence.alert.tables.ContractorData;
import persistence.hibernate.HibernateUtil;
import business.alert.access.AlertSystemAccess;
import business.alert.reporthandler.AlertReportHandler;

import communication.administration.corporation.tables.CorporationData;
import communication.alert.AlertConstants;
import communication.alert.AlertData;
import communication.alert.ContractorDataData;
import communication.alert.Message;

public class MailContractorAlertHandler extends AlertHandler {
	
	private Integer idCorporation;
	/**
	 * 
	 * @param alertId
	 * @param alertType
	 * @param body
	 * @param subject
	 * @param corporationId
	 * @param sys
	 */
	public MailContractorAlertHandler(AlertData alertData, AlertSystemAccess sys) {
		
		super(alertData.getAlertType().getResourceBundleId(),alertData.getAlertId().intValue());
		this.idCorporation =	 alertData.getCorporation();
		Collection contractors = null;
		
		if (this.idCorporation.equals(CorporationData.FORESTALORIENTAL)){
			contractors = this.getContractorMessagestoSendFOSAInline(alertData.getBody(),
					alertData.getSubject(),alertData.getCorporation(),sys);
			
		}else if (this.idCorporation.equals(CorporationData.WEYERHAEUSER)){
			contractors = this.getContractorMessagestoSendWEYERHAEUSER(alertData.getBody(),
					alertData.getSubject(),alertData.getCorporation(),sys);
		}
		this.constructPersonsMessages(contractors);
		sys = null;
	}
	
	/**
	 * 
	 * @param body
	 * @param subject
	 * @param corporationId
	 * @param sys
	 * @return
	 */
	private Collection getContractorMessagestoSendWEYERHAEUSER(String body,String subject,
			Integer corporationId, AlertSystemAccess sys){
	
		//Este Método se usa para WEYERHAEUSER
		String directory = AlertConstants.TEMP_DIRECTORY_ALERT+"weyerhaeuser/";
		
		
		Collection messages = new LinkedList();
		try{	
				
			Collection contractors = getContractorMailstoSend();
			
			//creo session para obtener reporte de forestal
			AlertReportHandler alertReportHandler = new AlertReportHandler();
			
			String attachment = null;
			if (contractors != null && contractors.size() > 0){
				Iterator it = contractors.iterator();
				
				if (sys != null && sys.getUserSessionData() != null){
					while(it.hasNext()){
						Collection to = new LinkedList();
						ContractorDataData contractor = (ContractorDataData)it.next();
						
							//generar attachment con la codificación correcta
							attachment = sys.getUserSessionData().getFilter().getRoot(2, contractor.getId());
							
							if (attachment  !=  null){
								attachment = attachment.replace(" ","_");
								attachment = attachment.toLowerCase();
								attachment = Util.getHtmlString(attachment);
								attachment = attachment.replace("__","_");
							}
							
							// Actualizacion 28/05/2012
							// Se envia los emails a Vale en vez de a los contratistas
							//to.add(AlertConstants.EMAIL_VALE);
							to.add(AlertConstants.EMAIL_MICA); // Agregado el 04/09/2012
							
							// to.add(contractor.getMail());
							// if (contractor.getMail2() != null && contractor.getMail2().trim() != "") to.add(contractor.getMail2());
							// to.add(AlertConstants.BCC);
							
							if (attachment != null){
							
								attachment = alertReportHandler.reportDriversForestales(contractor.getId().intValue(),
									directory,attachment,sys,corporationId);
							
							
								// Si no hay attachments (o reportes), no tiene sentido mandar el email de Contratistas
								// que es especificamente con un attachment con el reporte.
								Message message = 
										new Message(
												AlertConstants.FROM_WEYERHAEUSER,
												AlertConstants.FROMNAME_WEYERHAEUSER,
												AlertConstants.FROMPASSWORD_WEYERHAEUSER, to, subject,
												Util.getCompleteHTMLMail(body,
														AlertConstants.DIGITAL_SIGNATURE_WEYERHAEUSER,
														AlertConstants.FROM_WEYERHAEUSER),
														directory + attachment + ".xls", attachment + ".xls");
								message.setReplyTo(AlertConstants.EMAIL_MICA);
									
								messages.add(message);
							}
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
	 * @param body
	 * @param subject
	 * @param corporationId
	 * @param sys
	 * @return
	 */
	private Collection getContractorMessagestoSendFOSA(String body,String subject,
			Integer corporationId, AlertSystemAccess sys){
	
		//Este Método se usa para FOSA
		
		Collection messages = new LinkedList();
		try{	
				
			Collection contractors = getContractorMailstoSend();
			
			//creo session para obtener reporte de forestal
			AlertReportHandler alertReportHandler = new AlertReportHandler();
			
			String attachment = null;
			if (contractors != null && contractors.size() > 0){
				Iterator it = contractors.iterator();
				
				if (sys != null && sys.getUserSessionData() != null){
					while(it.hasNext()){
						Collection to = new LinkedList();
						ContractorDataData contractor= (ContractorDataData)it.next();
						
							//generar attachment con la codificación correcta
							attachment = getContractorName(contractor.getId());
							if (attachment != null){
								attachment = attachment.replace(" ","_");
								attachment = attachment.toLowerCase();
								attachment = Util.getHtmlString(attachment);
								attachment = attachment.replace("__","_");
							}
							
							StringTokenizer tokenizer = new StringTokenizer(contractor.getMail(),",");
							while(tokenizer.hasMoreTokens()) {
								to.add(tokenizer.nextToken());
							}
//							to.add(contractor.getMail());
//							if (contractor.getMail2() != null && contractor.getMail2().trim() != "") to.add(contractor.getMail2());
//							to.add(AlertConstants.BCC);
//							to.add(AlertConstants.EMAIL_MICA);
							
							attachment = alertReportHandler.reportDriversForestales(contractor.getId().intValue(),
									AlertConstants.TEMP_DIRECTORY_ALERT,attachment,sys,corporationId);
							
							Message message = 
								new Message(
										AlertConstants.EMAIL_MICA,
										AlertConstants.DIGITAL_SIGNATURE_FOSA,
										AlertConstants.FROMPASSWORD_FOSA,to,subject,
										Util.getCompleteHTMLMail(body,AlertConstants.DIGITAL_SIGNATURE_FOSA,
										AlertConstants.EMAIL_MICA),AlertConstants.TEMP_DIRECTORY_ALERT+
										attachment+".xls",attachment+".xls");
							message.setReplyTo(AlertConstants.EMAIL_MICA);
							
							messages.add(message);
							
							//messages.add(new Message(AlertConstants.FROM_ALERTS,AlertConstants.FROMNAME_ALERTS,AlertConstants.FROMPASSWORDALERTS,to,subject,Util.getCompleteHTMLMail(body,AlertConstants.DIGITAL_SIGNATURE__ALERTS,AlertConstants.FROM_ALERTS),AlertConstants.TEMP_DIRECTORY_ALERT+attachment+".xls",attachment+".xls"));
							
							//to.clear();
						
					}
				}
			}
			return messages;
		
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		return null;
	}
	
	private Collection getContractorMessagestoSendFOSAInline(String bodyAlert,String subject,
			Integer corporationId, AlertSystemAccess sys){
	
		//Este Método se usa para FOSA
		
		Collection messages = new LinkedList();
		try{	
				
			Collection contractors = getContractorMailstoSend();
			String bodyGabinete="";
			String bodyBTWs="";
			
					
			//creo session para obtener reporte de forestal
			AlertReportHandler alertReportHandler = new AlertReportHandler();
			
			String attachment = null;
			if (contractors != null && contractors.size() > 0){
				Iterator it = contractors.iterator();
				
				if (sys != null && sys.getUserSessionData() != null){
					while(it.hasNext()){
						Collection to = new LinkedList();
						ContractorDataData contractor= (ContractorDataData)it.next();
						
													
							StringTokenizer tokenizer = new StringTokenizer(contractor.getMail(),",");
							while(tokenizer.hasMoreTokens()) {
								to.add(tokenizer.nextToken());
							}

							
							bodyGabinete = alertReportHandler.reportDriversForestalesGabinetesInline(contractor.getId().intValue()
									,sys,corporationId);
							bodyBTWs = alertReportHandler.reportDriversForestalesBTWsInline(contractor.getId().intValue()
									,sys,corporationId);
							
							 
							String body=bodyAlert.replace(" ", "&nbsp;").replace("#G", bodyGabinete).replace("#C", bodyBTWs).replace("\n", "<br>");
							
							Message message = 
								new Message(
										AlertConstants.EMAIL_MICA,
										AlertConstants.DIGITAL_SIGNATURE_FOSA,
										AlertConstants.FROMPASSWORD_FOSA,to,subject+"-"+getContractorName(contractor.getId()),
										Util.getCompleteHTMLMail(body,AlertConstants.DIGITAL_SIGNATURE_FOSA,
										AlertConstants.EMAIL_MICA));
							
							message.setReplyTo(AlertConstants.EMAIL_MICA);
							
							messages.add(message);
							
							//messages.add(new Message(AlertConstants.FROM_ALERTS,AlertConstants.FROMNAME_ALERTS,AlertConstants.FROMPASSWORDALERTS,to,subject,Util.getCompleteHTMLMail(body,AlertConstants.DIGITAL_SIGNATURE__ALERTS,AlertConstants.FROM_ALERTS),AlertConstants.TEMP_DIRECTORY_ALERT+attachment+".xls",attachment+".xls"));
							
							//to.clear();
						
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
	private Collection getContractorMailstoSend()throws Exception{
	
		// Este Método se usa para FOSA o WEYERHAEUSER
		// ContractorDataData contractorMail = null;
		Collection list = new LinkedList();
		try{	
				Session session = HibernateUtil.currentSession();
			
					String queryStr = " select contractorData from ContractorData as contractorData " +
							"where contractorData.state = 1 and contractorData.mail is not null " +
							"and contractorData.mail != '' and contractorData.corporation = " +
							this.idCorporation;
					//" and contractorData.id in (330219)";
					
					Query query = session.createQuery(queryStr);
					List contractors= query.list();
					Iterator iter = contractors.iterator();
					ContractorData contractorData= null;
					int i = 1;
					while (iter.hasNext()) {
						contractorData = (ContractorData) iter.next();
						
						System.out.println(i + ": Contractor mail: "+contractorData.getMail());
						i++;
						
						list.add(contractorData.getContractorDataData());					
					}
					return list;
			    
		
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		return null;
	}
	
	private String getContractorName(Integer id)throws Exception{
		
		// Este Método se usa para FOSA o WEYERHAEUSER
		// ContractorDataData contractorMail = null;
		try{	
				Session session = HibernateUtil.currentSession();
			
					String queryStr = " select resourcekey from divorgitemlevel1s where id = "+id;
					
					Query query = session.createSQLQuery(queryStr).addScalar("resourcekey",Hibernate.STRING);
					List contractors= query.list();
					Iterator iter = contractors.iterator();
					if (iter.hasNext()) {
						return (String) iter.next();
											
					}
			    
		
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		return "";
	}
}
