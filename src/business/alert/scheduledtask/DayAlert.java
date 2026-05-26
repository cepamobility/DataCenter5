package business.alert.scheduledtask;

import java.io.Serializable;
import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.varia.scheduler.Schedulable;

public class DayAlert implements Schedulable, Serializable {

	private static final long serialVersionUID = 1L;
	
	public void perform(Date fecha, long veces) {

		System.out.println("Me invocan a las:" + fecha);

		System.out.println("Todavía me deben ejecutar :" + veces + " veces" );
		
		System.out.println("Voy a encolar mensaje ....." );
		
		InitialContext ctxJNDI;
		
		
		try {
			//ctxJNDI = new InitialContext(props);
			ctxJNDI = new InitialContext();
			TopicConnectionFactory topicConnectionFactory = (TopicConnectionFactory)
			                                                  ctxJNDI.lookup("ConnectionFactory");
			
			System.out.println("Lookup: Connection Factory OK");
			try {
				TopicConnection topicConnection = topicConnectionFactory.createTopicConnection();
				
				TopicSession topicSession = topicConnection.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);
				
				Topic topic = (Topic) ctxJNDI.lookup("topic/DayAlertTopic");
				System.out.println("Lookup: topic/DayAlertTopic OK");
				
				TopicPublisher topicPublisher = topicSession.createPublisher(topic);
	
				TextMessage message = topicSession.createTextMessage();
				
				message.setText("Este es un mensaje JMS");
				
				System.out.println("Este es un mensaje JMS");
				
				topicPublisher.send(message);
				
				
			} catch (JMSException e) {
				
				e.printStackTrace();
			}

		} catch (NamingException e) {
			
			e.printStackTrace();
		}		
		
		System.out.println("Encolé mensaje desde DayAlert......." );		
		

		}
	
}





