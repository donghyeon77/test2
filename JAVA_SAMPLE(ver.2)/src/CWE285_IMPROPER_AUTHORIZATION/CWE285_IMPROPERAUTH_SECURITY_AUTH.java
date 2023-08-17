package CWE285_IMPROPER_AUTHORIZATION;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.ldap.LdapContext;

/*
 * 검출목록
 * 주요검출 :: CWE285_부적절한 인가
 */

public class CWE285_IMPROPERAUTH_SECURITY_AUTH {
	public void Bad(String sSingleld, int iFlag, String sServiceProvider, String sUid, String sPwd)
	{
		//LDAP 접속 환경설정
		Hashtable<String, String> environment = new Hashtable<String, String>();
		environment.put(LdapContext.CONTROL_FACTORIES, "ldap.factories.control");
		environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
		environment.put(Context.PROVIDER_URL, sServiceProvider);
		/* FLAW */
		environment.put(Context.SECURITY_AUTHENTICATION, "none");
		environment.put(Context.SECURITY_PRINCIPAL, sUid);
		environment.put(Context.SECURITY_CREDENTIALS, sPwd);
		
		try {
			DirContext ctx = new InitialDirContext(environment); //LDAP 서버와의 연결 생성
		} catch (NamingException e) {
			System.out.println("NamingException Message");
		}
	}

/* ACA hold
	public void ACA(String sSingleld, int iFlag, String sServiceProvider, String sUid, String sPwd)
	{
		//LDAP 접속 환경설정
		Hashtable<String, String> environment = new Hashtable<String, String>();
		environment.put(LdapContext.CONTROL_FACTORIES, "ldap.factories.control");
		environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
		environment.put(Context.PROVIDER_URL, sServiceProvider);
		environment.put(Context.SECURITY_AUTHENTICATION, "none");
		environment.put(Context.SECURITY_PRINCIPAL, sUid);
		environment.put(Context.SECURITY_CREDENTIALS, sPwd);
		
		try {
			DirContext ctx = new InitialDirContext(environment); //LDAP 서버와의 연결 생성
		} catch (NamingException e) {
			System.out.println("NamingException Message");
		}
	}
*/

	public void Good(String sSingleld, int iFlag, String sServiceProvider, String sUid, String sPwd)
	{
		//LDAP 접속 환경설정
		Hashtable<String, String> environment = new Hashtable<String, String>();
		environment.put(LdapContext.CONTROL_FACTORIES, "ldap.factories.control");
		environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
		environment.put(Context.PROVIDER_URL, sServiceProvider);
		/* FIX */
		environment.put(Context.SECURITY_AUTHENTICATION, "simple");
		environment.put(Context.SECURITY_PRINCIPAL, sUid);
		environment.put(Context.SECURITY_CREDENTIALS, sPwd);
		
		try {
			DirContext ctx = new InitialDirContext(environment); //LDAP 서버와의 연결 생성
		} catch (NamingException e) {
			System.out.println("NamingException Message");
		}
	}
}