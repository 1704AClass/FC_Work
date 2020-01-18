package com.itheima.jobs;

import java.util.Set;

import javax.annotation.Resource;

import com.fuceng.util.RedisConstant;

import redis.clients.jedis.JedisPool;


public class ClearImgJob {

	@Resource
	private JedisPool jedisPool;
	
	public void clearImg() {
		//根据Redis中保存的两个set集合进行差值计算，获得垃圾图片名称集合
		Set<String> set = jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES,
				RedisConstant.SETMEAL_PIC_DB_RESOURCES);
		if(set != null) {
			for (String picName : set) {
				//从Redis集合中删除图片名称
				jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES, picName);
			}
		}
	}
	
}
