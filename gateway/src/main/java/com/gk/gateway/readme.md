1. 整合你上次作业的 httpclient/okhttp。
http 执行器[httpExecutor](https://github.com/gaota0/gk/blob/main/gateway/src/main/java/com/gk/gateway/http/HttpExecutor.java)
执行器调用处[HttpInboundHandler](https://github.com/gaota0/gk/blob/main/gateway/src/main/java/com/gk/gateway/server/handler/HttpInboundHandler.java)

2. 使用 Netty 实现后端 HTTP 访问（代替上一步骤）。
client,调用网关的server端 [client](https://github.com/gaota0/gk/blob/main/gateway/src/main/java/com/gk/gateway/client/Client.java)

3. 实现过滤器。
[filter](https://github.com/gaota0/gk/tree/main/gateway/src/main/java/com/gk/gateway/server/filter)

4. 实现路由
[router](https://github.com/gaota0/gk/tree/main/gateway/src/main/java/com/gk/gateway/server/route)



- 流程介绍：用netty实现了客户端，调用服务端gateway，服务端经过请求过滤器和路由，使用okHttp调用后端服务，后端服务用bilibili和baidu的搜索链接模拟，调用完成之后，经过响应过滤器返回给客户端
- 有个问题：gateway是netty实现的服务端，对于后端服务，gateway又是客户端的角色。如果在gateway里用netty实现作为服务端接受客户端的请求，同时有作为客户端请求后端服务，这两种角色怎么配合使用呢


