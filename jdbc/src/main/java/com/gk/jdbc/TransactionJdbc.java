package com.gk.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @author gaot
 * @date 2021/12/5
 */
public class TransactionJdbc {
    private static final String SQL = "insert into user values (0,?,?,?)";

    /**
     * <pre>
     * create table user
     * (
     *  id    int auto_increment  primary key,
     *  name  varchar(50) null,
     *  age   int         null,
     *  email varchar(50) null
     * );
     * </pre>
     *
     * @param args
     */
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
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
                if (i == 3) {
                    throw new RuntimeException("事务处理失败");
                }
            }
            // 成功提交
            connection.commit();
        } catch (Exception e) {
            try {
                if (Objects.nonNull(connection)) {
                    // 回滚事务
                    connection.rollback();
                }
            } catch (SQLException ex) {
                // do nothing
            }
            throw new RuntimeException(e);
        } finally {
            try {
                if (Objects.nonNull(statement)) {
                    statement.close();
                }
                if (Objects.nonNull(connection)) {
                    connection.close();
                }
            } catch (SQLException e) {
                // do nothing
            }

        }
    }
}
