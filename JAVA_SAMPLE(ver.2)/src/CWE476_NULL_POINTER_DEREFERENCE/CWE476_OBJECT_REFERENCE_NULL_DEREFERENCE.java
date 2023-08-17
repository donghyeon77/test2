package CWE476_NULL_POINTER_DEREFERENCE;

/*
 * ������
 * �ֿ���� :: CWE476_NullPointerDereference - Null ������ ������
 * �ΰ����� :: CWE352_RequestForgeXSS - ũ�ν�����Ʈ ��ũ��Ʈ ��û ����
 *         :: CWE79_OutputCrossSiteScript - ũ�ν�����Ʈ ��ũ��Ʈ
 */

// Null ������ ������
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
