package CWE190_INTEGER_OVERFLOW;

/*
 * 검출목록
 * 주요검출 :: CWE190_ArrayIndexIntegerOverflow - 정수 오버플로우
 */

// 정수 오버플로우
public class CWE190_ARRAY_INTEGER_OVERFLOW
{
	public void Bad()
	{
		String[] num = new String[]{"abc", "ccc"};
		
		String n_size = System.getProperty( "valueSize" );
		int size = 0;
		if(n_size != null){
			size = new Integer(n_size).intValue();
		}
		
		/* FLAW */
		String temp = num[size];
	}
	
/* 
	public void ACA()
	{
		String[] num = new String[]{"abc", "ccc"};
		
		String n_size = System.getProperty( "valueSize" );
		int size = 0;
		if(n_size != null){
			size = new Integer(n_size).intValue();
		}
		
// ACA position : cause top , agru_last value
// ${hsAcaTarget.lineSplitMid} : size
// if(${hsAcaTarget.lineSplitMid} < 0){ throw new RuntimeException(); }

		if(size < 0){
		throw new RuntimeException(); }
	
		String temp = num[size];
	}
*/
	
	public void Good() throws Exception
	{
		String[] num = new String[]{"abc", "ccc"};
		
		String n_size = System.getProperty( "valueSize" );
		int size = 0;
		if(n_size != null){
			size = new Integer(n_size).intValue();
		}
		
		/* FIX */
		if(size < 0)
		{
			throw new Exception();
		}
		String temp = num[size];
	}
	
	public void Good2()
	{
		String[] num = new String[]{"abc", "ccc"};
		
		String n_size = System.getProperty( "valueSize" );
		int size = 0;
		if(n_size != null){
			size = new Integer(n_size).intValue();
		}
		
		/* FIX */
		if(size < 0)
		{
			return;
		}
		String temp = num[size];
	}
}