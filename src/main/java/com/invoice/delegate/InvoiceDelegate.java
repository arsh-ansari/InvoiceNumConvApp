package com.invoice.delegate;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dataaccess.webservicesserver.NumberToWords;
import com.dataaccess.webservicesserver.NumberToWordsResponse;
import com.invoice.adaptors.InoviceDataTranslator;
import com.invoice.domain.InvoiceRequest;
import com.invoice.domain.InvoiceResponse;
import com.invoice.domain.ItemDetails;
import com.invoice.exception.InvoiceBadRequestException;
import com.invoice.soap.gateway.SoapConnector;

/**
 * The Invoice Delegate class used for delegating the request to Soap Connector
 * for invoking the web service for Number conversion
 * 
 * @author mohansar0
 * 
 */
@Service
public class InvoiceDelegate {

	private static Logger LOG = LoggerFactory.getLogger(InvoiceDelegate.class);

	@Value("${numberconversion.service.uri}")
	private String serviceURL;

	@Autowired
	private InvoiceResponse invoiceResponse;

	@Autowired
	private SoapConnector soapConnector;

	@Autowired
	private InoviceDataTranslator invoDataTranslator;

	/**
	 * Delegates the invoice request to generate invoice amount in words using
	 * the soap connector to invoke the web service for number conversion.
	 * 
	 * @param invoiceRequest
	 * @return
	 * @throws InvoiceBadRequestException
	 */
	public InvoiceResponse generateInvoice(InvoiceRequest invoiceRequest) {

		List<ItemDetails> lstItemDetails = invoiceRequest.getItemDetails();
		for (ItemDetails item : lstItemDetails) {

			// Handling a not number scenario
			try {
				Float.parseFloat(item.getItemAmount());
			} catch (NumberFormatException e) {
				LOG.error("Input is not a number", e);
				throw new InvoiceBadRequestException(e);
			}

			// Number cannot be less than zero for amount
			if (Float.parseFloat(item.getItemAmount()) < 0) {
				item.setError(true);
				item.setItemAmountinWords("Bad Number :" + item.getItemAmount());
			} else {
				// prepare the request for web-service
				NumberToWords numberToWords = invoDataTranslator
						.mapInputDomainRequestToWSRequest(item);
				if (null != numberToWords) {
					item.setItemAmount(item.getItemAmount());
				}

				// web service invocation via the soap connector
				NumberToWordsResponse numberToWordsResponse = ((NumberToWordsResponse) soapConnector
						.marshalSendAndReceive(serviceURL, numberToWords));
				
				if (null != numberToWordsResponse) {
					// Map the web-service response
					invoDataTranslator.mapWSResponseToDomainResponse(
							numberToWordsResponse, item);
				}
				
				
			}
		}

		invoiceResponse.setLstItemDetails(lstItemDetails);

		return invoiceResponse;
	}

}
