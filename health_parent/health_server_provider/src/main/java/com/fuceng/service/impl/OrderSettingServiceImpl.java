package com.fuceng.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.bawei.mapper.OrderSettingMapper;
import com.fuceng.Bean.OrderSetting;
import com.fuceng.Interface.OrderSettingService;

@Service
public class OrderSettingServiceImpl implements OrderSettingService{

	@Resource
	private OrderSettingMapper orderSettingMapper;

	@Override
	public void add(List<OrderSetting> list) {
		// TODO Auto-generated method stub
		if(list != null && list.size() > 0) {
			for (OrderSetting orderSetting : list) {
				//检查次数据（日期）是否存在
				long count = orderSettingMapper.findCountByOrderDate(orderSetting.getOrderDate());
				if(count > 0) {
					//已经存在，执行更新操作
					orderSettingMapper.editNumberByOrderDate(orderSetting);
				}else {
					//不存在，执行添加操作
					orderSettingMapper.add(orderSetting);
				}
			}
		}
	}

	@Override
	public List<Map> getOrderSettingByMonth(String date) {
		// TODO Auto-generated method stub
		String dateBegin = date + "-1";
		String dateEnd = date + "-31";
		Map map = new HashMap();
		map.put("dateBegin",dateBegin);
		map.put("dateEnd",dateEnd);
		List<OrderSetting> list = orderSettingMapper.getOrderSettingByMonth(map);
		List<Map> data = new ArrayList<Map>();
		for (OrderSetting orderSetting : list) {
			Map orderSettingMap = new HashMap();
			orderSettingMap.put("date",orderSetting.getOrderDate().getDate());//获取日期
			orderSettingMap.put("number",orderSetting.getNumber());
			orderSettingMap.put("reservations",orderSetting.getReservations());
			data.add(orderSettingMap);
		}
		return data;
	}

	@Override
	public void editNumberByDate(OrderSetting orderSetting) {
		// TODO Auto-generated method stub
		long count = orderSettingMapper.findCountByOrderDate(orderSetting.getOrderDate());
		if(count > 0) {
			//当前日期已经进行了预约设置，需进行修改操作
			orderSettingMapper.editNumberByOrderDate(orderSetting);
		}else {
			//当前日期没有进行预约设置，进行添加操作
			orderSettingMapper.add(orderSetting);
		}
	}
	
	
}
