package com.sdocean.redis.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

@Component
public class RedisDao {

	private static Logger log = LoggerFactory.getLogger(RedisDao.class);
	
	private StringRedisTemplate redisTemplate;

	public StringRedisTemplate getRedisTemplate() {
		return redisTemplate;
	}

	@Autowired
	public void setRedisTemplate(StringRedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
		log.info("程序启动初始化spring redisTemplate");
	}
	
	
	 public Member get(final String keyId) {  
	        Member result = redisTemplate.execute(new RedisCallback<Member>() {  
	            public Member doInRedis(RedisConnection connection)  
	                    throws DataAccessException {  
	                RedisSerializer<String> serializer = getRedisSerializer();  
	                byte[] key = serializer.serialize(keyId);  
	                byte[] value = connection.get(key);  
	                if (value == null) {  
	                    return null;  
	                }  
	                String nickname = serializer.deserialize(value);  
	                return new Member(keyId, nickname);  
	            }  
	        });  
	        return result;  
	    }  
	 
	 protected RedisSerializer<String> getRedisSerializer() {  
	        return redisTemplate.getStringSerializer();  
	    }  
	 
}
