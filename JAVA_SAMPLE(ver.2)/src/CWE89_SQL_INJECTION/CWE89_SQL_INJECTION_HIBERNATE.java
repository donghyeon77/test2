package CWE89_SQL_INJECTION;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.StatelessSession;

/*
 * 검출목록
 * 주요검출 :: CWE89_InsertSQL - SQL삽입
 */

// SQL 삽입
public class CWE89_SQL_INJECTION_HIBERNATE
{
	public void bad_Aliases(StatelessSession session)
	{	
		Properties props = new Properties();
		String tableName = props.getProperty("jdbc.tableName");
		String name = props.getProperty("jdbc.name");
		Query query = session.createQuery("SELECT * FROM " + tableName + "WHERE Name = " + name);
		/* FLAW */
		String[] result = query.getReturnAliases();
	}

/*	
// import com.hsecure.xwm.aca.utils.*;

	public void ACA(StatelessSession session)
	{	
		Properties props = new Properties();
// ${hsAcaTarget.lineSplitMid} : props.getProperty("jdbc.tableName")
// ${hsAcaTarget.lineSplitMid} : props.getProperty("jdbc.name")
// HsSqlEncoder.encode( ${hsAcaTarget.lineSplitMid} )
		
		String tableName = HsSqlEncoder.encode( props.getProperty("jdbc.tableName") );
		String name = HsSqlEncoder.encode( props.getProperty("jdbc.name") );
		Query query = session.createQuery("SELECT * FROM " + tableName + "WHERE Name = " + name);
		String[] result = query.getReturnAliases();
	}
*/	
	
	public void good_Aliases(StatelessSession session)
	{	
		Properties props = new Properties();
		String tableName = props.getProperty("jdbc.tableName");
		String name = props.getProperty("jdbc.name");
		/* FIX */
		Query query = session.createQuery("SELECT * FROM ? WHERE Name = ?").setParameter(1, tableName).setParameter(2, name);
		String[] result = query.getReturnAliases();		
	}
	
	public void bad_ExecuteUpdate(StatelessSession session)
	{	
		Properties props = new Properties();
		String tableName = props.getProperty("jdbc.tableName"); 
		String name = props.getProperty("jdbc.name");
		Query query = session.createQuery("SELECT * FROM " + tableName + "WHERE Name = " + name);
		/* FLAW */
		int result = query.executeUpdate();
	}
	
/*
// import import org.owasp.esapi.*;
// import org.owasp.esapi.codecs.*;

	public void ACA(StatelessSession session)
	{
		Properties props = new Properties();

	// ${hsAcaTarget.lineSplitMid} : props.getProperty("jdbc.tableName")
	// ${hsAcaTarget.lineSplitMid} : props.getProperty("jdbc.name")
	// ESAPI.encoder().encodeForSQL( new OracleCodec(), ${hsAcaTarget.lineSplitMid} )

		String tableName = ESAPI.encoder().encodeForSQL( new OracleCodec(), props.getProperty("jdbc.tableName") ); 
		String name = ESAPI.encoder().encodeForSQL( new OracleCodec(), props.getProperty("jdbc.name") );
		Query query = session.createQuery("SELECT * FROM " + tableName + "WHERE Name = " + name);
		
		int result = query.executeUpdate();
	}
*/
	
	public void good_ExecuteUpdate(StatelessSession session)
	{	
		Properties props = new Properties();
		String tableName = props.getProperty("jdbc.tableName");
		String name = props.getProperty("jdbc.name");
		/* FIX */
		Query query = session.createQuery("SELECT * FROM ? WHERE Name = ?").setParameter(1, tableName).setParameter(2, name);
		int result = query.executeUpdate();		
	}
	
	public void bad_Iterate(Session session)
	{	
		Properties props = new Properties();
		String tableName = props.getProperty("jdbc.tableName");
		String name = props.getProperty("jdbc.name");
		Query query = session.createSQLQuery("SELECT * FROM " + tableName + "WHERE Name = " + name);
		/* FLAW */
		Iterator result = query.iterate();
	}
	
/*
// import import org.owasp.esapi.*;
// import org.owasp.esapi.codecs.*;

	public void ACA(Session session)
	{	
		Properties props = new Properties();
		
	// ${hsAcaTarget.lineSplitMid} : props.getProperty("jdbc.tableName")
	// ${hsAcaTarget.lineSplitMid} : props.getProperty("jdbc.name")
	// ESAPI.encoder().encodeForSQL( new OracleCodec(), ${hsAcaTarget.lineSplitMid} )
		
		String tableName = ESAPI.encoder().encodeForSQL( new OracleCodec(), props.getProperty("jdbc.tableName"));
		String name = ESAPI.encoder().encodeForSQL( new OracleCodec(), props.getProperty("jdbc.name"));
		Query query = session.createSQLQuery("SELECT * FROM " + tableName + "WHERE Name = " + name);
		
		Iterator result = query.iterate();
	}
*/
	 
	public void good_Iterate(Session session)
	{	
		Properties props = new Properties();
		String tableName = props.getProperty("jdbc.tableName");
		String name = props.getProperty("jdbc.name");
		/* FIX */
		Query query = session.createSQLQuery("SELECT * FROM ? WHERE Name = ?").setParameter(1, tableName).setParameter(2, name);
		Iterator result = query.iterate();		
	}
	
	public void bad_List(Session session)
	{	
		Properties props = new Properties();
		String tableName = props.getProperty("jdbc.tableName");
		String name = props.getProperty("jdbc.name");
		Query query = session.createQuery("SELECT * FROM " + tableName + "WHERE Name = " + name);
		/* FLAW */
		List result = query.list();
	}

/* 
// import import org.owasp.esapi.*;
// import org.owasp.esapi.codecs.*;

	public void ACA(Session session)
	{	
		Properties props = new Properties();
		
	// ${hsAcaTarget.lineSplitMid} : props.getProperty("jdbc.tableName")
	// ${hsAcaTarget.lineSplitMid} : props.getProperty("jdbc.name")
	// ESAPI.encoder().encodeForSQL( new OracleCodec(), ${hsAcaTarget.lineSplitMid} )
		
		String tableName = ESAPI.encoder().encodeForSQL( new OracleCodec(), props.getProperty("jdbc.tableName") );
		String name = ESAPI.encoder().encodeForSQL( new OracleCodec(), props.getProperty("jdbc.name") );
		Query query = session.createQuery("SELECT * FROM " + tableName + "WHERE Name = " + name);
		
		List result = query.list();
	}
*/
	
	public void good_List(Session session)
	{	
		Properties props = new Properties();
		String tableName = props.getProperty("jdbc.tableName");
		String name = props.getProperty("jdbc.name");
		/* FIX */
		Query query = session.createQuery("SELECT * FROM ? WHERE Name = ?").setParameter(1, tableName).setParameter(2, name);
		List result = query.list();		
	}
	
	public void bad_Scroll(StatelessSession session)
	{	
		Properties props = new Properties();
		String tableName = props.getProperty("jdbc.tableName");
		String name = props.getProperty("jdbc.name");
		Query query = session.createSQLQuery("SELECT * FROM " + tableName + "WHERE Name = " + name);
		/* FLAW */
		ScrollableResults result = query.scroll();
	}
/*
// import import org.owasp.esapi.*;
// import org.owasp.esapi.codecs.*;

	public void ACA(StatelessSession session)
	{
		Properties props = new Properties();
		
	// ${hsAcaTarget.lineSplitMid} : props.getProperty("jdbc.tableName")
	// ${hsAcaTarget.lineSplitMid} : props.getProperty("jdbc.name")
	// ESAPI.encoder().encodeForSQL( new OracleCodec(), ${hsAcaTarget.lineSplitMid} )
		
		String tableName = ESAPI.encoder().encodeForSQL( new OracleCodec(), props.getProperty("jdbc.tableName") );
		String name = ESAPI.encoder().encodeForSQL( new OracleCodec(), props.getProperty("jdbc.name") );
		Query query = session.createSQLQuery("SELECT * FROM " + tableName + "WHERE Name = " + name);
		
		ScrollableResults result = query.scroll();
	}
*/
	
	public void good_Scroll(StatelessSession session)
	{	
		Properties props = new Properties();
		String tableName = props.getProperty("jdbc.tableName");
		String name = props.getProperty("jdbc.name");
		/* FIX */
		Query query = session.createSQLQuery("SELECT * FROM ? WHERE Name = ?").setParameter(1, tableName).setParameter(2, name);
		ScrollableResults result = query.scroll();		
	}
}
