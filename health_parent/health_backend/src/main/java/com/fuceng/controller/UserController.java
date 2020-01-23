package com.fuceng.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fuceng.util.MessageConstant;
import com.fuceng.util.Result;

@RestController
@RequestMapping("/user")
public class UserController {

	//获取当前登录用户的用户名
	@RequestMapping("/getUsername")
	public Result getUsername() throws Exception{
		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return new Result(true,MessageConstant.GET_USERNAME_SUCCESS,user.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return new Result(false,MessageConstant.GET_USERNAME_FAIL);
		}
	}
	
	
	
	
	
}
