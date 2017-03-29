package com.pip.model;

/**
 * 
 * This is our model class containing
 * the attributes of an Email body
 * 
 * @author asara3
 *
 */
public class Email {
	
	/**
	 * Default Constructor
	 */
	public Email() {
			
	}

	private String hostName;
	private String portName;
	private String[] recipient;
	private String from;
	private String subject;
	private String body;
	private String[] file;
	
	/**
	 * @return the array of File
	 */
	public String[] getFile() {
		return file;
	}
	/**
	 * @param file
	 */
	public void setFile(String[] file) {
		this.file = file;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}
	/**
	 * @param body
	 */
	public void setBody(String body) {
		this.body = body;
	}
	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}
	/**
	 * @param from
	 */
	public void setFrom(String from) {
		this.from = from;
	}
	/**
	 * @return the array of recipients
	 */
	public String[] getRecipient() {
		return recipient;
	}
	/**
	 * @param strings
	 */
	public void setRecipient(String[] strings) {
		this.recipient = strings;
	}
	/**
	 * @return the hostName
	 */
	public String getHostName() {
		return hostName;
	}
	/**
	 * @param hostName
	 */
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	/**
	 * @return the portName
	 */
	public String getPortName() {
		return portName;
	}
	/**
	 * @param portName
	 */
	public void setPortName(String portName) {
		this.portName = portName;
	}
	
	@Override
	public String toString() {
		return "Email [hostName=" + hostName + ", portName=" + portName
				+ ", recipient=" + recipient + ", from=" + from + ", subject="
				+ subject + ", body=" + body + ", file=" + file + "]";
	}
}
