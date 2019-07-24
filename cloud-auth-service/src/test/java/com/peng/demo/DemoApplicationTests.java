package com.peng.demo;

import com.peng.core.redis.RedisConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Test
	public void contextLoads() {
		redisTemplate.opsForValue().set("name","lixiaopeng");
		Object name = redisTemplate.opsForValue().get("name");
		System.out.println(name);
	}

}
