package CWE652_XQUERY_INJECTION;

import java.util.Properties;

import javax.xml.namespace.QName;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

import net.sf.saxon.xqj.SaxonXQDataSource;

/*
 * 검출목록
 * 주요검출 :: CWE652_XQueryInsertion - XQuery 삽입
 */

public class CWE652_XQUERY_INJECTION_EXCUTE
{
	public void bad_Execute(XQDataSource dataSource)
	{
		XQConnection conn = null;
		Properties props = new Properties();
		
		try
		{
			conn = dataSource.getConnection();
			String name = props.getProperty("name");
			String es = "doc('users.xml')/userlist/user[uname='" + name + "']";
			XQExpression expr = conn.createExpression();
			
			/* FLAW */
			XQResultSequence result = expr.executeQuery(es);
		}
		catch ( XQException e )
		{
			System.out.println("XQException Occured!!");
		}
	}
	
/* 
// import com.hsecure.xwm.aca.utils.*;

	public void ACA_Execute(XQDataSource dataSource)
	{
		XQConnection conn = null;
		Properties props = new Properties();
		
		try
		{
			conn = dataSource.getConnection();
// ACA position : cause replace 
// ${hsAcaTarget.lineSplitMid} : props.getProperty("name")
// HsSqlEncoder.encode( ${hsAcaTarget.lineSplitMid} )

			String name = HsSqlEncoder.encode(props.getProperty("name"));
			String es = "doc('users.xml')/userlist/user[uname='" + name + "']";
			XQExpression expr = conn.createExpression();
			
			
			XQResultSequence result = expr.executeQuery(es);
		}
		catch ( XQException e )
		{
			System.out.println("XQException Occured!!");
		}
	}
 */
	
	public void good_Execute(XQDataSource dataSource)
	{	
		XQConnection conn = null;
		Properties props = new Properties();
		
		try
		{
			conn = dataSource.getConnection();
			String name = props.getProperty("name");
			
			/* FIX */
			String es = "doc('users.xml')/userlist/user[uname='$xpathname']";
			XQPreparedExpression expr = conn.prepareExpression(es);
			expr.bindString( new QName("xpathname"), name, null );
			XQResultSequence result = expr.executeQuery();
		}
		catch( XQException e )
		{
			System.out.println("Exception");
		}
	}
	
	public void bad_Saxon()
	{
		Properties props = new Properties();
		net.sf.saxon.javax.xml.xquery.XQResultSequence data = null;
		
		try
		{
			String name = props.getProperty("name");
			
			net.sf.saxon.javax.xml.xquery.XQDataSource ds = new SaxonXQDataSource();
			net.sf.saxon.javax.xml.xquery.XQConnection xqconn = ds.getConnection();
			String es = "doc('users.xml')/userlist/user\nwhere $user/uname='"+name+"'\nreturn $user/pwd";
			net.sf.saxon.javax.xml.xquery.XQExpression exp = xqconn.createExpression();

			/* FLAW */
			data = exp.executeQuery(es);
			while(data.next()){
				System.out.println(data.getAtomicValue());
			}
		}
		catch ( net.sf.saxon.javax.xml.xquery.XQException e )
		{
			System.out.println("XQException Occured!!");
		}
	}
	
	public void good_Saxon()
	{
		Properties props = new Properties();
		net.sf.saxon.javax.xml.xquery.XQResultSequence data = null;
		
		try
		{
			String name = props.getProperty("name");
			
			net.sf.saxon.javax.xml.xquery.XQDataSource ds = new SaxonXQDataSource();
			net.sf.saxon.javax.xml.xquery.XQConnection xqconn = ds.getConnection();
			
			/* FIX */
			String es = "doc('users.xml')/userlist/user\nwhere $user/uname='$xname'\nreturn $user/pwd";
			net.sf.saxon.javax.xml.xquery.XQPreparedExpression exp = xqconn.prepareExpression(es);
			exp.bindObject(new QName("xname"), name, null);			
			data = exp.executeQuery();
			while(data.next()){
				System.out.println(data.getAtomicValue());
			}
		}
		catch ( net.sf.saxon.javax.xml.xquery.XQException e )
		{
			System.out.println("XQException Occured!!");
		}		
	}
}
