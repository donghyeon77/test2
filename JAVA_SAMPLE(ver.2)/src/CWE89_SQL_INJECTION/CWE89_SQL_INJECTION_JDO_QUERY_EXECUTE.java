package CWE89_SQL_INJECTION;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/*
 * 검출목록
 * 주요검출 :: CWE89_InsertSQL - SQL삽입
 */

// SQL 삽입
public class CWE89_SQL_INJECTION_JDO_QUERY_EXECUTE
{
	public void Bad(PersistenceManager pm)
	{	 
		Properties props = new Properties();
		String tableName = props.getProperty("jdbc.tableName");
		String name = props.getProperty("jdbc.name");
		Query query = pm.newQuery("SELECT * FROM " + tableName + "WHERE Name = " + name);
		List results = (List) query.execute();
	}

/*
// import com.hsecure.xwm.aca.utils.*;

	public void ACA(PersistenceManager pm)
	{	 
		Properties props = new Properties();
		
// ${hsAcaTarget.lineSplitMid} : props.getProperty("jdbc.tableName")
// ${hsAcaTarget.lineSplitMid} : props.getProperty("jdbc.name")
// HsSqlEncoder.encode( ${hsAcaTarget.lineSplitMid} )
		
		String tableName = HsSqlEncoder.encode( props.getProperty("jdbc.tableName") );
		String name = HsSqlEncoder.encode( props.getProperty("jdbc.name") );
		Query query = pm.newQuery("SELECT * FROM " + tableName + "WHERE Name = " + name);
		List results = (List) query.execute();
	}
 */
	
	public void Good(PersistenceManager pm)
	{
		Properties props = new Properties();
		String tableName = props.getProperty("jdbc.tableName");
		String name = props.getProperty("jdbc.name");
		/* FIX */
		Query query = pm.newQuery("SELECT * FROM ? WHERE Name = ?");
		List results = (List) query.execute(tableName, name);
	}
}
