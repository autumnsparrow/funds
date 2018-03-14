/**
 * 
 */
package com.palmcommerce.funds.protocol.parser;

import java.util.LinkedList;
import java.util.List;

import com.palmcommerce.funds.protocol.parser.exception.ProtocolParserException;

/**
 * @author sparrow
 *
 */
public class DefaultProtocolParser implements IProtocolParser {

	/**
	 * 
	 */
	public DefaultProtocolParser() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.palmcommerce.funds.protocol.parser.IProtocolParser#parse(java.lang.String)
	 */
	private static String[] fastSplit(String line, char split){  
		List<String> lst=new LinkedList<String>();
        int wordCount = 0;  
        int i = 0;  
        int j = line.indexOf(split);  // First substring  
        while( j >= 0){  
            lst.add(wordCount++, line.substring(i,j));  
            i = j + 1;  
            j = line.indexOf(split, i);   // Rest of substrings  
        }  
       // temp[wordCount++] = line.substring(i); // Last substring  
        String[] result = new String[wordCount];  
        lst.toArray(result);
       // System.arraycopy(temp, 0, result, 0, wordCount);  
        return result;  
    }
	
	public String[] parse(String packet) throws ProtocolParserException {
		// TODO Auto-generated method stub
		return fastSplit(packet,'|');//.split(packet,"\\|");
	}

}
