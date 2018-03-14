/**
 * 
 */
package com.palmcommerce.funds.alert.service.impl;

/**
 * @author Administrator
 *
 */
public class EmailProperty {
	
	
			
			
	public String getFrom() {
		return from;
	}


	public void setFrom(String from) {
		this.from = from;
	}


	public String getHost() {
		return host;
	}


	public void setHost(String host) {
		this.host = host;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	String from;
	String host;
	String subject;
	String username;
	

	/**
	 * 
	 */
	public EmailProperty() {
		// TODO Auto-generated constructor stub
	}

}
