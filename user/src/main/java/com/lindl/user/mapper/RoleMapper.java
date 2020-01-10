package com.lindl.user.mapper;

import com.lindl.user.po.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2019/12/9 14:46
 */
@Mapper
public interface RoleMapper {

    public Role selectById(Long id);

    public void save(Role role);

    public List<Role> findByUserId(Long userId);
}
