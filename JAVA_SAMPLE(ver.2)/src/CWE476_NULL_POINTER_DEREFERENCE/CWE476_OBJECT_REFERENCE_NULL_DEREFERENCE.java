package CWE476_NULL_POINTER_DEREFERENCE;

/*
 * 검출목록
 * 주요검출 :: CWE476_NullPointerDereference - Null 포인터 역참조
 * 부가검출 :: CWE352_RequestForgeXSS - 크로스사이트 스크립트 요청 위조
 *         :: CWE79_OutputCrossSiteScript - 크로스사이트 스크립트
 */

// Null 포인터 역참조
public class CWE476_OBJECT_REFERENCE_NULL_DEREFERENCE
{
	
	public void Bad(String args)
	{
		String cmd = System.getProperty("cmd");
		/* FLAW */
		cmd = cmd.trim();
		System.out.println("error message");
	}
	
/* 
	public void ACA(String args)
	{
		String cmd = System.getProperty("cmd");
// ACA postion : result top
// ${hsAcaTarget.lineSplitMid} : cmd
//if({hsAcaTarget.lineSplitMid} == null){ throw new RuntimeException();}

		if(cmd == null){throw new RuntimeException();}
		cmd = cmd.trim();
		System.out.println("error message");
	}
*/
	
	public void Good(String args[])
	{
		String cmd = System.getProperty("cmd");
		/* FIX */
		if( cmd != null )
		{
			cmd = cmd.trim();
			System.out.println(cmd);
		}
		else
		{
			System.out.println("null command");
		}
	}
}
