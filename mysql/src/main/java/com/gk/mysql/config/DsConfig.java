package com.gk.mysql.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.gk.mysql.constant.DsRouteConstant;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gaot
 * @date 2021/7/24
 */
@Configuration
public class DsConfig {

    @Bean(DsRouteConstant.MASTER)
    public DataSource masterDs() {
        final HikariDataSource ds = new HikariDataSource();
        ds.setUsername("root");
        ds.setPassword("root");
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/test");
        return ds;
    }

    @Bean(DsRouteConstant.SLAVE)
    public DataSource slaveDs() {
        final HikariDataSource ds = new HikariDataSource();
        ds.setUsername("root");
        ds.setPassword("root");
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setJdbcUrl("jdbc:mysql://127.0.0.1:3307/test");
        return ds;
    }

    @Primary
    @Bean(DsRouteConstant.DYNAMIC)
    public DataSource dynamicDs() {
        final DynamicDataSource dynamicDataSource = new DynamicDataSource();
        final Map<Object, Object> dsMap = new HashMap<>();
        dsMap.put(DsRouteConstant.MASTER, masterDs());
        dsMap.put(DsRouteConstant.SLAVE, slaveDs());
        dynamicDataSource.setTargetDataSources(dsMap);
        dynamicDataSource.setDefaultTargetDataSource(masterDs());
        return dynamicDataSource;
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dynamicDs());

        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        //是否使用转驼峰
        sqlSessionFactory.setConfiguration(configuration);

        //扫描 mapper 路径
        return sqlSessionFactory.getObject();
    }

    @Bean(name = "multipleTransactionManager")
    @Primary
    public DataSourceTransactionManager multipleTransactionManager() {
        return new DataSourceTransactionManager(dynamicDs());
    }
}
