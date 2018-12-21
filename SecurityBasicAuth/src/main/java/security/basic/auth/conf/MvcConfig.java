package security.basic.auth.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/home").setViewName("home"); // /home url로 접근시 home view이름을 반환함
		registry.addViewController("/").setViewName("home");  
		registry.addViewController("/hello").setViewName("hello");
		registry.addViewController("/login").setViewName("login");
	}

}
