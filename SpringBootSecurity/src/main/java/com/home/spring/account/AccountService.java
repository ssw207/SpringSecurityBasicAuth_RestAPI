package com.home.spring.account;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService extends UserDetailsService{
	Account save(Account entity);
}
