## 第五周作业
- [1.（选做）使 Java 里的动态代理，实现一个简单的 AOP](https://github.com/gaota0/gk/blob/main/spring/src/main/java/com/gk/spring/aop/JdkDynamicProxy.java)
- [2.（必做）写代码实现 Spring Bean 的装配，方式越多越好（XML、Annotation 都可以）, 提交到 GitHub。](https://github.com/gaota0/gk/tree/main/spring/src/main/java/com/gk/spring/bean/config)
- [4.4 （中级挑战）尝试使用 ByteBuddy 实现一个简单的基于类的 AOP；](https://github.com/gaota0/gk/blob/main/spring/src/main/java/com/gk/spring/aop/ByteBuddyServiceProxy.java)
- [5.（选做）总结一下，单例的各种写法，比较它们的优劣。](https://github.com/gaota0/gk/tree/main/spring/src/main/java/com/gk/spring/singleton)
    - 饿汉，枚举，DCL，静态内部类都是线程安全的单例实现
    - 静态内部类和DCL懒加载，饿汉枚举反之
    - 可以把bean直接交给spring管理，默认就是单例的
- 6（选做）maven/spring 的 profile 机制，都有什么用法？
    - maven的profile 定义在pom.xml,在不同的打包环境中切换profile，替换打包文件中的环境变量，如开发环境package打包文件名以SNAPSHOT结尾，线上环境打包RELEASE结尾
    - spring的profile 一般也用于在不同环境激活不同的配置，如常用的中间件，redis，mysql配置等
- 7.（选做）总结 Hibernate 与 MyBatis 的各方面异同点。
    - 相同点：都是ORM框架内，封装了java程序对数据库操作的细节，自动的orm映射，缓存和其他扩展机制
    - 不同点：hibernate 可以让用户无需关心sql，O/R映射能力强，自动生成sql，移植性好；mybatis 半自动的ORM框架，一般需要用户自行完成sql，sql优化可以更细致
- [8.（必做）给前面课程提供的 Student/Klass/School 实现自动配置和 Starter。](https://github.com/gaota0/gk/tree/main/springboot-starter)
- [10.（必做）研究一下 JDBC 接口和数据库连接池，掌握它们的设计和用法：](https://github.com/gaota0/gk/tree/main/jdbc/src/main/java/com/gk/jdbc)


