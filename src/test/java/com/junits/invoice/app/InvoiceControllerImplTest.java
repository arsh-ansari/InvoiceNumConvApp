package com.junits.invoice.app;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.invoice.controller.impl.InvoiceControllerImpl;
import com.invoice.delegate.InvoiceDelegate;
import com.invoice.domain.InvoiceRequest;
import com.invoice.domain.InvoiceResponse;
import com.invoice.domain.ItemDetails;
import com.invoice.exception.InvoiceBadRequestException;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceControllerImplTest {

	@InjectMocks
	private  InvoiceControllerImpl controller;
	
	@Mock
	private InvoiceDelegate invoiceService;

	
	@Test
	public void generateInvoice() throws InvoiceBadRequestException  {
		InvoiceResponse invoiceResponse = new InvoiceResponse();
		
		InvoiceRequest invoiceRequest = new InvoiceRequest();
		ItemDetails itemDetails = new ItemDetails();
		List<ItemDetails> list = new  ArrayList<>();
		itemDetails.setItemAmount("1000");
		list.add(itemDetails);
		invoiceRequest.setItemDetails(list);
		Mockito.when(invoiceService.generateInvoice(invoiceRequest)).thenReturn(invoiceResponse);
		controller.generateInvoice(invoiceRequest);
		Assert.assertNotNull(invoiceResponse);
		
	}
	
}
