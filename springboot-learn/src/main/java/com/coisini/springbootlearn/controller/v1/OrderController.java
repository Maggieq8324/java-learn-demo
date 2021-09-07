package com.coisini.springbootlearn.controller.v1;

import com.coisini.springbootlearn.core.annotation.ScopeLevel;
import com.coisini.springbootlearn.core.common.LocalUser;
import com.coisini.springbootlearn.core.common.PageCounter;
import com.coisini.springbootlearn.core.common.PagingDozer;
import com.coisini.springbootlearn.dto.OrderDTO;
import com.coisini.springbootlearn.exception.http.NotFoundException;
import com.coisini.springbootlearn.logic.OrderChecker;
import com.coisini.springbootlearn.model.Order;
import com.coisini.springbootlearn.service.OrderService;
import com.coisini.springbootlearn.util.CommonUtil;
import com.coisini.springbootlearn.vo.OrderIdVo;
import com.coisini.springbootlearn.vo.OrderPureVo;
import com.coisini.springbootlearn.vo.OrderSimplifyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @Description Order 控制器
 * @author coisini
 * @date Aug 21, 2021
 * @Version 1.0
 */
@RestController
@RequestMapping("/order")
@Validated
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Value("${coisini.order.pay-time-limit}")
    private Long payTimeLimit;


    /**
     * 下单
     * @param orderDTO
     * @return
     */
    @PostMapping("")
    @ScopeLevel()
    public OrderIdVo placeOrder(@RequestBody OrderDTO orderDTO) {
        Long uid = LocalUser.getUser().getId();
        OrderChecker orderChecker = orderService.isOk(uid, orderDTO);

        Long oid = this.orderService.placeOrder(uid, orderDTO, orderChecker);
        return new OrderIdVo(oid);
    }

    /**
     * 获取待支付订单
     * @param start
     * @param count
     * @return
     */
    @ScopeLevel
    @GetMapping("/status/unpaid")
    @SuppressWarnings("unchecked")
    public PagingDozer getUnpaid(@RequestParam(defaultValue = "0") Integer start,
                                 @RequestParam(defaultValue = "10") Integer count) {
        PageCounter page = CommonUtil.convertToPageParameter(start, count);
        Page<Order> orderPage = orderService.getUnpaid(page.getPage(), page.getCount());
        PagingDozer pagingDozer = new PagingDozer<>(orderPage, OrderSimplifyVo.class);
        pagingDozer.getData().forEach((o) -> ((OrderSimplifyVo) o).setPeriod(this.payTimeLimit));
        return pagingDozer;
    }

    /**
     * 根据状态获取订单
     * @param status 订单状态
     * @param start
     * @param count
     * @return
     */
    @ScopeLevel
    @GetMapping("/by/status/{status}")
    public PagingDozer getByStatus(@PathVariable int status,
                                   @RequestParam(name = "start", defaultValue = "0") Integer start,
                                   @RequestParam(name = "count", defaultValue = "10") Integer count) {
        PageCounter page = CommonUtil.convertToPageParameter(start, count);
        Page<Order> paging = orderService.getByStatus(status, page.getPage(), page.getCount());
        PagingDozer pagingDozer = new PagingDozer<>(paging, OrderSimplifyVo.class);
        pagingDozer.getData().forEach(o -> ((OrderSimplifyVo) o).setPeriod(this.payTimeLimit));
        return pagingDozer;
    }

    @ScopeLevel
    @GetMapping("/detail/{id}")
    public OrderPureVo getOrderDetail(@PathVariable(name = "id") Long oid) {
        Optional<Order> orderOptional = orderService.getOrderDetail(oid);
        return orderOptional.map((o) -> new OrderPureVo(o, payTimeLimit))
                .orElseThrow(() -> new NotFoundException(50009));
    }

}
