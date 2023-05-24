package pers.qlc.restaurant.service;

import pers.qlc.restaurant.entity.CustomPageInfo;
import pers.qlc.restaurant.entity.OrderDetail;

import java.util.List;

/**
 * 订单明细业务逻辑接口
 */
public interface OrderDetailService {

    /**
     * 根据订单id查询订单明细
     *
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrderId(Long orderId);

    /**
     * 分页查询订单明细
     *
     * @param pageInfo
     * @return
     */
    CustomPageInfo<OrderDetail> findPage(CustomPageInfo<OrderDetail> pageInfo);

    /**
     * 根据id删除订单明细
     *
     * @param strIds
     */
    void deleteByIds(String strIds);

    List<OrderDetail> findOrderDetailsByDeskCode(String deskCode);

}
