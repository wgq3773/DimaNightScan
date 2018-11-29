package com.dima.DimaNightScan.redis;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dima.commons.redis.impl.JedisClientPool;

public class RedisTest {

	@Test
	public void testRedis() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-redis.xml");
		context.start();
		JedisClientPool jedisClientPool = (JedisClientPool) context.getBean("jedisClientPool");
		String string = jedisClientPool.get("hello");
		System.out.println(string);
		
		jedisClientPool.hset("key1", "field1", "value1");
		String hget = jedisClientPool.hget("key1", "field1");
		System.out.println(hget);
		
		jedisClientPool.expire("key1", 5);
	}
}
