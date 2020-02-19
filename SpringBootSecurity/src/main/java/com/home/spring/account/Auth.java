package com.home.spring.account;

import org.springframework.security.core.GrantedAuthority;

public enum Auth implements GrantedAuthority{
	ADMIN("ROLE_ADMIN"),
	USER("ROLE_USER");
	
	private String auth;
	
	private Auth(String auth) {
		this.auth = auth;
	}
	
	@Override
	public String getAuthority() {
		return this.auth;
	}
}
