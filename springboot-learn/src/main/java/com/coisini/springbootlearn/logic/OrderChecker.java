package com.coisini.springbootlearn.logic;

import com.coisini.springbootlearn.bo.SkuOrderBo;
import com.coisini.springbootlearn.dto.OrderDTO;
import com.coisini.springbootlearn.dto.SkuInfoDTO;
import com.coisini.springbootlearn.exception.http.ParameterException;
import com.coisini.springbootlearn.model.OrderSku;
import com.coisini.springbootlearn.model.Sku;
import lombok.Getter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Description Order 校验
 * @author coisini
 * @date Aug 21, 2021
 * @Version 1.0
 */
public class OrderChecker {

    private OrderDTO orderDTO;
    private List<Sku> serverSkuList;
    private CouponChecker couponChecker;
    private Integer maxSkuLimit;

    @Getter
    private List<OrderSku> orderSkuList = new ArrayList<>();

    /**
     *
     * @param orderDTO 前端提交订单数据
     * @param serverSkuList 后端读取skuList
     * @param couponChecker 优惠劵校验
     * @param maxSkuLimit 最大购买数量
     */
    public OrderChecker(OrderDTO orderDTO, List<Sku> serverSkuList,
                        CouponChecker couponChecker, Integer maxSkuLimit) {
        this.orderDTO = orderDTO;
        this.serverSkuList = serverSkuList;
        this.couponChecker = couponChecker;
        this.maxSkuLimit = maxSkuLimit;
    }

    /**
     * 获取订单主图片
     * @return
     */
    public String getLeaderImg() {
        return serverSkuList.get(0).getImg();
    }

    /**
     * 获取订单主标题
     * @return
     */
    public String getLeaderTitle() {
        return serverSkuList.get(0).getTitle();
    }

    /**
     * 获取订单总价
     * @return
     */
    public Integer getTotalCount() {
        return orderDTO.getSkuInfoList().stream()
                                        .map(SkuInfoDTO::getCount)
                                        .reduce(Integer::sum)
                                        .orElse(0);
    }

    /**
     * 订单校验
     */
    public void isOk() {

        // 服务端总价格
        BigDecimal serverTotalPrice = new BigDecimal("0");
        List<SkuOrderBo> skuOrderBoList = new ArrayList<>();

        /**
         * 下架sku判断
         */
        this.skuNotOnSale(orderDTO.getSkuInfoList().size(), this.serverSkuList.size());

        for (int i = 0; i < this.serverSkuList.size(); i++) {
            // 后端Sku
            Sku sku = serverSkuList.get(i);
            // 前端Sku
            SkuInfoDTO skuInfoDTO = orderDTO.getSkuInfoList().get(i);

            // Sku是否售罄
            this.containsSoldOutSku(sku);
            // Sku是否超卖
            this.beyondSkuStock(sku, skuInfoDTO);
            // 最大购买数量校验
            this.beyondMaxSkuLimit(skuInfoDTO);

            serverTotalPrice = serverTotalPrice.add(calculateSkuOrderPrice(sku, skuInfoDTO));
            skuOrderBoList.add(new SkuOrderBo(sku, skuInfoDTO));
            this.orderSkuList.add(new OrderSku(sku, skuInfoDTO));
        }

        // 总价格校验
        this.totalPriceIsOk(orderDTO.getTotalPrice(), serverTotalPrice);

        /**
         * 优惠劵校验
         */
        if (Objects.nonNull(couponChecker)) {
            // 优惠劵是否可用
            couponChecker.isOk();
            // 是否可使用检测
            couponChecker.canBeUsed(skuOrderBoList, serverTotalPrice);
            // 最终价格检测
            couponChecker.finalTotalPriceIsOk(orderDTO.getFinalTotalPrice(), serverTotalPrice);
        }
    }

    /**
     * 总价格校验
     * @param orderTotalPrice 前端总原价
     * @param serverTotalPrice 服务端计算总原价
     */
    private void totalPriceIsOk(BigDecimal orderTotalPrice, BigDecimal serverTotalPrice) {
        if (orderTotalPrice.compareTo(serverTotalPrice) != 0) {
            throw new ParameterException(50005);
        }
    }

    /**
     * Sku总价格计算
     * @param sku
     * @param skuInfoDTO
     * @return
     */
    private BigDecimal calculateSkuOrderPrice(Sku sku, SkuInfoDTO skuInfoDTO) {
        if (skuInfoDTO.getCount() <= 0) {
            throw new ParameterException(50007);
        }

        return sku.getActualPrice().multiply(new BigDecimal(skuInfoDTO.getCount()));
    }

    /**
     * Sku销售检验
     * 前后端sku数量校验
     * @param count1 前端Sku数量
     * @param count2 后端Sku数量
     */
    private void skuNotOnSale(int count1, int count2) {
        if (count1 != count2) {
            throw new ParameterException(50002);
        }
    }

    /**
     * Sku是否售罄
     * @param sku
     */
    private void containsSoldOutSku(Sku sku) {
        if (sku.getStock() == 0) {
            throw new ParameterException(50001);
        }
    }

    /**
     * Sku是否超卖
     * @param sku
     * @param skuInfoDTO
     */
    private void beyondSkuStock(Sku sku, SkuInfoDTO skuInfoDTO) {
        /**
         * 库存量 < 购买数量
         */
        if (sku.getStock() < skuInfoDTO.getCount()) {
            throw new ParameterException(50003);
        }
    }

    /**
     * 最大购买数量校验
     * @param skuInfoDTO
     */
    private void beyondMaxSkuLimit(SkuInfoDTO skuInfoDTO) {
        if (skuInfoDTO.getCount() > maxSkuLimit) {
            throw new ParameterException(50004);
        }
    }

}
