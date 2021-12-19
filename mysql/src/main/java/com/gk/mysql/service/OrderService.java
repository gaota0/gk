package com.gk.mysql.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gk.mysql.aspect.DS;
import com.gk.mysql.constant.DsRouteConstant;
import com.gk.mysql.entity.OrderEntity;
import com.gk.mysql.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author gaot
 * @date 2021/7/24
 */
@Service
@Slf4j
public class OrderService extends ServiceImpl<OrderMapper, OrderEntity> {
    public void clearTable() {
        this.baseMapper.delete(new QueryWrapper<>());
    }

    @DS(value = DsRouteConstant.MASTER)
    public OrderEntity queryById(Long id) {
        return this.baseMapper.selectById(id);
    }

    @DS(value = DsRouteConstant.SLAVE)
    public OrderEntity queryByNo(String no) {
        return this.lambdaQuery().eq(OrderEntity::getOrderNo, no).list().stream().findAny().orElse(null);
    }
}
