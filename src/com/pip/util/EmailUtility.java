package com.pip.util;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import com.pip.model.Email;

/**
 * <p> This is the Email Utility Class which 
 * processes the parameters set from the model object 
 * through the controller and processes it to construct an
 * Email. <p>
 * 
 * @author asara3
 *
 */
public class EmailUtility {
	
	static Logger log = Logger.getLogger(EmailUtility.class);
	
	/**
	 * Instantiates a new Email Utility
	 * 
	 */
	EmailUtility() {
		
	}

	/**
	 * Populates the Email Object and sets them
	 * accordingly in the message object
	 * 
	 * @param emailMessage
	 * @throws Exception
	 */
	public static void sendEmail(Email emailMessage) throws Exception {
		
		log.info("Email Processing starts");
		processEmail(emailMessage);
		log.info("Email Processing Completed");
		
	}

	/**
	 * Sets the Email Object in the
	 * message object accordingly
	 * 
	 * @param emailMessage
	 * @throws Exception
	 */
	private static void processEmail(Email emailMessage) throws Exception {
		
		log.info("Creation of Property & Message Starts");
		Message message = createPropertyAndMessage(emailMessage);
		log.info("Propery & Message set successfully");
		String[] recipientList = emailMessage.getRecipient();
		InternetAddress[] recipientaddresses = new InternetAddress[recipientList.length];
		int counter = 0;
		for(String recipient: recipientList) {
			recipientaddresses[counter] = new InternetAddress(recipient);
			counter++;
		}
		
		message.setRecipients(Message.RecipientType.TO,recipientaddresses);
		setFromAddress(emailMessage, message);
		message.setSubject(emailMessage.getSubject());
		log.info("Subject Set successfully");
		message.setContent(emailMessage.getBody(), "text/plain");
		log.info("Content set successfully");
		setAttachment(emailMessage, message);
		log.info("Attachment Added successfully");
		message.saveChanges();
		log.info("Message saved successfully");
		Transport.send(message);
		log.info("Message sent successfully");
	}

	/**
	 * 
	 * Sets the Email Attachment
	 * 
	 * @param emailMessage
	 * @param message
	 * @throws Exception
	 */
	private static void setAttachment(Email emailMessage, Message message) throws Exception {
		log.info("Adding Attachment");
		MimeBodyPart bodyPart1 = new MimeBodyPart();
		bodyPart1.setContent(emailMessage.getBody(), "text/html");
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(bodyPart1);
		if (!(emailMessage.getFile()[0].equals(""))) {
		for (String filepath: emailMessage.getFile()) {
			MimeBodyPart bodyPart2 = new MimeBodyPart();
			bodyPart2.attachFile(filepath);
			multipart.addBodyPart(bodyPart2);
		}	
		}
		message.setContent(multipart);
		
	}
	
	/**
	 * Sets the From Address
	 * 
	 * @param emailMessage
	 * @param message
	 * @throws Exception
	 */
	private static void setFromAddress(Email emailMessage, Message message) throws Exception {
		
		log.info("Set the From Address Starts");
		if (emailMessage.getRecipient().equals(null)) {
			message.setFrom();
		}
		else {
			message.setFrom(new InternetAddress(emailMessage.getFrom()));
		}
		
		log.info("Setting of From Address Completes");
		
	}

	/**
	 * 
	 * Accepts the given parameters and creates the
	 * messages and properties object
	 * 
	 * @param emailMessage
	 * @return
	 */
	private static Message createPropertyAndMessage(Email emailMessage) {
		log.info("Property & Message setting starts");
		Properties properties = new Properties();
		properties.put("mail.smtp.host", emailMessage.getHostName());
		properties.put("mail.smtp.port", emailMessage.getPortName());
		Session session = Session.getDefaultInstance(properties);
		Message message = new MimeMessage(session);
		return message;
	}
	

}
