package com.clyon.service.rabbitMQ;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clyon.bean.rabbitMQ.MsgDTO;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

@Service
public class RabbitMQTopicsService {

	public static final String QUEUE_NAME1 = "queueTopics1";

	public static final String QUEUE_NAME2 = "queueTopics2";

	public static final String EXCHANGE_TOPICS = "exchangeTopics";

	public static final String TOPICS_EMAIL = "inform.#.email.#";

	public static final String TOPICS_SMS = "inform.#.sms.#";

	@Transactional(rollbackFor = Exception.class)
	public void producer(MsgDTO dto) throws Exception {

		// 通过连接工厂创建新连接和RabbitMQ建立连接
		ConnectionFactory connectionFactory = new ConnectionFactory();
		// 设置连接
		connectionFactory.setHost("120.24.60.74");
		connectionFactory.setPort(5672);
		// 设置用户名和密码
		connectionFactory.setUsername("cly");
		connectionFactory.setPassword("cly");
		// 设置虚拟机
		connectionFactory.setVirtualHost("/");

		Connection connection = null;
		Channel channel = null;

		try {
			// 建立新连接
			connection = connectionFactory.newConnection();

			// 建立通道
			channel = connection.createChannel();

			/**
			 * 声明交换机 String exchange, BuiltinExchangeType type 参数明细 1、交换机名称
			 * 2、交换机类型，fanout、topic、direct、headers
			 */
			channel.exchangeDeclare(EXCHANGE_TOPICS, BuiltinExchangeType.TOPIC);

			/**
			 * 声明队列，如果Rabbit中没有此队列将自动创建 String queue, boolean durable, boolean exclusive,
			 * boolean autoDelete,Map<String, Object> arguments param1:队列名称 param2:是否持久化
			 * param3:队列是否独占此连接 param4:队列不再使用时是否自动删除此队列 param5:队列参数
			 */
			channel.queueDeclare(QUEUE_NAME1, true, false, false, null);
			channel.queueDeclare(QUEUE_NAME2, true, false, false, null);

			/**
			 * 交换机和队列绑定String queue, String exchange, String TopicsKey 参数明细 1、队列名称 2、交换机名称
			 * 3、路由key
			 */
			channel.queueBind(QUEUE_NAME1, EXCHANGE_TOPICS, TOPICS_EMAIL);
			channel.queueBind(QUEUE_NAME2, EXCHANGE_TOPICS, TOPICS_SMS);

			/**
			 * 发布消息 String exchange, String TopicsKey, BasicProperties props, byte[] body
			 * param1：Exchange的名称，如果没有指定，则使用Default Exchange
			 * param2:TopicsKey,消息的路由Key，是用于Exchange（交换机）将消息转发到指定的消息队列，使用默认则为队列名称
			 * param3:消息包含的属性 param4：消息体
			 */
			if (dto.getType().equals("inform")) {
				for (int i = 0; i < 2; i++) {
					String msg = dto.getMsg();
					channel.basicPublish(EXCHANGE_TOPICS, "inform.email.sms", null, ("通知" + msg).getBytes());
				}
			}
			if (dto.getType().equals("email")) {
				for (int i = 0; i < 2; i++) {
					String msg = dto.getMsg();
					channel.basicPublish(EXCHANGE_TOPICS, "inform.email", null, ("邮件" + msg).getBytes());
				}
			}
			if (dto.getType().equals("sms")) {
				for (int i = 0; i < 2; i++) {
					String msg = dto.getMsg();
					channel.basicPublish(EXCHANGE_TOPICS, "inform.sms", null, ("短信" + msg).getBytes());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 先关闭通道
			try {
				channel.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (TimeoutException e) {
				e.printStackTrace();
			}
			// 再关闭连接
			connection.close();
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public void consumer1() throws Exception {

		Connection connection = null;
		Channel channel = null;

		try {
			// 通过连接工厂创建新连接和RabbitMQ建立连接
			ConnectionFactory connectionFactory = new ConnectionFactory();
			// 设置连接
			connectionFactory.setHost("120.24.60.74");
			connectionFactory.setPort(5672);
			// 设置用户名和密码
			connectionFactory.setUsername("cly");
			connectionFactory.setPassword("cly");
			// 设置虚拟机
			connectionFactory.setVirtualHost("/");
			// 创建与RabbitMQ服务的TCP连接
			connection = connectionFactory.newConnection();
			// 建立通道
			channel = connection.createChannel();

			/**
			 * 声明交换机 String exchange, BuiltinExchangeType type
			 */
			channel.exchangeDeclare(EXCHANGE_TOPICS, BuiltinExchangeType.TOPIC);

			// 声明队列
			channel.queueDeclare(QUEUE_NAME1, true, false, false, null);

			// 交换机和队列绑定
			channel.queueBind(QUEUE_NAME1, EXCHANGE_TOPICS, TOPICS_EMAIL);

			// 定义消费方法
			DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {

				/**
				 * 消费者接收消息调用此方法
				 * 
				 * @param consumerTag 消费者的标签，在channel.basicConsume()去指定
				 * @param envelope    消息包的内容，可从中获取消息id，消息Topicskey，交换机，消息和重传标志(收到消息失败后是否需要重新发送)
				 * @param properties
				 * @param body
				 */
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
						byte[] body) throws IOException {

					// 交换机
					String exchange = envelope.getExchange();
					// 路由key
					String routingKey = envelope.getRoutingKey();
					// 消息id
					long deliveryTag = envelope.getDeliveryTag();
					// 消息内容
					String msg = new String(body, "utf-8");
					System.out
							.println("收到（交换机）" + exchange + "/（路由key）" + routingKey + "发来的邮件（id）" + deliveryTag + msg);
				}
			};

			/**
			 * 监听队列String queue, boolean autoAck,Consumer callback 参数明细 1、队列名称
			 * 2、是否自动回复，设置为true为表示消息接收到自动向mq回复接收到了，mq接收到回复会删除消息，设置 为false则需要手动回复
			 * 3、消费消息的方法，消费者接收到消息后调用此方法
			 */
			channel.basicConsume(QUEUE_NAME1, true, defaultConsumer);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Transactional(rollbackFor = Exception.class)
	public void consumer2() throws Exception {

		Connection connection = null;
		Channel channel = null;

		try {
			// 通过连接工厂创建新连接和RabbitMQ建立连接
			ConnectionFactory connectionFactory = new ConnectionFactory();
			// 设置连接
			connectionFactory.setHost("120.24.60.74");
			connectionFactory.setPort(5672);
			// 设置用户名和密码
			connectionFactory.setUsername("cly");
			connectionFactory.setPassword("cly");
			// 设置虚拟机
			connectionFactory.setVirtualHost("/");
			// 创建与RabbitMQ服务的TCP连接
			connection = connectionFactory.newConnection();
			// 建立通道
			channel = connection.createChannel();

			/**
			 * 声明交换机 String exchange, BuiltinExchangeType type
			 */
			channel.exchangeDeclare(EXCHANGE_TOPICS, BuiltinExchangeType.TOPIC);

			// 声明队列
			channel.queueDeclare(QUEUE_NAME2, true, false, false, null);

			// 交换机和队列绑定
			channel.queueBind(QUEUE_NAME2, EXCHANGE_TOPICS, TOPICS_SMS);

			// 定义消费方法
			DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {

				/**
				 * 消费者接收消息调用此方法
				 * 
				 * @param consumerTag 消费者的标签，在channel.basicConsume()去指定
				 * @param envelope    消息包的内容，可从中获取消息id，消息Topicskey，交换机，消息和重传标志(收到消息失败后是否需要重新发送)
				 * @param properties
				 * @param body
				 */
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
						byte[] body) throws IOException {

					// 交换机
					String exchange = envelope.getExchange();
					// 路由key
					String routingKey = envelope.getRoutingKey();
					// 消息id
					long deliveryTag = envelope.getDeliveryTag();
					// 消息内容
					String msg = new String(body, "utf-8");
					System.out
							.println("收到（交换机）" + exchange + "/（路由key）" + routingKey + "发来的短信（id）" + deliveryTag + msg);
				}
			};

			/**
			 * 监听队列String queue, boolean autoAck,Consumer callback 参数明细 1、队列名称
			 * 2、是否自动回复，设置为true为表示消息接收到自动向mq回复接收到了，mq接收到回复会删除消息，设置 为false则需要手动回复
			 * 3、消费消息的方法，消费者接收到消息后调用此方法
			 */
			channel.basicConsume(QUEUE_NAME2, true, defaultConsumer);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
