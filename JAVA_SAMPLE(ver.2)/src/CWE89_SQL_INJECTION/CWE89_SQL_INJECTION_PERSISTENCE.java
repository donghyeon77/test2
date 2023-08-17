package CWE89_SQL_INJECTION;

import java.util.List;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/*
 * 검출목록
 * 주요검출 :: CWE89_InsertSQL - SQL삽입
 */

// SQL 삽입
public class CWE89_SQL_INJECTION_PERSISTENCE
{
	public void bad_ExecuteUpdate(EntityManager em)
	{
		Properties props = new Properties();
		String tableName = props.getProperty("jdbc.tableName");
		String name = props.getProperty("jdbc.name");
		Query query = em.createNativeQuery("SELECT * FROM " + tableName + "WHERE Name = " + name);
		/* FLAW */
		query.executeUpdate();
	}
	
	
/*
// import com.hsecure.xwm.aca.utils.*;

	public void ACA(EntityManager em)
	{
		Properties props = new Properties();
		
// ${hsAcaTarget.lineSplitMid} : props.getProperty("jdbc.tableName")
// ${hsAcaTarget.lineSplitMid} : props.getProperty("jdbc.name")
// HsSqlEncoder.encode( ${hsAcaTarget.lineSplitMid} )
		
		String tableName = HsSqlEncoder.encode( props.getProperty("jdbc.tableName") );
		String name = HsSqlEncoder.encode( props.getProperty("jdbc.name") );
		Query query = em.createNativeQuery("SELECT * FROM " + tableName + "WHERE Name = " + name);
		
		query.executeUpdate();
	}
 */

	public void good_ExecuteUpdate(EntityManager em)
	{
		Properties props = new Properties();
		String tableName = props.getProperty("jdbc.tableName");
		String name = props.getProperty("jdbc.name");
		/* FIX */
		Query query = em.createNativeQuery("SELECT * FROM  ? WHERE Name = ?");		
		query.setParameter(1, tableName).setParameter(2, name);
		query.executeUpdate();
	}
	
	public void bad_ResultList(EntityManager em)
	{
		Properties props = new Properties();
		String tableName = props.getProperty("jdbc.tableName");
		String name = props.getProperty("jdbc.name");
		Query query = em.createNativeQuery("SELECT * FROM " + tableName + "WHERE Name = " + name);
		/* FLAW */
		List<String> list = query.getResultList();
	}
	
/*
// import import org.owasp.esapi.*;
// import org.owasp.esapi.codecs.*;

	public void ACA(EntityManager em)
	{
		Properties props = new Properties();
		
// ${hsAcaTarget.lineSplitMid} : props.getProperty("jdbc.tableName")
// ${hsAcaTarget.lineSplitMid} : props.getProperty("jdbc.name")
// ESAPI.encoder().encodeForSQL( new OracleCodec(), ${hsAcaTarget.lineSplitMid} )
		
		String tableName = ESAPI.encoder().encodeForSQL( new OracleCodec(), props.getProperty("jdbc.tableName"));
		String name = ESAPI.encoder().encodeForSQL( new OracleCodec(), props.getProperty("jdbc.name"));
		Query query = em.createNativeQuery("SELECT * FROM " + tableName + "WHERE Name = " + name);
		
		List<String> list = query.getResultList();
	}
*/
	
	public void good_ResultList(EntityManager em)
	{
		Properties props = new Properties();
		String tableName = props.getProperty("jdbc.tableName");
		String name = props.getProperty("jdbc.name");
		/* FIX */
		Query query = em.createNativeQuery("SELECT * FROM  ? WHERE Name = ?");		
		query.setParameter(1, tableName).setParameter(2, name);
		List<String> list = query.getResultList();
	}
	
	public void bad_SingleResult(EntityManager em)
	{
		Properties props = new Properties();
		String tableName = props.getProperty("jdbc.tableName");
		String name = props.getProperty("jdbc.name");
		Query query = em.createNativeQuery("SELECT * FROM " + tableName + "WHERE Name = " + name);
		/* FLAW */
		Object result = query.getSingleResult();
	}
	
/* 
// import import org.owasp.esapi.*;
// import org.owasp.esapi.codecs.*;

	public void ACA(EntityManager em)
	{
		Properties props = new Properties();
		
// ${hsAcaTarget.lineSplitMid} : props.getProperty("jdbc.tableName")
// ${hsAcaTarget.lineSplitMid} : props.getProperty("jdbc.name")
// ESAPI.encoder().encodeForSQL( new OracleCodec(), ${hsAcaTarget.lineSplitMid} )
		
		String tableName = ESAPI.encoder().encodeForSQL( new OracleCodec(), props.getProperty("jdbc.tableName"));
		String name = ESAPI.encoder().encodeForSQL( new OracleCodec(), props.getProperty("jdbc.name"));
		Query query = em.createNativeQuery("SELECT * FROM " + tableName + "WHERE Name = " + name);
		
		Object result = query.getSingleResult();
	}
*/

	public void good_SingleResult(EntityManager em)
	{
		Properties props = new Properties();
		String tableName = props.getProperty("jdbc.tableName");
		String name = props.getProperty("jdbc.name");
		/* FIX */
		Query query = em.createNativeQuery("SELECT * FROM  ? WHERE Name = ?");		
		query.setParameter(1, tableName).setParameter(2, name);
		Object result = query.getSingleResult();
	}
}
