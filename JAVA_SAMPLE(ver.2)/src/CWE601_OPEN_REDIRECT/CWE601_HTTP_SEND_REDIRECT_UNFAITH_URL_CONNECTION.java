package CWE601_OPEN_REDIRECT;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 검출목록
 * 주요검출 :: CWE601_신로되지 않는 URL 주소로 자동 접속 연결
  */

public class CWE601_HTTP_SEND_REDIRECT_UNFAITH_URL_CONNECTION
{
	public void Bad(ServletRequest request, HttpServletResponse response)
	{
		String url = request.getParameter("url");
		if(url != null)
		{
			try
			{
				//http response split filtering
				url = url.replaceAll("\r", ""); 
				url = url.replaceAll("\n", "");
				
				/* FLAW */
				response.sendRedirect(url);
			}
			catch ( IOException e )
			{
				System.out.println("IOException Occured!!");
			}
		}
	}
	
/* ACA hold
	public void ACA(ServletRequest request, HttpServletResponse response)
	{
		String url = request.getParameter("url");
		if(url != null)
		{
			try
			{
				//http response split filtering
				url = url.replaceAll("\r", ""); 
				url = url.replaceAll("\n", "");
				
				
				response.sendRedirect(url);
			}
			catch ( IOException e )
			{
				System.out.println("IOException Occured!!");
			}
		}
	}
 */
	
	public void Good(ServletRequest request, HttpServletResponse response)
	{
		/* FIX */
		String urlType = request.getParameter("urlType");
		String url = "";
		
		switch (urlType) {
		case "1":
			url = "/main.do";
			break;
		case "2":
			url = "/login.do";
			break;	
		case "3":
			url = "/list.do";
			break;	
		default:
			break;
		}
		
		try{
			if(!url.equals("") ){
				response.sendRedirect(url);
			}
		}catch(IOException e){
			System.out.println("IOException Occured!!");
		}
	}
	
	public void Good2(ServletRequest request, HttpServletResponse response)
	{
		/* FIX */
		String urlType = request.getParameter("urlType");
		String url = getRedirectURL(urlType);
		
		try{
			if(!url.equals("") ){
				response.sendRedirect(url);
			}
		}catch(IOException e){
			System.out.println("IOException Occured!!");
		}
	}
	
	public void Test(ServletRequest request, HttpServletResponse response)
	{
		/* FIX */
		String urlType = request.getParameter("urlType");
		String urlParam = request.getParameter("urlParam"); //URL_PARAM 추적상태에 urlParam 등록 
		String url = getRedirectURL(urlType);
		String filtered_urlParam = "";
		try{
			if(!url.equals("") ){
				if(urlParam != null && !urlParam.equals("")){
					//http response split filtering
					filtered_urlParam = urlParam.replaceAll("\r", "").replaceAll("\n", "");
					response.sendRedirect(url+"?"+filtered_urlParam);
				}else{
					response.sendRedirect(url);
				}
			}
		}catch(IOException e){
			System.out.println("IOException Occured!!");
		}
	}
	
	public String getRedirectURL(String urlType){
		
		String url = "";
		
		switch (urlType) {
		case "1":
			url = "/main.do";
			break;
		case "2":
			url = "/login.do";
			break;	
		case "3":
			url = "/list.do";
			break;	
		default:
			break;
		}
		
		return url;
	}
}