package CWE390_DETECTION_OF_ERROR_CONDITION_WITHOUT_ACTION;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/*
 * 검출목록
 * 주요검출 :: CWE390_AbsenceOfCopeWithErrMsg - 오류상황 대응 부재
 */

// 오류상황 대응 부재
public class CWE390_EMPTY_CATCH_STATEMENT
{
	private Connection conn = null;
	
	public Connection Bad(String url, String id, String password)
	{
		try
		{
			String CONNECT_STRING = url + " : " + id + " : " + password;
			InitialContext ctx = new InitialContext();
			DataSource datasource = (DataSource)ctx.lookup(CONNECT_STRING);
			conn = datasource.getConnection();
		}
		catch(SQLException e)
		{
			/* FLAW */
			//오류상황 대응 부재
		}
		catch(NamingException e)
		{
			/* FLAW */
			//오류상황 대응 부재
		}
		return conn;
	}

/* ACA hold
	public Connection ACA(String url, String id, String password)
	{
		try
		{
			String CONNECT_STRING = url + " : " + id + " : " + password;
			InitialContext ctx = new InitialContext();
			DataSource datasource = (DataSource)ctx.lookup(CONNECT_STRING);
			conn = datasource.getConnection();
		}
		catch(SQLException e){
			
		}
		catch(NamingException e)
		{
			
		}
		return conn;
	}
*/
	
	public Connection Good(String url, String id, String password)
	{
		try
		{
			String CONNECT_STRING = url + " : " + id + " : " + password;
			InitialContext ctx = new InitialContext();
			DataSource datasource = (DataSource)ctx.lookup(CONNECT_STRING);
			conn = datasource.getConnection();
		}
		catch(SQLException e)
		{
			/* FIX */
			System.out.println("SQLException Error!!");
		}
		catch(NamingException e)
		{
			/* FIX */
			System.out.println("NamingException Error!!");
		}
		return conn;
	}
}