package com.fuceng.Interface;

import java.util.List;

import com.alibaba.dubbo.config.annotation.Service;
import com.fuceng.Bean.CheckItem;
import com.fuceng.Bean.User;
import com.fuceng.util.QueryPageBean;

@Service
public interface CheckItemService {

	Integer getCount();

	List<CheckItem> findPage(QueryPageBean pageBean);

	void add(CheckItem checkItem);

	void delete(Integer id);

	void deleteByIds(Integer[] ids);

	void update(CheckItem checkItem);

}
