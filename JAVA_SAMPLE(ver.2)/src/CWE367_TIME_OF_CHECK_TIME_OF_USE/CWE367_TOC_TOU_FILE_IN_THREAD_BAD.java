package CWE367_TIME_OF_CHECK_TIME_OF_USE;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/*
 * ������
 * �ֿ���� :: CWE367_TOCTOU - �������� : �˻������ ������(TOCTOU)
 * �ΰ����� :: CWE-489_���ŵ��� �ʰ� ���� ����� �ڵ�
 */

// �������� : �˻������ ������(TOCTOU)
public class CWE367_TOC_TOU_FILE_IN_THREAD_BAD extends Thread {
	private boolean isDelete;
	
	public CWE367_TOC_TOU_FILE_IN_THREAD_BAD( boolean isDelete )
	{
		this.isDelete = isDelete;
	}

	/* FLAW */
	@Override
	public void run() {
		if(isDelete) {
			System.out.println("File Delte Thread");
			File f = new File("test1.txt");

			if(f.exists()){
				boolean b = f.delete();
			}
		}else{
			System.out.println("File Access Thread");
			BufferedReader br = null;
			try{
				File f = new File("test1.txt");
	
				if(f.exists()){
					br = new BufferedReader(new FileReader(f));
					while(br.ready()) {
						br.readLine();
					}
				}
			}catch(IOException e) {
				System.err.println("IOException occurred");
			}finally{
				if(br != null) try{ br.close(); }catch(IOException e){ System.out.println("IOException occurred");}
			}
		}
	}

/*
// void run()
// ${hsAcaTarget.lineSplitLeft} : public void
// ${hsAcaTarget.lineSplitMid} : run()
// synchronized ${hsAcaTarget.lineSplitLeft} ${hsAcaTarget.lineSplitMid}

	@Override
	synchronized public void run() {
		if(isDelete) {
			System.out.println("File Delte Thread");
			File f = new File("test1.txt");

			if(f.exists()){
				boolean b = f.delete();
			}
		}else{
			System.out.println("File Access Thread");
			BufferedReader br = null;
			try{
				File f = new File("test1.txt");
	
				if(f.exists()){
					br = new BufferedReader(new FileReader(f));
					while(br.ready()) {
						br.readLine();
					}
				}
			}catch(IOException e) {
				System.err.println("IOException occurred");
			}finally{
				if(br != null) try{ br.close(); }catch(IOException e){ System.out.println("IOException occurred");}
			}
		}
	}
 */

	public void SampleTest_CWE367_TOCTOU(boolean isDelete){
		this.isDelete = isDelete;
	}

	public static void main (String[] args){
		CWE367_TOC_TOU_FILE_IN_THREAD_BAD accessThread = new CWE367_TOC_TOU_FILE_IN_THREAD_BAD(false);
		CWE367_TOC_TOU_FILE_IN_THREAD_BAD deleteThread = new CWE367_TOC_TOU_FILE_IN_THREAD_BAD(true);

		accessThread.start();
		deleteThread.start();
	}
}