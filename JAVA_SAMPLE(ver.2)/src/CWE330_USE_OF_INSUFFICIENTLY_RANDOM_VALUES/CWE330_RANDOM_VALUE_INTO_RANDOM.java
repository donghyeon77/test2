package CWE330_USE_OF_INSUFFICIENTLY_RANDOM_VALUES;

import java.util.Random;
import java.security.SecureRandom;

public class CWE330_RANDOM_VALUE_INTO_RANDOM
{
	public void bad_SetSeed()
	{
		Random random = new Random();
		random.setSeed(100); //고정된 SEED 사용
		/* FLAW */
		random.nextInt();
	}

/* ACA hold
	public void ACA_SetSeed()
	{

		Random random = new Random());
		random.setSeed(100);
		
//SecureRandom rand = new SecureRandom()
//${hsAcaTarget.lineSplitMid} : random.nextInt()
//rand.nextInt();

		SecureRandom rand = new SecureRandom()
		rand.nextInt();
	}
*/
	
	public void good_SetSeed()
	{
		/* FIX */
		SecureRandom rand = new SecureRandom();
		rand.nextInt();
	}
	
	public int bad_Random()
	{
		Random random = new Random();
		/* FLAW */
		return random.nextInt();
	}
	
/* ACA hold
	public int ACA_Random()
	{
		Random random = new Random();

//SecureRandom rand = new SecureRandom()
//${hsAcaTarget.lineSplitMid} : random.nextInt()
//rand.nextInt();

		SecureRandom rand = new SecureRandom()
		return rand.nextInt();
	}
*/
	
	public int good_Random()
	{
		/* FIX */
		SecureRandom rand = new SecureRandom();
		return (rand.nextInt()%6)+1;
	}
	
	public void bad_MathRandom()
	{
		/* FLAW */		
		Math.random();
	}
	
	public void good_MathRandom()
	{
		/* FIX */
		SecureRandom rand = new SecureRandom();
		rand.nextInt();
	}
}