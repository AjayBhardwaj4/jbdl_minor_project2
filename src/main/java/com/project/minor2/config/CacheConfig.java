package com.project.minor2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class CacheConfig {
    //Redis configs
    @Bean
    RedisConnectionFactory getConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("redis-10476.c301.ap-south-1-1.ec2.cloud.redislabs.com", 10476);
        redisStandaloneConfiguration.setPassword("hxe3ntWy32znIi8AZTep5RLmPlsbc1er");

        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    RedisTemplate<String, Object> getTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setConnectionFactory(getConnectionFactory());

        return redisTemplate;
    }
}
