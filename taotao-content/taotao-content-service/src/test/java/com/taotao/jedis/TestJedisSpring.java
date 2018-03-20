package com.taotao.jedis;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试jedis在spring中注入
 * 使用接口，方便切换单列或者集群
 * @author Administrator
 *
 */
public class TestJedisSpring {
	@SuppressWarnings("resource")
	@Test
	public void testJedis(){
		ApplicationContext context=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
		JedisClient jedisClient = context.getBean(JedisClient.class);
		String str = jedisClient.get("jedis-test");
		System.out.println(str);
	}
}
