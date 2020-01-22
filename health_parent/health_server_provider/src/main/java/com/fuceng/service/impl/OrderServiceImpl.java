package com.fuceng.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.bawei.mapper.MemberMapper;
import com.bawei.mapper.OrderMapper;
import com.bawei.mapper.OrderSettingMapper;
import com.fuceng.Bean.Member;
import com.fuceng.Bean.Order;
import com.fuceng.Bean.OrderSetting;
import com.fuceng.Interface.OrderService;
import com.fuceng.util.DateUtils;
import com.fuceng.util.MessageConstant;
import com.fuceng.util.Result;

@Service
public class OrderServiceImpl implements OrderService{

	@Resource
	private OrderSettingMapper orderSettingMapper;
	
	@Resource
	private MemberMapper memberMapper;
	
	@Resource
	private OrderMapper orderMapper;
	
	
	//体检预约
	@Override
	public Result order(Map map) throws Exception {
		// TODO Auto-generated method stub
		//检查当前日期是否进行了预约设置
		String orderDate = (String) map.get("orderDate");
		Date date = DateUtils.parseString2Date(orderDate);
		System.out.println(date);
		OrderSetting orderSetting = orderSettingMapper.findByOrderDate(date);
		System.out.println(orderSetting);
		if(orderSetting == null) {
			return new Result(false,MessageConstant.SELECTED_DATE_CANNOT_ORDER);
		}
		
		//检查预约日期是否预约已满
		int number = orderSetting.getNumber();//可预约人数
		int reservations = orderSetting.getReservations();//已预约人数
		if(reservations >= number) {
			//预约已满，不能预约
			return new Result(false,MessageConstant.ORDER_FULL);
		}
		
		//检查当前用户是否为会员，根据手机号判断
		String telephone = (String) map.get("telephone");
		Member member = memberMapper.findByTelephone(telephone);
		
		//防止重复预约
		if(member != null) {
			Integer memberId = member.getId();
			int setmealId = Integer.parseInt((String)map.get("setmealId"));
			Order order = new Order(memberId,date,null,null,setmealId);
			List<Order> list = orderMapper.findByCondition(order);
			if(list != null && list.size() > 0) {
				//已经完成了预约，不能重复预约
				return new  Result(false,MessageConstant.HAS_ORDERED);
			}
		}
		
		
		
		//可以预约，=设置预约人数加一
		
		orderSetting.setReservations(orderSetting.getReservations() + 1);
		orderSettingMapper.editReservationsByOrderDate(orderSetting);
		
		if(member == null) {
			//当前用户不是会员，=需要添加到会员表
			member = new Member();
			member.setName((String)map.get("name"));
			member.setPhoneNumber(telephone);
			member.setIdCard((String) map.get("idCard"));
			member.setSex((String)map.get("sex"));
			member.setRegTime(new Date());
			memberMapper.add(member);
		}
		
		//保存预约信息到预约表
		Order order = new Order(member.getId(),date,(String)map.get("orderType"),Order.ORDERSTATUS_NO,Integer.parseInt((String)map.get("setmealId")));
		orderMapper.add(order);
			
		return new Result(true,MessageConstant.ORDER_SUCCESS,order.getId());
	}


	@Override
	public Map findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		Map map = orderMapper.findById4Detail(id);
		if(map != null) {
			//处理日期格式
			Date orderDate = (Date) map.get("orderDate");
			map.put("orderDate",DateUtils.parseDate2String(orderDate));
		}
		return null;
	}

}
