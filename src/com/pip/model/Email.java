package com.pip.model;

import java.io.File;
import java.util.List;

public class Email {
	
	private String hostName;
	private String portName;
	private String recipient;
	private String from;
	private String subject;
	private String body;
	private String file;
	
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getPortName() {
		return portName;
	}
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
