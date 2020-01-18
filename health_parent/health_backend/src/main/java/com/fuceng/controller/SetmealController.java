package com.fuceng.controller;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fuceng.Bean.Setmeal;
import com.fuceng.Interface.SetmealService;
import com.fuceng.util.AliyunOSSClientUtils;
import com.fuceng.util.MessageConstant;
import com.fuceng.util.PageResult;
import com.fuceng.util.QueryPageBean;
import com.fuceng.util.RedisConstant;
import com.fuceng.util.Result;

import redis.clients.jedis.JedisPool;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {

	@Reference
	private SetmealService setmealService;
	
	@Resource
	private JedisPool jedisPool;
	
	
	@RequestMapping("/findPage")
	public PageResult findPage(@RequestBody QueryPageBean pageBean){
		PageResult pageResult = setmealService.pageQuery(
				pageBean.getCurrentPage(),
				pageBean.getPageSize(),
				pageBean.getQueryString()
				);
		return pageResult;
	}
	
	@RequestMapping("/upload")
	public Result upload(@RequestParam("imgFile") MultipartFile imgFile) {
		try {
			AliyunOSSClientUtils ossClientUtils = new AliyunOSSClientUtils();
			String img2Oss = ossClientUtils.uploadImg2Oss(imgFile);
			String imgUrl = ossClientUtils.getImgUrl(img2Oss);
			jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES, imgUrl);
			return new Result(true,MessageConstant.PIC_UPLOAD_SUCCESS, imgUrl);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false,MessageConstant.PIC_UPLOAD_FAIL);
			// TODO: handle exception
		}
	}
	
	@RequestMapping("/add")
	public Result add(@RequestBody Setmeal setmeal,Integer[] checkgroupIds) {
		try {
			setmealService.add(setmeal,checkgroupIds);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false,MessageConstant.ADD_SETMEAL_FAIL);
			// TODO: handle exception
		}
		return new Result(true,MessageConstant.ADD_SETMEIL_SUCCESS);
	}
	
	
	
	
}
