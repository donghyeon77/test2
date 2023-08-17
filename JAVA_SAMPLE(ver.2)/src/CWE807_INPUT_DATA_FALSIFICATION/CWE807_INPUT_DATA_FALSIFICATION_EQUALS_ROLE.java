package CWE807_INPUT_DATA_FALSIFICATION;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

public class CWE807_INPUT_DATA_FALSIFICATION_EQUALS_ROLE {
	public void Bad(HttpServletRequest request)
	{
		Cookie[] cookies = request.getCookies();
		for (int i=0; cookies!=null && i<cookies.length; i++)
		{
			/* FLAW */
			Cookie c = cookies[i];
			if (c.getName().equals("role"))
			{
				String userRole = c.getValue();
			}
		}
	}
	
/* ACA hold
	public void ACA(HttpServletRequest request)
	{
		Cookie[] cookies = request.getCookies();
		for (int i=0; cookies!=null && i<cookies.length; i++)
		{
			
			Cookie c = cookies[i];
			if (c.getName().equals("role"))
			{
				String userRole = c.getValue();
			}
		}
	}
 */
	
	public void Good(HttpServletRequest request)
	{
		HttpSession dummySession = request.getSession(true);
	    HttpSessionContext context = dummySession.getSessionContext();

		//......
		/* FIX */
	    String id = "id";
		HttpSession session = context.getSession(id);
		   String userRole = (String)session.getValue("role");
		//......
	}
}
