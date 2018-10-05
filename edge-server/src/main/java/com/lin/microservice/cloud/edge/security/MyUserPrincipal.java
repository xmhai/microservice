package com.lin.microservice.cloud.edge.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserPrincipal implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private User user;
    
    public MyUserPrincipal(User user) {
        this.user = user;
    }
    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (user != null) {
			List<GrantedAuthority> authorities = new ArrayList<>();
			for (Role role : user.getRoles()) {
				authorities.add(new SimpleGrantedAuthority(role.getName()));
				//role.getPrivileges().stream().map(p -> new SimpleGrantedAuthority(p.getName()))
				//		.forEach(authorities::add);
			}

			return authorities;
		}

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
