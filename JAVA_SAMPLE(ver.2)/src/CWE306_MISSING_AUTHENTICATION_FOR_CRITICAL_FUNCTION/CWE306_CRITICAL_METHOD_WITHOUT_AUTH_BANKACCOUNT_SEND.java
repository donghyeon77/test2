package CWE306_MISSING_AUTHENTICATION_FOR_CRITICAL_FUNCTION;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CWE306_CRITICAL_METHOD_WITHOUT_AUTH_BACKACCOUNT_SEND {
	String m_strAccountNumber = new String();
	String m_strPerson = new String();
	double m_fBalanse = 0;
	public void Bad(String accountNumber, String toPerson, double balance)
	{
		/* FLAW */
		BankAccount account = new BankAccount();
		account.setAccountNumber(accountNumber);
		account.setToPerson(toPerson);
		account.setBalance(balance);
		account.send(this);
	}

/* ACA hold
	public void ACA(String accountNumber, String toPerson, double balance)
	{
		
		BankAccount account = new BankAccount();
		account.setAccountNumber(accountNumber);
		account.setToPerson(toPerson);
		account.setBalance(balance);
		account.send(this);
	}
*/
	
	@SuppressWarnings("deprecation")
	public void Good(HttpServletRequest request, HttpSession session, String accountNumber, String toPerson, double balance) throws Exception
	{
	   //�������� ���� �˾� ȭ���� ���� ������� credential�� �޴´�.
	   String newUserName = request.getParameter("username");
	   String newPassword = request.getParameter("password");
	   if ( newUserName == null || newPassword == null )
	   {
		   throw new Exception("������ ����:");
	   }

	   //�������κ��� �α��� ������� credential�� �д´�.
	   String password = (String) session.getValue("password");
	   String userName = (String) session.getValue("username");

	   //�������� ���ؼ� ��ü���θ� �Ǵ��Ѵ�.
	   /* FIX */
	   if (isAuthenticatedUser() && newUserName.equals(userName) && newPassword.equals(password))
	   {
	      BankAccount account = new BankAccount();
	      account.setAccountNumber(accountNumber);
	      account.setToPerson(toPerson);
	      account.setBalance(balance);
	      account.send(this);
	   }
	}
	
	boolean isAuthenticatedUser()
	{
		return true;
	}
	
	class BankAccount
	{
		String m_strAccountNumber;
		String m_strPerson;
		double m_fBalanse;

		void setAccountNumber(String accountNumber)
		{
			m_strAccountNumber = accountNumber;
		}
		void setToPerson(String person)
		{
			m_strPerson = person;
		}
		void setBalance(double balance)
		{
			m_fBalanse = balance;
		}
		void send(Object test)
		{
			System.out.println( test );
		}		
	}
}
