package com.lindl.user.common.exception;

import cn.hutool.core.util.ArrayUtil;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description： 系统响应码与消息
 * @Author: ldl
 * @CreateDate: 2020/1/13 15:44
 */
public abstract class MyCodeMsg {

    private static Map<Integer, String> msg = new HashMap<>();

    /**
    *@Description异常构建
    *@author  ldl
    *@date   2020/1/13
    */
    public  MyException build(int code, String ...args) throws Exception {
        String message = msg.get(code);
        if (!ArrayUtil.isEmpty(args)) {
            String result = new String(message);
            for (int i = 0; i < args.length; i++) {
                result = result.replace("{" + i + "}", args[i]);
            }
            message = result;
        }
        return new MyException(code, message);
    }


    public static final Integer PARAMTER_MISS = 100001;

    static{
        msg.put(PARAMTER_MISS, "缺少必要参数");
    }
}
