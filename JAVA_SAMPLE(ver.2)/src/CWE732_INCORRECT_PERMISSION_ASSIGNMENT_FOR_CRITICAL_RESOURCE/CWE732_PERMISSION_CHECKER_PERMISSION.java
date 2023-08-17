package CWE732_INCORRECT_PERMISSION_ASSIGNMENT_FOR_CRITICAL_RESOURCE;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/*
 * 검출목록
 * 주요검출 :: CWE732_WrongResourcePermission - 잘못된 중요 자원 권한
 */

public class CWE732_PERMISSION_CHECKER_PERMISSION {
	public void bad()
	{
		String text = "test";
		String filePath = System.getProperty("report");
		if( filePath == null )
		{
			filePath = "/home/report/report.txt";
		}
		File file = new File( filePath );
		/* FLAW */
		file.setExecutable(true, false); //실행
		file.setReadable(true, false); //읽기
		file.setWritable(true, false); //쓰기
		
		BufferedWriter fw = null;
		try {			
			fw = new BufferedWriter(new FileWriter(file, true));
	        fw.write(text);
	        fw.flush();
		} catch (IOException e) {
			System.out.println("IOException Occured!!");
		}finally{
			if(fw != null) try{ fw.close(); }catch(IOException e){ System.out.println("Exception Message"); }
		}
	}
	
/*  ACA hold
	public void ACA()
	{
		String text = "test";
		String filePath = System.getProperty("report");
		if( filePath == null )
		{
			filePath = "/home/report/report.txt";
		}
		File file = new File( filePath );
		
		file.setExecutable(true, false); //실행
		file.setReadable(true, false); //읽기
		file.setWritable(true, false); //쓰기
		
		BufferedWriter fw = null;
		try {			
			fw = new BufferedWriter(new FileWriter(file, true));
	        fw.write(text);
	        fw.flush();
		} catch (IOException e) {
			System.out.println("IOException Occured!!");
		}finally{
			if(fw != null) try{ fw.close(); }catch(IOException e){ System.out.println("Exception Message"); }
		}
	}
 */
	
	public void good()
	{
		String text = "test";		
		String filePath = System.getProperty("report");
		if( filePath == null )
		{
			filePath = "/home/report/report.txt";
		}
		File file = new File( filePath );
		/* FIX */
		file.setExecutable(false); 
		file.setReadable(false);
		file.setWritable(false); 
		
		BufferedWriter fw = null;
		try {
			fw = new BufferedWriter(new FileWriter(file, true));
	        fw.write(text);
	        fw.flush();
		} catch (IOException e) {
			System.out.println("IOException Occured!!");
		}finally{
			if(fw != null) try{ fw.close(); }catch(IOException e){ System.out.println("Exception Message"); }
		}
	}
}
