package com.lindl.user.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2019/12/30 17:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class Result<T extends CommonVo> {
    private Integer code;

    private String msg;

    private T data;

    public static <T extends CommonVo> Result<T> build(Integer code, String msg, T data) {
        return new Result<>(code, msg, data);
    }



}
