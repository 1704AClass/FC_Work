package com.fuceng.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class UserController {

	@RequestMapping("/add")
	@PreAuthorize("hasAuthority('add')")
	public String add() {
		System.out.println("add....");
		return "success";
	}
	
	
	@RequestMapping("/delete")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String delete() {
		System.out.println("delete....");
		return "success";
	}
	
	
}
