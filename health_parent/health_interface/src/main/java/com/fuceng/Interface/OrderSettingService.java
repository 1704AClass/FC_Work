package com.fuceng.Interface;

import java.util.List;
import java.util.Map;

import com.fuceng.Bean.OrderSetting;

public interface OrderSettingService {

	void add(List<OrderSetting> orderSettingList);

	List<Map> getOrderSettingByMonth(String date);

	void editNumberByDate(OrderSetting orderSetting);

}
