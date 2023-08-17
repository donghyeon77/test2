package CWE676_USE_OF_POTENTIALLY_DANGEROUS_FUNCTION;

/*
 * ������
 * �ֿ���� :: CWE676_Use_of_Potentially_Dangerous_Function - ����� API ���
 * �ΰ����� :: CWE489_RemainderDebuggerCode - ���ŵ��� ���� ����� �ڵ�
 *         :: CWE674_RecursiveFunctionNotUsingControlStatement - ������� �ʴ� ��� �Լ�
 *         :: CWE404_ImproperResourceRelease - �������� �ڿ� ����
 */

public class CWE676_DANGER_API_THREAD_RUN extends Thread {
	private boolean isDelete;
	
	public CWE676_DANGER_API_THREAD_RUN( boolean isDelete )
	{
		this.isDelete = isDelete;
	}

	public static void Bad (String[] args){
		CWE676_DANGER_API_THREAD_RUN accessThread = new CWE676_DANGER_API_THREAD_RUN(false);
		CWE676_DANGER_API_THREAD_RUN deleteThread = new CWE676_DANGER_API_THREAD_RUN(true);
		/* FLAW */
		accessThread.run();
		deleteThread.run();
	}
	
/*
	public static void ACA (String[] args){
		CWE676_DANGER_API_THREAD_RUN accessThread = new CWE676_DANGER_API_THREAD_RUN(false);
		CWE676_DANGER_API_THREAD_RUN deleteThread = new CWE676_DANGER_API_THREAD_RUN(true);
// ACA position : cause replace
// ${hsAcaTarget.lineSplitMid} : accessThread
// ${hsAcaTarget.lineSplitMid}.start();//

		//accessThread.run();
		accessThread.Start();
		//deleteThread.run();
		deleteThread.Start();
	}
 */
	
	public static void Good (String[] args){		
		CWE676_DANGER_API_THREAD_RUN accessThread = new CWE676_DANGER_API_THREAD_RUN(false);
		CWE676_DANGER_API_THREAD_RUN deleteThread = new CWE676_DANGER_API_THREAD_RUN(true);
		/* FIX */
		accessThread.start();
		deleteThread.start();
	}
}
