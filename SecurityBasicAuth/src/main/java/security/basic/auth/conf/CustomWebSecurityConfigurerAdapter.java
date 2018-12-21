package security.basic.auth.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


/*웹 보안을 활성화 WebSecurityConfigurer구현하거나 WebSebSecurityConfigurerAdapter 확장한 빈으로 설정되어 있어야함.
핸들러 메소드가 @AuthenticationPrincipal 붙은 인자를 사용하여 인증한 사용자 주체를 받음
자동으로 숨겨진사이트간 요청위조 토큰필드를 스프링의 폼바인딩 태그 라이브러리를 사용하여 추가하는 빈을 설정함*/
@Configuration
@EnableWebSecurity 
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
	/*WebSecurityConfigurerAdapter의 메소드
    configure(WebSecurity) : 시큐리티 필터 연결을 설정하기 위함
    configure(HttpSecurity) : 인터셉터로 요청을 안전하게 보호하는 방법을 설정하기 위함 
    configure(AuthenticationManager) : 사용자 세부 서비스를 설정하기 위한 오버라이딩*/
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication() // 메모리인증
          .withUser("user")
          .password(passwordEncoder().encode("password")) // 유저, 아이디, 권한을 를 여기서 설정함
          .authorities("ROLE_USER");
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception { // URL 패스에따라 선택적으로 보안을 적용
        http.authorizeRequests() // 기본호출
          .antMatchers("/securityNone").permitAll() // url이 '/securityNone' 일경우 모두 접속 허가 
          .anyRequest().permitAll() // 모든 요청에 대해 인증을 요청( authenticated() : 인증요청 )
          .and() // 다른 인증 추가
          .csrf().disable()
          .httpBasic(); // http 기본 인증 설정
    }
 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}