package com.lindl.user.po;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2019/12/9 11:23
 */
@Data
@Accessors
@ToString
public class User {
    private Long id;
    private String password;
    private String name;
    //只会在类似@ResponseBody返回json数据的时候，才会返回格式化的yyyy-MM-dd HH:mm:ss时间，你直接使用System.out.println()输出的话，仍然是类似“Fri Dec 01 21:05:20 CST 2017”这样的时间样式。
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createTime;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date updateTime;
}
