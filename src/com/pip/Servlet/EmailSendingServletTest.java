package com.pip.Servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author asara3
 *
 */
@RunWith(PowerMockRunner.class)
public class EmailSendingServletTest extends Mockito{
	
	
	/**
	 * Test The Email Send Servlet
	 */
	@Test
	public void TestEmailSendingServlet() {
		 
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
		
		try {
			servlet.doPost(request, response);
		} catch (ServletException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
}
