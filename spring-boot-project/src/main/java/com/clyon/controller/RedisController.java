package com.clyon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clyon.common.RespData;
import com.clyon.dto.redis.RedisHashDTO;
import com.clyon.dto.redis.RedisListDTO;
import com.clyon.dto.redis.RedisStringDTO;
import com.clyon.emus.StatusCode;
import com.clyon.exception.ServiceException;
import com.clyon.service.RedisService;
import com.clyon.util.StringUtil;

@RestController
@RequestMapping(value = "/redis")
public class RedisController {

	@Autowired
	private RedisService redisService;

	@RequestMapping("/getString")
	public RespData getString(RedisStringDTO dto) throws Exception {

		if (StringUtil.isBlank(dto.getKey())) {
			throw new ServiceException(StatusCode.CODE_100001.value(), StatusCode.CODE_100001.remark());
		}

		return RespData.success(redisService.getString(dto.getKey()));

	}

	@RequestMapping("/setString")
	public RespData setString(RedisStringDTO dto) throws Exception {

		if (StringUtil.isBlank(dto.getKey()) || StringUtil.isBlank(dto.getValue())) {
			throw new ServiceException(StatusCode.CODE_100001.value(), StatusCode.CODE_100001.remark());
		}

		redisService.setString(dto);

		return RespData.success("保存成功");

	}

	@RequestMapping("/delString")
	public RespData delString(RedisStringDTO dto) throws Exception {

		if (StringUtil.isBlank(dto.getKey())) {
			throw new ServiceException(StatusCode.CODE_100001.value(), StatusCode.CODE_100001.remark());
		}

		redisService.delString(dto);

		return RespData.success("删除成功");

	}

	@RequestMapping("/getHash")
	public RespData getHash(RedisHashDTO dto) throws Exception {

		if (StringUtil.isBlank(dto.getKey()) || StringUtil.isBlank(dto.getHashKey())) {
			throw new ServiceException(StatusCode.CODE_100001.value(), StatusCode.CODE_100001.remark());
		}

		return RespData.success(redisService.getHash(dto));

	}

	@RequestMapping("/setHash")
	public RespData setHash(RedisHashDTO dto) throws Exception {

		if (StringUtil.isBlank(dto.getKey()) || StringUtil.isBlank(dto.getHashKey())
				|| StringUtil.isBlank(dto.getValue())) {
			throw new ServiceException(StatusCode.CODE_100001.value(), StatusCode.CODE_100001.remark());
		}

		redisService.setHash(dto);

		return RespData.success("保存成功");

	}
	
	@RequestMapping("/popList")
	public RespData popList(RedisListDTO dto) throws Exception {

		if (StringUtil.isBlank(dto.getKey()) || StringUtil.isBlank(dto.getOrientation())) {
			throw new ServiceException(StatusCode.CODE_100001.value(), StatusCode.CODE_100001.remark());
		}

		return RespData.success(redisService.popList(dto));

	}
	
	@RequestMapping("/rangeList")
	public RespData rangeList(RedisListDTO dto) throws Exception {

		if (StringUtil.isBlank(dto.getKey())) {
			throw new ServiceException(StatusCode.CODE_100001.value(), StatusCode.CODE_100001.remark());
		}

		return RespData.success(redisService.rangeList(dto));

	}

	@RequestMapping("/pushList")
	public RespData pushList(RedisListDTO dto) throws Exception {

		if (StringUtil.isBlank(dto.getKey()) || StringUtil.isBlank(dto.getValue()) || StringUtil.isBlank(dto.getOrientation())) {
			throw new ServiceException(StatusCode.CODE_100001.value(), StatusCode.CODE_100001.remark());
		}

		redisService.pushList(dto);

		return RespData.success("保存成功");

	}

}
