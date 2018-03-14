package com.palmcommerce.funds.alert.service.sms;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class SmsCodeSendHelper {
	private static Log log = LogFactory.getLog(SmsCodeSendHelper.class);
	
	SmsServerConfig smsServerconfig;
	
	/**
	 * @return the smsServerconfig
	 */
	public SmsServerConfig getSmsServerconfig() {
		return smsServerconfig;
	}

	/**
	 * @param smsServerconfig the smsServerconfig to set
	 */
	public void setSmsServerconfig(SmsServerConfig smsServerconfig) {
		this.smsServerconfig = smsServerconfig;
	}

	public  void send(String mobile, String sendMesg) {
		
		log.info("SmsCodeSendHelper.send"+mobile+"content:" + sendMesg +" send msg start !  ");
		
		if (mobile != null && !"".equals(mobile)) {

			String remoteUrl = smsServerconfig.getRemoteUrl();
			String userId = smsServerconfig.getUserId();
			String password = smsServerconfig.getPassword();
			String bizType = smsServerconfig.getBizType();
			try {
				sendMesg = java.net.URLEncoder.encode(sendMesg, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			StringBuffer smsSb = new StringBuffer();
			smsSb.append("?UserId=").append(userId);
			smsSb.append("&Password=").append(password);
			smsSb.append("&MsgContent=").append(sendMesg);
			smsSb.append("&DestNumber=").append(mobile);
			smsSb.append("&SendTime=");
			smsSb.append("&SubNumber=");
			smsSb.append("&BatchSendID=");
			smsSb.append("&BizType=").append(bizType);
			smsSb.append("&WapURL=");
			httpGet(remoteUrl+smsSb.toString());
			//HttpClientHelper.httpGet(remoteUrl + smsSb.toString());
			log.info("SmsCodeSendHelper.send "+mobile+" content:" + sendMesg +" send msg end !  ");
		}
	}
	
	
	private void httpGet(String url){
		 CloseableHttpClient httpclient = HttpClients.createDefault();
	        try {
	            HttpGet httpget = new HttpGet(url);

	            log.info("executing request " + httpget.getURI());

	            // Create a custom response handler
	            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

	                public String handleResponse(
	                        final HttpResponse response) throws ClientProtocolException, IOException {
	                    int status = response.getStatusLine().getStatusCode();
	                    if (status >= 200 && status < 300) {
	                        HttpEntity entity = response.getEntity();
	                        
	                        String resp= entity != null ? EntityUtils.toString(entity) : null;
	                        if(resp!=null){
	                        	
	                        }
	                        return resp;
	                    } else {
	                        throw new ClientProtocolException("Unexpected response status: " + status);
	                    }
	                }

	            };
	            String responseBody = httpclient.execute(httpget, responseHandler);
	            log.info("----------------------------------------");
	            log.info(responseBody);
	            log.info("----------------------------------------");

	        } catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
	            try {
					httpclient.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	}
	public static void main(String[] args) {
	}
}
