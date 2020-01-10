package com.lindl.user.common;

import lombok.Data;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2019/12/30 17:30
 */
@Data
public final class ResParam<T extends CommonVo>  {

    private T pdata;
}
