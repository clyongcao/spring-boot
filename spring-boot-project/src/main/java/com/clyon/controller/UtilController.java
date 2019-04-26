package com.clyon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clyon.common.RespData;
import com.clyon.dto.redis.RedisHashDTO;
import com.clyon.dto.redis.RedisListDTO;
import com.clyon.dto.redis.RedisStringDTO;
import com.clyon.dto.utli.UtliDTO;
import com.clyon.emus.StatusCode;
import com.clyon.exception.ServiceException;
import com.clyon.service.RedisService;
import com.clyon.util.MD5Utils;
import com.clyon.util.StringUtil;

@RestController
@RequestMapping(value = "/util")
public class UtilController {

	@Autowired
	private MD5Utils mD5Utils;

	@RequestMapping("/newMd5Password")
	public RespData newMd5Password(UtliDTO dto) throws Exception {

		if (StringUtil.isBlank(dto.getConcent())) {
			throw new ServiceException(StatusCode.CODE_100001.value(), StatusCode.CODE_100001.remark());
		}

		return RespData.success(mD5Utils.newMd5Password(dto.getConcent()));

	}

}
