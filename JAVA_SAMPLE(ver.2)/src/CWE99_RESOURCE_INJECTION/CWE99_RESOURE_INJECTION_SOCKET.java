package CWE99_RESOURCE_INJECTION;

import java.net.Proxy;
import java.net.ServerSocket;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Properties;

/*
 * 검출목록
 * 주요검출 :: CWE99_ResourceInsertion - 자원삽입
 */

// 경로 조작 및 자원삽입
public class CWE99_RESOURE_INJECTION_SOCKET
{
	public void bad_Socket01()
	{
		Properties props = new Properties();
		Socket socket = null;
		try {
			String service = props.getProperty("Service No");
			String host = "host.com";
			int port = Integer.parseInt(service);
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, port));
			socket = new Socket(proxy);			
		} catch (SecurityException e) {
			System.out.println("SecurityException Message");
		}finally{
			if(socket != null) try{ socket.close(); }catch(IOException e){ System.out.println("Exception Message"); }
		}		
	}
	
/* ACA hold
	public void ACA_Socket01()
	{
		Properties props = new Properties();
		Socket socket = null;
		try {
			String service = props.getProperty("Service No");
			String host = "host.com";
			int port = Integer.parseInt(service);
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, port));
			socket = new Socket(proxy);			
		} catch (SecurityException e) {
			System.out.println("SecurityException Message");
		}finally{
			if(socket != null) try{ socket.close(); }catch(IOException e){ System.out.println("Exception Message"); }
		}		
	}
 */
 
	public void good_Socket01()
	{
		Properties props = new Properties();
		Socket socket = null;
		try {
			String service = props.getProperty("Service No");
			String host = "host.com";
			int port = Integer.parseInt(service);
			/* FIX */
			switch(port)
			{
				case 1:
					port = 3001;
					break;
				case 2:
					port = 3002;
					break;
				case 3:
					port = 3003;
					break;
				default:
					port = 3000;
			}
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, port));
			socket = new Socket(proxy);
		} catch (SecurityException e) {
			System.out.println("SecurityException Message");
		}finally{
			if(socket != null) try{ socket.close(); }catch(IOException e){ System.out.println("Exception Message"); }
		}
	}
	
	public void bad_Socket02()
	{
		Properties props = new Properties();
		Socket socket = null;
		try
		{
			int def = 1000;
			String service = props.getProperty("Service No");
			String host = "host.com";
			int port = Integer.parseInt(service);
			/* FLAW */
			if(port != 0)
			{
				socket = new Socket();
			}
			else if(service.indexOf("&" + port) == 0)
			{
				socket = new Socket(host, port + 3000);
			}
			else
			{
				socket = new Socket(host, def + 3000);
			}
		}catch(IOException e){
			System.out.println("Exception Message");
		}finally{
			if(socket != null) try{ socket.close(); }catch(IOException e){ System.out.println("Exception Message"); }
		}
	}
	
/* ACA hold
	public void ACA_Socket02()
	{
		Properties props = new Properties();
		Socket socket = null;
		try
		{
			int def = 1000;
			String service = props.getProperty("Service No");
			String host = "host.com";
			int port = Integer.parseInt(service);
			
			if(port != 0)
			{
				socket = new Socket();
			}
			else if(service.indexOf("&" + port) == 0)
			{
				socket = new Socket(host, port + 3000);
			}
			else
			{
				socket = new Socket(host, def + 3000);
			}
		}catch(IOException e){
			System.out.println("Exception Message");
		}finally{
			if(socket != null) try{ socket.close(); }catch(IOException e){ System.out.println("Exception Message"); }
		}
	}
*/
	
	public void good_Socket02()
	{
		Properties props = new Properties();
		Socket socket = null;
		try
		{	
			String service = props.getProperty("Service No");
			String host = "host.com";
			int port = Integer.parseInt(service);
			/* FIX */
			switch(port)
			{
				case 1:
					port = 3001;
					break;
				case 2:
					port = 3002;
					break;
				case 3:
					port = 3003;
					break;
				default:
					port = 3000;
			}
			socket = new Socket(host, port + 3000);
		}catch(IOException e){
			System.out.println("Exception Message");
		}finally{
			if(socket != null) try{ socket.close(); }catch(IOException e){ System.out.println("Exception Message"); }
		}
	}
	
	public void bad_ServerSocket()
	{
		Properties props = new Properties();
		ServerSocket serverSocket = null;
		try
		{
			int def = 1000;
			String service = props.getProperty("Service No");
			int port = Integer.parseInt(service);
			/* FLAW */
			if(port != 0)
			{
				serverSocket = new ServerSocket(port + 3000);
			}
			else if(service.indexOf("&" + port) == 0)
			{
				serverSocket = new ServerSocket(port + 3000);
			}
			else
			{
				serverSocket = new ServerSocket(def + 3000);
			}
		}
		catch(IOException e)
		{
			System.out.println("IOException Message");
		}finally{
			if(serverSocket != null) try{ serverSocket.close(); }catch(IOException e){ System.out.println("IOException Message"); }
		}
	}

/* ACA hold 
	public void ACA_ServerSocket()
	{
		Properties props = new Properties();
		ServerSocket serverSocket = null;
		try
		{
			int def = 1000;
			String service = props.getProperty("Service No");
			int port = Integer.parseInt(service);
			
			if(port != 0)
			{
				serverSocket = new ServerSocket(port + 3000);
			}
			else if(service.indexOf("&" + port) == 0)
			{
				serverSocket = new ServerSocket(port + 3000);
			}
			else
			{
				serverSocket = new ServerSocket(def + 3000);
			}
		}
		catch(IOException e)
		{
			System.out.println("IOException Message");
		}finally{
			if(serverSocket != null) try{ serverSocket.close(); }catch(IOException e){ System.out.println("IOException Message"); }
		}
	}
*/

	public void good_ServerSocket()
	{
		Properties props = new Properties();
		ServerSocket serverSocket = null;
		try
		{
			int def = 1000;
			String service = props.getProperty("Service No");
			int port = Integer.parseInt(service);
			/* FIX */
			switch(port)
			{
				case 1:
					port = 3001;
					break;
				case 2:
					port = 3002;
					break;
				case 3:
					port = 3003;
					break;
				default:
					port = 3000;
			}
			serverSocket = new ServerSocket(port);
		}
		catch(IOException e)
		{
			System.out.println("IOException Message");
		}finally{
			if(serverSocket != null) try{ serverSocket.close(); }catch(IOException e){ System.out.println("IOException Message"); }
		}
	}
}