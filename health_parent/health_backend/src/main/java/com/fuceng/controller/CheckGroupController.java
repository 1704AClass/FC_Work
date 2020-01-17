package com.fuceng.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fuceng.Bean.CheckGroup;
import com.fuceng.Bean.CheckItem;
import com.fuceng.Interface.CheckGroupService;
import com.fuceng.util.MessageConstant;
import com.fuceng.util.PageResult;
import com.fuceng.util.QueryPageBean;
import com.fuceng.util.Result;

@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {

	@Reference
	public CheckGroupService checkGroupService;
	
	//新增
	@RequestMapping("/add")
	public Result add(@RequestBody CheckGroup checkGroup,Integer[] checkitemIds) {
		try {
			checkGroupService.add(checkGroup,checkitemIds);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new Result(false,MessageConstant.ADD_CHECKGROUP_FAIL);
		}
		return new Result(true,MessageConstant.ADD_CHECKGROUP_SUCCESS);
		
	}
	
	
	@RequestMapping("/findPage")
	public PageResult findPage(@RequestBody QueryPageBean pageBean){
		PageResult pageResult = checkGroupService.pageQuery(
			pageBean.getCurrentPage(),
			pageBean.getPageSize(),
			pageBean.getQueryString()
		);
		return pageResult;
	}

	
	@RequestMapping("/findById")
	public Result findById(Integer id){
		CheckGroup checkGroup= checkGroupService.findById(id);
		if(checkGroup != null) {
			return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroup);
		}
		return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
	}

	@RequestMapping("/findCheckItemIdsByCheckGroupId")
	public Result findCheckItemIdsByCheckGroupId(Integer id){
		try {
			List<Integer> checkitemIds= checkGroupService.findCheckItemIdsByCheckGroupId(id);
			return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkitemIds);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
		}
	}
	
	@RequestMapping("/edit")
	public Result edit(@RequestBody CheckGroup checkGroup,Integer[] checkitemIds) {
		try {
			checkGroupService.edit(checkGroup,checkitemIds);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new Result(false,MessageConstant.EDIT_CHECKGROUP_FAIL);
		}
		return new Result(true,MessageConstant.EDIT_CHECKGROUP_SUCCESS);
	}
	
	
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(Integer id){
		try {
			checkGroupService.delete(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new Result(false,MessageConstant.DELETE_CHECKITEM_FAIL);
		}
		return new Result(true,MessageConstant.DELETE_CHECKITEM_SUCCESS);
	}
	
}
