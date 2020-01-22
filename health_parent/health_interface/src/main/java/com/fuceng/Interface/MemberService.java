package com.fuceng.Interface;

import com.fuceng.Bean.Member;

public interface MemberService {

	Member findByTelephone(String telephone);

	void add(Member member);

}
