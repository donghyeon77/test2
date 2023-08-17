package CWE494_DOWNLOAD_OF_CODE_WITHOUT_INTEGRITY_CHECK;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class CWE494_DOWNLOAD_WITHOUT_ITEGRITY_CHECK_URLCLASSLOADER
{
	public void Bad(String[] args)
	{
		String data;

		data = "";
		
		File file = new File("C:/data.txt");
		FileInputStream streamFileInput = null;
		InputStreamReader readerInputStream = null;
		BufferedReader readerBuffered = null;

		try
		{
			streamFileInput = new FileInputStream(file);
			readerInputStream = new InputStreamReader(streamFileInput, "UTF-8");
			readerBuffered = new BufferedReader(readerInputStream);

			data = readerBuffered.readLine();
		}
		catch (IOException exceptIO)
		{
			System.out.println( "Error with stream reading" );
		}
		finally
		{
			try
			{
				if (readerBuffered != null)
				{
					readerBuffered.close();
				}
			}
			catch (IOException exceptIO)
			{
				System.out.println( "Error closing BufferedReader" );
			}

			try
			{
				if (readerInputStream != null)
				{
					readerInputStream.close();
				}
			}
			catch (IOException exceptIO)
			{
				System.out.println( "Error closing InputStreamReader" );
			}

			try
			{
				if (streamFileInput != null)
				{
					streamFileInput.close();
				}
			}
			catch (IOException exceptIO)
			{
				System.out.println( "Error closing FileInputStream" );
			}
		}
        
		
		URL[] classURL = null;
		try
		{
			classURL = new URL[]{
					new URL(data)
			};
			/* FLAW */
			URLClassLoader loader = new URLClassLoader(classURL);
			Class loadedClass = Class.forName("LoadMe", true, loader);
		}
		catch ( MalformedURLException e )
		{
			System.out.println( "MalformedURLException Occured!!" );
		}
		catch ( ClassNotFoundException e ) {
			System.out.println( "ClassNotFoundException Occured!!" );
		}
	}
	public void Good(String[] args)
	{
		String data;

        data = "";

		File file = new File("C:/data.txt");
		FileInputStream streamFileInput = null;
		InputStreamReader readerInputStream = null;
		BufferedReader readerBuffered = null;

		try
		{
			streamFileInput = new FileInputStream(file);
			readerInputStream = new InputStreamReader(streamFileInput, "UTF-8");
			readerBuffered = new BufferedReader(readerInputStream);

			data = readerBuffered.readLine();
		}
		catch (IOException exceptIO)
		{
			System.out.println( "Error with stream reading" );
		}
		finally
		{
			try
			{
				if (readerBuffered != null)
				{
					readerBuffered.close();
				}
			}
			catch (IOException exceptIO)
			{
				System.out.println( "Error closing BufferedReader" );
			}

			try
			{
				if (readerInputStream != null)
				{
					readerInputStream.close();
				}
			}
			catch (IOException exceptIO)
			{
				System.out.println( "Error closing InputStreamReader" );
			}

			try
			{
				if (streamFileInput != null)
				{
					streamFileInput.close();
				}
			}
			catch (IOException exceptIO)
			{
				System.out.println( "Error closing FileInputStream" );
			}
		}

		
        URL[] classURL = null;
		try
		{
			/* FIX */
			if( checksum(data) == true )
			{
				/* FLAW */
				classURL = new URL[]{
						new URL(data)
				};
				URLClassLoader loader = new URLClassLoader(classURL);
				Class loadedClass = Class.forName("LoadMe", true, loader);
			}
		}
		catch ( MalformedURLException e )
		{
			System.out.println( "MalformedURLException Occured!!" );
		}
		catch ( ClassNotFoundException e )
		{
			System.out.println( "ClassNotFoundException Occured!!" );
		}
	}
}