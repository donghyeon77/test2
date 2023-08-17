package CWE190_INTEGER_OVERFLOW;

/*
 * ������
 * �ֿ���� :: CWE190_ArrayIndexIntegerOverflow - ���� �����÷ο�
 */

// ���� �����÷ο�
public class CWE190_CAST_INTEGER_OVERFLOW
{
	public void Bad()
	{
		double data = Double.MIN_VALUE;
		String stringNumber = System.getenv("ADD");
        if (stringNumber != null)
        {
            try
            {
                data = Double.parseDouble(stringNumber.trim());
            }
            catch(NumberFormatException exceptNumberFormat)
            {
                System.out.println("exceptNumberFormat Message");
            }
        }
        /* FLAW */
        double result = (double)(data + 1);
	}

/* 
	public void ACA()
	{
		double data = Double.MIN_VALUE;
		String stringNumber = System.getenv("ADD");
        if (stringNumber != null)
        {
            try
            {
                data = Double.parseDouble(stringNumber.trim());
            }
            catch(NumberFormatException exceptNumberFormat)
            {
                System.out.println("exceptNumberFormat Message");
            }
        }

// ACA position : result top
// ${hsAcaTarget.lineSplitMid} : data
// if(${hsAcaTarget.lineSplitMid} < Double.MAX_VALUE){ throw new RuntimeException(); }

		if(data < Double.MAX_VALUE){ throw new RuntimeException(); }

        double result = (double)(data + 1);
	}
 */
	
	public void Good()
	{
		double data = Double.MIN_VALUE;
		String stringNumber = System.getenv("ADD");
        if (stringNumber != null)
        {
            try
            {
                data = Double.parseDouble(stringNumber.trim());
            }
            catch(NumberFormatException exceptNumberFormat)
            {
                System.out.println("exceptNumberFormat Message");
            }
        }
        /* FIX */
		if ( ( data + 1 ) < Double.MAX_VALUE)
        {
			double result = (double)(data + 1);   
        }
	}
}