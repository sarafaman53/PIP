package com.pip.util;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

import org.junit.*;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import com.pip.model.Email;

/**
 * @author asara3
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ Message.class, Session.class, Transport.class, MimeMessage.class, InternetAddress.class,
	               MimeBodyPart.class} )
public class EmailUtilityTest {
	
	/**
	 * Mocked Classes
	 */
	public void performCommonMocking() {
		PowerMock.mockStatic(Message.class);
		PowerMock.mockStatic(Session.class);
		PowerMock.mockStatic(Transport.class);
		PowerMock.mockStatic(MimeMessage.class);
		PowerMock.mockStatic(MimeBodyPart.class);	
	}

	/**
	 * Test for the Email Utility
	 */
	@Test
	public void EmailUtility() {

		performCommonMocking();
		
		Email emailMessage = new Email();
		emailMessage.setHostName("localhost");
		emailMessage.setPortName("25");
		String[] recipients = new String [3];
		recipients[0] = "abc@abc.com";
		recipients[1] = "xyz@xyz.com";
		recipients[2] = "qwe@qwe.com";
		emailMessage.setRecipient(recipients);
		emailMessage.setFrom("robi@robi.com");
		emailMessage.setSubject("Test Email");
		emailMessage.setBody("Test Body");
		String[] files = new String[1];
		files[0] = "C:\\Users\\asara3\\Documents\\Architecture.jpg";
		emailMessage.setFile(files);
		
		try {
			Whitebox.invokeMethod(EmailUtility.class, "processEmail", emailMessage);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
	}
	
}
