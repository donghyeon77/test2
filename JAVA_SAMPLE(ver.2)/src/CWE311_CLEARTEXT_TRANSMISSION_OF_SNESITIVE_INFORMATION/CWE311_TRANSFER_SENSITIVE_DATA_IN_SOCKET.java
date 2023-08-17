package CWE311_CLEARTEXT_TRANSMISSION_OF_SNESITIVE_INFORMATION;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

public class CWE311_TRANSFER_SENSITIVE_DATA_IN_SOCKET {
	public String getPassword(){
		return "";
		/*패스워드를 반환 하는 코드*/
	}

	public void bad() {
		Socket socket = null;
		PrintWriter out = null;
		OutputStream os = null;
		try {
			socket = new Socket("taranis", 4444);
			os = socket.getOutputStream();
			out = new PrintWriter(os, true);
			String password = getPassword();
			/* FLAW */
			out.write(password);
		}
		catch (FileNotFoundException e){
			System.out.println("FileNotFoundException Message");
		} catch (UnknownHostException e) {
			System.out.println("UnknownHostException Message");
		} catch (IOException e) {
			System.out.println("IOException Message");
		}finally{
			if(os != null) try{ os.close(); }catch(IOException e){ System.out.println("IOException Message"); }
			if(out != null) try{ out.close(); }catch(SecurityException e){ System.out.println("SecurityException Message"); }
			if(socket != null) try{ socket.close(); }catch(IOException e){ System.out.println("IOException Message"); }
		}
	}
	
/* 
// import com.hsecure.xwm.aca.utils.*;

	public void ACA() {
		Socket socket = null;
		PrintWriter out = null;
		OutputStream os = null;
		try {
			socket = new Socket("taranis", 4444);
			os = socket.getOutputStream();
			out = new PrintWriter(os, true);
			String password = getPassword();

//ACA position : result replace
//${hsAcaTarget.lineSplitMid} : password
//out.write(HsEncryptAES.encryptAES(${hsAcaTarget.lineSplitMid}),0,HsEncryptAES.encryptAES(${hsAcaTarget.lineSplitMid}).length);

			out.write(HsEncryptAES.encryptAES(password),0,HsEncryptAES.encryptAES(password).length);
		}
		catch (FileNotFoundException e){
			System.out.println("FileNotFoundException Message");
		} catch (UnknownHostException e) {
			System.out.println("UnknownHostException Message");
		} catch (IOException e) {
			System.out.println("IOException Message");
		}finally{
			if(os != null) try{ os.close(); }catch(IOException e){ System.out.println("IOException Message"); }
			if(out != null) try{ out.close(); }catch(SecurityException e){ System.out.println("SecurityException Message"); }
			if(socket != null) try{ socket.close(); }catch(IOException e){ System.out.println("IOException Message"); }
		}
	}
*/

	public void good() {
		Socket socket = null;
		PrintStream out = null;
		OutputStream os = null;
		try {
			socket = new Socket("taranis", 4444);
			os = socket.getOutputStream();
			out = new PrintStream(os, true);
			/* FIX */
			Cipher c = Cipher.getInstance("AES");
			String password = getPassword();
			byte[] encryptedStr = c.update(password.getBytes());
			out.write(encryptedStr, 0, encryptedStr.length);
		}
		catch (FileNotFoundException e){
			System.out.println("FileNotFoundException Message");
		} catch (UnknownHostException e) {
			System.out.println("UnknownHostException Message");
		} catch (IOException e) {
			System.out.println("IOException Message");
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException Message");
		} catch (NoSuchPaddingException e) {
			System.out.println("NoSuchPaddingException Message");
		}finally{
			if(os != null) try{ os.close(); }catch(IOException e){ System.out.println("IOException Message"); }
			if(out != null) try{ out.close(); }catch(SecurityException e){ System.out.println("SecurityException Message"); }
			if(socket != null) try{ socket.close(); }catch(IOException e){ System.out.println("IOException Message"); }
		}
	}
}
