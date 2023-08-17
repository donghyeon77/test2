package CWE496_PUBLIC_ARGU_METHOD_IN_PRIVATE_VARIABLE;
/*
 * ������
 * �ֿ���� :: CWE496_AllocPublicToPrivateArray - Private �迭�� public ������ �Ҵ�
 */

// Private �迭�� public ������ �Ҵ�
public class CWE496_PUBLIC_ARGU_METHOD_IN_PRIVATE_VARIABLE
{
	private String[] userRoles;
	public void Bad(String[] userRoles)
	{
		userRoles[0] = "abc";
		/* FLAW : assign public information in private member variable */
		this.userRoles = userRoles;
	}
	
/* ACA hold
	public void ACA(String[] userRoles)
	{
		userRoles[0] = "abc";
		
		this.userRoles = userRoles;
	}
*/
	
	public void Good(String[] userRoles)
	{
		/* FIX */
		this.userRoles = new String[userRoles.length];
		for( int i = 0; i < userRoles.length; i++ )
		{
			this.userRoles[i] = userRoles[i];
		}
	}
}