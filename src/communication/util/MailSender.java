

package communication.util;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import communication.administration.corporation.tables.CorporationData;
import communication.alert.AlertConstants;
import communication.security.SecurityConstants;


public class MailSender implements Serializable{
	
    private Connection conn = null;

	private static final long serialVersionUID = 1L;
/*
	public void send(String toMail, String smtp,String fromName, String fromMail,
			String password, String subject, String body)throws MessagingException, UnsupportedEncodingException{
		
		Collection bccAddress = new LinkedList();
		send(toMail,smtp,fromName,fromMail,password,subject,body,bccAddress);
	}

	/**
	 * 
	 * @param toMail
	 * @param smtp
	 * @param fromName
	 * @param fromMail
	 * @param password
	 * @param subject
	 * @param body
	 * @param bcc: Si no lleva copia oculta poner null.
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 *
	public void send(String toMail, String smtp,String fromName, String fromMail,
			String password, String subject, String body, String bcc)
		throws MessagingException, UnsupportedEncodingException{
		
		Collection bccAddress = new LinkedList();
		if (bcc != null && bcc.trim().length() > 0){
			bccAddress.add(bcc);
		}
		send(toMail,smtp,fromName,fromMail,password,subject,body,bccAddress);
	}
		
	public void send(String toMail, String smtp,String fromName, String fromMail,
			String password, String subject, String body, Collection bcc)
		throws MessagingException, UnsupportedEncodingException{
		Authenticator auth = null;
		
        Properties properties = new Properties();
        properties.put("mail.smtp.host",smtp);
        
        
        if (smtp.contains("gmail.com")){
        	System.out.println("Gmail");
        	properties.put("mail.smtp.starttls.enable","true");
        	properties.put("mail.smtp.auth", "true");
        	// properties.put("mail.smtps.auth", "true");
        	properties.put("mail.smtp.socketFactory.port", "465");
        	properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        	properties.put("mail.smtp.socketFactory.fallback", "false");
        	
        	auth = new SMTPAuthenticator(fromMail, password);

        }else{
        	properties.put("mail.smtp.auth", "true");
        	auth = new SMTPAuthenticator(fromMail, password);
        }
        
        
        properties.put("mail.from",fromName);
        // Session mailSession = Session.getInstance(properties,null);
        Session mailSession = Session.getInstance(properties,auth);

        InternetAddress[] address = {new InternetAddress(toMail,toMail)};
        Message message = new MimeMessage(mailSession);
        message.setFrom( new InternetAddress(fromMail,fromName) );
        message.setRecipients(Message.RecipientType.TO, address);
        message.setSentDate(new Date());
        message.setSubject(subject);
        message.setContent(message.toString(),"text/html");
        message.setDataHandler(new DataHandler(body,"text/html"));
        
        InternetAddress[] replyTo = {new InternetAddress(fromMail,fromName)};
        message.setReplyTo(replyTo);

	    // Si lleva copia oculta:
	    if (bcc != null){
	    	Iterator it = bcc.iterator();
    		InternetAddress[] bccAddress = new InternetAddress[bcc.size()];
    		int i = 0; 
	    	while(it.hasNext()) {
	    		String bccStr = (String)it.next();
	    		bccAddress[i] = new InternetAddress(bccStr,bccStr);
	    		i++;
	    	}
    		message.setRecipients(Message.RecipientType.BCC, bccAddress);
	    }

	    if (smtp.contains("gmail.com")){
        	Transport.send(message);
        }else{
        	Transport.send(message);
        	
        	//Transport transport = mailSession.getTransport(address[0]);
        	//transport.connect(smtp, fromMail, password);
        	//transport.sendMessage(message,address);
        }
	    
    }
	
	/**
	 * 
	 * @param toMail
	 * @param smtp
	 * @param fromName
	 * @param fromMail
	 * @param password
	 * @param subject
	 * @param body
	 * @param bcc: si no lleva copia oculta poner null
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 *
	public void send(Collection toMail, String smtp,String fromName, String fromMail,
			String password, String subject, String body, String bcc)
			throws MessagingException, UnsupportedEncodingException{
	
	    Properties properties = new Properties();
	    Authenticator auth = null;
	    
	    //Si se usa locaweb como servidor de correo saliente	    
	    properties.put("mail.smtp.host",smtp);
	    
	    if (smtp.contains("gmail.com")){
        	System.out.println("Gmail");
        	properties.put("mail.smtp.starttls.enable","true");
        	properties.put("mail.smtp.auth", "true");
        	// properties.put("mail.smtps.auth", "true");
        	properties.put("mail.smtp.socketFactory.port", "465");
        	properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        	properties.put("mail.smtp.socketFactory.fallback", "false");
        	
        	auth = new SMTPAuthenticator(fromMail, password);

        }else{
        	properties.put("mail.smtp.auth", "true");
        	auth = new SMTPAuthenticator(fromMail, password);
        }
	    
	    properties.put("mail.from",fromName);	    
					
		
	    // Session mailSession = Session.getInstance(properties,null);
	    Session mailSession = Session.getInstance(properties,auth);
	    
	    Iterator it = toMail.iterator();
	    String to;
	    InternetAddress[] address = new InternetAddress[toMail.size()];
	    int i = 0;
	    while (it.hasNext()) {
	    	to = (String) it.next();
	    	address[i] = new InternetAddress(to,to);
	    	i++;
	    }
	    	    
	    Message message = new MimeMessage(mailSession);
	    message.setFrom( new InternetAddress(fromMail,fromName) );
	    message.setRecipients(Message.RecipientType.TO, address);
	    message.setSentDate(new Date());
	    message.setSubject(subject);
	    message.setContent(message.toString(),"text/html");
	    message.setDataHandler(new DataHandler(body,"text/html"));
	    
	    InternetAddress[] replyTo = {new InternetAddress(fromMail,fromName)};
        message.setReplyTo(replyTo);
	   
	    // Si lleva copia oculta:
	    if (bcc != null){
	    	InternetAddress bccAddress = new InternetAddress(bcc,bcc);
	    	message.setRecipient(Message.RecipientType.BCC, bccAddress);
	    }

	    if (smtp.contains("gmail.com")){
        	Transport.send(message);
        }else{
        	Transport.send(message);
        }
	    
	
	    
	}
	
	
	/**
	 * Envio de emails para Alertas
	 * @param toMail
	 * @param smtp
	 * @param fromName
	 * @param fromMail
	 * @param password
	 * @param subject
	 * @param body
	 * @param bcc: si no lleva copia oculta poner null
	 * @return
	 * @throws Exception
	 *
	public boolean sendalert(Collection toMail, String smtp,String fromName, String fromMail,
			String password, String subject, String body, String replyToMail, 
			String replyToName, Collection bccMail)
			throws Exception{
	
		 try{
			
			Properties properties = new Properties();
			Authenticator auth = null;
			InternetAddress[] addressTo = null;
			InternetAddress[] addressBcc = null;
			String to;
			String bcc;
			 int i = 0;
			 Iterator it;
			 Message message;
			 Session mailSession;
			 
		    properties.put("mail.smtp.host",smtp);
	        
	        if (smtp.contains("gmail.com")){
	        	System.out.println("Gmail");
	        	properties.put("mail.smtp.starttls.enable","true");
	        	properties.put("mail.smtp.auth", "true");
	        	// properties.put("mail.smtps.auth", "true");
	        	properties.put("mail.smtp.socketFactory.port", "465");
	        	properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        	properties.put("mail.smtp.socketFactory.fallback", "false");
	        	
	        	auth = new SMTPAuthenticator(fromMail, password);

	        }else{
	        	properties.put("mail.smtp.auth", "true");
	        	auth = new SMTPAuthenticator(fromMail, password);
	        }

		    properties.put("mail.from",fromName);
			
		    mailSession = Session.getInstance(properties,auth);
		    // Session mailSession = Session.getInstance(properties,null);
		    
			message = new MimeMessage(mailSession);
			
			// TO 
			if (toMail == null || toMail.size() == 0){
				toMail.add(fromMail);
			}
		    it = toMail.iterator();		  
		    addressTo = new InternetAddress[toMail.size()];		   
		    while (it.hasNext()) {
		    	to = (String) it.next();
		    	addressTo[i] = new InternetAddress(to,to);
		    	i++;
		    }    
		    message.setRecipients(Message.RecipientType.TO, addressTo);
		   
		    // Si lleva copia oculta:
		    if (bccMail != null && bccMail.size() > 0){
		    	 it = bccMail.iterator();		  
				 addressBcc = new InternetAddress[bccMail.size()];
				 i = 0;
				 while (it.hasNext()) {
				    	bcc = (String) it.next();
				    	if (!empty(bcc) && bcc.contains("@")){
				    		addressBcc[i] = new InternetAddress(bcc,bcc);
				    	}
				    	i++;
				 }    
		    	message.setRecipients(Message.RecipientType.BCC, addressBcc);
		    }
		    
		    message.setFrom( new InternetAddress(fromMail,fromName) );		    
		    message.setSentDate(new Date());
		    message.setSubject(subject);
		    message.setContent(message.toString(),"text/html");
		    message.setDataHandler(new DataHandler(body,"text/html"));
		   
		    if (replyToMail != null && replyToName != null){
		    	InternetAddress[] replyTo = new InternetAddress[1];
		    	replyTo[0] = new InternetAddress(replyToMail,replyToName);
		    	message.setReplyTo(replyTo);
		    }else{
		    	 InternetAddress[] replyTo = {new InternetAddress(fromMail,fromName)};
		         message.setReplyTo(replyTo);
		    }
		
		
			// mailSession.setDebug(true);
		    
		    if (smtp.contains("gmail.com")){
		    	
	        	Transport.send(message);
	        }else{
	        	Transport.send(message);
	        	 
	        }
		
		 }catch(javax.mail.AuthenticationFailedException e){
			 e.printStackTrace();
			 return false;
			 
		 }catch(com.sun.mail.smtp.SMTPSendFailedException e){			
			e.printStackTrace();
		   	return false;
			
		}catch(Exception e){
			
			e.printStackTrace();
			return false;
		}
	    return true;
	    
	}
	
	/**
	 * Envio de email con attachment para Alertas
	 * @param toMail
	 * @param smtp
	 * @param fromName
	 * @param fromMail
	 * @param password
	 * @param subject
	 * @param body
	 * @param attachment
	 * @param name
	 * @param bcc: si no lleva copia oculta poner null
	 * @return
	 * @throws Exception
	 *
	public boolean send(Collection toMail, String smtp,String fromName, String fromMail,
			String password, String subject, String body, String attachment,
			String fileName, String replyToMail, String replyToName, Collection bccMail)
			throws Exception{
	
	    try{
	    	
	    	Properties properties = new Properties();
			Authenticator auth = null;
			String to = null;
			String bcc = null;
			Session mailSession = null;
			LinkedList addressList = null;
			Iterator it = null;
			Message message;
			InternetAddress[] addressTo = null;
			InternetAddress[] addressBcc = null;
			int i = 0;
			
		    properties.put("mail.smtp.host",smtp);
	        
	        if (smtp.contains("gmail.com")){
	        	System.out.println("Gmail");
	        	properties.put("mail.smtp.starttls.enable","true");
	        	properties.put("mail.smtp.auth", "true");
	        	// properties.put("mail.smtps.auth", "true");
	        	properties.put("mail.smtp.socketFactory.port", "465");
	        	properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        	properties.put("mail.smtp.socketFactory.fallback", "false");
	        	
	        	auth = new SMTPAuthenticator(fromMail, password);

	        }else{
	        	
	        	properties.put("mail.smtp.auth", "true");
	        	auth = new SMTPAuthenticator(fromMail, password);
	        }

		    properties.put("mail.from",fromName);
			
		    mailSession = Session.getInstance(properties,auth);
		    message = new MimeMessage(mailSession);
		    
		    // To 		 
		    if (toMail == null || toMail.size() == 0){
		    	toMail.add(fromMail);
		    }
		    
		   //  if (toMail != null && toMail.size() > 0){
			    it = toMail.iterator();
			    addressList = new LinkedList();
			    i = 0;
			    
			    // Filtro direcciones de emails vacias:
			    while (it.hasNext()) {
			    	to = (String) it.next();		
			    	if (!empty(to) && to.contains("@")) { 
			    		addressList.add(to);	    		
			    	}
			    	i++;
			    }
		    
			    // Armo lista de direcciones de emails definitiva:
			    
			    if (addressList.size() > 0){
			    	i = 0;
			    	addressTo = new InternetAddress[addressList.size()];
			    	it = addressList.iterator();
			    	while (it.hasNext()){
			    		to = (String) it.next();
			    		addressTo[i] = new InternetAddress(to,to);
			    		i++;
			    	}
			    }
		   // }
		    if (addressTo != null && addressTo.length > 0){
		    	// message.setRecipients(Message.RecipientType.TO, addressTo);
		    	message.addRecipients(Message.RecipientType.TO, addressTo);
		    }
		    // Fin TO: 
		    
		    // BCC 
		    if (bccMail != null && bccMail.size() > 0){
			    it = bccMail.iterator();
			    addressList = new LinkedList();
			    i = 0;			    
			    // Filtro direcciones de emails vacias:
			    while (it.hasNext()) {
			    	bcc = (String) it.next();		
			    	if (!empty(bcc) && bcc.contains("@")) { 
			    		// bcc = "gbenaderet@cepasafedrive.com"; // TODO
			    		addressList.add(bcc);	    		
			    	}
			    	i++;
			    }		    
			    // Armo lista de direcciones de emails definitiva:			    
			    if (addressList.size() > 0){
			    	i = 0;
			    	addressBcc = new InternetAddress[addressList.size()];
			    	it = addressList.iterator();
			    	while (it.hasNext()){
			    		bcc = (String) it.next();
			    		addressBcc[i] = new InternetAddress(bcc,bcc);
			    		i++;
			    	}
			    }
		    }
		    // Si lleva copia oculta:
		    if (addressBcc != null && addressBcc.length > 0){
		    	message.addRecipients(Message.RecipientType.BCC, addressBcc);
		    	// 	message.setRecipients(Message.RecipientType.BCC,addressBcc);
			}
		    // Fin BCC 
		   
		   // Si tiene algun destinatario ahi si mando el mail
		    if ((addressBcc != null && addressBcc.length > 0) ||
		    		(addressTo != null && addressTo.length > 0) ){
		    
			    message.setFrom( new InternetAddress(fromMail,fromName) );			  
			    message.setSentDate(new Date());
			    message.setSubject(subject);
			    
			    if (replyToMail != null && replyToName != null){
			    	InternetAddress[] replyTo = new InternetAddress[1];
			    	replyTo[0] = new InternetAddress(replyToMail,replyToName);
			    	message.setReplyTo(replyTo);
			    }else{
			    	 InternetAddress[] replyTo = {new InternetAddress(fromMail,fromName)};
			         message.setReplyTo(replyTo);
			    }
			    
			    // En esta parte creamos el contenedor para el cuerpo html del mensaje
			    MimeBodyPart messageBodyPart = new MimeBodyPart();
			    messageBodyPart.setContent(body,"text/html");
			    Multipart multipart = new MimeMultipart();
			    multipart.addBodyPart(messageBodyPart);
			    
			    // Aquí se anexa el attachment
			    messageBodyPart = new MimeBodyPart();
			    DataSource source = new FileDataSource(attachment);
			    messageBodyPart.setDataHandler(new DataHandler(source));
			    messageBodyPart.setFileName(fileName);
			    multipart.addBodyPart(messageBodyPart);
			    message.setContent(multipart);
			
			    if (smtp.contains("gmail.com")){
		        	Transport.send(message);
		        }else{

		        	// Debug:
		        	// mailSession.setDebug(true);
		        	
		        	Transport.send(message);
		        	System.out.println("Send Multipart Mail");
		        	// transport.close();
		        }
			
				return true;
		    }
		
	    }catch(com.sun.mail.smtp.SMTPSendFailedException e){
	    	//System.out.println("com.sun.mail.smtp.SMTPSendFailedException: 454 Atencao: voce atingiu o limite maximo de envio: 1000 mensagens em 60 minuto(s). Apos este periodo o envio volta ao normal.");
	    	e.printStackTrace();
	    	return false;
	    	
		}catch(Exception e){
		   	e.printStackTrace();
			return false;
		}
		
		System.out.println("No se envió el email, destinatarios vacios.");
		return false;
		
	}

	 /**
     * Valida que el string sea diferente de null y de vacio.
     * @param label
     * @return
     */
    private boolean empty(String label) {
        return label == null || label.trim().length() == 0;
    }

	/**
	 * Devuelve la cuenta de email a utilizar para enviar el email.
	 * [0]: address
	 * [1]: password
	 * @return
	 */
	public String[] getAvailableAddress(Integer idCorporation) {
		String[] address = new String[2];
	    try {
	      Class.forName("org.postgresql.Driver");
	      
	      /* 
	       * Siempre accedemos a la conecion de produccion
	       * desde produccion usamos localhost
	       * y en zonamerica usamos la ip de produccion
	       */
	      if(conn == null) {
		      if (AlertConstants.isProductionServer()){
		    	  conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/datacenter5","postgres","pr0v1s0r1A");
		      }else{
		    	  //conn = DriverManager.getConnection("jdbc:postgresql://74.124.197.17:5432/datacenter5","postgres","pr0v1s0r1A");
	
		    	  // Temporal para usar desde casa:
		    	  conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/datacenter5","postgres","pr0v1s0r1A");
		      }
	      }	      
	      
	      Calendar today = Calendar.getInstance();
	      String sql = "";
	      if (idCorporation != null && idCorporation.intValue() == CorporationData.BAYER){
	    	  
	    	  String mailUsed = AlertConstants.BAYER_ALERT_EMAILS[(new Double(Math.random()*19)).intValue()];
	    	  address[0] = mailUsed;
	    	  address[1] =  AlertConstants.BAYER_ALERT_EMAILS_PASSWORD;// password
	    	  conn.close();
	    	  conn = null;
	    	  return address;
	    	  
	      }else {
	    	  sql = "SELECT address,password,date,sended FROM emailaddress WHERE address LIKE '%@cepalogistica%' ORDER BY date";
	      }
	      
	      
	      Statement stQuery = conn.createStatement();
	      Statement stUpdate = conn.createStatement();
	      ResultSet set = stQuery.executeQuery(sql);
	      
	      boolean found = false;
	      while(set.next() && !found) {
		      Calendar last = Calendar.getInstance();
		      last.setTime(set.getDate("date"));
		      
		      if(last.get(Calendar.DATE) != today.get(Calendar.DATE) || last.get(Calendar.MONTH) != today.get(Calendar.MONTH) || last.get(Calendar.YEAR) != today.get(Calendar.YEAR)) {
		    	  address[0] = set.getString("address");
		    	  address[1] = set.getString("password");
		    	  String update = "UPDATE emailaddress SET sended = 1, \"date\" = CURRENT_TIMESTAMP WHERE address = '"+address[0]+"' ";
		    	  System.out.println(update);
		    	  stUpdate.execute(update);
		    	  found = true;
		    	  System.out.println("Email enviado desde: "+address[0]);
		      }else {
		    	  int sended = set.getInt("sended");
		    	  if(sended < 100) {
		    		  address[0] = set.getString("address");
		    		  address[1] = set.getString("password");
		    		  
		    		  String update = "UPDATE emailaddress SET sended = "+String.valueOf(sended+1)+", \"date\" = CURRENT_TIMESTAMP WHERE address = '"+address[0]+"' ";
		    		  System.out.println(update);
		    		  stUpdate.execute(update);
		    		  found = true;
		    		  System.out.println("Email enviado desde: "+address[0]);
		    	  }
		      }
	      }
	     
		    conn.close();
		    conn = null;
	     }catch (Exception e) {
	    	e.printStackTrace();
	     }
	     
	     
	     return address;
    }

	/**
	 * Envio de email con attachment para Alertas
	 * @param toMail
	 * @param smtp
	 * @param fromName
	 * @param fromMail
	 * @param password
	 * @param subject
	 * @param body
	 * @param attachment
	 * @param name
	 * @param bcc: si no lleva copia oculta poner null
	 * @return
	 * @throws Exception
	 *
	public boolean send(Collection toMail, String smtp,String fromName, String fromMail,
			String password, String subject, String body, Collection<String> attachments,
			Collection<String> names, String replyToMail, String replyToName, Collection bccMail)
			throws Exception{
	
	    try{
	    	
	    	Properties properties = new Properties();
			Authenticator auth = null;
			String to = null;
			String bcc = null;
			Session mailSession = null;
			LinkedList addressList = null;
			Iterator it = null;
			Message message;
			InternetAddress[] addressTo = null;
			InternetAddress[] addressBcc = null;
			int i = 0;
			
		    properties.put("mail.smtp.host",smtp);
	        
	        if (smtp.contains("gmail.com")){
	        	System.out.println("Gmail");
	        	properties.put("mail.smtp.starttls.enable","true");
	        	properties.put("mail.smtp.auth", "true");
	        	// properties.put("mail.smtps.auth", "true");
	        	properties.put("mail.smtp.socketFactory.port", "465");
	        	properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        	properties.put("mail.smtp.socketFactory.fallback", "false");
	        	
	        	auth = new SMTPAuthenticator(fromMail, password);

	        }else{
	        	
	        	properties.put("mail.smtp.auth", "true");
	        	auth = new SMTPAuthenticator(fromMail, password);
	        }

		    properties.put("mail.from",fromName);
			
		    mailSession = Session.getInstance(properties,auth);
		    message = new MimeMessage(mailSession);
		    
		    /// To 		 
		    if (toMail == null || toMail.size() == 0){
		    	toMail.add(fromMail);
		    }
		    
		   //  if (toMail != null && toMail.size() > 0){
			    it = toMail.iterator();
			    addressList = new LinkedList();
			    i = 0;
			    
			    // Filtro direcciones de emails vacias:
			    while (it.hasNext()) {
			    	to = (String) it.next();		
			    	if (!empty(to) && to.contains("@")) { 
			    		addressList.add(to);	    		
			    	}
			    	i++;
			    }
		    
			    // Armo lista de direcciones de emails definitiva:
			    
			    if (addressList.size() > 0){
			    	i = 0;
			    	addressTo = new InternetAddress[addressList.size()];
			    	it = addressList.iterator();
			    	while (it.hasNext()){
			    		to = (String) it.next();
			    		addressTo[i] = new InternetAddress(to,to);
			    		i++;
			    	}
			    }
		   // }
		    if (addressTo != null && addressTo.length > 0){
		    	// message.setRecipients(Message.RecipientType.TO, addressTo);
		    	message.addRecipients(Message.RecipientType.TO, addressTo);
		    }
		    // Fin TO: 
		    
		    // BCC 
		    if (bccMail != null && bccMail.size() > 0){
			    it = bccMail.iterator();
			    addressList = new LinkedList();
			    i = 0;			    
			    // Filtro direcciones de emails vacias:
			    while (it.hasNext()) {
			    	bcc = (String) it.next();		
			    	if (!empty(bcc) && bcc.contains("@")) { 
			    		// bcc = "gbenaderet@cepasafedrive.com"; // TODO
			    		addressList.add(bcc);	    		
			    	}
			    	i++;
			    }		    
			    // Armo lista de direcciones de emails definitiva:			    
			    if (addressList.size() > 0){
			    	i = 0;
			    	addressBcc = new InternetAddress[addressList.size()];
			    	it = addressList.iterator();
			    	while (it.hasNext()){
			    		bcc = (String) it.next();
			    		addressBcc[i] = new InternetAddress(bcc,bcc);
			    		i++;
			    	}
			    }
		    }
		    // Si lleva copia oculta:
		    if (addressBcc != null && addressBcc.length > 0){
		    	message.addRecipients(Message.RecipientType.BCC, addressBcc);
		    	// 	message.setRecipients(Message.RecipientType.BCC,addressBcc);
			}
		    // Fin BCC 
		   
		   // Si tiene algun destinatario ahi si mando el mail
		    if ((addressBcc != null && addressBcc.length > 0) ||
		    		(addressTo != null && addressTo.length > 0) ){
		    
			    message.setFrom( new InternetAddress(fromMail,fromName) );			  
			    message.setSentDate(new Date());
			    message.setSubject(subject);
			    
			    if (replyToMail != null && replyToName != null){
			    	InternetAddress[] replyTo = new InternetAddress[1];
			    	replyTo[0] = new InternetAddress(replyToMail,replyToName);
			    	message.setReplyTo(replyTo);
			    }else{
			    	 InternetAddress[] replyTo = {new InternetAddress(fromMail,fromName)};
			         message.setReplyTo(replyTo);
			    }
			    
			    // En esta parte creamos el contenedor para el cuerpo html del mensaje
			    MimeBodyPart messageBodyPart = new MimeBodyPart();
			    messageBodyPart.setContent(body,"text/html");
			    Multipart multipart = new MimeMultipart();
			    multipart.addBodyPart(messageBodyPart);
			    
			    Iterator<String> atts = attachments.iterator();
			    Iterator<String> nameIt = names.iterator();
			    while(atts.hasNext()) {
			    	String attachment = atts.next();
			    	String name = nameIt.next();
				    // Aquí se anexa el attachment
				    messageBodyPart = new MimeBodyPart();
				    DataSource source = new FileDataSource(attachment);
				    messageBodyPart.setDataHandler(new DataHandler(source));
				    messageBodyPart.setFileName(name);
				    multipart.addBodyPart(messageBodyPart);
				    message.setContent(multipart);
			    }
			    
			    if (smtp.contains("gmail.com")){
		        	Transport.send(message);
		        }else{

		        	// Debug:
		        	// mailSession.setDebug(true);
		        	
		        	Transport.send(message);
		        }
			
				return true;
		    }
		
	    }catch(com.sun.mail.smtp.SMTPSendFailedException e){
	    	//System.out.println("com.sun.mail.smtp.SMTPSendFailedException: 454 Atencao: voce atingiu o limite maximo de envio: 1000 mensagens em 60 minuto(s). Apos este periodo o envio volta ao normal.");
	    	e.printStackTrace();
	    	return false;
	    	
		}catch(Exception e){
		   	e.printStackTrace();
			return false;
		}
		
		System.out.println("No se envió el email, destinatarios vacios.");
		return false;
		
	}*/

	/**
	 * Envio de email con attachment para Alertas
	 * @param toMail
	 * @param smtp
	 * @param fromName
	 * @param fromMail
	 * @param password
	 * @param subject
	 * @param body
	 * @param attachment
	 * @param name
	 * @param bcc: si no lleva copia oculta poner null
	 * @return
	 * @throws Exception
	 *
	public boolean sendImage(Collection toMail, String smtp,String fromName, String fromMail,
			String password, String subject, String body, Collection<String> attachments,
			Collection<String> names, String replyToMail, String replyToName, Collection bccMail, String image)
			throws Exception{
	
	    try{
	    	
	    	Properties properties = new Properties();
			Authenticator auth = null;
			String to = null;
			String bcc = null;
			Session mailSession = null;
			LinkedList addressList = null;
			Iterator it = null;
			Message message;
			InternetAddress[] addressTo = null;
			InternetAddress[] addressBcc = null;
			int i = 0;
			
		    properties.put("mail.smtp.host",smtp);
	        
	        if (smtp.contains("gmail.com")){
	        	System.out.println("Gmail");
	        	properties.put("mail.smtp.starttls.enable","true");
	        	properties.put("mail.smtp.auth", "true");
	        	// properties.put("mail.smtps.auth", "true");
	        	properties.put("mail.smtp.socketFactory.port", "465");
	        	properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        	properties.put("mail.smtp.socketFactory.fallback", "false");
	        	
	        	auth = new SMTPAuthenticator(fromMail, password);

	        }else{
	        	
	        	properties.put("mail.smtp.auth", "true");
	        	auth = new SMTPAuthenticator(fromMail, password);
	        }

		    properties.put("mail.from",fromName);
			
		    mailSession = Session.getInstance(properties,auth);
		    message = new MimeMessage(mailSession);
		    
		    // To 		 
		    if (toMail == null || toMail.size() == 0){
		    	toMail.add(fromMail);
		    }
		    
		   //  if (toMail != null && toMail.size() > 0){
			    it = toMail.iterator();
			    addressList = new LinkedList();
			    i = 0;
			    
			    // Filtro direcciones de emails vacias:
			    while (it.hasNext()) {
			    	to = (String) it.next();		
			    	if (!empty(to) && to.contains("@")) { 
			    		addressList.add(to);	    		
			    	}
			    	i++;
			    }
		    
			    // Armo lista de direcciones de emails definitiva:
			    
			    if (addressList.size() > 0){
			    	i = 0;
			    	addressTo = new InternetAddress[addressList.size()];
			    	it = addressList.iterator();
			    	while (it.hasNext()){
			    		to = (String) it.next();
			    		addressTo[i] = new InternetAddress(to,to);
			    		i++;
			    	}
			    }
		   // }
		    if (addressTo != null && addressTo.length > 0){
		    	// message.setRecipients(Message.RecipientType.TO, addressTo);
		    	message.addRecipients(Message.RecipientType.TO, addressTo);
		    }
		    // Fin TO: 
		    
		    // BCC 
		    if (bccMail != null && bccMail.size() > 0){
			    it = bccMail.iterator();
			    addressList = new LinkedList();
			    i = 0;			    
			    // Filtro direcciones de emails vacias:
			    while (it.hasNext()) {
			    	bcc = (String) it.next();		
			    	if (!empty(bcc) && bcc.contains("@")) { 
			    		// bcc = "gbenaderet@cepasafedrive.com"; // TODO
			    		addressList.add(bcc);	    		
			    	}
			    	i++;
			    }		    
			    // Armo lista de direcciones de emails definitiva:			    
			    if (addressList.size() > 0){
			    	i = 0;
			    	addressBcc = new InternetAddress[addressList.size()];
			    	it = addressList.iterator();
			    	while (it.hasNext()){
			    		bcc = (String) it.next();
			    		addressBcc[i] = new InternetAddress(bcc,bcc);
			    		i++;
			    	}
			    }
		    }
		    // Si lleva copia oculta:
		    if (addressBcc != null && addressBcc.length > 0){
		    	message.addRecipients(Message.RecipientType.BCC, addressBcc);
		    	// 	message.setRecipients(Message.RecipientType.BCC,addressBcc);
			}
		    // Fin BCC 
		   
		   // Si tiene algun destinatario ahi si mando el mail
		    if ((addressBcc != null && addressBcc.length > 0) ||
		    		(addressTo != null && addressTo.length > 0) ){
		    
			    message.setFrom( new InternetAddress(fromMail,fromName) );			  
			    message.setSentDate(new Date());
			    message.setSubject(subject);
			    
			    if (replyToMail != null && replyToName != null){
			    	InternetAddress[] replyTo = new InternetAddress[1];
			    	replyTo[0] = new InternetAddress(replyToMail,replyToName);
			    	message.setReplyTo(replyTo);
			    }else{
			    	 InternetAddress[] replyTo = {new InternetAddress(fromMail,fromName)};
			         message.setReplyTo(replyTo);
			    }
			    
			    // En esta parte creamos el contenedor para el cuerpo html del mensaje
			    MimeBodyPart messageBodyPart = new MimeBodyPart();
			    messageBodyPart.setContent(body,"text/html");
			    Multipart multipart = new MimeMultipart();
			    multipart.addBodyPart(messageBodyPart);
			    
			    if(image != null) {
					MimeBodyPart imagen = new MimeBodyPart();
					imagen.attachFile(image);
					imagen.setHeader("Content-ID","<figura1>");
					multipart.addBodyPart(imagen);
			    }
			    
			    Iterator<String> atts = attachments.iterator();
			    Iterator<String> nameIt = names.iterator();
			    while(atts.hasNext()) {
			    	String attachment = atts.next();
			    	String name = nameIt.next();
				    // Aquí se anexa el attachment
				    messageBodyPart = new MimeBodyPart();
				    DataSource source = new FileDataSource(attachment);
				    messageBodyPart.setDataHandler(new DataHandler(source));
				    messageBodyPart.setFileName(name);
				    multipart.addBodyPart(messageBodyPart);
			    }
			    message.setContent(multipart);
			    
			    if (smtp.contains("gmail.com")){
		        	Transport.send(message);
		        }else{

		        	// Debug:
		        	// mailSession.setDebug(true);
		        	
		        	Transport.send(message);
		        }
			
				return true;
		    }
		
	    }catch(com.sun.mail.smtp.SMTPSendFailedException e){
	    	//System.out.println("com.sun.mail.smtp.SMTPSendFailedException: 454 Atencao: voce atingiu o limite maximo de envio: 1000 mensagens em 60 minuto(s). Apos este periodo o envio volta ao normal.");
	    	e.printStackTrace();
	    	return false;
	    	
		}catch(Exception e){
		   	e.printStackTrace();
			return false;
		}
		
		System.out.println("No se envió el email, destinatarios vacios.");
		return false;
		
	}*/
	
	/**
	 * Envio de email con attachment para Alertas
	 * @param toMail
	 * @param smtp
	 * @param fromName
	 * @param fromMail
	 * @param password
	 * @param subject
	 * @param body
	 * @param attachment
	 * @param name
	 * @param bcc: si no lleva copia oculta poner null
	 * @return
	 * @throws Exception
	 *
	public boolean sendImage(Collection toMail, String smtp,String fromName, String fromMail,
			String password, String subject, String body, Collection<String> attachments,
			Collection<String> names, String replyToMail, String replyToName, Collection ccMail, Collection bccMail, String image)
			throws Exception{
	
	    try{
	    	
	    	Properties properties = new Properties();
			Authenticator auth = null;
			String to = null;
			String cc = null;
			String bcc = null;
			Session mailSession = null;
			LinkedList addressList = null;
			Iterator it = null;
			Message message;
			InternetAddress[] addressTo = null;
			InternetAddress[] addresscc = null;
			InternetAddress[] addressBcc = null;
			int i = 0;
			
		    properties.put("mail.smtp.host",smtp);
	        
	        if (smtp.contains("gmail.com")){
	        	System.out.println("Gmail");
	        	properties.put("mail.smtp.starttls.enable","true");
	        	properties.put("mail.smtp.auth", "true");
	        	// properties.put("mail.smtps.auth", "true");
	        	properties.put("mail.smtp.socketFactory.port", "465");
	        	properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        	properties.put("mail.smtp.socketFactory.fallback", "false");
	        	
	        	auth = new SMTPAuthenticator(fromMail, password);

	        }else{
	        	
	        	properties.put("mail.smtp.auth", "true");
	        	auth = new SMTPAuthenticator(fromMail, password);
	        }

		    properties.put("mail.from",fromName);
			
		    mailSession = Session.getInstance(properties,auth);
		    message = new MimeMessage(mailSession);
		    
		    // To 		 
		    if (toMail == null || toMail.size() == 0){
		    	toMail.add(fromMail);
		    }
		    
		   //  if (toMail != null && toMail.size() > 0){
			    it = toMail.iterator();
			    addressList = new LinkedList();
			    i = 0;
			    
			    // Filtro direcciones de emails vacias:
			    while (it.hasNext()) {
			    	to = (String) it.next();		
			    	if (!empty(to) && to.contains("@")) { 
			    		addressList.add(to);	    		
			    	}
			    	i++;
			    }
		    
			    // Armo lista de direcciones de emails definitiva:
			    
			    if (addressList.size() > 0){
			    	i = 0;
			    	addressTo = new InternetAddress[addressList.size()];
			    	it = addressList.iterator();
			    	while (it.hasNext()){
			    		to = (String) it.next();
			    		addressTo[i] = new InternetAddress(to,to);
			    		i++;
			    	}
			    }
		   // }
		    if (addressTo != null && addressTo.length > 0){
		    	// message.setRecipients(Message.RecipientType.TO, addressTo);
		    	message.addRecipients(Message.RecipientType.TO, addressTo);
		    }
		    // Fin TO:
		    
		    // BCC 
		    if (bccMail != null && bccMail.size() > 0){
			    it = bccMail.iterator();
			    addressList = new LinkedList();
			    i = 0;			    
			    // Filtro direcciones de emails vacias:
			    while (it.hasNext()) {
			    	bcc = (String) it.next();		
			    	if (!empty(bcc) && bcc.contains("@")) { 
			    		// bcc = "gbenaderet@cepasafedrive.com"; // TODO
			    		addressList.add(bcc);	    		
			    	}
			    	i++;
			    }		    
			    // Armo lista de direcciones de emails definitiva:			    
			    if (addressList.size() > 0){
			    	i = 0;
			    	addressBcc = new InternetAddress[addressList.size()];
			    	it = addressList.iterator();
			    	while (it.hasNext()){
			    		bcc = (String) it.next();
			    		addressBcc[i] = new InternetAddress(bcc,bcc);
			    		i++;
			    	}
			    }
		    }
		    // Si lleva copia oculta:
		    if (addressBcc != null && addressBcc.length > 0){
		    	message.addRecipients(Message.RecipientType.BCC, addressBcc);
		    	// 	message.setRecipients(Message.RecipientType.BCC,addressBcc);
			}
		    // Fin BCC 

		    if (ccMail != null && ccMail.size() > 0){
			    it = ccMail.iterator();
			    addressList = new LinkedList();
			    i = 0;			    
			    // Filtro direcciones de emails vacias:
			    while (it.hasNext()) {
			    	cc = (String) it.next();		
			    	if (!empty(cc) && cc.contains("@")) { 
			    		// bcc = "gbenaderet@cepasafedrive.com"; // TODO
			    		addressList.add(cc);	    		
			    	}
			    	i++;
			    }		    
			    // Armo lista de direcciones de emails definitiva:			    
			    if (addressList.size() > 0){
			    	i = 0;
			    	addresscc = new InternetAddress[addressList.size()];
			    	it = addressList.iterator();
			    	while (it.hasNext()){
			    		cc = (String) it.next();
			    		addresscc[i] = new InternetAddress(cc,cc);
			    		i++;
			    	}
			    }
		    }
		    // Si lleva copia oculta:
		    if (addresscc != null && addresscc.length > 0){
		    	message.addRecipients(Message.RecipientType.CC, addresscc);
		    	// 	message.setRecipients(Message.RecipientType.BCC,addressBcc);
			}

		   // Si tiene algun destinatario ahi si mando el mail
		    if ((addressBcc != null && addressBcc.length > 0) ||
		    		(addressTo != null && addressTo.length > 0) ){
		    
			    message.setFrom( new InternetAddress(fromMail,fromName) );			  
			    message.setSentDate(new Date());
			    message.setSubject(subject);
			    
			    if (replyToMail != null && replyToName != null){
			    	InternetAddress[] replyTo = new InternetAddress[1];
			    	replyTo[0] = new InternetAddress(replyToMail,replyToName);
			    	message.setReplyTo(replyTo);
			    }else{
			    	 InternetAddress[] replyTo = {new InternetAddress(fromMail,fromName)};
			         message.setReplyTo(replyTo);
			    }
			    
			    // En esta parte creamos el contenedor para el cuerpo html del mensaje
			    MimeBodyPart messageBodyPart = new MimeBodyPart();
			    messageBodyPart.setContent(body,"text/html");
			    Multipart multipart = new MimeMultipart();
			    multipart.addBodyPart(messageBodyPart);
			    
			    if(image != null) {
					MimeBodyPart imagen = new MimeBodyPart();
					imagen.attachFile(image);
					imagen.setHeader("Content-ID","<figura1>");
					multipart.addBodyPart(imagen);
			    }
			    
			    Iterator<String> atts = attachments.iterator();
			    Iterator<String> nameIt = names.iterator();
			    while(atts.hasNext()) {
			    	String attachment = atts.next();
			    	String name = nameIt.next();
				    // Aquí se anexa el attachment
				    messageBodyPart = new MimeBodyPart();
				    DataSource source = new FileDataSource(attachment);
				    messageBodyPart.setDataHandler(new DataHandler(source));
				    messageBodyPart.setFileName(name);
				    multipart.addBodyPart(messageBodyPart);
			    }
			    message.setContent(multipart);
			    
			    if (smtp.contains("gmail.com")){
		        	Transport.send(message);
		        }else{

		        	// Debug:
		        	// mailSession.setDebug(true);
		        	
		        	Transport.send(message);
		        	
		      //  	Transport transport = mailSession.getTransport(addressTo[0]);
		      //  	transport.connect(smtp, fromMail,password);
		       // 	message.saveChanges();
		       // 	transport.sendMessage(message,message.getAllRecipients());
		        	
		        	//System.out.println("Send Multipart Mail");
		        	// transport.close();
		        }
			
				return true;
		    }
		
	    }catch(com.sun.mail.smtp.SMTPSendFailedException e){
	    	//System.out.println("com.sun.mail.smtp.SMTPSendFailedException: 454 Atencao: voce atingiu o limite maximo de envio: 1000 mensagens em 60 minuto(s). Apos este periodo o envio volta ao normal.");
	    	e.printStackTrace();
	    	return false;
	    	
		}catch(Exception e){
		   	e.printStackTrace();
			return false;
		}
		
		System.out.println("No se envió el email, destinatarios vacios.");
		return false;
		
	}
*/	
	public boolean sendImageMandrill(Collection toMail,String fromName, String fromMail,
			String subject, String body, Collection<String> attachments,
			Collection<String> names, Collection bccMail, String image)
			throws Exception{
		return sendImageMandrill(toMail, fromName, fromMail, subject, body, attachments, names,  fromMail, fromName, bccMail, image);
	}


	 
	public boolean sendImageMandrill(Collection toMail,String fromName, String fromMail,
			String subject, String body, Collection<String> attachments,
			Collection<String> names, String replyToMail, String replyToName, Collection bccMail, String image)
			throws Exception{
	
	    try{
	    	
	    	Properties properties = new Properties();
			Authenticator auth = null;
			String to = null;
			String bcc = null;
			
			if(!SecurityConstants.isProductionServer()) {
				toMail = new LinkedList<String>();
				toMail.add("cepasafedrive@gmail.com");
				subject = "TESTING - "+subject;
				bccMail = new LinkedList<String>();
				bccMail.add("nguillermo@cepamobility.com");
			} 

			Session mailSession = null;
			LinkedList addressList = null;
			Iterator it = null;
			Message message;
			InternetAddress[] addressTo = null;
			InternetAddress[] addressBcc = null;
			int i = 0;
			
			final String username = "fcarriquiry@cepasafedrive.com";
	        final String password = "ukIBIPot5B-5fD2SBXtYQA";
	        final String host= "smtp.mandrillapp.com";
	        Properties props = new Properties();
	        props.put("mail.smtp.auth", "true");
	        //props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", "smtp.mandrillapp.com");
	        props.put("mail.smtp.port", "587");


	        mailSession = Session.getInstance(props,
	          new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(username, password);
	            }
	          });
			
		    message = new MimeMessage(mailSession);
		    
		    /* To */		 
		    if (toMail == null || toMail.size() == 0){
		    	toMail.add(fromMail);
		    }
		    
		   //  if (toMail != null && toMail.size() > 0){
			    it = toMail.iterator();
			    addressList = new LinkedList();
			    i = 0;
			    
			    // Filtro direcciones de emails vacias:
			    while (it.hasNext()) {
			    	to = (String) it.next();		
			    	if (!empty(to) && to.contains("@")) { 
			    		addressList.add(to);	    		
			    	}
			    	i++;
			    }
		    
			    // Armo lista de direcciones de emails definitiva:
			    
			    if (addressList.size() > 0){
			    	i = 0;
			    	addressTo = new InternetAddress[addressList.size()];
			    	it = addressList.iterator();
			    	while (it.hasNext()){
			    		to = (String) it.next();
			    		addressTo[i] = new InternetAddress(to,to);
			    		i++;
			    	}
			    }
		   // }
		    if (addressTo != null && addressTo.length > 0){
		    	// message.setRecipients(Message.RecipientType.TO, addressTo);
		    	message.addRecipients(Message.RecipientType.TO, addressTo);
		    }
		    /* Fin TO: */
		    
		    /* BCC */
		    if (bccMail != null && bccMail.size() > 0){
			    it = bccMail.iterator();
			    addressList = new LinkedList();
			    i = 0;			    
			    // Filtro direcciones de emails vacias:
			    while (it.hasNext()) {
			    	bcc = (String) it.next();		
			    	if (!empty(bcc) && bcc.contains("@")) { 
			    		// bcc = "gbenaderet@cepasafedrive.com"; // TODO
			    		addressList.add(bcc);	    		
			    	}
			    	i++;
			    }		    
			    // Armo lista de direcciones de emails definitiva:			    
			    if (addressList.size() > 0){
			    	i = 0;
			    	addressBcc = new InternetAddress[addressList.size()];
			    	it = addressList.iterator();
			    	while (it.hasNext()){
			    		bcc = (String) it.next();
			    		addressBcc[i] = new InternetAddress(bcc,bcc);
			    		i++;
			    	}
			    }
		    }
		    // Si lleva copia oculta:
		    if (addressBcc != null && addressBcc.length > 0){
		    	message.addRecipients(Message.RecipientType.BCC, addressBcc);
		    	// 	message.setRecipients(Message.RecipientType.BCC,addressBcc);
			}
		    /* Fin BCC */
		   
		   // Si tiene algun destinatario ahi si mando el mail
		    if ((addressBcc != null && addressBcc.length > 0) ||
		    		(addressTo != null && addressTo.length > 0) ){
		    
			    message.setFrom( new InternetAddress(fromMail,fromName) );			  
			    message.setSentDate(new Date());
			    message.setSubject(subject);
			    
			    if (replyToMail != null && replyToName != null){
			    	InternetAddress[] replyTo = new InternetAddress[1];
			    	replyTo[0] = new InternetAddress(replyToMail,replyToName);
			    	message.setReplyTo(replyTo);
			    }else{
			    	 InternetAddress[] replyTo = {new InternetAddress(fromMail,fromName)};
			         message.setReplyTo(replyTo);
			    }
			    
			    // En esta parte creamos el contenedor para el cuerpo html del mensaje
			    MimeBodyPart messageBodyPart = new MimeBodyPart();
			    messageBodyPart.setContent(body,"text/html");
			    Multipart multipart = new MimeMultipart();
			    multipart.addBodyPart(messageBodyPart);
			    
			    if(image != null) {
					MimeBodyPart imagen = new MimeBodyPart();
					imagen.attachFile(image);
					imagen.setHeader("Content-ID","<figura1>");
					multipart.addBodyPart(imagen);
			    }
			    
			    Iterator<String> atts = attachments.iterator();
			    Iterator<String> nameIt = names.iterator();
			    while(atts.hasNext()) {
			    	String attachment = atts.next();
			    	String name = nameIt.next();
				    // Aquí se anexa el attachment
				    messageBodyPart = new MimeBodyPart();
				    DataSource source = new FileDataSource(attachment);
				    messageBodyPart.setDataHandler(new DataHandler(source));
				    messageBodyPart.setFileName(name);
				    multipart.addBodyPart(messageBodyPart);
			    }
			    message.setContent(multipart);
			    
	        	Transport.send(message);
			
				return true;
		    }
		
	    }catch(com.sun.mail.smtp.SMTPSendFailedException e){
	    	//System.out.println("com.sun.mail.smtp.SMTPSendFailedException: 454 Atencao: voce atingiu o limite maximo de envio: 1000 mensagens em 60 minuto(s). Apos este periodo o envio volta ao normal.");
	    	e.printStackTrace();
	    	return false;
	    	
		}catch(Exception e){
		   	e.printStackTrace();
			return false;
		}
		
		System.out.println("No se envi� el email, destinatarios vacios: "+subject);
		return false;
		
	}
	 public static boolean sendMailMandrill(String body, String subject, String fromMail, Collection<String> toMails, Collection<String> toMailsCC, Collection<String> toMailsBCC) {
	        // TODO Auto-generated method stub
		 if(!SecurityConstants.isProductionServer()) {
			 toMails = new LinkedList<String>();
			 toMails.add("cepasafedrive@gmail.com");
			 subject = "TESTING - "+subject;
			 toMailsCC = new LinkedList<String>();
			 toMailsBCC = new LinkedList<String>();
			if(SecurityConstants.isTesingServer()) {
				toMailsBCC.add("jrodriguez@cepasafedrive.com");
				/*toMailsBCC.add("valeria.viera@upm.com");
				toMailsBCC.add("miguel.otegui@upm.com");
				toMailsBCC.add("gonzalo.buscio@upm.com");
				toMailsBCC.add("sandra.rodriguez@upm.com");*/
			}
		 }

	        final String username = "fcarriquiry@cepasafedrive.com";
	        final String password = "ukIBIPot5B-5fD2SBXtYQA";
	        final String host= "smtp.mandrillapp.com";
	        Properties props = new Properties();
	        props.put("mail.smtp.auth", "true");
	        //props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", "smtp.mandrillapp.com");
	        props.put("mail.smtp.port", "587");


	        Session session = Session.getInstance(props,
	          new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(username, password);
	            }
	          });

	        try {

	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(fromMail,"CEPA DataCenter"));
	            for(String mail: toMails){
	            	 message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
	            }
	            for(String mail: toMailsCC){
	            	 message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(mail));
	            }
	            for(String mail: toMailsBCC){
	            	 message.addRecipients(Message.RecipientType.BCC, InternetAddress.parse(mail));
	            }
	            
	            
	            message.setSubject(subject);
	            message.setContent(body,"text/html");

	            Transport.send(message);

	            System.out.println("Message sent successfully");

	            return true;
	        } catch (Throwable e) {
	            return false;
	        }
	        
	        
	    }

	public void send(String mail, String fromName, String fromMail, String subject, String body) throws Exception {
		Collection to = new LinkedList();
		to.add(mail);
		Collection bcc = new LinkedList();
		sendImageMandrill(to, fromName, fromMail, subject, body, new LinkedList(), new LinkedList(), fromMail, fromName, bcc, null);
	}
} 
