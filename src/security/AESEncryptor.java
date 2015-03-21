package security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * 目前Java預設只支援到128bit的AES對稱加密
 * 
 * @author coreyou
 *
 */
public class AESEncryptor {
	private SecretKeySpec secretKeySpec;
	// Cipher 負責完成加密或解密工作
	private Cipher cipher;
	
	public AESEncryptor() throws NoSuchAlgorithmException, NoSuchPaddingException {
		// TODO Auto-generated constructor stub
		// KeyGenerator 提供對稱密鑰生成器的功能，支援各種演算法
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(128);
		
		// 產生Secret Key Spec
		// SecretKey 負責保存對稱密鑰
		SecretKey secretKey = keyGen.generateKey();
		byte[] raw = secretKey.getEncoded();
		this.secretKeySpec = new SecretKeySpec(raw, "AES");
		
		// 初始化cipher
		this.cipher = Cipher.getInstance("AES");
	}
	
	public AESEncryptor(String key) throws NoSuchAlgorithmException, NoSuchPaddingException {
		// TODO Auto-generated constructor stub
		// KeyGenerator 提供對稱密鑰生成器的功能，支援各種演算法
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(128, new SecureRandom(key.getBytes()));
		
		// 產生Secret Key Spec
		// SecretKey 負責保存對稱密鑰
		SecretKey secretKey = keyGen.generateKey();
		byte[] raw = secretKey.getEncoded();
		this.secretKeySpec = new SecretKeySpec(getHash("MD5", key), "AES");
		// 可以和上一行互相置換
//		this.secretKeySpec = new SecretKeySpec(raw, "AES");
		
		// 初始化cipher
		this.cipher = Cipher.getInstance("AES");
	}
	
	private static byte[] getHash(final String algorithm, final byte[] data) {
		try {
			final MessageDigest digest = MessageDigest.getInstance(algorithm);
			digest.update(data);
			return digest.digest();
		} catch (final Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}
	
	private static byte[] getHash(final String algorithm, final String text) {
		try {
			return getHash(algorithm, text.getBytes("UTF-8"));
		} catch (final Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}
	
	public SecretKeySpec getSecretKeySpec() {
		return secretKeySpec;
	}
	
	public void setSecretKeySpec(SecretKeySpec secretKeySpec) {
		this.secretKeySpec = secretKeySpec;
	}
	
	public Cipher getCipher() {
		return cipher;
	}
	
	public void setCipher(Cipher cipher) {
		this.cipher = cipher;
	}
	
	/**
	 * 輸入未加密位元，輸出加密位元
	 * @param original
	 * @return
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public byte[] encrypt(byte[] original) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		this.getCipher().init(Cipher.ENCRYPT_MODE, secretKeySpec);
		byte[] encrypted = cipher.doFinal(original);
		return encrypted;
	}
	
	/**
	 * 輸入加密位元，輸出解密位元
	 * @param original
	 * @return
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public byte[] decrypt(byte[] original) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		this.getCipher().init(Cipher.DECRYPT_MODE, secretKeySpec);		
		byte[] decrypted = cipher.doFinal(original);
		return decrypted;
	}
	
	/**
	 * 輸入未加密字串，輸出加密字串
	 * @param inputString
	 * @return
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws UnsupportedEncodingException 
	 */
	public String stringEncryptString(String inputString) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		this.getCipher().init(Cipher.ENCRYPT_MODE, secretKeySpec);
		byte[] encrypted = cipher.doFinal(inputString.getBytes("UTF-8"));
		return new String(Base64.encodeBase64(encrypted), "UTF-8");
	}
	
	/**
	 * 輸入加密字串，輸出解密字串
	 * @param inputString
	 * @return
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws UnsupportedEncodingException 
	 */
	public String stringDecryptString(String inputString) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		this.getCipher().init(Cipher.DECRYPT_MODE, secretKeySpec);		
		byte[] decrypted = cipher.doFinal(Base64.decodeBase64(inputString));
		return new String(decrypted, "UTF-8");
	}
	
	/**
	 * 將未加密檔案inputFile，以encryptKey為加密金鑰，加密後複製到outputFilePath檔案名稱
	 * @param inputFile
	 * @param outputFilePath
	 * @param encryptKey
	 * @throws Exception
	 */
	public void fileEncryptFile(File inputFile, String outputFilePath) throws Exception {
		FileInputStream fis = new FileInputStream(inputFile);
		long length = inputFile.length();
	    byte[] bytes = new byte[(int)length];
	    int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead = fis.read(bytes, offset, bytes.length - offset)) >= 0) 
        {
            offset += numRead;
        }
        byte[] en = this.encrypt(bytes);
       
        FileOutputStream fos = new FileOutputStream(new File(outputFilePath));     
	    fos.write(en);
	    fis.close();
	    fos.close();
	}
	
	/**
	 * 將加密檔案inputFile，以encryptKey為加密金鑰，解密後複製到outputFilePath檔案名稱
	 * @param inputFile
	 * @param outputFilePath
	 * @param encryptKey
	 * @throws Exception
	 */
	public void fileDecryptFile(File inputFile, String outputFilePath) throws Exception {
		FileInputStream fis = new FileInputStream(inputFile);
		long length = inputFile.length();
	    byte[] bytes = new byte[(int)length];
	    int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead = fis.read(bytes, offset, bytes.length - offset)) >= 0) 
        {
            offset += numRead;
        }

        byte[] de = this.decrypt(bytes);
        
        FileOutputStream fos = new FileOutputStream(new File(outputFilePath)); 
	    fos.write(de);
	    fis.close();
	    fos.close();
	}
	
	public static void main(String[] args) {
	    try {
	    	String key = "handsome";
	    	AESEncryptor aes = new AESEncryptor(key);
	    	
	    	System.out.println("===Demo String AES Encrypting===");
	    	String plainText = "plainText";
	    	System.out.println("plainText: " + plainText);
	    	String cryptText = aes.stringEncryptString(plainText);
	    	System.out.println("after encrypt: " + cryptText);
	    	String decryptText = aes.stringDecryptString(cryptText);
	    	System.out.println("after decrypt: " + decryptText + "\n");
		
	    	System.out.println("===Demo File AES Encrypting===");
	    	File file = new File("C:\\Users\\coreyou\\workspace\\coreyou.corp\\src\\coreyouCorp.properties");
			FileInputStream fis = new FileInputStream(file);
			long length = file.length();
		    byte[] bytes = new byte[(int)length];
		    int offset = 0;
	        int numRead = 0;
	        while (offset < bytes.length && (numRead = fis.read(bytes, offset, bytes.length-offset)) >= 0) 
	        {
	            offset += numRead;
	        }	        

	        byte[] en = aes.encrypt(bytes);
	        
	        FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\coreyou\\workspace\\coreyou.corp\\src\\coreyouCorp_2.properties")); 
		    byte[] de = aes.decrypt(en);
		    
//		    fos.write(de);
		    fos.write(en);
		    
		    fis.close();
		    fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
}
