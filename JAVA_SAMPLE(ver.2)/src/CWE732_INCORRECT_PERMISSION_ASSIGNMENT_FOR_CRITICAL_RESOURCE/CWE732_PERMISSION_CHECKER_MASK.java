package CWE732_INCORRECT_PERMISSION_ASSIGNMENT_FOR_CRITICAL_RESOURCE;

import java.io.File;
import java.io.IOException;

/*
 * ������
 * �ֿ���� :: CWE732_WrongResourcePermission - �߸��� �߿� �ڿ� ����
 */

public class CWE732_PERMISSION_CHECKER_MASK {
	public void Bad()
	{
		//���� ���� : rw-rw-rw-, ���͸� ���� : rwxrwxrwx
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
		//���� ���� : rw-rw-rw-, ���͸� ���� : rwxrwxrwx
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
		//���� ���� : rw-------, ���͸� ���� : rwx------
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
