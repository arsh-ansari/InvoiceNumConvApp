package com.invoice.exception.handlers;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.invoice.domain.ErrorDetails;
import com.invoice.exception.InvoiceBadRequestException;

/**
 * Controller Advice to handle Exceptions for Invoice Number conversion App
 * 
 * @author mohansar0
 * 
 */
@ControllerAdvice
@RestController
public class InvoiceExceptionHandler extends ResponseEntityExceptionHandler {

	private static Logger LOG = LoggerFactory
			.getLogger(InvoiceExceptionHandler.class);

	/**
	 * Handles All exceptions other than the InvoiceBadRequestException
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleInvoiceException(
			Exception ex, WebRequest request) {
		LOG.error("handleInvoiceException", ex);
		ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(),
				new Date(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails,
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(InvoiceBadRequestException.class)
	public final ResponseEntity<ErrorDetails> handleInvoiceNotFoundException(
			Exception ex, WebRequest request) {
		LOG.error("handleInvoiceNotFoundException", ex);
		ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(),
				new Date(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
}
