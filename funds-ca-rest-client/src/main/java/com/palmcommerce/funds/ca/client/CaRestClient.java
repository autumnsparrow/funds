/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Oct 19, 2013
 *
 */
package com.palmcommerce.funds.ca.client;

import java.net.URI;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;



/**
 * @author sparrow
 *
 */
public class CaRestClient {
	private static final Log logger=LogFactory.getLog(CaRestClient.class);
	@Autowired
	RestTemplate restTemplate;
	
	
	
	/**
	 * @return the baseUrl
	 */
	public String getBaseUrl() {
		return baseUrl;
	}


	/**
	 * @param baseUrl the baseUrl to set
	 */
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}


	private String baseUrl;
	


	/**
	 * 
	 */
	public CaRestClient() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Cacrts getCacrtByNodecode(String nodecode){
		String url=baseUrl+"/cacrtses?find=ByNodecodeEquals&nodecode={nodecode}";
		MultiValueMap<String, String> headers =
			    new LinkedMultiValueMap<String, String>();
			headers.add("Accept", "application/json");
			HttpEntity<Object> requestEntity = new HttpEntity<Object>(headers);
		
		ResponseEntity<Cacrts[]> response=this.restTemplate.exchange(url, HttpMethod.GET, requestEntity, Cacrts[].class,nodecode);
		Cacrts[] obj=response.getBody();
		Cacrts crts=null;
		if(obj!=null&obj.length>0)
			crts=obj[0];
		return crts;
		
	}
	
	public Cakeys getCakeyByNodecode(String nodecode){
		String url=baseUrl+"/cakeyses?find=ByNodecodeEquals&nodecode={nodecode}";
		MultiValueMap<String, String> headers =
			    new LinkedMultiValueMap<String, String>();
			headers.add("Accept", "application/json");
			HttpEntity<Object> requestEntity = new HttpEntity<Object>(headers);
		
		ResponseEntity<Cakeys[]> response=this.restTemplate.exchange(url, HttpMethod.GET, requestEntity, Cakeys[].class,nodecode);
		Cakeys[] obj=response.getBody();
		Cakeys crts=null;
		if(obj!=null&obj.length>0)
			crts=obj[0];
		return crts;
		
	}
	
	
	public boolean addCacrt(Cacrts caEntity){
		
		String url=baseUrl+"/cacrtses";
		ResponseEntity<Cacrts> response=null;
		try {
			response = this.restTemplate.postForEntity(url, caEntity, Cacrts.class);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		HttpStatus created=response.getStatusCode();
		logger.info("status:"+created.toString());
		return created==HttpStatus.CREATED;
	}
	
	
	public boolean addCakey(Cakeys caEntity){
		
		String url=baseUrl+"/cakeyses";
		ResponseEntity<Cakeys> response=this.restTemplate.postForEntity(url, caEntity, Cakeys.class);	
		HttpStatus created=response.getStatusCode();
		logger.info("status:"+created.toString());
		return created==HttpStatus.CREATED;
	}
	

}
