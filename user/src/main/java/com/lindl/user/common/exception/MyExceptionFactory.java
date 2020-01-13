package com.lindl.user.common.exception;

import org.springframework.stereotype.Component;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/1/13 15:51
 */
public final class MyExceptionFactory {

    private MyCodeMsg myCodeMsg;

    public MyExceptionFactory(MyCodeMsg myCodeMsg) {
        this.myCodeMsg = myCodeMsg;
    }

    /**
    *@Description 异常实例获取
    *@author  ldl
    *@date   2020/1/13
    */
    public MyException getInstance(int code, String ...args) throws Exception {
        return myCodeMsg.build(code, args);
    }
}
