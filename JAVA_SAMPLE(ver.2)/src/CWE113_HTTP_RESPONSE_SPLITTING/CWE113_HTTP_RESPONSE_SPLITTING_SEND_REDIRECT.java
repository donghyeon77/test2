package CWE113_HTTP_RESPONSE_SPLITTING;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * ������
 * �ֿ���� :: CWE113_httpResponseSplitting - HTTP ���� ����
 * �ΰ����� :: CWE601_�ŷڵ��� �ʴ� URL �ּҷ� �ڵ� ���� ����
 */

// HTTP ���� ����
public class CWE113_HTTP_RESPONSE_SPLITTING_SEND_REDIRECT
{
	public void bad(ServletRequest request, HttpServletResponse response)
	{
		String url = request.getParameter("url");
		/* FLAW */
		try {
			response.sendRedirect(url);
		} catch (IOException e) {
			System.out.println("IOException Message");
		}
	}
	
/*
// import com.hsecure.xwm.aca.utils.*;

	public void ACA(ServletRequest request, HttpServletResponse response)
	{
// ${hsAcaTarget.lineSplitMid} : request.getParameter("url")
// HsHtmlEncoder.encodeCRLF( ${hsAcaTarget.lineSplitMid} )

		String url = HsHtmlEncoder.encodeCRLF( request.getParameter("url") );
		
		try {
			response.sendRedirect(url);
		} catch (IOException e) {
			System.out.println("IOException Message");
		}
	}
*/

	public void good(ServletRequest request, HttpServletResponse response)
	{
		String url = request.getParameter("url");
		/* FIX */
		url = url.replaceAll("\r", "").replaceAll("\n", "");
		try {
			response.sendRedirect(url);
		} catch (IOException e) {
			System.out.println("IOException Message");
		}
	}
}
