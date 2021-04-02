package com.wang.bilibuild.mapper;

import com.wang.bilibuild.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Mapper
@Repository
public interface UserMapper {

    //添加新用户
    int save(User user);


    //以id作为依据更新用户信息
    int update(User user);

    //以名称作为依据来更新
    int updateByName(User user);

    //查询全部用户信息
    Collection<User> getAll();

    //拿到所有用户的名称
    List<String> getNames();

    //通过ID查询用户
    User getUsersById(@Param("id") Integer id);

    //通过ID删除用户
    int delete(@Param("id") Integer id);

    //得到用户名列表
    List<String> getUserNames();


    //根据用户找密码

    String getPwdByName(@Param("name")String name);


    //根据名字得到权限

    Integer getStatuesByName(@Param("name") String name);

}
