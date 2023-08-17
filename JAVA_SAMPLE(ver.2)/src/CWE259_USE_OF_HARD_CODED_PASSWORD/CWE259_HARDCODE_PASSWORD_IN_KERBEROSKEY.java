package CWE259_USE_OF_HARD_CODED_PASSWORD;

import java.util.Properties;
import javax.security.auth.kerberos.KerberosKey;
import javax.security.auth.kerberos.KerberosPrincipal;

/*
 * ������
 * �ֿ���� :: CWE259_HardCodePassword - �ϵ��ڵ�� ��й�ȣ
 * �ΰ����� :: CWE256_�߿����� �� ����
 */

// �ϵ��ڵ�� ��й�ȣ
public class CWE259_HARDCODE_PASSWORD_IN_KERBEROSKEY
{
	public void Bad(String args[])
	{
		/* FLAW */
		String data = "7e5tc4s3";
        
        if (data != null)
        {
        	KerberosPrincipal principal = new KerberosPrincipal("test");            
            KerberosKey key = new KerberosKey(principal, data.toCharArray(), null);         
            System.out.println(key.toString());
        }
	}

/*  ACA hold
	public void ACA(String args[])
	{

		String data = "7e5tc4s3";
        
        if (data != null)
        {
        	KerberosPrincipal principal = new KerberosPrincipal("test");            
            KerberosKey key = new KerberosKey(principal, data.toCharArray(), null);         
            System.out.println(key.toString());
        }
	}
*/
	
	public void Good(Properties properties)
	{
		/* FIX */
		String data = properties.getProperty("dbPassword");
		if (data != null)
        {
			KerberosPrincipal principal = new KerberosPrincipal("test");            
            KerberosKey key = new KerberosKey(principal, data.toCharArray(), null);         
            System.out.println(key.toString());
        }
	}
}