package com.fuceng.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.bawei.mapper.MemberMapper;
import com.fuceng.Bean.Member;
import com.fuceng.Interface.MemberService;
import com.fuceng.util.MD5Utils;

@Service
public class MemberServiceImpl implements MemberService{

	
	@Resource
	private MemberMapper memberMapper;
	
	
	@Override
	public Member findByTelephone(String telephone) {
		// TODO Auto-generated method stub
		return memberMapper.findByTelephone(telephone);
	}

	@Override
	public void add(Member member) {
		// TODO Auto-generated method stub
		if(member.getPassword() != null) {
			member.setPassword(MD5Utils.md5(member.getPassword()));
		}
		memberMapper.add(member);
	}

	@Override
	public List<Integer> findMemberCountByMonth(List<String> list) {
		// TODO Auto-generated method stub
		List<Integer> arrayList = new ArrayList<>();
		for (String m : list) {
			m = m + ".31";
			Integer count = memberMapper.findMemberCountBeforeDate(m);
			arrayList.add(count);
		}
		
		return arrayList;
	}

	
	
	
	
	
}
