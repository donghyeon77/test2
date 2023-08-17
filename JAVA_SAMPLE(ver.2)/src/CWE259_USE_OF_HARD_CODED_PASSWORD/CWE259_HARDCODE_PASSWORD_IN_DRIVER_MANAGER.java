package CWE259_USE_OF_HARD_CODED_PASSWORD;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/*
 * ������
 * �ֿ���� :: CWE259_HardCodePassword - �ϵ��ڵ�� ��й�ȣ
 * �ΰ����� :: CWE256_�߿����� �� ����
 */

// �ϵ��ڵ�� ��й�ȣ
public class CWE259_HARDCODE_PASSWORD_IN_DRIVER_MANAGER
{
	public void Bad(String args[])
	{
		String dbUrl = "jdbc:oracle:thin:@255.255.255.255:1521:orcl";
		String dbUser = "5dol";
		Connection conn = null;
		try
		{
			/* FLAW */
			conn = DriverManager.getConnection(dbUrl, dbUser, "story");
		}
		catch(SQLException e)
		{
			System.out.println("Exception");
		}
		finally
		{
			try
			{
				conn.close();
			}
			catch ( SQLException e )
			{
				System.out.println( "SQLException Occured!!" );
			}
		}
	}

/*  ACA hold
	public void ACA(String args[])
	{
		String dbUrl = "jdbc:oracle:thin:@255.255.255.255:1521:orcl";
		String dbUser = "5dol";
		Connection conn = null;
		try
		{
			
			conn = DriverManager.getConnection(dbUrl, dbUser, "story");
		}
		catch(SQLException e)
		{
			System.out.println("Exception");
		}
		finally
		{
			try
			{
				conn.close();
			}
			catch ( SQLException e )
			{
				System.out.println( "SQLException Occured!!" );
			}
		}
	}
 */

	public void Good(Properties properties)
	{
		String dbUrl = properties.getProperty("dbUrl");
		String dbUser = properties.getProperty("dbUser");
		String dbPassword = properties.getProperty("dbPassword");
		Connection conn = null;
		try
		{
			/* FIX */
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		}
		catch(SQLException e)
		{
			System.out.println( "SQLException Occured!!" );
		}
		finally
		{
			try
			{
				conn.close();
			}
			catch ( SQLException e )
			{
				System.out.println( "SQLException Occured!!" );
			}
		}
	}
}