package CWE89_SQL_INJECTION;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * ������
 * �ֿ���� :: CWE89_InsertSQL - SQL����
 */

// SQL ����
public class CWE89_SQL_INJECTION_EXECUTE
{
	public void bad_Execute(Connection conn, HttpServletRequest request)
	{	
		Statement stmt = null;
		
		try
		{
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String query = "UPDATE MEMBER SET NAME = '" + name + "' WHERE ID = '" + id + "'";

			conn = DriverManager.getConnection(null, null, null);
			stmt = conn.createStatement();
			/* FLAW */
			stmt.execute( query );
		}
		catch(SQLException e)
		{
			System.out.println("Exception");
		}
		finally
		{	
			if(stmt !=null) try { stmt.close();	}catch ( SQLException e ){ stmt=null; }
			if(conn !=null) try { conn.close();	}catch ( SQLException e ){ conn=null; }
		}
	}
	

/*
// import com.hsecure.xwm.aca.utils.*;
	
	public void ACA(Connection conn, HttpServletRequest request)
	{	
		Statement stmt = null;
		
		try
		{
// ${hsAcaTarget.lineSplitMid} : request.getParameter("id")
// ${hsAcaTarget.lineSplitMid} : request.getParameter("name")
// HsSqlEncoder.encode( ${hsAcaTarget.lineSplitMid} )

			String id = HsSqlEncoder.encode( request.getParameter("id") );
			String name = HsSqlEncoder.encode( request.getParameter("name") );
			String query = "UPDATE MEMBER SET NAME = '" + name + "' WHERE ID = '" + id + "'";

			conn = DriverManager.getConnection(null, null, null);
			stmt = conn.createStatement();
			stmt.execute( query );
		}
		catch(SQLException e)
		{
			System.out.println("Exception");
		}
		finally
		{	
			if(stmt !=null) try { stmt.close();	}catch ( SQLException e ){ stmt=null; }
			if(conn !=null) try { conn.close();	}catch ( SQLException e ){ conn=null; }
		}
	}
*/	
	
	public void good_Execute(Connection conn, HttpServletRequest request)
	{	
		PreparedStatement psmt = null;
		
		try
		{
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			
			conn = DriverManager.getConnection(null, null, null);
			
			/* FIX */
			String query = "UPDATE MEMBER SET NAME = ? WHERE ID = ?";
			psmt = conn.prepareStatement(query);
			psmt.setString(1, name);
			psmt.setString(2, id);
			psmt.execute();
		}
		catch(SQLException e)
		{
			System.out.println("Exception");
		}
		finally
		{	
			if(psmt !=null) try { psmt.close();	}catch ( SQLException e ){ psmt=null; }
			if(conn !=null) try { conn.close();	}catch ( SQLException e ){ conn=null; }
		}
	}
	
	public void bad_ExecuteQuery(Connection conn)
	{	
		Properties props = new Properties();
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			String tableName = props.getProperty("jdbc.tableName");
			String name = props.getProperty("jdbc.name");
			String query = "SELECT * FROM" + tableName + "WHERE Name = " + name;

			conn = DriverManager.getConnection(null, null, null);
			stmt = conn.createStatement();
			/* FLAW */
			rs = stmt.executeQuery( query );
		}
		catch(SQLException e)
		{
			System.out.println("Exception");
		}
		finally
		{
			if(rs !=null) try { rs.close();	}catch ( SQLException e ){ rs=null; }
			if(stmt !=null) try { stmt.close();	}catch ( SQLException e ){ stmt=null; }
			if(conn !=null) try { conn.close();	}catch ( SQLException e ){ conn=null; }
		}
	}
	

/*
	
// import com.hsecure.xwm.aca.utils.*;
	
	public void ACA(Connection conn)
	{	
		Properties props = new Properties();
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			
// ${hsAcaTarget.lineSplitMid} : props.getProperty("jdbc.tableName")
// ${hsAcaTarget.lineSplitMid} : props.getProperty("jdbc.name")
// HsSqlEncoder.encode( ${hsAcaTarget.lineSplitMid} )
			
			String tableName = HsSqlEncoder.encode( props.getProperty("jdbc.tableName") );
			String name = HsSqlEncoder.encode( props.getProperty("jdbc.name") );
			String query = "SELECT * FROM" + tableName + "WHERE Name = " + name;

			conn = DriverManager.getConnection(null, null, null);
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery( query );
		}
		catch(SQLException e)
		{
			System.out.println("Exception");
		}
		finally
		{
			if(rs !=null) try { rs.close();	}catch ( SQLException e ){ rs=null; }
			if(stmt !=null) try { stmt.close();	}catch ( SQLException e ){ stmt=null; }
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
			
			conn = DriverManager.getConnection(null, null, null);
			
			/* FIX */
			String query = "SELECT * FROM ? WHERE Name = ?";
			psmt = conn.prepareStatement(query);
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
	
	public void bad_ExecuteUpdate(Connection conn, HttpServletRequest request)
	{	
		Statement stmt = null;
		try
		{
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String query = "UPDATE MEMBER SET NAME = '" + name + "' WHERE ID = '" + id + "'";

			conn = DriverManager.getConnection(null, null, null);
			stmt = conn.createStatement();
			/* FLAW */
			stmt.executeUpdate( query );
		}
		catch(SQLException e)
		{
			System.out.println("Exception");
		}
		finally
		{
			if(stmt !=null) try { stmt.close();	}catch ( SQLException e ){ stmt=null; }
			if(conn !=null) try { conn.close();	}catch ( SQLException e ){ conn=null; }
		}
	}
	
	
/*
	
// import com.hsecure.xwm.aca.utils.*;
	
	
	public void ACA(Connection conn, HttpServletRequest request)
	{	
		Statement stmt = null;
		try
		{
		
// ${hsAcaTarget.lineSplitMid} : request.getParameter("id")
// ${hsAcaTarget.lineSplitMid} : request.getParameter("name")
// HsSqlEncoder.encode( ${hsAcaTarget.lineSplitMid} )
			
			String id = HsSqlEncoder.encode( request.getParameter("id") );
			String name = HsSqlEncoder.encode( request.getParameter("name") );
			String query = "UPDATE MEMBER SET NAME = '" + name + "' WHERE ID = '" + id + "'";

			conn = DriverManager.getConnection(null, null, null);
			stmt = conn.createStatement();
			stmt.executeUpdate( query );
		}
		catch(SQLException e)
		{
			System.out.println("Exception");
		}
		finally
		{
			if(stmt !=null) try { stmt.close();	}catch ( SQLException e ){ stmt=null; }
			if(conn !=null) try { conn.close();	}catch ( SQLException e ){ conn=null; }
		}
	}
*/	
	
	public void good_ExecuteUpdate(Connection conn, HttpServletRequest request)
	{	
		PreparedStatement psmt = null;
		try
		{
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			
			conn = DriverManager.getConnection(null, null, null);
			
			/* FIX */
			String query = "UPDATE MEMBER SET NAME = ? WHERE ID = ?";
			psmt = conn.prepareStatement(query);
			psmt.setString(1, name);
			psmt.setString(2, id);
			psmt.executeUpdate();
		}
		catch(SQLException e)
		{
			System.out.println("Exception");
		}
		finally
		{
			if(psmt !=null) try { psmt.close();	}catch ( SQLException e ){ psmt=null; }
			if(conn !=null) try { conn.close();	}catch ( SQLException e ){ conn=null; }
		}
	}
}
