package pers.qlc.restaurant.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.qlc.restaurant.Enums.ResultEnum;
import pers.qlc.restaurant.dao.OrderDao;
import pers.qlc.restaurant.dao.OrderDetailDao;
import pers.qlc.restaurant.entity.CustomPageInfo;
import pers.qlc.restaurant.entity.Order;
import pers.qlc.restaurant.entity.OrderDetail;
import pers.qlc.restaurant.exception.CustomException;
import pers.qlc.restaurant.service.OrderDetailService;
import pers.qlc.restaurant.utils.SplitIdsUtil;

import java.util.Date;
import java.util.List;

/**
 * 订单明细业务逻辑类
 */
@Service
@Transactional
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailDao orderDetailDao;
    @Autowired
    private OrderDao orderDao;

    /**
     * 根据订单id查询订单明细
     *
     * @param orderId
     * @return
     */
    @Override
    public List<OrderDetail> findByOrderId(Long orderId) {
        OrderDetail orderDetail = new OrderDetail();
        Order order = new Order();
        order.setOrderId(orderId);
        orderDetail.setOrder(order);
        return orderDetailDao.findPage(orderDetail);
    }

    /**
     * 分页查询订单明细
     *
     * @param pageInfo
     * @return
     */
    @Override
    public CustomPageInfo<OrderDetail> findPage(CustomPageInfo<OrderDetail> pageInfo) {
        Page page = PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        orderDetailDao.findPage(pageInfo.getT());
        return new CustomPageInfo<>(page);
    }

    /**
     * 根据明细id删除订单明细
     *
     * @param strIds
     */
    @Override
    public void deleteByIds(String strIds) {
        List<Long> idList = SplitIdsUtil.splitStrIds(strIds);
        //计算删除菜品的总价格
        Double minusPrice = orderDetailDao.countPriceByOdIds(idList);
        OrderDetail orderDetail = new OrderDetail();
        //获取订单明细id
        orderDetail.setOdId(idList.get(0));
        //查询当前订单id
        List<OrderDetail> orderDetailList = orderDetailDao.findPage(orderDetail);
        Long orderId = orderDetailList.get(0).getOrder().getOrderId();
        //查询当前订单信息
        Order findOrder = orderDao.findById(orderId);
        /**
         * 修改订单的总价格
         */
        Order updateOrder = new Order();
        updateOrder.setOrderId(findOrder.getOrderId());
        updateOrder.setTotalPrice(findOrder.getTotalPrice() - minusPrice);
        updateOrder.setModifyTime(new Date());
        orderDao.update(updateOrder);
        //删除订单明细
        int effect = orderDetailDao.deleteByIds(idList);
        if (effect <= 0) {
            throw new CustomException(ResultEnum.DEL_DB_FAIL);
        }
    }

    /**
     * 根据桌号查询订单明细
     *
     * @param deskCode
     * @return
     */
    @Override
    public List<OrderDetail> findOrderDetailsByDeskCode(String deskCode) {
        OrderDetail orderDetail = new OrderDetail();
        Order order = new Order();
        //设置桌号
        order.setDeskCode(deskCode);
        //未支付的
        order.setPayStatus(0);
        orderDetail.setOrder(order);
        return orderDetailDao.findPage(orderDetail);
    }
}
