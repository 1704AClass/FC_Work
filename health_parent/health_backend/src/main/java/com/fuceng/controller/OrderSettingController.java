package com.fuceng.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fuceng.Bean.OrderSetting;
import com.fuceng.Interface.OrderSettingService;
import com.fuceng.util.MessageConstant;
import com.fuceng.util.POIUtils;
import com.fuceng.util.Result;

@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {

	@Reference
	private OrderSettingService orderSettingService;
	
	@RequestMapping("/upload")
	public Result upload(@RequestParam("excelFile") MultipartFile excelFile) {
		try {
			//读取Excel 文件数据
			List<String[]> list = POIUtils.readExcel(excelFile);
			if(list != null && list.size() > 0) {
				List<OrderSetting> orderSettingList = new ArrayList<>();
				for (String[] strings : list) {
					OrderSetting orderSetting = new OrderSetting(new Date(strings[0]),Integer.parseInt(strings[1]));
					orderSettingList.add(orderSetting);
				}
				orderSettingService.add(orderSettingList);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new Result(false,MessageConstant.IMPORT_ORDERSETTING_FAIL);
		}
		return new Result(true,MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
	}
	
	
	@RequestMapping("/getOrderSettingByMonth")
	public Result getOrderSettingByMonth(String date) {
		System.out.println(date);
		try {
			List<Map> list = orderSettingService.getOrderSettingByMonth(date);
			//获取预约数据成功
			return new Result(true,MessageConstant.GET_ORDERSETTING_SUCCESS, list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//获取预约数据失败
			return new Result(false,MessageConstant.GET_ORDERSETTING_FAIL);
		}
	}
	
	
	@RequestMapping("/editNumberByDate")
	public Result editNumberByDate(@RequestBody OrderSetting orderSetting) {
		System.out.println(orderSetting);
		try {
			orderSettingService.editNumberByDate(orderSetting);
			//预约设置成功
			return new Result(true,MessageConstant.ORDERSETTING_SUCCESS);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//预约设置失败
			return new Result(false,MessageConstant.ORDERSETTING_FAIL);
		}
	}
	
	
	
	
	
	
}
