package com.hdoubleq.backstage.dao;

import com.hdoubleq.backstage.service.Channel;
import com.hdoubleq.backstage.service.ServiceStart;
import com.hdoubleq.bean.User;
import com.hdoubleq.mapper.list.UserList;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * @author hdoubleq
 * @Date 2020/11/1-22:13
 */
/*
登录验证与注册
 */
public class LoginDao {
    static String uname ="";
    static String upwd ="";
    static String reacc ="";
    static String acc ="";
    static String reupwd ="";
    static String reuname ="";
    static String reuwho ="用户";
    public static String loginDeal(String login){
        //分解信息
        String[] dataArray = login.split("&");
        System.out.println(Arrays.asList(dataArray));
        for(String info:dataArray) {
            String[] userInfo =info.split("=");
            if (userInfo[0].equals("acc")){
                System.out.println("你的账号为:"+userInfo[1]);
                acc = userInfo[1];
            }else if(userInfo[0].equals("uname")) {
                System.out.println("你的用户名为:"+userInfo[1]);
                uname = userInfo[1];
            }else if(userInfo[0].equals("upwd")) {
                System.out.println("你的密码为:"+userInfo[1]);
                upwd = userInfo[1];
            }else if(userInfo[0].equals("reacc")){
                System.out.println("你注册的账号为:"+userInfo[1]);
                reacc=userInfo[1];
            }else if(userInfo[0].equals("reuname")) {
                System.out.println("你注册的用户名为:"+userInfo[1]);
                reuname = userInfo[1];
            }else if(userInfo[0].equals("reupwd")) {
                System.out.println("你注册的密码为:"+userInfo[1]);
                reupwd = userInfo[1];
                List<User> list = UserList.getList();
                User user=null;
//                判断该账号是否被注册,如果被注册则无法进行注册
                for(User u:list){
                    if(u.getAcc().equals(reacc)){
                        user=u;
                    }
                }
                System.out.println(user);
                if (user==null){
                    user = new User();
                    user.setAcc(reacc);
                    user.setName(reuname);
                    user.setPwd(reupwd);
                    user.setWho(reuwho);
                    //实现插入到数据库
                    DBUtils regionUtils = DBUtils.getInstance();
                    int integer = 0;
                    try {
                        integer = regionUtils.saveClient(user);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    if(integer!=0) {
                        return "注册成功!";
                    }else  return "注册失败!";
                }

            }
        }

        for(Channel other: ServiceStart.getAll()) {
            if(other.acc.equals(acc)){
                System.out.println(acc+"已经在线！");
//				return "";
                return "账号为“"+acc+"”的用户已经在线!";
            }
        }
        List<User> list = UserList.getList();
        for(User info:list) {
            if((acc.equals(info.getAcc()) && upwd.equals(info.getPwd()))) {
//				return uname;    //成功
                return acc+" 登录成功";    //成功
            }
        }
        return "用户名或密码错误!";   //失败
    }
}
