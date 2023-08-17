package CWE79_CROSS_SITE_SCRIPT;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 검출목록
 * 주요검출 :: CWE79_OutputCrossSiteScript - 크로스사이트 스크립트
 */

public class CWE79_CROSS_SITE_SCRIPT_RESPONSE_PRINT
{
	public void bad(HttpServletRequest request, HttpServletResponse response)
	{
		String data;
		data = request.getParameter("name");		
		/* FLAW */
		try
		{
			response.getWriter().print("bad(): data = " + data);
			response.getWriter().printf("bad(): data = " + data);
			response.getWriter().println("bad(): data = " + data);
		}
		catch (IOException e)
		{
			System.out.println("IOException");
		}
	}

/*
// import com.hsecure.xwm.aca.utils.*;

	public void ACA(HttpServletRequest request, HttpServletResponse response)
	{
		String data;

// ${hsAcaTarget.lineSplitMid} : request.getParameter("name")
// HsHtmlEncoder.encode( ${hsAcaTarget.lineSplitMid} )
		
		data = HsHtmlEncoder.encode( request.getParameter("name") );		
		
		try
		{
			response.getWriter().print("bad(): data = " + data );
		}
		catch (IOException e)
		{
			System.out.println("IOException");
		}
	}
	*/
	
	public void good(HttpServletRequest request, HttpServletResponse response)
	{
		String data;
		data = request.getParameter("name");
		/* FIX */
		try
		{
			if( data != null )
			{
				data = data.replaceAll("<", "&lt;");
				data = data.replaceAll(">", "&gt;");
				data = data.replaceAll("&", "&amp;");
				data = data.replaceAll("\"", "&quot;");
				data = data.replaceAll("'", "&#x27;");
				data = data.replaceAll("/", "&#x2F;");
			}
			response.getWriter().print("bad(): data = " + data);
			response.getWriter().printf("bad(): data = " + data);
			response.getWriter().println("bad(): data = " + data);
		}
		catch (IOException e)
		{
			System.out.println("IOException");
		}
	}
}