package com.gk.shardingjdbc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author gaot
 * @date 2021/7/24
 */
@Data
@TableName("t_order")
public class OrderEntity {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 总数量
     */
    private Integer amountTotal;
    /**
     * 订单时间
     */
    private Date orderTime;
    /**
     * 支付时间
     */
    private Date payTime;
}


