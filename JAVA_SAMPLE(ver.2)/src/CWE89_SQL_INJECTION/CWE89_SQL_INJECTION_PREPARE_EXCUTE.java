package CWE89_SQL_INJECTION;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

/*
 * ������
 * �ֿ���� :: CWE89_InsertSQL - SQL����
 */

// SQL ����
public class CWE89_SQL_INJECTION_PREPARE_EXCUTE
{
	public void bad_ExecuteQuery(Connection conn)
	{	
		Properties props = new Properties();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try
		{
			String tableName = props.getProperty("jdbc.tableName");
			String name = props.getProperty("jdbc.name");
			String query = "SELECT * FROM" + tableName + "WHERE Name = " + name;

			conn = DriverManager.getConnection(null, null, null);
			psmt = conn.prepareStatement(query);
			/* FLAW */
			rs = psmt.executeQuery( );
		}
		catch(SQLException e)
		{
			System.out.println("Exception");
		}
		finally
		{
			if(rs !=null) try { rs.close();	}catch ( SQLException e ){ rs=null; }
			if(psmt !=null) try { psmt.close();	}catch ( SQLException e ){ psmt=null; }
			if(conn !=null) try { conn.close();	}catch ( SQLException e ){ conn=null; }
		}
	}

/*
// import com.hsecure.xwm.aca.utils.*;

	public void ACA(Connection conn)
	{	
		Properties props = new Properties();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
// ${hsAcaTarget.lineSplitMid} : props.getProperty("jdbc.tableName")
// ${hsAcaTarget.lineSplitMid} : props.getProperty("jdbc.name")
// HsSqlEncoder.encode( ${hsAcaTarget.lineSplitMid} )
		
		try
		{
			String tableName = HsSqlEncoder.encode( props.getProperty("jdbc.tableName") );
			String name = HsSqlEncoder.encode( props.getProperty("jdbc.name") );
			String query = "SELECT * FROM" + tableName + "WHERE Name = " + name;

			conn = DriverManager.getConnection(null, null, null);
			psmt = conn.prepareStatement(query);
			
			rs = psmt.executeQuery( query );
		}
		catch(SQLException e)
		{
			System.out.println("Exception");
		}
		finally
		{
			if(rs !=null) try { rs.close();	}catch ( SQLException e ){ rs=null; }
			if(psmt !=null) try { psmt.close();	}catch ( SQLException e ){ psmt=null; }
			if(conn !=null) try { conn.close();	}catch ( SQLException e ){ conn=null; }
		}
	}
*/

	public void good_ExecuteQuery(Connection conn)
	{	
		Properties props = new Properties();
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try
		{
			String tableName = props.getProperty("jdbc.tableName");
			String name = props.getProperty("jdbc.name");

			String query = "SELECT * FROM ? WHERE Name = ?";
			
			conn = DriverManager.getConnection(null, null, null);
			psmt = conn.prepareStatement(query);
			/* FIX */
			psmt.setString(1, tableName);
			psmt.setString(2, name);
			rs = psmt.executeQuery();
		}
		catch(SQLException e)
		{
			System.out.println("Exception");
		}
		finally
		{
			if(rs !=null) try { rs.close();	}catch ( SQLException e ){ rs=null; }
			if(psmt !=null) try { psmt.close();	}catch ( SQLException e ){ psmt=null; }
			if(conn !=null) try { conn.close();	}catch ( SQLException e ){ conn=null; }
		}
	}
}
