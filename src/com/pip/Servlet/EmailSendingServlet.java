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

public class EmailSendingServlet extends HttpServlet{
	
	Email emailMessage = new Email();
	
	public void init() {
		ServletContext context = getServletContext();
		emailMessage.setHostName(context.getInitParameter("host"));
		emailMessage.setPortName(context.getInitParameter("port"));	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		emailMessage.setFrom(request.getParameter("from"));
		emailMessage.setRecipient(request.getParameter("recipients"));
		emailMessage.setSubject(request.getParameter("subject"));
		emailMessage.setBody(request.getParameter("body"));
		emailMessage.setFile(request.getParameter("file"));
		
		String resultMessage = "";
		
		try {
			EmailUtility.sendEmail(emailMessage);
			resultMessage = "The Email was sent successfully";
			request.setAttribute("message", resultMessage);
			RequestDispatcher view = request.getRequestDispatcher("/Result.jsp");
			view.forward(request, response);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
}
