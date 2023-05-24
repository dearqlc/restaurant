package pers.qlc.restaurant.service;

import pers.qlc.restaurant.entity.CustomPageInfo;
import pers.qlc.restaurant.entity.OrderDetail;

import java.util.List;

/**
 * Created with IDEA
 * author:QLC
 * Date:2018/10/2
 * Time:15:08
 * <p>
 * 做菜上菜管理业务逻辑接口
 */

public interface CookAndServingService {

    /**
     * 制菜任务分页查询
     *
     * @param pageInfo
     * @return
     */
    List<OrderDetail> cookTaskFindPage(CustomPageInfo<OrderDetail> pageInfo);

    /**
     * 上菜任务分页查询
     *
     * @param pageInfo
     * @return
     */
    CustomPageInfo<OrderDetail> servingTaskFindPage(CustomPageInfo<OrderDetail> pageInfo);

    /**
     * 完成上菜
     *
     * @param orderDetail
     */
    void finishServing(OrderDetail orderDetail);

    /**
     * 将查询出的数据根据，菜品id合并汇总
     *
     * @param cookList
     */
    void cookTaskManage(List<OrderDetail> cookList);

    /**
     * 根据odId,明细id集合修改明细完成状态
     *
     * @param orderDetail
     */
    void updateStatusByOdIds(OrderDetail orderDetail);

}
