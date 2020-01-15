package com.fuceng.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fuceng.Bean.User;
import com.fuceng.Interface.UserService;
import com.fuceng.util.PageUtils;

@Controller
@RequestMapping("/user")
@CrossOrigin
public class UserController {

	@Reference
	private UserService userService;
	
	
	@RequestMapping("/findAllUser")
	@ResponseBody
	public Map findAllUser(@RequestParam("page") Integer page){
		Map map = new HashMap();
		System.out.println(page);
		Integer count = userService.getCount();
		System.out.println(5);
		PageUtils util = new PageUtils(page, 5, 5);
		Integer startIndex = util.getStartIndex();
		Integer pageSize = util.getPageSize();
		System.out.println(startIndex + "===" + pageSize);
		startIndex = startIndex == null ? 1 : startIndex ;
		pageSize = pageSize == null ? 5 : pageSize ;
		List<User> userList = userService.findAllUserPage(startIndex,pageSize);
		for (User user : userList) {
			System.out.println(user);
		}
		map.put("rows",userList);
		map.put("total",5);
		return map;
	}
	
}
