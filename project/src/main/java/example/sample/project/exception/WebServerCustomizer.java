package example.sample.project.exception;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

//에러페이지 수정하는곳
@Component
public class WebServerCustomizer implements WebServerFactoryCustomizer<ConfigurableWebServerFactory>{

	@Override
	public void customize(ConfigurableWebServerFactory factory) {
		ErrorPage error404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404");
		ErrorPage error400 = new ErrorPage(HttpStatus.BAD_REQUEST, "/error/400");
		ErrorPage error500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500");
		ErrorPage errorRE = new ErrorPage(RuntimeException.class, "/error/500");
		
		factory.addErrorPages(error404,error400,error500,errorRE);
	}

}
