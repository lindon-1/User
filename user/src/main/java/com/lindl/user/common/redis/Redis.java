package com.lindl.user.common.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2019/12/31 16:35
 */
public interface Redis {
    /**
     * @param lockKey
     * @param requestId
     * @param expire    秒
     * @return
     * @Description: 获取分布式锁
     */
    public boolean tryGetDistributedLock(String lockKey, String requestId, long expire);

    /**
     * @param lockKey
     * @param requestId
     * @return
     * @Description: 释放分布式锁
     */
    public boolean releaseDistributedLock(String lockKey, String requestId);

    /**
     * 自减1
     *
     * @param key
     * @return
     */
    public Long decr(String key);

    /**
     * 自减 decrement
     *
     * @param key
     * @param decrement
     * @return
     */
    public Long decrby(String key, long decrement);

    /**
     * 自增1
     *
     * @param key
     * @return
     */
    public Long incr(String key);

    /**
     * 自增 increment
     *
     * @param key
     * @param increment
     * @return
     */
    public Long incrby(String key, long increment);

    /**
     * 获取所有符合通配符为pattern的Key
     *
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern);

    /**
     * 删除Keys
     *
     * @param keySet
     */
    public void delete(Set<String> keySet);

    /**
     * 缓存 key=Obj sec秒
     *
     * @param key
     * @param obj
     * @param sec
     */
    public void keepObjOnSec(String key, Object obj, long sec);

    /**
     * 获取缓存key=Obj
     *
     * @param key
     * @param clz
     * @return
     */
    public <T> T getKeepingObj(String key, Class<T> clz);

    /**
     * 获取缓存key=List
     *
     * @param key
     * @param clz
     * @return
     */
    public <T> List<T> getKeepingList(String key, Class<T> clz);


    /**
     * set key=value
     *
     * @param key
     * @param value
     * @return
     */
    public void set(String key, String value);

    /**
     * set key=value
     *
     * @param key
     * @param value
     * @param expire (秒)
     * @return
     */
    public void set(String key, String value, long expire);

    /**
     * get key=value
     *
     * @param key
     * @return
     */
    public String get(String key);

    /**
     * 设置key过期时间 （秒）
     *
     * @param key
     * @param expire
     * @return
     */
    public boolean expire(String key, long expire);

    /**
     * 保存集合
     *
     * @param key
     * @param list
     * @return
     */
    public void setList(String key, List<?> list);

    /**
     * 获取集合
     *
     * @param key
     * @param clz
     * @return
     */
    public <T> List<T> getList(String key, Class<T> clz);

    /**
     * 将obj存入列表key头部
     *
     * @param key
     * @param obj
     * @return
     */
    public long lpush(String key, Object obj);

    /**
     * 将obj存入列表key尾部
     *
     * @param key
     * @param obj
     * @return
     */
    public long rpush(String key, Object obj);

    /**
     * 移除并返回列表 key的头元素
     *
     * @param key
     * @return
     */
    public String lpop(String key);


    public String rpop(String key);

    /**
     * 删除key
     *
     * @param key
     */
    public void delete(String key);

    /**
     * 获取key的过期时间 （秒）
     *
     * @param key
     * @return
     */
    public long getExpire(String key);

    /**
     * 是否存在key
     *
     * @param key
     * @return
     */
    public boolean isExists(String key);

    /**
     * 获取队列长度
     *
     * @param key
     * @return
     */
    public Long llen(String key);


    public void saveMap(String key, Map<String,String> params, long expire);


    public Map<Object,Object> getRedisMap (String key);

    public Map<Object, Object> getStringMap (String key);


    public void setMap(String key, Map map, long expire);
}
