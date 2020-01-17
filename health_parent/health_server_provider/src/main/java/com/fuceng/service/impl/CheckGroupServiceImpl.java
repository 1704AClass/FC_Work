	package com.fuceng.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.bawei.mapper.CheckGroupMapper;
import com.fuceng.Bean.CheckGroup;
import com.fuceng.Interface.CheckGroupService;
import com.fuceng.util.PageResult;

@Service
public class CheckGroupServiceImpl implements CheckGroupService{

	@Resource
	private CheckGroupMapper checkGroupMapper;
	
	@Override
	public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
		// TODO Auto-generated method stub
		checkGroupMapper.add(checkGroup);
		setCheckGroupAndCheckItem(checkGroup.getId(),checkitemIds);
	}

	private void setCheckGroupAndCheckItem(Integer checkGroupId, Integer[] checkitemIds) {
		// TODO Auto-generated method stub
		if(checkitemIds != null && checkitemIds.length > 0) {
			for (Integer checkitemId : checkitemIds) {
				Map<String,Integer> map = new HashMap<String, Integer>();
				map.put("checkgroup_id",checkGroupId);
				map.put("checkitem_id",checkitemId);
				checkGroupMapper.setCheckGroupAndCheckItem(map);
			}
		}
	}

	@Override
	public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
		// TODO Auto-generated method stub
		currentPage = currentPage == null ? 0 : (currentPage-1)*pageSize;
		queryString = queryString == null ? "" : queryString;
		System.out.println(currentPage+"=="+pageSize+"=="+queryString);
		Integer total = checkGroupMapper.getCount(queryString);
		System.out.println(total);
		List<CheckGroup> rows = checkGroupMapper.selectByCondition(currentPage,pageSize,queryString);
		for (CheckGroup checkGroup : rows) {
			System.out.println(checkGroup);
		}
		return new PageResult(Long.parseLong(total+""), rows);
	}

	@Override
	public CheckGroup findById(Integer id) {
		// TODO Auto-generated method stub
		return checkGroupMapper.findById(id);
	}

	@Override
	public List<Integer> findCheckItemIdsByCheckGroupId(Integer id) {
		// TODO Auto-generated method stub
		return checkGroupMapper.findCheckItemIdsByCheckGroupId(id);
	}

	@Override
	public void edit(CheckGroup checkGroup, Integer[] checkitemIds) {
		// TODO Auto-generated method stub
		//根据检查组id删除中间表数据
		checkGroupMapper.deleteAssociation(checkGroup.getId());
		//向中间表插入数据
		setCheckGroupAndCheckItem(checkGroup.getId(), checkitemIds);
		//更新检查组基本信息
		checkGroupMapper.edit(checkGroup);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		checkGroupMapper.delete(id);
	}

}
