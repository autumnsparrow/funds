/**
 * @company Palm Lottery Information&Technology Co.,Ltd.
 *
 * @author  sparrow
 *
 * @date    Oct 15, 2013
 *
 */
package com.palmcommerce.funds.configuration.v2;

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
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import org.bouncycastle.openssl.PEMReader;
import org.bouncycastle.openssl.PasswordFinder;




/**
 * @author sparrow
 *
 */
public class PEMFileLoader {
	
	static {
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
	}
	

	
	private static class Password implements PasswordFinder {
		char[] password;

		Password(char[] word) {
			this.password = word;
		}

		public char[] getPassword() {
			return password;
		}
	}


	/**
	 * 
	 */
	public PEMFileLoader() {
		// TODO Auto-generated constructor stub
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

	private PublicKey getCA(String path) throws CertificateException,
			NoSuchProviderException, IOException {
		
		
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
	
	

	public PublicKey loadCertificateFromFile(String path,String capath)
			throws IOException, CertificateException,
			NoSuchProviderException, InvalidKeyException,
			NoSuchAlgorithmException, SignatureException {

		//PrivateKey localKey = null;
		//SecurityKey securityKey = this.keys.get(code);
		
		PEMReader pr = openPEMResource(PEMFileLoader.class.getResource(path).getFile(), null);

		CertificateFactory fact = CertificateFactory.getInstance("X.509",
				"BC");
		ByteArrayInputStream bin = new ByteArrayInputStream(pr
				.readPemObject().getContent());
		java.security.cert.Certificate cert = fact.generateCertificate(bin);// (X509Certificate)fact.generateCertificate(bin);
		// /bin.close();
		PublicKey remoteKey = cert.getPublicKey();
		PublicKey rootca = getCA(PEMFileLoader.class.getResource(capath).getFile());
		cert.verify(rootca);

		pr.close();
		return remoteKey;
	}

	public PrivateKey loadPrivateKeyFromFile(String path, String password,Class expectedPrivKeyClass)
			throws IOException {

		PrivateKey localKey = null;
		
		
		PEMReader pr = openPEMResource(PEMFileLoader.class.getResource(path).getFile(),
				new Password(password.toCharArray()));
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


}
