package study.tinyurl.controller;

import java.util.Date;
import java.util.Map;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import study.tinyurl.bean.*;
import study.tinyurl.configuration.ApplicationConfig;
import study.tinyurl.logic.TinyURLGenerator;

@RestController
@RequestMapping("/tinyurl")
public class TinyURLController {
	private static final int TenK = 10000;
	private static final int ONE_MILLION = 1000000;
	TinyURLGenerator tinyUrlGenerator = new TinyURLGenerator();
	static final Logger log = LoggerFactory.getLogger(TinyURLController.class);
	@Autowired
	ApplicationConfig config = null;
	
	@PostMapping(path = "/insertUrl", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response insertUrl(@RequestBody RequestInput input) {
		log.info("RequestBody is = " + input);
		TinyURLBean tinyurlbean = new TinyURLBean();
		tinyurlbean.setHash(tinyUrlGenerator.generateTinyURL(input.getUrl()));
		tinyurlbean.setOriginalURL(input.getUrl());
		tinyurlbean.setUserID(1);
		tinyurlbean.setCreationDate(new Date().getTime());
		tinyurlbean.setExpirationDate(new Date().getTime());
		
		Response response = config.getCassandraDatabaseAccess().insertURL(tinyurlbean);
		return response;
	}
	
	@PostMapping(path="/bulkInsert", consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	public Response insertBulkUrl(@RequestBody UrlCountInput input) {
		String baseurl="https://www.google.co.in/search?q=";
		Response response = null;
		int count = 0;
		try{
			count = input.getUrlCount();
			if(count < 1){
				return invalidCount(input.getUrlCount(),"");
			}
		}catch(Exception e){
			return invalidCount(input.getUrlCount(),e.getMessage());
		}
		for(int i =1; i <=count; i++) {
			String url = baseurl + i;
			TinyURLBean tinyurlbean = new TinyURLBean();
			tinyurlbean.setHash(tinyUrlGenerator.generateTinyURL(url));
			tinyurlbean.setOriginalURL(url);
			tinyurlbean.setUserID(1);
			tinyurlbean.setCreationDate(new Date().getTime());
			tinyurlbean.setExpirationDate(new Date().getTime());
			response = config.getCassandraDatabaseAccess().insertURL(tinyurlbean);
			if(response.getStatus() == ResponseEnum.FAILURE) {
				return response;
			}
		}
		
		return response;
	}
	
	@GetMapping(path="/getAllUrls", produces = MediaType.APPLICATION_JSON_VALUE)
	public TinyURLBeanList findAllUrl() {
		return config.getCassandraDatabaseAccess().findAll();
	}
	@GetMapping(path="/getUrl", produces = MediaType.APPLICATION_JSON_VALUE)
	public TinyURLBean findUrl(@RequestParam(value = "hash") String hash) {
		return config.getCassandraDatabaseAccess().findUrl(hash);
	}

	@GetMapping(path="/count", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map urlCount() {
		Map<String,Long> countMap = new HashMap<String,Long>();
		countMap.put("rows",config.getCassandraDatabaseAccess().findUrlCount());
		return countMap;
	}

	@PutMapping(path="/clean", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response clean() {
		return config.getCassandraDatabaseAccess().cleanTable();
	}

	@GetMapping(path="/health", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map checkHealth() {
		Map<String,String> healthMap = new HashMap<String,String>(0);
		healthMap.put("Rest Url","Reachable");
		healthMap.put("Cassandra Status",config.getCassandraDatabaseAccess().getHealthStatus());
		return healthMap;
	}

	private Response invalidCount(int count,String message) {
		Response response = new Response();
		response.setStatusMessage(message+" invalid count. Count should be between 1 and "+Integer.MAX_VALUE);
		return response;
	}

}
