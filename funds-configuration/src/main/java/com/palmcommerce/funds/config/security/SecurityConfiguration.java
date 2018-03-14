/**
 * 
 */
package com.palmcommerce.funds.config.security;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;

import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.security.cert.X509Certificate;

import org.apache.commons.digester3.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.bouncycastle.crypto.tls.Certificate;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.provider.X509CertificateObject;
import org.bouncycastle.openssl.PEMReader;
import org.bouncycastle.openssl.PasswordFinder;
import org.bouncycastle.util.io.pem.PemObject;
import org.xml.sax.SAXException;

import com.palmcommerce.funds.connection.config.FundsConfiguration;
import com.palmcommerce.funds.connection.config.RouteConfiguration;

/**
 * 
 * 
 * <KeyStore base="path" cacrt="">
 * 
 * <Key code="" name="" password="" blocksize="100" signature=""  siglen="" folder="" privateKey="" remotePublicKey="" >
 * 
 * </Key>
 * 
 * <Key code="" name="" > </Key>
 * 
 * </KeyStore>
 * 
 * <KeyStore blocksize="" signature="" siglen="" />
 *  database should store the 
 *  
 *  from to
 *  
 *  
 * 
 * 
 * @author sparrow
 * 
 */
public class SecurityConfiguration {
	private static final Log logger=LogFactory.getLog(SecurityConfiguration.class);
//	private static final SecurityConfiguration factory=new SecurityConfiguration();
//	
//	private static volatile boolean isParsed=false;
//	public static SecurityConfiguration getInstance(){
//		if(!isParsed){
//			try {
//				factory.setSecurityKeyStore( factory.parse());
//				isParsed=true;
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (SAXException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		return factory;
//	}
	
	FundsConfiguration minaConfiguration;
	
	

	public void setMinaConfiguration(FundsConfiguration minaConfiguration) {
		this.minaConfiguration = minaConfiguration;
	}



	private final static String RESOUCE = SecurityConfiguration.class
			.getResource("/config/security.xml").getFile();
	private final static String LOCAL_KEY = "local.key";
	private final static String REMOTE_CRT = "remote.crt";

	private static class Password implements PasswordFinder {
		char[] password;

		Password(char[] word) {
			this.password = word;
		}

		public char[] getPassword() {
			return password;
		}
	}

	public static class SecurityKey {
		private String code;
		private String name;
		private String password;
		
		private int blockSize;
		private String signature;
		private int siglen;
		
		private PrivateKey localKey;
		// private Public
		private PublicKey remoteCrt;
		
		private Signature sig;
		private Cipher cipher;
		
		
		private String folder;
		private String privateKey;
		private String remotePublicKey;


		public String getFolder() {
			return folder;
		}

		public void setFolder(String folder) {
			this.folder = folder;
		}

		public String getPrivateKey() {
			return privateKey;
		}

		public void setPrivateKey(String privateKey) {
			this.privateKey = privateKey;
		}

		public String getRemotePublicKey() {
			return remotePublicKey;
		}

		public void setRemotePublicKey(String remotePublicKey) {
			this.remotePublicKey = remotePublicKey;
		}

		public int getBlockSize() {
			return blockSize;
		}

		public void setBlockSize(int blockSize) {
			this.blockSize = blockSize;
		}

		public String getSignature() {
			return signature;
		}

		public void setSignature(String signature) {
			this.signature = signature;
			try {
				sig = Signature.getInstance(this.signature);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public int getSiglen() {
			return siglen;
		}

		public void setSiglen(int siglen) {
			this.siglen = siglen;
		}

		
		public PublicKey getRemoteCrt() {
			return remoteCrt;
		}

		public void setRemoteCrt(PublicKey remoteCrt) {
			this.remoteCrt = remoteCrt;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "SecurityKey [code=" + code + ", name=" + name
					+ ", password=" + password + ", blockSize=" + blockSize
					+ ", signature=" + signature + ", siglen=" + siglen
					+ ", folder=" + folder + ", privateKey=" + privateKey
					+ ", remotePublicKey=" + remotePublicKey + "]";
		}

		public PrivateKey getLocalKey() {
			return localKey;
		}

		public void setLocalKey(PrivateKey localKey) {
			this.localKey = localKey;
		}

		

		public synchronized byte[] signature(byte[] content) throws Exception {

			sig.initSign(localKey);

			sig.update(content);
			byte[] signed = sig.sign();
			return signed;

		}

		public synchronized boolean validateSignature(byte[] content)
				throws Exception {
			sig.initVerify(remoteCrt);
			sig.update(content,0, content.length-siglen);
			boolean ret = sig.verify(content, content.length-siglen, siglen);
			return ret;
		}

		public synchronized byte[] decrypt(byte[] content) throws Exception {
			cipher.init(Cipher.DECRYPT_MODE, localKey);
			ByteBuffer buffer=ByteBuffer.allocate(1024);
			int blocksize=siglen;//cipher.getBlockSize();
			
			for(int i=0;i<content.length;i+=blocksize){
				
				if((content.length-i)>blocksize){
					byte[]  bytes=cipher.doFinal(content,i,blocksize);
					
					buffer.put(bytes);
				}
					
				else{
					byte[]  bytes=cipher.doFinal(content,i,content.length-i);
					
					buffer.put(bytes);
				}
					
			}
			//byte[] encryptedData = cipher.doFinal();
			//buffer.put(cipher.doFinal());
			buffer.flip();
			int len=buffer.limit();
			//byte[]  encryptedData=buffer.array();
			ByteBuffer realBuffer=ByteBuffer.allocate(len);
			realBuffer.put(buffer);
			realBuffer.flip();
			
			return realBuffer.array();

		}

		public synchronized byte[]  encrypt(byte[] content) throws Exception {

			cipher.init(Cipher.ENCRYPT_MODE, remoteCrt);
			int blocksize=blockSize;//cipher.getBlockSize();
			int bufLen=(content.length/blockSize+(content.length%blockSize>0?1:0))*siglen;
			ByteBuffer buffer=ByteBuffer.allocate(bufLen);
			
			
			for(int i=0;i<content.length;i+=blocksize){
				
				if((content.length-i)>blocksize){
					byte[]  bytes=cipher.doFinal(content,i,blocksize);
					
					buffer.put(bytes);
				}
					
				else{
					byte[]  bytes=cipher.doFinal(content,i,content.length-i);
					
					buffer.put(bytes);
				}
					
			}
			//byte[] encryptedData = cipher.doFinal();
			//buffer.put(cipher.doFinal());
			buffer.flip();
			
			return buffer.array();
		}

		public SecurityKey() {
			super();
			// TODO Auto-generated constructor stub
			try {
				sig = Signature.getInstance("SHA256withRSA");
				cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
				blockSize=96;
				siglen=128;
				
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
		public SecurityKey(SecurityKey key) {
			super();
			this.blockSize=key.blockSize;
			//this.cipher=key.cipher;
			this.code=key.code;
			this.localKey=key.localKey;
			this.name=key.name;
			this.password=key.password;
			this.remoteCrt=key.remoteCrt;
			//this.sig=key.sig;
			this.siglen=key.siglen;
			this.signature=key.signature;
			try {
				this.sig = Signature.getInstance(this.signature);
				this.cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}

	public static class SecurityKeyStore {
		private String base;
		private String cacrt;

		HashMap<String, SecurityKey> keys;

		public SecurityKeyStore() {
			super();
			// TODO Auto-generated constructor stub
			keys = new HashMap<String, SecurityKey>();

		}

		public String getBase() {
			return base;
		}

		public void setBase(String base) {
			this.base = base;
		}

		public String getCacrt() {
			return cacrt;
		}

		public void setCacrt(String cacrt) {
			this.cacrt = cacrt;
		}

		public void addKey(SecurityKey key) {

			this.keys.put(key.code, key);
			this.loadPEM(key);

		}

		public SecurityKey getKey(String code) {
			return this.keys.get(code);
		}
		
		
		
		

		private void loadPEM(SecurityKey key) {
			PrivateKey localKey;
			PublicKey remoteCrt;
			try {
				localKey = doOpenSslFile(key.code, RSAPrivateKey.class);
				key.setLocalKey(localKey);
				remoteCrt = loadCertificateFromFile(key.code);
				key.setRemoteCrt(remoteCrt);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CertificateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchProviderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SignatureException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		private PEMReader openPEMResource(String fileName, PasswordFinder pGet)
				throws FileNotFoundException {
			if(fileName==null)
				throw new FileNotFoundException("PEM Resource can't be empty!");
			PEMReader reader=null;
			if(fileName.startsWith("http://")){
				
				
				InputStream res =null;
				
				try {
					URL url=new URL(fileName);
					res=url.openStream(); // this.getClass().getResourceAsStream(fileName);
					Reader fRd = new BufferedReader(new InputStreamReader(res));
					reader= new PEMReader(fRd, pGet);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else{
				InputStream res = new FileInputStream(fileName);// this.getClass().getResourceAsStream(fileName);
				Reader fRd = new BufferedReader(new InputStreamReader(res));
				try {
					reader= new PEMReader(fRd, pGet);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return reader;
			
		}

		private PublicKey getCA() throws CertificateException,
				NoSuchProviderException, IOException {
			String path=null;
			if(this.base.startsWith("http://")){
				path=this.base + splash+this.cacrt;
			}else{
				path=this.base + File.separator
						+ this.cacrt;
			}
			
			PEMReader pr = openPEMResource(path, null);

			CertificateFactory fact = CertificateFactory.getInstance("X.509",
					"BC");
			ByteArrayInputStream bin = new ByteArrayInputStream(pr
					.readPemObject().getContent());
			java.security.cert.Certificate cert = fact.generateCertificate(bin);// (X509Certificate)fact.generateCertificate(bin);
			// /bin.close();
			PublicKey ca = cert.getPublicKey();

			pr.close();
			return ca;
		}
		
		private static final String splash="/";

		private PublicKey loadCertificateFromFile(String code)
				throws IOException, CertificateException,
				NoSuchProviderException, InvalidKeyException,
				NoSuchAlgorithmException, SignatureException {

			//PrivateKey localKey = null;
			SecurityKey securityKey = this.keys.get(code);
			String path=null;
			if(this.base.startsWith("http://")){
				path=this.base + splash+ securityKey.folder
						+ splash+ securityKey.remotePublicKey;
			}else{
				path= this.base + File.separator + securityKey.folder
					+ File.separator + securityKey.remotePublicKey;
			}
			PEMReader pr = openPEMResource(path, null);

			CertificateFactory fact = CertificateFactory.getInstance("X.509",
					"BC");
			ByteArrayInputStream bin = new ByteArrayInputStream(pr
					.readPemObject().getContent());
			java.security.cert.Certificate cert = fact.generateCertificate(bin);// (X509Certificate)fact.generateCertificate(bin);
			// /bin.close();
			PublicKey remoteKey = cert.getPublicKey();
			PublicKey rootca = getCA();
			cert.verify(rootca);

			pr.close();
			return remoteKey;
		}

		private PrivateKey doOpenSslFile(String code, Class expectedPrivKeyClass)
				throws IOException {

			PrivateKey localKey = null;
			SecurityKey securityKey = this.keys.get(code);
			String path=null;
			if(this.base.startsWith("http://")){
				path=this.base + splash+ securityKey.folder
						+ splash+ securityKey.privateKey;
			}else{
			 path= this.base + File.separator + securityKey.folder
					+ File.separator + securityKey.privateKey;
			}
			PEMReader pr = openPEMResource(path,
					new Password(securityKey.password.toCharArray()));
			Object o = pr.readObject();
			pr.close();
			if (o == null || !(o instanceof KeyPair)) {
				// fail("Didn't find OpenSSL key");
			}

			KeyPair kp = (KeyPair) o;
			localKey = kp.getPrivate();

			if (!expectedPrivKeyClass.isInstance(localKey)) {
				// fail("Returned key not of correct type");
			}

			return localKey;
		}

	};

	
	private SecurityKeyStore securityKeyStore;
	

	
	/**
	 * 
	 */
	public SecurityConfiguration() {
		// TODO Auto-generated constructor stub
		super();
		
		
		
	}
	
	

	public SecurityKeyStore getSecurityKeyStore() {
		return securityKeyStore;
	}



	public void setSecurityKeyStore(SecurityKeyStore securityKeyStore) {
		this.securityKeyStore = securityKeyStore;
	}
	
	public void init(){
		try {
			this.securityKeyStore=this.parse();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public SecurityKeyStore parse() throws IOException, SAXException {
		SecurityKeyStore keystore=null;
		Digester digester = new Digester();
		digester.setValidating(false);

		digester.addObjectCreate("KeyStore/Key", SecurityKey.class);
		digester.addObjectCreate("KeyStore", SecurityKeyStore.class);

		digester.addSetProperties("KeyStore", "base", "base");
		
		digester.addSetProperties("KeyStore", "cacrt", "cacrt");

		digester.addSetProperties("KeyStore/Key", "code", "code");
		digester.addSetProperties("KeyStore/Key", "name", "name");
		digester.addSetProperties("KeyStore/Key", "password", "password");
		digester.addSetProperties("KeyStore/Key", "blockSize", "blockSize");
		digester.addSetProperties("KeyStore/Key", "signature", "signature");
		digester.addSetProperties("KeyStore/Key", "siglen", "siglen");
		digester.addSetProperties("KeyStore/Key", "folder", "folder");
		digester.addSetProperties("KeyStore/Key", "privateKey", "privateKey");
		digester.addSetProperties("KeyStore/Key", "remotePublicKey", "remotePublicKey");
		// digester.addCallMethod("address-book/contact/province",
		// "setProvince", 0);

		digester.addSetNext("KeyStore/Key", "addKey");
		
		if(keystore==null){
			boolean enableHttpResources=minaConfiguration.getSecurityConfiguration().startsWith("http://");
			if(!enableHttpResources){
				String config=RouteConfiguration.class.getResource(minaConfiguration.getSecurityConfiguration()).getFile();
				File f=new File(config);
				logger.info("config file="+f.getAbsolutePath());
				keystore = (SecurityKeyStore) digester.parse(f);
			}else{
				String url=minaConfiguration.getSecurityConfiguration();
				URL httpUrl=new URL(url);
				logger.info("config file="+httpUrl.toString());
				keystore = (SecurityKeyStore) digester.parse(httpUrl);
				
			}
		}
		
		return keystore;

	}

}
