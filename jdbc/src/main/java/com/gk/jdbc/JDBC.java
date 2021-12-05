package com.gk.jdbc;

import java.sql.*;
import java.util.Objects;
import java.util.Optional;

/**
 * @author gaot
 * @date 2021/12/5
 */
public class JDBC {
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
            statement = connection.prepareStatement(SQL);
            statement.setString(1, "张三");
            statement.setInt(2, 12);
            statement.setString(3, "zhangsan@qq.com");
            final int updateCount = statement.executeUpdate();
            System.out.println(updateCount);
        } catch (Exception e) {
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
