package com.invoice.domain;

import java.util.Date;

/**
 * Error details object used in case of errors from web services or validation exceptions
 * @author mohansar0
 *
 */
public class ErrorDetails {

	
	private String message;
	private Date timeStamp;
	private String details;

	/**
	 * @param message
	 * @param timeStamp
	 * @param details
	 */
	public ErrorDetails(String message, Date timeStamp, String details){
		super();
		this.details = details;
		this.message = message;
		this.timeStamp = timeStamp;
	}

	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the timeStamp
	 */
	public Date getTimeStamp() {
		return timeStamp;
	}

	/**
	 * @param timeStamp
	 *            the timeStamp to set
	 */
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * @param details
	 *            the details to set
	 */
	public void setDetails(String details) {
		this.details = details;
	}
}