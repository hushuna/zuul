package com.hsuhuna.controller;


import com.hsuhuna.utils.RedisUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/redis")
public class TestRedisController {

    @GetMapping(value = "/getRedis")
    public void getRedis(){
        String hushuna = RedisUtil.get("hushuna");
        System.out.println(hushuna);
    }
}
