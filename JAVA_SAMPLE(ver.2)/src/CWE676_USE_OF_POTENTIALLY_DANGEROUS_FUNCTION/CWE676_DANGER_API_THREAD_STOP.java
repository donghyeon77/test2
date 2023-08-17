package CWE676_USE_OF_POTENTIALLY_DANGEROUS_FUNCTION;

/*
 * 검출목록
 * 주요검출 :: CWE676_Use_of_Potentially_Dangerous_Function - 취약한 API 사용
 * 부가검출 :: CWE489_RemainderDebuggerCode - 제거되지 않은 디버거 코드
 *         :: CWE674_RecursiveFunctionNotUsingControlStatement - 종료되지 않는 재귀 함수
 *         :: CWE404_ImproperResourceRelease - 부적절한 자원 해제
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
