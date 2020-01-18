package com.fuceng.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import com.alibaba.dubbo.config.annotation.Service;
import com.bawei.mapper.SetmealMapper;
import com.fuceng.Bean.CheckGroup;
import com.fuceng.Bean.Setmeal;
import com.fuceng.Interface.SetmealService;
import com.fuceng.util.PageResult;
import com.fuceng.util.RedisConstant;

import redis.clients.jedis.JedisPool;

@Service
public class SetmealServiceImpl implements SetmealService{

	@Resource
	private SetmealMapper setmealMapper;
	
	@Resource
	private JedisPool jedisPool;

	@Override
	public void add(Setmeal setmeal, Integer[] checkgroupIds) {
		// TODO Auto-generated method stub
		setmealMapper.add(setmeal);
		if(checkgroupIds != null && checkgroupIds.length > 0) {
			//绑定套餐和检查组的多对多关系
			setSetmealAndCheckGroup(setmeal.getId(),checkgroupIds);
		}
		savePic2Redis(setmeal.getImg());
	}

	private void savePic2Redis(String img) {
		// TODO Auto-generated method stub
		jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,img);
	}

	private void setSetmealAndCheckGroup(Integer id, Integer[] checkgroupIds) {
		// TODO Auto-generated method stub
		for (Integer checkgroupId : checkgroupIds) {
			Map<String,Integer> map = new HashMap<>();
			map.put("setmeal_id", id);
			map.put("checkgroup_id",checkgroupId);
			setmealMapper.setSetmealAndCheckGroup(map);
		}
	}

	@Override
	public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
		currentPage = currentPage == null ? 0 : (currentPage-1)*pageSize;
		queryString = queryString == null ? "" : queryString;
		System.out.println(currentPage+"=="+pageSize+"=="+queryString);
		Integer total = setmealMapper.getCount(queryString);
		System.out.println(total);
		List<Setmeal> rows = setmealMapper.selectByCondition(currentPage,pageSize,queryString);
		for (Setmeal meal : rows) {
			System.out.println(meal);
		}
		return new PageResult(Long.parseLong(total+""), rows);
	}
	
	
}
