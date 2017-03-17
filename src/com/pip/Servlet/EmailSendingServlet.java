package com.pip.Servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.pip.model.Email;
import com.pip.util.EmailUtility;

/**
 * This class is the Servlet Class
 * 
 * @author asara3
 *
 */
public class EmailSendingServlet extends HttpServlet{
	
	private static final long serialVersionUID = -7796409155466523414L;
	
	/**
	 * Creates an Email Model Object
	 */
	Email emailMessage = new Email();
	
	/**
	 * Overrides the Service method of Generic Servlet 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext context = getServletContext();
		emailMessage.setHostName(context.getInitParameter("host"));
		emailMessage.setPortName(context.getInitParameter("port"));	
		
		emailMessage.setFrom(request.getParameter("from"));
		emailMessage.setRecipient(request.getParameterValues("recipients"));
		emailMessage.setSubject(request.getParameter("subject"));
		emailMessage.setBody(request.getParameter("body"));
		emailMessage.setFile(request.getParameterValues("file"));
		
		String resultMessage = "";
		
		try {
			EmailUtility.sendEmail(emailMessage);
			resultMessage = "The Email was sent successfully";
			request.setAttribute("message", resultMessage);
			response.setContentType("text/html");
			RequestDispatcher view = request.getRequestDispatcher("/Result.jsp");
			view.forward(request, response);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
}
