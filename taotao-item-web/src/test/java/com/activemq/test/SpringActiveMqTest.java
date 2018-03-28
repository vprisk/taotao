package com.activemq.test;

import java.io.IOException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringActiveMqTest {
	@SuppressWarnings("resource")
	@Test
	public void testConsumerSpring() throws IOException{
		//因为配置了监听，所以直接启动spring容器即可
		//初始化spring容器
		@SuppressWarnings("unused")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/springmvc-activemq.xml");
		//等待
		System.out.println("已经就绪。。");
		System.in.read();
	}
}
