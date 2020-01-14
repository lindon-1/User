package com.lindl.user.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.lindl.user.common.ResParam;
import com.lindl.user.common.Result;
import com.lindl.user.common.redis.RedisImpl;
import com.lindl.user.dto.LoginParam;
import com.lindl.user.mapper.UserMapper;
import com.lindl.user.po.User;
import com.lindl.user.service.LoginService;
import com.lindl.user.vo.LoginInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2019/12/30 17:49
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private RedisImpl redis;

    private final Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Override
    public Result<LoginInfoVo> login(ResParam<LoginParam> resParam) {
        LoginParam pdata = resParam.getPdata();
        if (StringUtils.isEmpty(pdata.getUserName()) || StringUtils.isEmpty(pdata.getPassword())) {
            return Result.build(505, "登录信息为空", null);
        }
        User user = userMapper.findByName(pdata.getUserName());
        if (Objects.isNull(user)) {
            return Result.build(506, "该用户不存在", null);
        }

        if (!pdata.getPassword().equals(user.getPassword())) {
            return Result.build(506, "该用户密码不正确", null);
        }
        redis.set(user.getName(), user.getId().toString());
        logger.info("user login info: {} ", redis.get(user.getName()));
        LoginInfoVo loginInfoVo = new LoginInfoVo();
        loginInfoVo.setUser(user);
        System.out.println(user);
        return Result.build(200, "登录成功", loginInfoVo);
    }

    @Override
    public Result<LoginInfoVo> loginOut(ResParam<LoginParam> resParam) {
        redis.delete(resParam.getPdata().getUserName());
        logger.info("user loginout info: {}", redis.get(resParam.getPdata().getUserName()));
        return Result.build(200, "退出成功", null);
    }
}
