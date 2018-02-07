package com.yp.ypprovidermapper.mapper;

import com.yp.ypprovidermapper.Entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    @Insert("insert into user(name, password) values (#{name}, #{password})")
    void add(User user);
}
