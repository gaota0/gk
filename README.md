## 第八周作业
#### 2.（必做）设计对前面的订单表数据进行水平分库分表，拆分 2 个库，每个库 16 张表。并在新结构在演示常见的增删改查操作。代码、sql 和配置文件，上传到 Github。
- https://github.com/gaota0/gk/blob/main/shardingjdbc/src/main/resources/application.yml
- https://github.com/gaota0/gk/blob/main/shardingjdbc/src/test/java/com/gk/shardingjdbc/service/OrderServiceTest.java
#### 6.（必做）基于 hmily TCC 或 ShardingSphere 的 Atomikos XA 实现一个简单的分布式事务应用 demo（二选一），提交到 Github。
- 下了hmily的源码和demo看了相关代码和使用方式，细节还没了解太多