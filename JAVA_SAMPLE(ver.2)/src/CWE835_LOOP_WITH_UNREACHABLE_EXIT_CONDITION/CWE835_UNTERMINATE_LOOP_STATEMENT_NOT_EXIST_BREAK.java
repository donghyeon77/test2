package CWE835_LOOP_WITH_UNREACHABLE_EXIT_CONDITION;

/*
 * 검출목록
 * 주요검출 :: CWE835_UnterminatedLoopStatement - 종료되지 않는 반복문 또는 재귀 함수
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
