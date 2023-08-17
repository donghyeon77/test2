package CWE113_HTTP_RESPONSE_SPLITTING;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/*
 * 검출목록
 * 주요검출 :: CWE113_httpResponseSplitting - HTTP 응답 분할
 */

// HTTP 응답 분할
public class CWE113_HTTP_RESPONSE_SPLITTING_COOKIE
{
	public void bad(ServletRequest request, HttpServletResponse response)
	{
		String author = request.getParameter("authorName");
		/* FLAW */
		Cookie cookie = new Cookie("replidedAuthor", author);
		cookie.setMaxAge(1000);
		cookie.setSecure(true);
		response.addCookie(cookie);
	}
/*
// import com.hsecure.xwm.aca.utils.*;

	public void ACA(ServletRequest request, HttpServletResponse response)
	{
// ${hsAcaTarget.lineSplitMid} : request.getParameter("authorName")
// HsHtmlEncoder.encodeCRLF( ${hsAcaTarget.lineSplitMid} )

		String author = HsHtmlEncoder.encodeCRLF( request.getParameter("authorName") );
		
		Cookie cookie = new Cookie("replidedAuthor", author);
		cookie.setMaxAge(1000);
		cookie.setSecure(true);
		response.addCookie(cookie);
	}
*/
	 
	public void good(ServletRequest request, HttpServletResponse response)
	{
		String author = request.getParameter("authorName");
		/* FIX */
		String filtered_author = author.replaceAll("\r", "").replaceAll("\n", "");
		Cookie cookie = new Cookie("replidedAuthor", filtered_author);
		cookie.setMaxAge(1000);
		cookie.setSecure(true);
		response.addCookie(cookie);
	}
}
