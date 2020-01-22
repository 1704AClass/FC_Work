package com.fuceng.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fuceng.Bean.Order;
import com.fuceng.Interface.OrderService;
import com.fuceng.util.MessageConstant;
import com.fuceng.util.MessageTestUtil;
import com.fuceng.util.RedisMessageConstant;
import com.fuceng.util.Result;
import com.fuceng.util.SMSUtils;

import redis.clients.jedis.JedisPool;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Reference
	private OrderService orderService;
	
	@Resource
	private JedisPool jedisPool;
	
	
	
	@RequestMapping("/findById")
	public Result findById(Integer id) {
		try {
			Map map = orderService.findById(id);
			return new Result(true,MessageConstant.QUERY_ORDER_SUCCESS,map);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false,MessageConstant.QUERY_ORDER_FAIL);
			// TODO: handle exception
		}
	}
	
	
	@RequestMapping("/submit")
	public Result submitOrder(@RequestBody Map map) {
		String telephone = (String) map.get("telephone");
		//从Redis中获取缓存的验证码，key为手机号 + RedisConstant.SendType_order
		
		String codeInRedis = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_ORDER);
		
		String validateCode = (String) map.get("validateCode");
		//效验手机验证码
		if(codeInRedis == null || !codeInRedis.equals(validateCode)) {
			return new Result(false,MessageConstant.VALIDATECODE_ERROR);
		}
		Result result = null;
		//调用体检预约服务
		try {
			map.put("orderType",Order.ORDERTYPE_WEIXIN);
			result = orderService.order(map);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//预约失败
			return result;
		}
		
		if(result.isFlag()) {
			//预约成功，发送短信通知
			String orderDate = (String) map.get("orderDate");
			try {
				//预约成功,发送短信通知
				//MessageTestUtil.getMessagesUtil(telephone,orderDate);
				SMSUtils.sendShortMessage(SMSUtils.ORDER_NOTICE,telephone,orderDate);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	
	
	
	
}
