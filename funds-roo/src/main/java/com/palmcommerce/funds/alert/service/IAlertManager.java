/**
 *
 */
package com.palmcommerce.funds.alert.service;
import com.palmcommerce.funds.roo.model.TransactionMeta;


/**
 * @author Administrator
 *   
 */
public interface IAlertManager {

    /**
     *  
     */
    public void loadDefaultTradeRecord();

    /**
     * 
     * @param meta
     */
    public void notifyTradeRecord(TransactionMeta meta);
    
    /**
     * 
     * 
     * @param message
     * @param to
     * @param from
     * @param template
     */
    public  void sendMail(final Object message,final String to,final String from,final String template);

 
}
