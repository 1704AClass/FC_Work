package com.fuceng.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.ptg.TblPtg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fuceng.Bean.CheckItem;
import com.fuceng.Bean.User;
import com.fuceng.Interface.CheckItemService;
import com.fuceng.util.MessageConstant;
import com.fuceng.util.PageUtils;
import com.fuceng.util.QueryPageBean;
import com.fuceng.util.Result;

@Controller
@RequestMapping("/checkitem")
@CrossOrigin
public class CheckItemController {

	@Reference
	private CheckItemService checkItemService;
	
	
	@RequestMapping("/findPage")
	@ResponseBody
	public Map findAllUser(@RequestBody QueryPageBean pageBean){
		Map map = new HashMap();
		System.out.println(pageBean);
		Integer count = checkItemService.getCount();
		System.out.println(pageBean.getCurrentPage() + "===" + pageBean.getPageSize());
		List<CheckItem> checkItemList = checkItemService.findPage(pageBean);
		for (CheckItem user : checkItemList) {
			System.out.println(user);
		}
		map.put("rows",checkItemList);
		map.put("total",count);
		return map;
	}

	@RequestMapping("/add")
	@ResponseBody
	public Result add(@RequestBody CheckItem checkItem){
		try {
			checkItemService.add(checkItem);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new Result(true,MessageConstant.ADD_CHECKITEM_FAIL);
		}
		return new Result(true,MessageConstant.ADD_CHECKITEM_SUCCESS);
	}

	@RequestMapping("/update")
	@ResponseBody
	public Result update(@RequestBody CheckItem checkItem){
		try {
			checkItemService.update(checkItem);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new Result(true,MessageConstant.ADD_CHECKITEM_FAIL);
		}
		return new Result(true,MessageConstant.ADD_CHECKITEM_SUCCESS);
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(Integer id){
		try {
			checkItemService.delete(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new Result(true,MessageConstant.DELETE_CHECKITEM_FAIL);
		}
		return new Result(true,MessageConstant.DELETE_CHECKITEM_SUCCESS);
	}
	
	@RequestMapping("/deleteByIds")
	@ResponseBody
	public Result deleteByIds(Integer[] ids){
		System.out.println(Arrays.toString(ids));
		try {
			checkItemService.deleteByIds(ids);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new Result(true,MessageConstant.DELETE_CHECKITEM_FAIL);
		}
		return new Result(true,MessageConstant.DELETE_CHECKITEM_SUCCESS);
	}
	
}
