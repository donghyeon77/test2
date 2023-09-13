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
 * 검출목록
 * 주요검출 :: CWE89_InsertSQL - SQL삽입
 */

// SQL 삽입
public class CWE89_SQL_INJECTION_EXECUTE_BATCH
{
	public void bad(Connection conn, HttpServletRequest request)
	{
		Statement stmt = null;
		try
		{
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String query = "UPDATE MEMBER SET NAME = '" + name + "' WHERE ID = '" + id + "'";

			conn = DriverManager.getConnection(null, null, null);
			stmt = conn.createStatement();
			stmt.addBatch(query);
		    
		    /* FLAW */
			stmt.executeBatch();

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
		PreparedStatement psmt = null;
		try
		{
			
// ${hsAcaTarget.lineSplitMid} : request.getParameter("id")
// ${hsAcaTarget.lineSplitMid} : request.getParameter("name")
// HsSqlEncoder.encode( ${hsAcaTarget.lineSplitMid} )
			
			String id = HsSqlEncoder.encode( request.getParameter("id") );
			String name = HsSqlEncoder.encode( request.getParameter("name") );
			String query = "UPDATE MEMBER SET NAME = '" + name + "' WHERE ID = '" + id + "'";

			conn = DriverManager.getConnection(null, null, null);
			psmt = conn.prepareStatement(query);
			
			psmt.addBatch();
		    psmt.clearParameters();
			psmt.executeBatch();

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
*/	
	
	public void good(Connection conn, HttpServletRequest request)
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
			psmt.addBatch();
		    psmt.clearParameters();
			psmt.executeBatch();
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
