package com.clyon.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clyon.common.RespData;
import com.clyon.emus.StatusCode;
import com.clyon.exception.ServiceException;
import com.clyon.redis.dto.RedisStringDTO;
import com.clyon.redis.service.LinkRedisService;
import com.clyon.util.StringUtil;

@RestController
@RequestMapping(value = "/redis")
public class LinkRedisController {

	@Autowired
	private LinkRedisService linkRedisService;

	@RequestMapping("/getString")
	public RespData getString(RedisStringDTO dto) throws Exception {
		
		if (StringUtil.isBlank(dto.getKey())) {
			throw new ServiceException(StatusCode.CODE_100001.value(), StatusCode.CODE_100001.remark());
		}

		return RespData.success(linkRedisService.getString(dto.getKey()));

	}

	@RequestMapping("/setString")
	public RespData setString(RedisStringDTO dto) throws Exception {

		if (StringUtil.isBlank(dto.getKey()) || StringUtil.isBlank(dto.getValue())) {
			throw new ServiceException(StatusCode.CODE_100001.value(), StatusCode.CODE_100001.remark());
		}

		linkRedisService.setString(dto);

		return RespData.success("保存成功");

	}

	@RequestMapping("/delString")
	public RespData delString(RedisStringDTO dto) throws Exception {

		if (StringUtil.isBlank(dto.getKey())) {
			throw new ServiceException(StatusCode.CODE_100001.value(), StatusCode.CODE_100001.remark());
		}

		linkRedisService.delString(dto.getKey());

		return RespData.success("删除成功");

	}

}
