package com.hdoubleq.dao;

import com.hdoubleq.bean.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hdoubleq
 * @Date 2020/11/2-19:28
 */
@Repository
public interface UserDao {
    //    添加用户
    Integer saveUser(User user);
    //根据账号删除用户
    Integer deleteByAcc(String acc);
    //根据账号查找用户
    User findByAcc(String acc);
    //查询所有用户
     List<User> findAllUser();
}
