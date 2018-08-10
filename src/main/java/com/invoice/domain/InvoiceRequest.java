package com.invoice.domain;

import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Invoice Request object having item details.
 * @author mohansar0
 *
 */
@Component
public class InvoiceRequest {

	private List<ItemDetails> itemDetails;

	public List<ItemDetails> getItemDetails() {
		return this.itemDetails;
	}

	public void setItemDetails(List<ItemDetails> itemDetails) {
		this.itemDetails = itemDetails;
	}

}
