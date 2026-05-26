package persistence.log;

import org.apache.log4j.jdbc.JDBCAppender;
import org.apache.log4j.spi.LoggingEvent;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import persistence.hibernate.HibernateUtil;
import persistence.log.tables.Loggings;

public class DataCenterJDBCAppender extends JDBCAppender {
	
public synchronized void doAppend(LoggingEvent event) {
	// TODO Auto-generated method stub
	try{
		Session session=HibernateUtil.currentSession();
		Transaction tx=session.beginTransaction();
		Loggings line=new Loggings();
		line.setLocation(event.getLocationInformation().toString());
		line.setLog_level(event.getLevel().toString());
		if(event.getMessage().toString().split(",").length==2){
			line.setUserID(event.getMessage().toString().split(",")[0].trim());
			line.setMessage(event.getMessage().toString().split(",")[1].trim());
		}
		else{
			line.setMessage(event.getMessage().toString());
			line.setUserID("-1");
		}
		session.save(line);
	    tx.commit();
	}
	catch(Exception e){
	}
    
}

}
