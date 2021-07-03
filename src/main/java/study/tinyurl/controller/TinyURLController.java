package study.tinyurl.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import study.tinyurl.bean.RequestInput;
import study.tinyurl.bean.Response;
import study.tinyurl.bean.ResponseEnum;
import study.tinyurl.bean.TinyURLBean;
import study.tinyurl.bean.TinyURLBeanList;
import study.tinyurl.configuration.ApplicationConfig;
import study.tinyurl.logic.TinyURLGenerator;

@RestController
@RequestMapping("/tinyurl")
public class TinyURLController {
	TinyURLGenerator tinyUrlGenerator = new TinyURLGenerator();
	
	@Autowired
	ApplicationConfig config = null;
	
	@PostMapping(path = "/insertUrl", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response insertUrl(@RequestBody RequestInput input) {
		System.out.println("RequestBody is = " + input);
		TinyURLBean tinyurlbean = new TinyURLBean();
		tinyurlbean.setHash(tinyUrlGenerator.generateTinyURL(input.getUrl()));
		tinyurlbean.setOriginalURL(input.getUrl());
		tinyurlbean.setUserID(1);
		tinyurlbean.setCreationDate(new Date().getTime());
		tinyurlbean.setExpirationDate(new Date().getTime());
		
		Response response = config.getCassandraDatabaseAccess().insertURL(tinyurlbean);
		return response;
	}
	
	@PostMapping(path="/bulkInsert", produces = MediaType.APPLICATION_JSON_VALUE)
	public Response insertBulkUrl() {
		String baseurl="https://www.google.co.in/search?q=";
		Response response = null;
		for(int i =1; i <=1000000; i++) {
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

	@GetMapping(path="/health", produces = MediaType.APPLICATION_JSON_VALUE)
	public String checkHealth() {
		return "Tinyurl is hit !!!!!.";
	}
}
