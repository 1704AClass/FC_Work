package com.fuceng.Interface;

import java.util.List;
import java.util.Map;

import com.fuceng.Bean.Setmeal;
import com.fuceng.util.PageResult;

public interface SetmealService {

	void add(Setmeal setmeal, Integer[] checkgroupIds);

	PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

	List<Setmeal> findAll();

	Setmeal findById(int id);

	List<Map<String, Object>> findSetmealCount();

}
