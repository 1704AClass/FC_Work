package com.bawei.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.fuceng.Bean.User;

public interface UserMapper {

	@Select("select * from t_user where username = #{username}")
	User findByUsername(@Param("username")String username);


}
