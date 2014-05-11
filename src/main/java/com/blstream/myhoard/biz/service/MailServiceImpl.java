package com.blstream.myhoard.biz.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

public class MailServiceImpl implements MailService{
	
	private static final Logger logger = Logger.getLogger(MailServiceImpl.class.getCanonicalName());
	
	public void sendNotification() {
		
	      String to = "krzyszek@gmail.com";
	      String from = "noreply@myhoard.com";
	      String host = "localhost";
	      Properties properties = System.getProperties();
	      properties.setProperty("mail.smtp.host", host);
	      Session session = Session.getDefaultInstance(properties);
	      
	      try{
	         MimeMessage message = new MimeMessage(session);
	         message.setFrom(new InternetAddress(from));
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	         message.setSubject("This is the Subject Line!");
	         message.setText("This is actual message");
	         Transport.send(message);
	         logger.info("Sent message successfully....");
	      }catch (MessagingException mex) {
	         logger.error("Sent message failed");
	      }
	}
}
