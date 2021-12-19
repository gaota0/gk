package com.gk.shardingjdbc.service;

import com.gk.shardingjdbc.entity.OrderEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author gaot
 * @date 2021/7/24
 */
@SpringBootTest
@Slf4j
class OrderServiceTest {
    @Autowired
    private OrderService orderService;
    @Test
    public void testSharding() {
        IntStream.range(0, 10).mapToObj(this::buildEntity).forEach(orderEntity -> orderService.getBaseMapper().insert(orderEntity));
    }
    private OrderEntity buildEntity(Integer id) {
        final OrderEntity orderEntity = new OrderEntity();
        final int randomInt = RandomUtils.nextInt();
        orderEntity.setId(id);
        orderEntity.setUserId(randomInt);
        orderEntity.setOrderNo(randomInt+"");
        orderEntity.setStatus(1);
        orderEntity.setAmountTotal(randomInt);
        orderEntity.setOrderTime(new Date());
        orderEntity.setPayTime(new Date());
        return orderEntity;
    }
}