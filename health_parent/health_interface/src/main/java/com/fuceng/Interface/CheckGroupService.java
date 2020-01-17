package com.fuceng.Interface;

import java.util.List;

import com.fuceng.Bean.CheckGroup;
import com.fuceng.util.PageResult;

public interface CheckGroupService {

	void add(CheckGroup checkGroup, Integer[] checkitemIds);

	PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

	CheckGroup findById(Integer id);

	List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

	void edit(CheckGroup checkGroup, Integer[] checkitemIds);

	void delete(Integer id);

}
