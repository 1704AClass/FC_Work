package com.fuceng.Interface;

import java.util.Map;

import com.fuceng.util.Result;

public interface OrderService {

	Result order(Map map) throws Exception;

	Map findById(Integer id) throws Exception;

}
