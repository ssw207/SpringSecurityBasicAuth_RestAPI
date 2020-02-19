package com.home.spring.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.home.spring.account.AccountService;

/*웹 보안을 활성화 WebSecurityConfigurer구현하거나 WebSebSecurityConfigurerAdapter 확장한 빈으로 설정되어 있어야함.
핸들러 메소드가 @AuthenticationPrincipal 붙은 인자를 사용하여 인증한 사용자 주체를 받음
자동으로 숨겨진사이트간 요청위조 토큰필드를 스프링의 폼바인딩 태그 라이브러리를 사용하여 추가하는 빈을 설정함*/
@Configuration
@EnableWebSecurity 
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	/*WebSecurityConfigurerAdapter의 메소드
    configure(WebSecurity) : 시큐리티 필터 연결을 설정하기 위함
    configure(HttpSecurity) : 인터셉터로 요청을 안전하게 보호하는 방법을 설정하기 위함 
    configure(AuthenticationManager) : 사용자 세부 서비스를 설정하기 위한 오버라이딩*/
    
//	@Bean
//	public SpringAuthenticationProvider springAuthenticationProvider() {
//	    return new SpringAuthenticationProvider();
//	}
	
	@Autowired
	private AccountService accountService;;
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
    @Override
    protected void configure(HttpSecurity http) throws Exception { // URL 패스에따라 선택적으로 보안을 적용
        http
	        .authorizeRequests() // 기본호출
	          .antMatchers("/h2-console/**").permitAll() // 모두 접속 허가 
	          .antMatchers("/").authenticated() // 모든 요청에 대해 인증을 요청( authenticated() : 인증요청 )
	          .and() // 다른 인증 추가
	         .formLogin()
//	         	.loginPage("/login.do")
//	         	.failureUrl("/login.do?error")
//	         	.loginProcessingUrl("/authLogin.do")
//	         	.defaultSuccessUrl("/web/index.do",true)
//	         	.permitAll()
	         	.and()
	         	.csrf().disable()
	         .logout()
		         .logoutUrl("/logout.do")
	             .logoutSuccessUrl("/login.do?logout")
	             .invalidateHttpSession(true)
	             .deleteCookies("JSESSIONID")
	             .logoutSuccessUrl("/")
	             .and()
	          .httpBasic();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountService);
    }
    
//    @Autowired
//    private DataSource dataSource;
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        // ensure the passwords are encoded properly
//    	UserBuilder users = User.withDefaultPasswordEncoder();
//        auth
//            .jdbcAuthentication()
//                .dataSource(dataSource)
//                .withDefaultSchema()
//                .withUser(users.username("user").password("password").roles("USER"))
//                .withUser(users.username("admin").password("password").roles("USER","ADMIN"));
//    }
    
}