package com.taotao.jedis;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class TestJedis {
	@Test
	public void testJedis(){
		Jedis jedis=new Jedis("192.168.107.182", 6379);
		jedis.set("jedis-test", "welcom to jedis");
		String str = jedis.get("jedis-test");
		System.out.println(str);
		jedis.close();
	}
	@Test
	public void testJedisPool(){
		JedisPool jedisPool=new JedisPool("192.168.107.182", 6379);
		Jedis jedis = jedisPool.getResource();
		String str = jedis.get("hello");
		System.out.println(str);
		jedis.close();
		jedisPool.close();
	}
}
