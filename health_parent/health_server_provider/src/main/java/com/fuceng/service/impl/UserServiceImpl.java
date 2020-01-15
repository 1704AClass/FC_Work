package com.fuceng.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.fuceng.Bean.User;
import com.fuceng.Interface.UserService;
import com.fuceng.mapper.OrderMapper;
import com.fuceng.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService{

	@Resource
	private OrderMapper orderMapper;
	
	@Resource
	private UserMapper userMapper;
	
	@Override
	public Integer getCount() {
		// TODO Auto-generated meth1od stub
		System.out.println("1111111");
		Integer count = userMapper.getCount();
		System.out.println(count);
		return count;
	}

	@Override
	public List<User> findAllUserPage(Integer startIndex, Integer pageSize) {
		System.out.println(startIndex + "===" + pageSize);
		// TODO Auto-generated method stub
		return userMapper.findAllUserPage(startIndex,pageSize);
	}

}
