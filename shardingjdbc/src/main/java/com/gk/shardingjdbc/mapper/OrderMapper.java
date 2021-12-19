package com.gk.shardingjdbc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gk.shardingjdbc.entity.OrderEntity;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

/**
 * @author gaot
 * @date 2021/7/24
 */
public interface OrderMapper extends BaseMapper<OrderEntity> {
    @Insert("<script>"
        + "INSERT INTO `t_order` ( id, user_id, order_no, shop_no, status, product_count, amount_total, logistics_fee, address_id, logistics_id, pay_channel, trade_no, order_time, pay_time, remark ) "
        + "values" + "<foreach collection=\"list\" index=\"index\" item=\"item\"  separator=\",\">"
        + "(#{item.id},#{item.userId},#{item.orderNo},#{item.shopNo}, #{item.status},#{item.productCount},#{item.amountTotal},#{item.logisticsFee},"
        + "#{item.addressId},#{item.logisticsId},#{item.payChannel},#{item.tradeNo},#{item.orderTime},#{item.payTime},#{item.remark})"
        + "</foreach>" + "</script>")
    int batchInsert(List<OrderEntity> list);
}
