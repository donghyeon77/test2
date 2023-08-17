package CWE676_USE_OF_POTENTIALLY_DANGEROUS_FUNCTION;

/*
 * 검출목록
 * 주요검출 :: CWE676_Use_of_Potentially_Dangerous_Function - 취약한 API 사용
  */

public class CWE676_DANGER_API_SYSTEM_EXIT extends Thread {
	private boolean isDelete;
	
	public CWE676_DANGER_API_SYSTEM_EXIT( boolean isDelete )
	{
		this.isDelete = isDelete;
	}

	public static void Bad (String[] args){
		CWE676_DANGER_API_SYSTEM_EXIT accessThread = new CWE676_DANGER_API_SYSTEM_EXIT(false);
		CWE676_DANGER_API_SYSTEM_EXIT deleteThread = new CWE676_DANGER_API_SYSTEM_EXIT(true);		
		try{
			accessThread.start();
			deleteThread.start();
		}catch(Exception e){
			/* FLAW */
			System.exit(1);
		}
		
	}
	
/* 
	public static void ACA (String[] args){
		CWE676_DANGER_API_SYSTEM_EXIT accessThread = new CWE676_DANGER_API_SYSTEM_EXIT(false);
		CWE676_DANGER_API_SYSTEM_EXIT deleteThread = new CWE676_DANGER_API_SYSTEM_EXIT(true);		
		try{
			accessThread.start();
			deleteThread.start();
		}catch(Exception e){
// ACA postion : result replace
// ${hsAcaTarget.lineSplitMid} : System.exit(1)
//System.out.println("Exception Message")

			System.out.println("Exception Message");
		}
		
	}
 */
	
	public static void Good (String[] args){		
		CWE676_DANGER_API_SYSTEM_EXIT accessThread = new CWE676_DANGER_API_SYSTEM_EXIT(false);
		CWE676_DANGER_API_SYSTEM_EXIT deleteThread = new CWE676_DANGER_API_SYSTEM_EXIT(true);		
		try{
			accessThread.start();
			deleteThread.start();
		}catch(Exception e){
			/* FIX */
			System.out.println("Exception Message");
		}
	}
}
