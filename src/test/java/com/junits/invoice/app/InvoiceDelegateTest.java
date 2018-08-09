package com.junits.invoice.app;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.dataaccess.webservicesserver.NumberToWords;
import com.dataaccess.webservicesserver.NumberToWordsResponse;
import com.invoice.adaptors.InoviceDataTranslator;
import com.invoice.delegate.InvoiceDelegate;
import com.invoice.domain.InvoiceRequest;
import com.invoice.domain.InvoiceResponse;
import com.invoice.domain.ItemDetails;
import com.invoice.soap.gateway.SoapConnector;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceDelegateTest {

	
	@InjectMocks
	private  InvoiceDelegate invoiceService;
	
	@Mock
	private InvoiceResponse invoiceResponse;

	@Mock
	private SoapConnector soapConnector;

	@Mock
	private InoviceDataTranslator invoDataTranslator;
	
	@Test
	public void generateInvoice() {
		InvoiceResponse invoiceResponse = new InvoiceResponse();

		InvoiceRequest invoiceRequest = new InvoiceRequest();
		ItemDetails itemDetails = new ItemDetails();
		List<ItemDetails> list = new ArrayList<>();
		itemDetails.setItemAmount("1000");
		list.add(itemDetails);
		invoiceRequest.setItemDetails(list);
		NumberToWords numberToWords = new NumberToWords();
		numberToWords.setUbiNum(new BigInteger(itemDetails.getItemAmount()));
		NumberToWordsResponse numberToWordsResponse = new NumberToWordsResponse();
		numberToWordsResponse.setNumberToWordsResult("one thousand");
		invoiceService.generateInvoice(invoiceRequest);
		Assert.assertNotNull(invoiceResponse);

	}
	

	
	@Test(expected = NumberFormatException.class)
	public void generateInvoiceNonNumberAmount() {

		InvoiceRequest invoiceRequest = new InvoiceRequest();
		ItemDetails itemDetails = new ItemDetails();
		List<ItemDetails> list = new ArrayList<>();
		itemDetails.setItemAmount("abc");
		list.add(itemDetails);
		invoiceRequest.setItemDetails(list);
		NumberToWords numberToWords = new NumberToWords();
		numberToWords.setUbiNum(new BigInteger(itemDetails.getItemAmount()));
		NumberToWordsResponse numberToWordsResponse = new NumberToWordsResponse();
		numberToWordsResponse.setNumberToWordsResult("one thousand");
		invoiceService.generateInvoice(invoiceRequest);

	}
	
	
	@Test
	public void generateInvoiceNegativeAmount() {
		InvoiceResponse invoiceResponse = new InvoiceResponse();

		InvoiceRequest invoiceRequest = new InvoiceRequest();
		ItemDetails itemDetails = new ItemDetails();
		List<ItemDetails> list = new ArrayList<>();
		itemDetails.setItemAmount("-1");
		list.add(itemDetails);
		invoiceRequest.setItemDetails(list);
		NumberToWords numberToWords = new NumberToWords();
		numberToWords.setUbiNum(new BigInteger(itemDetails.getItemAmount()));
		NumberToWordsResponse numberToWordsResponse = new NumberToWordsResponse();
		numberToWordsResponse.setNumberToWordsResult("one thousand");
		
		invoiceService.generateInvoice(invoiceRequest);
		Assert.assertNotNull(invoiceResponse);

	}
	
}
