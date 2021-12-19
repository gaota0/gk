package com.gk.shardingjdbc.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gk.shardingjdbc.entity.OrderEntity;
import com.gk.shardingjdbc.mapper.OrderMapper;
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

    public OrderEntity queryById(Long id) {
        return this.baseMapper.selectById(id);
    }

    public OrderEntity queryByNo(String no) {
        return this.lambdaQuery().eq(OrderEntity::getOrderNo, no).list().stream().findAny().orElse(null);
    }
}
