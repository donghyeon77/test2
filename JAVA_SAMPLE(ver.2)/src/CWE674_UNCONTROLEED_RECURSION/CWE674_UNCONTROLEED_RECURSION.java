package CWE674_UNCONTROLEED_RECURSION;

import java.io.File;
import java.io.IOException;

/*
 * ������
 * �ֿ���� :: CWE674_RecursiveFunctionNotUsingControlStatement - ������� �ʴ� �ݺ��� �Ǵ� ��� �Լ�
 */

// ������� �ʴ� �ݺ��� �Ǵ� ��� �Լ�
public class CWE674_UNCONTROLEED_RECURSION
{
	public int Bad(int n)
	{
		/* FLAW */
		return n * Bad(n-1);
	}

/* ACA hold
	public int Bad(int n)
	{
//${hsAcaTarget.lineSplitMid} : 
//
		return n * Bad(n-1);
	}
 */
	
	public int Good(int n)
	{
		/* FIX */
		if(n <= 0)
		{
			return 1;
		}
		return n * Good(n-1);
	}
}