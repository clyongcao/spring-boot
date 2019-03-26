package com.clyon.items.redis.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clyon.emus.StatusCode;
import com.clyon.exception.ServiceException;
import com.clyon.items.redis.dto.RedisHashDTO;
import com.clyon.items.redis.dto.RedisListDTO;
import com.clyon.items.redis.dto.RedisStringDTO;
import com.clyon.items.redis.vo.RedisStringVO;

@Service
public class OperationRedisService {

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
	public void delString(RedisStringDTO dto) throws Exception {

		try {
			stringRedisTemplate.delete(dto.getKey());
		} catch (Exception e) {
			throw new ServiceException(StatusCode.CODE_100003.value(), StatusCode.CODE_100003.remark());
		}

	}

	public Object getHash(RedisHashDTO dto) {

		return redisTemplate.opsForHash().get(dto.getKey(), dto.getHashKey());

	}

	@Transactional(rollbackFor = Exception.class)
	public void setHash(RedisHashDTO dto) throws Exception {

		HashMap<String, Object> map = new HashMap<>();
		map.put(dto.getHashKey(), dto.getValue());

		try {
			// redisTemplate.opsForHash().put(dto.getKey(),dto.getHashKey(),
			// dto.getValue());
			redisTemplate.opsForHash().putAll(dto.getKey(), map);
		} catch (Exception e) {
			throw new ServiceException(StatusCode.CODE_100002.value(), StatusCode.CODE_100002.remark());
		}

	}

	public Object popList(RedisListDTO dto) throws Exception {

		if (dto.getOrientation().toLowerCase().equals("l")) {
			return redisTemplate.opsForList().leftPop(dto.getKey());
		} else if (dto.getOrientation().toLowerCase().equals("r")) {
			return redisTemplate.opsForList().rightPop(dto.getKey());
		} else {
			throw new ServiceException(StatusCode.CODE_100002.value(), StatusCode.CODE_100002.remark());
		}

	}

	public Object rangeList(RedisListDTO dto) throws Exception {

		return redisTemplate.opsForList().range(dto.getKey(), 0, -1);

	}

	@Transactional(rollbackFor = Exception.class)
	public void pushList(RedisListDTO dto) throws Exception {

		try {
			if (dto.getOrientation().toLowerCase().equals("l")) {
				redisTemplate.opsForList().leftPush(dto.getKey(), dto.getValue());
			} else if (dto.getOrientation().toLowerCase().equals("r")) {
				redisTemplate.opsForList().rightPush(dto.getKey(), dto.getValue());
			} else {
				throw new ServiceException(StatusCode.CODE_100002.value(), StatusCode.CODE_100002.remark());
			}
		} catch (Exception e) {
			throw new ServiceException(StatusCode.CODE_100002.value(), StatusCode.CODE_100002.remark());
		}

	}

}
