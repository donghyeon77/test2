package CWE676_USE_OF_POTENTIALLY_DANGEROUS_FUNCTION;

/*
 * ������
 * �ֿ���� :: CWE676_Use_of_Potentially_Dangerous_Function - ����� API ���
 * �ΰ����� :: CWE489_RemainderDebuggerCode - ���ŵ��� ���� ����� �ڵ�
 *         :: CWE674_RecursiveFunctionNotUsingControlStatement - ������� �ʴ� ��� �Լ�
 *         :: CWE404_ImproperResourceRelease - �������� �ڿ� ����
 */

public class CWE676_DANGER_API_THREAD_STOP extends Thread {
	private boolean isDelete;
	
	public CWE676_DANGER_API_THREAD_STOP( boolean isDelete )
	{
		this.isDelete = isDelete;
	}

	public static void bad (String[] args){
		CWE676_DANGER_API_THREAD_STOP accessThread = new CWE676_DANGER_API_THREAD_STOP(false);
		/* FLAW */
		accessThread.start();
		accessThread.stop();
	}
	
/* ACA hold
	public static void ACA (String[] args){
		CWE676_DANGER_API_THREAD_STOP accessThread = new CWE676_DANGER_API_THREAD_STOP(false);
// ACA position : cause replace
// ${hsAcaTarget.lineSplitMid} : accessThread
// ${hsAcaTarget.lineSplitMid}.interrupt();//

		accessThread.interrupt();//.stop();
	}
 */
	
	public static void good (String[] args){		
		CWE676_DANGER_API_THREAD_STOP accessThread = new CWE676_DANGER_API_THREAD_STOP(false);
		/* FIX */
		accessThread.start();
		accessThread.interrupt();
	}
}
