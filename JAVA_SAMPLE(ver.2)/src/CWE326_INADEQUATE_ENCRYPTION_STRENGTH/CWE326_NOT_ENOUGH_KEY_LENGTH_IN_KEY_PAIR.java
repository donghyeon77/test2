package CWE326_INADEQUATE_ENCRYPTION_STRENGTH;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

/*
 * 검출목록
 * 주요검출 :: CWE326_UsingNotEnoughKeyLength - 충분하지 않은 키 길이 사용
 */

// 충분하지 않은 키 길이 사용
public class CWE326_NOT_ENOUGH_KEY_LENGTH_IN_KEY_PAIR
{
	public void Bad(String[] arg)
	{
		String cipherName = "RSA";
		KeyPairGenerator keyGen = null;
		try
		{
			keyGen = KeyPairGenerator.getInstance(cipherName);
			/* FLAW */
			keyGen.initialize(512);
		}
		catch ( NoSuchAlgorithmException e )
		{
			System.out.println( "NoSuchAlgorithmException Occured!!" );
		}
		
	}
	
/* ACA hold
public void ACA(String[] arg)
	{
		String cipherName = "RSA";
		KeyPairGenerator keyGen = null;
		try
		{
			keyGen = KeyPairGenerator.getInstance(cipherName);
			
			keyGen.initialize(512);
		}
		catch ( NoSuchAlgorithmException e )
		{
			System.out.println( "NoSuchAlgorithmException Occured!!" );
		}
		
	}
*/
	
	public void Good(String[] arg)
	{
		String cipherName = "RSA";
		KeyPairGenerator keyGen;
		try
		{
			keyGen = KeyPairGenerator.getInstance(cipherName);
			/* FIX */
			keyGen.initialize(4096);
		}
		catch ( NoSuchAlgorithmException e )
		{
			System.out.println( "NoSuchAlgorithmException Occured!!" );
		}
	}
}