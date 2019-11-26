package com.clyon.service.rabbitMQ;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clyon.bean.rabbitMQ.MsgDTO;
import com.clyon.config.RabbitMQConfig;
import com.rabbitmq.client.Channel;

@Service
public class RabbitMQSPDService {

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Transactional(rollbackFor = Exception.class)
	public void producer(MsgDTO dto) throws Exception {
		if (dto.getType().equals("inform")) {
			for (int i = 0; i < 2; i++) {
				String msg = dto.getMsg();
				rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_TOPICS, "inform.email.sms", "通知" + msg);
			}
		}
		if (dto.getType().equals("email")) {
			for (int i = 0; i < 2; i++) {
				String msg = dto.getMsg();
				rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_TOPICS, "inform.email", "邮件" + msg);
			}
		}
		if (dto.getType().equals("sms")) {
			for (int i = 0; i < 2; i++) {
				String msg = dto.getMsg();
				rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_TOPICS, "inform.sms", "短信" + msg);
			}
		}

	}

	@Transactional(rollbackFor = Exception.class)
	@RabbitListener(queues = { RabbitMQConfig.QUEUE_NAME1 })
	public void consumer1(String msg, Message message, Channel channel) throws Exception {
		System.out.println("由1号队列发来的消息（id）" + msg);
	}

	@Transactional(rollbackFor = Exception.class)
	@RabbitListener(queues = { RabbitMQConfig.QUEUE_NAME2 })
	public void consumer2(String msg, Message message, Channel channel) throws Exception {
		System.out.println("由2号队列发来的消息（id）" + msg);
	}

}
