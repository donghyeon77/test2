package CWE134_INSERT_FORMAT_STRING;

import javax.servlet.ServletRequest;

/*
 * ������
 * �ֿ���� :: CWE134_InsertFormatString - ���� ��Ʈ�� ����
 */

public class CWE134_INSERT_FORMAT_STRING_FORMAT
{	
	public void bad_Format(ServletRequest request)
	{
		String data = request.getParameter("data");
		/* FLAW */
		System.out.format(data);
	}
/* ACA hold
	public void ACA_Format(ServletRequest request)
	{
		String data = request.getParameter("data");
		
		System.out.format(data);
	}
*/
	public void good_Format(ServletRequest request)
	{
		String data = request.getParameter("data");
		/* FIX */
		System.out.format("%s",data);
	}
	
	public void bad_Print(ServletRequest request)
	{
		String data = request.getParameter("data");
		/* FLAW */
		System.out.printf(data);
	}
/* 
	public void ACA_Print(ServletRequest request)
	{
		String data = request.getParameter("data");
		
		System.out.printf(data);
	}
*/
	public void good_Print(ServletRequest request)
	{
		String data = request.getParameter("data");
		/* FIX */
		System.out.printf("%s",data);
	}
}