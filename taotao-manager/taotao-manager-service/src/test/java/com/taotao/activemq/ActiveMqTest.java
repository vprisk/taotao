package com.taotao.activemq;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

public class ActiveMqTest {
	@Test
	public void testQueueProduct() throws JMSException{
		//创建一个连接工厂对象
		ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://192.168.107.182:61616");
		//使用connectionFactory创建连接对象
		Connection connection = connectionFactory.createConnection();
		//开启连接
		connection.start();
		//创建session对象
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//使用session创建Destination对象
		Queue queue = session.createQueue("test-queue");
		//使用session创建生产者
		MessageProducer producer = session.createProducer(queue);
		//创建一个消息对象
		TextMessage textMessage=session.createTextMessage("hello activeMq");
		//发送消息
		producer.send(textMessage);
		//关闭资源
		producer.close();
		session.close();
		connection.close();
	}
	@Test
	public void testQueueConsumer() throws JMSException, IOException{
		//创建一个连接工厂对象
		ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://192.168.107.182:61616");
		//使用connectionFactory创建连接对象
		Connection connection = connectionFactory.createConnection();
		//开启连接
		connection.start();
		//创建session对象
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//使用session创建Destination对象
		Queue queue = session.createQueue("test-queue");
		//使用session创建一个consumer
		MessageConsumer consumer = session.createConsumer(queue);
		System.out.println("已经就绪。。。");
		consumer.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message msg) {
				if (msg instanceof TextMessage) {
					try {
						System.out.println("接收到消息："+((TextMessage) msg).getText());
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
				
			}
		});
		System.in.read();
	}
	
	@Test
	public void testTopicConsumser() throws Exception {
		//创建一个连接工厂对象
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.107.182:61616");
		//使用连接工厂对象创建一个连接
		Connection connection = connectionFactory.createConnection();
		//开启连接
		connection.start();
		//使用连接对象创建一个Session对象
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//使用Session创建一个Destination，Destination应该和消息的发送端一致。
		Topic topic = session.createTopic("item-add-topic");
		//使用Session创建一个Consumer对象
		MessageConsumer consumer = session.createConsumer(topic);
		//向Consumer对象中设置一个MessageListener对象，用来接收消息
		consumer.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message message) {
				//取消息的内容
				if (message instanceof TextMessage) {
					TextMessage textMessage = (TextMessage) message;
					try {
						String text = textMessage.getText();
						//打印消息内容
						System.out.println(text);
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
				
			}
		});
		//系统等待接收消息
		/*while(true) {
			Thread.sleep(100);
		}*/
		System.out.println("topic消费者.。。。");
		System.in.read();
		//关闭资源
		consumer.close();
		session.close();
		connection.close();
	}
}
