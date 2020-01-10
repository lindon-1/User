package com.lindl.user.vo;

import com.lindl.user.common.CommonVo;
import com.lindl.user.po.Resource;
import com.lindl.user.po.Role;
import com.lindl.user.po.User;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2019/12/30 17:22
 */
@Data
@Accessors
public class LoginInfoVo extends CommonVo {

    private User user;

    private List<Role> roles;

    private List<Resource> resources;
}
