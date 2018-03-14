/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Oct 20, 2013
 *
 */
package com.palmcommerce.funds.ca.client.execute;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.palmcommerce.funds.ca.client.CaRestClient;
import com.palmcommerce.funds.ca.client.Cacrts;
import com.palmcommerce.funds.ca.client.Cakeys;

/**
 * @author sparrow
 *
 */
public class CommandLineTool {
	
	

	/**
	 * 
	 */
	public CommandLineTool() {
		// TODO Auto-generated constructor stub
	}

	public static void log(String message){
		System.out.println(message);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:META-INF/spring/applicationContext-funds-ca-rest-client.xml");
		CaRestClient caRestClient=(CaRestClient) ctx.getBean("caRestClient");
		CommandLineParser parser=new BasicParser();
		
		Options options=new Options();
		options.addOption("h", true, "Usage: \n" +
				"-t .crt -f xxx.crt -c Rxxxxxxx(Pxxxxxx,Bxxxxxx)\n" +
				"-t .key -f xxx.key -c Pxxxxx                   \n" +
				"-t .csr -f xxxx.csr -c Pxxxxxx(Bxxxxx,xxxxxx)  \n" +
				"\n");
		options.addOption("t", true, "Ca file type,(.key or .crt .csr)");
		options.addOption("f", true, "Ca file path,(location of the file)");
		options.addOption("p",true,"Ca key file passphase");
		options.addOption("c", true, "Ca file node code(must be unique)");
		String type=null;
		String file=null;
		String passphase=null;
		String nodecode=null;
		
		try {
			CommandLine commandLine=parser.parse(options, args);
			if(commandLine.hasOption("t")){
				type=commandLine.getOptionValue("t");
			}
			
			if(commandLine.hasOption("f")){
				file=commandLine.getOptionValue("f");
			}
			if(commandLine.hasOption("p")){
				passphase=commandLine.getOptionValue("p");
			}
			
			if(commandLine.hasOption("c")){
				nodecode=commandLine.getOptionValue("c");
			}
			
			if(type==null){
				log(options.getOption("h").getDescription()+"  - type must not be empty!");
				return;
			}
			if(file==null){
				log(options.getOption("h").getDescription()+"   - file must not be empty!");
				return;
			}
			
			if(nodecode==null){
				log(options.getOption("h").getDescription()+"   - nodecode must not be empty!");
				return;
			}
			
			
			if(".key".equals(type)){
				if(passphase==null){
					log(options.getOption("h").getDescription()+" -key  must not be empty!");
					return;
				}
				// do save the keys.
				Cakeys caEntity=new Cakeys();
				String caContent=FileUtils.readFileToString(new File(file), "utf-8");
				//byte[] caContent=FileUtils.readFileToByteArray(new File(file));
				caEntity.setContent(caContent);
				caEntity.setPassphase(passphase);
				caEntity.setNodecode(nodecode);
				caEntity.setModifytime(new Date());
				if(caRestClient.addCakey(caEntity)){
					log(file+"-- Ca key file saved!");
				}
				
			}else{
				boolean iscsr=false;
				if(".csr".equals(type))	
					iscsr=true;
					
				Cacrts caEntity=new Cacrts();
				String caContent=FileUtils.readFileToString(new File(file), "utf-8");
				//byte[] caContent=FileUtils.readFileToByteArray(new File(file));
				caEntity.setContent(caContent);
				caEntity.setIscsr(iscsr);
				caEntity.setNodecode(nodecode);
				caEntity.setModifytime(new Date());
				if(caRestClient.addCacrt(caEntity)){
					if(iscsr)
						log(file+"-- Ca csr file saved!");
					else
						log(file+"-- Ca crt file saved!");
				}
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		

	}

}
