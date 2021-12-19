package com.gk.mysql.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gk.mysql.entity.OrderEntity;
import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.checkerframework.checker.index.qual.SameLen;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author gaot
 * @date 2021/7/24
 */
@SpringBootTest
@Slf4j
class OrderServiceTest {
    ThreadPoolExecutor threadPoolExecutor =
        new ThreadPoolExecutor(16, 16, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<>(10000));
    @Resource
    private OrderService orderService;

    @Autowired
    private DataSource dataSource;
    @Test
    public void testQueryById() {
        System.out.println(orderService.queryById(1L));
    }
    @Test
    public void testQueryByNo() {
        System.out.println(orderService.queryByNo("450085268"));
    }

    @Test
    public void insetOrderTest() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");

        clearTable();
        final List<OrderEntity> list =
            IntStream.range(0, 1_000_000).mapToObj(this::buildEntity).collect(Collectors.toList());

        final long start = System.currentTimeMillis();

        Lists.partition(list, 100000).stream().map(p -> CompletableFuture.runAsync(() -> {
                insertByJdbc(p);
            }, threadPoolExecutor)).collect(Collectors.toList()).stream().map(CompletableFuture::join)
            .collect(Collectors.toList());

        final long cost = System.currentTimeMillis() - start;
        log.info("耗时{}ms", cost);
    }

    @SneakyThrows
    private void insertByJdbc(List<OrderEntity> list) {
        final Connection connection = dataSource.getConnection();
        final String sql =
            "INSERT INTO test.t_order (id, user_id, order_no, status, amount_total, order_time, pay_time) VALUES"
                + " ( ?, ?, ?, ?, ?, ?, ? )";
        final PreparedStatement ps = connection.prepareStatement(sql);

        for (OrderEntity e : list) {
            ps.setInt(1, e.getId());
            ps.setInt(2, e.getUserId());
            ps.setString(3, e.getOrderNo());
            ps.setInt(4, e.getStatus());
            ps.setInt(5, e.getAmountTotal());
            ps.setTimestamp(6, new Timestamp(e.getOrderTime().getTime()));
            ps.setTimestamp(7, new Timestamp(e.getPayTime().getTime()));
            ps.addBatch();
        }
        ps.executeBatch();
        connection.close();

    }

    private void clearTable() {
        log.info("清理数据....");
        orderService.clearTable();
        log.info("清理数据完成");
        int existCount = orderService.count();
        log.info("清理完成查询结果:{}", existCount);
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