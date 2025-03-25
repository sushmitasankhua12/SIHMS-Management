package com.sihm.SIHMSystem.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sihm.SIHMSystem.Model.Userdetails;
import com.sihm.SIHMSystem.Repository.UserdetailsRepository;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserdetailsRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Userdetails user = repository.findByusernameIgnoreCase(username);
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassWord(),
				new ArrayList<>());
	}
}
