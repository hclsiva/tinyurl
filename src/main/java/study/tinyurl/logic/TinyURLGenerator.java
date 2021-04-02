package study.tinyurl.logic;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;

public class TinyURLGenerator {

	public String generateTinyURL(String originalURL){
		if(originalURL == null) {
			return null;
		}
		return Hashing.murmur3_32().hashString(originalURL, StandardCharsets.UTF_8).toString();
	}
	public static void main(String[] args) {
		TinyURLGenerator tinyURL = new TinyURLGenerator();
		System.out.println(tinyURL.generateTinyURL("www.google.co.in"));
		System.out.println(tinyURL.generateTinyURL("www.google.co.in"));
		System.out.println(tinyURL.generateTinyURL("www.google.co.in"));
		System.out.println(tinyURL.generateTinyURL("www.google.co.in"));
		System.out.println(tinyURL.generateTinyURL("www.google.co.in"));
	}
}
