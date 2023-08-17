package CWE312_CLEARTEXT_STORAGE_OF_SENSITIVE_INFORMATION;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

/*
 * ������
 * �ֿ���� :: CWE312_�߿����� �� ����
 */

public class CWE312_SAVE_SENSITIVE_DATA_IN_PREPARED_STATEMENT {
	public void Bad(HttpServletRequest request, Connection conn)
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		PreparedStatement p = null;
		try {

			if (username == null || password == null || !isAuthenticatedUser(username, password)) {
				throw new Exception("���� ����");
			}
			p = conn.prepareStatement("INSERT INTO employees VALUES(?, ?)");
			p.setString(1, username);
			/* FLAW */
			p.setString(2, password);
			p.execute();

		} catch (SQLException e) {
			System.out.println("IOException Message");
		} catch (Exception e) {
			System.out.println("Exception Message");
		}
		finally
		{
			if(p !=null) try { p.close();	}catch ( SQLException e ){ p=null; }
			if(conn !=null) try { conn.close();	}catch ( SQLException e ){ conn=null; }				
		}
	}
	
/*
// import com.hsecure.xwm.aca.utils.*;

	public void ACA(HttpServletRequest request, Connection conn)
	{
		String username = request.getParameter("username");
// ACA position : cause replace
//${hsAcaTarget.lineSplitMid} : request.getParameter("password")
//HsMesgDigest.mesgDigest(${hsAcaTarget.lineSplitMid})

		String password = HsMesgDigest.mesgDigest(request.getParameter("password"));
		PreparedStatement p = null;
		try {

			if (username == null || password == null || !isAuthenticatedUser(username, password)) {
				throw new Exception("���� ����");
			}
			p = conn.prepareStatement("INSERT INTO employees VALUES(?, ?)");
			p.setString(1, username);

			p.setString(2, password);
			p.execute();

		} catch (SQLException e) {
			System.out.println("IOException Message");
		} catch (Exception e) {
			System.out.println("Exception Message");
		}
		finally
		{
			if(p !=null) try { p.close();	}catch ( SQLException e ){ p=null; }
			if(conn !=null) try { conn.close();	}catch ( SQLException e ){ conn=null; }				
		}
	}
*/
	
	public void Good(HttpServletRequest request, Connection conn)
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		PreparedStatement p = null;
		try {

			if (username == null || password == null || !isAuthenticatedUser(username, password)) {
				throw new Exception("���� ����");
			}
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.reset();
			md.update(getSecureRandomSalt());
			
			/* FIX */
			// �н������ �ؽ� �Լ��� �̿��Ͽ� DB�� ����
			password = md.digest(password.getBytes()).toString();
			p = conn.prepareStatement("INSERT INTO employees VALUES(?, ?)");
			p.setString(1, username);
			p.setString(2, password);
			p.execute();

		} catch (SQLException e) {
			System.out.println("IOException Message");
		} catch (Exception e) {
			System.out.println("Exception Message");
		}
		finally
		{
			if(p !=null) try { p.close();	}catch ( SQLException e ){ p=null; }
			if(conn !=null) try { conn.close();	}catch ( SQLException e ){ conn=null; }			
		}
	}
	
	boolean isAuthenticatedUser(String username, String password)
	{
		if( username.length() > 0 && password.length() > 0 )
			return true;
		
		return false;
	}
	
	public byte[] getSecureRandomSalt(){
		
		byte[] returnSalt = null;	
		try
		{
			SecureRandom prng = SecureRandom.getInstance("SHA256PRNG");
			String randomNum = new Integer(prng.nextInt()).toString();
			
			//SecureRandom Ŭ������ �̿��Ͽ� ������ ��Ʈ ����
			returnSalt = randomNum.getBytes();
				 
		}catch (NoSuchAlgorithmException e){
		   System.out.println("NoSuchAlgorithmException Message");
		}
		return returnSalt;		
	}
}
