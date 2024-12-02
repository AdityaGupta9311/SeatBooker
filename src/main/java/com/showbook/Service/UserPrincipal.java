package com.showbook.Service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.showbook.Modals.Users;

public class UserPrincipal implements UserDetails {

	@Autowired
	Users users;
	
	public UserPrincipal(Users user) {
		this.users= user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return users.getPassword();
	}

	@Override
	public String getUsername() {
		return users.getEmail();
	}

}
