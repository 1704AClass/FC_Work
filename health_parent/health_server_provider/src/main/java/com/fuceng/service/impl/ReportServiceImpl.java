package com.fuceng.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.bawei.mapper.MemberMapper;
import com.bawei.mapper.OrderMapper;
import com.bawei.mapper.ReportMapper;
import com.fuceng.Interface.ReportService;
import com.fuceng.util.DateUtils;

@Service
public class ReportServiceImpl implements ReportService{

	@Resource
	private ReportMapper reportMapper;

	@Resource
	private MemberMapper memberMapper;
	@Resource
	private OrderMapper orderMapper;
	
	
	@Override
	public Map<String, Object> getBusinessReport() throws Exception{
		// TODO Auto-generated method stub
		//获取当前日期
		String today = DateUtils.parseDate2String(DateUtils.getToday());
		//获得本周一的日期
		String thisWeekMonday = DateUtils.parseDate2String(DateUtils.getThisWeekMonday());
		//获得本月第一天的日期
		String firstDat4ThisMonth = DateUtils.parseDate2String(DateUtils.getFirstDay4ThisMonth());
		//今日新增会员数
		Integer todayNewMember = memberMapper.findMemberCountByDate(today);
		//总会员数
		Integer totalMember = memberMapper.findMemberTotalCount();
		//本周新增会员数
		Integer thisWeekNewMember = memberMapper.findMemberCountAfterDate(thisWeekMonday);
		//本月新增会员数
		Integer thisMonthNewMember = memberMapper.findMemberCountAfterDate(firstDat4ThisMonth);
		//今日预约数
		Integer todayOrderNumber = orderMapper.findOrderCountByDate(today);
		//本周预约数
		Integer thisWeekOrderNumber = orderMapper.findOrderCountAfterDate(thisWeekMonday);
		//本月预约数
		Integer thisMonthOrderNumber = orderMapper.findOrderCountAfterDate(firstDat4ThisMonth);
		//今日到诊数
		Integer todayVisitsNumber = orderMapper.findVisitsCountByDate(today);
		//本周到诊数
		Integer thisWeekVisitsNumber = orderMapper.findVisitsCountAfterDate(thisWeekMonday);
		//本月到诊数
		Integer thisMonthVisitsNumber = orderMapper.findVisitsCountAfterDate(firstDat4ThisMonth);
		//热门套餐
		List<Map> hotSetmeal = orderMapper.finHotSetmeal();
		
		Map<String, Object> result = new HashMap<>();
		result.put("reportDate",today);
		result.put("todayNewMember",todayNewMember);
		result.put("todayMember",totalMember);
		result.put("thisWeekNewMember",thisWeekNewMember);
		result.put("thisMonthNewMember",thisMonthNewMember);
		result.put("todayOrderNumber",todayOrderNumber);
		result.put("thisWeekOrderNumber",thisWeekOrderNumber);
		result.put("thisMonthOrderNumber",thisMonthOrderNumber);
		result.put("todayVisitsNumber",todayVisitsNumber);
		result.put("thisWeekVisitsNumber",thisWeekVisitsNumber);
		result.put("thisMonthVisitsNumber",thisMonthVisitsNumber);
		result.put("hotSetmeal",hotSetmeal);
		
		return result;
	}

	
	
	
}
