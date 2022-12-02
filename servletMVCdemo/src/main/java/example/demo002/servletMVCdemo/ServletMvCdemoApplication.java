package example.demo002.servletMVCdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class ServletMvCdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServletMvCdemoApplication.class, args);
	}

}
