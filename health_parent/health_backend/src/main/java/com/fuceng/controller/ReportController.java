package com.fuceng.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fuceng.Interface.MemberService;
import com.fuceng.util.MessageConstant;
import com.fuceng.util.Result;

@RestController
@RequestMapping("/report")
public class ReportController {

	@Reference
	private MemberService memberService;
	
	@RequestMapping("/getMemberReport")
	public Result getMemberReport() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -12);
		//获取当前日期之前12个月的日期
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 12; i++) {
			calendar.add(Calendar.MONTH, 1);
			list.add(new SimpleDateFormat("yyyy.MM").format(calendar.getTime()));
		}
		
		Map<String,Object> map = new HashMap<>();
		map.put("months",list);
		
		List<Integer> memberCount = memberService.findMemberCountByMonth(list);
		map.put("memberCount", memberCount);
		return new Result(true, MessageConstant.GET_MEMBER_NUMBER_REPORT_SUCCESS, map);
		
	}
	
	
	
	
	
	
	
}
