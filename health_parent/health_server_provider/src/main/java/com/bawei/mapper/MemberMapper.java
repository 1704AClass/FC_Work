package com.bawei.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.fuceng.Bean.Member;

public interface MemberMapper {

	@Select("select * from t_member")
	List<Member> findAll();
	
	@Select("select * from t_member where phoneNumber = #{telephone}")
	Member findByTelephone(@Param("telephone")String telephone);

	@Insert("insert into t_member(fileNumber,name,sex,idCard,phoneNumber,regTime,password,email,birthday,remark) values(#{fileNumber},#{name},#{sex},#{idCard},#{phoneNumber},#{regTime},#{password},#{email},#{birthday},#{remark})")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	void add(Member member);

	@Delete("delete from t_member where id = #{id}")
	void deleteById(@Param("id")Integer id);
	
	@Select("select * from t_member where id = #{id}")
	Member findById(@Param("id")Integer id);
	
	@Update("update t_member set fileNumber=#{fileNumber},name=#{name},sex=#{sex},idCard=#{idCard},phoneNumber=#{phoneNumber},regTime=#{regTime},password=#{password},email=#{email},birthday=#{birthday},remark=#{remark} where id = #{id}")
	void edit(Member member);
	
	@Select("select count(id) from t_member where regTime <= #{value}")
	Integer findMemberCountBeforeDate(@Param("date")String date);
	
	@Select("select count(id) from t_member where regTime = #{value}")
	Integer findMemberCountByDate(@Param("date")String date);
	
	@Select("select count(id) from t_member where regTime >= #{value}")
	Integer findMemberCountAfterDate(@Param("date")String date);
	
	@Select("select count(id) from t_member")
	Integer findMemberTotalCount();
	
	
	
	
}
