package com.lindl.user.common.exception;

import lombok.Data;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/1/13 15:42
 */
@Data
public final class MyException extends Exception {
    private static final long serialVersionUID = -2572765744958618894L;

    /**
     * 异常状态码
     */
    private int code;

    /**
    *@Description
    *@author  ldl
    *@date   2020/1/13
    */
    public MyException(int code, String message) {
        super(message);
        this.code = code;
    }
}
