package CWE78_COMMAND_INJECTION;

import java.util.Properties;
import java.io.IOException;
import java.lang.Runtime;

/*
 * 검출목록
 * 주요검출 :: CWE78_InsertOSCommand - 운영체제 명령어 삽입
 */

public class CWE78_COMMAND_INJECTION_EXE_RUNTIME
{
	public void Bad()
	{
		String version = null;
		String cmd = null;
		Properties props = new Properties();
		try
		{
			version = props.getProperty("dir_type");
			cmd = new String("cmd.exe /K \" rmanDB.bat && cleanup.bat\" ");
			/* FLAW */
			Runtime.getRuntime().exec(cmd + " c:\\ prog_cmd \\ " + version);
		}
		catch ( IOException e )
		{
			System.out.println("IOException Occured!!");
		}
		catch( NullPointerException e )
		{
			System.out.println("Null_Pointer_Exception Occured!!");
		}
	}
	
	
/*

// import com.hsecure.xwm.aca.utils.*;

	public void ACA()
	{
		String version = null;
		String cmd = null;
		Properties props = new Properties();
		try
		{
			
// ${hsAcaTarget.lineSplitMid} : props.getProperty("dir_type")
// HsCommandEncoder.encode( ${hsAcaTarget.lineSplitMid} )
		
			version = HsCommandEncoder.encode( props.getProperty("dir_type") );
			cmd = new String("cmd.exe /K \" rmanDB.bat && cleanup.bat\" ");
			
			
			Runtime.getRuntime().exec(cmd + " c:\\ prog_cmd \\ " + ESAPI.encoder().encodeForOS( new WindowsCodec(), version ) );
		}
		catch ( IOException e )
		{
			System.out.println("IOException Occured!!");
		}
		catch( NullPointerException e )
		{
			System.out.println("Null_Pointer_Exception Occured!!");
		}
	}
	*/
	
	public void Good()
	{
		Properties props = new Properties();

		String version[] = {"1.0", "1.1"};
		int versionSelection = Integer.parseInt(props.getProperty("version"));
		String cmd = new String("cmd.exe /K \"rmanDB.bat \"");
		String vs = "";
		/* FIX */
		if( versionSelection == 0 )
		{
			vs = version[0];
		}
		else if( versionSelection == 1 )
		{
			vs = version[1];
		}
		else
		{
			vs = version[1];
		}		
		try
		{
			Runtime.getRuntime().exec(cmd + "c:\\prog_cmd\\" + vs);
		}
		catch ( IOException e )
		{
			System.out.println("IOException Occured!!");
		}
	}
}