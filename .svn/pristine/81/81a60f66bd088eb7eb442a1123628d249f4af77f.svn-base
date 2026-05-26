package persistence.administration.alert;

import java.util.Date;
import java.util.Hashtable;

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

public class WeekAlert implements Schedulable {

	public void perform(Date fecha, long veces) {

		System.out.println("Me invocan a las:" + fecha);

		System.out.println("Todavía me deben ejecutar :" + veces + " veces" );
		
		System.out.println("Voy a encolar mensaje ....." );
		
		InitialContext ctxJNDI;
		
		/*
		Hashtable props = new Hashtable();
		props.put("java.naming.factory.initial",
				"org.jnp.interfaces.NamingContextFactory");
		props.put("java.naming.factory.url.pkgs",
				"org.jboss.naming:org.jnp.interfaces");
		props.put("java.naming.provider.url", "jnp://localhost:1099");
		*/
		try {
			//ctxJNDI = new InitialContext(props);
			ctxJNDI = new InitialContext();
			TopicConnectionFactory topicConnectionFactory = (TopicConnectionFactory)
			                                                  ctxJNDI.lookup("ConnectionFactory");
			
			try {
				TopicConnection topicConnection = topicConnectionFactory.createTopicConnection();
				
				TopicSession topicSession = topicConnection.createTopicSession(false,Session.AUTO_ACKNOWLEDGE);
				
				Topic topic = (Topic) ctxJNDI.lookup("topic/WeekAlertTopic");
				
				TopicPublisher topicPublisher = topicSession.createPublisher(topic);
				
				
				TextMessage message = topicSession.createTextMessage();
				
				message.setText("Este es un mensaje JMS");
				
				topicPublisher.send(message);
				
				
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		System.out.println("Encolé mensaje ....." );		
		
		

		}
	
}





