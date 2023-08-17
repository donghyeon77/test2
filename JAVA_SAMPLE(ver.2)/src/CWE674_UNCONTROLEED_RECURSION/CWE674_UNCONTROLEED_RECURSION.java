package CWE674_UNCONTROLEED_RECURSION;

import java.io.File;
import java.io.IOException;

/*
 * 검출목록
 * 주요검출 :: CWE674_RecursiveFunctionNotUsingControlStatement - 종료되지 않는 반복문 또는 재귀 함수
 */

// 종료되지 않는 반복문 또는 재귀 함수
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