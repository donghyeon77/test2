package CWE327_USE_OF_BROKEN_OR_RISKY_CRYPTOGRAPHIC_ALGORITHM;

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

/*
 * 검출목록
 * 주요검출 :: CWE327_UsingVulnerableEncryptionAlgorithm - 취약한 암호화 알고리즘 사용
 */

public class CWE327_INSECURE_HASH
{	
	public byte[] Bad_MD2(String password, byte[] salt) throws NoSuchAlgorithmException
	{
		/* FLAW */
		MessageDigest digest = MessageDigest.getInstance("MD2");
		digest.reset();
		digest.update(salt);
		try
		{	
			return digest.digest(password.getBytes("UTF-8"));
		}
		catch ( UnsupportedEncodingException e )
		{
			System.out.println( "UnsupportedEncodingException Occured!!" );
		}
		
		return null;
	}
	
	public byte[] Bad_MD5(String password, byte[] salt) throws NoSuchAlgorithmException
	{
		/* FLAW */
		MessageDigest digest = MessageDigest.getInstance("MD5");
		digest.reset();
		digest.update(salt);
		try
		{	
			return digest.digest(password.getBytes("UTF-8"));
		}
		catch ( UnsupportedEncodingException e )
		{
			System.out.println( "UnsupportedEncodingException Occured!!" );
		}
		
		return null;
	}
	
	public byte[] Bad_SHA1(String password, byte[] salt) throws NoSuchAlgorithmException
	{
		/* FLAW */
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		digest.reset();
		digest.update(salt);
		try
		{	
			return digest.digest(password.getBytes("UTF-8"));
		}
		catch ( UnsupportedEncodingException e )
		{
			System.out.println( "UnsupportedEncodingException Occured!!" );
		}
		
		return null;
	}
	
/* 
	public byte[] Bad_SHA1(String password, byte[] salt) throws NoSuchAlgorithmException
	{
//ACA postion : cause replace
//${hsAcaTarget.lineSplitMid} : "SHA-1", "MD5", "MD2"
//MessageDigest.getInstance("SHA-256")
	
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		digest.reset();
		digest.update(salt);
		try
		{	
			return digest.digest(password.getBytes("UTF-8"));
		}
		catch ( UnsupportedEncodingException e )
		{
			System.out.println( "UnsupportedEncodingException Occured!!" );
		}
		
		return null;
	}
 */
	
	public byte[] Good(String password, byte[] salt) throws NoSuchAlgorithmException
	{
		/* FIX */
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		digest.reset();
		digest.update(salt);
		try
		{
			return digest.digest(password.getBytes("UTF-8"));
		}
		catch ( UnsupportedEncodingException e )
		{
			System.out.println( "UnsupportedEncodingException Occured!!" );
		}
		
		return null;
	}

	
	public byte[] getSecureRandomSalt(){
		
		byte[] returnSalt = null;	
		try
		{
			SecureRandom prng = SecureRandom.getInstance("SHA256PRNG");
			String randomNum = new Integer(prng.nextInt()).toString();
			
			//SecureRandom 클래스를 이용하여 안전한 솔트 생성
			returnSalt = randomNum.getBytes();
				 
		}catch (NoSuchAlgorithmException e){
		   System.out.println("NoSuchAlgorithmException Message");
		}
		return returnSalt;		
	}
}