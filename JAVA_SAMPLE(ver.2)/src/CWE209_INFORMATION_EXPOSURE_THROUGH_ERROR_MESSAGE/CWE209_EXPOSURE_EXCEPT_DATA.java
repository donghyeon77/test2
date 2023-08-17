package CWE209_INFORMATION_EXPOSURE_THROUGH_ERROR_MESSAGE;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/*
 * ������
 * �ֿ���� :: CWE209_InfoExposureErrMsg - �����޽����� ���� ��������
 * �ΰ����� :: CWE497_EXPOSURE_OF_SYSTEM_DATA_TO_UNAUTHORIZED_CONTROL_SPHERE - �ý��� ������ ���� ����
 */

// �����޽����� ���� ��������
public class CWE209_EXPOSURE_EXCEPT_DATA
{
	public void bad_GetMessage(String args)
	{
		Reader reader = null;
		String filePath = args;
		try{
			reader = new FileReader( new File(filePath));
		}catch(IOException e){
			/* FLAW */
			System.out.println(e.getMessage());
		}finally{
			if(reader != null) try{ reader.close(); }catch(IOException e){ System.out.println("IOException Message"); }
		}
	}
/* 
	public void ACA_GetMessage(String args)
	{
		Reader reader = null;
		String filePath = args;
		try{
			reader = new FileReader( new File(filePath));
		}catch(IOException e){
// ACA position : RESULT replace
// ${hsAcaTarget.lineSplitMid} : System.out.println(e.getMessage())
// System.out.println("EXCEPTION Message")

			System.out.println("EXCEPTION Message");
		}finally{
			if(reader != null) try{ reader.close(); }catch(IOException e){ System.out.println("IOException Message"); }
		}
	}
*/
	public void good_GetMessage(String args)
	{
		Reader reader = null;
		String filePath = args;
		try{
			reader = new FileReader( new File(filePath));
		}catch(IOException e){
			/* FIX */
			System.out.println("IOException Occured");
		}finally{
			if(reader != null) try{ reader.close(); }catch(IOException e){ System.out.println("IOException Message"); }
		}
	}
	
	public void bad_ToString(String args)
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
			System.out.println(e.toString()); 
		}finally{
			if(reader != null) try{ reader.close(); }catch(IOException e){ System.out.println("IOException Message"); }
		}
	}
	
	public void good_ToString(String args)
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