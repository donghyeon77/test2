package CWE256_CLEARTEXT_STORAGE_OF_SENSITIVE_INFORMATION;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import Decoder.BASE64Decoder;

/*
 * 검출목록
 * 주요검출 :: CWE256_SavePasswordwithPlainText - 중요정보 평문저장 
 * 부가검출 :: CWE321_하드코드된 암호화 키
 */

// 중요정보 평문저장
public class CWE256_SAVE_PLAINTEXT_IN_GET_CONNECTION
{
	public void Bad(String[] arg)
	{
		String dbUrl = "jdbc:oracle:thin:@255.255.255.255:1521:orcl";
		String dbUser = "5dol";
		Connection conn = null;
		FileInputStream input = null;

		try
		{
			input = new FileInputStream(new File("src"));
			byte[] pass = new byte[8];
			input.read(pass);
			/* FLAW */
			conn = DriverManager.getConnection(dbUrl, dbUser, new String(pass));
		}
		catch(IOException e)
		{
			System.out.println("IOException Message");
		}
		catch(SQLException e)
		{
			System.out.println("SQLException Message");
		}
		finally
		{
			if(input != null) try{ input.close(); }catch ( IOException e ){System.out.println( "IOException Occured!!" );}
			if(conn != null) try{ conn.close(); }catch ( SQLException e ){System.out.println( "SQLException Occured!!" );}
		}
	}

/* ACA hold
	public void ACA(String[] arg)
	{
		String dbUrl = "jdbc:oracle:thin:@255.255.255.255:1521:orcl";
		String dbUser = "5dol";
		Connection conn = null;
		FileInputStream input = null;

		try
		{
			input = new FileInputStream(new File("src"));
			byte[] pass = new byte[8];
			input.read(pass);
			
			conn = DriverManager.getConnection(dbUrl, dbUser, new String(pass));
		}
		catch(IOException e)
		{
			System.out.println("IOException Message");
		}
		catch(SQLException e)
		{
			System.out.println("SQLException Message");
		}
		finally
		{
			if(input != null) try{ input.close(); }catch ( IOException e ){System.out.println( "IOException Occured!!" );}
			if(conn != null) try{ conn.close(); }catch ( SQLException e ){System.out.println( "SQLException Occured!!" );}
		}
	}
*/
	
	public void Good(String[] arg)
	{
		Properties props = new Properties();
		String dbUrl = "jdbc:oracle:thin:@255.255.255.255:1521:orcl";
		String dbUser = "5dol";
		Connection conn = null;

		try
		{
			/* FIX */
			String pass = decrypt(props.getProperty("passwd"));
			conn = DriverManager.getConnection(dbUrl, dbUser, pass);
		}
		catch(SQLException e)
		{
			System.out.println("SQLException");
		}
		finally
		{	
			if(conn != null) try{ conn.close(); }catch ( SQLException e ){System.out.println( "SQLException Occured!!" );}
		}
	}
	
	 public static String decrypt(String strVal)
	 {
        if (strVal == null || strVal.length() == 0)
            return "";
        
        Cipher cipher = null;
        byte[] inputBytes1 = null;
        byte[] outputBytes2 = null;
        String strResult = null;
        
		try
		{
			BASE64Decoder decoder = new BASE64Decoder();
			cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			Key key = new SecretKeySpec("this is the Key Sentense!!".getBytes(), 0, "this is the Key Sentense!!".length(), "AES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			
			inputBytes1 = decoder.decodeBuffer(strVal);
			outputBytes2 = cipher.doFinal(inputBytes1);
			strResult = new String(outputBytes2, "UTF-8");
		}
        catch ( NoSuchAlgorithmException e )
        {
			System.out.println( "NoSuchAlgorithmException Occured!!" );
		}
        catch ( NoSuchPaddingException e )
		{
			System.out.println( "NoSuchPaddingException Occured!!" );
		}
        catch ( InvalidKeyException e )
        {
			System.out.println( "InvalidKeyException Occured!!" );
		}
		catch ( IllegalBlockSizeException e )
		{
			System.out.println( "IllegalBlockSizeException Occured!!" );
		}
		catch ( BadPaddingException e )
		{
			System.out.println( "BadPaddingException Occured!!" );
		}
		catch ( IOException e )
		{
			System.out.println( "IOException Occured!!" );
		}
		
        return strResult;
    }
}