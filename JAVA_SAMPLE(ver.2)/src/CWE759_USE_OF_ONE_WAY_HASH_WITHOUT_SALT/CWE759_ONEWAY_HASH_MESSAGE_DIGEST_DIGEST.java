package CWE759_USE_OF_ONE_WAY_HASH_WITHOUT_SALT;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/*
 * 검출목록
 * 주요검출 :: CWE759_솔트 없이 일방향 해쉬 함수 사용
 */

public class CWE759_ONEWAY_HASH_MESSAGE_DIGEST_DIGEST
{
	public byte[] Bad(String password) throws NoSuchAlgorithmException
	{
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		digest.reset();
		try
		{
			/* FLAW */
			return digest.digest(password.getBytes("UTF-8"));
		}
		catch ( UnsupportedEncodingException e )
		{
			System.out.println( "UnsupportedEncodingException Occured!!" );
		}
		
		return null;
	}

/* ACA hold
	public byte[] ACA(String password, byte[] salt) throws NoSuchAlgorithmException
	{
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		digest.reset();
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
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		digest.reset();
		/* FIX */
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
	
	public byte[] Good2(String password) throws NoSuchAlgorithmException
	{
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		digest.reset();
		/* FIX */
		digest.update(getSecureRandomSalt());
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