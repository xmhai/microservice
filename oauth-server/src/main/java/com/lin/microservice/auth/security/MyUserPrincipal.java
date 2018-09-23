package com.lin.microservice.auth.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lin.microservice.auth.entity.User;

public class MyUserPrincipal implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private User user;
    
    public MyUserPrincipal(User user) {
        this.user = user;
    }
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		return user==null ? null : user.getPassword();
	}

	@Override
	public String getUsername() {
		return user==null ? null : user.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
