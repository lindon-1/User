package com.lindl.user.mapper;

import com.lindl.user.po.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;


@SpringBootTest
class RoleMapperTest {
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

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
}