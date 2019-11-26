package com.clyon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clyon.bean.rabbitMQ.MsgDTO;
import com.clyon.common.RespData;
import com.clyon.service.rabbitMQ.*;

@RestController
@RequestMapping(value = "/rabbitMQ/")
public class RabbitMQController {

	@Autowired
	RabbitMQWorkQueueService rabbitMQWorkQueueService;

	@Autowired
	RabbitMQPublishSubscribeService rabbitMQPublishSubscribeService;
	
	@Autowired
	RabbitMQRoutingService rabbitMQRoutingService;
	
	@Autowired
	RabbitMQTopicsService rabbitMQTopicsService;
	
	@Autowired
	RabbitMQHeaderService rabbitMQHeaderService;
	
	@Autowired
	RabbitMQSPDService rabbitMQSPDService;

	@RequestMapping("workQueuesProducer")
	public RespData workQueuesProducer(@RequestBody MsgDTO dto) throws Exception {

		rabbitMQWorkQueueService.producer(dto);

		return RespData.success("发布成功");
	}

	@RequestMapping("workQueuesConsumer")
	public RespData consumer() throws Exception {

		rabbitMQWorkQueueService.consumer();

		return RespData.success("开始接收");
	}

	@RequestMapping("publishSubscribePublish")
	public RespData publishSubscribePublish(@RequestBody MsgDTO dto) throws Exception {

		rabbitMQPublishSubscribeService.producer(dto);

		return RespData.success("发布成功");
	}
	
	@RequestMapping("publishSubscribeConsumer1")
	public RespData publishSubscribeSubscribe1() throws Exception {

		rabbitMQPublishSubscribeService.consumer1();

		return RespData.success("开始接收");
	}
	
	@RequestMapping("publishSubscribeConsumer2")
	public RespData publishSubscribeSubscribe2() throws Exception {

		rabbitMQPublishSubscribeService.consumer2();

		return RespData.success("开始接收");
	}
	
	@RequestMapping("routingPublish")
	public RespData routingPublish(@RequestBody MsgDTO dto) throws Exception {

		rabbitMQRoutingService.producer(dto);

		return RespData.success("发布成功");
	}
	
	@RequestMapping("routingConsumer1")
	public RespData routingConsumer1() throws Exception {

		rabbitMQRoutingService.consumer1();

		return RespData.success("开始接收");
	}
	
	@RequestMapping("routingConsumer2")
	public RespData routingConsumer2() throws Exception {

		rabbitMQRoutingService.consumer2();

		return RespData.success("开始接收");
	}
	
	@RequestMapping("topicsPublish")
	public RespData topicsPublish(@RequestBody MsgDTO dto) throws Exception {

		rabbitMQTopicsService.producer(dto);

		return RespData.success("发布成功");
	}
	
	@RequestMapping("topicsConsumer1")
	public RespData topicsConsumer1() throws Exception {

		rabbitMQTopicsService.consumer1();

		return RespData.success("开始接收");
	}
	
	@RequestMapping("topicsConsumer2")
	public RespData topicsConsumer2() throws Exception {

		rabbitMQTopicsService.consumer2();

		return RespData.success("开始接收");
	}
	
	@RequestMapping("headerPublish")
	public RespData headerPublish(@RequestBody MsgDTO dto) throws Exception {

		rabbitMQHeaderService.producer(dto);

		return RespData.success("发布成功");
	}
	
	@RequestMapping("headerConsumer1")
	public RespData headerConsumer1() throws Exception {

		rabbitMQHeaderService.consumer1();

		return RespData.success("开始接收");
	}
	
	@RequestMapping("headerConsumer2")
	public RespData headerConsumer2() throws Exception {

		rabbitMQHeaderService.consumer2();

		return RespData.success("开始接收");
	}
	
	@RequestMapping("SPDTopicsPublish")
	public RespData SPDTopicsPublish(@RequestBody MsgDTO dto) throws Exception {

		rabbitMQSPDService.producer(dto);

		return RespData.success("发布成功");
	}
	
	@RequestMapping("SPDTopicsConsumer1")
	public RespData SPDTopicsConsumer1() throws Exception {

		rabbitMQHeaderService.consumer1();

		return RespData.success("开始接收");
	}
	
	@RequestMapping("SPDTopicsConsumer2")
	public RespData SPDTopicsConsumer2() throws Exception {

		rabbitMQHeaderService.consumer2();

		return RespData.success("开始接收");
	}

}
