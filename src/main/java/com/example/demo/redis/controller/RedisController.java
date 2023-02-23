package com.example.demo.redis.controller;

import com.example.demo.redis.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("redis")
public class RedisController {

    private final RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    public RedisController(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("save")
    public void save(String key, String value){
        redisTemplate.opsForValue().set(key, value);
    }

    @GetMapping("add")
    public void add() {
        Map<String, String> map = new HashMap<>();
        map.put("jin", "peng");
        redisUtil.add("test", map);
    }


}