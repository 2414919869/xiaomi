package com.czy.library.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.czy.library.response.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/redis")
public class RedisTestController {

    @Autowired  //注入
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("/set")
    public Resp set(){
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set("name","Hello World!");
        return Resp.success()
                .data("time",new Date());
    }
    @GetMapping("/get")
    public Resp get(){
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        return Resp.success()
                .data("message",ops.get("name"))
                .data("time",new Date());
    }

}
