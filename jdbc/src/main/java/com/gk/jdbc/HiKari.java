package com.gk.jdbc;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @author gaot
 * @date 2021/12/5
 */
public class HiKari {
    private static final String SQL = "insert into user values (0,?,?,?)";

    public static void main(String[] args) throws SQLException {
        HikariDataSource hikariDataSource = getDataSource();
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = hikariDataSource.getConnection();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            // 关闭自动提交
            connection.setAutoCommit(false);
            // 设置事务隔离级别 rr
            //            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

            for (int i = 0; i < 5; i++) {
                statement = connection.prepareStatement(SQL);
                statement.setString(1, "张三");
                statement.setInt(2, 12);
                statement.setString(3, "zhangsan@qq.com");
                statement.executeUpdate();
                // 模拟操作失败
//                if (i == 3) {
//                    throw new RuntimeException("事务处理失败");
//                }
            }
            // 成功提交
            connection.commit();
        } catch (SQLException e) {
            if (Objects.nonNull(connection)) {
                connection.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            if (Objects.nonNull(connection)) {
                hikariDataSource.evictConnection(connection);
                // 还原
                connection.setAutoCommit(true);
            }
        }
        final boolean autoCommit = connection.getAutoCommit();
        System.out.println("autoCommit = " + autoCommit);
    }

    private static HikariDataSource getDataSource() {
        final HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        hikariDataSource.setUsername("root");
        hikariDataSource.setPassword("root");
        hikariDataSource.setReadOnly(false);
        hikariDataSource.setConnectionTimeout(30_000);
        hikariDataSource.setIdleTimeout(60_000);
        hikariDataSource.setMaxLifetime(180_000);
        hikariDataSource.setMaximumPoolSize(60);
        hikariDataSource.setMinimumIdle(10);
        return hikariDataSource;
    }
}
