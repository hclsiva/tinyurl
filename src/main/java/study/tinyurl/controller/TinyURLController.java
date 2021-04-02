package study.tinyurl.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import study.tinyurl.bean.Response;

@RestController
public class TinyURLController {

	@PostMapping
	public Response insertUrl(String url,String expirationDate,String user) {
		return null;
	}
}
