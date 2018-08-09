package com.invoice.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.invoice.soap.gateway.SoapConnector;

/**
 * The spring configuration class contains bean for Soap connector and
 * JaxB2Marshaller for consuming the public Web service
 * 
 * @author mohansar0
 * 
 */
@Configuration
public class Config {

	private static Logger LOG = LoggerFactory.getLogger(Config.class);
	
	@Value("${numberconversion.default.uri}")
	private String defaultURI;

	@Value("${invoice.ws.readtimeout}")
	private String readTimeout;

	@Value("${invoice.ws.connectiontimeout}")
	private String connectionTimeout;

	/**
	 * Sets the Jaxb2Marshaller context path to client POJOs
	 * 
	 * @return
	 */
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.dataaccess.webservicesserver");
		return marshaller;
	}

	/**
	 * Setting up the default URL and marshaller for Soap connection to
	 * webservice
	 * 
	 * @param marshaller
	 * @return
	 */
	@Bean
	public SoapConnector soapConnector(Jaxb2Marshaller marshaller) {
		LOG.info("Initializing Soap connector using JaxB");
		SoapConnector client = new SoapConnector();
		client.setDefaultUri(defaultURI);
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
}
