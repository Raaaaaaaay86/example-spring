package com.example.plan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

import java.lang.reflect.Method;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

  @Autowired
  private RedisConnectionFactory redisConnectionFactory;

  @Bean
  public KeyGenerator keyGenerator() {
    return new KeyGenerator() {
      @Override
      public Object generate(Object target, Method method, Object... params) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(target.getClass().getName());
        stringBuilder.append(method.getName());
        for (Object obj : params) {
          stringBuilder.append(obj.toString());
        }
        return stringBuilder.toString();
      }
    };
  }

//  @SuppressWarnings("rawtypes")
  @Bean
  public CacheManager cacheManager(RedisTemplate redisTemplate) {
    RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisTemplate.getConnectionFactory());
    RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
      .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisTemplate.getValueSerializer()));

    return new RedisCacheManager(redisCacheWriter, redisCacheConfiguration);
  }

  @Bean
  public RedisTemplate<String, Object> redisTemplate() {
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(this.redisConnectionFactory);

    Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
    StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

    redisTemplate.setKeySerializer(stringRedisSerializer);
    redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);

    redisTemplate.setHashKeySerializer(stringRedisSerializer);
    redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

    redisTemplate.setEnableDefaultSerializer(true);
    redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer);
    return redisTemplate;
  }

}
