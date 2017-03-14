package com.pip.util;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.pip.model.Email;


public class EmailUtility {
	
	public static void sendEmail(Email emailMessage) throws Exception {
		
		processEmail(emailMessage);
		
	}

	private static void processEmail(Email emailMessage) throws Exception {
		
		Message message = createPropertyAndMessage(emailMessage);
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse
				(emailMessage.getRecipient(),Boolean.TRUE));
		setFromAddress(emailMessage, message);
		message.setSubject(emailMessage.getSubject());
		message.setContent(emailMessage.getBody(), "text/plain");
		setEmailAttachment(emailMessage, message);
		message.saveChanges();
		Transport.send(message);
	}

	private static void setEmailAttachment(Email emailMessage, Message message) throws Exception {
		
		if (!emailMessage.getFile().equals(null) && !emailMessage.getFile().equals("")) {
			
			setAttachment(emailMessage, message);
		}
		
	}

	private static void setAttachment(Email emailMessage, Message message) throws Exception {
		
		MimeBodyPart bodyPart1 = new MimeBodyPart();
		bodyPart1.setContent(message, "text/html");
		
		MimeBodyPart bodyPart2 = new MimeBodyPart();
		FileDataSource fileDataSource = new FileDataSource( emailMessage.getFile());
		bodyPart2.setDataHandler(new DataHandler(fileDataSource));
		bodyPart2.setFileName(fileDataSource.getName());
		
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(bodyPart1);
		multipart.addBodyPart(bodyPart2);
		
		message.setContent(multipart);
		
	}

	private static void setFromAddress(Email emailMessage, Message message) throws Exception {
		
		if (emailMessage.getRecipient().equals(null)) {
			message.setFrom();
		}
		else {
			message.setFrom(new InternetAddress(emailMessage.getFrom()));
		}
		
	}

	private static Message createPropertyAndMessage(Email emailMessage) {

		Properties properties = new Properties();
		properties.put("mail.smtp.host", emailMessage.getHostName());
		properties.put("mail.smtp.port", emailMessage.getPortName());
		Session session = Session.getDefaultInstance(properties);
		Message message = new MimeMessage(session);
		return message;
	}
	

}
