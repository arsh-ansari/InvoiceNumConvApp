package com.invoice.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.domain.InvoiceRequest;
import com.invoice.domain.InvoiceResponse;
import com.invoice.exception.InvoiceBadRequestException;

/**
 * The Rest controller exposing the invoice rest services.
 * <li>generateInvoice</li>
 * 
 * @author mohansar0
 *
 */
@RestController
public interface InvoiceController {

	/**
	 * The generateInvoice Rest service.
	 * This service consumes Json input for Invoice Request and sends the amount in words.
	 * 
	 * @param invoiceRequest
	 * @return
	 * @throws InvoiceBadRequestException 
	 */
	@RequestMapping(value = "/generateInvoice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<InvoiceResponse> generateInvoice(@RequestBody InvoiceRequest invoiceRequest);
}
