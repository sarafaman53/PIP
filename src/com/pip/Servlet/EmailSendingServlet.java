package com.pip.Servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pip.model.Email;
import com.pip.util.EmailUtility;

/**
 * <p>This class is our controller which takes the 
 * request parameters from the Email JSP, SMTP settings
 * from the properties file and forwards the paramters to
 * the Email Utility class for processing the Email.
 * Finally, the output from the Email Utility class is 
 * handled by the controller itself and the final result
 * is forwarded to the final JSP. <p>
 * 
 * @author asara3
 *
 */
public class EmailSendingServlet extends HttpServlet{
	
	Logger log = Logger.getLogger(EmailSendingServlet.class);
	
	private static final long serialVersionUID = -7796409155466523414L;
	
	/**
	 * Creates an Email Model Object
	 */
	Email emailMessage = new Email();
	
	/**
	 * Overrides the Service method of Generic Servlet Class
	 * and implements the HTTPServlet interface to process request 
	 * and response for our requirement respectively. 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		log.info("Inside do Post Method");
		
		ServletContext context = getServletContext();
		InputStream input = context.getResourceAsStream("/WEB-INF/Email.properties");
		Properties prop = new Properties();
		prop.load(input);
		
		log.info("Property loaded successfully");
		emailMessage.setHostName(prop.getProperty("hostName"));
		emailMessage.setPortName(prop.getProperty("portName"));	
		
		emailMessage.setFrom(request.getParameter("from"));
		emailMessage.setRecipient(request.getParameterValues("recipients"));
		emailMessage.setSubject(request.getParameter("subject"));
		emailMessage.setBody(request.getParameter("body"));
		emailMessage.setFile(request.getParameterValues("file"));
		
		log.info("Model Updated Successfully");
		
		String resultMessage = "";
		
		try {
			EmailUtility.sendEmail(emailMessage);
			
			log.info("Email Processed Successfully");
			
			resultMessage = "The Email was sent successfully";
			request.setAttribute("message", resultMessage);
			response.setContentType("text/html");
			RequestDispatcher view = request.getRequestDispatcher("/Result.jsp");
			view.forward(request, response);
			
			log.info("Email Sent Successfully");
			
		} catch (Exception e) {
			
			log.error("Email Sending Failed");
		}
	}
	
}
