package com.lindl.user.po;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2019/12/9 14:35
 */
@Data
@ToString
public class Role {
    private Long id;
    private String name;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createTime;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date updateTime;
}
