package com.xizi.cache;

import com.xizi.utils.ApplicationContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.Cache;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.concurrent.locks.ReadWriteLock;

@Slf4j
public class RedisCache implements Cache {

    private String id;

    public RedisCache(String id) {
        log.info("当前缓存id:[{}]",id);
        this.id = id;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override   //放入缓存
    public void putObject(Object key, Object value) {
        log.info("放入缓存key:[{}] 放入缓存的value:[{}]",key,value);
        getRedisTemplate().opsForHash().put(id,key.toString(),value);
    }

    @Override  //从redis缓存获取
    public Object getObject(Object key) {
        log.info("获取缓存key:[{}] ",key.toString());
        return getRedisTemplate().opsForHash().get(id,key.toString());
    }

    @Override   //清除缓存
    public Object removeObject(Object key) {
        return null;
    }

    @Override
    public void clear() {
        log.info("清除缓存信息......");
        getRedisTemplate().delete(id);
    }

    @Override
    public int getSize() {
        return getRedisTemplate().opsForHash().size(id).intValue();
    }

    //封装获取redistemplate 的方法
    public RedisTemplate getRedisTemplate(){
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
