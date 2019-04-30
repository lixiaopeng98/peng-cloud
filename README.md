# peng-cloud
spring-cloud-peng
====
* cloud-auth-service &emsp;&emsp;&emsp;    权限中心 
* cloud-eureka &emsp;&emsp;&emsp;&emsp;&emsp;服务注册中心
* spring-cloud-config&emsp;&emsp;&emsp;   配置中心  各个服务都从配置中心拉取配置文件  
&emsp;&emsp;&emsp;&emsp;&emsp;使用 bus 加 @RefreshScope 加 http://localhost:7800/bus/env?goods.msg=777 实现动态刷新  
&emsp;&emsp;&emsp;&emsp;&emsp;(配置中心--git) (配置管理工具包，让你可以把配置放到远程服务器，集中化管理集群配置，目前支持本地存储、Git以及Subversion。)  
* cloud-gateway &emsp;&emsp;&emsp; 服务网关  请求转发 实现自定义的Filter√  
&emsp;&emsp;&emsp;&emsp;&emsp;还可以做这些：API开放平台必然涉及到客户应用的接入、API权限的管理、调用次数管理
* cloud-zipkin &emsp;&emsp;&emsp;&emsp; 服务链路追踪 调用、接收服务日志 cs cr ss sr
* cloud-feign-one &emsp;&emsp;&emsp;&emsp;feign-one 客户、服务端  
* cloud-feign-two &emsp;&emsp;&emsp;&emsp;feign-two 客户、服务端√
* 
    
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
&emsp;&emsp;&emsp;&emsp;动态刷新需要 @RefreshScope标签支持 和 链接式的刷新配置中心的配置  然后通过rabbitMQ通知第三方服务  
&emsp;&emsp;&emsp;&emsp; http://localhost:7800/bus/refresh?destination=cloud-feign-one:**)  
gateway 指定路径转发 请求路径前缀过滤  详细请看 cloud-gateway.yml:routes  
cloud-zipkin 需要其他服务注册到本服务中 然后通过每次请求推送来产生日志