package com.hsuhuna.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

/*    @Bean(name="genericJackson2JsonRedisSerializer")
    public GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer() {
        return new GenericJackson2JsonRedisSerializer();
    }

    @Bean(name = "myRedisTemplate")
    public RedisTemplate<Object,Object> myRedisTemplate(RedisConnectionFactory redisConnectionFactory, GenericJackson2JsonRedisSerializer ser){
        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        //设置序列化方法
        template.setDefaultSerializer(ser);
        return template;
    }*/

    /**
     * 配置redistemplate
     *
     * @param factory
     *            redis连接工厂
     * @return RedisTemplate redistemplate实例
     */
    @Bean(name = "myRedisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        // 创建redistemplate实例
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        // 设置string实现
        StringRedisSerializer stringSerializer = new StringRedisSerializer();
        // 设置json实现
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(
                Object.class);
        // 设置template key 和 value 实现策略
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        // 序列化储存
        jackson2JsonRedisSerializer.setObjectMapper(om);
        //TODO测试
        //template.setValueSerializer(jackson2JsonRedisSerializer);

        //key
        template.setKeySerializer(stringSerializer);
        template.setValueSerializer(stringSerializer);

        template.afterPropertiesSet();
        // 返回模板
        return template;
    }
}
