package CWE521_WEAK_PASSWORD_REQUIREMENTS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 검출목록
 * 주요검출 :: CWE521_취약한 비밀번호 허용
 */

public class CWE521_WEAK_PASSWORD_REQUIREMENTS
{

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data = "";
    	PreparedStatement psmt = null;
    	Connection conn = null;
    	
        try {
        	ArrayList<String> connInfo = getConnectionInfo();
			String passwd = request.getParameter("passwd");
			
			data = passwd;
			
			conn = DriverManager.getConnection(connInfo.get(1), connInfo.get(2), connInfo.get(3));

			psmt = conn.prepareStatement("insert into users (ID, name) values ('5',?)");
			psmt.setString(1, data);

			/* FLAW */
			Boolean bResult = psmt.execute();

			if( bResult )
			{
				System.out.println("Name, taint, has been selected");
			} 
			else
			{
				System.out.println("Name, taint, has been inserted");
			}
	        
		} catch (SQLException e) 
		{
			System.err.println("SQLException occurred");
		}finally{
			if(psmt !=null) try { psmt.close();	}catch ( SQLException e ){ psmt=null; }
			if(conn !=null) try { conn.close();	}catch ( SQLException e ){ conn=null; }
		}

    }

/* ACA hold
    public void ACA(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        String data = "";
    	PreparedStatement psmt = null;
    	Connection conn = null;
    	
        try {
        	ArrayList<String> connInfo = getConnectionInfo();
			String passwd = request.getParameter("passwd");
			
			data = passwd;
			
			conn = DriverManager.getConnection(connInfo.get(1), connInfo.get(2), connInfo.get(3));

			psmt = conn.prepareStatement("insert into users (ID, name) values ('5',?)");
			psmt.setString(1, data);

			
			Boolean bResult = psmt.execute();

			if( bResult )
			{
				System.out.println("Name, taint, has been selected");
			} 
			else
			{
				System.out.println("Name, taint, has been inserted");
			}
	        
		} catch (SQLException e) 
		{
			System.err.println("SQLException occurred");
		}finally{
			if(psmt !=null) try { psmt.close();	}catch ( SQLException e ){ psmt=null; }
			if(conn !=null) try { conn.close();	}catch ( SQLException e ){ conn=null; }
		}
    }
*/
	
	public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
		String data = "";
        PreparedStatement psmt = null;
    	Connection conn = null;
        try {        	
			ArrayList<String> connInfo = getConnectionInfo();
			String passwd = request.getParameter("passwd");
			if(passwd == null || "".equals(passwd)) return;
			
			int count = 0;
	        Pattern special = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
	        Pattern lowerCase = Pattern.compile("[a-z]");
	        Pattern upperCase = Pattern.compile("[A-Z]");
	        Pattern number = Pattern.compile("[0-9]");
	        
	        Matcher s = special.matcher(passwd);
	        Matcher l = lowerCase.matcher(passwd);
	        Matcher u = upperCase.matcher(passwd);
	        Matcher n = number.matcher(passwd);
	        
	        if(s.find()) count++;
	        if(l.find()) count++;
	        if(u.find()) count++;
	        if(n.find()) count++;
	        
			data = passwd;
			
	        /* FIX */
	        if((count == 2 && data.length() >= 10) || (count > 2 && data.length() >= 8))
	        {
	        	conn = DriverManager.getConnection(connInfo.get(1), connInfo.get(2), connInfo.get(3));
	        	
				psmt = conn.prepareStatement("insert into users (ID,name) values ('5',?)");
				psmt.setString(1, data);

				Boolean bResult = psmt.execute();

				if( bResult )
				{
					System.out.println("Name, taint, has been selected");
				} 
				else
				{
					System.out.println("Name, taint, has been inserted");
				}
	        }
	        else
	        {
	        	response.getWriter().println("Invalid password. Please try again");
	        }
		} catch (SQLException e) {
			System.err.println("SQLException occurred");
		}finally{
			if(psmt !=null) try { psmt.close();	}catch ( SQLException e ){ psmt=null; }
			if(conn !=null) try { conn.close();	}catch ( SQLException e ){ conn=null; }
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