package com.lindl.user.aop;

import java.lang.annotation.*;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2020/1/10 15:20
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserAccess {

    //自定义角色
    public String role() default "";
}
