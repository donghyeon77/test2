package CWE90_LDAP_INJECTION;

import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.SearchControls;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

/*
 * 검출목록
 * 주요검출 :: CWE90_LDAPInsertion - LDAP 삽입
 */

public class CWE90_LDAP_INJECTION_INITIAL_LDAP_CONTEXT_SEARCH
{
	public void Bad(String args[])
	{
		Properties props = new Properties();
		Hashtable env = new Hashtable();
		
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://localhost:1389");
		
		try
		{				
			LdapContext ctx = new InitialLdapContext(env, null);
			String name = props.getProperty("name");
			String filter = "(name =" + name + ")";

			/* FLAW */
			ctx.search("ou=NewHires", filter, new SearchControls());
		}
		catch(NamingException e)
		{
			System.out.println("Exception");
		}
	}
/*
// import com.hsecure.xwm.aca.utils.*;

	public void ACA(String args[])
	{
		Properties props = new Properties();
		Hashtable env = new Hashtable();
		
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://localhost:1389");
		
		try
		{
// ${hsAcaTarget.lineSplitMid} : props.getProperty("name")
// HsSqlEncoder.encode( ${hsAcaTarget.lineSplitMid} )
			
			LdapContext ctx = new InitialLdapContext(env, null);
			String name = HsSqlEncoder.encode( props.getProperty("name") );
			String filter = "(name =" + name + ")";

			ctx.search("ou=NewHires", filter, new SearchControls());
		}
		catch(NamingException e)
		{
			System.out.println("Exception");
		}
	}
*/
	
	public void Good(String args[])
	{
		Properties props = new Properties();
		Hashtable env = new Hashtable();
		
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://localhost:1389");
		
		try
		{
			LdapContext ctx = new InitialLdapContext(env, null);
			String name = props.getProperty("name");
			String filter = "(name =" + name + ")";
			/* FIX */
			String f = filter.replaceAll("\\*", "");

			ctx.search("ou=NewHires", f, new SearchControls());
		}
		catch(NamingException e)
		{
			System.out.println("Exception");
		}
	}
}
