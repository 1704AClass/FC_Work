package com.fuceng.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.bawei.mapper.TCheckitemMapper;
import com.fuceng.Bean.CheckItem;
import com.fuceng.Interface.CheckItemService;
import com.fuceng.util.QueryPageBean;

@Service
public class CheckItemServiceImpl implements CheckItemService{

	@Resource
	private TCheckitemMapper checkitemMapper;
	
	
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
		Integer currentPage = pageBean.getCurrentPage();
		Integer pageSize = pageBean.getPageSize();
		queryString = queryString == null ? "" : queryString;
		currentPage = currentPage == null ? 0 : (currentPage-1)*pageSize;
		return checkitemMapper.findAllUserPage(currentPage,pageSize,queryString);
	}

	@Override
	public void add(CheckItem checkItem) {
		// TODO Auto-generated method stub
		checkitemMapper.add(checkItem);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		Long count = checkitemMapper.findCountByCheckItemId(id);
		if(count > 0) {
			//当前检查项被引用，不能删除
			throw new RuntimeException("当前检查项被引用，不能删除");
		}
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

	@Override
	public CheckItem findById(Integer id) {
		// TODO Auto-generated method stub
		return checkitemMapper.findById(id);
	}

	@Override
	public List<CheckItem> findAll() {
		// TODO Auto-generated method stub
		return checkitemMapper.findAll();
	}

}
