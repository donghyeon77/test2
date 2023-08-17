package CWE190_INTEGER_OVERFLOW;

/*
 * 검출목록
 * 주요검출 :: CWE190_UnsignedAllocFuncIntegerOverflow - 정수 오버플로우
 */

// 정수 오버플로우
public class CWE190_METHOD_ARRAY_INTEGER_OVERFLOW
{
	public void Bad()
	{
		String n_size = System.getProperty( "valueSize" );
		int size = 0;
		if(n_size != null){
			size = new Integer(n_size).intValue();
		}
		
		/* FLAW */
		Object[] data = new Object[size];
	}
	
/* 
	public void ACA()
	{
		
		String n_size = System.getProperty( "valueSize" );
		int size = 0;
		if(n_size != null){
			size = new Integer(n_size).intValue();
		}
		
// ACA position : result top
// ${hsAcaTarget.lineSplitMid} : size
// if(${hsAcaTarget.lineSplitMid} < 0){ throw new RuntimeException(); }

	if(size < 0){
	throw new RuntimeException(); }

		Object[] data = new Object[size];
	}
*/
	
	public void Good() throws Exception
	{
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
		Object[] data = new Object[size];
	}
	
	public void Good2()
	{
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
		Object[] data = new Object[size];
	}
}