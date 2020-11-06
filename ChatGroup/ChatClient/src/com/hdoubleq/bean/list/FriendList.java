package com.hdoubleq.bean.list;

import com.hdoubleq.bean.Friend;

import java.util.ArrayList;

/**
 * @author hdoubleq
 * @Date 2020/10/31-15:34
 */
public class FriendList {
    private static ArrayList<Friend> list;
    static{
        if (list==null) {
            System.out.println("创建列表");
            list=new ArrayList<Friend>();
        }
    }

    public static void add(Friend fri){
        list.add(fri);
    }

    public static ArrayList<Friend> getList() {
        return list;
    }
}
