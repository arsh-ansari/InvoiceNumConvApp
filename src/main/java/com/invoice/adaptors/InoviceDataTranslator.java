package com.invoice.adaptors;

import java.math.BigInteger;

import org.springframework.stereotype.Component;

import com.dataaccess.webservicesserver.NumberToWords;
import com.dataaccess.webservicesserver.NumberToWordsResponse;
import com.invoice.domain.ItemDetails;

/**
 * Data Translator for mapping 
 * <li>Input Request  for invoice into the web-service format</li> 
 * <li>web service response to rest response.</li>
 * 
 * @author mohansar0
 *
 */
@Component
public class InoviceDataTranslator {

	
	/**
	 * Maps Input Item details to WS Request object - NumberToWords 
	 * @param itemDetails
	 * @return
	 */
	public NumberToWords mapInputDomainRequestToWSRequest(ItemDetails itemDetails){
		NumberToWords numberToWords = new NumberToWords();
		//set the request with item amount
		numberToWords.setUbiNum(new BigInteger(itemDetails.getItemAmount()));
		return numberToWords;
	}
	
	/**
	 * Map WS reponse to invoice domain response.
	 * 
	 * @param numberToWordsResponse
	 * @param itemDetails
	 */
	public void mapWSResponseToDomainResponse(NumberToWordsResponse numberToWordsResponse,ItemDetails itemDetails){
		//set the amount in words
		itemDetails.setItemAmountinWords(numberToWordsResponse.getNumberToWordsResult());
	}
}
