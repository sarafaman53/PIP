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

import com.pip.model.Email;

/**
 * This class performs
 * commong Email Utilities
 * 
 * @author asara3
 *
 */
public class EmailUtility {
	
	/**
	 * Instantiates a new Email Utility
	 * 
	 */
	private EmailUtility() {
		
	}

	/**
	 * Populates the Email Object and sets them
	 * accordingly in the message object
	 * 
	 * @param emailMessage
	 * @throws Exception
	 */
	public static void sendEmail(Email emailMessage) throws Exception {
		
		processEmail(emailMessage);
		
	}

	/**
	 * Sets the Email Object in the
	 * message object accordingly
	 * 
	 * @param emailMessage
	 * @throws Exception
	 */
	private static void processEmail(Email emailMessage) throws Exception {
		
		Message message = createPropertyAndMessage(emailMessage);
		String[] recipientList = emailMessage.getRecipient();
		InternetAddress[] recipientaddresses = new InternetAddress[emailMessage.getRecipient().length];
		int counter = 0;
		for(String recipient: recipientList) {
			recipientaddresses[counter] = new InternetAddress(recipient);
			counter++;
		}
		
		message.setRecipients(Message.RecipientType.TO,recipientaddresses);
		setFromAddress(emailMessage, message);
		message.setSubject(emailMessage.getSubject());
		message.setContent(emailMessage.getBody(), "text/plain");
		setAttachment(emailMessage, message);
		message.saveChanges();
		Transport.send(message);
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
		
		if (emailMessage.getRecipient().equals(null)) {
			message.setFrom();
		}
		else {
			message.setFrom(new InternetAddress(emailMessage.getFrom()));
		}
		
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

		Properties properties = new Properties();
		properties.put("mail.smtp.host", emailMessage.getHostName());
		properties.put("mail.smtp.port", emailMessage.getPortName());
		Session session = Session.getDefaultInstance(properties);
		Message message = new MimeMessage(session);
		return message;
	}
	

}
