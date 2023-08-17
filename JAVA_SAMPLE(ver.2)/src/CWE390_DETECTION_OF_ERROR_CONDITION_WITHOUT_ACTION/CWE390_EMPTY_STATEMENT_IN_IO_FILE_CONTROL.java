package CWE390_DETECTION_OF_ERROR_CONDITION_WITHOUT_ACTION;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

// 오류상황 대응 부재
public class CWE390_EMPTY_STATEMENT_IN_IO_FILE_CONTROL
{}
	public void bad() {
		File f = new File("test1.txt");
		if( !f.canRead() )
		{
			/* FLAW */
			//...
		}
	}
	
	public void good() {
		File f = new File("test1.txt");
		if( !f.canRead() )
		{
			/* FIX */
			System.out.println("File CAN'T READ");
			return;
		}
		//...
	}
}