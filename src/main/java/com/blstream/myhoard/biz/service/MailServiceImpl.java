package com.blstream.myhoard.biz.service;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

public class MailServiceImpl implements MailService{
	
	private List<String> recipients;
	private String host;
	private String port;
	private String username;
	private String password;
	private String title;
	private String message;
	
	private static final Logger logger = Logger.getLogger(MailServiceImpl.class.getCanonicalName());
	
	public List<String> getRecipients() {
		return recipients;
	}

	public void setRecipients(List<String> recipients) {
		this.recipients = recipients;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public void sendMessage() {
	      
		if(this.getRecipients().size() > 0) {
			
	      Properties properties = System.getProperties();
	      properties.setProperty("mail.smtp.host", host);
	      properties.setProperty("mail.smtp.port", port);
	      
	      properties.setProperty("mail.smtp.auth", "true");
	      properties.setProperty("mail.smtp.starttls.enable", "true");
	      
	      //Session session = Session.getDefaultInstance(properties);
	      
	      Session session = Session.getInstance(properties,
	    	      new javax.mail.Authenticator() {
	    	         protected PasswordAuthentication getPasswordAuthentication() {
	    	            return new PasswordAuthentication(username, password);
	    	         }
	    	      });
	      
	      try{
	    	  
	         MimeMessage msg = new MimeMessage(session);
	         msg.setFrom(new InternetAddress(username));
	         for(String email : this.getRecipients()) {
	        	 msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email)); 
	         }
	         msg.setSubject(title);
	         msg.setText(message);
	         Transport.send(msg);
	         logger.info("Sent message successfully to: ");
	         for(String email : this.getRecipients()) {
	        	 logger.info(email + ", ");
	         }
	      } catch (MessagingException mex) {
	         logger.error("Sent message failed [ " + username + " " + host + " " + port + "]");
	      }
		}
		else {
			logger.info("No definitions of recipients. Email cannot be send");
		}
	}
}
