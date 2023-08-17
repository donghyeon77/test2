package CWE247_RELIANCE_ON_DNS_LOOKUPS_IN_SECURITY_DECISION;

import java.io.IOException;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 검출목록
 * 주요검출 :: CWE247_SecurityDecisionDependentDNSLookup - DNS lookup에 의존한 보안결정
 */

// DNS lookup에 의존한 보안결정
public class CWE247_DNS_LOOKUP_INETADDRESS_GETBYNAME
{
	public void Bad(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		boolean trusted = false;
		
		String ip = req.getRemoteAddr();
		/* FLAW */
		InetAddress addr = InetAddress.getByName(ip);
		if(addr.getCanonicalHostName().endsWith("trustme.com"))
		{
			trusted = true;
			//보안 결정 실행
		}
	}

/* ACA hold 
	public void ACA(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		boolean trusted = false;
		
		String ip = req.getRemoteAddr();
		
		InetAddress addr = InetAddress.getByName(ip);
		if(addr.getCanonicalHostName().endsWith("trustme.com"))
		{
			trusted = true;
			//보안 결정 실행
		}
	}
*/
 
	public void Good(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		boolean trusted = false;
		
		String ip = req.getRemoteAddr();
		/* FIX */
		if( ip == null || "".equals( ip ) )
		{
			return;
		}
		String trustedAddr = "127.0.0.1";
		if( ip.equals( trustedAddr ) )
		{
			trusted = true;
			//보안 결정 실행
		}
	}
}