package CWE614_CLEARTEXT_STORAGE_OF_SENSITIVE_INFORMATION;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 검출목록
 * 주요검출 :: CWE614_중요정보 평문 전송
*/

public class CWE614_COOKIE_EXPOSURE_RESPONSE_ADDCOOKIE
{
	public void Bad(HttpServletRequest request, HttpServletResponse response)
	{
		String sessionID = request.getParameter("sessionID");
		//http response split filtering
		sessionID = sessionID.replaceAll("\r", "").replaceAll("\n", ""); 
		
		Cookie c = new Cookie("sessionID", sessionID);
		response.addCookie(c);
	}
	
/* ACA hold  
	public void ACA(HttpServletRequest request, HttpServletResponse response)
	{
		String sessionID = request.getParameter("sessionID");
		//http response split filtering
		sessionID = sessionID.replaceAll("\r", "").replaceAll("\n", ""); 

		Cookie c = new Cookie("sessionID", sessionID);
//ACA postion : casue-2 bottom  
//${hsAcaTarget.lineSplitMid} : c
//${hsAcaTarget.lineSplitMid}.setSecure(true); 
		c.setSecure(true);
		
		response.addCookie(c);
	}
 */
	
	public void Good(HttpServletRequest request, HttpServletResponse response)
	{
		String sessionID = request.getParameter("sessionID");
		//http response split filtering
		sessionID = sessionID.replaceAll("\r", "").replaceAll("\n", ""); 
		
		Cookie c = new Cookie("sessionID", sessionID);
		c.setSecure(true);
		response.addCookie(c);
	}
}