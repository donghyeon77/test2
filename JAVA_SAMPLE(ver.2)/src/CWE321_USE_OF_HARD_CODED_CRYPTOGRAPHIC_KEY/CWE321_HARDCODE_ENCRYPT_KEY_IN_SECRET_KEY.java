package CWE321_USE_OF_HARD_CODED_CRYPTOGRAPHIC_KEY;

import java.util.Properties;
import java.security.NoSuchAlgorithmException;

import javax.crypto.NoSuchPaddingException;

/*
 * 검출목록
 * 주요검출 :: CWE321_EncryptKeyHardCode - 하드코드된 암호화 키
 */

// 하드코드된 암호화 키
public class CWE321_HARDCODE_ENCRYPT_KEY_IN_SECRET_KEY
{
	public void Bad(String[] arg)
	{
		byte[] privateKey = {'6', '8', 'a'};
		try
		{
			javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("RSA");
			/* FLAW */
			javax.crypto.SecretKey myDesKey = new javax.crypto.spec.SecretKeySpec(privateKey, "RSA");
		}
		catch ( NoSuchAlgorithmException e )
		{
			System.out.println( "NoSuchAlgorithmException Occured!!" );
		}
		catch ( NoSuchPaddingException e )
		{
			System.out.println( "NoSuchPaddingException Occured!!" );
		}
	}
	
/* ACA hold
	public void ACA(String[] arg)
	{
		byte[] privateKey = {'6', '8', 'a'};
		try
		{
			javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("RSA");
			
			javax.crypto.SecretKey myDesKey = new javax.crypto.spec.SecretKeySpec(privateKey, "RSA");
		}
		catch ( NoSuchAlgorithmException e )
		{
			System.out.println( "NoSuchAlgorithmException Occured!!" );
		}
		catch ( NoSuchPaddingException e )
		{
			System.out.println( "NoSuchPaddingException Occured!!" );
		}
	}
*/

	public void Good(String[] arg)
	{
		Properties pros = new Properties();
		String seed = null;
		try
		{
			/* FIX */
			seed = pros.getProperty( "password" );
			javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("RSA");
			javax.crypto.SecretKey myDesKey = new javax.crypto.spec.SecretKeySpec(seed.getBytes(), "RSA");
		}
		catch ( NoSuchAlgorithmException e )
		{
			System.out.println( "NoSuchAlgorithmException Occured!!" );
		}
		catch ( NoSuchPaddingException e )
		{
			System.out.println( "NoSuchPaddingException Occured!!" );
		}
		
	}
}