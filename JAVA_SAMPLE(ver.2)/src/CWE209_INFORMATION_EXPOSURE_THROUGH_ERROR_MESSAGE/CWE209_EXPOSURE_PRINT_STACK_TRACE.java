package CWE209_INFORMATION_EXPOSURE_THROUGH_ERROR_MESSAGE;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/*
 * 검출목록
 * 주요검출 :: CWE209_InfoExposureErrMsg - 오류메시지를 통한 정보노출
 */

// 오류메시지를 통한 정보노출
public class CWE209_EXPOSURE_PRINT_STACK_TRACE
{
	public void bad(String args)
	{
		Reader reader = null;
		String filePath = args;
		try
		{
			reader = new FileReader( new File(filePath));
		}
		catch(IOException e)
		{
			/* FLAW */
			e.printStackTrace();
		}finally{
			if(reader != null) try{ reader.close(); }catch(IOException e){ System.out.println("IOException Message"); }
		}
	}
/* 
	public void ACA(String args)
	{
		Reader reader = null;
		String filePath = args;
		try
		{
			reader = new FileReader( new File(filePath));
		}
		catch(IOException e)
		{
// ACA position : RESULT replace
// ${hsAcaTarget.lineSplitMid} : e.printStackTrace()
// System.out.println("EXCEPTION Message")

			System.out.println("EXCEPTION Message");
		}finally{
			if(reader != null) try{ reader.close(); }catch(IOException e){ System.out.println("IOException Message"); }
		}
	}
*/
	
	public void good(String args)
	{
		Reader reader = null;
		String filePath = args;
		try
		{
			reader = new FileReader( new File(filePath));
		}
		catch(IOException e)
		{
			/* FIX */
			System.out.println("IOException Occured");
		}finally{
			if(reader != null) try{ reader.close(); }catch(IOException e){ System.out.println("IOException Message"); }
		}
	}
}