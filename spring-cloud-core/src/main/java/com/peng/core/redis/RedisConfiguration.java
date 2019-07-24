package com.peng.core.redis;

import com.sun.xml.internal.ws.encoding.soap.SerializationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

@Configuration
public class RedisConfiguration {

    @Bean
    LettuceConnectionFactory lettuceConnectionFactory() {
        return new LettuceConnectionFactory(new RedisClusterConfiguration());
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
