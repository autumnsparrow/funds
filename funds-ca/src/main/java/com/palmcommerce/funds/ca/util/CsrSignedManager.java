/**
 * 
 */
package com.palmcommerce.funds.ca.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.Watchdog;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.palmcommerce.funds.ca.model.Cacrts;

/**
 * @author sparrow
 *
 */
public class CsrSignedManager {
	private static final Log logger=LogFactory.getLog(CsrSignedManager.class);
	
	String cryptoHome;
	String rootCa;
	
	String cryptoCsrHome;
	String cryptoCrtHome;
	
	int  executeTimeout;
	
	
	
	
	
	/**
	 * @return the executeTimeout
	 */
	public int getExecuteTimeout() {
		return executeTimeout;
	}

	/**
	 * @param executeTimeout the executeTimeout to set
	 */
	public void setExecuteTimeout(int executeTimeout) {
		this.executeTimeout = executeTimeout;
	}


	public static class CsrSignedCommand{
		String inFile;
		String outFile;
		String caFile;
		String caKeyFile;
		String caSerialFile;
		String nodeCode;
		public CsrSignedCommand(String inFile, String outFile, String caFile,
				String caKeyFile, String caSerialFile) {
			super();
			this.inFile = inFile;
			this.outFile = outFile;
			this.caFile = caFile;
			this.caKeyFile = caKeyFile;
			this.caSerialFile = caSerialFile;
		}
		public CsrSignedCommand() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
		
		
		public CsrSignedCommand(String inFile, String outFile, String caFile,
				String caKeyFile, String caSerialFile, String nodeCode) {
			super();
			this.inFile = inFile;
			this.outFile = outFile;
			this.caFile = caFile;
			this.caKeyFile = caKeyFile;
			this.caSerialFile = caSerialFile;
			this.nodeCode = nodeCode;
		}
		/**
		 * @return the nodeCode
		 */
		public String getNodeCode() {
			return nodeCode;
		}
		/**
		 * @param nodeCode the nodeCode to set
		 */
		public void setNodeCode(String nodeCode) {
			this.nodeCode = nodeCode;
		}
		/**
		 * @return the inFile
		 */
		public String getInFile() {
			return inFile;
		}
		/**
		 * @param inFile the inFile to set
		 */
		public void setInFile(String inFile) {
			this.inFile = inFile;
		}
		/**
		 * @return the outFile
		 */
		public String getOutFile() {
			return outFile;
		}
		/**
		 * @param outFile the outFile to set
		 */
		public void setOutFile(String outFile) {
			this.outFile = outFile;
		}
		/**
		 * @return the caFile
		 */
		public String getCaFile() {
			return caFile;
		}
		/**
		 * @param caFile the caFile to set
		 */
		public void setCaFile(String caFile) {
			this.caFile = caFile;
		}
		/**
		 * @return the caKeyFile
		 */
		public String getCaKeyFile() {
			return caKeyFile;
		}
		/**
		 * @param caKeyFile the caKeyFile to set
		 */
		public void setCaKeyFile(String caKeyFile) {
			this.caKeyFile = caKeyFile;
		}
		/**
		 * @return the caSerailFile
		 */
		public String getCaSerialFile() {
			return caSerialFile;
		}
		/**
		 * @param caSerailFile the caSerailFile to set
		 */
		public void setCaSerialFile(String caSerialFile) {
			this.caSerialFile = caSerialFile;
		}
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return String.format(CSR_SIGNED_COMMAND, inFile,outFile,caFile,caKeyFile,caSerialFile);
		}
		
		public String getCommand(){
			return "openssl";//String.format(CSR_SIGNED_COMMAND, inFile,outFile,caFile,caKeyFile,caSerialFile);
		}
		
		public String[]  getCommandArgs(){
			return new String[]{
					"x509",
					"-req",
					"-days",
					"365",
					"-in",
					inFile,
					"-out",
					outFile,
					"-CA",
					caFile,
					"-CAkey",
					caKeyFile,
					"-CAserial",
					caSerialFile
					
			};
		}
	
		
	}
	/**
	 * 
	 * openssl x509 -req -days 365 \
	-in $SELF_SIGNED.csr -out $SELF_SIGNED.crt \
	-CA $CRYPTO_HOME/ca/$SELFSIGNED_CA.crt \
	-CAkey $CRYPTO_HOME/ca/$SELFSIGNED_CA.key \
	-CAserial $CRYPTO_HOME/ca/serial
	 */
	private static final String CSR_SIGNED_COMMAND="/usr/bin/openssl x509 -req -days 365 " +
			"-in %s -out %s -CA %s -CAkey %s -CAserial %s ";
	


	private CsrSignedCommand getCommand(String signedCsr){
		
		String inFile=cryptoCsrHome+File.separator+signedCsr+".csr";
		String outFile=cryptoCrtHome+File.separator+signedCsr+".crt";
		String caFile=cryptoHome+File.separator+rootCa+".crt";
		String caKeyFile=cryptoHome+File.separator+rootCa+".key";
		String caSerialFile=cryptoHome+File.separator+"serial";
		
		
		CsrSignedCommand command=new CsrSignedCommand(inFile, outFile, caFile, caKeyFile, caSerialFile,signedCsr);
		
		logger.info(command.getCommand());
		return command;
		
	}

	
	
	/**
	 * @return the cryptoHome
	 */
	public String getCryptoHome() {
		return cryptoHome;
	}



	/**
	 * @param cryptoHome the cryptoHome to set
	 */
	public void setCryptoHome(String cryptoHome) {
		this.cryptoHome = cryptoHome;
	}



	/**
	 * @return the rootCa
	 */
	public String getRootCa() {
		return rootCa;
	}



	/**
	 * @param rootCa the rootCa to set
	 */
	public void setRootCa(String rootCa) {
		this.rootCa = rootCa;
	}



	/**
	 * @return the cryptoCsrHome
	 */
	public String getCryptoCsrHome() {
		return cryptoCsrHome;
	}



	/**
	 * @param cryptoCsrHome the cryptoCsrHome to set
	 */
	public void setCryptoCsrHome(String cryptoCsrHome) {
		this.cryptoCsrHome = cryptoCsrHome;
	}



	/**
	 * @return the cryptoCrtHome
	 */
	public String getCryptoCrtHome() {
		return cryptoCrtHome;
	}



	/**
	 * @param cryptoCrtHome the cryptoCrtHome to set
	 */
	public void setCryptoCrtHome(String cryptoCrtHome) {
		this.cryptoCrtHome = cryptoCrtHome;
	}



	/**
	 * 
	 */
	public CsrSignedManager() {
		// TODO Auto-generated constructor stub
	}
	
	public void executeSignCsrContent(String csrContent,String nodecode){
		// 1.first we get the csr signed command
		CsrSignedCommand command=this.getCommand(nodecode);
		// 2.write the csrContent into the inFile
		File inFile=new File(command.getInFile());
		if(!inFile.exists()){
			try {
				FileUtils.writeStringToFile(inFile, csrContent, "utf-8");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 3. signed the csr
		if(inFile.exists()){
			execute(nodecode);
		}
		
	}
	
	
	/**
	 * 
	 * signed the CSR Request file.
	 * 
	 * @param csr
	 */
	public void execute(String csr){
		CsrSignedCommand command=this.getCommand(csr);
		String cmd=command.getCommand();
		Executor executor=new DefaultExecutor();
		executor.setExitValue(0);
		ExecuteWatchdog executeWatchdog=new ExecuteWatchdog(executeTimeout);
		executor.setWatchdog(executeWatchdog);
		
		CsrSignedResultHandler resultHandler=new CsrSignedResultHandler(executeWatchdog, command);
		CommandLine cmdLine=new CommandLine(cmd);
		cmdLine.addArguments(command.getCommandArgs());
		
		try {
			logger.info("execute :"+cmd+".......");
			executor.execute(cmdLine, resultHandler);
		} catch (ExecuteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public static class CsrSignedResultHandler extends DefaultExecuteResultHandler{

		/* (non-Javadoc)
		 * @see org.apache.commons.exec.DefaultExecuteResultHandler#onProcessComplete(int)
		 */
		@Override
		public void onProcessComplete(int exitValue) {
			// TODO Auto-generated method stub
			super.onProcessComplete(exitValue);
			logger.info(" Csr Signed success.");
			// should delete the csr file
			File f=new File(this.signedCommand.inFile);
			File df=new File(this.signedCommand.inFile+"_parsed_"+System.currentTimeMillis());
			File outFileSaved=new File(this.signedCommand.outFile+"_saved_"+System.currentTimeMillis());
			File outFile=new File(this.signedCommand.outFile);
			if(outFile.exists()){
				try {
					//Cacrts.deleteCacrtsesByNodecodeEquals(this.signedCommand.getNodeCode());
					List<Cacrts> cacrts=Cacrts.findCacrtsesByNodecodeEquals(this.signedCommand.getNodeCode()).getResultList();
					
					if(cacrts!=null){
						for(Cacrts ca:cacrts){
							ca.remove();
						}
						//cacrts.removeAll(cacrts);
					}
					
					Cacrts crts=new Cacrts();
					String signedContent=FileUtils.readFileToString(new File(this.signedCommand.outFile));
					
					crts.setContent(signedContent);
					crts.setNodecode(this.signedCommand.getNodeCode());
					crts.setModifytime(new Date());
					crts.persist();
					FileUtils.copyFile(outFile, outFileSaved);
					outFile.delete();
					//FileUtils.deleteDirectory(outFile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.info("CSR signed exception: (save crt content failed)"+this.signedCommand.toString()+"\n"+e.getMessage());

				}
			}
			
			if(f.exists()){
				try {
					// we need write this into the  database.
					
					
					FileUtils.copyFile(f, df);
					f.delete();
					//FileUtils.deleteQuietly(f);
					//FileUtils.deleteDirectory(f);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					logger.info("CSR signed exception:(rename in file failed.)"+this.signedCommand.toString()+"\n"+e.getMessage());

				}
			}
			
		}

		/* (non-Javadoc)
		 * @see org.apache.commons.exec.DefaultExecuteResultHandler#onProcessFailed(org.apache.commons.exec.ExecuteException)
		 */
		@Override
		public void onProcessFailed(ExecuteException e) {
			// TODO Auto-generated method stub
			super.onProcessFailed(e);
			if(executeWatchdog!=null&&executeWatchdog.killedProcess()){
				logger.info("CSR signed timeout:"+this.signedCommand.toString());
			}else{
				logger.info("CSR signed exception:"+this.signedCommand.toString()+"\n"+e.getMessage());
			}
		}
		
		private ExecuteWatchdog  executeWatchdog;

		
	

		public CsrSignedResultHandler(int exitValue) {
			super.onProcessComplete(exitValue);
		}
		
		
		CsrSignedCommand signedCommand;

		public CsrSignedResultHandler(ExecuteWatchdog executeWatchdog,
				CsrSignedCommand signedCommand) {
			super();
			this.executeWatchdog = executeWatchdog;
			this.signedCommand = signedCommand;
		}
		
	}
	

}
