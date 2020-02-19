package com.home.spring;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.home.spring.account.Account;
import com.home.spring.account.AccountRepo;
import com.home.spring.account.AccountService;
import com.home.spring.account.Auth;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class RepositoryTest {

	@Autowired
	private AccountService s;
	
	@Autowired
	private AccountRepo r;
	
	@Autowired
	private UserDetailsService ds;
	
	
//	@AfterEach
//	public void delAll() {
//		r.deleteAll();
//	}
	
	@Test
	public void 생성() {
		Account entity = new Account("id","pw", Auth.USER.getAuthority());
		entity = s.save(entity);
		assertThat(entity).isNotNull();
	}
	@Test
	public void 조회() {
		Account entity = new Account("id","pw", Auth.USER.getAuthority());
		String rs = s.loadUserByUsername("id").toString();
		
		assertThat(entity).isNotNull();
	}
	
	@Test
	public void 로그인() {
		Account entity = new Account("id","pw", Auth.USER.getAuthority());
		entity = s.save(entity);
		
		UserDetails userDetails = ds.loadUserByUsername("id");
		assertThat(userDetails).isNotNull();
	}
}
