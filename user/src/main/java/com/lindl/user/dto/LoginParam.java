package com.lindl.user.dto;

import com.lindl.user.common.CommonVo;
import lombok.Data;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2019/12/30 17:37
 */
@Data
public class LoginParam extends CommonVo {

    private String userName;

    private String password;
}
