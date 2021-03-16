package org.save.the.children.practicaltest.cryptography.engine;

import java.util.Base64;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class CryptographyEngine {

	Logger logger = Logger.getLogger(CryptographyEngine.class.toString());

	@Autowired
	Environment environment;

	/**
	 * Encrypt data passing String value
	 * 
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public String encryptData(String value) throws Exception {
		String encyptedVale = null;
		if(value != null){			
			logger.info("Start Encryption");
			encyptedVale = encryptWithAESKey(value.getBytes(), Base64.getDecoder().decode(environment.getProperty("save.the.children.cryptoKey")));
			logger.info("End Encryption");			
		}	
		return encyptedVale;
	}

	/**
	 * 
	 * Decrypt data passing String value
	 * 
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public String decryptData(String value) throws Exception {
		String decryptedValue = null;
		if(value != null) {	
			logger.info("Start Decryption");
			byte[] decryptedValueByte = decryptWithAESKey(value, Base64.getDecoder().decode(environment.getProperty("save.the.children.cryptoKey")));
			decryptedValue = new String(decryptedValueByte);
			logger.info("End Decryption");
		}		
		return decryptedValue;
	}

	/**
	 * encryption using AES key
	 * 
	 * @param dekData
	 * @param key
	 * @return
	 * @throws Exception
	 */
	private String encryptWithAESKey(byte[] PlainValue, byte[] key) throws Exception {
		SecretKey secKey = new SecretKeySpec(key, "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, secKey);
		byte[] plainValueByte = cipher.doFinal(PlainValue);
		return Base64.getEncoder().encodeToString(plainValueByte);
	}

	/**
	 * Decryption using AES key
	 * 
	 * @param storedDbValue
	 * @param key
	 * @return
	 * @throws Exception
	 */
	private byte[] decryptWithAESKey(String encryptedValue, byte[] key) throws Exception {
		Cipher cipher = Cipher.getInstance("AES");
		SecretKey secKey = new SecretKeySpec(key, "AES");
		cipher.init(Cipher.DECRYPT_MODE, secKey);
		byte[] decValueByte = cipher.doFinal(Base64.getDecoder().decode(encryptedValue.getBytes()));
		return decValueByte;
	}

	/**
	 * we can use this method for generate 128 AES key
	 * 
	 * @return
	 * @throws Exception
	 */
	public final String generateDEK() throws Exception {
		KeyGenerator keygenerator = KeyGenerator.getInstance("AES");
		keygenerator.init(128);
		SecretKey dekAESKey = keygenerator.generateKey();
		byte[] aesEncoded = dekAESKey.getEncoded();
		return Base64.getEncoder().encodeToString(aesEncoded);
	}

}
