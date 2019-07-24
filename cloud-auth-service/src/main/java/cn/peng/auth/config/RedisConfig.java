package cn.peng.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class RedisConfig {

    @Value( value = "spring.redis.cluster.nodes")
    private static String redisClusterNodes;

    public static void main(String[] args) {
        //分割集群节点
        String[] cNodes = redisClusterNodes.split(",");
        //创建set集合对象
        Set<HostAndPort> nodes =new HashSet<>();
        for (String node:cNodes) {
            //127.0.0.1:7001
            String[] hp = node.split(":");
            nodes.add(new HostAndPort(hp[0],Integer.parseInt(hp[1])));
        }
        new JedisCluster(nodes);
    }
}
