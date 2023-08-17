package CWE113_HTTP_RESPONSE_SPLITTING;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * ������
 * �ֿ���� :: CWE113_httpResponseSplitting - HTTP ���� ����
 */

// HTTP ���� ����
public class CWE113_HTTP_RESPONSE_SPLITTING_HEADER
{
	public void bad_AddHeader(ServletRequest request, HttpServletResponse response)
	{
		String author = request.getParameter("authorName");
		/* FLAW */
		response.addHeader("author", author);
	}
	
/* 
// import com.hsecure.xwm.aca.utils.*;

	public void ACA_AddHeader(ServletRequest request, HttpServletResponse response)
	{
// ${hsAcaTarget.lineSplitMid} : request.getParameter("authorName")
// HsHtmlEncoder.encodeCRLF( ${hsAcaTarget.lineSplitMid} )

		String author = HsHtmlEncoder.encodeCRLF( request.getParameter("authorName") );
		
		response.addHeader("author", author);
	}
*/
	
	public void good_AddHeader(ServletRequest request, HttpServletResponse response)
	{
		String author = request.getParameter("authorName");
		/* FIX */
		String filtered_author = author.replaceAll("\r", "").replaceAll("\n", "");
		response.addHeader("author", filtered_author);
	}
	
	public void bad_SetHeader(ServletRequest request, HttpServletResponse response)
	{
		String author = request.getParameter("authorName");
		/* FLAW */
		response.setHeader("author", author);
	}
	
/* 
// import com.hsecure.xwm.aca.utils.*;

	public void ACA_SetHeader(ServletRequest request, HttpServletResponse response)
	{
// ${hsAcaTarget.lineSplitMid} : request.getParameter("authorName")
// HsHtmlEncoder.encodeCRLF( ${hsAcaTarget.lineSplitMid} )

		String author = HsHtmlEncoder.encodeCRLF( request.getParameter("authorName") );
		
		response.setHeader("author", author);
	}
*/
	
	public void good_SetHeader(ServletRequest request, HttpServletResponse response)
	{
		String author = request.getParameter("authorName");
		/* FIX */
		String filtered_author = author.replaceAll("\r", "").replaceAll("\n", "");
		response.setHeader("author", filtered_author);
	}
}
