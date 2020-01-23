package com.fuceng.service.impl;

import java.util.Set;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.bawei.mapper.PermissionMapper;
import com.bawei.mapper.RoleMapper;
import com.bawei.mapper.UserMapper;
import com.fuceng.Bean.Permission;
import com.fuceng.Bean.Role;
import com.fuceng.Bean.User;
import com.fuceng.Interface.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Resource
	private UserMapper userMapper;
	
	@Resource
	private RoleMapper roleMapper;
	
	@Resource
	private PermissionMapper permissionMapper;
	
	
	
	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		User user = userMapper.findByUsername(username);
		if(user == null) {
			return null;
		}
		Integer userId = user.getId();
		Set<Role> roles = roleMapper.findByUserId(userId);
		if(roles != null && roles.size() > 0) {
			for (Role role : roles) {
				Integer roleId = role.getId();
				Set<Permission> permissions = permissionMapper.findByRoleId(roleId);
				if(permissions != null && permissions.size() > 0) {
					role.setPermissions(permissions);
				}
			}
			user.setRoles(roles);
		}
		return user;
	}

}
