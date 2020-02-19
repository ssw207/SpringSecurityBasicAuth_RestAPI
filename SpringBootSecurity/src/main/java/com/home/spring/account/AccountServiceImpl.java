package com.home.spring.account;

import java.util.Arrays;
import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepo r;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostConstruct
	public void init() {
		Account entity = r.save(new Account("id", passwordEncoder.encode("pw"),Auth.USER.getAuthority()));
		log.debug(entity.toString());
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = r.findById(username).orElseThrow(() -> new UsernameNotFoundException(username));
		return new User(account.getUsername(), passwordEncoder.encode(account.getPassword()), authorities(account.getRole()));
	}
	
	//User 객체의 세번째 인자 USER라는 ROLE을 가짂 사용자이다 라고 설정하는 부분
	private Collection<? extends GrantedAuthority> authorities(String role) {
		return Arrays.asList(new SimpleGrantedAuthority(role));
	}

	@Override
	public Account save(Account entity) {
		return r.save(entity);
	}
}
