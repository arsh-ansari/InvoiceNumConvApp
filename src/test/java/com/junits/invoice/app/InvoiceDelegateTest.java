package com.junits.invoice.app;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;

import com.dataaccess.webservicesserver.NumberToWords;
import com.dataaccess.webservicesserver.NumberToWordsResponse;
import com.invoice.adaptors.InoviceDataTranslator;
import com.invoice.delegate.InvoiceDelegate;
import com.invoice.domain.InvoiceRequest;
import com.invoice.domain.InvoiceResponse;
import com.invoice.domain.ItemDetails;
import com.invoice.exception.InvoiceBadRequestException;
import com.invoice.soap.gateway.SoapConnector;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceDelegateTest {

	
	@InjectMocks
	private  InvoiceDelegate invoiceService;
	
	@Mock
	private SoapConnector soapConnector;

	@Mock
	private InoviceDataTranslator invoDataTranslator;
	
	@Value("${numberconversion.service.uri}")
	private String serviceURL;
	
	@Test
	public void generateInvoice_LstItemDetails_Null(){
		InvoiceRequest invoiceRequest = new InvoiceRequest();
		invoiceRequest.setItemDetails(null);
		InvoiceResponse invoiceResponse = invoiceService.generateInvoice(invoiceRequest);
		List<ItemDetails> lstItemDetails = invoiceResponse.getLstItemDetails();
		Assert.assertNull(lstItemDetails);
	}
	
	@Test(expected = InvoiceBadRequestException.class)
	public void generateInvoice_BadRequestException_NotANumber(){
		InvoiceRequest invoiceRequest = new InvoiceRequest();
		List<ItemDetails> lstItemDetails = new ArrayList<>();
		ItemDetails itemDetails = new ItemDetails();
		itemDetails.setItemAmount("ABC");
		lstItemDetails.add(itemDetails);
		invoiceRequest.setItemDetails(lstItemDetails);
		invoiceService.generateInvoice(invoiceRequest);
	}
	
	@Test
	public void generateInvoice_InvalidAmountValue(){
		InvoiceRequest invoiceRequest = new InvoiceRequest();
		List<ItemDetails> lstItemDetails = new ArrayList<>();
		ItemDetails itemDetails = new ItemDetails();
		itemDetails.setItemAmount("-10");
		lstItemDetails.add(itemDetails);
		invoiceRequest.setItemDetails(lstItemDetails);
		InvoiceResponse  invoiceRes =invoiceService.generateInvoice(invoiceRequest);
		
		Assert.assertNotNull(invoiceRes);
		List<ItemDetails> lstDetails = invoiceRes.getLstItemDetails();
		Assert.assertNotNull(lstDetails);
		
		ItemDetails itemDetail = lstDetails.get(0);
		Assert.assertNotNull(itemDetail);
		
		Assert.assertEquals(true, itemDetail.isError());
		Assert.assertEquals("Bad Number :-10", itemDetail.getItemAmountinWords());
	}
	
	@Test
	public void generateInvoice() {
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
		Mockito.when(invoDataTranslator.mapInputDomainRequestToWSRequest(itemDetails)).thenReturn(numberToWords);
		Mockito.when(
				soapConnector
						.marshalSendAndReceive(
								serviceURL,
								numberToWords)).thenReturn(
				(Object)numberToWordsResponse);
		InvoiceResponse invoiceResponse = invoiceService.generateInvoice(invoiceRequest);
		Assert.assertNotNull(invoiceResponse);

	}
	
}
