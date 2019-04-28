# peng-cloud
spring-cloud-peng
====
目前想到的研究方向
* eureka
* feign (Hystrix熔断)
* config   
    (配置中心--git) (配置管理工具包，让你可以把配置放到远程服务器，集中化管理集群配置，目前支持本地存储、Git以及Subversion。)
* gateway zuul (动态路由 权限控制)
* security (maybe) oauth2
* sleuth   
    (日志收集工具包，封装了Dapper和log-based追踪以及Zipkin和HTrace操作，为SpringCloud应用实现了一种分布式追踪解决方案。)
* bus 
    (消息总线)(事件、消息总线，用于在集群（例如，配置变化事件）中传播状态变化，可与Spring Cloud Config联合实现热部署。)
    
另外还有
+ rabbitMQ
+ zookeeper
+ h2
+ redis
+ 数据库的分库分表
+ 高并发的场景
+ hadoop
+ mongodb
+ memcached

已完成  
eureka  feign config gateway  
eureka (注册中心)  
feign+hystrix(客户端请求，加fallback，加hystrix熔断)  
config(配置中心服务 所有的服务都需要在config service请求配置文件
        动态刷新需要 @RefreshScope标签支持 和 链接式的刷新配置中心的配置  然后通过rabbitMQ通知第三方服务
        http://localhost:7800/bus/refresh?destination=cloud-feign-one:**)  
gateway 指定路径转发 请求路径前缀过滤  详细请看 cloud-gateway.yml:routes