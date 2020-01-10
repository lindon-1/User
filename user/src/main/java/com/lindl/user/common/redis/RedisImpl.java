package com.lindl.user.common.redis;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2019/12/31 16:36
 */
@Component
public class RedisImpl implements Redis {
    private static final String UNLOCK_LUA = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${spring.jpa.show-sql}")
    private Boolean showSql;


    @Override
    public boolean tryGetDistributedLock(final String lockKey, final String requestId, long expire) {
        return (Boolean) stringRedisTemplate.execute((RedisCallback<Boolean>) (connection) -> {
            return connection.set(lockKey.getBytes(Charset.forName("UTF-8")), requestId.getBytes(Charset.forName("UTF-8")), Expiration.seconds(expire), RedisStringCommands.SetOption.SET_IF_ABSENT);
        });
    }

    @Override
    public boolean releaseDistributedLock(final String lockKey, final String requestId) {
        return (Boolean) stringRedisTemplate.execute((RedisCallback<Boolean>) (connection) -> {
            return connection.eval(UNLOCK_LUA.getBytes(), ReturnType.BOOLEAN, 1, lockKey.getBytes(Charset.forName("UTF-8")), requestId.getBytes(Charset.forName("UTF-8")));
        });
    }

    @Override
    public Long incr(String key) {
        return incrby(key, 1);
    }

    @Override
    public Long decr(String key) {
        return decrby(key, 1);
    }

    @Override
    public Long decrby(String key, long decrement) {
        if (showSql) {
            System.out.println("StringRedisTemplate: decrby [key:" + key + "] [decrement:" + decrement + "]");
        }
        return stringRedisTemplate.opsForValue().increment(key, -decrement);
    }

    @Override
    public Long incrby(String key, long increment) {
        if (showSql) {

            System.out.println("StringRedisTemplate: incrby [key:" + key + "] [increment:" + increment + "]");
        }
        return stringRedisTemplate.opsForValue().increment(key, increment);
    }

    @Override
    public Set<String> keys(String pattern) {
        if (showSql) {

            System.out.println("StringRedisTemplate: keys [pattern:" + pattern + "]");
        }
        return stringRedisTemplate.keys(pattern);
    }

    @Override
    public void delete(Set<String> keySet) {
        if (showSql) {

            System.out.println("StringRedisTemplate: del [keys:" + keySet + "]");
        }
        stringRedisTemplate.delete(keySet);
    }


    @Override
    public void keepObjOnSec(String key, Object obj, long sec) {
        String value = JSONObject.toJSONString(obj, SerializerFeature.WriteMapNullValue);
        set(key, value, sec);
    }

    @Override
    public <T> T getKeepingObj(String key, Class<T> clz) {
        String json = get(key);
        return StringUtils.isEmpty(json) ? null : JSONObject.parseObject(json, clz);
    }

    @Override
    public <T> List<T> getKeepingList(String key, Class<T> clz) {
        String json = get(key);
        return StringUtils.isEmpty(json) ? null : JSONObject.parseArray(json, clz);
    }

    @Override
    public boolean isExists(String key) {
        if (showSql) {

            System.out.println("StringRedisTemplate: exists [key:" + key + "]");
        }
        return stringRedisTemplate.hasKey(key);
    }

    @Override
    public void delete(String key) {
        if (showSql) {

            System.out.println("StringRedisTemplate: del [key:" + key + "]");
        }
        stringRedisTemplate.delete(key);
    }

    @Override
    public long getExpire(String key) {
        if (showSql) {

            System.out.println("StringRedisTemplate: ttl [key:" + key + "]");
        }
        return stringRedisTemplate.getExpire(key);
    }

    @Override
    public void set(String key, String value) {
        if (showSql) {

            System.out.println("StringRedisTemplate: set [key:" + key + "] [value:" + value + "]");
        }
        stringRedisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void set(String key, String value, long expire) {
        if (showSql) {

            System.out.println("StringRedisTemplate: set [key:" + key + "] [value:" + value + "] [expire:" + expire + " s]");
        }
        stringRedisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
    }

    @Override
    public String get(String key) {
        if (showSql) {

            System.out.println("StringRedisTemplate: get [key:" + key + "]");
        }
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public boolean expire(String key, long expire) {
        if (showSql) {

            System.out.println("StringRedisTemplate: expire [key:" + key + "] [expire:" + expire + " s]");
        }
        return stringRedisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    @Override
    public void setList(String key, List<?> list) {
        set(key, JSONObject.toJSONString(list, SerializerFeature.WriteMapNullValue));
    }

    @Override
    public <T> List<T> getList(String key, Class<T> clz) {
        String json = get(key);
        return StringUtils.isEmpty(json) ? null : JSONObject.parseArray(json, clz);
    }

    @Override
    public long lpush(String key, Object obj) {
        String value = JSONObject.toJSONString(obj, SerializerFeature.WriteMapNullValue);
        if (showSql) {

            System.out.println("StringRedisTemplate: lPush [key:" + key + "] [value:" + value + "]");
        }
        return stringRedisTemplate.opsForList().leftPush(key, value);

    }

    @Override
    public long rpush(final String key, Object obj) {
        String value = JSONObject.toJSONString(obj, SerializerFeature.WriteMapNullValue);
        if (showSql) {

            System.out.println("StringRedisTemplate: rpush [key:" + key + "] [value:" + value + "]");
        }
        return stringRedisTemplate.opsForList().rightPush(key, value);
    }

    @Override
    public String lpop(final String key) {
        if (showSql) {

            System.out.println("StringRedisTemplate: lpop [key:" + key + "]");
        }
        return stringRedisTemplate.opsForList().leftPop(key);
    }

    @Override
    public String rpop(String key) {
        if (showSql) {

            System.out.println("StringRedisTemplate: rpop [key:" + key + "]");
        }
        return stringRedisTemplate.opsForList().rightPop(key);
    }

    @Override
    public Long llen(String key) {
        if (showSql) {

            System.out.println("StringRedisTemplate: list size [key:" + key + "]");
        }
        return stringRedisTemplate.opsForList().size(key);
    }


    @Override
    public void saveMap(String key, Map<String, String> params, long expire) {
        stringRedisTemplate.opsForHash().putAll(key, params);
        this.expire(key, expire);
        if (showSql) {

            System.out.println("StringRedisTemplate: hset [key:" + key + "] [map:" + params + "]");
        }
    }

    @Override
    public Map<Object, Object> getRedisMap(String key) {
        if (showSql) {

            System.out.println("StringRedisTemplate: hget [key:" + key + "]");
        }
        return stringRedisTemplate.opsForHash().entries(key);
    }

    @Override
    public Map<Object, Object> getStringMap(String key) {
        if (showSql) {

            System.out.println("StringRedisTemplate: hget [key:" + key + "]");
        }
        return stringRedisTemplate.opsForHash().entries(key);
    }

    @Override
    public void setMap(String key, Map params, long expire) {
        stringRedisTemplate.opsForHash().putAll(key, params);
        this.expire(key, expire);
        if (showSql) {
            System.out.println("StringRedisTemplate: hset [key:" + key + "] [map:" + params + "]");
        }
    }

}
