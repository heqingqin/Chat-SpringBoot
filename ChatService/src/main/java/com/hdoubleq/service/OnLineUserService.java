package com.hdoubleq.service;

import com.hdoubleq.bean.OnLineUser;

import java.util.List;

/**
 * @author hdoubleq
 * @Date 2020/11/3-11:49
 */
public interface OnLineUserService {
//    查询所有在线用户
    public List<OnLineUser> getAllOnLineUser();
    //根据账号删除
    public void logoff(int id);
    //禁言解禁
    public boolean say(int id);
}
