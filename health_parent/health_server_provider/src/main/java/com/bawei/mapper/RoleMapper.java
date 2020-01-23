package com.bawei.mapper;

import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.fuceng.Bean.Role;

public interface RoleMapper {

	@Select("select r.* from t_role  r,t_user_role ur where r.id = ur.role_id and ur.user_id = #{userId}")
	Set<Role> findByUserId(@Param("userId")Integer userId);


}
