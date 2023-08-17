package CWE497_EXPOSURE_OF_SYSTEM_DATA_TO_UNAUTHORIZED_CONTROL_SPHERE;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletContext;

/*
 * 검출목록
 * 주요검출 :: CWE497_SystemDataInfoLeak - 시스템 데이터 정보 누출
 */

// 시스템 데이터 정보 누출
public class CWE497_SYSTEM_INFO_LEAK_IN_PRINT_STREAM_OUT
{
	public void bad_SystemProperty(String[] args)
	{
		String defaultPath = "";
		FileInputStream fis = null;
		 
		try
		{
			defaultPath = System.getProperty("java.library.path");
			if(defaultPath != null){
				defaultPath = defaultPath.toLowerCase();
				fis = new FileInputStream(directoryTraversalFilter(defaultPath) + "exec_file");
				//...
			} 
		}
		catch(IOException e)
		{
			/* FLAW */
			System.out.print("Cannot find exe on path " + defaultPath + "\n"); 
			
			/* FLAW */
			System.err.print("Cannot find exe on path " + defaultPath + "\n"); 
		}finally{
			if(fis != null) try{ fis.close(); }catch(IOException e){ System.out.println("IOException Message"); }
		}
	}
	
/* 
	public void ACA_SystemProperty(String[] args)
	{
		String defaultPath = "";
		FileInputStream fis = null;
		 
		try
		{
			defaultPath = System.getProperty("java.library.path");
			if(defaultPath != null){
				defaultPath = defaultPath.toLowerCase();
				fis = new FileInputStream(directoryTraversalFilter(defaultPath) + "exec_file");
				//...
			} 
		}
		catch(IOException e)
		{
//ACA position : result replace
//${hsAcaTarget.lineSplitMid} : System.out.print("Cannot find exe on path " + defaultPath + "\n")
//System.out.print("Error Exception Message")

			System.out.print("Error Exception Message"); 
			
			
			System.out.print("Error Exception Message"); 
			
		}finally{
			if(fis != null) try{ fis.close(); }catch(IOException e){ System.out.println("IOException Message"); }
		}
	}
*/
	
	public void good_SystemProperty(String[] args)
	{
		String defaultPath = "";
		FileInputStream fis = null;
		 
		try
		{
			defaultPath = System.getProperty("java.library.path");
			if(defaultPath != null){
				defaultPath = defaultPath.toLowerCase();
				fis = new FileInputStream(directoryTraversalFilter(defaultPath) + "exec_file");
				//...
			} 
		}
		catch(IOException e)
		{
			/* FIX */
			System.out.print("Error-03 : Cannot execute file"); 
			
			/* FIX */
			System.err.print("Error-03 : Cannot execute file"); 
		}finally{
			if(fis != null) try{ fis.close(); }catch(IOException e){ System.out.println("IOException Message"); }
		}
	}
	
	public void bad_SystemEnv(String[] args)
	{
		String defaultPath = "";
		FileInputStream fis = null;
		 
		try
		{
			defaultPath = System.getProperty("java.library.path");
			if(defaultPath != null){
				defaultPath = defaultPath.toLowerCase();
				fis = new FileInputStream(directoryTraversalFilter(defaultPath) + "exec_file");
				//...
			} 
		}
		catch(IOException e)
		{
			/* FLAW */
			System.out.printf("Cannot find exe on path %s \n", defaultPath); 
			
			/* FLAW */
			System.err.printf("Cannot find exe on path %s \n", defaultPath); 
		}finally{
			if(fis != null) try{ fis.close(); }catch(IOException e){ System.out.println("IOException Message"); }
		}
	}
	
	public void good_SystemEnv(String[] args)
	{
		String defaultPath = "";
		FileInputStream fis = null;
		 
		try
		{
			defaultPath = System.getProperty("java.library.path");
			if(defaultPath != null){
				defaultPath = defaultPath.toLowerCase();
				fis = new FileInputStream(directoryTraversalFilter(defaultPath) + "exec_file");
				//...
			} 
		}
		catch(IOException e)
		{
			/* FIX */
			System.out.printf("Error-03 : Cannot execute file"); 
			
			/* FIX */
			System.err.printf("Error-03 : Cannot execute file"); 
		}finally{
			if(fis != null) try{ fis.close(); }catch(IOException e){ System.out.println("IOException Message"); }
		}
	}
	
	public void bad_ContextPath(String[] args)
	{
		String defaultPath = "";
		FileInputStream fis = null;
		 
		try
		{
			defaultPath = System.getProperty("java.library.path");
			if(defaultPath != null){
				defaultPath = defaultPath.toLowerCase();
				fis = new FileInputStream(directoryTraversalFilter(defaultPath) + "exec_file");
				//...
			} 
		}
		catch(IOException e)
		{
			/* FLAW */
			System.out.println("Cannot find exe on path " + defaultPath + "\n"); 
			
			/* FLAW */
			System.err.println("Cannot find exe on path " + defaultPath + "\n"); 
		}finally{
			if(fis != null) try{ fis.close(); }catch(IOException e){ System.out.println("IOException Message"); }
		}
	}
	public void good_ContextPath(String[] args)
	{
		String defaultPath = "";
		FileInputStream fis = null;
		 
		try
		{
			defaultPath = System.getProperty("java.library.path");
			if(defaultPath != null){
				defaultPath = defaultPath.toLowerCase();
				fis = new FileInputStream(directoryTraversalFilter(defaultPath) + "exec_file");
				//...
			} 
		}
		catch(IOException e)
		{
			/* FIX */
			System.out.println("Error-03 : Cannot execute file"); 
			
			/* FIX */
			System.err.println("Error-03 : Cannot execute file"); 
		}finally{
			if(fis != null) try{ fis.close(); }catch(IOException e){ System.out.println("IOException Message"); }
		}
	}
	
	public String directoryTraversalFilter(String filePath){
		
		String filteredFilePath = "";
		
		if(filePath != null){
			/* 경로순회(directory traversal) 문자열 제거 */
			filePath = filePath.replaceAll("/", ""); // "/" 필터링 
			filePath = filePath.replaceAll("\\\\", ""); // "\" 필터링
			filePath = filePath.replaceAll("\\.\\.", ""); // ".." 필터링
			
			filteredFilePath = filePath;
		}
		return filteredFilePath;		
	}
}
