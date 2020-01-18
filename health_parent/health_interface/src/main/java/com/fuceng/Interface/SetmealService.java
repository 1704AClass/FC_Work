package com.fuceng.Interface;

import com.fuceng.Bean.Setmeal;
import com.fuceng.util.PageResult;

public interface SetmealService {

	void add(Setmeal setmeal, Integer[] checkgroupIds);

	PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

}
