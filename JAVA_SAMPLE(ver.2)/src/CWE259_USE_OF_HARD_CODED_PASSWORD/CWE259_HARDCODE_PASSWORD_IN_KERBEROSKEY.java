package CWE259_USE_OF_HARD_CODED_PASSWORD;

import java.util.Properties;
import javax.security.auth.kerberos.KerberosKey;
import javax.security.auth.kerberos.KerberosPrincipal;

/*
 * 검출목록
 * 주요검출 :: CWE259_HardCodePassword - 하드코드된 비밀번호
 * 부가검출 :: CWE256_중요정보 평문 저장
 */

// 하드코드된 비밀번호
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