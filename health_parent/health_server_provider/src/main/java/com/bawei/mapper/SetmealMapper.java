package com.bawei.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.fuceng.Bean.CheckGroup;
import com.fuceng.Bean.Setmeal;

public interface SetmealMapper {

	@Insert("insert into t_setmeal(name,code,helpCode,sex,age,price,remark,attention,img) values(#{name},#{code},#{helpCode},#{sex},#{age},#{price},#{remark},#{attention},#{img})")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	void add(Setmeal setmeal);

	@Insert("insert into t_setmeal_checkgroup(setmeal_id,checkgroup_id) values(#{setmeal_id},#{checkgroup_id})")
	void setSetmealAndCheckGroup(Map<String, Integer> map);
  
	@Select("select count(*) from t_setmeal where code like concat('%',#{queryString},'%') or name like concat('%',#{queryString},'%') or helpCode like concat('%',#{queryString},'%') ")
	Integer getCount(@Param("queryString")String queryString);

	@Select("select * from t_setmeal where code like concat('%',#{queryString},'%') or name like concat('%',#{queryString},'%') or helpCode like concat('%',#{queryString},'%') limit #{currentPage},#{pageSize}")
	List<Setmeal> selectByCondition(@Param("currentPage")Integer currentPage,@Param("pageSize") Integer pageSize,@Param("queryString") String queryString);

}
