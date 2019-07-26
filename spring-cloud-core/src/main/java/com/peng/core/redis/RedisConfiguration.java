package com.peng.core.redis;

import com.sun.xml.internal.ws.encoding.soap.SerializationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

@Configuration
@PropertySource(value = "classpath:application.yml")
public class RedisConfiguration {

    @Value("${spring.redis.cluster.nodes}")
    private String nodes;

    @Bean
    LettuceConnectionFactory lettuceConnectionFactory() {

        // 集群redis
        RedisClusterConfiguration redisConfig = new RedisClusterConfiguration();
        Set<RedisNode> nodeses = new HashSet<>();
        String[] hostses = nodes.split(",");
        for (String h : hostses) {
            h = h.replaceAll("\\s", "").replaceAll("\n", "");
            if (!"".equals(h)) {
                String host = h.split(":")[0];
                int port = Integer.valueOf(h.split(":")[1]);
                nodeses.add(new RedisNode(host, port));
            }
        }
        redisConfig.setClusterNodes(nodeses);
        // 跨集群执行命令时要遵循的最大重定向数量
        redisConfig.setMaxRedirects(3);
//        redisConfig.setPassword(password);

        return new LettuceConnectionFactory(redisConfig);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(lettuceConnectionFactory);
        //序列化类
        MyRedisSerializer myRedisSerializer = new MyRedisSerializer();
        //key序列化方式
        template.setKeySerializer(myRedisSerializer);
        //value序列化
        template.setValueSerializer(myRedisSerializer);
        //value hashmap序列化
        template.setHashValueSerializer(myRedisSerializer);
        return template;
    }

    static class MyRedisSerializer implements RedisSerializer<Object> {

        @Override
        public byte[] serialize(Object o) throws SerializationException {
            return serializeObj(o);
        }

        @Override
        public Object deserialize(byte[] bytes) throws SerializationException {
            return deserializeObj(bytes);
        }

        /**
         * 序列化
         * @param object
         * @return
         */
        private static byte[] serializeObj(Object object) {
            ObjectOutputStream oos = null;
            ByteArrayOutputStream baos = null;
            try {
                baos = new ByteArrayOutputStream();
                oos = new ObjectOutputStream(baos);
                oos.writeObject(object);
                byte[] bytes = baos.toByteArray();
                return bytes;
            } catch (Exception e) {
                throw new RuntimeException("序列化失败!", e);
            }
        }

        /**
         * 反序列化
         * @param bytes
         * @return
         */
        private static Object deserializeObj(byte[] bytes) {
            if (bytes == null){
                return null;
            }
            ByteArrayInputStream bais = null;
            try {
                bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                return ois.readObject();
            } catch (Exception e) {
                throw new RuntimeException("反序列化失败!", e);
            }
        }
    }
}
