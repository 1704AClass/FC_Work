package com.fuceng.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.fuceng.Bean.User;

@Repository
public interface UserMapper {

	@Select("select count(*) from t_user")
	Integer getCount();

	@Select("select * from t_user limit #{startIndex},#{pageSize}")
	List<User> findAllUserPage(@Param("startIndex")Integer startIndex,@Param("pageSize") Integer pageSize);
	
}
