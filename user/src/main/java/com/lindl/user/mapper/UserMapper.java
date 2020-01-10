package com.lindl.user.mapper;


import com.lindl.user.po.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2019/12/9 14:40
 */
@Mapper
public interface UserMapper {
    public void save(User user);

    public User selectById(Long id);

    public User findByName(String name);

    public List<User> findAll();

    public List<User> findByDate(Date createTime);

    public List<User> findByDateBetween(Map<String, Date> map);

    public Integer getCountByCondition(User user);

}
