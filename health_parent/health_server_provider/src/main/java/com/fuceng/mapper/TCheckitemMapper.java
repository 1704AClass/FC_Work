package com.fuceng.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.fuceng.Bean.CheckItem;

@Repository
public interface TCheckitemMapper {

	@Select("select count(*) from t_checkitem")
	Integer getCount();

	@Select("select * from t_checkitem where code like concat('%',#{queryString},'%') or name like concat('%',#{queryString},'%')  limit #{startIndex},#{pageSize}")
	List<CheckItem> findAllUserPage(@Param("startIndex")Integer startIndex,@Param("pageSize") Integer pageSize,@Param("queryString") String queryString);

	@Insert("insert into t_checkitem values(#{id},#{code},#{name},#{sex},#{age},#{price},#{type},#{attention},#{remark})")
	void add(CheckItem checkItem);

	@Delete("delete from t_checkitem where id = #{id}")
	void delete(@Param("id")Integer id);

	@Update("update t_checkitem code = #{code},name=#{name},sex=#{sex},age=#{age},price=#{price},type=#{type},attention=#{attention},remark=#{remark} where id =#{id}")
	void update(CheckItem checkItem);
	
	
}
