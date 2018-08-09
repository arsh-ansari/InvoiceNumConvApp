package com.invoice.domain;

import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Response class for generate invoice rest service.
 * Contains attributes for Invoice response.
 * 
 * @author mohansar0
 *
 */
@Component
public class InvoiceResponse {

	private List<ItemDetails> lstItemDetails;
	
	/**
	 * @return the lstItemDetails
	 */
	public List<ItemDetails> getLstItemDetails() {
		return lstItemDetails;
	}
	/**
	 * @param lstItemDetails the lstItemDetails to set
	 */
	public void setLstItemDetails(List<ItemDetails> lstItemDetails) {
		this.lstItemDetails = lstItemDetails;
	}
}
