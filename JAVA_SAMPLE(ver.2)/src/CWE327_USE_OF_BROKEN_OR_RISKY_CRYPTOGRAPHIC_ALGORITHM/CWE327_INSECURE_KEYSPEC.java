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

public class CWE327_INSECURE_KEYSPEC
{
	public void Bad_DES()
	{
		try{
			//KeyGenerator
			byte[] bytekey = getSecretKey("DES");
			
			/* FLAW */
			SecretKeySpec secretKeySpec = new SecretKeySpec(bytekey, "DES");
			
	        //Cipher ...       
		}catch (NoSuchAlgorithmException e) {
			System.out.println( "NoSuchAlgorithmException Occured!!" );
		}   
	}
	
	public void Bad_TDES()
	{	
		try{
			//KeyGenerator
			byte[] bytekey = getSecretKey("DESede");
			
			/* FLAW */
			SecretKeySpec secretKeySpec = new SecretKeySpec(bytekey, "DESede");
			
	        //Cipher ...       
		}catch (NoSuchAlgorithmException e) {
			System.out.println( "NoSuchAlgorithmException Occured!!" );
		}   
	}
	
	public void Bad_RC4()
	{
		try{
			//KeyGenerator
			byte[] bytekey = getSecretKey("RC4");
			
			/* FLAW */
			SecretKeySpec secretKeySpec = new SecretKeySpec(bytekey, "RC4");
			
	        //Cipher ...       
		}catch (NoSuchAlgorithmException e) {
			System.out.println( "NoSuchAlgorithmException Occured!!" );
		}   
	}
	
	public void Bad_RC5()
	{	
		try{
			//KeyGenerator
			byte[] bytekey = getSecretKey("RC5");
			
			/* FLAW */
			SecretKeySpec secretKeySpec = new SecretKeySpec(bytekey, "RC5");
			
	        //Cipher ...       
		}catch (NoSuchAlgorithmException e) {
			System.out.println( "NoSuchAlgorithmException Occured!!" );
		}   
	}
	
	public void Bad_RC6()
	{
		try{
			//KeyGenerator
			byte[] bytekey = getSecretKey("RC6");
			
			/* FLAW */
			SecretKeySpec secretKeySpec = new SecretKeySpec(bytekey, "RC6");
			
	        //Cipher ...       
		}catch (NoSuchAlgorithmException e) {
			System.out.println( "NoSuchAlgorithmException Occured!!" );
		}   
	}

/*
	public void ACA_RC6()
	{
		try{
			
//ACA postion : cause replace
//${hsAcaTarget.lineSplitMid} : "DES", "DESede", "RC4", "RC5", "RC6"
//getSecretKey("AES")
//SecretKeySpec(bytekey, "AES");

			//KeyGenerator
			byte[] bytekey = getSecretKey("AES");
			
			
			SecretKeySpec secretKeySpec = new SecretKeySpec(bytekey, "AES");
			
	        //Cipher ...       
		}catch (NoSuchAlgorithmException e) {
			System.out.println( "NoSuchAlgorithmException Occured!!" );
		}   
	}
*/

	public void Good()
	{
		try{
			//KeyGenerator
			byte[] bytekey = getSecretKey("AES");
			//SecretKeySpec
			SecretKeySpec secretKeySpec = new SecretKeySpec(bytekey, "AES");
			
			//Cipher ...
		}catch (NoSuchAlgorithmException e) {
			System.out.println( "NoSuchAlgorithmException Occured!!" );
		} 
	}	
	
	public byte[] getSecretKey(String keyType) throws NoSuchAlgorithmException
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
}