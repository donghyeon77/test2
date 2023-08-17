package CWE327_USE_OF_BROKEN_OR_RISKY_CRYPTOGRAPHIC_ALGORITHM;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/*
 * 검출목록
 * 주요검출 :: CWE327_UsingVulnerableEncryptionAlgorithm - 취약한 암호화 알고리즘 사용
 */

public class CWE327_INSECURE_CIPHER
{	
	public void Bad_DES()
	{
		final String CIPHER_INPUT = "ABCDEFG123456";

		try{
			//KeyGenerator
			byte[] bytekey = getSecretKey("DES");
			//SecretKeySpec
			SecretKeySpec secretKeySpec = getSecretKeySpec("DES", bytekey);
			
			/* FLAW */
	        Cipher cipher = Cipher.getInstance("DES");
	        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

	        byte[] encrypted = cipher.doFinal(CIPHER_INPUT.getBytes("UTF-8"));        
			
		}catch (NoSuchPaddingException e) {
			System.out.println( "NoSuchPaddingException Occured!!" );
		}catch (NoSuchAlgorithmException e) {
			System.out.println( "NoSuchAlgorithmException Occured!!" );
		}catch (IllegalBlockSizeException e) {
			System.out.println( "IllegalBlockSizeException Occured!!" );
		}catch (BadPaddingException e) {
			System.out.println( "BadPaddingException Occured!!" );
		}catch (UnsupportedEncodingException e) {
			System.out.println( "UnsupportedEncodingException Occured!!" );
		}catch (InvalidKeyException e) {
			System.out.println( "InvalidKeyException Occured!!" );
		}    
	}

	
	public void Bad_TDES()
	{
		final String CIPHER_INPUT = "ABCDEFG123456";

		try{
			//KeyGenerator
			byte[] bytekey = getSecretKey("DESede");
			//SecretKeySpec
			SecretKeySpec secretKeySpec = getSecretKeySpec("DESede", bytekey);
			
			/* FLAW */
	        Cipher cipher = Cipher.getInstance("DESede");
	        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

	        byte[] encrypted = cipher.doFinal(CIPHER_INPUT.getBytes("UTF-8"));        
			
		}catch (NoSuchPaddingException e) {
			System.out.println( "NoSuchPaddingException Occured!!" );
		}catch (NoSuchAlgorithmException e) {
			System.out.println( "NoSuchAlgorithmException Occured!!" );
		}catch (IllegalBlockSizeException e) {
			System.out.println( "IllegalBlockSizeException Occured!!" );
		}catch (BadPaddingException e) {
			System.out.println( "BadPaddingException Occured!!" );
		}catch (UnsupportedEncodingException e) {
			System.out.println( "UnsupportedEncodingException Occured!!" );
		}catch (InvalidKeyException e) {
			System.out.println( "InvalidKeyException Occured!!" );
		}    
	}

	
	public void Bad_RC4()
	{
		final String CIPHER_INPUT = "ABCDEFG123456";

		try{
			//KeyGenerator
			byte[] bytekey = getSecretKey("RC4");
			//SecretKeySpec
			SecretKeySpec secretKeySpec = getSecretKeySpec("RC4", bytekey);
			
			/* FLAW */
	        Cipher cipher = Cipher.getInstance("RC4");
	        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

	        byte[] encrypted = cipher.doFinal(CIPHER_INPUT.getBytes("UTF-8"));        
			
		}catch (NoSuchPaddingException e) {
			System.out.println( "NoSuchPaddingException Occured!!" );
		}catch (NoSuchAlgorithmException e) {
			System.out.println( "NoSuchAlgorithmException Occured!!" );
		}catch (IllegalBlockSizeException e) {
			System.out.println( "IllegalBlockSizeException Occured!!" );
		}catch (BadPaddingException e) {
			System.out.println( "BadPaddingException Occured!!" );
		}catch (UnsupportedEncodingException e) {
			System.out.println( "UnsupportedEncodingException Occured!!" );
		}catch (InvalidKeyException e) {
			System.out.println( "InvalidKeyException Occured!!" );
		}    
	}

	
	public void Bad_RC5()
	{
		final String CIPHER_INPUT = "ABCDEFG123456";

		try{
			//KeyGenerator
			byte[] bytekey = getSecretKey("RC5");
			//SecretKeySpec
			SecretKeySpec secretKeySpec = getSecretKeySpec("RC5", bytekey);
			
			/* FLAW */
	        Cipher cipher = Cipher.getInstance("RC5");
	        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

	        byte[] encrypted = cipher.doFinal(CIPHER_INPUT.getBytes("UTF-8"));        
			
		}catch (NoSuchPaddingException e) {
			System.out.println( "NoSuchPaddingException Occured!!" );
		}catch (NoSuchAlgorithmException e) {
			System.out.println( "NoSuchAlgorithmException Occured!!" );
		}catch (IllegalBlockSizeException e) {
			System.out.println( "IllegalBlockSizeException Occured!!" );
		}catch (BadPaddingException e) {
			System.out.println( "BadPaddingException Occured!!" );
		}catch (UnsupportedEncodingException e) {
			System.out.println( "UnsupportedEncodingException Occured!!" );
		}catch (InvalidKeyException e) {
			System.out.println( "InvalidKeyException Occured!!" );
		}    
	}

	
	public void Bad_RC6()
	{
		final String CIPHER_INPUT = "ABCDEFG123456";

		try{
			//KeyGenerator
			byte[] bytekey = getSecretKey("RC6");
			//SecretKeySpec
			SecretKeySpec secretKeySpec = getSecretKeySpec("RC6", bytekey);
			
			/* FLAW */
	        Cipher cipher = Cipher.getInstance("RC6");
	        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

	        byte[] encrypted = cipher.doFinal(CIPHER_INPUT.getBytes("UTF-8"));        
			
		}catch (NoSuchPaddingException e) {
			System.out.println( "NoSuchPaddingException Occured!!" );
		}catch (NoSuchAlgorithmException e) {
			System.out.println( "NoSuchAlgorithmException Occured!!" );
		}catch (IllegalBlockSizeException e) {
			System.out.println( "IllegalBlockSizeException Occured!!" );
		}catch (BadPaddingException e) {
			System.out.println( "BadPaddingException Occured!!" );
		}catch (UnsupportedEncodingException e) {
			System.out.println( "UnsupportedEncodingException Occured!!" );
		}catch (InvalidKeyException e) {
			System.out.println( "InvalidKeyException Occured!!" );
		}    
	}

/* 
	public void ACA()
	{
		final String CIPHER_INPUT = "ABCDEFG123456";

		try{
			
//ACA postion : cause replace
//${hsAcaTarget.lineSplitMid} : "DES", "DESede", "RC4", "RC5", "RC6"
//getSecretKey("AES")
//SecretKeySpec(bytekey, "AES");
//Cipher.getInstance("AES");

			//KeyGenerator
			byte[] bytekey = getSecretKey("AES");
			
			//SecretKeySpec
			SecretKeySpec secretKeySpec = getSecretKeySpec("AES", bytekey);
			
	        Cipher cipher = Cipher.getInstance("AES");
	        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

	        byte[] encrypted = cipher.doFinal(CIPHER_INPUT.getBytes("UTF-8"));        
			
		}catch (NoSuchPaddingException e) {
			System.out.println( "NoSuchPaddingException Occured!!" );
		}catch (NoSuchAlgorithmException e) {
			System.out.println( "NoSuchAlgorithmException Occured!!" );
		}catch (IllegalBlockSizeException e) {
			System.out.println( "IllegalBlockSizeException Occured!!" );
		}catch (BadPaddingException e) {
			System.out.println( "BadPaddingException Occured!!" );
		}catch (UnsupportedEncodingException e) {
			System.out.println( "UnsupportedEncodingException Occured!!" );
		}catch (InvalidKeyException e) {
			System.out.println( "InvalidKeyException Occured!!" );
		}    
	}
*/
	
	public void Good()
	{
		final String CIPHER_INPUT = "ABCDEFG123456";

		try{
			//KeyGenerator
			byte[] bytekey = getSecretKey("AES");
			//SecretKeySpec
			SecretKeySpec secretKeySpec = getSecretKeySpec("AES", bytekey);
			
			/* FIX */
	        Cipher cipher = Cipher.getInstance("AES");
	        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

	        byte[] encrypted = cipher.doFinal(CIPHER_INPUT.getBytes("UTF-8"));        
			
		}catch (NoSuchPaddingException e) {
			System.out.println( "NoSuchPaddingException Occured!!" );
		}catch (NoSuchAlgorithmException e) {
			System.out.println( "NoSuchAlgorithmException Occured!!" );
		}catch (IllegalBlockSizeException e) {
			System.out.println( "IllegalBlockSizeException Occured!!" );
		}catch (BadPaddingException e) {
			System.out.println( "BadPaddingException Occured!!" );
		}catch (UnsupportedEncodingException e) {
			System.out.println( "UnsupportedEncodingException Occured!!" );
		}catch (InvalidKeyException e) {
			System.out.println( "InvalidKeyException Occured!!" );
		}   
	}	
	
	public byte[] getSecretKey(String keyType)
	{
		byte[] byteKey = null;
		try{
			KeyGenerator keyGenerator = KeyGenerator.getInstance(keyType);
			keyGenerator.init(128);
	        SecretKey secretKey = keyGenerator.generateKey();
	        byteKey = secretKey.getEncoded();
		}catch (NoSuchAlgorithmException e) {
			System.out.println( "NoSuchAlgorithmException Occured!!" );
		}   
		return byteKey;
	}
	
	public SecretKeySpec getSecretKeySpec(String keyType, byte[] byteKey)
	{
		SecretKeySpec secretKeySpec = new SecretKeySpec(byteKey, keyType);
		
		return secretKeySpec;
	}
}