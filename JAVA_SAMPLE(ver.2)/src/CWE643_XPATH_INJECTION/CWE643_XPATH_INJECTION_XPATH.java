package CWE643_XPATH_INJECTION;

import java.util.Properties;
import java.util.HashMap;
import java.util.Map;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

/*
 * 검출목록
 * 주요검출 :: CWE643_XPathInsertion - XPath 삽입
 */

public class CWE643_XPATH_INJECTION_XPATH
{
	public void bad_Compile(String args[])
	{
		Properties props = new Properties();
		try
		{
			String name = props.getProperty("name");
			String pwd = props.getProperty("password");

			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();

			/* FLAW */
			XPathExpression expr = xpath.compile("//users/user[login/text()-'" + name + "' and password/text() = '" + pwd + "']/homde_dir/text()");

		}
		catch ( XPathExpressionException e )
		{
			System.out.println( "XPathExpressionException Occured!!" );
		}
		catch(RuntimeException e)
		{
			System.out.println("RuntimeException");
		}
	}
	
/* 
// import com.hsecure.xwm.aca.utils.*;

	public void ACA_Compile(String args[])
	{
		Properties props = new Properties();
		try
		{
// ACA position : cause replace 
// ${hsAcaTarget.lineSplitMid} : props.getProperty("name")
// ${hsAcaTarget.lineSplitMid} : props.getProperty("password")
// HsSqlEncoder.encode( ${hsAcaTarget.lineSplitMid} )

			String name = HsSqlEncoder.encode(props.getProperty("name"));
			String pwd = HsSqlEncoder.encode(props.getProperty("password"));

			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();

			
			XPathExpression expr = xpath.compile("//users/user[login/text()-'" + name + "' and password/text() = '" + pwd + "']/homde_dir/text()");

		}
		catch ( XPathExpressionException e )
		{
			System.out.println( "XPathExpressionException Occured!!" );
		}
		catch(RuntimeException e)
		{
			System.out.println("RuntimeException");
		}
	}
 */
	
	public void good_Compile(String args[])
	{
		Properties props = new Properties();
		try
		{
			String name = props.getProperty("name");
			String pwd = props.getProperty("password");
			Document doc = new Builder().build("users.xml");

			/* FIX */
			XQuery xquery = new XqueryFactory().createXQuery(new File("dologin.xp"));
			Map vars = new HashMap();
			vars.put("logineId", name);
			vars.put("password", pwd);
			Nodes results = xquery.excute(doc, null, vars).toNodes();
		}
		catch(RuntimeException e)
		{
			System.out.println("RuntimeException");
		}
	}
	
	public void bad_Evaluate(String args[])
	{
		Properties props = new Properties();
		try
		{
			String name = props.getProperty("name");
			String pwd = props.getProperty("password");

			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();
			
			/* FLAW */
			String secret = (String)xpath.evaluate("//users/user[login/text()-'" + name + "' and password/text() = '" + pwd + "']/homde_dir/text()", XPathConstants.STRING);

		}
		catch ( XPathExpressionException e )
		{
			System.out.println( "XPathExpressionException Occured!!" );
		}
		catch(RuntimeException e)
		{
			System.out.println("RuntimeException");
		}
	}
	
	public void good_Evaluate(String args[])
	{
		Properties props = new Properties();
		try
		{
			String name = props.getProperty("name");
			String pwd = props.getProperty("password");
			Document doc = new Builder().build("users.xml");

			/* FIX */
			XQuery xquery = new XqueryFactory().createXQuery(new File("dologin.xp"));
			Map vars = new HashMap();
			vars.put("logineId", name);
			vars.put("password", pwd);
			Nodes results = xquery.excute(doc, null, vars).toNodes();
		}
		catch(RuntimeException e)
		{
			System.out.println("RuntimeException");
		}
	}
}