package com.project.minor2.repository;

import com.project.minor2.model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class MyUserCacheRepository {

    private final static String USER_KEY_PREFIX = "usr::";
    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    public void set(MyUser myUser) {
        String key = getKey(myUser.getUsername());
        redisTemplate.opsForValue().set(key, myUser, 24, TimeUnit.HOURS);
    }

    public MyUser get(String username) {
        String key = getKey(username);
        return (MyUser) redisTemplate.opsForValue().get(key);
    }

    public String getKey(String username) {
        return USER_KEY_PREFIX + username;
    }
}
