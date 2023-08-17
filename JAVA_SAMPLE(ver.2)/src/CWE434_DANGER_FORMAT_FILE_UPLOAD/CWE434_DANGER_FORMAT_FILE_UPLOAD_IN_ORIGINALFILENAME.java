package CWE434_DANGER_FORMAT_FILE_UPLOAD;

import java.io.File;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class CWE434_DANGER_FORMAT_FILE_UPLOAD_IN_ORIGINALFILENAME
{
	public void Bad(HttpServletRequest request)
	{
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
		String next = (String)multipartRequest.getFileNames().next();
		MultipartFile file = multipartRequest.getFile(next);
		/* FLAW */
		String fileName = file.getOriginalFilename();
		File uploadFile = new File("/app/webapp/data/upload/notice", fileName);
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
	public void Good(HttpServletRequest request)
	{
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
		String fileName = (String)multipartRequest.getFileNames().next();
		if( fileName != null )
		{
			/* FIX */
			if( fileName.endsWith(".doc") || fileName.endsWith(".hwp") || fileName.endsWith(".pdf") || fileName.endsWith(".xls"))
			{
				MultipartFile file = multipartRequest.getFile( fileName );
				String oriFileName = file.getOriginalFilename();
				File uploadFile = new File("/app/webapp/data/upload/notice", oriFileName);
			}
		}
	}
}