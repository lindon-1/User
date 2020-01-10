package com.lindl.user.po;

import lombok.Data;

import java.util.Date;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2019/12/9 14:36
 */
@Data
public class Resource {
    private Long id;
    private String name;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createTime;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date updateTime;
}
