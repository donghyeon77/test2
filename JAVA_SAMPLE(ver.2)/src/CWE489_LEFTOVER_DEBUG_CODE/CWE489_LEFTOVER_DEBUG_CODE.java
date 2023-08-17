package CWE489_LEFTOVER_DEBUG_CODE;

/*
 * 검출목록
 * 주요검출 :: CWE489_RemainderDebuggerCode - 제거되지 않고 남은 디버거 코드
 */

// 제거되지 않고 남은 디버거 코드
public class CWE489_LEFTOVER_DEBUG_CODE
{	
	/* FLAW */
	public static void main(String[] args)
	{
		System.err.println("Print debug code");
	}

/* ACA hold
	public static void main(String[] args)
	{
		System.err.println("Print debug code");
	}
*/
}
