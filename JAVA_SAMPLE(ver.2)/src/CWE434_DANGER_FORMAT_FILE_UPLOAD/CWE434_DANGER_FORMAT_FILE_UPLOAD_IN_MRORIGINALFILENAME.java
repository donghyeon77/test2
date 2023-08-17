package CWE434_DANGER_FORMAT_FILE_UPLOAD;

import java.io.File;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import com.oreilly.servlet.MultipartRequest;

public class CWE434_DANGER_FORMAT_FILE_UPLOAD_IN_MRORIGINALFILENAME
{
	public void bad(HttpServletRequest request)
	{
		int maxPostSize = 1024;
        String saveDirectory = "/cwe434/upload";
		String fileData = request.getParameter("CWE434");
		//...
		MultipartRequest multi = new MultipartRequest(request, saveDirectory, maxPostSize, "UTF-8");
		/* FLAW */
		String oriFileName = multi.getOriginalFileName(fileData);
		//...
	}

/* ACA hold
	public void ACA(HttpServletRequest request)
	{
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
		String next = (String)multipartRequest.getFileNames().next();
		MultipartFile file = multipartRequest.getFile(next);
		
		String fileName = file.getOriginalFilename();
		File uploadFile = new File("/app/webapp/data/upload/notice", fileName);
	}
*/
	public void good(HttpServletRequest request)
	{
		int maxPostSize = 1024;
        String saveDirectory = "/cwe434/upload";
		String fileData = "CWE434";
		//...
		MultipartRequest multi = new MultipartRequest(request, saveDirectory, maxPostSize, "UTF-8");
		/* FLAW */
		String oriFileName = multi.getOriginalFileName(fileData);
		//...
	}
}