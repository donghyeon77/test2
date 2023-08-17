package CWE326_INADEQUATE_ENCRYPTION_STRENGTH;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

/*
 * ������
 * �ֿ���� :: CWE326_UsingNotEnoughKeyLength - ������� ���� Ű ���� ���
 */

// ������� ���� Ű ���� ���
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