package com.fuceng.Interface;

import java.util.List;

import com.fuceng.Bean.Member;

public interface MemberService {

	Member findByTelephone(String telephone);

	void add(Member member);

	List<Integer> findMemberCountByMonth(List<String> list);

}
