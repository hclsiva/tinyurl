package study.tinyurl.database;

import java.net.InetSocketAddress;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CassandraConnector {

	private static CassandraConnector INSTANCE = null;
	private static Object LOCK = new Object();
	private CqlSession session;

	private String datacenter = "datacenter1";
	static final Logger log = LoggerFactory.getLogger(CassandraConnector.class);
	private CassandraConnector(String node, Integer port) {

		this.connect(node, port, datacenter);
	}

	private void connect(String node, Integer port, String datacenter) {

		CqlSessionBuilder builder = CqlSession.builder();
		builder.addContactPoint(new InetSocketAddress(node, port));
		builder.withLocalDatacenter(datacenter).withKeyspace("tinyurl");
		session = builder.build();
		log.info("Session = " + session);
	}

	public CqlSession getSession() {

		return this.session;
	}
	
	public void close() {
		try {
			this.session.close();
		} catch (Exception e) {
			
		}
	}
	public static CassandraConnector getInstance(String node, Integer port) {
		if(INSTANCE == null || INSTANCE.getSession().isClosed()) {
			synchronized (LOCK) {
				if(INSTANCE == null || INSTANCE.getSession().isClosed()) {
					INSTANCE = new CassandraConnector(node, port);
				}
			}
		}
		return INSTANCE;
	}
}
