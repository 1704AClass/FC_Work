package com.fuceng.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.fuceng.Bean.CheckItem;
import com.fuceng.Bean.User;
import com.fuceng.Interface.CheckItemService;
import com.fuceng.mapper.TCheckitemMapper;
import com.fuceng.mapper.TUserMapper;
import com.fuceng.util.QueryPageBean;

@Service
public class CheckItemServiceImpl implements CheckItemService{

	@Resource
	private TCheckitemMapper checkitemMapper;
	
	@Resource
	private TUserMapper userMapper;
	
	@Override
	public Integer getCount() {
		// TODO Auto-generated meth1od stub
		Integer count = checkitemMapper.getCount();
		System.out.println(count);
		return count;
	}

	@Override
	public List<CheckItem> findPage(QueryPageBean pageBean) {
		// TODO Auto-generated method stub
		String queryString = pageBean.getQueryString();
		queryString = queryString == null ? "" : queryString;
		return checkitemMapper.findAllUserPage(pageBean.getCurrentPage(),pageBean.getPageSize(),queryString);
	}

	@Override
	public void add(CheckItem checkItem) {
		// TODO Auto-generated method stub
		checkitemMapper.add(checkItem);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		checkitemMapper.delete(id);
	}

	@Override
	public void deleteByIds(Integer[] ids) {
		// TODO Auto-generated method stub
		for (Integer id : ids) {
			checkitemMapper.delete(id);
		}
	}

	@Override
	public void update(CheckItem checkItem) {
		// TODO Auto-generated method stub
		checkitemMapper.update(checkItem);
	}

}
