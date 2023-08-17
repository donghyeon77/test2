package CWE732_INCORRECT_PERMISSION_ASSIGNMENT_FOR_CRITICAL_RESOURCE;

import java.io.File;
import java.io.IOException;

/*
 * 검출목록
 * 주요검출 :: CWE732_WrongResourcePermission - 잘못된 중요 자원 권한
 */

public class CWE732_PERMISSION_CHECKER_MASK {
	public void Bad()
	{
		//파일 권한 : rw-rw-rw-, 디렉터리 권한 : rwxrwxrwx
		String cmd = "umask 0";
		File file = new File("/home/report/report.txt");
		//...
		try {
			/* FLAW */
			Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			System.out.println("IOException Occured!!");
		}
	}

/* 
	public void ACA()
	{
		//파일 권한 : rw-rw-rw-, 디렉터리 권한 : rwxrwxrwx
// ACA position : cause replace
// ${hsAcaTarget.lineSplitMid} : "umask 0"
//"umask 77"
		String cmd = "umask 77";
		File file = new File("/home/report/report.txt");
		//...
		try {
			
			Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			System.out.println("IOException Occured!!");
		}
	}
 */

	public void Good()
	{
		//파일 권한 : rw-------, 디렉터리 권한 : rwx------
		/* FIX */
		String cmd = "umask 77";
		File file = new File("/home/report/report.txt");
		//...
		try {
			Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			System.out.println("IOException Occured!!");
		}
	}
}
