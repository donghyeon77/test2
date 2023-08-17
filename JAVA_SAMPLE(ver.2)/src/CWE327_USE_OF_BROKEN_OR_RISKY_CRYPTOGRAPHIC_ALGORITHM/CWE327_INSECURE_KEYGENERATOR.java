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

public class CWE327_INSECURE_KEYGENERATOR
{
	public void Bad_DES()
	{	
		try
		{
			/* FLAW */
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
			keyGenerator.init(128);
	        SecretKey secretKey = keyGenerator.generateKey();
	        byte[] byteKey = secretKey.getEncoded();
	        
	        //SecretKeySpec ...
	        //Cipher ...	
		}catch (NoSuchAlgorithmException e) {
			System.out.println( "NoSuchAlgorithmException Occured!!" );
		}    
	}

	
	public void Bad_TDES()
	{	
		try
		{
			/* FLAW */
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
			keyGenerator.init(128);
	        SecretKey secretKey = keyGenerator.generateKey();
	        byte[] byteKey = secretKey.getEncoded();
	        
	        //SecretKeySpec ...
	        //Cipher ...	
		}catch (NoSuchAlgorithmException e) {
			System.out.println( "NoSuchAlgorithmException Occured!!" );
		}    
	}

	
	public void Bad_RC4()
	{
		try
		{
			/* FLAW */
			KeyGenerator keyGenerator = KeyGenerator.getInstance("RC4");
			keyGenerator.init(128);
	        SecretKey secretKey = keyGenerator.generateKey();
	        byte[] byteKey = secretKey.getEncoded();
	        
	        //SecretKeySpec ...
	        //Cipher ...	
		}catch (NoSuchAlgorithmException e) {
			System.out.println( "NoSuchAlgorithmException Occured!!" );
		}    
	}

	
	public void Bad_RC5()
	{	
		try
		{
			/* FLAW */
			KeyGenerator keyGenerator = KeyGenerator.getInstance("RC5");
			keyGenerator.init(128);
	        SecretKey secretKey = keyGenerator.generateKey();
	        byte[] byteKey = secretKey.getEncoded();
	        
	        //SecretKeySpec ...
	        //Cipher ...	
		}catch (NoSuchAlgorithmException e) {
			System.out.println( "NoSuchAlgorithmException Occured!!" );
		}   
	}
	
	
	public void Bad_RC6()
	{
		try
		{
			/* FLAW */
			KeyGenerator keyGenerator = KeyGenerator.getInstance("RC6");
			keyGenerator.init(128);
	        SecretKey secretKey = keyGenerator.generateKey();
	        byte[] byteKey = secretKey.getEncoded();
	        
	        //SecretKeySpec ...
	        //Cipher ...	
		}catch (NoSuchAlgorithmException e) {
			System.out.println( "NoSuchAlgorithmException Occured!!" );
		}
		
		    
	}
	
/* 
	public void ACA()
	{
		try
		{
//ACA position : cause replace
//${hsAcaTarget.lineSplitMid} :"RC6", "RC5","RC4", "DESede"
//KeyGenerator.getInstance("AES")

			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128);
	        SecretKey secretKey = keyGenerator.generateKey();
	        byte[] byteKey = secretKey.getEncoded();
	        
	        //SecretKeySpec ...
	        //Cipher ...	
		}catch (NoSuchAlgorithmException e) {
			System.out.println( "NoSuchAlgorithmException Occured!!" );
		}
		
		    
	}
*/
	
	public void Good()
	{
		try
		{
			/* FIX */
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(256);
	        SecretKey secretKey = keyGenerator.generateKey();
	        byte[] byteKey = secretKey.getEncoded();
	        
	        //SecretKeySpec ...
	        //Cipher ...
		}catch (NoSuchAlgorithmException e) {
			System.out.println( "NoSuchAlgorithmException Occured!!" );
		}
	}	
}