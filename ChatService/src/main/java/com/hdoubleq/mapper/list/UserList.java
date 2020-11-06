package com.hdoubleq.mapper.list;

import com.hdoubleq.bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hdoubleq
 * @Date 2020/11/3-20:20
 */
public class UserList {
    public static List<User> list;

   static {
      if (list==null){
          list=new ArrayList<>();
      }
    }

    public static List<User> getList() {
        return list;
    }

    public static void setList(List<User> list) {
        UserList.list = list;
    }

    public User findByAcc(String acc){
       for (User user:list){
           if (user.getAcc().equals(acc)){
               return user;
           }
       }
      return null;
    }

}
