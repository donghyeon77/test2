package CWE539_INFORMATION_EXPOSURE_THROUGH_PERSISTENT_COOKIES;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/*
 * 검출목록
 * 주요검출 :: CWE539_CookieExposureByCookieFromHardDisk - 사용자 하드디스크에 저장되는 쿠키를 통한 정보노출
 * 부가검출 :: CWE476_NullPointerDereference - 널 포인터 역참조
 */

// 사용자 하드디스크에 저장되는 쿠키를 통한 정보노출
public class CWE539_COOKIE_EXPOSURE_SET_MAX_AGE
{
	public void bad_External(HttpServletRequest request)
	{
		String r = request.getParameter("maxAge");
		String num = request.getParameter("num");

		if(r.matches("[0-9]+"))
		{
			String sessionID = request.getParameter("sessionID");
			if(sessionID.matches("[A-Z=0-9a-z]+"))
			{
				Cookie c = new Cookie("sessionID", sessionID);
				/* FLAW */
				c.setMaxAge(Integer.parseInt(num));
			}
		}
	}
	
/* 
	public void ACA_External(HttpServletRequest request)
	{
		String r = request.getParameter("maxAge");
		String num = request.getParameter("num");

		if(r.matches("[0-9]+"))
		{
			String sessionID = request.getParameter("sessionID");
			if(sessionID.matches("[A-Z=0-9a-z]+"))
			{
				Cookie c = new Cookie("sessionID", sessionID);

// ACA position : result replace 
// ${hsAcaTarget.lineSplitMid} : Integer.parseInt(num)
// 0

				c.setMaxAge(Integer.parseInt(num));
			}
		}
	}
 */
	
	public void good_External(HttpServletRequest request)
	{
		String r = request.getParameter("maxAge");
		/* FIX */
		String num = "0";

		if(r.matches("[0-9]+"))
		{
			String sessionID = request.getParameter("sessionID");
			if(sessionID.matches("[A-Z=0-9a-z]+"))
			{
				Cookie c = new Cookie("sessionID", sessionID);
				c.setMaxAge(Integer.parseInt(num));
			}
		}
	}
	
	public void bad_Literal(HttpServletRequest request)
	{
		String r = request.getParameter("maxAge");
		String num = "3700";

		if(r.matches("[0-9]+"))
		{
			String sessionID = request.getParameter("sessionID");
			if(sessionID.matches("[A-Z=0-9a-z]+"))
			{
				Cookie c = new Cookie("sessionID", sessionID);
				/* FLAW */
				c.setMaxAge(Integer.parseInt(num));
			}
		}
	}
	
	public void good_Literal(HttpServletRequest request)
	{
		String r = request.getParameter("maxAge");
		/* FIX */
		String num = "0";

		if(r.matches("[0-9]+"))
		{
			String sessionID = request.getParameter("sessionID");
			if(sessionID.matches("[A-Z=0-9a-z]+"))
			{
				Cookie c = new Cookie("sessionID", sessionID);
				c.setMaxAge(Integer.parseInt(num));
			}
		}
	}
}