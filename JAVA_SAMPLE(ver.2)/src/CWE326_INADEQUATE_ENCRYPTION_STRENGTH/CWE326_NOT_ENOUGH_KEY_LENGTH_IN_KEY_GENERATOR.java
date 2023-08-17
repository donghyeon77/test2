package CWE326_INADEQUATE_ENCRYPTION_STRENGTH;

import java.security.NoSuchAlgorithmException;
import javax.crypto.KeyGenerator;

/*
 * 검출목록
 * 주요검출 :: CWE326_UsingNotEnoughKeyLength - 충분하지 않은 키 길이 사용
 */

// 충분하지 않은 키 길이 사용
public class CWE326_NOT_ENOUGH_KEY_LENGTH_IN_KEY_GENERATOR
{
	public void Bad(String[] arg)
	{
		String cipherName = "DES";
		KeyGenerator keyGen = null;
		try
		{
			keyGen = KeyGenerator.getInstance(cipherName);
			/* FLAW */
			keyGen.init(56);
		}
		catch ( NoSuchAlgorithmException e )
		{
			System.out.println( "NoSuchAlgorithmException Occured!!" );
		}
		
	}
	
/* ACA hold
	public void ACA(String[] arg)
	{
		String cipherName = "DES";
		KeyGenerator keyGen = null;
		try
		{
			keyGen = KeyGenerator.getInstance(cipherName);
			
			keyGen.init(56);
		}
		catch ( NoSuchAlgorithmException e )
		{
			System.out.println( "NoSuchAlgorithmException Occured!!" );
		}
		
	}
*/
	
	public void Good(String[] arg)
	{
		String cipherName = "AES";
		KeyGenerator keyGen = null;
		try
		{
			keyGen = KeyGenerator.getInstance(cipherName);
			/* FLAW */
			keyGen.init(256);
		}
		catch ( NoSuchAlgorithmException e )
		{
			System.out.println( "NoSuchAlgorithmException Occured!!" );
		}
	}
}