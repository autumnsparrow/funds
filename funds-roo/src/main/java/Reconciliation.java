import javax.management.openmbean.OpenDataException;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.palmcommerce.funds.roo.tasklet.schedule.ScheduledTaskJmxClient;

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
		String ip;
		String port;
		String user;
		String pass;
		String state;
		
		options.addOption("help", false, "Usage:\r\n" +
				"------------------------------------------------------------------------------------------------\r\n" +
				"basic configuration of connect the jmx server:\r\n" +
				
				"-ip jmx host\r\n" +
				"-port jmx port\r\n" +
				"-user jmx user\r\n" +
				"-pass jmx password\r\n" +
				"------------------------------------------------------------------------------------------------\r\n" +
				"query and change the current reconciliation state:\r\n" +
				
				"-query get reconciliation state\r\n" +
				"-clean clean the reconciliation state\r\n" +
				"-set  set the state of the reconcilication\r\n" +
				"(INVALID=-1;ACTIVING=0;RECEIVING=1;PROCESSING=2;SENDING=3;TIMEOUT=4;FINISHED=5;ACTIVE_WAITING=6)\r\n" +
				"------------------------------------------------------------------------------------------------\r\n" +
				" change the account date of the reconciliation system: \r\n" +
				"-accountdate query account date \r\n" +
				"-setdate set account date\r\n" +
				"------------------------------------------------------------------------------------------------\r\n" +
				" send single bank request and reconciliation:\r\n" +
				"-bankcode bankcode ,format:Bxxxx\r\n" +
				"-bankdate bankdate ,format:20131213\r\n" +
				"-bankfile bankfile ,format:ChangeDate.xxx.Bxxx.\r\n"+
				"------------------------------------------------------------------------------------------------\r\n" +
				" load routerule:"+
				"-loadrules  load server rule and certification\r\n" +
				"------------------------------------------------------------- -----------------------------------\r\n" +
				" sending single trade file:\r\n" +
				"-tradecode trade code\r\n" +
				"-bankdate  bankdate \r\n"+
				"------------------------------------------------------------------------------------------------\r\n" +
				" ****(MOST IMPORTANT) reconciliation :\r\n" +
				"-reconciliation_date"
				
				);
		
	
		options.addOption("ip", true, "jmx host");
		options.addOption("port",true,"jmx port");
		options.addOption("user", true, "jmx user");
		options.addOption("pass", true, "jmx pass");
		
		
		options.addOption("query", false, "query state");
		options.addOption("clean", false, "clean state");
		options.addOption("set", true, "seet state" +
				"(INVALID=-1;ACTIVING=0;RECEIVING=1;PROCESSING=2;SENDING=3;TIMEOUT=4;FINISHED=5;ACTIVE_WAITING=6)\r\n");
		
		options.addOption("accountdate", false, "query accountdate state");
		options.addOption("setdate", true, "set account date format: 0 , -1 ,-2");
		
		options.addOption("bankcode", true, "bankcode");
		options.addOption("bankdate", true, "bank date format:20131212");
		options.addOption("bankfile", true, "bank file format:ChargeData.xxxxx.Bxxxx...");
		
		options.addOption("loadrules", false, "bankcode");
		options.addOption("tradecode", true, "tradecode");
		options.addOption("reconciliation_date", true, "reconciliation_date  format:20140109");
		
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
		
		ScheduledTaskJmxClient client=new ScheduledTaskJmxClient(ip, Integer.parseInt(port), user, pass);
		if(commandLine.hasOption("loadrules")){
			client.loadRouteRule();
			log("\r\n LoadRules: Done\r\n");
		}else
		if(commandLine.hasOption("query")){
			state=client.getScheduledState();
			log("\r\n Query:"+state+"\r\n");
		}else if(commandLine.hasOption("clean")){
			client.onCleanup();
			state=client.getScheduledState();
			log("\r\n Cleaned:"+state+"\r\n");
		}else if(commandLine.hasOption("set")){
			state=commandLine.getOptionValue("set");
			log("\r\n Before ChangedState:"+state+"\r\n");
			client.changeState(Integer.parseInt(state));
			state=client.getScheduledState();
			log("\r\n After ChangedState:"+state+"\r\n");
			
		}else if(commandLine.hasOption("accountdate")){
			String days=client.getAccountDate();
			log("\r\n Account Date:"+days+"\r\n");
		}else if(commandLine.hasOption("setdate")){
			String prevDays=client.getAccountDate();
			String days=commandLine.getOptionValue("setdate");
			client.changePreviousDays(Integer.parseInt(days));
			log("\r\n Before Set Account Date:"+prevDays+"\r\n");
			prevDays=client.getAccountDate();
			log("\r\n Before Set Account Date:"+prevDays+"\r\n");
		}else if(commandLine.hasOption("bankdate")){
			String bankdate=commandLine.getOptionValue("bankdate");
			if(commandLine.hasOption("bankcode")){
				String bankcode=commandLine.getOptionValue("bankcode");
				
				 if(commandLine.hasOption("bankfile")){
						String bankfile=commandLine.getOptionValue("bankfile");
						log("\r\n reconciliationBank:(bankcode="+bankcode+",bankfile="+bankfile+",bankdate="+bankdate+")\r\n");
						
						client.reconciliationBank(bankcode,bankfile, bankdate);
				}else{
					log("\r\n sendReconciliationRequest:(bankcode="+bankcode+",bankdate="+bankdate+")\r\n");
					client.sendReconciliationRequest(bankcode, bankdate);
					
				}
			}else if(commandLine.hasOption("tradecode")){
				String tradecode=commandLine.getOptionValue("tradecode");
				client.reconciliationTrade(tradecode, bankdate);
				log("\r\n reconciliationTrade:(bankcode="+tradecode+",bankdate="+bankdate+")\r\n");
				
			}
			
		}else if(commandLine.hasOption("reconciliation_date")){
			String bankdate=commandLine.getOptionValue("reconciliation_date");
			log("\r\n reconciliation:(bankdate="+bankdate+")\r\n");
			client.reconciliation(bankdate);
			
		}
		
		
		
		
		
	}

	private static void log(String message){
		System.out.println(message);
	}
}
