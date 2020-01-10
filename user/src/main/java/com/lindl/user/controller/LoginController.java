package com.lindl.user.controller;

import com.alibaba.druid.util.StringUtils;
import com.lindl.user.common.ResParam;
import com.lindl.user.common.Result;
import com.lindl.user.dto.LoginParam;
import com.lindl.user.mapper.UserMapper;
import com.lindl.user.po.User;
import com.lindl.user.service.LoginService;
import com.lindl.user.vo.LoginInfoVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2019/12/30 16:51
 */
@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    /**
     * 登录
     * @param resParam
     * @return
     */
    @PostMapping(value = "/user/login")
    public Result<LoginInfoVo> login(@RequestBody ResParam<LoginParam> resParam) {
        return loginService.login(resParam);
    }

    /**
    *@Description  退出
    *@author  ldl
    *@date   2020/1/2
    */
    public Result<LoginInfoVo> loginOut(@RequestBody ResParam<LoginParam> resParam) {
        return loginService.loginOut(resParam);
    }


}
