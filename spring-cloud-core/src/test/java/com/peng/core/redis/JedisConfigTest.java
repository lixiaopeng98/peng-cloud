package com.peng.core.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
//import redis.clients.jedis.HostAndPort;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisCluster;
//
//import java.util.HashSet;
//import java.util.Set;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JedisConfigTest {

    @Autowired
    RedisTemplate<Object,String > redisTemplate;
//    @Test
//    public void testJedisCluster() throws Exception {
//        // 第一步：使用JedisCluster对象。需要一个Set<HostAndPort>参数。Redis节点的列表。
//        Set<HostAndPort> nodes = new HashSet<>();
//        nodes.add(new HostAndPort("47.94.208.130", 7000));
//        nodes.add(new HostAndPort("47.94.208.130", 7001));
//        nodes.add(new HostAndPort("47.94.208.130", 7002));
//        nodes.add(new HostAndPort("47.94.208.130", 7003));
//        nodes.add(new HostAndPort("47.94.208.130", 7004));
//        nodes.add(new HostAndPort("47.94.208.130", 7005));
//
//        JedisCluster jedisCluster = new JedisCluster(nodes);
//
//        System.out.println("加载完毕");
//        // 第二步：直接使用JedisCluster对象操作redis。在系统中单例存在。
//        jedisCluster.set("a", "2");
//        String result = jedisCluster.get("a");
//        // 第三步：打印结果
//        System.out.println(result);
//        // 第四步：系统关闭前，关闭JedisCluster对象。
//        jedisCluster.close();
//    }

    @Test
    public void testRedis(){
        redisTemplate.opsForValue().set("name","lixiaopeng");
        System.out.println(redisTemplate.opsForValue().get("name"));
    }
}