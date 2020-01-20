package com.fuceng.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fuceng.Bean.Setmeal;
import com.fuceng.Interface.SetmealService;
import com.fuceng.util.MessageConstant;
import com.fuceng.util.Result;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {

	@Reference
	private SetmealService setmealService;
	
	//获取所有套餐信息
	@RequestMapping("/getSetmeal")
	public Result getSetmeal() {
		try {
			List<Setmeal> list = setmealService.findAll();
			return new Result(true,MessageConstant.GET_SETMEAL_LIST_SUCCESS,list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new Result(false,MessageConstant.GET_SETMEAL_LIST_FAIL);
		}
	}

	//根据id查询套餐信息
	@RequestMapping("/findById")
	public Result findById(int id) {
		try {
			Setmeal setmeal = setmealService.findById(id);
			return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,setmeal);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new Result(false,MessageConstant.QUERY_SETMEAL_FAIL);
		}
	}
	
	
	
}
