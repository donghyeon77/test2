package CWE488_EXPOSURE_OF_DATA_ELEMENT_TO_WRONG_WESSION;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * ������
 * �ֿ���� :: CWE488_DataInfoExposureByWrongSession - �߸��� ���ǿ� ���� ������ ���� ����
 */

// �߸��� ���ǿ� ���� ������ ���� ����
public class CWE488_EXPOSURE_WRONG_SESSION_IN_HTTP_SERVLET extends HttpServlet
{
	private String name;
	private final String except ="";
	public void Bad(HttpServletRequest request, HttpServletResponse response)
	{
		/* FLAW */
		name = request.getParameter("name");
		if(-1 != name.indexOf("/")){
			System.err.printf("bad input");
		}else{	
			System.err.printf("%s thanks for visiting !", name);
		}
	}
	
/* ACA Hold
	public void ACA(HttpServletRequest request, HttpServletResponse response)
	{
// ACA postition : result replace
// ${hsAcaTarget.lineSplitMid} : name
// String ${hsAcaTarget.lineSplitMid}
		String name = request.getParameter("name");
		if(-1 != name.indexOf("/")){
			System.err.printf("bad input");
		}else{	
			System.err.printf("%s thanks for visiting !", name);
		}
	}
*/
	
	public void Good(HttpServletRequest request, HttpServletResponse response)
	{
		/* FIX */
		String dataName = request.getParameter("name");
		if(-1 != dataName.indexOf("/")){
			System.err.printf("bad input");
		}else{
			System.err.printf("%s thanks for visiting !", name);
		}
	}
}