package study.tinyurl.database;

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
	
	public CassandraDatabaseAccess() {
	}
	public void init() {
		this.cassandraIp = env.getProperty("CASSANDRA_IP_ADDRESS");
		this.cassandraPort = env.getProperty("CASSANDRA_PORT");		
		this.dbConnector = CassandraConnector.getInstance(cassandraIp, Integer.parseInt(cassandraPort));
		this.session = dbConnector.getSession();
	}
	public Response insertURL(TinyURLBean tinyUrlBean) {
		StringBuilder insertQuery = new StringBuilder("insert into tinyurl.url(hash,originalurl,")
				.append( "creationdate,expirationdate,userid) ")
				.append("values ('" +tinyUrlBean.getHash())
				.append("','" + tinyUrlBean.getOriginalURL())
				.append("'," + tinyUrlBean.getCreationDate()) 
				.append("," + tinyUrlBean.getExpirationDate())
				.append("," + tinyUrlBean.getUserID()+" )");
		System.out.println("Insert Query = " +insertQuery.toString());
		Response response = new Response();
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
		System.out.println(tinyurlBeanList);
		return tinyurlBeanList;
	}
	public TinyURLBean findUrl(String hash) {
		TinyURLBean tinyurlbean = new TinyURLBean();
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
		System.out.println(tinyurlbean);
		return tinyurlbean;
	}
	public String toString() {
		return "This is cassandra database helper";
	}
}
