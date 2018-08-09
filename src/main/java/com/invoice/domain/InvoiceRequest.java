package com.invoice.domain;

import java.util.List;

import org.springframework.stereotype.Component;

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
