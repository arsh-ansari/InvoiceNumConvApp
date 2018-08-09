package com.invoice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvoiceBadRequestException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2816955516445225294L;

	public InvoiceBadRequestException(Exception exception) {
		// TODO Auto-generated constructor stub
		super(exception);
	}
	
}
