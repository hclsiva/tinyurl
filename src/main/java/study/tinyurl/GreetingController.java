package study.tinyurl;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import study.tinyurl.bean.TinyURLBean;
import study.tinyurl.configuration.ApplicationConfig;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	@Autowired
    private ApplicationConfig beanConfig = null;

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		System.out.println("Cassandra Database = " + beanConfig.getCassandraDatabaseAccess().toString());
		beanConfig.getCassandraDatabaseAccess();
		
		TinyURLBean tinyURLBean = new TinyURLBean();
		tinyURLBean.setHash("Hello");
		tinyURLBean.setOriginalURL("URL");
		tinyURLBean.setCreationDate(new Date().getTime());
		tinyURLBean.setExpirationDate(new Date().getTime());
		tinyURLBean.setUserID(1);
		beanConfig.getCassandraDatabaseAccess().insertURL(tinyURLBean);
		
		tinyURLBean = new TinyURLBean();
		tinyURLBean.setHash("Hello1");
		tinyURLBean.setOriginalURL("URL");
		tinyURLBean.setCreationDate(new Date().getTime());
		tinyURLBean.setExpirationDate(new Date().getTime());
		tinyURLBean.setUserID(1);
		beanConfig.getCassandraDatabaseAccess().insertURL(tinyURLBean);
		
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}
