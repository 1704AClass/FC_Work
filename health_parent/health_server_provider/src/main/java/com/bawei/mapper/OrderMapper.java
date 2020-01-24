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

	@Select("select count(id) from t_order where orderDate = #{today}")
	Integer findOrderCountByDate(@Param("today")String today);

	@Select("select count(id) from t_order where orderDate >= #{thisWeekMonday}")
	Integer findOrderCountAfterDate(@Param("thisWeekMonday")String thisWeekMonday);

	@Select("select count(id) from t_order where orderDate = #{today} and orderStatus = '已到诊'")
	Integer findVisitsCountByDate(@Param("today")String today);

	@Select("select count(id) from t_order where orderDate >= #{thisWeekMonday} and orderStatus = '已到诊'")
	Integer findVisitsCountAfterDate(@Param("thisWeekMonday")String thisWeekMonday);

	@Select("select s.name,count(o.id) setmeal_count,count(o.id)/(select count(id) from t_order) proportion from t_order o inner join t_setmeal s on s.id = o.setmeal_id group by o.setmeal_id order by setmeal_count desc limit 0,4")
	List<Map> finHotSetmeal();

}
