package CWE615_INFORMATION_EXPOSURE_THROUGH_COMMENTS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

/*
 * ������
 * �ֿ���� :: CWE615_SystemCriticalInfoInCommentStatement - �ּ��� �ȿ� ���Ե� �н����� �� �ý��� �ֿ�����
 */

public class CWE615_INFORMATION_EXPOSURE_IN_COMMENT
{
	public void Bad()
	{
		Connection conn = null;
		try
		{
			ArrayList<String> connInfo = getConnectionInfo();
			
			/* FLAW */
			//pw : story
			conn = DriverManager.getConnection(connInfo.get(1), connInfo.get(2), connInfo.get(3));
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

/* ACA hold
	public void ACA()
	{
		Connection conn = null;
		try
		{
			ArrayList<String> connInfo = getConnectionInfo();
			
			
			//pw : story
			conn = DriverManager.getConnection(connInfo.get(1), connInfo.get(2), connInfo.get(3));
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
	
	public void Good(String args)
	{
		Connection conn = null;
		try
		{
			ArrayList<String> connInfo = getConnectionInfo();
			
			/* Fix */
			// �ý��� �ֿ������� ���� �ּ� ����
			conn = DriverManager.getConnection(connInfo.get(1), connInfo.get(2), connInfo.get(3));
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
	
	public ArrayList<String> getConnectionInfo()
	{
		Properties props = new Properties();
				
		String DBServer = props.getProperty("DBServer");
		String DBUser = props.getProperty("DBUser");
		String DBPwd = props.getProperty("DBPwd");
		
		ArrayList<String> connInfoArrList = new ArrayList<String>();
		connInfoArrList.add(1, DBServer);
		connInfoArrList.add(2, DBUser);
		connInfoArrList.add(3, DBPwd);
		
		return connInfoArrList;
	}
}