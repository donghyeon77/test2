package CWE676_USE_OF_POTENTIALLY_DANGEROUS_FUNCTION;

/*
 * 검출목록
 * 주요검출 :: CWE676_Use_of_Potentially_Dangerous_Function - 취약한 API 사용
 * 부가검출 :: CWE489_RemainderDebuggerCode - 제거되지 않은 디버거 코드
 *         :: CWE674_RecursiveFunctionNotUsingControlStatement - 종료되지 않는 재귀 함수
 *         :: CWE404_ImproperResourceRelease - 부적절한 자원 해제
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
