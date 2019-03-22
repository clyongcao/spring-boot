package com.clyon.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

/**
 * Created by bolinlee on 2017/7/27 redis工具类
 */
@Component
public class RedisUtil {

	// 一些Key的约定命名
	public final static String  ADMIN_MENU_KEY = "admin_menu_key:";
  
	@Autowired
    private RedisTemplate redisTemplate;

	/**
	 * 更新session有效期
	 * @param token
	 * @param time
	 */
	public void updateSessionExpireTime(String key,long time){
		redisTemplate.expire(key,time,TimeUnit.MILLISECONDS);
	}

	/**
	 *  
	 * @Description 获取JSON转对象
	 * @Param [key, clazz]
	 * @return java.util.List<R>
	 * @Date 2018/9/6 11:15
	 **/
	public  <T> T getObjectByJson(String key,Class<T> clazz){
		T object = null;
		if(key!=null&&!"".equals(key)){
			String json = getStr(key);
			if(json!=null&&!"".equals(json)){
				ObjectMapper mapper = new ObjectMapper();
				try {
					object = mapper.readValue(new StringReader(json), clazz);
				} catch (JsonParseException e) {
					e.printStackTrace();
					delete(key);
				} catch (JsonMappingException e) {
					e.printStackTrace();
					delete(key);
				} catch (IOException e) {
					e.printStackTrace();
					delete(key);
				}

			}
		}
		return object;
	}

	/**
	 *  
	 * @Description 对象保存JSON格式
	 * @Param [key, object, time]
	 * @return void
	 * @Date 2018/9/6 11:13
	 **/
	public void setObjectJson(String key,Object object, Long time) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			set(key,  mapper.writeValueAsString(object),time);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    /**
     * 获取字符串型值
     *
     * @param key
     * @return
     */
    public String getStr(String key) {
	return (String) redisTemplate.opsForValue().get(key);
    }

	/**
	 * 获取整数
	 * @param key
	 * @return
	 */
	public Integer getInteger(String key){
    	return (Integer)redisTemplate.opsForValue().get(key);
	}

	/**
	 * 设置缓存
	 * @param key	key
	 * @param o	值
	 * @param time	过期时间
	 */
    public void set(String key, Object o,Long time) {
	if (o instanceof String) {
		if(time==null){
			redisTemplate.opsForValue().set(key, o);
		}else{
			redisTemplate.opsForValue().set(key,o,time, TimeUnit.MINUTES);
		}
	} else if (o instanceof Map) {
			redisTemplate.opsForHash().putAll(key, (Map) o);
			if(time!=null){
				redisTemplate.expire(key,time,TimeUnit.MINUTES);
			}
	} else if (o instanceof List) {
	    redisTemplate.opsForList().rightPushAll(key,(List)o);
		if(time!=null){
			redisTemplate.expire(key,time,TimeUnit.MINUTES);
		}
	} else if (o instanceof Set) {
	    redisTemplate.opsForSet().add(key, o);
		if(time!=null){
			redisTemplate.expire(key,time,TimeUnit.MINUTES);
		}
	} else {
		if(time==null){
			redisTemplate.opsForValue().set(key, o);
		}else{
			redisTemplate.opsForValue().set(key, o,time, TimeUnit.MINUTES);
		}

	}
    }

	/**
	 * 获取map的值
	 * @param key		key
	 * @param field	map的key
	 * @return
	 */
    public Object getMapValue(String key, String field) {
	return redisTemplate.opsForHash().get(key, field);
    }

	/**
	 * 获取整个map
	 * @param key	key
	 * @return
	 */
    public Map getMap(String key) {
	return redisTemplate.opsForHash().entries(key);
    }

	/**
	 * 设置map的某个key的值
	 * @param key	redis key
	 * @param fieldName	map对应的key
	 * @param fieldValue	map对应的value
	 */
    public void setMapValue(String key, String fieldName, Object fieldValue) {
	redisTemplate.opsForHash().put(key, fieldName, fieldValue);
    }

    public void lpush(String key, String value) {
	redisTemplate.opsForList().leftPush(key, value);
    }

    public Object lpop(String key, String value) {
	return redisTemplate.opsForList().leftPop(key);
    }

    public void rpush(String key, String value) {
	redisTemplate.opsForList().rightPush(key, value);
    }

    public Object rpop(String key, String value) {
	return redisTemplate.opsForList().rightPop(key);
    }

    public List getList(String key) {
	long length = redisTemplate.opsForList().size(key);
	return redisTemplate.opsForList().range(key, 0, length);
    }

	/**
	 * 获取缓存对象
	 * @param key
	 * @param <T>
	 * @return
	 */
	public <T> T getObj(String key){
		return (T)redisTemplate.opsForValue().get(key);
	}

	/**
	 * 批量获取对象
	 * @param keys
	 * @param <T>
	 * @return
	 */
    public <T> List<T> getList(final Set<String> keys) {
	final RedisSerializer<T> valueSerializer = redisTemplate
		.getValueSerializer();
	final RedisSerializer keySerializer = redisTemplate.getKeySerializer();
	return redisTemplate.executePipelined(new RedisCallback<List<T>>() {
	    @Override
	    public List<T> doInRedis(RedisConnection redisConnection)
		    throws DataAccessException {
		Iterator iterator = keys.iterator();
		while (iterator.hasNext()) {
		    valueSerializer.deserialize(redisConnection
			    .get(keySerializer.serialize(iterator.next())));
		}
		return null;
	    }
	});
    }

	/**
	 * 根据正则表达式获取key
	 * @param str
	 * @return
	 */
    public Set<String> keys(final String str) {
	Set<String> result = (Set<String>) redisTemplate
		.execute(new RedisCallback<Set<String>>() {
		    @Override
		    public Set<String> doInRedis(RedisConnection redisConnection)
			    throws DataAccessException {
			Set<byte[]> temp = redisConnection.keys(str.getBytes());
			Set<String> sets = new HashSet<String>();
			Iterator<byte[]> iter = temp.iterator();
			while (iter.hasNext()) {
			    sets.add(new String(iter.next()));
			}
			return sets;
		    }
		});
	return result;
    }

	/**
	 * 删除缓存
	 * @param key
	 */
	public void delete(String key) {
		redisTemplate.delete(key);
	}

	/**
	 * 批量删除缓存
	 * @param key
	 */
	public void delete(Collection<String> key) {
		redisTemplate.delete(key);
	}

	/**
	 * 在key对应的值上面增加value
	 * @param key
	 * @param value
	 * @return
	 */
	public Long increment(String key,long value){
		return redisTemplate.opsForValue().increment(key,value);
	}

	/**
	 * @return boolean
	 *  
	 * @Description 新增
	 * @Param [key, value, score]
	 * @Date 2018/10/26 14:02
	 **/
	public boolean zsetAdd(String key, Object value, Double score) {

		return redisTemplate.opsForZSet().add(key, value, score);
	}
	/**
	 * 在field对应的值上面增加value
	 * @param key
	 * @param value
	 * @return
	 */
	public Long incrementMapKey(String key,String field,long value){
		return redisTemplate.opsForHash().increment(key,field,value);
	}

	/**
	 *  
	 * @Description 移除
	 * @Param [key, value]
	 * @return boolean
	 * @Date 2018/10/26 14:10
	 **/
	public boolean remove(String key, Object value) {

		return redisTemplate.opsForZSet().remove(key,value)>0;
	}

}
