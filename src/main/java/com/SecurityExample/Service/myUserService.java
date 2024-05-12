package com.SecurityExample.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.SecurityExample.Model.UserDetailsImpl;
import com.SecurityExample.Model.Users;
import com.SecurityExample.Repository.MyUserRepo;

@Service

public class myUserService implements UserDetailsService {

	@Autowired
	MyUserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users users = userRepo.findUserByUserName(username);
		if (users == null) {
			throw new UsernameNotFoundException("user not found");
		}
		return new UserDetailsImpl(users);
	}

	public Users Save(Users user) {
		return userRepo.save(user);

	}

}
