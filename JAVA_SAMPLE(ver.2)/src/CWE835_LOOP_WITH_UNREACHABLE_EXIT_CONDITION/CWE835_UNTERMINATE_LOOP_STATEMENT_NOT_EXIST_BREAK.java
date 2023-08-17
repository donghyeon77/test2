package CWE835_LOOP_WITH_UNREACHABLE_EXIT_CONDITION;

/*
 * ������
 * �ֿ���� :: CWE835_UnterminatedLoopStatement - ������� �ʴ� �ݺ��� �Ǵ� ��� �Լ�
 */

public class CWE835_UNTERMINATE_LOOP_STATEMENT_NOT_EXIST_BREAK {
	public void Bad( int n )
	{
		/* FLAW */
		while( true )
		{
			System.out.println( "Unlimited Loop" );
		}
	}

/*  ACA hold 
	public void ACA( int n )
	{
		
		while( true )
		{
			System.out.println( "Unlimited Loop" );
		}
	}
*/
    
	public void Good( int n ) 
	{
		while( true )
		{
			/* FIX */
			if( n > 10 )
			{
				System.out.println( "Not Unlimited Loop" );
				break;
			}
			n++;
		}
	}
}
