package com.bawei.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.fuceng.Bean.CheckGroup;

public interface CheckGroupMapper {

	@Insert("insert into t_checkgroup(code,name,sex,helpCode,remark,attention) values(#{code},#{name},#{sex},#{helpCode},#{remark},#{attention})")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	void add(CheckGroup checkGroup);

	@Insert("insert into t_checkgroup_checkitem(checkgroup_id,checkitem_id) values(#{checkgroup_id},#{checkitem_id})")
	void setCheckGroupAndCheckItem(Map<String, Integer> map);

	@Select("select count(*) from t_checkgroup where code like concat('%',#{queryString},'%') or name like concat('%',#{queryString},'%') or helpCode like concat('%',#{queryString},'%') ")
	Integer getCount(@Param("queryString")String queryString);
	
	@Select("select * from t_checkgroup where code like concat('%',#{queryString},'%') or name like concat('%',#{queryString},'%') or helpCode like concat('%',#{queryString},'%') limit #{currentPage},#{pageSize}")
	List<CheckGroup> selectByCondition(@Param("currentPage")Integer currentPage,@Param("pageSize") Integer pageSize,@Param("queryString") String queryString);

	@Select("select * from t_checkgroup where id = #{id}")
	CheckGroup findById(@Param("id")Integer id);

	@Select("select checkitem_id from t_checkgroup_checkitem where checkgroup_id = #{id}")
	List<Integer> findCheckItemIdsByCheckGroupId(@Param("id")Integer id);

	@Delete("delete from t_checkgroup_checkitem where checkgroup_id = #{id}")
	void deleteAssociation(@Param("id")Integer id);

	@Update("update t_checkgroup set code=#{code},name=#{name},helpCode=#{helpCode},sex=#{sex},remark=#{remark},attention=#{attention} where id = #{id}")
	void edit(CheckGroup checkGroup);

	@Delete("delete from t_checkgroup where id = #{id}")
	void delete(@Param("id")Integer id);

	@Select("select * from t_checkgroup")
	List<CheckGroup> findAll();

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
