package com.fuceng.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fuceng.Bean.User;

public class UserService implements UserDetailsService{

	@Resource
	private BCryptPasswordEncoder passwordEmcoder;
	
	public Map<String,com.fuceng.Bean.User> map = new HashMap<>();
	
	public void initData() {
		User user = new User();
		user.setUsername("admin");
		user.setPassword(passwordEmcoder.encode("admin"));
		User user2 = new User();
		user2.setUsername("xiaoming");
		user2.setPassword(passwordEmcoder.encode("1234"));
		map.put(user.getUsername(),user);
		map.put(user2.getUsername(),user2);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println(username);
		User user = map.get(username);
		if(user == null) {
			return null;
		}
		String password = user.getPassword();
		List<GrantedAuthority> list = new ArrayList<>();
		list.add(new SimpleGrantedAuthority("add"));
		list.add(new SimpleGrantedAuthority("delete"));
		list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		return new org.springframework.security.core.userdetails.User(username, password, list);
	}

}
