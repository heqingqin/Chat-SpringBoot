package com.hdoubleq.service.impl.List;

import com.hdoubleq.backstage.service.Channel;
import com.hdoubleq.bean.OnLineUser;
import com.hdoubleq.bean.User;
import com.hdoubleq.mapper.UserMapper;
import com.hdoubleq.mapper.list.UserList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hdoubleq
 * @Date 2020/11/3-15:05
 */
@Component
public class OnLineUserList {

//    在线用户列表
    private static List<OnLineUser> list;
    static{
        System.out.println("列表被创建");
        if (list==null) {
            System.out.println("创建列表");
            list=new ArrayList<OnLineUser>();
        }
    }

    public List<OnLineUser> getList() {
        return list;
    }


//    将刚上线的用户添加到list
    public void setList(Channel channel) {
        User user =new UserList().findByAcc(channel.acc);
        list.add(new OnLineUser(user.getId(),user.getAcc(),user.getName(),user.getWho(),"正常"));
    }
}
