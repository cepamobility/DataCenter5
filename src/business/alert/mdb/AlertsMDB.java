package business.alert.mdb;


import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.ejb.Stateless;
import javax.jms.Message;
import javax.jms.MessageListener;

import business.alert.access.AlertSystemAccess;
import business.alert.handler.AlertHandler;
import business.alert.handler.AlertHandlerFactory;

import communication.alert.AlertConstants;
import communication.alert.AlertData;

@Stateless
public class AlertsMDB implements MessageDrivenBean, MessageListener, Serializable{

	private static final long serialVersionUID = 1L;
	private MessageDrivenContext context;

	// --------------------------------------------------------------
	// EJB Methods from MessageDrivenBean interface
	// --------------------------------------------------------------
	public void ejbCreate() {
		System.out.println("EmailMDB: ejbCreate called");
	}

	public void ejbRemove() {
		System.out.println("EmailMDB: ejbRemove called");
	}

	public void setMessageDrivenContext(MessageDrivenContext context) {
		System.out.println("setMessageDrivenContext called");
		this.context = context;
	}


	/**
	 * Take in a MapMessage and use the EmailHelper to send out an email message
	 * --------------------------------------------------------------
	 * Method from MessageListener interface
	 * --------------------------------------------------------------
	 * Problemas con Transacciones, Bibliografia:
	 * 
	 * http://seamframework.org/Community/JTATransactionTimeout
	 * http://community.jboss.org/wiki/TxMultipleThreads
	 * http://community.jboss.org/wiki/transactiontimeout
	 * http://openejb.apache.org/3.0/transaction-annotations.html
	 * http://community.jboss.org/wiki/WhydoIgetCouldnotfindnewXAResourcetouseforrecoveringnon-serializableXAResource
	 * http://community.jboss.org/wiki/TxNonSerializableXAResource
	 * 
	 * http://www.coderanch.com/t/418754/java-EJB-SCBCD/certification/Message-driven-beans-transaction-attributes
	 */	
	// @ TransactionAttribute(TransactionAttributeType.REQUIRED)
	// @ TransactionTimeout(7200)
	public void onMessage(Message message) {
		
		
		System.out.println("Entre a AlertsMDB");	
		//System.out.println("INICIO MBEAN CHECKLIST");
		AlertHandlerFactory af = new AlertHandlerFactory();
		AlertHandler alertHandler = null;		
		AlertSystemAccess system = null;
		LinkedList<AlertData> todayAlert = null;
		
		try {
			
			// ALERTAS AGENDADAS para correr en determinada fecha:
			
			todayAlert = AlertHandler.getTodayAlert(); 
			
			if (todayAlert != null && todayAlert.size() > 0){
				
				System.out.println("Cantidad de Alertas: "+todayAlert.size() );
				
				if (AlertConstants.isProductionServer()){
					System.out.println("Corriendo las Alertas en RACKSPACE !! ");					
				}else{
					System.out.println("Corriendo las Alertas en la máquina LOCA...L !! ");
				}
				
				
				Iterator<AlertData> it = todayAlert.iterator();
				AlertData alertItem;
				int i = 1;
				while (it.hasNext()) {
					
					System.out.println("Empecé Alerta (" + i + ")" );
					
					alertItem = it.next();
					alertHandler = af.getAlertHandler(alertItem,system);
					
					if (alertHandler != null){
						alertHandler.sendMessages();						
						AlertHandler.updateDateAlert(alertItem);					
					}
					System.out.println("Termine Alerta (" + i + ")" );
					i++;
					
				}	
			}else{
				System.out.println("No hay alertas para enviar.");
			}
			
			AlertHandler.dpaLoadUnconformityAlertsAndSend();
			AlertHandler.forestalesLoadUnconformityAlertsAndSend();
			AlertHandler.logisticaUruguay();
			
		} catch (Throwable e) {			
			e.printStackTrace();
		}

		
	}
	
	
	

	/**
	 * @return the context
	 */
	public MessageDrivenContext getContext() {
		return context;
	}

	/**
	 * @param context the context to set
	 */
	public void setContext(MessageDrivenContext context) {
		this.context = context;
	}
	
	

}




