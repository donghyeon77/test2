package CWE307_IMPROPER_RESTRICTION_OF_EXCESSIVE_AUTHENTICATION_ATTEMPTS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class CWE307_EXCESSIVE_AUTH_ATTEMPTS_VERIFY_USER {
	public static final int SUCCESS = 1; 
	public static final int FAIL = -1; 
	public static final String SERVER_IP = "127.0.0.1";
	public static final int SERVER_PORT = 9001;
	public static final int MAX_ATTEMPTS = 10;
	
	public void Bad() {
		String username = "name";
		String password = "1234";
		Socket socket = null;
		int OK = FAIL;

		try {
			socket = new Socket(SERVER_IP, SERVER_PORT);
			
	       //... 중간생략
			
			/* FLAW */
			while(OK == FAIL) {
				//사용자 이름과 패스워드를 입력받음
				OK = verifyUser(username, password);
			}
		} catch(IOException e) {
			System.out.println("IOException Message");
		}finally{
			if(socket != null) try{ socket.close(); }catch(IOException e){ System.out.println("IOException Message"); }
		}
	}
	
/* ACA hold
	public void ACA() {
		String username = "name";
		String password = "1234";
		Socket socket = null;
		int OK = FAIL;

		try {
			socket = new Socket(SERVER_IP, SERVER_PORT);
			
	       //... 중간생략
			
			
			while(OK == FAIL) {
				//사용자 이름과 패스워드를 입력받음
				OK = verifyUser(username, password);
			}
		} catch(IOException e) {
			System.out.println("IOException Message");
		}finally{
			if(socket != null) try{ socket.close(); }catch(IOException e){ System.out.println("IOException Message"); }
		}
	}
*/

	public void Good() {
		String username = "name";
		String password = "1234";
		Socket socket = null;
		int OK = FAIL;
		int count = 0;
		try {
			socket = new Socket(SERVER_IP, SERVER_PORT);
			
			//... 중간생략
			
			/* FIX */
			while((OK == FAIL) && (count < MAX_ATTEMPTS)) {
				//사용자 이름과 패스워드를 입력받음
				OK = verifyUser(username, password);
				count++;
			}
		} catch(IOException e) {
			System.out.println("IOException Message");
		}finally{
			if(socket != null) try{ socket.close(); }catch(IOException e){ System.out.println("IOException Message"); }
		}
	}
	
	public int verifyUser(String user, String pwd)
	{
		if( user.length() > 0 && pwd.length() > 0 )
			return SUCCESS;
		
		return FAIL;
	}
}
