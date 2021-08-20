package study.tinyurl.logic;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import study.tinyurl.database.CassandraConnector;

public class TinyURLGenerator {
	static final Logger log = LoggerFactory.getLogger(TinyURLGenerator.class);
	public String generateTinyURL(String originalURL){
		if(originalURL == null) {
			return null;
		}
		return Hashing.murmur3_32().hashString(originalURL, StandardCharsets.UTF_8).toString();
	}
	public static void main(String[] args) {
		TinyURLGenerator tinyURL = new TinyURLGenerator();
		log.info(tinyURL.generateTinyURL("www.google.co.in"));
	}
}
