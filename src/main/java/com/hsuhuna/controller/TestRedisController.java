package com.hsuhuna.controller;


import com.hsuhuna.utils.RedisUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/redis")
public class TestRedisController {

    @GetMapping(value = "/test")
    public String test(){
        RedisUtil.get("hushuna");
        System.out.println(RedisUtil.get("hushuna"));
        return "huhu";
    }


    @GetMapping(value = "/getRedis")
    public void getRedis(){
//        RedisUtil.setHash("token","123","123");
//        RedisUtil.setValue("hushuna","hushua");
        RedisUtil.setHash("token","huhu","huhu");
        String token = RedisUtil.get("��");
        System.out.println(token);
    }


}
