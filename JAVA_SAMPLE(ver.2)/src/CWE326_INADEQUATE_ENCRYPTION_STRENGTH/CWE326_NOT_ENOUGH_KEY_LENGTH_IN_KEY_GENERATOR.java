package CWE326_INADEQUATE_ENCRYPTION_STRENGTH;

import java.security.NoSuchAlgorithmException;
import javax.crypto.KeyGenerator;

/*
 * ������
 * �ֿ���� :: CWE326_UsingNotEnoughKeyLength - ������� ���� Ű ���� ���
 */

// ������� ���� Ű ���� ���
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