package study.tinyurl.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

import study.tinyurl.bean.Response;
import study.tinyurl.bean.ResponseEnum;
import study.tinyurl.bean.TinyURLBean;
import study.tinyurl.bean.TinyURLBeanList;

public class CassandraDatabaseAccess {

	@Autowired
	private Environment env = null;
	private String cassandraIp = null;
	private String cassandraPort = null;
	private CassandraConnector dbConnector = null;
	private CqlSession session = null;
	static final Logger log = LoggerFactory.getLogger(CassandraDatabaseAccess.class);
	public CassandraDatabaseAccess() {
	}
	public void init() {
		this.cassandraIp = env.getProperty("CASSANDRA_IP_ADDRESS");
		this.cassandraPort = env.getProperty("CASSANDRA_PORT");
		try {
			this.dbConnector = CassandraConnector.getInstance(cassandraIp, Integer.parseInt(cassandraPort));
			this.session = dbConnector.getSession();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public Response insertURL(TinyURLBean tinyUrlBean) {
		Response response = new Response(); 
		if(dbConnector == null) {
			response.setStatus(ResponseEnum.FAILURE);
			response.setStatusMessage("Database not reachable. Could not process the request");
			return response;
		}

		StringBuilder insertQuery = new StringBuilder("insert into tinyurl.url(hash,originalurl,")
				.append( "creationdate,expirationdate,userid) ")
				.append("values ('" +tinyUrlBean.getHash())
				.append("','" + tinyUrlBean.getOriginalURL())
				.append("'," + tinyUrlBean.getCreationDate()) 
				.append("," + tinyUrlBean.getExpirationDate())
				.append("," + tinyUrlBean.getUserID()+" )");
		log.info("Insert Query = " +insertQuery.toString());

		try {
			this.session.execute(insertQuery.toString());
			response.setStatus(ResponseEnum.SUCCESS);
			
		}catch (Exception e) {
			e.printStackTrace();
			response.setStatus(ResponseEnum.FAILURE);
			response.setStatusMessage(e.getMessage());
		}
		return response;
	}
	public TinyURLBeanList findAll() {
		TinyURLBeanList tinyurlBeanList = new TinyURLBeanList();
		if(this.dbConnector == null){
			return tinyurlBeanList;
		}
		StringBuilder selectBulk = new StringBuilder("SELECT * FROM TINYURL.URL");
		try {
			ResultSet resultSet =this.session.execute(selectBulk.toString());
			for(Row row : resultSet) {
				TinyURLBean tinyurlbean = new TinyURLBean();
				tinyurlbean.setHash(row.getString("hash"));
				tinyurlbean.setOriginalURL(row.getString("originalurl"));
				tinyurlbean.setUserID(row.getInt("userid"));
				tinyurlBeanList.add(tinyurlbean);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		log.info(tinyurlBeanList.toString());
		return tinyurlBeanList;
	}
	public TinyURLBean findUrl(String hash) {
		TinyURLBean tinyurlbean = new TinyURLBean();
		if(this.dbConnector == null){
			return tinyurlbean;
		}

		StringBuilder selectBulk = new StringBuilder("SELECT * FROM TINYURL.URL WHERE HASH='"+hash+"'");
		try {
			ResultSet resultSet =this.session.execute(selectBulk.toString());
			for(Row row : resultSet) {				
				tinyurlbean.setHash(row.getString("hash"));
				tinyurlbean.setOriginalURL(row.getString("originalurl"));
				tinyurlbean.setUserID(row.getInt("userid"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		log.info(tinyurlbean.toString());
		return tinyurlbean;
	}
	public String getHealthStatus(){
		String status = (this.session != null)? "Cassandra is reachable": "Cassandra is not up. Check if it is reachable";
		return status;
	}
	public String toString() {
		return "This is cassandra database helper";
	}
}
