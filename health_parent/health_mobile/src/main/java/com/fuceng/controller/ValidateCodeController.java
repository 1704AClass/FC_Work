package com.fuceng.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fuceng.util.MessageConstant;
import com.fuceng.util.MessageTestUtil;
import com.fuceng.util.RedisMessageConstant;
import com.fuceng.util.Result;
import com.fuceng.util.SMSUtils;
import com.fuceng.util.ValidateCodeUtils;

import redis.clients.jedis.JedisPool;

@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {

	@Resource
	private JedisPool jedisPool;
	
	
	//体检预约时发送手机验证码
	@RequestMapping("/send4Order")
	public Result send4Order(String telephone) {
		Integer code = ValidateCodeUtils.generateValidateCode(4);
		try {
			//发送短信
			MessageTestUtil.getMessagesUtil(telephone,code);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false,MessageConstant.SEND_VALIDATECODE_FAIL);
			// TODO: handle exception
		}
		System.out.println("发送的手机验证码是:"+code);
		//将生成的验证码缓存到redis
		jedisPool.getResource().setex(telephone + RedisMessageConstant.SENDTYPE_ORDER
				,5 * 60 , code.toString());
		return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCSS);
	}
	
	
	
	
	
}
