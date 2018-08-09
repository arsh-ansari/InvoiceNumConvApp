package com.invoice.controller.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.invoice.controller.InvoiceController;
import com.invoice.delegate.InvoiceDelegate;
import com.invoice.domain.InvoiceRequest;
import com.invoice.domain.InvoiceResponse;

/**
 * This is implementation of Invoice rest service.
 * 
 * @author mohansar0
 * 
 */
@Service
public class InvoiceControllerImpl implements InvoiceController {

	private static Logger LOG = LoggerFactory
			.getLogger(InvoiceControllerImpl.class);

	@Autowired
	private InvoiceDelegate invoiceService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.invoice.controller.InvoiceController#generateInvoice(com.invoice.
	 * domain.InvoiceRequest)
	 */
	@Override
	public @ResponseBody
	ResponseEntity<InvoiceResponse> generateInvoice(
			@RequestBody InvoiceRequest invoiceRequest) {
		LOG.info("Processing the invoice number conversion");
		InvoiceResponse invoiceResponse = invoiceService
				.generateInvoice(invoiceRequest);

		if (invoiceResponse != null) {
			return new ResponseEntity<InvoiceResponse>(invoiceResponse,
					HttpStatus.CREATED);
		}

		return new ResponseEntity<InvoiceResponse>(invoiceResponse,
				HttpStatus.BAD_REQUEST);
	}

}
