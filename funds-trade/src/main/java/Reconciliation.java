import javax.management.openmbean.OpenDataException;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.palmcommerce.funds.trade.ibatis.impl.ReconciliationJmxClient;



/**
 * 
 */

/**
 * @author sparrow
 *
 */
public class Reconciliation {

	/**
	 * 
	 */
	public Reconciliation() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Options options=new Options();
		String ip=null;
		String port="0";
		String user=null;
		String pass=null;
		String state;
		
		options.addOption("help", false, "Usage:\r\n" +
				"------------------------------------------------------------------------------------------------\r\n" +
				"basic configuration of connect the jmx server:\r\n" +
				
				"-ip jmx host\r\n" +
				"-port jmx port\r\n" +
				"-user jmx user\r\n" +
				"-pass jmx password\r\n" +
				
				"-reconciliation reconciliation\r\n" +
				"-changedate change reconciliation date\r\n" +
				"-query  get reconciliation date\r\n" +
				
				"-clean  clean the current stat."+
				"-bankdate   bankdate\r\n" +
				"-filename   bankfilename\r\n"
				
				);
		
	
		options.addOption("ip", true, "jmx host");
		options.addOption("port",true,"jmx port");
		options.addOption("user", true, "jmx user");
		options.addOption("pass", true, "jmx pass");
		options.addOption("reconciliation", true, "reconciliation with date :20131211");
		options.addOption("changedate", true, "change reconciliation date :0,current date,-1,prev date .. -n");
		options.addOption("query", false, "get current reconciliation date");
		options.addOption("clean", false, "clean current date.");
		
		options.addOption("bankdate", true, "bank date");
		options.addOption("filename", true, "bank file name.");
		
			
		
		CommandLineParser parser=new BasicParser();
		CommandLine commandLine=null;
		try {
			commandLine = parser.parse(options, args);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(commandLine.hasOption("help")){
			log(options.getOption("help").getDescription());
			return;
		}
		
	
		
		
		if(commandLine.hasOption("ip")){
			ip=commandLine.getOptionValue("ip");
		}else{
			log("missing -"+options.getOption("ip").getDescription());
			return;
		}
		
		if(commandLine.hasOption("port")){
			port=commandLine.getOptionValue("port");
		}else{
			log("missing -"+options.getOption("port").getDescription());
			return;
		}
		if(commandLine.hasOption("user")){
			user=commandLine.getOptionValue("user");
		}else{
			log("missing -"+options.getOption("user").getDescription());
			return;
		}
		if(commandLine.hasOption("pass")){
			pass=commandLine.getOptionValue("pass");
		}else{
			log("missing -"+options.getOption("pass").getDescription());
			return;
		}
		
		ReconciliationJmxClient client=new ReconciliationJmxClient(ip, Integer.parseInt(port), user, pass);
		
		
		if(commandLine.hasOption("clean")){
			client.cleanStates();
			log(" \r\n Clean  states\r\n");
		}else if (commandLine.hasOption("query")){
			state=client.getReconciliationDate();
			log("\r\n reconciliation date:"+state+"\r\n");
		}else if(commandLine.hasOption("changedate")){
			String date=commandLine.getOptionValue("changedate");
			state=client.getReconciliationDate();
			log("\r\n reconciliation date:"+state+"\r\n");
			client.changeReconciliationDate(date);
			state=client.getReconciliationDate();
			log("\r\n reconciliation date:"+state+"\r\n");
		}else if(commandLine.hasOption("reconciliation")){
			String date=commandLine.getOptionValue("reconciliation");
			client.reconciliation(date);
			log("\r\n reconciliation  date=:"+date+"\r\n");
		}else if(commandLine.hasOption("bankdate")){
			String bankdate=commandLine.getOptionValue("bankdate");
			if(commandLine.hasOption("filename")){
				String filename=commandLine.getOptionValue("filename");
				client.reconciliationByDate(filename, bankdate);
				log("\r\n Reconciliation by [filename=" +filename+
						",bankdate=" +bankdate+
						"]");
			}
		}
		
		
		
		
	}

	private static void log(String message){
		System.out.println(message);
	}
}
