package com.clyon.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clyon.emus.StatusCode;
import com.clyon.exception.ServiceException;
import com.clyon.redis.dto.RedisStringDTO;
import com.clyon.redis.vo.RedisStringVO;

@Service
public class LinkRedisService {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate redisTemplate;

	public RedisStringVO getString(String key) {

		RedisStringVO vo = new RedisStringVO();

		vo.setKey(key);
		vo.setValue(stringRedisTemplate.opsForValue().get(key));

		return vo;

	}

	@Transactional(rollbackFor = Exception.class)
	public void setString(RedisStringDTO dto) throws Exception {

		try {
			stringRedisTemplate.opsForValue().set(dto.getKey(), dto.getValue());
		} catch (Exception e) {
			throw new ServiceException(StatusCode.CODE_100002.value(), StatusCode.CODE_100002.remark());
		}

	}

	@Transactional(rollbackFor = Exception.class)
	public void delString(String key) throws Exception {

		try {
			stringRedisTemplate.delete(key);
		} catch (Exception e) {
			throw new ServiceException(StatusCode.CODE_100003.value(), StatusCode.CODE_100003.remark());
		}

	}

}
