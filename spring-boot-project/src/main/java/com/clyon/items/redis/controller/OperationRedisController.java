package com.clyon.items.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clyon.common.RespData;
import com.clyon.emus.StatusCode;
import com.clyon.exception.ServiceException;
import com.clyon.items.redis.dto.RedisHashDTO;
import com.clyon.items.redis.dto.RedisListDTO;
import com.clyon.items.redis.dto.RedisStringDTO;
import com.clyon.items.redis.service.OperationRedisService;
import com.clyon.util.StringUtil;

@RestController
@RequestMapping(value = "/redis")
public class OperationRedisController {

	@Autowired
	private OperationRedisService operationRedisService;

	@RequestMapping("/getString")
	public RespData getString(RedisStringDTO dto) throws Exception {

		if (StringUtil.isBlank(dto.getKey())) {
			throw new ServiceException(StatusCode.CODE_100001.value(), StatusCode.CODE_100001.remark());
		}

		return RespData.success(operationRedisService.getString(dto.getKey()));

	}

	@RequestMapping("/setString")
	public RespData setString(RedisStringDTO dto) throws Exception {

		if (StringUtil.isBlank(dto.getKey()) || StringUtil.isBlank(dto.getValue())) {
			throw new ServiceException(StatusCode.CODE_100001.value(), StatusCode.CODE_100001.remark());
		}

		operationRedisService.setString(dto);

		return RespData.success("保存成功");

	}

	@RequestMapping("/delString")
	public RespData delString(RedisStringDTO dto) throws Exception {

		if (StringUtil.isBlank(dto.getKey())) {
			throw new ServiceException(StatusCode.CODE_100001.value(), StatusCode.CODE_100001.remark());
		}

		operationRedisService.delString(dto);

		return RespData.success("删除成功");

	}

	@RequestMapping("/getHash")
	public RespData getHash(RedisHashDTO dto) throws Exception {

		if (StringUtil.isBlank(dto.getKey()) || StringUtil.isBlank(dto.getHashKey())) {
			throw new ServiceException(StatusCode.CODE_100001.value(), StatusCode.CODE_100001.remark());
		}

		return RespData.success(operationRedisService.getHash(dto));

	}

	@RequestMapping("/setHash")
	public RespData setHash(RedisHashDTO dto) throws Exception {

		if (StringUtil.isBlank(dto.getKey()) || StringUtil.isBlank(dto.getHashKey())
				|| StringUtil.isBlank(dto.getValue())) {
			throw new ServiceException(StatusCode.CODE_100001.value(), StatusCode.CODE_100001.remark());
		}

		operationRedisService.setHash(dto);

		return RespData.success("保存成功");

	}
	
	@RequestMapping("/getList")
	public RespData popList(RedisListDTO dto) throws Exception {

		if (StringUtil.isBlank(dto.getKey()) || StringUtil.isBlank(dto.getOrientation())) {
			throw new ServiceException(StatusCode.CODE_100001.value(), StatusCode.CODE_100001.remark());
		}

		return RespData.success(operationRedisService.popList(dto));

	}
	
	@RequestMapping("/rangeList")
	public RespData rangeList(RedisListDTO dto) throws Exception {

		if (StringUtil.isBlank(dto.getKey())) {
			throw new ServiceException(StatusCode.CODE_100001.value(), StatusCode.CODE_100001.remark());
		}

		return RespData.success(operationRedisService.rangeList(dto));

	}

	@RequestMapping("/setList")
	public RespData setList(RedisListDTO dto) throws Exception {

		if (StringUtil.isBlank(dto.getKey()) || StringUtil.isBlank(dto.getValue()) || StringUtil.isBlank(dto.getOrientation())) {
			throw new ServiceException(StatusCode.CODE_100001.value(), StatusCode.CODE_100001.remark());
		}

		operationRedisService.setList(dto);

		return RespData.success("保存成功");

	}

}
