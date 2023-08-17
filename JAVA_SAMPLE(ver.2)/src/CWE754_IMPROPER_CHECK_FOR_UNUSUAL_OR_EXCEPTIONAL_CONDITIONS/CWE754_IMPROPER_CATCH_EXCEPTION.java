package CWE754_IMPROPER_CHECK_FOR_UNUSUAL_OR_EXCEPTIONAL_CONDITIONS;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

/*
 * 검출목록
 * 주요검출 :: CWE754_ImproperException - 부적절한 예외 처리
 */

public class CWE754_IMPROPER_CATCH_EXCEPTION
{
	public void Bad(String fileName)
	{
		FileReader fr = null;
		try
		{
			File myFile = new File(fileName);
			fr = new FileReader(myFile);
		}
		/* FLAW */
		catch(Exception e)
		{
			System.out.println("Exception Occured");
		}finally{
			if(fr != null) try{ fr.close(); }catch(IOException e){ System.out.println("Exception Message"); }
		}
	}
	
/* ACA hold
	public void ACA(String fileName)
	{
		FileReader fr = null;
		try
		{
			File myFile = new File(fileName);
			fr = new FileReader(myFile);
		}
		
		catch(Exception e)
		{
			System.out.println("Exception Occured");
		}finally{
			if(fr != null) try{ fr.close(); }catch(IOException e){ System.out.println("Exception Message"); }
		}
	}
 */
	
	public void Good(String fileName) throws FileNotFoundException, IOException
	{
		FileReader fr = null;
		try
		{
			File myFile = new File(fileName);
			fr = new FileReader(myFile);
		}
		/* FIX */
		catch(FileNotFoundException e)
		{
			System.out.println("FileNotFoundException Occured");
		}finally{
			if(fr != null) try{ fr.close(); }catch(IOException e){ System.out.println("Exception Message"); }
		}
	}
}
