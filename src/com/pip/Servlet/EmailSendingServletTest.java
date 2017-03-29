package com.pip.Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * 
 * This is our Servlet Test class which
 * tests the controller for its email sending
 * functionality
 * 
 * @author asara3
 *
 */
@RunWith(PowerMockRunner.class)
public class EmailSendingServletTest extends Mockito{
	
	Logger log = Logger.getLogger(EmailSendingServletTest.class);
	
	/**
	 * Test The Email Send Servlet
	 */
	@Test
	public void TestEmailSendingServlet() {
		 
		log.info("Testing Starts");
		final ServletContext context = mock(ServletContext.class);
		EmailSendingServlet servlet= new EmailSendingServlet() {
			public ServletContext getServletContext() {
				return context;
			}
		};
		
		Mockito.when(context.getInitParameter("host")).thenReturn("localhost");
		Mockito.when(context.getInitParameter("port")).thenReturn("25");
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
		
		when(request.getParameter("from")).thenReturn("robi@robi.com");
		String[] recipients = new String [3];
		recipients[0] = "abc@abc.com";
		recipients[1] = "xyz@xyz.com";
		recipients[2] = "qwe@qwe.com";
		when(request.getParameterValues("recipients")).thenReturn(recipients);
		when(request.getParameter("subject")).thenReturn("Test Mail");
		when(request.getParameter("body")).thenReturn("This is Body");
		String[] files = new String[1];
		files[0] = "C:\\Users\\asara3\\Documents\\Architecture.jpg";
		when(request.getParameterValues("file")).thenReturn(files);
		when(request.getRequestDispatcher("/Result.jsp")).thenReturn(requestDispatcher);
		
		log.info("Email Model Set Successfully");
		try {
			servlet.doPost(request, response);
			
			log.info("Email Sent Successfully");
		} catch (ServletException e) {
			
			log.error("Servlet Exception");
		} catch (IOException e) {
			
			log.error("IO Exception");
		}
		
		
	}
	
}
