package com.invoice.soap.gateway;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.transport.WebServiceMessageSender;
import org.springframework.ws.transport.http.HttpUrlConnectionMessageSender;

/**
 * The Soap connector class having methods for calling the web service using
 * spring web service template.
 * 
 * @author mohansar0
 * 
 */
public class SoapConnector extends WebServiceGatewaySupport {

	@Value("${invoice.ws.readtimeout}")
	private String readTimeout;

	@Value("${invoice.ws.connectiontimeout}")
	private String connectionTimeout;

	/**
	 * Call web service using the end point URL and request pay load.
	 * 
	 * @param url
	 * @param request
	 * @return
	 */
	public Object callWebService(String url, Object request) {

		return getWebServiceTemplate().marshalSendAndReceive(url, request);
	}

	/**
	 * 
	 * @param endpoint
	 * @param requestPayload
	 * @return
	 */
	public Object marshalSendAndReceive(String endpoint, Object requestPayload) {

		WebServiceTemplate wsTemplate = getWebServiceTemplate();
		WebServiceMessageSender[] senders = wsTemplate.getMessageSenders();
		for (WebServiceMessageSender sender : senders) {
			try {
				int readTimeoutMsec = Integer.parseInt(readTimeout);
				int connTimeoutMsec = Integer.parseInt(connectionTimeout);
				HttpUrlConnectionMessageSender httpSender = (HttpUrlConnectionMessageSender) sender;
				httpSender.setConnectionTimeout(Duration
						.ofMillis(connTimeoutMsec));
				httpSender.setReadTimeout(Duration.ofMillis(readTimeoutMsec));
			} catch (ClassCastException | NumberFormatException cex) {
				logger.warn("Cannot set WS timeout: " + cex.getMessage());
			}
		}

		return wsTemplate.marshalSendAndReceive(endpoint, requestPayload);

	}

}
