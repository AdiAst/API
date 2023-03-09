package com.lithan.xyz.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


import com.lithan.xyz.secuirty.WebUserDetails;
import com.lithan.xyz.entities.User;
import com.lithan.xyz.repository.UserRepository;

@Component
public class WebUserDetailsService implements UserDetailsService{
	@Autowired
	private UserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User>user= repo.findByUsername(username);
		System.out.println(user.get().getUsername());
		return user.map(WebUserDetails::new)
				.orElseThrow(()->new UsernameNotFoundException("User Not Found"+username));
	}

}
