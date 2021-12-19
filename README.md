## 第七周作业
#### 2.（必做）按自己设计的表结构，插入 100 万订单模拟数据，测试不同方式的插入效率
- 这个题参考了网上一些实现，开启了 jdbc连接的`rewriteBatchedStatements=true`这个参数
- 使用了Hikari连接池，并自定义了线程池并行插入
- 把表字段减少到7个，删除所有索引，1_000_000插入时间在12s左右
- 不知道还有什么更快的方式，希望老师指正下哈
#### 9.（必做）读写分离 - 动态切换数据源版本 1.0
- 此题主要是实现`AbstractRoutingDataSource`接口，给db操作方法加上切面，对操作数据源进行路由
- https://github.com/gaota0/gk/blob/main/mysql/src/main/java/com/gk/mysql/config/DynamicDataSource.java
- https://github.com/gaota0/gk/blob/main/mysql/src/main/java/com/gk/mysql/config/DsConfig.java
- https://github.com/gaota0/gk/blob/main/mysql/src/main/java/com/gk/mysql/aspect/DsRouteAspect.java

###  10.（必做）读写分离 - 数据库框架版本 2.0
- 集成sharding-jdbc 完成读写分离和分库分表
- 主要是配置，代码无其他变化
- https://github.com/gaota0/gk/blob/main/shardingjdbc/src/main/resources/application.yml
