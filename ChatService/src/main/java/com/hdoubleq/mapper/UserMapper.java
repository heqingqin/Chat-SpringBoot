package com.hdoubleq.mapper;

import com.hdoubleq.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author hdoubleq
 * @Date 2020/11/2-21:41
 */

//指定这是一个操作数据库的mapper，关于用户数据库的增删改查
@Mapper
public interface UserMapper {

    @Select("select * from user where acc=#{acc}")
    public User findByAcc(String acc);
    @Select("select * from user where id=#{id}")
    public User findById(int id);
    @Select("select * from user")
    public List<User> findAllUser();
    @Delete("delete from user where id=#{id}")
    public Integer deleteByAcc(int id);
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("INSERT INTO User(acc,name,pwd,who) VALUES (#{acc},#{name},#{pwd},#{who})")
    public  Integer saveUser(User user);
    @Update("update user set acc=#{acc},name=#{name},pwd=#{pwd},who=#{who} where id=#{id}")
    public int updateUser(User user);
   //查询所有身份类型
    @Select("select DISTINCT who from user")
    public List<String> findUserWho();
}
