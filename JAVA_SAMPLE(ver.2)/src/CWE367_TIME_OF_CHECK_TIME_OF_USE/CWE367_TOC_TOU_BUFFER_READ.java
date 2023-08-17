package CWE367_TIME_OF_CHECK_TIME_OF_USE;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

// 경쟁조건 : 검사시점과 사용시점(TOCTOU)
public class CWE367_TOC_TOU_BUFFER_READ {
	public void bad() {
		File f = new File("test1.txt");
		if( !f.canRead() )
		{
			System.out.println("File CAN'T READ");
			return;
		}
		
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

	public void good() {
		File f = new File("test1.txt");
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
