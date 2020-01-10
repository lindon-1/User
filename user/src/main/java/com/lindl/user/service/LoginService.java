package com.lindl.user.service;

import com.lindl.user.common.ResParam;
import com.lindl.user.common.Result;
import com.lindl.user.dto.LoginParam;
import com.lindl.user.vo.LoginInfoVo;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2019/12/30 17:47
 */

public interface LoginService {

    Result<LoginInfoVo> login(ResParam<LoginParam> resParam);

    Result<LoginInfoVo> loginOut(ResParam<LoginParam> resParam);
}
