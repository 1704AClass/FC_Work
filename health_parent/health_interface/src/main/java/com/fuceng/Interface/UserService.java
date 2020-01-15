package com.fuceng.Interface;

import java.util.List;

import com.alibaba.dubbo.config.annotation.Service;
import com.fuceng.Bean.User;

@Service
public interface UserService {

	Integer getCount();

	List<User> findAllUserPage(Integer startIndex, Integer pageSize);

}
