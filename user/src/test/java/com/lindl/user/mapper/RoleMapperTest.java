package com.lindl.user.mapper;

import com.lindl.user.po.Role;
import com.lindl.user.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@SpringBootTest
class RoleMapperTest {
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate redisTemplate;

    @Test
    public void test() {
        List<Role> roleList = roleMapper.findByUserId(11L);
        System.out.println(roleList);

    }

    @Test
    public void test1() {
        List<String> list = Arrays.asList(new String[]{"11","22"});
//        long time = 1000 * 60 * 60;
//        stringRedisTemplate.opsForValue().set("dd", list.toString(), time, TimeUnit.MILLISECONDS);
        String dd = stringRedisTemplate.opsForValue().get("aaa");
        System.out.println(dd);
    }

    @Test
    public void test2() {
//        redisTemplate.opsForValue().set("lindl", "ttly");
//        redisTemplate.opsForValue().set("tt", "海贼王");
        String tt = (String)redisTemplate.opsForValue().get("tt");
        System.out.println(tt);
    }

    /**
    *@Description  redis  hash  设值
    *@author  ldl
    *@date   2020/1/16
    */
    @Test
    public void testRedisHash() {
        List<Role> roles = roleMapper.findByUserId(1L);
        roles.forEach(e -> {
            redisTemplate.opsForHash().put("HASHKEY", e.getId(), e);
        });
    }

    /**
    *@Description  hash获取值
    *@author  ldl
    *@date   2020/1/16
    */
    @Test
    public void testRedisHashGet() {
        List hashkey = redisTemplate.opsForHash().values("HASHKEY");
        System.out.println(hashkey);
    }

    /**
    *@Description   set类型  设值
    *@author  ldl
    *@date   2020/1/16
    */
    @Test
    public void testSet() {
        List<Role> roles = roleMapper.findByUserId(1L);
        roles.stream().map(Role::getName).forEach(e -> {
            redisTemplate.opsForSet().add("SETKEY", e);
        });
    }

    /**
    *@Description  set 获取值
    *@author  ldl
    *@date   2020/1/16
    */
    @Test
    public void testGetSet() {
        Set setkey = redisTemplate.opsForSet().members("SETKEY");
//        redisTemplate.opsForSet().remove("SETKEY");
        System.out.println(setkey);
    }

    @Test
    public void testList() {
        List<Role> roles = roleMapper.findByUserId(1L);
        roles.stream().map(Role::getName).forEach(e -> {
            redisTemplate.opsForList().leftPush("LIST", e);
        });

    }
}