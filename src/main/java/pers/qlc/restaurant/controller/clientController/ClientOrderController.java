package pers.qlc.restaurant.controller.clientController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pers.qlc.restaurant.entity.CustomPageInfo;
import pers.qlc.restaurant.entity.Order;
import pers.qlc.restaurant.entity.OrderDetail;
import pers.qlc.restaurant.entity.Result;
import pers.qlc.restaurant.service.OrderDetailService;
import pers.qlc.restaurant.service.OrderService;
import pers.qlc.restaurant.utils.ResultUtil;

@Controller
@RequestMapping("/guest/client")
public class ClientOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;

    /**
     * 添加订单
     *
     * @return
     */
    @PostMapping("/addorder.do")
    @ResponseBody
    private Result addOrder(@RequestBody Order order) {
        String orderCode = orderService.addOrder(order);
        return ResultUtil.success(orderCode);
    }

    @GetMapping("/myOrder.do")
    @ResponseBody
    private Result<OrderDetail> myOrder(CustomPageInfo info, Order order) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder(order);
        info.setT(orderDetail);
        CustomPageInfo<OrderDetail> resultInfo = orderDetailService.findPage(info);
        System.out.println(resultInfo.getList());
        return ResultUtil.success(resultInfo.getList(), resultInfo.getTotal());
    }

    @GetMapping("/myOrder.html")
    public String viewOrderDetail(@RequestParam("orderCode") String orderCode, Model model) {
        model.addAttribute("orderCode", orderCode);
        return "/client/myOrder";
    }

    @PostMapping("/delGood.do")
    @ResponseBody
    public Result<OrderDetail> deleteByOrderDetailIds(@RequestParam("ids") String ids) {
        orderDetailService.deleteByIds(ids);
        return ResultUtil.success();
    }
}
