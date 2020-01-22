package com.bawei.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.fuceng.Bean.Order;

public interface OrderMapper {

	@Select("select * from t_order where member_id = #{memberId} and orderDate = #{orderDate} and setmeal_id = #{setmealId}")
	List<Order> findByCondition(Order order);

	@Insert("insert into t_order(member_id,orderDate,orderType,orderStatus,setmeal_id) values(#{memberId},#{orderDate},#{orderType},#{orderStatus},#{setmealId})")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	void add(Order order);

	@Select("select m.name member,s.name setmeal,o.orderDate orderDate,o.orderType orderType from t_order o,t_member m,t_setmeal s where o.member_id = m.id and o.setmeal_id=s.id and o.id =#{id}")
	Map findById4Detail(@Param("id")Integer id);

}
