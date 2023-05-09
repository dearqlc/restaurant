package pers.qlc.restaurant.dao;

import org.apache.ibatis.annotations.Mapper;
import pers.qlc.restaurant.entity.CustomPageInfo;
import pers.qlc.restaurant.entity.Order;

import java.util.List;

/**
 * Created with IDEA
 * author:QLC
 * Date:2018/10/1
 * Time:23:36
 */
@Mapper
public interface OrderDao {
    int insert(Order order);

    List<Order> findPage(CustomPageInfo<Order> pageInfo);

    Order findById(Long orderId);

    int deleteByIds(List<Long> idList);

    int update(Order order);

    int updateByOrderCode(Order order);
}
