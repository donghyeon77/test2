package CWE495_PUBLIC_RETURN_METHOD_IN_PRIVATE_VARIABLE;

/*
 * ������
 * �ֿ���� :: CWE495_publicMethodreturnprivateArray - Public �޼���κ��� ��ȯ�� private �迭
 */

// Public �޼���κ��� ��ȯ�� private �迭
public class CWE495_PUBLIC_RETURN_METHOD_IN_PRIVATE_VARIABLE
{
	private String[] colors;
	
	public CWE495_PUBLIC_RETURN_METHOD_IN_PRIVATE_VARIABLE(){
		colors = new String[]{"RED","BLUE","BLACK","YELLOW"};
	}
	
	public String[] Bad()
	{		
		/* FLAW */
		return this.colors;
	}

/* ACA hold 
	public String[] ACA(String[] args)
	{
		colors[0] = "abc";
		String[]colors = new String[]{"aaa", "bbb"};
//${hsAcaTarget.lineSplitMid} : this.colors
//
		return this.colors;
	}
*/
	
	public String[] Good()
	{	
		String[]returnColors;
		int colorsSize = this.colors.length;
		
		/* FIX */
		if(colorsSize > 0){
			returnColors = new String[colorsSize];			
			for(int i=0;i<colorsSize;i++){
				returnColors[i] = this.colors[i];
			}			
			return returnColors;
		}else{
			return null;
		}
	}
}