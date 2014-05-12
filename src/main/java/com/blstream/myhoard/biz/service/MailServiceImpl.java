package com.blstream.myhoard.biz.service;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

public class MailServiceImpl implements MailService{
	
	private List<String> recipients;
	private final String SENDER = "noreply@myhoard.com";
	private final String HOST = "localhost";
	private String title;
	private String message;
	
	private static final Logger logger = Logger.getLogger(MailServiceImpl.class.getCanonicalName());
	
	public List<String> getRecipients() {
		return recipients;
	}

	public void setRecipients(List<String> recipients) {
		this.recipients = recipients;
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

	public void setMessage(String message) {
		this.message = message;
	}

	public void sendNotification() {
	      
		if(this.getRecipients().size() > 0) {
	      Properties properties = System.getProperties();
	      properties.setProperty("mail.smtp.host", HOST);
	      Session session = Session.getDefaultInstance(properties);
	      
	      try{
	    	  
	         MimeMessage message = new MimeMessage(session);
	         message.setFrom(new InternetAddress(SENDER));
	         for(String email : this.getRecipients()) {
	        	 message.addRecipient(Message.RecipientType.TO, new InternetAddress(email)); 
	         }
	         message.setSubject(this.getTitle());
	         message.setText(this.getMessage());
		         Transport.send(message);
		         logger.info("Sent message successfully...");
	      } catch (MessagingException mex) {
	         logger.error("Sent message failed");
	      }
		}
		else {
			logger.info("No definitions of recipients. Email cannot be send");
		}
	}
}
