package CWE404_IMPROPER_RESOURCE_SHUTDOWN_OR_RELEASE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * 검출목록
 * 주요검출 :: CWE404_ImproperResourceRelease - 부적절한 자원 해제
 */

// 부적절한 자원 해제
public class CWE404_RESOURCE_RELEASE_NOT_FINALLY
{
	public void Bad(String args[])
	{
		Connection conn = null;
		final String url = "jdbc:mysql://127.0.0.1/example?user=root&password=1234";
		try
		{
			/* FLAW : declare close method in try */
			conn = DriverManager.getConnection(url);
			conn.close();
		}
		catch(final SQLException e)
		{
			System.err.println("SQLException occured");
		}
	}

/* ACA hold
	public void ACA(String args[])
	{
		Connection conn = null;
		final String url = "jdbc:mysql://127.0.0.1/example?user=root&password=1234";
		try
		{
			
			conn = DriverManager.getConnection(url);
			conn.close();
		}
		catch(final SQLException e)
		{
			System.err.println("SQLException occured");
		}
	}
*/
	
	public void Good(String args[])
	{
		Connection conn = null;
		final String url = "jdbc:mysql://127.0.0.1/example?user=root&password=1234";
		try
		{
			conn = DriverManager.getConnection(url);
		}
		catch(final SQLException e)
		{
			System.err.println("SQLException? occured");
		}
		finally
		{
			try
			{
				/* FIX : declare close method in finally */
				conn.close();
			}
			catch (SQLException e)
			{
				System.err.println("SQLException occured");
			}
		}
	}
}