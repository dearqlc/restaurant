package pers.qlc.restaurant.dao;

import org.apache.ibatis.annotations.Mapper;
import pers.qlc.restaurant.entity.OrderDetail;

import java.util.List;

/**
 * Created with IDEA
 * author:QLC
 * Date:2018/10/1
 * Time:23:36
 */
@Mapper
public interface OrderDetailDao {

    List<OrderDetail> findPage(OrderDetail orderDetail);

    List<OrderDetail> findPageByStatus(List<Integer> statusList);

    List<OrderDetail> findOderDetailByOdIds(List<Long> odIdList);

    int insert(List<OrderDetail> detailList);

    int deleteByIds(List<Long> odIdList);

    int deleteByOrderIds(List<Long> orderIdList);

    int update(OrderDetail orderDetail);

    int updateStatusByOdIds(OrderDetail orderDetail);

    Double countPriceByOdIds(List<Long> odIds);

}
